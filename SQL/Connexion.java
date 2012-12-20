package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class Connexion {
	Connection conn = conexion();
	
	@SuppressWarnings("unchecked")
	Vector miVector = new Vector();
	
	public static Connection conexion(){
		Connection conn = null;	
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn=DriverManager.getConnection("jdbc:odbc:Grupo_Izagar");
			System.out.println("Se ha establecido la conexion con la base de datos: '"+conn.getCatalog()+"' exitosamente");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
}
