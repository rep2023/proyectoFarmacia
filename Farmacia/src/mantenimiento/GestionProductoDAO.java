package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Producto;
import interfaces.ProductoInerfacesDAO;
import utils.MySQLConexion8;

public class GestionProductoDAO implements ProductoInerfacesDAO {

	@Override
	public int registrarProducto(Producto prod) {
		//declaracion de variables
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			// paso 1: establecer la conexion con la bd
			con = MySQLConexion8.getConexion();
			// paos 2: Definir la instrucción sql--REGISTRO
			String sql = "insert into tb_productos values (?,?,?,?,?,?)";
			//paso 3: preparar la instrucción
			pstm=con.prepareStatement(sql);
			// paso 4: obtener los parametros
			pstm.setString(1,prod.getCod());
			pstm.setString(2,prod.getProd());
			pstm.setInt(3,prod.getCant());
			pstm.setDouble(4,prod.getPre());
			pstm.setInt(5,prod.getTipo());
			pstm.setInt(6,prod.getEstado());//+++++++++++++++
			//paso 5: ejecución de la instrucción.
			res = pstm.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(">>>> Error en la instrucción SQL - Gestion"
					+ "Productos"+e.getMessage());
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

	@Override
	public int actualizarProducto(Producto prod) {
		int res=0;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql = "update tb_productos set  descripcion=?,"
					+ "stock=?,precio=?,idtipo=?,estado=? where idprod=?";
			pstm=con.prepareStatement(sql);
			
			pstm.setString(1,prod.getProd());
			pstm.setInt(2,prod.getCant());
			pstm.setDouble(3,prod.getPre());
			pstm.setInt(4,prod.getTipo());
			pstm.setInt(5,prod.getEstado());
			pstm.setString(6,prod.getCod());
			
			res= pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error en SQL actualizarProducto"+e.getMessage());
		}finally {
			try {
					if(pstm != null)pstm.close();
					if(con != null)con.close();
				
			} catch (SQLException e2) {
				System.out.println("Erro al cerrar la BD"+e2.getMessage());
			}
		}
		return res;
	}

	@Override
	public int eliminarProducto(String idProd) {
		int res=0;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=MySQLConexion8.getConexion();
			String sql = "DELETE FROM tb_productos WHERE idprod = ?;";
			pstm=con.prepareStatement(sql);
			
			pstm.setString(1,idProd);
			
			
			res= pstm.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error en SQL eliminar"+e.getMessage());
		}finally {
			try {
					if(pstm != null)pstm.close();
					if(con != null)con.close();
				
			} catch (SQLException e2) {
				System.out.println("Erro al cerrar la BD"+e2.getMessage());
			}
		}
		return res;
	}
	@Override
	public ArrayList<Producto> listarProducto() {
		ArrayList<Producto> listaProd = new ArrayList<Producto>();
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res= null;
		
		Producto prod=null;
		try {
			//paso 1 conectar a la bd
			con=MySQLConexion8.getConexion();
			//paso 2: Establecer la instruccion SQL
			String sql = "SELECT * FROM tb_productos";
			//paso 3: Enviar la instruccion al objeto pstm -->
			pstm=con.prepareStatement(sql);
			//paso 4: obtener los parametros: no hay
			//paso 5: ejecutar la instruccion SQL
			res=pstm.executeQuery();
			//paso 6: bucle --> para realizar el recorrido al objeto 
			while(res.next()) {
				//crear un objeto de tipo usuario
				prod = new Producto();
				prod.setCod(res.getString(1));
				prod.setProd(res.getString(2));
				prod.setCant(res.getInt(3));
				prod.setPre(res.getDouble(4));
				prod.setTipo(res.getInt(5));
				prod.setEstado(res.getInt(6));
				
				listaProd.add(prod);	
			}	
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL listar productos"+e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();
				
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD"+e2.getMessage());
			}
		}
		return listaProd;
	}

}
