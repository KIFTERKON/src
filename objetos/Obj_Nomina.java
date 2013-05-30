package objetos;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Nomina {
	int numero_listaraya;
	String establecimiento;
	String nomina;
	String pago_linea;
	String cheque_nomina;
	String lista_raya;
	String diferencia;
	String fecha;
	
	
	public Obj_Nomina(){
		this.numero_listaraya=0; 		this.establecimiento="";
		this.nomina="";		this.pago_linea="";
		this.cheque_nomina="";		this.lista_raya="";
		this.diferencia="";		this.fecha="";
	}
	
	public int getNumero_listaraya() {
		return numero_listaraya;
	}

	public void setNumero_listaraya(int numero_listaraya) {
		this.numero_listaraya = numero_listaraya;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getNomina() {
		return nomina;
	}

	public void setNomina(String nomina) {
		this.nomina = nomina;
	}

	public String getPago_linea() {
		return pago_linea;
	}

	public void setPago_linea(String pago_linea) {
		this.pago_linea = pago_linea;
	}

	public String getCheque_nomina() {
		return cheque_nomina;
	}

	public void setCheque_nomina(String cheque_nomina) {
		this.cheque_nomina = cheque_nomina;
	}

	public String getLista_raya() {
		return lista_raya;
	}

	public void setLista_raya(String lista_raya) {
		this.lista_raya = lista_raya;
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String[][] MatrizNomina(int Folio){
		return new BuscarSQL().getNomina(Folio);
	}
	
	public int MaxListaRaya(){
		return new BuscarSQL().MaximoListaRaya();
	}
	
	public String[] getTotales(int Folio){
		return new BuscarSQL().getTotalesNomina(Folio);
	}
	
	public String[] getCheque1(int Folio){
		return new BuscarSQL().getTotalesCheque1(Folio);
	}
	
	public String[] getCheque1_ferre(int Folio){
		return new BuscarSQL().getTotalesCheque1Ferre(Folio);
	}
	
	public String[] getCheque1_izacel(int Folio){
		return new BuscarSQL().getTotalesCheque1Izacel(Folio);
	}
	
	public String[] getChequeABC(int Folio){
		return new BuscarSQL().getNominaChequeABC(Folio);
	}
	
	public float getNominaIndividual(String Establecimiento, int lista){
		return new BuscarSQL().getNominaIndividual(Establecimiento, lista);
	}
	
	public float getBancosIndividual(String Establecimiento, int lista){
		return new BuscarSQL().getBancosIndividual(Establecimiento, lista);
	}
	
	public float getListaRayaIndividual(String Establecimiento, int lista){
		return new BuscarSQL().getListaRayaIndividual(Establecimiento, lista);
	}
	
	public float getDiferenciaIndividual(String Establecimiento, int lista){
		return new BuscarSQL().getDiferenciaIndividual(Establecimiento, lista);
	}
	
	public int returnMaximo(){
		return new BuscarSQL().getMaximoNomina();
	}
	
	public boolean Guardar(){ return new GuardarSQL().Guardar(this); }
	
	public boolean Actualizar(String Establecimiento, int Folio){ return new ActualizarSQL().Actualizar(this,Establecimiento,Folio); }
	
//	public boolean Guardar(){
//		return new G
//	}
}
