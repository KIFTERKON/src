package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;

import objetos.Obj_Alimentacion_Cuadrante;

public class GuardarTablasModel {
	public boolean tabla_model_bancos(Object[][] tabla){
		String query_delete = "exec sp_borrado_empleados_dif_1";
		String query = "exec sp_insert_bancos ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			
			PreparedStatement pstmtDelete = con.prepareStatement(query_delete);
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			pstmtDelete.executeUpdate();
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setFloat(4, Float.parseFloat(tabla[i][3].toString()));
				pstmt.setFloat(5, Float.parseFloat(tabla[i][4].toString()));
				pstmt.setString(6, "1");
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_inasistencia(Object[][] tabla){
		String query = "exec sp_insert_deducc_inasistencia ?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setString(4, tabla[i][3].toString().trim().equals("true") ? "true" : "false");
				pstmt.setString(5, tabla[i][4].toString().trim().equals("true") ? "true" : "false");
				pstmt.setInt(6, Integer.parseInt(tabla[i][5].toString()));
				pstmt.setString(7, tabla[i][6].toString().trim().equals("true") ? "true" : "false");
				pstmt.setString(8, tabla[i][8].toString().trim().equals("true") ? "true" : "false");
				pstmt.setInt(9, Integer.parseInt(tabla[i][9].toString()));
				pstmt.setFloat(10, Float.parseFloat(tabla[i][10].toString()));
				pstmt.executeUpdate();
			
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_persecciones(Object[][] tabla){
		String query = "exec sp_insert_persecciones_extra ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setFloat(4, Float.parseFloat(tabla[i][3].toString()));
				pstmt.setString(5, tabla[i][4].toString().trim().equals("true") ? "true" : "false");
				pstmt.setInt(6, Integer.parseInt(tabla[i][5].toString()));
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_lista_raya_update(){
		String query = "exec sp_lista_raya_assets";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_lista_raya(Object[][] tabla, String fecha){
		String query = "exec sp_insert_pre_listaraya ?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, Boolean.parseBoolean(tabla[i][0].toString().trim()) ? "true" : "false");
				pstmt.setInt(2, Integer.parseInt(tabla[i][1].toString().trim()));
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setFloat(4, Float.parseFloat(tabla[i][3].toString().trim()));
				pstmt.setString(5, tabla[i][4].toString().trim());
				pstmt.setString(6, tabla[i][5].toString().trim());
				pstmt.setString(7, fecha);
				pstmt.executeUpdate();
			}
					
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_alimentacion_totales(Object[][] tabla){
		String query = "exec sp_insert_costos_totales ?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, tabla[i][0].toString().trim());
				pstmt.setFloat(2, Float.parseFloat(tabla[i][1].toString().trim()));
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_lista_raya_generar(Object[][] tabla, String fecha){
		String query = "exec sp_insert_lista_raya ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][1].toString().trim()));
				pstmt.setString(2, tabla[i][2].toString().trim());
				pstmt.setString(3, tabla[i][3].toString().trim());
				pstmt.setFloat(4, Float.parseFloat(tabla[i][4].toString().trim()));
				pstmt.setFloat(5, Float.parseFloat(tabla[i][5].toString().trim()));
				pstmt.setFloat(6, Float.parseFloat(tabla[i][6].toString().trim()));
				pstmt.setFloat(7, Float.parseFloat(tabla[i][7].toString().trim()));
				pstmt.setFloat(8, Float.parseFloat(tabla[i][8].toString().trim()));
				pstmt.setFloat(9, Float.parseFloat(tabla[i][9].toString().trim()));
				pstmt.setFloat(10, Float.parseFloat(tabla[i][10].toString().trim()));
				pstmt.setFloat(11, Float.parseFloat(tabla[i][11].toString().trim()));
				pstmt.setFloat(12, Float.parseFloat(tabla[i][12].toString().trim()));
				pstmt.setFloat(13, Float.parseFloat(tabla[i][13].toString().trim()));
				pstmt.setFloat(14, Float.parseFloat(tabla[i][14].toString().trim()));
				pstmt.setFloat(15, Float.parseFloat(tabla[i][15].toString().trim()));
				pstmt.setFloat(16, Float.parseFloat(tabla[i][16].toString().trim()));
				pstmt.setFloat(17, Float.parseFloat(tabla[i][17].toString().trim()));
				pstmt.setFloat(18, Float.parseFloat(tabla[i][18].toString().trim()));
				pstmt.setFloat(19, Float.parseFloat(tabla[i][19].toString().trim()));
				pstmt.setFloat(20, Float.parseFloat(tabla[i][20].toString().trim()));
				pstmt.setFloat(21, Float.parseFloat(tabla[i][21].toString().trim()));
				pstmt.setFloat(22, Float.parseFloat(tabla[i][22].toString().trim()));
				pstmt.setString(23, tabla[i][23].toString().trim());
				pstmt.setString(24, fecha);
				pstmt.setInt(25, 1);
				pstmt.executeUpdate();
			}
					
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_respuesta(String nombre, Object[][] tabla){
		String query_delete = "exec sp_actualizar_tabla_respuesta '"+nombre+"';";
		String query = "exec sp_insert_tabla_respuestas ?,?,?";
		Connection con = new Connexion().conexion();
		
		try {

			PreparedStatement pstmt_delete = con.prepareStatement(query_delete);
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			pstmt_delete.executeUpdate();
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, nombre.trim());
				pstmt.setInt(2, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(3, tabla[i][1].toString().toUpperCase().trim());
				
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Alimentacion_cuadrante_multiple(Object[][] tabla){
//		String query_delete = "exec sp_delete_alimentacion_multiple ?";
//		String query = "exec sp_insert_tabla_alimentacion_multiple ?,?,?,?,?";
		
		String query = "exec sp_insert_multiple_actividades_cuadrante ?,?,?,?,?,?,?,?,?,?,?;";
		Connection con = new Connexion().conexion();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			System.out.println(tabla.length);
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, tabla[i][0].toString().trim());
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setString(4, tabla[i][3].toString().trim());
				pstmt.setString(5, tabla[i][4].toString().trim());
				pstmt.setString(6, tabla[i][5].toString().trim());
				pstmt.setInt(7, Integer.parseInt(tabla[i][6].toString().trim()));
				pstmt.setString(8, tabla[i][7].toString().trim());
				pstmt.setString(9, tabla[i][8].toString().trim());
				pstmt.setString(10, tabla[i][9].toString().trim());
				pstmt.setString(11, tabla[i][10].toString().toUpperCase());
				pstmt.execute();
			
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean terminar_cuadrante_multiple(String nombre){
		String query = "exec sp_termina_captura_cuadrante ?;";
		
		Connection con = new Connexion().conexion();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			pstmt.setString(1, nombre);
			pstmt.execute();
			
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Alimentacion_cuadrante_libre(Object[][] tabla, Obj_Alimentacion_Cuadrante alimentacion){
		String query_delete = "exec sp_borrado_alimentacion_libre";
		String query = "exec sp_insert_bancos ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmtDelete = con.prepareStatement(query_delete);
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			pstmtDelete.executeUpdate();
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setFloat(4, Float.parseFloat(tabla[i][3].toString()));
				pstmt.setFloat(5, Float.parseFloat(tabla[i][4].toString()));
				pstmt.setString(6, "1");
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Cuadrante_Tabla_Real(int folio, int cuadrante, String encargado, String[][] tabla){
		//String queryDelete = "delete from tb_tabla_cuadrante_actividad where folio_empleado="+folio;
		
		String querytabla = "exec sp_insert_tabla_cuadrante_real ?,?,?,?,?,?,?,?,?,?";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtTabla = null;
		try {
			con.setAutoCommit(false);
			
			pstmtTabla = con.prepareStatement(querytabla);
				
			for(int i=0; i<tabla.length; i++){
				
				pstmtTabla.setInt(1, folio);
				pstmtTabla.setString(2, procesa_texto(encargado));
				pstmtTabla.setInt(3, cuadrante);
				pstmtTabla.setInt(4, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmtTabla.setString(5, tabla[i][1].toString().trim().toUpperCase());
				pstmtTabla.setString(6, tabla[i][2].toString().trim());
				pstmtTabla.setInt(7, Boolean.parseBoolean(tabla[i][3]) ? 1 : 0);
				pstmtTabla.setString(8, tabla[i][4]);
				pstmtTabla.setString(9, tabla[i][5]);
				pstmtTabla.setString(10, tabla[i][6]);
				
				pstmtTabla.executeUpdate();
			}
			
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada Guardar_Cuadrante_Tabla");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public String procesa_texto(String texto) {
		StringTokenizer tokens = new StringTokenizer(texto);
	    texto = "";
	    while(tokens.hasMoreTokens()){
	    	texto += " "+tokens.nextToken();
	    }
	    texto = texto.toString();
	    texto = texto.trim().toUpperCase();
	     return texto;
	}
}
