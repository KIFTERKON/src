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
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
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

import objetos.Obj_Establecimiento;
@SuppressWarnings("serial")
public class Cat_Filtro_Prestamo extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
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
	JTextField txtNombre_Completo = new JTextField();
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    @SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Filtro_Prestamo() {
		this.setTitle("Filtro De Prestamos");
		panel.setBorder(BorderFactory.createTitledBorder("Filtro De Prestamos"));
		
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		panel.add(getPanelTabla()).setBounds(15,42,655,327);
		
		panel.add(txtFolio).setBounds(15,20,68,20);
		panel.add(txtNombre_Completo).setBounds(85,20,239,20);
		panel.add(cmbEstablecimientos).setBounds(325,20, 148, 20);

		cont.add(panel);
		
		agregar(tabla);
		
		this.setModal(true);
		this.setSize(690,415);
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
	
	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
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

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(70);
		tabla.getColumnModel().getColumn(0).setMinWidth(70);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(1).setMaxWidth(240);
		tabla.getColumnModel().getColumn(1).setMinWidth(240);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(2).setMaxWidth(130);
		tabla.getColumnModel().getColumn(2).setMinWidth(130);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(3).setMaxWidth(50);
		tabla.getColumnModel().getColumn(3).setMinWidth(50);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Limite De Prestamo");
		tabla.getColumnModel().getColumn(4).setMaxWidth(110);
		tabla.getColumnModel().getColumn(4).setMinWidth(110);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Saldo");
		tabla.getColumnModel().getColumn(5).setMaxWidth(52);
		tabla.getColumnModel().getColumn(5).setMinWidth(52);
		
		TableCellRenderer render = new TableCellRenderer() { 
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
		tabla.getColumnModel().getColumn(5).setCellRenderer(render);
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery(
					"select tb_empleado.folio as [Folio],"+
					 "  tb_empleado.nombre as [Nombre], "+
					 "  tb_empleado.ap_paterno as [Paterno], "+
					 "  tb_empleado.ap_materno as [Materno], "+ 
					 "  tb_establecimiento.nombre as [Establecimiento], "+
					
					 "  tb_empleado.status as [Status], "+
					 "  ROUND(tb_rango_prestamos.minimo,2) as [RangoMin], "+
					 "  ROUND(tb_rango_prestamos.maximo,2) as [RangoMax] "+
					 

					"  from tb_empleado, tb_establecimiento, tb_rango_prestamos"+

					"  where "+
						"  tb_empleado.establecimiento_id = tb_establecimiento.folio and" +
						"  tb_empleado.status < 3 and tb_empleado.fuente_sodas = '1' and" +
						"  tb_empleado.rango_prestamo_id = tb_rango_prestamos.folio "
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
				
				float[] prestamoInicial = getPrestamos(Integer.parseInt(folio_empleado));
				
				fila[5] = prestamoInicial[0] - getDescuentoPrest(Integer.parseInt(folio_empleado))+"";
			   
				model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);	   
	    return scrol; 
	}

	public float getSaldo(int folio){
		float valores= 0;
		try {
			Statement s = con.conexion().createStatement();
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
	
	public float[] getPrestamos(int folio){
		float[] valores= new float[3];
		valores[0] = 0;
		valores[1] = 0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select cantidad, descuento from tb_prestamo where status_descuento = 1 and  folio_empleado="+folio);
			while(rs.next()){
				valores[0] = Float.parseFloat(rs.getString(1));
				valores[1] = Float.parseFloat(rs.getString(2));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public float getDescuentoPrest(int folio){
		float valor = 0;
		try {
			
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select sum(descuento)as 'descuento' from tb_abono where folio_empleado = "+folio+" and status = 1");
			while(rs.next()){
				valor = rs.getFloat(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
}

