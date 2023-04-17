package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion8 {
	//Método estatico encargado de realizar la conxión con la BD
	public static Connection getConexion() {
		Connection con = null;
		try {
			//Establecer la ruta del driver de conexión --->nombre del paquete
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Datos para establecer la conexión a la bd
			
			String url = "jdbc:mysql://localhost:3306/ciberfarma?serverTimezone=UTC";
			String usr = "root";//usuario
			String psw = "123456";//clave
			
			con = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException e) {
			System.out.println("Error >> Driver no Instalado!!" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error >> de conexión con la BD" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error >> general : " + e.getMessage());
		} 
		return con;
	}

}
