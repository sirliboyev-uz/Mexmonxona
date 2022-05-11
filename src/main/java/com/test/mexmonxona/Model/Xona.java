package com.test.mexmonxona.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Xona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nomi;

    @Column(nullable = false)
    private Integer qavati;

    @Column(nullable = false)
    private Integer xonalarsoni;

    @ManyToOne
    Mexmonxona mexmonxona;
}
