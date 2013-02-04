package SQL;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Connexion {
	Connection conn = null;	
	
	public Connection conexion(){
		Connection conn = null;	
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.2.26:1433;databaseName=Grupo_Izagar", "sa", "ragazi*12345");
			System.out.println("Se ha establecido la conexion con la base de datos: '"+conn.getCatalog()+"' exitosamente");
			
		}catch(Exception e){
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Error al realizar la conexion con la Base de Datos \n" +
												"Verifique el cable de red ó las asignaciones de IP`s.\n "+ e.toString().substring(0,48)+"\n"+
												e.toString().substring(48,88),"Error",JOptionPane.WARNING_MESSAGE);
		}
		return conn;
	}
	
}
