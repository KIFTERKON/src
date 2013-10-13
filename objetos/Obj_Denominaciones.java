package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;



public class Obj_Denominaciones {
	private int folio;
	private String denominacion;
	private float valor_denominacion;
	private String moneda;
	private boolean status;

	public Obj_Denominaciones(){
		this.folio=0; this.denominacion=""; this.valor_denominacion=0; this.moneda=""; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public float getValor_denominacion() {
		return valor_denominacion;
	}

	public void setValor_denominacion(float valor_denominacion) {
		this.valor_denominacion = valor_denominacion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String[] Combo_Denominaciones(){ 
		try {
			return new Cargar_Combo().Denominaciones("tb_divisas_tipo_de_cambio");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public Obj_Denominaciones buscar(int folio){
		try {
			return new BuscarSQL().denominaciones(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Denominaciones(this); }
	
	public Obj_Denominaciones buscar_nuevo(){
		try {
			return new BuscarSQL().Denominaciones_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Denominaciones(this,folio); }
	
//	public Obj_Denominaciones buscar_denominaciones(String nombre){
//		try{
//			return new BuscarSQL().Denominaciones_buscar(nombre); 
//		} catch(SQLException e){
//		}
//		return null;
//	}	
}