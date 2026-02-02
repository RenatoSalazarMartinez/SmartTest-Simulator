
package DAO;

import Conexion.ConexionSQL;
import DTO.Historial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HistorialDAO {
    public boolean insertar(Historial h) {
        String sql = "INSERT INTO historial (fecha, aciertos, fallos, categoria) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Convertimos LocalDateTime a Timestamp para que MySQL lo entienda
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(h.getFecha()));
            ps.setInt(2, h.getAciertos());
            ps.setInt(3, h.getFallos());
            ps.setString(4, h.getCategoria());

            // Ejecutamos la inserción
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Si es mayor a 0, se insertó con éxito

        } catch (SQLException e) {
            System.out.println("Error al insertar historial: " + e.getMessage());
            return false;
        }
    }
    
    public List<Historial> listar() {
        List<Historial> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial ORDER BY fecha DESC"; // Traemos los más recientes primero

        try (Connection con = ConexionSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Historial h = new Historial();

                // Extraemos los datos de la fila actual del ResultSet
                h.setIdHistorial(rs.getInt("idHistorial"));

                // Convertimos el Timestamp de la BD de vuelta a LocalDateTime de Java
                h.setFecha(rs.getTimestamp("fecha").toLocalDateTime());

                h.setAciertos(rs.getInt("aciertos"));
                h.setFallos(rs.getInt("fallos"));
                h.setCategoria(rs.getString("categoria"));

                // Agregamos el objeto a nuestra lista
                lista.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar historial: " + e.getMessage());
        }
        return lista;
    }
}
