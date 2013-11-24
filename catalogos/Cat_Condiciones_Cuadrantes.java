package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;

import objetos.JTextFieldLimit;

@SuppressWarnings("serial")
public class Cat_Condiciones_Cuadrantes extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	String operador[] = {"Todos","Igual","Esta en lista","Menor que","Mayor que","Diferente"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbOperador = new JComboBox(operador);
	
	JTextField txtComparacion = new JTextField();
	JButton btnSeleccionEmpleado = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnLimpiar = new JButton(new ImageIcon("Iconos/limpiar.png"));
	JButton btnEnviar = new JButton("Enviar");
	
	JTextArea txaArmado = new JTextArea();
	JScrollPane armado = new JScrollPane(txaArmado);
	Border border = LineBorder.createGrayLineBorder();
	
	public void getConstructor(){

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
		this.setTitle("Seleccion Tipo De Reporte");
		
		btnSeleccionEmpleado.setToolTipText("Seleccion de empleados");
		btnLimpiar.setToolTipText("Limpiar");

		txaArmado.setBorder(border);
		txaArmado.setLineWrap(true);
		txaArmado.setDocument(new JTextFieldLimit(240));
		
		int y=20;
		
		panel.add(new JLabel("Empleado ")).setBounds(20,y+=35,60,20);
		panel.add(cmbOperador).setBounds(80,y,100,20);
		
		panel.add(txtComparacion).setBounds(200, y, 140, 20);
		panel.add(btnSeleccionEmpleado).setBounds(350, y, 30, 20);
		panel.add(btnLimpiar).setBounds(390, y, 30, 20);
		
		panel.add(armado).setBounds(20, y+=35, 400, 100);
		
		panel.add(btnEnviar).setBounds(350, y+=110, 70, 20);
		
		cmbOperador.addActionListener(opComparar);
		btnLimpiar.addActionListener(opLimpiar);
		btnSeleccionEmpleado.addActionListener(opFiltroEmpleado);
		
		txtComparacion.setEditable(false);
		btnSeleccionEmpleado.setEnabled(false);

		cont.add(panel);
		this.setSize(460,280);
		this.setLocationRelativeTo(null);
	}
	
	public Cat_Condiciones_Cuadrantes(){
		 getConstructor();
	}
	
	ActionListener opComparar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(cmbOperador.getSelectedIndex() == 0){
				btnSeleccionEmpleado.setEnabled(false);
			}else{
				btnSeleccionEmpleado.setEnabled(true);
			}
			
			actionAplicar();
		}
	};
	
	ActionListener opLimpiar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			txtComparacion.setText("");
			actionAplicar();
		}
	};
	
	public void actionAplicar(){
			switch(cmbOperador.getSelectedIndex()){
				case 0:  txaArmado.setText("");break;
				case 1:  txaArmado.setText("folio_empleado = "+txtComparacion.getText()); break;
				case 2:  txaArmado.setText("folio_empleado IN "+txtComparacion.getText()); break;
				case 3:  txaArmado.setText("folio_empleado < "+txtComparacion.getText()); break;
				case 4:  txaArmado.setText("folio_empleado > "+txtComparacion.getText()); break;
				case 5:  txaArmado.setText("folio_empleado <> "+txtComparacion.getText()); break;
			}
	}
	
//	Cat_Filtro_Actividades
	ActionListener opFiltroEmpleado = new ActionListener(){
		public void actionPerformed(ActionEvent e){
				
				new Cat_Filtro_Empleados_Con_Cuadrante().setVisible(true);
			}
	};

	public static void main (String [] arg){
		try{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		Cat_Condiciones_Cuadrantes thisClass = new Cat_Condiciones_Cuadrantes();
		thisClass.setVisible(true);

		//utilizacion del AWTUtilities con el metodo opaque
		try {
			   @SuppressWarnings("rawtypes")
			Class clazz =  Class.forName("com.sun.awt.AWTUtilities");
			   @SuppressWarnings("unchecked")
			Method method = clazz.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);
			   method.invoke(clazz,thisClass , false);
			   } catch (Exception e) 
			   { }	
	}
	
	
	
 	public class Cat_Filtro_Empleados_Con_Cuadrante extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		String dia = "";
		
		Object[][] MatrizFiltro ;
		
		Object[][] getTablaFiltro = getTablaFiltro();
		DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
	            new String[]{"Folio", "Actividad","Nivel Crítico",""}
				){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Integer.class,
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
	        	 	
	        	 	case 3 : return true;
	        	 		
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tablaFiltro = new JTable(modeloFiltro);
	    JScrollPane scroll = new JScrollPane(tablaFiltro);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		JButton btnAgregar = new JButton("Agregar");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		public Cat_Filtro_Empleados_Con_Cuadrante() {
			
			this.setModal(true);
			setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
			setTitle("Filtro De Empleados En Cuadrantes");
			campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Empleados En Cuadrantes"));
			trsfiltro = new TableRowSorter(modeloFiltro); 
			tablaFiltro.setRowSorter(trsfiltro);  
			
			campo.add(scroll).setBounds(15,43,574,360);
			
			campo.add(txtFolio).setBounds(15,20,40,20);
			campo.add(txtNombre_Completo).setBounds(56,20,400,20);
			campo.add(btnAgregar).setBounds(510,20,80,20);
			
			cont.add(campo);
			
			tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
			tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
			tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(400);
			tablaFiltro.getColumnModel().getColumn(1).setMinWidth(400);
			tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(80);
			tablaFiltro.getColumnModel().getColumn(2).setMinWidth(80);
			tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(40);
			tablaFiltro.getColumnModel().getColumn(3).setMinWidth(40);
			
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
							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
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
							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
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
							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
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
							componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
							if(row%2==0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
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
		
			tablaFiltro.getColumnModel().getColumn(0).setCellRenderer(render); 
			tablaFiltro.getColumnModel().getColumn(1).setCellRenderer(render); 
			tablaFiltro.getColumnModel().getColumn(2).setCellRenderer(render);
			tablaFiltro.getColumnModel().getColumn(3).setCellRenderer(render);
			
			txtFolio.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			
			btnAgregar.addActionListener(opAgregar);
			
			setSize(615,450);
			setResizable(false);
			setLocationRelativeTo(null);
			
		}
		
		ActionListener opAgregar = new ActionListener() {
			@SuppressWarnings({ "unchecked" })
			public void actionPerformed(ActionEvent arg0) {
				
				if(tablaFiltro.isEditing()){
		 			tablaFiltro.getCellEditor().stopCellEditing();
				}
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
				
				txtFolio.setText("");
				txtNombre_Completo.setText("");
				
				Object[] fila = new Object[5];
				
				int contador=0;
		 		String cadena="'('";	
		 		
		 		
		 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
		 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
		 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
		 					fila[0] = posicion;
		 					fila[1] = modeloFiltro.getValueAt(i, 1);
		 					fila[2] = false;
		 					fila[3] = fila[2] = "";
		 					fila[4] = "" ;
		 					
		 					contador=contador+=1;
		 					
		 					if(cmbOperador.getSelectedIndex() != 2 && contador == 1){
		 							cadena=cadena+"'"+posicion+"'";
		 					}else{
		 						
		 						if(cmbOperador.getSelectedIndex() != 2 && contador != 1){
		 							JOptionPane.showMessageDialog(null, "Seleccione solo un empleado", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
		 							return;
		 						}else{
		 							if(contador == 1){
				 						cadena=cadena+"'"+posicion+"'";
				 					}else{
				 						cadena=cadena+"'"+","+"'"+"'"+posicion+"'";
				 					}
		 						}
		 					}
		 				}
		 			}
		 			
		 			cadena=cadena+"')'";
		 			txtComparacion.setText(cadena);
		 			actionAplicar();
		 			dispose();
			}
		};
		
		
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
		
		
	   	public Object[][] getTablaFiltro(){
			String todos = "exec sp_select_filtro_empleados_con_cuadrante_asignado";
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(todos);
				
				MatrizFiltro = new Object[getFilas(todos)][4];
				int i=0;
				while(rs.next()){
					int folio = rs.getInt(1);
					MatrizFiltro[i][0] = folio+"  ";
					MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
					MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
					MatrizFiltro[i][3] = false;
					i++;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    return MatrizFiltro; 
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
