package interfaces;

import entidad.Usuario;
import entidad.ReporteUsuario;

import java.util.ArrayList;

public interface UsuarioInterfacesDAO {
	//definir los procesos
		public int registrarUsuario(Usuario u);
		public int eliminarUsuario(int codigo);
		//Buscar un usuario por c�digo
		public Usuario buscarUsuario(int codigo);
		public int actualizarUsuario(Usuario u);
		public ArrayList<Usuario> listarUsuarios();
		public ArrayList<ReporteUsuario> listarXTipo(int tipo);
		//Validar el acceso al sistema
		public Usuario validarAcceso(String usr,String clav);

}
