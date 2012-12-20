package catalogos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SQL.Connexion;

public class Matriz {
	
	public static void main(String args[]){
		
		String Nombre = "Jose Mario Rodrìguez Sanchez";
		String[][] algo = getMatriz(Nombre);
		for(int i=0; i<algo.length; i++){
			for(int j=0; j<3; j++){
				System.out.print(algo[i][j]+" "); 
			}
			System.out.println();
		}
	}
	
	public static String[][] getMatriz(String NombreCompleto){
		String qry = "select folio,fecha,cantidad from tb_fuente_sodas_rh where nombre_completo='"+NombreCompleto+"'";
		
		String[][] Matriz = new String[getFilas(qry)][3];
		Connection conn = Connexion.conexion();
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(qry);
			int i=0;
			while(rs.next()){
				Matriz[i][0] = rs.getString(1).trim();
				Matriz[i][1] = rs.getString(2).trim();
				Matriz[i][2] = rs.getString(3).trim();
				i++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz; 
	}
	
	public static int getFilas(String qry){
		int filas=0;
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(qry);
			
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}

}
