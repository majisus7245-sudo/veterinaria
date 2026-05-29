package includes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Cita;
import models.Expediente;

public class BD_cita extends DataBase {
    
    private static String msg;
    private static String tabla = "cita";
    private static String columnas = "mascota";
    
    public static Lista<Cita> all() {
        Lista<Cita> citas = new Lista<>();
        String sql = "SELECT * FROM " + tabla;

        try (Statement stmt = DataBase.getConn().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while(rs.next()) {
                citas.InsertarFinal(
                        new Cita(
                                rs.getInt(1),
                                rs.getInt(2)
                        )
                );
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return citas;
    }
    
    public static Cita where(String columna, String valor) {
        Cita cita = null;
        
        String sql = "SELECT TOP 1 * FROM " + tabla + " WHERE " + columna + " = ?";
        
        try (PreparedStatement pstmt = DataBase.getConn().prepareStatement(sql)) {
            
            pstmt.setString(1, valor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cita = new Cita(
                            rs.getInt(1),
                            rs.getInt(2)
                    );
                }
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return cita;
    }
    
    public static boolean insert(Cita cita) {
        String sql = "INSERT INTO " + tabla + " (" + columnas + ") VALUES ("+ cita.getValues() +")";
        
        try (Statement stmt = DataBase.getConn().createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            
            return rowsAffected > 0;
            
        } catch(SQLException e) {
            msg = e.getMessage();
            System.out.println(msg);
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