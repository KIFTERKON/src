package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Gen_Code_Bar;
import objetos.Obj_Reporte_Impresion_Gafetes;
import reporte.Reporte_Impresion_de_Gafetes;
import reporte.Reporte_Impresion_de_Gafetes_2;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Reporte_Impresion_Gafetes extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	JButton btn_Empleados = new  JButton("Empleados");
	JButton btn_Generar = new JButton("Generar 4 Gafetes");
	JButton btn_Generar_2 = new JButton("Generar 2 Gafetes");
	
	private DefaultTableModel tabla_model = new DefaultTableModel(null,
            new String[]{"Folio", "Nombre Completo","Establecimiento","Puesto"}
			){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class,
	    	java.lang.Object.class,
	    	java.lang.Object.class
         };
	     
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
        public boolean isCellEditable(int fila, int columna){
        	switch(columna){
        		case 0 : return false; 
        	 	case 1 : return false; 
        		case 2 : return false;
        		case 3 : return false;
        
        	 } 				
 			return false;
 		}
	};
	
	public JTable tabla = new JTable(tabla_model);
	public JScrollPane scroll_tabla = new JScrollPane(tabla);
	
	public Cat_Reporte_Impresion_Gafetes(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/reporte_icon&16.png"));
		this.setTitle("Impresión de Gafetes");
		this.panel.setBorder(BorderFactory.createTitledBorder("Impresión de Gafetes"));
		
		this.panel.add(btn_Empleados).setBounds(25,25,100,20);
		
		this.panel.add(btn_Generar).setBounds(25,50,180,20);
		this.panel.add(btn_Generar_2).setBounds(25,70,180,20);
		
		this.panel.add(scroll_tabla).setBounds(25,90,750,92);
		
		this.btn_Empleados.addActionListener(op_filtro);
		this.btn_Generar.addActionListener(op_generar);
		this.btn_Generar.setEnabled(false);
		this.btn_Generar_2.addActionListener(op_generar_2);
		this.btn_Generar_2.setEnabled(false);
		
		
		this.cont.add(panel);
		
		this.setSize(800,250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		tabla.getColumnModel().getColumn(0).setMaxWidth(45);
		tabla.getColumnModel().getColumn(0).setMinWidth(45);
		tabla.getColumnModel().getColumn(1).setMaxWidth(335);
		tabla.getColumnModel().getColumn(1).setMinWidth(335);
		tabla.getColumnModel().getColumn(2).setMaxWidth(140);
		tabla.getColumnModel().getColumn(2).setMinWidth(140);
		tabla.getColumnModel().getColumn(3).setMaxWidth(240);
		tabla.getColumnModel().getColumn(3).setMinWidth(240);

	}
	
	ActionListener op_generar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Obj_Gen_Code_Bar().Reset_Code();
			new Obj_Gen_Code_Bar().Reset_Users();
			new Obj_Reporte_Impresion_Gafetes().buscar_masivo(list_folios().trim());
			new Reporte_Impresion_de_Gafetes();
		}
	};
	ActionListener op_generar_2 = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Obj_Gen_Code_Bar().Reset_Code();
			new Obj_Gen_Code_Bar().Reset_Users();
			new Obj_Reporte_Impresion_Gafetes().buscar_masivo(list_folios().trim());
			new Reporte_Impresion_de_Gafetes_2();
		}
	};

	public String list_folios(){
		String lista = "";
		
		for(int i=0; i<tabla.getRowCount(); i++){
			lista += tabla_model.getValueAt(i,0).toString().trim()+", ";
		}
		
		return lista;
	}

	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reporte_Impresion_Gafetes().setVisible(true);
		}catch(Exception e){
			System.err.println("Error: "+e.getMessage());
		}
	}
	
	ActionListener op_filtro = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Cat_Filtro_Empleado().setVisible(true);
		}
	};
	class Cat_Filtro_Empleado extends JFrame {

		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		Object[][] Matriz ;
		
		Object[][] Tabla = getTabla();
		DefaultTableModel model1 = new DefaultTableModel(Tabla,
	            new String[]{"Folio", "Nombre Completo","Establecimiento","Puesto", "S"}
				){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Integer.class,
		    	java.lang.String.class,
		    	java.lang.String.class,
		    	java.lang.String.class,
		    	java.lang.Boolean.class	    	
	         };
		     @SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
	             return types[columnIndex];
	         }
	         public boolean isCellEditable(int fila, int columna){
	        	 switch(columna){
	        	 	case 0 : return false; 
	        	 	case 1 : return false; 
	        	 	case 2 : return false; 
	        	 	case 3 : return false; 
	        	 	case 4 : return true; 
	        	 } 				
	 			return false;
	 		}
			
		};
		
		JTable tabla1 = new JTable(model1);
	    JScrollPane scroll = new JScrollPane(tabla1);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		JButton btnAgregar = new JButton("Agregar 4 Empleados");
		JButton btnAgregar_2 = new JButton("Agregar 2 Empleados");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		public Cat_Filtro_Empleado()	{
			setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
			setTitle("Filtro de Empleados");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Empleado"));
			trsfiltro = new TableRowSorter(model1); 
			tabla1.setRowSorter(trsfiltro);  
			
			campo.add(scroll).setBounds(15,42,810,500);
			
			campo.add(txtFolio).setBounds(15,20,48,20);
			campo.add(txtNombre_Completo).setBounds(64,20,259,20);
			campo.add(btnAgregar).setBounds(324,20,150, 20);
			campo.add(btnAgregar_2).setBounds(484,20,150, 20);
			
			cont.add(campo);
			
			tabla1.getColumnModel().getColumn(0).setMaxWidth(45);
			tabla1.getColumnModel().getColumn(0).setMinWidth(45);
			tabla1.getColumnModel().getColumn(1).setMaxWidth(335);
			tabla1.getColumnModel().getColumn(1).setMinWidth(335);
			tabla1.getColumnModel().getColumn(2).setMaxWidth(140);
			tabla1.getColumnModel().getColumn(2).setMinWidth(140);
			tabla1.getColumnModel().getColumn(3).setMaxWidth(240);
			tabla1.getColumnModel().getColumn(3).setMinWidth(240);
			tabla1.getColumnModel().getColumn(4).setMaxWidth(25);
			tabla1.getColumnModel().getColumn(4).setMinWidth(25);
					
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					
					Component componente = null;
					
					switch(column){
						case 0: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(model1.getValueAt(row,2).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
							break;
						case 1: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(model1.getValueAt(row,2).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
						case 2: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(model1.getValueAt(row,2).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
						case 3: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(model1.getValueAt(row,2).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
						case 4: 
							componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(model1.getValueAt(row,2).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
							break;
						
					}
						
					return componente;
				} 
			}; 
			
			for(int i= 0; i<tabla1.getColumnCount(); i++){
				tabla1.getColumnModel().getColumn(i).setCellRenderer(render); 
			}
			
			txtFolio.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			
			btnAgregar.addActionListener(Agregar);
			btnAgregar_2.addActionListener(Agregar_2);
			setSize(850,600);
//			setSize(425,450);
			setResizable(false);
			setLocationRelativeTo(null);
			
		}
		
		ActionListener Agregar = new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
				
				txtFolio.setText("");
				txtNombre_Completo.setText("");
				
				if(valida_select() < 4 || valida_select() > 4){
					JOptionPane.showMessageDialog(null,"Debes seleccionar a 4 Empleados", "Aviso", JOptionPane.WARNING_MESSAGE);
				}else{
					while(tabla_model.getRowCount() > 0)
						tabla_model.removeRow(0);

					Object[] vectornull = new Object[tabla.getColumnCount()];
					for(int i=0; i<tabla1.getRowCount(); i++){
						if(Boolean.parseBoolean(model1.getValueAt(i, 4).toString()) == true){
							tabla_model.addRow(vectornull);
							for(int j=0; j<tabla.getColumnCount(); j++){
								tabla_model.setValueAt(model1.getValueAt(i,j), tabla.getRowCount()-1, j);
							}
						}
					}
					btn_Generar.setEnabled(true);
					btn_Generar_2.setEnabled(false);
					dispose();
				}
			}
		};
		ActionListener Agregar_2 = new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
				
				txtFolio.setText("");
				txtNombre_Completo.setText("");
				
				if(valida_select() < 2 || valida_select_2() > 2){
					JOptionPane.showMessageDialog(null,"Debes seleccionar a 2 Empleados", "Aviso", JOptionPane.WARNING_MESSAGE);
				}else{
					while(tabla_model.getRowCount() > 0)
						tabla_model.removeRow(0);

					Object[] vectornull = new Object[tabla.getColumnCount()];
					for(int i=0; i<tabla1.getRowCount(); i++){
						if(Boolean.parseBoolean(model1.getValueAt(i, 4).toString()) == true){
							tabla_model.addRow(vectornull);
							for(int j=0; j<tabla.getColumnCount(); j++){
								tabla_model.setValueAt(model1.getValueAt(i,j), tabla.getRowCount()-1, j);
							}
						}
					}
					btn_Generar_2.setEnabled(true);
					btn_Generar.setEnabled(false);
					dispose();
				}
			}
		};
		public int valida_select(){
			int contador = 0;
			for(int i=0; i<tabla1.getRowCount(); i++){
				if(Boolean.parseBoolean(model1.getValueAt(i, 4).toString()) == true){
					contador++;
				}
			}
			return contador;
		}
		public int valida_select_2(){
			int contador = 0;
			for(int i=0; i<tabla1.getRowCount(); i++){
				if(Boolean.parseBoolean(model1.getValueAt(i, 4).toString()) == true){
					contador++;
				}
			}
			return contador;
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
		
	   	public Object[][] getTabla(){
			String todos = "exec sp_filtro_empleado_status_vigente";
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(todos);
				Matriz = new Object[getFilas(todos)][10];
				int i=0;
				while(rs.next()){
					Matriz[i][0] = rs.getString(1)+"  ";
					Matriz[i][1] = "   "+rs.getString(2);
					Matriz[i][2] = "   "+rs.getString(3);
					Matriz[i][3] = "   "+rs.getString(4);
					Matriz[i][4] = false;
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
				stmt = new Connexion().conexion().createStatement();
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){
					filas++;
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return filas;
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
}
