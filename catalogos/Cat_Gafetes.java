package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
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

import objetos.Obj_Gafete;



@SuppressWarnings("serial")
public class Cat_Gafetes extends JFrame {
	
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		
		DefaultTableModel modeloFiltro = new DefaultTableModel(new Obj_Gafete().Obtener_Gafete(),
	            new String[]{"Folio", "Nombre","Establecimiento","Puesto",""}
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
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		JButton btnGenerar = new JButton("Genenerar");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		public Cat_Gafetes()	{
			setTitle("Filtro de Actividades");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Actividades"));
			trsfiltro = new TableRowSorter(modeloFiltro); 
			tablaFiltro.setRowSorter(trsfiltro);  
			
			
			campo.add(scroll).setBounds(15,43,880,760);
			
			campo.add(txtFolio).setBounds(15,20,48,20);
			campo.add(txtNombre_Completo).setBounds(64,20,340,20);
			campo.add(btnGenerar).setBounds(485,20,100,20);
			
			cont.add(campo);
			
			tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(50);
			tablaFiltro.getColumnModel().getColumn(0).setMinWidth(50);
			tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(340);
			tablaFiltro.getColumnModel().getColumn(1).setMinWidth(340);
			tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(200);
			tablaFiltro.getColumnModel().getColumn(2).setMinWidth(200);
			tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(220);
			tablaFiltro.getColumnModel().getColumn(3).setMinWidth(220);
			tablaFiltro.getColumnModel().getColumn(4).setMaxWidth(50);
			tablaFiltro.getColumnModel().getColumn(4).setMinWidth(50);
			
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
//			txtFolio.addKeyListener(opFiltroFolio);
//			txtNombre_Completo.addKeyListener(opFiltroNombre);
			
			btnGenerar.addActionListener(opAgregar);
			
			setSize(920,850);
			setResizable(false);
			setLocationRelativeTo(null);
			
		}
		
		ActionListener opAgregar = new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				
				txtFolio.setText("");
				txtNombre_Completo.setText("");
				
				if(tablaFiltro.isEditing()){
					tablaFiltro.getCellEditor().stopCellEditing();
				}
				if(valida_cantidad_seleccion ()==6){
					System.out.println("correcto");
					int[] prueba = Obtener_empleados_seleccionados();
					
					for(int j =0; j<prueba.length; j++){
						System.out.println(prueba[j]);
					}
				}
				else{
					Toolkit.getDefaultToolkit().beep();
					
					JOptionPane.showMessageDialog(null,"Solo Se Pueden Seleccionar Seis Empleados","Aviso",JOptionPane.NO_OPTION);
					
				}				
			}
			
		};
		
		 
		
		
	public int valida_cantidad_seleccion (){
		int i=0;
		for (int y=0; y<tablaFiltro.getRowCount(); y=y+1){
			if(Boolean.parseBoolean(modeloFiltro.getValueAt(y,4).toString().trim())){
				i=i+1;
			}
		}
		
		return i;	
	}
	
	public  int [] Obtener_empleados_seleccionados (){
		int [] vector_seleccionados=new int[6] ;
		int i=0;
		for (int y=0; y<tablaFiltro.getRowCount(); y=y+1){
			if(Boolean.parseBoolean(modeloFiltro.getValueAt(y,4).toString().trim()) == true){
				vector_seleccionados[i]=Integer.parseInt(modeloFiltro.getValueAt(y,0).toString().trim());
				i++;
			}
		}
		
		return vector_seleccionados;
		
	}
	
}
