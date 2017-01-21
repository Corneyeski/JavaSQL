
package model;
import java.time.LocalDate;

public class Jugador {

    private String nombre;
    private LocalDate fechan;
    private int canasto;
    private int asisto;
    private int reboto;
    private String posicion;
    private Team equipo;

    public Jugador() {}

    public Jugador(Jugador jugador) {
    }

    public Jugador(String nombre, LocalDate fechan, int canasto, int asisto, int reboto, String posicion, Team equipo) {
        this.nombre = nombre;
        this.fechan = fechan;
        this.canasto = canasto;
        this.asisto = asisto;
        this.reboto = reboto;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechan() {
        return fechan;
    }

    public void setFechan(LocalDate fechan) {
        this.fechan = fechan;
    }

    public int getCanasto() {
        return canasto;
    }

    public void setCanasto(int canasto) {
        this.canasto = canasto;
    }

    public int getAsisto() {
        return asisto;
    }

    public void setAsisto(int asisto) {
        this.asisto = asisto;
    }

    public int getReboto() {
        return reboto;
    }

    public void setReboto(int reboto) {
        this.reboto = reboto;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Team getEquipo() {
        return equipo;
    }

    public void setEquipo(Team equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", fechan=" + fechan + ", canasto=" + canasto + ", asisto=" + asisto + ", reboto=" + reboto + ", posicion=" + posicion + ", equipo=" + equipo + '}';
    }
}
