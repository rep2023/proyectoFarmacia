package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;


import entidad.TipoUsuario;
import entidad.Usuario;
import mantenimiento.GestionTipoUsuarioDAO;
import mantenimiento.GestionUsuarioDAO;
import utils.Validaciones;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class FrmRegistro extends JFrame implements ActionListener, MouseListener {
	
	// esta en el 2:56:21 del video E: sesion 07
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JDateChooser txtFecha;
	
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JTextField txtCodigo;
	private JButton btnBuscar;
	private JTable tblUsuarios;
	private JButton btnNewButton;
	private JButton btnActualizar;
	private JScrollPane scrollPane;
	private JComboBox cboTipos;
	// Estructura de la tabla
	DefaultTableModel model = new DefaultTableModel();
	// Instanciar objeto GetionTipoUsuarioDAO
	GestionTipoUsuarioDAO gTip = new GestionTipoUsuarioDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistro frame = new FrmRegistro();
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
	public FrmRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1029, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroDeUsuario = new JLabel("Registro de Usuario");
		lblRegistroDeUsuario.setBounds(517, 11, 224, 32);
		contentPane.add(lblRegistroDeUsuario);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(619, 36, 83, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblAutogenerado = new JLabel("Autogenerado");
		lblAutogenerado.setBounds(160, 20, 92, 14);
		contentPane.add(lblAutogenerado);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(619, 64, 83, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(619, 89, 83, 14);
		contentPane.add(lblApellido);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(619, 123, 83, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(619, 148, 83, 19);
		contentPane.add(lblClave);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(619, 178, 83, 14);
		contentPane.add(lblFecha);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(707, 61, 130, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(707, 86, 130, 20);
		contentPane.add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(707, 120, 130, 20);
		contentPane.add(txtUsuario);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(707, 147, 130, 20);
		contentPane.add(txtClave);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarDatos();
			}
		});
		btnRegistrar.setBounds(613, 274, 89, 23);
		contentPane.add(btnRegistrar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(613, 245, 89, 23);
		contentPane.add(btnNuevo);
		
		txtFecha = new JDateChooser();
		txtFecha.setDateFormatString("dd-MM-yyyy");
		txtFecha.setBounds(707, 178, 130, 20);
		contentPane.add(txtFecha);
		
		btnEliminar = new JButton("Eliminar");
		//btnEliminar.setIcon(new ImageIcon(FrmRegistro.class.getResource("/img/eliminar.png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(707, 274, 89, 23);
		contentPane.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(707, 245, 89, 23);
		contentPane.add(btnBuscar);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(710, 33, 127, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 578, 244);
		contentPane.add(scrollPane);
		
		tblUsuarios = new JTable();
		tblUsuarios.addMouseListener(this);
		scrollPane.setViewportView(tblUsuarios);
		tblUsuarios.setFillsViewportHeight(true);
		
		// Crear columnas para la tabla
		model.addColumn("Codigo");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Usuario");
		model.addColumn("Clave");
		model.addColumn("Fecha");
		model.addColumn("Tipo");
		model.addColumn("Estado");
		//asociar tabla con objeto model
		tblUsuarios.setModel(model);
		
		
		btnNewButton = new JButton("Limpiar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(811, 274, 89, 23);
		contentPane.add(btnNewButton);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(811, 245, 89, 23);
		contentPane.add(btnActualizar);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(619, 203, 59, 14);
		contentPane.add(lblTipo);
		
		cboTipos = new JComboBox();
		cboTipos.setBounds(707, 203, 130, 22);
		contentPane.add(cboTipos);
		
		//cargar data a la tabla
		cargarDatosUsuario();
		mostrarData(0);
		cargarDataCbo();
	}
	
	void registrarDatos(){
		// variables
		String nomb,ape,user,clave,fecha;
		int tipo;
		
		// entradas
		nomb=getNombre();
		ape=getApellido();
		user=getUsuario();
		clave=getClave();
		fecha=getFecha();
		tipo = getTipo();
		//validar
		if(nomb==null || ape==null||user==null||clave==null||fecha==null) {
			return;
		}else {
			//crear un objeto de la clase Usuario
			Usuario u = new Usuario();
			//setear --> asignar los valores de la GUI a los atributos privados
			u.setNombre(nomb);
			u.setApellido(ape);
			u.setUsuario(user);
			u.setClave(clave);
			u.setFecha(fecha);
			u.setTipo(tipo);
			
			//llamar al proceso de registro
			int res = gUser.registrarUsuario(u);
			//Validar el resultado del proceso de registro
			if(res==0) {
				mensajeError("No se registro");
			}else {
				mensajeExitoso("Usuario registrado");
			}
		}
		// procesos
		cargarDatosUsuario();
		// salidas
		
		
	}

	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this,msj, "Sistema",1);
		
	}

	private String getFecha() {
		String fecha=null;
		//a�o, mes, dia
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		fecha = sdf.format(txtFecha.getDate());
		return fecha;
	}

	private String getClave() {
		String pass=null;
		
		if(String.valueOf(txtClave.getPassword()).trim().length()==0) {
			mensajeError("Ingrese el Password:");
			txtClave.requestFocus();
		}else if(String.valueOf(txtClave.getPassword()).matches(Validaciones.PASSWORD)) {
			pass=encriptado(String.valueOf(txtClave.getPassword()).trim());
		}else {
			mensajeError("Formato incorrecto, ingresar password letras y numeros:");
			txtClave.setText("");
			txtClave.requestFocus();
		}
		//pass = encriptado(String.valueOf(txtClave.getPassword()));
		return pass;
	}
		private String encriptado(String clave){
							StringBuilder encrip= new StringBuilder();
							encrip.append(clave);
							for(int i=0;i<encrip.length();i++) {
								switch(encrip.charAt(i)) {
								case 'a':encrip.setCharAt(i, 'e');break;
								case 'e':encrip.setCharAt(i, 'i');break;
								case 'i':encrip.setCharAt(i, 'o');break;
								case 'o':encrip.setCharAt(i, 'u');break;
								case 'u':encrip.setCharAt(i, 'a');break;
								}
		}
		return encrip.reverse().toString();
		}
	

	private String getUsuario() {
		String user=null;
		if(txtUsuario.getText().trim().length()==0) {
			mensajeError("Ingrese el c�digo de Usuario:");
			txtUsuario.requestFocus();
		}else if(txtUsuario.getText().trim().matches(Validaciones.CODIGO_USUARIO)) {
			user=txtUsuario.getText().trim();
		}else {
			mensajeError("Formato incorrecto, ingresar codigo correcto:");
			txtUsuario.setText("");
			txtUsuario.requestFocus();
		}
		
		return user;
	}

	private String getApellido() {
		String ape=null;
		if(txtApellido.getText().trim().length()==0) {
			mensajeError("Ingrese el Apellido:");
			txtNombre.requestFocus();
		}else if(txtApellido.getText().trim().matches(Validaciones.APELLIDO_USUARIO)) {
			ape=txtApellido.getText().trim();
		}else {
			mensajeError("Formato incorrecto, ingresar de 4 a 35 letras");
			txtApellido.setText("");
			txtApellido.requestFocus();
		}
		
		return ape;
	}

	private String getNombre() {
		String nomb = null;
		if(txtNombre.getText().trim().length()==0) {
			mensajeError("Ingrese el nombre:");
			txtNombre.requestFocus();
		}else if(txtNombre.getText().trim().matches(Validaciones.NOMBRE_USUARIO)) {
			nomb=txtNombre.getText().trim();
		}else {
			mensajeError("Formato incorrecto, ingresar de 4 a 20 letras");
			txtNombre.setText("");
			txtNombre.requestFocus();
		}
				
		return nomb;
	}
	
	private int getCodigo() {
				
		return 3;//llenar este parte
	}
	
	private int getTipo() {
		int tip = cboTipos.getSelectedIndex();
		 
		 return tip;
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this,msj,"Error !!!",0);
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizarJButton(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButtonJButton(e);
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscarJButton(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminarJButton(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnLimpiarJButton(e);
		}
	}
	protected void actionPerformedBtnLimpiarJButton(ActionEvent e) {
		nuevoUsuario();
	}

	private void nuevoUsuario() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtUsuario.setText("");
		txtClave.setText("");
		txtFecha.setDate(null);
		//cursor activo
		txtNombre.requestFocus();
		
	}
	protected void actionPerformedBtnEliminarJButton(ActionEvent e) {
		eliminarUsuario();
	}

	private void eliminarUsuario() {
		int cod, opcion;
		// 1 obtener el c�digo ingresado
		cod= getCodigo();
		if(cod==-1) {
			return;
		}else {
			//mensaje de confirmaci�n
			opcion = JOptionPane.showConfirmDialog(this, "Seguro de eliminar","Sistema",JOptionPane.YES_NO_OPTION);
			if(opcion ==0) {
				//Ejecutar el proceso
				int ok=gUser.eliminarUsuario(cod);
				//validar el resultado del proceso
				if(ok==0) {
					mensajeError("Codigo no existe");
				}else {
					mensajeExitoso("Usuario Eliminado");
				}
			}
		}
		
		
		
	}
	protected void actionPerformedBtnBuscarJButton(ActionEvent e) {
		buscarUsuario();
	}

	private void buscarUsuario() {
		int codigo;
		codigo = getCodigo();
		//validar
		if(codigo==-1) {
			return;
		}else {
			//llamar al proceso
			Usuario user= gUser.buscarUsuario(codigo);
			//Validar el resultado del proceso
			if(user==null) {
				mensajeError("Cdigo no existe");
			}else {
				txtNombre.setText(user.getNombre());
				txtApellido.setText(user.getApellido());
				txtUsuario.setText(user.getUsuario());
				txtClave.setText(user.getClave());
				try {
					txtFecha.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(user.getFecha()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en el formato de la fecha:");
				}
			}
			
		}
		
	}
	protected void actionPerformedBtnNewButtonJButton(ActionEvent e) {
		 limpiar();
	}

	private void limpiar() {
		
		txtCodigo.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtUsuario.setText("");
		txtClave.setText("");
		txtFecha.setDate(null);
		
	}
	protected void actionPerformedBtnActualizarJButton(ActionEvent e) {
		actualizarUsuario();
	}

	private void actualizarUsuario() {
		// variables
				String nomb,ape,user,clave,fecha;
				int codigo;
				
				// entradas
				nomb=getNombre();
				ape=getApellido();
				user=getUsuario();
				clave=getClave();
				fecha=getFecha();
				codigo=getCodigo();
				//validar
				if(nomb==null || ape==null||user==null||clave==null||fecha==null||codigo==-1) {
					return;
				}else {
					//crear un objeto de la clase Usuario
					Usuario u = new Usuario();
					//setear --> asignar los valores de la GUI a los atributos privados
					u.setNombre(nomb);
					u.setApellido(ape);
					u.setUsuario(user);
					u.setClave(clave);
					u.setFecha(fecha);
					u.setCodigo(codigo);
					//llamar al proceso de ACTUALIZAR
					int res = gUser.actualizarUsuario(u);
					//Validar el resultado del proceso de ACTUALIZAR
					if(res==0) {
						mensajeError("No se ha actualizado");
					}else {
						mensajeExitoso("Usuario actualizado");
					}
		
	}
}
	
	private void cargarDatosUsuario(){
		//paso 1 limpiar la tabla
		model.setRowCount(0);
		//paso 2: obtener el resultado del proceso de consulta
		ArrayList<Usuario> list = gUser.listarUsuarios();
		//paso 3: bucle para el recorrido
		for(Usuario u: list) {
			Object fila []= {
					u.getCodigo(),
					u.getNombre(),
					u.getApellido(),
					u.getUsuario(),
					u.getClave(),
					u.getFecha(),
					u.getTipo(),
					u.getEstado()		
			};
		//a�adir fila a la tabla
			model.addRow(fila);
		}
	}
	private void mostrarData(int fila) {
		//variables
		int cod;
		//paso1: obtener datos de la tabla
		cod = (int) tblUsuarios.getValueAt(fila, 0);
		//validar
				if(cod==-1) {
					return;
				}else {
					//llamar al proceso
					Usuario user= gUser.buscarUsuario(cod);
					//Validar el resultado del proceso
					if(user==null) {
						mensajeError("Codigo no existe");
					}else {
						txtNombre.setText(user.getNombre());
						txtApellido.setText(user.getApellido());
						txtUsuario.setText(user.getUsuario());
						txtClave.setText(user.getClave());
						txtCodigo.setText(Integer.toString(cod));
						try {
							txtFecha.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(user.getFecha()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							System.out.println("Error en el formato de la fecha:");
						}
					}
					
				}
		
	}
	
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblUsuarios) {
			mouseClickedTblUsuariosJTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTblUsuariosJTable(MouseEvent e) {
		int fila;
		//obtener el valor de la fila seleccionada
		fila= tblUsuarios.getSelectedRow();
		//mostrar data
		mostrarData(fila);
	}
	
	//cargar data al cbo
	private void cargarDataCbo() {
		//1. obtener el el resultado del proceso--listar
		ArrayList<TipoUsuario> lista = gTip.listarTipoUsuario();
		//2. validar el resultado del proceso
		if(lista.size()==0) {
			System.out.println("Lista Vaia");
		}else {
			cboTipos.addItem("Seleccione...");
			for (TipoUsuario tipoUser : lista) {
				cboTipos.addItem(tipoUser.getIdTipo()+"-"+tipoUser.getDescripcionTipo());
				
			}
			
		}
	}
}