package catalogos;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;


import objetos.JTextFieldLimit;
@SuppressWarnings("serial")
public class Cat_Filtro_Fue_Soda extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
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
	
	String busqueda[] = {"Folio","Nombre Completo","Otra Cosa"};
	@SuppressWarnings("unchecked")
	JComboBox cmbBuscar = new JComboBox(busqueda);
	
	@SuppressWarnings("unchecked")
	public Cat_Filtro_Fue_Soda()	{
		this.setTitle("..:: Filtro Fuente de Sodas ::..");
		txtBuscar.setDocument(new JTextFieldLimit(10));
		
		txtBuscar.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		
		cmbBuscar.setSelectedIndex(1);
		panel.add(getPanelTabla()).setBounds(10,70,710,327);
		
		agregar(tabla);
		
		panel.add(lblBuscar).setBounds(10,30,70,20);
		panel.add(txtBuscar).setBounds(90,30,220,20);
		
		panel.add(new JLabel("Buscar por: ")).setBounds(330, 30, 80, 20);
		panel.add(cmbBuscar).setBounds(410, 30, 160, 20);
	
		cont.add(panel);
		
		this.setModal(true);
		this.setSize(740,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	        		dispose();
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0);
	    			new Cat_Fue_Soda(folio+"").setVisible(true);
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
//			case 2 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 2)); break;	
		}		 
	}  
	private JScrollPane getPanelTabla()	{		
		new Connexion();
		Connection conn = Connexion.conexion();

		// Creamos las columnas.
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(1).setMaxWidth(230);
		tabla.getColumnModel().getColumn(1).setMinWidth(230);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMinWidth(100);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(3).setMaxWidth(50);
		tabla.getColumnModel().getColumn(3).setMinWidth(50);
		tabla.getColumnModel().getColumn(4).setHeaderValue("F Sodas");
		tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		tabla.getColumnModel().getColumn(4).setMinWidth(69);
		
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery("select folio,nombre,ap_paterno,ap_materno,establecimiento_id,status,fuente_sodas from tb_empleado where fuente_sodas='1' and status ='1' or status ='2'");
			
			while (rs.next())
			{ 
			   String [] fila = new String[5];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim()+" "+rs.getString(3).trim()+" "+rs.getString(4).trim();
			   fila[2] = rs.getString(5).trim();
			   fila[3] = rs.getString(6).trim();
			   fila[4] = rs.getString(7).trim();
			   
//			   for (int i=0;i<3;i++){
//			      fila[i] = rs.getObject(i+1);
//			   }
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

