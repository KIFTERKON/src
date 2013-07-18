package CatalogoChecador;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerDateModel;

@SuppressWarnings("serial")
public class Cat_Horario2 extends JFrame
{
	JLayeredPane horario2 = new JLayeredPane();
	
	JToggleButton btnDomingo = new JToggleButton("Domingo");
	JToggleButton btnLunes = new JToggleButton("Lunes");
	JToggleButton btnMartes = new JToggleButton("Martes");
	JToggleButton btnMiercoles = new JToggleButton("Miércoles");
	JToggleButton btnJueves = new JToggleButton("Jueves");
	JToggleButton btnViernes = new JToggleButton("Viernes");
	JToggleButton btnSabado = new JToggleButton("Sábado");
	
	JToggleButton btnTurno = new JToggleButton(new ImageIcon("Imagen/2do.png"));
	
	JButton btnIgual = new JButton(new ImageIcon("Imagen/igual.png"));
	
	JTextField txtNombre = new JTextField();
	
	Date date = new Date();
	
      //Domingo//
	  SpinnerDateModel sm1 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spDomingo1 = new JSpinner(sm1);
	  JSpinner.DateEditor de1 = new JSpinner.DateEditor(spDomingo1,"H:mm");
	  
	  SpinnerDateModel sm2 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spDomingo2 = new JSpinner(sm2);
	  JSpinner.DateEditor de2 = new JSpinner.DateEditor(spDomingo2, "EEE H:mm");
	  
	  SpinnerDateModel sm3 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spDomingo3 = new JSpinner(sm3);
	  JSpinner.DateEditor de3 = new JSpinner.DateEditor(spDomingo3, "H:mm");
	  
	  SpinnerDateModel sm4 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spDomingo4 = new JSpinner(sm4);
	  JSpinner.DateEditor de4 = new JSpinner.DateEditor(spDomingo4, "H:mm");
	  
	  SpinnerDateModel sm5 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spDomingo5 = new JSpinner(sm5);
	  JSpinner.DateEditor de5 = new JSpinner.DateEditor(spDomingo5, "H:mm");
	  

	  SpinnerDateModel sm6 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spDomingo6 = new JSpinner(sm6);
	  JSpinner.DateEditor de6 = new JSpinner.DateEditor(spDomingo6, "H:mm");
	 
	  
	  //Lunes//
	  SpinnerDateModel sml1 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spLunes1 = new JSpinner(sml1);
	  JSpinner.DateEditor del1 = new JSpinner.DateEditor(spLunes1, "H:mm");
	  
	  SpinnerDateModel sml2 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spLunes2 = new JSpinner(sml2);
	  JSpinner.DateEditor del2 = new JSpinner.DateEditor(spLunes2, "EEE H:mm");
	  
	  SpinnerDateModel sml3 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spLunes3 = new JSpinner(sml3);
	  JSpinner.DateEditor del3 = new JSpinner.DateEditor(spLunes3, "H:mm");

	  SpinnerDateModel sml4 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spLunes4 = new JSpinner(sml4);
	  JSpinner.DateEditor del4 = new JSpinner.DateEditor(spLunes4, "H:mm");
	  
	  SpinnerDateModel sml5 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spLunes5 = new JSpinner(sml5);
	  JSpinner.DateEditor del5 = new JSpinner.DateEditor(spLunes5, "H:mm");
	  
	  SpinnerDateModel sml6 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spLunes6 = new JSpinner(sml6);
	  JSpinner.DateEditor del6 = new JSpinner.DateEditor(spLunes6, "H:mm");
	  
	  
	  //Martes//
	  SpinnerDateModel smm1 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMartes1 = new JSpinner(smm1);
	  JSpinner.DateEditor dem1 = new JSpinner.DateEditor(spMartes1, "H:mm");
	  
	  SpinnerDateModel smm2 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMartes2 = new JSpinner(smm2);
	  JSpinner.DateEditor dem2 = new JSpinner.DateEditor(spMartes2, "EEE H:mm");
	  
	  SpinnerDateModel smm3 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMartes3 = new JSpinner(smm3);
	  JSpinner.DateEditor dem3 = new JSpinner.DateEditor(spMartes3, "H:mm");
	  
	  SpinnerDateModel smm4 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMartes4 = new JSpinner(smm4);
	  JSpinner.DateEditor dem4 = new JSpinner.DateEditor(spMartes4, "H:mm");
	  
	  SpinnerDateModel smm5 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMartes5 = new JSpinner(smm5);
	  JSpinner.DateEditor dem5 = new JSpinner.DateEditor(spMartes5, "H:mm");
	  
	  SpinnerDateModel smm6 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMartes6 = new JSpinner(smm6);
	  JSpinner.DateEditor dem6 = new JSpinner.DateEditor(spMartes6, "H:mm");
	
	  	
	  //Miercoles//
	  SpinnerDateModel smmi1 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMiercoles1 = new JSpinner(smmi1);
	  JSpinner.DateEditor demmi1 = new JSpinner.DateEditor(spMiercoles1, "H:mm");
	  
	  SpinnerDateModel smmi2 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMiercoles2 = new JSpinner(smmi2);
	  JSpinner.DateEditor demmi2 = new JSpinner.DateEditor(spMiercoles2, "EEE H:mm");
	  
	  SpinnerDateModel smmi3 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMiercoles3 = new JSpinner(smmi3);
	  JSpinner.DateEditor demmi3 = new JSpinner.DateEditor(spMiercoles3, "H:mm");
	  
	  SpinnerDateModel smmi4 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMiercoles4 = new JSpinner(smmi4);
	  JSpinner.DateEditor demmi4 = new JSpinner.DateEditor(spMiercoles4, "H:mm");
	  
	  SpinnerDateModel smmi5 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMiercoles5 = new JSpinner(smmi5);
	  JSpinner.DateEditor demmi5 = new JSpinner.DateEditor(spMiercoles5, "H:mm");
	  
	  SpinnerDateModel smmi6 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spMiercoles6 = new JSpinner(smmi6);
	  JSpinner.DateEditor demmi6 = new JSpinner.DateEditor(spMiercoles6, "H:mm");
	  
	  
	  //Jueves//
	  SpinnerDateModel smj1 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spJueves1 = new JSpinner(smj1);
	  JSpinner.DateEditor dej1 = new JSpinner.DateEditor(spJueves1, "H:mm");
	  
	  SpinnerDateModel smj2 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spJueves2 = new JSpinner(smj2);
	  JSpinner.DateEditor dej2 = new JSpinner.DateEditor(spJueves2, "EEE H:mm");
	  
	  SpinnerDateModel smj3 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spJueves3 = new JSpinner(smj3);
	  JSpinner.DateEditor dej3 = new JSpinner.DateEditor(spJueves3, "H:mm");
	  
	  SpinnerDateModel smj4 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spJueves4 = new JSpinner(smj4);
	  JSpinner.DateEditor dej4 = new JSpinner.DateEditor(spJueves4, "H:mm");
	  
	  SpinnerDateModel smj5 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spJueves5 = new JSpinner(smj5);
	  JSpinner.DateEditor dej5 = new JSpinner.DateEditor(spJueves5, "H:mm");
	  
	  SpinnerDateModel smj6 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spJueves6 = new JSpinner(smj6);
	  JSpinner.DateEditor dej6 = new JSpinner.DateEditor(spJueves6, "H:mm");
	  
	  
	  //Viernes//
	  SpinnerDateModel smv1 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spViernes1 = new JSpinner(smv1);
	  JSpinner.DateEditor dev1 = new JSpinner.DateEditor(spViernes1, "H:mm");
	  
	  SpinnerDateModel smv2 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spViernes2 = new JSpinner(smv2);
	  JSpinner.DateEditor dev2 = new JSpinner.DateEditor(spViernes2, "EEE H:mm");
	  
	  SpinnerDateModel smv3 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spViernes3 = new JSpinner(smv3);
	  JSpinner.DateEditor dev3 = new JSpinner.DateEditor(spViernes3, "H:mm");
	  
	  SpinnerDateModel smv4 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spViernes4 = new JSpinner(smv4);
	  JSpinner.DateEditor dev4 = new JSpinner.DateEditor(spViernes4, "H:mm");
	  
	  SpinnerDateModel smv5 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spViernes5 = new JSpinner(smv5);
	  JSpinner.DateEditor dev5 = new JSpinner.DateEditor(spViernes5, "H:mm");
	  
	  SpinnerDateModel smv6 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spViernes6 = new JSpinner(smv6);
	  JSpinner.DateEditor dev6 = new JSpinner.DateEditor(spViernes6, "H:mm");
	  
	  //Sabado//
	  SpinnerDateModel sms1 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spSabado1 = new JSpinner(sms1);
	  JSpinner.DateEditor des1 = new JSpinner.DateEditor(spSabado1, "H:mm");
	  
	  SpinnerDateModel sms2 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spSabado2 = new JSpinner(sms2);
	  JSpinner.DateEditor des2 = new JSpinner.DateEditor(spSabado2, "EEE H:mm");
	  
	  SpinnerDateModel sms3 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spSabado3 = new JSpinner(sms3);
	  JSpinner.DateEditor des3 = new JSpinner.DateEditor(spSabado3, "H:mm");
	  
	  SpinnerDateModel sms4 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spSabado4 = new JSpinner(sms4);
	  JSpinner.DateEditor des4 = new JSpinner.DateEditor(spSabado4, "H:mm");
	  
	  SpinnerDateModel sms5 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spSabado5 = new JSpinner(sms5);
	  JSpinner.DateEditor des5 = new JSpinner.DateEditor(spSabado5, "H:mm");
	  
	  SpinnerDateModel sms6 =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	  JSpinner spSabado6 = new JSpinner(sms6);
	  JSpinner.DateEditor des6 = new JSpinner.DateEditor(spSabado6, "H:mm");
	
	JCheckBox chLunes = new JCheckBox();
	JCheckBox chMartes = new JCheckBox();
	JCheckBox chMiercoles = new JCheckBox();
	JCheckBox chJueves = new JCheckBox();
	JCheckBox chViernes = new JCheckBox();
	JCheckBox chSabado = new JCheckBox();
	JCheckBox chDomingo = new JCheckBox();
	
	JLabel lblDia = new JLabel("Día");
	JLabel lblLimi = new JLabel("Límites del día");
	JLabel lblInicio = new JLabel("Inicio");
	JLabel lblFin = new JLabel("Fin");
	
	JLabel lblTrabajo = new JLabel("Trabajo");
	JLabel lblEntrada = new JLabel("Entrada");
	JLabel lblSalida = new JLabel("Salida");
	
	JLabel lblComida = new JLabel("Comida");
	JLabel lblSalidaC = new JLabel("Salida");
	JLabel lblRegreso = new JLabel("Regreso");
	
	public Cat_Horario2()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/reloj.png"));
		this.setTitle("Horario");
		
		lblInicio.setFont(new Font("arial", Font.BOLD, 10));
		lblFin.setFont(new Font("arial", Font.BOLD, 10));
		lblEntrada.setFont(new Font("arial", Font.BOLD, 10));
		lblSalida.setFont(new Font("arial", Font.BOLD, 10));
		lblSalidaC.setFont(new Font("arial", Font.BOLD, 10));
		lblRegreso.setFont(new Font("arial", Font.BOLD, 10));
		
		lblLimi.setFont(new Font("Arial Black",Font.BOLD,10));
		lblTrabajo.setFont(new Font("Arial Black",Font.BOLD,10));
		lblComida.setFont(new Font("Arial Black",Font.BOLD,10));
		
		spDomingo1.setEditor(de1);
		spDomingo2.setEditor(de2);
		spDomingo3.setEditor(de3);
		spDomingo4.setEditor(de4);
		spDomingo5.setEditor(de5);
		spDomingo6.setEditor(de6);
		
		spLunes1.setEditor(del1);
		spLunes2.setEditor(del2);
		spLunes3.setEditor(del3);
		spLunes4.setEditor(del4);
		spLunes5.setEditor(del5);
		spLunes6.setEditor(del6);
		
		spMartes1.setEditor(dem1);
		spMartes2.setEditor(dem2);
		spMartes3.setEditor(dem3);
		spMartes4.setEditor(dem4);
		spMartes5.setEditor(dem5);
		spMartes6.setEditor(dem6);
		
		spMiercoles1.setEditor(demmi1);
		spMiercoles2.setEditor(demmi2);
		spMiercoles3.setEditor(demmi3);
		spMiercoles4.setEditor(demmi4);
		spMiercoles5.setEditor(demmi5);
		spMiercoles6.setEditor(demmi6);

		spJueves1.setEditor(dej1);
		spJueves2.setEditor(dej2);
		spJueves3.setEditor(dej3);
		spJueves4.setEditor(dej4);
		spJueves5.setEditor(dej5);
		spJueves6.setEditor(dej6);

		spViernes1.setEditor(dev1);
		spViernes2.setEditor(dev2);
		spViernes3.setEditor(dev3);
		spViernes4.setEditor(dev4);
		spViernes5.setEditor(dev5);
		spViernes6.setEditor(dev6);

		spSabado1.setEditor(des1);
		spSabado2.setEditor(des2);
		spSabado3.setEditor(des3);
		spSabado4.setEditor(des4);
		spSabado5.setEditor(des5);
		spSabado6.setEditor(des6);
		
		horario2.add(lblDia).setBounds			(20,90,50,20);
		horario2.add(btnDomingo).setBounds		(20,120,90,20);
		horario2.add(btnLunes).setBounds		(20,150,90,20);
		horario2.add(btnMartes).setBounds		(20,180,90,20);
		horario2.add(btnMiercoles).setBounds	(20,210,90,20);
		horario2.add(btnJueves).setBounds		(20,240,90,20);
		horario2.add(btnViernes).setBounds		(20,270,90,20);
		horario2.add(btnSabado).setBounds		(20,300,90,20);
		
		this.horario2.add(lblLimi).setBounds(130,60,100,20);
		this.horario2.add(lblTrabajo).setBounds(310,60,60,20);
		this.horario2.add(lblComida).setBounds(490,60,90,20);
		
		this.horario2.add(lblInicio).setBounds(130,90,50,20);
		this.horario2.add(lblFin).setBounds(210,90,50,20);
		
		this.horario2.add(lblEntrada).setBounds(310,90,60,20);
		this.horario2.add(lblSalida).setBounds(390,90,60,20);
		
		this.horario2.add(lblSalidaC).setBounds(505,90,50,20);
		this.horario2.add(lblRegreso).setBounds(585,90,60,20);

		horario2.add(spDomingo1).setBounds(130,120,70,20);
		horario2.add(spDomingo2).setBounds(210,120,70,20);
		horario2.add(spDomingo3).setBounds(310,120,70,20);
		horario2.add(spDomingo4).setBounds(390,120,70,20);
		horario2.add(spDomingo5).setBounds(505,120,70,20);
		horario2.add(spDomingo6).setBounds(585,120,70,20);
		
		horario2.add(chDomingo).setBounds	(480, 120, 18, 18);
		horario2.add(chLunes).setBounds		(480, 150, 18, 18);
		horario2.add(chMartes).setBounds	(480, 180, 18, 18);
		horario2.add(chMiercoles).setBounds	(480, 210, 18, 18);
		horario2.add(chJueves).setBounds	(480, 240, 18, 18);
		horario2.add(chViernes).setBounds	(480, 270, 18, 18);
		horario2.add(chSabado).setBounds	(480, 300, 18, 18);

		horario2.add(spLunes1).setBounds(130,150,70,20);
		horario2.add(spLunes2).setBounds(210,150,70,20);
		horario2.add(spLunes3).setBounds(310,150,70,20);
		horario2.add(spLunes4).setBounds(390,150,70,20);
		horario2.add(spLunes5).setBounds(505,150,70,20);
		horario2.add(spLunes6).setBounds(585,150,70,20);

		horario2.add(spMartes1).setBounds(130,180,70,20);
		horario2.add(spMartes2).setBounds(210,180,70,20);
		horario2.add(spMartes3).setBounds(310,180,70,20);
		horario2.add(spMartes4).setBounds(390,180,70,20);
		horario2.add(spMartes5).setBounds(505,180,70,20);
		horario2.add(spMartes6).setBounds(585,180,70,20);
		
		horario2.add(spMiercoles1).setBounds(130,210,70,20);
		horario2.add(spMiercoles2).setBounds(210,210,70,20);
		horario2.add(spMiercoles3).setBounds(310,210,70,20);
		horario2.add(spMiercoles4).setBounds(390,210,70,20);
		horario2.add(spMiercoles5).setBounds(505,210,70,20);
		horario2.add(spMiercoles6).setBounds(585,210,70,20);

		horario2.add(spJueves1).setBounds(130,240,70,20);
		horario2.add(spJueves2).setBounds(210,240,70,20);
		horario2.add(spJueves3).setBounds(310,240,70,20);
		horario2.add(spJueves4).setBounds(390,240,70,20);
		horario2.add(spJueves5).setBounds(505,240,70,20);
		horario2.add(spJueves6).setBounds(585,240,70,20);
		
		horario2.add(spViernes1).setBounds(130,270,70,20);
		horario2.add(spViernes2).setBounds(210,270,70,20);
		horario2.add(spViernes3).setBounds(310,270,70,20);
		horario2.add(spViernes4).setBounds(390,270,70,20);
		horario2.add(spViernes5).setBounds(505,270,70,20);
		horario2.add(spViernes6).setBounds(585,270,70,20);

		horario2.add(spSabado1).setBounds(130,300,70,20);
		horario2.add(spSabado2).setBounds(210,300,70,20);
		horario2.add(spSabado3).setBounds(310,300,70,20);
		horario2.add(spSabado4).setBounds(390,300,70,20);
		horario2.add(spSabado5).setBounds(505,300,70,20);
		horario2.add(spSabado6).setBounds(585,300,70,20);

		horario2.add(btnIgual).setBounds(620,60,20,20);
		
		horario2.add(new JLabel("Nombre:")).setBounds(20,20,50,20);
		horario2.add(txtNombre).setBounds(70,20,580,20);
		
		
		btnDomingo.addActionListener(Domingo);
		btnLunes.addActionListener(Lunes);
		btnMartes.addActionListener(Martes);
		btnMiercoles.addActionListener(Miercoles);
		btnJueves.addActionListener(Jueves);
		btnViernes.addActionListener(Viernes);
		btnSabado.addActionListener(Sabado);
		btnIgual.addActionListener(igualar);
		
		btnTurno.setToolTipText("Agregar Segundo Horario");
		btnIgual.setToolTipText("Igualar todos los dias");
		
		this.setSize(700,460);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void DomingoOculto()
	{
		spDomingo1.setVisible(false);
		spDomingo2.setVisible(false);
		spDomingo3.setVisible(false);
		spDomingo4.setVisible(false);
		spDomingo5.setVisible(false);
		spDomingo6.setVisible(false);
		chDomingo.setVisible(false);
	}
	
	public void DomingoVisible()
	{
		spDomingo1.setVisible(true);
		spDomingo2.setVisible(true);
		spDomingo3.setVisible(true);
		spDomingo4.setVisible(true);
		spDomingo5.setVisible(true);
		spDomingo6.setVisible(true);
		chDomingo.setVisible(true);
		chDomingo.setVisible(true);
	}
	
	public void LunesOculto()
	{
		spLunes1.setVisible(false);
		spLunes2.setVisible(false);
		spLunes3.setVisible(false);
		spLunes4.setVisible(false);
		spLunes5.setVisible(false);
		spLunes6.setVisible(false);
		chLunes.setVisible(false);
	}
	
	public void LunesVisible()
	{
		spLunes1.setVisible(true);
		spLunes2.setVisible(true);
		spLunes3.setVisible(true);
		spLunes4.setVisible(true);
		spLunes5.setVisible(true);
		spLunes6.setVisible(true);
		chLunes.setVisible(true);
	}
	
	public void MartesOculto()
	{
		spMartes1.setVisible(false);
		spMartes2.setVisible(false);
		spMartes3.setVisible(false);
		spMartes4.setVisible(false);
		spMartes5.setVisible(false);
		spMartes6.setVisible(false);
		chMartes.setVisible(false);
	}
	
	public void MartesVisible()
	{
		spMartes1.setVisible(true);
		spMartes2.setVisible(true);
		spMartes3.setVisible(true);
		spMartes4.setVisible(true);
		spMartes5.setVisible(true);
		spMartes6.setVisible(true);
		chMartes.setVisible(true);
	}
	
	public void MiercolesOculto()
	{
		spMiercoles1.setVisible(false);
		spMiercoles2.setVisible(false);
		spMiercoles3.setVisible(false);
		spMiercoles4.setVisible(false);
		spMiercoles5.setVisible(false);
		spMiercoles6.setVisible(false);
		chMiercoles.setVisible(false);
	}

	public void MiercolesVisible()
	{
		spMiercoles1.setVisible(true);
		spMiercoles2.setVisible(true);
		spMiercoles3.setVisible(true);
		spMiercoles4.setVisible(true);
		spMiercoles5.setVisible(true);
		spMiercoles6.setVisible(true);
		chMiercoles.setVisible(true);
	}
	
	public void JuevesOculto()
	{
		spJueves1.setVisible(false);
		spJueves2.setVisible(false);
		spJueves3.setVisible(false);
		spJueves4.setVisible(false);
		spJueves5.setVisible(false);
		spJueves6.setVisible(false);
		chJueves.setVisible(false);
	}
	
	public void JuevesVisible()
	{
		spJueves1.setVisible(true);
		spJueves2.setVisible(true);
		spJueves3.setVisible(true);
		spJueves4.setVisible(true);
		spJueves5.setVisible(true);
		spJueves6.setVisible(true);
		chJueves.setVisible(true);
	}
	
	public void ViernesOculto()
	{
		spViernes1.setVisible(false);
		spViernes2.setVisible(false);
		spViernes3.setVisible(false);
		spViernes4.setVisible(false);
		spViernes5.setVisible(false);
		spViernes6.setVisible(false);
		chViernes.setVisible(false);
	}
	
	public void ViernesVisible()
	{
		spViernes1.setVisible(true);
		spViernes2.setVisible(true);
		spViernes3.setVisible(true);
		spViernes4.setVisible(true);
		spViernes5.setVisible(true);
		spViernes6.setVisible(true);
		chViernes.setVisible(true);
	}
	
	public void SabadoOculto()
	{
		spSabado1.setVisible(false);
		spSabado2.setVisible(false);
		spSabado3.setVisible(false);
		spSabado4.setVisible(false);
		spSabado5.setVisible(false);
		spSabado6.setVisible(false);
		chSabado.setVisible(false);
	}
	
	public void SabadoVisible()
	{
		spSabado1.setVisible(true);
		spSabado2.setVisible(true);
		spSabado3.setVisible(true);
		spSabado4.setVisible(true);
		spSabado5.setVisible(true);
		spSabado6.setVisible(true);
		chSabado.setVisible(true);
	}
	
	ActionListener Domingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnDomingo.isSelected())
			{DomingoOculto();}else{DomingoVisible();}
		}
	};
	
	ActionListener Lunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnLunes.isSelected())
			{LunesOculto();}else{LunesVisible();}
		}
	};
	
	ActionListener Martes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnMartes.isSelected())
			{MartesOculto();}else{MartesVisible();}
		}
	};
	
	ActionListener Miercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnMiercoles.isSelected())
			{MiercolesOculto();}else{MiercolesVisible();}
		}
	};
	
	ActionListener Jueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnJueves.isSelected())
			{JuevesOculto();}else{JuevesVisible();}
		}
	};
	
	ActionListener Viernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnViernes.isSelected())
			{ViernesOculto();}else{ViernesVisible();}
		}
	};
	
	ActionListener Sabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnSabado.isSelected())
			{SabadoOculto();}else{SabadoVisible();}
		}
	};
	
	ActionListener igualar = new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			//Igualamos todos los campos al primer campo
			spLunes1.setValue(spDomingo1.getValue());
			spMartes1.setValue(spDomingo1.getValue());
			spMiercoles1.setValue(spDomingo1.getValue());
			spJueves1.setValue(spDomingo1.getValue());
			spViernes1.setValue(spDomingo1.getValue());
			spSabado1.setValue(spDomingo1.getValue());
			
			//validamos si el primer checkbox esta seleccionado para asignar los mismos valores a todos los checkbox
			if(chDomingo.isSelected()){
				chLunes.setSelected(true);
				chMartes.setSelected(true);
				chMiercoles.setSelected(true);
				chJueves.setSelected(true);
				chViernes.setSelected(true);
				chSabado.setSelected(true);				
			}else{}
			
			//Igualamos todos los campos al primer campo
			spLunes2.setValue(spDomingo2.getValue());
			spMartes2.setValue(spDomingo2.getValue());
			spMiercoles2.setValue(spDomingo2.getValue());
			spJueves2.setValue(spDomingo2.getValue());
			spViernes2.setValue(spDomingo2.getValue());
			spSabado2.setValue(spDomingo2.getValue());
			
			//Igualamos todos los campos al primer campo
			spLunes3.setValue(spDomingo3.getValue());
			spMartes3.setValue(spDomingo3.getValue());
			spMiercoles3.setValue(spDomingo3.getValue());
			spJueves3.setValue(spDomingo3.getValue());
			spViernes3.setValue(spDomingo3.getValue());
			spSabado3.setValue(spDomingo3.getValue());
			
			//Igualamos todos los campos al primer campo
			spLunes4.setValue(spDomingo4.getValue());
			spMartes4.setValue(spDomingo4.getValue());
			spMiercoles4.setValue(spDomingo4.getValue());
			spJueves4.setValue(spDomingo4.getValue());
			spViernes4.setValue(spDomingo4.getValue());
			spSabado4.setValue(spDomingo4.getValue());
			
			//Igualamos todos los campos al primer campo
			spLunes5.setValue(spDomingo5.getValue());
			spMartes5.setValue(spDomingo5.getValue());
			spMiercoles5.setValue(spDomingo5.getValue());
			spJueves5.setValue(spDomingo5.getValue());
			spViernes5.setValue(spDomingo5.getValue());
			spSabado5.setValue(spDomingo5.getValue());
			
			//Igualamos todos los campos al primer campo
			spLunes6.setValue(spDomingo6.getValue());
			spMartes6.setValue(spDomingo6.getValue());
			spMiercoles6.setValue(spDomingo6.getValue());
			spJueves6.setValue(spDomingo6.getValue());
			spViernes6.setValue(spDomingo6.getValue());
			spSabado6.setValue(spDomingo6.getValue());
		}
	};
}
