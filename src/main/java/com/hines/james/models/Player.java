package com.hines.james.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    @Id
    @GeneratedValue
    Long id;
    String name;
    String position;
    Integer age;
    Integer height;
    Integer weight;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="playerId")
    Set<Fan> fans;

    public Player() {
        super();
    }
    public Player(String name,
                  String position,
                  Integer age,
                  Integer height,
                  Integer weight,
                  Set<Fan> fans) {
        this();
        this.position = position;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.fans = fans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Set<Fan> getFans() {
        return fans;
    }

    public void setFans(Set<Fan> fans) {
        this.fans = fans;
    }
}