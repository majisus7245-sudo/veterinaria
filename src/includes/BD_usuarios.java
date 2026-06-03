package includes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Usuario;

public class BD_usuarios extends DataBase {
    
    private static String msg;
    private static String tabla = "usuarios";
    private static String columnas = "nombre, password, rol, estado";
    
    public static Lista<Usuario> all() {
        Lista<Usuario> usuarios = new Lista<>();
        String sql = "SELECT * FROM " + tabla;

        try (Statement stmt = DataBase.getConn().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while(rs.next()) {
                usuarios.InsertarFinal(
                        new Usuario(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4), 
                                rs.getString(5)
                        )
                );
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return usuarios;
    }
    
    public static Usuario where(String columna, String valor) {
        Usuario usuario = null;
        
        String sql = "SELECT TOP 1 * FROM " + tabla + " WHERE " + columna + " = ?";
        
        try (PreparedStatement pstmt = DataBase.getConn().prepareStatement(sql)) {
            
            pstmt.setString(1, valor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4), 
                            rs.getString(5)
                    );
                }
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return usuario;
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