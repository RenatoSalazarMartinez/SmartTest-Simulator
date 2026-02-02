
package com.mycompany.smarttestsimulator;

import API.APIClient;
import API.PreguntaService;
import DAO.PreguntaDAO;
import DTO.Pregunta;
import java.util.List;

public class SmartTestSimulator {

    public static void main(String[] args) {
        Conexion.ConexionSQL.conectar();
//        // 1. Instanciamos el servicio
//        PreguntaService servicio = new PreguntaService();
//
//        // 2. Llamamos al método que conecta todo
//        System.out.println("Solicitando preguntas a la API... ⏳");
//        List<Pregunta> lista = servicio.obtenerPreguntasDeInternet();
//
//        // 3. Verificamos los resultados
//        if (lista != null && !lista.isEmpty()) {
//            System.out.println("¡Exito! Se han cargado " + lista.size() + " preguntas.");
//
//            // Imprimimos la primera pregunta para ver si los datos están ahí
//            Pregunta primera = lista.get(0);
//            System.out.println("\n--- Ejemplo de Pregunta Cargada ---");
//            System.out.println("Categoría: " + primera.getCategoria());
//            System.out.println("Enunciado: " + primera.getEnunciado());
//            System.out.println("Respuesta Correcta: " + primera.getRespuestaCorrecta());
//            System.out.println("Respuestas Incorrectas: " + primera.getRespuestasIncorrectas());
//        } else {
//            System.out.println("No se pudieron obtener las preguntas. Revisa tu conexión.");
//        }

// 1. Obtenemos las preguntas de la API
        PreguntaService servicio = new PreguntaService();
        System.out.println("Buscando preguntas nuevas en la API...");
        List<Pregunta> desdeAPI = servicio.obtenerPreguntasDeInternet();

        // 2. Si recibimos datos, intentamos guardarlos en la BD
        if (desdeAPI != null && !desdeAPI.isEmpty()) {
            PreguntaDAO dao = new PreguntaDAO();
            System.out.println("Iniciando guardado en Base de Datos...");
            dao.guardarListaSiNoExisten(desdeAPI);
        } else {
            System.out.println("No se obtuvieron preguntas de la API.");
        }
    }
}
