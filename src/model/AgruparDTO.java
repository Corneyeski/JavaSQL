
package model;
public class AgruparDTO {

    private String posicion;
    private Double mediac;
    private int minimoc;
    private int maximoc;
    private Double mediaa;
    private int minimoa;
    private int maximoa;
    private Double mediar;
    private int minimor;
    private int maximor;

    public AgruparDTO() {
    }

    public AgruparDTO(String posicion, Double mediac, int minimoc, int maximoc, Double mediaa, int minimoa, int maximoa, Double mediar, int minimor, int maximor) {
        this.posicion = posicion;
        this.mediac = mediac;
        this.minimoc = minimoc;
        this.maximoc = maximoc;
        this.mediaa = mediaa;
        this.minimoa = minimoa;
        this.maximoa = maximoa;
        this.mediar = mediar;
        this.minimor = minimor;
        this.maximor = maximor;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Double getMediac() {
        return mediac;
    }

    public void setMediac(Double mediac) {
        this.mediac = mediac;
    }

    public int getMinimoc() {
        return minimoc;
    }

    public void setMinimoc(int minimoc) {
        this.minimoc = minimoc;
    }

    public int getMaximoc() {
        return maximoc;
    }

    public void setMaximoc(int maximoc) {
        this.maximoc = maximoc;
    }

    public Double getMediaa() {
        return mediaa;
    }

    public void setMediaa(Double mediaa) {
        this.mediaa = mediaa;
    }

    public int getMinimoa() {
        return minimoa;
    }

    public void setMinimoa(int minimoa) {
        this.minimoa = minimoa;
    }

    public int getMaximoa() {
        return maximoa;
    }

    public void setMaximoa(int maximoa) {
        this.maximoa = maximoa;
    }

    public Double getMediar() {
        return mediar;
    }

    public void setMediar(Double mediar) {
        this.mediar = mediar;
    }

    public int getMinimor() {
        return minimor;
    }

    public void setMinimor(int minimor) {
        this.minimor = minimor;
    }

    public int getMaximor() {
        return maximor;
    }

    public void setMaximor(int maximor) {
        this.maximor = maximor;
    }

}
