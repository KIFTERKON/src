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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import objetos.JTextFieldLimit;
import objetos.Obj_Actividad;
import objetos.Obj_Atributos;
import objetos.Obj_Nivel_Critico;
import objetos.Obj_OpRespuesta;
import objetos.Obj_Temporada;

@SuppressWarnings("serial")
public class Cat_Actividad extends JFrame {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();	
	
	JCheckBox chCondicion = new JCheckBox("Automatica");
	
	JCheckBox chbStatus = new JCheckBox("Status",true);
	
	JTextArea txaDescripcion = new JTextArea();
	JScrollPane scrolltxa = new JScrollPane(txaDescripcion);
	
	JTextArea txaActividad = new JTextArea();
	JScrollPane scrollact = new JScrollPane(txaActividad); 

	String respuesta[] = new Obj_OpRespuesta().Combo_Respuesta();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbRespuesta = new JComboBox(respuesta);
	
	String atributo[] = new Obj_Atributos().Combo_Atributo();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbAtributos = new JComboBox(atributo);
	
	String nivel_critico[] = new Obj_Nivel_Critico().Combo_Nivel_Critico();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbNivelCritico = new JComboBox(nivel_critico);
	
//	SpinnerDateModel HI_date_model = new SpinnerDateModel();
//	JSpinner HI_spiner = new JSpinner(HI_date_model);
//	DateEditor HI_editor = new DateEditor(HI_spiner,"H:mm");
//	
//	SpinnerDateModel HF_date_model = new SpinnerDateModel();
//	JSpinner HF_spiner = new JSpinner(HF_date_model);
//	DateEditor HF_editor = new DateEditor(HF_spiner,"H:mm");
	
	String temporada[] = new Obj_Temporada().Combo_Temporada();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbTemporada = new JComboBox(temporada);
	
	JCheckBox chbCajaDeTrabajo = new JCheckBox("Carga de trabajo");
	
	JSpinner spRepetir = new JSpinner(new SpinnerNumberModel(0,0,10,1));
	
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnSalir = new JButton("Salir");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnModificar = new JButton("Modificar");
	JButton btnSimilar = new JButton("Similar");
	JButton btnderecha = new JButton(new ImageIcon("Iconos/right_icon&16.png"));
	JButton btnizquierda = new JButton(new ImageIcon("Iconos/left_icon&16.png"));
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	
	public Cat_Actividad(){
		this.init();
		
		this.setSize(730,530);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public Cat_Actividad(int Folio){
		this.init();
		
		Obj_Actividad actividad = new Obj_Actividad().Buscar(Folio);
		
		txtFolio.setText(Folio+"");		
		txaActividad.setText(actividad.getActividad());
		txaDescripcion.setText(actividad.getDescripcion());
		
		cmbRespuesta.setSelectedItem(actividad.getRespuesta());
		cmbAtributos.setSelectedItem(actividad.getAtributos());
		cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
		
//		String[] arrayH_I = actividad.getHora_inicio().split(":");
//		HI_spiner.setValue(new Time(Integer.parseInt(arrayH_I[0]), Integer.parseInt(arrayH_I[1]), Integer.parseInt(arrayH_I[2])));
//		
//		String[] arrayH_F = actividad.getHora_final().split(":");
//		HF_spiner.setValue(new Time(Integer.parseInt(arrayH_F[0]), Integer.parseInt(arrayH_F[1]), Integer.parseInt(arrayH_F[2])));
	
		cmbTemporada.setSelectedItem(actividad.getTemporada());
		chbCajaDeTrabajo.setSelected(actividad.isCarga());
		spRepetir.setValue(actividad.getRepetir());
		chbStatus.setSelected(actividad.isStatus());
		
		panelEnabledFalse();
		txtFolio.setEditable(true);
		txtFolio.requestFocus();
		
		this.setSize(730,530);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void init(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/actividad_icon&16.png"));
		this.setTitle("Actividad");
		this.panel.setBorder(BorderFactory.createTitledBorder("Actividad"));
		
		this.spRepetir.setEnabled(false);
		int y = 15;
		this.panel.add(new JLabel("Folio:")).setBounds(15,y,100,20);
		this.panel.add(txtFolio).setBounds(80,y,80,20);
		this.panel.add(btnBuscar).setBounds(170,y,32,20);
		this.panel.add(btnNuevo).setBounds(212,y,65,20);
		this.panel.add(chbStatus).setBounds(280,y,80,20);
		
		this.panel.add(btnizquierda).setBounds(365, y, 30, 21);
		this.panel.add(btnderecha).setBounds(400, y, 30, 21);
		
		this.panel.add(btnModificar).setBounds(435,y,80,20);
		this.panel.add(btnSimilar).setBounds(530,y,80,20);
		
		this.panel.add(chCondicion).setBounds(620,15,80,20);

		this.panel.add(new JLabel("Descripción:")).setBounds(365,y+=25,150,20);
		this.panel.add(scrolltxa).setBounds(365,y+=25,340,410);
		
		this.panel.add(new JLabel("Actividad:")).setBounds(15,y-=25,100,20);
		this.panel.add(scrollact).setBounds(80,y,260,150);
		
		this.panel.add(new JLabel("Respuesta:")).setBounds(15,y+=155,100,20);
		this.panel.add(cmbRespuesta).setBounds(80,y,260,20);
		
		this.panel.add(new JLabel("Atributos:")).setBounds(15,y+=25,100,20);
		this.panel.add(cmbAtributos).setBounds(80,y,260,20);
		
		this.panel.add(new JLabel("Nivel Crítico:")).setBounds(15,y+=25,100,20);
		this.panel.add(cmbNivelCritico).setBounds(80,y,260,20);
		
//		this.panel.add(new JLabel("Hora Inicio:")).setBounds(85, y+=35, 100 , 20);
//		this.HI_spiner.setEditor(HI_editor);
//		this.HI_spiner.setValue(new Time(7,00,00));
//		this.panel.add(HI_spiner).setBounds(155, y, 50,20);
//		
//		this.panel.add(new JLabel("Hora Final:")).setBounds(230, y, 100 , 20);
//		this.HF_spiner.setEditor(HF_editor);
//		this.HF_spiner.setValue(new Time(7,00,00));
//		this.panel.add(HF_spiner).setBounds(290, y, 50,20);
		
		this.panel.add(new JLabel("Temporada:")).setBounds(15,y+=35,100,20);
		this.panel.add(cmbTemporada).setBounds(80,y,260,20);

		this.panel.add(chbCajaDeTrabajo).setBounds(80,y+=30,120,20);

		this.panel.add(new JLabel("Veces/Repetir:")).setBounds(215,y,80,20);
		this.panel.add(spRepetir).setBounds(290,y,50,20);
			
		this.panel.add(btnSalir).setBounds(15,y+=45,90,20);
		this.panel.add(btnLimpiar).setBounds(133,y,90,20);
		this.panel.add(btnGuardar).setBounds(250,y,90,20);
		
		this.cont.add(panel);
		
		this.chbCajaDeTrabajo.addActionListener(opRepetir);
		this.btnNuevo.addActionListener(opNuevo);
		this.btnLimpiar.addActionListener(opLimpiar);
		this.btnGuardar.addActionListener(opGuardar);
		this.btnSalir.addActionListener(opSalir);
		this.btnBuscar.addActionListener(opBuscar);
		this.btnModificar.addActionListener(opModificar);
		this.txtFolio.addKeyListener(numerico_action);
		this.btnSimilar.addActionListener(op_similar);
		this.btnizquierda.addActionListener(opLeft);
		this.btnderecha.addActionListener(opRigth);
		
		txtFolio.setDocument(new JTextFieldLimit(10));
		
	}
	
	ActionListener opRigth = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El campo de texto de folio está vacío", "Error al modificar", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				if(new Obj_Actividad().Existe(Integer.parseInt(txtFolio.getText())+1)==false) {
					JOptionPane.showMessageDialog(null, "El registro no existe", "Error al buscar registro", JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					Obj_Actividad actividad = new Obj_Actividad().Buscar(Integer.parseInt(txtFolio.getText())+1);
					
					txtFolio.setText(Integer.parseInt(txtFolio.getText())+1+"");
					txaActividad.setText(actividad.getActividad());
					txaDescripcion.setText(actividad.getDescripcion());
					
					cmbRespuesta.setSelectedItem(actividad.getRespuesta());
					cmbAtributos.setSelectedItem(actividad.getAtributos());
					cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
					
					cmbTemporada.setSelectedItem(actividad.getTemporada());
					chbCajaDeTrabajo.setSelected(actividad.isCarga());
					spRepetir.setValue(actividad.getRepetir());
					chbStatus.setSelected(actividad.isStatus());
					
					panelEnabledFalse();
					txtFolio.setEditable(true);
					txtFolio.requestFocus();

				}

			}
		}

	};
	
	ActionListener opLeft = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El campo de texto de folio está vacío", "Error al modificar", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				if(txtFolio.getText().equals("1")){
					JOptionPane.showMessageDialog(null, "No hay más registros", "Error al modificar", JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					if(new Obj_Actividad().Existe(Integer.parseInt(txtFolio.getText())-1)==false) {
						JOptionPane.showMessageDialog(null, "El registro no existe", "Error al buscar registro", JOptionPane.WARNING_MESSAGE);
						return;
					}else{
						Obj_Actividad actividad = new Obj_Actividad().Buscar(Integer.parseInt(txtFolio.getText())-1);
						
						txtFolio.setText(Integer.parseInt(txtFolio.getText())-1+"");
						txaActividad.setText(actividad.getActividad());
						txaDescripcion.setText(actividad.getDescripcion());
						
						cmbRespuesta.setSelectedItem(actividad.getRespuesta());
						cmbAtributos.setSelectedItem(actividad.getAtributos());
						cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
						
						cmbTemporada.setSelectedItem(actividad.getTemporada());
						chbCajaDeTrabajo.setSelected(actividad.isCarga());
						spRepetir.setValue(actividad.getRepetir());
						chbStatus.setSelected(actividad.isStatus());
						
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
					}

				}

			}
		}

	};
	
	ActionListener op_similar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtFolio.getText().equals("")){
				panelEnabledTrue();
				txtFolio.setEditable(false);
				txaActividad.requestFocus();
				txtFolio.setText(new Obj_Actividad().Nuevo()+"");
				
			}else{
				JOptionPane.showMessageDialog(null, "Busque un registro primero antes de hacer un similar", "Error al modificar", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opModificar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtFolio.getText().equals("")){
				panelEnabledTrue();
				txtFolio.setEditable(false);
				txaActividad.requestFocus();
				
			}else{
				JOptionPane.showMessageDialog(null, "Busque un registro primero antes de modificar", "Error al modificar", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				new Cat_Filtro_Actividades().setVisible(true);
				dispose();
			}else{
			if(new Obj_Actividad().Existe(Integer.parseInt(txtFolio.getText()))==true) {
				Obj_Actividad actividad = new Obj_Actividad().Buscar(Integer.parseInt(txtFolio.getText()));
				
				txaActividad.setText(actividad.getActividad());
				txaDescripcion.setText(actividad.getDescripcion());
				
				cmbRespuesta.setSelectedItem(actividad.getRespuesta());
				cmbAtributos.setSelectedItem(actividad.getAtributos());
				cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
				
				cmbTemporada.setSelectedItem(actividad.getTemporada());
				chbCajaDeTrabajo.setSelected(actividad.isCarga());
				spRepetir.setValue(actividad.getRepetir());
				chbStatus.setSelected(actividad.isStatus());
				
				panelEnabledFalse();
				txtFolio.setEditable(true);
				txtFolio.requestFocus();
				
			}else{
				JOptionPane.showMessageDialog(null, "El registro no existe", "Error al buscar registro", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		}
	};
	
	ActionListener opSalir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(validaCampos() !=""){
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				Obj_Actividad actividad = new Obj_Actividad();
//				SimpleDateFormat simpledateformat = new SimpleDateFormat("H:mm");
				
				if(new Obj_Actividad().Existe(Integer.parseInt(txtFolio.getText())) == true){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						actividad.setActividad(txaActividad.getText());
						actividad.setDescripcion(txaDescripcion.getText());

						actividad.setRespuesta(cmbRespuesta.getSelectedItem().toString());
						actividad.setAtributos(cmbAtributos.getSelectedItem().toString());
						actividad.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
						
						actividad.setTemporada(cmbTemporada.getSelectedItem().toString());
						actividad.setCarga(chbCajaDeTrabajo.isSelected());
						actividad.setRepetir(Integer.parseInt(spRepetir.getValue().toString()));
						actividad.setStatus(chbStatus.isSelected());
						
						if(actividad.Actualizar(Integer.parseInt(txtFolio.getText()))){
							JOptionPane.showMessageDialog(null, "El registro se actualizó exitosamente!" , "Exito al actualizar!", JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null, "Error al tratar de guardar el registro", "Error al actualizar registro", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
					}else{
						return;
					}
				}else{
					actividad.setActividad(txaActividad.getText());
					actividad.setDescripcion(txaDescripcion.getText());

					actividad.setRespuesta(cmbRespuesta.getSelectedItem().toString());
					actividad.setAtributos(cmbAtributos.getSelectedItem().toString());
					actividad.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
					
					actividad.setTemporada(cmbTemporada.getSelectedItem().toString());
					actividad.setCarga(chbCajaDeTrabajo.isSelected());
					actividad.setRepetir(Integer.parseInt(spRepetir.getValue().toString()));
					actividad.setStatus(chbStatus.isSelected());
					
					if(actividad.Guardar()){
						panelLimpiar();
						JOptionPane.showMessageDialog(null, "El registro se guardó exitosamente!" , "Exito al guardar!", JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						JOptionPane.showMessageDialog(null, "Error al tratar de guardar el registro", "Error al guardar registro", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
				}
			}
		}
	};
	
	public String validaCampos(){
		String error="";
		
		if(txtFolio.getText().equals("")) error += "Folio\n";
		if(txaActividad.getText().equals("")) error += "Actividad\n";
		if(txaDescripcion.getText().equals("")) error += "Descripcion\n";
		if(cmbRespuesta.getSelectedIndex()==0) error += "Respuesta\n";
		if(cmbAtributos.getSelectedIndex()==0) error += "Atributo\n";
		if(cmbNivelCritico.getSelectedIndex()==0) error += "Nivel Crítico\n";
					
		return error;
	}

	ActionListener opLimpiar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			panelEnabledTrue();
			panelLimpiar();
			txtFolio.requestFocus();
		}
		
	};
	
	ActionListener opNuevo = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			panelEnabledTrue();
			panelLimpiar();
			txtFolio.setText(new Obj_Actividad().Nuevo()+"");
			txtFolio.setEditable(false);
			txaActividad.requestFocus();
		}
	};
	
	ActionListener opRepetir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(chbCajaDeTrabajo.isSelected()){
				spRepetir.setEnabled(true);
			}else{
				spRepetir.setEnabled(false);
			}
		}
		
	};
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		txaActividad.setEditable(false);
		txaDescripcion.setEditable(false);
		cmbRespuesta.setEnabled(false);
		cmbAtributos.setEnabled(false);
		cmbNivelCritico.setEnabled(false);
		cmbTemporada.setEnabled(false);
		chbCajaDeTrabajo.setEnabled(false);
		spRepetir.setEnabled(false);
			
	}
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txaActividad.setEditable(true);
		txaDescripcion.setEditable(true);
		cmbRespuesta.setEnabled(true);
		cmbAtributos.setEnabled(true);
		cmbNivelCritico.setEnabled(true);
		cmbTemporada.setEnabled(true);
		chbCajaDeTrabajo.setEnabled(true);
		spRepetir.setEnabled(true);
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txaActividad.setText("");
		txaDescripcion.setText("");
		cmbRespuesta.setSelectedIndex(0);
		cmbAtributos.setSelectedIndex(0);
		cmbNivelCritico.setSelectedIndex(0);
		cmbTemporada.setSelectedIndex(0);
		chbCajaDeTrabajo.setSelected(false);
		spRepetir.setValue(0);
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
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Actividad().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
