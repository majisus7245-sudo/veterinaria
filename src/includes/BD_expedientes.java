package includes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Expediente;

public class BD_expedientes extends DataBase {
    
    private static String msg;
    private static String tabla = "expedientes";
    private static String columnas = "mascota, fechaCreacion";
    
    public static Lista<Expediente> all() {
        Lista<Expediente> expedientes = new Lista<>();
        String sql = "SELECT * FROM " + tabla;

        try (Statement stmt = DataBase.getConn().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while(rs.next()) {
                expedientes.InsertarFinal(
                        new Expediente(
                                rs.getInt(1),
                                rs.getString(2)
                        )
                );
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return expedientes;
    }
    
    public static Expediente where(String columna, String valor) {
        Expediente expediente = null;
        
        String sql = "SELECT TOP 1 * FROM " + tabla + " WHERE " + columna + " = ?";
        
        try (PreparedStatement pstmt = DataBase.getConn().prepareStatement(sql)) {
            
            pstmt.setString(1, valor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    expediente = new Expediente(
                            rs.getInt(1),
                            rs.getString(3)
                    );
                }
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        
        return expediente;
    }
    
    public static boolean insert(Expediente expediente) {
        String sql = "INSERT INTO " + tabla + " (" + columnas + ") VALUES ("+ expediente.getValues() +")";
        
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
        String sql = "SELECT ISNULL(MAX(id), 0) FROM " + tabla;
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