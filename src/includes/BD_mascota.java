package includes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Cliente;
import models.Mascota;

public class BD_mascota {
	
	private static String msg;
	private static String tabla = "mascotas";
	private static String columnas = "cliente, expediente, nombre, raza, tipo, sexo, edad, peso, color";
	
	public static Lista<Mascota> all() {
		Lista<Mascota> mascota = new Lista();
		try {
			String sql = "Select * From " + tabla;
			Statement stmt = DataBase.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				mascota.InsertarFinal(
						new Mascota(
								rs.getInt(1),
								rs.getInt(2),
								rs.getInt(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6), 
								rs.getString(7), 
								rs.getString(8), 
								rs.getString(9),
								rs.getString(10),
								rs.getString(11)
								)
						);
			}
			return mascota;
		} catch (SQLException e) {
			msg = e.getMessage();
			return mascota;
		}
	}
	
	public static Lista<Mascota> whereAll(String columna, String valor) {
		Lista<Mascota> mascotas = null;
		try {
			String sql = "Select top 1 * From " + tabla + " where " + columna + " = '" + valor + "'";
	        
	        Statement stmt = DataBase.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while(rs.next()) {
				mascotas.InsertarFinal(
						new Mascota(
								rs.getInt(1),
								rs.getInt(2),
								rs.getInt(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6), 
								rs.getString(7), 
								rs.getString(8), 
								rs.getString(9),
								rs.getString(10),
								rs.getString(11)
								)
						);
			}
			return mascotas;
		} catch (SQLException e) {
			msg = e.getMessage();
			return mascotas;
		}
	}
	
	public static boolean insert(Mascota mascota) {
		try {
			String sql = "insert into " + tabla + " (" + columnas + ") values ("+ mascota.getValues() +")";
			
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
