package includes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Consulta;

public class BD_consultas extends DataBase {
    
    private static String msg;
    private static String tabla = "consultas";
    private static String columnas = "expediente, veterinario, sintomas, diagnostico, observaciones, fecha";
    
    public static Lista<Consulta> all() {
        Lista<Consulta> consultas = new Lista<>();
        String sql = "SELECT * FROM " + tabla;

        try (Statement stmt = DataBase.getConn().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while(rs.next()) {
                consultas.InsertarFinal(
                        new Consulta(
                                rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getString(4), 
                                rs.getString(5), 
                                rs.getString(6), 
                                rs.getString(7)
                        )
                );
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return consultas;
    }
    
    public static Consulta where(String columna, String valor) {
        Consulta consulta = null;
        
        String sql = "SELECT TOP 1 * FROM " + tabla + " WHERE " + columna + " = ?";
        
        try (PreparedStatement pstmt = DataBase.getConn().prepareStatement(sql)) {
            
            pstmt.setString(1, valor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    consulta = new Consulta(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4), 
                            rs.getString(5), 
                            rs.getString(6), 
                            rs.getString(7)
                    );
                }
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return consulta;
    }
    
    public static Lista<Consulta> whereAll(String columna, String valor) {
        Lista<Consulta> consultas = new Lista<>();
        
        String sql = "SELECT * FROM " + tabla + " WHERE " + columna + " = ?";
        
        try (PreparedStatement pstmt = DataBase.getConn().prepareStatement(sql)) {
            
            pstmt.setString(1, valor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    consultas.InsertarFinal(
                            new Consulta(
                                    rs.getInt(1),
                                    rs.getInt(2),
                                    rs.getInt(3),
                                    rs.getString(4), 
                                    rs.getString(5), 
                                    rs.getString(6), 
                                    rs.getString(7)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return consultas;
    }
    
    public static boolean insert(Consulta consulta) {
        String sql = "INSERT INTO " + tabla + " (" + columnas + ") VALUES ("+ consulta.getValues() +")";

        try (Statement stmt = DataBase.getConn().createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            
            return rowsAffected > 0;
            
        } catch(SQLException e) {
            msg = e.getMessage();
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