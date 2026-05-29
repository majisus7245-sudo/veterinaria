package includes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.ReciboPago;

public class BD_recibosPago extends DataBase {
    
    private static String msg;
    private static String tabla = "recibosPago";
    private static String columnas = "consulta, precio, fecha";
    
    public static Lista<ReciboPago> all() {
        Lista<ReciboPago> recibos = new Lista<>();
        String sql = "SELECT * FROM " + tabla;

        try (Statement stmt = DataBase.getConn().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while(rs.next()) {
                recibos.InsertarFinal(
                        new ReciboPago(
                                rs.getInt(1),
                                rs.getInt(2),
                                rs.getDouble(3),
                                rs.getString(4)
                        )
                );
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return recibos;
    }
    
    public static ReciboPago where(String columna, String valor) {
        ReciboPago recibo = null;
        
        String sql = "SELECT TOP 1 * FROM " + tabla + " WHERE " + columna + " = ?";

        try (PreparedStatement pstmt = DataBase.getConn().prepareStatement(sql)) {
            
            pstmt.setString(1, valor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    recibo = new ReciboPago(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getDouble(3),
                            rs.getString(4)
                    );
                }
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return recibo;
    }
    
    public static boolean insert(ReciboPago recibo) {
        String sql = "INSERT INTO " + tabla + " (" + columnas + ") VALUES ("+ recibo.getValues() +")";
        
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