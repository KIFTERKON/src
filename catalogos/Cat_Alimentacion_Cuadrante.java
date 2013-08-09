package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import SQL.Connexion;


import objetos.Obj_Alimentacion_Cuadrante;
import objetos.Obj_Usuario;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Cuadrante extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	/* OPCION LIBRE */
	DefaultTableModel modelLibre = new DefaultTableModel(null,
			new String[]{"Folio", "Actividad", "Respuesta", "Comentarios" }){
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
	
	JTable tablaLibre = new JTable(modelLibre);
	JScrollPane scrollLibre = new JScrollPane(tablaLibre,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	/* OPCION MULTIPLE */
	DefaultTableModel modelMultiple = new DefaultTableModel(null,
			new String[]{"Folio", "Actividad", "Respuesta","Comentarios" }){
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
	
	JTable tablaMultiple = new JTable(modelMultiple);
	JScrollPane scrollMultiple = new JScrollPane(tablaMultiple,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JTextField txtNombre_Completo 	= new JTextField();
	JTextField txtPuesto 			= new JTextField();
	JTextField txtEstablecimiento	= new JTextField();
	JTextField txtEquipo_Trabajo 	= new JTextField();
	JTextField txtJefatura 			= new JTextField();
	JTextField txtDia 				= new JTextField();
	JTextField txtFecha 			= new JTextField();
	JTextField txtCuadrante 		= new JTextField();
	
	@SuppressWarnings("rawtypes")
	JComboBox cmbMultiple = new JComboBox();
	
	JButton btnSalir = new JButton("Salir");
	JButton btnEditar = new JButton("Editar");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnFoto = new JButton();
	
	JLayeredPane panelLibre    = new JLayeredPane();
	JLayeredPane panelMultiple = new JLayeredPane();
	
	JTabbedPane paneles = new JTabbedPane();
	
	public Cat_Alimentacion_Cuadrante()	{
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_captura_icon&16.png"));
		this.setTitle("Alimentación de Cuadrante");
		this.panel.setBorder(BorderFactory.createTitledBorder("Alimentación de Cuadrante"));
		
		this.panel.add(new JLabel("Nombre:")).setBounds(40,30,50,20);
		this.panel.add(txtNombre_Completo).setBounds(150,30,250,20);
		
		this.panel.add(btnGuardar).setBounds(750,30,100,20);
		this.panel.add(btnEditar).setBounds(750,60,100,20);
		this.panel.add(btnSalir).setBounds(750,90,100,20);
		
		this.panel.add(new JLabel("Puesto:")).setBounds(40,60,50,20);
		this.panel.add(txtPuesto).setBounds(150,60,250,20);
		
		this.panel.add(new JLabel("Establecimiento:")).setBounds(40,90,80,20);
		this.panel.add(txtEstablecimiento).setBounds(150,90,250,20);
		
		this.panel.add(new JLabel("Equipo De Trabajo:")).setBounds(40,120,100,20);
		this.panel.add(txtEquipo_Trabajo).setBounds(150,120,250,20);
		
		this.panel.add(new JLabel("Jefatura:")).setBounds(40,150,50,20);
		this.panel.add(txtJefatura).setBounds(150,150,250,20);
		
		this.panel.add(new JLabel("Fecha:")).setBounds(40,180,40,20);
		this.panel.add(txtFecha).setBounds(150,180,100,20);
		
		this.panel.add(new JLabel("Dia:")).setBounds(260,180,40,20);
		this.panel.add(txtDia).setBounds(300,180,100,20);
		
		this.panel.add(new JLabel("Cuadrante:")).setBounds(40,210,60,20);
		this.panel.add(txtCuadrante).setBounds(150,210,250,20);
	
		this.panel.add(btnFoto).setBounds(470,30,235,200);
	
		this.paneles.addTab("Opciones Múltiples", panelMultiple);
		this.paneles.addTab("Opciones Libre", panelLibre);
		
		this.panelMultiple.add(scrollMultiple).setBounds(5,5,865,335);
		this.panelLibre.add(scrollLibre).setBounds(5,5,865,335);
		
		this.panel.add(paneles).setBounds(8,240,880,375);
		
		this.btnEditar.addActionListener(editar);
		this.btnSalir.addActionListener(salir);
		this.btnGuardar.addActionListener(op_guardar);

		this.tablaMultiple.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.tablaLibre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.CamposEnabled(false);
		
		this.init();
		this.init_tabla_libre();
		this.init_tabla_multiple();

		this.cont.add(panel);
		this.setResizable(false);
		
		this.setSize(900,650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void init_tabla_libre(){
		this.tablaLibre.getTableHeader().setReorderingAllowed(false);
		this.tablaLibre.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaLibre.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaLibre.getColumnModel().getColumn(1).setMaxWidth(260);
		this.tablaLibre.getColumnModel().getColumn(1).setMinWidth(260);
		this.tablaLibre.getColumnModel().getColumn(2).setMaxWidth(270);
		this.tablaLibre.getColumnModel().getColumn(2).setMinWidth(270);
		this.tablaLibre.getColumnModel().getColumn(3).setMaxWidth(270);
		this.tablaLibre.getColumnModel().getColumn(3).setMinWidth(270);
		
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
					case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 3 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 
		
		this.tablaLibre.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaLibre.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaLibre.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaLibre.getColumnModel().getColumn(3).setCellRenderer(render); 
	}
	
	public void init_tabla_multiple(){
		this.tablaMultiple.getTableHeader().setReorderingAllowed(false);
		this.tablaMultiple.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaMultiple.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaMultiple.getColumnModel().getColumn(1).setMaxWidth(350);
		this.tablaMultiple.getColumnModel().getColumn(1).setMinWidth(350);
		this.tablaMultiple.getColumnModel().getColumn(2).setMaxWidth(150);
		this.tablaMultiple.getColumnModel().getColumn(2).setMinWidth(150);
		this.tablaMultiple.getColumnModel().getColumn(3).setMaxWidth(300);
		this.tablaMultiple.getColumnModel().getColumn(3).setMinWidth(300);
		
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
					case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 3 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 
		
		this.tablaMultiple.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaMultiple.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaMultiple.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaMultiple.getColumnModel().getColumn(3).setCellRenderer(render); 
		
	}
	
	public void init(){
		
		Obj_Usuario usuario = new Obj_Usuario().LeerSession();
		Obj_Alimentacion_Cuadrante datos_cuadrante = new Obj_Alimentacion_Cuadrante().buscarEmpleado(usuario.getNombre_completo());
		
		if(datos_cuadrante.getEquipo_trabajo() != null){
			
			txtNombre_Completo.setText(datos_cuadrante.getNombre());
			txtPuesto.setText(datos_cuadrante.getPuesto());
			txtEstablecimiento.setText(datos_cuadrante.getEstablecimiento());
			txtEquipo_Trabajo.setText(datos_cuadrante.getEquipo_trabajo());
			txtJefatura.setText(datos_cuadrante.getJefatura());
			txtFecha.setText(datos_cuadrante.getFecha());
			txtDia.setText(datos_cuadrante.getDia());
			txtCuadrante.setText(datos_cuadrante.getCuadrante());
			
			ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp_cuadrante/tmp.jpg");
		    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
			
			String[][] info_tabla_libre    = new Obj_Alimentacion_Cuadrante().buscarTablaLibre(usuario.getNombre_completo(),datos_cuadrante.getDia());
			String[][] info_tabla_multiple = new Obj_Alimentacion_Cuadrante().buscarTablaMultiple(usuario.getNombre_completo(),datos_cuadrante.getDia());
			
			String [] fila_libre = new String[4];
			for(int i=0; i<info_tabla_libre.length; i++){
				fila_libre[0]= info_tabla_libre[i][0]+"  ";
				fila_libre[1]= "   "+info_tabla_libre[i][1];
				fila_libre[2]= "   ";
				fila_libre[3]= "   "; 
				modelLibre.addRow(fila_libre);
				
			}
			
			String [] fila_multiple = new String[4];
			
			List<String[]> lista = new ArrayList<String[]>();
			
			for(int i=0; i<info_tabla_multiple.length; i++){
		    
	            lista.add(new Obj_Alimentacion_Cuadrante().ComboBox(Integer.parseInt(info_tabla_multiple[i][0].toString())));
	            	            
	            TableColumn col = tablaMultiple.getColumnModel().getColumn(2);
	            
	            col.setCellEditor(new MyComboEditor(lista));
	            
				fila_multiple[0]= info_tabla_multiple[i][1]+"  ";
				fila_multiple[1]= "   "+info_tabla_multiple[i][2];
				fila_multiple[2]= "   Respuestas";
				fila_multiple[3]= "   ";
				
				modelMultiple.addRow(fila_multiple);
				
			}
			
		}else{
			txtNombre_Completo.setText(datos_cuadrante.getNombre());
			txtPuesto.setText(datos_cuadrante.getPuesto());
			txtEstablecimiento.setText(datos_cuadrante.getEstablecimiento());
			txtEquipo_Trabajo.setText(datos_cuadrante.getEquipo_trabajo());
			txtJefatura.setText(datos_cuadrante.getJefatura());
			txtFecha.setText(datos_cuadrante.getFecha());
			txtDia.setText(datos_cuadrante.getDia());
			txtCuadrante.setText(datos_cuadrante.getCuadrante());
			
			ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp_cuadrante/tmp.jpg");
		    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
		    
			JOptionPane.showMessageDialog(null, "No tiene cuadrante", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
			
		}
	}
	
	private class MyComboEditor extends DefaultCellEditor{
        List<String[]> values;
        @SuppressWarnings("rawtypes")
		public MyComboEditor(List<String[]> values){
        	super(new JComboBox());
            this.values = values;
        }
         
        @SuppressWarnings({ "rawtypes", "unchecked" })
		public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
        	JComboBox combo = (JComboBox)getComponent();
        	
        	combo.removeAllItems();
            String[] valores = values.get(row);
                
            for(int i=0; i<valores.length; i++){
            	combo.addItem(valores[i]);
            }
//            combo.addActionListener(op_espacio);
            return combo;          
        }
    }
	
//	KeyListener op_espacio = new KeyListener() {
//		public void keyTyped(KeyEvent e) {
//			char caracter = e.getKeyChar();
//			if(caracter  == KeyEvent.VK_SPACE){
//				System.out.println("Se está oprimiendo la tecla espacio");
//		   	}		
//		}
//		@Override
//		public void keyPressed(KeyEvent e){}
//		@Override
//		public void keyReleased(KeyEvent e){}
//								
//	};
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = new Connexion().conexion().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	
	public String celdasVaciasLibre(){
		String error="";
		for(int i=0; i<tablaLibre.getRowCount(); i++){
			if(modelLibre.getValueAt(i,3) == null){
				error+="      la celda de respuesta en la actividad '"+modelLibre.getValueAt(i,1).toString()+"' está vacía en la tabla opciones libre.\n";
			}
		}
		
		return error;
	}
	
	public String celdasVaciasMultiple(){
		String error="";
		for(int i=0; i<tablaMultiple.getRowCount(); i++){
			if(modelMultiple.getValueAt(i,3).toString() == "Respuestas"){
				error+="      la celda de respuesta en la actividad '"+modelMultiple.getValueAt(i,1).toString()+"' está vacía en la tabla opciones multiple.\n";
			}
		}
		
		return error;
	}
	
	ActionListener op_guardar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(tablaLibre.isEditing())	{
				tablaLibre.getCellEditor().stopCellEditing();
			
			}
			if(tablaMultiple.isEditing()){
				tablaMultiple.getCellEditor().stopCellEditing();
			}
			if(celdasVaciasLibre().equals("") && celdasVaciasMultiple().equals("")){
				Obj_Alimentacion_Cuadrante datos_cuadrante = new Obj_Alimentacion_Cuadrante();
				
				datos_cuadrante.setNombre(txtNombre_Completo.getText());
				datos_cuadrante.setPuesto(txtPuesto.getText());
				datos_cuadrante.setEstablecimiento(txtEstablecimiento.getText());
				datos_cuadrante.setEquipo_trabajo(txtEquipo_Trabajo.getText());
				datos_cuadrante.setJefatura(txtJefatura.getText());
				datos_cuadrante.setFecha(txtFecha.getText());
				datos_cuadrante.setDia(txtDia.getText());
				datos_cuadrante.setCuadrante(txtCuadrante.getText());
				
				if(datos_cuadrante.guardar()){
					JOptionPane.showMessageDialog(null, "El registro se guardó con exito!" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de almacenar el registro" , "Aviso", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "Los siguientes campos son requeridos\n"+celdasVaciasLibre() +celdasVaciasMultiple() , "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener editar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
//			CamposEnabledTrue();
		}
	};
	
	public void CamposLimpiar()	{
		txtNombre_Completo.setText("");
		txtPuesto.setText("");
		txtEstablecimiento.setText("");
		txtEquipo_Trabajo.setText("");
		txtJefatura.setText("");
		txtFecha.setText("");
		txtDia.setText("");
		txtCuadrante.setText("");
	}
	
	public void CamposEnabled(boolean variable){
		txtNombre_Completo.setEditable(variable);
		txtPuesto.setEditable(variable);
		txtEstablecimiento.setEditable(variable);
		txtEquipo_Trabajo.setEditable(variable);
		txtJefatura.setEditable(variable);
		txtFecha.setEditable(variable);
		txtDia.setEditable(variable);
		txtCuadrante.setEditable(variable);
	}
	
	ActionListener salir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0)  {
			dispose();
		}
	};
	
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Alimentacion_Cuadrante().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
