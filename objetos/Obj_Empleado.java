package objetos;

import java.io.File;
import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;



public class Obj_Empleado {
	
//	datos personales	
	private int folio;
	private String no_checador;
	private String nombre;
	private String ap_paterno;
	private String ap_materno;
	private String fecha_nacimiento;
	private String calle;
	private String colonia;
	private String poblacion;
	private String telefono_familiar;
	private String telefono_propio;
	private String telefono_cuadrante;
	private String rfc;
	private String curp;
	private int sexo;
	private File foto;
	
//	laboral
	private int horario;
	private int horario2;
	private int status_h1;
	private int status_h2;
	private int status_rotativo;
//	dependen del horario activo
	private String descanso;
	private String dobla;
	private String fecha_ingreso;
	private int status;
	private String fecha_baja;
	private boolean cuadrante_parcial;
	private int departameto;
	private String imss;
	private int status_imss;
	private String numero_infonavit;
	private int establecimiento;
	private int puesto;
	
//	percepciones y deducciones
	private float salario_diario;
	private float salario_diario_integrado;
	private String forma_pago;
	private int sueldo;
	private int bono;
	private int prestamo;
	private float pension_alimenticia;
	private float infonavit;
	private String targeta_nomina;
	private int tipo_banco;
	private boolean gafete;
	private boolean fuente_sodas;
	private String observasiones;
	private String fecha_actualizacion;
	
	public Obj_Empleado(){
		
		folio=0; no_checador=""; nombre=""; ap_paterno=""; ap_materno=""; fecha_nacimiento=""; calle=""; colonia=""; poblacion=""; telefono_familiar="";
		telefono_propio=""; telefono_cuadrante=""; rfc=""; curp=""; sexo=0; foto=null;
		
		horario=0; horario2=0; status_h1=0; status_h2=0; status_rotativo=0; descanso=""; dobla=""; fecha_ingreso=""; status=0; fecha_baja=""; cuadrante_parcial=false;
		departameto=0; imss=""; status_imss=0; numero_infonavit=""; establecimiento=0; puesto=0;
		
		salario_diario=0; salario_diario_integrado=0; forma_pago=""; sueldo=0; bono=0; prestamo=0; pension_alimenticia=0; infonavit=0; targeta_nomina="";
		tipo_banco=0; gafete=false; fuente_sodas=false; observasiones="";
		
	}
	
	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getNo_checador() {
		return no_checador;
	}

	public void setNo_checador(String no_checador) {
		this.no_checador = no_checador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAp_paterno() {
		return ap_paterno;
	}

	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}

	public String getAp_materno() {
		return ap_materno;
	}

	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getColionia() {
		return colonia;
	}

	public void setColionia(String colonia) {
		this.colonia = colonia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getTelefono_familiar() {
		return telefono_familiar;
	}

	public void setTelefono_familiar(String telefono_familiar) {
		this.telefono_familiar = telefono_familiar;
	}

	public String getTelefono_propio() {
		return telefono_propio;
	}

	public void setTelefono_propio(String telefono_propio) {
		this.telefono_propio = telefono_propio;
	}

	public String getTelefono_cuadrante() {
		return telefono_cuadrante;
	}

	public void setTelefono_cuadrante(String telefono_cuadrante) {
		this.telefono_cuadrante = telefono_cuadrante;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	public int getHorario2() {
		return horario2;
	}

	public void setHorario2(int horario2) {
		this.horario2 = horario2;
	}

	public int getStatus_h1() {
		return status_h1;
	}

	public void setStatus_h1(int status_h1) {
		this.status_h1 = status_h1;
	}

	public int getStatus_h2() {
		return status_h2;
	}

	public void setStatus_h2(int status_h2) {
		this.status_h2 = status_h2;
	}

	public int getStatus_rotativo() {
		return status_rotativo;
	}

	public void setStatus_rotativo(int status_rotativo) {
		this.status_rotativo = status_rotativo;
	}

	public String getDescanso() {
		return descanso;
	}

	public void setDescanso(String descanso) {
		this.descanso = descanso;
	}

	public String getDobla() {
		return dobla;
	}

	public void setDobla(String dobla) {
		this.dobla = dobla;
	}

	public String getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public boolean isCuadrante_parcial() {
		return cuadrante_parcial;
	}

	public void setCuadrante_parcial(boolean cuadrante_parcial) {
		this.cuadrante_parcial = cuadrante_parcial;
	}

	public int getDepartameto() {
		return departameto;
	}

	public void setDepartameto(int departameto) {
		this.departameto = departameto;
	}

	public String getImss() {
		return imss;
	}

	public void setImss(String imss) {
		this.imss = imss;
	}

	public int getStatus_imss() {
		return status_imss;
	}

	public void setStatus_imss(int status_imss) {
		this.status_imss = status_imss;
	}

	public String getNumero_infonavit() {
		return numero_infonavit;
	}

	public void setNumero_infonavit(String numero_infonavit) {
		this.numero_infonavit = numero_infonavit;
	}

	public int getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(int establecimiento) {
		this.establecimiento = establecimiento;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public float getSalario_diario() {
		return salario_diario;
	}

	public void setSalario_diario(float salario_diario) {
		this.salario_diario = salario_diario;
	}

	public float getSalario_diario_integrado() {
		return salario_diario_integrado;
	}

	public void setSalario_diario_integrado(float salario_diario_integrado) {
		this.salario_diario_integrado = salario_diario_integrado;
	}

	public String getForma_pago() {
		return forma_pago;
	}

	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public int getBono() {
		return bono;
	}

	public void setBono(int bono) {
		this.bono = bono;
	}

	public int getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(int prestamo) {
		this.prestamo = prestamo;
	}

	public float getPension_alimenticia() {
		return pension_alimenticia;
	}

	public void setPension_alimenticia(float pension_alimenticia) {
		this.pension_alimenticia = pension_alimenticia;
	}

	public float getInfonavit() {
		return infonavit;
	}

	public void setInfonavit(float infonavit) {
		this.infonavit = infonavit;
	}

	public String getTargeta_nomina() {
		return targeta_nomina;
	}

	public void setTargeta_nomina(String targeta_nomina) {
		this.targeta_nomina = targeta_nomina;
	}

	public int getTipo_banco() {
		return tipo_banco;
	}

	public void setTipo_banco(int tipo_banco) {
		this.tipo_banco = tipo_banco;
	}

	public boolean isGafete() {
		return gafete;
	}

	public void setGafete(boolean gafete) {
		this.gafete = gafete;
	}

	public boolean isFuente_sodas() {
		return fuente_sodas;
	}

	public void setFuente_sodas(boolean fuente_sodas) {
		this.fuente_sodas = fuente_sodas;
	}

	public String getObservasiones() {
		return observasiones;
	}

	public void setObservasiones(String observasiones) {
		this.observasiones = observasiones;
	}

	public String getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(String fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public boolean guardar(){ return new GuardarSQL().Guardar_Empleado(this); }
	
	public Obj_Empleado buscar(int folio){ 
		try {
			return new BuscarSQL().Empleado(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Empleado(this,folio); }
	
	public Obj_Empleado buscar_nuevo() throws SQLException{ return new BuscarSQL().Empleado_Nuevo(); }
	
	public boolean existe_foto(int folio){
		try {
			return new BuscarSQL().isFoto(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean nombre_disponible(String nombre){ return new BuscarSQL().nombre_disponible(nombre); }
	
	
	public String[] Combo_Empleado(){ 
		try {
			return new Cargar_Combo().Empleado("tb_empleado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	public boolean insertar(int folio,String t_entrada){return new GuardarSQL().Insert_Empleado(folio,t_entrada);}
	
	public boolean insertar_comida(int folio,String t_entrada,int tipo_salida_comer){
		
		return new GuardarSQL().Insert_Empleado_Comida(folio,t_entrada,tipo_salida_comer);
		
	}
}
