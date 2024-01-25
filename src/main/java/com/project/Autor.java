package com.project;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Autor {

    public Autor() {}


    public Autor (String nom) {
        this.nom = nom;
    }


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;


    @Column(name = "nom")
    private String nom;


    @ManyToMany(mappedBy = "autors")
    private Set<Llibre> llibres;


    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-15s|",
                autorId, nom);
    }

}