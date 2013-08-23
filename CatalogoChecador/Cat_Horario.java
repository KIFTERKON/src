package CatalogoChecador;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import ObjetoChecador.ObjHorario;

@SuppressWarnings("serial")
public class Cat_Horario extends Cat_Horario_base
{
//	Cat_Horario2 hora = new Cat_Horario2();                                            
	public void getContenedor(){
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/reloj.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Horario"));	
		this.setTitle("Horario");
		
		lblInicio.setFont(new Font("arial", Font.BOLD, 10));
		lblFin.setFont(new Font("arial", Font.BOLD, 10));
		lblEntrada.setFont(new Font("arial", Font.BOLD, 10));
		lblSalida.setFont(new Font("arial", Font.BOLD, 10));
		lblReceso.setFont(new Font("arial", Font.BOLD, 10));
		
		lblLimi.setFont(new Font("Arial Black",Font.BOLD,10));
		lblTrabajo.setFont(new Font("Arial Black",Font.BOLD,10));
		lblComida.setFont(new Font("Arial Black",Font.BOLD,10));
		
		int x=40;
		
		horario1.add(lblDia).setBounds			(20,90,50,20);
		horario1.add(btnDomingo).setBounds		(20,120,90,20);
		horario1.add(btnLunes).setBounds		(20,150,90,20);
		horario1.add(btnMartes).setBounds		(20,180,90,20);
		horario1.add(btnMiercoles).setBounds	(20,210,90,20);
		horario1.add(btnJueves).setBounds		(20,240,90,20);
		horario1.add(btnViernes).setBounds		(20,270,90,20);
		horario1.add(btnSabado).setBounds		(20,300,90,20);
		
		this.horario1.add(lblLimi).setBounds(130,60,100,20);
		this.horario1.add(lblTrabajo).setBounds(310+x,60,60,20);
		this.horario1.add(lblComida).setBounds(490+x,60,90,20);
		
		this.horario1.add(lblInicio).setBounds(130,90,50,20);
		this.horario1.add(lblFin).setBounds(210,90,50,20);
		
		this.horario1.add(lblEntrada).setBounds(310+x,90,60,20);
		this.horario1.add(lblSalida).setBounds(390+x,90,60,20);
		
		this.horario1.add(lblReceso).setBounds(505+x,90,50,20);

		horario1.add(spDomingo1).setBounds(130,120,70,20);
		horario1.add(spDomingo2).setBounds(210,120,70,20);
		horario1.add(spDomingo3).setBounds(310+x,120,70,20);
		horario1.add(spDomingo4).setBounds(390+x,120,70,20);
		horario1.add(spDomingo5).setBounds(490+x,120,70,20);
		
		horario1.add(spLunes1).setBounds(130,150,70,20);
		horario1.add(spLunes2).setBounds(210,150,70,20);
		horario1.add(spLunes3).setBounds(310+x,150,70,20);
		horario1.add(spLunes4).setBounds(390+x,150,70,20);
		horario1.add(spLunes5).setBounds(490+x,150,70,20);

		horario1.add(spMartes1).setBounds(130,180,70,20);
		horario1.add(spMartes2).setBounds(210,180,70,20);
		horario1.add(spMartes3).setBounds(310+x,180,70,20);
		horario1.add(spMartes4).setBounds(390+x,180,70,20);
		horario1.add(spMartes5).setBounds(490+x,180,70,20);
		
		horario1.add(spMiercoles1).setBounds(130,210,70,20);
		horario1.add(spMiercoles2).setBounds(210,210,70,20);
		horario1.add(spMiercoles3).setBounds(310+x,210,70,20);
		horario1.add(spMiercoles4).setBounds(390+x,210,70,20);
		horario1.add(spMiercoles5).setBounds(490+x,210,70,20);

		horario1.add(spJueves1).setBounds(130,240,70,20);
		horario1.add(spJueves2).setBounds(210,240,70,20);
		horario1.add(spJueves3).setBounds(310+x,240,70,20);
		horario1.add(spJueves4).setBounds(390+x,240,70,20);
		horario1.add(spJueves5).setBounds(490+x,240,70,20);
		
		horario1.add(spViernes1).setBounds(130,270,70,20);
		horario1.add(spViernes2).setBounds(210,270,70,20);
		horario1.add(spViernes3).setBounds(310+x,270,70,20);
		horario1.add(spViernes4).setBounds(390+x,270,70,20);
		horario1.add(spViernes5).setBounds(490+x,270,70,20);

		horario1.add(spSabado1).setBounds(130,300,70,20);
		horario1.add(spSabado2).setBounds(210,300,70,20);
		horario1.add(spSabado3).setBounds(310+x,300,70,20);
		horario1.add(spSabado4).setBounds(390+x,300,70,20);
		horario1.add(spSabado5).setBounds(490+x,300,70,20);

		horario1.add(btnNuevo).setBounds(150,5,70,20);
		horario1.add(btnFiltro).setBounds(220,5,70,20);
		horario1.add(btnIgual).setBounds(620,60,20,20);
		
		horario1.add(lblFolio).setBounds(20,5,50,20);
		horario1.add(txtFolio).setBounds(70,5,70,20);
		
		horario1.add(new JLabel("Nombre:")).setBounds(20,35,50,20);
		horario1.add(txtNombre).setBounds(70,35,580,20);
		
		horario1.add(btnAceptar).setBounds(350,340,80,20);
		horario1.add(btnDeshacer).setBounds(435,340,80,20);
		horario1.add(btnCancelar).setBounds(520,340,80,20);
		
//		panel.add(btnTurno).setBounds(660,20,20,20);
		
		this.panel.add(paneles).setBounds(10,20,675,400);
		this.paneles.addTab("Horario 1", horario1);
		
		btnDomingo.addActionListener(Domingo);
		btnLunes.addActionListener(Lunes);
		btnMartes.addActionListener(Martes);
		btnMiercoles.addActionListener(Miercoles);
		btnJueves.addActionListener(Jueves);
		btnViernes.addActionListener(Viernes);
		btnSabado.addActionListener(Sabado);
		btnIgual.addActionListener(igualar);
		
		btnNuevo.addActionListener(nuevo);
		
		btnCancelar.addActionListener(cancelar);
		btnAceptar.addActionListener(Guardar);
//		btnTurno.addActionListener(turno);
		btnTurno.setToolTipText("Agregar Segundo Horario");
		btnAceptar.setToolTipText("Guardar");
		btnDeshacer.setToolTipText("Deshacer");
		btnDeshacer.addActionListener(deshacer);
		btnCancelar.setToolTipText("Cancelar");
		btnIgual.setToolTipText("Igualar todos los dias");
		txtNombre.addKeyListener(valida);
		
		txtFolio.setEditable(false);
		
//		asigna el foco al JTextField deseado al arrancar la ventana
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
		        txtNombre.requestFocus();
		     }
		});
		
		btnFiltro.addActionListener(filtro);
		
		spDomingo1.setEditor(de1);
		spDomingo2.setEditor(de2);
		spDomingo3.setEditor(de3);
		spDomingo4.setEditor(de4);
		spDomingo5.setEditor(de5);

		spLunes1.setEditor(del1);
		spLunes2.setEditor(del2);
		spLunes3.setEditor(del3);
		spLunes4.setEditor(del4);
		spLunes5.setEditor(del5);
		
		spMartes1.setEditor(dem1);
		spMartes2.setEditor(dem2);
		spMartes3.setEditor(dem3);
		spMartes4.setEditor(dem4);
		spMartes5.setEditor(dem5);
		
		spMiercoles1.setEditor(demmi1);
		spMiercoles2.setEditor(demmi2);
		spMiercoles3.setEditor(demmi3);
		spMiercoles4.setEditor(demmi4);
		spMiercoles5.setEditor(demmi5);

		spJueves1.setEditor(dej1);
		spJueves2.setEditor(dej2);
		spJueves3.setEditor(dej3);
		spJueves4.setEditor(dej4);
		spJueves5.setEditor(dej5);

		spViernes1.setEditor(dev1);
		spViernes2.setEditor(dev2);
		spViernes3.setEditor(dev3);
		spViernes4.setEditor(dev4);
		spViernes5.setEditor(dev5);

		spSabado1.setEditor(des1);
		spSabado2.setEditor(des2);
		spSabado3.setEditor(des3);
		spSabado4.setEditor(des4);
		spSabado5.setEditor(des5);
		
		cont.add(panel);
		this.setSize(700,460);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}		
	
	@SuppressWarnings("deprecation")
	public Cat_Horario(int folio)//String nom
	{
		getContenedor();
		ObjHorario buscar_horario = new ObjHorario().buscar(folio);
		
		
		if(buscar_horario.getFolio() != 0){
			
			System.out.println(buscar_horario.getFolio());
			System.out.println(buscar_horario.getNombre());
			
			System.out.println(buscar_horario.getDiaD()+"    "+buscar_horario.getDomingo1()+" "+buscar_horario.getDomingo2()+" "+buscar_horario.getDomingo3()+" "+buscar_horario.getDomingo4()+" "+buscar_horario.getDomingo5());
			System.out.println(buscar_horario.getDiaL()+"      "+buscar_horario.getLunes1()+" "+buscar_horario.getLunes2()+" "+buscar_horario.getLunes3()+" "+buscar_horario.getLunes4()+" "+buscar_horario.getLunes5());
			System.out.println(buscar_horario.getDiaM()+"     "+buscar_horario.getMartes1()+" "+buscar_horario.getMartes2()+" "+buscar_horario.getMartes3()+" "+buscar_horario.getMartes4()+" "+buscar_horario.getMartes5());
			System.out.println(buscar_horario.getDiaMI()+"  "+buscar_horario.getMiercoles1()+" "+buscar_horario.getMiercoles2()+" "+buscar_horario.getMiercoles3()+" "+buscar_horario.getMiercoles4()+" "+buscar_horario.getMiercoles5());
			System.out.println(buscar_horario.getDiaJ()+"     "+buscar_horario.getJueves1()+" "+buscar_horario.getJueves2()+" "+buscar_horario.getJueves3()+" "+buscar_horario.getJueves4()+" "+buscar_horario.getJueves5());
			System.out.println(buscar_horario.getDiaV()+"    "+buscar_horario.getViernes1()+" "+buscar_horario.getViernes2()+" "+buscar_horario.getViernes3()+" "+buscar_horario.getViernes4()+" "+buscar_horario.getViernes5());
			System.out.println(buscar_horario.getDiaS()+"     "+buscar_horario.getSabado1()+" "+buscar_horario.getSabado2()+" "+buscar_horario.getSabado3()+" "+buscar_horario.getSabado4()+" "+buscar_horario.getSabado5());
			
			System.out.println(buscar_horario.getDescanso());
			
			txtFolio.setText(buscar_horario.getFolio()+"");
			txtNombre.setText(buscar_horario.getNombre());
			
//			ASIGNAR AL BOTON SELECCIONADO Y OCULTAR SU CAMPOS DE DIA DE DESCANSO
			switch(buscar_horario.getDescanso()){
			
			case "D":	btnDomingo.setSelected(true);	 DomingoOculto();	break;
			case "L":	btnLunes.setSelected(true);		 LunesOculto();		break;
			case "M":	btnMartes.setSelected(true); 	 MartesOculto();	break;
			case "MI":	btnMiercoles.setSelected(true);  MiercolesOculto();	break;
			case "J":	btnJueves.setSelected(true); 	 JuevesOculto();	break;
			case "V":	btnViernes.setSelected(true); 	 ViernesOculto();	break;
			case "S":	btnSabado.setSelected(true); 	 SabadoOculto();	break;
			
			}
			
//			DOMINGO
			String[] domingoIn = buscar_horario.getDomingo1().split(":");
			spDomingo1.setValue(new Time(Integer.parseInt(domingoIn[0]),Integer.parseInt(domingoIn[1]),Integer.parseInt(domingoIn[2])));
			
			String[] domingoFin = buscar_horario.getDomingo2().split(":");
			spDomingo2.setValue(new Time(Integer.parseInt(domingoFin[0]),Integer.parseInt(domingoFin[1]),Integer.parseInt(domingoFin[2])));
			
			String[] domingoEn = buscar_horario.getDomingo3().split(":");
			spDomingo3.setValue(new Time(Integer.parseInt(domingoEn[0]),Integer.parseInt(domingoEn[1]),Integer.parseInt(domingoEn[2])));
			
			String[] domingoSal = buscar_horario.getDomingo4().split(":");
			spDomingo4.setValue(new Time(Integer.parseInt(domingoSal[0]),Integer.parseInt(domingoSal[1]),Integer.parseInt(domingoSal[2])));
			
			String[] domingoRec = buscar_horario.getDomingo5().split(":");
			spDomingo5.setValue(new Time(Integer.parseInt(domingoRec[0]),Integer.parseInt(domingoRec[1]),Integer.parseInt(domingoRec[2])));
			
//			LUNES
			String[] lunesIn = buscar_horario.getLunes1().split(":");
			spLunes1.setValue(new Time(Integer.parseInt(lunesIn[0]),Integer.parseInt(lunesIn[1]),Integer.parseInt(lunesIn[2])));
			
			String[] lunesFin = buscar_horario.getLunes2().split(":");
			spLunes2.setValue(new Time(Integer.parseInt(lunesFin[0]),Integer.parseInt(lunesFin[1]),Integer.parseInt(lunesFin[2])));
			
			String[] lunesEn = buscar_horario.getLunes3().split(":");
			spLunes3.setValue(new Time(Integer.parseInt(lunesEn[0]),Integer.parseInt(lunesEn[1]),Integer.parseInt(lunesEn[2])));
			
			String[] lunesSal = buscar_horario.getLunes4().split(":");
			spLunes4.setValue(new Time(Integer.parseInt(lunesSal[0]),Integer.parseInt(lunesSal[1]),Integer.parseInt(lunesSal[2])));
			
			String[] lunesRec = buscar_horario.getLunes5().split(":");
			spLunes5.setValue(new Time(Integer.parseInt(lunesRec[0]),Integer.parseInt(lunesRec[1]),Integer.parseInt(lunesRec[2])));
			
//			MARTES
			String[] martesIn = buscar_horario.getMartes1().split(":");
			spMartes1.setValue(new Time(Integer.parseInt(martesIn[0]),Integer.parseInt(martesIn[1]),Integer.parseInt(martesIn[2])));
			
			String[] martesFin = buscar_horario.getMartes2().split(":");
			spMartes2.setValue(new Time(Integer.parseInt(martesFin[0]),Integer.parseInt(martesFin[1]),Integer.parseInt(martesFin[2])));
			
			String[] martesEn = buscar_horario.getMartes3().split(":");
			spMartes3.setValue(new Time(Integer.parseInt(martesEn[0]),Integer.parseInt(martesEn[1]),Integer.parseInt(martesEn[2])));
			
			String[] martesSal = buscar_horario.getMartes4().split(":");
			spMartes4.setValue(new Time(Integer.parseInt(martesSal[0]),Integer.parseInt(martesSal[1]),Integer.parseInt(martesSal[2])));
			
			String[] martesRec = buscar_horario.getMartes5().split(":");
			spMartes5.setValue(new Time(Integer.parseInt(martesRec[0]),Integer.parseInt(martesRec[1]),Integer.parseInt(martesRec[2])));
			
//			MIERCOLES
			String[] miercolesIn = buscar_horario.getMiercoles1().split(":");
			spMiercoles1.setValue(new Time(Integer.parseInt(miercolesIn[0]),Integer.parseInt(miercolesIn[1]),Integer.parseInt(miercolesIn[2])));
			
			String[] miercolesFin = buscar_horario.getMiercoles2().split(":");
			spMiercoles2.setValue(new Time(Integer.parseInt(miercolesFin[0]),Integer.parseInt(miercolesFin[1]),Integer.parseInt(miercolesFin[2])));
			
			String[] miercolesEn = buscar_horario.getMiercoles3().split(":");
			spMiercoles3.setValue(new Time(Integer.parseInt(miercolesEn[0]),Integer.parseInt(miercolesEn[1]),Integer.parseInt(miercolesEn[2])));
			
			String[] miercolesSal = buscar_horario.getMiercoles4().split(":");
			spMiercoles4.setValue(new Time(Integer.parseInt(miercolesSal[0]),Integer.parseInt(miercolesSal[1]),Integer.parseInt(miercolesSal[2])));
			
			String[] miercolesRec = buscar_horario.getMiercoles5().split(":");
			spMiercoles5.setValue(new Time(Integer.parseInt(miercolesRec[0]),Integer.parseInt(miercolesRec[1]),Integer.parseInt(miercolesRec[2])));
			
//			JUEVES
			String[] juevesIn = buscar_horario.getJueves1().split(":");
			spJueves1.setValue(new Time(Integer.parseInt(juevesIn[0]),Integer.parseInt(juevesIn[1]),Integer.parseInt(juevesIn[2])));
			
			String[] juevesFin = buscar_horario.getJueves2().split(":");
			spJueves2.setValue(new Time(Integer.parseInt(juevesFin[0]),Integer.parseInt(juevesFin[1]),Integer.parseInt(juevesFin[2])));
			
			String[] juevesEn = buscar_horario.getJueves3().split(":");
			spJueves3.setValue(new Time(Integer.parseInt(juevesEn[0]),Integer.parseInt(juevesEn[1]),Integer.parseInt(juevesEn[2])));
			
			String[] juevesSal = buscar_horario.getJueves4().split(":");
			spJueves4.setValue(new Time(Integer.parseInt(juevesSal[0]),Integer.parseInt(juevesSal[1]),Integer.parseInt(juevesSal[2])));
			
			String[] juevesRec = buscar_horario.getJueves5().split(":");
			spJueves5.setValue(new Time(Integer.parseInt(juevesRec[0]),Integer.parseInt(juevesRec[1]),Integer.parseInt(juevesRec[2])));
			
//			VIERNES
			String[] viernesIn = buscar_horario.getViernes1().split(":");
			spViernes1.setValue(new Time(Integer.parseInt(viernesIn[0]),Integer.parseInt(viernesIn[1]),Integer.parseInt(viernesIn[2])));
			
			String[] viernesFin = buscar_horario.getViernes2().split(":");
			spViernes2.setValue(new Time(Integer.parseInt(viernesFin[0]),Integer.parseInt(viernesFin[1]),Integer.parseInt(viernesFin[2])));
			
			String[] viernesEn = buscar_horario.getViernes3().split(":");
			spViernes3.setValue(new Time(Integer.parseInt(viernesEn[0]),Integer.parseInt(viernesEn[1]),Integer.parseInt(viernesEn[2])));
			
			String[] viernesSal = buscar_horario.getViernes4().split(":");
			spViernes4.setValue(new Time(Integer.parseInt(viernesSal[0]),Integer.parseInt(viernesSal[1]),Integer.parseInt(viernesSal[2])));
			
			String[] viernesRec = buscar_horario.getViernes5().split(":");
			spViernes5.setValue(new Time(Integer.parseInt(viernesRec[0]),Integer.parseInt(viernesRec[1]),Integer.parseInt(viernesRec[2])));
			
//			SABADO
			String[] sabadoIn = buscar_horario.getSabado1().split(":");
			spSabado1.setValue(new Time(Integer.parseInt(sabadoIn[0]),Integer.parseInt(sabadoIn[1]),Integer.parseInt(sabadoIn[2])));
			
			String[] sabadoFin = buscar_horario.getSabado2().split(":");
			spSabado2.setValue(new Time(Integer.parseInt(sabadoFin[0]),Integer.parseInt(sabadoFin[1]),Integer.parseInt(sabadoFin[2])));
			
			String[] sabadoEn = buscar_horario.getSabado3().split(":");
			spSabado3.setValue(new Time(Integer.parseInt(sabadoEn[0]),Integer.parseInt(sabadoEn[1]),Integer.parseInt(sabadoEn[2])));
			
			String[] sabadoSal = buscar_horario.getSabado4().split(":");
			spSabado4.setValue(new Time(Integer.parseInt(sabadoSal[0]),Integer.parseInt(sabadoSal[1]),Integer.parseInt(sabadoSal[2])));
			
			String[] sabadoRec = buscar_horario.getSabado5().split(":");
			spSabado5.setValue(new Time(Integer.parseInt(sabadoRec[0]),Integer.parseInt(sabadoRec[1]),Integer.parseInt(sabadoRec[2])));
						
		}else{
			
			}
	}
	
	public Cat_Horario()
	{
		getContenedor();
		
	}
	ActionListener filtro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
			new Filtro_Horario().setVisible(true);
		}
	};
	public void DomingoOculto()
	{
		spDomingo1.setVisible(false);
		spDomingo2.setVisible(false);
		spDomingo3.setVisible(false);
		spDomingo4.setVisible(false);
		spDomingo5.setVisible(false);
	}
	
	public void DomingoVisible()
	{
		spDomingo1.setVisible(true);
		spDomingo2.setVisible(true);
		spDomingo3.setVisible(true);
		spDomingo4.setVisible(true);
		spDomingo5.setVisible(true);
	}
	
	public void LunesOculto()
	{
		spLunes1.setVisible(false);
		spLunes2.setVisible(false);
		spLunes3.setVisible(false);
		spLunes4.setVisible(false);
		spLunes5.setVisible(false);
	}
	
	public void LunesVisible()
	{
		spLunes1.setVisible(true);
		spLunes2.setVisible(true);
		spLunes3.setVisible(true);
		spLunes4.setVisible(true);
		spLunes5.setVisible(true);
	}
	
	public void MartesOculto()
	{
		spMartes1.setVisible(false);
		spMartes2.setVisible(false);
		spMartes3.setVisible(false);
		spMartes4.setVisible(false);
		spMartes5.setVisible(false);
	}
	
	public void MartesVisible()
	{
		spMartes1.setVisible(true);
		spMartes2.setVisible(true);
		spMartes3.setVisible(true);
		spMartes4.setVisible(true);
		spMartes5.setVisible(true);
	}
	
	public void MiercolesOculto()
	{
		spMiercoles1.setVisible(false);
		spMiercoles2.setVisible(false);
		spMiercoles3.setVisible(false);
		spMiercoles4.setVisible(false);
		spMiercoles5.setVisible(false);
	}

	public void MiercolesVisible()
	{
		spMiercoles1.setVisible(true);
		spMiercoles2.setVisible(true);
		spMiercoles3.setVisible(true);
		spMiercoles4.setVisible(true);
		spMiercoles5.setVisible(true);
	}
	
	public void JuevesOculto()
	{
		spJueves1.setVisible(false);
		spJueves2.setVisible(false);
		spJueves3.setVisible(false);
		spJueves4.setVisible(false);
		spJueves5.setVisible(false);
	}
	
	public void JuevesVisible()
	{
		spJueves1.setVisible(true);
		spJueves2.setVisible(true);
		spJueves3.setVisible(true);
		spJueves4.setVisible(true);
		spJueves5.setVisible(true);
	}
	
	public void ViernesOculto()
	{
		spViernes1.setVisible(false);
		spViernes2.setVisible(false);
		spViernes3.setVisible(false);
		spViernes4.setVisible(false);
		spViernes5.setVisible(false);

	}
	
	public void ViernesVisible()
	{
		spViernes1.setVisible(true);
		spViernes2.setVisible(true);
		spViernes3.setVisible(true);
		spViernes4.setVisible(true);
		spViernes5.setVisible(true);
	}
	
	public void SabadoOculto()
	{
		spSabado1.setVisible(false);
		spSabado2.setVisible(false);
		spSabado3.setVisible(false);
		spSabado4.setVisible(false);
		spSabado5.setVisible(false);
	}
	
	public void SabadoVisible()
	{
		spSabado1.setVisible(true);
		spSabado2.setVisible(true);
		spSabado3.setVisible(true);
		spSabado4.setVisible(true);
		spSabado5.setVisible(true);
	}
	ActionListener deshacer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			resetear();
		}
	};
	
//	ActionListener turno = new ActionListener() {
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			if(btnTurno.isSelected())
//			{
//				paneles.addTab("Horario 2",hora.horario2);
//			}else{
//				paneles.remove(hora.horario2);
//				}
//		}
//	};
	
	public void resetear()
	{
		 
		spDomingo1.setValue(date);
		spDomingo2.setValue(date);
		spDomingo3.setValue(date);
		spDomingo4.setValue(date);
		spDomingo5.setValue(date);
		
		spLunes1.setValue(date);
		spLunes2.setValue(date);
		spLunes3.setValue(date);
		spLunes4.setValue(date);
		spLunes5.setValue(date);
		
		spMartes1.setValue(date);
		spMartes2.setValue(date);
		spMartes3.setValue(date);
		spMartes4.setValue(date);
		spMartes5.setValue(date);
		
		spMiercoles1.setValue(date);
		spMiercoles2.setValue(date);
		spMiercoles3.setValue(date);
		spMiercoles4.setValue(date);
		spMiercoles5.setValue(date);
		
		spJueves1.setValue(date);
		spJueves2.setValue(date);
		spJueves3.setValue(date);
		spJueves4.setValue(date);
		spJueves5.setValue(date);
		
		spViernes1.setValue(date);
		spViernes2.setValue(date);
		spViernes3.setValue(date);
		spViernes4.setValue(date);
		spViernes5.setValue(date);
		
		spSabado1.setValue(date);
		spSabado2.setValue(date);
		spSabado3.setValue(date);
		spSabado4.setValue(date);
		spSabado5.setValue(date);

		DomingoVisible();
		LunesVisible();
		MartesVisible();
		MiercolesVisible();
		JuevesVisible();
		ViernesVisible();
		SabadoVisible();
		txtNombre.setText("");
		
		//Reseteamos a la hora del dia todos los campos de la segunda ventana
//		hora.spDomingo1.setValue(date);
//		hora.spDomingo2.setValue(date);
//		hora.spDomingo3.setValue(date);
//		hora.spDomingo4.setValue(date);
//		hora.spDomingo5.setValue(date);
//				
//		hora.spLunes1.setValue(date);
//		hora.spLunes2.setValue(date);
//		hora.spLunes3.setValue(date);
//		hora.spLunes4.setValue(date);
//		hora.spLunes5.setValue(date);
//				
//		hora.spMartes1.setValue(date);
//		hora.spMartes2.setValue(date);
//		hora.spMartes3.setValue(date);
//		hora.spMartes4.setValue(date);
//		hora.spMartes5.setValue(date);
//				
//		hora.spMiercoles1.setValue(date);
//		hora.spMiercoles2.setValue(date);
//		hora.spMiercoles3.setValue(date);
//		hora.spMiercoles4.setValue(date);
//		hora.spMiercoles5.setValue(date);
//				
//		hora.spJueves1.setValue(date);
//		hora.spJueves2.setValue(date);
//		hora.spJueves3.setValue(date);
//		hora.spJueves4.setValue(date);
//		hora.spJueves5.setValue(date);
//				
//		hora.spViernes1.setValue(date);
//		hora.spViernes2.setValue(date);
//		hora.spViernes3.setValue(date);
//		hora.spViernes4.setValue(date);
//		hora.spViernes5.setValue(date);
//		
//		hora.spSabado1.setValue(date);
//		hora.spSabado2.setValue(date);
//		hora.spSabado3.setValue(date);
//		hora.spSabado4.setValue(date);
//		hora.spSabado5.setValue(date);
//		
//		hora.DomingoVisible();
//		hora.LunesVisible();
//		hora.MartesVisible();
//		hora.MiercolesVisible();
//		hora.JuevesVisible();
//		hora.ViernesVisible();
//		hora.SabadoVisible();
//		txtNombre.setText("");
//		
//		btnDomingo.setSelected(false);
//		btnLunes.setSelected(false);
//		btnMartes.setSelected(false);
//		btnMiercoles.setSelected(false);
//		btnJueves.setSelected(false);
//		btnViernes.setSelected(false);
//		btnSabado.setSelected(false);
//
//		hora.btnDomingo.setSelected(false);
//		hora.btnLunes.setSelected(false);
//		btnMartes.setSelected(false);
//		hora.btnMiercoles.setSelected(false);
//		hora.btnJueves.setSelected(false);
//		hora.btnViernes.setSelected(false);
//		hora.btnSabado.setSelected(false);
	}
	@SuppressWarnings({ "deprecation", "unused" })
	public void preguntas()
	{
		ObjHorario horario = new ObjHorario();
		if(btnDomingo.isSelected()==true)
		{
			spDomingo1.setValue(new Date(00,00,00));
			spDomingo2.setValue(new Date(00,00,00));
			spDomingo3.setValue(new Date(00,00,00));
			spDomingo4.setValue(new Date(00,00,00));
			spDomingo5.setValue(new Date(00,00,00));
			Descanso="D";
		}
		if (btnLunes.isSelected()==true)
		{
			spLunes1.setValue(new Date(00,00,00));
			spLunes2.setValue(new Date(00,00,00));
			spLunes3.setValue(new Date(00,00,00));
			spLunes4.setValue(new Date(00,00,00));
			spLunes5.setValue(new Date(00,00,00));
			Descanso="L";
		}
		if (btnMartes.isSelected()==true)
		{
			spMartes1.setValue(new Date(00,00,00));
			spMartes2.setValue(new Date(00,00,00));
			spMartes3.setValue(new Date(00,00,00));
			spMartes4.setValue(new Date(00,00,00));
			spMartes5.setValue(new Date(00,00,00));
			Descanso="M";
		}
		if (btnMiercoles.isSelected()==true)
		{
			spMiercoles1.setValue(new Date(00,00,00));
			spMiercoles2.setValue(new Date(00,00,00));
			spMiercoles3.setValue(new Date(00,00,00));
			spMiercoles4.setValue(new Date(00,00,00));
			spMiercoles5.setValue(new Date(00,00,00));
			Descanso="MI";
		}
		if (btnJueves.isSelected()==true)
		{
			spJueves1.setValue(new Date(00,00,00));
			spJueves2.setValue(new Date(00,00,00));
			spJueves3.setValue(new Date(00,00,00));
			spJueves4.setValue(new Date(00,00,00));
			spJueves5.setValue(new Date(00,00,00));
			Descanso="J";
		}
		if (btnViernes.isSelected()==true)
		{
			spViernes1.setValue(new Date(00,00,00));
			spViernes2.setValue(new Date(00,00,00));
			spViernes3.setValue(new Date(00,00,00));
			spViernes4.setValue(new Date(00,00,00));
			spViernes5.setValue(new Date(00,00,00));
			Descanso="V";
		}
		if (btnSabado.isSelected()==true)
		{
			spSabado1.setValue(new Date(00,00,00));
			spSabado2.setValue(new Date(00,00,00));
			spSabado3.setValue(new Date(00,00,00));
			spSabado4.setValue(new Date(00,00,00));
			spSabado5.setValue(new Date(00,00,00));
			Descanso="S";
		}
	}
	
	ActionListener Guardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				ObjHorario horario = new ObjHorario().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(new ObjHorario().Existe(Integer.parseInt(txtFolio.getText())) == true){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(txtNombre.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "El Nombre es Requerido:", "Error al Actualizar el Registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
//							actualizar
							preguntas();
							
							SimpleDateFormat sdf = new SimpleDateFormat ("H:mm");
							
//							SimpleDateFormat sdf1 = new SimpleDateFormat ("E H:mm");
							
							//Domingo
							String Domingo_resultado1 = sdf.format ((Date) spDomingo1.getValue());
							String Domingo_resultado2 = sdf.format((Date) spDomingo2.getValue());
							String Domingo_resultado3 = sdf.format ((Date) spDomingo3.getValue());
							String Domingo_resultado4 = sdf.format ((Date) spDomingo4.getValue());
							String Domingo_resultado5 = sdf.format ((Date) spDomingo5.getValue());
							
							//Lunes
							String Lunes_resultado1 = sdf.format ((Date) spLunes1.getValue());
							String Lunes_resultado2 = sdf.format((Date) spLunes2.getValue());
							String Lunes_resultado3 = sdf.format ((Date) spLunes3.getValue());
							String Lunes_resultado4 = sdf.format ((Date) spLunes4.getValue());
							String Lunes_resultado5 = sdf.format ((Date) spLunes5.getValue());
							
							//Martes
							String Martes_resultado1 = sdf.format ((Date) spMartes1.getValue());
							String Martes_resultado2 = sdf.format((Date)spMartes2.getValue());
							String Martes_resultado3 = sdf.format ((Date) spMartes3.getValue());
							String Martes_resultado4 = sdf.format ((Date) spMartes4.getValue());
							String Martes_resultado5 = sdf.format ((Date) spMartes5.getValue());
							
							//Miercoles
							String Miercoles_resultado1 = sdf.format ((Date) spMiercoles1.getValue());
							String Miercoles_resultado2 = sdf.format ((Date) spMiercoles2.getValue());
							String Miercoles_resultado3 = sdf.format ((Date) spMiercoles3.getValue());
							String Miercoles_resultado4 = sdf.format ((Date) spMiercoles4.getValue());
							String Miercoles_resultado5 = sdf.format ((Date) spMiercoles5.getValue());
							
							//Jueves
							String Jueves_resultado1 = sdf.format ((Date) spJueves1.getValue());
							String Jueves_resultado2 = sdf.format ((Date) spJueves2.getValue());
							String Jueves_resultado3 = sdf.format ((Date) spJueves3.getValue());
							String Jueves_resultado4 = sdf.format ((Date) spJueves4.getValue());
							String Jueves_resultado5 = sdf.format ((Date) spJueves5.getValue());
							
							//Viernes
							String Viernes_resultado1 = sdf.format ((Date) spViernes1.getValue());
							String Viernes_resultado2 = sdf.format ((Date) spViernes2.getValue());
							String Viernes_resultado3 = sdf.format ((Date) spViernes3.getValue());
							String Viernes_resultado4 = sdf.format ((Date) spViernes4.getValue());
							String Viernes_resultado5 = sdf.format ((Date) spViernes5.getValue());
							
							//Sabado
							String Sabado_resultado1 = sdf.format ((Date) spSabado1.getValue());
							String Sabado_resultado2 = sdf.format ((Date) spSabado2.getValue());
							String Sabado_resultado3 = sdf.format ((Date) spSabado3.getValue());
							String Sabado_resultado4 = sdf.format ((Date) spSabado4.getValue());
							String Sabado_resultado5 = sdf.format ((Date) spSabado5.getValue());
							
							
							ObjHorario horario_emp = new ObjHorario();
							
								//Asignamos los datos
								horario_emp.setFolio(Integer.parseInt(txtFolio.getText()));
								horario_emp.setNombre(txtNombre.getText().toUpperCase());
								
								horario_emp.setDomingo1(Domingo_resultado1);
								horario_emp.setDomingo2(Domingo_resultado2);
								horario_emp.setDomingo3(Domingo_resultado3);
								horario_emp.setDomingo4(Domingo_resultado4);
								horario_emp.setDomingo5(Domingo_resultado5);
								
								horario_emp.setLunes1(Lunes_resultado1);
								horario_emp.setLunes2(Lunes_resultado2);
								horario_emp.setLunes3(Lunes_resultado3);
								horario_emp.setLunes4(Lunes_resultado4);
								horario_emp.setLunes5(Lunes_resultado5);
								
								horario_emp.setMartes1(Martes_resultado1);
								horario_emp.setMartes2(Martes_resultado2);
								horario_emp.setMartes3(Martes_resultado3);
								horario_emp.setMartes4(Martes_resultado4);
								horario_emp.setMartes5(Martes_resultado5);
								
								horario_emp.setMiercoles1(Miercoles_resultado1);
								horario_emp.setMiercoles2(Miercoles_resultado2);
								horario_emp.setMiercoles3(Miercoles_resultado3);
								horario_emp.setMiercoles4(Miercoles_resultado4);
								horario_emp.setMiercoles5(Miercoles_resultado5);
								
								horario_emp.setJueves1(Jueves_resultado1);
								horario_emp.setJueves2(Jueves_resultado2);
								horario_emp.setJueves3(Jueves_resultado3);
								horario_emp.setJueves4(Jueves_resultado4);
								horario_emp.setJueves5(Jueves_resultado5);
								
								horario_emp.setViernes1(Viernes_resultado1);
								horario_emp.setViernes2(Viernes_resultado2);
								horario_emp.setViernes3(Viernes_resultado3);
								horario_emp.setViernes4(Viernes_resultado4);
								horario_emp.setViernes5(Viernes_resultado5);
								
								horario_emp.setSabado1(Sabado_resultado1);
								horario_emp.setSabado2(Sabado_resultado2);
								horario_emp.setSabado3(Sabado_resultado3);
								horario_emp.setSabado4(Sabado_resultado4);
								horario_emp.setSabado5(Sabado_resultado5);
								
								horario_emp.setDescanso(Descanso);
								
								if(horario_emp.Actualizar(Integer.parseInt(txtFolio.getText()))){
									
									JOptionPane.showMessageDialog(null, "El registro se Actualizo exitosamente!" , "Exito al Actualizar!", JOptionPane.INFORMATION_MESSAGE);
//									resetear();
									return;
								}else{
									JOptionPane.showMessageDialog(null, "Error al tratar de Actualizar el registro", "Error al Actualizar registro", JOptionPane.WARNING_MESSAGE);
									return;
								}
						}
					}
					
				}else{
//						guardar
						if(txtNombre.getText().equals(""))
						{
								JOptionPane.showMessageDialog(null, "El Nombre es Requerido:", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
								return;
						}else{
							preguntas();
							SimpleDateFormat sdf = new SimpleDateFormat ("H:mm");
							
//							SimpleDateFormat sdf1 = new SimpleDateFormat ("E H:mm");
							
							//Domingo
							String Domingo_resultado1 = sdf.format ((Date) spDomingo1.getValue());
							String Domingo_resultado2 = sdf.format((Date) spDomingo2.getValue());
							String Domingo_resultado3 = sdf.format ((Date) spDomingo3.getValue());
							String Domingo_resultado4 = sdf.format ((Date) spDomingo4.getValue());
							String Domingo_resultado5 = sdf.format ((Date) spDomingo5.getValue());
							
							//Lunes
							String Lunes_resultado1 = sdf.format ((Date) spLunes1.getValue());
							String Lunes_resultado2 = sdf.format((Date) spLunes2.getValue());
							String Lunes_resultado3 = sdf.format ((Date) spLunes3.getValue());
							String Lunes_resultado4 = sdf.format ((Date) spLunes4.getValue());
							String Lunes_resultado5 = sdf.format ((Date) spLunes5.getValue());
							
							//Martes
							String Martes_resultado1 = sdf.format ((Date) spMartes1.getValue());
							String Martes_resultado2 = sdf.format((Date)spMartes2.getValue());
							String Martes_resultado3 = sdf.format ((Date) spMartes3.getValue());
							String Martes_resultado4 = sdf.format ((Date) spMartes4.getValue());
							String Martes_resultado5 = sdf.format ((Date) spMartes5.getValue());
							
							//Miercoles
							String Miercoles_resultado1 = sdf.format ((Date) spMiercoles1.getValue());
							String Miercoles_resultado2 = sdf.format ((Date) spMiercoles2.getValue());
							String Miercoles_resultado3 = sdf.format ((Date) spMiercoles3.getValue());
							String Miercoles_resultado4 = sdf.format ((Date) spMiercoles4.getValue());
							String Miercoles_resultado5 = sdf.format ((Date) spMiercoles5.getValue());
							
							//Jueves
							String Jueves_resultado1 = sdf.format ((Date) spJueves1.getValue());
							String Jueves_resultado2 = sdf.format ((Date) spJueves2.getValue());
							String Jueves_resultado3 = sdf.format ((Date) spJueves3.getValue());
							String Jueves_resultado4 = sdf.format ((Date) spJueves4.getValue());
							String Jueves_resultado5 = sdf.format ((Date) spJueves5.getValue());
							
							//Viernes
							String Viernes_resultado1 = sdf.format ((Date) spViernes1.getValue());
							String Viernes_resultado2 = sdf.format ((Date) spViernes2.getValue());
							String Viernes_resultado3 = sdf.format ((Date) spViernes3.getValue());
							String Viernes_resultado4 = sdf.format ((Date) spViernes4.getValue());
							String Viernes_resultado5 = sdf.format ((Date) spViernes5.getValue());
							
							//Sabado
							String Sabado_resultado1 = sdf.format ((Date) spSabado1.getValue());
							String Sabado_resultado2 = sdf.format ((Date) spSabado2.getValue());
							String Sabado_resultado3 = sdf.format ((Date) spSabado3.getValue());
							String Sabado_resultado4 = sdf.format ((Date) spSabado4.getValue());
							String Sabado_resultado5 = sdf.format ((Date) spSabado5.getValue());
							
							
							
//							ObjHorario horario_emp = new ObjHorario();
							horario.setNombre(txtNombre.getText().toUpperCase());
							
							//Asignamos los datos
							horario.setDomingo1(Domingo_resultado1);
							horario.setDomingo2(Domingo_resultado2);
							horario.setDomingo3(Domingo_resultado3);
							horario.setDomingo4(Domingo_resultado4);
							horario.setDomingo5(Domingo_resultado5);
							
							horario.setLunes1(Lunes_resultado1);
							horario.setLunes2(Lunes_resultado2);
							horario.setLunes3(Lunes_resultado3);
							horario.setLunes4(Lunes_resultado4);
							horario.setLunes5(Lunes_resultado5);
							
							horario.setMartes1(Martes_resultado1);
							horario.setMartes2(Martes_resultado2);
							horario.setMartes3(Martes_resultado3);
							horario.setMartes4(Martes_resultado4);
							horario.setMartes5(Martes_resultado5);
							
							horario.setMiercoles1(Miercoles_resultado1);
							horario.setMiercoles2(Miercoles_resultado2);
							horario.setMiercoles3(Miercoles_resultado3);
							horario.setMiercoles4(Miercoles_resultado4);
							horario.setMiercoles5(Miercoles_resultado5);
							
							horario.setJueves1(Jueves_resultado1);
							horario.setJueves2(Jueves_resultado2);
							horario.setJueves3(Jueves_resultado3);
							horario.setJueves4(Jueves_resultado4);
							horario.setJueves5(Jueves_resultado5);
							
							horario.setViernes1(Viernes_resultado1);
							horario.setViernes2(Viernes_resultado2);
							horario.setViernes3(Viernes_resultado3);
							horario.setViernes4(Viernes_resultado4);
							horario.setViernes5(Viernes_resultado5);
							
							horario.setSabado1(Sabado_resultado1);
							horario.setSabado2(Sabado_resultado2);
							horario.setSabado3(Sabado_resultado3);
							horario.setSabado4(Sabado_resultado4);
							horario.setSabado5(Sabado_resultado5);
							
							horario.setDescanso(Descanso);
							
							if(horario.Guardar()){
								JOptionPane.showMessageDialog(null, "El registro se guardó exitosamente!" , "Exito al guardar!", JOptionPane.INFORMATION_MESSAGE);
//								resetear();
								return;
							}else{
								JOptionPane.showMessageDialog(null, "Error al tratar de guardar el registro", "Error al guardar registro", JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
					}
			}
			
		}
	};
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
				try {
					ObjHorario horario = new ObjHorario().buscar_nuevo();
					
					if(horario.getFolio() >= 0){
						txtFolio.setText(horario.getFolio()+1+"");
						txtNombre.setText("");
						txtNombre.requestFocus();
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
	};
	
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
	
	ActionListener cancelar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
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
			
		}
	};

	
	KeyListener valida = new KeyListener() {
		public void keyTyped(KeyEvent arg0){}
		public void keyReleased(KeyEvent arg0){}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnAceptar.doClick();
				txtNombre.requestFocus();
			}
		}
	};
	
	public static void main(String[]a)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Horario().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
}
