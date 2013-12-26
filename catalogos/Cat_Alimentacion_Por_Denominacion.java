package catalogos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import objetos.Obj_Alimentacion_Denominacion;
import objetos.Obj_Alimentacion_Por_Denominacion;

//guardar denominaciones
@SuppressWarnings("serial")
public class Cat_Alimentacion_Por_Denominacion extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtAsignacion = new JTextField("as");
//	JTextField txtNombre_Completo = new JTextField();
//	JButton btn_refrescar= new JButton("Refrescar");
	
	public JToolBar menu_toolbar = new JToolBar();
	JButton btn_guardar= new JButton(new ImageIcon("Iconos/save_icon&16.png"));
	
	JLabel lblEmpleado = new JLabel("MARCO ANTONIO BODART GUZMAN");
	JDateChooser txtFecha = new JDateChooser();
	JTextField txtTotal = new JTextField();
	
	DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Alimentacion_Por_Denominacion().get_tabla_model(), new String[]{"Folio", "Denominacion","# Denominacion", "Valor", "$ Cantidad"}) {
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
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
        	 	case 2 : return false; 
        	 	case 3 : return false; 
        	 	case 4 :
        	 		float suma = 0;
	    			for(int i=0; i<tabla.getRowCount(); i++){
	    				if(tabla_model.getValueAt(i,4).toString().length() == 0){
	    					suma = suma + 0;
	    				}else{
	    					suma += (Float.parseFloat(tabla_model.getValueAt(i,4).toString()))*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
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
	
	public Cat_Alimentacion_Por_Denominacion(){
		
		Constructor();
	}
	
	public void Constructor(){
		this.setTitle("Alimentación de Denominaciones");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/captura_nomina_icon&16.png"));
		this.txtFecha.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		lblEmpleado.setForeground(Color.GRAY);
		
		this.panel.add(menu_toolbar).setBounds(0,0,150,25);
		this.panel.add(lblEmpleado).setBounds(30,35,350,20);
		this.panel.add(new JLabel("Fecha: ")).setBounds(420,35,100,20);
		this.panel.add(txtFecha).setBounds(460,35,90,20);
		this.panel.add(txtAsignacion).setBounds(560,35,110,20);
		
		this.panel.add(scroll_tabla).setBounds(20,60,730,420);
		
		this.panel.add(new JLabel("Total de Cantidades:")).setBounds(470,485,100,20);
		this.panel.add(txtTotal).setBounds(580,485,90,20);
		
		this.menu_toolbar.add(btn_guardar);
		this.menu_toolbar.setEnabled(false);
		this.txtAsignacion.setEditable(false);
		this.txtTotal.setEditable(false);
		
		this.init_tabla();
		
		this.cont.add(panel);
		this.btn_guardar.addActionListener(op_guardar);
		
		this.tabla.addKeyListener(op_key);
		
		this.setSize(780,550);
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
				
				if(tabla_model.getValueAt(i,4).toString().equals("")){
					suma = suma + 0;
				}else{
					
					if(isNumeric(tabla_model.getValueAt(i,4).toString().trim())){
    					suma += Float.parseFloat(tabla_model.getValueAt(i,4).toString())*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
					}else{
						JOptionPane.showMessageDialog(null, "La nomina en el establecimiento "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
						tabla_model.setValueAt("", i, 4);
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
				txtTotal.setText("$  0.0");
				JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				
					if(txtFecha.getDate()==null){
						JOptionPane.showMessageDialog(null, "La Fecha es Requerida","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}else{
					
					if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de Denominaciones?") == 0){
						Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
						
						Alim_Denom.setAsignacion(txtAsignacion.getText().trim());
						Alim_Denom.setEmpleado(lblEmpleado.getText());
						Alim_Denom.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
						
						if(Alim_Denom.guardar(tabla_guardar())){
							JOptionPane.showMessageDialog(null, "La tabla Denominaciones se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							txtTotal.setText("$  0.0");
							JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}else{
						return;
					}
				}
			}
		}
	};
	
	private Object[][] tabla_guardar(){
		Object[][] matriz = new Object[tabla.getRowCount()][5];
		for(int i=0; i<tabla.getRowCount(); i++){
			
			matriz[i][0] = tabla_model.getValueAt(i,0).toString().trim();
			matriz[i][1] = tabla_model.getValueAt(i, 1).toString().trim();
			matriz[i][2] = tabla_model.getValueAt(i, 2).toString().trim();
			matriz[i][3] = tabla_model.getValueAt(i, 3).toString().trim();
			
			if(tabla_model.getValueAt(i,4).toString().trim().length() == 0){
				matriz[i][4] = Float.parseFloat("0"); 
			}else{
				matriz[i][4] = Float.parseFloat(tabla_model.getValueAt(i,4).toString().trim());
			}
		}
		return matriz;
	}
	
	private String valida_tabla(){
		String error = "";
		for(int i=0; i<tabla.getRowCount(); i++){
			try{
				if(!isNumeric(tabla_model.getValueAt(i,4).toString())){
					error += "   La celda de la columna Cantidad no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
					tabla_model.setValueAt("",i, 4);
				}
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		return error;
	}
	
	public void init_tabla(){
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(120);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(290);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(290);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(120);		
    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(3).setMinWidth(120);
    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(100);
    	this.tabla.getColumnModel().getColumn(4).setMinWidth(100);
    	
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
					case 0 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					case 3 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					case 4 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
				}
			return lbl; 
			} 
		}; 

		this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(2).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
		
		float suma = 0;
		for(int i=0; i<tabla.getRowCount(); i++){
			if(tabla_model.getValueAt(i,4).toString().length() == 0){
				suma = suma + 0;
			}else{
				suma += Float.parseFloat(tabla_model.getValueAt(i,4).toString())*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
			}
		}
		txtTotal.setText("$  "+suma);
    }
	
    private static boolean isNumeric(String cadena){
    	try {
    		if(cadena.equals("")){
        		return true;
    		}else{
    			Float.parseFloat(cadena);
        		return true;
    		}
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }

}