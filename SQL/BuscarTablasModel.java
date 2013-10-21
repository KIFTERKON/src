package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objetos.Obj_Usuario;

public class BuscarTablasModel {
	
	public Object[][] tabla_model_bancos(){
		String query_lista = "exec sp_lista_banco";
		Object[][] matriz = new Object[get_filas(query_lista)][6];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getInt(1)+" ";
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				float banamex = rs.getFloat(4);
				float banorte = rs.getFloat(5);
				float total = rs.getFloat(6);
				matriz[i][3] = banamex == Float.parseFloat("0.0") ? "" : banamex ;
				matriz[i][4] = banorte == Float.parseFloat("0.0") ? "" : banorte ;
				matriz[i][5] = total == Float.parseFloat("0.0") ? "" : total ;
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_model_deduccion_inasistencia(){
		String query_lista = "exec sp_buscar_deduccion_inasistencia";
		Object[][] matriz = new Object[get_filas(query_lista)][11];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getInt(1)+" ";
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = rs.getString(4).trim().equals("true") ? true : false;
				matriz[i][4] = rs.getString(5).trim().equals("true") ? true : false;
				matriz[i][5] = Integer.parseInt(rs.getString(6)) == 0 ? "":Integer.parseInt(rs.getString(6));
				matriz[i][6] = rs.getString(7).trim().equals("true") ? true : false;
				matriz[i][7] = rs.getString(8).trim().equals("true") ? true : false;
				matriz[i][8] = rs.getString(9).trim().equals("true") ? true : false;
				matriz[i][9] = Integer.parseInt(rs.getString(10)) == 0 ? "":Integer.parseInt(rs.getString(10));
				matriz[i][10] = Float.parseFloat(rs.getString(11)) == 0 ? "":Float.parseFloat(rs.getString(11));
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_model_persecciones(){
		String query_lista = "exec sp_lista_persecciones";
		Object[][] matriz = new Object[get_filas(query_lista)][6];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getInt(1)+" ";
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = Float.parseFloat(rs.getString(4)) == 0 ? "" : Float.parseFloat(rs.getString(4));
				matriz[i][4] = rs.getString(5).trim().equals("true") ? true : false;
				matriz[i][5] = Integer.parseInt(rs.getString(6).trim()) == 0 ? "" : Integer.parseInt(rs.getString(6).trim());
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_model_lista_raya(){
		String query_lista = "exec sp_get_lista_raya";
		Object[][] matriz = new Object[get_filas(query_lista)][25];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = Boolean.parseBoolean(rs.getString(1).trim());
				matriz[i][1] = rs.getInt(2)+" ";
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = "   "+rs.getString(4);
				matriz[i][4] = Float.parseFloat(rs.getString(5)) == 0 ? "" : Float.parseFloat(rs.getString(5));
				matriz[i][5] = Float.parseFloat(rs.getString(6)) == 0 ? "" : Float.parseFloat(rs.getString(6));
				matriz[i][6] = Float.parseFloat(rs.getString(7)) == 0 ? "" : Float.parseFloat(rs.getString(7));
				matriz[i][7] = Float.parseFloat(rs.getString(8)) == 0 ? "" : Float.parseFloat(rs.getString(8));
				matriz[i][8] = Float.parseFloat(rs.getString(9)) == 0 ? "" : Float.parseFloat(rs.getString(9));
				matriz[i][9] = Float.parseFloat(rs.getString(10)) == 0 ? "" : Float.parseFloat(rs.getString(10));
				matriz[i][10] = Float.parseFloat(rs.getString(11)) == 0 ? "" : Float.parseFloat(rs.getString(11));
				matriz[i][11] = Float.parseFloat(rs.getString(12)) == 0 ? "" : Float.parseFloat(rs.getString(12));
				matriz[i][12] = Float.parseFloat(rs.getString(13)) == 0 ? "" : Float.parseFloat(rs.getString(13));
				matriz[i][13] = Float.parseFloat(rs.getString(14)) == 0 ? "" : Float.parseFloat(rs.getString(14));
				matriz[i][14] = Float.parseFloat(rs.getString(15)) == 0 ? "" : Float.parseFloat(rs.getString(15));
				matriz[i][15] = Float.parseFloat(rs.getString(16)) == 0 ? "" : Float.parseFloat(rs.getString(16));
				matriz[i][16] = Float.parseFloat(rs.getString(17)) == 0 ? "" : Float.parseFloat(rs.getString(17));
				matriz[i][17] = Float.parseFloat(rs.getString(18)) == 0 ? "" : Float.parseFloat(rs.getString(18));
				matriz[i][18] = Float.parseFloat(rs.getString(19)) == 0 ? "" : Float.parseFloat(rs.getString(19));
				matriz[i][19] = Float.parseFloat(rs.getString(20)) == 0 ? "" : Float.parseFloat(rs.getString(20));
				matriz[i][20] = Float.parseFloat(rs.getString(21)) == 0 ? "" : Float.parseFloat(rs.getString(21));
				matriz[i][21] = Float.parseFloat(rs.getString(22)) == 0 ? "" : Float.parseFloat(rs.getString(22));
				matriz[i][22] = Float.parseFloat(rs.getString(23)) == 0 ? "" : Decimal(Float.parseFloat(rs.getString(23)));
				matriz[i][23] = "   "+rs.getString(24);
				matriz[i][24] = "   "+rs.getString(25);
				
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public String tabla_model_lista_raya_fecha(){
		String valor = "";
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("exec sp_fecha_lista_pre_raya");
			while(rs.next()){
				valor = rs.getString(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public boolean tabla_model_lista_raya_revision_totales(){
		boolean varlor = false;
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("exec sp_init_revision_totales");
			while(rs.next()){
				varlor = rs.getString(1).trim().equals("true") ? true : false;			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return varlor;
	}
	
	public boolean tabla_model_lista_raya_autorizacion(){
		boolean varlor = false;
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("exec sp_autorizacion");
			while(rs.next()){
				varlor = rs.getString(1).trim().equals("true") ? true : false;			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return varlor;
	}
	
	public Object[][] tabla_model_alimentacion_totales(){
		String query_lista = "exec sp_select_captura_totales_nomina";
		Object[][] matriz = new Object[get_filas(query_lista)][10];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = "   "+rs.getString(1);
				matriz[i][1] = rs.getString(2).trim().equals("0.0") ? "" : Float.parseFloat(rs.getString(2).trim());
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_model_alimentacion_denominaciones(){
		String query_lista = "exec sp_select_denominaciones";
		Object[][] matriz = new Object[get_filas(query_lista)][10];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = "   "+rs.getString(1);
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = "   "+rs.getString(4);
				matriz[i][4] = rs.getString(5).trim().equals("0.0") ? "" : Float.parseFloat(rs.getString(5).trim());
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_model_alimentacion_denominaciones_modificar(String folio_corte){
		String query_lista = "exec sp_select_tabla_alimentacion_denominaciones "+folio_corte;
		
		Object[][] matriz = new Object[get_filas(query_lista)][10];
		try {
			Statement stmtl = new Connexion().conexion().createStatement();
			
			ResultSet rs = stmtl.executeQuery(query_lista);

			int i = 0;
			while(rs.next()){
				matriz[i][0] = "   "+rs.getString(1);
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = "   "+rs.getString(4);
				matriz[i][4] = rs.getString(5).toString().trim().equals("0.00") ? "" : Float.parseFloat(rs.getString(5).trim());
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_model_alimentacion_deposito_modificar(String folio_corte){
		String query_lista = "exec sp_select_tabla_alimentacion_deposito "+folio_corte;
		
		Object[][] matriz = new Object[get_filas(query_lista)][2];
		try {
			Statement stmtl = new Connexion().conexion().createStatement();
			
			ResultSet rs = stmtl.executeQuery(query_lista);

			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getString(1).toString().trim();
				matriz[i][1] = rs.getString(2).toString().trim().equals("0.00") ? "" : rs.getString(2).toString().trim();
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_model_filtro_cuadrante(){
		String query_lista = "exec sp_select_filtro_relacion_empleados_en_cuadrantes";
		
		Object[][] matriz = new Object[get_filas(query_lista)][2];
		try {
			Statement stmtl = new Connexion().conexion().createStatement();
			
			ResultSet rs = stmtl.executeQuery(query_lista);

			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getString(1).toString().trim()+"  ";
				matriz[i][1] = "  "+rs.getString(2).toString().trim();
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_filtro_empleados_en_cuadrantes(){
		String query_lista = "exec sp_select_filtro_empleados_en_cuadreantes";
		
		Object[][] matriz = new Object[get_filas(query_lista)][2];
		try {
			Statement stmtl = new Connexion().conexion().createStatement();
			
			ResultSet rs = stmtl.executeQuery(query_lista);

			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getString(1).toString().trim()+"  ";
				matriz[i][1] = "  "+rs.getString(2).toString().trim();
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public boolean tabla_model_lista_raya_init_totales_nomina(){
		boolean varlor = false;
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("exec sp_init_totales_nomina");
			while(rs.next()){
				varlor = rs.getString(1).trim().equals("true") ? true : false;			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return varlor;
	}
	
	public Object[][] filtro_actividad(){
		String query_lista = "exec sp_filtro_actividad";
		Object[][] matriz = new Object[get_filas(query_lista)][3];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getInt(1)+" ";
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public Object[][] tabla_model_respuesta(String nombre){
		String query_lista = "exec sp_select_tabla_respuesta '"+nombre+"'";
		Object[][] matriz = new Object[get_filas(query_lista)][2];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getInt(1)+" ";
				matriz[i][1] = "   "+rs.getString(2);

				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public int get_filas(String sentencia){
		int filas = 0;
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(sentencia);
			while(rs.next())
				filas++;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	
	public double Decimal(double x){
		String numeroString = x+"";
		String[] dec = numeroString.split("\\.");
	
		double valor = Integer.parseInt(dec[0]);
		double decimal = Double.parseDouble("."+dec[1]);
		
		if(decimal <= 0.25){
			return valor;
		}
		if(decimal > 0.25 && decimal <= 0.74){
			return valor + 0.5;
		}
		if(decimal >= 0.75 && decimal <= .9){
			return valor + 1;
		}
		return valor;
	}

	public boolean Lista_Raya_Obtener(int parseInt) {
		String query = "update tb_folio_lista_raya_pasada set folio=?";
		Connection con = new Connexion().conexion();
		
		try {
			
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
		
			pstmt.setInt(1, parseInt);
	
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
	
	public Object[][] tabla_model_filtro_gafetes(){
		String query_lista = "exec sp_select_empleados_gafete";
		Object[][] matriz = new Object[get_filas(query_lista)][5];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getInt(1)+" ";
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = "   "+rs.getString(4);
				matriz[i][4] = "   "+rs.getString(5);
				
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	public Object[][] tabla_model_filtro_Obtener_Emp_imprimir_cuadrantes(){
		Obj_Usuario usuario = new Obj_Usuario().LeerSession();
		System.out.println(usuario.getNombre_completo());
		String query_lista = "exec sp_select_impresion_cuadrante "+"'"+usuario.getNombre_completo()+"'" ;
		
		Object[][] matriz = new Object[get_filas(query_lista)][6];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getInt(1)+" ";
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = "   "+rs.getString(4);
				matriz[i][4] = "   "+rs.getString(5);
				matriz[i][5] = "   "+rs.getString(6);
				
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	

public boolean Guardar_Folio_de_Empleado_Imprimir_Cuadrante(int Folio) {
    
	String query = "exec sp_crear_tb_folio_impresion_cuadrante ?";
	Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
	
		try {
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(query);
		
		pstmt.setInt(1,Folio);
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
	public Object[][] tabla_model_checador(){
		String query_lista = "exec sp_select_tabla_checador";
		Object[][] matriz = new Object[get_filas(query_lista)][9];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = "   "+rs.getString(1);
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = "   "+rs.getString(4);
				matriz[i][4] = "   "+rs.getString(5);
				matriz[i][5] = "   "+rs.getString(6);
				matriz[i][6] = "   "+rs.getString(7);
				matriz[i][7] = "   "+rs.getString(8);
				matriz[i][8] = "   "+rs.getString(9);
				
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return matriz; 
	}
	
	public boolean tablas_status(String nombre){
		boolean varlor = false;
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("exec sp_status_tabla_cuadrantes '" + nombre + "';");
			while(rs.next()){
				varlor = rs.getString(1).trim().equals("true") ? true : false;			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return varlor;
	}

}




	
