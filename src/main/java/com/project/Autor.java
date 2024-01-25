package com.project;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autorId")
    private Long autorId;

    @Column(name = "nom")
    private String nom;

    @ManyToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Llibre> llibres;


    public Long getAutorId() {
        return this.autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Llibre> getLlibres() {
        return this.llibres;
    }

    public void setLlibres(List<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return "{" +
            " autorId='" + getAutorId() + "'" +
            ", nom='" + getNom() + "'" +
            ", llibres='" + getLlibres() + "'" +
            "}";
    }

    
}
