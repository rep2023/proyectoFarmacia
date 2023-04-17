package mantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.TipoUsuario;
import interfaces.TipoUsuarioInterfaceDAO;
import utils.MySQLConexion8;

public class GestionTipoUsuarioDAO implements TipoUsuarioInterfaceDAO {
	
	public ArrayList<TipoUsuario> listarTipoUsuario(){
		//Declaración de variables
		ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario>();//null
		TipoUsuario user=null;//solo declaramos no hemos creado todavia
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res= null;
		
		
		try {
			//paso 1: conexión a la base de datos
			con=MySQLConexion8.getConexion();
			//paso 2: Establecer la instruccion SQL; una consulta
			String sql = "SELECT * FROM tb_tipos";
			//paso 3: Enviar la instruccion al objeto pstm -->
			pstm=con.prepareStatement(sql);
			//paso 4: no hay
			//paso 5: 
			res=pstm.executeQuery();
			//paso 6: bucle --> para realizar el recorrido al objeto 
			while(res.next()) {
				user= new TipoUsuario();
				//setear
				user.setIdTipo(res.getInt(1));
				user.setDescripcionTipo(res.getString(2));
				//enviar a la lista
				lista.add(user);
				
			}	
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL listar tipos de usuario"+e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();
				
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());
			}
		}
		return lista;
	}	

}
