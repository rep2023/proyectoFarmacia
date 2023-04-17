package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.HiloBarraProgreso;
//import hilos.HiloTiempo;
//import hilos.MostrarHoras;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FrmPreLoader extends JFrame implements ChangeListener {

	private JPanel contentPane;
	public static JProgressBar prbCarga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					FrmPreLoader frame = new FrmPreLoader();
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
	public FrmPreLoader() {
		setTitle("Cargando...");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 157);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		prbCarga = new JProgressBar();
		prbCarga.addChangeListener(this);
		prbCarga.setStringPainted(true);
		prbCarga.setBounds(26, 64, 313, 19);
		contentPane.add(prbCarga);
		
		JLabel lblMensajes = new JLabel(" Espere unos segundos");
		lblMensajes.setForeground(Color.BLUE);
		lblMensajes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMensajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajes.setBounds(10, 5, 313, 14);
		contentPane.add(lblMensajes);
		cargarBarraProgreso();
	}
	private void cargarBarraProgreso() {
		HiloBarraProgreso h = new HiloBarraProgreso();
		h.start();
		
	}
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == prbCarga) {
			stateChangedPrbCargaJProgressBar(e);
		}
	}
	protected void stateChangedPrbCargaJProgressBar(ChangeEvent e) {
		abrirVentanaPrincipal();
	}

	private void abrirVentanaPrincipal() {
		if(prbCarga.getValue()==100) {
			FrmPrincipal prin = new FrmPrincipal();
	
			prin.setVisible(true);
			prin.setLocationRelativeTo(this);
			this.dispose();
			
			
		}
		
	}
}