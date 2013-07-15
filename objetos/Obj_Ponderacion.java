package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;



public class Obj_Ponderacion {
	
	private int folio;
	private String descripcion;
	private float valor;
	private boolean status;
	
	private boolean Domingo;
	private boolean Lunes;
	private boolean Martes;
	private boolean Miercoles;
	private boolean Jueves;
	private boolean Viernes;
	private boolean Sabado;
	
	
	private String fechaIn;
	private String fechaFin;

	public Obj_Ponderacion(){
		this.folio=0;  this.descripcion=""; this.valor=0; this.status=false; 
		this.fechaIn=""; this.fechaFin="";
	}

			public int getFolio() {
				return folio;
			}
			
			public void setFolio(int folio) {
				this.folio = folio;
			}
			
			public String getDescripcion() {
				return descripcion;
			}
			
			public void setDescripcion(String descripcion) {
				this.descripcion = descripcion;
			}
			
			public float getValor() {
				return valor;
			}
			
			public void setValor(float valor) {
				this.valor = valor;
			}
			
			public boolean getStatus() {
				return status;
			}
			
			public void setStatus(boolean status) {
				this.status = status;
			}
			
			public String getFechaIn() {
				return fechaIn;
			}

			public void setFechaIn(String fechaIn) {
				this.fechaIn = fechaIn;
			}

			public String getFechaFin() {
				return fechaFin;
			}

			public void setFechaFin(String fechaFin) {
				this.fechaFin = fechaFin;
			}
			
			public boolean isDomingo() {
				return Domingo;
			}

			public void setDomingo(boolean domingo) {
				Domingo = domingo;
			}

			public boolean isLunes() {
				return Lunes;
			}

			public void setLunes(boolean lunes) {
				Lunes = lunes;
			}

			public boolean isMartes() {
				return Martes;
			}

			public void setMartes(boolean martes) {
				Martes = martes;
			}

			public boolean isMiercoles() {
				return Miercoles;
			}

			public void setMiercoles(boolean miercoles) {
				Miercoles = miercoles;
			}

			public boolean isJueves() {
				return Jueves;
			}

			public void setJueves(boolean jueves) {
				Jueves = jueves;
			}

			public boolean isViernes() {
				return Viernes;
			}

			public void setViernes(boolean viernes) {
				Viernes = viernes;
			}

			public boolean isSabado() {
				return Sabado;
			}

			public void setSabado(boolean sabado) {
				Sabado = sabado;
			}

			public String[] Combo_Ponderacion() {
				try {
					return new Cargar_Combo().Ponderacion("tb_ponderacion");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}

			public Obj_Ponderacion buscar(int folio){
				try {
					return new BuscarSQL().Ponderacion(folio);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}
			
			public boolean guardar(){ return new GuardarSQL().Guardar_Ponderacion(this); }
			
			public boolean actualizar(int folio){ return new ActualizarSQL().Ponderacion(this,folio); }
			
			public Obj_Ponderacion buscar_nuevo(){
				try {
					return new BuscarSQL().Ponderacion_Nuevo();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}
}
