package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;



public class Obj_Equipo_Trabajo {
	
	private int folio;
	private String descripcion;
	private float valor;
	private Boolean status;
	
	public Obj_Equipo_Trabajo(){
		this.folio=0; this.descripcion=""; this.valor=0; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Obj_Equipo_Trabajo buscar(int folio){
		try {
			return new BuscarSQL().Eq_Tabajo(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Eq_Trabajo(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Eq_Trabajo(this,folio); }
	
	public Obj_Equipo_Trabajo buscar_nuevo(){
		try {
			return new BuscarSQL().Eq_Trabajo_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
