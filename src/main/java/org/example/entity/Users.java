package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String username;


}