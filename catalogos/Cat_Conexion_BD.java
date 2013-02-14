package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import objetos.Obj_Conexion_BD;

@SuppressWarnings("serial")
public class Cat_Conexion_BD extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtIp = new JTextField();
	JTextField txtPuerto = new JTextField();
	JTextField txtNombreBD = new JTextField();
	JTextField txtUsuario = new JTextField();
	JPasswordField txtContrasena = new JPasswordField();
	
	JButton btnEditar = new JButton("Editar");
	JButton btnSalir = new JButton("Salir");
	JButton btnGuardar = new JButton("Guardar");
	
	public Cat_Conexion_BD(){
		this.setTitle("Configuracion de Base de Datos");
		int x = 80, y=30, ancho=110;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("imagen/Database.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Configuracion de Base de Datos"));
			
//		txtIp.setDocument(new JTextFieldLimit(9));
//		txtNombre_Completo.setDocument(new JTextFieldLimit(53));
//		txtContrasena.setDocument(new JTextFieldLimit(33));
//		txtContrasena1.setDocument(new JTextFieldLimit(33));
		
		
		panel.add(new JLabel("Puerto:")).setBounds(x,y,ancho,20);
		panel.add(txtPuerto).setBounds(x+ancho-30,y,50,20);
		
		panel.add(new JLabel("Dirección IPv4:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtIp).setBounds(x+ancho-30,y,ancho*2,20);
		
		panel.add(new JLabel("Nombre BD:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombreBD).setBounds(x+ancho-30,y,ancho*2,20);
		
		panel.add(new JLabel("Usuario:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtUsuario).setBounds(x+ancho-30,y,ancho*2,20);
		
		panel.add(new JLabel("Contraseña:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtContrasena).setBounds(x+ancho-30,y,ancho*2,20);
		
		panel.add(btnSalir).setBounds(x,y+=30,ancho-20,20);
		panel.add(btnEditar).setBounds((x*3)-23,y,ancho-20,20);
		panel.add(btnGuardar).setBounds((x*4)+30,y,ancho-20,20);
		
		btnEditar.addActionListener(editar);
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(salir);
		
		txtPuerto.setText("1433");
		File archivo = new File(System.getProperty("user.dir")+"\\Config\\config");
		
		if(archivo.exists()){
			Obj_Conexion_BD config = new Obj_Conexion_BD().buscar();
			
			txtIp.setText(config.getDireccionIPV4());
			txtNombreBD.setText(config.getNombreBD());
			txtUsuario.setText(config.getUsuario());
			txtContrasena.setText(config.getContrasena());
		
		}		
		txtPuerto.setEditable(false);
		panelEnabledFalse();
		
		cont.add(panel);
		this.setSize(500,250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	ActionListener guardar = new ActionListener(){
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e){
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				Obj_Conexion_BD con = new Obj_Conexion_BD();
				
				con.setDireccionIPV4(txtIp.getText());
				con.setNombreBD(txtNombreBD.getText());
				con.setUsuario(txtUsuario.getText());
				con.setContrasena(txtContrasena.getText());
				
				con.guardar();
				
				panelEnabledFalse();
				JOptionPane.showMessageDialog(null,"El registró se almacenó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						
			}
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
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
	
	@SuppressWarnings("deprecation")
	private String validaCampos(){
		String error="";
		
		if(txtIp.getText().equals(""))error+= "Direción IPv4\n";
		if(txtNombreBD.getText().equals(""))error+= "Nombre de la BD\n";
		if(txtContrasena.getText().equals(""))error+= "Nombre de Usuario\n";
		if(txtUsuario.getText().equals(""))error+= "Contraseña\n";
				
		return error;
	}
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	public void panelEnabledFalse(){	
		txtIp.setEditable(false);
		txtNombreBD.setEditable(false);
		txtUsuario.setEditable(false);
		txtContrasena.setEditable(false);
	}		
	
	public void panelEnabledTrue(){	
		txtIp.setEditable(true);
		txtNombreBD.setEditable(true);
		txtUsuario.setEditable(true);
		txtContrasena.setEditable(true);
	}
	
}
