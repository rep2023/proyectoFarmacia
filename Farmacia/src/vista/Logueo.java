//Nos quedamos en 1:34 del segundo video (sesion 9)
package vista;

import entidad.Usuario;
import mantenimiento.GestionUsuarioDAO;
import utils.Alertas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Objects;


public class Logueo extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	public static Logueo frame;
	private JButton btnAceptar;
	private JButton btnSalir;
	//
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	//instanciar un usuario publico para acceder desde otra ventana
	public static Usuario usuario = new Usuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Logueo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Logueo() {

		setTitle("CIBERFARMA - Acceso al Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(114, 36, 96, 20);
		contentPane.add(lblUsuario);

		JLabel lblClave = new JLabel("Contrase\u00F1a:");
		lblClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClave.setBounds(114, 83, 96, 20);
		contentPane.add(lblClave);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(205, 36, 103, 22);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtClave = new JPasswordField();
		txtClave.setBounds(205, 80, 103, 20);
		contentPane.add(txtClave);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new escuchaBtnAceptar());
		btnAceptar.setBounds(114, 116, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.setEnabled(true);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(226, 116, 89, 23);
		contentPane.add(btnSalir);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("img/avatar.png"));
		lblFondo.setBounds(0, 11, 127, 184);
		contentPane.add(lblFondo);
	}

	class escuchaBtnAceptar implements ActionListener{
		public void actionPerformed(ActionEvent e){

			if (e.getSource()==btnAceptar){
				validarAcceso();
			}

		}

	}

	private void validarAcceso() {

		//Declaración de variables
		String usr;
		String clave;
		usr=getUsuario();
		clave=getClave();
		System.out.println("clave:"+clave);
		System.out.println("usuario:"+usr);
		if(usr==null|| clave==null){
			return;
		}else {
			//proceso de validación desde la BD
			usuario = gUser.validarAcceso(usr,clave);
			//valida el resultado del proceso
			if(usuario==null){
				Alertas.mensajeError("Usuario y/o password incorrecto");
				txtClave.setText("");
				txtUsuario.setText("");
				txtUsuario.requestFocus();
			}else {
				FrmPreLoader f1 = new   FrmPreLoader();
				f1.setVisible(true);
			}


		}
	}

	private String getUsuario(){
		String us = null;
		if(txtUsuario.getText().trim().length()==0){
			Alertas.mensajeError("Ingresar Usuario");
			txtUsuario.requestFocus();
		}else {
			us=txtUsuario.getText();
		}

		return us;
	}

	private String getClave(){
		String clav=null;
		if(txtClave.getText().trim().length()==0){
			Alertas.mensajeError("Ingresar la clave");
			txtClave.requestFocus();
		}else {
			clav = String.valueOf(txtClave.getPassword());
		}
		return clav;
	}
}
