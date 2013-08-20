//package CatalogoChecador;
//
//import java.awt.Font;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//@SuppressWarnings("serial")
//public class Cat_Horario2 extends Cat_Horario_base
//{
//	public Cat_Horario2()
//	{
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/reloj.png"));
//		this.setTitle("Horario");
//		
//		lblInicio.setFont(new Font("arial", Font.BOLD, 10));
//		lblFin.setFont(new Font("arial", Font.BOLD, 10));
//		lblEntrada.setFont(new Font("arial", Font.BOLD, 10));
//		lblSalida.setFont(new Font("arial", Font.BOLD, 10));
//		lblReceso.setFont(new Font("arial", Font.BOLD, 10));
//		
//		lblLimi.setFont(new Font("Arial Black",Font.BOLD,10));
//		lblTrabajo.setFont(new Font("Arial Black",Font.BOLD,10));
//		lblComida.setFont(new Font("Arial Black",Font.BOLD,10));
//		
//		spDomingo1.setEditor(de1);
//		spDomingo2.setEditor(de2);
//		spDomingo3.setEditor(de3);
//		spDomingo4.setEditor(de4);
//		spDomingo5.setEditor(de5);
//		
//		spLunes1.setEditor(del1);
//		spLunes2.setEditor(del2);
//		spLunes3.setEditor(del3);
//		spLunes4.setEditor(del4);
//		spLunes5.setEditor(del5);
//		
//		spMartes1.setEditor(dem1);
//		spMartes2.setEditor(dem2);
//		spMartes3.setEditor(dem3);
//		spMartes4.setEditor(dem4);
//		spMartes5.setEditor(dem5);
//		
//		spMiercoles1.setEditor(demmi1);
//		spMiercoles2.setEditor(demmi2);
//		spMiercoles3.setEditor(demmi3);
//		spMiercoles4.setEditor(demmi4);
//		spMiercoles5.setEditor(demmi5);
//
//		spJueves1.setEditor(dej1);
//		spJueves2.setEditor(dej2);
//		spJueves3.setEditor(dej3);
//		spJueves4.setEditor(dej4);
//		spJueves5.setEditor(dej5);
//
//		spViernes1.setEditor(dev1);
//		spViernes2.setEditor(dev2);
//		spViernes3.setEditor(dev3);
//		spViernes4.setEditor(dev4);
//		spViernes5.setEditor(dev5);
//
//		spSabado1.setEditor(des1);
//		spSabado2.setEditor(des2);
//		spSabado3.setEditor(des3);
//		spSabado4.setEditor(des4);
//		spSabado5.setEditor(des5);
//		
//int x=40;
//		
//		horario2.add(lblDia).setBounds			(20,90,50,20);
//		horario2.add(btnDomingo).setBounds		(20,120,90,20);
//		horario2.add(btnLunes).setBounds		(20,150,90,20);
//		horario2.add(btnMartes).setBounds		(20,180,90,20);
//		horario2.add(btnMiercoles).setBounds	(20,210,90,20);
//		horario2.add(btnJueves).setBounds		(20,240,90,20);
//		horario2.add(btnViernes).setBounds		(20,270,90,20);
//		horario2.add(btnSabado).setBounds		(20,300,90,20);
//		
//		this.horario2.add(lblLimi).setBounds(130,60,100,20);
//		this.horario2.add(lblTrabajo).setBounds(310+x,60,60,20);
//		this.horario2.add(lblComida).setBounds(490+x,60,90,20);
//		
//		this.horario2.add(lblInicio).setBounds(130,90,50,20);
//		this.horario2.add(lblFin).setBounds(210,90,50,20);
//		
//		this.horario2.add(lblEntrada).setBounds(310+x,90,60,20);
//		this.horario2.add(lblSalida).setBounds(390+x,90,60,20);
//		
//		this.horario2.add(lblReceso).setBounds(505+x,90,50,20);
//
//		horario2.add(spDomingo1).setBounds(130,120,70,20);
//		horario2.add(spDomingo2).setBounds(210,120,70,20);
//		horario2.add(spDomingo3).setBounds(310+x,120,70,20);
//		horario2.add(spDomingo4).setBounds(390+x,120,70,20);
//		horario2.add(spDomingo5).setBounds(490+x,120,70,20);
//		
//		horario2.add(spLunes1).setBounds(130,150,70,20);
//		horario2.add(spLunes2).setBounds(210,150,70,20);
//		horario2.add(spLunes3).setBounds(310+x,150,70,20);
//		horario2.add(spLunes4).setBounds(390+x,150,70,20);
//		horario2.add(spLunes5).setBounds(490+x,150,70,20);
//
//		horario2.add(spMartes1).setBounds(130,180,70,20);
//		horario2.add(spMartes2).setBounds(210,180,70,20);
//		horario2.add(spMartes3).setBounds(310+x,180,70,20);
//		horario2.add(spMartes4).setBounds(390+x,180,70,20);
//		horario2.add(spMartes5).setBounds(490+x,180,70,20);
//		
//		horario2.add(spMiercoles1).setBounds(130,210,70,20);
//		horario2.add(spMiercoles2).setBounds(210,210,70,20);
//		horario2.add(spMiercoles3).setBounds(310+x,210,70,20);
//		horario2.add(spMiercoles4).setBounds(390+x,210,70,20);
//		horario2.add(spMiercoles5).setBounds(490+x,210,70,20);
//
//		horario2.add(spJueves1).setBounds(130,240,70,20);
//		horario2.add(spJueves2).setBounds(210,240,70,20);
//		horario2.add(spJueves3).setBounds(310+x,240,70,20);
//		horario2.add(spJueves4).setBounds(390+x,240,70,20);
//		horario2.add(spJueves5).setBounds(490+x,240,70,20);
//		
//		horario2.add(spViernes1).setBounds(130,270,70,20);
//		horario2.add(spViernes2).setBounds(210,270,70,20);
//		horario2.add(spViernes3).setBounds(310+x,270,70,20);
//		horario2.add(spViernes4).setBounds(390+x,270,70,20);
//		horario2.add(spViernes5).setBounds(490+x,270,70,20);
//
//		horario2.add(spSabado1).setBounds(130,300,70,20);
//		horario2.add(spSabado2).setBounds(210,300,70,20);
//		horario2.add(spSabado3).setBounds(310+x,300,70,20);
//		horario2.add(spSabado4).setBounds(390+x,300,70,20);
//		horario2.add(spSabado5).setBounds(490+x,300,70,20);
//
//		horario2.add(btnIgual).setBounds(620,60,20,20);
//		
//		btnDomingo.addActionListener(Domingo);
//		btnLunes.addActionListener(Lunes);
//		btnMartes.addActionListener(Martes);
//		btnMiercoles.addActionListener(Miercoles);
//		btnJueves.addActionListener(Jueves);
//		btnViernes.addActionListener(Viernes);
//		btnSabado.addActionListener(Sabado);
//		btnIgual.addActionListener(igualar);
//		
//		btnTurno.setToolTipText("Agregar Segundo Horario");
//		btnIgual.setToolTipText("Igualar todos los dias");
//		
//		this.setSize(700,460);
//		this.setResizable(false);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//	}
//	
//	public void DomingoOculto()
//	{
//		spDomingo1.setVisible(false);
//		spDomingo2.setVisible(false);
//		spDomingo3.setVisible(false);
//		spDomingo4.setVisible(false);
//		spDomingo5.setVisible(false);
//	}
//	
//	public void DomingoVisible()
//	{
//		spDomingo1.setVisible(true);
//		spDomingo2.setVisible(true);
//		spDomingo3.setVisible(true);
//		spDomingo4.setVisible(true);
//		spDomingo5.setVisible(true);
//	}
//	
//	public void LunesOculto()
//	{
//		spLunes1.setVisible(false);
//		spLunes2.setVisible(false);
//		spLunes3.setVisible(false);
//		spLunes4.setVisible(false);
//		spLunes5.setVisible(false);
//	}
//	
//	public void LunesVisible()
//	{
//		spLunes1.setVisible(true);
//		spLunes2.setVisible(true);
//		spLunes3.setVisible(true);
//		spLunes4.setVisible(true);
//		spLunes5.setVisible(true);
//	}
//	
//	public void MartesOculto()
//	{
//		spMartes1.setVisible(false);
//		spMartes2.setVisible(false);
//		spMartes3.setVisible(false);
//		spMartes4.setVisible(false);
//		spMartes5.setVisible(false);
//	}
//	
//	public void MartesVisible()
//	{
//		spMartes1.setVisible(true);
//		spMartes2.setVisible(true);
//		spMartes3.setVisible(true);
//		spMartes4.setVisible(true);
//		spMartes5.setVisible(true);
//	}
//	
//	public void MiercolesOculto()
//	{
//		spMiercoles1.setVisible(false);
//		spMiercoles2.setVisible(false);
//		spMiercoles3.setVisible(false);
//		spMiercoles4.setVisible(false);
//		spMiercoles5.setVisible(false);
//	}
//
//	public void MiercolesVisible()
//	{
//		spMiercoles1.setVisible(true);
//		spMiercoles2.setVisible(true);
//		spMiercoles3.setVisible(true);
//		spMiercoles4.setVisible(true);
//		spMiercoles5.setVisible(true);
//	}
//	
//	public void JuevesOculto()
//	{
//		spJueves1.setVisible(false);
//		spJueves2.setVisible(false);
//		spJueves3.setVisible(false);
//		spJueves4.setVisible(false);
//		spJueves5.setVisible(false);
//	}
//	
//	public void JuevesVisible()
//	{
//		spJueves1.setVisible(true);
//		spJueves2.setVisible(true);
//		spJueves3.setVisible(true);
//		spJueves4.setVisible(true);
//		spJueves5.setVisible(true);
//	}
//	
//	public void ViernesOculto()
//	{
//		spViernes1.setVisible(false);
//		spViernes2.setVisible(false);
//		spViernes3.setVisible(false);
//		spViernes4.setVisible(false);
//		spViernes5.setVisible(false);
//	}
//	
//	public void ViernesVisible()
//	{
//		spViernes1.setVisible(true);
//		spViernes2.setVisible(true);
//		spViernes3.setVisible(true);
//		spViernes4.setVisible(true);
//		spViernes5.setVisible(true);
//	}
//	
//	public void SabadoOculto()
//	{
//		spSabado1.setVisible(false);
//		spSabado2.setVisible(false);
//		spSabado3.setVisible(false);
//		spSabado4.setVisible(false);
//		spSabado5.setVisible(false);
//	}
//	
//	public void SabadoVisible()
//	{
//		spSabado1.setVisible(true);
//		spSabado2.setVisible(true);
//		spSabado3.setVisible(true);
//		spSabado4.setVisible(true);
//		spSabado5.setVisible(true);
//	}
//	
//	ActionListener Domingo = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			if(btnDomingo.isSelected())
//			{DomingoOculto();}else{DomingoVisible();}
//		}
//	};
//	
//	ActionListener Lunes = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			if(btnLunes.isSelected())
//			{LunesOculto();}else{LunesVisible();}
//		}
//	};
//	
//	ActionListener Martes = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			if(btnMartes.isSelected())
//			{MartesOculto();}else{MartesVisible();}
//		}
//	};
//	
//	ActionListener Miercoles = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			if(btnMiercoles.isSelected())
//			{MiercolesOculto();}else{MiercolesVisible();}
//		}
//	};
//	
//	ActionListener Jueves = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			if(btnJueves.isSelected())
//			{JuevesOculto();}else{JuevesVisible();}
//		}
//	};
//	
//	ActionListener Viernes = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			if(btnViernes.isSelected())
//			{ViernesOculto();}else{ViernesVisible();}
//		}
//	};
//	
//	ActionListener Sabado = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			if(btnSabado.isSelected())
//			{SabadoOculto();}else{SabadoVisible();}
//		}
//	};
//	
//	ActionListener igualar = new ActionListener() {
//		public void actionPerformed(ActionEvent e) 
//		{
//			//Igualamos todos los campos al primer campo
//			spLunes1.setValue(spDomingo1.getValue());
//			spMartes1.setValue(spDomingo1.getValue());
//			spMiercoles1.setValue(spDomingo1.getValue());
//			spJueves1.setValue(spDomingo1.getValue());
//			spViernes1.setValue(spDomingo1.getValue());
//			spSabado1.setValue(spDomingo1.getValue());
//			
//			//Igualamos todos los campos al primer campo
//			spLunes2.setValue(spDomingo2.getValue());
//			spMartes2.setValue(spDomingo2.getValue());
//			spMiercoles2.setValue(spDomingo2.getValue());
//			spJueves2.setValue(spDomingo2.getValue());
//			spViernes2.setValue(spDomingo2.getValue());
//			spSabado2.setValue(spDomingo2.getValue());
//			
//			//Igualamos todos los campos al primer campo
//			spLunes3.setValue(spDomingo3.getValue());
//			spMartes3.setValue(spDomingo3.getValue());
//			spMiercoles3.setValue(spDomingo3.getValue());
//			spJueves3.setValue(spDomingo3.getValue());
//			spViernes3.setValue(spDomingo3.getValue());
//			spSabado3.setValue(spDomingo3.getValue());
//			
//			//Igualamos todos los campos al primer campo
//			spLunes4.setValue(spDomingo4.getValue());
//			spMartes4.setValue(spDomingo4.getValue());
//			spMiercoles4.setValue(spDomingo4.getValue());
//			spJueves4.setValue(spDomingo4.getValue());
//			spViernes4.setValue(spDomingo4.getValue());
//			spSabado4.setValue(spDomingo4.getValue());
//			
//			//Igualamos todos los campos al primer campo
//			spLunes5.setValue(spDomingo5.getValue());
//			spMartes5.setValue(spDomingo5.getValue());
//			spMiercoles5.setValue(spDomingo5.getValue());
//			spJueves5.setValue(spDomingo5.getValue());
//			spViernes5.setValue(spDomingo5.getValue());
//			spSabado5.setValue(spDomingo5.getValue());
//		}
//	};
//}
