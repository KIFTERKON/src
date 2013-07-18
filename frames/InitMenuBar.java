package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import reporte.Reporte_Fuente_de_Sodas_Desarrollo_Humano;

import catalogos.Cat_Actividad;
import catalogos.Cat_Alimentacion_Cuadrante;
import catalogos.Cat_Alimentacion_Totales;
import catalogos.Cat_Asistencia_Puntualidad;
import catalogos.Cat_Atributos;
import catalogos.Cat_Auto_Auditoria;
import catalogos.Cat_Auto_Finanzas;
import catalogos.Cat_Bancos;
import catalogos.Cat_Bono_Complemento_Sueldo;
import catalogos.Cat_Comprobar_Fuente_Sodas_RH;
import catalogos.Cat_Conexion_BD;
import catalogos.Cat_Configuracion_Sistema;
import catalogos.Cat_Cuadrante;
import catalogos.Cat_Deduccion_Inasistencia;
import catalogos.Cat_Empleado;
import catalogos.Cat_Empleados_Cuadrantes;
import catalogos.Cat_Equipo_Trabajo;
import catalogos.Cat_Establecimiento;
import catalogos.Cat_Filtro_Cortes;
import catalogos.Cat_Filtro_Diferiencia_Cortes;
import catalogos.Cat_Filtro_Empleado_Directorio;
import catalogos.Cat_Filtro_Fue_Soda_Auxf;
import catalogos.Cat_Filtro_Fue_Soda_Rh;
import catalogos.Cat_Filtro_Prestamo;
import catalogos.Cat_Imprimir_LR;
import catalogos.Cat_Jefatura;
import catalogos.Cat_Lista_Pago;
import catalogos.Cat_Nivel_Critico;
import catalogos.Cat_Opciones_Respuesta;
import catalogos.Cat_Percepciones_Extra;
import catalogos.Cat_Ponderacion;
import catalogos.Cat_Puesto;
import catalogos.Cat_Rango_Prestamos;
import catalogos.Cat_Revision_Lista_Raya;
import catalogos.Cat_Sueldo;
import catalogos.Cat_Tipo_Banco;
import catalogos.Cat_Turno;
import catalogos.Cat_Usuario;

@SuppressWarnings("serial")
public class InitMenuBar extends Init{
	
	/* PANEL DEL MENU */
	JTabbedPane tabbedPane = new JTabbedPane();
	
	/* ARCHIVO */
	JMenu Archivo = new JMenu("Archivo");
	JMenuItem Cerrar = new JMenuItem("Cerrar", new ImageIcon("foto/Salir.png"));
	
	/* CATALOGO */
	JMenu Catalogo = new JMenu("Catalogo");
		JMenuItem Catalogo_Empleado	       = new JMenuItem("Nuevo Empleado");
		JMenuItem Catalogo_Establecimiento = new JMenuItem("Nuevo Establecimiento");
		JMenuItem Catalogo_Puesto 		   = new JMenuItem("Nuevo Puesto");
		JMenuItem Catalogo_Rango_Prestamo  = new JMenuItem("Nuevo Rango de Prestamo");
		JMenuItem Catalogo_Sueldo 		   = new JMenuItem("Nuevo Sueldo");
		JMenuItem Catalogo_Tipo_Banco	   = new JMenuItem("Nuevo Tipos de Bancos");
		JMenuItem Catalogo_Turno 		   = new JMenuItem("Nuevo Turno");
	
	/* CONFIGURACION */
	JMenu Configuracion = new JMenu("Configuración");
		JMenuItem Configuracion_Asistencia_Puntualidad = new JMenuItem("Configuración de Asistencia y Puntualidad");
		JMenuItem Configuracion_ConexionBD 			   = new JMenuItem("Configuración de Base de Datos");
		JMenuItem Configuracion_Bono				   = new JMenuItem("Configuración de Bono" );
		JMenuItem Configuracion_Denominaciones		   = new JMenuItem("Configuración de Denominaciones");
		JMenuItem Configuracion_Divisas 			   = new JMenuItem("Configuración de Divisas y Tipo de Cambio");
		JMenuItem Configuracion_Sistema 			   = new JMenuItem("Configuración de Sistema");
		JMenuItem Configuracion_Usuario 			   = new JMenuItem("Configuración de Usuarios");
		
	/* CONTABILIDAD */
	JMenu Contabilidad = new JMenu("Contabilidad");
		JMenu Contabilidad_Consiliacion = new JMenu("Conciliación");
			JMenu Contabilidad_Consiliacion_Auxiliar_Finanzas = new JMenu("Auxiliar de Finanzas");
				JMenuItem Importar_Auxiliar 			  = new JMenuItem("Importar Auxiliar");
				JMenuItem Importar_Cheques 				  = new JMenuItem("Importar Cheques");
				JMenuItem Importar_Consiliacion 		  = new JMenuItem("Importar Conciliación AuxF");
				JMenuItem Importar_Voucher				  = new JMenuItem("Importar Voucher");
				
	/* CUADRANTES */
	JMenu Cuadrantes = new JMenu("Cuadrantes");
		/* ALIMENTACION */
		JMenu Cuadrantes_Alimentacion = new JMenu("Alimentación");
			JMenuItem Cuadrantes_Alimentacion_Actividades 			 = new JMenuItem("Actividades");
			JMenuItem Cuadrantes_Alimentacion_Actividades_Cuadrantes = new JMenuItem("Actividades en Cuadrantes");
			JMenuItem Cuadrantes_Alimentacion_Telefono   			 = new JMenuItem("Asignación de Telefonos");
			JMenuItem Cuadrantes_Alimentacion_Cuadrante 			 = new JMenuItem("Cuadrante");
			JMenuItem Cuadrantes_Alimentacion_Empleados_Cuadrantes   = new JMenuItem("Empleados en Cuadrantes");
			
			
		/* CATALOGO */
		JMenu Cuadrantes_Catalogo = new JMenu("Catalogo");
			JMenuItem Cuadrantes_Catalogo_Atributos 		= new JMenuItem("Atributos");
			JMenuItem Cuadrantes_Catalogo_Equipo_Trabajo 	= new JMenuItem("Equipo de Trabajo");
			JMenuItem Cuadrantes_Catalogo_Jefatura 			= new JMenuItem("Jefatura");
			JMenuItem Cuadrantes_Catalogo_Nivel_Critico 	= new JMenuItem("Nivel Crítico");
			JMenuItem Cuadrantes_Catalogo_Respuesta 		= new JMenuItem("Opciones de Respuesta");
			JMenuItem Cuadrantes_Catalogo_Ponderacion 		= new JMenuItem("Ponderacion");
		/* REPORTE */
		JMenu Cuadrantes_Reportes = new JMenu("Reporte");
			JMenuItem Cuadrantes_Reportes_Directivo   = new JMenuItem("Reportes Directivo");
			JMenuItem Cuadrantes_Reportes_Jefatura    = new JMenuItem("Reportes Jefatura");
			JMenuItem Cuadrantes_Reportes_Usuario 	  = new JMenuItem("Reportes Usuario");
					
	/* LISTA DE RAYA */
	JMenu Lista_Raya = new JMenu("Lista de Raya");
		/* ALIMENTACION */
		JMenu Alimentacion = new JMenu("Alimentación");
			JMenuItem Alimentacion_Bancos 				  = new JMenuItem("Alimentación Bancos", new ImageIcon("Iconos/money_icon&16.png"));
			JMenuItem Alimentacion_Captura_Totales_Nomina = new JMenuItem("Alimentación de Totales de Nómina", new ImageIcon("Iconos/captura_nomina_icon&16.png"));
			JMenuItem Alimentacion_Deducciones_Asistencia = new JMenuItem("Alimentación Deducción por Inasistencia", new ImageIcon("Iconos/hand_contra_icon&16.png"));
			JMenuItem Alimentacion_Diferencia_Cortes 	  = new JMenuItem("Alimentación Diferencia de Cortes");
			JMenuItem Alimentacion_Fuente_Sodas_auxf 	  = new JMenuItem("Alimentación Fuente de Sodas AUXF");
			JMenuItem Alimentacion_Fuente_Sodas_rh 		  = new JMenuItem("Alimentación Fuente de Sodas DH");
			JMenuItem Alimentacion_Percepciones_Extra 	  = new JMenuItem("Alimentación Percepciones Extras", new ImageIcon("Iconos/hand_pro_icon&16.png"));
			JMenuItem Alimentacion_Prestamos 			  = new JMenuItem("Alimentación Prestamos");
		/* AUTORIZACIONES */
		JMenu Autorizaciones = new JMenu("Autorizaciones");
			JMenuItem Autorizacion_Auditoria = new JMenuItem("Autorizacion Auditoria");
			JMenuItem Autorizacion_Finanzas  = new JMenuItem("Autorizacion Finanzas");
		/* COMPARACIONES */
		JMenu Comparaciones = new JMenu("Comparaciones");
			JMenuItem Comparaciones_Listas_Fuente_Sodas = new JMenuItem("Lista de Comparación FS.");
			JMenuItem Comparaciones_Listas_Raya = new JMenuItem("Lista de Raya", new ImageIcon("Iconos/list_bullets_icon&16.png"));
		/* DEPARTAMENTO DE CORTES */
		JMenu Departamento_Cortes = new JMenu("Departamento de Cortes");
			JMenuItem Departamento_Cortes_Alimentacion = new JMenuItem("Alimentación de Cortes");
		/* REPORTES */
		JMenu Reportes = new JMenu("Reportes");
	     	JMenuItem Reporte_Deducciones_Inasistencia = new JMenuItem("Reporte Deducciones Por Inasistencia");
	     	JMenuItem Reporte_Bancos = new JMenuItem("Reporte Depositos A Bancos");
	     	JMenuItem Reporte_Fuente_Sodas     = new JMenuItem("Reporte Fuente Sodas");
			JMenuItem Reporte_Lista_Firma 	   = new JMenuItem("Reporte Lista de Firmas");
			JMenuItem Reporte_Lista_Raya       = new JMenuItem("Reporte Lista de Raya");
			JMenuItem Reporte_Plantilla_Activa = new JMenuItem("Reporte Plantilla Activa");
			JMenuItem Reporte_Prestamos = new JMenuItem("Reporte Prestamos");
	
	/* AYUDA */
	JMenu Ayuda = new JMenu("Ayuda");
		JMenuItem Ayuda_Acerca_de = new JMenuItem("Acerca de");		
		
	public InitMenuBar(){
		this.setJMenuBar(miMenuTop());
	}
	
	public JMenuBar miMenuTop(){
		JMenuBar Barra = new JMenuBar();

		/* ARCHIVO */
		Archivo.setMnemonic(KeyEvent.VK_I);
		Archivo.add(Cerrar);
			Cerrar.addActionListener(Opciones);

		/* CATALOGO */
		Catalogo.setMnemonic(KeyEvent.VK_C);
		Catalogo.add(Catalogo_Empleado);
			Catalogo_Empleado.addActionListener(Opciones);
			Catalogo_Empleado.setEnabled(false);
		Catalogo.add(Catalogo_Establecimiento);
			Catalogo_Establecimiento.addActionListener(Opciones);
			Catalogo_Establecimiento.setEnabled(false);
		Catalogo.add(Catalogo_Puesto);
			Catalogo_Puesto.addActionListener(Opciones);
			Catalogo_Puesto.setEnabled(false);
		Catalogo.add(Catalogo_Rango_Prestamo);
			Catalogo_Rango_Prestamo.addActionListener(Opciones);
			Catalogo_Rango_Prestamo.setEnabled(false);
		Catalogo.add(Catalogo_Sueldo);
			Catalogo_Sueldo.addActionListener(Opciones);
			Catalogo_Sueldo.setEnabled(false);
		Catalogo.add(Catalogo_Tipo_Banco);
			Catalogo_Tipo_Banco.addActionListener(Opciones);
			Catalogo_Tipo_Banco.setEnabled(false);
		Catalogo.add(Catalogo_Turno);
			Catalogo_Turno.addActionListener(Opciones);
			Catalogo_Turno.setEnabled(false);
			
		/* CONFIGURACION */
		Configuracion.add(Configuracion_Asistencia_Puntualidad);
			Configuracion_Asistencia_Puntualidad.addActionListener(Opciones);
			Configuracion_Asistencia_Puntualidad.setEnabled(false);
		Configuracion.add(Configuracion_ConexionBD);
			Configuracion_ConexionBD.addActionListener(Opciones);
			Configuracion_ConexionBD.setEnabled(false);
		Configuracion.add(Configuracion_Bono);
			Configuracion_Bono.addActionListener(Opciones);
			Configuracion_Bono.setEnabled(false);
		Configuracion.add(Configuracion_Denominaciones);
			Configuracion_Denominaciones.addActionListener(Opciones);
			Configuracion_Denominaciones.setEnabled(false);
		Configuracion.add(Configuracion_Divisas);
			Configuracion_Divisas.addActionListener(Opciones);
			Configuracion_Divisas.setEnabled(false);
		Configuracion.add(Configuracion_Sistema);
			Configuracion_Sistema.addActionListener(Opciones);
			Configuracion_Sistema.setEnabled(false);
		Configuracion.add(Configuracion_Usuario);
			Configuracion_Usuario.addActionListener(Opciones);
			Configuracion_Usuario.setEnabled(false);
	
		/* CONTABILIDAD */			
		Contabilidad.add(Contabilidad_Consiliacion);
			Contabilidad_Consiliacion.add(Contabilidad_Consiliacion_Auxiliar_Finanzas);
				Contabilidad_Consiliacion_Auxiliar_Finanzas.add(Importar_Auxiliar);
					Importar_Auxiliar.addActionListener(Opciones);
					Importar_Auxiliar.setEnabled(false);
				Contabilidad_Consiliacion_Auxiliar_Finanzas.add(Importar_Cheques);
					Importar_Cheques.addActionListener(Opciones);
					Importar_Cheques.setEnabled(false);
				Contabilidad_Consiliacion_Auxiliar_Finanzas.add(Importar_Consiliacion);
					Importar_Consiliacion.addActionListener(Opciones);
					Importar_Consiliacion.setEnabled(false);
				Contabilidad_Consiliacion_Auxiliar_Finanzas.add(Importar_Voucher);
					Importar_Voucher.addActionListener(Opciones);
					Importar_Voucher.setEnabled(false);
					
		/* CUADRANTES 
		*		ALIMENTACION */
		Cuadrantes.add(Cuadrantes_Alimentacion);
			Cuadrantes_Alimentacion.add(Cuadrantes_Alimentacion_Actividades);
				Cuadrantes_Alimentacion_Actividades.addActionListener(Opciones);
				Cuadrantes_Alimentacion_Actividades.setEnabled(false);
			Cuadrantes_Alimentacion.add(Cuadrantes_Alimentacion_Actividades_Cuadrantes);
				Cuadrantes_Alimentacion_Actividades_Cuadrantes.addActionListener(Opciones);
				Cuadrantes_Alimentacion_Actividades_Cuadrantes.setEnabled(false);
			Cuadrantes_Alimentacion.add(Cuadrantes_Alimentacion_Cuadrante);
				Cuadrantes_Alimentacion_Cuadrante.addActionListener(Opciones);
				Cuadrantes_Alimentacion_Cuadrante.setEnabled(false);
			Cuadrantes_Alimentacion.add(Cuadrantes_Alimentacion_Empleados_Cuadrantes);
				Cuadrantes_Alimentacion_Empleados_Cuadrantes.addActionListener(Opciones);
				Cuadrantes_Alimentacion_Empleados_Cuadrantes.setEnabled(false);
				
			Cuadrantes_Alimentacion.add(Cuadrantes_Alimentacion_Telefono);
				Cuadrantes_Alimentacion_Telefono.addActionListener(Opciones);
				Cuadrantes_Alimentacion_Telefono.setEnabled(false);
				
		/* CUADRANTES 
		*		CATALOGO */
		Cuadrantes.add(Cuadrantes_Catalogo);
			Cuadrantes_Catalogo.add(Cuadrantes_Catalogo_Atributos);
				Cuadrantes_Catalogo_Atributos.addActionListener(Opciones);
				Cuadrantes_Catalogo_Atributos.setEnabled(false);
			Cuadrantes_Catalogo.add(Cuadrantes_Catalogo_Equipo_Trabajo);
				Cuadrantes_Catalogo_Equipo_Trabajo.addActionListener(Opciones);
				Cuadrantes_Catalogo_Equipo_Trabajo.setEnabled(false);
			Cuadrantes_Catalogo.add(Cuadrantes_Catalogo_Jefatura);
				Cuadrantes_Catalogo_Jefatura.addActionListener(Opciones);
				Cuadrantes_Catalogo_Jefatura.setEnabled(false);
			Cuadrantes_Catalogo.add(Cuadrantes_Catalogo_Nivel_Critico);
				Cuadrantes_Catalogo_Nivel_Critico.addActionListener(Opciones);
				Cuadrantes_Catalogo_Nivel_Critico.setEnabled(false);
			Cuadrantes_Catalogo.add(Cuadrantes_Catalogo_Respuesta);
				Cuadrantes_Catalogo_Respuesta.addActionListener(Opciones);
				Cuadrantes_Catalogo_Respuesta.setEnabled(false);
			Cuadrantes_Catalogo.add(Cuadrantes_Catalogo_Ponderacion);
				Cuadrantes_Catalogo_Ponderacion.addActionListener(Opciones);
				Cuadrantes_Catalogo_Ponderacion.setEnabled(false);
		Cuadrantes.add(Cuadrantes_Reportes);
		/* CUADRANTES 
		*		REPORTE */
		Cuadrantes.add(Cuadrantes_Reportes);
			Cuadrantes_Reportes.add(Cuadrantes_Reportes_Directivo);
				Cuadrantes_Reportes_Directivo.addActionListener(Opciones);
				Cuadrantes_Reportes_Directivo.setEnabled(false);
			Cuadrantes_Reportes.add(Cuadrantes_Reportes_Jefatura);
				Cuadrantes_Reportes_Jefatura.addActionListener(Opciones);
				Cuadrantes_Reportes_Jefatura.setEnabled(false);
			Cuadrantes_Reportes.add(Cuadrantes_Reportes_Usuario);
				Cuadrantes_Reportes_Usuario.addActionListener(Opciones);
				Cuadrantes_Reportes_Usuario.setEnabled(false);
		/* LISTA DE RAYA 
		* 		ALIMENTACION */
		Lista_Raya.add(Alimentacion);
			Alimentacion.add(Alimentacion_Bancos);
				Alimentacion_Bancos.addActionListener(Opciones);
				Alimentacion_Bancos.setEnabled(false);
			Alimentacion.add(Alimentacion_Captura_Totales_Nomina);
				Alimentacion_Captura_Totales_Nomina.addActionListener(Opciones);
				Alimentacion_Captura_Totales_Nomina.setEnabled(false);
			Alimentacion.add(Alimentacion_Deducciones_Asistencia);
				Alimentacion_Deducciones_Asistencia.addActionListener(Opciones);
				Alimentacion_Deducciones_Asistencia.setEnabled(false);
			Alimentacion.add(Alimentacion_Diferencia_Cortes);
				Alimentacion_Diferencia_Cortes.addActionListener(Opciones);
				Alimentacion_Diferencia_Cortes.setEnabled(false);
			Alimentacion.add(Alimentacion_Fuente_Sodas_auxf);
				Alimentacion_Fuente_Sodas_auxf.addActionListener(Opciones);
				Alimentacion_Fuente_Sodas_auxf.setEnabled(false);
			Alimentacion.add(Alimentacion_Fuente_Sodas_rh);
				Alimentacion_Fuente_Sodas_rh.addActionListener(Opciones);
				Alimentacion_Fuente_Sodas_rh.setEnabled(false);
			Alimentacion.add(Alimentacion_Percepciones_Extra);
				Alimentacion_Percepciones_Extra.addActionListener(Opciones);
				Alimentacion_Percepciones_Extra.setEnabled(false);
			Alimentacion.add(Alimentacion_Prestamos);
				Alimentacion_Prestamos.addActionListener(Opciones);
				Alimentacion_Prestamos.setEnabled(false);
		/* LISTA DE RAYA 
		* 		AUTORIZACIONES */
		Lista_Raya.add(Autorizaciones);
			Autorizaciones.add(Autorizacion_Auditoria);
				Autorizacion_Auditoria.addActionListener(Opciones);
				Autorizacion_Auditoria.setEnabled(false);
			Autorizaciones.add(Autorizacion_Finanzas);
				Autorizacion_Finanzas.addActionListener(Opciones);
				Autorizacion_Finanzas.setEnabled(false);
		/* LISTA DE RAYA 
		* 		COMPARACIONES */
		Lista_Raya.add(Comparaciones);
			Comparaciones.add(Comparaciones_Listas_Fuente_Sodas);
				Comparaciones_Listas_Fuente_Sodas.addActionListener(Opciones);
				Comparaciones_Listas_Fuente_Sodas.setEnabled(false);
			Comparaciones.add(Comparaciones_Listas_Raya);
				Comparaciones_Listas_Raya.addActionListener(Opciones);
				Comparaciones_Listas_Raya.setEnabled(false);
		/* LISTA DE RAYA 
		* 		DEPARTAMENTO DE CORTES */
		Lista_Raya.add(Departamento_Cortes);
			Departamento_Cortes.add(Departamento_Cortes_Alimentacion);
				Departamento_Cortes_Alimentacion.addActionListener(Opciones);
				Departamento_Cortes_Alimentacion.setEnabled(false);
		/* LISTA DE RAYA 
		* 		REPORTES */
		Lista_Raya.add(Reportes);
		    Reportes.add(Reporte_Deducciones_Inasistencia);
		    	Reporte_Deducciones_Inasistencia.addActionListener(Opciones);
		    	Reporte_Deducciones_Inasistencia.setEnabled(false);
		    Reportes.add(Reporte_Bancos);
		    	Reporte_Bancos.addActionListener(Opciones);
		    	Reporte_Bancos.setEnabled(false);
			Reportes.add(Reporte_Fuente_Sodas);
				Reporte_Fuente_Sodas.addActionListener(Opciones);
				Reporte_Fuente_Sodas.setEnabled(false);
			Reportes.add(Reporte_Lista_Firma);
				Reporte_Lista_Firma.addActionListener(Opciones);
				Reporte_Lista_Firma.setEnabled(false);
			Reportes.add(Reporte_Lista_Raya);
				Reporte_Lista_Raya.addActionListener(Opciones);
				Reporte_Lista_Raya.setEnabled(false);
			Reportes.add(Reporte_Plantilla_Activa);
				Reporte_Plantilla_Activa.addActionListener(Opciones);
				Reporte_Plantilla_Activa.setEnabled(false);
			Reportes.add(Reporte_Prestamos);
				Reporte_Prestamos.addActionListener(Opciones);
				Reporte_Prestamos.setEnabled(false);		
		/* AYUDA */
		Ayuda.setMnemonic(KeyEvent.VK_A);
			Ayuda.add(Ayuda_Acerca_de);
				
		Barra.add(Archivo);
		Barra.add(Catalogo);
		Barra.add(Configuracion);
		Barra.add(Contabilidad);
		Barra.add(Cuadrantes);
		Barra.add(Lista_Raya);
		Barra.add(Ayuda);
		
		return Barra;
	}
	
	ActionListener Opciones = new ActionListener(){
		public void actionPerformed(ActionEvent e){

			/* ARCHIVO */
			if(e.getActionCommand().equals("Cerrar"))
				dispose();
			
			/* CATALOGO */
			if(e.getActionCommand().equals("Nuevo Empleado"))
				new Cat_Empleado().setVisible(true);
			if(e.getActionCommand().equals("Nuevo Establecimiento"))
				new Cat_Establecimiento().setVisible(true);
			if(e.getActionCommand().equals("Nuevo Puesto"))
				new Cat_Puesto().setVisible(true);
			if(e.getActionCommand().equals("Nuevo Rango de Prestamo"))
				new Cat_Rango_Prestamos().setVisible(true);
			if(e.getActionCommand().equals("Nuevo Sueldo"))
				new Cat_Sueldo().setVisible(true);
			if(e.getActionCommand().equals("Nuevo Tipos de Bancos"))
				new Cat_Tipo_Banco().setVisible(true);
			if(e.getActionCommand().equals("Nuevo Turno"))
				new Cat_Turno().setVisible(true);
			
			/* CONFIGURACION */
			if(e.getActionCommand().equals("Configuración de Asistencia y Puntualidad"))
				new Cat_Asistencia_Puntualidad().setVisible(true);
			if(e.getActionCommand().equals("Configuración de Base de Datos"))
				new Cat_Conexion_BD().setVisible(true);
			if(e.getActionCommand().equals("Configuración de Bono"))
				new Cat_Bono_Complemento_Sueldo().setVisible(true);
			if(e.getActionCommand().equals("Configuración de Denominaciones"))
				System.out.println("Pendiente");
			if(e.getActionCommand().equals("Configuración de Divisas y Tipo de Cambio"))
				System.out.println("Pendiente");
			if(e.getActionCommand().equals("Configuración de Sistema"))
				new Cat_Configuracion_Sistema().setVisible(true);
			if(e.getActionCommand().equals("Configuración de Usuarios"))
				new Cat_Usuario().setVisible(true);
			
			/* CONTABILIDAD */
			if(e.getActionCommand().equals("Importar Auxiliar"))
				System.out.println("Pendiente");
			if(e.getActionCommand().equals("Importar Cheques"))	
				System.out.println("Pendiente");
			if(e.getActionCommand().equals("Importar Conciliación AuxF"))
				System.out.println("Pendiente");
			if(e.getActionCommand().equals("Importar Voucher"))
				System.out.println("Pendiente");
			
			/* CUADRANTES 
			 * 		ALIMENTACION */
			if(e.getActionCommand().equals("Actividades"))
				new Cat_Actividad().setVisible(true);
			if(e.getActionCommand().equals("Actividades en Cuadrantes"))
				new Cat_Alimentacion_Cuadrante().setVisible(true);
			if(e.getActionCommand().equals("Asignación de Telefonos"))
				new Cat_Filtro_Empleado_Directorio().setVisible(true);
			if(e.getActionCommand().equals("Cuadrante"))
				new Cat_Cuadrante().setVisible(true);
			if(e.getActionCommand().equals("Empleados en Cuadrantes"))
				new Cat_Empleados_Cuadrantes().setVisible(true);
			/* CUADRANTES 
			 * 		CATALOGO */
			if(e.getActionCommand().equals("Atributos"))
				new Cat_Atributos().setVisible(true);
			if(e.getActionCommand().equals("Equipo de Trabajo"))
				new Cat_Equipo_Trabajo().setVisible(true);
			if(e.getActionCommand().equals("Jefatura"))
				new Cat_Jefatura().setVisible(true);
			if(e.getActionCommand().equals("Nivel Crítico"))
				new Cat_Nivel_Critico().setVisible(true);
			if(e.getActionCommand().equals("Opciones de Respuesta"))
				new Cat_Opciones_Respuesta().setVisible(true);
			if(e.getActionCommand().equals("Ponderacion"))
				new Cat_Ponderacion().setVisible(true);
			/* CUADRANTES 
			 * 		CATALOGO */
			if(e.getActionCommand().equals("Reportes Directivo"))
				System.out.println("Pendiente");
			if(e.getActionCommand().equals("Reportes Jefatura"))
				System.out.println("Pendiente");
			if(e.getActionCommand().equals("Reportes Usuario"))
				System.out.println("Pendiente");
				
			/* LISTA DE RAYA 
			 * 		ALIMENTACION */
			if(e.getActionCommand().equals("Alimentación Bancos"))
				new Cat_Bancos().setVisible(true);
			if(e.getActionCommand().equals("Alimentación de Totales de Nómina"))
				new Cat_Alimentacion_Totales().setVisible(true);
			if(e.getActionCommand().equals("Alimentación Deducción por Inasistencia"))
				new Cat_Deduccion_Inasistencia().setVisible(true);
			if(e.getActionCommand().equals("Alimentación Diferencia de Cortes"))	
				new Cat_Filtro_Diferiencia_Cortes().setVisible(true);
			if(e.getActionCommand().equals("Alimentación Fuente de Sodas AUXF"))	
				new Cat_Filtro_Fue_Soda_Auxf().setVisible(true);
			if(e.getActionCommand().equals("Alimentación Fuente de Sodas DH"))	
				new Cat_Filtro_Fue_Soda_Rh().setVisible(true);
			if(e.getActionCommand().equals("Alimentación Percepciones Extras"))
				new Cat_Percepciones_Extra().setVisible(true);
			if(e.getActionCommand().equals("Alimentación Prestamos"))
				new Cat_Filtro_Prestamo().setVisible(true);
			/* LISTA DE RAYA 
			 * 		AUTORIZACIONES */
			if(e.getActionCommand().equalsIgnoreCase("Autorizacion Auditoria"))
				new Cat_Auto_Auditoria().setVisible(true);
			if(e.getActionCommand().equals("Autorizacion Finanzas"))
				new Cat_Auto_Finanzas().setVisible(true);
			/* LISTA DE RAYA 
			 * 		AUTORIZACIONES */
			if(e.getActionCommand().equals("Lista de Comparación FS."))
				new Cat_Comprobar_Fuente_Sodas_RH().setVisible(true);
			if(e.getActionCommand().equals("Lista de Raya"))
				new Cat_Revision_Lista_Raya().setVisible(true);
			/* LISTA DE RAYA 
			 * 		DEPARTAMENTO DE CORTES */
			if(e.getActionCommand().equalsIgnoreCase("Alimentación de Cortes"))
				new Cat_Filtro_Cortes().setVisible(true);
			/* LISTA DE RAYA 
			 * 		REPORTES */
			if(e.getActionCommand().equals("Reporte Depositos A Bancos"))
				new reporte.Reporte_Bancos();			
			if(e.getActionCommand().equals("Reporte Deducciones Por Inasistencia"))
				new reporte.Reporte_Deducciones_Inasistencia();
			if(e.getActionCommand().equals("Reporte Fuente Sodas"))
				new Reporte_Fuente_de_Sodas_Desarrollo_Humano();
			if(e.getActionCommand().equals("Reporte Lista de Firmas"))
				new Cat_Lista_Pago().setVisible(true);
			if(e.getActionCommand().equals("Reporte Lista de Raya"))
				new Cat_Imprimir_LR().setVisible(true);
			if(e.getActionCommand().equals("Reporte Plantilla Activa"))
				System.out.println("Pendiente");
			if(e.getActionCommand().equals("Reporte Prestamos"))
				new reporte.Reporte_Prestamos();	

			
		}
	};

}
