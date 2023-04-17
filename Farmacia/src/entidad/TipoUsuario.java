package entidad;

public class TipoUsuario {
	// atributos privados
	private int idTipo;
	private String descripcionTipo; 
	// constructores
	public TipoUsuario() {
		
	}
	public TipoUsuario(int idTipo, String descripcionTipo) {
		
		this.idTipo = idTipo;
		this.descripcionTipo = descripcionTipo;
	}
	
	//métodos de acceso
	
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public String getDescripcionTipo() {
		return descripcionTipo;
	}
	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}
	
	
}
