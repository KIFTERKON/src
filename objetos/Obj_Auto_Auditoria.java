package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;

public class Obj_Auto_Auditoria {
	private boolean autorizar;
	
	public Obj_Auto_Auditoria(){
		this.autorizar=false;
	}

	public boolean isAutorizar() {
		return autorizar;
	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}
	
	public Obj_Auto_Auditoria buscar(){
		try {
			return new BuscarSQL().Autorizar_Audi();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(){ return new ActualizarSQL().Auditoria(this); }	
	
}
