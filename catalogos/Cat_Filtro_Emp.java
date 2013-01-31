package catalogos;

import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;


import objetos.JTextFieldLimit;
@SuppressWarnings("serial")
public class Cat_Filtro_Emp extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	//DECLARACION PARA CREAR UNA TABLA
	DefaultTableModel model = new DefaultTableModel(0,9){
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
	public Cat_Filtro_Emp()	{
		this.setTitle("..:: Filtro ::..");
		txtBuscar.setDocument(new JTextFieldLimit(10));
		
		txtBuscar.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		
		cmbBuscar.setSelectedIndex(1);
		campo.add(getPanelTabla()).setBounds(10,70,710,327);
		
		agregar(tabla);
		
		campo.add(lblBuscar).setBounds(10,30,70,20);
		campo.add(txtBuscar).setBounds(90,30,220,20);
		
		campo.add(new JLabel("Buscar por: ")).setBounds(330, 30, 80, 20);
		campo.add(cmbBuscar).setBounds(410, 30, 160, 20);
	
		cont.add(campo);
		
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
	    			new Cat_Empleado(folio+"").setVisible(true);
	        	}
	        }
        });
    }
	
   	@SuppressWarnings("unchecked")
	public void filtro() { 
		// Busca segun el combo
		switch (cmbBuscar.getSelectedIndex()){
			case 0 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 0)); break;
			case 1 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1)); break;
			case 2 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 2)); break;	
		}		 
	}  
	private JScrollPane getPanelTabla()	{		
		new Connexion();
		Connection conn = Connexion.conexion();
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		int a=2;
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		
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
		tabla.getColumnModel().getColumn(3).setHeaderValue("Puesto");
		tabla.getColumnModel().getColumn(3).setMaxWidth(50);
		tabla.getColumnModel().getColumn(3).setMinWidth(50);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Sueldo");
		tabla.getColumnModel().getColumn(4).setMaxWidth(50);
		tabla.getColumnModel().getColumn(4).setMinWidth(50);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Bono");
		tabla.getColumnModel().getColumn(5).setMaxWidth(50);
		tabla.getColumnModel().getColumn(5).setMinWidth(50);
		tabla.getColumnModel().getColumn(6).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(6).setMaxWidth(50);
		tabla.getColumnModel().getColumn(6).setMinWidth(50);
		tabla.getColumnModel().getColumn(7).setHeaderValue("F Sodas");
		tabla.getColumnModel().getColumn(7).setMaxWidth(50);
		tabla.getColumnModel().getColumn(7).setMinWidth(50);
		tabla.getColumnModel().getColumn(8).setHeaderValue("Gafete");
		tabla.getColumnModel().getColumn(8).setMaxWidth(50);
		tabla.getColumnModel().getColumn(8).setMinWidth(50);
		
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
						tabla.getColumnModel().getColumn(a=0).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render);
						tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
		
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery("select tb_empleado.folio as [Folio],"+
								 "  tb_empleado.nombre as [Nombre], "+
								 "  tb_empleado.ap_paterno as [Paterno], "+
								 "  tb_empleado.ap_materno as [Materno], "+ 
								 "  tb_establecimiento.nombre as [Establecimiento], "+
								 "  tb_puesto.nombre as [Puesto], "+
								 "  tb_sueldo.sueldo as [Sueldo], "+
								 "  tb_bono.bono as [Bono], "+
								 "  tb_empleado.status as [Status], "+
								 "  tb_empleado.fuente_sodas as [Fuentes], "+
								 "  tb_empleado.gafete as [Gafete]  "+

								"  from tb_empleado, tb_establecimiento, tb_puesto, tb_sueldo, tb_bono "+

								"  where "+
									"  tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
									"  tb_empleado.puesto_id = tb_puesto.folio and "+
									"  tb_empleado.sueldo_id = tb_sueldo.folio and "+
									"  tb_empleado.bono_id = tb_bono.folio");
//			rs = s.executeQuery("select * from tb_empleado");
			
			while (rs.next())
			{ 
			   String [] fila = new String[9];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim()+" "+rs.getString(3).trim()+" "+rs.getString(4).trim();
			   fila[2] = rs.getString(5).trim();
			   fila[3] = rs.getString(6).trim();
			   fila[4] = rs.getString(7).trim();
			   fila[5] = rs.getString(8).trim();
			   
		   switch(Integer.parseInt(rs.getString(9).trim())){
		   case 1:  fila[6] = "Vigente";break;
		   case 2:  fila[6] = "Vacaciones";break;
		   case 3:  fila[6] = "Baja";break;
		   }
			   if(Integer.parseInt(rs.getString(10).trim()) == 1){
				  fila[7] = "Si";
			   }else {
				  fila[7] = "No";
			   }
			   if(Integer.parseInt(rs.getString(11).trim()) == 1){
					  fila[8] = "Si";
			   }else {
					  fila[8] = "No";
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
