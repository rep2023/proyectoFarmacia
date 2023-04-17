package interfaces;

import java.util.ArrayList;

import entidad.Producto;

public interface ProductoInerfacesDAO {
	//Registrar
	public int registrarProducto(Producto prod);
	//Actualizar
	public int actualizarProducto(Producto prod);
	//Eliminar
	public int eliminarProducto(String idProd);
	//buscar por codigo
	//listar
	public ArrayList<Producto> listarProducto();

}
