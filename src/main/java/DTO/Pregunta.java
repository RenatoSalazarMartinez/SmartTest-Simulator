
package DTO;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Pregunta {
    
    private int idPregunta;
    
    @SerializedName("question")
    private String enunciado;
    
    @SerializedName("category")
    private String categoria;
    
    @SerializedName("difficulty")
    private String dificultad;
    
    @SerializedName("correct_answer")
    private String respuestaCorrecta;
    
    @SerializedName("incorrect_answers")
    private List<String> respuestasIncorrectas;

   public Pregunta(){
   }
    
    public Pregunta(int idPregunta, String enunciado, String categoria, String dificultad, String respuestaCorrecta, List<String> respuestasIncorrectas) {
        this.idPregunta = idPregunta;
        this.enunciado = enunciado;
        this.categoria = categoria;
        this.dificultad = dificultad;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestasIncorrectas = respuestasIncorrectas;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public List<String> getRespuestasIncorrectas() {
        return respuestasIncorrectas;
    }

    public void setRespuestasIncorrectas(List<String> respuestasIncorrectas) {
        this.respuestasIncorrectas = respuestasIncorrectas;
    }
    
    
}
