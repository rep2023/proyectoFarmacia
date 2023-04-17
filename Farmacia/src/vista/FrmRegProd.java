package vista;
//me quedé en 4:03:51 del video 07

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entidad.Categorias;
import entidad.Producto;
import entidad.TipoUsuario;
import mantenimiento.GestionCategoriaDAO;
//import validaciones.Validaciones;
import mantenimiento.GestionProductoDAO;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

import utils.Alertas;

public class FrmRegProd extends JFrame implements KeyListener, MouseListener, ActionListener {
	private JTextField txtCodigo;
	private JTextField txtProducto;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JComboBox cboTipo;
	private JTable tblProductos;
	
	private JScrollPane scrollPane;
	
	String[] titulo = new String[] {"CODIGO","PRODUCTO","TIPO","CANTIDAD","PRECIO"};
	private JTextField txtEstado;
	
	// Instanciar un objeto para el modelamiento de la tabla
		DefaultTableModel model = new DefaultTableModel();
	// Instanciar el gestion de productos
		GestionProductoDAO gProd = new GestionProductoDAO();
	//Instanciar gestion de categorias
		GestionCategoriaDAO gCat = new GestionCategoriaDAO();
		private JButton btnActualizar;
		private JButton btnNuevo;
		private JButton btnEliminar;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegProd frame = new FrmRegProd();
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
	public FrmRegProd() {
		//setBounds(true);
		//setIconifiable(true);
		//setClosable(true);
		setBounds(100, 100, 656, 434);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("C\u00F3digo:");
		label.setBounds(30, 48, 75, 14);
		getContentPane().add(label);

		txtCodigo = new JTextField();
		txtCodigo.setText("");
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(88, 42, 86, 20);
		getContentPane().add(txtCodigo);

		JLabel label_1 = new JLabel("Producto:");
		label_1.setBounds(30, 73, 75, 14);
		getContentPane().add(label_1);

		txtProducto = new JTextField();
		txtProducto.setText("");
		txtProducto.setColumns(10);
		txtProducto.setBounds(88, 70, 86, 20);
		getContentPane().add(txtProducto);

		JLabel label_2 = new JLabel("Tipo:");
		label_2.setBounds(30, 98, 53, 14);
		getContentPane().add(label_2);

		cboTipo = new JComboBox();
		cboTipo.setBounds(88, 94, 123, 20);
		//cboTipo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione tipo", "Pastillas", "Jarabe", "Otros" }));
		getContentPane().add(cboTipo);

		JLabel label_3 = new JLabel("Cantidad:");
		label_3.setBounds(30, 122, 60, 14);
		getContentPane().add(label_3);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(this);
		txtCantidad.setText("");
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(88, 119, 53, 20);
		getContentPane().add(txtCantidad);

		JLabel label_4 = new JLabel("Precio:");
		label_4.setBounds(178, 122, 46, 14);
		getContentPane().add(label_4);

		txtPrecio = new JTextField();
		txtPrecio.setText("");
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(220, 119, 60, 20);
		getContentPane().add(txtPrecio);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(328, 28, 116, 34);
		getContentPane().add(btnNuevo);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingresar();
			}
		});
		btnGuardar.setBounds(328, 78, 116, 34);
		getContentPane().add(btnGuardar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(328, 123, 116, 34);
		getContentPane().add(btnEliminar);

		JLabel lblMantenimientoDeProductos = new JLabel("Mantenimiento de Productos");
		lblMantenimientoDeProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMantenimientoDeProductos.setBounds(21, 13, 224, 20);
		getContentPane().add(lblMantenimientoDeProductos);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 188, 594, 196);
		getContentPane().add(scrollPane);
		
		//Creamos una tabla
		tblProductos = new JTable();
		tblProductos.addMouseListener(this);
		scrollPane.setViewportView(tblProductos);
		tblProductos.setFillsViewportHeight(true);
		
		// definir columnas
		//model.setColumnIdentifiers(titulo);
		model.addColumn("Codigo");
		model.addColumn("Producto");
		model.addColumn("Stock");
		model.addColumn("Precio");
		model.addColumn("Tipo");
		model.addColumn("Estado");

		// asociar tabla con el objeto
		tblProductos.setModel(model);
		
				JLabel lblEstado = new JLabel("Estado");
				lblEstado.setBounds(30, 163, 53, 14);
				getContentPane().add(lblEstado);
				
				txtEstado = new JTextField();
				txtEstado.setBounds(88, 157, 86, 20);
				getContentPane().add(txtEstado);
				txtEstado.setColumns(10);
				
				btnActualizar = new JButton("Actualizar");
				btnActualizar.addActionListener(this);
				btnActualizar.setBounds(470, 28, 110, 39);
				getContentPane().add(btnActualizar);
				
				cargarDataCbo();
				cargarDatosProducto();
				
	}

	void ingresar() {
		String cod, prod;
		int cant;
		double pre;
		int tipo,estado;

		cod = leerCodigo();
		prod = leerProducto();
		cant = leerCantidad();
		pre = leerPrecio();
		tipo = leerTipo();
		estado = leerEstado();
		// validación
		if (cod == null ||cant == -1 || pre == -1 || prod == null||estado==-1) {
			return; // error retorna al proceso de ingresar datos

		} else {
			// intanciar un objeto de tipo producto
			Producto p = new Producto();
			// Setear.
			p.setCant(cant);
			p.setCod(cod);
			p.setEstado(estado);
			p.setPre(pre);
			p.setProd(prod);
			p.setTipo(tipo);
			
			//invocar al proceso de registro de productos
			int ok= gProd.registrarProducto(p);
			//validamos el resultado del proceso
			if(ok==0) {
				Alertas.mensajeError("No se logró ingresar el producto");
			}else {
				Alertas.mensajeExitoso("Se ingresó el producto a la BD");
			}
		}
		cargarDatosProducto();

	}

	private int leerEstado() {
		int estado=-1;
		estado = Integer.parseInt(txtEstado.getText());
		return estado;
	}

	private String leerNombreTipo() {
		if (leerTipo() == 0) {
			Alertas.mensajeError("Eliga una opcion valida");
		} else {
			return cboTipo.getSelectedItem().toString();
		}
		return null;
	}

	private String leerCodigo() {
		// Formato P0001 ó p0001
		String cod = null;
		// validar el campo vacío
		if (txtCodigo.getText().trim().length() == 0) {
			Alertas.mensajeError("Ingresar el codigo del producto");
			//// Formato P0001 ó p0001
		} else if (txtCodigo.getText().trim()!=null) {
			cod = txtCodigo.getText().trim();
		} else {
			Alertas.mensajeError("!Formato incorrecto¡Ej. P0001 ó p0001");
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		}
		return cod;
	}

	/*private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error !!!", 0);

	}
	
	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Exito !!!", 1);

	}*/

	String leerProducto() {

		String prod = null;
		// campo vacío
		if (txtProducto.getText().trim().length() == 0) {
			Alertas.mensajeError("Ingresa nombre de Producto");
			txtProducto.setText("");
			txtProducto.requestFocus();

			// ingresar de 3 - 20 carcateres // paracetamol forte
		} else if (txtProducto.getText().trim()!=null) {
			prod = txtProducto.getText().trim();

		} else {
			Alertas.mensajeError("!Formato incorrecto¡");
			txtProducto.setText("");
			txtProducto.requestFocus();

		}
		return prod;
	}

	int leerTipo() {
		return cboTipo.getSelectedIndex();
	}

	int leerCantidad() {
		int cant = -1;
		// campo vacío
		if (txtCantidad.getText().trim().length() == 0) {
			Alertas.mensajeError("Ingresar la cantidad del producto");
			txtCantidad.setText("");
			txtCantidad.requestFocus();
		} else {// validar el ingreso de solo números enteros -9
			try {
				cant = Integer.parseInt(txtCantidad.getText());
				if (cant <= 0) {
					Alertas.mensajeError("Ingresar valores mayores a 0");
					txtCantidad.setText("");
					txtCantidad.requestFocus();
					cant = -1;
				}
			} catch (NumberFormatException e) {
				Alertas.mensajeError("Ingresar valores enteros");
				txtCantidad.setBackground(Color.RED);
				txtCantidad.setText("");
				txtCantidad.requestFocus();
			}
		}
		return cant;
	}

	double leerPrecio() {
		double pre = -1;
		// campo vacío
		if (txtPrecio.getText().trim().length() == 0) {
			Alertas.mensajeError("Ingresar el precio del producto");
			txtPrecio.setText("");
			txtPrecio.requestFocus();
		} else {// solo números -- 0
			try {
				pre = Double.parseDouble(txtPrecio.getText());
				if (pre <= 0) {
					Alertas.mensajeError("Ingresar un precio mayor a 0");
					txtPrecio.setText("");
					txtPrecio.requestFocus();
					pre = -1;
				}
			} catch (NumberFormatException e) {
				Alertas.mensajeError("Ingresar valores numéricos en precio");
				txtPrecio.setText("");
				txtPrecio.requestFocus();
			}
		}
		return pre;
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (e.getSource() == tblProductos) {
			keyReleasedTblProductos(e);
		}
		if (e.getSource() == txtCantidad) {
			keyReleasedTxtCantidad(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	protected void keyReleasedTxtCantidad(KeyEvent e) {
		txtCantidad.setBackground(Color.WHITE);
	}

	// método para mostrar datos de la tabla en la cajas de texto
	private void mostrarDatos(int fila) {
		// paso 1: Obtener los datos de la tabla
		String cod, prod, pre, cant,estado;
		int tipo;
		cod = tblProductos.getValueAt(fila, 0).toString();
		prod = tblProductos.getValueAt(fila, 1).toString();
		cant = tblProductos.getValueAt(fila, 2).toString();
		pre = tblProductos.getValueAt(fila, 3).toString();
		tipo = (int) tblProductos.getValueAt(fila,4);
		estado = tblProductos.getValueAt(fila, 5).toString();

		// paso 2: mostrar los datos obtenidos en las cajas de texto
		txtCodigo.setText(cod);
		txtProducto.setText(prod);
		//cboTipo.setSelectedItem(tipo);
		cboTipo.setSelectedIndex(tipo);
		txtCantidad.setText(cant);
		txtPrecio.setText(pre);
		txtEstado.setText(estado);

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblProductos) {
			mouseClickedTblProductos(e);
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

	protected void mouseClickedTblProductos(MouseEvent e) {
		// obtener el valor de la fila seleccionada
		int fila = tblProductos.getSelectedRow();
		// mostrar datos
		mostrarDatos(fila);
	}

	protected void keyReleasedTblProductos(KeyEvent e) {
		// obtener el valor de la fila seleccionada
		int fila = tblProductos.getSelectedRow();
		// mostrar datos
		mostrarDatos(fila);
	}
	
	private void cargarDataCbo(){
		//1. obtener el el resultado del proceso--listar
		ArrayList<Categorias> lista = gCat.listarCategoria();
		//2. validar el resultado del proceso
		if(lista.size()==0) {
			System.out.println("Lista Vaia");
		}else {
			cboTipo.addItem("Seleccione...");
			for (Categorias c : lista) {
				cboTipo.addItem(c.getIdCategoria()+"-"+c.getDescripcion());
				
			}
			
		}
	}
	
	private void cargarDatosProducto(){
		//paso 1: limpiar la tabla
		model.setRowCount(0);
		//paso 2: obtener el proceso de la consulta
		ArrayList<Producto> lista = new ArrayList<>();
		lista = gProd.listarProducto();
		//paso 3:bucle para el recorrido
		for(Producto p :lista) {
			Object fila[]= {p.getCod(),p.getProd(),p.getCant(),
							p.getPre(),p.getTipo(),p.getEstado()};
		//Añador fila a la tabla
		model.addRow(fila);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminarJButton(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevoJButton(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizarJButton(e);
		}
	}
	protected void actionPerformedBtnActualizarJButton(ActionEvent e) {
		actualizarProducto();
	}

	private void actualizarProducto() {
		String cod, prod;
		int cant;
		double pre;
		int tipo,estado;

		cod = leerCodigo();
		prod = leerProducto();
		cant = leerCantidad();
		pre = leerPrecio();
		tipo = leerTipo();
		estado = leerEstado();
		// validación
		if (cod == null ||cant == -1 || pre == -1 || prod == null||estado==-1) {
			return; // error retorna al proceso de ingresar datos

		} else {
			// intanciar un objeto de tipo producto
			Producto p = new Producto();
			// Setear.
			p.setCant(cant);
			p.setCod(cod);
			p.setEstado(estado);
			p.setPre(pre);
			p.setProd(prod);
			p.setTipo(tipo);
			
			//invocar al proceso de actualizar el productos
			int ok= gProd.actualizarProducto(p);
			//validamos el resultado del proceso
			if(ok==0) {
				Alertas.mensajeError("No se logró actualizar el producto");
			}else {
				Alertas.mensajeExitoso("Producto actualizado en la BD");
			}
		}
		cargarDatosProducto();
		
	}
	protected void actionPerformedBtnNuevoJButton(ActionEvent e) {
		nuevoProducto();
	}

	private void nuevoProducto() {
		txtCodigo.setText("");
		txtProducto.setText("");
		cboTipo.setSelectedIndex(0);
		txtCantidad.setText("");
		txtPrecio.setText("");
		txtEstado.setText("");	
	}
	protected void actionPerformedBtnEliminarJButton(ActionEvent e) {
		eliminarRegistro();
	}

	private void eliminarRegistro() {
		String cod;
		cod = leerCodigo();
		gProd.eliminarProducto(cod);
		cargarDatosProducto();
		nuevoProducto();
		
	}
}
