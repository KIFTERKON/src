package catalogos;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objetos.JTextFieldLimit;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Establecimiento;

@SuppressWarnings("serial")
public class Cat_Establecimiento extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	JTextField txtAbreviatura = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnSalir = new JButton("Salir");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnDeshacer = new JButton("Deshcer");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnEditar = new JButton("Editar");

	public Cat_Establecimiento(){
		
		super("..:: Establecimiento ::..");
		
		chStatus.setSelected(true);
		
	int x = 45, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(x+ancho,y,ancho,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+5,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,ancho,20);
		
		panel.add(new JLabel("Nombre:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtNombre).setBounds(x+ancho,y,ancho,20);
		panel.add(btnNuevo).setBounds(x+200,y,ancho,20);
		
		panel.add(new JLabel("Abreviatura:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtAbreviatura).setBounds(x+ancho,y,ancho,20);
		panel.add(btnEditar).setBounds(x+200,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho,y+=25,ancho,20);
		panel.add(btnSalir).setBounds(x,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+200,y,ancho,20);
	
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtNombre.setDocument(new JTextFieldLimit(16));
		txtAbreviatura.setDocument(new JTextFieldLimit(5));
		
		btnSalir.addActionListener(cerrar);
		btnGuardar.addActionListener(guardar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		
		cont.add(panel);
		
		this.setSize(400,190);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
	}
	public JComponent getBase(){
			return panel;
	}
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Establecimiento establecimiento = new Obj_Establecimiento().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(establecimiento.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							establecimiento.setFolio(Integer.parseInt(txtFolio.getText()));
							establecimiento.setNombre(txtNombre.getText());
							establecimiento.setAbreviatura(txtAbreviatura.getText());
							establecimiento.setStatus(chStatus.isSelected());
							establecimiento.actualizar(Integer.parseInt(txtFolio.getText()));						
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
						establecimiento.setFolio(Integer.parseInt(txtFolio.getText()));
						establecimiento.setNombre(txtNombre.getText());
						establecimiento.setAbreviatura(txtAbreviatura.getText());
						establecimiento.setStatus(chStatus.isSelected());
						establecimiento.guardar();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}			
		}
	};
	
	ActionListener buscar = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Obj_Bono_Complemento_Sueldo re = new Obj_Bono_Complemento_Sueldo();
			re = re.buscar(Integer.parseInt(txtFolio.getText()));
			
			if(re != null){
			
			txtFolio.setText(re.getFolio()+"");
			txtNombre.setText(re.getBono()+"");
			txtAbreviatura.setText(re.getAbreviatura()+"");
			System.out.println(re.getStatus());
			if(re.getStatus() == true){chStatus.setSelected(true);}
			else{chStatus.setSelected(false);}
			
			btnNuevo.setEnabled(false);
			btnEditar.setEnabled(true);
			panelEnabledFalse();
			txtFolio.setEnabled(true);
			txtFolio.requestFocus();
			
			}
			else{
				JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
		
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledTrue();
			txtFolio.requestFocus();
		}
	};
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Establecimiento establecimiento = new Obj_Establecimiento().buscar_nuevo();
			if(establecimiento.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(establecimiento.getFolio()+1+"");
				txtFolio.setEnabled(false);
				txtNombre.requestFocus();
			}
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			txtFolio.setEnabled(false);
			btnEditar.setEnabled(false);
			btnNuevo.setEnabled(true);
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
	
	public void panelEnabledTrue(){	
		txtFolio.setEnabled(true);
		txtNombre.setEnabled(true);
		txtAbreviatura.setEnabled(true);
		chStatus.setEnabled(true);	
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtNombre.setText("");
		txtAbreviatura.setText("");
		chStatus.setSelected(true);
	}
	
	private String validaCampos(){
		
		String error="";
		
		if(txtFolio.getText().equals("")) 			error+= "Folio\n";
		if(txtNombre.getText().equals("")) 		error+= "Nombre\n";
		if(txtAbreviatura.getText().equals(""))		error+= "Abreviatura\n";
				
		return error;
	}
	
	public void panelEnabledFalse(){	
		txtFolio.setEnabled(false);
		txtNombre.setEnabled(false);
		txtAbreviatura.setEnabled(false);
		chStatus.setEnabled(false);
	}
}
