package catalogos;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import objetos.JTextFieldLimit;
import objetos.Obj_Nivel_Critico;

@SuppressWarnings("serial")
public class Cat_Actividades_Por_Proyecto extends JFrame{
	Container con = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnNuevo = new JButton("Nuevo");
	
	JButton btnderecha = new JButton(new ImageIcon("Iconos/right_icon&16.png"));
	JButton btnizquierda = new JButton(new ImageIcon("Iconos/left_icon&16.png"));
	
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnEditar = new JButton("Editar");
	JButton btnGuardar = new JButton("Guardar");
	
	JButton btnAgregar = new JButton("Agregar");
	JButton btnQuitar = new JButton("Quitar");
	
	JTextField txtFolioProyecto = new JTextField();
	JTextField txtProyecto = new JTextField();
	
	JTextArea txaDescripcion = new JTextArea(4,4);
	JScrollPane Descripcion = new JScrollPane(txaDescripcion);

	JCheckBox  chbStatus = new JCheckBox("Status",true);

	String nivel_critico[] = new Obj_Nivel_Critico().Combo_Nivel_Critico();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbJefatura = new JComboBox(nivel_critico);
	
	DefaultTableModel tabla_model = new DefaultTableModel(null,
            new String[]{"Folio", "Descripcion","Porcentaje"}
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
//        	 	case 3 : 
//        	 		if(Integer.parseInt(tabla_model.getValueAt(fila,0).toString().trim()) == 1 || Integer.parseInt(tabla_model.getValueAt(fila,0).toString().trim()) == 2){
//        	 			return false;
//        	 		}else{
//        	 			if(Boolean.parseBoolean(tabla_model.getValueAt(fila,3).toString()) == true){
//        	 				tabla_model.setValueAt("00:00",fila,4);
//        	 				tabla_model.setValueAt("00:00",fila,5);
//            	 		}
//	        	 		return true;
//        	 		}
//        	 	case 4 :
//        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,3).toString()) == true){
//        	 			return true;
//        	 		}
//        	 		return false;
//        	 	case 5 : 
//        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,3).toString()) == true){
//        	 			return true;
//        	 		}
//        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	JTable tabla = new JTable(tabla_model);
	
	JScrollPane panel_scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	public Cat_Actividades_Por_Proyecto(){
		
		int x=30;	int y=40; 
		
		panel.add(new JLabel("Folio: ")).setBounds( x, y, 60, 20);
		panel.add(txtFolioProyecto).setBounds( x+70, y, 80, 20);
		panel.add(chbStatus).setBounds( x+230, y, 80, 20);
		
		
		panel.add(new JLabel("Proyecto: ")).setBounds( x, y+=25, 60, 20);
		panel.add(txtProyecto).setBounds( x+70, y, 320, 20);
		
		panel.add(new JLabel("Descripcion: ")).setBounds( x, y+=25, 80, 20);
		panel.add(Descripcion).setBounds( x+70, y, 320, 280);
		
		panel.add(new JLabel("Nivel Critico: ")).setBounds( x, y+=290, 100, 20);
		panel.add(cmbJefatura).setBounds( x+70, y, 320, 20);
		
		panel.add(panel_scroll).setBounds( 440, 40, 700, 380);
		
		txaDescripcion.setLineWrap(true);
		txaDescripcion.setDocument(new JTextFieldLimit(400));
		
		this.con.add(panel);
		this.setSize(1200,500);
	}
	
	public static void main (String [] arg){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Actividades_Por_Proyecto().setVisible(true);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
