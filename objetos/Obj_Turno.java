package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Turno {
	private int folio;
	private String nombre;
	private String horario;
	private boolean status;
	public Obj_Turno(){
		this.folio=0; nombre=""; horario="";
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

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean guardar(){ return new GuardarSQL().Guardar_Turno(this); }
	
	public String[] Combo_Turno(){ 
		try {
			return new Cargar_Combo().Turno("tb_turno");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public Obj_Turno buscar(int folio) {
		try {
			return new BuscarSQL().Turno(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Turno(this,folio); }
	
	public Obj_Turno buscar_nuevo() throws SQLException{ return new BuscarSQL().Turno_Nuevo(); }
	
	public Obj_Turno buscar_tur(String nombre){
		try{
			return new BuscarSQL().Turn_buscar(nombre); 
		} catch(SQLException e){
			
		}
		return null;
	}
	
	public Obj_Turno buscar_tur(int folio){
		try{
			return new BuscarSQL().Turn_buscar(folio); 
		} catch(SQLException e){
			
		}
		return null;
	}
	
	public Obj_Turno buscar_hora(int folio){
		try{
			return new BuscarSQL().Horario_buscar(folio); 
		} catch(SQLException e){
			
		}
		return null;
	}
	
}
