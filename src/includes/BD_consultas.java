package includes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Lista;
import models.Consulta;

public class BD_consultas {
	
	private static String msg;
	private static String tabla = "consultas";
	private static String columnas = "expediente, veterinario, sintomas, diagnostico, observaciones, fecha";
	
	public static Lista<Consulta> whereAll(String columna, String valor) {
		Lista<Consulta> consultas = null;
		try {
			String sql = "Select top 1 * From " + tabla + " where " + columna + " = '" + valor + "'";
	        
	        Statement stmt = DataBase.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while(rs.next()) {
	        	consultas.InsertarFinal(
						new Consulta(
								rs.getInt(1),
								rs.getInt(2),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6), 
								rs.getString(7)
								)
						);
			}
			return consultas;
		} catch (SQLException e) {
			msg = e.getMessage();
			return consultas;
		}
	}

}
