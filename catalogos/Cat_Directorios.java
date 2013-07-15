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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objetos.Obj_Directorios;
import objetos.Obj_Empleado;

@SuppressWarnings("serial")
public class Cat_Directorios extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	JTextField txtTelefono = new JTextField();
	
	JButton btnGuardar = new JButton("Guardar");
//	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnSalir = new JButton("Salir");
	JButton btnModificar = new JButton("Modificar");
	
	public Cat_Directorios(int folio){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dial.png"));
		this.setTitle("Número de Teléfono");
		
		int x=30, y=25, z=80;
		
		System.out.println(folio);
		
		panel.setBorder(BorderFactory.createTitledBorder("Número de Teléfono"));
		
		this.panel.add(new JLabel("Folio:")).setBounds(x, y, z, 20);
		this.panel.add(txtFolio).setBounds(x+=90, y, z, 20);
		
//		this.panel.add(btnModificar).setBounds(x+z+60,y,z,20);
		
		this.panel.add(new JLabel("Nombre:")).setBounds(x-=90, y+=25, z, 20);
		this.panel.add(txtNombre).setBounds(x+=90,y,z+=150,20);
		
		this.panel.add(new JLabel("Teléfono Celular:")).setBounds(x-=90, y+=25, z-=80, 20);
		this.panel.add(txtTelefono).setBounds(x+=90, y, z+=80, 20);
		
		this.panel.add(btnSalir).setBounds(x-=60, y+=45, z-=150, 20);
		this.panel.add(btnModificar).setBounds(x+=100,y,z,20);
		this.panel.add(btnGuardar).setBounds(x+=100,y,z,20);
		
		
		this.btnGuardar.addActionListener(Op_Guardar);
		this.btnModificar.addActionListener(modificar);
		this.btnSalir.addActionListener(Op_Salir);
		
		txtTelefono.addKeyListener(numerico_action);
		
		Obj_Directorios directorio = new Obj_Directorios().buscar(folio);
		Obj_Empleado empleado = new Obj_Empleado().buscar(folio);
		
		if(directorio.getFolio() != 0){		
			txtFolio.setText(directorio.getFolio()+"");
			txtNombre.setText(directorio.getNombre());
			txtTelefono.setText(directorio.getTelefono());
		}else{
			
			txtFolio.setText(empleado.getFolio()+"");
			txtNombre.setText(empleado.getNombre()+" "+empleado.getAp_paterno()+" "+empleado.getAp_materno());
		}
		this.txtFolio.setEditable(false);
		this.txtNombre.setEditable(false);
		this.txtTelefono.setEditable(false);
		
		this.cont.add(panel);
		
		this.setSize(380,180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener Op_Guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Directorios directorio = new Obj_Directorios().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(directorio.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							directorio.setTelefono(txtTelefono.getText());
							directorio.actualizar(Integer.parseInt(txtFolio.getText()));
							txtTelefono.setEditable(false);
						}
						
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						directorio.setFolio(Integer.parseInt(txtFolio.getText()));
						directorio.setFolio_empleado(Integer.parseInt(txtFolio.getText()));
						directorio.setNombre(txtNombre.getText());
						directorio.setTelefono(txtTelefono.getText());
						directorio.guardar();
						txtTelefono.setEditable(false);
						
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}
		}
	};
	
	
	ActionListener modificar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0)
		{
			txtTelefono.setEditable(true);
			txtTelefono.requestFocus();
		}
	};
	
	ActionListener Op_Salir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new Cat_Filtro_Empleado_Directorio().setVisible(true);
		}
		
	};
	
	private String validaCampos(){
		String error="";
		if(txtFolio.getText().equals("")) 			error+= "Folio\n";
		if(txtNombre.getText().equals("")) 			error+= "Nombre\n";
		if(txtTelefono.getText().equals("")) 			error+= "Telefono\n";
		return error;
	}
	
	KeyListener numerico_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			int limite=10;

			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }
				if (txtTelefono.getText().length()== limite)
			     e.consume();
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnGuardar.doClick();
			}
		}
		public void keyReleased(KeyEvent arg0) {}
	};
}