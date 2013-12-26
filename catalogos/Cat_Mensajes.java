package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objetos.JTextFieldLimit;
import objetos.Obj_Mensajes;

@SuppressWarnings("serial")
public class Cat_Mensajes extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();

	JTextField txtFolio = new JTextField();
	JTextArea txtMensaje = new JTextArea();
	
	
	JLabel fondo = new JLabel(new ImageIcon("Imagen/cel.png"));
	
	
	JLabel lblBuscar = new JLabel(new ImageIcon("Imagen/busca.png"));
	JLabel lblEditar = new JLabel(new ImageIcon("Imagen/editara.png"));
	JLabel lblGuardar = new JLabel(new ImageIcon("Imagen/Guardar.png"));
	JLabel lblNuevo = new JLabel(new ImageIcon("Imagen/Nuevo.png"));
	JLabel lblCancelar = new JLabel(new ImageIcon("Imagen/delete.png"));
	
	int limite = 160;
	public Cat_Mensajes()
	{
		getContenedor();
	}
	
	public void getContenedor()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/mensaje.png"));
		this.setTitle("Mensajes");
		
		
		txtMensaje.setLineWrap(true); 
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setDocument(new JTextFieldLimit(limite));
		txtMensaje.setEditable(false);
		
		panel.add(new JLabel("Folio:")).setBounds(20,70,50,20);
		panel.add(txtFolio).setBounds(60,70,80,20);
		panel.add(txtMensaje).setBounds(15,100,180,220);
		
		panel.add(lblBuscar).setBounds(150,65,30,30);
		panel.add(lblGuardar).setBounds(20,340,30,30);
		panel.add(lblEditar).setBounds(70,340,30,30);
		panel.add(lblNuevo).setBounds(120,340,30,30);
		panel.add(lblCancelar).setBounds(160,340,30,30);
		panel.add(fondo).setBounds(0,0,208,383);
		
		lblBuscar.setToolTipText("Buscar Un Registro");
		lblGuardar.setToolTipText("Guardar Un Registro");
		lblEditar.setToolTipText("Editar Un Registro");
		lblNuevo.setToolTipText("Nuevo Registro");
		lblCancelar.setToolTipText("Cancelar");
		
		
		lblBuscar.addMouseListener(buscar);
		lblEditar.addMouseListener(editar);
		lblGuardar.addMouseListener(guardar);
		lblNuevo.addMouseListener(nuevo);
		lblCancelar.addMouseListener(cancelar);
		fondo.setBorder(null);
		txtFolio.addKeyListener(valida);
		
		cont.add(panel);
		this.setSize(208,383);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
	}
	
	
	MouseListener buscar = new MouseListener() {
		
		public void mouseReleased(MouseEvent e) {
			if(e.getClickCount()==1){
				if(txtFolio.getText().equals("")){
					dispose();
					new Cat_Filtro_Mensajes().setVisible(true);
				}else{
					Obj_Mensajes re = new Obj_Mensajes();
					re = re.buscar(Integer.parseInt(txtFolio.getText()));
					if(re.getFolio() != 0){			
						txtFolio.setText(re.getFolio()+"");
						txtMensaje.setText(re.getMensaje().toUpperCase());
						txtFolio.setEditable(false);
						txtMensaje.setEditable(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
						txtFolio.setEditable(true);
						return;
					}
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
	};
	
	MouseListener editar = new MouseListener() {
		
		public void mouseReleased(MouseEvent e) {
			if(e.getClickCount()==1){
				txtFolio.setEditable(false);
				txtMensaje.requestFocus();
				txtMensaje.setEditable(true);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
	};
	
	MouseListener nuevo = new MouseListener() {
		
		public void mouseReleased(MouseEvent e) {
			if(e.getClickCount()==1){
				try {
					Obj_Mensajes mensaje = new Obj_Mensajes().buscar_nuevo();
					if(mensaje.getFolio() != 0){
						txtFolio.setText(mensaje.getFolio()+1+"");
						txtFolio.setEditable(false);
						txtMensaje.setEditable(true);
						txtMensaje.requestFocus();
						txtMensaje.setText("");
					}else{;
						txtFolio.setText(1+"");
						txtMensaje.setText("");
						txtFolio.setEditable(false);
						txtMensaje.setEditable(true);
						txtMensaje.requestFocus();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
			}
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
	};
			
	
	MouseListener cancelar = new MouseListener() {
		
		public void mouseReleased(MouseEvent e) {
			if(e.getClickCount()==1){
				dispose();
			}
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
	};
	
	
	MouseListener guardar = new MouseListener() {
		
		public void mouseReleased(MouseEvent e) {
			if(e.getClickCount()==1){
				if(txtFolio.getText().equals("")){
					JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				}else{			
					Obj_Mensajes mensaje = new Obj_Mensajes().buscar(Integer.parseInt(txtFolio.getText()));
					
					if(mensaje.getFolio() == Integer.parseInt(txtFolio.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
							if(validaCampos()!="") {
								JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
								return;
							}else{
								mensaje.setMensaje(txtMensaje.getText().toUpperCase());
								mensaje.actualizar(Integer.parseInt(txtFolio.getText().toUpperCase()));
							}
							
							JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
							limpia();
							txtFolio.setEditable(true);
							txtMensaje.setEditable(false);
							txtFolio.requestFocus();
						}else{
							return;
						}
					}else{
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							mensaje.setFolio(Integer.parseInt(txtFolio.getText()));
							mensaje.setMensaje(txtMensaje.getText());
							
							txtFolio.setEditable(true);
							txtMensaje.setEditable(false);
							txtFolio.requestFocus();
							
							mensaje.guardar();
							limpia();
							JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						}
					}
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
	};
	
	
	public void limpia()
	{
		txtFolio.setText("");
		txtMensaje.setText("");
	}
	
	private String validaCampos(){
		String error="";
		if(txtFolio.getText().equals("")) 	 error+= "Folio\n";
		if(txtMensaje.getText().equals(""))  error+= "Mensaje\n";
		return error;
	}
	
	
	public Cat_Mensajes(String algo) {
		
		getContenedor();
		
		lblNuevo.setVisible(false);
		lblEditar.setVisible(true);
		
		Obj_Mensajes msj = new Obj_Mensajes().buscar(Integer.parseInt(algo));
		
		if(msj.getFolio() != 0){		
			txtFolio.setText(msj.getFolio()+"");
			txtMensaje.setText(msj.getMensaje());
			
		    lblNuevo.setVisible(true);
			lblEditar.setVisible(true);
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			
		}
		else{
			txtFolio.setEditable(true);
		}
		
		txtFolio.setEnabled(true);
		
	
	}
	
	
	KeyListener valida = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();

			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    	}
		}
		
		public void keyReleased(KeyEvent arg0) {
		}
		
		public void keyPressed(KeyEvent arg0) {
		}
	};
	
}
