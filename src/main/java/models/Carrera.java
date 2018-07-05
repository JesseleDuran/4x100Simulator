package models;

public class Carrera {

    private int id_carrera;

    private String nombre_carrera;

    private String fecha;

    private int id_equipo_ganador1;

    private int id_equipo_ganador2;

    private int id_equipo_ganador3;

    public Carrera() {
    }

    public Carrera(String nombre_carrera, String fecha, int id_equipo_ganador1, int id_equipo_ganador2, int id_equipo_ganador3) {
        this.nombre_carrera = nombre_carrera;
        this.fecha = fecha;
        this.id_equipo_ganador1 = id_equipo_ganador1;
        this.id_equipo_ganador2 = id_equipo_ganador2;
        this.id_equipo_ganador3 = id_equipo_ganador3;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_equipo_ganador1() {
        return id_equipo_ganador1;
    }

    public void setId_equipo_ganador1(int id_equipo_ganador1) {
        this.id_equipo_ganador1 = id_equipo_ganador1;
    }

    public int getId_equipo_ganador2() {
        return id_equipo_ganador2;
    }

    public void setId_equipo_ganador2(int id_equipo_ganador2) {
        this.id_equipo_ganador2 = id_equipo_ganador2;
    }

    public int getId_equipo_ganador3() {
        return id_equipo_ganador3;
    }

    public void setId_equipo_ganador3(int id_equipo_ganador3) {
        this.id_equipo_ganador3 = id_equipo_ganador3;
    }
}
