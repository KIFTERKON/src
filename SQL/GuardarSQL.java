package SQL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import ObjetoChecador.ObjHorario;
import ObjetoChecador.Obj_Dias_Inhabiles;
import ObjetoChecador.Obj_Mensaje_Personal;
import ObjetoChecador.Obj_Permisos_Checador;

import objetos.Obj_Actividad;
import objetos.Obj_Agregar_Submenus_Nuevos;
import objetos.Obj_Alimentacion_Cortes;
import objetos.Obj_Alimentacion_Cuadrante;
import objetos.Obj_Alimentacion_Denominacion;
import objetos.Obj_Asignacion_Mensajes;
import objetos.Obj_Asistencia_Puntualidad;
import objetos.Obj_Atributos;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Captura_Fuente_Sodas;
import objetos.Obj_Conexion_BD;
import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Cuadrante;
import objetos.Obj_Denominaciones;
import objetos.Obj_Directorios;
import objetos.Obj_Divisa_Y_TipoDeCambio;
import objetos.Obj_Diferencia_Cortes;
import objetos.Obj_Empleado;
import objetos.Obj_Empleados_Cuadrantes;
import objetos.Obj_Equipo_Trabajo;
import objetos.Obj_Establecimiento;
import objetos.Obj_Jefatura;
import objetos.Obj_Mensajes;
import objetos.Obj_Nivel_Critico;
import objetos.Obj_Nivel_Jerarquico;
import objetos.Obj_Nomina;
import objetos.Obj_OpRespuesta;
import objetos.Obj_Importar_Voucher;
import objetos.Obj_Ponderacion;
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
	String Qbitacora ="exec sp_insert_bitacora ?,?,?,?,?";
	PreparedStatement pstmtb = null;
	Obj_Usuario usuario = new Obj_Usuario().LeerSession();
	
				
	public boolean Guardar_Empleado(Obj_Empleado empleado){
		String query = "exec sp_insert_empleado ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			// insert bitacora
			String pc = InetAddress.getLocalHost().getHostName();
			String ip = InetAddress.getLocalHost().getHostAddress();
			pstmtb = con.prepareStatement(Qbitacora);
			pstmtb.setString(1, pc);
			pstmtb.setString(2, ip);
			pstmtb.setInt(3, usuario.getFolio());
			pstmtb.setString(4, "sp_insert_empleado alta "+empleado.getNombre().toUpperCase()+empleado.getAp_paterno().toUpperCase()+empleado.getAp_materno().toUpperCase());
			pstmtb.setString(5, "Empleados Nuevo");
			pstmtb.executeUpdate();
			
			
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
			
			FileInputStream stream_foto = new FileInputStream(empleado.getFoto());
			pstmt.setBinaryStream(20, stream_foto, empleado.getFoto().length());

			pstmt.setInt(21, empleado.getTipo_banco());
			pstmt.setString(22, empleado.getTargeta_nomina());
			pstmt.setString(23, empleado.getFecha_nacimiento());
			pstmt.setString(24, empleado.getImss().toUpperCase().trim());
			pstmt.setInt(25, empleado.getStatus_imss());
			pstmt.setString(26, empleado.getFecha_ingreso());
			pstmt.setString(27, empleado.getTelefono_familiar());
			pstmt.setInt(28, empleado.isCuadrante_parcial() ? 1 : 0);
			pstmt.setInt(29, empleado.getTurno());
			pstmt.setInt(30, empleado.getStatus_2h());
			pstmt.setInt(31, empleado.getTurno2());
			
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
	
//	public boolean generar_gafete(){
////		string query = "exec sp_insert_"
//	}
		
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
	
	public boolean Guardar_Dia_Inhabil(Obj_Dias_Inhabiles diaInA){
		String query = "exec sp_insert_diaInHabil ?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, diaInA.getFecha());
			pstmt.setString(2, diaInA.getDescripcion().toUpperCase());
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
	
	public boolean Guardar_Cuadrante(Obj_Cuadrante cuadrante){
		String query = "exec sp_insert_cuadrante ?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(query);
			
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
	
	public boolean Guardar_Cuadrante_Tabla(Obj_Cuadrante cuadrante, String[][] tabla){
		String querytabla = "exec sp_insert_tabla_cuadrante ?,?,?,?,?,?,?";
				
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtTabla = null;
		try {
			con.setAutoCommit(false);
			
			pstmtTabla = con.prepareStatement(querytabla);
				
			for(int i=0; i<tabla.length; i++){
				pstmtTabla.setString(1, cuadrante.getCuadrante().toUpperCase());
				pstmtTabla.setInt(2, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmtTabla.setString(3, tabla[i][2].toString().trim());
				pstmtTabla.setInt(4, Boolean.parseBoolean(tabla[i][3]) ? 1 : 0);
				pstmtTabla.setString(5, tabla[i][4]);
				pstmtTabla.setString(6, tabla[i][5]);
				pstmtTabla.setString(7, tabla[i][6]);
				
				pstmtTabla.executeUpdate();
			}
			
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
	
	public boolean opcion_respuesta(Obj_OpRespuesta respuesta){
		String query = "exec sp_insert_opciones_respuesta	?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, respuesta.getNombre().toUpperCase().trim());
			pstmt.setInt(2, respuesta.getTipo_opcion().equals("Opción Libre") ? 0 : 1);
			pstmt.setInt(3, respuesta.isStatus() ? 1 : 0);
			
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
	public boolean Guardar_Nivel_G(Obj_Nivel_Jerarquico pond){
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
		String query = "exec sp_insert_actividad ?,?,?,?,?,?,?,?";
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
			pstmt.setString(6, actividad.getTemporada());
			pstmt.setInt(7, actividad.isCarga() ? 1 : 0);
			pstmt.setInt(8, actividad.getRepetir());
			
			
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
	
	public boolean Guardar_Tabla_Nivel(Obj_Nivel_Jerarquico pond){
		String query = "exec sp_insert_nivel_jerarquico ?,?";
		String querytabla="exec sp_insert_tabla_nivel_jerarquico ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		PreparedStatement pstmtabla =null;
		try {
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmtabla=con.prepareStatement(querytabla);
			
			
			pstmt.setString (1, pond.getDescripcion());
			pstmt.setString (2, pond.getPuesto_principal());
			
				pstmtabla.setInt (1, pond.getFolio());
				pstmtabla.setString (2, pond.getPuesto_dependiente());
				pstmtabla.setString (3, pond.getEstablecimiento());
				pstmtabla.executeUpdate();
				
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
	
	public boolean Guardar_Mensaje_Personal(Obj_Mensaje_Personal MsjPersonal){
		String query = "exec sp_insert_mensaje ?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString (1, MsjPersonal.getFechaInicial().trim());
			pstmt.setString (2, MsjPersonal.getFechaFin().trim());
			pstmt.setString(3, MsjPersonal.getAsunto().toUpperCase().trim());
			pstmt.setString(4, MsjPersonal.getMensaje().toUpperCase().trim());
			pstmt.setBoolean(5, (MsjPersonal.getStatus())? true: false);
			
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
	
	public boolean Guardar_Permiso_Checador(Obj_Permisos_Checador Permiso){
		String query = "exec sp_insert_permiso_checador ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt (1, Permiso.getFolio_empleado());
			pstmt.setInt(2, Permiso.getFolio_usuario());
			pstmt.setString(3,Permiso.getFecha());
			
			
			pstmt.setInt(4, Permiso.getTipo_de_permiso());
			pstmt.setString(5, Permiso.getMotivo().toUpperCase().trim());
			pstmt.setBoolean(6, (Permiso.isStatus())? true: false);
			
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
	
	public boolean Guardar_Tabla_Nivel2(Obj_Nivel_Jerarquico pond,String[][] tabla){
		String query = "exec sp_insert_nivel_jerarquico ?,?";
		String querytabla="exec sp_insert_tabla_nivel_jerarquico ?,?,?";
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

				pstmtabla.setInt (1, pond.getFolio());
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
	
	public boolean Guardar_Empleado_Msj(Obj_Mensaje_Personal Empleado_Msj,String[] tabla){
//		String query = "exec sp_insert_nivel_jerarquico ?,?";
		String querytabla="exec sp_insert_tabla_empleado_mensaje ?,?";
		Connection con = new Connexion().conexion();
//		PreparedStatement pstmt = null;
		PreparedStatement pstmtabla =null;
		try {
			
			con.setAutoCommit(false);
//			pstmt = con.prepareStatement(query);
			pstmtabla=con.prepareStatement(querytabla);
			
			
			for (int i = 0; i < tabla.length; i++) {

				pstmtabla.setInt (1, Empleado_Msj.getFolioMensaje());
				pstmtabla.setString (2, tabla[i]);
				
				pstmtabla.executeUpdate();
				
			}
//			pstmt.executeUpdate();
		
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
	
	public boolean EmpleadoCuadrante(Obj_Empleados_Cuadrantes empleado_cuadrante){
		String query = "exec sp_insert_empleado_cuadrante ?,?";
				
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, empleado_cuadrante.getCuadrante());
			pstmt.setInt(2, empleado_cuadrante.isStatus() ? 1 : 0);

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
	
	public boolean EmpleadoCuadranteTabla(Obj_Empleados_Cuadrantes empleado_cuadrante, String[] lista){
		String querytabla = "exec sp_insert_tabla_empleado_cuadrante ?,?";
				
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtTabla = null;
		try {
			con.setAutoCommit(false);
			pstmtTabla = con.prepareStatement(querytabla);
			
			for(int i=0; i<lista.length; i++){
				pstmtTabla.setString(1, empleado_cuadrante.getCuadrante().toUpperCase().trim());
				pstmtTabla.setInt(2, Integer.parseInt(lista[i]));
				pstmtTabla.executeUpdate();
			}
						
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
	
	public boolean Guardar_Sesion(Obj_Usuario usuario){
		BufferedWriter bufferedWriter = null;
		String nomArchivo = System.getProperty("user.dir")+"\\Config\\users";
		try{
			File archivo = new File(nomArchivo);
			if(archivo.exists()){
				bufferedWriter = new BufferedWriter (new FileWriter(nomArchivo));
							
				bufferedWriter.write(usuario.getFolio()+    		"\n");
				bufferedWriter.write(usuario.getNombre_completo()+	"\n");
				
			}else{
				File folder = new File(System.getProperty("user.dir")+"\\Config");
				folder.mkdirs();
				archivo.createNewFile();
				bufferedWriter = new BufferedWriter (new FileWriter(nomArchivo));
				
				bufferedWriter.write(usuario.getFolio()+    		"\n");
				bufferedWriter.write(usuario.getNombre_completo()+	"\n");
				
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
	
	public boolean guardarAlimentacionCuadrante(Obj_Alimentacion_Cuadrante datos_cuadrante){
		String query = "exec sp_insert_alimentacion_cuadrante ?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
					
			pstmt.setString(1, datos_cuadrante.getNombre());
			pstmt.setString(2, datos_cuadrante.getPuesto());
			pstmt.setString(3, datos_cuadrante.getEstablecimiento());
			pstmt.setString(4, datos_cuadrante.getEquipo_trabajo());
			pstmt.setString(5, datos_cuadrante.getJefatura());
			pstmt.setString(6, datos_cuadrante.getFecha());
			pstmt.setString(7, datos_cuadrante.getDia());
			pstmt.setString(8, datos_cuadrante.getCuadrante());	
			
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

	public boolean guardar(
			Obj_Agregar_Submenus_Nuevos obj_Agregar_Submenus_Nuevos) {
	        	String query = "exec sp_agregar_nuevo_submenu ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1,obj_Agregar_Submenus_Nuevos.getNombre());
			pstmt.setString(2,obj_Agregar_Submenus_Nuevos.getCatalogo());
			pstmt.setString(3,obj_Agregar_Submenus_Nuevos.getMenu());
						
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
	
	
	//Guardar mensajes
	
	public boolean Guardar_Mensajes(Obj_Mensajes mensaje){
			
			String query = "insert into tb_mensajes(mensaje)values(?)";
			
			Connection con = new Connexion().conexion();
			PreparedStatement pstmt = null;
			try {
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, mensaje.getMensaje().toUpperCase());
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
	
		
	public boolean Insert_Empleado(int folio,String t_entrada){
		
		String insert ="exec sp_insert_entosal "+folio+",?,?,?;";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		
		try{
			String pc_nombre = InetAddress.getLocalHost().getHostName();
			String pc_ip = InetAddress.getLocalHost().getHostAddress();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(insert);
			
			
			pstmt.setString(1, pc_nombre);
			pstmt.setString(2, pc_ip);
			pstmt.setString(3, t_entrada);
			
			pstmt.executeUpdate();
			con.commit();
			
		}catch (Exception e) {
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
		}
		return true;
	}
	
	public boolean Insert_Empleado_Comida(int folio,String t_entrada){
		
		String insert ="exec sp_insert_entosal "+folio+",?,?,?,?;";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		
		try{
			String pc_nombre = InetAddress.getLocalHost().getHostName();
			String pc_ip = InetAddress.getLocalHost().getHostAddress();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(insert);
			
			
			pstmt.setString(1, pc_nombre);
			pstmt.setString(2, pc_ip);
			pstmt.setString(3, t_entrada);
			
			pstmt.executeUpdate();
			con.commit();
			
		}catch (Exception e) {
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
		}
		return true;
	}
	
//	public boolean Guardar_Checador(Obj_Checador chec){
//		String query = "exec sp_insert_checador ?,?,?,?,?,?";
//		Connection con = new Connexion().conexion();
//		PreparedStatement pstmt = null;
//		try {
//			con.setAutoCommit(false);
//			pstmt = con.prepareStatement(query);
//			pstmt.setInt(1,chec.getFolio_emp());
//			pstmt.setInt(2, chec.getClave());
//			pstmt.setString(3, chec.getEntrada());
//			pstmt.setString(4, chec.getSalida());
//			pstmt.setString(5, chec.getEntrada2());
//			pstmt.setString(6, chec.getSalida2());
//		 	pstmt.executeUpdate();
//		 	con.commit();
//		 	
//		} catch (Exception e) {
//			System.out.println("SQLException: "+e.getMessage());
//			if(con != null){
//				try{
//					System.out.println("La transacción ha sido abortada");
//					con.rollback();
//				}catch(SQLException ex){
//					System.out.println(ex.getMessage());
//				}
//			}
//			return false;
//		}finally{
//			try {
//				con.close();
//			} catch(SQLException e){
//				e.printStackTrace();
//			}
//		}		
//		return true;
//	}
	
public boolean Guardar_Asignacion_mensajes(Obj_Asignacion_Mensajes mensj){
		
		String query ="insert into tb_asignacion_mensaje(mensaje,mensajearea,puesto,equipo,jefatura,empleado,rbpuesto,rbequipo,rbjefatura,rbempleado)values(?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, mensj.getNo_mensajes());
			pstmt.setString(2, mensj.getMensaje());
			pstmt.setString(3,mensj.getPuesto() );
			pstmt.setString(4,mensj.getEquipo() );
			pstmt.setString(5,mensj.getJefatura());
			pstmt.setString(6,mensj.getEmpleado());
			
			pstmt.setBoolean(7, mensj.getRbpuesto());
			pstmt.setBoolean(8, mensj.getRbequipo());
			pstmt.setBoolean(9, mensj.getRbjefatura());
			pstmt.setBoolean(10, mensj.getRbempleado());
			
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

//Guardamos el horario
public boolean Guardar_Horario(ObjHorario horario){
	String query = "exec sp_insert_horarios ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
	Connection con = new Connexion().conexion();
	PreparedStatement pstmt = null;
	try {
		con.setAutoCommit(false);
		pstmt = con.prepareStatement(query);
		int i=1;
		pstmt.setString(i, horario.getNombre().trim());
		
		pstmt.setString(i+=1, "Domingo");
		pstmt.setString(i+=1, horario.getDomingo1());
		pstmt.setString(i+=1, horario.getDomingo2());
		pstmt.setString(i+=1, horario.getDomingo3());
		pstmt.setString(i+=1, horario.getDomingo4());
		pstmt.setString(i+=1, horario.getDomingo5());
		
		pstmt.setString(i+=1, "Lunes");
		pstmt.setString(i+=1, horario.getLunes1());
		pstmt.setString(i+=1, horario.getLunes2());
		pstmt.setString(i+=1, horario.getLunes3());
		pstmt.setString(i+=1, horario.getLunes4());
		pstmt.setString(i+=1, horario.getLunes5());
		
		pstmt.setString(i+=1, "Martes");
		pstmt.setString(i+=1, horario.getMartes1());
		pstmt.setString(i+=1, horario.getMartes2());
		pstmt.setString(i+=1, horario.getMartes3());
		pstmt.setString(i+=1, horario.getMartes4());
		pstmt.setString(i+=1, horario.getMartes5());
		
		pstmt.setString(i+=1, "Miércoles");
		pstmt.setString(i+=1, horario.getMiercoles1());
		pstmt.setString(i+=1, horario.getMiercoles2());
		pstmt.setString(i+=1, horario.getMiercoles3());
		pstmt.setString(i+=1, horario.getMiercoles4());
		pstmt.setString(i+=1, horario.getMiercoles5());
		
		pstmt.setString(i+=1, "Jueves");
		pstmt.setString(i+=1, horario.getJueves1());
		pstmt.setString(i+=1, horario.getJueves2());
		pstmt.setString(i+=1, horario.getJueves3());
		pstmt.setString(i+=1, horario.getJueves4());
		pstmt.setString(i+=1, horario.getJueves5());
		
		pstmt.setString(i+=1, "Viernes");
		pstmt.setString(i+=1, horario.getViernes1());
		pstmt.setString(i+=1, horario.getViernes2());
		pstmt.setString(i+=1, horario.getViernes3());
		pstmt.setString(i+=1, horario.getViernes4());
		pstmt.setString(i+=1, horario.getViernes5());
		
		pstmt.setString(i+=1, "Sábado");
		pstmt.setString(i+=1, horario.getSabado1());
		pstmt.setString(i+=1, horario.getSabado2());
		pstmt.setString(i+=1, horario.getSabado3());
		pstmt.setString(i+=1, horario.getSabado4());
		pstmt.setString(i+=1, horario.getSabado5());
		
		pstmt.setInt(i+=1, horario.getDescanso());
		pstmt.setInt(i+=1, horario.getDiaDobla());
		pstmt.setInt(i+=1, horario.getDiaDobla2());
		pstmt.setInt(i+=1, horario.getDiaDobla3());
		
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

	public boolean Insert_Empleado_Comida(int folio, String t_entrada, int tipo_salida_comer) {
		System.out.println("2sad13asd");
		
		System.out.println(tipo_salida_comer);
		
		String insert ="exec sp_insert_entosal_comida_dobla "+folio+",?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		
		try{
			String pc_nombre = InetAddress.getLocalHost().getHostName();
			String pc_ip = InetAddress.getLocalHost().getHostAddress();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(insert);
			
			
			pstmt.setString(1, pc_nombre);
			pstmt.setString(2, pc_ip);
			pstmt.setString(3, t_entrada);
			pstmt.setInt(4, tipo_salida_comer);
			
			pstmt.executeUpdate();
			con.commit();
			
		}catch (Exception e) {
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
		}
		return true;
	}
	
	
		
		//Guardar captura fuente de sodas
		public boolean Guardar_Fuente_Sodas(Obj_Captura_Fuente_Sodas sodas){
			String query = "insert into tb_captura_fuente_sodas(nombre_empleado,no_cliente,ticket,importe,nombre_cliente,establecimiento_cliente,puesto_cliente,foto)values(?,?,?,?,?,?,?,?)";
			Connection con = new Connexion().conexion();
			PreparedStatement pstmt = null;
			try {
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(query);
				
				pstmt.setString(1,sodas.getNombre_cajera());
				pstmt.setInt(2, sodas.getNo_cliente());
				pstmt.setString(3,sodas.getTicket());
				pstmt.setDouble(4,sodas.getImporte());
				pstmt.setString(5, sodas.getNombre_cliente());
				pstmt.setString(6,sodas.getEstablecimiento_cliente());
				pstmt.setString(7, sodas.getPuesto_cliente());
				
				FileInputStream stream_foto = new FileInputStream(sodas.getFoto());
				pstmt.setBinaryStream(8, stream_foto, sodas.getFoto().length());
				
				
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
	
		public boolean buscarBorrarPDependiente(String nombre, int folio_tabla,String establecimineto){
			String query = "exec sp_folio_puesto_dependiente '"+nombre+"', "+folio_tabla+",'"+establecimineto+"'";
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
		
		public boolean buscarBorrarPermiso(int folio){
			String query = "exec sp_folio_puesto_dependiente "+folio;
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
}