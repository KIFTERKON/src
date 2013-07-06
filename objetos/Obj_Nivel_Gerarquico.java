package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Nivel_Gerarquico 
{
	private int folio;
	private String descripcion;
	private String puesto_principal;
	private String puesto_dependiente;
	private String puesto;
	private String establecimiento;
	private boolean status;
	
	public Obj_Nivel_Gerarquico()
	{
		this.folio=0; this.descripcion=""; this.puesto_principal=""; this.puesto_dependiente="";
		this.puesto=""; this.status=false;
		
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

	public String getPuesto_principal() {
		return puesto_principal;
	}

	public void setPuesto_principal(String puesto_principal) {
		this.puesto_principal = puesto_principal;
	}

	public String getPuesto_dependiente() {
		return puesto_dependiente;
	}

	public void setPuesto_dependiente(String puesto_dependiente) {
		this.puesto_dependiente = puesto_dependiente;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Nivel_G(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().NivelG(this,folio); }
	
	public Obj_Nivel_Gerarquico buscar(int folio){
		try {
			return new BuscarSQL().Gerarquico(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	

	public Obj_Nivel_Gerarquico buscar_nuevo(){
		try {
			return new BuscarSQL().Gerarquico_nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] Combo_Nivel_Gerarquico(){
		try {
			return new Cargar_Combo().NivelGerarquico("tb_nivel_gerarquico");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	
}
