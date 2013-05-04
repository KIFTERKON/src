package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Cuadrante {
	private int folio;
	private String nombre;
	private int establecimiento;
	private int nivel_gerarquico;
	private int dia;
	private int eq_trabajo;
	private int jefatura;
	private boolean status;
	private String descripcion;
	
	public Obj_Cuadrante(){
		this.folio=0; this.nombre=""; this.establecimiento=0; this.nivel_gerarquico=0;
		this.dia=0; this.eq_trabajo=0; this.jefatura=0; this.status=false; this.descripcion="";
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(int establecimiento) {
		this.establecimiento = establecimiento;
	}

	public int getNivel_gerarquico() {
		return nivel_gerarquico;
	}

	public void setNivel_gerarquico(int nivelGerarquico) {
		nivel_gerarquico = nivelGerarquico;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getEq_trabajo() {
		return eq_trabajo;
	}

	public void setEq_trabajo(int eqTrabajo) {
		eq_trabajo = eqTrabajo;
	}

	public int getJefatura() {
		return jefatura;
	}

	public void setJefatura(int jefatura) {
		this.jefatura = jefatura;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Obj_Cuadrante buscar(int folio){
		try {
			return new BuscarSQL().Cuadrante(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Cuadrante(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Cuadrante(this,folio); }
	
	public Obj_Cuadrante buscar_nuevo(){
		try {
			return new BuscarSQL().Cuadrante_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
}
