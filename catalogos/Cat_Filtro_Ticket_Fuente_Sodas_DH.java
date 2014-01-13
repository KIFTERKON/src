package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import objetos.Obj_Captura_Fuente_Sodas;
import objetos.Obj_Filtro_Ticket_Fuente_Sodas;

//FILTRO DE TICKETS QUE NO SE LE AN DESCONTADO AL EMPLEADO SELECCIONADO	
	 	@SuppressWarnings("serial")
		public class Cat_Filtro_Ticket_Fuente_Sodas_DH extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			int folio_empleado=0;
			
			int bandera_capturado_auxf;
			DefaultTableModel modeloFiltro = new DefaultTableModel(null,
		            new String[]{"Ticket", "Importe","Fecha","AF","DH"}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.Integer.class,
			    	java.lang.String.class,
			    	java.lang.String.class,
			    	java.lang.Boolean.class,
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
		        	 	case 3 : if(Boolean.valueOf(tablaFiltro.getValueAt(fila, 4).toString())!=true){return false;}else{return true;}
		        	 	case 4 : return false;
		        	 		
		        	 } 				
		 			return false;
		 		}
			};
			
			
			JTable tablaFiltro = new JTable(modeloFiltro);
		    JScrollPane scroll = new JScrollPane(tablaFiltro);
			
			JTextField txtFolio = new JTextField();
			JTextField txtNombre_Completo = new JTextField();
			
			JButton btnAgregar = new JButton(new ImageIcon("Iconos/agregar.png"));
			
			public Cat_Filtro_Ticket_Fuente_Sodas_DH(int folio,String empleado) {
				
				this.setModal(true);
				setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
				setTitle("Tabla De Ticket Por Empleado (Desarrollo humano)");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Ticket Por Empleado"));
				
				this.txtFolio.setEditable(false);
				this.txtNombre_Completo.setEditable(false);
				
				folio_empleado=folio;
				
				txtFolio.setText(folio_empleado+"");
				txtNombre_Completo.setText(empleado);
				
				buscar_tabla(folio_empleado);
				
				btnAgregar.setToolTipText("Agregar");
				
				campo.add(scroll).setBounds(15,43,414,360);
				
				campo.add(txtFolio).setBounds(15,20,40,20);
				campo.add(txtNombre_Completo).setBounds(56,20,280,20);
				campo.add(btnAgregar).setBounds(340,20,50,20);
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(100);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(100);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(150);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(150);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(80);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(80);
				tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(3).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(4).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(4).setMinWidth(40);
				
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(186,143,73));
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,4).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(166,160,131));
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(186,143,73));
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,4).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(166,160,131));
								}
								if(table.getSelectedRow() == row){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(186,143,73));
								}
								((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
								break;
							case 2: 
								componente = new JLabel(value == null? "": value.toString());
								if(row %2 == 0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(186,143,73));
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,4).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(166,160,131));
								}
								if(table.getSelectedRow() == row){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(186,143,73));
								}
								((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
								break;
							case 3: 
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(186,143,73));
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,4).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(166,160,131));
								}
								if(table.getSelectedRow() == row){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(186,143,73));
								}
								((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
								break;
							case 4: 
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(186,143,73));
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,4).toString())){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(166,160,131));
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
				
				btnAgregar.addActionListener(opAgregar);
				
				setSize(455,450);
				setResizable(false);
				setLocationRelativeTo(null);
			}
			
			ActionListener opAgregar = new ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent arg0) {
					
					Obj_Filtro_Ticket_Fuente_Sodas ticket = new Obj_Filtro_Ticket_Fuente_Sodas();
					
						if(tablaFiltro.isEditing()){
				 			tablaFiltro.getCellEditor().stopCellEditing();
						}
					
					if(ticket.guardar_dh(tabla_guardar(), Integer.parseInt(txtFolio.getText()), txtNombre_Completo.getText())){

						//tabla de tickets--------------------------------
						while(tablaFiltro.getRowCount()>0){
							modeloFiltro.removeRow(0);
					    }
						buscar_tabla(folio_empleado);
						//------------------------------------------------
						//tabla de tickets--------------------------------
						while(new Cat_Filtro_Empleados_Con_Saldo_En_Fuente_De_Sodas_DH().tabla_model.getRowCount()>0){
							new Cat_Filtro_Empleados_Con_Saldo_En_Fuente_De_Sodas_DH().tabla_model.removeRow(0);
					    }
						buscar_tabla_empleado_con_pendiente_en_fuente_sodas();
						//------------------------------------------------
						
						if(tablaFiltro.getRowCount()==0){
							dispose();
						}
					}else{
						JOptionPane.showMessageDialog(null, "Error al guardar", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}
				}
			};
			
			public void buscar_tabla(int folio_empleado){
				
				try {
					String[][] tabla = new Obj_Captura_Fuente_Sodas().tabla_dh(folio_empleado);
										
					for(int i=0; i<tabla.length; i++){
						 		Object[] dom = new Object[5];
						 		
						 		dom[0] = tabla[i][0]+"   ";
						 		dom[1] = "   "+tabla[i][1];                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
						 		dom[2] = tabla[i][2];
						 		dom[3] = "";
						 		dom[4]=Integer.valueOf(tabla[i][3])==1?false:true;
						 		modeloFiltro.addRow(dom);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			@SuppressWarnings("static-access")
			public void buscar_tabla_empleado_con_pendiente_en_fuente_sodas(){
				Object [][] lista_tabla_empleados = new Cat_Filtro_Empleados_Con_Saldo_En_Fuente_De_Sodas_DH().get_tabla();
				 String[] fila = new String[9];
				         for(int i=0; i<lista_tabla_empleados.length; i++){
				                 fila[0] = lista_tabla_empleados[i][0]+"";
				                 fila[1] = lista_tabla_empleados[i][1]+"";
				                 fila[2] = lista_tabla_empleados[i][2]+"";
				                 fila[3] = lista_tabla_empleados[i][3]+"";
				                 fila[4] = lista_tabla_empleados[i][4]+"";
				                 new Cat_Filtro_Empleados_Con_Saldo_En_Fuente_De_Sodas_DH().tabla_model.addRow(fila);
				         }
			}
			
			private Object[][] tabla_guardar(){

				Object[][] matriz = new Object[tablaFiltro.getRowCount()][4];
				for(int i=0; i<tablaFiltro.getRowCount(); i++){
						
						matriz[i][0] = modeloFiltro.getValueAt(i,0).toString().trim();
						matriz[i][1] = modeloFiltro.getValueAt(i,1).toString().trim();
						matriz[i][2] = modeloFiltro.getValueAt(i,2).toString().trim();
						matriz[i][3] = Boolean.parseBoolean(modeloFiltro.getValueAt(i,3).toString().trim());
				}
				return matriz;
			}
}
	