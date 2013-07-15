package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;



public class Obj_Tipo_Banco {
	private int folio;
	private String banco;
	private String abreviatura;
	private boolean status;

	public Obj_Tipo_Banco(){
		this.folio=0; this.banco=""; this.abreviatura =""; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
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
	
	public String[] Combo_Puesto(){ 
		try {
			return new Cargar_Combo().Puesto("tb_puesto");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public Obj_Tipo_Banco buscar(int folio){
		try {
			return new BuscarSQL().Tipo_Banco(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Tipo_Banco(this); }
	
	public Obj_Tipo_Banco buscar_nuevo(){
		try {
			return new BuscarSQL().Tipo_Banco_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Tipo_Banco(this,folio); }
	
	public Obj_Tipo_Banco buscar_pues(String nombre){
		try{
			return new BuscarSQL().Banco_Buscar(nombre); 
		} catch(SQLException e){
			
		}
		return null;
	}	
	
	public Obj_Tipo_Banco buscar_pues(int folio){
		try{
			return new BuscarSQL().buscar_Banck(folio); 
		} catch(SQLException e){
			
		}
		return null;
	}	
	
	public String[] Combo_Tipo_Banco(){ 
		try {
			return new Cargar_Combo().Tipo_Banco("tb_tipo_banco");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
