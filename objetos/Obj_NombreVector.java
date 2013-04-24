package objetos;

import java.util.Vector;

@SuppressWarnings({ "serial", "rawtypes" })
public class Obj_NombreVector extends Vector{
	String name;
	 
	public Obj_NombreVector(String name) {
		this.name = name;
	}
	  
	@SuppressWarnings("unchecked")
	public Obj_NombreVector(String name, Object elements[]) {
		  this.name = name;
		  for (int i = 0, n = elements.length; i < n; i++) {
			  add(elements[i]);
		  }
	}

	public String toString() {
		return "[" + name + "]";
	}
}
