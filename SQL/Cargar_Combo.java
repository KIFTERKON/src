package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Cargar_Combo {
	Connexion con = new Connexion();
	@SuppressWarnings("rawtypes")
	Vector miVector = new Vector();
	
	@SuppressWarnings("unchecked")
	public String[] menus() throws SQLException{
		String query = "select nombre from tb_menus";
		
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Seleciona un Menu");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Establecimiento(String tabla) throws SQLException{
		String query = "select nombre from " + tabla + " where status = 1 order by nombre asc";
		
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Todos");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Establecimiento_Caja() throws SQLException{
		String query = "select " + 
					           "distinct tb_establecimiento.nombre as establecimiento " +
					   "from tb_empleado " +
					   "right join tb_establecimiento on tb_establecimiento.folio = tb_empleado.establecimiento_id " +
					   "right join tb_puesto on tb_puesto.folio = tb_empleado.puesto_id " +
					   "where tb_empleado.puesto_id=32 order by tb_establecimiento.nombre asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Todos");
				}
				miVector.add(rs.getString("establecimiento"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}


	@SuppressWarnings("unchecked")
	public String[] Establecimiento_Empleado(String tabla) throws SQLException{
		String query = "select nombre from " + tabla + " order by nombre asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Establecimiento");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Ponderacion(String tabla) throws SQLException{
		String query = "select descripcion from " + tabla + " order by descripcion asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Seleccione Pondercion");
				}
				miVector.add(rs.getString("descripcion"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] opRespuesta(String tabla) throws SQLException{
		String query = "select nombre from " + tabla + " order by nombre asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Seleccione una opción de Respuesta");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Jefatura(String tabla) throws SQLException{
		String query = "select descripcion from " + tabla + " order by descripcion asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona una Jefatura");
				}
				miVector.add(rs.getString("descripcion"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] EquipoTrabajo(String tabla) throws SQLException{
		String query = "select descripcion from " + tabla + " order by descripcion asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Equipo de Trabajo");
				}
				miVector.add(rs.getString("descripcion"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Puesto(String tabla) throws SQLException{
		String query = "select nombre from " + tabla+" order by nombre asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Puesto");
				}
				miVector.add(rs.getString("nombre").toUpperCase());
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] jefatu(String tabla) throws SQLException{
		String query = "select descripcion from " + tabla+" order by descripcion asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona una Jefatura");
				}
				miVector.add(rs.getString("descripcion").toUpperCase());
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
	}
	
	@SuppressWarnings("unchecked")
	public String[] Divisas(String tabla) throws SQLException{
		String query = "select nombre_divisas from " + tabla+" order by nombre_divisas asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un valor");
				}
				miVector.add(rs.getString("nombre_divisas").toUpperCase());
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Denominaciones(String tabla) throws SQLException{
		String query = "select nombre_divisas from " + tabla+" order by nombre_divisas asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un valor");
				}
				miVector.add(rs.getString("nombre_divisas").toUpperCase());
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Sueldo(String tabla) throws SQLException{
		String query = "select sueldo from " + tabla;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Sueldo");
				}
				miVector.add(rs.getString("sueldo"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Bono(String tabla) throws SQLException {
		String query = "select bono from " + tabla +" order by bono asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Bono");
				}
				miVector.add(rs.getString("bono"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;	
	}
	
	@SuppressWarnings("unchecked")
	public String[] Permiso(String tabla) throws SQLException{
		String query = "select nombre from " + tabla;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				miVector.add(rs.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){	stmt.close(); }
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;	
	}
	
	@SuppressWarnings("unchecked")
	public String[] Rango_Prestamos(String tabla) throws SQLException {
		String query = "select minimo,maximo from " + tabla;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Rango de Prestamo");
				}
				miVector.add(Math.rint(rs.getDouble("minimo")*100)/100+" - "+Math.rint(rs.getDouble("maximo")*100)/100);
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Turno(String tabla) throws SQLException{
		String query = "select nombre from " + tabla;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Turno");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Turno2(String tabla) throws SQLException{
		String query = "select nombre from " + tabla;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Turno");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Tipo_Banco(String tabla) throws SQLException{
		String query = "select nombre from " + tabla;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Banco");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	
	@SuppressWarnings("unchecked")
	public String[] Respuesta(String tabla) throws SQLException{
		String query = "select nombre as nombre from " + tabla + " order by nombre asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona una Opción");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Atributo(String tabla) throws SQLException{
		String query = "select descripcion from " + tabla + " order by descripcion asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Atributo");
				}
				miVector.add(rs.getString("descripcion"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Nivel_Critico(String tabla) throws SQLException{
		String query = "select descripcion from " + tabla + " order by descripcion asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Nivel Critico");
				}
				miVector.add(rs.getString("descripcion"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	@SuppressWarnings("unchecked")
	public String[] Temporada(String tabla) throws SQLException{
		String query = "select descripcion from " + tabla + " order by descripcion asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona una Temporada");
				}
				miVector.add(rs.getString("descripcion"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	
	@SuppressWarnings("unchecked")
	public String[] Establecimiento_Empleado_Entysal(String tabla) throws SQLException{
		String query = "select nombre from " + tabla + " order by nombre asc";
		Statement stmt = null;
		try {
			stmt = new Connexion().conexionDB_DOS().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Establecimiento");
				}
				miVector.add(rs.getString("nombre"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}

	
	@SuppressWarnings("unchecked")
	public String[] niveljerarquico(String tabla) throws SQLException{
		String query = "select descripcion from " + tabla + " order by descripcion asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("Selecciona un Nivel Jerarquico");
				}
				miVector.add(rs.getString("descripcion"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
	
	
	@SuppressWarnings("unchecked")
	public String[] ComboALimentacionMultiple(int actividad) throws SQLException{
		String query = "exec sp_select_combo_opciones_respuesta "+actividad;
		
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int j=0;
			while(rs.next()){
				if(j == 0){
					miVector.add("   Respuestas");
				}
				miVector.add("   "+rs.getString("descripcion"));
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		int i=0;
		String[] pila= new String[miVector.size()];
		
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;
			
	}
}
