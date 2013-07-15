package objetos;

import java.sql.SQLException;

import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;



public class Obj_Alimentacion_Cuadrante {

	String nombre;
	String puesto;
	String establecimiento;
	String equipo_trabajo;
	String jefatura;
    String dia;
	String fecha;
	String cuadrante;
			
	public Obj_Alimentacion_Cuadrante(){
		this.nombre="";
		this.puesto="";
		this.establecimiento="";
		this.equipo_trabajo="";
		this.jefatura="";
		this.dia="";
		this.fecha="";
		this.cuadrante="";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getEquipo_trabajo() {
		return equipo_trabajo;
	}

	public void setEquipo_trabajo(String equipo_trabajo) {
		this.equipo_trabajo = equipo_trabajo;
	}

	public String getJefatura() {
		return jefatura;
	}

	public void setJefatura(String jefatura) {
		this.jefatura = jefatura;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCuadrante() {
		return cuadrante;
	}

	public void setCuadrante(String cuadrante) {
		this.cuadrante = cuadrante;
	}
	
	public Obj_Alimentacion_Cuadrante buscarEmpleado(String nombre){ 
		try {
			return new BuscarSQL().EmpleadoNombre(nombre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public String[][] buscarTablaLibre(String nombre, String dia){
		return new BuscarSQL().tabla_alimentacion_cuadrante_libre(nombre, dia);
	}
	
	public String[][] buscarTablaMultiple(String nombre, String dia){
		return new BuscarSQL().tabla_alimentacion_cuadrante_multiple(nombre, dia);
	}
	
	public String[] ComboBox(String actividad){
		try {
			return new Cargar_Combo().ComboALimentacionMultiple(actividad);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public boolean guardar(){
		return new GuardarSQL().guardarAlimentacionCuadrante(this);	
	}

}
