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


}
