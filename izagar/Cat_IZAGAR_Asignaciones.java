package izagar;

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
import objetos.Obj_IZAGAR_Asignaciones_Liquidadas;
import com.toedter.calendar.JDateChooser;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_IZAGAR_Asignaciones extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	Object[][] MatrizFiltro ;
	Object[][] MatrizFiltroAsignada ;
	
	Object[][] getTablaFiltro = getTablaFiltro();
	DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
            new String[]{"Asignacion", "F. Cajero(a)","Nombre Cajera(o)","Total","Cod Estab","Establecimiento","Fecha de Asignacion","Fecha de Liquidacion",""}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
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
        	 	case 5 : return false;
        	 	case 6 : return false;
        	 	case 7 : return false;
        	 	case 8 : return true;
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
    

    
    JButton btnQuitar = new JButton("Quitar");
    
	Object[][] getTablaAsignada = getTablaFiltroAsignado();
	DefaultTableModel modeloFiltroAsignado = new DefaultTableModel(getTablaAsignada,
            new String[]{"Asignacion", "Cajero","Total","Establecimiento","Fecha Asignacion","Fecha Liquidacion","Fecha Guardado"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
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
	
	public Cat_IZAGAR_Asignaciones() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		setTitle("Asignaciones Liquidadas de los Establecimientos");
		campo.setBorder(BorderFactory.createTitledBorder("Asignaciones Liquidadas"));
		
		trsfiltro = new TableRowSorter(modeloFiltro); 
		tablaFiltro.setRowSorter(trsfiltro); 
		
		trsfiltroAsignado = new TableRowSorter(modeloFiltroAsignado); 
		tablaFiltroAsignado.setRowSorter(trsfiltroAsignado); 
		
		campo.add(txtFolio).setBounds(15,20,80,20);
		campo.add(txtNombre_Completo).setBounds(175,20,300,20);
		campo.add(cmbEstablecimiento).setBounds(545,20,220,20);
		campo.add(scroll).setBounds(15,43,1090,280);
		
		campo.add(btnAgregar).setBounds(688,330,80,20);
		campo.add(txtFolio_Asignado).setBounds(15,330,40,20);
		campo.add(txtNombre_Completo_Asignado).setBounds(56,330,350,20);
		campo.add(cmbEstablecimientoAsignado).setBounds(506,330,130,20);
		campo.add(scrollAsignado).setBounds(15,355,1090,250);
		
		campo.add(btnQuitar).setBounds(788,330,80,20);
		
		campo.add(new JLabel("De:")).setBounds(860, 20, 25, 20);
		campo.add(txtFechaInicial).setBounds(880, 20, 100, 20);
		campo.add(new JLabel("A:")).setBounds(985, 20, 25, 20);
		campo.add(txtFechaFinal).setBounds(1000, 20, 100, 20);
		
		cont.add(campo);
		
		tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(80);
		tablaFiltro.getColumnModel().getColumn(0).setMinWidth(80);
		tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(80);
		tablaFiltro.getColumnModel().getColumn(1).setMinWidth(80);
		tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(300);
		tablaFiltro.getColumnModel().getColumn(2).setMinWidth(300);
		tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(80);
		tablaFiltro.getColumnModel().getColumn(3).setMinWidth(80);
		tablaFiltro.getColumnModel().getColumn(4).setMaxWidth(80);
		tablaFiltro.getColumnModel().getColumn(4).setMinWidth(80);
		tablaFiltro.getColumnModel().getColumn(5).setMaxWidth(130);
		tablaFiltro.getColumnModel().getColumn(5).setMinWidth(130);
		tablaFiltro.getColumnModel().getColumn(6).setMaxWidth(130);
		tablaFiltro.getColumnModel().getColumn(6).setMinWidth(150);
		tablaFiltro.getColumnModel().getColumn(7).setMaxWidth(150);
		tablaFiltro.getColumnModel().getColumn(7).setMinWidth(150);
		tablaFiltro.getColumnModel().getColumn(8).setMaxWidth(30);
		tablaFiltro.getColumnModel().getColumn(8).setMinWidth(30);
		
		tablaFiltroAsignado.getColumnModel().getColumn(0).setMaxWidth(80);
		tablaFiltroAsignado.getColumnModel().getColumn(0).setMinWidth(80);
		tablaFiltroAsignado.getColumnModel().getColumn(1).setMaxWidth(300);
		tablaFiltroAsignado.getColumnModel().getColumn(1).setMinWidth(300);
		tablaFiltroAsignado.getColumnModel().getColumn(2).setMaxWidth(80);
		tablaFiltroAsignado.getColumnModel().getColumn(2).setMinWidth(80);
		tablaFiltroAsignado.getColumnModel().getColumn(3).setMaxWidth(130);
		tablaFiltroAsignado.getColumnModel().getColumn(3).setMinWidth(130);
		tablaFiltroAsignado.getColumnModel().getColumn(4).setMaxWidth(150);
		tablaFiltroAsignado.getColumnModel().getColumn(4).setMinWidth(150);
		tablaFiltroAsignado.getColumnModel().getColumn(5).setMaxWidth(150);
		tablaFiltroAsignado.getColumnModel().getColumn(5).setMinWidth(150);
		tablaFiltroAsignado.getColumnModel().getColumn(6).setMaxWidth(150);
		tablaFiltroAsignado.getColumnModel().getColumn(6).setMinWidth(150);

		
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
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
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
					case 7:
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
					case 8:
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
		tablaFiltro.getColumnModel().getColumn(5).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(6).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(7).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(8).setCellRenderer(render);
		
		tablaFiltroAsignado.getColumnModel().getColumn(0).setCellRenderer(render); 
		tablaFiltroAsignado.getColumnModel().getColumn(1).setCellRenderer(render); 
		tablaFiltroAsignado.getColumnModel().getColumn(2).setCellRenderer(render);
		tablaFiltroAsignado.getColumnModel().getColumn(3).setCellRenderer(render);
		tablaFiltroAsignado.getColumnModel().getColumn(4).setCellRenderer(render);
		tablaFiltroAsignado.getColumnModel().getColumn(5).setCellRenderer(render);
		tablaFiltroAsignado.getColumnModel().getColumn(6).setCellRenderer(render);
		
		

		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimiento.addActionListener(opFiltroChb);
		
		txtFolio_Asignado.addKeyListener(opFiltroFolioAsignado);
		txtNombre_Completo_Asignado.addKeyListener(opFiltroNombreAsignado);
		cmbEstablecimientoAsignado.addActionListener(opFiltroChbAsignado);
		
		btnAgregar.addActionListener(opAgregar);
		btnQuitar.addActionListener(opQuitar);
		
		setSize(1124,650);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	
	ActionListener opQuitar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(tablaFiltroAsignado.getSelectedRow()>=0){
				
					int filaSelecionada = tablaFiltroAsignado.getSelectedRow();
					
					String asignacion =  tablaFiltroAsignado.getValueAt(filaSelecionada, 0).toString().trim();
					
					
					Obj_IZAGAR_Asignaciones_Liquidadas remover = new Obj_IZAGAR_Asignaciones_Liquidadas();
					
					if(remover.borrar(asignacion)){
						
							  while(tablaFiltroAsignado.getRowCount()>0){
		                            modeloFiltroAsignado.removeRow(0);
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
	
		public void actionPerformed(ActionEvent arg0) {
			
			if(tablaFiltro.isEditing()){
	 			tablaFiltro.getCellEditor().stopCellEditing();
			}

			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			

			
			Obj_IZAGAR_Asignaciones_Liquidadas Guardado_Asignacion = new Obj_IZAGAR_Asignaciones_Liquidadas();

						if(Guardado_Asignacion.guardar_asignaciones(tabla_guardar())){
							
							  while(tablaFiltro.getRowCount()>0){
	                              modeloFiltro.removeRow(0);
	                      }
							  
							  while(tablaFiltroAsignado.getRowCount()>0){
	                                modeloFiltroAsignado.removeRow(0);
	                      }
							    
							  Object[][] getTablaFiltro = getTablaFiltro();
					              String[] fila = new String[9];
					                              for(int i=0; i<getTablaFiltro.length; i++){
					                                      fila[0] = getTablaFiltro[i][0]+"";
					                                      fila[1] = getTablaFiltro[i][1]+"";
					                                      fila[2] = getTablaFiltro[i][2]+"";
					                                      fila[3] = getTablaFiltro[i][3]+"";
					                                      fila[4] = getTablaFiltro[i][4]+"";
					                                      fila[5] = getTablaFiltro[i][5]+"";
					                                      fila[6] = getTablaFiltro[i][6]+"";
					                                      fila[7] = getTablaFiltro[i][7]+"";
					                                      fila[8] = "";
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
//					}else{
//						JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
//						return;
//					}
//			}
		}
	};
	///recorre la tabla para guardar completa los que tengan verdadero el boolean
	private Object[][] tabla_guardar(){

		Object[][] matriz = new Object[tablaFiltro.getRowCount()][9];
		for(int i=0; i<tablaFiltro.getRowCount(); i++){
			
				matriz[i][0] = modeloFiltro.getValueAt(i,0).toString().trim();
				matriz[i][1] = modeloFiltro.getValueAt(i,1).toString().trim();
				matriz[i][2] = modeloFiltro.getValueAt(i,2).toString().trim();
				matriz[i][3] = modeloFiltro.getValueAt(i,3).toString().trim();
				matriz[i][4] = modeloFiltro.getValueAt(i,4).toString().trim();
				matriz[i][5] = modeloFiltro.getValueAt(i,5).toString().trim();
				matriz[i][6] = modeloFiltro.getValueAt(i,6).toString().trim();
				matriz[i][7] = modeloFiltro.getValueAt(i,7).toString().trim();	
				matriz[i][8] = Boolean.parseBoolean(modeloFiltro.getValueAt(i,8).toString().trim());
		}
		return matriz;
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
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 2));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener opFiltroChb = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimiento.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimiento.getSelectedItem()+"", 4));
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
		String todos = "exec IZAGAR_select_asignaciones_liquidadas_todas ";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery(todos);
			
			MatrizFiltro = new Object[getFilasIZAGAR(todos)][9];
			int i=0;
			while(rs.next()){
				MatrizFiltro[i][0] = "   "+rs.getString(1).trim();
				MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltro[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltro[i][4] = "   "+rs.getString(5).trim();
				MatrizFiltro[i][5] = "   "+rs.getString(6).trim();
				MatrizFiltro[i][6] = "   "+rs.getString(7).trim()+"";
				MatrizFiltro[i][7] = "   "+rs.getString(8).trim()+"";
				MatrizFiltro[i][8] = "";
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltro; 
	}
   	
   	public Object[][] getTablaFiltroAsignado(){
		String todos = "exec IZAGAR_select_asignaciones_contables";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery(todos);
			
			MatrizFiltroAsignada = new Object[getFilasSCOI(todos)][7];
			int i=0;
			while(rs.next()){
				MatrizFiltroAsignada[i][0] = "   "+rs.getString(1).trim();
				MatrizFiltroAsignada[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltroAsignada[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltroAsignada[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltroAsignada[i][4] = "   "+rs.getString(5).trim()+"";
				MatrizFiltroAsignada[i][5] = "   "+rs.getString(6).trim()+"";
				MatrizFiltroAsignada[i][6] = "   "+rs.getString(7).trim()+"";
								i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltroAsignada; 
	}

   	public int getFilasIZAGAR(String qry){
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
	public int getFilasSCOI(String qry){
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
		public void keyTyped(KeyEvent e){ }
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
		}	
	};
	


	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_IZAGAR_Asignaciones().setVisible(true);
		}catch(Exception e){
			
		}
	}
}