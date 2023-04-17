package pq2;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DriverManager.getConnection("jdbc:mysql://localhost:3306/ciberfarma","root","123456");
			System.out.println("Conexion OK se hizo la conexion"); 
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
	}

}
