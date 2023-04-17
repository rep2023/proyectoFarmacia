package vista;

import entidad.ReporteUsuario;
import entidad.TipoUsuario;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import mantenimiento.GestionTipoUsuarioDAO;
import mantenimiento.GestionUsuarioDAO;


import javax.swing.table.DefaultTableModel;

public class Reporte2 extends JFrame {

	private JPanel contentPane;
	private JComboBox cboTipo;
	private JScrollPane scrollPane;
	private JTable tblReporteUsuario;
	GestionTipoUsuarioDAO gtU = new GestionTipoUsuarioDAO();
	GestionUsuarioDAO gTip = new GestionUsuarioDAO();
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Estoy en el 00:22:18 de sesion de recuperaci?n, ubicado en
	 * G:\LPI\LPI
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte2 frame = new Reporte2();
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
	public Reporte2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("LISTADO DE USUARIOS");
		lblTitulo.setBounds(27, 24, 200, 14);
		contentPane.add(lblTitulo);

		JLabel lblTipo = new JLabel("TIPO: ");
		lblTipo.setBounds(10, 49, 46, 14);
		contentPane.add(lblTipo);

		cboTipo = new JComboBox();
		cboTipo.setBounds(49, 45, 123, 22);
		contentPane.add(cboTipo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 107, 446, 221);
		contentPane.add(scrollPane);

		tblReporteUsuario = new JTable();
		scrollPane.setViewportView(tblReporteUsuario);
		tblReporteUsuario.setFillsViewportHeight(true);
		//crear columnas para la tabla
		model.addColumn("CODIGO");
		model.addColumn("NOMBRE COMPLETO");
		model.addColumn("DESCRIPCION");
		//asociar tabla con objeto model
		tblReporteUsuario.setModel(model);

		JButton btnReporte = new JButton("REPORTE");
		btnReporte.setBounds(10, 332, 100, 23);
		contentPane.add(btnReporte);
		//evento
		btnReporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				cargarDatosRU();
			}
		});
		cargarDataCbo();
	}

	private void cargarDataCbo() {
		//1. obtener el el resultado del proceso--listar
		ArrayList<TipoUsuario> lista = gtU.listarTipoUsuario();
		//2. validar el resultado del proceso
		if (lista.size() == 0) {
			System.out.println("Lista Vaia");
		} else {
			cboTipo.addItem("Seleccione...");
			for (TipoUsuario tipoUser : lista) {
				cboTipo.addItem(tipoUser.getIdTipo() + "-" + tipoUser.getDescripcionTipo());

			}

		}
	}

	private void cargarDatosRU() {
		//limpiar tabla
		model.setRowCount(0);
		//resultado de consulta
		int tipo = cboTipo.getSelectedIndex();
		ArrayList<ReporteUsuario> list = gTip.listarXTipo(tipo);
		//bucle
		for (ReporteUsuario ru : list) {
			Object fila[] = {ru.getCodigo(), ru.getNomCompletos(), ru.getDescrpTipo()};

			//a?dir
			model.addRow(fila);
		}

	}
}
