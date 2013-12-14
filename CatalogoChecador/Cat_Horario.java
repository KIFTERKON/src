package CatalogoChecador;

import java.awt.Color;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import ObjetoChecador.ObjHorario;

@SuppressWarnings("serial")
public class Cat_Horario extends Cat_Horario_base
{
	public void getContenedor(){
		
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/reloj.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Horario"));
		this.setTitle("Horario");
		
		lblInicio.setFont(new Font("arial", Font.BOLD, 10));
		lblFin.setFont(new Font("arial", Font.BOLD, 10));
		lblEntrada.setFont(new Font("arial", Font.BOLD, 10));
		lblSalida.setFont(new Font("arial", Font.BOLD, 10));
		lblReceso.setFont(new Font("arial", Font.BOLD, 10));
		
		lblDobla.setFont(new Font("arial", Font.BOLD, 10));
		lblDobla2.setFont(new Font("arial", Font.BOLD, 10));
		lblDobla21.setFont(new Font("arial", Font.BOLD, 10));
		lblDobla3.setFont(new Font("arial", Font.BOLD, 10));
		lblDobla31.setFont(new Font("arial", Font.BOLD, 10));
		
		lblLimi.setFont(new Font("Arial Black",Font.BOLD,10));
		lblTrabajo.setFont(new Font("Arial Black",Font.BOLD,10));
		lblComida.setFont(new Font("Arial Black",Font.BOLD,10));
		
		lblSintaxis.setFont(new Font("Arial ",Font.BOLD,10));
		lblSintaxis2.setFont(new Font("Arial ",Font.BOLD,10));
		lblSintaxis3.setFont(new Font("Arial ",Font.BOLD,10));
		
		lblSintaxis2.setForeground(Color.red);
		
		chbHorarioDeposito.setFont(new Font("Arial",Font.BOLD,10));
		chbRecesoExtraDiario.setFont(new Font("Arial",Font.BOLD,10));
		
		int x=40;
		
		horario1.add(lblDia).setBounds			(20,90,50,20);
		horario1.add(btnDomingo).setBounds		(20,120,90,20);
		horario1.add(btnLunes).setBounds		(20,150,90,20);
		horario1.add(btnMartes).setBounds		(20,180,90,20);
		horario1.add(btnMiercoles).setBounds	(20,210,90,20);
		horario1.add(btnJueves).setBounds		(20,240,90,20);
		horario1.add(btnViernes).setBounds		(20,270,90,20);
		horario1.add(btnSabado).setBounds		(20,300,90,20);
		horario1.add(btnSD).setBounds			(20,340,90,20);
		
		horario1.add(lblLimi).setBounds(130,70,100,20);
		horario1.add(lblTrabajo).setBounds(310+x,70,60,20);
		horario1.add(lblComida).setBounds(490+x,70,90,20);
		
		horario1.add(lblInicio).setBounds(130,90,50,20);
		horario1.add(lblFin).setBounds(210,90,50,20);
		
		horario1.add(lblEntrada).setBounds(310+x,90,60,20);
		horario1.add(lblSalida).setBounds(390+x,90,60,20);
		
		horario1.add(lblReceso).setBounds(505+x,90,50,20);
		
		horario1.add(lblDobla).setBounds(575+x,90,80,20);
		horario1.add(lblDobla2).setBounds(645+x,85,80,20);
		horario1.add(lblDobla21).setBounds(645+x,95,80,20);
		horario1.add(lblDobla3).setBounds(715+x,85,80,20);
		horario1.add(lblDobla31).setBounds(715+x,95,80,20);

		horario1.add(spDomingo1).setBounds(130,120,70,20);
		horario1.add(spDomingo2).setBounds(210,120,70,20);
		horario1.add(spDomingo3).setBounds(310+x,120,70,20);
		horario1.add(spDomingo4).setBounds(390+x,120,70,20);
		horario1.add(spDomingo5).setBounds(490+x,120,70,20);
		
		horario1.add(rbDomingo).setBounds(585+x,120,18,18);
		horario1.add(rbDomingo2).setBounds(655+x,120,18,18);
		horario1.add(rbDomingo3).setBounds(725+x,120,18,18);
		
		horario1.add(spLunes1).setBounds(130,150,70,20);
		horario1.add(spLunes2).setBounds(210,150,70,20);
		horario1.add(spLunes3).setBounds(310+x,150,70,20);
		horario1.add(spLunes4).setBounds(390+x,150,70,20);
		horario1.add(spLunes5).setBounds(490+x,150,70,20);
		
		horario1.add(rbLunes).setBounds(585+x,150,18,18);
		horario1.add(rbLunes2).setBounds(655+x,150,18,18);
		horario1.add(rbLunes3).setBounds(725+x,150,18,18);

		horario1.add(spMartes1).setBounds(130,180,70,20);
		horario1.add(spMartes2).setBounds(210,180,70,20);
		horario1.add(spMartes3).setBounds(310+x,180,70,20);
		horario1.add(spMartes4).setBounds(390+x,180,70,20);
		horario1.add(spMartes5).setBounds(490+x,180,70,20);
		
		horario1.add(rbMartes).setBounds(585+x,180,18,18);
		horario1.add(rbMartes2).setBounds(655+x,180,18,18);
		horario1.add(rbMartes3).setBounds(725+x,180,18,18);
		
		horario1.add(spMiercoles1).setBounds(130,210,70,20);
		horario1.add(spMiercoles2).setBounds(210,210,70,20);
		horario1.add(spMiercoles3).setBounds(310+x,210,70,20);
		horario1.add(spMiercoles4).setBounds(390+x,210,70,20);
		horario1.add(spMiercoles5).setBounds(490+x,210,70,20);
		
		horario1.add(rbMiercoles).setBounds(585+x,210,18,18);
		horario1.add(rbMiercoles2).setBounds(655+x,210,18,18);
		horario1.add(rbMiercoles3).setBounds(725+x,210,18,18);

		horario1.add(spJueves1).setBounds(130,240,70,20);
		horario1.add(spJueves2).setBounds(210,240,70,20);
		horario1.add(spJueves3).setBounds(310+x,240,70,20);
		horario1.add(spJueves4).setBounds(390+x,240,70,20);
		horario1.add(spJueves5).setBounds(490+x,240,70,20);
		
		horario1.add(rbJueves).setBounds(585+x,240,18,18);
		horario1.add(rbJueves2).setBounds(655+x,240,18,18);
		horario1.add(rbJueves3).setBounds(725+x,240,18,18);
		
		horario1.add(spViernes1).setBounds(130,270,70,20);
		horario1.add(spViernes2).setBounds(210,270,70,20);
		horario1.add(spViernes3).setBounds(310+x,270,70,20);
		horario1.add(spViernes4).setBounds(390+x,270,70,20);
		horario1.add(spViernes5).setBounds(490+x,270,70,20);
		
		horario1.add(rbViernes).setBounds(585+x,270,18,18);
		horario1.add(rbViernes2).setBounds(655+x,270,18,18);
		horario1.add(rbViernes3).setBounds(725+x,270,18,18);

		horario1.add(spSabado1).setBounds(130,300,70,20);
		horario1.add(spSabado2).setBounds(210,300,70,20);
		horario1.add(spSabado3).setBounds(310+x,300,70,20);
		horario1.add(spSabado4).setBounds(390+x,300,70,20);
		horario1.add(spSabado5).setBounds(490+x,300,70,20);
		
		horario1.add(rbSabado).setBounds(585+x,300,18,18);
		horario1.add(rbSabado2).setBounds(655+x,300,18,18);
		horario1.add(rbSabado3).setBounds(725+x,300,18,18);
		
		horario1.add(rbNoDobla).setBounds(585+x,340,18,18);
		horario1.add(rbNoDobla2).setBounds(655+x,340,18,18);
		horario1.add(rbNoDobla3).setBounds(725+x,340,18,18);

		horario1.add(btnNuevo).setBounds(150,5,70,20);
		horario1.add(btnFiltro).setBounds(220,5,70,20);
		horario1.add(btnIgual).setBounds(620,60,20,20);
		
		horario1.add(lblFolio).setBounds(20,5,50,20);
		horario1.add(txtFolio).setBounds(70,5,70,20);
		
		horario1.add(new JLabel("Nombre:")).setBounds(20,35,50,20);
		horario1.add(txtNombre).setBounds(70,35,580,20);
		
		horario1.add(chbHorarioDeposito).setBounds(655,35,165,20);
		horario1.add(chbRecesoExtraDiario).setBounds(655,60,165,20);

		horario1.add(lblSintaxis).setBounds(70,50,140,20);
		horario1.add(lblSintaxis2).setBounds(205,50,60,20);
		horario1.add(lblSintaxis3).setBounds(260,50,250,20);
		
		horario1.add(btnAceptar).setBounds(190,340,80,20);
		horario1.add(btnDeshacer).setBounds(285,340,80,20);
		horario1.add(btnEditar).setBounds(380,340,80,20);
		horario1.add(btnCancelar).setBounds(475,340,80,20);
		horario1.add(lblNoDobla).setBounds(770,340,70,20);
		
		panel.add(paneles).setBounds(10,20,825,400);
		paneles.addTab("Horario 1", horario1);
		
ButtonGroup botonesAgrupados = new ButtonGroup();
		botonesAgrupados.add(btnDomingo);
		botonesAgrupados.add(btnLunes);
		botonesAgrupados.add(btnMartes);
		botonesAgrupados.add(btnMiercoles);
		botonesAgrupados.add(btnJueves);
		botonesAgrupados.add(btnViernes);
		botonesAgrupados.add(btnSabado);
		botonesAgrupados.add(btnSD);
		
ButtonGroup RBAgrupados = new ButtonGroup();
		RBAgrupados.add(rbDomingo);
		RBAgrupados.add(rbLunes);
		RBAgrupados.add(rbMartes);
		RBAgrupados.add(rbMiercoles);
		RBAgrupados.add(rbJueves);
		RBAgrupados.add(rbViernes);
		RBAgrupados.add(rbSabado);
		RBAgrupados.add(rbNoDobla);
		
ButtonGroup RBAgrupados2 = new ButtonGroup();
		RBAgrupados2.add(rbDomingo2);
		RBAgrupados2.add(rbLunes2);
		RBAgrupados2.add(rbMartes2);
		RBAgrupados2.add(rbMiercoles2);
		RBAgrupados2.add(rbJueves2);
		RBAgrupados2.add(rbViernes2);
		RBAgrupados2.add(rbSabado2);
		RBAgrupados2.add(rbNoDobla2);
		
ButtonGroup RBAgrupados3 = new ButtonGroup();
		RBAgrupados3.add(rbDomingo3);
		RBAgrupados3.add(rbLunes3);
		RBAgrupados3.add(rbMartes3);
		RBAgrupados3.add(rbMiercoles3);
		RBAgrupados3.add(rbJueves3);
		RBAgrupados3.add(rbViernes3);
		RBAgrupados3.add(rbSabado3);
		RBAgrupados3.add(rbNoDobla3);
		
		btnDomingo.addActionListener(Domingo);
		btnLunes.addActionListener(Lunes);
		btnMartes.addActionListener(Martes);
		btnMiercoles.addActionListener(Miercoles);
		btnJueves.addActionListener(Jueves);
		btnViernes.addActionListener(Viernes);
		btnSabado.addActionListener(Sabado);
		
		btnSD.addActionListener(SinDescanso);
		
//		btnTurno.setToolTipText("Agregar Segundo Horario");
		btnCancelar.setToolTipText("Cancelar");
		btnIgual.setToolTipText("Igualar todos los dias");
		btnAceptar.setToolTipText("Guardar");
		btnDeshacer.setToolTipText("Deshacer");
	
//		btnTurno.addActionListener(turno);
		btnCancelar.addActionListener(cancelar);
		btnIgual.addActionListener(igualar);
		btnAceptar.addActionListener(Guardar);
		btnDeshacer.addActionListener(deshacer);
		btnFiltro.addActionListener(filtro);
		
		btnEditar.addActionListener(editar);
		
		chbHorarioDeposito.addActionListener(pintarchbHorarioDep);
		chbRecesoExtraDiario.addActionListener(pintarchbRecesoEx);
		
		btnNuevo.addActionListener(nuevo);
		txtNombre.addKeyListener(valida);
		
		txtFolio.addKeyListener(action_buscar);
		
//		txtFolio.setEditable(false);
		
//		asigna el foco al JTextField deseado al arrancar la ventana
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
		        txtNombre.requestFocus();
		     }
		});
		
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
		this.setSize(850,460);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}	
	

	   public void preguntas()
	   {
	     if(btnDomingo.isSelected()==true) { Descanso=7;}
	     if (btnLunes.isSelected()==true){Descanso=1;}
	     if (btnMartes.isSelected()==true){Descanso=2;}
	     if (btnMiercoles.isSelected()==true){Descanso=3;}
	     if (btnJueves.isSelected()==true){Descanso=4;}
	     if (btnViernes.isSelected()==true){Descanso=5;}
	     if (btnSabado.isSelected()==true){Descanso=6;}
	   }
	
	@SuppressWarnings("deprecation")
	public Cat_Horario(int folio)//String nom
	{
		getContenedor();
		btnAceptar.setEnabled(false);
		btnEditar.setEnabled(true);
		ObjHorario buscar_horario = new ObjHorario().buscar(folio);
		
		
		if(buscar_horario.getFolio() != 0){

			txtFolio.setText(buscar_horario.getFolio()+"");
			txtNombre.setText(buscar_horario.getNombre());
			
//			ASIGNAR AL BOTON SELECCIONADO Y OCULTAR SU CAMPOS DE DIA DE DESCANSO
			switch(buscar_horario.getDescanso()){
			
			case 7:	btnDomingo.setSelected(true);	 DomingoOculto();	break;
			case 1:	btnLunes.setSelected(true);		 LunesOculto();		break;
			case 2:	btnMartes.setSelected(true); 	 MartesOculto();	break;
			case 3:	btnMiercoles.setSelected(true);  MiercolesOculto();	break;
			case 4:	btnJueves.setSelected(true); 	 JuevesOculto();	break;
			case 5:	btnViernes.setSelected(true); 	 ViernesOculto();	break;
			case 6:	btnSabado.setSelected(true); 	 SabadoOculto();	break;
			
			}
			
				switch(buscar_horario.getDiaDobla()){
				
						case 0:	rbNoDobla.setSelected(true);									break;
						case 1:	rbLunes.setSelected(true);		rbNoDobla.setSelected(false);	break;
						case 2:	rbMartes.setSelected(true);		rbNoDobla.setSelected(false);	break;
						case 3:	rbMiercoles.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 4:	rbJueves.setSelected(true);		rbNoDobla.setSelected(false);	break;
						case 5: rbViernes.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 6:	rbSabado.setSelected(true);		rbNoDobla.setSelected(false);	break;
						case 7:	rbDomingo.setSelected(true);	rbNoDobla.setSelected(false);	break;
				}
				switch(buscar_horario.getDiaDobla2()){
						case 0:	rbNoDobla2.setSelected(true);									break;
						case 1:	rbLunes2.setSelected(true);		rbNoDobla.setSelected(false);	break;
						case 2:	rbMartes2.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 3:	rbMiercoles2.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 4:	rbJueves2.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 5: rbViernes2.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 6:	rbSabado2.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 7:	rbDomingo2.setSelected(true);	rbNoDobla.setSelected(false);	break;
				}
				switch(buscar_horario.getDiaDobla3()){
						case 0:	rbNoDobla3.setSelected(true);									break;
						case 1:	rbLunes3.setSelected(true);		rbNoDobla.setSelected(false);	break;
						case 2:	rbMartes3.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 3:	rbMiercoles3.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 4:	rbJueves3.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 5:	rbViernes3.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 6:	rbSabado3.setSelected(true);	rbNoDobla.setSelected(false);	break;
						case 7:	rbDomingo3.setSelected(true);	rbNoDobla.setSelected(false);	break;
				}	
				
			}
			
			if(buscar_horario.getRecesoDiarioExtra()==1){
				chbRecesoExtraDiario.setSelected(true);
				chbRecesoExtraDiario.setBackground(Color.ORANGE);
				chbRecesoExtraDiario.setForeground(Color.BLUE);
			
				}else{
					chbRecesoExtraDiario.setSelected(false);
					chbRecesoExtraDiario.setBackground(Color.GREEN);
					chbRecesoExtraDiario.setForeground(Color.BLACK);
					}
			
			if(buscar_horario.getHorarioDeposito()==1){
				chbHorarioDeposito.setSelected(true);
				chbHorarioDeposito.setBackground(Color.ORANGE);
				chbHorarioDeposito.setForeground(Color.BLUE);
				}else{
					chbHorarioDeposito.setSelected(false);
					chbHorarioDeposito.setBackground(Color.GREEN);
					chbHorarioDeposito.setForeground(Color.BLACK);
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
						
		}
	
	
	public Cat_Horario()
	{
		pintarChb();
		getContenedor();
		
		btnAceptar.setEnabled(false);
		btnEditar.setEnabled(false);
	}

//buscar horario
	KeyListener action_buscar = new KeyListener() {
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
            public void keyPressed(KeyEvent e){
                            
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    	dispose();
                    	new Cat_Horario(Integer.parseInt(txtFolio.getText())).setVisible(true);
                    }
            }
            @Override
            public void keyReleased(KeyEvent e){}
    };
	
	ActionListener pintarchbRecesoEx = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			pintarChb();
		}
	};
	
	ActionListener pintarchbHorarioDep = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			pintarChb();
		}
	};
	
	public void pintarChb(){
		if(chbRecesoExtraDiario.isSelected()){
			chbRecesoExtraDiario.setBackground(Color.ORANGE);
			chbRecesoExtraDiario.setForeground(Color.BLUE);
		}else{
			chbRecesoExtraDiario.setBackground(Color.GREEN);
			chbRecesoExtraDiario.setForeground(Color.BLACK);
		}
		
		if(chbHorarioDeposito.isSelected()){
			chbHorarioDeposito.setBackground(Color.ORANGE);
			chbHorarioDeposito.setForeground(Color.BLUE);
		}else{
			chbHorarioDeposito.setBackground(Color.GREEN);
			chbHorarioDeposito.setForeground(Color.BLACK);
		}
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
		
		if(rbDomingo.isSelected()){rbNoDobla.setSelected(true);}
		if(rbDomingo2.isSelected()){rbNoDobla2.setSelected(true);}
		if(rbDomingo3.isSelected()){rbNoDobla3.setSelected(true);}
		
		rbDomingo.setVisible(false);
		rbDomingo2.setVisible(false);
		rbDomingo3.setVisible(false);
	}
	
	public void DomingoVisible()
	{
		spDomingo1.setVisible(true);
		spDomingo2.setVisible(true);
		spDomingo3.setVisible(true);
		spDomingo4.setVisible(true);
		spDomingo5.setVisible(true);
		
		rbDomingo.setVisible(true);
		rbDomingo2.setVisible(true);
		rbDomingo3.setVisible(true);
	}
	
	public void LunesOculto()
	{
		spLunes1.setVisible(false);
		spLunes2.setVisible(false);
		spLunes3.setVisible(false);
		spLunes4.setVisible(false);
		spLunes5.setVisible(false);
		
		if(rbLunes.isSelected()){rbNoDobla.setSelected(true);}
		if(rbLunes2.isSelected()){rbNoDobla2.setSelected(true);}
		if(rbLunes3.isSelected()){rbNoDobla3.setSelected(true);}
		
		rbLunes.setVisible(false);
		rbLunes2.setVisible(false);
		rbLunes3.setVisible(false);
	}
	
	public void LunesVisible()
	{
		spLunes1.setVisible(true);
		spLunes2.setVisible(true);
		spLunes3.setVisible(true);
		spLunes4.setVisible(true);
		spLunes5.setVisible(true);
		
		rbLunes.setVisible(true);
		rbLunes2.setVisible(true);
		rbLunes3.setVisible(true);
	}
	
	public void MartesOculto()
	{
		spMartes1.setVisible(false);
		spMartes2.setVisible(false);
		spMartes3.setVisible(false);
		spMartes4.setVisible(false);
		spMartes5.setVisible(false);
		
		if(rbMartes.isSelected()){rbNoDobla.setSelected(true);}
		if(rbMartes2.isSelected()){rbNoDobla2.setSelected(true);}
		if(rbMartes3.isSelected()){rbNoDobla3.setSelected(true);}
		
		rbMartes.setVisible(false);
		rbMartes2.setVisible(false);
		rbMartes3.setVisible(false);
	}
	
	public void MartesVisible()
	{
		spMartes1.setVisible(true);
		spMartes2.setVisible(true);
		spMartes3.setVisible(true);
		spMartes4.setVisible(true);
		spMartes5.setVisible(true);
		
		rbMartes.setVisible(true);
		rbMartes2.setVisible(true);
		rbMartes3.setVisible(true);
	}
	
	public void MiercolesOculto()
	{
		spMiercoles1.setVisible(false);
		spMiercoles2.setVisible(false);
		spMiercoles3.setVisible(false);
		spMiercoles4.setVisible(false);
		spMiercoles5.setVisible(false);
		
		if(rbMiercoles.isSelected()){rbNoDobla.setSelected(true);}
		if(rbMiercoles2.isSelected()){rbNoDobla2.setSelected(true);}
		if(rbMiercoles3.isSelected()){rbNoDobla3.setSelected(true);}
		
		rbMiercoles.setVisible(false);
		rbMiercoles2.setVisible(false);
		rbMiercoles3.setVisible(false);
	}

	public void MiercolesVisible()
	{
		spMiercoles1.setVisible(true);
		spMiercoles2.setVisible(true);
		spMiercoles3.setVisible(true);
		spMiercoles4.setVisible(true);
		spMiercoles5.setVisible(true);
		
		rbMiercoles.setVisible(true);
		rbMiercoles2.setVisible(true);
		rbMiercoles3.setVisible(true);
	}
	
	public void JuevesOculto()
	{
		spJueves1.setVisible(false);
		spJueves2.setVisible(false);
		spJueves3.setVisible(false);
		spJueves4.setVisible(false);
		spJueves5.setVisible(false);
		
		if(rbJueves.isSelected()){rbNoDobla.setSelected(true);}
		if(rbJueves2.isSelected()){rbNoDobla2.setSelected(true);}
		if(rbJueves3.isSelected()){rbNoDobla3.setSelected(true);}
		
		rbJueves.setVisible(false);
		rbJueves2.setVisible(false);
		rbJueves3.setVisible(false);
	}
	
	public void JuevesVisible()
	{
		spJueves1.setVisible(true);
		spJueves2.setVisible(true);
		spJueves3.setVisible(true);
		spJueves4.setVisible(true);
		spJueves5.setVisible(true);
		
		rbJueves.setVisible(true);
		rbJueves2.setVisible(true);
		rbJueves3.setVisible(true);
	}
	
	public void ViernesOculto()
	{
		spViernes1.setVisible(false);
		spViernes2.setVisible(false);
		spViernes3.setVisible(false);
		spViernes4.setVisible(false);
		spViernes5.setVisible(false);
		
		if(rbViernes.isSelected()){rbNoDobla.setSelected(true);}
		if(rbViernes2.isSelected()){rbNoDobla2.setSelected(true);}
		if(rbViernes3.isSelected()){rbNoDobla3.setSelected(true);}
		
		rbViernes.setVisible(false);
		rbViernes2.setVisible(false);
		rbViernes3.setVisible(false);
	}
	
	public void ViernesVisible()
	{
		spViernes1.setVisible(true);
		spViernes2.setVisible(true);
		spViernes3.setVisible(true);
		spViernes4.setVisible(true);
		spViernes5.setVisible(true);
		
		rbViernes.setVisible(true);
		rbViernes2.setVisible(true);
		rbViernes3.setVisible(true);
	}
	
	public void SabadoOculto()
	{
		spSabado1.setVisible(false);
		spSabado2.setVisible(false);
		spSabado3.setVisible(false);
		spSabado4.setVisible(false);
		spSabado5.setVisible(false);
		
		rbNoDobla.setSelected(true);
		rbNoDobla2.setSelected(true);
		rbNoDobla3.setSelected(true);
		
		if(rbSabado.isSelected()){rbNoDobla.setSelected(true);}
		if(rbSabado2.isSelected()){rbNoDobla2.setSelected(true);}
		if(rbSabado3.isSelected()){rbNoDobla3.setSelected(true);}
		
		rbSabado.setVisible(false);
		rbSabado2.setVisible(false);
		rbSabado3.setVisible(false);
	}
	
	public void SabadoVisible()
	{
		spSabado1.setVisible(true);
		spSabado2.setVisible(true);
		spSabado3.setVisible(true);
		spSabado4.setVisible(true);
		spSabado5.setVisible(true);
		
		rbSabado.setVisible(true);
		rbSabado2.setVisible(true);
		rbSabado3.setVisible(true);
	}
	ActionListener deshacer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			resetear();
		}
	};
	
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
		
		txtFolio.setText("");
		txtNombre.setText("");
		
		rbNoDobla.setSelected(true);
		rbNoDobla2.setSelected(true);
		rbNoDobla3.setSelected(true);
		
		chbRecesoExtraDiario.setSelected(false);
		chbHorarioDeposito.setSelected(false);
		
		btnAceptar.setEnabled(false);
		btnEditar.setEnabled(false);
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
								
//								manda al objeto el dia de la semana que dobla numerico 
									if(rbNoDobla.isSelected()==true){	horario_emp.setDiaDobla(0);		}
									if(rbLunes.isSelected()==true){		horario_emp.setDiaDobla(1);		}
									if(rbMartes.isSelected()==true){	horario_emp.setDiaDobla(2);		}
									if(rbMiercoles.isSelected()==true){	horario_emp.setDiaDobla(3);		}
									if(rbJueves.isSelected()==true){	horario_emp.setDiaDobla(4);		}
									if(rbViernes.isSelected()==true){	horario_emp.setDiaDobla(5);		}
									if(rbSabado.isSelected()==true){	horario_emp.setDiaDobla(6);		}
									if(rbDomingo.isSelected()==true){	horario_emp.setDiaDobla(7);		}
									
//								manda al objeto el diaExtra1 de la semana que dobla numerico 
									if(rbNoDobla2.isSelected()==true){	horario_emp.setDiaDobla2(0);		}
									if(rbLunes2.isSelected()==true){	horario_emp.setDiaDobla2(1);		}
									if(rbMartes2.isSelected()==true){	horario_emp.setDiaDobla2(2);		}
									if(rbMiercoles2.isSelected()==true){horario_emp.setDiaDobla2(3);		}
									if(rbJueves2.isSelected()==true){	horario_emp.setDiaDobla2(4);		}
									if(rbViernes2.isSelected()==true){	horario_emp.setDiaDobla2(5);		}
									if(rbSabado2.isSelected()==true){	horario_emp.setDiaDobla2(6);		}
									if(rbDomingo2.isSelected()==true){	horario_emp.setDiaDobla2(7);		}
										
//								manda al objeto el diaExtra2 de la semana que dobla numerico 
									if(rbNoDobla3.isSelected()==true){	horario_emp.setDiaDobla3(0);		}
									if(rbLunes3.isSelected()==true){	horario_emp.setDiaDobla3(1);		}
									if(rbMartes3.isSelected()==true){	horario_emp.setDiaDobla3(2);		}
									if(rbMiercoles3.isSelected()==true){horario_emp.setDiaDobla3(3);		}
									if(rbJueves3.isSelected()==true){	horario_emp.setDiaDobla3(4);		}
									if(rbViernes3.isSelected()==true){	horario_emp.setDiaDobla3(5);		}
									if(rbSabado3.isSelected()==true){	horario_emp.setDiaDobla3(6);		}
									if(rbDomingo3.isSelected()==true){	horario_emp.setDiaDobla3(7);		}
									
//								status extras
									if(chbHorarioDeposito.isSelected()==true){ horario_emp.setHorarioDeposito(1);}else{horario_emp.setHorarioDeposito(0);}
									if(chbRecesoExtraDiario.isSelected()==true){ horario_emp.setRecesoDiarioExtra(1);}else{horario_emp.setRecesoDiarioExtra(0);}
									
									if(rbDomingo.isSelected() && rbDomingo2.isSelected() || rbDomingo2.isSelected() && rbDomingo3.isSelected() || rbDomingo.isSelected() && rbDomingo3.isSelected()){
										JOptionPane.showMessageDialog(null, "El dia Domingo Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
												                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
										return;
									}
									if(rbLunes.isSelected() && rbLunes2.isSelected() || rbLunes2.isSelected() && rbLunes3.isSelected() || rbLunes.isSelected() && rbLunes3.isSelected()){
										JOptionPane.showMessageDialog(null, "El dia Lunes Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
												                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
										return;
									}
									if(rbMartes.isSelected() && rbMartes2.isSelected() || rbMartes2.isSelected() && rbMartes3.isSelected() || rbMartes.isSelected() && rbMartes3.isSelected()){
										JOptionPane.showMessageDialog(null, "El dia Martes Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
												                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
										return;
									}
									if(rbMiercoles.isSelected() && rbMiercoles2.isSelected() || rbMiercoles2.isSelected() && rbMiercoles3.isSelected() || rbMiercoles.isSelected() && rbMiercoles3.isSelected()){
										JOptionPane.showMessageDialog(null, "El dia Miercoles Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
												                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
										return;
									}
									if(rbJueves.isSelected() && rbJueves2.isSelected() || rbJueves2.isSelected() && rbJueves3.isSelected() || rbJueves.isSelected() && rbJueves3.isSelected()){
										JOptionPane.showMessageDialog(null, "El dia Jueves Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
												                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
										return;
									}
									if(rbViernes.isSelected() && rbViernes2.isSelected() || rbViernes2.isSelected() && rbViernes3.isSelected() || rbViernes.isSelected() && rbViernes3.isSelected()){
										JOptionPane.showMessageDialog(null, "El dia Viernes Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
												                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
										return;
									}
									if(rbSabado.isSelected() && rbSabado2.isSelected() || rbSabado2.isSelected() && rbSabado3.isSelected() || rbSabado.isSelected() && rbSabado3.isSelected()){
										JOptionPane.showMessageDialog(null, "El dia Sabado Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
												                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
										return;
									}else{
											if(horario_emp.Actualizar(Integer.parseInt(txtFolio.getText()))){
												btnEditar.setEnabled(true);
												btnAceptar.setEnabled(false);
												JOptionPane.showMessageDialog(null, "El registro se Actualizo exitosamente!" , "Exito al Actualizar!", JOptionPane.INFORMATION_MESSAGE);
												return;
											}else{
												JOptionPane.showMessageDialog(null, "Error al tratar de Actualizar el registro", "Error al Actualizar registro", JOptionPane.WARNING_MESSAGE);
												return;
											}
									}
						}
					}
					
				}else{
//						guardar
					preguntas();
						if(txtNombre.getText().equals(""))
						{
								JOptionPane.showMessageDialog(null, "El Nombre es Requerido:", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
								return;
						}else{
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
							
//							manda al objeto el dia de la semana que dobla numerico 
								if(rbNoDobla.isSelected()==true){	horario.setDiaDobla(0);		}
								if(rbLunes.isSelected()==true){		horario.setDiaDobla(1);		}
								if(rbMartes.isSelected()==true){	horario.setDiaDobla(2);		}
								if(rbMiercoles.isSelected()==true){	horario.setDiaDobla(3);		}
								if(rbJueves.isSelected()==true){	horario.setDiaDobla(4);		}
								if(rbViernes.isSelected()==true){	horario.setDiaDobla(5);		}
								if(rbSabado.isSelected()==true){	horario.setDiaDobla(6);		}
								if(rbDomingo.isSelected()==true){	horario.setDiaDobla(7);		}
								
//							manda al objeto el diaExtra1 de la semana que dobla numerico 
								if(rbNoDobla2.isSelected()==true){	horario.setDiaDobla2(0);		}
								if(rbLunes2.isSelected()==true){	horario.setDiaDobla2(1);		}
								if(rbMartes2.isSelected()==true){	horario.setDiaDobla2(2);		}
								if(rbMiercoles2.isSelected()==true){horario.setDiaDobla2(3);		}
								if(rbJueves2.isSelected()==true){	horario.setDiaDobla2(4);		}
								if(rbViernes2.isSelected()==true){	horario.setDiaDobla2(5);		}
								if(rbSabado2.isSelected()==true){	horario.setDiaDobla2(6);		}
								if(rbDomingo2.isSelected()==true){	horario.setDiaDobla2(7);		}
									
//							manda al objeto el diaExtra2 de la semana que dobla numerico 
								if(rbNoDobla3.isSelected()==true){	horario.setDiaDobla3(0);		}
								if(rbLunes3.isSelected()==true){	horario.setDiaDobla3(1);		}
								if(rbMartes3.isSelected()==true){	horario.setDiaDobla3(2);		}
								if(rbMiercoles3.isSelected()==true){horario.setDiaDobla3(3);		}
								if(rbJueves3.isSelected()==true){	horario.setDiaDobla3(4);		}
								if(rbViernes3.isSelected()==true){	horario.setDiaDobla3(5);		}
								if(rbSabado3.isSelected()==true){	horario.setDiaDobla3(6);		}
								if(rbDomingo3.isSelected()==true){	horario.setDiaDobla3(7);		}
							
//							status extras
								if(chbHorarioDeposito.isSelected()==true){ horario.setHorarioDeposito(1);}else{horario.setHorarioDeposito(0);}
								if(chbRecesoExtraDiario.isSelected()==true){ horario.setRecesoDiarioExtra(1);}else{horario.setRecesoDiarioExtra(0);}
								
								if(rbDomingo.isSelected() && rbDomingo2.isSelected() || rbDomingo2.isSelected() && rbDomingo3.isSelected() || rbDomingo.isSelected() && rbDomingo3.isSelected()){
									JOptionPane.showMessageDialog(null, "El dia Domingo Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
											                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								if(rbLunes.isSelected() && rbLunes2.isSelected() || rbLunes2.isSelected() && rbLunes3.isSelected() || rbLunes.isSelected() && rbLunes3.isSelected()){
									JOptionPane.showMessageDialog(null, "El dia Lunes Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
											                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								if(rbMartes.isSelected() && rbMartes2.isSelected() || rbMartes2.isSelected() && rbMartes3.isSelected() || rbMartes.isSelected() && rbMartes3.isSelected()){
									JOptionPane.showMessageDialog(null, "El dia Martes Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
											                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								if(rbMiercoles.isSelected() && rbMiercoles2.isSelected() || rbMiercoles2.isSelected() && rbMiercoles3.isSelected() || rbMiercoles.isSelected() && rbMiercoles3.isSelected()){
									JOptionPane.showMessageDialog(null, "El dia Miercoles Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
											                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								if(rbJueves.isSelected() && rbJueves2.isSelected() || rbJueves2.isSelected() && rbJueves3.isSelected() || rbJueves.isSelected() && rbJueves3.isSelected()){
									JOptionPane.showMessageDialog(null, "El dia Jueves Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
											                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								if(rbViernes.isSelected() && rbViernes2.isSelected() || rbViernes2.isSelected() && rbViernes3.isSelected() || rbViernes.isSelected() && rbViernes3.isSelected()){
									JOptionPane.showMessageDialog(null, "El dia Viernes Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
											                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								if(rbSabado.isSelected() && rbSabado2.isSelected() || rbSabado2.isSelected() && rbSabado3.isSelected() || rbSabado.isSelected() && rbSabado3.isSelected()){
									JOptionPane.showMessageDialog(null, "El dia Sabado Tiene mas de un Dia Dobla seleccionado\n                        (selecciones solo uno)" ,
											                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
									return;
								}else{
								
									if(horario.Guardar()){
										btnEditar.setEnabled(true);
										btnAceptar.setEnabled(false);
										JOptionPane.showMessageDialog(null, "El registro se guardó exitosamente!" , "Exito al guardar!", JOptionPane.INFORMATION_MESSAGE);
										return;
									}else{
										JOptionPane.showMessageDialog(null, "Error al tratar de guardar el registro", "Error al guardar registro", JOptionPane.WARNING_MESSAGE);
										return;
									}
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
						
						rbNoDobla.setSelected(true);
						rbNoDobla2.setSelected(true);
						rbNoDobla3.setSelected(true);
						
						chbHorarioDeposito.setSelected(false);
						chbRecesoExtraDiario.setSelected(false);
						
						btnAceptar.setEnabled(true);
						btnEditar.setEnabled(false);
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
	};
	
	ActionListener Domingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnDomingo.isSelected()){
				LunesVisible();
				MartesVisible();
				MiercolesVisible();
				JuevesVisible();
				ViernesVisible();
				SabadoVisible();
				DomingoOculto();
			}else{
				DomingoVisible();
				}
		}
	};
	
	ActionListener Lunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnLunes.isSelected()){
				LunesOculto();
				MartesVisible();
				MiercolesVisible();
				JuevesVisible();
				ViernesVisible();
				SabadoVisible();
				DomingoVisible();
			}else{
				LunesVisible();
				}
		}
	};
	
	ActionListener Martes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnMartes.isSelected()){
				LunesVisible();
				MartesOculto();
				MiercolesVisible();
				JuevesVisible();
				ViernesVisible();
				SabadoVisible();
				DomingoVisible();
			}else{
				MartesVisible();
				}
		}
	};
	
	ActionListener Miercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnMiercoles.isSelected()){
				LunesVisible();
				MartesVisible();
				MiercolesOculto();
				JuevesVisible();
				ViernesVisible();
				SabadoVisible();
				DomingoVisible();
			}else{
				MiercolesVisible();
				}
		}
	};
	
	ActionListener Jueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnJueves.isSelected()){
				LunesVisible();
				MartesVisible();
				MiercolesVisible();
				JuevesOculto();
				ViernesVisible();
				SabadoVisible();
				DomingoVisible();
			}else{
				JuevesVisible();
				}
		}
	};
	
	ActionListener Viernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(btnViernes.isSelected()){
				LunesVisible();
				MartesVisible();
				MiercolesVisible();
				JuevesVisible();
				ViernesOculto();
				SabadoVisible();
				DomingoVisible();
			}else{
				ViernesVisible();
				}
		}
	};
	
	ActionListener Sabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0){
			if(btnSabado.isSelected()){
				LunesVisible();
				MartesVisible();
				MiercolesVisible();
				JuevesVisible();
				ViernesVisible();
				SabadoOculto();
				DomingoVisible();
			}else{
				SabadoVisible();
				}
		}
	};
	
	ActionListener SinDescanso = new ActionListener() {
		public void actionPerformed(ActionEvent arg0){
			if(btnSD.isSelected()){
				LunesVisible();
				MartesVisible();
				MiercolesVisible();
				JuevesVisible();
				ViernesVisible();
				SabadoVisible();
				DomingoVisible();
			}
		}
	};
	
	ActionListener editar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			btnAceptar.setEnabled(true);
			btnEditar.setEnabled(false);
		}
	};
	
	ActionListener cancelar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
		}
	};
	
	ActionListener igualar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//			si el btnDomingo esta deseleccionado, iguala los demas campos a los valores del dia domingo
//			de lo contrario iguala a lunes
			if(btnDomingo.isSelected()!=true){
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
				
			}else{
				//Igualamos todos los campos al primer campo
				spDomingo1.setValue(spLunes1.getValue());
				spMartes1.setValue(spLunes1.getValue());
				spMiercoles1.setValue(spLunes1.getValue());
				spJueves1.setValue(spLunes1.getValue());
				spViernes1.setValue(spLunes1.getValue());
				spSabado1.setValue(spLunes1.getValue());
				
				//Igualamos todos los campos al primer campo
				spDomingo2.setValue(spLunes2.getValue());
				spMartes2.setValue(spLunes2.getValue());
				spMiercoles2.setValue(spLunes2.getValue());
				spJueves2.setValue(spLunes2.getValue());
				spViernes2.setValue(spLunes2.getValue());
				spSabado2.setValue(spLunes2.getValue());
				
				//Igualamos todos los campos al primer campo
				spDomingo3.setValue(spLunes3.getValue());
				spMartes3.setValue(spLunes3.getValue());
				spMiercoles3.setValue(spLunes3.getValue());
				spJueves3.setValue(spLunes3.getValue());
				spViernes3.setValue(spLunes3.getValue());
				spSabado3.setValue(spLunes3.getValue());
				
				//Igualamos todos los campos al primer campo
				spDomingo4.setValue(spLunes4.getValue());
				spMartes4.setValue(spLunes4.getValue());
				spMiercoles4.setValue(spLunes4.getValue());
				spJueves4.setValue(spLunes4.getValue());
				spViernes4.setValue(spLunes4.getValue());
				spSabado4.setValue(spLunes4.getValue());
				
				//Igualamos todos los campos al primer campo
				spDomingo5.setValue(spLunes5.getValue());
				spMartes5.setValue(spLunes5.getValue());
				spMiercoles5.setValue(spLunes5.getValue());
				spJueves5.setValue(spLunes5.getValue());
				spViernes5.setValue(spLunes5.getValue());
				spSabado5.setValue(spLunes5.getValue());
			}
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
