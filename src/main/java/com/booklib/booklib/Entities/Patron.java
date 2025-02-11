package com.booklib.booklib.Entities;


import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table
@ToString
public class Patron {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactInformation;

    public Patron(){

    }
    public Patron(String name, String contactInformation){
        this.contactInformation = contactInformation;
        this.name = name;
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

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
}
