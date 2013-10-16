package objetos;

import datos.Archivos;

public class ObjTicket 
{
	private String izagar;
	private String talon;
	private String folio_emp;
	private String empleado;
	private String puesto;
	private String folio_corte;
	private String establecimineto;
	private String fecha;
	private String asignacion;
	private String tabla;
	private String corte_sistema;
	private String deposito;
	private String efectivo;
	private String diferencia;
	private String tiempo_aire;
	private String resivo_luz;
	

	public ObjTicket()
	{
		
	}

	public String getIzagar() {
		return izagar;
	}

	public void setIzagar(String izagar) {
		this.izagar = izagar;
	}

	public String getTalon() {
		return talon;
	}

	public void setTalon(String talon) {
		this.talon = talon;
	}

	public String getFolio_emp() {
		return folio_emp;
	}

	public void setFolio_emp(String folioEmp) {
		folio_emp = folioEmp;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}


	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getFolio_corte() {
		return folio_corte;
	}

	public void setFolio_corte(String folioCorte) {
		folio_corte = folioCorte;
	}

	public String getEstablecimineto() {
		return establecimineto;
	}

	public void setEstablecimineto(String establecimineto) {
		this.establecimineto = establecimineto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getAsignacion() {
		return asignacion;
	}


	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getCorte_sistema() {
		return corte_sistema;
	}

	public void setCorte_sistema(String corteSistema) {
		corte_sistema = corteSistema;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(String efectivo) {
		this.efectivo = efectivo;
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public String getTiempo_aire() {
		return tiempo_aire;
	}

	public void setTiempo_aire(String tiempo_aire) {
		this.tiempo_aire = tiempo_aire;
	}

	public String getResivo_luz() {
		return resivo_luz;
	}

	public void setResivo_luz(String resivo_luz) {
		this.resivo_luz = resivo_luz;
	}

	public boolean guardar(){ 
		if(new Archivos().escribirTicket(this))
			return true;
		else
			return false;
	}
	

}