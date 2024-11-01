package org.demo.parcialmagnetoLuciano.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.util.Arrays;
public class DnaValidator implements ConstraintValidator<ValidDna, String[]> {

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {

        if (dna == null || dna.length == 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El ADN no puede estar vacio ")
                    .addConstraintViolation();
            return false;
        }


        int size = dna.length;
        if (!Arrays.stream(dna).allMatch(row -> row.length() == size)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El ADN debe ser cuadrado (NxN)")
                    .addConstraintViolation();
            return false;
        }

        if (!Arrays.stream(dna).allMatch(row -> row.matches("[ATGC]+"))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El array solo debe contener los caracteres: 'A', 'T', 'G', 'C'")
                    .addConstraintViolation();
            return false;
        }

        // Validar que no sea homogéneo (todo el array igual)
        char firstChar = dna[0].charAt(0);
        boolean isHomogeneous = Arrays.stream(dna)
                .allMatch(row -> row.chars().allMatch(ch -> ch == firstChar));
        if (isHomogeneous) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("EL ADN no puede tener todas las letras iguales")
                    .addConstraintViolation();
            return false;
        }

        // Si pasa todas las validaciones, es válido
        return true;
    }
}
