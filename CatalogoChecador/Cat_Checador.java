package CatalogoChecador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import ObjetoChecador.Obj_Entosal;
import ObjetoChecador.Obj_Traer_Checador;

import objetos.JTextFieldLimit;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Checador extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Cat_Reloj trae_hora = new Cat_Reloj();
	
	private DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Traer_Checador().get_tabla_model(),
			new String[]{"Folio", "Nombre", "Entosal", "H Evento",
				"T Retardo", "Alerta", "PC", "IP","Tipo de Entrada"}){
				
			@SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
			   	java.lang.Object.class,
			   	java.lang.Object.class, 
			   	java.lang.Object.class, 
			   	java.lang.Object.class, 
			   	java.lang.Object.class, 
			   	java.lang.Object.class, 
			   	java.lang.Object.class, 
			   	java.lang.Object.class,  
			   	java.lang.Object.class,  
			   	java.lang.Object.class
			    	
		    };
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		    public boolean isCellEditable(int fila, int columna){
		       	 switch(columna){
		       	 	case 0  : return false; 
		      	 	case 1  : return false; 
		       	 	case 3  : return false; 
		       	 	case 4  : return false; 
		       	 	
		       	 	case 5  : return false;
		       	 	case 6  : return false; 
		       	 	case 7  : return false; 
		       	 	case 8  : return false; 
		       	 	
		       	 }
		 		return false;
		 	}
		};
	
		private JTable tabla = new JTable(tabla_model);
		JScrollPane panelScroll = new JScrollPane(tabla);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
	
		JLabel lblFolio = new JLabel("Numero de Empleado:");
		JTextField txtFolio = new JTextField();
		
		JLabel lblClave = new JLabel("Clave:");
		JPasswordField txtClaveReal = new JPasswordField();
		
		static JLabel fondo = new JLabel(new ImageIcon("Imagen/calaFondoChecador.jpg"));
		
		JButton btnEmplorar = new JButton("");
		
		JLabel lblFecha = new JLabel("");
		JLabel lblLogo = new JLabel("");
		JLabel lblCerrar = new JLabel("");
		JButton btnFoto = new JButton("");
		
		JLabel lblNota = new JLabel("");
		JLabel lblNota2 = new JLabel("");
		
		JLabel lblNombre = new JLabel("Empleado: ");
		JLabel lblEstablecimiento = new JLabel("Establecimiento: ");
		JLabel lblPuesto = new JLabel("Puesto: ");
		JLabel lblHorario = new JLabel("Horario: ");
		
	//	float escalar = 0.5F; // una ventana al 50% del tamaño de la pantalla
		int anchoMon = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width);
	//	int anchoMon = 1366;
		int altoMon = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height)-30;
	
	public Cat_Checador(){
		
		this.init_tabla();
		
		lblFolio.setForeground(Color.BLUE);
		lblFolio.setFont(new Font("Arial",0,12));
		
		lblClave.setForeground(Color.BLUE);
		lblClave.setFont(new Font("Arial",0,12));
		
		lblFecha.setForeground(Color.BLUE);
		lblFecha.setFont(new Font("Algerian",0,40));
		
		lblNota2.setForeground(Color.BLUE);
		lblNota2.setFont(new Font("Arial",0,28));
		
		lblNota.setForeground(Color.BLUE);
		lblNota.setFont(new Font("Arial",0,28));
		
		lblNota2.setForeground(Color.BLUE);
		lblNota2.setFont(new Font("Arial",0,28));
		
		lblNombre.setFont(new Font("Monospaced",0,14));
		lblEstablecimiento.setFont(new Font("Monospaced",0,14));
		lblPuesto.setFont(new Font("Monospaced",0,14));
		lblHorario.setFont(new Font("Monospaced",0,14));
		
//		btnFoto.setBorder(null);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Checador"));
		this.setTitle("Checador");
		
		int x = 15, y=80, ancho=100;
		
		if(anchoMon <= 1380){
//					panel.add(new JLabel("Numero de Empleado:")).setBounds(x+20,y,ancho+50,20);
//					panel.add(txtFolio).setBounds(ancho+50+20,y,ancho,20);
//					
//					panel.add(new JLabel("Clave:")).setBounds(x+20,y+=30,ancho,20);
//					panel.add(txtClaveReal).setBounds(ancho-20+20,y+20,ancho+70,20);
//					
//					panel.add(trae_hora.lblHora).setBounds(290,45, 550, 120);
//					panel.add(lblNota).setBounds(290,y+=100, 900, 30);
//					
//					panel.add(lblFecha).setBounds(935+33+222,-5, 300, 40);
//					panel.add(lblLogo).setBounds(anchoMon-25,40, 166, 166);
//					panel.add(lblCerrar).setBounds(anchoMon-25,40, 166, 166);
//					panel.add(btnFoto).setBounds(anchoMon-166-35,40, 166, 166);
//					
//					panel.add(lblNombre).setBounds(1000,40,280,20);
//					panel.add(lblEstablecimiento).setBounds(1000,50,280,20);
//					panel.add(lblPuesto).setBounds(1000,60,280,20);
//					panel.add(lblHorario).setBounds(1000,70,280,20);
//					
//					panel.add(panelScroll).setBounds(x+20,y+=25,ancho+1200,altoMon-280);
		}else{
					panel.add(lblFolio).setBounds(x+10,y,ancho+50,20);
					panel.add(txtFolio).setBounds(ancho+50+20,y,ancho,20);
					
					panel.add(lblClave).setBounds(x+10,y+=40,ancho,20);
					panel.add(txtClaveReal).setBounds(ancho-20+20,y,ancho+70,20);
					
					panel.add(trae_hora.lblHora).setBounds(300,45, 550, 120);
					panel.add(lblNota).setBounds(130,y+=145, 900, 30);
					panel.add(lblNota2).setBounds(190,y+=30, 900, 30);
					
					panel.add(lblFecha).setBounds(835+33+242,190, 300, 40);
					panel.add(lblLogo).setBounds(935+33-237,34, 147, 147);
					panel.add(lblCerrar).setBounds(935+33+522,34, 147, 147);
					panel.add(btnFoto).setBounds(935+33,35, 147, 147);
					
					panel.add(lblNombre).setBounds(1130,40,320,20);
					panel.add(lblEstablecimiento).setBounds(1130,80,320,20);
					panel.add(lblPuesto).setBounds(1130,120,320,20);
					panel.add(lblHorario).setBounds(1130,160,320,20);
					
					panel.add(panelScroll).setBounds(32,y+65,ancho+800,altoMon-380);
					panel.add(fondo).setBounds(-30,-20,1660,900);
		}
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtClaveReal.setDocument(new JTextFieldLimit(30));

		txtClaveReal.setEditable(false);
		
		String fileLogo = System.getProperty("user.dir")+"/Imagen/LogPrincipal3.png";
		ImageIcon tmpIconLogo = new ImageIcon(fileLogo);
		Icon iconoLogo = new ImageIcon(tmpIconLogo.getImage().getScaledInstance(147, 147, Image.SCALE_DEFAULT));
		lblLogo.setIcon(iconoLogo);	
		
		String fileCerrar = System.getProperty("user.dir")+"/Imagen/cerrar.png";
		ImageIcon tmpIconCerrar = new ImageIcon(fileCerrar);
		Icon iconoCerrar = new ImageIcon(tmpIconCerrar.getImage().getScaledInstance(120, 150, Image.SCALE_DEFAULT));
		lblCerrar.setIcon(iconoCerrar);
		
		String fileFoto = System.getProperty("user.dir")+"/Iconos/Un.JPG";
		ImageIcon tmpIconAuxFoto = new ImageIcon(fileFoto);
		Icon iconoFoto = new ImageIcon(tmpIconAuxFoto.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
		btnFoto.setIcon(iconoFoto);	
		
		txtFolio.addKeyListener(action_buscar);
		txtClaveReal.addKeyListener(action_entosal_clave);
		
//	----------------------------------------------------------------------------------------------------------------	
		cont.add(panel);
		
		this.setSize(anchoMon,altoMon);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

//		asigna el foco al JTextField deseado al arrancar la ventana
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
		        txtFolio.requestFocus();
		     }
		});
		
		lblCerrar.addMouseListener ( new  MouseAdapter ()  
			{  
				public void mouseReleased (MouseEvent e)  
				{  
		    		dispose();
		    	}  
			});  
		}
	
	@SuppressWarnings("rawtypes")
	public Object[] fila(int folio,String t_entrada){
		
//metodo para llenar vector para checador--------------------------------------
		Object [] vector = new Object[10];
		
		if(new Obj_Empleado().insertar(folio,t_entrada)){
			Vector fila_sql = new Obj_Entosal().buscar_hora_entosal(folio);

			for(int i=0; i<fila_sql.size(); i++){
				vector[i] = "   "+fila_sql.get(i);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Error al momento de checar","Error",JOptionPane.ERROR_MESSAGE);
		}
			
		return vector;
	}
	
//SE BUSCA AL EMPLEADO Y SE ASIGNA EL VALOR A LA VARIABLE (numero_de_checador) PARA SER COMPROVADA POSTERIORMENTE AL SOLICITAR LA CLAVE(no_checador)
	int numero_de_checador;
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
				
				txtFolio.setEditable(false);
				txtClaveReal.setEditable(true);
				txtClaveReal.requestFocus();
				
				if(txtFolio.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
					txtFolio.setEditable(true);
					txtFolio.setText("");
					txtFolio.requestFocus();
					txtClaveReal.setText("");
					txtClaveReal.setEditable(false);
				}else{
						Obj_Empleado re = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
						
						if(re.getFolio() != 0){	
								
							txtFolio.setText(re.getFolio()+"");
							numero_de_checador = re.getNo_checador();
							
							ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
							Icon icono = new ImageIcon(tmpIconAux.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
							btnFoto.setIcon(icono);	
							
							lblNombre.setText("Empleado: ");
							lblEstablecimiento.setText("Establecimiento: ");
							lblPuesto.setText("Puesto: ");
							lblHorario.setText("Horario: ");
						    
							txtFolio.setEditable(false);
							txtClaveReal.setEditable(true);
							txtClaveReal.requestFocus();
							
						}
						else{
							JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
							txtClaveReal.setEditable(false);
							panelLimpiar();
							return;
						}
				}
				
			}
		}
		@Override
		public void keyReleased(KeyEvent e){}
	};
	
	
//FUNCION PARA CONFIRMAR  EL No_Checador(clave) Y REGISTRAR MOVIMIENTO DE ENTRADA O SALIDA A TRAVES DE UN ENTER 
	KeyListener action_entosal_clave = new KeyListener() {
		@SuppressWarnings("deprecation")
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();

		   if(((caracter < '0') ||
		        (caracter > '9'))||
		        (caracter == KeyEvent.VK_BACK_QUOTE)&&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
			   
		    	e.consume(); 
		    	
		    }else{
		    	txtClaveReal.setText(txtClaveReal.getText()+"  ");
		    }			
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
						if(txtClaveReal.getText().equals("")){
							
								JOptionPane.showMessageDialog(null, "la clave es requerida \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
								txtFolio.setEditable(true);
								txtFolio.setText("");
								txtFolio.requestFocus();
								txtClaveReal.setText("");
								txtClaveReal.setEditable(false);
							
						}else{	
							
							Obj_Empleado re = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
								switch (re.getStatus()){
								 case 1:registrarEntrada(); break;
								 case 2:JOptionPane.showMessageDialog(null, "No Puedes Checar Tu Estatus es de Vacaciones Favor de Comunicarte a Desarrollo Humano, Para que Puedas Registrar tu Entrada a Trabajar, de lo Contrario no te Sera Valido el Pago de este Dia","Aviso",JOptionPane.WARNING_MESSAGE);
								    txtFolio.setEditable(true);
									txtFolio.setText("");
									txtFolio.requestFocus();
									txtClaveReal.setText("");
									txtClaveReal.setEditable(false);
								    break;
								 case 3:JOptionPane.showMessageDialog(null, "No Puedes Checar Tu Estatus es de Incapacidad Favor de Comunicarte a Desarrollo Humano, Para que Puedas Registrar tu Entrada a Trabajar, de lo Contrario no te Sera Valido el Pago de este Dia","Aviso",JOptionPane.WARNING_MESSAGE);
							        txtFolio.setEditable(true);
									txtFolio.setText("");
									txtFolio.requestFocus();
									txtClaveReal.setText("");
									txtClaveReal.setEditable(false);
						     		 break;
								 case 4:JOptionPane.showMessageDialog(null, "No Puedes Checar Tu Estatus es de Baja Favor de Comunicarte a Desarrollo Humano, Para que Puedas Registrar tu Entrada a Trabajar, de lo Contrario no te Sera Valido el Pago de este Dia","Aviso",JOptionPane.WARNING_MESSAGE);
								    txtFolio.setEditable(true);
									txtFolio.setText("");
									txtFolio.requestFocus();
									txtClaveReal.setText("");
									txtClaveReal.setEditable(false);
									break;
								 case 5:JOptionPane.showMessageDialog(null, "No Puedes Checar Tu Estatus es de Baja Favor de Comunicarte a Desarrollo Humano, Para que Puedas Registrar tu Entrada a Trabajar, de lo Contrario no te Sera Valido el Pago de este Dia","Aviso",JOptionPane.WARNING_MESSAGE);
								    txtFolio.setEditable(true);
									txtFolio.setText("");
									txtFolio.requestFocus();
									txtClaveReal.setText("");
									txtClaveReal.setEditable(false);
									break;
									};
									
							}			
					}		
			}
		
		@Override
		public void keyReleased(KeyEvent e){
			
		}
								
	};
	
	public void registrarEntrada(){
		
//meter split para que extraiga el puro numero
//		declarar variable que cachara el valor real de la clave
	       String CadenaDeClave = "";
//	       recorer la cadena de la clave para eliminar los espacion
	       for (int x=0; x < txtClaveReal.getText().length(); x++) {
//	     	condicion(si el caracter en la posicion ubicada es diferente de vacio entra y asigna asta)
	     	  if (txtClaveReal.getText().charAt(x) != ' ')
//	       		toma el valor de CadenaDeClave y le asigna el siguiente caracter
	      	    CadenaDeClave += txtClaveReal.getText().charAt(x);
		  }
        
		if(Integer.parseInt(CadenaDeClave) == numero_de_checador){
			 if(new Obj_Entosal().checar_dia_descanso(Integer.parseInt(txtFolio.getText()))){ 	
					 JOptionPane.showMessageDialog(null, "El Dia de Hoy lo Tienes Registrado Como tu Dia de Descanso Favor de Avisar a Desarrollo Humano Para que Puedas Registrar tu Entrada a Trabajar, de lo Contrario no te Sera Valido el Pago de este Dia ","aviso",JOptionPane.WARNING_MESSAGE);
					 JOptionPane.showMessageDialog(null, "El Dia de Hoy lo Tienes Registrado Como tu Dia de Descanso Favor de Avisar a Desarrollo Humano Para que Puedas Registrar tu Entrada a Trabajar, de lo Contrario no te Sera Valido el Pago de este Dia ","aviso",JOptionPane.INFORMATION_MESSAGE); 
			 }else{
				
					if(new Obj_Entosal().buscar_colicion(Integer.parseInt(txtFolio.getText()))){
								JOptionPane.showMessageDialog(null, "Estas Intentando Checar 2 Veces en Menos de 1 Minuto Espere un Momento y Reintente","aviso",JOptionPane.WARNING_MESSAGE);
					}else{
							if(new Obj_Entosal().checadas_dia_dobla(Integer.parseInt(txtFolio.getText()))){
								 JOptionPane.showMessageDialog(null, "A Excedido El Numero de Checadas Son 4 Para Turno Normal y 6 Para Dia que Doblan ","aviso",JOptionPane.INFORMATION_MESSAGE);
							}else{
					
								Obj_Empleado re = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
								
								if(re.getFolio() != 0 && re.getNo_checador() == Integer.parseInt(CadenaDeClave)){
							
										ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
										Icon icono = new ImageIcon(tmpIconAux.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
										btnFoto.setIcon(icono);	
										
										Object[] registro = fila(Integer.parseInt(txtFolio.getText()),"-");
										
										tabla_model.addRow(registro);
										String tipo=registro[2].toString();
										String hora=registro[3].toString();
										
										String Fecha=registro[9].toString();
										lblFecha.setText(Fecha);
										
										txtFolio.setEditable(true);
										txtFolio.setText("");
										txtFolio.requestFocus();
										txtClaveReal.setText("");
										txtClaveReal.setEditable(false);
								
												if(Integer.parseInt(registro[3].toString().trim().substring(0,2))<2){
														lblNota.setText("EL EMPLEADO "+re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
														lblNota2.setText("A CHECADO "+tipo+" A LA "+hora.substring(0,9)+" Hr");
					
												}else{
														lblNota.setText("EL EMPLEADO "+re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
														lblNota2.setText("A  CHECADO "+tipo+" A LAS "+hora.substring(0,9)+" Hrs");
													}
								
										lblNombre.setText(lblNombre.getText() + re.getNombre() + " "+re.getAp_paterno() + " "+re.getAp_materno());
										
										Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(re.getEstablecimiento());
										lblEstablecimiento.setText(lblEstablecimiento.getText() + comboNombreEsta.getNombre());
				
										Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(re.getPuesto());
										lblPuesto.setText(lblPuesto.getText() + comboNombrePues.getPuesto());
										
										txtFolio.setEditable(false);
										txtClaveReal.setEditable(true);
										txtClaveReal.requestFocus();
							
								}else{
										JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.ERROR_MESSAGE);
										txtFolio.setEditable(true);
										panelLimpiar();
										return;
									}
							}
					}
				}
	
				txtFolio.setEditable(true);
				txtClaveReal.setEditable(false);
				txtFolio.setText("");
				txtClaveReal.setText("");
				txtFolio.requestFocus();
			
		}else{
			
			Obj_Empleado re = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
			Obj_Entosal entosalClave = new Obj_Entosal().buscar(Integer.parseInt(CadenaDeClave));
			
				if(re.getFolio()!= 0 && entosalClave.getClave() == Integer.parseInt(CadenaDeClave)){
					ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
					Icon icono = new ImageIcon(tmpIconAux.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
					btnFoto.setIcon(icono);	
					
					Object[] registro = fila(Integer.parseInt(txtFolio.getText()),"MASTER");
					
					tabla_model.addRow(registro);
					String tipo=registro[2].toString();
					String hora=registro[3].toString();
					
					String Fecha=registro[9].toString();
					lblFecha.setText(Fecha);
					
					txtFolio.setEditable(true);
					txtFolio.setText("");
					txtFolio.requestFocus();
					txtClaveReal.setText("");
					txtClaveReal.setEditable(false);
					
						if(Integer.parseInt(registro[3].toString().trim().substring(0,2))<2){
							lblNota.setText("EL EMPLEADO "+re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
							lblNota2.setText("A CHECADO "+tipo+" A LA "+hora.substring(0,9)+" Hr");

						}else{
							lblNota.setText("EL EMPLEADO "+re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
							lblNota2.setText("A  CHECADO "+tipo+" A LAS "+hora.substring(0,9)+" Hrs");
						}
					
					lblNombre.setText(lblNombre.getText() + re.getNombre() + " "+re.getAp_paterno() + " "+re.getAp_materno());
					
					Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(re.getEstablecimiento());
					lblEstablecimiento.setText(lblEstablecimiento.getText() + comboNombreEsta.getNombre());

					Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(re.getPuesto());
					lblPuesto.setText(lblPuesto.getText() + comboNombrePues.getPuesto());
					
					txtFolio.setEditable(false);
					txtClaveReal.setEditable(true);
					txtClaveReal.requestFocus();
					
				}else{
					 	JOptionPane.showMessageDialog(null, "la clave es Incorrecta \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						txtFolio.setEditable(true);
						txtFolio.setText("");
						txtFolio.requestFocus();
						txtClaveReal.setText("");
						txtClaveReal.setEditable(false);
					}
				 }
			
			txtFolio.setEditable(true);
			txtFolio.setText("");
			txtFolio.requestFocus();
			txtClaveReal.setText("");
			txtClaveReal.setEditable(false);
	}
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.exit(getDefaultCloseOperation());		
		}
	};
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtClaveReal.setText("");
	}
	
	int x;			int y;			int z;
	
	@SuppressWarnings("unchecked")
	public void init_tabla(){
		
		if(anchoMon <= 1380){
			x=100;
			y=360;
			z=140;
		}else{
			x=50;
			y=280;
			z=80;
		}
		
		this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(x);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(x);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(y);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(y);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(z);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(z);
		this.tabla.getColumnModel().getColumn(3).setMaxWidth(z);
		this.tabla.getColumnModel().getColumn(3).setMinWidth(z);
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(z);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(z);
		
		this.tabla.getColumnModel().getColumn(5).setMaxWidth(z);
		this.tabla.getColumnModel().getColumn(5).setMinWidth(z);
		this.tabla.getColumnModel().getColumn(6).setMaxWidth(z);
		this.tabla.getColumnModel().getColumn(6).setMinWidth(z);
		this.tabla.getColumnModel().getColumn(7).setMaxWidth(z);
		this.tabla.getColumnModel().getColumn(7).setMinWidth(z);
		this.tabla.getColumnModel().getColumn(8).setMaxWidth(z+10);
		this.tabla.getColumnModel().getColumn(8).setMinWidth(z+10);
		
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				Component componente = null;
			
				switch(column){
					case 0: 
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
						
					case 1:
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
						
					case 2: 
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
						
					case 3: 
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
						
					case 5: 
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
						
					case 6: 
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
						
					case 7: 
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
						
					case 8: 
						componente = new JLabel(value == null? "": value.toString());
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
				}
				return componente;
			} 
		}; 
		for(int i=0; i<tabla.getColumnCount(); i++){
			this.tabla.getColumnModel().getColumn(i).setCellRenderer(render); 
		}
		
		this.tabla.setRowSorter(trsfiltro);  
				
    }
	
//	ActionListener opExplorar = new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			
//			 JFileChooser selector=new JFileChooser();
//             selector.setFileFilter(new FileFilter());
//             int estado=selector.showOpenDialog(null);
//             File archivoelegido=selector.getSelectedFile();
//String                          ruta=archivoelegido.getPath();
//             if(archivoelegido.exists())
//                     System.out.println("bien");
//             else
//                             System.out.println("no bien");
//             if(estado==JFileChooser.APPROVE_OPTION);
//             {
//                     ImageIcon imagen = new ImageIcon("ruta");
//             JLabel etiqueta = new JLabel(imagen);
//             etiqueta.setBounds(20,30,60,36);
//                     panel.add(etiqueta);
//             }
//		}
//	};
	
	public static void main (String [] arg){
		try{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		Cat_Checador thisClass = new Cat_Checador();
		thisClass.setVisible(true);

		//utilizacion del AWTUtilities con el metodo opaque
		try {
			   @SuppressWarnings("rawtypes")
			Class clazz =  Class.forName("com.sun.awt.AWTUtilities");
			   @SuppressWarnings("unchecked")
			Method method = clazz.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);
			   method.invoke(clazz,thisClass , false);
			   } catch (Exception e) 
			   { }	
	}
		
}
