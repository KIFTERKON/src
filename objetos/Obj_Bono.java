package objetos;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Bono {
	private int folio;
	private float bono;
	private String abreviatura;
	private boolean status;
	
	public Obj_Bono(){
		this.folio=0; this.bono=0; this.abreviatura=""; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public float getBono() {
		return bono;
	}

	public void setBono(float bono) {
		this.bono = bono;
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
	public String[] Combo_Bono(){ return new Cargar_Combo().Bono("tb_bono"); }
	
	public Obj_Bono buscar(int folio){ return new BuscarSQL().Bono(folio); }
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Bono(this); }
	
	public Obj_Bono buscar_nuevo(){ return new BuscarSQL().Bono_Nuevo(); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Bono(this,folio); }
	
}
