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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objetos.JTextFieldLimit;
import objetos.Obj_Bono;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;
import objetos.Obj_Sueldo;

@SuppressWarnings("serial")
public class Cat_Empleado extends JDialog{

	int forma;
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtChecador = new JTextField();
	JTextField txtNombre = new JTextField();
	JTextField txtApPaterno = new JTextField();
	JTextField txtApMaterno = new JTextField();
	JTextField txtFecha = new JTextField(new Date().toString());
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings("unchecked")
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String puesto[] = new Obj_Puesto().Combo_Puesto();
	@SuppressWarnings("unchecked")
	JComboBox cmbPuesto = new JComboBox(puesto);
	
	String sueldo[] = new Obj_Sueldo().Combo_Sueldo();
	@SuppressWarnings("unchecked")
	JComboBox cmbSueldo = new JComboBox(sueldo);
	
	String bono[] = new Obj_Bono().Combo_Bono();
	@SuppressWarnings("unchecked")
	JComboBox cmbBono = new JComboBox(bono);
	
	JCheckBox chbFuente_Sodas = new JCheckBox("Fnt de Sodas");
	JCheckBox chbGafete = new JCheckBox("Gafete");
	
	String status[] = {"Vigente","Vacaciones","Baja"};
	@SuppressWarnings("unchecked")
	JComboBox cmbStatus = new JComboBox(status);
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnEditar = new JButton("Editar");
	JButton btnSalir = new JButton("Salir");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnDeshacer = new JButton("Deshacer");
	
	public Cat_Empleado() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		int x = 40, y=30, ancho=140;
		panel.setBorder(BorderFactory.createTitledBorder("Empleado"));
		
		txtFecha.setEnabled(false);
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(x+ancho,y,ancho-15,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho-12,y,32,20);
		panel.add(btnFiltro).setBounds(x+ancho+ancho+20,y,32,20);
		panel.add(btnEditar).setBounds(x+ancho+ancho+51,y,ancho-49,20);
		btnEditar.setVisible(false);
		panel.add(btnNuevo).setBounds(x+ancho+ancho+51,y,ancho-49,20);
		
		panel.add(new JLabel("No Checador:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtChecador).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Nombre:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Apellido Paterno:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtApPaterno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Apellido Materno:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtApMaterno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Establecimiento:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbEstablecimiento).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Puesto:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbPuesto).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Sueldo:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbSueldo).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Bono:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbBono).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Status:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbStatus).setBounds(x+ancho,y,ancho-10,20);
		panel.add(chbFuente_Sodas).setBounds(x+ancho+130,y,90,20);
		panel.add(chbGafete).setBounds((x*3)+(ancho*2)+5,y,80,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtFecha).setBounds(x+ancho,y,ancho+50,20);
		
		panel.add(btnDeshacer).setBounds(x,y+=25,ancho-20,20);
		panel.add(btnSalir).setBounds(x+ancho+10,y,ancho-20,20);
		panel.add(btnGuardar).setBounds(x+ancho+ancho+20,y,ancho-20,20);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtChecador.setDocument(new JTextFieldLimit(9));
		txtNombre.setDocument(new JTextFieldLimit(20));
		txtApPaterno.setDocument(new JTextFieldLimit(20));
		txtApMaterno.setDocument(new JTextFieldLimit(20));
		
		btnEditar.addActionListener(editar);
		btnBuscar.addActionListener(buscar);
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(salir);
		btnNuevo.addActionListener(nuevo);
		btnDeshacer.addActionListener(deshacer);
		btnFiltro.addActionListener(filtro);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		txtChecador.addKeyListener(numerico_action);
		
		cont.add(panel);
		
		panelEnabledFalse();
		txtFolio.setEnabled(true);
		
		this.setModal(true);
		this.setSize(500,390);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

	}
	
	public JComponent getBase(){
		return panel;
	}
	ActionListener buscar = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			
			
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Empleado re = new Obj_Empleado();
				re = re.buscar(Integer.parseInt(txtFolio.getText()));
				if(re.getFolio() != 0){			
					txtFolio.setText(re.getFolio()+"");
					txtChecador.setText(re.getNo_checador()+"");
					txtNombre.setText(re.getNombre()+"");
					txtApPaterno.setText(re.getAp_paterno()+"");
					txtApMaterno.setText(re.getAp_materno()+"");	
					cmbEstablecimiento.setSelectedIndex(re.getEstablecimiento()-1);
					cmbPuesto.setSelectedIndex(re.getPuesto()-1);
					cmbSueldo.setSelectedIndex(re.getSueldo()-1);
					cmbBono.setSelectedIndex(re.getBono()-1);
					if(re.getFuente_sodas() == true){chbFuente_Sodas.setSelected(true);}
					else{chbFuente_Sodas.setSelected(false);}
					if(re.getGafete() == true){chbGafete.setSelected(true);}
					else{chbGafete.setSelected(false);}
					cmbStatus.setSelectedIndex(re.getStatus()-1);
					txtFecha.setText(re.getFecha());
					
					btnNuevo.setVisible(false);
					btnEditar.setVisible(true);
					panelEnabledFalse();
					txtFolio.setEnabled(true);
					txtFolio.requestFocus();
					
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
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(empleado.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							txtFecha.setText(new Date().toString());
							empleado.setNo_checador(Integer.parseInt(txtChecador.getText()));
							empleado.setNombre(txtNombre.getText());
							empleado.setAp_paterno(txtApPaterno.getText());
							empleado.setAp_materno(txtApMaterno.getText());
							empleado.setEstablecimiento(cmbEstablecimiento.getSelectedIndex()+1);
							empleado.setPuesto(cmbPuesto.getSelectedIndex()+1);
							empleado.setSueldo(cmbSueldo.getSelectedIndex()+1);
							empleado.setBono(cmbBono.getSelectedIndex()+1);
							empleado.setFuente_sodas(chbFuente_Sodas.isSelected());
							empleado.setGafete(chbGafete.isSelected());
							empleado.setStatus(cmbStatus.getSelectedIndex()+1);
							empleado.setFecha(txtFecha.getText());
							panelEnabledTrue();
							empleado.actualizar(Integer.parseInt(txtFolio.getText()));
							panelLimpiar();
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
						txtFecha.setText(new Date().toString());
						empleado.setNo_checador(Integer.parseInt(txtChecador.getText()));
						empleado.setNombre(txtNombre.getText());
						empleado.setAp_paterno(txtApPaterno.getText());
						empleado.setAp_materno(txtApMaterno.getText());
						empleado.setEstablecimiento(cmbEstablecimiento.getSelectedIndex()+1);
						empleado.setPuesto(cmbPuesto.getSelectedIndex()+1);
						empleado.setSueldo(cmbSueldo.getSelectedIndex()+1);
						empleado.setBono(cmbBono.getSelectedIndex()+1);
						empleado.setFuente_sodas(chbFuente_Sodas.isSelected());
						empleado.setGafete(chbGafete.isSelected());
						empleado.setStatus(cmbStatus.getSelectedIndex()+1);
						empleado.setFecha(txtFecha.getText());
						panelEnabledTrue();
						empleado.guardar();	
						panelLimpiar();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}			
		}
	};
	
	ActionListener filtro = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new Cat_Filtro_Emp().setVisible(true);
			
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
			if(empleado.getFolio() != 0){
				panelEnabledTrue();
				txtFolio.setEnabled(false);
				btnEditar.setVisible(false);
				btnNuevo.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null,"El registró que desea actualizar no existe","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
				return;
			}
		}		
	};
	public void panelEnabledTrue(){	
		txtFolio.setEnabled(true);
		txtChecador.setEnabled(true);
		txtNombre.setEnabled(true);
		txtApPaterno.setEnabled(true);
		txtApMaterno.setEnabled(true);
		cmbEstablecimiento.setEnabled(true);
		cmbPuesto.setEnabled(true);
		cmbSueldo.setEnabled(true);
		cmbBono.setEnabled(true);
		chbFuente_Sodas.setEnabled(true);
		chbGafete.setEnabled(true);
		cmbStatus.setEnabled(true);
		
	}
	
	public void panelEnabledFalse(){	
		txtFolio.setEnabled(false);
		txtChecador.setEnabled(false);
		txtNombre.setEnabled(false);
		txtApPaterno.setEnabled(false);
		txtApMaterno.setEnabled(false);
		cmbEstablecimiento.setEnabled(false);
		cmbPuesto.setEnabled(false);
		cmbSueldo.setEnabled(false);
		cmbBono.setEnabled(false);
		chbFuente_Sodas.setEnabled(false);
		chbGafete.setEnabled(false);
		cmbStatus.setEnabled(false);
		
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtChecador.setText("");
		txtNombre.setText("");
		txtApPaterno.setText("");
		txtApMaterno.setText("");
		cmbEstablecimiento.setSelectedIndex(0);
		cmbPuesto.setSelectedIndex(0);
		cmbSueldo.setSelectedIndex(0);
		cmbBono.setSelectedIndex(0);
		chbFuente_Sodas.setSelected(false);
		chbGafete.setSelected(false);
		cmbStatus.setSelectedIndex(0);
		
		panelEnabledFalse();
		txtFolio.setEnabled(true);
		
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Empleado empleado = new Obj_Empleado().buscar_nuevo();
			if(empleado.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(empleado.getFolio()+1+"");
				txtFolio.setEnabled(false);
				txtChecador.requestFocus();
				txtFecha.setText(new Date().toString());
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			panelEnabledTrue();
			txtFolio.requestFocus();
			btnEditar.setVisible(false);
			btnNuevo.setVisible(true);
		}
	};
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
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
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    // VERIFICAR SI LA TECLA PULSADA NO ES UN DIGITO
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){
		    		    	
//		    	String texto = txtDepocito.getText().toString();
//				if (texto.indexOf(".")>0) e.consume();
				
			}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	private String validaCampos(){
		String error="";
		
		if(txtFolio.getText().equals("")) 		error+= "Folio\n";
		if(txtChecador.getText().equals("")) 	error+= "Numero Checador\n";
		if(txtNombre.getText().equals("")) 		error+= "Nombre\n";
		if(txtApPaterno.getText().equals(""))	error+= "Ap Paterno\n";
		if(txtApMaterno.getText().equals(""))	error+= "Ap Materno\n";
				
		return error;
	}
	
	public Cat_Empleado(String algo) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		int x = 40, y=30, ancho=140;
		panel.setBorder(BorderFactory.createTitledBorder("Empleado"));
		
		txtFecha.setEnabled(false);
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(x+ancho,y,ancho-15,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho-12,y,32,20);
		panel.add(btnFiltro).setBounds(x+ancho+ancho+20,y,32,20);
		panel.add(btnNuevo).setBounds(x+ancho+ancho+51,y,ancho-49,20);
		btnNuevo.setVisible(false);
		panel.add(btnEditar).setBounds(x+ancho+ancho+51,y,ancho-49,20);
		
		panel.add(new JLabel("No Checador:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtChecador).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Nombre:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Apellido Paterno:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtApPaterno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Apellido Materno:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtApMaterno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Establecimiento:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbEstablecimiento).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Puesto:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbPuesto).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Sueldo:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbSueldo).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Bono:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbBono).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Status:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbStatus).setBounds(x+ancho,y,ancho-10,20);
		panel.add(chbFuente_Sodas).setBounds(x+ancho+130,y,90,20);
		panel.add(chbGafete).setBounds((x*3)+(ancho*2)+5,y,80,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtFecha).setBounds(x+ancho,y,ancho+50,20);
		
		
		panel.add(btnDeshacer).setBounds(x,y+=25,ancho-20,20);
		panel.add(btnSalir).setBounds(x+ancho+10,y,ancho-20,20);
		panel.add(btnGuardar).setBounds(x+ancho+ancho+20,y,ancho-20,20);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtChecador.setDocument(new JTextFieldLimit(9));
		txtNombre.setDocument(new JTextFieldLimit(20));
		txtApPaterno.setDocument(new JTextFieldLimit(20));
		txtApMaterno.setDocument(new JTextFieldLimit(20));
		
		
		btnEditar.addActionListener(editar);
		btnBuscar.addActionListener(buscar);
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(salir);
		btnNuevo.addActionListener(nuevo);
		btnDeshacer.addActionListener(deshacer);
		btnFiltro.addActionListener(filtro);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		txtChecador.addKeyListener(numerico_action);
		
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado();
		
		re = re.buscar(Integer.parseInt(algo));
			txtFolio.setText(re.getFolio()+"");
			txtChecador.setText(re.getNo_checador()+"");
			txtNombre.setText(re.getNombre()+"");
			txtApPaterno.setText(re.getAp_paterno()+"");
			txtApMaterno.setText(re.getAp_materno()+"");	
			cmbEstablecimiento.setSelectedIndex(re.getEstablecimiento()-1);
			cmbPuesto.setSelectedIndex(re.getPuesto()-1);
			cmbSueldo.setSelectedIndex(re.getSueldo()-1);
			cmbBono.setSelectedIndex(re.getBono()-1);
			if(re.getFuente_sodas() == true){chbFuente_Sodas.setSelected(true);}
			else{chbFuente_Sodas.setSelected(false);}
			if(re.getGafete() == true){chbGafete.setSelected(true);}
			else{chbGafete.setSelected(false);}
			cmbStatus.setSelectedIndex(re.getStatus()-1);
			txtFecha.setText(re.getFecha());
		
		panelEnabledFalse();
		txtFolio.setEnabled(true);
		
		this.setModal(true);
		this.setSize(500,390);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

	}
	
}
