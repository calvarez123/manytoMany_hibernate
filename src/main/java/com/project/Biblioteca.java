package com.project;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "biblioteca")
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bibliotecaId")
    private Long bibliotecaId;  // Cambiado a Long

    @Column(name = "nom")
    private String nom;

    @Column(name = "ciutat")
    private String ciutat;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "prestecs",
        joinColumns = @JoinColumn(name = "bibliotecaId"),
        inverseJoinColumns = @JoinColumn(name = "llibreId")
    )
    private List<Llibre> llibres;



    public Biblioteca() {}


    public Biblioteca(long bibliotecaId, String nom) {
        this.bibliotecaId = bibliotecaId;
        this.nom = nom;
        
    }
    

    public String getCiutat() {
        return this.ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public List<Llibre> getLlibres() {
        return this.llibres;
    }

    public void setLlibres(List<Llibre> llibres) {
        this.llibres = llibres;
    }


    public long getBibliotecaId() {
        return this.bibliotecaId;
    }

    public void setBibliotecaId(long bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    @Override
    public String toString() {
        return "{" +
            " bibliotecaId='" + getBibliotecaId() + "'" +
            ", nom='" + getNom();
    }
    

}
