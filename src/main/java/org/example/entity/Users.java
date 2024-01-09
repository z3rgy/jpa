package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Users {

    @Id
//    @Column(name = "user_id")
    private Long Id;

    private String username;

    private Integer age;

    @OneToMany(mappedBy = "users")
    private List<Consumption> consumption = new ArrayList<>();
}
