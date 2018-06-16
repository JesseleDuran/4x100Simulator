package models;

public class Corredor {


    private int id;

    private Equipo equipo;

    private String nombre;

    private String apellido;

    private int velocidad;

    private int velocidad_curva;

    private int velocidad_promedio;

    private int prob_terminar;

    private int tiempo_total;

    private int tipo_pantalla_viene;

    private int fase_inicial; //en que fase de la carrera se posicionara al inicio

    public Corredor(String nombre, String apellido, int velocidad, int velocidad_curva, int velocidad_promedio, int prob_terminar, int fase_inicial) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.velocidad = velocidad;
        this.velocidad_curva = velocidad_curva;
        this.velocidad_promedio = velocidad_promedio;
        this.prob_terminar = prob_terminar;
        this.fase_inicial = fase_inicial;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVelocidad_curva() {
        return velocidad_curva;
    }

    public void setVelocidad_curva(int velocidad_curva) {
        this.velocidad_curva = velocidad_curva;
    }

    public int getVelocidad_promedio() {
        return velocidad_promedio;
    }

    public void setVelocidad_promedio(int velocidad_promedio) {
        this.velocidad_promedio = velocidad_promedio;
    }

    public int getProb_terminar() {
        return prob_terminar;
    }

    public void setProb_terminar(int prob_terminar) {
        this.prob_terminar = prob_terminar;
    }

    public int getTiempo_total() {
        return tiempo_total;
    }

    public void setTiempo_total(int tiempo_total) {
        this.tiempo_total = tiempo_total;
    }

    public int getTipo_pantalla_viene() {
        return tipo_pantalla_viene;
    }

    public void setTipo_pantalla_viene(int tipo_pantalla_viene) {
        this.tipo_pantalla_viene = tipo_pantalla_viene;
    }

    public int getFase_inicial() {
        return fase_inicial;
    }

    public void setFase_inicial(int fase_inicial) {
        this.fase_inicial = fase_inicial;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
