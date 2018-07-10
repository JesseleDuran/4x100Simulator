package models;

public class Equipo {

    private int id_equipo;

    private String nombre_equipo;

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

    @Override
    public String toString() {
        return "Equipo{" +
                "id_equipo=" + id_equipo +
                ", nombre_equipo='" + nombre_equipo + '\'' +
                '}';
    }
}