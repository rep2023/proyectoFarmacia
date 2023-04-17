package utils;

import javax.swing.JOptionPane;

public class Alertas {
	public static void mensajeError(String msj) {
		
		JOptionPane.showMessageDialog(null, msj, "Error !!!", 0);

	}
	
	public static void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(null, msj, "Exito !!!", 1);

	}
}
