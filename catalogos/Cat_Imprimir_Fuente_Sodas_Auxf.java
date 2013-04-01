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

@SuppressWarnings("serial")
public class Cat_Imprimir_Fuente_Sodas_Auxf extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	//0 son las filas y 5 las columnas
	DefaultTableModel model = new DefaultTableModel(0,4){
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	//el constructor de la clase
	public Cat_Imprimir_Fuente_Sodas_Auxf()	{
		
		this.setTitle("Lista de Totales Por Empleado de Fuente de Sodas Auxiliar Finanzas");
		panel.setBorder(BorderFactory.createTitledBorder("Auxiliar de Finanzas"));
		txtFolio.setDocument(new JTextFieldLimit(10));
		
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  

		panel.add(getPanelTabla()).setBounds(15,45,500,600);
		
	//se agrega los componenetes a la pantalla (x,y,Largo,Ancho)
     	panel.add(txtFolio).setBounds(15,20,39,20);
	    panel.add(txtNombre_Completo).setBounds(55,20,288,20);
	    panel.add(cmbEstablecimientos).setBounds(345,20, 138, 20);
		panel.add(Impresion).setBounds (520,20,80,20);
		
	    cont.add(panel);
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
	
		Impresion.addActionListener(Imprimir);
		this.setModal(true);
		this.setSize(650,700);
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
			MessageFormat encabezado = new MessageFormat("Lista de Fuente de Sodas Aux Finanzas{0,number,integer})");
			try{tabla.print(JTable.PrintMode.NORMAL,encabezado,null);
				
			}
			catch(java.awt.print.PrinterException e1){
				JOptionPane.showMessageDialog(null,"No se Pudo Imprimir","Aviso",JOptionPane.WARNING_MESSAGE);
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
		tabla.getColumnModel().getColumn(0).setMaxWidth(34);
		tabla.getColumnModel().getColumn(0).setMinWidth(34);
		tabla.getColumnModel().getColumn(1).setHeaderValue("");
		tabla.getColumnModel().getColumn(1).setMaxWidth(288);
		tabla.getColumnModel().getColumn(1).setMinWidth(288);
		tabla.getColumnModel().getColumn(2).setHeaderValue("");
		tabla.getColumnModel().getColumn(2).setMaxWidth(115);
		tabla.getColumnModel().getColumn(2).setMinWidth(115);
		tabla.getColumnModel().getColumn(3).setHeaderValue("");
		tabla.getColumnModel().getColumn(3).setMaxWidth(55);
		tabla.getColumnModel().getColumn(3).setMinWidth(55);

		tabla.setRowHeight(8);
		
		TableCellRenderer render = new TableCellRenderer() { 
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

		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("exec sp_consulta_fsodas_auxf_impres");
			int contador = 0;
	    while (rs.next()) {
				String [] fila = new String[4];
				if(contador == 0){
					fila[0] = "Folio";
					fila[1] = "Nombre Completo";
					fila[2] = "Establecimiento"; 
					fila[3] = "Total";
					model.addRow(fila); 
					fila[0] =rs.getString(1)+"  ";
					fila[1] = "  "+rs.getString(2).trim();
					fila[2] =rs.getString(3).trim(); 
					fila[3] =rs.getString(4).trim();
					model.addRow(fila);
				}else{
					fila[0] =rs.getString(1)+"  ";
					fila[1] = "  "+rs.getString(2).trim();
					fila[2] =rs.getString(3).trim(); 
					fila[3] =rs.getString(4).trim();
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
	
	public static void main(String args[]){
		new Cat_Imprimir_Fuente_Sodas_Auxf().setVisible(true);
	}
	
}

