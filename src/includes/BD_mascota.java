package includes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Mascota;

public class BD_mascota {
    
    private static String msg;
    private static String tabla = "mascotas";
    private static String columnas = "cliente, expediente, nombre, raza, tipo, sexo, edad, peso, color";

    public static Lista<Mascota> all() {
        Lista<Mascota> mascota = new Lista<>();
        String sql = "SELECT * FROM " + tabla;
        
        // Using try-with-resources to prevent memory/cursor leaks
        try (Statement stmt = DataBase.getConn().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while(rs.next()) {
                mascota.InsertarFinal(
                        new Mascota(
                                rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                rs.getString(4), rs.getString(5), rs.getString(6), 
                                rs.getString(7), rs.getString(8), rs.getString(9),
                                rs.getString(10), rs.getString(11)
                        )
                );
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return mascota;
    }
    
    public static Lista<Mascota> whereAll(String columna, String valor) {
        Lista<Mascota> mascotas = new Lista<>(); 
        
        String sql = "SELECT * FROM " + tabla + " WHERE " + columna + " = ?";
        
        try (PreparedStatement pstmt = DataBase.getConn().prepareStatement(sql)) {
            
            // Set the value safely
            pstmt.setString(1, valor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()) {
                    mascotas.InsertarFinal(
                            new Mascota(
                                    rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                    rs.getString(4), rs.getString(5), rs.getString(6), 
                                    rs.getString(7), rs.getString(8), rs.getString(9),
                                    rs.getString(10), rs.getString(11)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return mascotas;
    }
    
    public static boolean insert(Mascota mascota) {
        String sql = "INSERT INTO " + tabla + " (" + columnas + ") VALUES ("+ mascota.getValues() +")";
        
        try (Statement stmt = DataBase.getConn().createStatement()) {
            
            int rowsAffected = stmt.executeUpdate(sql);
            return rowsAffected > 0;
            
        } catch(SQLException e) {
            msg = e.getMessage();
            return false;
        }
    }
    
    public static Integer count() {
        String sql = "SELECT COUNT(*) FROM " + tabla;
        Integer count = 0;
        
        try (Statement stmt = DataBase.getConn().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            if(rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch(SQLException e) {
            msg = e.getMessage();
            return null;
        }
        
        return count;
    }
}