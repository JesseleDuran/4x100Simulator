package models;

import java.util.List;
import java.util.Set;

public class Equipo {

    private int id;

    private String nombre;

    private Set<Corredor> integrantes;

    private List<Carrera> carreraList;

    public Equipo() {
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(int id, String nombre) {
        this.id = id;
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
                ", integrantes=" + integrantes +
                ", carreraList=" + carreraList +
                '}';
    }
}