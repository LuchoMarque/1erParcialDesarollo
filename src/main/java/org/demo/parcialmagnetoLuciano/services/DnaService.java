package org.demo.parcialmagnetoLuciano.services;

import org.demo.parcialmagnetoLuciano.entities.Dna;
import org.demo.parcialmagnetoLuciano.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class DnaService {

    @Autowired
    private DnaRepository dnaRepository;

    public boolean isMutant(String[] dna) {
        // Validar que el ADN solo contenga A, T, G, C
        if (!isValidDna(dna)) {
            throw new IllegalArgumentException("DNA must only contain the characters A, T, G, C.");
        }

        int sequenceCount = 0; // Contador de secuencias encontradas
        int size = dna.length;

        // Verificar horizontal, vertical y diagonal usando métodos separados
        sequenceCount += checkAllHorizontal(dna,size);
        sequenceCount += checkAllVertical(dna,size);
        sequenceCount += checkAllDiagonals(dna,size);

        // Es mutante si encuentra más de una secuencia
        return sequenceCount > 1;
    }

    //Método para validar que el ADN solo contenga A, T, G, C
    private boolean isValidDna(String[] dna) {
        return IntStream.range(0, dna.length)
                .allMatch(i -> dna[i].matches("[ATGC]+")); // Verificar que cada fila contenga solo A, T, G, C
    }


    private int checkAllHorizontal(String[] dna,int size) {
        return IntStream.range(0, size)
                .map(i -> checkSequence(dna[i]))
                .sum();
    }

    private int checkAllVertical(String[] dna,int size) {
        return IntStream.range(0, size)
                .map(j -> {
                    StringBuilder column = new StringBuilder();
                    IntStream.range(0, dna.length)
                            .forEach(i -> column.append(dna[i].charAt(j)));
                    return checkSequence(column.toString());
                })
                .sum();
    }

    private int checkAllDiagonals(String[] dna,int size) {

        // Diagonales de izquierda a derecha
        int leftToRightDiagonals = IntStream.range(0, size)
                .map(i -> {
                    StringBuilder diagonal = new StringBuilder();
                    IntStream.range(0, i + 1)
                            .filter(j -> i - j < size)
                            .forEach(j -> diagonal.append(dna[i - j].charAt(j)));
                    return checkSequence(diagonal.toString());
                })
                .sum();

        // Diagonales de derecha a izquierda
        int rightToLeftDiagonals = IntStream.range(0, size)
                .map(i -> {
                    StringBuilder diagonal = new StringBuilder();
                    IntStream.range(0, i + 1)
                            .filter(j -> i - j < size)
                            .forEach(j -> diagonal.append(dna[i - j].charAt(size - 1 - j)));
                    return checkSequence(diagonal.toString());
                })
                .sum();

        return leftToRightDiagonals + rightToLeftDiagonals;
    }

    private int checkSequence(String sequence) {
        int count = 0;
        char currentChar = sequence.charAt(0);
        int currentStreak = 1;

        // Contar secuencias de 4 caracteres iguales
        for (int i = 1; i < sequence.length(); i++) {
            if (sequence.charAt(i) == currentChar) {
                currentStreak++;
                if (currentStreak == 4) {
                    count++;
                    currentStreak = 0; // Reiniciar para contar otras secuencias
                }
            } else {
                currentChar = sequence.charAt(i);
                currentStreak = 1;
            }
        }

        return count;
    }

    public boolean saveDna(String[] dna) {
        String dnaSequence = String.join(",", dna);


        // Verificar si ya existe un registro con este ADN
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);

        // Si ya existe, no guardar de nuevo
        if (existingDna.isPresent()) {
            return existingDna.get().isMutant();  // Retorna el registro existente
        }

        // Determinamos si el ADN es mutante y lo guardamos en la base de datos
        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(dnaEntity);

        return isMutant(dna);
    }
}