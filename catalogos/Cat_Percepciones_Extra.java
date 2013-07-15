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

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
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

import SQL.Connexion;

import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Establecimiento;
import objetos.Obj_Persecciones_Extra;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Percepciones_Extra extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	@SuppressWarnings("rawtypes")
	TableRowSorter filter;
		
	Object[][] Matriz ;
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    @SuppressWarnings("rawtypes")
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	    
	JCheckBox chbHabilitar = new JCheckBox("Habilitar");
	JCheckBox chbTodos = new JCheckBox("");
	
	Object[][] Tabla = getTabla();
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Bono", "DE", "Cantidad Dias"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Integer.class, 
	    	java.lang.Boolean.class, 
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
    @SuppressWarnings("rawtypes")
	JComboBox cmbDias = new JComboBox(lista);
    @SuppressWarnings("rawtypes")
	JComboBox cmbDia = new JComboBox(lista);
    
    JToolBar menu = new JToolBar();
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	Obj_Configuracion_Sistema configs = new Obj_Configuracion_Sistema().buscar2();
	boolean bono_dia_extra = configs.isBono_dia_extra();
	
	@SuppressWarnings("rawtypes")
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
		panel.add(txtNombre).setBounds(170,45,355,20);
		panel.add(cmbEstablecimientos).setBounds(530,45,210,20);
		panel.add(chbHabilitar).setBounds(750,45,70,20);
		panel.add(chbTodos).setBounds(865,45,20,20);
		panel.add(cmbDia).setBounds(900,45,70,20);
		
		panel.add(scroll).setBounds(100,70,935,580);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		panel.add(menu);
		cont.add(panel);
		
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
		ColumnaDias.setCellEditor(new DefaultCellEditor(cmbDias));
		
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
		cmbEstablecimientos.addActionListener(opFiltro);

		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}
	
	 public void filtroFolio(){ 
		filter.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0)); 
	}
	
	public void filtro(){ 
		filter.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase(), 1)); 
	}  
	
	ActionListener opFiltro = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				filter.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				filter.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	ActionListener opDias = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			for(int i=0; i<tabla.getRowCount(); i++) {
				if(Boolean.parseBoolean(model.getValueAt(i,4).toString()) != true){
					model.setValueAt("", i,5);
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
					model.setValueAt("", j,5);
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
		String query  = "exec sp_lista_persecciones";

		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery(query);
			Matriz = new Object[getFilas(query)][6];
			int i=0;
			while(rs.next()){
				Matriz[i][0] = rs.getInt(1);
				Matriz[i][1] = rs.getString(2).trim();
				Matriz[i][2] = rs.getString(3).trim();
				float bono = rs.getInt(4);
				if(bono == 0){
					Matriz[i][3] = "";
				}else{
					Matriz[i][3] = bono;
				}
				Matriz[i][4] = rs.getBoolean(5);
				float cantidad = rs.getInt(6);
				if(cantidad == 0){
					Matriz[i][5] = "";
				}else{
					Matriz[i][5] = rs.getInt(6);
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
				if(getFilas("exec sp_status_persecciones") > 1){
					panel.setBorder(BorderFactory.createTitledBorder("Actualizando lista de Persecciones..."));
					if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
						for(int i=0; i<model.getRowCount(); i++){
							for(int j=0; j<model.getColumnCount(); j++){
								miVector.add(model.getValueAt(i,j));
							}
							Obj_Persecciones_Extra perseccion = new Obj_Persecciones_Extra();
							
							int index = Integer.parseInt(miVector.get(0).toString().trim());
							
							perseccion.setFolio_empleado(index);
							perseccion.setNombre_completo(miVector.get(1).toString().trim());
							perseccion.setEstablecimiento(miVector.get(2).toString().trim());
							if(miVector.get(3) != ""){
								perseccion.setBono(Float.parseFloat(miVector.get(3).toString().trim()));
							}else{
								miVector.set(3,0);
								perseccion.setBono(Float.parseFloat(miVector.get(3).toString().trim()));
							}
							boolean dia_extra = Boolean.parseBoolean(miVector.get(4).toString().trim());
							perseccion.setDia_extra(dia_extra+"");
							
							if(miVector.get(5) != ""){
								perseccion.setDias(Integer.parseInt(miVector.get(5).toString().trim()));
							}else{
								miVector.set(5,0);
								perseccion.setDias(Integer.parseInt(miVector.get(5).toString().trim()));
							}
							if(dia_extra != true) {
								perseccion.setCantidad_dias(0);
							}else{
								float[] bono_sueldo = getBono_Sueldo(index);
								if(bono_dia_extra != true){
									perseccion.setCantidad_dias(Math.round((bono_sueldo[1]/7) * Integer.parseInt(miVector.get(5)+"")));
								}else{
									perseccion.setCantidad_dias(Math.round(((bono_sueldo[0]+bono_sueldo[1])/7) * Integer.parseInt(miVector.get(5)+"")));
								}
							}
							Obj_Persecciones_Extra existe = new Obj_Persecciones_Extra().buscar(index);
							if(existe.getFolio_empleado() == index){
								perseccion.actualizar(index);
							}else{
								perseccion.guardar();
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
					panel.setBorder(BorderFactory.createTitledBorder("Guardando lista de Persecciones..."));
					for(int i=0; i<model.getRowCount(); i++){
						for(int j=0; j<model.getColumnCount(); j++){
							miVector.add(model.getValueAt(i,j));
						}
						Obj_Persecciones_Extra perseccion = new Obj_Persecciones_Extra();
				
						int index = Integer.parseInt(miVector.get(0).toString().trim());
						
						perseccion.setFolio_empleado(index);
						perseccion.setNombre_completo(miVector.get(1).toString().trim());
						perseccion.setEstablecimiento(miVector.get(2).toString().trim());
						if(miVector.get(3) != ""){
							perseccion.setBono(Float.parseFloat(miVector.get(3).toString().trim()));
						}else{
							miVector.set(3,0);
							perseccion.setBono(Float.parseFloat(miVector.get(3).toString().trim()));
						}
						boolean dia_extra = Boolean.parseBoolean(miVector.get(4).toString().trim());
						perseccion.setDia_extra(dia_extra+"");
						
						if(miVector.get(5) != ""){
							perseccion.setDias(Integer.parseInt(miVector.get(5).toString().trim()));
						}else{
							miVector.set(5,0);
							perseccion.setDias(Integer.parseInt(miVector.get(5).toString().trim()));
						}
						if(dia_extra != true) {
							perseccion.setCantidad_dias(0);
						}else{
							float[] bono_sueldo = getBono_Sueldo(index);
							if(bono_dia_extra != true){
								perseccion.setCantidad_dias(Math.round((bono_sueldo[1]/7) * Integer.parseInt(miVector.get(5)+"")));
							}else{
								perseccion.setCantidad_dias(Math.round(((bono_sueldo[0]+bono_sueldo[1])/7) * Integer.parseInt(miVector.get(5)+"")));
							}
						}
						perseccion.guardar();
						
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