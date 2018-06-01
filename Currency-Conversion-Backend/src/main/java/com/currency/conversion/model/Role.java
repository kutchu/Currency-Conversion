package com.currency.conversion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ksrivas on 6/1/2018.
 */
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Role(){
    }

    public Role(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "Role{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
