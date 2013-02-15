package frames;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import objetos.Obj_MD5;
import objetos.Obj_Usuario;

import catalogos.Cat_Bancos;
import catalogos.Cat_Comprobar_Fuente_Sodas_RH;
import catalogos.Cat_Deduccion_Inasistencia;
import catalogos.Cat_Empleado;
import catalogos.Cat_Filtro_Diferiencia_Cortes;
import catalogos.Cat_Filtro_Fue_Soda_Auxf;
import catalogos.Cat_Filtro_Fue_Soda_Rh;
import catalogos.Cat_Filtro_Prestamo;
import catalogos.Cat_Lista_Deudores_Prestamo;
import catalogos.Cat_Lista_Pago;
import catalogos.Cat_Lista_Raya;
import catalogos.Cat_Percepciones_Extra;
import catalogos.Cat_Puesto;
import catalogos.Cat_Sueldo;

@SuppressWarnings("serial")
public class Frm_Principal2 extends JDialog{
		
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
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
	JButton btnEntras = new JButton("Entrar");
	
	private Dimension dim; 
	public Frm_Principal2()	{

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
		campo.add(btnSalir).setBounds(1100, 580, 80, 20);
		campo.add(btnEntras).setBounds(1200, 580, 80, 20);
		
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
		
		btnEntras.addActionListener(opEntrar);
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
	
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	public JComponent getBase(){
		return campo;
	}
	ActionListener opEntrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			Obj_Usuario usuario = new Obj_Usuario();
			
			System.out.println(txtFolio.getText());
			System.out.println(usuario.getFolio());
			System.out.println(txtContrasena.getText());
			System.out.println(usuario.getContrasena());
			
			if(txtFolio.getText()== usuario.getFolio()+""){
				
				if(txtContrasena.getText()==usuario.getContrasena()){
					System.out.println("todo bien");
				}else{
					System.out.println("fallo");
				}
				
			}else{
				JOptionPane.showMessageDialog(null,"Verifique Usuario y Contraseña", "Error Datos Incorrectos", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}
//			System.out.println(cadena1);
			
			switch(Integer.parseInt(txtFolio.getText())){
				case 1:logDH();break;
				case 2:logAuxF();break;
				case 3:logAdmin();break;
			}
		}
	};
	
	ActionListener opSalir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
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
			new Cat_Lista_Raya().setVisible(true);
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
	
	public void logAuxF(){
		btnBanco.setEnabled(true);
		btnInasistencia.setEnabled(true);
		btnCaja.setEnabled(true);
		btnFsRH.setEnabled(false);
		btnFsAux.setEnabled(true);
		btnPExtras.setEnabled(true);
		btnPrestamo.setEnabled(false);
		btnAltaEmp.setEnabled(true);
		btnPuesto.setEnabled(true);
		btnSueldo.setEnabled(true);
		btnListaRaya.setEnabled(true);
		btnListaFirma.setEnabled(true);
		btnListaPrestamo.setEnabled(true);
		btnListaComparacion.setEnabled(false);
		btnRevicion.setEnabled(false);	
	}
}
