package CatalogoChecador;

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
import java.text.SimpleDateFormat;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

import objetos.Obj_Establecimiento;

import com.toedter.calendar.JDateChooser;

import ObjetoChecador.Obj_Asignacion_Horario_Temporada;
import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Asignacion_Horario_Temporada extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	Object[][] MatrizFiltro ;
	Object[][] MatrizFiltroAsignada ;
	
	Object[][] getTablaFiltro = getTablaFiltro();
	DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
            new String[]{"Folio", "Empleado","Establecimiento","Puesto",""}
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
	
	JTable tablaFiltro = new JTable(modeloFiltro);
    JScrollPane scroll = new JScrollPane(tablaFiltro);
    
    @SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
    
    String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimientoAsignado = new JComboBox(establecimiento);
    
    JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
    
	Object[][] getTablaAsignada = getTablaFiltroAsignado();
	DefaultTableModel modeloFiltroAsignado = new DefaultTableModel(getTablaAsignada,
            new String[]{"Folio", "Empleado","Establecimiento","Puesto","fecha Inicial","fecha final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class
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
        	 	case 4 : return false;
        	 	case 5 : return false;
        	 		
        	 } 				
 			return false;
 		}
	};
	
    JTable tablaFiltroAsignado = new JTable(modeloFiltroAsignado);
    JScrollPane scrollAsignado = new JScrollPane(tablaFiltroAsignado);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltroAsignado;
	
	JTextField txtFolio_Asignado = new JTextField();
	JTextField txtNombre_Completo_Asignado = new JTextField();
	
	JButton btnAgregar = new JButton("Agregar");
	
	JDateChooser txtFechaInicial = new JDateChooser();
	JDateChooser txtFechaFinal = new JDateChooser();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public Cat_Asignacion_Horario_Temporada() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		setTitle("Asignacion de horario de temporada");
		campo.setBorder(BorderFactory.createTitledBorder("Asignar a enpleado"));
		
		
		trsfiltro = new TableRowSorter(modeloFiltro); 
		tablaFiltro.setRowSorter(trsfiltro); 
		
		trsfiltroAsignado = new TableRowSorter(modeloFiltroAsignado); 
		tablaFiltroAsignado.setRowSorter(trsfiltroAsignado); 
		
		campo.add(txtFolio).setBounds(15,20,40,20);
		campo.add(txtNombre_Completo).setBounds(56,20,350,20);
		campo.add(cmbEstablecimiento).setBounds(406,20,130,20);
		campo.add(scroll).setBounds(15,43,854,250);
		
		campo.add(txtFolio_Asignado).setBounds(15,305,40,20);
		campo.add(txtNombre_Completo_Asignado).setBounds(56,305,350,20);
		campo.add(cmbEstablecimientoAsignado).setBounds(406,305,130,20);
		campo.add(scrollAsignado).setBounds(15,330,994,250);
		
		campo.add(new JLabel("De:")).setBounds(540, 20, 25, 20);
		campo.add(txtFechaInicial).setBounds(560, 20, 100, 20);
		campo.add(new JLabel("A:")).setBounds(665, 20, 25, 20);
		campo.add(txtFechaFinal).setBounds(680, 20, 100, 20);
		campo.add(btnAgregar).setBounds(788,20,80,20);
		
		cont.add(campo);
		
		tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
		tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
		tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(350);
		tablaFiltro.getColumnModel().getColumn(1).setMinWidth(350);
		tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(130);
		tablaFiltro.getColumnModel().getColumn(2).setMinWidth(130);
		tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(280);
		tablaFiltro.getColumnModel().getColumn(3).setMinWidth(280);
		tablaFiltro.getColumnModel().getColumn(4).setMaxWidth(40);
		tablaFiltro.getColumnModel().getColumn(4).setMinWidth(40);
		
		tablaFiltroAsignado.getColumnModel().getColumn(0).setMaxWidth(40);
		tablaFiltroAsignado.getColumnModel().getColumn(0).setMinWidth(40);
		tablaFiltroAsignado.getColumnModel().getColumn(1).setMaxWidth(350);
		tablaFiltroAsignado.getColumnModel().getColumn(1).setMinWidth(350);
		tablaFiltroAsignado.getColumnModel().getColumn(2).setMaxWidth(130);
		tablaFiltroAsignado.getColumnModel().getColumn(2).setMinWidth(130);
		tablaFiltroAsignado.getColumnModel().getColumn(3).setMaxWidth(280);
		tablaFiltroAsignado.getColumnModel().getColumn(3).setMinWidth(280);
		tablaFiltroAsignado.getColumnModel().getColumn(4).setMaxWidth(90);
		tablaFiltroAsignado.getColumnModel().getColumn(4).setMinWidth(90);
		tablaFiltroAsignado.getColumnModel().getColumn(5).setMaxWidth(90);
		tablaFiltroAsignado.getColumnModel().getColumn(5).setMinWidth(90);
		
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
					case 4: 
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
		tablaFiltro.getColumnModel().getColumn(4).setCellRenderer(render);
		
		TableCellRenderer render2 = new TableCellRenderer() { 
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
					case 4:
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
					case 5:
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
				}
				return componente;
			} 
		}; 
		
		tablaFiltroAsignado.getColumnModel().getColumn(0).setCellRenderer(render2); 
		tablaFiltroAsignado.getColumnModel().getColumn(1).setCellRenderer(render2); 
		tablaFiltroAsignado.getColumnModel().getColumn(2).setCellRenderer(render2);
		tablaFiltroAsignado.getColumnModel().getColumn(3).setCellRenderer(render2);
		tablaFiltroAsignado.getColumnModel().getColumn(4).setCellRenderer(render2);
		tablaFiltroAsignado.getColumnModel().getColumn(5).setCellRenderer(render2);
		
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimiento.addActionListener(opFiltroChb);
		
		txtFolio_Asignado.addKeyListener(opFiltroFolioAsignado);
		txtNombre_Completo_Asignado.addKeyListener(opFiltroNombreAsignado);
		cmbEstablecimientoAsignado.addActionListener(opFiltroChbAsignado);
		
		btnAgregar.addActionListener(opAgregar);
		
		setSize(1024,650);
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
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			
			if(valida_error() != ""){
				JOptionPane.showMessageDialog(null, "Los siguientes datos son requeridos:\n"+valida_error(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
					Obj_Asignacion_Horario_Temporada horario_temporada = new Obj_Asignacion_Horario_Temporada();
					
					String fechaIn = new SimpleDateFormat("dd/MM/yyyy").format(txtFechaInicial.getDate())+" 00:00";
					String fechaFin = new SimpleDateFormat("dd/MM/yyyy").format(txtFechaFinal.getDate())+" 23:59";
					
					if(txtFechaInicial.getDate().before(txtFechaFinal.getDate())){
						if(horario_temporada.guardar(tabla_guardar(),fechaIn,fechaFin)){
							
							  while(tablaFiltro.getRowCount()>0){
	                              modeloFiltro.removeRow(0);
	                      }
							  
							  while(tablaFiltroAsignado.getRowCount()>0){
	                                modeloFiltroAsignado.removeRow(0);
	                      }
							    
							  Object[][] getTablaFiltro = getTablaFiltro();
					              String[] fila = new String[5];
					                              for(int i=0; i<getTablaFiltro.length; i++){
					                                      fila[0] = getTablaFiltro[i][0]+"";
					                                      fila[1] = getTablaFiltro[i][1]+"";
					                                      fila[2] = getTablaFiltro[i][2]+"";
					                                      fila[3] = getTablaFiltro[i][3]+"";
					                                      fila[4] = "";
					                                      modeloFiltro.addRow(fila);
					                              }
					                              
					                          
					          Object[][] getTablaAsignada = getTablaFiltroAsignado();
					              String[] filaAsignada = new String[6];
					                              for(int j=0; j<getTablaAsignada.length; j++){
						                            	  filaAsignada[0] = getTablaAsignada[j][0]+"";
						                            	  filaAsignada[1] = getTablaAsignada[j][1]+"";
						                            	  filaAsignada[2] = getTablaAsignada[j][2]+"";
						                            	  filaAsignada[3] = getTablaAsignada[j][3]+"";
						                            	  filaAsignada[4] = getTablaAsignada[j][4]+"";
						                            	  filaAsignada[5] = getTablaAsignada[j][5]+"";
					                                      modeloFiltroAsignado.addRow(filaAsignada);
					                              }
					         cmbEstablecimiento.setSelectedIndex(0);
							JOptionPane.showMessageDialog(null, "La tabla se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}else{
						JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
						return;
					}
			}
		}
	};
	
	private Object[][] tabla_guardar(){

		Object[][] matriz = new Object[tablaFiltro.getRowCount()][4];
		for(int i=0; i<tablaFiltro.getRowCount(); i++){
			
				matriz[i][0] = Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim());
				matriz[i][1] = modeloFiltro.getValueAt(i,2).toString().trim();
				matriz[i][2] = modeloFiltro.getValueAt(i,3).toString().trim();
				matriz[i][3] = Boolean.parseBoolean(modeloFiltro.getValueAt(i,4).toString().trim());
		}
		return matriz;
	}
	
	public String valida_error(){
		String error="";
			String fechaIn = txtFechaInicial.getDate()+"";
			String fechaFin = txtFechaFinal.getDate()+"";
			if(fechaIn.equals("null"))error+= "Fecha Inicial\n";
			if(fechaFin.equals("null"))error+= "Fecha Final\n";
		return error;
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
	
	ActionListener opFiltroChb = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimiento.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimiento.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	KeyListener opFiltroFolioAsignado = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltroAsignado.setRowFilter(RowFilter.regexFilter(txtFolio_Asignado.getText(), 0));
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
	
	KeyListener opFiltroNombreAsignado = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltroAsignado.setRowFilter(RowFilter.regexFilter(txtNombre_Completo_Asignado.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener opFiltroChbAsignado = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientoAsignado.getSelectedIndex() != 0){
				trsfiltroAsignado.setRowFilter(RowFilter.regexFilter(cmbEstablecimientoAsignado.getSelectedItem()+"", 2));
			}else{
				trsfiltroAsignado.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
   	public Object[][] getTablaFiltro(){
		String todos = "exec sp_select_filtro_horarios_temporada";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery(todos);
			
			MatrizFiltro = new Object[getFilas(todos)][5];
			int i=0;
			while(rs.next()){
				int folio = rs.getInt(1);
				MatrizFiltro[i][0] = folio+"  ";
				MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltro[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltro[i][4] = "";
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltro; 
	}
   	
   	public Object[][] getTablaFiltroAsignado(){
		String todos = "exec sp_select_filtro_horarios_temporada_asignado";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery(todos);
			
			MatrizFiltroAsignada = new Object[getFilas(todos)][6];
			int i=0;
			while(rs.next()){
				int folio = rs.getInt(1);
				MatrizFiltroAsignada[i][0] = folio+"  ";
				MatrizFiltroAsignada[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltroAsignada[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltroAsignada[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltroAsignada[i][4] = "   "+rs.getString(5).trim();
				MatrizFiltroAsignada[i][5] = "   "+rs.getString(6).trim();
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltroAsignada; 
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
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Asignacion_Horario_Temporada().setVisible(true);
		}catch(Exception e){
			
		}
	}
}