package objetos;

public class Obj_Directorios {
	int folio;
	String Nombre;
	String Telefono;
	
	public Obj_Directorios(){
		this.folio=0; this.Nombre=""; this.Telefono="";
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	
	
}
