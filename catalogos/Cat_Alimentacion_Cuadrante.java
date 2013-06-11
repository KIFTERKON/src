package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import SQL.Connexion;

import com.toedter.calendar.JDateChooser;

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
			
	JLabel lblNombre_Completo = new JLabel();
	JLabel lblPuesto = new JLabel();
	JLabel lblEstablecimiento = new JLabel();
	JLabel lblEquipo = new JLabel();
	JLabel lblJefatura = new JLabel();
	
	JButton btnFoto = new JButton();
	
	JDateChooser txtCalendario = new JDateChooser();
	
	JTextField txtFecha = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	
	JTextArea txaCuadrante = new JTextArea();
	JScrollPane scrollCuadrante = new JScrollPane(txaCuadrante);
	
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnEditar = new JButton("Editar");
	JButton btnGuardar = new JButton("Guardar");
	
	public Cat_Alimentacion_Cuadrante(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Alimentación de Cuadrante");
		
		int x = 40, y=30, ancho=140;
		panel.setBorder(BorderFactory.createTitledBorder("Alta de Empleados"));
		
		this.panel.add(new JLabel("Nombre Completo:")).setBounds(x,y,100,20);
		this.panel.add(lblNombre_Completo).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(btnFoto).setBounds(ancho*2+60,y,y*4+20,y*5);
		
		this.panel.add(new JLabel("Puesto:")).setBounds(x,y+=25,100,20);
		this.panel.add(lblPuesto).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Establecimiento:")).setBounds(x,y+=25,100,20);
		this.panel.add(lblEstablecimiento).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Equipo:")).setBounds(x,y+=25,100,20);
		this.panel.add(lblEquipo).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Jefatura:")).setBounds(x,y+=25,100,20);
		this.panel.add(lblJefatura).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Día:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtCalendario).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtFecha).setBounds(ancho+10,y,ancho,20);
		
		this.panel.add(new JLabel("Descripción:")).setBounds(x,y+=25,100,20);
		this.panel.add(scrollCuadrante).setBounds(ancho+10,y,ancho*2+50,100);
		
		this.panel.add(scroll).setBounds(10,y+=130,675,270);
		
		this.panel.add(btnLimpiar).setBounds(140,y+=290,100,20);
		this.panel.add(btnEditar).setBounds(290,y,100,20);
		this.panel.add(btnGuardar).setBounds(440,y,100,20);
		
		this.cont.add(panel);
		
		this.setSize(700,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
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
