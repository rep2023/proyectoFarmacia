package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.ReporteUsuario;
import entidad.TipoUsuario;
import entidad.Usuario;

import utils.MySQLConexion8;

public class GestionUsuarioDAO {
	//LOGICA DEL NEGOCIO

	public int registrarUsuario(Usuario u) {
		//declaracion de variables
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			// paso 1: establecer la conexion con la bd
			con = MySQLConexion8.getConexion();
			// paos 2: Definir la instrucci�n sql--REGISTRO
			String sql = "insert into tb_usuarios values (null,?,?,?,?,?,?,default)";
			//paso 3: preparar la instrucci�n
			pstm=con.prepareStatement(sql);
			// paso 4: obtener los parametros
			pstm.setString(1,u.getNombre());
			pstm.setString(2,u.getApellido());
			pstm.setString(3,u.getUsuario());
			pstm.setString(4,u.getClave());
			pstm.setString(5,u.getFecha());
			pstm.setInt(6,u.getTipo());//+++++++++++++++
			//paso 5: ejecuci�n de la instrucci�n.
			res = pstm.executeUpdate();


		} catch (Exception e) {
			System.out.println(">>>> Error en la instrucci�n de resgistro"+e.getMessage());
		}finally {

			try {
				if(pstm != null)pstm.close();
				if(con != null)con.close();

			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());

			}
		}

		return res;

	}


	public int eliminarUsuario(int codigo) {

		int res = 0;

		Connection con=null;
		PreparedStatement pstm=null;

		try {
			//paso1:conectarse
			con=MySQLConexion8.getConexion();
			// paso 2 : Establecer la instrucci�n SQL - Eliminar
			String sql ="delete from tb_usuarios where codigo = ?";
			//paso3: Enviar la instruccion al objeto pstm
			pstm=con.prepareStatement(sql);
			// paso4: parametro
			pstm.setInt(1, codigo);
			//paso 5: ejecutar la instruccion
			res=pstm.executeUpdate();


		} catch (Exception e) {
			System.out.println("Error en la instruccion SQL - Eliminar"+e.getMessage());
		}finally{
			try {
				if(pstm != null)pstm.close();
				if(con != null)pstm.close();

			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());
			}

		}


		return res;
	}


	public Usuario buscarUsuario(int codigo) {
		Usuario user=null;
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet res=null;

		try {
			//paso1:
			con=MySQLConexion8.getConexion();
			//paso 2:
			String sql="select * from tb_usuarios where codigo = ?";
			//paso 3:
			pstm=con.prepareStatement(sql);
			//obtner los par�metros
			pstm.setInt(1, codigo);
			//paso 5:
			res=pstm.executeQuery();
			//
			if(res.next()) {
				user = new Usuario(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),
						res.getString(5),res.getString(6),res.getInt(7),res.getInt(8));
			}

		} catch (Exception e) {
			System.out.println("Erro en la instruccion SQL - Consultar Usuario"+e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res!=null)res.close();
				if(con!=null)con.close();

			} catch (SQLException e2) {
				System.out.println("Error al cerrar la bd "+e2.getMessage());
			}
		}

		return user;
	}


	public int actualizarUsuario(Usuario u) {
		//declaracion de variables
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			// paso 1: establecer la conexion con la bd
			con = MySQLConexion8.getConexion();
			// paos 2: Definir la instrucci�n sql--ACTUALIZAR
			String sql = "update tb_usuarios set nombre=?,apellido=?,usuario=?,clave=?,fnacim=? where codigo=?;";
			//paso 3: preparar la instrucci�n
			pstm=con.prepareStatement(sql);
			// paso 4: obtener los parametros
			pstm.setString(1,u.getNombre());
			pstm.setString(2,u.getApellido());
			pstm.setString(3,u.getUsuario());
			pstm.setString(4,u.getClave());
			pstm.setString(5,u.getFecha());
			pstm.setInt(6, u.getCodigo());
			//paso 5: ejecuci�n de la instrucci�n.
			res = pstm.executeUpdate();


		} catch (Exception e) {
			System.out.println(">>>> Error en la instrucci�n de actualizar"+e.getMessage());
		}finally {

			try {
				if(pstm != null)pstm.close();
				if(con != null)con.close();

			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());

			}
		}

		return res;
	}

	public ArrayList<Usuario> listarUsuarios(){
		ArrayList<Usuario> listaUser = new ArrayList<Usuario>();

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res= null;

		Usuario user=null;
		try {
			//paso 1 conectar a la bd
			con=MySQLConexion8.getConexion();
			//paso 2: Establecer la instruccion SQL
			String sql = "SELECT * FROM tb_usuarios";
			//paso 3: Enviar la instruccion al objeto pstm -->
			pstm=con.prepareStatement(sql);
			//paso 4: obtener los parametros: no hay
			//paso 5: ejecutar la instruccion SQL
			res=pstm.executeQuery();
			//paso 6: bucle --> para realizar el recorrido al objeto
			while(res.next()) {
				//crear un objeto de tipo usuario
				user = new Usuario();
				user.setCodigo(res.getInt(1));
				user.setNombre(res.getString(2));
				user.setApellido(res.getString(3));
				user.setUsuario(res.getString(4));
				user.setClave(res.getString(5));
				user.setFecha(res.getString(6));
				user.setTipo(res.getInt(7));
				user.setEstado(res.getInt(8));
				listaUser.add(user);
			}
		} catch (Exception e) {
			System.out.println("Errocito en la instrucci�n SQL listar usuarios"+e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();

			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());
			}
		}
		return listaUser;
	}

	public ArrayList<ReporteUsuario> listarXTipo(int tipo){
		ArrayList<ReporteUsuario> lista;
		lista = new ArrayList<ReporteUsuario>();
		ReporteUsuario us = null;
		Connection con = null;
		PreparedStatement pstm=null;
		ResultSet res= null;
		try {
			//1
			con=MySQLConexion8.getConexion();
			//2
			String sql="{call usp_listarUsuariosXTipo(?)}";
			//3
			pstm = con.prepareStatement(sql);
			//4
			pstm.setInt(1,tipo);
			//5
			res= pstm.executeQuery();
			while(res.next()) {
				//crear un objeto de tipo usuario
				us = new ReporteUsuario();
				us.setCodigo(res.getInt(1));
				us.setNomCompletos(res.getString(2));
				us.setDescrpTipo(res.getString(3));
				lista.add(us);
			}


		} catch (Exception e){
			System.out.println("Errocito  SQL listar reporteUsuario"+e.getMessage());
		}finally {
			try{
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();

			}catch (SQLException e2){
				System.out.println("Error al cerrar la BD en ReporteUsuario"+e2.getMessage());
			}
		}
		return  lista;
	}

	public Usuario validarAcceso(String usr, String clav){
		Usuario usuario=null;
		Connection con=null;
		PreparedStatement pstm= null;
		ResultSet res=null;

		try{
			//1 Conectarse a la base de datos
			con=MySQLConexion8.getConexion();
			//2
			String sql = "{call usp_validarAcceso(?,?)}";
			//3
			pstm = con.prepareStatement(sql);
			// 4
			pstm.setString(1,usr);
			pstm.setString(2,clav);
			//5
			res = pstm.executeQuery();
			//6
			if(res.next()){
				usuario = new Usuario(	res.getInt(1),
										res.getString(2),
										res.getString(3),
										res.getString(4),
										res.getString(5),
										res.getString(6),
										res.getInt(7),
										res.getInt(8));
			}
		}catch (Exception e){
			System.out.println("Error en el procedimiento almacenado validar acceso"+e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();

			}catch (SQLException e2){
				System.out.println("Error al cerrar la base de datos");
			}
		}

		return  usuario;
	}
}
