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
import java.util.Vector;

import objetos.Obj_Actividad;
import objetos.Obj_Alimentacion_Cortes;
import objetos.Obj_Alimentacion_Denominacion;
import objetos.Obj_Alimentacion_Totales;
import objetos.Obj_Asistencia_Puntualidad;
import objetos.Obj_Atributos;
import objetos.Obj_Bancos;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Conexion_BD;
import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Cuadrante;
import objetos.Obj_Deduccion_Iasistencia;
import objetos.Obj_Denominaciones;
import objetos.Obj_Directorios;
import objetos.Obj_Divisa_Y_TipoDeCambio;
import objetos.Obj_Diferencia_Cortes;
import objetos.Obj_Empleado;
import objetos.Obj_Equipo_Trabajo;
import objetos.Obj_Establecimiento;
import objetos.Obj_Jefatura;
import objetos.Obj_Nivel_Critico;
import objetos.Obj_Nivel_Gerarquico;
import objetos.Obj_Nomina;
import objetos.Obj_OpRespuesta;
import objetos.Obj_Importar_Voucher;
import objetos.Obj_Ponderacion;
import objetos.Obj_Revision_Lista_Raya;
import objetos.Obj_Persecciones_Extra;
import objetos.Obj_Prestamo;
import objetos.Obj_Puesto;
import objetos.Obj_Rango_Prestamos;
import objetos.Obj_Sueldo;
import objetos.Obj_Temporada;
import objetos.Obj_Tipo_Banco;
import objetos.Obj_Turno;
import objetos.Obj_Usuario;
import objetos.Obj_fuente_sodas_auxf;
import objetos.Obj_fuente_sodas_rh;

public class GuardarSQL {

	public boolean Guardar_Empleado(Obj_Empleado empleado){
		String query = "exec sp_insert_empleado ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
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

			pstmt.setInt(21, empleado.getTipo_banco());
			pstmt.setString(22, empleado.getTargeta_nomina());
			pstmt.setString(23, empleado.getFecha_nacimiento());
			
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
	
	public boolean Guardar_Atributos(Obj_Atributos atrib){
		String query = "exec sp_insert_atributo		 ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, atrib.getDescripcion().toUpperCase());
			pstmt.setFloat(2, atrib.getValor());
			pstmt.setString(3, (atrib.getStatus())?"1":"0");
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
	
	public boolean Guardar_Jefatura(Obj_Jefatura jefat){
		String query = "exec sp_insert_jefatura	?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, jefat.getDescripcion().toUpperCase());
			pstmt.setString(2, (jefat.getStatus())?"1":"0");
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

	public boolean Guardar_Eq_Trabajo(Obj_Equipo_Trabajo EqTabajo){
		String query = "exec sp_insert_equipo_trabajo ?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, EqTabajo.getDescripcion().toUpperCase());
			pstmt.setString(2, (EqTabajo.getStatus())?"1":"0");
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
	
	public boolean Guardar_Ponderacion(Obj_Ponderacion pond){
		String query = "exec sp_insert_ponderacion  ?,?,?,?,?, ?,?,?,?,?, ?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pond.getDescripcion().toUpperCase());
			pstmt.setFloat (2, pond.getValor());
			pstmt.setString(3, pond.getFechaIn());
			pstmt.setString(4, pond.getFechaFin());
			pstmt.setString(5, pond.isDomingo()?"1":"0");
			pstmt.setString(6, pond.isLunes()?"1":"0");
			pstmt.setString(7, pond.isMartes()?"1":"0");
			pstmt.setString(8, pond.isMiercoles()?"1":"0");
			pstmt.setString(9,pond.isJueves()?"1":"0");
			pstmt.setString(10,pond.isViernes()?"1":"0");
			pstmt.setString(11,pond.isSabado()?"1":"0");
			pstmt.setString(12, pond.getStatus()?"1":"0");
			
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
	
	public boolean Guardar_Nivel(Obj_Nivel_Critico nc){
		String query = "exec sp_insert_nivel_critico		 ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nc.getDescripcion().toUpperCase());
			pstmt.setFloat(2, nc.getValor());
			pstmt.setString(3, (nc.getStatus())?"1":"0");
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
	
	public boolean Guardar_Cuadrante(Obj_Cuadrante cuadrante, String[][] tabla){
		String query = "exec sp_insert_cuadrante ?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		String querytabla = "exec sp_insert_tabla_cuadrante ?,?,?,?";
				
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		PreparedStatement pstmtTabla = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(query);
			pstmtTabla = con.prepareStatement(querytabla);
			
			pstmt.setString(1, cuadrante.getCuadrante().toUpperCase());
			pstmt.setString(2, cuadrante.getPerfil().toUpperCase());
			pstmt.setString(3, cuadrante.getJefatura());
			pstmt.setString(4, cuadrante.getNivel_gerarquico());
			pstmt.setString(5, cuadrante.getEquipo_trabajo());
			pstmt.setString(6, cuadrante.getEstablecimiento());
			pstmt.setInt(7, cuadrante.getDomingo());
			pstmt.setInt(8, cuadrante.getLunes());
			pstmt.setInt(9, cuadrante.getMartes());
			pstmt.setInt(10, cuadrante.getMiercoles());
			pstmt.setInt(11, cuadrante.getJueves());
			pstmt.setInt(12, cuadrante.getViernes());
			pstmt.setInt(13, cuadrante.getSabado());
			pstmt.setInt(14, cuadrante.getStatus());
			
			for(int i=0; i<tabla.length; i++){
				pstmtTabla.setString(1, cuadrante.getCuadrante().toUpperCase());
				pstmtTabla.setInt(2, Integer.parseInt(tabla[i][0]));
				pstmtTabla.setString(3, tabla[i][1]);
				pstmtTabla.setString(4, tabla[i][2]);
				pstmtTabla.executeUpdate();
			}
			
			
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
	
	public boolean Guardar_OpRespuesta_Libre(Obj_OpRespuesta respuesta){
		String query = "exec sp_insert_op_respuesta_libre	?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, respuesta.getOpcion());
			pstmt.setString(2, respuesta.getNombre());
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
	
	public boolean Guardar_OpRespuesta_Multiple(Obj_OpRespuesta respuesta, String[] tabla){
		String query = "exec sp_insert_op_respuesta_libre	?,?";
		String querytabla = "exec sp_insert_tabla_op_respuesta_multiple ?,?";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		PreparedStatement pstmttabla = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmttabla = con.prepareStatement(querytabla);
			
			pstmt.setString(1, respuesta.getOpcion());
			pstmt.setString(2, respuesta.getNombre());
			
			for(int i=0; i<tabla.length; i++){
				pstmttabla.setString(1, respuesta.getNombre());
				pstmttabla.setString(2, tabla[i].toUpperCase());
				pstmttabla.executeUpdate();
			}
			
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
	
	
	@SuppressWarnings("rawtypes")
	public boolean Guardar_Usuario(Obj_Usuario usuario,Vector permisos){
		String query = "exec sp_insert_permiso ?,?,?,?,?";
		String insert_usuario = "exec sp_insert_usuario3 ?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		PreparedStatement insert_usuariopstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			insert_usuariopstmt = con.prepareStatement(insert_usuario);
			insert_usuariopstmt.setString(1, usuario.getNombre_completo());
			insert_usuariopstmt.setString(2, usuario.getContrasena());
			insert_usuariopstmt.setInt(3, 0);
			insert_usuariopstmt.setString(4,  new Date().toString());
			insert_usuariopstmt.setString(5, "");
			insert_usuariopstmt.setInt(6, 1);
			insert_usuariopstmt.setInt(7, 0);
			
			for(int i=0; i<permisos.size(); i++){
				pstmt.setInt(1, usuario.getFolio());
				pstmt.setString(2, usuario.getNombre_completo());
				String[] arreglo = permisos.get(i).toString().split("/");
				pstmt.setString(3, arreglo[0]);
				pstmt.setString(4, arreglo[1]);
				pstmt.setInt(5, getMenuId(arreglo[0].toString().trim()));
				pstmt.execute();
			}
			
			insert_usuariopstmt.execute();
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
				pstmt.close();
				insert_usuariopstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public int getMenuId(String Nombre){
		int filas=0;
		try {
			String query = "select menu_id from tb_submenus where nombre ='"+Nombre+"'";
			Statement s = new Connexion().conexion().createStatement();
			ResultSet rs = s.executeQuery(query);
			while(rs.next()){
				filas = rs.getInt(1);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
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
				File folder = new File(System.getProperty("user.dir")+"\\Config");
				folder.mkdirs();
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
		
		String query ="exec sp_insert_pre_listaraya ?,?,?,?,?,?,?,?";
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
			pstmt.setString(7, raya.getEstablecimiento());
			pstmt.setString(8, raya.getFecha_final());

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
				"exec sp_insert_lista_raya ?,?,?,?,?,?,?,?,?,?," +
				"						   ?,?,?,?,?,?,?,?,?,?," +
				"						   ?,?,?,?,?,?";
		
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
		
		/**APLICAR STATUS 0 EN FUENTE DE SODAS DH Y AUXF**/
		String update_fte_dh = "update tb_fuente_sodas_rh set status=? where status = 1";
		String update_fte_auxf = "update tb_fuente_sodas_auxf set status=? where status = 1";
		
		/**RESETEAR LISTA PRE-RAYA**/
		String deletetb_pre_listaraya = "delete from tb_pre_listaraya";
		
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
		PreparedStatement update_fte_dh1pstmt = null;
		PreparedStatement update_fte_auxf1pstmt = null;
		PreparedStatement deletetb_pre_listarayapstmt = null;

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
			update_fte_dh1pstmt = con.prepareStatement(update_fte_dh);
			update_fte_auxf1pstmt = con.prepareStatement(update_fte_auxf);
			deletetb_pre_listarayapstmt = con.prepareStatement(deletetb_pre_listaraya);
			
			int Folio_Empleado = raya.getFolio_empleado();
			float descuento = raya.getD_prestamo();
			
			sp_insert_lista_raya25pstmt.setInt(1, raya.getNumero_lista());
			sp_insert_lista_raya25pstmt.setInt(2, Folio_Empleado);
			sp_insert_lista_raya25pstmt.setString(3, raya.getNombre_completo().toUpperCase().trim());
			sp_insert_lista_raya25pstmt.setString(4, raya.getEstablecimiento().toUpperCase().trim());
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
			sp_insert_lista_raya25pstmt.setFloat(14, raya.getD_gafete());
			sp_insert_lista_raya25pstmt.setFloat(15, raya.getD_cortes());
			
			sp_insert_lista_raya25pstmt.setFloat(16, raya.getD_infonavit());
			sp_insert_lista_raya25pstmt.setFloat(17, raya.getPension());
			sp_insert_lista_raya25pstmt.setFloat(18, raya.getD_banamex());
			sp_insert_lista_raya25pstmt.setFloat(19, raya.getD_banorte());
			sp_insert_lista_raya25pstmt.setFloat(20, raya.getD_extra());
			
			sp_insert_lista_raya25pstmt.setFloat(21, raya.getP_dias_extra());
			sp_insert_lista_raya25pstmt.setFloat(22, raya.getP_bono_extra());
			sp_insert_lista_raya25pstmt.setFloat(23, raya.getA_pagar());
			sp_insert_lista_raya25pstmt.setString(24, raya.getObservasion_i());
			sp_insert_lista_raya25pstmt.setString(25, raya.getFecha());
			sp_insert_lista_raya25pstmt.setInt(26, 1);
			
			
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
			
			update_fte_dh1pstmt.setString(1,"0");
			update_fte_auxf1pstmt.setString(1, "0");
			
			/** EJECUTA EL PROCEDIMIENTO ALMACENADO sp_insert_lista_raya**/
			sp_insert_lista_raya25pstmt.execute();
			
			/** EJECUTA EL RESETEO DE TABLAS**/
			sp_update_status_persecciones1pstmt.execute();
			sp_update_status_deduci_inasis1pstmt.execute();
			
			/** EJECUTA LAS AUTORIZACIONES A FALSO **/
			sp_update_autorizaciones2pstmt.execute();
			
			/** EJECUTA EL RESETEO DE FUENTE DE SODAS LOS STATUS **/
			update_fte_dh1pstmt.execute();
			update_fte_auxf1pstmt.execute();
			
			/** EJECUTAMOS EL RESETEO DE LISTA PRE-RAYA**/
			deletetb_pre_listarayapstmt.execute();
			
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
				update_fte_dh1pstmt.close();
				update_fte_auxf1pstmt.close();
				deletetb_pre_listarayapstmt.close();
				
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean lista_Imprimir(){
		
		String query ="exec sp_imprimir_lista_raya";

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
	
	public boolean Guardar_Costos_Totales(Obj_Alimentacion_Totales costos){
		String query = "exec sp_insert_costos_totales ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
					
			pstmt.setInt(1, costos.getFolio_raya());
			pstmt.setString(2, costos.getEstablecimiento().toUpperCase());
			pstmt.setFloat(3, costos.getNomina());
			
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
	
	public boolean GuardarImportarVoucher(Obj_Importar_Voucher importar){
		String query = "exec sp_insert_voucher_importado ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, importar.getContrato());
			pstmt.setString(2, importar.getF_transaccion());
			pstmt.setString(3, importar.getH_transaccion());
			pstmt.setString(4, importar.getNo_codigo());
			pstmt.setString(5, importar.getLeyenda());
			pstmt.setFloat(6, importar.getImporte());
			pstmt.setString(7, importar.getTerminal());
			pstmt.setString(8, importar.getCuenta());
			pstmt.setString(9, importar.getAutorizacion());
			pstmt.setString(10, importar.getTipo_de_cuenta());
			pstmt.setString(11, importar.getF_abono());
			pstmt.setString(12, importar.getReferencia_1());
			pstmt.setString(13, importar.getReferencia_2());
			pstmt.setString(14, importar.getReferencia_3());
			pstmt.setFloat(15, importar.getQ6());
			pstmt.setFloat(16, importar.getImporta_cash_back());
			pstmt.setFloat(17, importar.getEci());
			pstmt.setString(18, importar.getControl_interno_comercio());
			pstmt.setString(19, importar.getLote1());
			pstmt.setString(20, importar.getLote2());
			
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
	
	public boolean DeleteBancos(){
		String query = "exec sp_borrado_empleados_dif_1";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
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
	
	public boolean Guardar(Obj_Nomina nomina){
		String query = "exec sp_insert_nomina ?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nomina.getNumero_listaraya());
			pstmt.setString(2,nomina.getEstablecimiento());
			pstmt.setString(3,nomina.getNomina());
			pstmt.setString(4,nomina.getPago_linea());
			pstmt.setString(5,nomina.getCheque_nomina());
			pstmt.setString(6,nomina.getLista_raya());
			pstmt.setString(7,nomina.getDiferencia());
			pstmt.setString(8,nomina.getFecha());
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
	
	public boolean TemporadaGuardar(Obj_Temporada temporada){
		String query = "exec sp_insert_temporada ?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
					
			pstmt.setString(1, temporada.getDescripcion());
			pstmt.setString(2, temporada.getFecha_in());
			pstmt.setString(3, temporada.getFecha_fin());
			pstmt.setString(4, temporada.getDia());
			
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
	
	/*****agregando funcion para guardar numeros telefonicos*****/
	public boolean Guardar_Telefono(Obj_Directorios directorio){
		String query = "exec sp_insert_tb_direccion_telefonicos ?,?,?";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1,directorio.getFolio_empleado());
			pstmt.setString(2, directorio.getNombre());
			pstmt.setString(3, directorio.getTelefono());
			
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

	
	//guardar nivel gerarquico
	public boolean Guardar_Nivel_G(Obj_Nivel_Gerarquico pond){
		String query = "exec sp_insert_tb_nivel_gerarquico  ?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString (1, pond.getDescripcion());
			pstmt.setString (2, pond.getPuesto_principal());
			pstmt.setString (3, pond.getPuesto_dependiente());
			pstmt.setString (4, pond.isStatus()?"1":"0");
			
			
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
	
	
	public boolean Guardar_Actividad(Obj_Actividad actividad){
		String query = "exec sp_insert_actividad ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
					
			pstmt.setString(1, actividad.getActividad().toUpperCase());
			pstmt.setString(2, actividad.getDescripcion().toUpperCase());
			pstmt.setString(3, actividad.getRespuesta());
			pstmt.setString(4, actividad.getAtributos());
			pstmt.setString(5, actividad.getNivel_critico());
			pstmt.setInt(6, actividad.getDomingo());
			pstmt.setInt(7, actividad.getLunes());
			pstmt.setInt(8, actividad.getMartes());
			pstmt.setInt(9, actividad.getMiercoles());
			pstmt.setInt(10, actividad.getJueves());
			pstmt.setInt(11, actividad.getViernes());
			pstmt.setInt(12, actividad.getSabado());
			pstmt.setString(13, actividad.getHora_inicio());
			pstmt.setString(14, actividad.getHora_final());
			pstmt.setString(15, actividad.getTemporada());
			pstmt.setBoolean(16, actividad.isCarga());
			pstmt.setInt(17, actividad.getRepetir());
			
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
	
	public boolean Guardar_Tabla_Nivel(Obj_Nivel_Gerarquico pond,String[][] tabla){
		String query = "insert into tb_nivel_gerarquico(descripcion,puesto_principal) values(?,?)";
		String querytabla="insert into tb_tabla_nivel_gerarquico (nombre,puesto_dependiente,establecimiento)values(?,?,?)";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		PreparedStatement pstmtabla =null;
		try {
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmtabla=con.prepareStatement(querytabla);
			
			
			pstmt.setString (1, pond.getDescripcion());
			pstmt.setString (2, pond.getPuesto_principal());
			
			for (int i = 0; i < tabla.length; i++) {

				pstmtabla.setString (1, pond.getDescripcion().toUpperCase());
				
				pstmtabla.setString (2, tabla[i][0]);
				pstmtabla.setString (3, tabla[i][1]);
				pstmtabla.executeUpdate();
				
			}
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
	
}