package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import frames.WholeNumberField;

import objetos.Obj_Bancos;
import objetos.Obj_Establecimiento;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Bancos extends JDialog {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	TableRowSorter filter;
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	
	JCheckBox chbHabilitarBanamex = new JCheckBox("Habilitar");
	JCheckBox chbHabilitarBanorte = new JCheckBox("Habilitar");
	JCheckBox chbHabilitarCooperacion = new JCheckBox("Habilitar");
	
	boolean bandera = false;
	
	Object[][] Matriz ;
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	    
	Object[][] Tabla = getTabla(cmbEstablecimientos.getSelectedIndex());
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Banamex", "Banorte", "Total a Pagar" }
			){
	     Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Integer.class, 
	    	java.lang.Integer.class, 
	    	java.lang.Integer.class
	    	
         };
	     public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : 
        	 		return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 : if(chbHabilitarBanamex.isSelected()){
        	 				if(model.getValueAt(fila,4).toString() != ""){
        	 					return false;
        	 				}else{
        	 					return true;
        	 				}
        	 			 }else{
        	 				 return false;
        	 			 }
        	 	case 4 : if(chbHabilitarBanorte.isSelected()){
        	 				if(model.getValueAt(fila,3).toString().trim() != ""){
        	 					return false;
        	 				}else{
        	 					return true;
        	 				}
        	 			 }else{
        	 				 return false;
        	 			 }
        	 	
        	 	case 5 : if(chbHabilitarCooperacion.isSelected()){
        	 		return true;

	 			 }else{
	 				 return false;
	 			 }

        	 } 				
 			return false;
 		}
		
	};
	JTable tabla = new JTable(model);
    JScrollPane scroll = new JScrollPane(tabla);
	
    String lista[] = {"0","1","2","3","4","5","6","7"};
    JComboBox cmbDias = new JComboBox(lista);
    	
    JToolBar menu = new JToolBar();
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	public Cat_Bancos(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dollar.png"));
		this.setTitle("Bancos");
		
		txtNombre.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });
		
		txtFolio.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtroFolio(); 
            } 
        });
		
		filter = new TableRowSorter(model); 
		tabla.setRowSorter(filter);  
		
		panel.add(txtFolio).setBounds(100,45,60,20);
		panel.add(txtNombre).setBounds(170,45,345,20);
		panel.add(cmbEstablecimientos).setBounds(590,45,90,20);
		panel.add(chbHabilitarBanamex).setBounds(750,45,90,20);
		panel.add(chbHabilitarBanorte).setBounds(875,45,90,20);
		panel.add(chbHabilitarCooperacion).setBounds(1000,45,70,20);
		panel.add(scroll).setBounds(100,70,1030,580);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		panel.add(menu);
		cont.add(panel);

		 setUpIntegerEditor(tabla);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		tabla.getColumnModel().getColumn(0).setMinWidth(72);		
		tabla.getColumnModel().getColumn(1).setMaxWidth(360);
		tabla.getColumnModel().getColumn(1).setMinWidth(360);
		tabla.getColumnModel().getColumn(2).setMaxWidth(210);
		tabla.getColumnModel().getColumn(2).setMinWidth(210);
		tabla.getColumnModel().getColumn(3).setMaxWidth(120);
		tabla.getColumnModel().getColumn(3).setMinWidth(120);
		tabla.getColumnModel().getColumn(4).setMaxWidth(120);
		tabla.getColumnModel().getColumn(4).setMinWidth(120);		
		tabla.getColumnModel().getColumn(5).setMaxWidth(130);
		tabla.getColumnModel().getColumn(5).setMinWidth(130);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);		
	
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
						
		
		btnGuardar.addActionListener(opGuardar);
		cmbEstablecimientos.addActionListener(opFiltrar);
		this.setModal(true);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
	}
	
	 private void setUpIntegerEditor(JTable table) {
	        final WholeNumberField integerField = new WholeNumberField(0, 5);
	        integerField.setHorizontalAlignment(WholeNumberField.RIGHT);

	        DefaultCellEditor integerEditor = 
	            new DefaultCellEditor(integerField) {
	                public Object getCellEditorValue() {
	                    return new Integer(integerField.getValue());
	                }
	            };
	        table.setDefaultEditor(Integer.class, integerEditor);
	}
		
	public void filtroFolio(){ 
		filter.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0)); 
	}
	
	public void filtro(){ 
		filter.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase(), 1)); 
	}  
	
	ActionListener opFiltrar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			int numero = tabla.getRowCount();
			while(numero > 0){
				model.removeRow(0);
				numero --;
			}
			Object[][] Tabla1 = getTabla(cmbEstablecimientos.getSelectedIndex());
			Object[] fila = new Object[tabla.getColumnCount()]; 
			for(int i=0; i<Tabla1.length; i++){
				model.addRow(fila); 
				for(int j=0; j<6; j++){
					model.setValueAt(Tabla1[i][j], i,j);
				}
			}
		}
	};
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			guardar();
		}
	};
		
	public void guardar(){
		Vector miVector = new Vector();
		if(getFilas("select * from tb_bancos where status = 1") > 1){
			if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						model.isCellEditable(i,j);
						miVector.add(model.getValueAt(i,j));
					}
					Obj_Bancos bancos = new Obj_Bancos();

					bancos.setFolio_empleado(Integer.parseInt(miVector.get(0).toString().trim()));
					bancos.setNombre_completo(miVector.get(1).toString().trim());
					bancos.setEstablecimiento(miVector.get(2).toString().trim());
					if(miVector.get(3) != ""){
						bancos.setBanamex(Integer.parseInt(miVector.get(3).toString().trim()));
					}else{
						miVector.set(3,0);
						bancos.setBanamex(Integer.parseInt(miVector.get(3).toString().trim()));
					}
					if(miVector.get(4) != ""){
						bancos.setBanorte(Integer.parseInt(miVector.get(4).toString().trim()));
					}else{
						miVector.set(4,0);
						bancos.setBanorte(Integer.parseInt(miVector.get(4).toString().trim()));
					}
					bancos.actualizar(Integer.parseInt(miVector.get(0).toString().trim()));
					
					miVector.clear();
				}
				JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				return;
			}
			
		}else{
			for(int i=0; i<model.getRowCount(); i++){
				for(int j=0; j<model.getColumnCount(); j++){
					model.isCellEditable(i,j);
					miVector.add(model.getValueAt(i,j).toString());
				}
				Obj_Bancos bancos = new Obj_Bancos();
				
				bancos.setFolio_empleado(Integer.parseInt(miVector.get(0).toString().trim()));
				bancos.setNombre_completo(miVector.get(1).toString().trim());
				bancos.setEstablecimiento(miVector.get(2).toString().trim());
				if(miVector.get(3) != ""){
					bancos.setBanamex(Integer.parseInt(miVector.get(3).toString().trim()));
				}else{
					miVector.set(3,0);
					bancos.setBanamex(Integer.parseInt(miVector.get(3).toString().trim()));
				}
				if(miVector.get(4) != ""){
					bancos.setBanorte(Integer.parseInt(miVector.get(4).toString().trim()));
				}else{
					miVector.set(4,0);
					bancos.setBanorte(Integer.parseInt(miVector.get(4).toString().trim()));
				}
				bancos.guardar();
				
				miVector.clear();
			}
			JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public Object[][] getTabla(int establecimiento){
		
		String qry = "select tb_empleado.folio," +
						"tb_empleado.nombre," +
						"tb_empleado.ap_paterno," +
						"tb_empleado.ap_materno," +
						"tb_establecimiento.nombre as establecimiento " +
					  "from tb_empleado, tb_establecimiento " +
					  "where tb_empleado.establecimiento_id = tb_establecimiento.folio and " +
					  "tb_empleado.establecimiento_id = "+establecimiento;
		
		String qry1 ="select tb_empleado.folio," +
						    "tb_empleado.nombre," +
	                        "tb_empleado.ap_paterno," +
                            "tb_empleado.ap_materno," +
                            "tb_establecimiento.nombre as establecimiento," +
                            "tb_bancos.banamex," +
                            "tb_bancos.banorte," +
                            "tb_bancos.mas_menos " +

                    "from tb_empleado, tb_establecimiento, tb_bancos "+ 
                    "where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
                    	   "tb_empleado.folio = tb_bancos.folio_empleado and "+
                    	   "tb_bancos.status=1 and tb_empleado.establecimiento_id = "+establecimiento;
		
		String todos = "select tb_empleado.folio," +
							"tb_empleado.nombre," +
							"tb_empleado.ap_paterno," +
							"tb_empleado.ap_materno," +
							"tb_establecimiento.nombre as establecimiento " +
						"from tb_empleado, tb_establecimiento " +
						"where tb_empleado.establecimiento_id = tb_establecimiento.folio";
		
		String todos1 = "select tb_empleado.folio," +
						    "tb_empleado.nombre," +
					        "tb_empleado.ap_paterno," +
					        "tb_empleado.ap_materno," +
					        "tb_establecimiento.nombre as establecimiento," +
					        "tb_bancos.banamex," +
                            "tb_bancos.banorte " +
					
					"from tb_empleado, tb_establecimiento, tb_bancos "+ 
					"where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
						   "tb_empleado.folio = tb_bancos.folio_empleado and "+
						   "tb_bancos.status=1";
		
		Statement stmt = null;
		ResultSet rs;
		try {
			if(establecimiento > 0){
				if(getFilas("select * from tb_bancos where status = 1") > 1){
					stmt = con.conexion().createStatement();
					rs = stmt.executeQuery(qry1);
					Matriz = new Object[getFilas(qry1)][6];
					int i=0;
					while(rs.next()){
						int folio_empleado = rs.getInt(1);
						Matriz[i][0] = folio_empleado;
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();

						int banamex = Integer.parseInt(rs.getString(6));
						if(banamex != 0){
							Matriz[i][3] = banamex;
						}else{
							Matriz[i][3] = "";
						}

						int banorte = Integer.parseInt(rs.getString(7));
						if(banorte != 0){
							Matriz[i][4] = banorte;
						}else{
							Matriz[i][4] = "";
						}
					
						float pagototal = getPagoTotal(folio_empleado);
						if(pagototal != 0){
							Matriz[i][5] = pagototal;
						}else {
							Matriz[i][5] = "";
						}
						
						i++;
					}
				}else{
					stmt = con.conexion().createStatement();
					rs = stmt.executeQuery(qry);
					Matriz = new Object[getFilas(qry)][6];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						Matriz[i][3] = "";
						Matriz[i][4] = "";
						Matriz[i][5] = "";
						i++;
					}
				}
			}else{
				if(getFilas("select * from tb_bancos where status = 1") > 1){
					stmt = con.conexion().createStatement();
					rs = stmt.executeQuery(todos1);
					Matriz = new Object[getFilas(todos1)][7];
					int i=0;
					while(rs.next()){
						int folio_empleado = rs.getInt(1);
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();

						int banamex = Integer.parseInt(rs.getString(6));
						if(banamex != 0){
							Matriz[i][3] = banamex;
						}else{
							Matriz[i][3] = "";
						}

						int banorte = Integer.parseInt(rs.getString(7));
						if(banorte != 0){
							Matriz[i][4] = banorte;
						}else{
							Matriz[i][4] = "";
						}
					
						float pagototal = getPagoTotal(folio_empleado);
						if(pagototal != 0){
							Matriz[i][5] = pagototal;
						}else {
							Matriz[i][5] = "";
						}
									
						i++;
					}
				}else{
					stmt = con.conexion().createStatement();
					rs = stmt.executeQuery(todos);
					Matriz = new Object[getFilas(todos)][7];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						Matriz[i][3] = "";
						Matriz[i][4] = "";
						Matriz[i][5] = "";
						i++;
					}
				}
			}
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz; 
	}
	
	public float getPagoTotal(int folio){
		float valores = 0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select a_pagar from tb_pre_listaraya where folio_empleado="+folio);
			while(rs.next()){
				valores = rs.getFloat(1);			
			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public int getFilas(String qry){
		int filas=0;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	

}
