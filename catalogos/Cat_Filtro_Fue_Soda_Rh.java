package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
@SuppressWarnings("serial")
public class Cat_Filtro_Fue_Soda_Rh extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	//DECLARACION PARA CREAR UNA TABLA
	DefaultTableModel model = new DefaultTableModel(0,5){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	
	@SuppressWarnings("unchecked")
	private TableRowSorter trsfiltro;
	
	JLabel lblBuscar = new JLabel("BUSCAR : ");
	JTextField txtBuscar = new JTextField();
	
	String busqueda[] = {"Folio","Nombre Completo","Establecimiento"};
	@SuppressWarnings("unchecked")
	JComboBox cmbBuscar = new JComboBox(busqueda);
	
	@SuppressWarnings("unchecked")
	public Cat_Filtro_Fue_Soda_Rh()	{
		this.setTitle("..:: Filtro Fuente de Sodas RRHH ::..");
		txtBuscar.setDocument(new JTextFieldLimit(10));
		
		txtBuscar.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });

		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		
		cmbBuscar.setSelectedIndex(1);
		panel.add(getPanelTabla()).setBounds(10,70,625,327);
		
		agregar(tabla);
		
		panel.add(lblBuscar).setBounds(15,30,70,20);
		panel.add(txtBuscar).setBounds(85,30,220,20);
		
		panel.add(new JLabel("Buscar por: ")).setBounds(390, 30, 80, 20);
		panel.add(cmbBuscar).setBounds(470, 30, 160, 20);
	
		cont.add(panel);
		
		this.setModal(true);
		this.setSize(650,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0);
	    			new Cat_Fue_Soda_Rh(folio+"").setVisible(true);
	    			dispose();
	        	}
	        }
        });
    }
	
   	@SuppressWarnings("unchecked")
	public void filtro() { 
   		
		// Busca segun el combo
		switch (cmbBuscar.getSelectedIndex()){
			case 0 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 0)); break;
			case 1 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 1)); break;
			case 2 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 2)); break;	
		}		 
	}  
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		// Creamos las columnas.
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(60);
		tabla.getColumnModel().getColumn(0).setMinWidth(60);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(1).setMaxWidth(260);
		tabla.getColumnModel().getColumn(1).setMinWidth(260);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(2).setMaxWidth(120);
		tabla.getColumnModel().getColumn(2).setMinWidth(120);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(3).setMaxWidth(90);
		tabla.getColumnModel().getColumn(3).setMinWidth(90);
		tabla.getColumnModel().getColumn(4).setHeaderValue("F Sodas");
		tabla.getColumnModel().getColumn(4).setMaxWidth(80);
		tabla.getColumnModel().getColumn(4).setMinWidth(80);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		int a=2;
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		
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
						tabla.getColumnModel().getColumn(2).setCellRenderer(render);
						tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select tb_empleado.folio as [Folio],"+
					 "  tb_empleado.nombre as [Nombre], "+
					 "  tb_empleado.ap_paterno as [Paterno], "+
					 "  tb_empleado.ap_materno as [Materno], "+ 
					 "  tb_establecimiento.nombre as [Establecimiento], "+
					
					 "  tb_empleado.status as [Status], "+
					 "  tb_empleado.fuente_sodas as [Fuentes]"+

					"  from tb_empleado, tb_establecimiento"+

					"  where "+
						"  tb_empleado.establecimiento_id = tb_establecimiento.folio and" +
						"  tb_empleado.status < 3 and tb_empleado.fuente_sodas = '1'");
			
			while (rs.next())
			{ 
			   String [] fila = new String[5];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim()+" "+rs.getString(3).trim()+" "+rs.getString(4).trim();
			   fila[2] = rs.getString(5).trim(); 
			 
			   switch (Integer.parseInt(rs.getString(6).trim())){
				case 1 : fila[3] = "Vigente"; break;
				case 2 : fila[3] = "Vacaciones"; break;
				case 3 : fila[3] = "Baja"; break;	
			}	
			   if(Integer.parseInt(rs.getString(7).trim()) == 1){
					  fila[4] = "Si";
				   }else {
					  fila[4] = "No";
				   }
			   
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	KeyListener validaCantidad = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();				
			if(((caracter < '0') ||	
			    	(caracter > '9')) && 
			    	(caracter != '.' )){
			    	e.consume();
			    	}
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
		}	
	};
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    // VERIFICAR SI LA TECLA PULSADA NO ES UN DIGITO
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.')){
		    	e.consume();
		    	}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
}

