package CatalogoChecador;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import SQL.Connexion;

import objetos.Obj_Establecimiento;
import reporte.Reporte_Empleados_Faltantes_o_Retardo;

@SuppressWarnings("serial")
public class Cat_Reportes_Checador extends JFrame {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	JButton btnEmpleadosFaltantes = new JButton();
	JButton btnEmpleadosConRetardo = new JButton();
	
	String filtro_establecimiento ="";
	
	public Cat_Reportes_Checador(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/user_icon&16.png"));
		this.setTitle("Reportes de Asistencia y Retardos del Dia");
		panel.setBorder(BorderFactory.createTitledBorder("Seleccion de Reporte"));


		btnEmpleadosFaltantes.setSelected(true);
		btnEmpleadosFaltantes.setText(	"<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk>" +
										"		<p>GENERAR REPORTE DE PERSONAL</p>" +
										"		<p>QUE NO HA REGISTRADO SU ENTRADA</p>" +
										"		<p>Y SU HORA DE INGRESO YA PASO</p></FONT>" +
										"</html>"); 
		
		btnEmpleadosConRetardo.setSelected(true);
		btnEmpleadosConRetardo.setText(	"<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk>" +
										"		<p>GENERAR REPORTE DE PERSONAL</p>" +
										"		<p>QUE REGISTRO ENTRADA DESPUES</p>" +
										"		<p>DE SU HORA DE INGRESO</p></FONT>" +
										"</html>"); 
		
		panel.add(cmbEstablecimiento).setBounds(270, 20, 170, 20);
		panel.add(btnEmpleadosFaltantes).setBounds(20, 50, 300, 75);
		panel.add(btnEmpleadosConRetardo).setBounds(140, 135, 300, 75);
		
		this.btnEmpleadosFaltantes.addActionListener(opGenerarReporteEmpleadosFaltantes);
		this.btnEmpleadosConRetardo.addActionListener(opGenerarReporteEmpleadosConRetardo);
		
		cont.add(panel);
		this.setSize(470, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opGenerarReporteEmpleadosFaltantes = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			filtro_establecimiento = cmbEstablecimiento.getSelectedItem().toString();
			if(llenado()==true){
				new Reporte_Empleados_Faltantes_o_Retardo(1,filtro_establecimiento,0);
			} 
		}
	};
	
	ActionListener opGenerarReporteEmpleadosConRetardo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			filtro_establecimiento = cmbEstablecimiento.getSelectedItem().toString();
				new Cat_Seleccion_Tiempo_Retardo().setVisible(true);
		}
	};
	
	public boolean llenado(){
		String query = "exec sp_select_empleados_faltantes_de_checar;";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}
	
	public static void main(String [] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_Checador().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
	
	public class Cat_Seleccion_Tiempo_Retardo extends JDialog{

		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		JTextField txtTiempo = new JTextField(); 

		public Cat_Seleccion_Tiempo_Retardo (){
			
			this.setModal(true);
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
			this.setTitle("Filtro");
			panel.setBorder(BorderFactory.createTitledBorder("Tiempo de retarde mayor a:"));
			
			panel.add(txtTiempo).setBounds(35,20,50,20);
			panel.add(new JLabel("Minutos")).setBounds(90,20,80,20);
			
			this.txtTiempo.addKeyListener(validaNumerico);
			this.txtTiempo.addActionListener(opTiempo);
			
			this.setSize(170,80);
	        cont.add(panel);
			this.setResizable(false);
			this.setLocationRelativeTo(null);		
		}

		   ActionListener opTiempo =new ActionListener() 
		   {
				public  void actionPerformed(ActionEvent e) {
					if(txtTiempo.getText().equals("")){
						dispose();
						new Reporte_Empleados_Faltantes_o_Retardo(2,filtro_establecimiento,0);
					}else{
						dispose();
						new Reporte_Empleados_Faltantes_o_Retardo(2,filtro_establecimiento,Integer.valueOf(txtTiempo.getText()));
					}
				}
		   };
		   
			KeyListener validaNumerico = new KeyListener() {
				public void keyTyped(KeyEvent e){
					char caracter = e.getKeyChar();				
					if((caracter < '0') ||	
					    	(caracter > '9')){
					    		e.consume();
					    	}
				}
				public void keyReleased(KeyEvent e) {}
				public void keyPressed(KeyEvent arg0) {}	
			};
	}
}
