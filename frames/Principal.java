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

import catalogos.Cat_Bono;
import catalogos.Cat_Comprobar_Fuente_Sodas;
import catalogos.Cat_Empleado;
import catalogos.Cat_Establecimiento;
import catalogos.Cat_Filtro_Fue_Soda_Auxf;
import catalogos.Cat_Filtro_Fue_Soda_Rh;
import catalogos.Cat_Filtro_Prestamo;
import catalogos.Cat_Puesto;
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
	
	JMenu Catalogo = new JMenu("Catalogo");
	
		JMenuItem Catalogo_Alta = new JMenuItem("Alta Empleado", new ImageIcon("Imagen/Usuario.png"));
		JMenuItem Catalogo_Bono = new JMenuItem("Bono", new ImageIcon(""));
		JMenuItem Catalogo_Establecimiento = new JMenuItem("Establecimiento", new ImageIcon("///"));
		JMenuItem Catalogo_Puesto = new JMenuItem("Puesto", new ImageIcon(""));
		JMenuItem Catalogo_Sueldo = new JMenuItem("Sueldo", new ImageIcon(""));
		JMenuItem Catalogo_Status = new JMenuItem("Status", new ImageIcon(""));
		JMenuItem Catalogo_Usuario = new JMenuItem("Usuario", new ImageIcon(""));
		
		JMenuItem Archivo_Cerrar = new JMenuItem("Cerrar", new ImageIcon("foto/Salir.png"));
	JMenu Alimentacion = new JMenu("Alimentación");
		JMenuItem Fuente_Sodas_rh = new JMenuItem("Fuente de Sodas RRHH");
		JMenuItem Fuente_Sodas_auxf = new JMenuItem("Fuente de Sodas AUXF");
		JMenuItem Bancos = new JMenuItem("Bancos");
		JMenuItem Prestamos = new JMenuItem("Prestamos");
	JMenu Lista_Raya = new JMenu("Lista de Raya");	
		
		JMenu Ayuda = new JMenu("Ayuda");
		JMenuItem Edicion_AcercaDe = new JMenuItem("Acerca de", new ImageIcon("foto/help.png"));
		JMenuItem Permiso_user = new JMenuItem("Permisos de Usuario", new ImageIcon("foto/help.png"));
		
	public Principal(){
		super("..:: Grupo Izagar ::..");
		
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
		
			Catalogo.setMnemonic(KeyEvent.VK_C);
			
			Catalogo.add(Catalogo_Alta);
			
			Catalogo_Alta.addActionListener(Opciones);
			
			Catalogo.add(Catalogo_Bono);
			Catalogo_Bono.addActionListener(Opciones);
			
			Catalogo.add(Catalogo_Establecimiento);
			Catalogo_Establecimiento.addActionListener(Opciones);
			
			Catalogo.add(Catalogo_Puesto);
			Catalogo_Puesto.addActionListener(Opciones);
			
			Catalogo.add(Catalogo_Sueldo);
			Catalogo_Sueldo.addActionListener(Opciones);
			
			Catalogo.add(Catalogo_Status);
			Catalogo_Status.addActionListener(Opciones);
			
			Catalogo.add(Catalogo_Usuario);
			Catalogo_Usuario.addActionListener(Opciones);
			
		Catalogo.add(Archivo_Cerrar);
		Archivo_Cerrar.addActionListener(Opciones);
		
			Alimentacion.add(Bancos);
			Bancos.addActionListener(Opciones);
			
			Alimentacion.add(Fuente_Sodas_rh);
			Fuente_Sodas_rh.addActionListener(Opciones);
			
			Alimentacion.add(Fuente_Sodas_auxf);
			Fuente_Sodas_auxf.addActionListener(Opciones);
						
			Alimentacion.add(Prestamos);
			Prestamos.addActionListener(Opciones);
		
		Ayuda.add(Edicion_AcercaDe);
		Ayuda.add(Permiso_user);
		
		Ayuda.setMnemonic(KeyEvent.VK_A);
		
		Barra.add(Alimentacion);
		Barra.add(Catalogo);
		Barra.add(Lista_Raya);
		Barra.add(Ayuda);
						
		return Barra;
	}
	
	ActionListener Opciones = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(e.getActionCommand().equals("Alta Empleado"))
				new Cat_Empleado().setVisible(true);
			
			if(e.getActionCommand().equals("Establecimiento"))
				new Cat_Establecimiento().setVisible(true);
			
//PENDIENTES-----------------------------------------------------------------------------------		
			if(e.getActionCommand().equals("Fuente de Sodas RRHH"))
				new Cat_Filtro_Fue_Soda_Rh().setVisible(true);
			
			if(e.getActionCommand().equals("Comprobar Lista de Sodas RRHH"))
				new Cat_Comprobar_Fuente_Sodas().setVisible(true);
			
			if(e.getActionCommand().equals("Fuente de Sodas AUXF"))
				new Cat_Filtro_Fue_Soda_Auxf().setVisible(true);
			
//			if(e.getActionCommand().equals("Bancos"))
//				dispose();
//			if(e.getActionCommand().equals("Permisos de Usuario"))
//				dispose();
//---------------------------------------------------------------------------------------------		
			if(e.getActionCommand().equals("Prestamos"))
				new Cat_Filtro_Prestamo().setVisible(true);
			if(e.getActionCommand().equals("Usuario"))
				new Cat_Usuario().setVisible(true);
			if(e.getActionCommand().equals("Bono"))
				new Cat_Bono().setVisible(true);
			if(e.getActionCommand().equals("Puesto"))
				new Cat_Puesto().setVisible(true);
			if(e.getActionCommand().equals("Sueldo"))
				new Cat_Sueldo().setVisible(true);
//			if(e.getActionCommand().equals("Catalogo_Status"))
			if(e.getActionCommand().equals("Cerrar"))
				dispose();
//				new CatPassword().setVisible(true);
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
