package frames;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import checador.Cat_Checador;


import objetos.Obj_Usuario;
import catalogos.Cat_Bancos;
import catalogos.Cat_Deduccion_Inasistencia;
import catalogos.Cat_Empleado;
import catalogos.Cat_Filtro_Diferiencia_Cortes;
import catalogos.Cat_Filtro_Prestamo;
import catalogos.Cat_Lista_Pago;
import catalogos.Cat_Percepciones_Extra;
import catalogos.Cat_Puesto;
import catalogos.Cat_Revision_Lista_Raya;
import catalogos.Cat_Sueldo;
import fuente_sodas.Cat_Comprobar_Fuente_Sodas_RH;
import fuente_sodas.Cat_Filtro_Fue_Soda_Auxf;
import fuente_sodas.Cat_Filtro_Fue_Soda_Rh;

@SuppressWarnings("serial")
public class InitButton extends InitMenuBar{
	
	/* BOTON BANCO */
	JButton btnBanco= new JButton(new ImageIcon("imagen/banco.png"));
	JLabel lblBanco= new JLabel("Banco");
	
	/* BOTON INASISTENCIA */
	JButton btnInasistencia= new JButton(new ImageIcon("imagen/inasistencia.png"));
	JLabel lblInasistencia2= new JLabel("Deduccion por");
	JLabel lblInasistencia3= new JLabel("Inasistencia");
	
	/* BOTON DIFERENCIA DE CORTES */
	JButton btnCaja= new JButton(new ImageIcon("imagen/caja2.png"));
	JLabel lblCaja2= new JLabel("Diferencia de");
	JLabel lblCaja3= new JLabel("Cortes");
	
	/* BOTON FUENTE DE SODAS DH*/
	JButton btnFsRH= new JButton(new ImageIcon("imagen/fsRH.png"));
	JLabel lblFsRH2= new JLabel("Fuente de Sodas");
	JLabel lblFsRH3= new JLabel("DH");
	
	/* BOTON FUENTE DE SODAS AUX FIN */
	JButton btnFsAux= new JButton(new ImageIcon("imagen/fsAux.png"));
	JLabel lblFsAux2= new JLabel("Fuente de Sodas");
	JLabel lblFsAux3= new JLabel("Auxiliar y Finanzas");
	
	/* BOTON PERCEPCIONES */
	JButton btnPExtras= new JButton(new ImageIcon("imagen/PExtra.png"));
	JLabel lblPExtras1= new JLabel("Percepciones");
	JLabel lblPExtras2= new JLabel("Extras");
	
	/* BOTON PRESTAMOS */
	JButton btnPrestamo= new JButton(new ImageIcon("imagen/prestamo.png"));
	JLabel lblPrestamo2= new JLabel("Prestamos");
	
	/* BOTON ALTA EMPLEADOS */
	JButton btnAltaEmp= new JButton(new ImageIcon("imagen/altaEmp.png"));
	JLabel lblAltaEmp2= new JLabel("Alta");
	JLabel lblAltaEmp3= new JLabel("Empleados");
	
	/* BOTON PUESTO */
	JButton btnPuesto= new JButton(new ImageIcon("imagen/puesto.png"));
	JLabel lblPuesto2= new JLabel("Puesto");
	
	/* BOTON SUELDOS */
	JButton btnSueldo= new JButton(new ImageIcon("imagen/sueldo.png"));
	JLabel lblSueldo2= new JLabel("Sueldo");
	
	/* BOTON LISTA DE RAYA */
	JButton btnListaRaya= new JButton(new ImageIcon("imagen/listaR.png"));
	JLabel lblListaRaya2= new JLabel("Lista de");
	JLabel lblListaRaya3= new JLabel("Raya");
	
	/* BOTON LISTA DE FIRMAS */
	JButton btnListaFirma= new JButton(new ImageIcon("imagen/listaF.png"));
	JLabel lblListaFirma2= new JLabel("Lista de");
	JLabel lblListaFirma3= new JLabel("Firmas");
	
	/* BOTON LISTA DE COMPARACION DE FUENTE DE SODAS */
	JButton btnListaComparacion= new JButton(new ImageIcon("imagen/comparacion.png"));
	JLabel lblListaComparacion2= new JLabel("Lista de");
	JLabel lblListaComparacion3= new JLabel("Comparacion FS");
	
	/* BOTON CHECADOR */
	
	JButton btnChecador= new JButton(new ImageIcon("imagen/checador.png"));
	JLabel lblListaChecador2= new JLabel("Checador");

	
	
	JTextField txtFolio = new JTextField("");
	JTextField txtUsuario = new JTextField("");
	JPasswordField txtContrasena = new JPasswordField("");
	
	JButton btnSalir = new JButton("Salir");
	JButton btnAceptar = new JButton("Entrar");
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	
	JLabel lblLogo = new JLabel(new ImageIcon("imagen/LogPrincipal.png"));
	
	public InitButton(){
		int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		/* MANEJO DE LA RESOLUCIONES */ 
		Resolucion(ancho, alto);
		
		btnSalir.addActionListener(opSalir);
		btnBuscar.addActionListener(opBuscar);
		txtFolio.addKeyListener(enterBuscar);
		txtContrasena.addKeyListener(enterIn);
		
		txtUsuario.setEditable(false);
		btnAceptar.setEnabled(false);
		
		btnBanco.addActionListener(Opciones);
		btnInasistencia.addActionListener(Opciones);
		btnCaja.addActionListener(Opciones);
		btnFsRH.addActionListener(Opciones);
		btnFsAux.addActionListener(Opciones);
		btnPExtras.addActionListener(Opciones);
		btnPrestamo.addActionListener(Opciones);
		btnAltaEmp.addActionListener(Opciones);
		btnPuesto.addActionListener(Opciones);
		btnSueldo.addActionListener(Opciones);
		btnListaRaya.addActionListener(Opciones);
		btnListaFirma.addActionListener(Opciones);
		btnListaComparacion.addActionListener(Opciones);
		btnChecador.addActionListener(Opciones);
		
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
		btnListaComparacion.setEnabled(false);
		btnChecador.setEnabled(true);
	}
	
	ActionListener Opciones = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource().equals(btnBanco))
				new Cat_Bancos().setVisible(true);
			if(arg0.getSource().equals(btnInasistencia))
				new Cat_Deduccion_Inasistencia().setVisible(true);
			if(arg0.getSource().equals(btnCaja))
				new Cat_Filtro_Diferiencia_Cortes().setVisible(true);
			if(arg0.getSource().equals(btnFsAux))
				new Cat_Filtro_Fue_Soda_Auxf().setVisible(true);
			if(arg0.getSource().equals(btnFsRH))
				new Cat_Filtro_Fue_Soda_Rh().setVisible(true);
			if(arg0.getSource().equals(btnPExtras))
				new Cat_Percepciones_Extra().setVisible(true);
			if(arg0.getSource().equals(btnPrestamo))
				new Cat_Filtro_Prestamo().setVisible(true);
			if(arg0.getSource().equals(btnAltaEmp))
				new Cat_Empleado().setVisible(true);
			if(arg0.getSource().equals(btnPuesto))
				new Cat_Puesto().setVisible(true);
			if(arg0.getSource().equals(btnSueldo))
				new Cat_Sueldo().setVisible(true);
			if(arg0.getSource().equals(btnListaRaya))
				new Cat_Revision_Lista_Raya().setVisible(true);
			if(arg0.getSource().equals(btnListaFirma))
				new Cat_Lista_Pago().setVisible(true);
			if(arg0.getSource().equals(btnListaComparacion))
				new Cat_Comprobar_Fuente_Sodas_RH().setVisible(true);
			if(arg0.getSource().equals(btnChecador))
				new Cat_Checador().setVisible(true);

		}
		
	};
	
	public void Resolucion(int ancho, int alto){
		if(ancho >= 1360){
			campo.add(lblLogo).setBounds(980,0,400,218);
			int x = 30, y = 40, z = 65;
			int yl = 60, zl = 120;
					
			/* FILA 1 */
			campo.add(btnBanco).setBounds( x, y, z, z);
			campo.add(btnPExtras).setBounds(220, y, z, z);
			campo.add(btnAltaEmp).setBounds(405, y, z, z);
			campo.add(btnListaRaya).setBounds(594, y, z, z);
			
			campo.add(lblBanco).setBounds(112, yl, zl, 20);
			campo.add(lblPExtras1).setBounds(300, yl, zl, 20);
			campo.add(lblPExtras2).setBounds(300, yl+10, zl, 20);
			campo.add(lblAltaEmp2).setBounds(488, yl, zl, 20);
			campo.add(lblAltaEmp3).setBounds(488, yl+10, zl, 20);
			campo.add(lblListaRaya2).setBounds(676, yl, zl, 20);
			campo.add(lblListaRaya3).setBounds(676, yl+10, zl, 20);
			
			/* FILA 2 */
			campo.add(btnInasistencia).setBounds(x, y+=134, z, z);
			campo.add(btnPrestamo).setBounds(220, y, z, z);
			campo.add(btnPuesto).setBounds(405, y, z, z);
			campo.add(btnListaFirma).setBounds(594, y, z, z);
			
			campo.add(lblInasistencia2).setBounds(112, y+=20, zl, 20);
			campo.add(lblInasistencia3).setBounds(112, y+10, zl, 20);
			campo.add(lblPrestamo2).setBounds(300, y, zl, 20);
			campo.add(lblPuesto2).setBounds(488, y, zl, 20);
			campo.add(lblListaFirma2).setBounds(676, y, zl, 20);
			campo.add(lblListaFirma3).setBounds(676, y+10, zl, 20);
			
			/* FILA 3 */
			campo.add(btnCaja).setBounds(x, y+=115, z, z);
			campo.add(btnSueldo).setBounds(405, y, z, z);
			campo.add(btnListaComparacion).setBounds(594, y, z, z);
			
			campo.add(lblCaja2).setBounds(112, y+=20, zl, 20);
			campo.add(lblCaja3).setBounds(112, y+10, zl, 20);
			campo.add(lblSueldo2).setBounds(488, y, zl, 20);
			campo.add(lblListaComparacion2).setBounds(676, y, zl, 20);
			campo.add(lblListaComparacion3).setBounds(676, y+10, zl, 20);
			
			/* FILA 4 */
			campo.add(btnFsRH).setBounds(x, y+=115, z, z);

			campo.add(lblFsRH2).setBounds(112, y+=20, zl, 20);
			campo.add(lblFsRH3).setBounds(112, y+10, zl, 20);
			
			/* FILA 5 */
			campo.add(btnFsAux).setBounds(x, y+=115, z, z);		
			campo.add(btnChecador).setBounds(594, y, z, z);
			
			campo.add(lblFsAux2).setBounds(112, y+=20, zl, 20);
			campo.add(lblFsAux3).setBounds(112, y+10, zl, 20);
			campo.add(lblListaChecador2).setBounds(676, y+10, zl, 20);
			

			
			campo.add(new JLabel("Folio:")).setBounds(1000, 490, 80, 20);
			campo.add(txtFolio).setBounds(1060, 490, 220, 20);
			
			campo.add(new JLabel("Usuario:")).setBounds(1000, 520, 80, 20);
			campo.add(txtUsuario).setBounds(1060, 520, 220, 20);
			
			campo.add(new JLabel("Contraseña:")).setBounds(980, 550, 100, 20);
			campo.add(txtContrasena).setBounds(1060, 550, 220, 20);
		
			campo.add(btnBuscar).setBounds(1290, 490, 30, 20);
			campo.add(btnSalir).setBounds(1150, 580, 65, 20);
			campo.add(btnAceptar).setBounds(1215, 580, 65, 20);
			
			cont.add(campo);
			
			this.setSize(ancho,alto);

		}
		if(ancho == 1280){
			switch(alto){
				case 768 : 
					campo.add(new JLabel(new ImageIcon("imagen/LogPrincipal.png"))).setBounds(780,0,400,218);
					int x = 30, y = 40, z = 65;
					int yl = 60, zl = 120;
							
					/* FILA 1 */
					campo.add(btnBanco).setBounds( x, y, z, z);
					campo.add(btnPExtras).setBounds(220, y, z, z);
					campo.add(btnAltaEmp).setBounds(405, y, z, z);
					campo.add(btnListaRaya).setBounds(594, y, z, z);
					
					campo.add(lblBanco).setBounds(112, yl, zl, 20);
					campo.add(lblPExtras1).setBounds(300, yl, zl, 20);
					campo.add(lblPExtras2).setBounds(300, yl+10, zl, 20);
					campo.add(lblAltaEmp2).setBounds(488, yl, zl, 20);
					campo.add(lblAltaEmp3).setBounds(488, yl+10, zl, 20);
					campo.add(lblListaRaya2).setBounds(676, yl, zl, 20);
					campo.add(lblListaRaya3).setBounds(676, yl+10, zl, 20);
					
					/* FILA 2 */
					campo.add(btnInasistencia).setBounds(x, y+=134, z, z);
					campo.add(btnPrestamo).setBounds(220, y, z, z);
					campo.add(btnPuesto).setBounds(405, y, z, z);
					campo.add(btnListaFirma).setBounds(594, y, z, z);
					
					campo.add(lblInasistencia2).setBounds(112, y+=20, zl, 20);
					campo.add(lblInasistencia3).setBounds(112, y+10, zl, 20);
					campo.add(lblPrestamo2).setBounds(300, y, zl, 20);
					campo.add(lblPuesto2).setBounds(488, y, zl, 20);
					campo.add(lblListaFirma2).setBounds(676, y, zl, 20);
					campo.add(lblListaFirma3).setBounds(676, y+10, zl, 20);
					
					/* FILA 3 */
					campo.add(btnCaja).setBounds(x, y+=115, z, z);
					campo.add(btnSueldo).setBounds(405, y, z, z);
					campo.add(btnListaComparacion).setBounds(594, y, z, z);
					
					campo.add(lblCaja2).setBounds(112, y+=20, zl, 20);
					campo.add(lblCaja3).setBounds(112, y+10, zl, 20);
					campo.add(lblSueldo2).setBounds(488, y, zl, 20);
					campo.add(lblListaComparacion2).setBounds(676, y, zl, 20);
					campo.add(lblListaComparacion3).setBounds(676, y+10, zl, 20);
					
					/* FILA 4 */
					campo.add(btnFsRH).setBounds(x, y+=115, z, z);

					campo.add(lblFsRH2).setBounds(112, y+=20, zl, 20);
					campo.add(lblFsRH3).setBounds(112, y+10, zl, 20);
					
					/* FILA 5 */
					campo.add(btnFsAux).setBounds(x, y+=115, z, z);		
					campo.add(btnChecador).setBounds(594, y+=115, z, z);
					
					campo.add(lblFsAux2).setBounds(112, y+=20, zl, 20);
					campo.add(lblFsAux3).setBounds(112, y+10, zl, 20);
					campo.add(lblListaChecador2).setBounds(676, y, zl, 20);
					
					campo.add(new JLabel("Folio:")).setBounds(880, 470, 80, 20);
					campo.add(new JLabel("Usuario:")).setBounds(880, 500, 80, 20);
					campo.add(new JLabel("Contraseña:")).setBounds(880, 530, 100, 20);
					
					campo.add(txtFolio).setBounds(960, 470, 220, 20);
					campo.add(txtUsuario).setBounds(960, 500, 220, 20);
					campo.add(txtContrasena).setBounds(960, 530, 220, 20);
				
					campo.add(btnBuscar).setBounds(1190, 470, 30, 20);
					campo.add(btnSalir).setBounds(1050, 560, 65, 20);
					campo.add(btnAceptar).setBounds(1115, 560, 65, 20);
					
					cont.add(campo);
					
					this.setSize(ancho,alto);
					
				break;
				case 720 : 
					
					campo.add(new JLabel(new ImageIcon("imagen/LogPrincipal.png"))).setBounds(870,0,400,218);
					 x = 30; y = 30; z = 65;
					 yl = 50; zl = 120;
							
					/* FILA 1 */
					campo.add(btnBanco).setBounds( x, y, z, z);
					campo.add(btnPExtras).setBounds(220, y, z, z);
					campo.add(btnAltaEmp).setBounds(405, y, z, z);
					campo.add(btnListaRaya).setBounds(594, y, z, z);
					
					campo.add(lblBanco).setBounds(112, yl, zl, 20);
					campo.add(lblPExtras1).setBounds(300, yl, zl, 20);
					campo.add(lblPExtras2).setBounds(300, yl+10, zl, 20);
					campo.add(lblAltaEmp2).setBounds(488, yl, zl, 20);
					campo.add(lblAltaEmp3).setBounds(488, yl+10, zl, 20);
					campo.add(lblListaRaya2).setBounds(676, yl, zl, 20);
					campo.add(lblListaRaya3).setBounds(676, yl+10, zl, 20);
					
					/* FILA 2 */
					campo.add(btnInasistencia).setBounds(x, y+=125, z, z);
					campo.add(btnPrestamo).setBounds(220, y, z, z);
					campo.add(btnPuesto).setBounds(405, y, z, z);
					campo.add(btnListaFirma).setBounds(594, y, z, z);
					
					campo.add(lblInasistencia2).setBounds(112, y+=20, zl, 20);
					campo.add(lblInasistencia3).setBounds(112, y+10, zl, 20);
					campo.add(lblPrestamo2).setBounds(300, y, zl, 20);
					campo.add(lblPuesto2).setBounds(488, y, zl, 20);
					campo.add(lblListaFirma2).setBounds(676, y, zl, 20);
					campo.add(lblListaFirma3).setBounds(676, y+10, zl, 20);
					
					/* FILA 3 */
					campo.add(btnCaja).setBounds(x, y+=105, z, z);
					campo.add(btnSueldo).setBounds(405, y, z, z);
					campo.add(btnListaComparacion).setBounds(594, y, z, z);
					
					campo.add(lblCaja2).setBounds(112, y+=20, zl, 20);
					campo.add(lblCaja3).setBounds(112, y+10, zl, 20);
					campo.add(lblSueldo2).setBounds(488, y, zl, 20);
					campo.add(lblListaComparacion2).setBounds(676, y, zl, 20);
					campo.add(lblListaComparacion3).setBounds(676, y+10, zl, 20);
					
					/* FILA 4 */
					campo.add(btnFsRH).setBounds(x, y+=105, z, z);

					campo.add(lblFsRH2).setBounds(112, y+=20, zl, 20);
					campo.add(lblFsRH3).setBounds(112, y+10, zl, 20);
					
					/* FILA 5 */
					campo.add(btnFsAux).setBounds(x, y+=105, z, z);	
					campo.add(btnChecador).setBounds(594, y+=105, z, z);
					
					campo.add(lblFsAux2).setBounds(112, y+=20, zl, 20);
					campo.add(lblFsAux3).setBounds(112, y+10, zl, 20);
					campo.add(lblListaChecador2).setBounds(676, y, zl, 20);
					
					campo.add(new JLabel("Folio:")).setBounds(880, 470, 80, 20);
					campo.add(new JLabel("Usuario:")).setBounds(880, 500, 80, 20);
					campo.add(new JLabel("Contraseña:")).setBounds(880, 530, 100, 20);
					
					campo.add(txtFolio).setBounds(960, 470, 220, 20);
					campo.add(txtUsuario).setBounds(960, 500, 220, 20);
					campo.add(txtContrasena).setBounds(960, 530, 220, 20);
				
					campo.add(btnBuscar).setBounds(1190, 470, 30, 20);
					campo.add(btnSalir).setBounds(1050, 560, 65, 20);
					campo.add(btnAceptar).setBounds(1115, 560, 65, 20);
					
					cont.add(campo);
					
					this.setSize(ancho,alto);

				break;
				case 600 : 
				
					campo.add(new JLabel(new ImageIcon("imagen/LogPrincipal.png"))).setBounds(870,0,400,218);
					 x = 10; y = 10; z = 65;
					 yl = 50; zl = 120;
							
					/* FILA 1 */
					campo.add(btnBanco).setBounds( x, y, z, z);
					campo.add(btnPExtras).setBounds(220, y, z, z);
					campo.add(btnAltaEmp).setBounds(405, y, z, z);
					campo.add(btnListaRaya).setBounds(594, y, z, z);
					
					campo.add(lblBanco).setBounds(112, yl, zl, 20);
					campo.add(lblPExtras1).setBounds(300, yl, zl, 20);
					campo.add(lblPExtras2).setBounds(300, yl+10, zl, 20);
					campo.add(lblAltaEmp2).setBounds(488, yl, zl, 20);
					campo.add(lblAltaEmp3).setBounds(488, yl+10, zl, 20);
					campo.add(lblListaRaya2).setBounds(676, yl, zl, 20);
					campo.add(lblListaRaya3).setBounds(676, yl+10, zl, 20);
					
					/* FILA 2 */
					campo.add(btnInasistencia).setBounds(x, y+=100, z, z);
					campo.add(btnPrestamo).setBounds(220, y, z, z);
					campo.add(btnPuesto).setBounds(405, y, z, z);
					campo.add(btnListaFirma).setBounds(594, y, z, z);
					
					campo.add(lblInasistencia2).setBounds(112, y+=20, zl, 20);
					campo.add(lblInasistencia3).setBounds(112, y+10, zl, 20);
					campo.add(lblPrestamo2).setBounds(300, y, zl, 20);
					campo.add(lblPuesto2).setBounds(488, y, zl, 20);
					campo.add(lblListaFirma2).setBounds(676, y, zl, 20);
					campo.add(lblListaFirma3).setBounds(676, y+10, zl, 20);
					
					/* FILA 3 */
					campo.add(btnCaja).setBounds(x, y+=80, z, z);
					campo.add(btnSueldo).setBounds(405, y, z, z);
					campo.add(btnListaComparacion).setBounds(594, y, z, z);
					
					campo.add(lblCaja2).setBounds(112, y+=20, zl, 20);
					campo.add(lblCaja3).setBounds(112, y+10, zl, 20);
					campo.add(lblSueldo2).setBounds(488, y, zl, 20);
					campo.add(lblListaComparacion2).setBounds(676, y, zl, 20);
					campo.add(lblListaComparacion3).setBounds(676, y+10, zl, 20);
					
					/* FILA 4 */
					campo.add(btnFsRH).setBounds(x, y+=80, z, z);

					campo.add(lblFsRH2).setBounds(112, y+=20, zl, 20);
					campo.add(lblFsRH3).setBounds(112, y+10, zl, 20);
					
					/* FILA 5 */
					campo.add(btnFsAux).setBounds(x, y+=80, z, z);	
					campo.add(btnChecador).setBounds(594, y+80, z, z);
		
					campo.add(lblFsAux2).setBounds(112, y+=20, zl, 20);
					campo.add(lblFsAux3).setBounds(112, y+10, zl, 20);
					campo.add(lblListaChecador2).setBounds(676, y, zl, 20);

					
					campo.add(new JLabel("Folio:")).setBounds(880, 370, 80, 20);
					campo.add(new JLabel("Usuario:")).setBounds(880, 400, 80, 20);
					campo.add(new JLabel("Contraseña:")).setBounds(880, 430, 100, 20);
					
					campo.add(txtFolio).setBounds(960, 370, 220, 20);
					campo.add(txtUsuario).setBounds(960, 400, 220, 20);
					campo.add(txtContrasena).setBounds(960, 430, 220, 20);
				
					campo.add(btnBuscar).setBounds(1190, 370, 30, 20);
					campo.add(btnSalir).setBounds(1050, 460, 65, 20);
					campo.add(btnAceptar).setBounds(1115, 460, 65, 20);
					
					cont.add(campo);
					
					this.setSize(ancho,alto);
					
				break;
				default :
					campo.add(new JLabel(new ImageIcon("imagen/LogPrincipal.png"))).setBounds(870,0,400,218);
					 x = 10; y = 10; z = 65;
					 yl = 50; zl = 120;
							
					/* FILA 1 */
					campo.add(btnBanco).setBounds( x, y, z, z);
					campo.add(btnPExtras).setBounds(220, y, z, z);
					campo.add(btnAltaEmp).setBounds(405, y, z, z);
					campo.add(btnListaRaya).setBounds(594, y, z, z);
					
					campo.add(lblBanco).setBounds(112, yl, zl, 20);
					campo.add(lblPExtras1).setBounds(300, yl, zl, 20);
					campo.add(lblPExtras2).setBounds(300, yl+10, zl, 20);
					campo.add(lblAltaEmp2).setBounds(488, yl, zl, 20);
					campo.add(lblAltaEmp3).setBounds(488, yl+10, zl, 20);
					campo.add(lblListaRaya2).setBounds(676, yl, zl, 20);
					campo.add(lblListaRaya3).setBounds(676, yl+10, zl, 20);
					
					/* FILA 2 */
					campo.add(btnInasistencia).setBounds(x, y+=100, z, z);
					campo.add(btnPrestamo).setBounds(220, y, z, z);
					campo.add(btnPuesto).setBounds(405, y, z, z);
					campo.add(btnListaFirma).setBounds(594, y, z, z);
					
					campo.add(lblInasistencia2).setBounds(112, y+=20, zl, 20);
					campo.add(lblInasistencia3).setBounds(112, y+10, zl, 20);
					campo.add(lblPrestamo2).setBounds(300, y, zl, 20);
					campo.add(lblPuesto2).setBounds(488, y, zl, 20);
					campo.add(lblListaFirma2).setBounds(676, y, zl, 20);
					campo.add(lblListaFirma3).setBounds(676, y+10, zl, 20);
					
					/* FILA 3 */
					campo.add(btnCaja).setBounds(x, y+=80, z, z);
					campo.add(btnSueldo).setBounds(405, y, z, z);
					campo.add(btnListaComparacion).setBounds(594, y, z, z);
					
					campo.add(lblCaja2).setBounds(112, y+=20, zl, 20);
					campo.add(lblCaja3).setBounds(112, y+10, zl, 20);
					campo.add(lblSueldo2).setBounds(488, y, zl, 20);
					campo.add(lblListaComparacion2).setBounds(676, y, zl, 20);
					campo.add(lblListaComparacion3).setBounds(676, y+10, zl, 20);
					
					/* FILA 4 */
					campo.add(btnFsRH).setBounds(x, y+=80, z, z);

					campo.add(lblFsRH2).setBounds(112, y+=20, zl, 20);
					campo.add(lblFsRH3).setBounds(112, y+10, zl, 20);
					
					/* FILA 5 */
					campo.add(btnFsAux).setBounds(x, y+=80, z, z);		

					campo.add(lblFsAux2).setBounds(112, y+=20, zl, 20);
					campo.add(lblFsAux3).setBounds(112, y+10, zl, 20);

					
					campo.add(new JLabel("Folio:")).setBounds(880, 370, 80, 20);
					campo.add(new JLabel("Usuario:")).setBounds(880, 400, 80, 20);
					campo.add(new JLabel("Contraseña:")).setBounds(880, 430, 100, 20);
					
					campo.add(txtFolio).setBounds(960, 370, 220, 20);
					campo.add(txtUsuario).setBounds(960, 400, 220, 20);
					campo.add(txtContrasena).setBounds(960, 430, 220, 20);
				
					campo.add(btnBuscar).setBounds(1190, 370, 30, 20);
					campo.add(btnSalir).setBounds(1050, 460, 65, 20);
					campo.add(btnAceptar).setBounds(1115, 460, 65, 20);
					
					cont.add(campo);
					
					this.setSize(ancho,alto);
					
				break;
			}
		}
		
		if(ancho == 1024){
			ImageIcon tmpIconAux = new ImageIcon("imagen/LogPrincipal.png");
			campo.add(new JLabel(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT)))).setBounds(680,0,400,218);
			
//			getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)
			int x = 10, y = 10, z = 65;
			int yl = 50, zl = 120;
					
			/* FILA 1 */
			campo.add(btnBanco).setBounds( x, y, z, z);
			campo.add(btnPExtras).setBounds(220, y, z, z);
			campo.add(btnAltaEmp).setBounds(405, y, z, z);
			campo.add(btnListaRaya).setBounds(594, y, z, z);
			
			campo.add(lblBanco).setBounds(112, yl, zl, 20);
			campo.add(lblPExtras1).setBounds(300, yl, zl, 20);
			campo.add(lblPExtras2).setBounds(300, yl+10, zl, 20);
			campo.add(lblAltaEmp2).setBounds(488, yl, zl, 20);
			campo.add(lblAltaEmp3).setBounds(488, yl+10, zl, 20);
			campo.add(lblListaRaya2).setBounds(676, yl, zl, 20);
			campo.add(lblListaRaya3).setBounds(676, yl+10, zl, 20);
			
			/* FILA 2 */
			campo.add(btnInasistencia).setBounds(x, y+=100, z, z);
			campo.add(btnPrestamo).setBounds(220, y, z, z);
			campo.add(btnPuesto).setBounds(405, y, z, z);
			campo.add(btnListaFirma).setBounds(594, y, z, z);
			
			campo.add(lblInasistencia2).setBounds(112, y+=20, zl, 20);
			campo.add(lblInasistencia3).setBounds(112, y+10, zl, 20);
			campo.add(lblPrestamo2).setBounds(300, y, zl, 20);
			campo.add(lblPuesto2).setBounds(488, y, zl, 20);
			campo.add(lblListaFirma2).setBounds(676, y, zl, 20);
			campo.add(lblListaFirma3).setBounds(676, y+10, zl, 20);
			
			/* FILA 3 */
			campo.add(btnCaja).setBounds(x, y+=80, z, z);
			campo.add(btnSueldo).setBounds(405, y, z, z);
			campo.add(btnListaComparacion).setBounds(594, y, z, z);
			
			campo.add(lblCaja2).setBounds(112, y+=20, zl, 20);
			campo.add(lblCaja3).setBounds(112, y+10, zl, 20);
			campo.add(lblSueldo2).setBounds(488, y, zl, 20);
			campo.add(lblListaComparacion2).setBounds(676, y, zl, 20);
			campo.add(lblListaComparacion3).setBounds(676, y+10, zl, 20);
			
			/* FILA 4 */
			campo.add(btnFsRH).setBounds(x, y+=80, z, z);

			campo.add(lblFsRH2).setBounds(112, y+=20, zl, 20);
			campo.add(lblFsRH3).setBounds(112, y+10, zl, 20);
			
			/* FILA 5 */
			campo.add(btnFsAux).setBounds(x, y+=80, z, z);		

			campo.add(lblFsAux2).setBounds(112, y+=20, zl, 20);
			campo.add(lblFsAux3).setBounds(112, y+10, zl, 20);

			
			campo.add(new JLabel("Folio:")).setBounds(580, 470, 80, 20);
			campo.add(new JLabel("Usuario:")).setBounds(580, 500, 80, 20);
			campo.add(new JLabel("Contraseña:")).setBounds(580, 530, 100, 20);
			
			campo.add(txtFolio).setBounds(660, 470, 220, 20);
			campo.add(txtUsuario).setBounds(660, 500, 220, 20);
			campo.add(txtContrasena).setBounds(660, 530, 220, 20);
		
			campo.add(btnBuscar).setBounds(890, 470, 30, 20);
			campo.add(btnSalir).setBounds(750, 560, 65, 20);
			campo.add(btnAceptar).setBounds(815, 560, 65, 20);
			
			cont.add(campo);
			
			this.setSize(ancho,alto);
			
		}
		if(ancho == 800){
			ImageIcon tmpIconAux = new ImageIcon("imagen/LogPrincipal.png");
			campo.add(new JLabel(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT)))).setBounds(500,0,400,218);
			
//			getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)
			int x = 10, y = 10, z = 55;
			int yl = 30, zl = 120;
					
			/* FILA 1 */
			campo.add(btnBanco).setBounds( x, y, z, z);
			campo.add(btnPExtras).setBounds(180, y, z, z);
			campo.add(btnAltaEmp).setBounds(350, y, z, z);
			campo.add(btnListaRaya).setBounds(490, y, z, z);
			
			campo.add(lblBanco).setBounds(70, yl, zl, 20);
			campo.add(lblPExtras1).setBounds(248, yl, zl, 20);
			campo.add(lblPExtras2).setBounds(248, yl+10, zl, 20);
			campo.add(lblAltaEmp2).setBounds(418, yl, zl, 20);
			campo.add(lblAltaEmp3).setBounds(418, yl+10, zl, 20);
			campo.add(lblListaRaya2).setBounds(560, yl, zl, 20);
			campo.add(lblListaRaya3).setBounds(560, yl+10, zl, 20);
			
			/* FILA 2 */
			campo.add(btnInasistencia).setBounds(x, y+=70, z, z);
			campo.add(btnPrestamo).setBounds(180, y, z, z);
			campo.add(btnPuesto).setBounds(350, y, z, z);
			campo.add(btnListaFirma).setBounds(490, y, z, z);
			
			campo.add(lblInasistencia2).setBounds(70, y+=20, zl, 20);
			campo.add(lblInasistencia3).setBounds(70, y+10, zl, 20);
			campo.add(lblPrestamo2).setBounds(248, y, zl, 20);
			campo.add(lblPuesto2).setBounds(418, y, zl, 20);
			campo.add(lblListaFirma2).setBounds(560, y, zl, 20);
			campo.add(lblListaFirma3).setBounds(560, y+10, zl, 20);
			
			/* FILA 3 */
			campo.add(btnCaja).setBounds(x, y+=50, z, z);
			campo.add(btnSueldo).setBounds(350, y, z, z);
			campo.add(btnListaComparacion).setBounds(490, y, z, z);
			
			campo.add(lblCaja2).setBounds(70, y+=20, zl, 20);
			campo.add(lblCaja3).setBounds(70, y+10, zl, 20);
			campo.add(lblSueldo2).setBounds(418, y, zl, 20);
			campo.add(lblListaComparacion2).setBounds(560, y, zl, 20);
			campo.add(lblListaComparacion3).setBounds(560, y+10, zl, 20);
			
			/* FILA 4 */
			campo.add(btnFsRH).setBounds(x, y+=50, z, z);

			campo.add(lblFsRH2).setBounds(70, y+=20, zl, 20);
			campo.add(lblFsRH3).setBounds(70, y+10, zl, 20);
			
			/* FILA 5 */
			campo.add(btnFsAux).setBounds(x, y+=50, z, z);		

			campo.add(lblFsAux2).setBounds(70, y+=20, zl, 20);
			campo.add(lblFsAux3).setBounds(70, y+10, zl, 20);

			
			campo.add(new JLabel("Folio:")).setBounds(380, 340, 80, 20);
			campo.add(new JLabel("Usuario:")).setBounds(380, 370, 80, 20);
			campo.add(new JLabel("Contraseña:")).setBounds(380, 400, 100, 20);
			
			campo.add(txtFolio).setBounds(460, 340, 220, 20);
			campo.add(txtUsuario).setBounds(460, 370, 220, 20);
			campo.add(txtContrasena).setBounds(460, 400, 220, 20);
		
			campo.add(btnBuscar).setBounds(690, 340, 30, 20);
			campo.add(btnSalir).setBounds(550, 430, 65, 20);
			campo.add(btnAceptar).setBounds(615, 430, 65, 20);
			
			cont.add(campo);
			
			this.setSize(ancho,alto);
			
		}
		lblLogo.addMouseListener ( new  MouseAdapter ()  
		{  
			public void mouseReleased (MouseEvent e)  
			{  
	    		new Cat_Checador().setVisible(true);
	    	}  
		});
	}
	
	KeyListener enterIn = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnAceptar.doClick();
			}
		}
	};
	
	KeyListener enterBuscar = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
			}
		}
	};
	
	ActionListener opBuscar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().length()!=0){
				Obj_Usuario user = new Obj_Usuario().buscar(Integer.parseInt(txtFolio.getText()));
				if(user.getFolio() != 0){
					txtFolio.setEditable(false);
					txtUsuario.setText(user.getNombre_completo());
					txtContrasena.requestFocus(true);
					btnAceptar.setEnabled(true);
				}else{
					JOptionPane.showMessageDialog(null, "El usuario no existe","Aviso",JOptionPane.WARNING_MESSAGE);
					txtFolio.requestFocus(true);
					return;
				}
			}else{
				JOptionPane.showMessageDialog(null, "Ingrese el Folio del Usuario","Aviso",JOptionPane.WARNING_MESSAGE);
				txtFolio.requestFocus(true);
				return;
			}
		}		
	};
	
	ActionListener opSalir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
						
			txtFolio.setText("");
			txtUsuario.setText("");
			txtContrasena.setText("");			
			btnAceptar.setEnabled(false);
			btnBuscar.setEnabled(true);

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
			btnListaComparacion.setEnabled(false);
			
		   	/* AUDITORIA */	
			    Reporte_de_Movimientos_Operados.setEnabled(false); 
				Captura_Cortes.setEnabled(false);
			
			/* CATALOGO */
			    Catalogo_Departamento.setEnabled(false);
				Catalogo_Empleado.setEnabled(false);
				Catalogo_Establecimiento.setEnabled(false);
				Catalogo_Puesto.setEnabled(false);
				Catalogo_Rango_Prestamo.setEnabled(false);
				Catalogo_Sueldo.setEnabled(false);
				Catalogo_Tipo_Banco.setEnabled(false);
				Catalogo_Turno.setEnabled(false);
				
			/* CONFIGURACION */
				Configuracion_Asistencia_Puntualidad.setEnabled(false);
				Configuracion_ConexionBD.setEnabled(false);
				Configuracion_Bono.setEnabled(false);
				Configuracion_Denominaciones.setEnabled(false);
				Configuracion_Divisas.setEnabled(false);
				Configuracion_Mantenimiento.setEnabled(false);
				Configuracion_Sistema.setEnabled(false);
				Configuracion_Usuario.setEnabled(false);
		
			/* CONTABILIDAD */			
				Importar_Auxiliar.setEnabled(false);
				Importar_Cheques.setEnabled(false);
				Importar_Consiliacion.setEnabled(false);
				Importar_Voucher.setEnabled(false);
				Egresos_Reporte_de_apartados_y_abonos.setEnabled(false);

			/* CUADRANTES 
			*		ALIMENTACION */
				Cuadrantes_Catalogo_Actividades.setEnabled(false);
				Cuadrantes_Alimentacion_Actividades_Cuadrantes.setEnabled(false);
				Cuadrantes_Catalogo_Telefono.setEnabled(false);
				Cuadrantes_Alimentacion_Cuadrante.setEnabled(false);
				Cuadrantes_Alimentacion_Empleados_Cuadrantes.setEnabled(false);
				Cuadrantes_Alimentacion_Asignacion_Actividades_Nivel_Jerarquico.setEnabled(false);
				
			/* CUADRANTES 
			*		CATALOGO */
				Cuadrantes_Catalogo_Atributos.setEnabled(false);
				Cuadrantes_Catalogo_Equipo_Trabajo.setEnabled(false);
				Cuadrantes_Catalogo_Jefatura.setEnabled(false);
				Cuadrantes_Catalogo_Nivel_Critico.setEnabled(false);
				Cuadrantes_Catalogo_Nivel_Jerarquico.setEnabled(false);
				Cuadrantes_Catalogo_Respuesta_Multiple.setEnabled(false);
				Cuadrantes_Catalogo_Ponderacion.setEnabled(false);
				Cuadrantes_Catalogo_Respuesta.setEnabled(false);
			/* CUADRANTES 
			*		REPORTE */
				Impresion_Cuadrante_Personal.setEnabled(false);
				Cuadrantes_Reportes_Directivo.setEnabled(false);
				Cuadrantes_Reportes_Jefatura.setEnabled(false);
				Cuadrantes_Reportes_Usuario.setEnabled(false);

			/* LISTA DE RAYA 
			* 		ALIMENTACION */
				Alimentacion_Bancos.setEnabled(false);
				Alimentacion_Cajeras_de_Fuente_Sodas.setEnabled(false);
				Alimentacion_Captura_Totales_Nomina.setEnabled(false);
				Alimentacion_Deducciones_Asistencia.setEnabled(false);
				Alimentacion_Diferencia_Cortes.setEnabled(false);
				Alimentacion_Fuente_Sodas_auxf.setEnabled(false);
				Alimentacion_FS_auxf_seleccionable.setEnabled(false);
				Alimentacion_Fuente_Sodas_rh.setEnabled(false);
				Alimentacion_FS_dh_seleccionable.setEnabled(false);
				Alimentacion_Percepciones_Extra.setEnabled(false);
				Alimentacion_Prestamos.setEnabled(false);
			/* LISTA DE RAYA 
			* 		AUTORIZACIONES */
				Autorizacion_Auditoria.setEnabled(false);
				Autorizacion_Finanzas.setEnabled(false);
			/* LISTA DE RAYA 
			* 		CHECADORES */
				Asignacion_Horario_Temporada.setEnabled(false);
				Checador_Menu.setEnabled(true);
				Dias_Inhabiles.setEnabled(false);
				Generacion_Gafetes_Empleados.setEnabled(false);
				Horarios.setEnabled(false);
				Mensajes_Personales.setEnabled(false);
				Permisos_Empleados.setEnabled(false);
				Reportes_del_Dia.setEnabled(false);
				Reportes_Checador_Gral.setEnabled(false);
								
			/* LISTA DE RAYA 
			* 		COMPARACIONES */
				Comparaciones_Listas_Fuente_Sodas.setEnabled(false);
				Comparaciones_Listas_Raya.setEnabled(false);
			/* LISTA DE RAYA 
			* 		DEPARTAMENTO DE CORTES */
					Departamento_Cortes_Alimentacion.setEnabled(false);
			/* LISTA DE RAYA 
			* 		REPORTES */
				Reporte_Deducciones_Inasistencia.setEnabled(false);
				Reporte_Bancos.setEnabled(false);
				Reporte_Fuente_Sodas.setEnabled(false);
				Reporte_Lista_Firma.setEnabled(false);
				Reporte_Lista_Raya.setEnabled(false);
				Reporte_Plantilla_Activa.setEnabled(false);
				Reporte_Prestamos.setEnabled(false);
				
			txtFolio.setEditable(true);
			txtContrasena.setEditable(true);
			txtFolio.requestFocus();
						
		}
	};
}
