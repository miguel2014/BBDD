package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class PruebaConexion {
	public static final String DB_URL = "jdbc:sqlite:/home/matinal/sqlite/ejemplo";
	public static final String DRIVER = "org.sqlite.JDBC";
	private static String nombre;
	private static String apellidos;
	
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
			
			//Vamos a usar PreparedStatement ,vamos a consultar los 5 primeros alumnos
			consulta="select * from alumno where id=?";
			PreparedStatement preparedStatement=conexion.prepareStatement(consulta);
			for (int i = 1; i < 7; i++) {
				preparedStatement.setInt(1,i);
				resultado=preparedStatement.executeQuery();
				while (resultado.next()) {
					nombre=resultado.getString("nombre");
					apellidos=resultado.getString("apellidos");
					
					System.out.printf("%15s %15s%n",nombre,apellidos);
					
				}
			}
			//Vamos a agrupar sentencias sql usando batch updates
			consulta="insert into alumno(nombre,apellidos) values('?','?');";
			preparedStatement=conexion.prepareStatement(consulta);
			preparedStatement.setString(1,"Jose");
			preparedStatement.setString(2,"Sanchez");
			preparedStatement.addBatch();
			
			consulta="update alumno set nombre='?' where id=?;";
			preparedStatement=conexion.prepareStatement(consulta);
			preparedStatement.setString(1, "Otro nombre");
			preparedStatement.setInt(2, 10);
			preparedStatement.addBatch();
			int[] affectedRecords=preparedStatement.executeBatch();
			System.out.println("Registros afectados "+affectedRecords.length);
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
