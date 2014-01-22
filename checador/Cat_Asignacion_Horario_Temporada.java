package checador;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.JDialog;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.JTextFieldLimit;
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
            new String[]{"Folio", "Empleado","Establecimiento","Puesto","Departamento",""}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
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
        	 	case 4 : return false;
        	 	case 5 : return true;
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
    
    JTextField txtHorarioTemporada = new JTextField();
    JLabel lblFolioHorarioTemporal = new JLabel();
    JButton btnFiltroHorario = new JButton("Horario");
    
    JButton btnQuitar = new JButton("Quitar");
    
	Object[][] getTablaAsignada = getTablaFiltroAsignado();
	DefaultTableModel modeloFiltroAsignado = new DefaultTableModel(getTablaAsignada,
            new String[]{"Folio", "Empleado","Establecimiento","Puesto","Fecha Inicial","Fecha final","Horario Temporal"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
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
        	 	case 6 : return false;
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
		campo.add(scroll).setBounds(15,43,1090,250);
		
		campo.add(new JLabel("Horiario temporal: ")).setBounds(15,300,100,20);
		campo.add(lblFolioHorarioTemporal).setBounds(110,300,100,20);
		campo.add(txtHorarioTemporada).setBounds(140,300,550,20);
		campo.add(btnFiltroHorario).setBounds(700,300,75,20);
		campo.add(btnAgregar).setBounds(788,300,80,20);
		
		campo.add(txtFolio_Asignado).setBounds(15,330,40,20);
		campo.add(txtNombre_Completo_Asignado).setBounds(56,330,350,20);
		campo.add(cmbEstablecimientoAsignado).setBounds(406,330,130,20);
		campo.add(scrollAsignado).setBounds(15,355,1090,250);
		
		campo.add(btnQuitar).setBounds(540,330,80,20);
		
		campo.add(new JLabel("De:")).setBounds(560, 20, 25, 20);
		campo.add(txtFechaInicial).setBounds(580, 20, 100, 20);
		campo.add(new JLabel("A:")).setBounds(685, 20, 25, 20);
		campo.add(txtFechaFinal).setBounds(700, 20, 100, 20);
		
		cont.add(campo);
		
		tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
		tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
		tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(350);
		tablaFiltro.getColumnModel().getColumn(1).setMinWidth(350);
		tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(130);
		tablaFiltro.getColumnModel().getColumn(2).setMinWidth(130);
		tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(280);
		tablaFiltro.getColumnModel().getColumn(3).setMinWidth(280);
		tablaFiltro.getColumnModel().getColumn(4).setMaxWidth(236);
		tablaFiltro.getColumnModel().getColumn(4).setMinWidth(236);
		tablaFiltro.getColumnModel().getColumn(5).setMaxWidth(40);
		tablaFiltro.getColumnModel().getColumn(5).setMinWidth(40);
		
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
		tablaFiltroAsignado.getColumnModel().getColumn(6).setMaxWidth(100);
		tablaFiltroAsignado.getColumnModel().getColumn(6).setMinWidth(100);
		
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
					case 6:
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
	
		TableCellRenderer render2 = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				Component componente = null;
				
				switch(column){
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
		
		
		tablaFiltro.getColumnModel().getColumn(0).setCellRenderer(render); 
		tablaFiltro.getColumnModel().getColumn(1).setCellRenderer(render); 
		tablaFiltro.getColumnModel().getColumn(2).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(3).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(4).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(5).setCellRenderer(render);
		
		tablaFiltroAsignado.getColumnModel().getColumn(0).setCellRenderer(render); 
		tablaFiltroAsignado.getColumnModel().getColumn(1).setCellRenderer(render); 
		tablaFiltroAsignado.getColumnModel().getColumn(2).setCellRenderer(render);
		tablaFiltroAsignado.getColumnModel().getColumn(3).setCellRenderer(render);
		tablaFiltroAsignado.getColumnModel().getColumn(4).setCellRenderer(render);
		tablaFiltroAsignado.getColumnModel().getColumn(5).setCellRenderer(render2);
		tablaFiltroAsignado.getColumnModel().getColumn(6).setCellRenderer(render);
		
		
		txtHorarioTemporada.setEnabled(false);
		
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimiento.addActionListener(opFiltroChb);
		
		txtFolio_Asignado.addKeyListener(opFiltroFolioAsignado);
		txtNombre_Completo_Asignado.addKeyListener(opFiltroNombreAsignado);
		cmbEstablecimientoAsignado.addActionListener(opFiltroChbAsignado);
		
		btnAgregar.addActionListener(opAgregar);
		btnFiltroHorario.addActionListener(opFiltroHorairoTemporal);
		btnQuitar.addActionListener(opQuitar);
		
		setSize(1124,650);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	ActionListener opFiltroHorairoTemporal = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Filtro_Horario_Temporal_Empleado().setVisible(true);
		}
	};
	
	ActionListener opQuitar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(tablaFiltroAsignado.getSelectedRow()>=0){
				
					int filaSelecionada = tablaFiltroAsignado.getSelectedRow();
					
					int folio =  Integer.parseInt(tablaFiltroAsignado.getValueAt(filaSelecionada, 0).toString().trim());
					Object establecimiento =  tablaFiltroAsignado.getValueAt(filaSelecionada, 2);
					Object puesto =  tablaFiltroAsignado.getValueAt(filaSelecionada, 3);
					Object fecha1 =  tablaFiltroAsignado.getValueAt(filaSelecionada, 4);
					Object fecha2 =  tablaFiltroAsignado.getValueAt(filaSelecionada, 5);
					
					Obj_Asignacion_Horario_Temporada remover = new Obj_Asignacion_Horario_Temporada();
					if(remover.borrar(folio,establecimiento+"",puesto+"",fecha1+"",fecha2+"")){
						
							  while(tablaFiltro.getRowCount()>0){
		                          modeloFiltro.removeRow(0);
							  }
							  while(tablaFiltroAsignado.getRowCount()>0){
		                            modeloFiltroAsignado.removeRow(0);
							  }
							    
							  Object[][] getTablaFiltro = getTablaFiltro();
				              String[] fila = new String[6];
				                              for(int i=0; i<getTablaFiltro.length; i++){
				                                      fila[0] = getTablaFiltro[i][0]+"";
				                                      fila[1] = getTablaFiltro[i][1]+"";
				                                      fila[2] = getTablaFiltro[i][2]+"";
				                                      fila[3] = getTablaFiltro[i][3]+"";
				                                      fila[4] = getTablaFiltro[i][4]+"";
				                                      fila[5] = "";
				                                      modeloFiltro.addRow(fila);
				                              }
					          Object[][] getTablaAsignada = getTablaFiltroAsignado();
					              String[] filaAsignada = new String[7];
					                              for(int j=0; j<getTablaAsignada.length; j++){
						                            	  filaAsignada[0] = getTablaAsignada[j][0]+"";
						                            	  filaAsignada[1] = getTablaAsignada[j][1]+"";
						                            	  filaAsignada[2] = getTablaAsignada[j][2]+"";
						                            	  filaAsignada[3] = getTablaAsignada[j][3]+"";
						                            	  filaAsignada[4] = getTablaAsignada[j][4]+"";
						                            	  filaAsignada[5] = getTablaAsignada[j][5]+"";
						                            	  filaAsignada[6] = getTablaAsignada[j][6]+"";
					                                      modeloFiltroAsignado.addRow(filaAsignada);
					                              }
					         cmbEstablecimiento.setSelectedIndex(0);
								JOptionPane.showMessageDialog(null, "El registro se a eliminado correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
					}else{
						JOptionPane.showMessageDialog(null, "El registro no se a podido remover","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
			}else{
				JOptionPane.showMessageDialog(null, "Seleccione el registro que desea remover","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	};
	
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
						if(horario_temporada.guardar(tabla_guardar(),fechaIn,fechaFin,txtHorarioTemporada.getText())){
							
							  while(tablaFiltro.getRowCount()>0){
	                              modeloFiltro.removeRow(0);
	                      }
							  
							  while(tablaFiltroAsignado.getRowCount()>0){
	                                modeloFiltroAsignado.removeRow(0);
	                      }
							    
							  Object[][] getTablaFiltro = getTablaFiltro();
					              String[] fila = new String[6];
					                              for(int i=0; i<getTablaFiltro.length; i++){
					                                      fila[0] = getTablaFiltro[i][0]+"";
					                                      fila[1] = getTablaFiltro[i][1]+"";
					                                      fila[2] = getTablaFiltro[i][2]+"";
					                                      fila[3] = getTablaFiltro[i][3]+"";
					                                      fila[4] = getTablaFiltro[i][4]+"";
					                                      fila[5] = "";
					                                      modeloFiltro.addRow(fila);
					                              }
					                              
					                          
					          Object[][] getTablaAsignada = getTablaFiltroAsignado();
					              String[] filaAsignada = new String[7];
					                              for(int j=0; j<getTablaAsignada.length; j++){
						                            	  filaAsignada[0] = getTablaAsignada[j][0]+"";
						                            	  filaAsignada[1] = getTablaAsignada[j][1]+"";
						                            	  filaAsignada[2] = getTablaAsignada[j][2]+"";
						                            	  filaAsignada[3] = getTablaAsignada[j][3]+"";
						                            	  filaAsignada[4] = getTablaAsignada[j][4]+"";
						                            	  filaAsignada[5] = getTablaAsignada[j][5]+"";
						                            	  filaAsignada[6] = getTablaAsignada[j][6]+"";
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

		Object[][] matriz = new Object[tablaFiltro.getRowCount()][5];
		for(int i=0; i<tablaFiltro.getRowCount(); i++){
			
				matriz[i][0] = Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim());
				matriz[i][1] = modeloFiltro.getValueAt(i,2).toString().trim();
				matriz[i][2] = modeloFiltro.getValueAt(i,3).toString().trim();
//				matriz[i][3] = modeloFiltro.getValueAt(i,4).toString().trim();
				matriz[i][3] = Boolean.parseBoolean(modeloFiltro.getValueAt(i,5).toString().trim());
		}
		return matriz;
	}
	
	public String valida_error(){
		String error="";
		
			String fechaIn = txtFechaInicial.getDate()+"";
			String fechaFin = txtFechaFinal.getDate()+"";
			
			if(fechaIn.equals("null"))			error+= "Fecha Inicial\n";
			if(fechaFin.equals("null"))			error+= "Fecha Final\n";
			
			if(txtHorarioTemporada.getText().equals(""))	error+= "Horario Temporal\n";
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
	
	public void update_de_horarios_vencidos(){
		String todos = "exec sp_update_status_de_horarios_de_temporada_que_expiraron ";
		PreparedStatement pstmt = null;
		Connection con = new Connexion().conexion();
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(todos);
		
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}
	
   	public Object[][] getTablaFiltro(){
   		update_de_horarios_vencidos();
   		
		String todos = "exec sp_select_filtro_horarios_temporada";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery(todos);
			
			MatrizFiltro = new Object[getFilas(todos)][6];
			int i=0;
			while(rs.next()){
				int folio = rs.getInt(1);
				MatrizFiltro[i][0] = folio+"  ";
				MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltro[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltro[i][4] = "   "+rs.getString(5).trim();
				MatrizFiltro[i][5] = "";
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
			
			MatrizFiltroAsignada = new Object[getFilas(todos)][7];
			int i=0;
			while(rs.next()){
				int folio = rs.getInt(1);
				MatrizFiltroAsignada[i][0] = folio+"  ";
				MatrizFiltroAsignada[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltroAsignada[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltroAsignada[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltroAsignada[i][4] = "   "+rs.getString(5).trim();
				MatrizFiltroAsignada[i][5] = "   "+rs.getString(6).trim();
				MatrizFiltroAsignada[i][6] = "   "+rs.getString(7).trim();
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
	
//	filtro de horario para asignarcelo al un empleado temporalmente
	public class Filtro_Horario_Temporal_Empleado extends JDialog
	{
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JTextField txtNombre = new JTextField();
		JTextField txtFolioHorario = new JTextField();

		DefaultTableModel modelo = new DefaultTableModel(0,2)	{
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla = new JTable(modelo);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Filtro_Horario_Temporal_Empleado()
		{
			this.setModal(true);
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/nivG.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Filtro Horario"));	
			
			panel.add(getPanelTabla()).setBounds(20,50,800,400);
			panel.add(txtFolioHorario).setBounds(20,20,80,20);
			panel.add(txtNombre).setBounds(100,20,720,20);
			
			cont.add(panel);
			txtNombre.setToolTipText("Filtro");
			
			txtFolioHorario.setDocument(new JTextFieldLimit(9));
			
			trsfiltro = new TableRowSorter(modelo); 
			tabla.setRowSorter(trsfiltro);
			
			txtNombre.addKeyListener(opFiltroNombre);
			txtFolioHorario.addKeyListener(opFiltroFolio);
			
			agregar(tabla);
			
			this.setTitle("Filtro Horario");
			this.setSize(845,500);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		}
		int x=2;
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		        		
		    			int fila = tabla.getSelectedRow();
		    			Object folio =  tabla.getValueAt(fila, 0);
		    			Object horario =  tabla.getValueAt(fila, 1);
		    			
		    			
		    			txtHorarioTemporada.setText(horario+"");
		    			lblFolioHorarioTemporal.setText(folio+"");
		    			
		    			dispose();
		        	}
		        }
	        });
	    }
		
		KeyListener opFiltroFolio = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioHorario.getText(), 0));
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
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
	   	private JScrollPane getPanelTabla()	{		
			new Connexion();
			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);

			
			// Creamos las columnas.
			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMinWidth(80);
			tabla.getColumnModel().getColumn(0).setMinWidth(80);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre");
			tabla.getColumnModel().getColumn(1).setMinWidth(720);
			tabla.getColumnModel().getColumn(1).setMaxWidth(720);
			
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

			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery( "  select tb_horarios.folio,tb_horarios.nombre from tb_horarios");
				int folio;
				String nombre;
				
				while (rs.next())
				{ 
					folio= rs.getInt(1);
					nombre= rs.getString(2).trim();
					
				   String [] fila = new String[2];
				   fila[0] = folio+"";
				   fila[1] = nombre;
				   
				   modelo.addRow(fila); 
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 JScrollPane scrol = new JScrollPane(tabla);
			   
		    return scrol; 
		}
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Asignacion_Horario_Temporada().setVisible(true);
		}catch(Exception e){
			
		}
	}
}