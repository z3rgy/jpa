package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit {

    @Id
//    @Column(name = "unit_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String unitName;

    @OneToMany(mappedBy = "unit")
    private List<Consumption> consumption = new ArrayList<>();

}

