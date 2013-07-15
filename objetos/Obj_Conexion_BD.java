package objetos;

import java.io.IOException;

import SQL.BuscarSQL;
import SQL.GuardarSQL;



public class Obj_Conexion_BD {
	private String direccionIPV4;
	private String nombreBD;
	private String usuario;
	private String contrasena;
	
	public Obj_Conexion_BD(){
		this.direccionIPV4=""; this.nombreBD=""; this.usuario=""; this.contrasena="";
	}

	public String getDireccionIPV4() {
		return direccionIPV4;
	}

	public void setDireccionIPV4(String direccionIPV4) {
		this.direccionIPV4 = direccionIPV4;
	}

	public String getNombreBD() {
		return nombreBD;
	}

	public void setNombreBD(String nombreBD) {
		this.nombreBD = nombreBD;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public boolean guardar(){ 
		return new GuardarSQL().Guardar_ConfigBD(this); 
	}
	
	public Obj_Conexion_BD buscar()
    {
		Obj_Conexion_BD config = new Obj_Conexion_BD();
    	try{
    		config = new BuscarSQL().Conexion_BD();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	return config;
  }
	
}
