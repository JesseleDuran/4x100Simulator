package models;

public class CarreraCorredor {

    private int id_carrera;

    private int id_corredor;

    private String tiempo_fase;

    public CarreraCorredor() {
    }

    public CarreraCorredor(int id_carrera, int id_corredor, String tiempo_fase) {
        this.id_carrera = id_carrera;
        this.id_corredor = id_corredor;
        this.tiempo_fase = tiempo_fase;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public int getId_corredor() {
        return id_corredor;
    }

    public void setId_corredor(int id_corredor) {
        this.id_corredor = id_corredor;
    }

    public String getTiempo_fase() {
        return tiempo_fase;
    }

    public void setTiempo_fase(String tiempo_fase) {
        this.tiempo_fase = tiempo_fase;
    }
}
