package com.project;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personaId")
    private Long personaId;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nom")
    private String nom;

    @Column(name = "telefon")
    private String telefon;

    @ManyToMany
    @JoinTable(
        name = "prestecs",
        joinColumns = @JoinColumn(name = "personaId"),
        inverseJoinColumns = @JoinColumn(name = "llibreId")
    )
    private List<Llibre> llibres;

    public Persona() {}

    public Persona(Long personaId, String dni, String nom, String telefon, List<Llibre> llibres) {
        this.personaId = personaId;
        this.dni = dni;
        this.nom = nom;
        this.telefon = telefon;
        this.llibres = llibres;
    }

    public Long getPersonaId() {
        return this.personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<Llibre> getLlibres() {
        return this.llibres;
    }

    public void setLlibres(List<Llibre> llibres) {
        this.llibres = llibres;
    }
    
}
