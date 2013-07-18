package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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
	
	JCheckBox chTodos = new JCheckBox("Todos",false);
	JCheckBox chDomingo = new JCheckBox("Domingo",false);
	JCheckBox chLunes = new JCheckBox("Lunes",false);
	JCheckBox chMartes = new JCheckBox("Martes",false);
	JCheckBox chMiercoles = new JCheckBox("Miércoles",false);
	JCheckBox chJueves = new JCheckBox("Jueves",false);
	JCheckBox chViernes = new JCheckBox("Viernes",false);
	JCheckBox chSabado = new JCheckBox("Sábado",false);
	
	JSpinner spHoraInicio = new JSpinner(new SpinnerNumberModel(0,0,12,1));
	JSpinner spMinutosInicio = new JSpinner(new SpinnerNumberModel(0,0,59,1));
	
	JRadioButton rbtAM = new JRadioButton("AM",true);
	JRadioButton rbtPM = new JRadioButton("PM",false); 
	 
	ButtonGroup grupoRBT = new ButtonGroup();

	
	JSpinner spHoraFin = new JSpinner(new SpinnerNumberModel(0,0,12,1));
	JSpinner spMinutosFin = new JSpinner(new SpinnerNumberModel(0,0,59,1));
	
	JRadioButton rbtAM1 = new JRadioButton("AM",true);
	JRadioButton rbtPM1 = new JRadioButton("PM",false); 
	 
	ButtonGroup grupoRBT1 = new ButtonGroup();
	
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
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	
	public Cat_Actividad(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Car Key.png"));
		this.setTitle("Actividad");
		this.panel.setBorder(BorderFactory.createTitledBorder("Actividad"));
		
		this.spRepetir.setEnabled(false);
		grupoRBT.add(rbtAM);
		grupoRBT.add(rbtPM);
		grupoRBT1.add(rbtAM1);
		grupoRBT1.add(rbtPM1);
		int y = 15;
		this.panel.add(new JLabel("Folio:")).setBounds(15,y,100,20);
		this.panel.add(txtFolio).setBounds(80,y,80,20);
		this.panel.add(btnBuscar).setBounds(170,y,32,20);
		this.panel.add(btnNuevo).setBounds(212,y,65,20);
		this.panel.add(chbStatus).setBounds(280,y,80,20);
		
		this.panel.add(btnModificar).setBounds(365,y,80,20);
		this.panel.add(chCondicion).setBounds(620,15,80,20);
		
		this.panel.add(new JLabel("Descripción:")).setBounds(365,y+=25,150,20);
		this.panel.add(scrolltxa).setBounds(365,y+=25,340,410);
		
		this.panel.add(new JLabel("Actividad:")).setBounds(15,y-=25,100,20);//añadiendo nuevo checkbos de guardado automatico
		this.panel.add(scrollact).setBounds(80,y,260,150);
		
		this.panel.add(new JLabel("Respuesta:")).setBounds(15,y+=155,100,20);
		this.panel.add(cmbRespuesta).setBounds(80,y,260,20);
		
		this.panel.add(new JLabel("Atributos:")).setBounds(15,y+=25,100,20);
		this.panel.add(cmbAtributos).setBounds(80,y,260,20);
		
		this.panel.add(new JLabel("Nivel Crítico:")).setBounds(15,y+=25,100,20);
		this.panel.add(cmbNivelCritico).setBounds(80,y,260,20);
		
		this.panel.add(new JLabel("Día:")).setBounds(15,y+=30,100,20);
		
			this.panel.add(chTodos).setBounds(80,y,60,20);
			this.panel.add(chDomingo).setBounds(147,y,70,20);
			this.panel.add(chLunes).setBounds(217,y,60,20);
			this.panel.add(chMartes).setBounds(280,y,60,20);
			this.panel.add(chMiercoles).setBounds(80,y+=25,70,20);
			this.panel.add(chJueves).setBounds(147,y,60,20);
			this.panel.add(chViernes).setBounds(217,y,60,20);
			this.panel.add(chSabado).setBounds(280,y,70,20);
		
		this.panel.add(new JLabel("Hora Inicio:")).setBounds(15,y+=30,100,20);
			this.panel.add(spHoraInicio).setBounds(85,y,35,20);
			this.panel.add(new JLabel(":")).setBounds(130,y,10,20);
			this.panel.add(spMinutosInicio).setBounds(140,y,35,20);
			this.panel.add(rbtAM).setBounds(217,y,50,20);
			this.panel.add(rbtPM).setBounds(280,y,50,20);

		this.panel.add(new JLabel("Hora Final:")).setBounds(15,y+=25,100,20);
			this.panel.add(spHoraFin).setBounds(85,y,35,20);
			this.panel.add(new JLabel(":")).setBounds(130,y,10,20);
			this.panel.add(spMinutosFin).setBounds(140,y,35,20);
			this.panel.add(rbtAM1).setBounds(217,y,50,20);
			this.panel.add(rbtPM1).setBounds(280,y,50,20);
			
		
		this.panel.add(new JLabel("Temporada:")).setBounds(15,y+=30,100,20);
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
		
		txtFolio.setDocument(new JTextFieldLimit(10));
		this.chTodos.addActionListener(opTodos);
		
		this.setSize(730,530);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	ActionListener opTodos = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chTodos.isSelected()){
				chDomingo.setSelected(true);
				chLunes.setSelected(true);
				chMartes.setSelected(true);
				chMiercoles.setSelected(true);
				chJueves.setSelected(true);
				chViernes.setSelected(true);
				chSabado.setSelected(true);
			}else{
				chDomingo.setSelected(false);
				chLunes.setSelected(false);
				chMartes.setSelected(false);
				chMiercoles.setSelected(false);
				chJueves.setSelected(false);
				chViernes.setSelected(false);
				chSabado.setSelected(false);
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
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtFolio.getText().equals("")){
				
				JOptionPane.showMessageDialog(null, "Introdusca un numero de folio");
			}else{
			if(new Obj_Actividad().Existe(Integer.parseInt(txtFolio.getText()))==true)
			{
				Obj_Actividad actividad = new Obj_Actividad().Buscar(Integer.parseInt(txtFolio.getText()));
				
				txaActividad.setText(actividad.getActividad());
				txaDescripcion.setText(actividad.getDescripcion());
				
				cmbRespuesta.setSelectedItem(actividad.getRespuesta());
				cmbAtributos.setSelectedItem(actividad.getAtributos());
				cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
				
				chDomingo.setSelected(actividad.getDomingo()==1 ? true : false);
				chLunes.setSelected(actividad.getLunes()==1 ? true : false);
				chMartes.setSelected(actividad.getMartes()==1 ? true : false);
				chMiercoles.setSelected(actividad.getMiercoles()==1 ? true : false);
				chJueves.setSelected(actividad.getJueves()==1 ? true : false);
				chViernes.setSelected(actividad.getViernes()==1 ? true : false);
				chSabado.setSelected(actividad.getSabado()==1 ? true : false);
			
				String horaInicio = actividad.getHora_inicio();
						
				spHoraInicio.setValue(Integer.parseInt(horaInicio.substring(0,horaInicio.indexOf(':'))));
				spMinutosInicio.setValue(Integer.parseInt(horaInicio.substring(horaInicio.indexOf(':')+1,horaInicio.indexOf(' '))));
				rbtAM.setSelected((horaInicio.substring(horaInicio.indexOf(' ')+1,horaInicio.length()).equals("AM")) ? true : false);
				rbtPM.setSelected((horaInicio.substring(horaInicio.indexOf(' ')+1,horaInicio.length()).equals("PM")) ? true : false);
				
				String horaFin = actividad.getHora_final();
				
				spHoraFin.setValue(Integer.parseInt(horaFin.substring(0,horaFin.indexOf(':'))));
				spMinutosFin.setValue(Integer.parseInt(horaFin.substring(horaFin.indexOf(':')+1,horaFin.indexOf(' '))));
				rbtAM1.setSelected((horaFin.substring(horaFin.indexOf(' ')+1,horaFin.length()).equals("AM")) ? true : false);
				rbtPM1.setSelected((horaFin.substring(horaFin.indexOf(' ')+1,horaFin.length()).equals("PM")) ? true : false);
				
				cmbTemporada.setSelectedItem(actividad.getTemporada());
				chbCajaDeTrabajo.setSelected(actividad.isCarga());
				spRepetir.setValue(actividad.getRepetir());
				chbStatus.setSelected(actividad.isStatus());
				
				if(chDomingo.isSelected() == true &&
				   chLunes.isSelected() == true &&
				   chMartes.isSelected() == true &&
				   chMiercoles.isSelected() == true &&
				   chJueves.isSelected() == true &&
				   chViernes.isSelected() == true &&
				   chSabado.isSelected() == true){
				   chTodos.setSelected(true);
				}else{
					chTodos.setSelected(false);
				}
				
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
			
			if(new Obj_Actividad().Existe(Integer.parseInt(txtFolio.getText())) == true){
				if(validaCampos() !=""){
					JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}else{
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						Obj_Actividad actividad = new Obj_Actividad();
						
						actividad.setActividad(txaActividad.getText());
						actividad.setDescripcion(txaDescripcion.getText());

						actividad.setRespuesta(cmbRespuesta.getSelectedItem().toString());
						actividad.setAtributos(cmbAtributos.getSelectedItem().toString());
						actividad.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
						
						actividad.setDomingo(chDomingo.isSelected()? 1 : 0);
						actividad.setLunes(chLunes.isSelected()? 1 : 0);
						actividad.setMartes(chMartes.isSelected()? 1 : 0);
						actividad.setMiercoles(chMiercoles.isSelected()? 1 : 0);
						actividad.setJueves(chJueves.isSelected()? 1 : 0);
						actividad.setViernes(chViernes.isSelected()? 1 : 0);
						actividad.setSabado(chSabado.isSelected()? 1 : 0);
						actividad.setHora_inicio(spHoraInicio.getValue()+":"+spMinutosInicio.getValue()+rubro_inicio());	
						actividad.setHora_final(spHoraFin.getValue()+":"+spMinutosFin.getValue()+rubro_fin());
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
				}
			}else{
				if(validaCampos() !="") {
					JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}else{
					Obj_Actividad actividad = new Obj_Actividad();
					
					actividad.setActividad(txaActividad.getText());
					actividad.setDescripcion(txaDescripcion.getText());
					actividad.setRespuesta(cmbRespuesta.getSelectedItem().toString());
					actividad.setAtributos(cmbAtributos.getSelectedItem().toString());
					actividad.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
					actividad.setDomingo(chDomingo.isSelected()? 1 : 0);
					actividad.setLunes(chLunes.isSelected()? 1 : 0);
					actividad.setMartes(chMartes.isSelected()? 1 : 0);
					actividad.setMiercoles(chMiercoles.isSelected()? 1 : 0);
					actividad.setJueves(chJueves.isSelected()? 1 : 0);
					actividad.setViernes(chViernes.isSelected()? 1 : 0);
					actividad.setSabado(chSabado.isSelected()? 1 : 0);
					actividad.setHora_inicio(spHoraInicio.getValue()+":"+spMinutosInicio.getValue()+rubro_inicio());	
					actividad.setHora_final(spHoraFin.getValue()+":"+spMinutosFin.getValue()+rubro_fin());
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
		
//		if(chDomingo.isSelected() == false &&
//		   chLunes.isSelected() == false &&
//		   chMartes.isSelected() == false &&
//		   chMiercoles.isSelected() == false &&
//		   chJueves.isSelected() == false &&
//		   chViernes.isSelected() == false &&
//		   chSabado.isSelected() == false )	error += "Día\n";
													
//		if((spHoraInicio.getValue()+":"+spMinutosInicio.getValue()).equals("0:0")) error += "Hora Inicio\n";	
//		if((spHoraFin.getValue()+":"+spMinutosFin.getValue()).equals("0:0")) error += "Hora Fin\n";
//		if(cmbTemporada.getSelectedIndex()==0) error += "Temporada\n";
		
		return error;
	}

	
	public String rubro_inicio(){
		if(rbtAM.isSelected()){
			return " AM";
		}else{
			return " PM";
		}
	}
	
	public String rubro_fin(){
		if(rbtAM1.isSelected()){
			return " AM";
		}else{
			return " PM";
		}
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
		spHoraInicio.setEnabled(false);
		spMinutosInicio.setEnabled(false);
		spHoraFin.setEnabled(false);
		spMinutosFin.setEnabled(false);
		cmbTemporada.setEnabled(false);
		chbCajaDeTrabajo.setEnabled(false);
		spRepetir.setEnabled(false);
		chDomingo.setEnabled(false);
		chLunes.setEnabled(false);
		chMartes.setEnabled(false);
		chMiercoles.setEnabled(false);
		chJueves.setEnabled(false);
		chViernes.setEnabled(false);
		chSabado.setEnabled(false);
		chTodos.setEnabled(false);
		rbtAM.setEnabled(false);
		rbtAM1.setEnabled(false);
		rbtPM.setEnabled(false);
		rbtPM1.setEnabled(false);
			
	}
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txaActividad.setEditable(true);
		txaDescripcion.setEditable(true);
		cmbRespuesta.setEnabled(true);
		cmbAtributos.setEnabled(true);
		cmbNivelCritico.setEnabled(true);
		spHoraInicio.setEnabled(true);
		spMinutosInicio.setEnabled(true);
		spHoraFin.setEnabled(true);
		spMinutosFin.setEnabled(true);
		cmbTemporada.setEnabled(true);
		chbCajaDeTrabajo.setEnabled(true);
		spRepetir.setEnabled(true);
		chDomingo.setEnabled(true);
		chLunes.setEnabled(true);
		chMartes.setEnabled(true);
		chMiercoles.setEnabled(true);
		chJueves.setEnabled(true);
		chViernes.setEnabled(true);
		chSabado.setEnabled(true);
		chTodos.setEnabled(true);
		rbtAM.setEnabled(true);
		rbtAM1.setEnabled(true);
		rbtPM.setEnabled(true);
		rbtPM1.setEnabled(true);
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txaActividad.setText("");
		txaDescripcion.setText("");
		cmbRespuesta.setSelectedIndex(0);
		cmbAtributos.setSelectedIndex(0);
		cmbNivelCritico.setSelectedIndex(0);
		chTodos.setSelected(false);
		chDomingo.setSelected(false);
		chLunes.setSelected(false);
		chMartes.setSelected(false);
		chMiercoles.setSelected(false);
		chJueves.setSelected(false);
		chViernes.setSelected(false);
		chSabado.setSelected(false);
		rbtAM.setSelected(true);
		rbtAM1.setSelected(true);
		spHoraInicio.setValue(0);
		spMinutosInicio.setValue(0);
		spHoraFin.setValue(0);
		spMinutosFin.setValue(0);
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
