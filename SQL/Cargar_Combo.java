package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Cargar_Combo {
	Connexion con = new Connexion();
	@SuppressWarnings("unchecked")
	Vector miVector = new Vector();
	
	@SuppressWarnings("unchecked")
	public String[] Establecimiento(String tabla) throws SQLException{
		String query = "select nombre from " + tabla + " order by folio asc";
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
	public String[] Establecimiento_Empleado(String tabla) throws SQLException{
		String query = "select nombre from " + tabla + " order by folio asc";
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
	public String[] Puesto(String tabla) throws SQLException{
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
			while(rs.next()){
				miVector.add(rs.getString("sueldo"));
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
		String query = "select bono from " + tabla;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				miVector.add(rs.getString("bono"));
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
			while(rs.next()){
				miVector.add(Math.rint(rs.getDouble("minimo")*100)/100+" - "+Math.rint(rs.getDouble("maximo")*100)/100);
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
