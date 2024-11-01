package org.demo.parcialmagneto.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ADN")
public class Dna extends Base implements Serializable {

    private String dna;

    private boolean isMutant;
}
