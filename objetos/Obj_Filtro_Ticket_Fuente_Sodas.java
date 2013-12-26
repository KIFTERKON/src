package objetos;

import SQL.GuardarTablasModel;

public class Obj_Filtro_Ticket_Fuente_Sodas {
	
	Object[][] tabla_model;
	private int folio;
	private String nombre_completo;
        
    public Obj_Filtro_Ticket_Fuente_Sodas(){
    	this.folio=0;	this.nombre_completo="";
    }

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	public boolean guardar(Object[][] tabla, int folio,String empleado){
		return new GuardarTablasModel().tablaTicketFuenteSodas(tabla,folio,empleado);
	}
}
