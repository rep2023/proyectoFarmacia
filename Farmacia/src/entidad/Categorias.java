package entidad;

public class Categorias {
	int idCategoria;
	String descripcion;
	public Categorias() {
	}
	public Categorias(int idCategoria, String descripcion) {
		this.idCategoria = idCategoria;
		this.descripcion = descripcion;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

}
