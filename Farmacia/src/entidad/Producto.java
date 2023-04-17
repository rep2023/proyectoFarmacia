package entidad;

public class Producto {
	// atributos privados -> Tablas / GUI
	private String cod, prod;
	private int cant;
	private double pre;
	private int tipo,estado;
	
	public Producto() {
		
	}
	public Producto(String cod, String prod, int cant, double pre, int tipo,int estado) {
		this.cod = cod;
		this.prod = prod;
		this.cant = cant;
		this.pre = pre;
		this.tipo = tipo;
		this.estado=estado;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public double getPre() {
		return pre;
	}
	public void setPre(double pre) {
		this.pre = pre;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
