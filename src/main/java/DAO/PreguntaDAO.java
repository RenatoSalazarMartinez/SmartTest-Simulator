
package DAO;

import Conexion.ConexionSQL;
import DTO.Pregunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.LinkedHashMap;

public class PreguntaDAO {

    public boolean insertar(Pregunta p) {
        String sqlPregunta = "INSERT INTO Pregunta (enunciado, categoria, dificultad, respuestaCorrecta) VALUES (?, ?, ?, ?)";
        String sqlRespuesta = "INSERT INTO respuestasincorrectas (idPregunta, respuesta) VALUES (?, ?)";

        try (Connection con = ConexionSQL.conectar()) {
            // Desactivamos el autocommit para tratar todo como una sola transacción
            con.setAutoCommit(false);

            try (PreparedStatement psP = con.prepareStatement(sqlPregunta, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psP.setString(1, p.getEnunciado());
                psP.setString(2, p.getCategoria());
                psP.setString(3, p.getDificultad());
                psP.setString(4, p.getRespuestaCorrecta());
                psP.executeUpdate();

                // Obtenemos el ID generado automáticamente 
                ResultSet rs = psP.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);

                    // Insertamos cada respuesta incorrecta de la List<String>
                    try (PreparedStatement psR = con.prepareStatement(sqlRespuesta)) {
                        for (String respuesta : p.getRespuestasIncorrectas()) {
                            psR.setInt(1, idGenerado);
                            psR.setString(2, respuesta);
                            psR.executeUpdate();
                        }
                    }
                }
                con.commit(); // Si todo salio bien, guardamos cambios
                return true;
            } catch (SQLException e) {
                con.rollback(); // Si algo fallo, deshacemos todo para no dejar datos huérfanos
                System.out.println("Error en transacción: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return false;
    }

    public List<Pregunta> obtenerAleatorias(int cantidad) {
        // Usamos LinkedHashMap para mantener el orden aleatorio que nos de SQL
        Map<Integer, Pregunta> mapaPreguntas = new LinkedHashMap<>();

        String sql = "SELECT p.*, ri.respuesta "
                + "FROM (SELECT * FROM pregunta ORDER BY RAND() LIMIT ?) p "
                + "INNER JOIN respuestasincorrectas ri ON p.idPregunta = ri.idPregunta";

        try (Connection con = ConexionSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idPregunta");

                // Si la pregunta no está en el mapa, la creamos
                if (!mapaPreguntas.containsKey(id)) {
                    Pregunta p = new Pregunta();
                    p.setIdPregunta(id);
                    p.setEnunciado(rs.getString("enunciado"));
                    p.setCategoria(rs.getString("categoria"));
                    p.setDificultad(rs.getString("dificultad"));
                    p.setRespuestaCorrecta(rs.getString("respuestaCorrecta"));
                    p.setRespuestasIncorrectas(new ArrayList<>()); // Inicializamos la lista

                    mapaPreguntas.put(id, p);
                }

                // Siempre añadimos la respuesta incorrecta de la fila actual a la lista
                mapaPreguntas.get(id).getRespuestasIncorrectas().add(rs.getString("respuesta"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener preguntas: " + e.getMessage());
        }

        return new ArrayList<>(mapaPreguntas.values());
    }
    
    public boolean existePregunta(String enunciado) {
        String sql = "SELECT COUNT(*) FROM pregunta WHERE enunciado = ?";
        try (Connection con = ConexionSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, enunciado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar existencia: " + e.getMessage());
        }
        return false;
    }
    
    public void guardarListaSiNoExisten(List<Pregunta> listaNuevas) {
        int guardadas = 0;
        int saltadas = 0;

        for (Pregunta p : listaNuevas) {
            if (!existePregunta(p.getEnunciado())) {
                if (insertar(p)) {
                    guardadas++;
                }
            } else {
                saltadas++;
            }
        }
        System.out.println("Proceso terminado: " + guardadas + " guardadas, " + saltadas + " ya existían.");
    }
}
