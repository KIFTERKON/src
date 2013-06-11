package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Temporada {
	int folio;
	String descripcion;
	String fecha_in;
	String fecha_fin;
	String dia;
	boolean status;
	
	public Obj_Temporada(){
		this.folio=0; this.descripcion=""; this.fecha_in=""; this.fecha_fin=""; this.dia="";
		this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getDescripcion() {
		return descripcion.toUpperCase();
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
	}

	public String getFecha_in() {
		return fecha_in;
	}

	public void setFecha_in(String fecha_in) {
		this.fecha_in = fecha_in;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int nuevo(){
		return new BuscarSQL().TemporadaNuevo();
	}
	
	public Obj_Temporada buscar(int folio){ 
		try {
			return new BuscarSQL().TemporadaBuscar(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().TemporadaGuardar(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().TemporadaActualizar(this,folio); }
	
	public String[] Combo_Temporada() {
		try {
			return new Cargar_Combo().Temporada("tb_temporada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
