package objetos;

import java.io.File;
import java.sql.SQLException;

import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Captura_Fuente_Sodas 
{
	String nombre_cajera;
	int no_cliente;
	String nombre_cliente;
	String establecimiento_cliente;
	String puesto_cliente;
	String ticket;
	double importe;
	private File foto;
	public Obj_Captura_Fuente_Sodas()
	{
		this.nombre_cajera="";
		this.no_cliente=0;
		this.nombre_cliente="";
		this.establecimiento_cliente="";
		this.puesto_cliente="";
		this.ticket="";
		this.importe=0.0;
	}
	public String getNombre_cajera() {
		return nombre_cajera;
	}
	public void setNombre_cajera(String nombre_cajera) {
		this.nombre_cajera = nombre_cajera;
	}
	public int getNo_cliente() {
		return no_cliente;
	}
	public void setNo_cliente(int no_cliente) {
		this.no_cliente = no_cliente;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getEstablecimiento_cliente() {
		return establecimiento_cliente;
	}
	public void setEstablecimiento_cliente(String establecimiento_cliente) {
		this.establecimiento_cliente = establecimiento_cliente;
	}
	public String getPuesto_cliente() {
		return puesto_cliente;
	}
	public void setPuesto_cliente(String puesto_cliente) {
		this.puesto_cliente = puesto_cliente;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public boolean Guardar(){
		return new GuardarSQL().Guardar_Fuente_Sodas(this);
	}
	

	public File getFoto() {
		return foto;
	}
	public void setFoto(File foto) {
		this.foto = foto;
	}
	public Obj_Captura_Fuente_Sodas buscar_folio(int folio){ 
		try {
			return new BuscarSQL().fuente(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
}
