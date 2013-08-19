package CatalogoChecador;

import java.awt.Container;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerDateModel;

@SuppressWarnings("serial")
public class Cat_Horario_base  extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JLayeredPane horario1 = new JLayeredPane();       
	JLayeredPane horario2 = new JLayeredPane(); 
	JTabbedPane paneles = new JTabbedPane();                                           
	                                                                                   
	JToggleButton btnDomingo = new JToggleButton("Domingo");                           
	JToggleButton btnLunes = new JToggleButton("Lunes");                               
	JToggleButton btnMartes = new JToggleButton("Martes");                             
	JToggleButton btnMiercoles = new JToggleButton("Miércoles");                       
	JToggleButton btnJueves = new JToggleButton("Jueves");                             
	JToggleButton btnViernes = new JToggleButton("Viernes");                           
	JToggleButton btnSabado = new JToggleButton("Sábado");                             
	JToggleButton btnTurno = new JToggleButton(new ImageIcon("Imagen/2do.png"));       
	                                                                   
	JButton btnNuevo = new JButton("Nuevo"); 
	JLabel lblFolio = new JLabel("Folio");
	JTextField txtFolio = new JTextField("");
	
	JButton btnIgual = new JButton(new ImageIcon("Imagen/igual.png"));                 
	JButton btnAceptar = new JButton("Guardar");                                       
	JButton btnCancelar = new JButton("Cancelar");                                     
	JButton btnDeshacer = new JButton("Deshacer");  
	JButton btnFiltro = new JButton("Filtro");
	
	String Descanso="";
	
	JTextField txtNombre = new JTextField();                                           
	                               
	Date date = new Date();	
	
	    //Domingo//                                                                    
	  SpinnerDateModel sm1 =  new SpinnerDateModel();
	  JSpinner spDomingo1 = new JSpinner(sm1);                                         
	  JSpinner.DateEditor de1 = new JSpinner.DateEditor(spDomingo1,"H:mm");              
	                                                                                   
	  SpinnerDateModel sm2 =  new SpinnerDateModel();
	  JSpinner spDomingo2 = new JSpinner(sm2);                                         
	  JSpinner.DateEditor de2 = new JSpinner.DateEditor(spDomingo2,"H:mm");          
	  
		SpinnerDateModel sm3 =  new SpinnerDateModel();
	  JSpinner spDomingo3 = new JSpinner(sm3);                                         
	  JSpinner.DateEditor de3 = new JSpinner.DateEditor(spDomingo3,"H:mm");              
	                                                                                   
	SpinnerDateModel sm4 =  new SpinnerDateModel();
	  JSpinner spDomingo4 = new JSpinner(sm4);                                         
	  JSpinner.DateEditor de4 = new JSpinner.DateEditor(spDomingo4,"H:mm");              

	SpinnerDateModel sm5 =  new SpinnerDateModel();
	  JSpinner spDomingo5 = new JSpinner(sm5);                                         
	  JSpinner.DateEditor de5 = new JSpinner.DateEditor(spDomingo5,"H:mm");              
	                                                                                                                                                                 
	  //Lunes//                                                                        
	  SpinnerDateModel sml1 =  new SpinnerDateModel();
	  JSpinner spLunes1 = new JSpinner(sml1);                                          
	  JSpinner.DateEditor del1 = new JSpinner.DateEditor(spLunes1,"H:mm");               
	                                                                                   
	  SpinnerDateModel sml2 =  new SpinnerDateModel();
	  JSpinner spLunes2 = new JSpinner(sml2);                                          
	  JSpinner.DateEditor del2 = new JSpinner.DateEditor(spLunes2,"H:mm");           
	                                                                                   
	SpinnerDateModel sml3 =  new SpinnerDateModel();
	  JSpinner spLunes3 = new JSpinner(sml3);                                          
	  JSpinner.DateEditor del3 = new JSpinner.DateEditor(spLunes3,"H:mm");               
	                                                                                   
	SpinnerDateModel sml4 =  new SpinnerDateModel();
	  JSpinner spLunes4 = new JSpinner(sml4);                                          
	  JSpinner.DateEditor del4 = new JSpinner.DateEditor(spLunes4,"H:mm");               
	                                                                                   
	SpinnerDateModel sml5 =  new SpinnerDateModel();
	  JSpinner spLunes5 = new JSpinner(sml5);                                          
	  JSpinner.DateEditor del5 = new JSpinner.DateEditor(spLunes5,"H:mm");               
	                                                                                   
	  //Martes//                                                                       
	  SpinnerDateModel smm1 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spMartes1 = new JSpinner(smm1);                                         
	  JSpinner.DateEditor dem1 = new JSpinner.DateEditor(spMartes1,"H:mm");              
	                                                                                   
	  SpinnerDateModel smm2 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spMartes2 = new JSpinner(smm2);                                         
	  JSpinner.DateEditor dem2 = new JSpinner.DateEditor(spMartes2,"H:mm");          
	                                                                                   
	SpinnerDateModel smm3 =  new SpinnerDateModel();
	  JSpinner spMartes3 = new JSpinner(smm3);                                         
	  JSpinner.DateEditor dem3 = new JSpinner.DateEditor(spMartes3,"H:mm");              
	                                                                                   
	SpinnerDateModel smm4 =  new SpinnerDateModel();
	  JSpinner spMartes4 = new JSpinner(smm4);                                         
	  JSpinner.DateEditor dem4 = new JSpinner.DateEditor(spMartes4,"H:mm");              
	                                                                                   
	SpinnerDateModel smm5 =  new SpinnerDateModel();
	  JSpinner spMartes5 = new JSpinner(smm5);                                         
	  JSpinner.DateEditor dem5 = new JSpinner.DateEditor(spMartes5,"H:mm");              
	                                                                                   
	  //Miercoles//                                                                    
	  SpinnerDateModel smmi1 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spMiercoles1 = new JSpinner(smmi1);                                     
	  JSpinner.DateEditor demmi1 = new JSpinner.DateEditor(spMiercoles1,"H:mm");         
	                                                                                   
	  SpinnerDateModel smmi2 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spMiercoles2 = new JSpinner(smmi2);                                     
	  JSpinner.DateEditor demmi2 = new JSpinner.DateEditor(spMiercoles2,"H:mm");     
	                                                                                   
	SpinnerDateModel smmi3 =  new SpinnerDateModel();
	  JSpinner spMiercoles3 = new JSpinner(smmi3);                                     
	  JSpinner.DateEditor demmi3 = new JSpinner.DateEditor(spMiercoles3,"H:mm");         
	                                                                                   
	SpinnerDateModel smmi4 =  new SpinnerDateModel();
	  JSpinner spMiercoles4 = new JSpinner(smmi4);                                     
	  JSpinner.DateEditor demmi4 = new JSpinner.DateEditor(spMiercoles4,"H:mm");         
	                                                                                   
	SpinnerDateModel smmi5 =  new SpinnerDateModel();
	  JSpinner spMiercoles5 = new JSpinner(smmi5);                                     
	  JSpinner.DateEditor demmi5 = new JSpinner.DateEditor(spMiercoles5,"H:mm");         
	                                                                                   
	  //Jueves//                                                                       
	  SpinnerDateModel smj1 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spJueves1 = new JSpinner(smj1);                                         
	  JSpinner.DateEditor dej1 = new JSpinner.DateEditor(spJueves1,"H:mm");              
	                                                                                   
	  SpinnerDateModel smj2 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spJueves2 = new JSpinner(smj2);                                         
	  JSpinner.DateEditor dej2 = new JSpinner.DateEditor(spJueves2,"H:mm");          
	                                                                                   
	SpinnerDateModel smj3 =  new SpinnerDateModel();
	  JSpinner spJueves3 = new JSpinner(smj3);                                         
	  JSpinner.DateEditor dej3 = new JSpinner.DateEditor(spJueves3,"H:mm");              
	                                                                                   
	SpinnerDateModel smj4 =  new SpinnerDateModel();
	  JSpinner spJueves4 = new JSpinner(smj4);                                         
	  JSpinner.DateEditor dej4 = new JSpinner.DateEditor(spJueves4,"H:mm");              
	                                                                                   
	SpinnerDateModel smj5 =  new SpinnerDateModel();
	  JSpinner spJueves5 = new JSpinner(smj5);                                         
	  JSpinner.DateEditor dej5 = new JSpinner.DateEditor(spJueves5,"H:mm");              
	                                                                                   
	  //Viernes//                                                                      
	  SpinnerDateModel smv1 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spViernes1 = new JSpinner(smv1);                                        
	  JSpinner.DateEditor dev1 = new JSpinner.DateEditor(spViernes1,"H:mm");             
	                                                                                   
	  SpinnerDateModel smv2 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spViernes2 = new JSpinner(smv2);                                        
	  JSpinner.DateEditor dev2 = new JSpinner.DateEditor(spViernes2,"H:mm");         
	                                                                                   
	SpinnerDateModel smv3 =  new SpinnerDateModel();
	  JSpinner spViernes3 = new JSpinner(smv3);                                        
	  JSpinner.DateEditor dev3 = new JSpinner.DateEditor(spViernes3,"H:mm");             
	                                                                                   
	SpinnerDateModel smv4 =  new SpinnerDateModel();
	  JSpinner spViernes4 = new JSpinner(smv4);                                        
	  JSpinner.DateEditor dev4 = new JSpinner.DateEditor(spViernes4,"H:mm");             
	                                                                                   
	SpinnerDateModel smv5 =  new SpinnerDateModel();
	  JSpinner spViernes5 = new JSpinner(smv5);                                        
	  JSpinner.DateEditor dev5 = new JSpinner.DateEditor(spViernes5,"H:mm");             

	  //Sabado//                                                                       
	  SpinnerDateModel sms1 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spSabado1 = new JSpinner(sms1);                                         
	  JSpinner.DateEditor des1 = new JSpinner.DateEditor(spSabado1,"H:mm");              
	                                                                                   
	  SpinnerDateModel sms2 =  new SpinnerDateModel(date, null, null,Calendar.HOUR_OF_DAY);
	  JSpinner spSabado2 = new JSpinner(sms2);                                         
	  JSpinner.DateEditor des2 = new JSpinner.DateEditor(spSabado2,"H:mm");          
	                                                                                   
	SpinnerDateModel sms3 =  new SpinnerDateModel();
	  JSpinner spSabado3 = new JSpinner(sms3);                                         
	  JSpinner.DateEditor des3 = new JSpinner.DateEditor(spSabado3,"H:mm");              
	                                                                                   
	SpinnerDateModel sms4 =  new SpinnerDateModel();
	  JSpinner spSabado4 = new JSpinner(sms4);                                         
	  JSpinner.DateEditor des4 = new JSpinner.DateEditor(spSabado4,"H:mm");              
	                                                                                   
	SpinnerDateModel sms5 =  new SpinnerDateModel();
	  JSpinner spSabado5 = new JSpinner(sms5);                                         
	  JSpinner.DateEditor des5 = new JSpinner.DateEditor(spSabado5,"H:mm");              
	                                                                                   
	JLabel lblDia = new JLabel("Día");                                                 
	JLabel lblLimi = new JLabel("Límites del día");                                    
	JLabel lblInicio = new JLabel("Inicio");                                           
	JLabel lblFin = new JLabel("Fin");                                                 
	                                                                                   
	JLabel lblTrabajo = new JLabel("Trabajo");                                         
	JLabel lblEntrada = new JLabel("Entrada");                                         
	JLabel lblSalida = new JLabel("Salida");                                           
	                                                                                   
	JLabel lblComida = new JLabel("Comida");                                           
	JLabel lblReceso = new JLabel("Receso");
}
