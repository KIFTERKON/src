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
import objetos.Obj_Sueldo;

@SuppressWarnings("serial")
public class Cat_Sueldo extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtSueldo = new JTextField();
	JTextField txtAbreviatura = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	
	public Cat_Sueldo(){
		
		super("..:: Sueldo ::..");
	
		chStatus.setSelected(true);
		
		int x = 45, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(x+ancho,y,ancho,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+5,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,ancho,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtSueldo).setBounds(x+ancho,y,ancho,20);
		panel.add(btnNuevo).setBounds(x+200,y,ancho,20);
		
		panel.add(new JLabel("Abreviatura:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtAbreviatura).setBounds(x+ancho,y,ancho,20);
		panel.add(btnEditar).setBounds(x+200,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho,y+=25,ancho,20);
		panel.add(btnSalir).setBounds(x,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+200,y,ancho,20);
		
		chStatus.setEnabled(false);
		txtSueldo.setEnabled(false);
		txtAbreviatura.setEnabled(false);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtSueldo.setDocument(new JTextFieldLimit(8));
		txtAbreviatura.setDocument(new JTextFieldLimit(5));
		
		txtFolio.addKeyListener(numerico_action);
		txtSueldo.addKeyListener(validaNumericoConPunto);
		txtFolio.addKeyListener(buscar_action);

		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		
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
				Obj_Sueldo sueldo = new Obj_Sueldo().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(sueldo.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							sueldo.setSueldo(Float.parseFloat(txtSueldo.getText()));
							sueldo.setAbreviatura(txtAbreviatura.getText());
							sueldo.setStatus(chStatus.isSelected());
							sueldo.actualizar(Integer.parseInt(txtFolio.getText()));	
							
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEnabled(true);
							txtFolio.requestFocus();
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
						sueldo.setSueldo(Float.parseFloat(txtSueldo.getText()));
						sueldo.setAbreviatura(txtAbreviatura.getText());
						sueldo.setStatus(chStatus.isSelected());
						sueldo.guardar();
						panelLimpiar();
						panelEnabledFalse();
						txtFolio.setEnabled(true);
						txtFolio.requestFocus();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}			
		}
	};
	
	public void panelEnabledFalse(){	
		txtFolio.setEnabled(false);
		txtSueldo.setEnabled(false);
		txtAbreviatura.setEnabled(false);
		chStatus.setEnabled(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEnabled(true);
		txtSueldo.setEnabled(true);
		txtAbreviatura.setEnabled(true);
		chStatus.setEnabled(true);	
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtSueldo.setText("");
		txtAbreviatura.setText("");
		chStatus.setSelected(true);
	}
	
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
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){
		    		    	
		    	String texto = txtSueldo.getText().toString();
				if (texto.indexOf(".")>0) e.consume();
				
			}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
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
	
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Sueldo re = new Obj_Sueldo();
				re = re.buscar(Integer.parseInt(txtFolio.getText()));
				
				if(re.getFolio() != 0){
				
				txtFolio.setText(re.getFolio()+"");
				txtSueldo.setText(re.getSueldo()+"");
				txtAbreviatura.setText(re.getAbreviatura()+"");
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
		}
	};
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
		
	};
	
	private String validaCampos(){
		String error="";
		if(txtSueldo.getText().equals("")) 			error+= "Bono\n";
		if(txtAbreviatura.getText().equals(""))		error+= "Abreviatura\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Sueldo sueldo = new Obj_Sueldo().buscar_nuevo();
			if(sueldo.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(sueldo.getFolio()+1+"");
				txtFolio.setEnabled(false);
				txtSueldo.requestFocus();
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEnabled(true);
			txtFolio.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(true);
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "No hay registro que Editar","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				panelEnabledTrue();
				txtFolio.setEnabled(false);
				btnEditar.setEnabled(false);
				btnNuevo.setEnabled(true);
			}
			
		}		
	};
}
