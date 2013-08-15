package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mantenimiento_BD {
	//se declara la funcion que sera carga en el objeto
	public boolean reducir_log(){
		//se carga la consulta a una varible tipo string
		String query = "exec sp_reducir_log_de_transacciones";
		//variable distancia a la clase connection
		Connection con = new Connexion().conexion();
        //se declara la variable  que prepara el query primero nula
		PreparedStatement pstmt = null;
	    //intenta o carga el error 	
		
		
			
		try {
	    	//se inicializa ala transaccion en falto hasta el commit
			con.setAutoCommit(false);
			//prepara la coneccion con el query
			pstmt = con.prepareStatement(query);
			//ejecuta el query 
			pstmt.execute();
            //si se ejecuta la transaccion  la marcara como verdadero 		
 			con.commit();
			//cacha el error
 			
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
			
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean agregar_submenus_nuevos(){
	
		String query = "exec sp_insert_submenu_nuevo ";
		Connection con = new Connexion().conexion();
     	PreparedStatement pstmt = null;
			
			
		try {
	    	
			con.setAutoCommit(false);
		    pstmt = con.prepareStatement(query);
			pstmt.execute();
 			con.commit();
		
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
			
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	
	
}
