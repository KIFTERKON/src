package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

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
	
	String dias[] = {"Seleccione un dia","Todos","Lunes","Martes","Miercoles","Jueves","Viernes","Savado","Domingo",};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbDias = new JComboBox(dias);
	
	JSpinner spHoraInicio = new JSpinner(new SpinnerNumberModel(0,0,12,1));
	JSpinner spMinutosInicio = new JSpinner(new SpinnerNumberModel(0,0,59,1));
	JSpinner spSegundosInicio = new JSpinner(new SpinnerNumberModel(0,0,0,0));
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbPasDiaInicio = new JComboBox(new String[]{"Selecciona","Am","Pm"});
	
	JSpinner spHoraFin = new JSpinner(new SpinnerNumberModel(0,0,12,1));
	JSpinner spMinutosFin = new JSpinner(new SpinnerNumberModel(0,0,59,1));
	JSpinner spSegundosFin = new JSpinner(new SpinnerNumberModel(0,0,0,0));
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbPasDiaFin = new JComboBox(new String[]{"Selecciona","Am","Pm"});
	
	String temporada[] = new Obj_Temporada().Combo_Temporada();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbTemporada = new JComboBox(temporada);
	
	JCheckBox chbCajaDeTrabajo = new JCheckBox("Asignación de carga de trabajo");
	
	JSpinner spRepetir = new JSpinner(new SpinnerNumberModel(0,0,10,1));
	
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnSalir = new JButton("Salir");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnGuardar = new JButton("Guardar");
	
	public Cat_Actividad(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Car Key.png"));
		this.setTitle("Actividad");
		this.panel.setBorder(BorderFactory.createTitledBorder("Actividad"));
		
//		this.spRepetir.sestEnabled(false);
		
		int y = 15;
		this.panel.add(new JLabel("Folio:")).setBounds(15,y,120,20);
		this.panel.add(txtFolio).setBounds(130,y,150,20);
		
		this.panel.add(chbStatus).setBounds(285,y,80,20);
		
		this.panel.add(new JLabel("Descripción de la actividad")).setBounds(365,y,150,20);
		this.panel.add(btnNuevo).setBounds(605,y,100,20);
		this.panel.add(scrolltxa).setBounds(365,y+=25,340,400);
		
		this.panel.add(new JLabel("Actividad:")).setBounds(15,y,100,20);
		this.panel.add(scrollact).setBounds(130,y,225,150);
		
		this.panel.add(new JLabel("Respuesta:")).setBounds(15,y+=155,100,20);
		this.panel.add(cmbRespuesta).setBounds(130,y,225,20);
		
		this.panel.add(new JLabel("Atributos:")).setBounds(15,y+=25,100,20);
		this.panel.add(cmbAtributos).setBounds(130,y,225,20);
		
		this.panel.add(new JLabel("Nivel Crítico:")).setBounds(15,y+=25,100,20);
		this.panel.add(cmbNivelCritico).setBounds(130,y,225,20);
		
		this.panel.add(new JLabel("Día:")).setBounds(15,y+=25,100,20);
		this.panel.add(cmbDias).setBounds(130,y,225,20);
		
		this.panel.add(new JLabel("Hora de Inicio:")).setBounds(15,y+=25,100,20);
			this.panel.add(spHoraInicio).setBounds(130,y,35,20);
			this.panel.add(new JLabel(":")).setBounds(170,y,10,20);
			this.panel.add(spMinutosInicio).setBounds(180,y,35,20);
			this.panel.add(new JLabel(":")).setBounds(220,y,10,20);
			this.panel.add(spSegundosInicio).setBounds(230,y,35,20);
			this.panel.add(cmbPasDiaInicio).setBounds(275,y,80,20);
			
		this.panel.add(new JLabel("Hora Final")).setBounds(15,y+=25,100,20);
			this.panel.add(spHoraFin).setBounds(130,y,35,20);
			this.panel.add(new JLabel(":")).setBounds(170,y,10,20);
			this.panel.add(spMinutosFin).setBounds(180,y,35,20);
			this.panel.add(new JLabel(":")).setBounds(220,y,10,20);
			this.panel.add(spSegundosFin).setBounds(230,y,35,20);
			this.panel.add(cmbPasDiaFin).setBounds(275,y,80,20);
		
		this.panel.add(new JLabel("Temporada:")).setBounds(15,y+=25,100,20);
		this.panel.add(cmbTemporada).setBounds(130,y,225,20);
		
		this.panel.add(chbCajaDeTrabajo).setBounds(15,y+=25,180,20);
		
		this.panel.add(new JLabel("Veces/Repetir:")).setBounds(220,y,80,20);
		this.panel.add(spRepetir).setBounds(305,y,50,20);
			
		this.panel.add(btnSalir).setBounds(15,y+=45,100,20);
		this.panel.add(btnLimpiar).setBounds(130,y,100,20);
		this.panel.add(btnGuardar).setBounds(255,y,100,20);
		
		this.cont.add(panel);
		
		this.chbCajaDeTrabajo.addActionListener(opRepetir);
		this.btnNuevo.addActionListener(opNuevo);
		
		this.setSize(730,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	ActionListener opNuevo = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			txtFolio.setText(new Obj_Actividad().Nuevo()+"");
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
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Actividad().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
