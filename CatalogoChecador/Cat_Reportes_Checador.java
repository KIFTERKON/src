package CatalogoChecador;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
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
		this.setTitle("Reporte Checador");
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
				new Reporte_Empleados_Faltantes_o_Retardo(1,filtro_establecimiento);
			} 
		}
	};
	
	ActionListener opGenerarReporteEmpleadosConRetardo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			filtro_establecimiento = cmbEstablecimiento.getSelectedItem().toString();
				new Reporte_Empleados_Faltantes_o_Retardo(2,filtro_establecimiento);
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

}
