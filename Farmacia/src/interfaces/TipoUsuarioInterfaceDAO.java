package interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.ReporteUsuario;
import entidad.TipoUsuario;
import entidad.Usuario;
import utils.MySQLConexion8;

public interface TipoUsuarioInterfaceDAO {
	//proceso para listar los tipos de usuarios
	public ArrayList<TipoUsuario> listarTipoUsuario();

}
