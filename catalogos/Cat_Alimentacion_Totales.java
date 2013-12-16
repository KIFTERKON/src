package catalogos;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import objetos.Obj_Alimentacion_Totales;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Totales extends Cat_Root {
	
	JTextField txtTotal = new JTextField();
	
	DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Alimentacion_Totales().get_tabla_model(), new String[]{"Establecimiento", "Nómina"}) {
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
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
        	 	case 1 :
        	 		float suma = 0;
	    			for(int i=0; i<tabla.getRowCount(); i++){
	    				if(tabla_model.getValueAt(i,1).toString().length() == 0){
	    					suma = suma + 0;
	    				}else{
	    					suma += Float.parseFloat(tabla_model.getValueAt(i,1).toString());
	    				}
	    				
	    			}
	    			txtTotal.setText("$  "+suma);
        	 		return true; 
        	 } 				
 			return false;
 		}
	};
	
	JTable tabla = new JTable(tabla_model);
	JScrollPane scroll_tabla = new JScrollPane(tabla);
	
	public Cat_Alimentacion_Totales(){
		this.setTitle("Alimentación de Totales de Nomina");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/captura_nomina_icon&16.png"));
		
		this.txtFolio.setVisible(false);
		this.txtNombre_Completo.setVisible(false);
		this.btn_refrescar.setVisible(false);
		
		this.panel.add(new JLabel("Total de Cantidades:")).setBounds(220,5,100,20);
		this.panel.add(txtTotal).setBounds(330,5,90,20);
		
		this.txtTotal.setEditable(false);
		
		this.panel.add(scroll_tabla).setBounds(20,30,400,420);
		
		this.cont.add(panel);
		
		this.init_tabla();
		
		this.btn_guardar.addActionListener(op_guardar);
		this.tabla.addKeyListener(op_key);
		
		this.setSize(450,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	KeyListener op_key = new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			float suma = 0;
			for(int i=0; i<tabla.getRowCount(); i++){
				
				if(tabla_model.getValueAt(i,1).toString().equals("")){
					suma = suma + 0;
				}else{
					
					if(isNumeric(tabla_model.getValueAt(i,1).toString().trim())){
						suma = suma + Float.parseFloat(tabla_model.getValueAt(i,1).toString().trim());
					}else{
						JOptionPane.showMessageDialog(null, "La nomina en el establecimiento "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
						tabla_model.setValueAt("", i, 1);
						return;
						
					}
				}
			}
			txtTotal.setText("$  "+suma);
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	
	ActionListener op_guardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
		
			if(valida_tabla() != ""){
				JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de bancos?") == 0){
					Obj_Alimentacion_Totales totales = new Obj_Alimentacion_Totales();
					if(totales.guardar(tabla_guardar())){
						JOptionPane.showMessageDialog(null, "La tabla bancos se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}else{
					return;
				}
			}
		}
		
	};
	
	private Object[][] tabla_guardar(){
		Object[][] matriz = new Object[tabla.getRowCount()][2];
		for(int i=0; i<tabla.getRowCount(); i++){
			matriz[i][0] = tabla_model.getValueAt(i,0).toString().trim();
			if(tabla_model.getValueAt(i,1).toString().trim().length() == 0){
				matriz[i][1] = Float.parseFloat("0"); 
			}else{
				matriz[i][1] = Float.parseFloat(tabla_model.getValueAt(i,1).toString().trim());
			}
		}
		return matriz;
	}
	
	private String valida_tabla(){
		String error = "";
		for(int i=0; i<tabla.getRowCount(); i++){
			try{
				if(!isNumeric(tabla_model.getValueAt(i,1).toString())){
					error += "   La celda de la columna Nómina no es un número en el [Establecimiento: "+tabla_model.getValueAt(i,0)+"]\t\n";
				}
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		return error;
	}
	
	public void init_tabla(){
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(300);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(300);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(90);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(90);
    	
		TableCellRenderer render = new TableCellRenderer() { 
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
					case 0 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
				}
			return lbl; 
			} 
		}; 

		this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		
		float suma = 0;
		for(int i=0; i<tabla.getRowCount(); i++){
			if(tabla_model.getValueAt(i,1).toString().length() == 0){
				suma = suma + 0;
			}else{
				suma += Float.parseFloat(tabla_model.getValueAt(i,1).toString());
			}
		}
		txtTotal.setText("$  "+suma);
    }
	
    private static boolean isNumeric(String cadena){
    	try {
//    		if(cadena.equals("")){
//    			return true;
//    		}else{
    			Float.parseFloat(cadena);
        		return true;
//    		}
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
}
