
package DTO;
import java.util.List;

public class Examen {
    private int idExamen;
    private List<Pregunta> preguntas;
    private int indiceActual, puntaje, tiempoRestante;

    public Examen() {
    }

    public Examen(int idExamen, List<Pregunta> preguntas, int indiceActual, int puntaje, int tiempoRestante) {
        this.idExamen = idExamen;
        this.preguntas = preguntas;
        this.indiceActual = indiceActual;
        this.puntaje = puntaje;
        this.tiempoRestante = tiempoRestante;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getIndiceActual() {
        return indiceActual;
    }

    public void setIndiceActual(int indiceActual) {
        this.indiceActual = indiceActual;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }
    
    
}
