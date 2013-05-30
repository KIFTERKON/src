package catalogos;

import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import objetos.JTextFieldLimit;

@SuppressWarnings("serial")
public class Cat_Temporada extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,3)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new JTextField();
	JTextField txtDescripcion = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	com.toedter.calendar.JDateChooser txtCalendario = new com.toedter.calendar.JDateChooser();
	com.toedter.calendar.JDateChooser txtCalendario1 = new com.toedter.calendar.JDateChooser();
	
	String dias[] = {"Seleccione un dia","Todos","Lunes","Martes","Miercoles","Jueves","Viernes","Savado","Domingo",};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbDias = new JComboBox(dias);
	
	public Cat_Temporada(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Bookmark.png"));
		this.setTitle("Actividad");
		this.panel.setBorder(BorderFactory.createTitledBorder("Actividad"));
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(5,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Descripcion:")).setBounds(5,y+=30,ancho,20);
		panel.add(txtDescripcion).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Valor:")).setBounds(5,y+=30,ancho,20);
		panel.add(btnEditar).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Fecha:      De")).setBounds(5,y+=30,100,20);
		panel.add(txtCalendario).setBounds(ancho-20,y,ancho-8,20);
		
		panel.add(new JLabel("A")).setBounds(ancho+75,y,30,20);
		panel.add(txtCalendario1).setBounds(ancho+87,y,ancho-8,20);
		
		panel.add(new JLabel("Dia: ")).setBounds(5,y+=30,100,20);
		panel.add(cmbDias).setBounds(ancho-20,y,ancho+ancho-2,20);
		
		panel.add(btnDeshacer).setBounds(x+ancho+60,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x-10+60,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
//		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho+30,20,ancho+230,180);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtDescripcion.setDocument(new JTextFieldLimit(100));
		
		chStatus.setEnabled(false);
		txtDescripcion.setEditable(false);
		
		txtFolio.requestFocus();
//		txtFolio.addKeyListener(buscar_action);
//		txtFolio.addKeyListener(numerico_action);
//		
//		txtValor.addKeyListener(validaNumericoConPunto);
		
//		btnGuardar.addActionListener(guardar);
//		btnSalir.addActionListener(cerrar);
//		btnBuscar.addActionListener(buscar);
//		btnDeshacer.addActionListener(deshacer);
//		btnNuevo.addActionListener(nuevo);
//		btnEditar.addActionListener(editar);
		btnEditar.setEnabled(false);
		cont.add(panel);
		
//		agregar(tabla);
		
		
		this.setSize(760,240);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Temporada().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
