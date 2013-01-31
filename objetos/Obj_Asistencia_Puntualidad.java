package objetos;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Asistencia_Puntualidad {
	private float ValorAsistencia;
	private float ValorPuntualidad;
	private float valorGafete;
	private int existe;

	public Obj_Asistencia_Puntualidad() {
		this.ValorAsistencia=0; this.ValorPuntualidad=0; this.existe=0;
	}

	public float getValorAsistencia() {
		return ValorAsistencia;
	}

	public void setValorAsistencia(float valorAsistencia) {
		ValorAsistencia = valorAsistencia;
	}

	public float getValorPuntualidad() {
		return ValorPuntualidad;
	}

	public void setValorPuntualidad(float valorPuntualidad) {
		ValorPuntualidad = valorPuntualidad;
	}
	
	public int getExiste() {
		return existe;
	}

	public void setExiste(int existe) {
		this.existe = existe;
	}
	
	public float getValorGafete() {
		return valorGafete;
	}

	public void setValorGafete(float valorGafete) {
		this.valorGafete = valorGafete;
	}

	public boolean guardar(){ return new GuardarSQL().Asistencia_Puntualidad(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Asistecia_Puntualidad(this,folio); }
	
	public Obj_Asistencia_Puntualidad nuevo(){ return new BuscarSQL().Asistencia_Puntualidad(); }
	
	public Obj_Asistencia_Puntualidad buscar(int folio){ return new BuscarSQL().Asistencia_Puntualidad(folio); }

}
