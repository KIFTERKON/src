package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
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

import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Deduccion_Iasistencia;
import objetos.Obj_Establecimiento;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Deduccion_Inasistencia extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	@SuppressWarnings("rawtypes")
	TableRowSorter trsfiltro;
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings("rawtypes")
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	
	JCheckBox chbPuntualidad = new JCheckBox("Inpuntualidad");
	JCheckBox chbFalta = new JCheckBox("Falta");
	JCheckBox chbGafete = new JCheckBox("Gafete");
	JCheckBox chbHabilitar = new JCheckBox("Habilitar");
	
	Object[][] Matriz ;
	
	Object[][] Tabla = getTabla();
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Inpuntualidad", "Falta", "Días Falta", "Asistencia", "Gafete", "Días Gafete","Extra" }
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Object.class,
	    	java.lang.Boolean.class,
	    	java.lang.Boolean.class,
	    	java.lang.Object.class,
	    	java.lang.Object.class
	    	
         };
	     @SuppressWarnings("rawtypes")
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 : return true; 
        	 	case 4 : 
        	 		if(Boolean.parseBoolean(model.getValueAt(fila,4).toString()) != true){
        	 			model.setValueAt(1, fila, 5);
        	 			return true; 
        	 		}else {
        	 			model.setValueAt("", fila, 5);
        	 			model.setValueAt(false, fila, 6);
        	 			return true; 
        	 		}
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 6 : 
        	 		if(Boolean.parseBoolean(model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}else{
        	 			return false;
        	 		}
        	 	case 7 :
        	 		if(Boolean.parseBoolean(model.getValueAt(fila,7).toString()) != true){
        	 			model.setValueAt(1, fila, 8);
        	 			return true;
        	 		}else {
        	 			model.setValueAt("", fila, 8);
        	 			return true;
        	 		}
        	 	case 8 : return true;
        	 	case 9 :
        	 		if(chbHabilitar.isSelected()){
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
	
    TableColumn ColumnaDias = tabla.getColumnModel().getColumn(5);
    TableColumn ColumnaDiasGf = tabla.getColumnModel().getColumn(8);
   
    String lista[] = {"0","1","2","3","4","5","6","7"};
    @SuppressWarnings("rawtypes")
	JComboBox cmbDias = new JComboBox(lista);
    @SuppressWarnings("rawtypes")
	JComboBox cmbDia = new JComboBox(lista);
    @SuppressWarnings("rawtypes")
	JComboBox cmbDias_Gafete = new JComboBox(lista);
    @SuppressWarnings("rawtypes")
	JComboBox cmbGafete = new JComboBox(lista);
    	
    JToolBar menu = new JToolBar();
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	Obj_Configuracion_Sistema configs = new Obj_Configuracion_Sistema().buscar2();
	boolean bono_dia_extra = configs.isBono_dia_extra();
	
	@SuppressWarnings("rawtypes")
	public Cat_Deduccion_Inasistencia(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Lista.png"));
		this.setTitle("Deducción por Inasistencia");
		
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		panel.add(txtFolio).setBounds(110,45,70,20);
		panel.add(txtNombre_Completo).setBounds(181,45,310,20);
		panel.add(cmbEstablecimientos).setBounds(492,45,130,20);
		panel.add(chbPuntualidad).setBounds(623,45,94,20);
		panel.add(chbFalta).setBounds(713,45,55,20);
		panel.add(cmbDia).setBounds(770,45,60,20);
		panel.add(chbGafete).setBounds(910,45,60,20);
		panel.add(cmbGafete).setBounds(980,45,65,20);
		panel.add(chbHabilitar).setBounds(1060,45,65,20);
		
		panel.add(scroll).setBounds(110,70,1050,580);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		panel.add(menu);
		cont.add(panel);
	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		tabla.getColumnModel().getColumn(0).setMinWidth(72);
		tabla.getColumnModel().getColumn(1).setMaxWidth(310);
		tabla.getColumnModel().getColumn(1).setMinWidth(310);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setMaxWidth(130);
		tabla.getColumnModel().getColumn(2).setMinWidth(130);
		tabla.getColumnModel().getColumn(3).setMaxWidth(90);
		tabla.getColumnModel().getColumn(3).setMinWidth(90);
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
		tabla.getColumnModel().getColumn(9).setMaxWidth(90);
		tabla.getColumnModel().getColumn(9).setMinWidth(90);
		
		DefaultTableCellRenderer tcrR = new DefaultTableCellRenderer();
		tcrR.setHorizontalAlignment(SwingConstants.RIGHT);
		tabla.getColumnModel().getColumn(9).setCellRenderer(tcrR);
		
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
		chbPuntualidad.addActionListener(opPuntualidad);
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
	}
	
	KeyListener opFiltroFolio = new KeyListener(){
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
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener opFiltro = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
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
					model.setValueAt(1, j,5);
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
					model.setValueAt("", i,5);
				}else{
					if(Integer.parseInt(cmbDia.getSelectedItem()+"") == 0){
						model.setValueAt("", i,5);
					}else{
						model.setValueAt(cmbDia.getSelectedIndex(), i,5);
					}
				}
			}			
		}
	};
	
	ActionListener opGafete = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chbGafete.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("true"), j,7);
					model.setValueAt(1, j, 8);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					model.setValueAt(Boolean.parseBoolean("false"), j,7);
					model.setValueAt("", j, 8);
				}
			}
			
		}
	};
	
	ActionListener opDiasGafete = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			for(int i=0; i<tabla.getRowCount(); i++) {
				if(Boolean.parseBoolean(model.getValueAt(i,7).toString()) != true){
					model.setValueAt("", i,8);
				}else{
					if(Integer.parseInt(cmbGafete.getSelectedItem()+"") == 0){
						model.setValueAt("", i,8);
					}else{
						model.setValueAt(cmbGafete.getSelectedIndex(), i,8);
					}
					
				}
			}			
		}
	};
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			new Progress_Bar_Guardar().setVisible(true);
		}
	};
	
	public Object[][] getTabla(){
		String todos = "exec sp_buscar_deduccion_inasistencia";
	
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery(todos);
			Matriz = new Object[getFilas(todos)][10];
			int i=0;
			while(rs.next()){
				Matriz[i][0] = rs.getString(1).trim();
				Matriz[i][1] = rs.getString(2).trim();
				Matriz[i][2] = rs.getString(3).trim();
				Matriz[i][3] = rs.getBoolean(4);
				Matriz[i][4] = rs.getBoolean(5);
				int dias_falta = rs.getInt(6);
				if(dias_falta == 0){
					Matriz[i][5] = "";
				}else{
					Matriz[i][5] = dias_falta;
				}
				Matriz[i][6] = rs.getBoolean(7);
				Matriz[i][7] = rs.getBoolean(8);
				int dias_gafete = rs.getInt(9);
				if(dias_gafete == 0){
					Matriz[i][8] = "";
				}else{
					Matriz[i][8] = dias_gafete;
				}
				double extra = Math.rint(rs.getDouble(10)*100)/100;
				if(extra == 0){
					Matriz[i][9] = "";
				}else{
					Matriz[i][9] = extra;
				}
				
				i++;
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
	
	public float[] getBono_Sueldo(int folio){
		float[] valores= new float[2];
		valores[0] = 0;
		valores[1] = 0;
		
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery("exec sp_bono_sueldo " + folio);
			while(rs.next()){
				valores[0] = rs.getFloat(1);
				valores[1] = rs.getFloat(2);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public class Progress_Bar_Guardar extends JDialog {
		Container cont = getContentPane();
		
		JLayeredPane panel = new JLayeredPane();
		JProgressBar barra = new JProgressBar();
		
		String titleBorder = "";
		public Progress_Bar_Guardar() {
			barra.setStringPainted(true);
			Thread hilo = new Thread(new Hilo());
			hilo.start();
			panel.setBorder(BorderFactory.createTitledBorder("- ..."));
			panel.add(barra).setBounds(20,25,350,20);
			
			cont.add(panel);
			
			this.setUndecorated(true);
			this.setSize(400,100);
			this.setModal(true);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		
		}

		class Hilo implements Runnable {
			@SuppressWarnings("rawtypes")
			public void run() {
				int total = model.getRowCount();
		
				Vector miVector = new Vector();
				if(getFilas("exec sp_status_deduccion_inasistencia") > 1){
					if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
						panel.setBorder(BorderFactory.createTitledBorder("Actualizando Deducción por inasistencia..."));
						for(int i=0; i<model.getRowCount(); i++){
							for(int j=0; j<model.getColumnCount(); j++){
								miVector.add(model.getValueAt(i,j));
							}
							Obj_Deduccion_Iasistencia deduccion = new Obj_Deduccion_Iasistencia();
							
							int index = Integer.parseInt(miVector.get(0).toString().trim());
							
							deduccion.setFolio_empleado(index);
							deduccion.setNombre_completo(miVector.get(1).toString().trim());
							deduccion.setEstablecimiento(miVector.get(2).toString().trim());
							deduccion.setPuntualidad(miVector.get(3).toString().trim());
							
							boolean falta = Boolean.parseBoolean(miVector.get(4)+"".trim());
							int dias_faltas = 0;
							if(miVector.get(5).toString() != ""){
								dias_faltas = Integer.parseInt(miVector.get(5).toString().trim());
							}else{
								dias_faltas = 0;	
							}
							deduccion.setFalta(falta+"");
							deduccion.setDia_faltas(dias_faltas);
							
							deduccion.setAsistencia(miVector.get(6).toString().trim());
							deduccion.setGafete(miVector.get(7).toString().trim());
							if(miVector.get(8).toString() != ""){
								deduccion.setDia_gafete(Integer.parseInt(miVector.get(8).toString()));
							}else {
								deduccion.setDia_gafete(0);
							}
							if(miVector.get(9).toString() != ""){
								deduccion.setExtra(Float.parseFloat(miVector.get(9).toString()));
							}else {
								deduccion.setExtra(0);
							}
							if(falta != true){
								deduccion.setCantidad_faltas(0);
							}else{
								float[] bono_sueldo = getBono_Sueldo(index);
								deduccion.setCantidad_faltas((bono_sueldo[0]+bono_sueldo[1])/7 * dias_faltas);
							}
							
							Obj_Deduccion_Iasistencia existe = new Obj_Deduccion_Iasistencia().buscarExis(index);
							
							if(existe.getFolio_empleado() == index){
								deduccion.actualizar(index);
							}else{
								deduccion.guardar();
							}
							
							miVector.clear();
							
							int porcent = (i*100)/total;
							barra.setValue(porcent+1);
							try {
								Thread.sleep(0);
							} catch (InterruptedException e) {
								e.printStackTrace();
									
							}
						}
						JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
						dispose();
					}else{
						dispose();
						return;
					}
					
				}else{
					panel.setBorder(BorderFactory.createTitledBorder("Guardando Deducción por inasistencia..."));
					for(int i=0; i<model.getRowCount(); i++){
						for(int j=0; j<model.getColumnCount(); j++){
							miVector.add(model.getValueAt(i,j));
						}
						Obj_Deduccion_Iasistencia deduccion = new Obj_Deduccion_Iasistencia();
						int index = Integer.parseInt(miVector.get(0).toString().trim());
						
						deduccion.setFolio_empleado(index);
						deduccion.setNombre_completo(miVector.get(1).toString().trim());
						deduccion.setEstablecimiento(miVector.get(2).toString().trim());
						deduccion.setPuntualidad(miVector.get(3).toString().trim());
						boolean falta = Boolean.parseBoolean(miVector.get(4)+"".trim());
						
						int dias_faltas = 0;
						if(miVector.get(5).toString() != ""){
							dias_faltas = Integer.parseInt(miVector.get(5).toString().trim());
						}else{
							dias_faltas = 0;	
						}
						deduccion.setFalta(falta+"");
						deduccion.setDia_faltas(dias_faltas);
						deduccion.setAsistencia(miVector.get(6).toString().trim());
						deduccion.setGafete(miVector.get(7).toString().trim());
						
						if(miVector.get(8).toString() != ""){
							deduccion.setDia_gafete(Integer.parseInt(miVector.get(8).toString()));
						}else {
							deduccion.setDia_gafete(0);
						}
						if(miVector.get(9).toString() != ""){
							deduccion.setExtra(Float.parseFloat(miVector.get(9).toString()));
						}else {
							deduccion.setExtra(0);
						}
						if(falta != true){
							deduccion.setCantidad_faltas(0);
						}else{
							float[] bono_sueldo = getBono_Sueldo(index);
							if(bono_dia_extra != true){
								deduccion.setCantidad_faltas(bono_sueldo[1]/7 * dias_faltas);
							}else{
								deduccion.setCantidad_faltas((bono_sueldo[0]+bono_sueldo[1])/7 * dias_faltas);
							}					
						}
				
						deduccion.guardar();
						
						miVector.clear();
						int porcent = (i*100)/total;
						barra.setValue(porcent+1);
						try {
							Thread.sleep(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
								
						}
					}
					JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
					dispose();
				}
			}
		}
	}
}
