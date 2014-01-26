package cuadrantes;


	import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Imprimir_Cuadrante;
import objetos.Reporte_de_Cuadrantes;
import reporte.Reporte_Impresion_de_Cuadrantes;



	@SuppressWarnings("serial")
	public class Cat_Reporte_Cuadrantes_Impresion_de_Cuadrantes_Usuario extends JFrame{
				
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			int folio_empleado=0;
			DefaultTableModel modeloFiltro = new DefaultTableModel(new Obj_Imprimir_Cuadrante().Obj_Obtener_Empleados_Cuadrantes(),
		            new String[]{"Folio", "Nombre","Establecimiento","Puesto","Cuadrante",""}
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
			
			JTextField txtFolio = new JTextField();
			JTextField txtNombre_Completo = new JTextField();
			
			JButton btnGenerar = new JButton(" Imprimir Actividades del Cuadrante de Hoy");
			JButton btnGenerarreportecaptura = new JButton(" Imprimir Captura del Cuadrante de Hoy");
			JButton btnGenerarcaptura7= new JButton(" Imprimir Captura del Cuadrante 7 Dias");
			
			JButton btnGenerarAsistenciaH = new JButton(" Imprimir Movimientos del Checador Hoy");
			JButton btnGenerarAsistenciaH7 = new JButton(" Imprimir Movimientos del Checador 7 Dias");
			
			
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			
			public Cat_Reporte_Cuadrantes_Impresion_de_Cuadrantes_Usuario()	{
				
				
				
				setTitle("Reportes Personales Cuadrantes y Checador");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion de Empleado Para Impresion"));
				trsfiltro = new TableRowSorter(modeloFiltro); 
				tablaFiltro.setRowSorter(trsfiltro);  
				
				setSize(1024,425);
				setResizable(false);
				setLocationRelativeTo(null);
				campo.add(scroll).setBounds(10,43,1000,300);
				campo.add(txtFolio).setBounds(10,20,38,20);
				campo.add(txtNombre_Completo).setBounds(54,20,300,20);
				campo.add(btnGenerar).setBounds(10,360,280,20);
				campo.add(btnGenerarreportecaptura).setBounds(385,360,280,20);
				campo.add(btnGenerarcaptura7).setBounds(730,360,280,20);
				
				
				
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(300);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(300);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(190);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(190);
				tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(180);
				tablaFiltro.getColumnModel().getColumn(3).setMinWidth(180);
				tablaFiltro.getColumnModel().getColumn(4).setMaxWidth(240);
				tablaFiltro.getColumnModel().getColumn(4).setMinWidth(240);
				tablaFiltro.getColumnModel().getColumn(5).setMaxWidth(50);
				tablaFiltro.getColumnModel().getColumn(5).setMinWidth(50);
				
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
				btnGenerar.addActionListener(opAgregar);
				btnGenerarreportecaptura.addActionListener(opReporteCaptura);
				btnGenerarcaptura7.addActionListener(opReporteCaptura7);
				
				
			}
			
			ActionListener opAgregar = new ActionListener() {
				@SuppressWarnings({ })
				public void actionPerformed(ActionEvent arg0) {
					
					txtFolio.setText("");
					txtNombre_Completo.setText("");
					
					if(tablaFiltro.isEditing()){
						tablaFiltro.getCellEditor().stopCellEditing();
					}

					if(valida_cantidad_seleccion ()==1){

					  		new Reporte_Impresion_de_Cuadrantes(folio_empleado);
 
					}
					else{JOptionPane.showMessageDialog(null,"Debe de Seleccionar Un Empleado","Aviso",JOptionPane.NO_OPTION);
					}				
				}
				
			};
			
			ActionListener opReporteCaptura = new ActionListener() {
				@SuppressWarnings({ })
				public void actionPerformed(ActionEvent arg0) {
					
					txtFolio.setText("");
					txtNombre_Completo.setText("");
					
					if(tablaFiltro.isEditing()){
						tablaFiltro.getCellEditor().stopCellEditing();
					}

					if(valida_cantidad_seleccion ()==1){


//REVISAR PARA BORRAR EL OBJETO Y EL PROCEDIMIENTO						
//						if (new Obj_Imprimir_Cuadrante().Obj_Imprimir_Cuadrante_Update_Folio(folio_empleado)) {

					  		
							new Reporte_de_Cuadrantes("scoi", "scoif",
									   0, "0",
									1,"(''" +folio_empleado+"'')",
									0,"0",
									0,"0",
									0,"0",
									0,"0",
									0,"0",
									0);
//					}
					
					}
					else{JOptionPane.showMessageDialog(null,"Debe de Seleccionar Un Empleado","Aviso",JOptionPane.NO_OPTION);
					}				
				}
			};
			ActionListener opReporteCaptura7 = new ActionListener() {
				@SuppressWarnings({ })
				public void actionPerformed(ActionEvent arg0) {
					
					txtFolio.setText("");
					txtNombre_Completo.setText("");
					
					if(tablaFiltro.isEditing()){
						tablaFiltro.getCellEditor().stopCellEditing();
					}

					if(valida_cantidad_seleccion()==1){

								new Reporte_de_Cuadrantes("scoi7", "scoif7",
										   0, "0",
										1,"(''" +folio_empleado+"'')",
										0,"0",
										0,"0",
										0,"0",
										0,"0",
										0,"0",
										0);
						}else{
								JOptionPane.showMessageDialog(null,"Debe de Seleccionar Un Empleado","Aviso",JOptionPane.NO_OPTION);
							}
				}
				
			};
			
		public int valida_cantidad_seleccion (){
			int i=0;
			
			for (int y=0; y<tablaFiltro.getRowCount(); y=y+1){
				if(Boolean.parseBoolean(modeloFiltro.getValueAt(y,5).toString().trim())){
					i=i+1;
					folio_empleado= Integer.valueOf(modeloFiltro.getValueAt(y, 0).toString());
				}
			}
			
			return i;	
		}
		
		public  int [] Obtener_empleados_seleccionados (){
			int [] vector_seleccionados=new int[1] ;
			int i=0;
			for (int y=0; y<tablaFiltro.getRowCount(); y=y+1){
				if(Boolean.parseBoolean(modeloFiltro.getValueAt(y,5).toString().trim()) == true){
					vector_seleccionados[i]=Integer.parseInt(modeloFiltro.getValueAt(y,0).toString().trim());
					i++;
				}
			}
			
			return vector_seleccionados;
			
		}
		public static  void main(String []arg){
		new Cat_Reporte_Cuadrantes_Impresion_de_Cuadrantes_Usuario().setVisible(true);	
			
		}
}

	
