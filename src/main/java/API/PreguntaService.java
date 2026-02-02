package API;

import DTO.Pregunta;
import DTO.ResponseDTO;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class PreguntaService {
    private APIClient apiClient;
    private Gson gson;

    public PreguntaService() {
        this.apiClient = new APIClient();
        this.gson = new Gson();
    }

    public List<Pregunta> obtenerPreguntasDeInternet() {
        String json = apiClient.obtenerJsonDeAPI();

        if (json != null) {
            ResponseDTO respuesta = gson.fromJson(json, ResponseDTO.class);

            if (respuesta != null && respuesta.getResponseCode() == 0) {
                List<Pregunta> listaLimpia = respuesta.getResultados();
                
                // Procesamos cada pregunta para quitar los códigos HTML
                for (Pregunta p : listaLimpia) {
                    p.setEnunciado(limpiarTexto(p.getEnunciado()));
                    p.setRespuestaCorrecta(limpiarTexto(p.getRespuestaCorrecta()));
                    
                    // Limpiamos la lista de incorrectas
                    List<String> incLimpias = new ArrayList<>();
                    for (String inc : p.getRespuestasIncorrectas()) {
                        incLimpias.add(limpiarTexto(inc));
                    }
                    p.setRespuestasIncorrectas(incLimpias);
                }
                return listaLimpia;
            }
        }
        return null;
    }

    // El "filtro" que convierte códigos web en símbolos legibles 🧼
    private String limpiarTexto(String texto) {
        if (texto == null) return null;
        
        return texto.replace("&quot;", "\"")
                    .replace("&#039;", "'")
                    .replace("&amp;", "&")
                    .replace("&iacute;", "í")
                    .replace("&aacute;", "á")
                    .replace("&eacute;", "é")
                    .replace("&oacute;", "ó")
                    .replace("&uacute;", "ú")
                    .replace("&ntilde;", "ñ")
                    .replace("&deg;", "°");
    }
}