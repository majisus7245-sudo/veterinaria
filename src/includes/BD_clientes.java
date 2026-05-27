package includes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Cliente;
import models.Usuario;

public class BD_clientes extends DataBase{
	
	private static String msg;
	private static String tabla = "clientes";
	private static String columnas = "nombre, domicilio, celCasa, celPersonal, email, estado";
	
	public static Lista<Cliente> all() {
		Lista<Cliente> clientes = new Lista();
		try {
			String sql = "Select * From " + tabla;
			Statement stmt = DataBase.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + " usuario " + rs.getString(2));
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
			return clientes;
		} catch (SQLException e) {
			msg = e.getMessage();
			return clientes;
		}
	}
	
	public static Cliente where(String columna, String valor) {
		Cliente cliente = null;
		try {
			String sql = "Select top 1 * From " + tabla + " where " + columna + " = '" + valor + "'";
	        
	        Statement stmt = DataBase.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
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
			return cliente;
		} catch (SQLException e) {
			msg = e.getMessage();
			return cliente;
		}
	}
	
	public static boolean insert(Cliente cliente) {
		try {
			String sql = "insert into " + tabla + " (" + columnas + ") values ()";
			
			Statement stmt = DataBase.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        
	        
			return true;
		}catch(SQLException e) {
			msg = e.getMessage();
			return false;
		}
	}
	
	public static Integer count() {
		try {
			Integer count = null;
			String sql = "select count(*) from " + tabla;
			
			Statement stmt = DataBase.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        if(rs.next()) {
	        	count = rs.getInt(1);
	        }
			return count;
		}catch(SQLException e) {
			msg = e.getMessage();
			return null;
		}
	}
	
}
