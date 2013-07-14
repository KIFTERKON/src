package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import SQL.Connexion;

import objetos.Obj_Alimentacion_Cuadrante;
import objetos.Obj_Usuario;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Cuadrante extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	
	//Empieza tabla Opciones libres
	Object[][] Tabla = getTabla();
	DefaultTableModel model = new DefaultTableModel(Tabla,
			new String[]{"Folio", "Actividad", "Opción", "Respuesta","Comentarios" }){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
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
        	 	case 0 : return true; 
        	 	case 1 : return true; 
        	 	case 2 : return true; 
        	 	case 3 : return true;
        	 	case 4 : return true; 

        	 } 				
 			return false;
         }
         
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	TableColumn columnaCombo = tabla.getColumnModel().getColumn(3);
	//Termina tabla opciones libre
	
	
	//Empieza tabla opciones multiples
	Object[][] Tabla2 = getTabla();
	DefaultTableModel model2 = new DefaultTableModel(Tabla2,
			new String[]{"Folio", "Actividad", "Opción", "Respuesta","Comentarios" }){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
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
        	 	case 0 : return true; 
        	 	case 1 : return true; 
        	 	case 2 : return true; 
        	 	case 3 : return true;
        	 	case 4 : return true; 

        	 } 				
 			return false;
         }
         
	};
	
	JTable tabla2 = new JTable(model2);
	JScrollPane scroll2 = new JScrollPane(tabla2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	TableColumn columnaCombo2 = tabla2.getColumnModel().getColumn(3);
	//Termina tabla opciones multiples
	
	
	JTextField txtNombre_Completo = new JTextField();
	JTextField txtPuesto = new JTextField();
	JTextField txtEstablecimiento = new JTextField();
	JTextField txtEquipo_Trabajo = new JTextField();
	JTextField txtJefatura = new JTextField();
	JTextField txtDia = new JTextField();
	JTextField txtFecha = new JTextField();
	JTextField txtCuadrante = new JTextField();
	
	@SuppressWarnings("rawtypes")
	JComboBox cmbMultiple = new JComboBox();
	
	JButton btnSalir = new JButton("Salir");
	JButton btnEditar = new JButton("Editar");
	JButton btnEnviar = new JButton("Enviar");
	JButton btnFoto = new JButton();
	
	JLayeredPane panel1 = new JLayeredPane();
	JLayeredPane panel2 = new JLayeredPane();
	JLayeredPane panel3 = new JLayeredPane();
	JLayeredPane panel4 = new JLayeredPane();
	
	JTabbedPane tablapane = new JTabbedPane();
	public Cat_Alimentacion_Cuadrante()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Alimentación de Cuadrante");
		panel.setBorder(BorderFactory.createTitledBorder("Alimentación de Cuadrante"));
		
		this.panel.add(new JLabel("Nombre:")).setBounds(40,30,50,20);
		this.panel.add(txtNombre_Completo).setBounds(150,30,250,20);
		
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
	
		this.panel.add(btnFoto).setBounds(470,30,150,150);
		tablapane.addTab("Opciones Multiples", panel1);
		tablapane.addTab("Opciones Libres", panel2);
		
		panel2.add(scroll).setBounds(5,5,835,230);
		panel1.add(scroll2).setBounds(5,5,835,230);
		this.panel.add(tablapane).setBounds(8,250,850,270);
		
		//Tabla opciones multiples
		this.tabla2.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tabla2.getColumnModel().getColumn(0).setMinWidth(60);
		this.tabla2.getColumnModel().getColumn(1).setMaxWidth(200);
		this.tabla2.getColumnModel().getColumn(1).setMinWidth(200);
		this.tabla2.getColumnModel().getColumn(2).setMaxWidth(80);
		this.tabla2.getColumnModel().getColumn(2).setMinWidth(80);
		this.tabla2.getColumnModel().getColumn(3).setMaxWidth(200);
		this.tabla2.getColumnModel().getColumn(3).setMinWidth(200);
		this.tabla2.getColumnModel().getColumn(4).setMaxWidth(300);
		this.tabla2.getColumnModel().getColumn(4).setMinWidth(300);
		
//		tabla opciones libres
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(60);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(200);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(200);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(3).setMaxWidth(400);
		this.tabla.getColumnModel().getColumn(3).setMinWidth(400);
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(300);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(300);
		
		columnaCombo.setCellEditor(new javax.swing.DefaultCellEditor(cmbMultiple));
	
		btnEditar.addActionListener(editar);
		btnSalir.addActionListener(salir);
		
		this.panel.add(btnSalir).setBounds(180,550,100,20);
		this.panel.add(btnEditar).setBounds(330,550,100,20);
		this.panel.add(btnEnviar).setBounds(480,550,100,20);
		//Opciones libres//
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//Opcions multiples//
		tabla2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		CamposEnabledFalse();
		init();

		cont.add(panel);
		this.setResizable(false);
		
		this.setSize(870,620);
		this.setLocationRelativeTo(null);
	}
	
	public void CamposLimpiar()
	{
		txtNombre_Completo.setText("");
		txtPuesto.setText("");
		txtEstablecimiento.setText("");
		txtEquipo_Trabajo.setText("");
		txtJefatura.setText("");
		txtFecha.setText("");
		txtDia.setText("");
		txtCuadrante.setText("");
	}
	
	
	public void CamposEnabledFalse()
	{
		txtNombre_Completo.setEditable(false);
		txtPuesto.setEditable(false);
		txtEstablecimiento.setEditable(false);
		txtEquipo_Trabajo.setEditable(false);
		txtJefatura.setEditable(false);
		txtFecha.setEditable(false);
		txtDia.setEditable(false);
		txtCuadrante.setEditable(false);
		btnFoto.setEnabled(false);
		tabla.setEnabled(false);
		tabla2.setEnabled(false);
	}
	
	public void CamposEnabledTrue()
	{
//		txtNombre_Completo.setEditable(true);
//		txtPuesto.setEditable(true);
//		txtEstablecimiento.setEditable(true);
//		txtEquipo_Trabajo.setEditable(true);
//		txtJefatura.setEditable(true);
//		txtFecha.setEditable(true);
//		txtDia.setEditable(true);
//		txtCuadrante.setEditable(true);
//		tabla.setEnabled(true);
	}
	
	public void init(){
		Obj_Usuario usuario = new Obj_Usuario().LeerSession();
		Obj_Alimentacion_Cuadrante datos_cuadrante = new Obj_Alimentacion_Cuadrante().buscarEmpleado(usuario.getNombre_completo());
		
		txtNombre_Completo.setText(datos_cuadrante.getNombre());
		txtPuesto.setText(datos_cuadrante.getPuesto());
		txtEstablecimiento.setText(datos_cuadrante.getEstablecimiento());
		txtEquipo_Trabajo.setText(datos_cuadrante.getEquipo_trabajo());
		txtJefatura.setText(datos_cuadrante.getJefatura());
		txtFecha.setText(datos_cuadrante.getFecha());
		txtDia.setText(datos_cuadrante.getDia());
		txtCuadrante.setText(datos_cuadrante.getCuadrante());
		
		
		String[][] info_tabla = new Obj_Alimentacion_Cuadrante().buscarTabla(usuario.getNombre_completo());
		String [] fila = new String[3];
		for(int i=0; i<info_tabla.length; i++){
			fila[0]= info_tabla[i][0];
			fila[1]= info_tabla[i][1];
			fila[2]= info_tabla[i][2];
			if(!info_tabla[i][2].equals("Libre")){
				
			}
//			model.addRow(fila);
		}
	}
	
	private Object[][] getTabla(){
		try {
			String datos = "select top 2 (folio) from tb_empleado";
			Statement s = new Connexion().conexion().createStatement();
			ResultSet rs = s.executeQuery(datos);
			Object[][] Matriz = new Object[2][5];
			int i=0;
			while(rs.next()){
				Matriz[i][0] = 0;
				Matriz[i][1] = "";
				Matriz[i][2] = "";
				Matriz[i][3] = "";
				Matriz[i][4] = false;
				i++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Tabla;

	}
	
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
	
	ActionListener editar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			CamposEnabledTrue();
		}
	};
	
	ActionListener salir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
		}
	};
	
	public static void main(String[]a)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Alimentacion_Cuadrante().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
