package catalogos;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class Cat_Plantilla_Tabla extends JFrame{
	
	public DefaultTableModel modelPantilla = new DefaultTableModel(null,
            new String[]{"Orden", "Actividad","Nivel Crítico","","Hora Inicio","Hora Final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
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
        	 	case 4 :
        	 		if(Boolean.parseBoolean(modelPantilla.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(modelPantilla.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	public JTable tablaPlantilla = new JTable(modelPantilla);
	public JScrollPane scrollPlantilla = new JScrollPane(tablaPlantilla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	public Cat_Plantilla_Tabla(){
		this.tablaPlantilla.getTableHeader().setReorderingAllowed(false) ;
		this.tablaPlantilla.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaPlantilla.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaPlantilla.getColumnModel().getColumn(1).setMaxWidth(620);
		this.tablaPlantilla.getColumnModel().getColumn(1).setMinWidth(270);
		this.tablaPlantilla.getColumnModel().getColumn(2).setMaxWidth(145);
		this.tablaPlantilla.getColumnModel().getColumn(2).setMinWidth(145);
		this.tablaPlantilla.getColumnModel().getColumn(3).setMaxWidth(50);
		this.tablaPlantilla.getColumnModel().getColumn(3).setMinWidth(50);
		this.tablaPlantilla.getColumnModel().getColumn(4).setMaxWidth(80);
		this.tablaPlantilla.getColumnModel().getColumn(4).setMinWidth(80);
		this.tablaPlantilla.getColumnModel().getColumn(5).setMaxWidth(80);
		this.tablaPlantilla.getColumnModel().getColumn(5).setMinWidth(80);
		
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
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 5: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
				}
					
				return componente;
			} 
		}; 
	
		this.tablaPlantilla.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaPlantilla.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaPlantilla.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaPlantilla.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tablaPlantilla.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tablaPlantilla.getColumnModel().getColumn(5).setCellRenderer(render);
	}
	
	public void limpiarPlatilla(){
		while(tablaPlantilla.getRowCount() > 0)
			modelPantilla.removeRow(0);
	}
}
