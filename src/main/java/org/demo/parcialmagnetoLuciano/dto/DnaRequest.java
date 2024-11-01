package org.demo.parcialmagnetoLuciano.dto;

import lombok.Getter;
import lombok.Setter;
import org.demo.parcialmagnetoLuciano.validators.ValidDna;

@Getter
@Setter
public class DnaRequest {
    @ValidDna
    private String[] dna;
}
