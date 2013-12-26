package objetos;

import java.io.File;
import java.sql.SQLException;

import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Captura_Fuente_Sodas 
{
	private String clave;
	private String establecimiento;
	private String puesto;
	private String ticket;
	private float importe;
	private String usuario;
	
	private int folio_empleado;
	private String empleado;
	
	private float total;
	
	private File foto;
	
	
	
	public Obj_Captura_Fuente_Sodas()
	{
		this.clave="";    this.establecimiento="";    this.puesto="";    this.ticket="";    this.importe=0;    this.usuario="";
		
		this.folio_empleado=0;	this.empleado="";		this.total=0;	this.foto=null;
	}
		
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folio_empleado) {
		this.folio_empleado = folio_empleado;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}

	public boolean Guardar(){
		return new GuardarSQL().Guardar_Fuente_Sodas(this);
	}
	
	public Obj_Captura_Fuente_Sodas buscar_folio(int folio){ 
		try {
			return new BuscarSQL().fuente(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public String[][] tabla(String folio_empleado) throws SQLException{
		return new BuscarSQL().getTablaCapturaFuenteSodas(folio_empleado);
	}
	
	public Obj_Captura_Fuente_Sodas buscar(String clave){ 
		try {
			return new BuscarSQL().CapturaFuenteSodas(clave);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
//	tabla de tickets por empleado
	public String[][] tabla(int folio_empleado) throws SQLException{
		return new BuscarSQL().getTablaTicketFuenteSodas(folio_empleado);
	}
}
