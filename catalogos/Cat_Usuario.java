package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import objetos.JTextFieldLimit;
import objetos.Obj_Empleado;
import objetos.Obj_MD5;
import objetos.Obj_Permiso;
import objetos.Obj_Usuario;

@SuppressWarnings("serial")
public class Cat_Usuario extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	JPasswordField txtContrasena = new JPasswordField();
	JPasswordField txtContrasena1 = new JPasswordField();
	
	String permiso[]=new Obj_Permiso().Combo_Permiso();
	@SuppressWarnings("unchecked")
	JComboBox cmbPermisos = new JComboBox(permiso);
	
	String status[]= {"Vigente","Vacaciones","Baja"};
	@SuppressWarnings("unchecked")
	JComboBox cmbStatus = new JComboBox(status);
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnEditar = new JButton("Editar");
	JButton btnSalir = new JButton("Salir");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnDeshacer = new JButton("Deshacer");
	
	public Cat_Usuario(){
		super("Agregar Usuario");
		int x = 80, y=30, ancho=110;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("imagen/Person.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Agregar Usuario"));
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtNombre_Completo.setDocument(new JTextFieldLimit(53));
		txtContrasena.setDocument(new JTextFieldLimit(33));
		txtContrasena1.setDocument(new JTextFieldLimit(33));
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(x+ancho,y,ancho+15,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+16,y,32,20);
		panel.add(btnNuevo).setBounds(x+ancho+ancho+50,y,ancho-20,20);
		
		panel.add(new JLabel("Nombre Completo:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre_Completo).setBounds(x+ancho,y,ancho*2+30,20);
		
		panel.add(new JLabel("Contraseña:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtContrasena).setBounds(x+ancho,y,ancho*2+30,20);
		
		panel.add(new JLabel("Confirmar:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtContrasena1).setBounds(x+ancho,y,ancho*2+30,20);
		
		panel.add(new JLabel("Permiso:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbPermisos).setBounds(x+ancho,y,ancho*2+30,20);
		
		panel.add(new JLabel("Status:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbStatus).setBounds(x+ancho,y,ancho*2+30,20);
		
		panel.add(btnSalir).setBounds(x,y+=25,ancho-20,20);
		panel.add(btnDeshacer).setBounds((x*2)+10,y,ancho-20,20);
		panel.add(btnEditar).setBounds((x*3)+20,y,ancho-20,20);
		panel.add(btnGuardar).setBounds((x*4)+30,y,ancho-20,20);
		
		btnEditar.setEnabled(false);
		panelEnabledFalse();
		txtFolio.setEnabled(true);
		
		btnEditar.addActionListener(editar);
		btnBuscar.addActionListener(buscar);
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(salir);
		btnNuevo.addActionListener(nuevo);
		btnDeshacer.addActionListener(deshacer);
		txtFolio.addKeyListener(numerico_action);
		txtFolio.addKeyListener(buscar_action);
		
		cont.add(panel);
		this.setSize(500,250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	ActionListener buscar = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Usuario usuario = new Obj_Usuario();
				usuario = usuario.buscar(Integer.parseInt(txtFolio.getText()));
				
				if(usuario.getFolio() != 0){
					txtFolio.setText(usuario.getFolio()+"");
					txtNombre_Completo.setText(usuario.getNombre_completo().trim());
					txtContrasena.setText(usuario.getContrasena().substring(0,15)+"");
					txtContrasena1.setText(usuario.getContrasena().substring(0,15)+"");
					cmbPermisos.setSelectedIndex(usuario.getPermiso_id()-1);
					cmbStatus.setSelectedIndex(usuario.getStatus()-1);		
					btnEditar.setEnabled(true);
					btnNuevo.setEnabled(false);
					btnGuardar.setEnabled(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
					panelEnabledFalse();
					txtFolio.setEnabled(true);
					panelLimpiar();
					return;
				}
			
			}
		}
	};
	
	ActionListener guardar = new ActionListener(){
		@SuppressWarnings({ "static-access", "deprecation" })
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Usuario usuario = new Obj_Usuario().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(usuario.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							
							usuario.setNombre_completo(txtNombre_Completo.getText());
							usuario.setPermiso_id(cmbPermisos.getSelectedIndex()+1);
							usuario.setStatus(cmbStatus.getSelectedIndex()+1);
							usuario.setFecha_alta(new Date().toString());
							usuario.actualizar(Integer.parseInt(txtFolio.getText()));	
							panelLimpiar();
							btnEditar.setEnabled(false);
							panelEnabledFalse();
							txtFolio.setEnabled(true);
							txtFolio.requestFocus();
							JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
							
						}
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						Obj_MD5 algoritmo = new Obj_MD5();
						String cadena1 = algoritmo.cryptMD5(txtContrasena.getText(),"izagar");
						String cadena2 = algoritmo.cryptMD5(txtContrasena1.getText(),"izagar");						
						if(!cadena1.equals(cadena2)){
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;							
						}else{
							usuario.setFolio(Integer.parseInt(txtFolio.getText()));
							usuario.setNombre_completo(txtNombre_Completo.getText());
							usuario.setContrasena(cadena1);
							usuario.setPermiso_id(cmbPermisos.getSelectedIndex()+1);
							usuario.setStatus(cmbStatus.getSelectedIndex()+1);
							usuario.guardar();	
							panelLimpiar();
							btnEditar.setEnabled(false);
							panelEnabledFalse();
							txtFolio.setEnabled(true);	
							txtFolio.requestFocus();
							JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));

						}
					}
				}
			}	
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
			if(empleado.getFolio() != 0){
				panelEnabledTrue();
				txtFolio.setEnabled(false);
				btnEditar.setEnabled(false);
				btnGuardar.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(null,"El registró que desea actualizar no existe","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
				return;
			}
		}		
	};
	
	KeyListener buscar_action = new KeyListener() {
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
	
	public void panelLimpiar(){
		txtFolio.setText("");
		txtNombre_Completo.setText("");
		txtContrasena.setText("");
		txtContrasena1.setText("");
		cmbPermisos.setSelectedIndex(0);
		cmbStatus.setSelectedIndex(0);
	}
	
	@SuppressWarnings("deprecation")
	private String validaCampos(){
		String error="";
		
		if(txtFolio.getText().equals(""))error+= "Folio\n";
		if(txtNombre_Completo.getText().equals(""))error+= "Numero Checador\n";
		if(txtContrasena.getText().equals(""))error+= "Contraseña\n";
		if(txtContrasena1.getText().equals(""))error+= "Confirmar Contraseña\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			Obj_Usuario usuario = new Obj_Usuario().buscarMaximo();
			txtFolio.setText(usuario.getFolio()+1+"");
			panelEnabledTrue();
			btnEditar.setEnabled(true);
			txtFolio.setEnabled(false);
			txtNombre_Completo.requestFocus();
			
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEnabled(true);
			txtFolio.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(false);
		}
	};
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	public void panelEnabledFalse(){	
		txtFolio.setEnabled(false);
		txtNombre_Completo.setEnabled(false);
		txtContrasena.setEnabled(false);
		txtContrasena1.setEnabled(false);	
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEnabled(true);
		txtNombre_Completo.setEnabled(true);
		txtContrasena.setEnabled(true);
		txtContrasena1.setEnabled(true);	
	}
	
	public static void main(String args[]){
		try{
			// 6677914218
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//Obj_Usuario usuario = new Obj_Usuario().buscarMaximo();
			new Cat_Usuario().setVisible(true);
			//new Principal().setVisible(true);
			
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
