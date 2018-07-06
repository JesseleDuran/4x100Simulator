package models;

public class CarreraEquipo {

    private int id_carrera;

    private int id_equipo;

    private String tiempo_equipo;

    private int n_posicion;

    public CarreraEquipo() {
    }

    public CarreraEquipo(int id_carrera, int id_equipo, String tiempo_equipo, int n_posicion) {
        this.id_carrera = id_carrera;
        this.id_equipo = id_equipo;
        this.tiempo_equipo = tiempo_equipo;
        this.n_posicion = n_posicion;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getTiempo_equipo() {
        return tiempo_equipo;
    }

    public void setTiempo_equipo(String tiempo_equipo) {
        this.tiempo_equipo = tiempo_equipo;
    }

    public int getN_posicion() {
        return n_posicion;
    }

    public void setN_posicion(int n_posicion) {
        this.n_posicion = n_posicion;
    }
}
