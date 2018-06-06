package models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carrera")
public class Carrera {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "tiempo_total")
    private String tiempo_total;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name="id", nullable=false) //FK from equipo
    @Column(name = "id_equipo_ganador1")
    private Equipo equipo_ganador1;

    @ManyToOne
    @JoinColumn(name="id", nullable=false) //FK from equipo
    @Column(name = "id_equipo_ganador2")
    private Equipo equipo_ganador2;

    @ManyToOne
    @JoinColumn(name="id", nullable=false) //FK from equipo
    @Column(name = "id_equipo_ganador3")
    private Equipo equipo_ganador3;

    @ManyToOne
    @JoinColumn(name="id", nullable=false) //FK from equipo
    @Column(name = "id_corredor_ganador_fase1")
    private Corredor corredor_ganador1;

    @ManyToOne
    @JoinColumn(name="id", nullable=false) //FK from equipo
    @Column(name = "id_corredor_ganador_fase2")
    private Corredor corredor_ganador2;

    @ManyToOne
    @JoinColumn(name="id", nullable=false) //FK from equipo
    @Column(name = "id_corredor_ganador_fase3")
    private Corredor corredor_ganador3;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "carrera_equipo",
            joinColumns = @JoinColumn(name = "id_carrera", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_equipo", referencedColumnName = "id"))
    private List<Equipo> equipoList;

    public Carrera(String tiempo_total, Date fecha) {
        this.tiempo_total = tiempo_total;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Corredor getCorredor_ganador1() {
        return corredor_ganador1;
    }

    public void setCorredor_ganador1(Corredor corredor_ganador1) {
        this.corredor_ganador1 = corredor_ganador1;
    }

    public Corredor getCorredor_ganador2() {
        return corredor_ganador2;
    }

    public void setCorredor_ganador2(Corredor corredor_ganador2) {
        this.corredor_ganador2 = corredor_ganador2;
    }

    public Corredor getCorredor_ganador3() {
        return corredor_ganador3;
    }

    public void setCorredor_ganador3(Corredor corredor_ganador3) {
        this.corredor_ganador3 = corredor_ganador3;
    }
}
