package DTO;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseDTO {
    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("results")
    private List<Pregunta> resultados;

    public int getResponseCode() {return responseCode;}

    public void setResponseCode(int responseCode) {this.responseCode = responseCode;}

    public List<Pregunta> getResultados() {return resultados;}
    public void setResultados(List<Pregunta> resultados) {this.resultados = resultados;}

    
}