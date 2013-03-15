package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Revision_Lista_Raya {
	
	private int numero_lista;
	private boolean checado;
	private int folio;
	private int folio_empleado;
	private String nombre_completo;
	private String establecimiento;
	private float sueldo;
	private float p_bono_complementario;
	private float saldo_prestamo_inicial;
	private float d_prestamo;
	private float saldo_final;
	private float d_fuente_sodas;
	private float d_puntualidad;
	private float d_faltas;
	private float d_asistencia;
	private float d_cortes;
	private float d_infonavit;
	private float d_banamex;
	private float d_banorte;
	private float d_extra;
	private float p_dias_extra;
	private float p_bono_extra;
	private float a_pagar;
	private String observasion_i;
	private String observasion_ii;
	private int status;
	
	public Obj_Revision_Lista_Raya(){
		this.folio=0; numero_lista=0; checado=false; folio_empleado=0; nombre_completo=""; establecimiento=""; sueldo=0;
		this.p_bono_complementario=0; saldo_prestamo_inicial=0; d_prestamo=0; saldo_final=0; d_fuente_sodas=0; d_puntualidad=0;
		this.d_faltas=0; d_asistencia=0; d_cortes=0; d_infonavit=0; d_banamex=0; d_banorte=0; d_extra = 0; p_dias_extra=0;
		this.p_bono_extra=0; a_pagar=0; observasion_i=""; observasion_ii=""; status=0;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getNumero_lista() {
		return numero_lista;
	}

	public void setNumero_lista(int numeroLista) {
		numero_lista = numeroLista;
	}

	public boolean isChecado() {
		return checado;
	}

	public void setChecado(boolean checado) {
		this.checado = checado;
	}

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folioEmpleado) {
		folio_empleado = folioEmpleado;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombreCompleto) {
		nombre_completo = nombreCompleto;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public float getP_bono_complementario() {
		return p_bono_complementario;
	}

	public void setP_bono_complementario(float pBonoComplementario) {
		p_bono_complementario = pBonoComplementario;
	}

	public float getSaldo_prestamo_inicial() {
		return saldo_prestamo_inicial;
	}

	public void setSaldo_prestamo_inicial(float saldoPrestamoInicial) {
		saldo_prestamo_inicial = saldoPrestamoInicial;
	}

	public float getD_prestamo() {
		return d_prestamo;
	}

	public void setD_prestamo(float dPrestamo) {
		d_prestamo = dPrestamo;
	}

	public float getSaldo_final() {
		return saldo_final;
	}

	public void setSaldo_final(float saldoFinal) {
		saldo_final = saldoFinal;
	}

	public float getD_fuente_sodas() {
		return d_fuente_sodas;
	}

	public void setD_fuente_sodas(float dFuenteSodas) {
		d_fuente_sodas = dFuenteSodas;
	}

	public float getD_puntualidad() {
		return d_puntualidad;
	}

	public void setD_puntualidad(float dPuntualidad) {
		d_puntualidad = dPuntualidad;
	}

	public float getD_faltas() {
		return d_faltas;
	}

	public void setD_faltas(float dFaltas) {
		d_faltas = dFaltas;
	}

	public float getD_asistencia() {
		return d_asistencia;
	}

	public void setD_asistencia(float dAsistencia) {
		d_asistencia = dAsistencia;
	}

	public float getD_cortes() {
		return d_cortes;
	}

	public void setD_cortes(float dCortes) {
		d_cortes = dCortes;
	}

	public float getD_infonavit() {
		return d_infonavit;
	}

	public void setD_infonavit(float dInfonavit) {
		d_infonavit = dInfonavit;
	}

	public float getD_banamex() {
		return d_banamex;
	}

	public void setD_banamex(float dBanamex) {
		d_banamex = dBanamex;
	}

	public float getD_banorte() {
		return d_banorte;
	}

	public void setD_banorte(float dBanorte) {
		d_banorte = dBanorte;
	}

	public float getD_extra() {
		return d_extra;
	}

	public void setD_extra(float D_extra) {
		d_extra = D_extra;
	}

	public float getP_dias_extra() {
		return p_dias_extra;
	}

	public void setP_dias_extra(float pDiasExtra) {
		p_dias_extra = pDiasExtra;
	}

	public float getP_bono_extra() {
		return p_bono_extra;
	}

	public void setP_bono_extra(float pBonoExtra) {
		p_bono_extra = pBonoExtra;
	}

	public float getA_pagar() {
		return a_pagar;
	}

	public void setA_pagar(float aPagar) {
		a_pagar = aPagar;
	}

	public String getObservasion_i() {
		return observasion_i;
	}

	public void setObservasion_i(String observasionI) {
		observasion_i = observasionI;
	}

	public String getObservasion_ii() {
		return observasion_ii;
	}

	public void setObservasion_ii(String observasionIi) {
		observasion_ii = observasionIi;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Pre_Lista(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Actualizar_Pre_Lista(this,folio); }
	
	public Obj_Revision_Lista_Raya buscar_folio(int nombre_completo){
		try{
			return new BuscarSQL().Lista_buscar_folio(nombre_completo); 
		} catch(SQLException e){
			
		}
		return null;
	}
	
	public boolean guardar_lista(){ return new GuardarSQL().Guardar(this); }
	
	public boolean borrar(){ return new GuardarSQL().lista_Imprimir(this); }
	public boolean imprimir_lista(){ return new GuardarSQL().Guardar_Imprimir(this); }
	
	public Obj_Revision_Lista_Raya buscar(int numero_lista){ 
		try {
			return new BuscarSQL().ListaR(numero_lista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}	
}
