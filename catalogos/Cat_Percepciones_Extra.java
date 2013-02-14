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
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import frames.WholeNumberField;

import objetos.Obj_Establecimiento;
import objetos.Obj_Persecciones_Extra;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Percepciones_Extra extends JDialog {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	TableRowSorter filter;
	
	Object[][] Matriz ;
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	    
	JCheckBox chbHabilitar = new JCheckBox("Habilitar");
	JCheckBox chbTodos = new JCheckBox("");
	
	Object[][] Tabla = getTabla(cmbEstablecimientos.getSelectedIndex());
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Bono", "DE", "Cantidad Dias"}
			){
	     Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Integer.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Object.class
	    	
         };
	     public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 : 
        	 		if(chbHabilitar.isSelected()){
        	 			return true; 
        	 		}
        	 		return false;
        	 	case 4 : 
        	 		if(Boolean.parseBoolean(model.getValueAt(fila,4).toString()) == true){
        	 			model.setValueAt(0, fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	JTable tabla = new JTable(model);
    JScrollPane scroll = new JScrollPane(tabla);
	
    TableColumn ColumnaDias = tabla.getColumnModel().getColumn(5);
   
    String lista[] = {"0","1","2","3","4","5","6","7"};
    JComboBox cmbDias = new JComboBox(lista);
    JComboBox cmbDia = new JComboBox(lista);
    
    JToolBar menu = new JToolBar();
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	public Cat_Percepciones_Extra(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Lista.png"));
		this.setTitle("Percepciones Extras");
		
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
		panel.add(chbHabilitar).setBounds(750,45,70,20);
		panel.add(chbTodos).setBounds(865,45,20,20);
		panel.add(cmbDia).setBounds(900,45,70,20);
		
		panel.add(scroll).setBounds(100,70,935,580);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		panel.add(menu);
		cont.add(panel);
		
		setUpIntegerEditor(tabla);
	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		tabla.getColumnModel().getColumn(0).setMinWidth(72);
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setMaxWidth(360);
		tabla.getColumnModel().getColumn(1).setMinWidth(360);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setMaxWidth(210);
		tabla.getColumnModel().getColumn(2).setMinWidth(210);
		tabla.getColumnModel().getColumn(3).setMaxWidth(120);
		tabla.getColumnModel().getColumn(3).setMinWidth(120);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(4).setMaxWidth(30);
		tabla.getColumnModel().getColumn(4).setMinWidth(30);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(5).setMaxWidth(120);
		tabla.getColumnModel().getColumn(5).setMinWidth(120);
		ColumnaDias.setCellEditor(new javax.swing.DefaultCellEditor(cmbDias));
		
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
						tabla.getColumnModel().getColumn(5).setCellRenderer(render);

		cmbDia.addActionListener(opDias);
		chbTodos.addActionListener(opTodos);
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
	
	ActionListener opDias = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			for(int i=0; i<tabla.getRowCount(); i++) {
				if(Boolean.parseBoolean(model.getValueAt(i,4).toString()) != true){
					model.setValueAt(0, i,5);
				}else{
					model.setValueAt(cmbDia.getSelectedIndex(), i,5);
				}
			}			
		}
	};
	
	ActionListener opTodos = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chbTodos.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("true"), j,4);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("false"), j,4);
					model.setValueAt(0, j,5);
				}
			}
			
		}
	};
	
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
		if(getFilas("select * from tb_persecciones_extra where status = 1") > 1){
			if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						miVector.add(model.getValueAt(i,j)+" ");
					}
					Obj_Persecciones_Extra perseccion = new Obj_Persecciones_Extra();
					
					perseccion.setFolio_empleado(Integer.parseInt(miVector.get(0).toString().trim()));
					perseccion.setNombre_completo(miVector.get(1).toString().trim());
					perseccion.setEstablecimiento(miVector.get(2).toString().trim());
					if(miVector.get(3).toString().trim() == null){
						perseccion.setBono(0);
					}else{
						perseccion.setBono(Float.parseFloat(miVector.get(3).toString().trim()));
					}
					perseccion.setDia_extra(miVector.get(4).toString().trim());
					if(miVector.get(5).toString().trim() == null){
						perseccion.setDias(0);
					}else{
						perseccion.setDias(Integer.parseInt(miVector.get(5).toString().trim()));
					}
				
					perseccion.actualizar(Integer.parseInt(miVector.get(0).toString().trim()));
					
					miVector.clear();
				}
				JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				return;
			}
			
		}else{
			for(int i=0; i<model.getRowCount(); i++){
				for(int j=0; j<model.getColumnCount(); j++){
					miVector.add(model.getValueAt(i,j));
				}
				Obj_Persecciones_Extra perseccion = new Obj_Persecciones_Extra();
		
				perseccion.setFolio_empleado(Integer.parseInt(miVector.get(0).toString().trim()));
				perseccion.setNombre_completo(miVector.get(1).toString().trim());
				perseccion.setEstablecimiento(miVector.get(2).toString().trim());
				if(miVector.get(3) != ""){
					perseccion.setBono(Float.parseFloat(miVector.get(3).toString().trim()));
				}else{
					miVector.set(3,0);
					perseccion.setBono(Float.parseFloat(miVector.get(3).toString().trim()));
				}
				perseccion.setDia_extra(miVector.get(4).toString().trim());
				
				if(miVector.get(5) != ""){
					perseccion.setDias(Integer.parseInt(miVector.get(5).toString().trim()));
				}else{
					miVector.set(5,0);
					perseccion.setDias(Integer.parseInt(miVector.get(5).toString().trim()));
				}
				perseccion.guardar();
				
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
                            "tb_persecciones_extra.bono," +
                            "tb_persecciones_extra.dia_extra," +
                            "tb_persecciones_extra.dias " +

                    "from tb_empleado, tb_establecimiento, tb_persecciones_extra "+ 
                    "where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
                    	   "tb_empleado.folio = tb_persecciones_extra.folio_empleado and "+
                    	   "tb_persecciones_extra.status=1 and tb_empleado.establecimiento_id = "+establecimiento;
		
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
					        "tb_persecciones_extra.bono," +
					        "tb_persecciones_extra.dia_extra," +
					        "tb_persecciones_extra.dias " +
					
					"from tb_empleado, tb_establecimiento, tb_persecciones_extra "+ 
					"where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
						   "tb_empleado.folio = tb_persecciones_extra.folio_empleado and "+
						   "tb_persecciones_extra.status=1";

		Statement s;
		ResultSet rs;
		try {
			if(establecimiento > 0){
				if(getFilas("select * from tb_persecciones_extra where status = 1") > 1){
					s = con.conexion().createStatement();
					rs = s.executeQuery(qry1);
					Matriz = new Object[getFilas(qry1)][6];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						int bono = Integer.parseInt(rs.getString(6));
						if(bono != 0){
							Matriz[i][3] = bono;
						}else{
							Matriz[i][3] = "";
						}
						Matriz[i][4] = Boolean.parseBoolean(rs.getString(7).trim());
						int dias = Integer.parseInt(rs.getString(8).trim());
						if(dias != 0){
							Matriz[i][5] = dias;
						}else{
							Matriz[i][5] = "";
						}
						i++;
					}
				}else{
					s = con.conexion().createStatement();
					rs = s.executeQuery(qry);
					Matriz = new Object[getFilas(qry)][6];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						Matriz[i][3] = "";
						Matriz[i][4] = false;
						Matriz[i][5] = "";
						i++;
					}
				}
			}else{
				if(getFilas("select * from tb_persecciones_extra where status = 1") > 1){
					s = con.conexion().createStatement();
					rs = s.executeQuery(todos1);
					Matriz = new Object[getFilas(todos1)][6];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						int bono = Integer.parseInt(rs.getString(6));
						if(bono != 0){
							Matriz[i][3] = bono;
						}else{
							Matriz[i][3] = "";
						}
						Matriz[i][4] = Boolean.parseBoolean(rs.getString(7).trim());
						int dias = Integer.parseInt(rs.getString(8).trim());
						if(dias != 0){
							Matriz[i][5] = dias;
						}else{
							Matriz[i][5] = "";
						}
						i++;
					}
				}else{
					s = con.conexion().createStatement();
					rs = s.executeQuery(todos);
					Matriz = new Object[getFilas(todos)][6];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						Matriz[i][3] = "";
						Matriz[i][4] = false;
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
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	

}