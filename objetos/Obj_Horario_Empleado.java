package objetos;

import java.sql.SQLException;
import SQL.BuscarSQL;

public class Obj_Horario_Empleado {
	private int folio;
	private String nombre;
	private String horario;
	private boolean status;
	private String descanso;
	private String dobla;
	
	public Obj_Horario_Empleado(){
		this.folio=0; this.nombre=""; this.horario=""; this.status=false; this.descanso=""; this.dobla="";
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

	public String getDescanso() {
		return descanso;
	}

	public void setDescanso(String descanso) {
		this.descanso = descanso;
	}

	public String getDobla() {
		return dobla;
	}

	public void setDobla(String dobla) {
		this.dobla = dobla;
	}
	
//	public Obj_Horario_Empleado buscar(int folio) {
//		try {
//			return new BuscarSQL().Turno(folio);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null; 
//	}
	
	public Obj_Horario_Empleado buscar_tur(String nombre){
		try{
			return new BuscarSQL().Turn_buscar(nombre); 
		} catch(SQLException e){
			
		}
		return null;
	}
	
	public Obj_Horario_Empleado buscar_tur(int folio){
		try{
			return new BuscarSQL().Turn_buscar(folio); 
		} catch(SQLException e){
			
		}
		return null;
	}
	
//	public boolean guardar(){ return new GuardarSQL().Guardar_Turno(this); }
	
//	public String[] Combo_Turno(){ 
//		try {
//			return new Cargar_Combo().Turno("tb_horarios");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null; 
//	}
	
//	public boolean actualizar(int folio){ return new ActualizarSQL().Turno(this,folio); }
//	
//	public Obj_Turno buscar_nuevo() throws SQLException{ return new BuscarSQL().Turno_Nuevo(); }
	
//	public Obj_Turno buscar_hora(int folio){
//	try{
//		return new BuscarSQL().Horario_buscar(folio); 
//	} catch(SQLException e){
//		
//	}
//	return null;
//}
	
}
