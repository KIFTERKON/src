package catalogos;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Cat_Plantilla_Tabla_Cuadrante {
	
	@SuppressWarnings("serial")
	public DefaultTableModel modelPlantillaLibre = new DefaultTableModel(null,
			new String[]{"Orden", "Actividad", "Respuesta", "Comentarios" }){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Object.class,
	    	java.lang.Object.class
         };
	     
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return true; 
        	 	case 3 : return true;
        	 } 				
 			return false;
         }
         
	};
	
	public JTable tablaPlantillaLibre = new JTable(modelPlantillaLibre);
	public JScrollPane scrollLibre = new JScrollPane(tablaPlantillaLibre,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	@SuppressWarnings("serial")
	public DefaultTableModel modelPlantillaMultiple = new DefaultTableModel(null,
			new String[]{"Orden", "Actividad", "Respuesta","Comentarios" }){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Object.class,
	    	java.lang.Object.class
         };
	     
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return true; 
        	 	case 3 : return true;

        	 } 				
 			return false;
         }
         
	};
	
	public JTable tablaPlantillaMultiple = new JTable(modelPlantillaMultiple);
	public JScrollPane scrollMultiple = new JScrollPane(tablaPlantillaMultiple,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	public Cat_Plantilla_Tabla_Cuadrante(){
		
		this.tablaPlantillaLibre.getTableHeader().setReorderingAllowed(false);
		this.tablaPlantillaLibre.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaPlantillaLibre.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaPlantillaLibre.getColumnModel().getColumn(1).setMaxWidth(260);
		this.tablaPlantillaLibre.getColumnModel().getColumn(1).setMinWidth(260);
		this.tablaPlantillaLibre.getColumnModel().getColumn(2).setMaxWidth(270);
		this.tablaPlantillaLibre.getColumnModel().getColumn(2).setMinWidth(270);
		this.tablaPlantillaLibre.getColumnModel().getColumn(3).setMaxWidth(270);
		this.tablaPlantillaLibre.getColumnModel().getColumn(3).setMinWidth(270);
		
		TableCellRenderer renderPlantillaLibre = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
				
				if(table.getSelectedRow() == row){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(186,143,73));
				}
				
				switch(column){
					case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 3 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 
		for(int i=0; i<tablaPlantillaLibre.getColumnCount(); i++){
			this.tablaPlantillaLibre.getColumnModel().getColumn(i).setCellRenderer(renderPlantillaLibre); 
		}
		
		this.tablaPlantillaMultiple.getTableHeader().setReorderingAllowed(false);
		this.tablaPlantillaMultiple.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaPlantillaMultiple.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaPlantillaMultiple.getColumnModel().getColumn(1).setMaxWidth(350);
		this.tablaPlantillaMultiple.getColumnModel().getColumn(1).setMinWidth(350);
		this.tablaPlantillaMultiple.getColumnModel().getColumn(2).setMaxWidth(150);
		this.tablaPlantillaMultiple.getColumnModel().getColumn(2).setMinWidth(150);
		this.tablaPlantillaMultiple.getColumnModel().getColumn(3).setMaxWidth(300);
		this.tablaPlantillaMultiple.getColumnModel().getColumn(3).setMinWidth(300);
		
		TableCellRenderer renderPlantillaMultiple = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
				
				if(table.getSelectedRow() == row){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(186,143,73));
				}
				
				switch(column){
					case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 3 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 
		for(int i=0; i<tablaPlantillaMultiple.getColumnCount(); i++){
			this.tablaPlantillaMultiple.getColumnModel().getColumn(i).setCellRenderer(renderPlantillaMultiple); 
		}
		
		this.tablaPlantillaMultiple.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.tablaPlantillaLibre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
}
