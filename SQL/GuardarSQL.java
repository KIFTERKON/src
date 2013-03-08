package SQL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import catalogos.Cat_Lista_Raya;

import objetos.Obj_Asistencia_Puntualidad;
import objetos.Obj_Bancos;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Conexion_BD;
import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Deduccion_Iasistencia;
import objetos.Obj_Diferencia_Cortes;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Lista_Raya;
import objetos.Obj_Persecciones_Extra;
import objetos.Obj_Prestamo;
import objetos.Obj_Puesto;
import objetos.Obj_Rango_Prestamos;
import objetos.Obj_Sueldo;
import objetos.Obj_Turno;
import objetos.Obj_Usuario;
import objetos.Obj_fuente_sodas_auxf;
import objetos.Obj_fuente_sodas_rh;

public class GuardarSQL {
	
	
	public boolean Guardar_Empleado(Obj_Empleado empleado){
		String query = "insert into tb_empleado(" +
				"no_checador,nombre,ap_paterno,ap_materno,establecimiento_id," +
				"puesto_id,turno_id,descanso,dia_dobla,sueldo_id," +
				"bono_id,rango_prestamo_id,pension_alimenticia,infonavit,fuente_sodas," +
				"gafete,status,fecha,observaciones,foto) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		String query = "insert into tb_establecimiento(nombre,abreviatura,status) values(?,?,?)";
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
		String query = "insert into tb_puesto(nombre,abreviatura,status) values(?,?,?)";
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
	
	public boolean Guardar_Bono(Obj_Bono_Complemento_Sueldo bono){
		String query = "insert into tb_bono(bono,abreviatura,status) values(?,?,?)";
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
		String query = "insert into tb_sueldo(sueldo,status) values(?,?)";
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
		String query = "insert into tb_usuario(nombre_completo,contrasena,permiso_id,fecha,status) values(?,?,?,?,?)";
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
		String query = "insert into tb_fuente_sodas_rh(status_ticket,folio_empleado,nombre_completo,cantidad,fecha,status) values(?,?,?,?,?,?)";
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
		String query = "insert into tb_fuente_sodas_auxf(status_ticket,folio_empleado,nombre_completo,cantidad,fecha,status) values(?,?,?,?,?,?)";
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
		String query = "insert into tb_prestamo(folio_empleado,nombre_completo,fecha,cantidad,descuento,status,saldo,status_descuento,abonos) values(?,?,?,?,?,?,?,?,?)";
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
		String query = "insert into tb_rango_prestamos(minimo,maximo,descuento,status) values(?,?,?,?)";
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
		String query = "insert into tb_deduccion_inasistencia(folio_empleado,nombre_completo,establecimiento,puntualidad,falta,dia_faltas,asistencia,gafete,dia_gafete,extra,status) " +
						"values(?,?,?,?,?,?,?,?,?,?,?);";
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
			pstmt.setInt(11 , 1);
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
		String query = "insert into tb_asistencia_puntualidad(asistencia,puntualidad,gafete) values(?,?,?);";
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
		String query = "insert into tb_persecciones_extra(folio_empleado,nombre_completo,establecimiento,bono,dia_extra,dias,status) " +
						"values(?,?,?,?,?,?,?);";
		System.out.println(query);
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
			pstmt.setInt(7 , 1);
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
		String query = "insert into tb_bancos(folio_empleado,nombre_completo,establecimiento,banamex,banorte,status) " +
						"values(?,?,?,?,?,?);";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bancos.getFolio_empleado());
			pstmt.setString(2, bancos.getNombre_completo().toUpperCase());
			pstmt.setString(3, bancos.getEstablecimiento().toUpperCase());
			pstmt.setInt(4, bancos.getBanamex());
			pstmt.setInt(5, bancos.getBanorte());
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
	
	public boolean Guardar(Obj_Diferencia_Cortes pres){
		String query = "insert into tb_diferencia_cortes(folio_empleado,nombre_completo,fecha,cantidad,descuento,status,saldo,status_descuento,abonos) values(?,?,?,?,?,?,?,?,?)";
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
		String query = "insert into tb_turno(nombre,horario,status) values(?,?,?)";
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
	
	
	public boolean Guardar_Pre_Lista(Obj_Lista_Raya raya){
		
		String query ="insert into tb_pre_listaraya(boolean,folio_empleado,observasion_i,observasion_ii,status)" +
				" values(?,?,?,?,?);";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, (raya.isChecado()) ? "true": "false");
			pstmt.setInt(2, raya.getFolio_empleado());
			pstmt.setString(3, raya.getObservasion_i());
			pstmt.setString(4, raya.getObservasion_ii());
			pstmt.setInt(5, 1);

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
		String query = "insert into tb_configuracion_sistema(bono_10_12,bono_dia_extra) values(?,?)";
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
	
	public boolean Guardar(Obj_Lista_Raya raya){
		
		String query ="insert into tb_lista_raya(numero_lista,folio_empleado,nombre_completo,establecimiento,sueldo,"+
						 "p_bono_comptario,saldo_prest_inic,d_prestamo,saldo_prest_fina,d_fte_sodas,"+
						 "d_puntualidad,d_falta,d_asistencia,d_corte,d_infonavit,"+
						 "d_banamex,d_banorte,d_extra,p_dias_extra,p_bono_extra,"+
						 "a_pagar,observaciones,status) " +
						 "values(?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?);";
		String queryI = "insert into tb_abono(folio_prestamo,folio_empleado,descuento,status) values(?,?,?,?);";

		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		PreparedStatement pstmtt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmtt = con.prepareStatement(queryI);

			Cat_Lista_Raya lista = new Cat_Lista_Raya(); 
			
			
			int Folio_Empleado = raya.getFolio_empleado();
			float descuento = raya.getD_prestamo();
			pstmt.setInt(1, 1);
			pstmt.setInt(2, Folio_Empleado);
			pstmt.setString(3, raya.getNombre_completo().toUpperCase());
			pstmt.setString(4, raya.getEstablecimiento().toUpperCase());
			pstmt.setFloat(5, raya.getSueldo());
			pstmt.setFloat(6, raya.getP_bono_complementario());
			pstmt.setFloat(7, raya.getSaldo_prestamo_inicial());
			pstmt.setFloat(8, descuento);
			pstmt.setFloat(9, raya.getSaldo_final());
			pstmt.setFloat(10, raya.getD_fuente_sodas());
			pstmt.setFloat(11, raya.getD_puntualidad());
			pstmt.setFloat(12, raya.getD_faltas());
			pstmt.setFloat(13, raya.getD_asistencia());
			pstmt.setFloat(14, raya.getD_cortes());
			pstmt.setFloat(15, raya.getD_infonavit());
			pstmt.setFloat(16, raya.getD_banamex());
			pstmt.setFloat(17, raya.getD_banorte());
			pstmt.setFloat(18, raya.getD_extra());
			pstmt.setFloat(19, raya.getP_dias_extra());
			pstmt.setFloat(20, raya.getP_bono_extra());
			pstmt.setFloat(21, raya.getA_pagar());
			pstmt.setString(22, raya.getObservasion_i());
			pstmt.setInt(23, 1);
			
			int Folio_prestamo = lista.getFolio_prestamo(Folio_Empleado);
			if(Folio_prestamo > 0){
				pstmtt.setInt(1, Folio_prestamo);
				pstmtt.setInt(2, Folio_Empleado);
				pstmtt.setFloat(3, descuento);
				pstmtt.setInt(4, 1);
				pstmtt.executeUpdate();
			}
			
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
public boolean lista_Imprimir(Obj_Lista_Raya raya){
		
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
	
public boolean Guardar_Imprimir(Obj_Lista_Raya raya){
		
		String query ="insert into tb_imprimir_lista_raya(numero_lista,folio_empleado,nombre_completo,establecimiento,sueldo,"+
						 "p_bono_comptario,saldo_prest_inic,d_prestamo,saldo_prest_fina,d_fte_sodas,"+
						 "d_puntualidad,d_falta,d_asistencia,d_corte,d_infonavit,"+
						 "d_banamex,d_banorte,d_extra,p_dias_extra,p_bono_extra,"+
						 "a_pagar,observaciones,status) " +
						 "values(?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?,?,?," +
						 "?,?,?);";

		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);

			int Folio_Empleado = raya.getFolio_empleado();
			float descuento = raya.getD_prestamo();
			pstmt.setInt(1, 1);
			pstmt.setInt(2, Folio_Empleado);
			pstmt.setString(3, raya.getNombre_completo().toUpperCase());
			pstmt.setString(4, raya.getEstablecimiento().toUpperCase());
			pstmt.setFloat(5, raya.getSueldo());
			pstmt.setFloat(6, raya.getP_bono_complementario());
			pstmt.setFloat(7, raya.getSaldo_prestamo_inicial());
			pstmt.setFloat(8, descuento);
			pstmt.setFloat(9, raya.getSaldo_final());
			pstmt.setFloat(10, raya.getD_fuente_sodas());
			pstmt.setFloat(11, raya.getD_puntualidad());
			pstmt.setFloat(12, raya.getD_faltas());
			pstmt.setFloat(13, raya.getD_asistencia());
			pstmt.setFloat(14, raya.getD_cortes());
			pstmt.setFloat(15, raya.getD_infonavit());
			pstmt.setFloat(16, raya.getD_banamex());
			pstmt.setFloat(17, raya.getD_banorte());
			pstmt.setFloat(18, raya.getD_extra());
			pstmt.setFloat(19, raya.getP_dias_extra());
			pstmt.setFloat(20, raya.getP_bono_extra());
			pstmt.setFloat(21, raya.getA_pagar());
			pstmt.setString(22, raya.getObservasion_i());
			pstmt.setInt(23, 1);
			
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
	
}