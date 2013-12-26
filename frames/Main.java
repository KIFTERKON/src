package frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import SQL.Connexion;
import catalogos.Cat_Conexion_BD;
import objetos.Obj_Auto_Auditoria;
import objetos.Obj_Auto_Finanzas;
import objetos.Obj_MD5;
import objetos.Obj_Main;
import objetos.Obj_Usuario;

/*****************************************************
***												   *** 
***     https://github.com/RguezMario/src.git      ***
***	                     						   ***
***	                 Colaboradores				   ***
***												   ***
***	    online + Jimenez Molina Edgar Eduardo      ***
***	    offline+ López Arballo Oscar Manuel        ***
***	    online+ Rodríguez Sánchez José Mario      ***
***		online + Marco Antonio Bodart Guzman	   ***
***												   ***
*****************************************************/

@SuppressWarnings("serial")
public class Main extends InitButton {
	
	public Main(){
//----Edicion de ToolTipText---------------------------------------------------------------------------------
		try { 
			UIManager.put ("ToolTip.background", Color.black); 
			UIManager.put ("ToolTip.foreground", Color.white); 
		} catch (Throwable th) { 
			th.printStackTrace (); 
		} 
		//asigna color de fondo,color de texto al JLabel
		lblLogo.updateUI ();
		//cacha la ruta e informacion con HTML para despues asignarla al JLabel
		String tooltiptext ="<html>" +
		 						"<img src=\"file:iconoChecador.jpg\">" + "<br/>" +
		 							"<DIV ALIGN=center>" +
		 									"CHECADOR!!!" +
		 							"</DIV>" +
		 					"</html>";
		lblLogo.setToolTipText(tooltiptext);
//----Termina Edicion de ToolTipText--------------------------------------------------------------------------
		
		btnAceptar.addActionListener(opIn);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	ActionListener opIn = new ActionListener(){
		@SuppressWarnings({ "deprecation", "static-access" })
		public void actionPerformed(ActionEvent arg0) {
			if(txtContrasena.getText().length() != 0){
				Obj_MD5 algoritmo = new Obj_MD5();
				Obj_Usuario user = new Obj_Usuario().buscar(Integer.parseInt(txtFolio.getText()));

				if(!algoritmo.cryptMD5(txtContrasena.getText(), "izagar").trim().toLowerCase().equals(user.getContrasena().trim().toLowerCase())){
					JOptionPane.showMessageDialog(null, "La contraseña no es válida...","Aviso",JOptionPane.WARNING_MESSAGE);
					txtContrasena.setText("");
					txtContrasena.requestFocus(true);
					return;
				}else{
					txtContrasena.setText("");
					txtContrasena.requestFocus(true);
					btnBuscar.setEnabled(false);
					btnAceptar.setEnabled(false);
					init_subMenus();
					user.Session();
				}
			}else{
				JOptionPane.showMessageDialog(null, "La contraseña está vacía...","Aviso",JOptionPane.WARNING_MESSAGE);
				txtContrasena.requestFocus(true);
				return;
			}
		}
		
	};
		
	public void init_subMenus(){
		try {
			Obj_Auto_Auditoria auditoria = new Obj_Auto_Auditoria().buscar();
			Obj_Auto_Finanzas finanzas = new Obj_Auto_Finanzas().buscar();
			
			boolean auditoriaBoolean = auditoria.isAutorizar();
			boolean finanzasBoolean = finanzas.isAutorizar();
			
			if((auditoriaBoolean == true)  || (finanzasBoolean == true)){
				Object[] permisos = new Obj_Main().Permisos(txtUsuario.getText());
				for(int i=0; i<permisos.length; i++){
					
					/* CATALOGO */
					if(permisos[i].equals("Nuevo Departamento")){
						Catalogo_Departamento.setEnabled(false);
					}
					if(permisos[i].equals("Nuevo Empleado")){
						Catalogo_Empleado.setEnabled(false);
						btnAltaEmp.setEnabled(false);
					}
					if(permisos[i].equals("Nuevo Establecimiento"))
						Catalogo_Establecimiento.setEnabled(true);
					if(permisos[i].equals("Nuevo Puesto")){
						Catalogo_Puesto.setEnabled(true);
						btnPuesto.setEnabled(true);
					}
					if(permisos[i].equals("Nuevo Rango de Prestamo"))
						Catalogo_Rango_Prestamo.setEnabled(true);
					if(permisos[i].equals("Nuevo Sueldo")){
						Catalogo_Sueldo.setEnabled(false);
						btnSueldo.setEnabled(false);
					}
					if(permisos[i].equals("Nuevo Tipos de Bancos"))
						Catalogo_Tipo_Banco.setEnabled(true);
					if(permisos[i].equals("Nuevo Turno"))
						Catalogo_Turno.setEnabled(true);
					
					/* CONFIGURACION */
					if(permisos[i].equals("Configuración de Asistencia y Puntualidad"))
						Configuracion_Asistencia_Puntualidad.setEnabled(false);
					if(permisos[i].equals("Configuración de Base de Datos"))
						Configuracion_ConexionBD.setEnabled(true);
					if(permisos[i].equals("Configuración de Bono"))
						Configuracion_Bono.setEnabled(false);
					if(permisos[i].equals("Configuración de Denominaciones"))
						Configuracion_Denominaciones.setEnabled(true);
					if(permisos[i].equals("Configuración de Divisas y Tipo de Cambio"))
						Configuracion_Divisas.setEnabled(true);
					if(permisos[i].equals("Configuración Mantenimiento Base de Datos"))
						Configuracion_Mantenimiento.setEnabled(true);
					if(permisos[i].equals("Configuración de Sistema"))
						Configuracion_Sistema.setEnabled(true);
					if(permisos[i].equals("Configuración de Usuarios"))
						Configuracion_Usuario.setEnabled(true);
					
					/* CONTABILIDAD */
					if(permisos[i].equals("Importar Auxiliar"))
						Importar_Auxiliar.setEnabled(true);
					if(permisos[i].equals("Importar Cheques"))
						Importar_Cheques.setEnabled(true);
					if(permisos[i].equals("Importar Conciliación AuxF"))
						Importar_Consiliacion.setEnabled(true);
					if(permisos[i].equals("Importar Voucher"))
						Importar_Voucher.setEnabled(true);
					if(permisos[i].equals("Reporte de Apartados y Abonos en una Asignacion"))
						Egresos_Reporte_de_apartados_y_abonos.setEnabled(true);
													
					/*CORTES*/
					if(permisos[i].equals("Captura de Cortes de Cajeras"))
						Captura_Cortes .setEnabled(true);
					
					/* CUADRANTES 
					*		ALIMENTACION */
					if(permisos[i].equals("Alimentación de Cuadrantes"))
						Cuadrantes_Alimentacion_Actividades_Cuadrantes.setEnabled(true);
					if(permisos[i].equals("Cuadrante"))
						Cuadrantes_Alimentacion_Cuadrante.setEnabled(true);
					if(permisos[i].equals("Empleados en Cuadrantes"))
						Cuadrantes_Alimentacion_Empleados_Cuadrantes.setEnabled(true);	
					/* CUADRANTES
					 * 		CATALOGO */
					if(permisos[i].equals("Actividades"))
						Cuadrantes_Catalogo_Actividades.setEnabled(true);
					if(permisos[i].equals("Asignación de Telefonos"))
						Cuadrantes_Catalogo_Telefono.setEnabled(true);
					if(permisos[i].equals("Atributos"))
						Cuadrantes_Catalogo_Atributos.setEnabled(true);
					if(permisos[i].equals("Equipo de Trabajo"))
						Cuadrantes_Catalogo_Equipo_Trabajo.setEnabled(true);
					if(permisos[i].equals("Jefatura"))
						Cuadrantes_Catalogo_Jefatura.setEnabled(true);
					if(permisos[i].equals("Nivel Crítico"))
						Cuadrantes_Catalogo_Nivel_Critico.setEnabled(true);
					if(permisos[i].equals("Nivel Jerarquico"))
						Cuadrantes_Catalogo_Nivel_Jerarquico.setEnabled(true);
					if(permisos[i].equals("Opciones de Respuesta"))
						Cuadrantes_Catalogo_Respuesta.setEnabled(true);
					if(permisos[i].equals("Opciones Múltiple de Respuesta"))
						Cuadrantes_Catalogo_Respuesta_Multiple.setEnabled(true);
					if(permisos[i].equals("Ponderacion"))
						Cuadrantes_Catalogo_Ponderacion.setEnabled(true);
					/* CUADRANTES
					*		REPORTE */
				
				    if(permisos[i].equals("Impresion de Cuadrante Personal"));
					 Impresion_Cuadrante_Personal.setEnabled(true);
				    if(permisos[i].equals("Reportes Directivo"))
						Cuadrantes_Reportes_Directivo.setEnabled(true);
					if(permisos[i].equals("Reporte Dinamico de Cuadrantes"))
						
						Cuadrantes_Reportes_Dinamico.setEnabled(true);
					if(permisos[i].equals("Reportes Usuario"))
						Cuadrantes_Reportes_Usuario.setEnabled(true);
									
					/* LISTA DE RAYA 
					*		ALIMENTACION */
					if(permisos[i].equals("Alimentación Bancos")){
						Alimentacion_Bancos.setEnabled(false);
						btnBanco.setEnabled(false);
					}
					if(permisos[i].equals("Alimentación de Totales de Nómina"))
						Alimentacion_Captura_Totales_Nomina.setEnabled(false);
					if(permisos[i].equals("Alimentación Deducción por Inasistencia")){
						Alimentacion_Deducciones_Asistencia.setEnabled(false);
						btnInasistencia.setEnabled(false);
					}
					if(permisos[i].equals("Alimentación Diferencia de Cortes")){
						Alimentacion_Diferencia_Cortes.setEnabled(false);
						btnCaja.setEnabled(false);
					}
					if(permisos[i].equals("Alimentación Fuente de Sodas AUXF")){
						Alimentacion_Fuente_Sodas_auxf.setEnabled(false);
						btnFsAux.setEnabled(false);
					}
					if(permisos[i].equals("Alimentación Fuente de Sodas DH")){
						Alimentacion_Fuente_Sodas_rh.setEnabled(false);
						btnFsRH.setEnabled(false);
					}
					if(permisos[i].equals("Alimentación Percepciones Extras")){
						Alimentacion_Percepciones_Extra.setEnabled(false);
						btnPExtras.setEnabled(false);
					}
					if(permisos[i].equals("Alimentación Prestamos")){
						Alimentacion_Prestamos.setEnabled(false);
						btnPrestamo.setEnabled(false);
					}
					/* LISTA DE RAYA 	
					*		AUTORIZACIONES */
					if(permisos[i].equals("Autorizacion Auditoria"))
						Autorizacion_Auditoria.setEnabled(true);
					if(permisos[i].equals("Autorizacion Finanzas"))
						Autorizacion_Finanzas.setEnabled(true);
					/* LISTA DE RAYA 	
					*		COMPARACIONES */
					if(permisos[i].equals("Lista de Comparación FS.")){
						Comparaciones_Listas_Fuente_Sodas.setEnabled(false);
						btnListaComparacion.setEnabled(false);
					}
					if(permisos[i].equals("Lista de Raya")){
						Comparaciones_Listas_Raya.setEnabled(true);
						btnListaRaya.setEnabled(true);
					}
					/* LISTA DE RAYA 	
					*		CHECADOR */
					if(permisos[i].equals("Asignacion de Horario de Temporada")){
						Asignacion_Horario_Temporada.setEnabled(true);
					   }
					if(permisos[i].equals("Checador")){
						Checador_Menu.setEnabled(true);
					   }
					if(permisos[i].equals("Dias Inhabiles")){
						Dias_Inhabiles.setEnabled(false);
								}
					if(permisos[i].equals("Generacion de Gafetes de Empleados")){
						Generacion_Gafetes_Empleados.setEnabled(true);
								}
					if(permisos[i].equals("Horarios")){
						Horarios.setEnabled(true);
								}
					if(permisos[i].equals("Mensajes Personales a Empleados")){
						Mensajes_Personales.setEnabled(true);
								}
					if(permisos[i].equals("Permisos a Empleados")){
						Permisos_Empleados.setEnabled(true);
								}
					if(permisos[i].equals("Reporte General de Asistencia")){
						Reportes_Checador_Gral.setEnabled(true);
								}

					/* LISTA DE RAYA 
					*		DEPARTAMENTO DE CORTES */
					if(permisos[i].equals("Alimentación de Cortes"))
						Departamento_Cortes_Alimentacion.setEnabled(false);
					/* LISTA DE RAYA 
					*		REPORTES */
					if(permisos[i].equals("Reporte Deducciones Por Inasistencia"))
						Reporte_Deducciones_Inasistencia.setEnabled(true);
					if(permisos[i].equals("Reporte Depositos A Bancos"))
						Reporte_Bancos.setEnabled(true);
					if(permisos[i].equals("Reporte Fuente Sodas"))
						Reporte_Fuente_Sodas.setEnabled(true);
					if(permisos[i].equals("Reporte Lista de Firmas")){
						Reporte_Lista_Firma.setEnabled(true);
						btnListaFirma.setEnabled(true);
					}
					if(permisos[i].equals("Reporte Lista de Raya"))
						Reporte_Lista_Raya.setEnabled(true);
					if(permisos[i].equals("Reporte Plantilla Activa"))
						Reporte_Plantilla_Activa.setEnabled(true);
					if(permisos[i].equals("Reporte Prestamos"))
						Reporte_Prestamos.setEnabled(true);
				}
			}else{
				Object[] permisos = new Obj_Main().Permisos(txtUsuario.getText());
				for(int i=0; i<permisos.length; i++){
					
					/* CATALOGO */
					if(permisos[i].equals("Nuevo Departamento")){
						Catalogo_Departamento.setEnabled(true);
					}
					if(permisos[i].equals("Nuevo Empleado")){
						Catalogo_Empleado.setEnabled(true);
						btnAltaEmp.setEnabled(true);
					}
					if(permisos[i].equals("Nuevo Establecimiento"))
						Catalogo_Establecimiento.setEnabled(true);
					if(permisos[i].equals("Nuevo Puesto")){
						Catalogo_Puesto.setEnabled(true);
						btnPuesto.setEnabled(true);
					}
					if(permisos[i].equals("Nuevo Rango de Prestamo"))
						Catalogo_Rango_Prestamo.setEnabled(true);
					if(permisos[i].equals("Nuevo Sueldo")){
						Catalogo_Sueldo.setEnabled(true);
						btnSueldo.setEnabled(true);
					}
					if(permisos[i].equals("Nuevo Tipos de Bancos"))
						Catalogo_Tipo_Banco.setEnabled(true);
					if(permisos[i].equals("Nuevo Turno"))
						Catalogo_Turno.setEnabled(true);
					
					/* CONFIGURACION */
					if(permisos[i].equals("Configuración de Asistencia y Puntualidad"))
						Configuracion_Asistencia_Puntualidad.setEnabled(true);
					if(permisos[i].equals("Configuración de Base de Datos"))
						Configuracion_ConexionBD.setEnabled(true);
					if(permisos[i].equals("Configuración de Bono"))
						Configuracion_Bono.setEnabled(true);
					if(permisos[i].equals("Configuración de Denominaciones"))
						Configuracion_Denominaciones.setEnabled(true);
					if(permisos[i].equals("Configuración de Divisas y Tipo de Cambio"))
						Configuracion_Divisas.setEnabled(true);
					if(permisos[i].equals("Configuración Mantenimiento Base de Datos"))
						Configuracion_Mantenimiento.setEnabled(true);
					if(permisos[i].equals("Configuración de Sistema"))
						Configuracion_Sistema.setEnabled(true);
					if(permisos[i].equals("Configuración de Usuarios"))
						Configuracion_Usuario.setEnabled(true);
					
					/* CONTABILIDAD */
					if(permisos[i].equals("Importar Auxiliar"))
						Importar_Auxiliar.setEnabled(true);
					if(permisos[i].equals("Importar Cheques"))
						Importar_Cheques.setEnabled(true);
					if(permisos[i].equals("Importar Conciliación AuxF"))
						Importar_Consiliacion.setEnabled(true);
					if(permisos[i].equals("Importar Voucher"))
						Importar_Voucher.setEnabled(true);
					if(permisos[i].equals("Reporte de Apartados y Abonos en una Asignacion"))
						Egresos_Reporte_de_apartados_y_abonos.setEnabled(true);
					
					/*CORTES*/
					if(permisos[i].equals("Captura de Cortes de Cajeras"))
						Captura_Cortes .setEnabled(true);
					
					/* CUADRANTES 
					*		ALIMENTACION */
				    if(permisos[i].equals("Impresion de Cuadrante Personal"));
					 Impresion_Cuadrante_Personal.setEnabled(true);
					if(permisos[i].equals("Alimentación de Cuadrantes"))
						Cuadrantes_Alimentacion_Actividades_Cuadrantes.setEnabled(true);
					if(permisos[i].equals("Cuadrante"))
						Cuadrantes_Alimentacion_Cuadrante.setEnabled(true);
					if(permisos[i].equals("Empleados en Cuadrantes"))
						Cuadrantes_Alimentacion_Empleados_Cuadrantes.setEnabled(true);	
					/* CUADRANTES
					 * 		CATALOGO */
					if(permisos[i].equals("Actividades"))
						Cuadrantes_Catalogo_Actividades.setEnabled(true);
					if(permisos[i].equals("Asignación de Telefonos"))
						Cuadrantes_Catalogo_Telefono.setEnabled(true);
					if(permisos[i].equals("Atributos"))
						Cuadrantes_Catalogo_Atributos.setEnabled(true);
					if(permisos[i].equals("Equipo de Trabajo"))
						Cuadrantes_Catalogo_Equipo_Trabajo.setEnabled(true);
					if(permisos[i].equals("Jefatura"))
						Cuadrantes_Catalogo_Jefatura.setEnabled(true);
					if(permisos[i].equals("Nivel Crítico"))
						Cuadrantes_Catalogo_Nivel_Critico.setEnabled(true);
					if(permisos[i].equals("Nivel Jerarquico"))
						Cuadrantes_Catalogo_Nivel_Jerarquico.setEnabled(true);
					if(permisos[i].equals("Opciones de Respuesta"))
						Cuadrantes_Catalogo_Respuesta.setEnabled(true);
					if(permisos[i].equals("Opciones Múltiple de Respuesta"))
						Cuadrantes_Catalogo_Respuesta_Multiple.setEnabled(true);
					if(permisos[i].equals("Ponderacion"))
						Cuadrantes_Catalogo_Ponderacion.setEnabled(true);
					/* CUADRANTES
					*		REPORTE */
					if(permisos[i].equals("Reportes Directivo"))
						Cuadrantes_Reportes_Directivo.setEnabled(true);
					 if(permisos[i].equals("Reporte Dinamico de Cuadrantes"))
						 Cuadrantes_Reportes_Dinamico.setEnabled(true);
					if(permisos[i].equals("Reportes Usuario"))
						Cuadrantes_Reportes_Usuario.setEnabled(true);
									
					/* LISTA DE RAYA 
					*		ALIMENTACION */
					if(permisos[i].equals("Alimentación Bancos")){
						Alimentacion_Bancos.setEnabled(true);
						btnBanco.setEnabled(true);
					}
					if(permisos[i].equals("Alimentación de Totales de Nómina"))
						Alimentacion_Captura_Totales_Nomina.setEnabled(true);
					if(permisos[i].equals("Alimentación Deducción por Inasistencia")){
						Alimentacion_Deducciones_Asistencia.setEnabled(true);
						btnInasistencia.setEnabled(true);
					}
					if(permisos[i].equals("Alimentación Diferencia de Cortes")){
						Alimentacion_Diferencia_Cortes.setEnabled(true);
						btnCaja.setEnabled(true);
					}
					if(permisos[i].equals("Alimentación Fuente de Sodas AUXF")){
						Alimentacion_Fuente_Sodas_auxf.setEnabled(true);
						btnFsAux.setEnabled(true);
					}
					if(permisos[i].equals("Alimentación Fuente de Sodas DH")){
						Alimentacion_Fuente_Sodas_rh.setEnabled(true);
						btnFsRH.setEnabled(true);
					}
					if(permisos[i].equals("Alimentación Percepciones Extras")){
						Alimentacion_Percepciones_Extra.setEnabled(true);
						btnPExtras.setEnabled(true);
					}
					if(permisos[i].equals("Alimentación Prestamos")){
						Alimentacion_Prestamos.setEnabled(true);
						btnPrestamo.setEnabled(true);
					}
					/* LISTA DE RAYA 	
					*		AUTORIZACIONES */
					if(permisos[i].equals("Autorizacion Auditoria"))
						Autorizacion_Auditoria.setEnabled(true);
					if(permisos[i].equals("Autorizacion Finanzas"))
						Autorizacion_Finanzas.setEnabled(true);
					/* LISTA DE RAYA 	
					*		COMPARACIONES */
					if(permisos[i].equals("Lista de Comparación FS.")){
						Comparaciones_Listas_Fuente_Sodas.setEnabled(true);
						btnListaComparacion.setEnabled(true);
					}
					if(permisos[i].equals("Lista de Raya")){
						Comparaciones_Listas_Raya.setEnabled(true);
						btnListaRaya.setEnabled(true);
					}
					/* LISTA DE RAYA 	
					*		CHECADOR */
					if(permisos[i].equals("Asignacion de Horario de Temporada")){
						Asignacion_Horario_Temporada.setEnabled(true);
					   }
					if(permisos[i].equals("Checador")){
						Checador_Menu.setEnabled(true);
					   }
					if(permisos[i].equals("Dias Inhabiles")){
						Dias_Inhabiles.setEnabled(true);
					}
					if(permisos[i].equals("Generacion de Gafetes de Empleados")){
						Generacion_Gafetes_Empleados.setEnabled(true);
								}
					if(permisos[i].equals("Horarios")){
						Horarios.setEnabled(true);
								}
					if(permisos[i].equals("Mensajes Personales a Empleados")){
						Mensajes_Personales.setEnabled(true);
								}
					if(permisos[i].equals("Permisos a Empleados")){
						Permisos_Empleados.setEnabled(true);
					            }
					if(permisos[i].equals("Reporte General de Asistencia")){
						Reportes_Checador_Gral.setEnabled(true);
				              	}  
					if(permisos[i].equals("Reporte General de Asistencia")){
						Reportes_Checador_Gral.setEnabled(true);
								}
					/* LISTA DE RAYA 
					*		DEPARTAMENTO DE CORTES */
					if(permisos[i].equals("Alimentación de Cortes"))
						Departamento_Cortes_Alimentacion.setEnabled(true);
					/* LISTA DE RAYA 
					*		REPORTES */
					if(permisos[i].equals("Reporte Deducciones Por Inasistencia"))
						Reporte_Deducciones_Inasistencia.setEnabled(true);
					if(permisos[i].equals("Reporte Depositos A Bancos"))
						Reporte_Bancos.setEnabled(true);
					if(permisos[i].equals("Reporte Fuente Sodas"))
						Reporte_Fuente_Sodas.setEnabled(true);
					if(permisos[i].equals("Reporte Lista de Firmas")){
						Reporte_Lista_Firma.setEnabled(true);
						btnListaFirma.setEnabled(true);
					}
					if(permisos[i].equals("Reporte Lista de Raya"))
						Reporte_Lista_Raya.setEnabled(true);
					if(permisos[i].equals("Reporte Plantilla Activa"))
						Reporte_Plantilla_Activa.setEnabled(true);
					if(permisos[i].equals("Reporte Prestamos"))
						Reporte_Prestamos.setEnabled(true);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			String ruta = System.getProperty("user.dir")+"\\Config\\config";
			File archivo = new File(ruta);
			
			if(archivo.exists()){
				boolean ejecutar = new Connexion().init();
				if(ejecutar == true){
					new Main().setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Error en el programa!","Error",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}else{
				JOptionPane.showMessageDialog(null, "Configure las variables de la conexion a la Base de Datos","Error",JOptionPane.WARNING_MESSAGE);
				new Cat_Conexion_BD().setVisible(true);
			}

		}catch(Exception e){
			e.printStackTrace();
		}	
	}

}
