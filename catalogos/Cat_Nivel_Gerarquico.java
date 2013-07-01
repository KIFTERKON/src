package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Nivel_Gerarquico extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	JCheckBox chStatus = new JCheckBox();
	JTextField txtDescripción = new JTextField();
	JTextField txtFolio = new JTextField();
	

	String lista[] = {"Seleccione Una Opcion","Libre","OpMultiple"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbP_Principal = new JComboBox(lista);
	
	String lista2[] = {"Seleccione Una Opcion","Libre","OpMultiple"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbP_Dependiente = new JComboBox(lista2);
	

	
	JButton btnModificar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnAgregar = new JButton("Agregar");
	JButton btnSalir = new JButton("Salir");
	JButton btnLimpìar = new JButton("Limpiar");
	JButton btnGuardar = new JButton("Guardar");
	
	DefaultTableModel modelo       = new DefaultTableModel(0,2)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	public Cat_Nivel_Gerarquico(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Nivel Gerarquico"));
		this.setTitle("Nivel Gerarquico");
		
		this.panel.add(new JLabel("Folio:")).setBounds(5,30,100,20);
		this.panel.add(txtFolio).setBounds(110,30,100,20);
		
		this.panel.add(chStatus).setBounds(210,30,20,20);
		this.panel.add(new JLabel("Status")).setBounds(230,30,40,20);
		
		this.panel.add(btnNuevo).setBounds(280,30,70,20);
		this.panel.add(btnModificar).setBounds(360,30,70,20);
		
		this.panel.add(new JLabel("Descripción:")).setBounds(5,60,71,20);
		this.panel.add(txtDescripción).setBounds(110,60,150,20);
		
		this.panel.add(new JLabel("PuestoPrincipal:")).setBounds(5,90,90,20);
		this.panel.add(cmbP_Principal).setBounds(110,90,150,20);
		
		this.panel.add(new JLabel("PuestoDependiente:")).setBounds(5,120,100,20);
		this.panel.add(cmbP_Dependiente).setBounds(110,120,150,20);
		
		this.panel.add(btnAgregar).setBounds(280,120,90,20);
		
		panel.add(getPanelTabla()).setBounds(5,160,430,100);
		
		panel.add(btnSalir).setBounds(10,270,90,20);
		panel.add(btnLimpìar).setBounds(180,270,90,20);
		panel.add(btnGuardar).setBounds(340,270,90,20);
		
		btnSalir.addActionListener(opSalir);
		btnLimpìar.addActionListener(opLimpiar);
		btnNuevo.addActionListener(opNuevo);
		
		this.setSize(450,330);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		btnNuevo.addActionListener(opNuevo);
		
		txtFolio.setEditable(false);
		txtDescripción.setEditable(false);
		cmbP_Dependiente.setEnabled(false);
		cmbP_Principal.setEnabled(false);
		chStatus.setSelected(true);
		
		cont.add(panel);
	}	
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getColumnModel().getColumn(0).setHeaderValue("Puesto");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Establecimiento");
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
			rs = s.executeQuery("select tb_opciones_respuesta.folio as [Folio],"+
					 "  tb_opciones_respuesta.descripcion as [Descripcion] "+
					"  from tb_opciones_respuesta where status=1");
			
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
	
	public void limpiar()
	{
		txtFolio.setText("");
		txtDescripción.setText("");
		chStatus.setSelected(false);
		cmbP_Dependiente.setSelectedIndex(0);
		cmbP_Principal.setSelectedIndex(0);

	}
	public void nuevo ()
	{
		txtFolio.setEditable(true);
		txtDescripción.setEditable(true);
		cmbP_Dependiente.setEnabled(true);
		cmbP_Principal.setEnabled(true);
		
	}
	
	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			nuevo();
		}
	};
	
	ActionListener opLimpiar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			limpiar();
		}
	};
	
	
	
	ActionListener opSalir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	public static void main(String[]a)
	{
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Nivel_Gerarquico().setVisible(true);
			}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
