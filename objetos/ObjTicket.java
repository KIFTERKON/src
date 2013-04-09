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

	public boolean guardar(){ 
		if(new Archivos().escribirTicket(this))
			return true;
		else
			return false;
	}
	

}