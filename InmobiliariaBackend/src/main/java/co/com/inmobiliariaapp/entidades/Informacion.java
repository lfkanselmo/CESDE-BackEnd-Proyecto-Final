package co.com.inmobiliariaapp.entidades;

public class Informacion {
    private Long idInformacion;
    private int habitaciones;
    private int banhod;
    private boolean patio;
    private int nivel;
    private double area;
    private boolean gasNatural;
    private boolean zonaRopa;

    public Long getIdInformacion() {
        return idInformacion;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getBanhod() {
        return banhod;
    }

    public void setBanhod(int banhod) {
        this.banhod = banhod;
    }

    public boolean isPatio() {
        return patio;
    }

    public void setPatio(boolean patio) {
        this.patio = patio;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isGasNatural() {
        return gasNatural;
    }

    public void setGasNatural(boolean gasNatural) {
        this.gasNatural = gasNatural;
    }

    public boolean isZonaRopa() {
        return zonaRopa;
    }

    public void setZonaRopa(boolean zonaRopa) {
        this.zonaRopa = zonaRopa;
    }
}
