package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;
import objetos.JTextFieldLimit;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Imprimir_Plantilla_Activa extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	//0 son las filas y 5 las columnas
	DefaultTableModel model = new DefaultTableModel(0,6){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	JButton Impresion = new JButton("Imprimir");
	JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	String Puestos[] = new Obj_Puesto().Combo_Puesto();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbPuestos = new JComboBox(Puestos);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	//el constructor de la clase
	public Cat_Imprimir_Plantilla_Activa()	{
		
		this.setTitle("Filtro de Plantilla Activa");
		panel.setBorder(BorderFactory.createTitledBorder("Plantilla Activa"));
		txtFolio.setDocument(new JTextFieldLimit(10));
		
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  

		panel.add(getPanelTabla()).setBounds(15,45,570,600);
		
		//se agrega los componenetes a la pantalla (x,y,Largo,Ancho)
		panel.add(txtFolio).setBounds(15,20,39,20);
		panel.add(txtNombre_Completo).setBounds(55,20,288,20);
		panel.add(cmbEstablecimientos).setBounds(345,20, 138, 20);
		panel.add(cmbPuestos).setBounds(490,20,195, 20);
		panel.add(Impresion).setBounds (700,20,80,20);
		
	    cont.add(panel);
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		cmbPuestos.addActionListener(opFiltroPuesto);
		Impresion.addActionListener(Imprimir);

		this.setSize(800,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}


	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
		}
		public void keyTyped(KeyEvent arg0) {
			char caracter = arg0.getKeyChar();
			if(((caracter < '0') ||
				(caracter > '9')) &&
			    (caracter != KeyEvent.VK_BACK_SPACE)){
				arg0.consume(); 
			}	
		}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	KeyListener opFiltroNombre = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	ActionListener opFiltro = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	ActionListener Imprimir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			MessageFormat encabezado = new MessageFormat("Lista de Empleados Activos({0,number,integer})");
			try{tabla.print(JTable.PrintMode.NORMAL,encabezado,null);
				
			}
			catch(java.awt.print.PrinterException e1){
				JOptionPane.showMessageDialog(null,"No se Pudo Imprimir","Aviso",JOptionPane.WARNING_MESSAGE);
			}
		}
	};
ActionListener opFiltroPuesto = new ActionListener(){
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent arg0){
		if(cmbPuestos.getSelectedIndex() != 0){
			trsfiltro.setRowFilter(RowFilter.regexFilter(cmbPuestos.getSelectedItem()+"", 3));
		}else{
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
		}
	}
};
 
	private JScrollPane getPanelTabla()	{		
		new Connexion();
		
	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		DefaultTableCellRenderer tcrR = new DefaultTableCellRenderer();
		tcrR.setHorizontalAlignment(SwingConstants.RIGHT);
		
		DefaultTableCellRenderer tcrL = new DefaultTableCellRenderer();
		tcrL.setHorizontalAlignment(SwingConstants.LEFT);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcrR);

		tabla.getColumnModel().getColumn(0).setHeaderValue("");
		tabla.getColumnModel().getColumn(0).setMaxWidth(20);
		tabla.getColumnModel().getColumn(0).setMinWidth(20);
		tabla.getColumnModel().getColumn(1).setHeaderValue("");
		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
		tabla.getColumnModel().getColumn(1).setMinWidth(160);
		tabla.getColumnModel().getColumn(2).setHeaderValue("");
		tabla.getColumnModel().getColumn(2).setMaxWidth(80);
		tabla.getColumnModel().getColumn(2).setMinWidth(80);
		tabla.getColumnModel().getColumn(3).setHeaderValue("");
		tabla.getColumnModel().getColumn(3).setMaxWidth(170);
		tabla.getColumnModel().getColumn(3).setMinWidth(170);
		tabla.getColumnModel().getColumn(4).setHeaderValue("");
		tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		tabla.getColumnModel().getColumn(4).setMinWidth(60);
		tabla.getColumnModel().getColumn(5).setHeaderValue("");
		tabla.getColumnModel().getColumn(5).setMaxWidth(50);
		tabla.getColumnModel().getColumn(5).setMinWidth(50);
		tabla.setRowHeight(8);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				if(row == 0){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(214,214,214));
				}
				lbl.setFont(new java.awt.Font("",0,7));

			return lbl; 
			
			} 
		}; 
		tabla.getColumnModel().getColumn(0).setCellRenderer(render);
		tabla.getColumnModel().getColumn(1).setCellRenderer(render);
		tabla.getColumnModel().getColumn(2).setCellRenderer(render);
		tabla.getColumnModel().getColumn(3).setCellRenderer(render);
		tabla.getColumnModel().getColumn(4).setCellRenderer(render);
		tabla.getColumnModel().getColumn(5).setCellRenderer(render);
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("exec sp_plantilla_activa");
			int contador = 0;
			while (rs.next()) {
				String [] fila = new String[7];
				if(contador == 0){
					fila[0] = "Folio";
					fila[1] = "Nombre Completo";
					fila[2] = "Establecimiento"; 
					fila[3] = "Puesto";
					fila[4] = "Descanso";
					fila[5] = "Dobla"; 
					model.addRow(fila); 
					fila[0] =rs.getString(1)+"  ";
					fila[1] = "  "+rs.getString(2).trim();
					fila[2] =rs.getString(3).trim(); 
					fila[3] =rs.getString(4).trim();
					fila[4] =rs.getString(5).trim();
					fila[5] =rs.getString(6).trim(); 
					model.addRow(fila); 
				}else{
					fila[0] =rs.getString(1)+"  ";
					fila[1] = "  "+rs.getString(2).trim();
					fila[2] =rs.getString(3).trim(); 
					fila[3] =rs.getString(4).trim();
					fila[4] =rs.getString(5).trim();
					fila[5] =rs.getString(6).trim(); 
					model.addRow(fila); 
				}
				contador ++;
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JScrollPane scrol = new JScrollPane(tabla);	   
	    return scrol; 
	}
	
}

