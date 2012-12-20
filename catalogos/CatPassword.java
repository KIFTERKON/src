package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import frames.Principal;

import objetos.Obj_MD5;
import objetos.Obj_Usuario;

@SuppressWarnings("serial")
public class CatPassword extends JDialog {
	Container cont = getContentPane();
	
	JLayeredPane campos  = new JLayeredPane();
	JTextField txtFolio = new JTextField("");
	JTextField txtNombre = new JTextField("");
	JPasswordField pxpClave = new JPasswordField("");
	
	JButton btnAceptar = new JButton("Entrar");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	public CatPassword() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("imagen/Key.png"));
		
		this.setTitle("Password Empleado");
		
		int x = 40, y=30, ancho=140;
		campos.setBorder(BorderFactory.createTitledBorder("Entra al Sistema"));
				
		campos.add(new JLabel("Usuario Id:")).setBounds(x,y,ancho,20);
		campos.add(txtFolio).setBounds(x+ancho-60,y,ancho,20);
		campos.add(btnBuscar).setBounds(x+ancho+ancho,y,40,20);
		
		campos.add(new JLabel("Nombre:")).setBounds(x,y+=25,ancho,20);
		campos.add(txtNombre).setBounds(x+ancho-60,y,ancho*2,20);
		
		campos.add(new JLabel("Contraseña:")).setBounds(x,y+=25,ancho,20);
		campos.add(pxpClave).setBounds(x+ancho-60,y,ancho*2,20);
	
		campos.add(btnDeshacer).setBounds(x+ancho-60,y+=25,90,20);
		campos.add(btnSalir).setBounds(x+ancho+40,y,85,20);
		campos.add(btnAceptar).setBounds(x+ancho+135,y,85,20);
		
		txtFolio.addKeyListener(validaNumerico);
		txtFolio.addKeyListener(validaBuscar);
		btnBuscar.addActionListener(buscar);
		btnAceptar.addActionListener(aceptarAction);
		btnSalir.addActionListener(salir);
		btnDeshacer.addActionListener(deshacer);
		
		pxpClave.addKeyListener(validaGuardar);
				
		cont.add(campos);
		
		this.setModal(true);
		this.setSize(450,180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(deshacer !=null ){
				int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere deshacer?", "Confirmacion", JOptionPane.YES_NO_OPTION);
				if(respuesta!=0)
				return;
			}
			Limpiar();
		}	
	};
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();			
		}	
	};
	
	ActionListener aceptarAction = new ActionListener(){
		@SuppressWarnings({ "static-access", "deprecation" })
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				txtFolio.setText("");
				txtNombre.setText("");
				pxpClave.setText("");
				JOptionPane.showMessageDialog(null,"Ingrese un folio para realizar la función", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Usuario usuario = new Obj_Usuario().buscar(Integer.parseInt(txtFolio.getText()));
				if(usuario.getFolio() !=0){
					if(pxpClave.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Inserte la contraseña.", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}else{
						Obj_MD5 algoritmo = new Obj_MD5();
						String cadena = algoritmo.cryptMD5(pxpClave.getText(),"izagar");
						String clave= usuario.getContrasena();
						
						System.out.println("pxp="+cadena+"  bd="+clave);
						System.out.println(cadena.length()+" "+clave.length());
						if(!clave.equals(cadena)){
							txtFolio.setText("");
							txtNombre.setText("");
							pxpClave.setText("");
							JOptionPane.showMessageDialog(null,"La contraseñas no es valida", "Aviso", JOptionPane.WARNING_MESSAGE);
							return;						
						}else{
							dispose();
							new Principal().setVisible(true);	
						}
					}

				}else{
					JOptionPane.showMessageDialog(null,"El Usuario no existe", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
									
		}	
	};
			
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(!txtFolio.getText().equals("")){
				Obj_Usuario usuario = new Obj_Usuario();
				usuario = usuario.buscar(Integer.parseInt(txtFolio.getText()));	
				
				if(usuario.getFolio() != 0){	
					txtNombre.setText(usuario.getNombre_completo());
					pxpClave.requestFocus();
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
	
	public void Limpiar() {
		txtFolio			   .setText("");
		txtNombre			   .setText("");
		pxpClave			   .setText("");
				
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
	
	KeyListener validaNumerico = new KeyListener() {
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
	
	
}
