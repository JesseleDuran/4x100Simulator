package models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "equipo")
public class Equipo {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", unique=true, nullable=false)
    private String nombre;

    @OneToMany(mappedBy="equipo")
    private Set<Corredor> integrantes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "carrera_equipo",
            joinColumns = @JoinColumn(name = "id_equipo", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_carrera", referencedColumnName = "id"))
    private List<Carrera> carreraList;


    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Set<Corredor> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(Set<Corredor> integrantes) {
        this.integrantes = integrantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}