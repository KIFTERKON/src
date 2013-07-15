package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		Object[][] matriz = new Object[get_filas(query_lista)][10];
		try {
			Statement stmt = new Connexion().conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query_lista);
			
			int i = 0;
			while(rs.next()){
				matriz[i][0] = rs.getInt(1)+" ";
				matriz[i][1] = "   "+rs.getString(2);
				matriz[i][2] = "   "+rs.getString(3);
				matriz[i][3] = Boolean.parseBoolean(rs.getString(4));
				matriz[i][4] = Boolean.parseBoolean(rs.getString(5));
				matriz[i][5] = Integer.parseInt(rs.getString(6));
				matriz[i][6] = Boolean.parseBoolean(rs.getString(7));
				matriz[i][7] = Boolean.parseBoolean(rs.getString(8));
				matriz[i][8] = Integer.parseInt(rs.getString(9));
				matriz[i][9] = Float.parseFloat(rs.getString(10));
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
}
