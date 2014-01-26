package catalogos;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cuadrantes.Cat_Filtro_Asignacion_Mensaje;

import objetos.JTextFieldLimit;
import objetos.Obj_Asignacion_Mensajes;
import objetos.Obj_Empleado;
import objetos.Obj_Equipo_Trabajo;
import objetos.Obj_Jefatura;
import objetos.Obj_Mensajes;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Asignacion_Mensajes extends JFrame
{
	
	/*crear las tablas del proyecto en la base de datos izagar**/
	
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtMensaje = new JTextField();
	JTextArea txaMensaje = new JTextArea();
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnCancelar = new JButton("Cancelar");
	
	JButton btnBrFolio = new JButton("Buscar");
	JButton btnBrMensaje = new JButton("Buscar");
	
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnLimpiar = new JButton("Limpiar");
	
	String puesto[] = new Obj_Puesto().Combo_Puesto();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbPuesto = new JComboBox(puesto);
	
	String equipo[] = new Obj_Equipo_Trabajo().Combo_Equipo();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEquipo = new JComboBox(equipo);

	String jefatura[] = new Obj_Jefatura().Combo_Jefatura();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbJefatura = new JComboBox(jefatura);
	
	String empleado[] = new Obj_Empleado().Combo_Empleado();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEmpleado = new JComboBox(empleado);
	
	JRadioButton rbPuesto = new JRadioButton();
	JRadioButton rbEquipo = new JRadioButton();
	JRadioButton rbJefatura = new JRadioButton();
	JRadioButton rbEmpleado = new JRadioButton();
	
	ButtonGroup rbGrupo = new ButtonGroup();
	
	int limite =160;
	
	public Cat_Asignacion_Mensajes()
	{
		getContenedor();
	}
	
	public void getContenedor()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/mensaje.png"));
		this.setTitle("Asignacion de mensajes");
		this.panel.setBorder(BorderFactory.createTitledBorder("Asignacion de mensajes"));
		
		rbGrupo.add(rbPuesto);
		rbGrupo.add(rbEquipo);
		rbGrupo.add(rbJefatura);
		rbGrupo.add(rbEmpleado);
		
		panel.add(new JLabel("Folio:")).setBounds(20,20,30,20);
		panel.add(txtFolio).setBounds(90,20,80,20);
		panel.add(btnBrFolio).setBounds(190,20,75,20);
		panel.add(btnNuevo).setBounds(280,20,75,20);
		
		panel.add(new JLabel("NºMensaje:")).setBounds(20,50,65,20);
		panel.add(btnBrMensaje).setBounds(190,50,75,20);
		panel.add(txtMensaje).setBounds(90,50,80,20);
		
		panel.add(txaMensaje).setBounds(20,80,300,140);
		
		panel.add(cmbPuesto).setBounds(330,80,300,20);
		panel.add(cmbEquipo).setBounds(330,120,300,20);
		panel.add(cmbJefatura).setBounds(330,160,300,20);
		panel.add(cmbEmpleado).setBounds(330,200,300,20);
		
		panel.add(rbPuesto).setBounds(640,80,20,20);
		panel.add(rbEquipo).setBounds(640,120,20,20);
		panel.add(rbJefatura).setBounds(640,160,20,20);
		panel.add(rbEmpleado).setBounds(640,200,20,20);
		
		panel.add(btnGuardar).setBounds(20,240,80,20);
		panel.add(btnEditar).setBounds(130,240,80,20);
		panel.add(btnLimpiar).setBounds(240,240,80,20);
		panel.add(btnCancelar).setBounds(350,240,80,20);
		
		txaMensaje.setLineWrap(true); 
		txaMensaje.setWrapStyleWord(true);
		txaMensaje.setDocument(new JTextFieldLimit(limite));
		txaMensaje.setBorder(BorderFactory.createLineBorder(Color.black));
		
		rbPuesto.addActionListener(OpRadios);
		rbEquipo.addActionListener(OpRadios);
		rbJefatura.addActionListener(OpRadios);
		rbEmpleado.addActionListener(OpRadios);
		
		btnLimpiar.addActionListener(OpLimpiar);
		btnCancelar.addActionListener(OpCancelar);
		btnNuevo.addActionListener(OpNuevo);
		btnBrMensaje.addActionListener(OpBuscar);
		btnGuardar.addActionListener(OpGuardar);
		btnBrFolio.addActionListener(OpBuscar_Folio);
		btnEditar.addActionListener(edita);
		
		this.setSize(750,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		
		SeleccionInicio();
		ocultar();
		rbPuesto.setSelected(true);
		txtFolio.addKeyListener(ValidaDigito);
		txtMensaje.addKeyListener(ValidaDigito2);
		cont.add(panel);
	}
	
	public Cat_Asignacion_Mensajes(String algo) {
		
		getContenedor();
		Obj_Asignacion_Mensajes msj2 = new Obj_Asignacion_Mensajes().buscar_folio(Integer.parseInt(algo));
		
		if(msj2.getFolio() != 0){		
			txtFolio.setText(msj2.getFolio()+"");
			txtMensaje.setText(msj2.getNo_mensajes()+"");
			txaMensaje.setText(msj2.getMensaje());
			
			rbPuesto.setSelected(msj2.getRbpuesto());
			rbEquipo.setSelected(msj2.getRbequipo());
			rbJefatura.setSelected(msj2.getRbjefatura());
			rbEmpleado.setSelected(msj2.getRbempleado());

			seleccion();
			cmbPuesto.setSelectedItem(msj2.getPuesto()+"");
			cmbEquipo.setSelectedItem(msj2.getEquipo()+"");
			cmbJefatura.setSelectedItem(msj2.getJefatura()+"");
			cmbEmpleado.setSelectedItem(msj2.getEmpleado()+"");	
			
			panelfalse();
			mostrar();
			
			
		}
		else{}
	}
	
	ActionListener OpGuardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{	
				
				Obj_Asignacion_Mensajes mensaje = new Obj_Asignacion_Mensajes().buscar_folio(Integer.parseInt(txtFolio.getText()));
				
				if(mensaje.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							mensaje.setNo_mensajes(Integer.parseInt(txtMensaje.getText()));
							mensaje.setMensaje(txaMensaje.getText().toUpperCase());
							
							mensaje.setPuesto(cmbPuesto.getSelectedItem()+"");
							mensaje.setEquipo(cmbEquipo.getSelectedItem()+"");
							mensaje.setJefatura(cmbJefatura.getSelectedItem()+"");
							mensaje.setEmpleado(cmbEmpleado.getSelectedItem()+"");
							
							
							mensaje.setRbpuesto(rbPuesto.isSelected());
							mensaje.setRbequipo(rbEquipo.isSelected());
							mensaje.setRbjefatura(rbJefatura.isSelected());
							mensaje.setRbempleado(rbEmpleado.isSelected());
							
							mensaje.actualizar(Integer.parseInt(txtFolio.getText()));
						}
						
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						limpiar();
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						mensaje.setFolio(Integer.parseInt(txtFolio.getText()));
						mensaje.setNo_mensajes(Integer.parseInt(txtMensaje.getText()));
						mensaje.setMensaje(txaMensaje.getText().toUpperCase());
						
						mensaje.setPuesto(cmbPuesto.getSelectedItem()+"");
						mensaje.setEquipo(cmbEquipo.getSelectedItem()+"");
						mensaje.setJefatura(cmbJefatura.getSelectedItem()+"");
						mensaje.setEmpleado(cmbEmpleado.getSelectedItem()+"");
						
						mensaje.setRbpuesto(rbPuesto.isSelected());
						mensaje.setRbequipo(rbEquipo.isSelected());
						mensaje.setRbjefatura(rbJefatura.isSelected());
						mensaje.setRbempleado(rbEmpleado.isSelected());
						
						
						
						mensaje.guardar();
						limpiar();
						SeleccionInicio();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}
		}
	};

	private String validaCampos(){
		String error="";
		if(txtFolio.getText().equals("")) 	 error+= "Folio\n";
		if(txtMensaje.getText().equals(""))  error+= "NºMensaje\n";
		if(txaMensaje.getText().equals(""))	 error+="Mensae";
		return error;
	}
	
	ActionListener OpBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtMensaje.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Por Favor De Insertar Un Nº De Mensaje");
				txtMensaje.requestFocus();
				
			}else{
				Obj_Mensajes re = new Obj_Mensajes();
				re = re.buscar(Integer.parseInt(txtMensaje.getText()));
				if(re.getFolio() != 0){		
					txtMensaje.setText(re.getFolio()+"");
					txaMensaje.setText(re.getMensaje().toUpperCase());
					txtMensaje.requestFocus();
				}
				else{
					txtMensaje.setText("");
					
					JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
					txtMensaje.requestFocus();
					
				}
			}
	}
};
	
	ActionListener OpBuscar_Folio = new ActionListener() {
	public void actionPerformed(ActionEvent arg0) 
	{
		if(txtFolio.getText().equals("")){
			dispose();
			new Cat_Filtro_Asignacion_Mensaje().setVisible(true);
		}else{
			
				
//			if(mensaje.get)
			if(txtFolio.getText()!="")
			{
				dispose();
				new Cat_Filtro_Asignacion_Mensaje().setVisible(true);
			}
			
//			Obj_As_Msjs re = new Obj_As_Msjs();
//			re = re.buscar_folio(Integer.parseInt(txtFolio.getText()));
//			if(re.getFolio() != 0){		
//				txtFolio.setText(re.getFolio()+"");
//				txtMensaje.setText(re.getNo_mensajes()+"");
//				txaMensaje.setText(re.getMensaje());
//				
//				rbPuesto.setSelected(re.getRbpuesto());
//				rbEquipo.setSelected(re.getRbequipo());
//				rbJefatura.setSelected(re.getRbjefatura());
//				rbEmpleado.setSelected(re.getRbempleado());
//				
//				seleccion();
//				panelfalse();
//				cmbPuesto.setSelectedItem(re.getPuesto()+"");
//				cmbEquipo.setSelectedItem(re.getEquipo()+"");
//				cmbJefatura.setSelectedItem(re.getJefatura()+"");
//				cmbEmpleado.setSelectedItem(re.getEmpleado()+"");	
				
//			}
//			else{
//				JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
//				txtFolio.setText("");
//				return;
//			}
		}
}
};

	ActionListener OpNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			try {
				Obj_Asignacion_Mensajes mensajes = new Obj_Asignacion_Mensajes().buscar_nuevo();
				
				if(mensajes.getFolio() != 0){
					txtFolio.setText(mensajes.getFolio()+1+"");
					txtMensaje.requestFocus();
					txtFolio.setEditable(false);
					paneltrue();
					mostrar();
					
					cmbPuesto.setEnabled(true);
					cmbEquipo.setEnabled(false);
					cmbJefatura.setEnabled(false);
					cmbEmpleado.setEnabled(false);
					
					cmbPuesto.setSelectedIndex(0);
					cmbEquipo.setSelectedIndex(1);
					cmbJefatura.setSelectedIndex(1);
					cmbEmpleado.setSelectedIndex(1);
					
					rbPuesto.setSelected(true);
				}else{
					txtFolio.setText(1+"");
					txtMensaje.requestFocus();


					cmbPuesto.setEnabled(true);
					cmbEquipo.setEnabled(false);
					cmbJefatura.setEnabled(false);
					cmbEmpleado.setEnabled(false);
					
					cmbPuesto.setSelectedIndex(0);
					cmbEquipo.setSelectedIndex(1);
					cmbJefatura.setSelectedIndex(1);
					cmbEmpleado.setSelectedIndex(1);
					
					rbPuesto.setSelected(true);
					
					paneltrue();
					mostrar();
					
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	};
	
	ActionListener OpRadios = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			seleccion();
		}
	};
	
	public void ocultar()
	{
		btnBrMensaje.setVisible(false);
		btnEditar.setVisible(false);
		btnCancelar.setVisible(false);
	}
	
	public void mostrar()
	{
		btnBrMensaje.setVisible(true);
		btnEditar.setVisible(true);
		btnCancelar.setVisible(true);
	}
	
	
	public void seleccion()
	{
		if(rbPuesto.isSelected()==true)
		{
			cmbPuesto.setEnabled(true);
			cmbPuesto.setSelectedIndex(0);
			cmbEquipo.setSelectedIndex(1);
			cmbJefatura.setSelectedIndex(1);
			cmbEmpleado.setSelectedIndex(1);
			cmbEquipo.setEnabled(false);
			cmbJefatura.setEnabled(false);
			cmbEmpleado.setEnabled(false);
		}
		
		if(rbEquipo.isSelected()==true)
		{
			cmbPuesto.setEnabled(false);
			cmbEquipo.setEnabled(true);
			cmbPuesto.setSelectedIndex(1);	
			cmbEquipo.setSelectedIndex(0);
			cmbJefatura.setSelectedIndex(1);
			cmbEmpleado.setSelectedIndex(1);
			cmbJefatura.setEnabled(false);
			cmbEmpleado.setEnabled(false);
		}
		
		if(rbJefatura.isSelected()==true)
		{
			cmbPuesto.setEnabled(false);
			cmbEquipo.setEnabled(false);
			cmbJefatura.setEnabled(true);
			cmbPuesto.setSelectedIndex(1);
			cmbEquipo.setSelectedIndex(1);
			cmbJefatura.setSelectedIndex(0);
			cmbEmpleado.setSelectedIndex(1);
			cmbEmpleado.setEnabled(false);
		}
		
		if(rbEmpleado.isSelected()==true)
		{
			cmbPuesto.setEnabled(false);
			cmbEquipo.setEnabled(false);
			cmbJefatura.setEnabled(false);
			cmbEmpleado.setEnabled(true);
			cmbPuesto.setSelectedIndex(1);
			cmbEquipo.setSelectedIndex(1);
			cmbJefatura.setSelectedIndex(1);
			cmbEmpleado.setSelectedIndex(0);
		}
	}
	
	public void SeleccionInicio()
	{
		cmbPuesto.setEnabled(true);
		cmbEquipo.setEnabled(false);
		cmbJefatura.setEnabled(false);
		cmbEmpleado.setEnabled(false);
		
		cmbPuesto.setSelectedIndex(0);
		cmbEquipo.setSelectedIndex(1);
		cmbJefatura.setSelectedIndex(1);
		cmbEmpleado.setSelectedIndex(1);
		
		txtFolio.requestFocus();
		txtMensaje.setEditable(false);
		txaMensaje.setEditable(false);
		rbPuesto.setSelected(true);
		seleccion();
	}
	
	public void limpiar()
	{
		txtFolio.setText("");
		txtMensaje.setText("");
		txaMensaje.setText("");
		
		SeleccionInicio();
		seleccion();
		panelfalse();
		ocultar();
		
		
		txtFolio.requestFocus();
		txtFolio.setEditable(true);
		}
	
	public void panelfalse()
	{
		txtMensaje.setEditable(false);
		txaMensaje.setEditable(false);
		txtFolio.setEditable(false);
	}
	
	public void paneltrue()
	{
		txtMensaje.setEditable(true);
		txaMensaje.setEditable(true);
	}
	
	ActionListener edita = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			paneltrue();
			txtMensaje.requestFocus();
			txtFolio.setEditable(false);
		}
	};
	
	ActionListener OpLimpiar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			limpiar();
		}
	};
	
	ActionListener OpCancelar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
		}
	};
	
	KeyListener ValidaDigito = new KeyListener() {
		public void keyTyped(KeyEvent e) 
		{
			char caracter = e.getKeyChar();
			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    	}
		}
		
		public void keyReleased(KeyEvent arg0) {
		}
		
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBrFolio.doClick();
				txtFolio.requestFocus();
				}
			
		}
	};
	
	KeyListener ValidaDigito2 = new KeyListener() {
		public void keyTyped(KeyEvent e) 
		{
			char caracter = e.getKeyChar();
			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    	}
		}
		
		public void keyReleased(KeyEvent arg0) {
		}
		
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBrMensaje.doClick();
				txtFolio.requestFocus();
				}
			
		}
	};
}