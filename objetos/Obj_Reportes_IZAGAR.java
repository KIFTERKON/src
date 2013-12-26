package objetos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import SQL.Connexion;

public class Obj_Reportes_IZAGAR {
	private String Asignacion;

	
	public Obj_Reportes_IZAGAR(){
		this.Asignacion=""; 
	}


	public String getAsignacion() {
		return Asignacion;
	}


	public void setAsignacion(String asignacion) {
		Asignacion = asignacion;
	}
	
	public boolean  guardar(Obj_Reportes_IZAGAR a) {
		String query = "update IZAGAR set asignacion= ?";
		
		Connection con = new Connexion().conexionDB_DOS();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, a.getAsignacion());		
			pstmt.executeUpdate();
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
