package pq2;

import entidad.Usuario;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hola");
		Usuario us1 = new Usuario();
		us1.setCodigo(134);
		us1.setNombre("Belguno");
		us1.setApellido("Almagro");
		us1.setUsuario("U134");
		us1.setClave("3e45");
		us1.setFecha("12-10-2023");
		us1.setTipo(3);
		us1.setEstado(1);
		
		System.out.println("hola");
		Usuario us2 = new Usuario();
		us1.setCodigo(154);
		us1.setNombre("Raterio");
		us1.setApellido("Suahuaca");
		us1.setUsuario("U164");
		us1.setClave("3r42");
		us1.setFecha("01-11-2021");
		us1.setTipo(2);
		us1.setEstado(3);
		System.out.println(us1.getNombre()+us1.getApellido()+us1.getCodigo()+us1.getClave()+us1.getFecha());
		
	}

}
