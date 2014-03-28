package ObjetoChecador;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Permisos_Checador {
	int folio;
	int folio_empleado;
	String nombre_empleado;
	int folio_usuario;
	String fecha;
	int tipo_de_permiso;
	String motivo;
	boolean status;
	int descanso;
	String tiempo_comida;

	public Obj_Permisos_Checador() {

		this.folio = 0;
		this.folio_empleado = 0;
		this.nombre_empleado = "";
		this.folio_usuario = 0;
		this.fecha = "";
		this.tipo_de_permiso = 0;
		this.motivo = "";
		this.status = false;
		this.descanso = 0;
		this.tiempo_comida = "";

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

	public String getNombre_empleado() {
		return nombre_empleado;
	}

	public void setNombre_empleado(String nombre_empleado) {
		this.nombre_empleado = nombre_empleado;
	}

	public int getFolio_usuario() {
		return folio_usuario;
	}

	public void setFolio_usuario(int folio_usuario) {
		this.folio_usuario = folio_usuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getTipo_de_permiso() {
		return tipo_de_permiso;
	}

	public void setTipo_de_permiso(int tipo_de_permiso) {
		this.tipo_de_permiso = tipo_de_permiso;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getDescanso() {
		return descanso;
	}

	public void setDescanso(int descanso) {
		this.descanso = descanso;
	}

	public String getTiempo_comida() {
		return tiempo_comida;
	}

	public void setTiempo_comida(String tiempo_comida) {
		this.tiempo_comida = tiempo_comida;
	}

	public Obj_Permisos_Checador buscar(int folio) {
		try {
			return new BuscarSQL().buscar_permiso(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Obj_Permisos_Checador ComparacionFecha(String fecha) {
		try {
			return new BuscarSQL().comparacionDeFecha(fecha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean guardar_permiso() {
		return new GuardarSQL().Guardar_Permiso_Checador(this);
	}

	public boolean actualizar(int folio) {
		return new ActualizarSQL().permiso(this, folio);
	}

	public int nuevoPermiso() {
		try {
			return new BuscarSQL().NuevoPermisoChecador();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	// public boolean buscarYborraPermiso(int folio){ return new
	// GuardarSQL().buscarBorrarPermiso(folio); }
}
