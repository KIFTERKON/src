package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import objetos.Obj_Conexion_BD;

public class Connexion {
	Connection conn = null;	
	
	public Connection conexion(){
		Connection conn = null;	
		try{
			Obj_Conexion_BD configs = new Obj_Conexion_BD().buscar();
				
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://"+configs.getDireccionIPV4()+":1433;databaseName="+configs.getNombreBD(), configs.getUsuario(), configs.getContrasena());
			System.out.println("Se ha establecido la conexion con la base de datos: '"+conn.getCatalog()+"' exitosamente");		
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al realizar la conexion con la Base de Datos \n" +
												"Verifique el cable de red ó las asignaciones de IP`s.\n "+ e.toString().substring(0,48)+"\n"+
												e.toString().substring(48,88),"Error",JOptionPane.WARNING_MESSAGE);
			
		}
		return conn;
	}
	
	public boolean init(){
		if(conexion() != null){
			return true;
		}else{
			return false;
		}
	}
}
