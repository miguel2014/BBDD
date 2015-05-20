package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class PruebaConexion {
	public static final String DB_URL = "jdbc:sqlite:/home/matinal/sqlite/ejemplo";
	public static final String DRIVER = "org.sqlite.JDBC";
	
	public static void main(String[] args) {
		try {
			Class.forName(DRIVER);
			//vamos a permitir la integridad referencial en sqlite
			//debemos establecer pargma foreign_keys=on;
			SQLiteConfig config=new SQLiteConfig();
			config.enforceForeignKeys(true);
			
			Connection conexion=DriverManager.getConnection(DB_URL,config.toProperties());
			System.out.println("Conectado a la base de datos");
			//Thread.sleep(3000);//Simulo ejecuciones con la BD(3s en espera)
			//Consulta 
			String consulta="select * from alumno;";
			Statement statement=conexion.createStatement();
			ResultSet resultado=statement.executeQuery(consulta);
			int id;
			while(resultado.next()){
				String nombre,apellidos;
				id=resultado.getInt("id");
				nombre=resultado.getString("nombre");
				apellidos=resultado.getString("apellidos");
				System.out.printf("ID %d autor %s apellidos %s%n",id,nombre,apellidos);
			}
			//Vamos a cambiar al id=12 su apellido
			String consultaupdate=" update alumno set apellidos='pruebaconsulta' where id=12";
			int filasAfectadas=statement.executeUpdate(consultaupdate);
			System.out.println("Filas afectadas "+filasAfectadas);
			//Vamos a eliminar al id 5
			String consultadelete="delete from alumno where id=3;";
			int filasAfectadas2=statement.executeUpdate(consultadelete);
			System.out.println("Filas borradas "+filasAfectadas2);
			
			conexion.close();
			System.out.println("Desconexion de la base de datos");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
