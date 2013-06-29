package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_ORespuesta extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	JTextField txtOpcion = new JTextField();
	JTextField txtDescripcion = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JSpinner spHoraInicio = new JSpinner(new SpinnerNumberModel(0,0,12,1));
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	DefaultTableModel modelo       = new DefaultTableModel(0,2)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	
	
	String lista[] = {"Seleccione Una Opcion","Libre","OpMultiple"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbRespuesta = new JComboBox(lista);
	
	public Cat_ORespuesta()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Opcion de Respuesta"));
		
		this.setTitle("Opciones de Respuesta");
		
		int x = 15, y=30, ancho=100;
		
		this.panel.add(new JLabel("Folio:")).setBounds(5,y,ancho,20);
		this.panel.add(txtOpcion).setBounds(ancho-20,y,ancho,20);
		this.panel.add(btnBuscar).setBounds(ancho+ancho-x,y,32,20);
		
		this.panel.add(chStatus).setBounds(220,y,70,20);
		
		this.panel.add(new JLabel("Opciones:")).setBounds(5,60,70,20);
		this.panel.add(cmbRespuesta).setBounds(80,60,180,20);
		
		this.panel.add(spHoraInicio).setBounds(280,60,35,20);
		
		panel.add(btnSalir).setBounds(10,170,90,20);
		panel.add(btnDeshacer).setBounds(110,170,90,20);
		panel.add(btnGuardar).setBounds(210,170,90,20);
		
		panel.add(getPanelTabla()).setBounds(5,90,385,70);
		
		btnBuscar.addKeyListener(buscar_action);
		
		
		this.setSize(400,230);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
//		cmbRespuesta.addActionListener(opciones);
		btnSalir.addActionListener(OpSalir);
		btnDeshacer.addActionListener(limpia);
		cont.add(panel);
		
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Descripcion");
		tabla.getColumnModel().getColumn(1).setMinWidth(160);
		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
		
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
			return lbl; 
			} 
		}; 
						tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select tb_op_respuesta.folio as [Folio],"+
					 "  tb_op_respuesta.descripcion as [Descripcion] "+
					"  from tb_op_respuesta where status=1");
			
			while (rs.next())
			{ 
			   String [] fila = new String[2];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
	    return scrol; 
	}
	
	ActionListener OpSalir = new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
		}
	};
	
	public void limpiar()
	{
		txtOpcion.setText("");
		cmbRespuesta.setSelectedIndex(0);
		chStatus.setSelected(false);
		spHoraInicio.setValue(0);
	}
	
	ActionListener limpia = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			limpiar();
		}
	};
	
	KeyListener buscar_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
			}
		}
	};
	
	ActionListener opciones = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(cmbRespuesta.equals("Libre"))
					{
				dispose();
				
					}
		}
	};
	
	public static void main(String[]a)
	{
		new Cat_ORespuesta().setVisible(true);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
