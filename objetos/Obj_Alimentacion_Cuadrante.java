package objetos;

import java.sql.SQLException;
import java.util.StringTokenizer;

import SQL.BuscarSQL;
import SQL.BuscarTablasModel;
import SQL.Cargar_Combo;
import SQL.GuardarTablasModel;



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
		this.cuadrante = procesa_texto(cuadrante);
	}
	
	public Obj_Alimentacion_Cuadrante buscarEmpleado(String nombre){ 
		try {
			return new BuscarSQL().EmpleadoNombre(nombre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public String procesa_texto(String texto) {
		StringTokenizer tokens = new StringTokenizer(texto);
	    texto = "";
	    while(tokens.hasMoreTokens()){
	    	texto += " "+tokens.nextToken();
	    }
	    texto = texto.toString();
	    texto = texto.trim().toUpperCase();
	     return texto;
	}
	
	public String[][] buscarTablaMultipleJerarquico(String nombre){
		return new BuscarSQL().tabla_alimentacion_cuadrante_multiple_jerarquico(nombre);
	}
	
	public String[][] buscarTablaMultiple(String nombre){
		return new BuscarSQL().tabla_alimentacion_cuadrante_multiple(nombre);
	}
	
	public String[][] buscarTablaMultipleCapturada(String nombre){
		return new BuscarSQL().tabla_alimentacion_cuadrante_multiple_capturada(nombre);
	}
	
	public String[][] buscarTablaMultipleCapturadaJerarquico(String nombre){
		return new BuscarSQL().tabla_alimentacion_cuadrante_multiple_capturada_jerarquica(nombre);
	}
	
	public String[][] buscarTablaPrimeraParte(String nombre){
		return new BuscarSQL().tabla_alimentacion_cuadrante_primera_parte(nombre);
	}
	
	public String[][] buscarTablaLibreJerarquico(String nombre){
		return new BuscarSQL().tabla_libre_jerarquico(nombre);
	}
	
	public String[][] buscarTablaLibre(String nombre){
		return new BuscarSQL().tabla_libre(nombre);
	}
	
	public String[][] buscarTablaLibreContestada(String nombre){
		return new BuscarSQL().tabla_libre_contestada(nombre);
	}
	
	public String[] ComboBox(int actividad){
		try {
			return new Cargar_Combo().ComboALimentacionMultiple(actividad);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public boolean Existe(String nombre){
		return new BuscarSQL().Existe_Cuadrante_Muliple(nombre);		
	}
	
	public boolean guardar(Object[][] multiple, Object[][] multiple_proyecto){
		return new GuardarTablasModel().Alimentacion_cuadrante_multiple(multiple, multiple_proyecto);
	}
	
	public boolean terminar_captura(String nombre){
		return new GuardarTablasModel().terminar_cuadrante_multiple(nombre);
	}
	
	public boolean status_llanado_tabla(String nombre){
		return new BuscarTablasModel().tablas_status(nombre);
	}
	
	public boolean guardarLibre(Object[][] libre, Object[][] libreJerarquico){
		return new GuardarTablasModel().Alimentacion_cuadrante_libre(libre, libreJerarquico);
	}

}
