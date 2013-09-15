package ObjetoChecador;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Permisos_Checador {
	int folio;
	int folio_empleado;
	String fecha;
	boolean p_travajarCorrido;
	boolean p_salirTemprano;
	boolean p_entrarTarde;
	boolean p_noAsistir;
	String motivo;
//	boolean status;
	
	public Obj_Permisos_Checador(){
		
		this.folio=0;					this.folio_empleado=0;		this.fecha="";				this.p_travajarCorrido=false;
		this.p_salirTemprano=false;		this.p_entrarTarde=false;	this.p_noAsistir=false;		this.motivo="";			
//		this.status=false;
		
	}

	public int getFolio() {
		return folio;
	}


	public void setFolio(int folio) {
		this.folio = folio;
	}


	public int getFolio_empleado() {
		return folio_empleado;
	}


	public void setFolio_empleado(int folio_empleado) {
		this.folio_empleado = folio_empleado;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public boolean isP_travajarCorrido() {
		return p_travajarCorrido;
	}


	public void setP_travajarCorrido(boolean p_travajarCorrido) {
		this.p_travajarCorrido = p_travajarCorrido;
	}


	public boolean isP_salirTemprano() {
		return p_salirTemprano;
	}


	public void setP_salirTemprano(boolean p_salirTemprano) {
		this.p_salirTemprano = p_salirTemprano;
	}


	public boolean isP_entrarTarde() {
		return p_entrarTarde;
	}


	public void setP_entrarTarde(boolean p_entrarTarde) {
		this.p_entrarTarde = p_entrarTarde;
	}


	public boolean isP_noAsistir() {
		return p_noAsistir;
	}


	public void setP_noAsistir(boolean p_noAsistir) {
		this.p_noAsistir = p_noAsistir;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


//	public boolean isStatus() {
//		return status;
//	}
//
//
//	public void setStatus(boolean status) {
//		this.status = status;
//	}

	public Obj_Permisos_Checador buscar(int folio){
		try {
			return new BuscarSQL().buscar_permiso(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar_permiso(){ return new GuardarSQL().Guardar_Permiso_Checador(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().permiso(this,folio); }
	
	public int nuevoPermiso(){
		try {
			return new BuscarSQL().NuevoPermisoChecador();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public boolean buscarYborraPermiso(int folio){ return new GuardarSQL().buscarBorrarPermiso(folio); }
	
}
