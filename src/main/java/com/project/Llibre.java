package com.project;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "llibre")
public class Llibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "llibreId")
    private Long llibreId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "editorial")
    private String editorial;

    @ManyToMany
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "llibres")
    private List<Biblioteca> biblioteques;


    @ManyToMany(mappedBy = "llibres")
    private List<Persona> persones;


    public Llibre() {
        //TODO Auto-generated constructor stub
    }

    public Long getLlibreId() {
        return this.llibreId;
    }

    public void setLlibreId(Long llibreId) {
        this.llibreId = llibreId;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return this.autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Biblioteca> getBiblioteques() {
        return this.biblioteques;
    }

    public void setBiblioteques(List<Biblioteca> biblioteques) {
        this.biblioteques = biblioteques;
    }

    public List<Persona> getPersones() {
        return this.persones;
    }

    public void setPersones(List<Persona> persones) {
        this.persones = persones;
    }


    @Override
    public String toString() {
        return "{" +
            " llibreId='" + getLlibreId() + "'" +
            ", nom='" + getNom() + "'" +
            ", editorial='" + getEditorial() + "'" +
            ", autor='" + getAutor() + "'" +
            ", biblioteques='" + getBiblioteques() + "'" +
            ", persones='" + getPersones() + "'" +
            "}";
    }

    
}
