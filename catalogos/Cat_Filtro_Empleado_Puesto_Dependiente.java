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
import java.util.StringTokenizer;

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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;

import objetos.Obj_Establecimiento;
import objetos.Obj_Usuario;

@SuppressWarnings("serial")
public class Cat_Filtro_Empleado_Puesto_Dependiente extends JFrame {
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	DefaultTableModel model = new DefaultTableModel(0, 7){
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
	JTextField txtNombre_Completo = new JTextField();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    @SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Filtro_Empleado_Puesto_Dependiente(){
    	this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		this.setTitle("Filtro de Empleados con Puestos Dependientes");
		campo.setBorder(BorderFactory.createTitledBorder("Filtro de Empleados con Puestos Dependientes"));
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(getPanelTabla()).setBounds(15,42,1100,565);
		
		campo.add(txtFolio).setBounds(15,20,48,20);
		campo.add(txtNombre_Completo).setBounds(64,20,229,20);
		campo.add(cmbEstablecimientos).setBounds(295,20, 148, 20);
		
		agregar(tabla);
		
		cont.add(campo);
		
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
		this.setSize(1140,650);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0).toString().trim();
	    			Object Nombre = tabla.getValueAt(fila,1).toString().trim();
	    			dispose();
	    			new Cat_Cuadrante_Nivel_Jerarquico(String.valueOf(Nombre), Integer.parseInt(folio.toString())).setVisible(true);
	        	}
	        }
        });
    }
    
 	private JScrollPane getPanelTabla()	{		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(1).setMaxWidth(230);
		tabla.getColumnModel().getColumn(1).setMinWidth(230);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(2).setMaxWidth(150);
		tabla.getColumnModel().getColumn(2).setMinWidth(150);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Puesto Raíz");
		tabla.getColumnModel().getColumn(3).setMaxWidth(180);
		tabla.getColumnModel().getColumn(3).setMinWidth(180);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Puesto Dependiente");
		tabla.getColumnModel().getColumn(4).setMaxWidth(180);
		tabla.getColumnModel().getColumn(4).setMinWidth(180);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Cuadrante");
		tabla.getColumnModel().getColumn(5).setMaxWidth(238);
		tabla.getColumnModel().getColumn(5).setMinWidth(238);
		tabla.getColumnModel().getColumn(6).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(6).setMaxWidth(70);
		tabla.getColumnModel().getColumn(6).setMinWidth(70);
		
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
					case 6 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 

		for(int x = 0; x<tabla.getColumnCount(); x++){
			this.tabla.getColumnModel().getColumn(x).setCellRenderer(render); 
		}
		
		Statement s;
		ResultSet rs;
		Obj_Usuario usuario = new Obj_Usuario().LeerSession();
		try {
			s = new Connexion().conexion().createStatement();
			String query = "exec sp_construir_in_filtro_nivel_jerarquico '"+usuario.getNombre_completo()+"'";
			rs = s.executeQuery(query);
			while (rs.next()) { 
			   String [] fila = new String[7];
			   
			   fila[0] = rs.getString(1)+"  ";
			   fila[1] = "   "+rs.getString(2);
			   fila[2] = "   "+rs.getString(3).trim();
			   fila[3] = "   " +rs.getString(4);
			   fila[4] = "   "+rs.getString(5).trim();
			   fila[5] = "   "+rs.getString(6);
			   fila[6] = "   "+rs.getString(7).trim();
			
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
 	
 	public String getnombreCompleto(){
 		return new Obj_Usuario().LeerSession().getNombre_completo();
 	}
 	
 	public String procesa_texto(String texto) {
		StringTokenizer tokens = new StringTokenizer(texto);
	    texto = "";
	    while(tokens.hasMoreTokens()){
	    	texto += " "+tokens.nextToken();
	    }
	    texto = texto.toString();
	    texto = texto.trim().toUpperCase();
	     return texto;
	}
 	
 	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Filtro_Empleado_Puesto_Dependiente().setVisible(true);
		}catch(Exception e){
			System.err.println("Exception "+ e.getMessage());
		}
	}
}
