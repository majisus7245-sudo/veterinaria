package includes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Expediente;

public class BD_expediente {
	
	private static String msg;
	private static String tabla = "expediente";
	private static String columnas = "fechaCreacion";
	
	public static Expediente where(String columna, String valor) {
		Expediente expediente = null;
		try {
			String sql = "Select top 1 * From " + tabla + " where " + columna + " = '" + valor + "'";
	        
	        Statement stmt = DataBase.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        if (rs.next()) {
	        	expediente = new Expediente(
	        			rs.getInt(1),
						rs.getString(2)
	            );
	        }
			return expediente;
		} catch (SQLException e) {
			msg = e.getMessage();
			return expediente;
		}
	}
	
	public static boolean insert(Expediente expediente) {
		try {
			String sql = "insert into " + tabla + " (" + columnas + ") values ("+ expediente.getValues() +")";
			
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
