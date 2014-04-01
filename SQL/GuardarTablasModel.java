package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;

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
	
	public boolean tablaTicketFuenteSodas_auxf(Object[][] tabla, int folio, String empleado){
		String query = "exec sp_insert_fuente_soda_auxf_de_seleccion_de_ticket ?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, folio);
				pstmt.setString(2, empleado.toUpperCase().trim());
				pstmt.setString(3, tabla[i][0].toString().toUpperCase().trim());
				pstmt.setFloat(4, Float.valueOf(tabla[i][1].toString().trim()));
				pstmt.setString(5, tabla[i][2].toString().trim());
				pstmt.setInt(6, Boolean.parseBoolean(tabla[i][3].toString().trim()) ? 1 : 0);
				pstmt.setString(7, "0");
				pstmt.setString(8, "1");
				
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
	
	public boolean tablaTicketFuenteSodas_dh(Object[][] tabla, int folio, String empleado){
		String query = "exec sp_insert_fuente_soda_dh_de_seleccion_de_ticket ?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, folio);
				pstmt.setString(2, empleado.toUpperCase().trim());
				pstmt.setString(3, tabla[i][0].toString().toUpperCase().trim());
				pstmt.setFloat(4, Float.valueOf(tabla[i][1].toString().trim()));
				pstmt.setString(5, tabla[i][2].toString().trim());
				pstmt.setInt(6, Boolean.parseBoolean(tabla[i][3].toString().trim()) ? 1 : 0);
				pstmt.setString(7, "0");
				pstmt.setString(8, "1");
				
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
	
	public boolean tabla_horario_temporado(Object[][] tabla, String fechaIn,String fechaFin,String horarioTemporal){
		String query = "exec sp_insert_horario_temporada ?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setInt(4, Boolean.parseBoolean(tabla[i][3].toString().trim()) ? 1 : 0);
				pstmt.setString(5, fechaIn);
				pstmt.setString(6, fechaFin);
				pstmt.setString(7, horarioTemporal);
				
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
	
	public boolean borrar_empleado_con_horario_temporal(int folio, String establecimiento,String puesto,String fecha1,String fecha2){
		String query = "exec sp_delete_empleado_horario_temporada "+folio+",'"+establecimiento+"','"+puesto+"','"+fecha1+"','"+fecha2+"';";
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
	
	public boolean Alimentacion_cuadrante_multiple(Object[][] tabla, Object[][] multipleProyecto){
		String query = "exec sp_insert_multiple_actividades_cuadrante ?,?,?,?,?,?,?,?,?,?,?;";
		String query_proyecto = "exec sp_insert_multiple_actividades_cuadrante_jerarquico ?,?,?,?,?,?,?,?,?,?";
		
		Connection con = new Connexion().conexion();
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(query);
			PreparedStatement pstmtJerarquico = con.prepareStatement(query_proyecto);
			
			
			System.out.println("longitud de la tabla a: "+tabla.length);
			for(int i=0; i<tabla.length; i++){
				if(!tabla[i][8].toString().trim().equalsIgnoreCase("Respuestas")){
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
			}
			System.out.println("longitud de la tabla b: "+multipleProyecto.length);
			for(int j=0; j<multipleProyecto.length; j++){
				if(!multipleProyecto[j][8].toString().trim().equalsIgnoreCase("Respuestas")){
					pstmtJerarquico.setString(1, multipleProyecto[j][0].toString().trim());
					pstmtJerarquico.setString(2, multipleProyecto[j][1].toString().trim());
					pstmtJerarquico.setString(3, multipleProyecto[j][2].toString().trim());
					pstmtJerarquico.setString(4, multipleProyecto[j][3].toString().trim());
					pstmtJerarquico.setString(5, multipleProyecto[j][4].toString().trim());
					pstmtJerarquico.setString(6, multipleProyecto[j][5].toString().trim());
					pstmtJerarquico.setInt(7, Integer.parseInt(multipleProyecto[j][6].toString().trim()));
					pstmtJerarquico.setString(8, multipleProyecto[j][7].toString().trim());
					pstmtJerarquico.setString(9, multipleProyecto[j][8].toString().trim());
					pstmtJerarquico.setString(10, multipleProyecto[j][9].toString().trim());
					pstmtJerarquico.execute();
				}				
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
			System.out.println("Error: "+e.getCause());
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
				System.err.println("Error de SQL: "+e.getMessage());
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
	
	public boolean Alimentacion_cuadrante_libre(Object[][] tabla_libre, Object[][] tabla_libre_jerarquico){
		String query = "exec sp_insert_libre_actividades_cuadrante ?,?,?,?,?,?,?,?,?,?,?;";
		String query_proyecto = "exec [sp_insert_libre_actividades_cuadrante_jerarquico] ?,?,?,?,?,?,?,?,?,?";
		
		Connection con = new Connexion().conexion();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			PreparedStatement pstmtJerarquico = con.prepareStatement(query_proyecto);
			con.setAutoCommit(false);
			
			for(int i=0; i<tabla_libre.length; i++){
				if(!tabla_libre[i][8].toString().trim().equalsIgnoreCase("")){
					pstmt.setString(1, tabla_libre[i][0].toString().trim());
					pstmt.setString(2, tabla_libre[i][1].toString().trim());
					pstmt.setString(3, tabla_libre[i][2].toString().trim());
					pstmt.setString(4, tabla_libre[i][3].toString().trim());
					pstmt.setString(5, tabla_libre[i][4].toString().trim());
					pstmt.setString(6, tabla_libre[i][5].toString().trim());
					pstmt.setInt(7, Integer.parseInt(tabla_libre[i][6].toString().trim()));
					pstmt.setString(8, tabla_libre[i][7].toString().trim());
					pstmt.setString(9, tabla_libre[i][8].toString().trim());
					pstmt.setString(10, tabla_libre[i][9].toString().trim());
					pstmt.setString(11, tabla_libre[i][10].toString().trim());
					pstmt.execute();
				}
			}
			for(int j=0; j<tabla_libre_jerarquico.length; j++){
				if(!tabla_libre_jerarquico[j][8].toString().trim().equalsIgnoreCase("")){
					pstmtJerarquico.setString(1, tabla_libre_jerarquico[j][0].toString().trim());
					pstmtJerarquico.setString(2, tabla_libre_jerarquico[j][1].toString().trim());
					pstmtJerarquico.setString(3, tabla_libre_jerarquico[j][2].toString().trim());
					pstmtJerarquico.setString(4, tabla_libre_jerarquico[j][3].toString().trim());
					pstmtJerarquico.setString(5, tabla_libre_jerarquico[j][4].toString().trim());
					pstmtJerarquico.setString(6, tabla_libre_jerarquico[j][5].toString().trim());
					pstmtJerarquico.setInt(7, Integer.parseInt(tabla_libre_jerarquico[j][6].toString().trim()));
					pstmtJerarquico.setString(8, tabla_libre_jerarquico[j][7].toString().trim());
					pstmtJerarquico.setString(9, tabla_libre_jerarquico[j][8].toString().trim());
					pstmtJerarquico.setString(10, tabla_libre_jerarquico[j][9].toString().trim());
					pstmtJerarquico.execute();
				}	
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
		String queryDelete = "exec sp_delete_tabla_cuadrante_nivel_jerarquico "+ folio;
		String querytabla = "exec sp_insert_tabla_cuadrante_real ?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtTabla = null;
		try {
			con.setAutoCommit(false);
			pstmtDelete = con.prepareStatement(queryDelete);
			pstmtDelete.execute();
			
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
				pstmtTabla.execute();
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
	public boolean tabla_IZAGAR_asignaciones_liquidadas(Object[][] tabla){
		String query = "exec IZAGAR_insert_liquidaciones_seleccionadas ?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, tabla[i][0].toString().trim());
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setString(4, tabla[i][3].toString().trim());
				pstmt.setString(5, tabla[i][4].toString().trim());
				pstmt.setString(6, tabla[i][5].toString().trim());
				pstmt.setString(7, tabla[i][6].toString().trim());
				pstmt.setString(8, tabla[i][7].toString().trim());
				pstmt.setInt(9, Boolean.parseBoolean(tabla[i][8].toString().trim()) ? 1 : 0);
	
				
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
	public boolean borrar_IZAGAR_asignacion_liquidada(String asignacion){
		String query = "exec IZAGAR_delete_asignaciones_liquidadas '"+asignacion+"';";
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


public boolean IZAGAR_insert_valores_por_tasa_todas(Object[][] tabla){
	String query = "exec IZAGAR_insert_valores_por_tasa_todas ?,?,?,?,?";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);

		con.setAutoCommit(false);
		
		for(int i=0; i<tabla.length; i++){
			pstmt.setString(1, tabla[i][0].toString().trim());
			pstmt.setString(2, tabla[i][1].toString().trim());
			pstmt.setString(3, tabla[i][2].toString().trim());
			pstmt.setString(4, tabla[i][3].toString().trim());
			pstmt.setString(5, tabla[i][4].toString().trim());
			;
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
public boolean IZAGAR_asignaciones_liquidadas_todas(Object[][] tabla){
	String query = "exec IZAGAR_insert_liquidaciones_todas ?,?,?,?,?,?,?,?";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);

		con.setAutoCommit(false);
		
		for(int i=0; i<tabla.length; i++){
			pstmt.setString(1, tabla[i][0].toString().trim());
			pstmt.setString(2, tabla[i][1].toString().trim());
			pstmt.setString(3, tabla[i][2].toString().trim());
			pstmt.setString(4, tabla[i][3].toString().trim());
			pstmt.setString(5, tabla[i][4].toString().trim());
			pstmt.setString(6, tabla[i][5].toString().trim());
			pstmt.setString(7, tabla[i][6].toString().trim());
			pstmt.setString(8, tabla[i][7].toString().trim());
			;
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
public boolean IZAGAR_insert_Netos_Nominas_Temp(Object[][] tabla){
	String query = "exec IZAGAR_insert_Netos_Nominas_Temp ?,?,?";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		
		for(int i=0; i<tabla.length; i++){
			pstmt.setString(1, tabla[i][0].toString().trim());
			pstmt.setString(2, tabla[i][1].toString().trim());
			pstmt.setString(3, tabla[i][2].toString().trim());
			;
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

public boolean IZAGAR_insert_Netos_Nominas_Temp_individual(int folio_empleado,int nomina,float neto){
	String query = "exec IZAGAR_insert_netos_de_nomina_por_empleado_pre_conciliados_individual "+folio_empleado+","+nomina+","+neto+";";
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

public boolean IZAGAR_insert_totales_deposito_nomina_bancos(){
	String query = "exec sp_traspaso_de_totales_a_depositos_nomina_bancos;";
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
}
