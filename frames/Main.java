package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import SQL.Connexion;

import catalogos.Cat_Conexion_BD;

import objetos.Obj_MD5;
import objetos.Obj_Main;
import objetos.Obj_Usuario;

/*****************************************************
***												   *** 
***     https://github.com/RguezMario/src.git      ***
***	                     						   ***
***	                Colaboradores    			   ***       
***												   ***
***		   + Jimenez Molina Edgar Eduardo    	   ***
***		   + Rodriguez Sanchez Jose Mario          ***
***												   ***
*****************************************************/

@SuppressWarnings("serial")
public class Main extends InitButton {
	
	public Main(){
		btnAceptar.addActionListener(opIn);
	}
	
	ActionListener opIn = new ActionListener(){
		@SuppressWarnings({ "deprecation", "static-access" })
		public void actionPerformed(ActionEvent arg0) {
			if(txtContrasena.getText().length() != 0){
				Obj_MD5 algoritmo = new Obj_MD5();
				Obj_Usuario user = new Obj_Usuario().buscar(Integer.parseInt(txtFolio.getText()));

				if(!algoritmo.cryptMD5(txtContrasena.getText(), "izagar").trim().toLowerCase().equals(user.getContrasena().trim().toLowerCase())){
					JOptionPane.showMessageDialog(null, "La contrase�a no es v�lida...","Aviso",JOptionPane.WARNING_MESSAGE);
					txtContrasena.setText("");
					txtContrasena.requestFocus(true);
					return;
				}else{
					txtContrasena.setText("");
					txtContrasena.requestFocus(true);
					btnBuscar.setEnabled(false);
					btnAceptar.setEnabled(false);
					init_subMenus();
				}
			}else{
				JOptionPane.showMessageDialog(null, "La contrase�a est� vac�a...","Aviso",JOptionPane.WARNING_MESSAGE);
				txtContrasena.requestFocus(true);
				return;
			}
		}
		
	};
		
	public void init_subMenus(){
		try {
			Object[] permisos = new Obj_Main().Permisos(txtUsuario.getText());
			for(int i=0; i<permisos.length; i++){
				
				/* CATALOGO */
				if(permisos[i].equals("Nuevo Empleado"))
					Catalogo_Empleado.setEnabled(true);
					btnAltaEmp.setEnabled(true);
				if(permisos[i].equals("Nuevo Establecimiento"))
					Catalogo_Establecimiento.setEnabled(true);
				if(permisos[i].equals("Nuevo Puesto"))
					Catalogo_Puesto.setEnabled(true);
					btnPuesto.setEnabled(true);
				if(permisos[i].equals("Nuevo Rango de Prestamo"))
					Catalogo_Rango_Prestamo.setEnabled(true);
				if(permisos[i].equals("Nuevo Sueldo"))
					Catalogo_Sueldo.setEnabled(true);
					btnSueldo.setEnabled(true);
				if(permisos[i].equals("Nuevo Tipos de Bancos"))
					Catalogo_Tipo_Banco.setEnabled(true);
				if(permisos[i].equals("Nuevo Turno"))
					Catalogo_Turno.setEnabled(true);
				
				/* CONFIGURACION */
				if(permisos[i].equals("Configuraci�n de Asistencia y Puntualidad"))
					Configuracion_Asistencia_Puntualidad.setEnabled(true);
				if(permisos[i].equals("Configuraci�n de Base de Datos"))
					Configuracion_ConexionBD.setEnabled(true);
				if(permisos[i].equals("Configuraci�n de Bono"))
					Configuracion_Bono.setEnabled(true);
				if(permisos[i].equals("Configuraci�n de Denominaciones"))
					Configuracion_Denominaciones.setEnabled(true);
				if(permisos[i].equals("Configuraci�n de Divisas y Tipo de Cambio"))
					Configuracion_Divisas.setEnabled(true);
				if(permisos[i].equals("Configuraci�n de Sistema"))
					Configuracion_Sistema.setEnabled(true);
				if(permisos[i].equals("Configuraci�n de Usuarios"))
					Configuracion_Usuario.setEnabled(true);
				
				/* CONTABILIDAD */
				if(permisos[i].equals("Importar Auxiliar"))
					Importar_Auxiliar.setEnabled(true);
				if(permisos[i].equals("Importar Cheques"))
					Importar_Cheques.setEnabled(true);
				if(permisos[i].equals("Importar Conciliaci�n AuxF"))
					Importar_Consiliacion.setEnabled(true);
				if(permisos[i].equals("Importar Voucher"))
					Importar_Voucher.setEnabled(true);			
				
				/* CUADRANTES 
				*		ALIMENTACION */
				if(permisos[i].equals("Actividades"))
					Cuadrantes_Alimentacion_Actividades.setEnabled(true);
				if(permisos[i].equals("Actividades en Cuadrantes"))
					Cuadrantes_Alimentacion_Actividades_Cuadrantes.setEnabled(true);
				if(permisos[i].equals("Cuadrante"))
					Cuadrantes_Alimentacion_Cuadrante.setEnabled(true);
				if(permisos[i].equals("Empleados en Cuadrantes"))
					Cuadrantes_Alimentacion_Empleados_Cuadrantes.setEnabled(true);		
				/* CUADRANTES
				 * 		CATALOGO */
				if(permisos[i].equals("Atributos"))
					Cuadrantes_Catalogo_Atributos.setEnabled(true);
				if(permisos[i].equals("Equipo de Trabajo"))
					Cuadrantes_Catalogo_Equipo_Trabajo.setEnabled(true);
				if(permisos[i].equals("Jefatura"))
					Cuadrantes_Catalogo_Jefatura.setEnabled(true);
				if(permisos[i].equals("Nivel Cr�tico"))
					Cuadrantes_Catalogo_Nivel_Critico.setEnabled(true);
				if(permisos[i].equals("Opciones de Respuesta"))
					Cuadrantes_Catalogo_Respuesta.setEnabled(true);
				if(permisos[i].equals("Ponderacion"))
					Cuadrantes_Catalogo_Ponderacion.setEnabled(true);
				/* CUADRANTES
				*		REPORTE */
				if(permisos[i].equals("Reportes Directivo"))
					Cuadrantes_Reportes_Directivo.setEnabled(true);
				if(permisos[i].equals("Reportes Jefatura"))
					Cuadrantes_Reportes_Jefatura.setEnabled(true);
				if(permisos[i].equals("Reportes Usuario"))
					Cuadrantes_Reportes_Usuario.setEnabled(true);
								
				/* LISTA DE RAYA 
				*		ALIMENTACION */
				if(permisos[i].equals("Alimentaci�n Bancos"))
					Alimentacion_Bancos.setEnabled(true);
					btnBanco.setEnabled(true);
				if(permisos[i].equals("Alimentaci�n de Totales de N�mina"))
					Alimentacion_Captura_Totales_Nomina.setEnabled(true);
				if(permisos[i].equals("Alimentaci�n Deducci�n por Inasistencia"))
					Alimentacion_Deducciones_Asistencia.setEnabled(true);
					btnInasistencia.setEnabled(true);
				if(permisos[i].equals("Alimentaci�n Diferencia de Cortes"))
					Alimentacion_Diferencia_Cortes.setEnabled(true);
					btnCaja.setEnabled(true);
				if(permisos[i].equals("Alimentaci�n Fuente de Sodas AUXF"))
					Alimentacion_Fuente_Sodas_auxf.setEnabled(true);
					btnFsAux.setEnabled(true);
				if(permisos[i].equals("Alimentaci�n Fuente de Sodas DH"))
					Alimentacion_Fuente_Sodas_rh.setEnabled(true);
					btnFsRH.setEnabled(true);
				if(permisos[i].equals("Alimentaci�n Percepciones Extras"))
					Alimentacion_Percepciones_Extra.setEnabled(true);
					btnPExtras.setEnabled(true);
				if(permisos[i].equals("Alimentaci�n Prestamos"))
					Alimentacion_Prestamos.setEnabled(true);
					btnPrestamo.setEnabled(true);
				/* LISTA DE RAYA 	
				*		AUTORIZACIONES */
				if(permisos[i].equals("Autorizacion Auditoria"))
					Autorizacion_Auditoria.setEnabled(true);
				if(permisos[i].equals("Autorizacion Finanzas"))
					Autorizacion_Finanzas.setEnabled(true);
				/* LISTA DE RAYA 	
				*		COMPARACIONES */
				if(permisos[i].equals("Lista de Comparaci�n FS."))
					Comparaciones_Listas_Fuente_Sodas.setEnabled(true);
					btnListaComparacion.setEnabled(true);
				if(permisos[i].equals("Lista de Raya"))
					Comparaciones_Listas_Raya.setEnabled(true);
					btnListaRaya.setEnabled(true);
				/* LISTA DE RAYA 
				*		DEPARTAMENTO DE CORTES */
				if(permisos[i].equals("Alimentaci�n de Cortes"))
					Departamento_Cortes_Alimentacion.setEnabled(true);
				/* LISTA DE RAYA 
				*		REPORTES */
				if(permisos[i].equals("Reporte Fuente Sodas"))
					Reporte_Fuente_Sodas.setEnabled(true);
				if(permisos[i].equals("Reporte Lista de Firmas"))
					Reporte_Lista_Firma.setEnabled(true);
					btnListaFirma.setEnabled(true);
				if(permisos[i].equals("Reporte Plantilla Activa"))
					Reporte_Plantilla_Activa.setEnabled(true);
				
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