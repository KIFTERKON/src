package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;



public class Obj_Cuadrante {
	
	int folio;
	String cuadrante;
	String perfil;
	String jefatura;
	String nivel_gerarquico;
	String nivel_critico;
	String equipo_trabajo;
	String establecimiento;
	int domingo;
	int lunes;
	int martes;
	int miercoles;
	int jueves;
	int viernes;
	int sabado;
	int status;

	public Obj_Cuadrante(){
		this.folio = 0;		this.cuadrante = "";	this.perfil = "";	this.jefatura = "";
		this.nivel_gerarquico = "";		this.nivel_critico=""; this.equipo_trabajo = "";	this.establecimiento = "";
		this.domingo = 0;	this.lunes = 0;		this.martes = 0;		this.miercoles = 0;
		this.jueves = 0;		this.viernes = 0;		this.sabado = 0; this.status = 0;

	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getCuadrante() {
		return cuadrante;
	}

	public void setCuadrante(String cuadrante) {
		this.cuadrante = cuadrante;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getJefatura() {
		return jefatura;
	}

	public void setJefatura(String jefatura) {
		this.jefatura = jefatura;
	}

	public String getNivel_gerarquico() {
		return nivel_gerarquico;
	}

	public void setNivel_gerarquico(String nivel_gerarquico) {
		this.nivel_gerarquico = nivel_gerarquico;
	}
	
	public String getNivel_critico() {
		return nivel_critico;
	}

	public void setNivel_critico(String nivel_critico) {
		this.nivel_critico = nivel_critico;
	}

	public String getEquipo_trabajo() {
		return equipo_trabajo;
	}

	public void setEquipo_trabajo(String equipo_trabajo) {
		this.equipo_trabajo = equipo_trabajo;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public int getDomingo() {
		return domingo;
	}

	public void setDomingo(int domingo) {
		this.domingo = domingo;
	}

	public int getLunes() {
		return lunes;
	}

	public void setLunes(int lunes) {
		this.lunes = lunes;
	}

	public int getMartes() {
		return martes;
	}

	public void setMartes(int martes) {
		this.martes = martes;
	}

	public int getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(int miercoles) {
		this.miercoles = miercoles;
	}

	public int getJueves() {
		return jueves;
	}

	public void setJueves(int jueves) {
		this.jueves = jueves;
	}

	public int getViernes() {
		return viernes;
	}

	public void setViernes(int viernes) {
		this.viernes = viernes;
	}

	public int getSabado() {
		return sabado;
	}

	public void setSabado(int sabado) {
		this.sabado = sabado;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean existe(int cuadrante){
		try {
			return new BuscarSQL().existeCuadrante(cuadrante);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean existe(String cuadrante){
		try {
			return new BuscarSQL().existeCuadrante(cuadrante);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean guardar(String[][] tabla){ 
		boolean registro = new GuardarSQL().Guardar_Cuadrante(this); 
		boolean tabla1 = new GuardarSQL().Guardar_Cuadrante_Tabla(this, tabla);
		
		if(registro == true && tabla1 == true){
			return true;
		}else{
			return false;
		}
	
	}
	
	public boolean actualizar(int folio, String[][] tabla){ return new ActualizarSQL().Cuadrante(this,tabla); }
	
	public int nuevo(){
		try {
			return new BuscarSQL().Cuadrante_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	public Obj_Cuadrante buscarCuadrante(int folio){
		try {
			return new BuscarSQL().Cuadrante(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[][] tabla(int cuadrante) throws SQLException{
		return new BuscarSQL().getTablaDias(cuadrante);
	}

	
}
