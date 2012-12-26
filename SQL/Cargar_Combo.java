package SQL;

import java.sql.ResultSet;


public class Cargar_Combo extends Connexion{
		
	@SuppressWarnings("unchecked")
	public String[] Establecimiento(String tabla){
		String query = "select nombre from " + tabla;
		try {
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				miVector.add(rs.getString("nombre"));				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
	public String[] Puesto(String tabla){
		String query = "select nombre from " + tabla;
		try {
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				miVector.add(rs.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
	public String[] Sueldo(String tabla){
		String query = "select sueldo from " + tabla;
		try {
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				miVector.add(rs.getString("sueldo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
	public String[] Bono(String tabla){
		String query = "select bono from " + tabla;
		try {
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				miVector.add(rs.getString("bono"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
	public String[] Permiso(String tabla){
		String query = "select nombre from " + tabla;
		try {
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				miVector.add(rs.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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