package vista;

import com.mysql.cj.xdevapi.AddStatement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static javax.swing.BorderFactory.*;

public class FrmBoleta extends JFrame {

    private JPanel panel_2;
    private  JPanel panel_3,panel_4;
    private  JPanel contentPane;
    private Border border;
    private JLabel lblCliente,lblProducto,lblCantidad,lblNum,lblFecha;

    private  Container contenedor;
    private JTextField txtCliente,txtProducto,txtCantidad,txtNumero,txtFecha;
    private JTextField txtResCli;
    private  JButton btnBusCli,btnBusProd;
    public FrmBoleta(){
        setTitle("BOLETA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contenedor = getContentPane();
        contenedor.setLayout(null);
        border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        /************************ PANEL 02  *********************************/
        panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBounds(10,10,400,150);
        contenedor.add(panel_2);
        TitledBorder titleBoder = createTitledBorder(border,"Datos del Cliente");
        panel_2.setBorder(titleBoder);

        lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10,21,70,25);
        panel_2.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(85,21,185,25);
        panel_2.add(txtCliente);

        btnBusCli = new JButton("Buscar");
        btnBusCli.setBounds(275,21,100,25);
        panel_2.add(btnBusCli);

        txtResCli = new JTextField();
        txtResCli.setBounds(85,71,290,25);
        panel_2.add(txtResCli);
        /************************ PANEL 03  *********************************/
        panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBounds(10,180,400,150);
        contenedor.add(panel_3);
        panel_3.setBorder(createTitledBorder(border,"Datos del Producto"));

        lblProducto = new JLabel("Producto");
        lblProducto.setBounds(10,21,70,25);
        panel_3.add(lblProducto);

        txtProducto = new JTextField();
        txtProducto.setBounds(85,21,185,25);
        panel_3.add(txtProducto);

        btnBusProd = new JButton("Buscar");
        btnBusProd.setBounds(275,21,100,25);
        panel_3.add(btnBusProd);

        lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(10,71,70,25);
        panel_3.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(85,71,185,25);
        panel_3.add(txtCantidad);
        /**************************************** Panel 04   ***************************************++++*/
        panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setBounds(420,10,400,150);
        contenedor.add(panel_4);
        panel_4.setBorder(createTitledBorder(border,"Datos"));

        lblNum = new JLabel("Número");
        lblNum.setBounds(10,21,70,25);
        panel_4.add(lblNum);

        txtNumero = new JTextField();
        txtNumero.setBounds(85,21,185,25);
        panel_4.add(txtNumero);

        lblFecha = new JLabel("Fecha");
        lblFecha.setBounds(10,71,70,25);
        panel_4.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(85,71,185,25);
        panel_4.add(txtFecha);





        setSize(850,650);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmBoleta frame = new FrmBoleta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
