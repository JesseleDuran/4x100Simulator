package models;

import java.util.List;

public class Equipo {

    private int id_equipo;

    private String nombre_equipo;

    private List<Corredor> integrantes;

    private List<Carrera> carreraList;

    public Equipo() {
    }

    public Equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public Equipo(int id_equipo, String nombre_equipo) {
        this.id_equipo = id_equipo;
        this.nombre_equipo = nombre_equipo;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public List<Corredor> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Corredor> integrantes) {
        this.integrantes = integrantes;
    }

    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id_equipo +
                ", nombre='" + nombre_equipo + '\'' +
                ", integrantes=" + integrantes +
                ", carreraList=" + carreraList +
                '}';
    }
}