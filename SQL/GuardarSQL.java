package SQL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import objetos.Obj_Alimentacion_Cortes;
import objetos.Obj_Alimentacion_Denominacion;
import objetos.Obj_Asistencia_Puntualidad;
import objetos.Obj_Bancos;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Conexion_BD;
import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Deduccion_Iasistencia;
import objetos.Obj_Denominaciones;
import objetos.Obj_Divisa_Y_TipoDeCambio;
import objetos.Obj_Diferencia_Cortes;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Revision_Lista_Raya;
import objetos.Obj_Persecciones_Extra;
import objetos.Obj_Prestamo;
import objetos.Obj_Puesto;
import objetos.Obj_Rango_Prestamos;
import objetos.Obj_Sueldo;
import objetos.Obj_Tipo_Banco;
import objetos.Obj_Turno;
import objetos.Obj_Usuario;
import objetos.Obj_fuente_sodas_auxf;
import objetos.Obj_fuente_sodas_rh;

public class GuardarSQL {
	
	
	public boolean Guardar_Empleado(Obj_Empleado empleado){
		String query = "exec sp_insert_empleado ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
					
			pstmt.setInt(1, empleado.getNo_checador());
			pstmt.setString(2, empleado.getNombre().toUpperCase());
			pstmt.setString(3, empleado.getAp_paterno().toUpperCase());
			pstmt.setString(4, empleado.getAp_materno().toUpperCase());
			pstmt.setInt(5, empleado.getEstablecimiento());
			
			pstmt.setInt(6, empleado.getPuesto());
			pstmt.setInt(7, empleado.getTurno());
			pstmt.setInt(8, empleado.getDescanso());
			pstmt.setInt(9, empleado.getDobla());
			pstmt.setInt(10,empleado.getSueldo());
			
			pstmt.setInt(11, empleado.getBono());
			pstmt.setInt(12, empleado.getPrestamo());
			pstmt.setFloat(13, empleado.getPension_alimenticia());
			pstmt.setFloat(14,empleado.getInfonavit());
			pstmt.setBoolean(15, (empleado.getFuente_sodas())? true: false);
			
			pstmt.setBoolean(16, (empleado.getGafete())? true: false);
			pstmt.setInt(17, empleado.getStatus());				
			pstmt.setString(18, empleado.getFecha());
			pstmt.setString(19, empleado.getObservasiones());
			pstmt.setString(20, empleado.getFoto());
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Establecimiento(Obj_Establecimiento establecimiento){
		String query = "exec sp_insert_establecimiento ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, establecimiento.getNombre().toUpperCase());
			pstmt.setString(2, establecimiento.getAbreviatura().toUpperCase());
			pstmt.setString(3, (establecimiento.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Puesto(Obj_Puesto puesto){
		String query = "exec sp_insert_puesto ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, puesto.getPuesto().toUpperCase());
			pstmt.setString(2, puesto.getAbreviatura().toUpperCase());
			pstmt.setString(3, (puesto.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Tipo_Banco(Obj_Tipo_Banco banck){
		String query = "exec sp_insert_tipo_banco ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, banck.getBanco().toUpperCase());
			pstmt.setString(2, banck.getAbreviatura().toUpperCase());
			pstmt.setString(3, (banck.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
			
	public boolean Guardar_Divisas(Obj_Divisa_Y_TipoDeCambio divisas){
		String query = "insert into tb_divisas_tipo_de_cambio(nombre_divisas,valor,status) values(?,?,?)";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, divisas.getNombre().toUpperCase());
			pstmt.setFloat (2, divisas.getValor());
			pstmt.setString(3, (divisas.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Denominaciones(Obj_Denominaciones denominaciones){
		String query = "insert into tb_denominaciones(nombre,moneda,status) values(?,?,?)";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, denominaciones.getNombre().toUpperCase());
			pstmt.setString(2, denominaciones.getMoneda());
			pstmt.setString(3, (denominaciones.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Corte(Obj_Alimentacion_Cortes corte){
		String query = "insert into tb_alimentacion_cortes(folio_empleado,nombre_empleado," +
						"puesto,establecimiento,asignacion,corte_del_sistema,deposito," +
						"efectivo,diferencia_corte,fecha,status,comentario) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, corte.getFolio_empleado());
			pstmt.setString(2, corte.getNombre().toUpperCase());
			pstmt.setString(3,corte.getPuesto().toUpperCase());
			pstmt.setString(4, corte.getEstablecimiento().toUpperCase());
			pstmt.setString(5, corte.getAsignacion().toUpperCase());
			pstmt.setFloat(6, corte.getCorte_sistema());
			pstmt.setFloat(7, corte.getDeposito());
			pstmt.setFloat(8, corte.getEfectivo());
			pstmt.setFloat(9, corte.getDiferencia_corte());
			pstmt.setString(10, corte.getFecha().toUpperCase());
			pstmt.setString(11, (corte.isStatus())?"1":"0");
			pstmt.setString(12, corte.getComentario().toUpperCase());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Bono(Obj_Bono_Complemento_Sueldo bono){
		String query = "exec sp_insert_bono ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setFloat(1, bono.getBono());
			pstmt.setString(2, bono.getAbreviatura().toUpperCase());
			pstmt.setString(3, (bono.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Sueldo(Obj_Sueldo sueldo){
		String query = "exec sp_insert_sueldo ?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setFloat(1, sueldo.getSueldo());
			pstmt.setString(2, (sueldo.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transaccón ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}

	public boolean Guardar_Usuario(Obj_Usuario usuario){
		String query = "exec sp_insert_usuario ?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, usuario.getNombre_completo().toUpperCase());
			pstmt.setString(2, usuario.getContrasena());
			pstmt.setInt(3, usuario.getPermiso_id());
			String fecha = new Date().toString();
			pstmt.setString(4, fecha);
			pstmt.setInt(5, usuario.getStatus());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+ e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_fuente_sodas_rh(Obj_fuente_sodas_rh fuentesodasrh){
		String query = "exec sp_insert_fuent_soda_rh ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "0");
			pstmt.setInt(2, fuentesodasrh.getFolio());
			pstmt.setString(3, fuentesodasrh.getNombre_Completo().toUpperCase());
			pstmt.setDouble(4, fuentesodasrh.getCantidad());
			pstmt.setString(5, fuentesodasrh.getFecha());
			pstmt.setString(6, "1");
		 	pstmt.executeUpdate();
		 	con.commit();
		 	
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_fuente_sodas_auxf(Obj_fuente_sodas_auxf fuentesodasauxf){
		String query = "exec sp_insert_fuent_soda_auxf ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "0");
			pstmt.setInt(2,fuentesodasauxf.getFolio());
			pstmt.setString(3, fuentesodasauxf.getNombre_Completo().toUpperCase());
			pstmt.setDouble(4, fuentesodasauxf.getCantidad());
			pstmt.setString(5, fuentesodasauxf.getFecha());
			pstmt.setString(6, "1");
		 	pstmt.executeUpdate();
		 	con.commit();
		 	
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_prestamo(Obj_Prestamo pres){
		String query = "exec sp_insert_prestamo ?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,pres.getFolio_empleado());
			pstmt.setString(2, pres.getNombre_Completo().toUpperCase());
			pstmt.setString(3, pres.getFecha());
			pstmt.setDouble(4, pres.getCantidad());
			pstmt.setDouble(5, pres.getDescuento());
			
			pstmt.setString(6, "1");
			pstmt.setDouble(7, pres.getSaldo());
			pstmt.setString(8, "1");
			pstmt.setDouble(9, 0);
			pstmt.executeUpdate();
			con.commit();
			
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Rango_Prestamos(Obj_Rango_Prestamos rango_prestamo){
		String query = "exec sp_insert_rango_prestamo ?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setDouble(1, rango_prestamo.getPrestamo_minimo());
			pstmt.setDouble(2, rango_prestamo.getPrestamo_maximo());
			pstmt.setDouble(3, rango_prestamo.getDescuento());
			pstmt.setString(4, (rango_prestamo.isStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();	
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Deduccion_Asistencia(Obj_Deduccion_Iasistencia deduccion){
		String query = "exec sp_insert_deducc_inasistencia ?,?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, deduccion.getFolio_empleado());
			pstmt.setString(2, deduccion.getNombre_completo().toUpperCase());
			pstmt.setString(3, deduccion.getEstablecimiento().toUpperCase());
			pstmt.setString(4, deduccion.getPuntualidad());
			pstmt.setString(5, deduccion.getFalta());
			pstmt.setInt(6, deduccion.getDia_faltas());
			pstmt.setString(7, deduccion.getAsistencia());
			pstmt.setString(8, deduccion.getGafete());
			pstmt.setInt(9, deduccion.getDia_gafete());
			pstmt.setFloat(10, deduccion.getExtra());
			pstmt.setFloat(11, deduccion.getCantidad_faltas());
			pstmt.setInt(12 , 1);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Asistencia_Puntualidad(Obj_Asistencia_Puntualidad asistencia_puntualidad){
		String query = "exec sp_insert_asistencia_puntualidad ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setFloat(1, asistencia_puntualidad.getValorAsistencia());
			pstmt.setFloat(2, asistencia_puntualidad.getValorPuntualidad());
			pstmt.setFloat(3, asistencia_puntualidad.getValorGafete());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortado");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar(Obj_Persecciones_Extra persecciones){
		String query = "exec sp_insert_persecciones_extra ?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, persecciones.getFolio_empleado());
			pstmt.setString(2, persecciones.getNombre_completo().toUpperCase());
			pstmt.setString(3, persecciones.getEstablecimiento().toUpperCase());
			pstmt.setFloat(4, persecciones.getBono());
			pstmt.setString(5, persecciones.getDia_extra());
			pstmt.setInt(6, persecciones.getDias());
			pstmt.setFloat(7, persecciones.getCantidad_dias());
			pstmt.setInt(8 , 1);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Bancos(Obj_Bancos bancos){
		String query = "exec sp_insert_bancos ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bancos.getFolio_empleado());
			pstmt.setString(2, bancos.getNombre_completo().toUpperCase());
			pstmt.setString(3, bancos.getEstablecimiento().toUpperCase());
			pstmt.setFloat(4, bancos.getBanamex());
			pstmt.setFloat(5, bancos.getBanorte());
			pstmt.setString(6, "1");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Alimentacion_denominacio(Obj_Alimentacion_Denominacion alim_denom){
		
		String query = "insert into tb_alimentacion_denominaciones(asignacion," +
				"folio_empleado,folio_denominacion,denominacion,valor,cantidad,fecha)" +
				" values(?,?,?,?,?,?,?)";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, alim_denom.getAsignacion().toUpperCase());
			pstmt.setInt(2, alim_denom.getFolio_empleado());
			pstmt.setInt(3, alim_denom.getFolio_denominacion());
			pstmt.setString(4, alim_denom.getDenominacion().toUpperCase());
			pstmt.setFloat(5, alim_denom.getValor());
			pstmt.setFloat(6, alim_denom.getCantidad());
			pstmt.setString(7, alim_denom.getFecha());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar(Obj_Diferencia_Cortes pres){
		String query = "exec sp_insert_diferencia_cortes ?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,pres.getFolio_empleado());
			pstmt.setString(2, pres.getNombre_Completo().toUpperCase());
			pstmt.setString(3, pres.getFecha());
			pstmt.setDouble(4, pres.getCantidad());
			pstmt.setDouble(5, pres.getDescuento());
			pstmt.setString(6, "1");
			pstmt.setDouble(7, pres.getSaldo());
			pstmt.setString(8, "1");
			pstmt.setDouble(9, 0);
		 	pstmt.executeUpdate();
		 	con.commit();
		 } catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	
	public boolean Guardar_Turno(Obj_Turno turno){
		String query = "exec sp_insert_turno ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, turno.getNombre().toUpperCase());
			pstmt.setString(2, turno.getHorario().toUpperCase());
			pstmt.setString(3, (turno.isStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	
	
	public boolean Guardar_ConfigBD(Obj_Conexion_BD config){
		BufferedWriter bufferedWriter = null;
		String nomArchivo = System.getProperty("user.dir")+"\\Config\\config";
		try{
			File archivo = new File(nomArchivo);
			if(archivo.exists()){
				bufferedWriter = new BufferedWriter (new FileWriter(nomArchivo));
				
				bufferedWriter.write(config.getDireccionIPV4()+    		"\n");
				bufferedWriter.write(config.getNombreBD()+      		"\n");
				bufferedWriter.write(config.getUsuario()+ 	    		"\n");
				bufferedWriter.write(config.getContrasena()+       		"\n");
				
			}else{
				archivo.createNewFile();
				bufferedWriter = new BufferedWriter (new FileWriter(nomArchivo));
				
				bufferedWriter.write(config.getDireccionIPV4()+    		"\n");
				bufferedWriter.write(config.getNombreBD()+      		"\n");
				bufferedWriter.write(config.getUsuario()+ 	    		"\n");
				bufferedWriter.write(config.getContrasena()+       		"\n");
				
			}
			
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}finally
		{
			try
			{
				if(bufferedWriter!=null)
				{
					bufferedWriter.flush();
					bufferedWriter.close();
				}
			}catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}return true;
	}
	
	
	public boolean Guardar_Pre_Lista(Obj_Revision_Lista_Raya raya){
		
		String query ="exec sp_insert_pre_listaraya ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, (raya.isChecado()) ? "true": "false");
			pstmt.setInt(2, raya.getFolio_empleado());
			pstmt.setFloat(3,raya.getA_pagar());
			pstmt.setString(4, raya.getObservasion_i());
			pstmt.setString(5, raya.getObservasion_ii());
			pstmt.setInt(6, 1);

			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortado");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar(Obj_Configuracion_Sistema configs){
		String query = "exec sp_config_sistema ?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, (configs.isBono_10_12())? "true" : "false");
			pstmt.setString(2, (configs.isBono_dia_extra())? "true" : "false");
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar(Obj_Revision_Lista_Raya raya){
		
		/** EL PROCEDIMIENTO sp_insert_lista_raya INSERTA LOS VALORES A LA TABLA 
		 *  tb_lista_raya
 		 * **/
		String sp_insert_lista_raya25 =
				"exec sp_insert_lista_raya ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		
		/** EL PROCEDIMIENTO sp_insert_abono INSERTA EL ABONO INDICADO DE PRESTAMOS**/
		String sp_insert_abono4 = "exec sp_insert_abono ?,?,?,?";
		
		/**CUANDO UN PRESTAMO SE QUEDA EN CANTIDAD DE CERO LOS STATUS SON CAMBIADOS A 0
		 * JUNTO CON LOS ABONOS**/
		String sp_update_abono2 = "exec sp_update_abono ?,?";
		String sp_update_prestamo1 = "exec sp_update_prestamo ?";
		
		/**EL PROCEDIMIENTO sp_insert_abono_cortes INSERTA EL ABONO DEL CORTES INDICADO**/
		String sp_insert_abono_cortes4 = "exec sp_insert_abono_cortes ?,?,?,?";
		
		/**CUANDO UN CORTE SE QUEDA EN CANTIDAD DE CERO LOS STATUS SON CAMBIADOS A 0
		 * JUNTO CON LOS ABONOS DE CORTE**/
		String sp_update_abono_cortes2 = "exec sp_update_abono_cortes ?,?";
		String sp_update_cortes1 = "exec sp_update_cortes ?";
		
		/**RESETEAR LAS TABLAS SIGUIENTES PERSECCIONES Y DEDUCION INASISTENCIA**/
		String sp_update_status_persecciones1 = "exec sp_update_status_persecciones ?";
		String sp_update_status_deduci_inasis1 = "exec sp_update_status_deduci_inasis ?";
				
		/**REGRESAR LAS AUTORIZACIONES A FALSO**/
		String sp_update_autorizaciones2 ="exec sp_update_autorizaciones ?,?";	
		
		Connection con = new Connexion().conexion();
		PreparedStatement sp_insert_lista_raya25pstmt = null;
		PreparedStatement sp_insert_abono4pstmt = null;
		PreparedStatement sp_update_abono2pstmt = null;
		PreparedStatement sp_update_prestamo1pstmt = null;
		PreparedStatement sp_insert_abono_cortes4pstmt = null;
		PreparedStatement sp_update_abono_cortes2pstmt = null;
		PreparedStatement sp_update_cortes1pstmt = null;
		PreparedStatement sp_update_status_persecciones1pstmt = null;
		PreparedStatement sp_update_status_deduci_inasis1pstmt = null;
		PreparedStatement sp_update_autorizaciones2pstmt = null;

		try {
			con.setAutoCommit(false);
			sp_insert_lista_raya25pstmt = con.prepareStatement(sp_insert_lista_raya25);
			sp_insert_abono4pstmt = con.prepareStatement(sp_insert_abono4);
			sp_update_abono2pstmt = con.prepareStatement(sp_update_abono2);
			sp_update_prestamo1pstmt = con.prepareStatement(sp_update_prestamo1);
			sp_insert_abono_cortes4pstmt = con.prepareStatement(sp_insert_abono_cortes4);
			sp_update_abono_cortes2pstmt = con.prepareStatement(sp_update_abono_cortes2);
			sp_update_cortes1pstmt = con.prepareStatement(sp_update_cortes1);
			sp_update_status_persecciones1pstmt = con.prepareStatement(sp_update_status_persecciones1);
			sp_update_status_deduci_inasis1pstmt = con.prepareStatement(sp_update_status_deduci_inasis1);
			sp_update_autorizaciones2pstmt = con.prepareStatement(sp_update_autorizaciones2);
			
			int Folio_Empleado = raya.getFolio_empleado();
			float descuento = raya.getD_prestamo();
			
			sp_insert_lista_raya25pstmt.setInt(1, raya.getNumero_lista());
			sp_insert_lista_raya25pstmt.setInt(2, Folio_Empleado);
			sp_insert_lista_raya25pstmt.setString(3, raya.getNombre_completo().toUpperCase());
			sp_insert_lista_raya25pstmt.setString(4, raya.getEstablecimiento().toUpperCase());
			sp_insert_lista_raya25pstmt.setFloat(5, raya.getSueldo());
			sp_insert_lista_raya25pstmt.setFloat(6, raya.getP_bono_complementario());
			
			float saldo_pres_inicial = raya.getSaldo_prestamo_inicial();
			
			sp_insert_lista_raya25pstmt.setFloat(7, saldo_pres_inicial);
			sp_insert_lista_raya25pstmt.setFloat(8, descuento);
			float finalSaldo = raya.getSaldo_final();
			sp_insert_lista_raya25pstmt.setFloat(9, finalSaldo);
			
			sp_insert_lista_raya25pstmt.setFloat(10, raya.getD_fuente_sodas());
			sp_insert_lista_raya25pstmt.setFloat(11, raya.getD_puntualidad());
			sp_insert_lista_raya25pstmt.setFloat(12, raya.getD_faltas());
			sp_insert_lista_raya25pstmt.setFloat(13, raya.getD_asistencia());
			sp_insert_lista_raya25pstmt.setFloat(14, raya.getD_cortes());
			sp_insert_lista_raya25pstmt.setFloat(15, raya.getD_infonavit());
			sp_insert_lista_raya25pstmt.setFloat(16, raya.getPension());
			sp_insert_lista_raya25pstmt.setFloat(17, raya.getD_banamex());
			sp_insert_lista_raya25pstmt.setFloat(18, raya.getD_banorte());
			sp_insert_lista_raya25pstmt.setFloat(19, raya.getD_extra());
			sp_insert_lista_raya25pstmt.setFloat(20, raya.getP_dias_extra());
			sp_insert_lista_raya25pstmt.setFloat(21, raya.getP_bono_extra());
			sp_insert_lista_raya25pstmt.setFloat(22, raya.getA_pagar());
			sp_insert_lista_raya25pstmt.setString(23, raya.getObservasion_i());
			sp_insert_lista_raya25pstmt.setString(24, raya.getFecha());
			sp_insert_lista_raya25pstmt.setInt(25, 1);
			
			if(saldo_pres_inicial > 0){
				int Folio_prestamo = getFolio_prestamo(Folio_Empleado);
				if(Folio_prestamo > 0){
					sp_insert_abono4pstmt.setInt(1, Folio_prestamo);
					sp_insert_abono4pstmt.setInt(2, Folio_Empleado);
					sp_insert_abono4pstmt.setFloat(3, descuento);
					sp_insert_abono4pstmt.setInt(4, 1);
					
					sp_insert_abono4pstmt.execute();
				}
				if(finalSaldo == 0){
					sp_update_abono2pstmt.setInt(1, Folio_Empleado);
					sp_update_abono2pstmt.setInt(2, Folio_prestamo);
					sp_update_abono2pstmt.execute();
					
					sp_update_prestamo1pstmt.setInt(1,Folio_Empleado);
					sp_update_prestamo1pstmt.execute();
				}
			}
			
			if(raya.getD_cortes() > 0 ){
				String[] final_corte = getFinalCorte(Folio_Empleado);
				int folio_corte = Integer.parseInt(final_corte[0]);
				float cantida_corte = Float.parseFloat(final_corte[1]);
				float abonos_corte = Float.parseFloat(final_corte[2]);
				float corte = raya.getD_cortes();
				
				sp_insert_abono_cortes4pstmt.setInt(1, folio_corte);
				sp_insert_abono_cortes4pstmt.setInt(2, Folio_Empleado);
				sp_insert_abono_cortes4pstmt.setFloat(3, corte);
				sp_insert_abono_cortes4pstmt.setInt(4, 1);
					
				sp_insert_abono_cortes4pstmt.execute();
				
				if((cantida_corte - (abonos_corte+corte)) == 0){
					sp_update_abono_cortes2pstmt.setInt(1, Folio_Empleado);
					sp_update_abono_cortes2pstmt.setInt(2, folio_corte);
					sp_update_abono_cortes2pstmt.execute();
					
					sp_update_cortes1pstmt.setInt(1, Folio_Empleado);
					sp_update_cortes1pstmt.execute();
				}

			}
			
			sp_update_status_persecciones1pstmt.setInt(1, Folio_Empleado);
			sp_update_status_deduci_inasis1pstmt.setInt(1, Folio_Empleado);
			
			sp_update_autorizaciones2pstmt.setString(1, "false");
			sp_update_autorizaciones2pstmt.setString(2, "false");
			
			/** EJECUTA EL PROCEDIMIENTO ALMACENADO sp_insert_lista_raya**/
			sp_insert_lista_raya25pstmt.execute();
			
			/** EJECUTA EL RESETEO DE TABLAS**/
			sp_update_status_persecciones1pstmt.execute();
			sp_update_status_deduci_inasis1pstmt.execute();
			
			/** EJECUTA LAS AUTORIZACIONES A FALSO **/
			sp_update_autorizaciones2pstmt.execute();
			
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
		}finally{
			try {				
				sp_insert_lista_raya25pstmt.close();
				sp_insert_abono4pstmt.close();
				sp_update_abono2pstmt.close();
				sp_update_prestamo1pstmt.close();
				sp_insert_abono_cortes4pstmt.close();
				sp_update_abono_cortes2pstmt.close();
				sp_update_cortes1pstmt.close();
				sp_update_status_persecciones1pstmt.close();
				sp_update_status_deduci_inasis1pstmt.close();
				sp_update_autorizaciones2pstmt.close();
			
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean lista_Imprimir(Obj_Revision_Lista_Raya raya){
		
		String query ="delete tb_imprimir_lista_raya";

		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);

			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Imprimir(Obj_Revision_Lista_Raya raya){
		
		String query ="insert into tb_imprimir_lista_raya(numero_lista,folio_empleado,nombre_completo,establecimiento,sueldo,"+
						 "p_bono_comptario,saldo_prest_inic,d_prestamo,saldo_prest_fina,d_fte_sodas,"+
						 "d_puntualidad,d_falta,d_asistencia,d_corte,d_infonavit,pension,"+
						 "d_banamex,d_banorte,d_extra,p_dias_extra,p_bono_extra,"+
						 "a_pagar,observaciones,status) " +
						 "values(?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?,?);";

		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);

			int Folio_Empleado = raya.getFolio_empleado();
			float descuento = raya.getD_prestamo();
			pstmt.setInt(1, raya.getNumero_lista());
			pstmt.setInt(2, Folio_Empleado);
			pstmt.setString(3, raya.getNombre_completo().toUpperCase());
			pstmt.setString(4, raya.getEstablecimiento().toUpperCase());
			pstmt.setFloat(5, raya.getSueldo());
			pstmt.setFloat(6, raya.getP_bono_complementario());

			float saldo_pres_inicial = raya.getSaldo_prestamo_inicial();
			
			pstmt.setFloat(7, saldo_pres_inicial);
			pstmt.setFloat(8, descuento);
			float finalSaldo = raya.getSaldo_final();
			pstmt.setFloat(9, finalSaldo);
			pstmt.setFloat(10, raya.getD_fuente_sodas());
			pstmt.setFloat(11, raya.getD_puntualidad());
			pstmt.setFloat(12, raya.getD_faltas());
			pstmt.setFloat(13, raya.getD_asistencia());
			pstmt.setFloat(14, raya.getD_cortes());
			pstmt.setFloat(15, raya.getD_infonavit());
			pstmt.setFloat(16, raya.getPension());
			pstmt.setFloat(17, raya.getD_banamex());
			pstmt.setFloat(18, raya.getD_banorte());
			pstmt.setFloat(19, raya.getD_extra());
			pstmt.setFloat(20, raya.getP_dias_extra());
			pstmt.setFloat(21, raya.getP_bono_extra());
			pstmt.setFloat(22, raya.getA_pagar());
			pstmt.setString(23, raya.getObservasion_i());
			pstmt.setInt(24, 1);
			
			pstmt.execute();
			
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public int getFolio_prestamo(int folio){
		int valor = 0;
	try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select folio from tb_prestamo where folio_empleado = "+folio+" and status = 1");
			while(rs.next()){
				valor = rs.getInt(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
		
	public String[] getFinalCorte(int folio){
		String[] valor = new String[3];
		valor[0] = "";	valor[1] = "";	valor[2] = "";
	try {
		Connexion con = new Connexion();
		Statement s = con.conexion().createStatement();
		ResultSet rs = s.executeQuery("exec sp_get_parametros_cortes "+folio);
			while(rs.next()){
				valor[0] = rs.getString(1);
				valor[1] = rs.getString(2);
				valor[2] = rs.getString(3);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
}