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
import java.text.DecimalFormat;

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
import javax.swing.table.TableRowSorter;

import SQL.Connexion;

import objetos.JTextFieldLimit;
@SuppressWarnings("serial")
public class Cat_Filtro_Prestamo extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	DefaultTableModel model = new DefaultTableModel(0,6){
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
	public Cat_Filtro_Prestamo()	{
		this.setTitle("Filtro De Prestamos");
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
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0);
	    			new Cat_Prestamo(folio+"").setVisible(true);
	    			dispose();
	        	}
	        }
        });
    }
	
   	@SuppressWarnings("unchecked")
	public void filtro() { 
		switch (cmbBuscar.getSelectedIndex()){
			case 0 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 0)); break;
			case 1 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 1)); break;
			case 2 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 2)); break;	
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

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(45);
		tabla.getColumnModel().getColumn(0).setMinWidth(45);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(1).setMaxWidth(230);
		tabla.getColumnModel().getColumn(1).setMinWidth(230);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMinWidth(100);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(3).setMaxWidth(70);
		tabla.getColumnModel().getColumn(3).setMinWidth(70);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Limite De Prestamo");
		tabla.getColumnModel().getColumn(4).setMaxWidth(120);
		tabla.getColumnModel().getColumn(4).setMinWidth(120);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Saldo");
		tabla.getColumnModel().getColumn(5).setMaxWidth(60);
		tabla.getColumnModel().getColumn(5).setMinWidth(60);
		
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(
					"select tb_empleado.folio as [Folio],"+
					 "  tb_empleado.nombre as [Nombre], "+
					 "  tb_empleado.ap_paterno as [Paterno], "+
					 "  tb_empleado.ap_materno as [Materno], "+ 
					 "  tb_establecimiento.nombre as [Establecimiento], "+
					
					 "  tb_empleado.status as [Status], "+
					 "  ROUND(tb_rango_prestamos.minimo,2) as [RangoMin], "+
					 "  ROUND(tb_rango_prestamos.maximo,2) as [RangoMax], "+
					 
					 "tb_sueldo.sueldo as [sueldo] "+

					"  from tb_empleado, tb_establecimiento, tb_sueldo, tb_rango_prestamos"+

					"  where "+
						"  tb_empleado.establecimiento_id = tb_establecimiento.folio and" +
						"  tb_empleado.status < 3 and tb_empleado.fuente_sodas = '1' and" +
						"  tb_empleado.sueldo_id = tb_sueldo.folio and " +
						"  tb_empleado.rango_prestamo_id = tb_rango_prestamos.folio and" +
						"  tb_empleado.sueldo_id = tb_sueldo.folio"
						);
			
			
			while (rs.next()) {
				@SuppressWarnings("unused")
				DecimalFormat decimalFormat = new DecimalFormat("#0.00");
				
				String [] fila = new String[6];
				String folio_empleado = rs.getString(1).trim();
			   
			   
				fila[0] = folio_empleado;
				fila[1] = rs.getString(2).trim()+" "+rs.getString(3).trim()+" "+rs.getString(4).trim();
				fila[2] = rs.getString(5).trim(); 
			  
				switch (Integer.parseInt(rs.getString(6).trim())){
					case 1 : fila[3] = "Vigente"; break;
					case 2 : fila[3] = "Vacaciones"; break;
					case 3 : fila[3] = "Baja"; break;	
				}	
				fila[4] =(Math.rint(rs.getDouble(7)*100)/100 +"  -  "+ Math.rint(rs.getDouble(8)*100)/100);	

				fila[5] = getSaldo(Integer.parseInt(folio_empleado.trim()))+"";
			   
				model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);	   
	    return scrol; 
	}

	public static float getSaldo(int folio){
		float valores= 0;
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select saldo from tb_prestamo where folio_empleado="+folio);
			while(rs.next()){
				valores = Float.parseFloat(rs.getString(1));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
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

