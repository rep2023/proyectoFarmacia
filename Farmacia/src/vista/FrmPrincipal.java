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
	private JMenuItem mntmRegistrarClie;

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 378);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("ARCHIVO");
		menuBar.add(mnArchivo);

		JMenuItem mntmCambiarUs = new JMenuItem("Cambiar usuario");
		mnArchivo.add(mntmCambiarUs);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK));
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);

		JMenu mnMantenimiento = new JMenu("MANTENIMIENTO");
		menuBar.add(mnMantenimiento);

		mntmRegistrarClie = new JMenuItem("Registrar Clientes");
		mntmRegistrarClie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.ALT_DOWN_MASK));
		mntmRegistrarClie.addActionListener(this);
		mnMantenimiento.add(mntmRegistrarClie);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
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


	}

	private void salirSistema() {

		System.exit(0);
	}

}
