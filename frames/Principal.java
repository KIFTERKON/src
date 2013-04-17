package frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import objetos.Obj_Auto_Auditoria;
import objetos.Obj_Auto_Finanzas;
import objetos.Obj_MD5;
import objetos.Obj_Usuario;

import catalogos.Cat_Asistencia_Puntualidad;
import catalogos.Cat_Auto_Auditoria;
import catalogos.Cat_Auto_Finanzas;
import catalogos.Cat_Bancos;
import catalogos.Cat_Bono_Complemento_Sueldo;
import catalogos.Cat_Comprobar_Fuente_Sodas_RH;
import catalogos.Cat_Conexion_BD;
import catalogos.Cat_Configuracion_Sistema;
import catalogos.Cat_Deduccion_Inasistencia;
import catalogos.Cat_Denominaciones;
import catalogos.Cat_Divisa_Y_TipoDeCambio;
import catalogos.Cat_Empleado;
import catalogos.Cat_Establecimiento;
import catalogos.Cat_Filtro_Cortes;
import catalogos.Cat_Filtro_Diferiencia_Cortes;
import catalogos.Cat_Filtro_Fue_Soda_Auxf;
import catalogos.Cat_Filtro_Fue_Soda_Rh;
import catalogos.Cat_Filtro_Prestamo;
import catalogos.Cat_Imprimir_Fuente_Sodas_Auxf;
import catalogos.Cat_Imprimir_LR;
import catalogos.Cat_Imprimir_Plantilla_Activa;
import catalogos.Cat_Lista_Deudores_Prestamo;
import catalogos.Cat_Lista_Pago;
import catalogos.Cat_Nomina;
import catalogos.Cat_Revision_Lista_Raya;
import catalogos.Cat_Percepciones_Extra;
import catalogos.Cat_Puesto;
import catalogos.Cat_Rango_Prestamos;
import catalogos.Cat_Sueldo;
import catalogos.Cat_Tipo_Banco;
import catalogos.Cat_Turno;
import catalogos.Cat_Usuario;

@SuppressWarnings("serial")
public class Principal extends JFrame{
	
	 /**     https://github.com/RguezMario/josedg.git      ***
	 ***	   Frame Principal							   ***
	 ***       autores: Jimenez Molina Edgar Eduardo       ***
	 ***			 Rodriguez Sanchez Jose Mario.         ***
	 ********************************************************/
	
	String Inasistencia =("Deduccion por Inasistencia");
	
	JButton btnBanco= new JButton(new ImageIcon("imagen/banco.png"));
	JLabel lblBanco2= new JLabel("Banco");
	JButton btnInasistencia= new JButton(new ImageIcon("imagen/inasistencia.png"));
	JLabel lblInasistencia2= new JLabel("Deduccion por");
	JLabel lblInasistencia3= new JLabel("Inasistencia");
	
	JButton btnCaja= new JButton(new ImageIcon("imagen/caja2.png"));
	JLabel lblCaja2= new JLabel("Diferencia de");
	JLabel lblCaja3= new JLabel("Cortes");
	
	JButton btnFsRH= new JButton(new ImageIcon("imagen/fsRH.png"));
	JLabel lblFsRH2= new JLabel("Fuente de Sodas");
	JLabel lblFsRH3= new JLabel("DH");
	
	JButton btnFsAux= new JButton(new ImageIcon("imagen/fsAux.png"));
	JLabel lblFsAux2= new JLabel("Fuente de Sodas");
	JLabel lblFsAux3= new JLabel("Auxiliar y Finanzas");
	
	JButton btnPExtras= new JButton(new ImageIcon("imagen/PExtra.png"));
	JLabel lblPExtras2= new JLabel("Percepciones");
	JLabel lblPExtras3= new JLabel("Extras");
	
	JButton btnPrestamo= new JButton(new ImageIcon("imagen/prestamo.png"));
	JLabel lblPrestamo2= new JLabel("Prestamos");
	
	JButton btnAltaEmp= new JButton(new ImageIcon("imagen/altaEmp.png"));
	JLabel lblAltaEmp2= new JLabel("Alta");
	JLabel lblAltaEmp3= new JLabel("Empleados");
	
	JButton btnPuesto= new JButton(new ImageIcon("imagen/puesto.png"));
	JLabel lblPuesto2= new JLabel("Puesto");
	
	JButton btnSueldo= new JButton(new ImageIcon("imagen/sueldo.png"));
	JLabel lblSueldo2= new JLabel("Sueldo");
	
	JButton btnListaRaya= new JButton(new ImageIcon("imagen/listaR.png"));
	JLabel lblListaRaya2= new JLabel("Lista de");
	JLabel lblListaRaya3= new JLabel("Raya");
	
	JButton btnListaFirma= new JButton(new ImageIcon("imagen/listaF.png"));
	JLabel lblListaFirma2= new JLabel("Lista de");
	JLabel lblListaFirma3= new JLabel("Firmas");
	
	JButton btnListaPrestamo= new JButton(new ImageIcon("imagen/listaP.png"));
	JLabel lblListaPrestamo2= new JLabel("Lista de");
	JLabel lblListaPrestamo3= new JLabel("Prestamos");
	
	JButton btnListaComparacion= new JButton(new ImageIcon("imagen/comparacion.png"));
	JLabel lblListaComparacion2= new JLabel("Lista de");
	JLabel lblListaComparacion3= new JLabel("Comparacion FS");
	
	JButton btnRevicion= new JButton(new ImageIcon("imagen/rebicionTotales.png"));
	JLabel lblRevicion2= new JLabel("Revision de");
	JLabel lblRevicion3= new JLabel("Totales");
	
	JLabel lblFolio = new JLabel("Folio:");
	JTextField txtFolio = new JTextField("");
	JLabel lblUsuario = new JLabel("Usuario:");
	JTextField txtUsuario = new JTextField("");
	JLabel lblContrasena = new JLabel("Contraseña:");
	JPasswordField txtContrasena = new JPasswordField("");
	
	JButton btnSalir = new JButton("Salir");
	JButton btnAceptar = new JButton("Entrar");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	
	JLabel lblNivel = new JLabel("");
	
	private Dimension dim; 
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	JMenu Archivo = new JMenu("Archivo");
	JMenuItem Cerrar = new JMenuItem("Cerrar", new ImageIcon("foto/Salir.png"));
		
	JMenu Alimentacion = new JMenu("Alimentación");
		JMenuItem Alimentacion_Fuente_Sodas_rh = new JMenuItem("Fuente de Sodas RRHH");
		JMenuItem Alimentacion_Fuente_Sodas_auxf = new JMenuItem("Fuente de Sodas AUXF");
		JMenuItem Alimentacion_Deducciones_Asistencia = new JMenuItem("Deducción por Inasistencia");
		JMenuItem Alimentacion_Diferencia_Cortes = new JMenuItem("Diferencia de Cortes");
		JMenuItem Alimentacion_Bancos = new JMenuItem("Bancos");
		JMenuItem Alimentacion_Percepciones_Extra = new JMenuItem("Percepciones Extras");
		JMenuItem Alimentacion_Prestamos = new JMenuItem("Prestamos");
		
	JMenu Catalogo = new JMenu("Catalogo");
		JMenuItem Catalogo_Alta = new JMenuItem("Alta Empleados", new ImageIcon(""));
		JMenuItem Catalogo_Puesto = new JMenuItem("Puesto", new ImageIcon(""));
		JMenuItem Catalogo_Sueldo = new JMenuItem("Sueldo", new ImageIcon(""));
		JMenuItem Catalogo_Tipo_Banco = new JMenuItem("Tipo Bancos", new ImageIcon(""));
		
	JMenu Listas = new JMenu("Listas");	
		JMenuItem Listas_Raya = new JMenuItem("Revision Lista de Raya");
		JMenuItem Listas_Analisis = new JMenuItem("Analisis Lista de Raya");
		JMenuItem Listas_Firma = new JMenuItem("Lista de Firmas");
		JMenuItem Listas_Prestamo = new JMenuItem("Lista de Prestamos");
		JMenuItem Listas_Comparacion_Fuente_Soda = new JMenuItem("Lista de Comparación FS.");
		JMenuItem Listas_Total_Nomina = new JMenuItem("Totales De Cheque");
		
	JMenu Configuracion = new JMenu("Configuración");
		JMenuItem Configuracion_Asistencia_Puntualidad = new JMenuItem("Asistencia y Puntualidad", new ImageIcon(""));
		JMenuItem Configuracion_Bono = new JMenuItem("Bono", new ImageIcon(""));
		JMenuItem Configuracion_ConexionBD = new JMenuItem("Configuración BD", new ImageIcon(""));
		JMenuItem Configuracion_Establecimiento = new JMenuItem("Establecimiento", new ImageIcon("///"));
		JMenuItem Configuracion_Prestamo = new JMenuItem("Rango de Prestamo", new ImageIcon(""));
		JMenuItem Configuracion_Sistema = new JMenuItem("Configuracion de Sistema", new ImageIcon(""));
		JMenuItem Configuracion_Usuario = new JMenuItem("Usuario", new ImageIcon(""));
		JMenuItem Configuracion_Turno = new JMenuItem("Turno", new ImageIcon(""));
	
	JMenu Autorizaciones = new JMenu("Autorizaciones");
		JMenuItem Autorizacion_Auditoria = new JMenuItem("Autorizacion Auditoria");
		JMenuItem Autorizacion_Finanzas = new JMenuItem("Autorizacion Finanzas");
	
	JMenu Reportes = new JMenu("Reportes");
		JMenuItem Reporte_Plantilla_Activa = new JMenuItem("Reporte Plantilla Activa");
		JMenuItem Reporte_Fuente_Sodas = new JMenuItem("Reporte Fuente Sodas");
		
	JMenu Ayuda = new JMenu("Ayuda");
		JMenuItem Edicion_AcercaDe = new JMenuItem("Acerca de", new ImageIcon("foto/help.png"));
		JMenuItem Permiso_user = new JMenuItem("Permisos de Usuario", new ImageIcon("foto/help.png"));
		
	JMenu Cortes = new JMenu("Cortes");
		JMenuItem Divisas = new JMenuItem("Divisas y Tipo de Cambio", new ImageIcon(""));
		JMenuItem Denominaciones = new JMenuItem("Denominaciones", new ImageIcon(""));
		JMenuItem Alimentacion_Corte = new JMenuItem("Alimentacion de Cortes",new ImageIcon(""));
		
	public Principal(){
		this.setTitle("  Grupo Izagar  ");
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/izagar2.png"));
						
		this.setJMenuBar(miMenuTop());
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		campo.add(new JLabel(new ImageIcon("imagen/LogPrincipal.png"))).setBounds(980,0,400,218);
		campo.add(btnBanco).setBounds(30,40,64,64);
		campo.add(btnInasistencia).setBounds(30,164,64,64);
		campo.add(btnCaja).setBounds(30,288,64,64);
		campo.add(btnFsRH).setBounds(30,412,64,64);
		campo.add(btnFsAux).setBounds(30,536,64,64);		
		
		campo.add(lblBanco2).setBounds(124,60,60,20);
		campo.add(lblInasistencia2).setBounds(124,184,120,20);
		campo.add(lblInasistencia3).setBounds(124,194,120,20);
		campo.add(lblCaja2).setBounds(124,308,64,20);
		campo.add(lblCaja3).setBounds(124,318,64,20);
		campo.add(lblFsRH2).setBounds(124,432,90,20);
		campo.add(lblFsRH3).setBounds(124,442,64,20);
		campo.add(lblFsAux2).setBounds(124,556,90,20);
		campo.add(lblFsAux3).setBounds(124,566,90,20);
		
		campo.add(btnPExtras).setBounds(218,40,64,64);
		campo.add(btnPrestamo).setBounds(218,164,64,64);
		
		campo.add(lblPExtras2).setBounds(312,60,64,20);
		campo.add(lblPExtras3).setBounds(312,70,64,20);
		campo.add(lblPrestamo2).setBounds(312,184,64,20);

		campo.add(btnAltaEmp).setBounds(406,40,64,64);
		campo.add(btnPuesto).setBounds(406,164,64,64);
		campo.add(btnSueldo).setBounds(406,288,64,64);
		
		campo.add(lblAltaEmp2).setBounds(500,60,64,20);
		campo.add(lblAltaEmp3).setBounds(500,70,64,20);
		campo.add(lblPuesto2).setBounds(500,184,64,20);
		campo.add(lblSueldo2).setBounds(500,308,64,20);
		
		campo.add(btnListaRaya).setBounds(594,40,64,64);
		campo.add(btnListaFirma).setBounds(594,164,64,64);
		campo.add(btnListaPrestamo).setBounds(594,288,64,64);
		campo.add(btnListaComparacion).setBounds(594,412,64,64);
		campo.add(btnRevicion).setBounds(594,536,64,64);
		
		campo.add(lblListaRaya2).setBounds(688,60,64,20);
		campo.add(lblListaRaya3).setBounds(688,70,64,20);
		campo.add(lblListaFirma2).setBounds(688,184,64,20);
		campo.add(lblListaFirma3).setBounds(688,194,64,20);
		campo.add(lblListaPrestamo2).setBounds(688,308,64,20);
		campo.add(lblListaPrestamo3).setBounds(688,318,64,20);
		campo.add(lblListaComparacion2).setBounds(688,432,64,20);
		campo.add(lblListaComparacion3).setBounds(688,442,90,20);
		campo.add(lblRevicion2).setBounds(688,556,64,20);
		campo.add(lblRevicion3).setBounds(688,566,64,20);
		
		campo.add(lblFolio).setBounds(1000, 490, 80, 20);
		campo.add(lblUsuario).setBounds(1000, 520, 80, 20);
		campo.add(lblContrasena).setBounds(980, 550, 100, 20);
		campo.add(txtFolio).setBounds(1060, 490, 220, 20);
		campo.add(txtUsuario).setBounds(1060, 520, 220, 20);
		campo.add(txtContrasena).setBounds(1060, 550, 220, 20);
	
		campo.add(btnBuscar).setBounds(1290, 490, 30, 20);
		campo.add(btnSalir).setBounds(1150, 580, 65, 20);
		campo.add(btnAceptar).setBounds(1215, 580, 65, 20);
		
		txtFolio.grabFocus();
		Permisos_False();
		
		btnBanco.setEnabled(false);
		btnInasistencia.setEnabled(false);
		btnCaja.setEnabled(false);
		btnFsRH.setEnabled(false);
		btnFsAux.setEnabled(false);
		btnPExtras.setEnabled(false);
		btnPrestamo.setEnabled(false);
		btnAltaEmp.setEnabled(false);
		btnPuesto.setEnabled(false);
		btnSueldo.setEnabled(false);
		btnListaRaya.setEnabled(false);
		btnListaFirma.setEnabled(false);
		btnListaPrestamo.setEnabled(false);
		btnListaComparacion.setEnabled(false);
		btnRevicion.setEnabled(false);
		
		txtFolio.addKeyListener(validaBuscar);
		txtContrasena.addKeyListener(validaGuardar);
		
		btnBuscar.addActionListener(buscar);
		btnAceptar.addActionListener(opEntrar);
		btnSalir.addActionListener(opSalir);
		btnBanco.addActionListener(opBanco);
		btnInasistencia.addActionListener(opInasistencia);
		btnCaja.addActionListener(opCortes);
		btnFsRH.addActionListener(opFSRH);
		btnFsAux.addActionListener(opFSAuxF);
		btnPExtras.addActionListener(opPersecciones);
		btnPrestamo.addActionListener(opPrestamo);
		btnAltaEmp.addActionListener(opEmpleado);
		btnPuesto.addActionListener(opPuesto);
		btnSueldo.addActionListener(opSueldo);
		btnListaRaya.addActionListener(opLRaya);
		btnListaFirma.addActionListener(opLPago);
		btnListaPrestamo.addActionListener(opLDeudores);
		btnListaComparacion.addActionListener(opComprobarFS);
		btnRevicion.addActionListener(opRevicion);
		
		txtFolio.addKeyListener(numerico_action);
		txtUsuario.setEnabled(false);
		
		cont.add(campo);
				
		dim=super.getToolkit().getScreenSize(); 
		this.setSize(dim); 	
		tabbedPane.setBackground(Color.white);
		tabbedPane.addTab("Lista de Raya", new ImageIcon("imagen/Report.png"), campo);
		cont.add(tabbedPane);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(!txtFolio.getText().equals("")){
				Obj_Usuario usuario = new Obj_Usuario();
				usuario = usuario.buscar(Integer.parseInt(txtFolio.getText()));	
				
				if(usuario.getFolio() != 0){	
					txtUsuario.setText(usuario.getNombre_completo());
					txtContrasena.requestFocus();
				}
				else{
					JOptionPane.showMessageDialog(null, "El usuario no existe","Aviso",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}else{
				JOptionPane.showMessageDialog(null, "Ingrese el Folio del Usuario","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}	
		}
	};
			
			ActionListener opEntrar = new ActionListener(){
			@SuppressWarnings({ "deprecation", "static-access" })
			public void actionPerformed(ActionEvent e){
				if(txtFolio.getText().equals("")){
					txtFolio.setText("");
					txtFolio.setText("");
					txtContrasena.setText("");
					JOptionPane.showMessageDialog(null,"Ingrese un folio para realizar la función", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					Obj_Usuario usuario = new Obj_Usuario().buscar(Integer.parseInt(txtFolio.getText()));
					if(usuario.getFolio() !=0){
						if(txtContrasena.getText().equals("")){
							JOptionPane.showMessageDialog(null,"Inserte la contraseña.", "Aviso", JOptionPane.WARNING_MESSAGE);
							return;
						}else{
							Obj_MD5 algoritmo = new Obj_MD5();
							String cadena = algoritmo.cryptMD5(txtContrasena.getText(),"izagar");
							String clave= usuario.getContrasena();
							
							if(!clave.equals(cadena)){
								txtContrasena.setText("");
								JOptionPane.showMessageDialog(null,"La contraseñas no es valida", "Aviso", JOptionPane.WARNING_MESSAGE);
								return;						
							}else{
								txtUsuario.setText(usuario.getNombre_completo().trim());
								btnAceptar.setEnabled(false);
								txtFolio.setEditable(false);
								txtContrasena.setEditable(false);
								switch(usuario.getPermiso_id()){
								
									case 1:	logAdmin();		PrermisoAdmin();	break;
									case 2:	logDH();		PrermisoDH();		break;
									case 3:	logAuxF();		PrermisoAuxf();		break;	
									case 4:	logAudit();		PrermisoAudit();	break;
									case 5:	logCont();		PrermisoCont();		break;
									case 6:	logCons();		PrermisoCons();		break;
								}
							}
						}

					}else{
						JOptionPane.showMessageDialog(null,"El Usuario no existe", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}	
			}
		};
		
		ActionListener opSalir = new ActionListener(){
			public void actionPerformed(ActionEvent e){
							
				txtFolio.setText("");
				txtUsuario.setText("");
				txtContrasena.setText("");			
				btnAceptar.setEnabled(true);
				
				btnBanco.setEnabled(false);
				btnInasistencia.setEnabled(false);
				btnCaja.setEnabled(false);
				btnFsRH.setEnabled(false);
				btnFsAux.setEnabled(false);
				btnPExtras.setEnabled(false);
				btnPrestamo.setEnabled(false);
				btnAltaEmp.setEnabled(false);
				btnPuesto.setEnabled(false);
				btnSueldo.setEnabled(false);
				btnListaRaya.setEnabled(false);
				btnListaFirma.setEnabled(false);
				btnListaPrestamo.setEnabled(false);
				btnListaComparacion.setEnabled(false);
				btnRevicion.setEnabled(false);
				
//				txtFolio.requestFocus();
				txtFolio.setEditable(true);
				txtContrasena.setEditable(true);
							
				Permisos_False();
			}
		};
		
		KeyListener numerico_action = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
	
			   if(((caracter < '0') ||
			        (caracter > '9')) &&
			        (caracter != KeyEvent.VK_BACK_SPACE)){
			    	e.consume(); 
			    }			
			}
			@Override
			public void keyPressed(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
									
		};
		
		ActionListener opBanco = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Bancos().setVisible(true);
			}
		};
		ActionListener opInasistencia = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Deduccion_Inasistencia().setVisible(true);
			}
		};
		ActionListener opCortes = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Filtro_Diferiencia_Cortes().setVisible(true);
			}
		};
		ActionListener opFSRH = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Filtro_Fue_Soda_Rh().setVisible(true);
			}
		};
		ActionListener opFSAuxF = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Filtro_Fue_Soda_Auxf().setVisible(true);
			}
		};
		ActionListener opPersecciones = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Percepciones_Extra().setVisible(true);
			}
		};
		ActionListener opPrestamo = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Filtro_Prestamo().setVisible(true);
			}
		};
		ActionListener opEmpleado = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Empleado().setVisible(true);
			}
		};
		ActionListener opPuesto = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Puesto().setVisible(true);
			}
		};
		ActionListener opSueldo = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Sueldo().setVisible(true);
			}
		};
		ActionListener opLRaya = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Revision_Lista_Raya().setVisible(true);
			}
		};
		ActionListener opLPago = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Lista_Pago().setVisible(true);	
			}
		};
		ActionListener opLDeudores = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Lista_Deudores_Prestamo().setVisible(true);
			}
		};
		ActionListener opComprobarFS = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Cat_Comprobar_Fuente_Sodas_RH().setVisible(true);
			}
		};
		ActionListener opRevicion = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//pendiente
			}
		};
				
	public JMenuBar miMenuTop(){
		JMenuBar Barra = new JMenuBar();
		
		Archivo.setMnemonic(KeyEvent.VK_I);
		Archivo.add(Cerrar);
			Cerrar.addActionListener(Opciones);
			
		Alimentacion.add(Alimentacion_Bancos);
			Alimentacion_Bancos.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Deducciones_Asistencia);
			Alimentacion_Deducciones_Asistencia.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Diferencia_Cortes);
			Alimentacion_Diferencia_Cortes.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Fuente_Sodas_rh);
			Alimentacion_Fuente_Sodas_rh.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Fuente_Sodas_auxf);
			Alimentacion_Fuente_Sodas_auxf.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Percepciones_Extra);
			Alimentacion_Percepciones_Extra.addActionListener(Opciones);
		Alimentacion.add(Alimentacion_Prestamos);
			Alimentacion_Prestamos.addActionListener(Opciones);
			
		Catalogo.setMnemonic(KeyEvent.VK_C);
		Catalogo.add(Catalogo_Alta);
			Catalogo_Alta.addActionListener(Opciones);
		Catalogo.add(Catalogo_Puesto);
			Catalogo_Puesto.addActionListener(Opciones);
		Catalogo.add(Catalogo_Sueldo);
			Catalogo_Sueldo.addActionListener(Opciones);
		Catalogo.add(Catalogo_Tipo_Banco);
			Catalogo_Tipo_Banco.addActionListener(Opciones);
			

		Listas.add(Listas_Raya);
			Listas_Raya.addActionListener(Opciones);
		Listas.add(Listas_Analisis);
			Listas_Analisis.addActionListener(Opciones);
		Listas.add(Listas_Firma);
			Listas_Firma.addActionListener(Opciones);
		Listas.add(Listas_Prestamo);
			Listas_Prestamo.addActionListener(Opciones);
		Listas.add(Listas_Comparacion_Fuente_Soda);
			Listas_Comparacion_Fuente_Soda.addActionListener(Opciones);
		Listas.add(Listas_Total_Nomina);
			Listas_Total_Nomina.addActionListener(Opciones);
		
			
		Configuracion.add(Configuracion_Asistencia_Puntualidad);
			Configuracion_Asistencia_Puntualidad.addActionListener(Opciones);
		Configuracion.add(Configuracion_Bono);
			Configuracion_Bono.addActionListener(Opciones);
		Configuracion.add(Configuracion_ConexionBD);
			Configuracion_ConexionBD.addActionListener(Opciones);
		Configuracion.add(Configuracion_Establecimiento);
			Configuracion_Establecimiento.addActionListener(Opciones);
		Configuracion.add(Configuracion_Prestamo);
			Configuracion_Prestamo.addActionListener(Opciones);
		Configuracion.add(Configuracion_Sistema);
			Configuracion_Sistema.addActionListener(Opciones);
		Configuracion.add(Configuracion_Usuario);
			Configuracion_Usuario.addActionListener(Opciones);
		Configuracion.add(Configuracion_Turno);
			Configuracion_Turno.addActionListener(Opciones);
			
		Reportes.add(Reporte_Plantilla_Activa);
			Reporte_Plantilla_Activa.addActionListener(Opciones);
		Reportes.add(Reporte_Fuente_Sodas);
			Reporte_Fuente_Sodas.addActionListener(Opciones);
			
		Autorizaciones.add(Autorizacion_Auditoria);
			Autorizacion_Auditoria.addActionListener(Opciones);
		Autorizaciones.add(Autorizacion_Finanzas);
			Autorizacion_Finanzas.addActionListener(Opciones);

			
		Ayuda.setMnemonic(KeyEvent.VK_A);
		Ayuda.add(Edicion_AcercaDe);
		Ayuda.add(Permiso_user);
		
		Cortes.add(Divisas);
			Divisas.addActionListener(Opciones);
		Cortes.add(Denominaciones);
			Denominaciones.addActionListener(Opciones);
		Cortes.add(Alimentacion_Corte);
			Alimentacion_Corte.addActionListener(Opciones);
		
		Barra.add(Archivo);
		Barra.add(Alimentacion);
		Barra.add(Catalogo);
		Barra.add(Listas);
		Barra.add(Configuracion);
		Barra.add(Reportes);
		Barra.add(Autorizaciones);
		Barra.add(Ayuda);
		Barra.add(Cortes);
						
		return Barra;
	}
	KeyListener validaBuscar = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
			}
		}
	};
	
	KeyListener validaGuardar = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnAceptar.doClick();
			}
		}
	};
	ActionListener Opciones = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			//Archivo
			if(e.getActionCommand().equals("Cerrar"))
				dispose();

			// Catalogo
			if(e.getActionCommand().equals("Puesto"))
				new Cat_Puesto().setVisible(true);

			// Listas
			if(e.getActionCommand().equals("Revision Lista de Raya"))
				new Cat_Revision_Lista_Raya().setVisible(true);
			if(e.getActionCommand().equals("Analisis Lista de Raya"))
				new Cat_Imprimir_LR().setVisible(true);
			if(e.getActionCommand().equals("Lista de Firmas"))
				new Cat_Lista_Pago().setVisible(true);
			if(e.getActionCommand().equals("Lista de Comparación FS."))
				new Cat_Comprobar_Fuente_Sodas_RH().setVisible(true);
			if(e.getActionCommand().equals("Totales De Cheque"))
				new Cat_Nomina().setVisible(true);
			
			// Configuración
			if(e.getActionCommand().equals("Asistencia y Puntualidad"))
				new Cat_Asistencia_Puntualidad().setVisible(true);
			if(e.getActionCommand().equals("Bono"))
				new Cat_Bono_Complemento_Sueldo().setVisible(true);
			if(e.getActionCommand().equals("Configuración BD"))
				new Cat_Conexion_BD().setVisible(true);
			if(e.getActionCommand().equals("Establecimiento"))
				new Cat_Establecimiento().setVisible(true);	
			if(e.getActionCommand().equals("Rango de Prestamo"))
				new Cat_Rango_Prestamos().setVisible(true);
			if(e.getActionCommand().equals("Configuracion de Sistema"))
				new Cat_Configuracion_Sistema().setVisible(true);
			if(e.getActionCommand().equals("Usuario"))
				new Cat_Usuario().setVisible(true);
			if(e.getActionCommand().equals("Turno"))
				new Cat_Turno().setVisible(true);

			//Autorizacion
			if(e.getActionCommand().equalsIgnoreCase("Autorizacion Auditoria"))
				new Cat_Auto_Auditoria().setVisible(true);
			if(e.getActionCommand().equals("Autorizacion Finanzas"))
				new Cat_Auto_Finanzas().setVisible(true);
			
			//Cortes
			if(e.getActionCommand().equals("Divisas y Tipo de Cambio"))
				new Cat_Divisa_Y_TipoDeCambio().setVisible(true);
			if(e.getActionCommand().equals("Denominaciones"))
				new Cat_Denominaciones().setVisible(true);
			if(e.getActionCommand().equals("Alimentacion de Cortes"))
				new Cat_Filtro_Cortes().setVisible(true);
		
			//Alimentación
			if(e.getActionCommand().equals("Bancos")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Bancos().setVisible(true);
				}
			}
				
			if(e.getActionCommand().equals("Deducción por Inasistencia")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();

				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Deduccion_Inasistencia().setVisible(true);
				}
			}
				
			if(e.getActionCommand().equals("Diferencia de Cortes")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Filtro_Diferiencia_Cortes().setVisible(true);
				}
			}
				
			if(e.getActionCommand().equals("Fuente de Sodas RRHH")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();

				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Filtro_Fue_Soda_Rh().setVisible(true);
				}
			}
				
			if(e.getActionCommand().equals("Fuente de Sodas AUXF")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Filtro_Fue_Soda_Auxf().setVisible(true);
				}
			}
				
			if(e.getActionCommand().equals("Percepciones Extras")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Percepciones_Extra().setVisible(true);
				}
			}
				
			if(e.getActionCommand().equals("Prestamos")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Filtro_Prestamo().setVisible(true);
				}
			}
			
			// Catalogo
			if(e.getActionCommand().equals("Alta Empleados")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Empleado().setVisible(true);
				}
			}
				
			if(e.getActionCommand().equals("Sueldo")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Sueldo().setVisible(true);
				}
			}
			
			if(e.getActionCommand().equals("Tipo Bancos")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Tipo_Banco().setVisible(true);
				}
			}
			
			//listas
			if(e.getActionCommand().equals("Lista de Prestamos")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();
				
				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Lista_Deudores_Prestamo().setVisible(true);
				}
			}
			
			// Reportes
			if(e.getActionCommand().equals("Reporte Plantilla Activa")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();

				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Imprimir_Plantilla_Activa().setVisible(true);
				}
			}
			
			if(e.getActionCommand().equals("Reporte Fuente Sodas")){
				Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
				boolean auto_auditoria = autoriza_auditoria.isAutorizar();
				Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
				boolean auto_finanza = autoriza_finanza.isAutorizar();

				if(auto_auditoria == true || auto_finanza == true){
					JOptionPane.showMessageDialog(null, "La lista de raya esta autorizada, ya no puede realizar\n " +
							"ningún cambio hasta generar la lista de raya...!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					new Cat_Imprimir_Fuente_Sodas_Auxf().setVisible(true);
				}
			}
				
		}
	};
	
	public void Permisos_False(){
		Alimentacion_Fuente_Sodas_rh.setEnabled(false);
		Alimentacion_Fuente_Sodas_auxf.setEnabled(false);
		Alimentacion_Deducciones_Asistencia.setEnabled(false);
		Alimentacion_Diferencia_Cortes.setEnabled(false);
		Alimentacion_Bancos.setEnabled(false);
		Alimentacion_Percepciones_Extra.setEnabled(false);
		Alimentacion_Prestamos.setEnabled(false);
		
		Catalogo_Alta.setEnabled(false);
		Catalogo_Puesto.setEnabled(false);
		Catalogo_Sueldo.setEnabled(false);
		
		Listas_Raya.setEnabled(false);
		Listas_Analisis.setEnabled(false);
		Listas_Firma.setEnabled(false);
		Listas_Prestamo.setEnabled(false);
		Listas_Comparacion_Fuente_Soda.setEnabled(false);
		
		Configuracion_Asistencia_Puntualidad.setEnabled(false);
		Configuracion_Bono.setEnabled(false);
		Configuracion_ConexionBD.setEnabled(false);
		Configuracion_Establecimiento.setEnabled(false);
		Configuracion_Prestamo.setEnabled(false);
		Configuracion_Usuario.setEnabled(false);
		Configuracion_Turno.setEnabled(false);
		Configuracion_Sistema.setEnabled(false);
		
		Autorizacion_Auditoria.setEnabled(false);
		Autorizacion_Finanzas.setEnabled(false);

	}
	
	public void PrermisoAdmin(){
		Alimentacion_Fuente_Sodas_rh.setEnabled(true);
		Alimentacion_Fuente_Sodas_auxf.setEnabled(true);
		Alimentacion_Deducciones_Asistencia.setEnabled(true);
		Alimentacion_Diferencia_Cortes.setEnabled(true);
		Alimentacion_Bancos.setEnabled(true);
		Alimentacion_Percepciones_Extra.setEnabled(true);
		Alimentacion_Prestamos.setEnabled(true);
		
		Catalogo_Alta.setEnabled(true);
		Catalogo_Puesto.setEnabled(true);
		Catalogo_Sueldo.setEnabled(true);

		Listas_Raya.setEnabled(true);
		Listas_Analisis.setEnabled(true);
		Listas_Firma.setEnabled(true);
		Listas_Prestamo.setEnabled(true);
		Listas_Comparacion_Fuente_Soda.setEnabled(true);
		
		Configuracion_Asistencia_Puntualidad.setEnabled(true);
		Configuracion_Bono.setEnabled(true);
		Configuracion_ConexionBD.setEnabled(true);
		Configuracion_Establecimiento.setEnabled(true);
		Configuracion_Prestamo.setEnabled(true);
		Configuracion_Usuario.setEnabled(true);
		Configuracion_Turno.setEnabled(true);
		Configuracion_Sistema.setEnabled(true);
		
		Autorizacion_Auditoria.setEnabled(true);
		Autorizacion_Finanzas.setEnabled(true);
}
	
public void PrermisoDH(){
	Alimentacion_Fuente_Sodas_rh.setEnabled(true);
	Alimentacion_Fuente_Sodas_auxf.setEnabled(false);
	Alimentacion_Deducciones_Asistencia.setEnabled(true);
	Alimentacion_Diferencia_Cortes.setEnabled(true);
	Alimentacion_Bancos.setEnabled(false);
	Alimentacion_Percepciones_Extra.setEnabled(true);
	Alimentacion_Prestamos.setEnabled(true);
	
	Catalogo_Alta.setEnabled(true);
	Catalogo_Puesto.setEnabled(true);
	Catalogo_Sueldo.setEnabled(true);
	
	Listas_Raya.setEnabled(true);
	Listas_Analisis.setEnabled(true);
	Listas_Firma.setEnabled(true);
	Listas_Prestamo.setEnabled(true);
	Listas_Comparacion_Fuente_Soda.setEnabled(true);
	
	Configuracion_Asistencia_Puntualidad.setEnabled(false);
	Configuracion_Bono.setEnabled(true);
	Configuracion_ConexionBD.setEnabled(false);
	Configuracion_Establecimiento.setEnabled(false);
	Configuracion_Prestamo.setEnabled(true);
	Configuracion_Usuario.setEnabled(false);
	Configuracion_Turno.setEnabled(true);
}

public void PrermisoAuxf(){
	Alimentacion_Fuente_Sodas_rh.setEnabled(false);
	Alimentacion_Fuente_Sodas_auxf.setEnabled(true);
	Alimentacion_Deducciones_Asistencia.setEnabled(false);
	Alimentacion_Diferencia_Cortes.setEnabled(false);
	Alimentacion_Bancos.setEnabled(false);
	Alimentacion_Percepciones_Extra.setEnabled(false);
	Alimentacion_Prestamos.setEnabled(false);
	
	Catalogo_Alta.setEnabled(false);
	Catalogo_Puesto.setEnabled(false);
	Catalogo_Sueldo.setEnabled(false);

	Listas_Raya.setEnabled(false);
	Listas_Analisis.setEnabled(true);
	Listas_Firma.setEnabled(false);
	Listas_Prestamo.setEnabled(false);
	Listas_Comparacion_Fuente_Soda.setEnabled(false);
	
	Configuracion_Asistencia_Puntualidad.setEnabled(false);
	Configuracion_Bono.setEnabled(false);
	Configuracion_ConexionBD.setEnabled(false);
	Configuracion_Establecimiento.setEnabled(false);
	Configuracion_Prestamo.setEnabled(false);
	Configuracion_Usuario.setEnabled(false);
	Configuracion_Turno.setEnabled(false);
}

public void PrermisoAudit(){
	Alimentacion_Fuente_Sodas_rh.setEnabled(true);
	Alimentacion_Fuente_Sodas_auxf.setEnabled(true);
	Alimentacion_Deducciones_Asistencia.setEnabled(true);
	Alimentacion_Diferencia_Cortes.setEnabled(true);
	Alimentacion_Bancos.setEnabled(true);
	Alimentacion_Percepciones_Extra.setEnabled(true);
	Alimentacion_Prestamos.setEnabled(true);
	
	Catalogo_Alta.setEnabled(true);
	Catalogo_Puesto.setEnabled(true);
	Catalogo_Sueldo.setEnabled(true);
	
	Listas_Raya.setEnabled(true);
	Listas_Analisis.setEnabled(true);
	Listas_Firma.setEnabled(true);
	Listas_Prestamo.setEnabled(true);
	Listas_Comparacion_Fuente_Soda.setEnabled(true);
	
	Configuracion_Asistencia_Puntualidad.setEnabled(true);
	Configuracion_Bono.setEnabled(true);
	Configuracion_ConexionBD.setEnabled(true);
	Configuracion_Establecimiento.setEnabled(true);
	Configuracion_Prestamo.setEnabled(true);
	Configuracion_Usuario.setEnabled(true);
	Configuracion_Turno.setEnabled(true);
	
	Autorizacion_Auditoria.setEnabled(true);
}

public void PrermisoCont(){
	Alimentacion_Fuente_Sodas_rh.setEnabled(true);
	Alimentacion_Fuente_Sodas_auxf.setEnabled(true);
	Alimentacion_Deducciones_Asistencia.setEnabled(true);
	Alimentacion_Diferencia_Cortes.setEnabled(true);
	Alimentacion_Bancos.setEnabled(true);
	Alimentacion_Percepciones_Extra.setEnabled(true);
	Alimentacion_Prestamos.setEnabled(true);
	
	Catalogo_Alta.setEnabled(true);
	Catalogo_Puesto.setEnabled(true);
	Catalogo_Sueldo.setEnabled(true);

	Listas_Raya.setEnabled(true);
	Listas_Analisis.setEnabled(true);
	Listas_Firma.setEnabled(true);
	Listas_Prestamo.setEnabled(true);
	Listas_Comparacion_Fuente_Soda.setEnabled(true);
	
	Configuracion_Asistencia_Puntualidad.setEnabled(true);
	Configuracion_Bono.setEnabled(true);
	Configuracion_ConexionBD.setEnabled(true);
	Configuracion_Establecimiento.setEnabled(true);
	Configuracion_Prestamo.setEnabled(true);
	Configuracion_Usuario.setEnabled(true);
	Configuracion_Turno.setEnabled(true);
}

public void PrermisoCons(){
	Alimentacion_Fuente_Sodas_rh.setEnabled(true);
	Alimentacion_Fuente_Sodas_auxf.setEnabled(true);
	Alimentacion_Deducciones_Asistencia.setEnabled(true);
	Alimentacion_Diferencia_Cortes.setEnabled(true);
	Alimentacion_Bancos.setEnabled(true);
	Alimentacion_Percepciones_Extra.setEnabled(true);
	Alimentacion_Prestamos.setEnabled(true);
	
	Catalogo_Alta.setEnabled(true);
	Catalogo_Puesto.setEnabled(true);
	Catalogo_Sueldo.setEnabled(true);

	Listas_Raya.setEnabled(true);
	Listas_Analisis.setEnabled(true);
	Listas_Firma.setEnabled(true);
	Listas_Prestamo.setEnabled(true);
	Listas_Comparacion_Fuente_Soda.setEnabled(true);
	
	Configuracion_Asistencia_Puntualidad.setEnabled(true);
	Configuracion_Bono.setEnabled(true);
	Configuracion_ConexionBD.setEnabled(true);
	Configuracion_Establecimiento.setEnabled(true);
	Configuracion_Prestamo.setEnabled(true);
	Configuracion_Usuario.setEnabled(true);
	Configuracion_Turno.setEnabled(true);
}
//PERMISO DE BOTONES	
public void logAdmin(){
	btnBanco.setEnabled(true);
	btnInasistencia.setEnabled(true);
	btnCaja.setEnabled(true);
	btnFsRH.setEnabled(true);
	btnFsAux.setEnabled(true);
	btnPExtras.setEnabled(true);
	btnPrestamo.setEnabled(true);
	btnAltaEmp.setEnabled(true);
	btnPuesto.setEnabled(true);
	btnSueldo.setEnabled(true);
	btnListaRaya.setEnabled(true);
	btnListaFirma.setEnabled(true);
	btnListaPrestamo.setEnabled(true);
	btnListaComparacion.setEnabled(true);
	btnRevicion.setEnabled(true);	
}

public void logDH(){
	btnBanco.setEnabled(false);
	btnInasistencia.setEnabled(true);
	btnCaja.setEnabled(true);
	btnFsRH.setEnabled(true);
	btnFsAux.setEnabled(false);
	btnPExtras.setEnabled(true);
	btnPrestamo.setEnabled(true);
	btnAltaEmp.setEnabled(true);
	btnPuesto.setEnabled(true);
	btnSueldo.setEnabled(true);
	btnListaRaya.setEnabled(true);
	btnListaFirma.setEnabled(true);
	btnListaPrestamo.setEnabled(true);
	btnListaComparacion.setEnabled(true);
	btnRevicion.setEnabled(true);	
}

public void logAuxF(){
	btnBanco.setEnabled(false);
	btnInasistencia.setEnabled(false);
	btnCaja.setEnabled(false);
	btnFsRH.setEnabled(false);
	btnFsAux.setEnabled(true);
	btnPExtras.setEnabled(false);
	btnPrestamo.setEnabled(false);
	btnAltaEmp.setEnabled(false);
	btnPuesto.setEnabled(false);
	btnSueldo.setEnabled(false);
	btnListaRaya.setEnabled(false);
	btnListaFirma.setEnabled(false);
	btnListaPrestamo.setEnabled(false);
	btnListaComparacion.setEnabled(false);
	btnRevicion.setEnabled(false);	
}

public void logAudit(){
	btnBanco.setEnabled(true);
	btnInasistencia.setEnabled(false);
	btnCaja.setEnabled(false);
	btnFsRH.setEnabled(false);
	btnFsAux.setEnabled(false);
	btnPExtras.setEnabled(false);
	btnPrestamo.setEnabled(false);
	btnAltaEmp.setEnabled(false);
	btnPuesto.setEnabled(false);
	btnSueldo.setEnabled(false);
	btnListaRaya.setEnabled(true);
	btnListaFirma.setEnabled(false);
	btnListaPrestamo.setEnabled(false);
	btnListaComparacion.setEnabled(false);
	btnRevicion.setEnabled(false);	
}

public void logCont(){
	btnBanco.setEnabled(false);
	btnInasistencia.setEnabled(false);
	btnCaja.setEnabled(false);
	btnFsRH.setEnabled(false);
	btnFsAux.setEnabled(true);
	btnPExtras.setEnabled(false);
	btnPrestamo.setEnabled(true);
	btnAltaEmp.setEnabled(false);
	btnPuesto.setEnabled(false);
	btnSueldo.setEnabled(false);
	btnListaRaya.setEnabled(false);
	btnListaFirma.setEnabled(false);
	btnListaPrestamo.setEnabled(false);
	btnListaComparacion.setEnabled(false);
	btnRevicion.setEnabled(false);	
}

public void logCons(){
	btnBanco.setEnabled(false);
	btnInasistencia.setEnabled(false);
	btnCaja.setEnabled(false);
	btnFsRH.setEnabled(false);
	btnFsAux.setEnabled(false);
	btnPExtras.setEnabled(false);
	btnPrestamo.setEnabled(false);
	btnAltaEmp.setEnabled(true);
	btnPuesto.setEnabled(false);
	btnSueldo.setEnabled(false);
	btnListaRaya.setEnabled(false);
	btnListaFirma.setEnabled(false);
	btnListaPrestamo.setEnabled(false);
	btnListaComparacion.setEnabled(false);
	btnRevicion.setEnabled(false);	
}
public static void main(String[] args) {
	try{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		String ruta = System.getProperty("user.dir")+"\\Config\\config";
		
		File archivo = new File(ruta);
		if(archivo.exists()){
			@SuppressWarnings("unused")
			Obj_Usuario usuario = new Obj_Usuario().buscarMaximo();
			
			new Principal().setVisible(true);
		
		}else{
			JOptionPane.showMessageDialog(null, "Configure las variables de la conexion a la Base de Datos","Error",JOptionPane.WARNING_MESSAGE);
			new Cat_Conexion_BD().setVisible(true);
		}

	}catch(Exception e){
		e.printStackTrace();
	}	  		
}
}