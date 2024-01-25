package com.project;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Persona {


    public Persona() {}


    public Persona (String dni, String nom,String telefon ) {
        this.nom = nom;
        this.dni = dni;
        this.telefon = telefon;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long personaId;

    @Column(name = "dni")
    private String dni;
    @Column(name = "nom")
    private String nom;
    @Column(name = "telefon")
    private String telefon;

    @ManyToMany
    @JoinTable(
            name = "persona_llibre",
            joinColumns = @JoinColumn(name = "personaId"),
            inverseJoinColumns = @JoinColumn(name = "llibreId"))
    private Set<Llibre> llibres;

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-15s | %-15s | %-15s |",
                personaId, dni, nom, telefon);
    }
    

}
