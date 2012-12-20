package objetos;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Sueldo {
	private int folio;
	private float sueldo;
	private String abreviatura;
	private boolean status;
	
	public Obj_Sueldo(){
		this.folio=0; this.sueldo=0; this.abreviatura=""; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String[] Combo_Sueldo(){ return new Cargar_Combo().Sueldo("tb_sueldo"); }
	
	public Obj_Sueldo buscar(int folio){ return new BuscarSQL().Sueldo(folio); }
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Sueldo(this); }
	
	public Obj_Sueldo buscar_nuevo(){ return new BuscarSQL().Sueldo_Nuevo(); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Sueldo(this,folio); }
	
}
