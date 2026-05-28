package includes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Cliente;

public class BD_clientes extends DataBase {
    
    private static String msg;
    private static String tabla = "clientes";
    
    private static String columnas = "nombre, domicilio, celCasa, celPersonal, email";
    
    public static Lista<Cliente> all() {
        Lista<Cliente> clientes = new Lista<>();
        String sql = "SELECT * FROM " + tabla;

        try (Statement stmt = DataBase.getConn().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while(rs.next()) {
                clientes.InsertarFinal(
                        new Cliente(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
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
        return clientes;
    }
    
    public static Cliente where(String columna, String valor) {
        Cliente cliente = null;
        
        String sql = "SELECT TOP 1 * FROM " + tabla + " WHERE " + columna + " = ?";
        
        try (PreparedStatement pstmt = DataBase.getConn().prepareStatement(sql)) {
            
            pstmt.setString(1, valor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
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
        return cliente;
    }
    
    public static boolean insert(Cliente cliente) {
        String sql = "INSERT INTO " + tabla + " (" + columnas + ") VALUES ("+ cliente.getValues() +")";
        
        System.out.println(sql);
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