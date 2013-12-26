package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;



public class Obj_Configuracion_Sistema {
	
	private int couns;
	private boolean bono_10_12;
	private boolean bono_dia_extra;
	private boolean guardar_horario;
	private boolean guardar_departamento;
	
	public Obj_Configuracion_Sistema(){
		this.bono_10_12=false; this.bono_dia_extra=false; this.guardar_horario=false; this.guardar_departamento=false;
		this.couns=0;
	}

	public boolean isBono_10_12() {
		return bono_10_12;
	}

	public void setBono_10_12(boolean bono_10_12) {
		this.bono_10_12 = bono_10_12;
	}
	
	public boolean isBono_dia_extra() {
		return bono_dia_extra;
	}

	public void setBono_dia_extra(boolean bonoDiaExtra) {
		bono_dia_extra = bonoDiaExtra;
	}

	public boolean isGuardar_horario() {
		return guardar_horario;
	}

	public void setGuardar_horario(boolean guardar_horario) {
		this.guardar_horario = guardar_horario;
	}

	public boolean isGuardar_departamento() {
		return guardar_departamento;
	}

	public void setGuardar_departamento(boolean guardar_departamento) {
		this.guardar_departamento = guardar_departamento;
	}

	public int getCouns() {
		return couns;
	}

	public void setCouns(int couns) {
		this.couns = couns;
	}

	public boolean guardar(){ 
		return new GuardarSQL().Guardar(this); 
	}
	
	public Obj_Configuracion_Sistema buscar() {
		try {
			return new BuscarSQL().Configuracion_sistema();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	public Obj_Configuracion_Sistema buscar2() {
		try {
			return new BuscarSQL().Configuracion_sistema2();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	public boolean actualizar(){ return new ActualizarSQL().Configurar_Sistema(this); }
}
