package models;

import java.util.Date;
import java.util.List;


public class Carrera {

    private int id_carrera;

    private String nombre_carrera;

    private String tiempo_total;

    private Date fecha;

    private Equipo equipo_ganador1;

    private Equipo equipo_ganador2;

    private Equipo equipo_ganador3;

    private List<Equipo> equipoList;

    public Carrera() {
    }

    public Carrera(String nombre_carrera, String tiempo_total, Date fecha, Equipo equipo_ganador1, Equipo equipo_ganador2, Equipo equipo_ganador3, List<Equipo> equipoList) {
        this.nombre_carrera = nombre_carrera;
        this.tiempo_total = tiempo_total;
        this.fecha = fecha;
        this.equipo_ganador1 = equipo_ganador1;
        this.equipo_ganador2 = equipo_ganador2;
        this.equipo_ganador3 = equipo_ganador3;
        this.equipoList = equipoList;
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

    public String getTiempo_total() {
        return tiempo_total;
    }

    public void setTiempo_total(String tiempo_total) {
        this.tiempo_total = tiempo_total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Equipo getEquipo_ganador1() {
        return equipo_ganador1;
    }

    public void setEquipo_ganador1(Equipo equipo_ganador1) {
        this.equipo_ganador1 = equipo_ganador1;
    }

    public Equipo getEquipo_ganador2() {
        return equipo_ganador2;
    }

    public void setEquipo_ganador2(Equipo equipo_ganador2) {
        this.equipo_ganador2 = equipo_ganador2;
    }

    public Equipo getEquipo_ganador3() {
        return equipo_ganador3;
    }

    public void setEquipo_ganador3(Equipo equipo_ganador3) {
        this.equipo_ganador3 = equipo_ganador3;
    }

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }
}
