package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;



public class Obj_Auto_Finanzas {
	private boolean autorizar;
	
	public Obj_Auto_Finanzas(){
		this.autorizar=false;
	}

	public boolean isAutorizar() {
		return autorizar;
	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}
	
	public Obj_Auto_Finanzas buscar(){
		try {
			return new BuscarSQL().Autorizar_Finanzas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(){ return new ActualizarSQL().Autorizar_Finanzas(this); }	
	
}
