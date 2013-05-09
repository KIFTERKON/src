package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_OpRespuesta {
	
	private int folio;
	private String descripcion;
	private boolean status;

	public Obj_OpRespuesta(){
		this.folio=0;  this.descripcion=""; this.status=false;
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
			
			public boolean getStatus() {
				return status;
			}
			
			public void setStatus(boolean status) {
				this.status = status;
			}
			
			public String[] Combo_OpRespuesta() {
				try {
					return new Cargar_Combo().opRespuesta("tb_op_respuesta");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}
			
			public Obj_OpRespuesta buscar(int folio){
				try {
					return new BuscarSQL().OpRespuesta(folio);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}
			
			public boolean guardar(){ return new GuardarSQL().Guardar_OpRespuesta(this); }
			
			public boolean actualizar(int folio){ return new ActualizarSQL().OpRespuesta(this,folio); }
			
			public Obj_OpRespuesta buscar_nuevo(){
				try {
					return new BuscarSQL().OpRespuesta_Nuevo();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}
}