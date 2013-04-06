package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Divisa_Y_TipoDeCambio {
	private int folio;
	private String nombre_divisas;
	private float valor;
	private boolean status;

	public Obj_Divisa_Y_TipoDeCambio(){
		this.folio=0; this.nombre_divisas=""; this.valor=0; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getNombre() {
		return nombre_divisas;
	}

	public void setNombre(String nombre_divisas) {
		this.nombre_divisas = nombre_divisas;
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
	
	public Obj_Divisa_Y_TipoDeCambio buscar(int folio){
		try {
			return new BuscarSQL().divisas(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Divisas(this); }
	
	public Obj_Divisa_Y_TipoDeCambio buscar_nuevo(){
		try {
			return new BuscarSQL().Divisas_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Divisas(this,folio); }
	
	public Obj_Divisa_Y_TipoDeCambio buscar_divisas(String nombre){
		try{
			return new BuscarSQL().Divisas_buscar(nombre); 
		} catch(SQLException e){
		}
		return null;
	}	
}