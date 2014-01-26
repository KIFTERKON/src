package cuadrantes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objetos.Obj_OpRespuesta;

@SuppressWarnings("serial")
public class Cat_Opciones_Respuesta extends JFrame {
	
	private Container cont = getContentPane();
	private JLayeredPane panel = new JLayeredPane();
	
	private JTextField txtFolio = new JTextField();
	private JTextField txtNombre = new JTextField();
	
	private JCheckBox chStatus = new JCheckBox("Status",true);
	
	private JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	private JButton btnSalir = new JButton("Salir");
	private JButton btnDeshacer = new JButton("Deshacer");
	private JButton btnGuardar = new JButton("Guardar");
	private JButton btnEditar = new JButton("Editar");
	private JButton btnNuevo = new JButton("Nuevo");
	
	String lista[] = {"Seleccione Una Opción","Opción Libre","Opción Múltiple"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbRespuesta = new JComboBox(lista);
	
	public Cat_Opciones_Respuesta() {
		init();
		
		this.setSize(410,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public Cat_Opciones_Respuesta(int folio){
		init();
		
		Obj_OpRespuesta respuesta = new Obj_OpRespuesta().buscar(folio);
		
		txtFolio.setText(respuesta.getFolio()+"");
		txtNombre.setText(respuesta.getNombre());
		cmbRespuesta.setSelectedItem(respuesta.getTipo_opcion().toString());
		chStatus.setSelected(respuesta.isStatus());
		
		btnEditar.setEnabled(true);
		
		this.setSize(410,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void init(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/page_layout_icon&16.png"));
		this.panel.setBorder(BorderFactory.createTitledBorder("Opcion de Respuesta"));
		
		int y=30, ancho=100;
		
		this.panel.add(new JLabel("Folio:")).setBounds(20,y,ancho,20);
		this.panel.add(txtFolio).setBounds(ancho-5,y,ancho,20);
		this.panel.add(btnBuscar).setBounds(ancho+ancho,y,32,20);
		this.panel.add(chStatus).setBounds(235,y,70,20);
		this.panel.add(btnNuevo).setBounds(305,y,75,20);
		
		this.panel.add(new JLabel("Nombre:")).setBounds(20,y+=30,70,20);
		this.panel.add(txtNombre).setBounds(95,y,190,20);
		this.panel.add(btnEditar).setBounds(305,y,75,20);
		
		this.panel.add(new JLabel("Opciones:")).setBounds(20,y+=30,70,20);
		this.panel.add(cmbRespuesta).setBounds(95,y,190,20);
		
		this.add(btnSalir).setBounds(20,y+=40,75,20);
		this.add(btnDeshacer).setBounds(160,y,80,20);
		this.add(btnGuardar).setBounds(305,y,75,20);
		
		this.txtFolio.setEditable(true);
		this.txtFolio.requestFocus();
		this.btnNuevo.setEnabled(true);
		this.btnBuscar.setEnabled(true);
		
		this.cont.add(panel);
		
		this.Editing(false);
		this.txtFolio.setEditable(true);
		this.txtFolio.requestFocus();
		this.btnNuevo.setEnabled(true);
		
		this.txtFolio.addKeyListener(numerico_action);
		
		this.btnNuevo.addActionListener(op_nuevo);
		this.btnDeshacer.addActionListener(op_deshacer);
		this.btnGuardar.addActionListener(op_guardar);
		this.btnBuscar.addActionListener(op_buscar);
		this.btnEditar.addActionListener(op_editar);
		this.btnSalir.addActionListener(op_salir);
		
	}
	
	ActionListener op_salir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	ActionListener op_editar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Editing(true);
			txtFolio.setEditable(false);
		}
	};
	
	ActionListener op_buscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				new Cat_Filtro_Opciones_Respuesta().setVisible(true);
				dispose();
			}else{
				if(new Obj_OpRespuesta().status_buscar(Integer.parseInt(txtFolio.getText()))){
					Obj_OpRespuesta respuesta = new Obj_OpRespuesta().buscar(Integer.parseInt(txtFolio.getText()));
					
					txtFolio.setText(respuesta.getFolio()+"");
					txtNombre.setText(respuesta.getNombre());
					cmbRespuesta.setSelectedItem(respuesta.getTipo_opcion().toString());
					chStatus.setSelected(respuesta.isStatus());
					
					btnEditar.setEnabled(true);
				}else{
					JOptionPane.showMessageDialog(null,"El registro no existe","Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		}
	};
	
	ActionListener op_guardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!valida().equals("")){
				JOptionPane.showMessageDialog(null,"Los siguientes campos son requeridos: \n"+valida(),"Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_OpRespuesta respuesta = new Obj_OpRespuesta();
				if(new Obj_OpRespuesta().status_buscar(Integer.parseInt(txtFolio.getText()))) {
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						respuesta.setFolio(Integer.parseInt(txtFolio.getText()));
						respuesta.setNombre(txtNombre.getText());
						respuesta.setTipo_opcion(cmbRespuesta.getSelectedItem().toString());
						respuesta.setStatus(chStatus.isSelected());
						
						if(respuesta.actualizar(Integer.parseInt(txtFolio.getText()))){
							limpiar();
							JOptionPane.showMessageDialog(null,"El registro se actualizó con éxito","Aviso", JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null,"Ocurrió un problema al intentar actualizar el registro","Aviso", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
					}else{
						return;
					}
				}else{
					respuesta.setFolio(Integer.parseInt(txtFolio.getText()));
					respuesta.setNombre(txtNombre.getText());
					respuesta.setTipo_opcion(cmbRespuesta.getSelectedItem().toString());
					respuesta.setStatus(chStatus.isSelected());
					
					if(respuesta.guardar()){
						limpiar();
						JOptionPane.showMessageDialog(null,"El registro se guardó con éxito","Aviso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						JOptionPane.showMessageDialog(null,"Ocurrió un problema al intentar guardar el registro","Aviso", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		}
	};
	
	public String valida(){
		String error ="";
		
		if(txtFolio.getText().equals("")) error += "Folio\n";
		if(txtNombre.getText().equals("")) error += "Nombre\n";
		if(cmbRespuesta.getSelectedIndex() == 0) error += "Tipo de Respuesta";
		
		return error;
	}
	
	ActionListener op_deshacer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			limpiar();
			Editing(false);
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			btnNuevo.setEnabled(true);
		}
	};
	ActionListener op_nuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			limpiar();
			txtFolio.setText(new Obj_OpRespuesta().Nuevo()+"");
		    Editing(false);
		    txtNombre.setEditable(true);
		    cmbRespuesta.setEnabled(true);
		    txtNombre.requestFocus();
		    btnNuevo.setEnabled(true);
		}
	};
	
	public void limpiar(){
		txtFolio.setText("");
		txtNombre.setText("");
		cmbRespuesta.setSelectedIndex(0);
	}
	
	public void Editing(boolean variable){
		txtFolio.setEditable(variable);
		txtNombre.setEditable(variable);
		cmbRespuesta.setEnabled(variable);
		btnEditar.setEnabled(variable);
		btnNuevo.setEnabled(variable);
	}
	
	KeyListener numerico_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();
			int limite=10;

			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }
				if (txtFolio.getText().length()== limite)
			     e.consume();
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
				txtFolio.requestFocus();
			}
		}
	};
	
}
