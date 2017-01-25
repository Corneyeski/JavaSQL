package DTO;

/**
 * Created by alanv on 21/01/2017.
 */
public class RankingDTO {

    private String nombre;
    private int puntos;

    public RankingDTO() {
    }

    public RankingDTO(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return nombre + " " + puntos;
    }
}
