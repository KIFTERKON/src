package objetos;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Alimentacion_Totales {
	
	int folio_raya;
	String establecimiento;
	float nomina;
	
	public Obj_Alimentacion_Totales(){
		folio_raya=0; establecimiento=""; nomina=0;
	}

	public int getFolio_raya() {
		return folio_raya;
	}

	public void setFolio_raya(int folio_raya) {
		this.folio_raya = folio_raya;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public float getNomina() {
		return nomina;
	}

	public void setNomina(float nomina) {
		this.nomina = nomina;
	}
	
	public boolean guardar(){ 
		return new GuardarSQL().Guardar_Costos_Totales(this); 
	}
	
	public boolean actualizar(){ 
		return new ActualizarSQL().ActualizarCostosTotales(this); 
	}
	
	public String[][] EstablecimientoMatriz(){
		return new BuscarSQL().getEstablecimientoLista();
	}
}
