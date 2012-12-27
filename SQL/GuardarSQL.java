package SQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import objetos.Obj_Bono;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;
import objetos.Obj_Rango_Prestamos;
import objetos.Obj_Sueldo;
import objetos.Obj_Usuario;
import objetos.Obj_fuente_sodas_auxf;
import objetos.Obj_fuente_sodas_rh;

public class GuardarSQL extends Connexion{
	
	/** METODO DE ESCRITURA DE UN EMPLEADO A SQL SERVER*/
	public boolean Guardar_Empleado(Obj_Empleado empleado){
		String query = "insert into tb_empleado(no_checador,nombre,ap_paterno,ap_materno,establecimiento_id,puesto_id,sueldo_id,bono_id,fuente_sodas,gafete,status,fecha,rango_prestamo_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empleado.getNo_checador());
			pstmt.setString(2, empleado.getNombre().toUpperCase());
			pstmt.setString(3, empleado.getAp_paterno().toUpperCase());
			pstmt.setString(4, empleado.getAp_materno().toUpperCase());
			pstmt.setInt(5, empleado.getEstablecimiento());
			pstmt.setInt(6, empleado.getPuesto());
			pstmt.setInt(7,empleado.getSueldo());
			pstmt.setInt(8, empleado.getBono());
			pstmt.setBoolean(9, (empleado.getFuente_sodas())? true: false);
			pstmt.setBoolean(10, (empleado.getGafete())? true: false);
			pstmt.setInt(11, empleado.getStatus());				
			pstmt.setString(12, empleado.getFecha());
			pstmt.setInt(13, empleado.getPrestamo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Establecimiento(Obj_Establecimiento establecimiento){
		String query = "insert into tb_establecimiento(folio,nombre,abreviatura,status) values(?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			
				pstmt.setInt(1, establecimiento.getFolio());
				pstmt.setString(2, establecimiento.getNombre().toUpperCase());
				pstmt.setString(3, establecimiento.getAbreviatura().toUpperCase());
				pstmt.setString(4, (establecimiento.getStatus())?"1":"0");
				pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Puesto(Obj_Puesto puesto){
		String query = "insert into tb_puesto(nombre,abreviatura,status) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, puesto.getPuesto());
				pstmt.setString(2, puesto.getAbreviatura());
				pstmt.setString(3, (puesto.getStatus())?"1":"0");
				
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Bono(Obj_Bono bono){
		String query = "insert into tb_bono(bono,abreviatura,status) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			
				pstmt.setFloat(1, bono.getBono());
				pstmt.setString(2, bono.getAbreviatura().toUpperCase());
				pstmt.setString(3, (bono.getStatus())?"1":"0");
				
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Sueldo(Obj_Sueldo sueldo){
		String query = "insert into tb_sueldo(sueldo,abreviatura,status) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			
				pstmt.setFloat(1, sueldo.getSueldo());
				pstmt.setString(2, sueldo.getAbreviatura().toUpperCase());
				pstmt.setString(3, (sueldo.getStatus())?"1":"0");
				
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}

	public boolean Guardar_Usuario(Obj_Usuario usuario){
		String query = "insert into tb_usuario(nombre_completo,contrasena,permiso_id,fecha,status) values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setString(1, usuario.getNombre_completo().toUpperCase());
				pstmt.setString(2, usuario.getContrasena());
				pstmt.setInt(3, usuario.getPermiso_id());
				String fecha = new Date().toString();
				pstmt.setString(4, fecha);
				pstmt.setInt(5, usuario.getStatus());
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	public boolean Guardar_fuente_sodas_rh(Obj_fuente_sodas_rh fuentesodasrh){
		String query = "insert into tb_fuente_sodas_rh(status_ticket,folio_empleado,nombre_completo,cantidad,fecha,status) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, "0");
				pstmt.setInt(2, fuentesodasrh.getFolio());
				pstmt.setString(3, fuentesodasrh.getNombre_Completo().toUpperCase());
				pstmt.setDouble(4, fuentesodasrh.getCantidad());
				pstmt.setString(5, fuentesodasrh.getFecha());
				pstmt.setString(6, "1");
				
			 	pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_fuente_sodas_auxf(Obj_fuente_sodas_auxf fuentesodasauxf){
		String query = "insert into tb_fuente_sodas_auxf(status_ticket,folio_empleado,nombre_completo,cantidad,fecha,status) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "0");
				pstmt.setInt(2,fuentesodasauxf.getFolio());
				pstmt.setString(3, fuentesodasauxf.getNombre_Completo().toUpperCase());
				pstmt.setDouble(4, fuentesodasauxf.getCantidad());
				pstmt.setString(5, fuentesodasauxf.getFecha());
				pstmt.setString(6, "1");
				
			 	pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Rango_Prestamos(Obj_Rango_Prestamos rango_prestamo){
		String query = "insert into tb_rango_prestamos(minimo,maximo,descuento,status) values(?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			
				pstmt.setDouble(1, rango_prestamo.getPrestamo_minimo());
				pstmt.setDouble(2, rango_prestamo.getPrestamo_maximo());
				pstmt.setDouble(3, rango_prestamo.getDescuento());
				pstmt.setString(4, (rango_prestamo.isStatus())?"1":"0");
				pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
}
