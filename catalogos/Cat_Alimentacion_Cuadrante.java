package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import objetos.Obj_Alimentacion_Cuadrante;
import objetos.Obj_Usuario;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Cuadrante extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Object[][] Tabla = getTabla();
	DefaultTableModel model = new DefaultTableModel(Tabla,
			new String[]{"Folio", "Actividad", "Respuesta", "Comentarios", "Habilitar Edición" }){
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
        	 	case 5 : return false;

        	 } 				
 			return false;
 		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla);
			
	JTextField txtNombre_Completo 	= new JTextField();
	JTextField txtPuesto 			= new JTextField();
	JTextField txtEstablecimiento 	= new JTextField();
	JTextField txtEquipo_Trabajo 	= new JTextField();
	JTextField txtJefatura 			= new JTextField();
	JTextField txtDia 				= new JTextField();
	JTextField txtFecha 			= new JTextField();
	JTextField txtCuadrante 		= new JTextField();
	
	JButton btnFoto = new JButton();
	
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnEditar = new JButton("Editar");
	JButton btnGuardar = new JButton("Guardar");
	
	public Cat_Alimentacion_Cuadrante(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Alimentación de Cuadrante");
		
		int x = 40, y=30, ancho=140;
		panel.setBorder(BorderFactory.createTitledBorder("Alta de Empleados"));
		
		this.panel.add(new JLabel("Nombre:")).setBounds(x,y,100,20);
		this.panel.add(txtNombre_Completo).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(btnFoto).setBounds(ancho*2+60,y,y*4+20,y*5);
		
		this.panel.add(new JLabel("Puesto:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtPuesto).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Establecimiento:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtEstablecimiento).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Equipo de Trabajo:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtEquipo_Trabajo).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Jefatura:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtJefatura).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Día:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtDia).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtFecha).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Cuadrante:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtCuadrante).setBounds(ancho+10,y,ancho*2+50,100);
		
		this.panel.add(scroll).setBounds(10,y+=130,675,270);
		
		this.panel.add(btnLimpiar).setBounds(140,y+=290,100,20);
		this.panel.add(btnEditar).setBounds(290,y,100,20);
		this.panel.add(btnGuardar).setBounds(440,y,100,20);
		
		init();
		
		this.cont.add(panel);
		
		this.setSize(700,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void init(){
		Obj_Usuario usuario = new Obj_Usuario().LeerSession();
		Obj_Alimentacion_Cuadrante datos_cuadrante = new Obj_Alimentacion_Cuadrante().buscarEmpleado(usuario.getNombre_completo());
		
		System.out.println(datos_cuadrante.getNombre());
		System.out.println(datos_cuadrante.getPuesto());
		System.out.println(datos_cuadrante.getEstablecimiento());
		System.out.println(datos_cuadrante.getEquipo_trabajo());
		System.out.println(datos_cuadrante.getDia());
		System.out.println(datos_cuadrante.getFecha());
		System.out.println(datos_cuadrante.getCuadrante());
		
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
	public static void main (String arg []){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Alimentacion_Cuadrante().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
