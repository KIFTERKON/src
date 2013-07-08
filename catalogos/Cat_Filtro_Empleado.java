package catalogos;

import java.awt.Component;
import java.awt.Container;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;

import objetos.Obj_Establecimiento;
@SuppressWarnings("serial")
public class Cat_Filtro_Empleado extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	DefaultTableModel model = new DefaultTableModel(0,9){
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
	public Cat_Filtro_Empleado()	{
		this.setTitle("Filtro de Empleados");
		campo.setBorder(BorderFactory.createTitledBorder("Filtro De Empleado"));
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(getPanelTabla()).setBounds(15,42,700,337);
		
		campo.add(txtFolio).setBounds(15,20,48,20);
		campo.add(txtNombre_Completo).setBounds(64,20,229,20);
		campo.add(cmbEstablecimientos).setBounds(295,20, 148, 20);
		
		agregar(tabla);
		
		cont.add(campo);
		
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
		this.setSize(740,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0);
	    			dispose();
	    			new Cat_Empleado(folio+"").setVisible(true);
	        	}
	        }
        });
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
	
   	private JScrollPane getPanelTabla()	{		
		new Connexion();
		
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
			s = new Connexion().conexion().createStatement();
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
