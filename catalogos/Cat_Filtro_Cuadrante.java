package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;

import objetos.Obj_Establecimiento;

@SuppressWarnings("serial")
public class Cat_Filtro_Cuadrante extends JFrame {
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
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
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre_Cuadrante = new JTextField();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    @SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Filtro_Cuadrante(){
    	this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
    	this.setTitle("Filtro de Cuadrante");
		campo.setBorder(BorderFactory.createTitledBorder("Filtro De Empleado"));
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(getPanelTabla()).setBounds(15,42,960,550);
		
		campo.add(txtFolio).setBounds(15,20,48,20);
		campo.add(txtNombre_Cuadrante).setBounds(64,20,229,20);
		campo.add(cmbEstablecimientos).setBounds(295,20, 148, 20);
		
		agregar(tabla);
		
		cont.add(campo);
		
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Cuadrante.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
		this.setSize(1000,650);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			int fila = tabla.getSelectedRow();
	    			int folio =  Integer.parseInt(tabla.getValueAt(fila, 0).toString().trim());
	    			dispose();
	    			new Cat_Cuadrante(folio).setVisible(true);
	        	}
	        }
        });
    }
    
   	private JScrollPane getPanelTabla()	{		
   		
   		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

   		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre de Cuadrante");
		tabla.getColumnModel().getColumn(1).setMaxWidth(320);
		tabla.getColumnModel().getColumn(1).setMinWidth(210);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(2).setMaxWidth(150);
		tabla.getColumnModel().getColumn(2).setMinWidth(150);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Jefatura de Cuadrante");
		tabla.getColumnModel().getColumn(3).setMaxWidth(380);
		tabla.getColumnModel().getColumn(3).setMinWidth(150);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Nivel Jerarquico de Cuadrante");
		tabla.getColumnModel().getColumn(4).setMaxWidth(280);
		tabla.getColumnModel().getColumn(4).setMinWidth(190);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Equipo de Trabajo de Cuadrante");
		tabla.getColumnModel().getColumn(5).setMaxWidth(320);
		tabla.getColumnModel().getColumn(5).setMinWidth(190);

		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
				
				if(table.getSelectedRow() == row){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(186,143,73));
				}
				
				switch(column){
					case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 3 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 4 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 5 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 
		
		for(int i= 0; i<tabla.getColumnCount(); i++){
			tabla.getColumnModel().getColumn(i).setCellRenderer(render); 
		}
		
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery("exec sp_select_filtro_cuadrante");
			
			while (rs.next()) { 
			   String [] fila = new String[6];
			   fila[0] = rs.getString(1)+"  ";
			   fila[1] = "   "+rs.getString(2);
			   fila[2] = "   "+rs.getString(3);
			   fila[3] = "   "+rs.getString(4);
			   fila[4] = "   "+rs.getString(5);
			   fila[5] = "   "+rs.getString(6);

			   model.addRow(fila); 
			}	
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
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
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Cuadrante.getText().toUpperCase().trim(), 1));
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
	
}
