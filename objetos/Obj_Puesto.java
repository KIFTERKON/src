package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Puesto {
	private int folio;
	private String puesto;
	private String abreviatura;
	private boolean status;

	public Obj_Puesto(){
		this.folio=0; this.puesto=""; this.abreviatura =""; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
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
	
	public String[] Combo_Jefatura(){ 
		try {
			return new Cargar_Combo().jefatu("tb_Jefatura");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	
	
	public String[] Combo_Puesto(){ 
		try {
			return new Cargar_Combo().Puesto("tb_puesto");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public Obj_Puesto buscar(int folio){
		try {
			return new BuscarSQL().Puesto(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Puesto(this); }
	
	public Obj_Puesto buscar_nuevo(){
		try {
			return new BuscarSQL().Puesto_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Puesto(this,folio); }
	
	public Obj_Puesto buscar_pues(String nombre){
		try{
			return new BuscarSQL().Pues_buscar(nombre); 
		} catch(SQLException e){
			
		}
		return null;
	}	
	
	public Obj_Puesto buscar_pues(int folio){
		try{
			return new BuscarSQL().Pues_buscar(folio); 
		} catch(SQLException e){
			
		}
		return null;
	}	
}
