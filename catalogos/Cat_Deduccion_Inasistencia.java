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

import objetos.Obj_Deduccion_Iasistencia;
import objetos.Obj_Establecimiento;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Deduccion_Inasistencia extends JDialog {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	TableRowSorter filter;
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	
	JCheckBox chbPuntualidad = new JCheckBox("Puntualidad");
	JCheckBox chbFalta = new JCheckBox("Falta");
	JCheckBox chbAsistencia = new JCheckBox("Asistencia");
	JCheckBox chbGafete = new JCheckBox("Gafete");
	
	Object[][] Matriz ;
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	    
	Object[][] Tabla = getTabla(cmbEstablecimientos.getSelectedIndex());
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Puntualidad", "Falta", "Días Falta", "Asistencia", "Gafete", "Días" }
			){
	     Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Object.class,
	    	java.lang.Boolean.class,
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
        	 	case 3 : return true; 
        	 	case 4 : return true; 
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 6 : return true;
        	 	case 7 : return true;
        	 	case 8 : return true;
        	 } 				
 			return false;
 		}
		
	};
	JTable tabla = new JTable(model);
    JScrollPane scroll = new JScrollPane(tabla);
	
    TableColumn ColumnaDias = tabla.getColumnModel().getColumn(5);
    TableColumn ColumnaDiasGf = tabla.getColumnModel().getColumn(8);
   
    String lista[] = {"0","1","2","3","4","5","6","7"};
    JComboBox cmbDias = new JComboBox(lista);
    JComboBox cmbDia = new JComboBox(lista);
    JComboBox cmbDias_Gafete = new JComboBox(lista);
    JComboBox cmbGafete = new JComboBox(lista);
    	
    JToolBar menu = new JToolBar();
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	public Cat_Deduccion_Inasistencia(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Lista.png"));
		this.setTitle("Deducción por Inasistencia");
		
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
		
		panel.add(txtFolio).setBounds(160,45,70,20);
		panel.add(txtNombre).setBounds(231,45,360,20);
		panel.add(cmbEstablecimientos).setBounds(592,45,100,20);
		panel.add(chbPuntualidad).setBounds(693,45,85,20);
		panel.add(chbFalta).setBounds(775,45,55,20);
		panel.add(cmbDia).setBounds(830,45,65,20);
		panel.add(chbAsistencia).setBounds(900,45,80,20);
		panel.add(chbGafete).setBounds(980,45,60,20);
		panel.add(cmbGafete).setBounds(1050,45,89,20);
		
		panel.add(scroll).setBounds(160,70,980,580);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		panel.add(menu);
		cont.add(panel);
	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		tabla.getColumnModel().getColumn(0).setMinWidth(72);
		tabla.getColumnModel().getColumn(1).setMaxWidth(360);
		tabla.getColumnModel().getColumn(1).setMinWidth(360);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMinWidth(100);
		tabla.getColumnModel().getColumn(3).setMaxWidth(85);
		tabla.getColumnModel().getColumn(3).setMinWidth(85);
		tabla.getColumnModel().getColumn(4).setMaxWidth(50);
		tabla.getColumnModel().getColumn(4).setMinWidth(50);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(5).setMaxWidth(70);
		tabla.getColumnModel().getColumn(5).setMinWidth(70);
		tabla.getColumnModel().getColumn(6).setMaxWidth(80);
		tabla.getColumnModel().getColumn(6).setMinWidth(80);
		tabla.getColumnModel().getColumn(7).setMaxWidth(70);
		tabla.getColumnModel().getColumn(7).setMinWidth(70);
		tabla.getColumnModel().getColumn(8).setMaxWidth(70);
		tabla.getColumnModel().getColumn(8).setMinWidth(70);
		
		ColumnaDias.setCellEditor(new javax.swing.DefaultCellEditor(cmbDias));
		ColumnaDiasGf.setCellEditor(new javax.swing.DefaultCellEditor(cmbDias_Gafete));
		
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

		cmbDia.addActionListener(opDias);
		chbFalta.addActionListener(opFaltas);
		chbGafete.addActionListener(opGafete);
		btnGuardar.addActionListener(opGuardar);
		cmbGafete.addActionListener(opDiasGafete);
		chbAsistencia.addActionListener(opAsistencia);
		chbPuntualidad.addActionListener(opPuntualidad);
		cmbEstablecimientos.addActionListener(opFiltrar);
		
		this.setModal(true);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
	}
	
	ActionListener opPuntualidad = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chbPuntualidad.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("true"), j,3);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("false"), j,3);					
				}
			}
			
		}
	};
	
	ActionListener opFaltas = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chbFalta.isSelected()){
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
	
	ActionListener opAsistencia = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chbAsistencia.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("true"), j,6);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("false"), j,6);
				}
			}
			
		}
	};
	
	ActionListener opGafete = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chbGafete.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("true"), j,7);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("false"), j,7);
				}
			}
			
		}
	};
	
	ActionListener opDiasGafete = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			for(int i=0; i<tabla.getRowCount(); i++) {
				if(Boolean.parseBoolean(model.getValueAt(i,7).toString()) != true){
					model.setValueAt(0, i,8);
				}else{
					model.setValueAt(cmbGafete.getSelectedIndex(), i,8);
				}
			}			
		}
	};
	
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
				for(int j=0; j<7; j++){
					model.setValueAt(Tabla1[i][j], i,j);
				}
			}
		}
	};
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			tabla.setSelectionMode(1);
			guardar();
		}
	};
		
	public void guardar(){
		Vector miVector = new Vector();
		if(getFilas("select * from tb_deduccion_inasistencia where status = 1") > 1){
			if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						miVector.add(model.getValueAt(i,j)+" ");
					}
					Obj_Deduccion_Iasistencia deduccion = new Obj_Deduccion_Iasistencia();
					
					deduccion.setFolio_empleado(Integer.parseInt(miVector.get(0).toString().trim()));
					deduccion.setNombre_completo(miVector.get(1).toString().trim());
					deduccion.setEstablecimiento(miVector.get(2).toString().trim());
					deduccion.setPuntualidad(miVector.get(3).toString().trim());
					deduccion.setFalta(miVector.get(4).toString().trim());
					deduccion.setDia_faltas(Integer.parseInt(miVector.get(5).toString().trim()));
					deduccion.setAsistencia(miVector.get(6).toString().trim());
					deduccion.setGafete(miVector.get(7).toString().trim());
					deduccion.setDia_gafete(Integer.parseInt(miVector.get(8).toString().trim()));
					deduccion.actualizar(Integer.parseInt(miVector.get(0).toString().trim()));
					
					miVector.clear();
				}
				JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				return;
			}
			
		}else{
			for(int i=0; i<model.getRowCount(); i++){
				for(int j=0; j<model.getColumnCount(); j++){
					miVector.add(model.getValueAt(i,j)+" ");
				}
				Obj_Deduccion_Iasistencia deduccion = new Obj_Deduccion_Iasistencia();
				
				deduccion.setFolio_empleado((Integer.parseInt(miVector.get(0).toString().trim())));
				deduccion.setNombre_completo(miVector.get(1).toString().trim());
				deduccion.setEstablecimiento(miVector.get(2).toString().trim());
				deduccion.setPuntualidad(miVector.get(3).toString().trim());
				deduccion.setFalta(miVector.get(4).toString().trim());
				deduccion.setDia_faltas(Integer.parseInt(miVector.get(5).toString().trim()));
				deduccion.setAsistencia(miVector.get(6).toString().trim());
				deduccion.setGafete(miVector.get(7).toString().trim());
				deduccion.setDia_gafete(Integer.parseInt(miVector.get(8).toString().trim()));
				deduccion.guardar();
				
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
                            "tb_deduccion_inasistencia.puntualidad," +
                            "tb_deduccion_inasistencia.falta," +
                            "tb_deduccion_inasistencia.dia_faltas," +
                            "tb_deduccion_inasistencia.asistencia," +
                            "tb_deduccion_inasistencia.gafete," +
                            "tb_deduccion_inasistencia.dia_gafete " +

                    "from tb_empleado, tb_establecimiento, tb_deduccion_inasistencia "+ 
                    "where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
                    	   "tb_empleado.folio = tb_deduccion_inasistencia.folio_empleado and "+
                    	   "tb_deduccion_inasistencia.status=1 and tb_empleado.establecimiento_id = "+establecimiento;
		
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
					        "tb_deduccion_inasistencia.puntualidad," +
					        "tb_deduccion_inasistencia.falta," +
					        "tb_deduccion_inasistencia.dia_faltas," +
					        "tb_deduccion_inasistencia.asistencia," +
					        "tb_deduccion_inasistencia.gafete," +
					        "tb_deduccion_inasistencia.dia_gafete " +
					
					"from tb_empleado, tb_establecimiento, tb_deduccion_inasistencia "+ 
					"where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
						   "tb_empleado.folio = tb_deduccion_inasistencia.folio_empleado and "+
						   "tb_deduccion_inasistencia.status=1";
		Statement s;
		ResultSet rs;
		try {
			if(establecimiento > 0){
				if(getFilas("select * from tb_deduccion_inasistencia where status = 1") > 1){
					s = con.conexion().createStatement();
					rs = s.executeQuery(qry1);
					Matriz = new Object[getFilas(qry1)][9];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						Matriz[i][3] = Boolean.parseBoolean(rs.getString(6).trim());
						Matriz[i][4] = Boolean.parseBoolean(rs.getString(7).trim());
						Matriz[i][5] = Integer.parseInt(rs.getString(8).trim());
						Matriz[i][6] = Boolean.parseBoolean(rs.getString(9).trim());
						Matriz[i][7] = Boolean.parseBoolean(rs.getString(10).trim());
						Matriz[i][8] = rs.getString(11).trim();
						i++;
					}
				}else{
					s = con.conexion().createStatement();
					rs = s.executeQuery(qry);
					Matriz = new Object[getFilas(qry)][9];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						Matriz[i][3] = false;
						Matriz[i][4] = false;
						Matriz[i][5] = 0;
						Matriz[i][6] = false;
						Matriz[i][7] = false;
						Matriz[i][8] = 0;
						i++;
					}
				}
			}else{
				if(getFilas("select * from tb_deduccion_inasistencia where status = 1") > 1){
					s = con.conexion().createStatement();
					rs = s.executeQuery(todos1);
					Matriz = new Object[getFilas(todos1)][9];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						Matriz[i][3] = Boolean.parseBoolean(rs.getString(6).trim());
						Matriz[i][4] = Boolean.parseBoolean(rs.getString(7).trim());
						Matriz[i][5] = Integer.parseInt(rs.getString(8).trim());
						Matriz[i][6] = Boolean.parseBoolean(rs.getString(9).trim());
						Matriz[i][7] = Boolean.parseBoolean(rs.getString(10).trim());
						Matriz[i][8] = rs.getString(11).trim();
						i++;
					}
				}else{
					s = con.conexion().createStatement();
					rs = s.executeQuery(todos);
					Matriz = new Object[getFilas(todos)][9];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][2] = rs.getString(5).trim();
						Matriz[i][3] = false;
						Matriz[i][4] = false;
						Matriz[i][5] = 0;
						Matriz[i][6] = false;
						Matriz[i][7] = false;
						Matriz[i][8] = 0;
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
