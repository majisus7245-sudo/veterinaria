package includes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classes.Lista;
import classes.Nodo;

public class DataBase {
	
	protected static String msg;
	private static Connection conn;
	protected String columna;
	protected static String [] columnas;
	
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
        Lista<String> listaSQL = new Lista();

        listaSQL.InsertarFinal("if object_id('usuarios', 'u') is null begin " +
                "create table usuarios(id int identity not null, nombre varchar(50) not null, password varchar(60) not null, rol char not null, estado char not null default '1'); " +
                "alter table usuarios add constraint pk_usuarios primary key (id); " +
                "alter table usuarios add constraint ck_rol check(rol in ('1', '2')); " +
                "alter table usuarios add constraint ck_estado check(estado in ('0', '1')); " +
                "insert into usuarios(nombre, password, rol) values ('recepcion', 'password', '1'); " +
                "insert into usuarios(nombre, password, rol) values ('veterinario', 'password', '2'); " +
                "end");

        listaSQL.InsertarFinal("if object_id('clientes', 'u') is null begin " +
                "create table clientes(id int identity not null, nombre varchar(50) not null, domicilio varchar(100) not null, celcasa varchar(10) not null, celpersonal varchar(10) not null, email varchar(50) not null, estado char not null default '1'); " +
                "alter table clientes add constraint pk_clientes primary key (id); " +
                "alter table clientes add constraint ck_estado_clientes check(estado in ('0', '1')); " +
                "end");

        listaSQL.InsertarFinal("if object_id('mascotas', 'u') is null begin " +
                "create table mascotas(id int identity not null, cliente int not null, nombre varchar(50) not null, raza varchar(30) not null, tipo varchar(20) not null, sexo char not null, edad varchar(2) not null, peso varchar(4) not null, color varchar(20) not null, estado char not null default '1'); " +
                "alter table mascotas add constraint pk_mascotas primary key (id); " +
                "alter table mascotas add constraint ck_sexo_mascota check(sexo in ('m', 'f')); " +
                "alter table mascotas add constraint ck_estado_mascota check(estado in ('0', '1')); " +
                "alter table mascotas add constraint fk_mascotas_cliente foreign key (cliente) references clientes (id); " +
                "end");

        listaSQL.InsertarFinal("if object_id('expedientes', 'u') is null begin " +
                "create table expedientes(id int identity not null, mascota int not null, fechacreacion date default(format(getdate(), 'yyyy/mm/dd'))); " +
                "alter table expedientes add constraint pk_expediente primary key (id); " +
                "alter table expedientes add constraint fk_expediente_mascotas foreign key (mascota) references mascotas (id); " +
                "end");

        listaSQL.InsertarFinal("if object_id('cita', 'u') is null begin " +
                "create table cita(id int identity not null, mascota int not null); " +
                "alter table cita add constraint pk_cita primary key (id); " +
                "alter table cita add constraint fk_cita_mascotas foreign key (mascota) references mascotas (id); " +
                "end");

        listaSQL.InsertarFinal("if object_id('consultas', 'u') is null begin " +
                "create table consultas(id int identity not null, expediente int not null, veterinario int not null, sintomas varchar(255) not null, diagnostico varchar(255) not null, observaciones varchar(255) not null, fecha date default(format(getdate(), 'yyyy/mm/dd'))); " +
                "alter table consultas add constraint pk_consultas primary key (id); " +
                "alter table consultas add constraint fk_consultas_expediente foreign key (expediente) references expedientes (id); " +
                "alter table consultas add constraint fk_consultas_veterinario foreign key (veterinario) references usuarios (id); " +
                "end");

        listaSQL.InsertarFinal("if object_id('recibospago', 'u') is null begin " +
                "create table recibospago(id int identity not null, consulta int not null, precio numeric(12,2) not null, fecha date default(format(getdate(), 'yyyy/mm/dd'))); " +
                "alter table recibospago add constraint pk_recibospago primary key (id); " +
                "alter table recibospago add constraint fk_recibospago_consulta foreign key (consulta) references consultas (id); " +
                "end");

        try (Statement stmt = conn.createStatement()) {
        	Nodo<String> nodo = listaSQL.getFrente();
        	
            while(nodo != null) {
            	stmt.executeUpdate(nodo.getInfo());
            	nodo = nodo.getSig();
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