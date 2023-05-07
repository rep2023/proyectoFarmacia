package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class FrmPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem mntmSalir;
	private JMenuItem mntmRegistrarClie,mntmRegistrarProd;

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 378);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//************** MENU SISTEMA ***********************
		JMenu mnSistema= new JMenu("SISTEMA");
		menuBar.add(mnSistema);

		JMenuItem mntmRelax= new JMenuItem("Relax");
		mnSistema.add(mntmRelax);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK));
		mntmSalir.addActionListener(this);
		mnSistema.add(mntmSalir);
		//*************** MANTENIMIENTO ****************
		JMenu mnMantenimiento = new JMenu("MANTENIMIENTO");
		menuBar.add(mnMantenimiento);

		mntmRegistrarClie = new JMenuItem("Registrar Clientes");
		mntmRegistrarClie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.ALT_DOWN_MASK));
		mntmRegistrarClie.addActionListener(this);
		mnMantenimiento.add(mntmRegistrarClie);

		mntmRegistrarProd = new JMenuItem("Registrar Producto");
		mnMantenimiento.add(mntmRegistrarProd);

		//**************** MENU REPORTE *************************
		JMenu mnReporte = new JMenu("REPORTE");
		menuBar.add(mnReporte);

		//**************** MENU VENTAS *************************
		JMenu mnVentas = new JMenu("VENTAS");
		menuBar.add(mnVentas);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		//Definir los perfiles
		restringirPermisos();
	}

	private void restringirPermisos() {
		switch(Logueo.usuario.getTipo()){
			case 2://CLIENTE
				mntmRegistrarClie.setEnabled(false);
				mntmRegistrarProd.setEnabled(false);
				break;
			case 3://CAJERO
				mntmRegistrarClie.setEnabled(false);
				mntmRegistrarProd.setEnabled(false);
			default:
				break;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmSalir) {
			salirSistema();
		}
		if (e.getSource()==mntmRegistrarClie){
			cargarFrmRegistro();

		}


	}

	private void cargarFrmRegistro() {
		FrmRegistro f = new FrmRegistro();
		f.setVisible(true);

	}

	private void salirSistema() {

		System.exit(0);
	}

}
