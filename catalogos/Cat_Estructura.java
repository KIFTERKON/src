package catalogos;

import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Cat_Estructura extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();

	DefaultTableModel modelo = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(modelo);
	
	JScrollPane scroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new JTextField();
	JTextField txtDescripcion = new JTextField();
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnSalir = new JButton("Salir");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));

	JCheckBox chbStatus = new JCheckBox("Status",true);
	
	public Cat_Estructura(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Report.png"));
		this.setTitle("Estructura");
		panel.setBorder(BorderFactory.createTitledBorder("Estructura"));
		
		this.panel.add(new JLabel("Folio:")).setBounds(15,15,100,20);
		this.panel.add(txtFolio).setBounds(115,15,80,20);
		
		this.panel.add(new JLabel("Descripción:")).setBounds(15,40,100,20);
		this.panel.add(txtDescripcion).setBounds(115,40,210,20);

		this.panel.add(scroll).setBounds(335,15,250,190);
		this.panel.add(btnBuscar).setBounds(205,15,35,20);
		this.panel.add(chbStatus).setBounds(260,15,70,20);
		
		this.panel.add(btnSalir).setBounds(15,70,80,20);
		this.panel.add(btnLimpiar).setBounds(130,70,80,20);
		this.panel.add(btnGuardar).setBounds(245,70,80,20);
		
		this.tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tabla.getColumnModel().getColumn(0).setMinWidth(60);
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tabla.getColumnModel().getColumn(1).setHeaderValue("Descripción");
		
		this.cont.add(panel);
		this.setSize(600,250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Estructura().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
