package ObjetoChecador;

import java.sql.SQLException;

import SQL.BuscarSQL;

public class Obj_Encargado_De_Solicitudes {

	int folio;
	int establecimiento;

	public Obj_Encargado_De_Solicitudes() {
		this.folio = 0;
		this.establecimiento = 0;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(int establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Obj_Encargado_De_Solicitudes buscar(String encargado) {
		try {
			return new BuscarSQL().Emcargado_de_solicitudes(encargado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
