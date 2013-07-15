package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
