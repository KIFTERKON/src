package objetos;

import SQL.GuardarSQL;

public class Obj_Importar_Voucher {

	private int Folio;
	private int Contrato;
	private String F_transaccion;
	private String H_transaccion;
	private String No_codigo;
	private String Leyenda;
	private float Importe;
	private String Terminal;
	private String Cuenta;
	private String Autorizacion;
	private String Tipo_de_cuenta;
	private String F_abono;
	private String Referencia_1;
	private String Referencia_2;
	private String Referencia_3;
	private float Q6;
	private float Importa_cash_back;
	private float Eci;
	private String Control_interno_comercio;
	private String Lote1;
	private String Lote2;
	
	public Obj_Importar_Voucher(){
		this.Folio=0; this.Contrato=0; this.F_transaccion=""; this.H_transaccion=""; this.No_codigo="";
		this.Leyenda=""; this.Importe=0; this.Terminal=""; this.Cuenta=""; this.Autorizacion="";
		this.Tipo_de_cuenta=""; this.F_abono=""; this.Referencia_1=""; this.Referencia_2="";
		this.Referencia_3=""; this.Q6=0; this.Importa_cash_back=0; this.Eci=0; this.Control_interno_comercio="";
		this.Lote1=""; this.Lote2="";
	}

	public int getFolio() {
		return Folio;
	}

	public void setFolio(int folio) {
		Folio = folio;
	}

	public int getContrato() {
		return Contrato;
	}

	public void setContrato(int contrato) {
		Contrato = contrato;
	}

	public String getF_transaccion() {
		return F_transaccion;
	}

	public void setF_transaccion(String f_transaccion) {
		F_transaccion = f_transaccion;
	}

	public String getH_transaccion() {
		return H_transaccion;
	}

	public void setH_transaccion(String h_transaccion) {
		H_transaccion = h_transaccion;
	}

	public String getNo_codigo() {
		return No_codigo;
	}

	public void setNo_codigo(String no_codigo) {
		No_codigo = no_codigo;
	}

	public String getLeyenda() {
		return Leyenda;
	}

	public void setLeyenda(String leyenda) {
		Leyenda = leyenda;
	}

	public float getImporte() {
		return Importe;
	}

	public void setImporte(float importe) {
		Importe = importe;
	}

	public String getTerminal() {
		return Terminal;
	}

	public void setTerminal(String terminal) {
		Terminal = terminal;
	}

	public String getCuenta() {
		return Cuenta;
	}

	public void setCuenta(String cuenta) {
		Cuenta = cuenta;
	}

	public String getAutorizacion() {
		return Autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		Autorizacion = autorizacion;
	}

	public String getTipo_de_cuenta() {
		return Tipo_de_cuenta;
	}

	public void setTipo_de_cuenta(String tipo_de_cuenta) {
		Tipo_de_cuenta = tipo_de_cuenta;
	}

	public String getF_abono() {
		return F_abono;
	}

	public void setF_abono(String f_abono) {
		F_abono = f_abono;
	}

	public String getReferencia_1() {
		return Referencia_1;
	}

	public void setReferencia_1(String referencia_1) {
		Referencia_1 = referencia_1;
	}

	public String getReferencia_2() {
		return Referencia_2;
	}

	public void setReferencia_2(String referencia_2) {
		Referencia_2 = referencia_2;
	}

	public String getReferencia_3() {
		return Referencia_3;
	}

	public void setReferencia_3(String referencia_3) {
		Referencia_3 = referencia_3;
	}

	public float getQ6() {
		return Q6;
	}

	public void setQ6(float q6) {
		Q6 = q6;
	}

	public float getImporta_cash_back() {
		return Importa_cash_back;
	}

	public void setImporta_cash_back(float importa_cash_back) {
		Importa_cash_back = importa_cash_back;
	}

	public float getEci() {
		return Eci;
	}

	public void setEci(float eci) {
		Eci = eci;
	}

	public String getControl_interno_comercio() {
		return Control_interno_comercio;
	}

	public void setControl_interno_comercio(String control_interno_comercio) {
		Control_interno_comercio = control_interno_comercio;
	}

	public String getLote1() {
		return Lote1;
	}

	public void setLote1(String lote1) {
		Lote1 = lote1;
	}

	public String getLote2() {
		return Lote2;
	}

	public void setLote2(String lote2) {
		Lote2 = lote2;
	}

	public boolean Guardar(){ 
		return new GuardarSQL().GuardarImportarVoucher(this); 
	}
	
//	public Obj_Importar_Voucher buscar() {
//		Obj_Conexion_BD config = new Obj_Conexion_BD();
//    	try{
//    		config = new BuscarSQL().Conexion_BD();
//    	}catch(IOException e){
//    		e.printStackTrace();
//    	}
//    	return config;
//  }
}
