package includes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConect {
	
	private static String msg;
	private static Connection conn;
	
	public static boolean Connect() {
		if(!connectdb())
			return false;
		if(!inicializarTablas())
			return false;
		return true;
	}

	private static boolean connectdb() {
		try {
			if(!crearDB()) {
				return false;
			}
			String url = "jdbc:sqlserver://" + enviroment.DB_SERVER + ";databaseName=" + 
					enviroment.DB_NAME + ";encrypt=true;trustServerCertificate=true;";
			conn = DriverManager.getConnection(url,enviroment.DB_USER,enviroment.DB_PASSWORD);
			return true;
		} catch (SQLException e) {
			msg = "Hubo un error inesperado: " + e.getMessage();
			return false;
		}
	}
	
	private static boolean crearDB() {
		String urlMaster = "jdbc:sqlserver://" + enviroment.DB_SERVER + ";databaseName=master;encrypt=true;trustServerCertificate=true;";
		
		String query = "IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = N'" + enviroment.DB_NAME + "')\n"
				+ "BEGIN\n"
				+ "    CREATE DATABASE [" + enviroment.DB_NAME + "];\n"
				+ "END";
		
		try (Connection connMaster = DriverManager.getConnection(urlMaster, enviroment.DB_USER, enviroment.DB_PASSWORD);
			 Statement stmt = connMaster.createStatement()) {
			
			stmt.executeUpdate(query);
			return true;
			
		} catch (SQLException e) {
			msg = "Error al intentar crear la base de datos: " + e.getMessage();
			return false;
		}
	}
	
	private static boolean inicializarTablas() {
		List<String> sqlStatements = new ArrayList<>();

		// 1. Tabla Usuarios
		sqlStatements.add("IF OBJECT_ID('usuarios', 'U') IS NULL CREATE TABLE usuarios("
				+ "id int identity not null, nombre varchar(50) not null, password varchar(60) not null, "
				+ "rol char not null, estado char not null default '1')");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'pk_usuarios') "
				+ "ALTER TABLE usuarios add constraint pk_usuarios PRIMARY KEY (id)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'ck_rol') "
				+ "ALTER TABLE usuarios add constraint ck_rol CHECK(rol IN ('1', '2'))");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'ck_estado') "
				+ "ALTER TABLE usuarios add constraint ck_estado CHECK(estado IN ('0', '1'))");

		// 2. Tabla Clientes
		sqlStatements.add("IF OBJECT_ID('clientes', 'U') IS NULL CREATE TABLE clientes("
				+ "id int identity not null, nombre varchar(50) not null, domicilio varchar(100) not null, "
				+ "celCasa varchar(10) not null, celPersonal varchar(10) not null, email varchar(50) not null, "
				+ "estado char not null default '1')");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'pk_clientes') "
				+ "ALTER TABLE clientes add constraint pk_clientes PRIMARY KEY (id)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'ck_estado_clientes') "
				+ "ALTER TABLE clientes add constraint ck_estado_clientes CHECK(estado IN ('0', '1'))");

		// 3. Tabla Mascotas
		sqlStatements.add("IF OBJECT_ID('mascotas', 'U') IS NULL CREATE TABLE mascotas("
				+ "id int identity not null, cliente int not null, nombre varchar(50) not null, "
				+ "raza varchar(30) not null, tipo varchar(20) not null, sexo char not null, "
				+ "edad varchar(2) not null, peso varchar(4) not null, color varchar(20) not null, "
				+ "estado char not null default '1')");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'pk_mascotas') "
				+ "ALTER TABLE mascotas add constraint pk_mascotas PRIMARY KEY (id)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'ck_sexo_mascota') "
				+ "ALTER TABLE mascotas add constraint ck_sexo_mascota CHECK(sexo IN ('M', 'F'))");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'ck_estado_mascota') "
				+ "ALTER TABLE mascotas add constraint ck_estado_mascota CHECK(estado IN ('0', '1'))");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'fk_mascotas_cliente') "
				+ "ALTER TABLE mascotas add constraint fk_mascotas_cliente FOREIGN KEY (cliente) references clientes (id)");

		// 4. Tabla Cita
		sqlStatements.add("IF OBJECT_ID('cita', 'U') IS NULL CREATE TABLE cita("
				+ "id int identity not null, mascota int not null)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'pk_cita') "
				+ "ALTER TABLE cita add constraint pk_cita PRIMARY KEY (id)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'fk_cita_mascotas') "
				+ "ALTER TABLE cita add constraint fk_cita_mascotas FOREIGN KEY (mascota) references mascotas (id)");

		// 5. Tabla Expediente
		sqlStatements.add("IF OBJECT_ID('expediente', 'U') IS NULL CREATE TABLE expediente("
				+ "id int identity not null, mascota int not null, fechaCreacion date default(FORMAT(GETDATE(), 'yyyy/MM/dd')))");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'pk_expediente') "
				+ "ALTER TABLE expediente add constraint pk_expediente PRIMARY KEY (id)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'fk_expediente_mascotas') "
				+ "ALTER TABLE expediente add constraint fk_expediente_mascotas FOREIGN KEY (mascota) references mascotas (id)");

		// 6. Tabla Consultas
		sqlStatements.add("IF OBJECT_ID('consultas', 'U') IS NULL CREATE TABLE consultas("
				+ "id int identity not null, expediente int not null, veterinario int not null, "
				+ "sintomas varchar(255) not null, diagnostico varchar(255) not null, observaciones varchar(255) not null, "
				+ "fecha date default(FORMAT(GETDATE(), 'yyyy/MM/dd')))");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'pk_consultas') "
				+ "ALTER TABLE consultas add constraint pk_consultas PRIMARY KEY (id)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'fk_consultas_expediente') "
				+ "ALTER TABLE consultas add constraint fk_consultas_expediente FOREIGN KEY (expediente) references expediente (id)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'fk_consultas_veterinario') "
				+ "ALTER TABLE consultas add constraint fk_consultas_veterinario FOREIGN KEY (veterinario) references usuarios (id)");

		// 7. Tabla RecibosPago
		sqlStatements.add("IF OBJECT_ID('recibosPago', 'U') IS NULL CREATE TABLE recibosPago("
				+ "id int identity not null, consulta int not null, precio numeric(12,2) not null, "
				+ "fecha date default(FORMAT(GETDATE(), 'yyyy/MM/dd')))");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'pk_recibosPago') "
				+ "ALTER TABLE recibosPago add constraint pk_recibosPago PRIMARY KEY (id)");
		sqlStatements.add("IF NOT EXISTS (SELECT * FROM sys.objects WHERE name = 'fk_recibosPago_consulta') "
				+ "alter table recibosPago add constraint fk_recibosPago_consulta FOREIGN KEY (consulta) references consultas (id)");
		

		// Ejecutar todas las sentencias en orden
		try (Statement stmt = conn.createStatement()) {
			for (String sql : sqlStatements) {
				stmt.executeUpdate(sql);
			}
			return true;
		} catch (SQLException e) {
			msg = "Error al inicializar las tablas: " + e.getMessage();
			return false;
		}
	}

	public static String getMsg() {
		return msg;
	}

	public static Connection getConn() {
		return conn;
	}

}