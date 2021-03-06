
package model;
import java.time.LocalDate;

public class Team {

    private String nombre;
    private String localidad;
    private LocalDate fechac;

    public Team() {}

    public Team(String nombre, String localidad, LocalDate fechac) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.fechac = fechac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public LocalDate getFechac() {
        return fechac;
    }

    public void setFechac(LocalDate fechac) {
        this.fechac = fechac;
    }

    @Override
    public String toString() {
        return "Team{" + "nombre=" + nombre + ", localidad=" + localidad + ", fechac=" + fechac + '}';
    }


}