package frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import objetos.Obj_Usuario;

import catalogos.Cat_Asistencia_Puntualidad;
import catalogos.Cat_Bono_Complemento_Sueldo;
import catalogos.Cat_Comprobar_Fuente_Sodas_RH;
import catalogos.Cat_Deduccion_Asistencia;
import catalogos.Cat_Empleado;
import catalogos.Cat_Establecimiento;
import catalogos.Cat_Filtro_Fue_Soda_Auxf;
import catalogos.Cat_Filtro_Fue_Soda_Rh;
import catalogos.Cat_Filtro_Prestamo;
import catalogos.Cat_Puesto;
import catalogos.Cat_Rango_Prestamos;
import catalogos.Cat_Sueldo;
import catalogos.Cat_Usuario;

@SuppressWarnings("serial")
public class Principal extends JFrame{
	
	 /**     https://github.com/RguezMario/josedg.git      ***
	 ***	   Frame Principal							   ***
	 ***       autores: Jimenez Molina Edgar Eduardo       ***
	 ***			 Rodriguez Sanchez Jose Mario.         ***
	 ********************************************************/
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	JMenu Archivo = new JMenu("Archivo");
		JMenuItem Cerrar = new JMenuItem("Cerrar", new ImageIcon("foto/Salir.png"));
		
	JMenu Alimentacion = new JMenu("Alimentación");
		JMenuItem Alimentacion_Fuente_Sodas_rh = new JMenuItem("Fuente de Sodas RRHH");
		JMenuItem Alimentacion_Fuente_Sodas_auxf = new JMenuItem("Fuente de Sodas AUXF");
		JMenuItem Alimentacion_Deducciones_Asistencia = new JMenuItem("Deducción por Asistencia");
		JMenuItem Alimentacion_Bancos = new JMenuItem("Bancos");
		JMenuItem Alimentacion_Prestamos = new JMenuItem("Prestamos");
		
	JMenu Catalogo = new JMenu("Catalogo");
		JMenuItem Catalogo_Alta = new JMenuItem("Alta Empleado", new ImageIcon(""));
		JMenuItem Catalogo_Puesto = new JMenuItem("Puesto", new ImageIcon(""));
		JMenuItem Catalogo_Sueldo = new JMenuItem("Sueldo", new ImageIcon(""));
		JMenuItem Catalogo_Status = new JMenuItem("Status", new ImageIcon(""));
		
	JMenu Listas = new JMenu("Listas");	
		JMenuItem Listas_Raya = new JMenuItem("Lista de Raya");
		JMenuItem Listas_Comparacion_Fuente_Soda = new JMenuItem("Lista de Comparación FS.");
		
	JMenu Configuracion = new JMenu("Configuración");
		JMenuItem Configuracion_Asistencia_Puntualidad = new JMenuItem("Asistencia y Puntualidad", new ImageIcon(""));
		JMenuItem Configuracion_Bono = new JMenuItem("Bono", new ImageIcon(""));
		JMenuItem Configuracion_Establecimiento = new JMenuItem("Establecimiento", new ImageIcon("///"));
		JMenuItem Configuracion_Prestamo = new JMenuItem("Rango de Prestamo", new ImageIcon(""));
		JMenuItem Configuracion_Usuario = new JMenuItem("Usuario", new ImageIcon(""));
		
	JMenu Ayuda = new JMenu("Ayuda");
		JMenuItem Edicion_AcercaDe = new JMenuItem("Acerca de", new ImageIcon("foto/help.png"));
		JMenuItem Permiso_user = new JMenuItem("Permisos de Usuario", new ImageIcon("foto/help.png"));
		
	public Principal(){
		super("  Grupo Izagar  ");
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/izagar2.png"));
						
		this.setJMenuBar(miMenuTop());
		Container cont = getContentPane();
		
		tabbedPane.setBackground(Color.white);
		tabbedPane.addTab("Lista de Raya", new ImageIcon("imagen/Report.png"), new Frm_Principal2().getBase());
		cont.add(tabbedPane);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JMenuBar miMenuTop(){
		JMenuBar Barra = new JMenuBar();
		
		Archivo.setMnemonic(KeyEvent.VK_I);
		Archivo.add(Cerrar);
			Cerrar.addActionListener(Opciones);
			
		Alimentacion.add(Alimentacion_Bancos);
			Alimentacion_Bancos.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Deducciones_Asistencia);
			Alimentacion_Deducciones_Asistencia.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Fuente_Sodas_rh);
			Alimentacion_Fuente_Sodas_rh.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Fuente_Sodas_auxf);
			Alimentacion_Fuente_Sodas_auxf.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Prestamos);
			Alimentacion_Prestamos.addActionListener(Opciones);
			
		Catalogo.setMnemonic(KeyEvent.VK_C);
		Catalogo.add(Catalogo_Alta);
			Catalogo_Alta.addActionListener(Opciones);
		Catalogo.add(Catalogo_Puesto);
			Catalogo_Puesto.addActionListener(Opciones);
		Catalogo.add(Catalogo_Sueldo);
			Catalogo_Sueldo.addActionListener(Opciones);
			
		Listas.add(Listas_Raya);
			Listas_Raya.addActionListener(Opciones);
		Listas.add(Listas_Comparacion_Fuente_Soda);
			Listas_Comparacion_Fuente_Soda.addActionListener(Opciones);
			
		Configuracion.add(Configuracion_Asistencia_Puntualidad);
			Configuracion_Asistencia_Puntualidad.addActionListener(Opciones);
		Configuracion.add(Configuracion_Bono);
			Configuracion_Bono.addActionListener(Opciones);
		Configuracion.add(Configuracion_Establecimiento);
			Configuracion_Establecimiento.addActionListener(Opciones);
		Configuracion.add(Configuracion_Prestamo);
			Configuracion_Prestamo.addActionListener(Opciones);
		Configuracion.add(Configuracion_Usuario);
			Configuracion_Usuario.addActionListener(Opciones);
			
		Ayuda.setMnemonic(KeyEvent.VK_A);
		Ayuda.add(Edicion_AcercaDe);
		Ayuda.add(Permiso_user);
		
		Barra.add(Archivo);
		Barra.add(Alimentacion);
		Barra.add(Catalogo);
		Barra.add(Listas);
		Barra.add(Configuracion);
		Barra.add(Ayuda);
						
		return Barra;
	}
	
	ActionListener Opciones = new ActionListener(){
		public void actionPerformed(ActionEvent e){
					
			//Archivo
			if(e.getActionCommand().equals("Cerrar"))
				dispose();
			
			//Alimentación
			if(e.getActionCommand().equals("Bancos"))
				dispose();
			if(e.getActionCommand().equals("Deducción por Asistencia"))
				new Cat_Deduccion_Asistencia().setVisible(true);
			if(e.getActionCommand().equals("Fuente de Sodas RRHH"))
				new Cat_Filtro_Fue_Soda_Rh().setVisible(true);
			if(e.getActionCommand().equals("Fuente de Sodas AUXF"))
				new Cat_Filtro_Fue_Soda_Auxf().setVisible(true);
			if(e.getActionCommand().equals("Prestamos"))
				new Cat_Filtro_Prestamo().setVisible(true);

			// Catalogo
			if(e.getActionCommand().equals("Alta Empleado"))
				new Cat_Empleado().setVisible(true);
			if(e.getActionCommand().equals("Puesto"))
				new Cat_Puesto().setVisible(true);
			if(e.getActionCommand().equals("Sueldo"))
				new Cat_Sueldo().setVisible(true);
			
			// Listas
			if(e.getActionCommand().equals("Lista de Raya"))
				dispose();
			if(e.getActionCommand().equals("Lista de Comparación FS."))
				new Cat_Comprobar_Fuente_Sodas_RH().setVisible(true);
			
			// Configuración
			if(e.getActionCommand().equals("Asistencia y Puntualidad"))
				new Cat_Asistencia_Puntualidad().setVisible(true);
			if(e.getActionCommand().equals("Bono"))
				new Cat_Bono_Complemento_Sueldo().setVisible(true);
			if(e.getActionCommand().equals("Establecimiento"))
				new Cat_Establecimiento().setVisible(true);	
			if(e.getActionCommand().equals("Rango de Prestamo"))
				new Cat_Rango_Prestamos().setVisible(true);
			if(e.getActionCommand().equals("Usuario"))
				new Cat_Usuario().setVisible(true);
			
//			if(e.getActionCommand().equals("Permisos de Usuario"))
//				dispose();
			
			
		}
	};
	
	public static void main(String[] args) {
		try{
			// 6677914218
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			@SuppressWarnings("unused")
			Obj_Usuario usuario = new Obj_Usuario().buscarMaximo();
			
			new Principal().setVisible(true);
			
//			if(usuario.getFolio()  0){
//				new Cat_Usuario().setVisible(true);
//			}else{
			//	new CatPassword().setVisible(true);
//			}	
		}catch(Exception e){
			e.printStackTrace();
		}
			  		
	}

}
