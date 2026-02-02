
package DTO;

import java.time.LocalDateTime;

public class Historial {
    private int idHistorial;
    private LocalDateTime fecha;
    private int aciertos, fallos;
    private String categoria;

    public Historial() {
    }

    public Historial(int idHistorial, LocalDateTime fecha, int aciertos, int fallos, String categoria) {
        this.idHistorial = idHistorial;
        this.fecha = fecha;
        this.aciertos = aciertos;
        this.fallos = fallos;
        this.categoria = categoria;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
