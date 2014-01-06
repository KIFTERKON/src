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
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
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
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ObjetoChecador.Obj_Entosal;
import ObjetoChecador.Obj_Traer_Checador;

import objetos.JTextFieldLimit;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Checador extends JFrame {
	// DECLARAMOS EL OBJETO RUNTIME PARA EJECUTAR APLICACIONES
	Runtime R = Runtime.getRuntime();
        Container cont = getContentPane();
        JLayeredPane panel = new JLayeredPane();
        
        Cat_Reloj trae_hora = new Cat_Reloj();
        
        public static DefaultTableModel tabla_model = new DefaultTableModel(
        		new Obj_Traer_Checador().get_tabla_model(),	new String[]{	"Folio",	"Nombre", "EntoSal", "H Evento", "T Retardo", 
        																	"Alerta",	"PC",		"IP",	  "Tipo Entrada"}){
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
                                        case 2  : return false; 
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
        
                static JTable tabla = new JTable(tabla_model);
                JScrollPane panelScroll = new JScrollPane(tabla);
                
                JLabel lblClave = new JLabel("Clave:");
                JPasswordField txtClaveReal = new JPasswordField();
                
                JButton btnEmplorar = new JButton("");
                
                static JLabel lblSemaforoRojo = new JLabel("");
                static JLabel lblSemaforoVerde = new JLabel("");
                JLabel lblLogo = new JLabel("");
                JLabel lblCerrar = new JLabel("");
                
                JButton btnFoto = new JButton("");
                JLabel lblFecha = new JLabel("");
                
                JLabel lblNota = new JLabel("");
                JLabel lblNota2 = new JLabel("");
                
                JLabel lblNombre = new JLabel("Emp: ");
                JLabel lblEstablecimiento = new JLabel("Estab: ");
                JLabel lblPuesto = new JLabel("Puesto: ");
                JLabel lblHorario = new JLabel("Horario: ");
                
                JLabel btnMensaje = new JLabel("");
                JButton btnChecar = new JButton("Checar sin gafete");
//                JButton btnExaminar = new JButton("Examinar");
                
                JScrollPane barra_mensaje= new JScrollPane();
                JTextArea txaAvisos = new JTextArea("");
                ImageIcon img = new ImageIcon("imagen/txa.jpg");
                
                
                int anchoMon = Toolkit.getDefaultToolkit().getScreenSize().width;
        		int altoMon = Toolkit.getDefaultToolkit().getScreenSize().height;
                
                static JLabel fondo = new JLabel();
              
                Font font;
                
                String semaforoR = System.getProperty("user.dir")+"/Imagen/semaforo_rojo_chica.png";
                ImageIcon tmpIconSemR = new ImageIcon(semaforoR);
                
                String semaforoV = System.getProperty("user.dir")+"/Imagen/semaforo_verde_chica.png";
                ImageIcon tmpIconSemV = new ImageIcon(semaforoV);
                
                String fileLogo = System.getProperty("user.dir")+"/Imagen/LogPrincipal3.png";
                ImageIcon tmpIconLogo = new ImageIcon(fileLogo);
                
                String fileCerrar = System.getProperty("user.dir")+"/Imagen/cerrar.png";
                ImageIcon tmpIconCerrar = new ImageIcon(fileCerrar);
              
                String fileFondo = "Imagen/calaFondoChecador.jpg";
                String fileFondo2 = "Imagen/calaFondoChecador2.jpg";
                ImageIcon tmpIconAuxFondo;
               
                String fileFoto = System.getProperty("user.dir")+"/Iconos/Un.JPG";
                ImageIcon tmpIconAuxFoto = new ImageIcon(fileFoto);
                
                Icon iconoFondo;
                
                Icon iconoSemaforoR;
                Icon iconoSemaforoV;
                
                Icon iconoLogo;
                Icon iconoCerrar;
                
                
        public Cat_Checador(){
                
                this.init_tabla();
                Resolucion(anchoMon, altoMon);
                
                Icon iconoFoto = new ImageIcon(tmpIconAuxFoto.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
                btnFoto.setIcon(iconoFoto);
                
                txtClaveReal.setDocument(new JTextFieldLimit(100));
                
//                btnExaminar.addActionListener(opExaminar);
                
                txtClaveReal.addKeyListener(action_registrar_entrada);
                btnChecar.addActionListener(opChecar);
                
                lblSemaforoRojo.setEnabled(false);
                lblSemaforoVerde.setEnabled(false);
             
//           asigna el foco al JTextField deseado al arrancar la ventana
              this.addWindowListener(new WindowAdapter() {
                      public void windowOpened( WindowEvent e ){
                      txtClaveReal.requestFocus();
                   }
              });
                
//           pone el foco en el txtFolio al presionar la tecla scape
                getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                   KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "foco");
                
                getRootPane().getActionMap().put("foco", new AbstractAction(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                                     txtClaveReal.setText("");
                                     txtClaveReal.requestFocus();
                    }
                });
                
                tabla.addMouseListener(new MouseListener() {
					public void mousePressed(MouseEvent e) {
						txtClaveReal.setText("");
                        txtClaveReal.requestFocus();
					}
					public void mouseClicked(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
				});
                
                cont.add(panel);
                
                if(anchoMon>=1280){
                	this.setSize(1280,768);
                }else{
                	this.setSize(anchoMon,altoMon);
                }
                
                this.setResizable(false);
                this.setUndecorated(true);
                this.setLocationRelativeTo(null);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                lblCerrar.addMouseListener ( new  MouseAdapter (){
                        public void mouseReleased (MouseEvent e){
                             dispose();
                 			try {
                				R.exec("taskkill /f /im javaw.exe");
                			} catch (Exception e2){}
                        }  
                 });  
        }
        
        KeyListener action_registrar_entrada = new KeyListener() {
			public void keyPressed(KeyEvent e) {	
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
						checar();
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        }; 
        
        ActionListener opChecar = new ActionListener(){
            @SuppressWarnings("deprecation")
      	public void actionPerformed(ActionEvent e) {
          	  Obj_Entosal entosalClave = new Obj_Entosal().buscar();
          	  
          	  txtClaveReal.setText(txtClaveReal.getText()+"C"+entosalClave.getClave());
          	  checar();
            }
      };
        
        int folio_empleado;
        String claveMaster;
        
        public void checar(){        
        	if(txtClaveReal.getText().toUpperCase().equals("")){
				 
				 lblSemaforoRojo.setEnabled(true);
				 lblSemaforoVerde.setEnabled(false);
	                
                JOptionPane.showMessageDialog(null, "La clave es requerida \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
                txtClaveReal.setText("");
                txtClaveReal.requestFocus();
                return;
			 }else{   
				 	
				 String codigoBarrar = txtClaveReal.getText().toUpperCase().trim();
				 
				 	int posicionC = codigoBarrar.indexOf('C');
				 	
				 	if(posicionC>0){
				 		
							if(isNumeric(codigoBarrar.substring(0, posicionC))){
								
								folio_empleado = Integer.parseInt(codigoBarrar.substring(0, posicionC));
								claveMaster = codigoBarrar.substring(posicionC+1,codigoBarrar.length());
								
								Obj_Empleado re = new Obj_Empleado().buscar(folio_empleado);  //busca a empleado 
		                        Obj_Entosal entosalClave = new Obj_Entosal().buscar(); //busca clave maestra
		                 
			                        if(re.getFolio()==folio_empleado){
			                        	
		                           		switch (re.getStatus()){
		                                         case 1: if(re.getNo_checador().equals(codigoBarrar)){
		                                            		 		registrarEntrada("-");
		                                            	 }else{
		                                            		 if(entosalClave.getClave().equals(claveMaster)){
		                                            			 	registrarEntrada("MASTER");
		                                            		 }else{
		                                            			 
			                                            			 lblSemaforoRojo.setEnabled(true);
			                                                         lblSemaforoVerde.setEnabled(false);
		                                                         
		                                                			 JOptionPane.showMessageDialog(null, "La clave no corresponde","Aviso",JOptionPane.WARNING_MESSAGE);
		                                                			 txtClaveReal.setText("");
		                                                			 txtClaveReal.requestFocus();
		                                                           return;
		                                            		 }
		                                            	 }
		                                          break;
		                                          case 2:	lblSemaforoRojo.setEnabled(true);
		                                          			lblSemaforoVerde.setEnabled(false);
		                                          			JOptionPane.showMessageDialog(null, "No puedes checar, tu estatus es vacaciones\nfavor de comunicarte a desarrollo humano,\npara que puedas registrar tu entrada a trabajar,\nde lo contrario no te sera valido el pago de este dia","Aviso",JOptionPane.WARNING_MESSAGE);
		                                                                         txtClaveReal.setText("");
		                                                                         txtClaveReal.requestFocus();
		                                          break;
		                                          case 3:	lblSemaforoRojo.setEnabled(true);
		                                          			lblSemaforoVerde.setEnabled(false);
		                                          			JOptionPane.showMessageDialog(null, "No puedes checar, tu estatus es de incapacidad\nfavor de comunicarte a desarrollo humano,\npara que puedas registrar tu entrada a trabajar,\nde lo contrario no te sera valido el pago de este dia","Aviso",JOptionPane.WARNING_MESSAGE);
		                                                                         txtClaveReal.setText("");
		                                                                         txtClaveReal.requestFocus();
		                                          break;
		                                          case 4:	lblSemaforoRojo.setEnabled(true);
		                                          			lblSemaforoVerde.setEnabled(false);
		                                          			JOptionPane.showMessageDialog(null, "No puedes checar, tu estatus es de baja\nfavor de comunicarte a desarrollo humano,\npara que puedas registrar tu entrada a trabajar,\nde lo contrario no te sera valido el pago de este dia","Aviso",JOptionPane.WARNING_MESSAGE);
		                                                                         txtClaveReal.setText("");
		                                                                         txtClaveReal.requestFocus();
		                                          break;
		                                          case 5:	lblSemaforoRojo.setEnabled(true);
		                                          			lblSemaforoVerde.setEnabled(false);
		                                          			JOptionPane.showMessageDialog(null, "No puedes checar, tu estatus es de baja\nfavor de comunicarte a desarrollo humano,\npara que puedas registrar tu entrada a trabajar,\nde lo contrario no te sera valido el pago de este dia","Aviso",JOptionPane.WARNING_MESSAGE);
		                                                                         txtClaveReal.setText("");
		                                                                         txtClaveReal.requestFocus();
		                                          break;
		                                          case 6: if(re.getNo_checador().equals(codigoBarrar)){
				                                          		 		registrarEntrada("-");
				                                          	 }else{
				                                          		 if(entosalClave.getClave().equals(claveMaster)){
				                                          			 	registrarEntrada("MASTER");
				                                          		 }else{
				                                          			 
					                                            			 lblSemaforoRojo.setEnabled(true);
					                                                         lblSemaforoVerde.setEnabled(false);
				                                                       
				                                              			 JOptionPane.showMessageDialog(null, "La clave no corresponde","Aviso",JOptionPane.WARNING_MESSAGE);
				                                              			 txtClaveReal.setText("");
				                                              			 txtClaveReal.requestFocus();
				                                                         return;
				                                          		 }
				                                          	 }
				                                  break;  
		                                };
                               
								}else{
									lblSemaforoRojo.setEnabled(true);
									lblSemaforoVerde.setEnabled(false);
					                
			                         JOptionPane.showMessageDialog(null, "El empleado no se encontro", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			                         txtClaveReal.setText("");
			                         txtClaveReal.requestFocus();
			                         return;
								}
					 	}else{
	                   		 lblSemaforoRojo.setEnabled(true);
                           lblSemaforoVerde.setEnabled(false);
                       
              			 JOptionPane.showMessageDialog(null, "La clave no es valida","Aviso",JOptionPane.WARNING_MESSAGE);
              			 txtClaveReal.setText("");
              			 txtClaveReal.requestFocus();
                         return;
	                        }
				 }else{
				 		lblSemaforoRojo.setEnabled(true);
						 lblSemaforoVerde.setEnabled(false);
			                
                        JOptionPane.showMessageDialog(null, "La clave es incorrecta", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
                        txtClaveReal.setText("");
                        txtClaveReal.requestFocus();
                        return;
			 		}
			 }
        }
        
        @SuppressWarnings("deprecation")
        public void registrarEntrada(String checada){
//----------------------------------------------------------------------------------------------------------------------        
////meter split para que extraiga el puro numero
////                        declarar variable que cachara el valor real de la clave
//                        String CadenaDeClave = "";
//                        for (int x=0; x < txtClaveReal.getText().length(); x++) {
////                     condicion(si el caracter en la posicion ubicada es diferente de vacio entra y asigna)
////                    toma el valor de CadenaDeClave y le asigna el siguiente caracter
//                                if (txtClaveReal.getText().charAt(x) != ' ')
//                                        CadenaDeClave += txtClaveReal.getText().charAt(x);
//                        }
//----------------------------------------------------------------------------------------------------------------------        
//                if(txtClaveReal.getText().toUpperCase().equals(numero_de_checador)){
//-----------------------------------------
        if(new Obj_Entosal().checar_dia_descanso(folio_empleado)){   
		        	lblSemaforoRojo.setEnabled(true);
		            lblSemaforoVerde.setEnabled(false);
                         JOptionPane.showMessageDialog(null, "El dia de hoy lo tienes registrado como tu dia de descanso,\nfavor de avisar a desarrollo humano para que pueda regustrar su entrada a trabajar,\nde lo contrario n ote sera valido el pago de este dia","Aviso",JOptionPane.WARNING_MESSAGE);
                         JOptionPane.showMessageDialog(null, "El dia de hoy lo tienes registrado como tu dia de descanso,\nfavor de avisar a desarrollo humano para que pueda regustrar su entrada a trabajar,\nde lo contrario n ote sera valido el pago de este dia","Aviso",JOptionPane.INFORMATION_MESSAGE);
                                 
                                                txtClaveReal.setText("");
                                                txtClaveReal.requestFocus();
                         return;
         }else{
                if(new Obj_Entosal().buscar_colicion(folio_empleado)){
                	lblSemaforoRojo.setEnabled(true);
                    lblSemaforoVerde.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Estas intentando checar 2 veces en menos\nde 1 minuto espere un momento y reintente","Aviso",JOptionPane.WARNING_MESSAGE);
                                        txtClaveReal.setText("");
                                        txtClaveReal.requestFocus();
                        return;
                }else{
                        if(new Obj_Entosal().checadas_dia_dobla(folio_empleado)){
                        	lblSemaforoRojo.setEnabled(true);
                            lblSemaforoVerde.setEnabled(false);
                                JOptionPane.showMessageDialog(null, "A excedido el numero de checadas son 4 para turno normal\ny 6 para el dia que tienen 15 minutos extras ","Aviso",JOptionPane.INFORMATION_MESSAGE);
                                txtClaveReal.setText("");
                                txtClaveReal.requestFocus();
                                return;
                        }else{
                              if(new Obj_Entosal().checa_salida_comer(folio_empleado)){
                                        new Cat_Checador_Selecion_Comida((folio_empleado),checada).setVisible(true);
                               }else{
	                                    Obj_Empleado re = new Obj_Empleado().buscar(folio_empleado);
	                                    Obj_Entosal entosalClave = new Obj_Entosal().buscar();
	                                    
	                                    if(re.getNo_checador().equals(txtClaveReal.getText().toUpperCase())||entosalClave.getClave().equals(claveMaster)){
	                                    	
	                                    	ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
	         		                        Icon icono = new ImageIcon(tmpIconAux.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
	         		                        btnFoto.setIcon(icono);  
	                                    	
	                                    	Object[] registro = fila2(folio_empleado,checada,0);
			
			                                    String tipo=registro[2].toString();
			                                    String hora=registro[3].toString();
			                                    
			                                    String Fecha=registro[9].toString();
			                                    lblFecha.setText(Fecha);
			                                    
			                                    txtClaveReal.setText("");
			                                    txtClaveReal.requestFocus();
	            
		                                            if(Integer.parseInt(registro[3].toString().trim().substring(0,2))<2){
		                                                            lblNota.setText("EL EMPLEADO "+re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
		                                                            lblNota2.setText("A CHECADO "+tipo+" A LA "+hora.substring(0,9)+" Hr");
		
		                                            }else{
		                                                            lblNota.setText("EL EMPLEADO "+re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
		                                                            lblNota2.setText("A  CHECADO "+tipo+" A LAS "+hora.substring(0,9)+" Hrs");
		                                            }
	                                            
		                                        lblNombre.setText("Emp: ");
		                                        lblEstablecimiento.setText("Estab: ");
		                                        lblPuesto.setText("Puesto: ");
		                                        lblHorario.setText("Horario: ");
                                                            
	                                            lblNombre.setText(lblNombre.getText() + re.getNombre() + " "+re.getAp_paterno() + " "+re.getAp_materno());
	                                            
	                                            Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(re.getEstablecimiento());
	                                            lblEstablecimiento.setText(lblEstablecimiento.getText() + comboNombreEsta.getNombre());
	
	                                            Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(re.getPuesto());
	                                            lblPuesto.setText(lblPuesto.getText() + comboNombrePues.getPuesto());
	                                            
	                                            txtClaveReal.requestFocus(); 		
                                 }else{
	                                	 lblSemaforoRojo.setEnabled(true);
	                                     lblSemaforoVerde.setEnabled(false);
                                                JOptionPane.showMessageDialog(null, "La clave no corresponde","Error",JOptionPane.WARNING_MESSAGE);
                                                panelLimpiar();
                                                txtClaveReal.requestFocus();
                                                return;
                                    }
                               }
                        }
             }
        }
                txtClaveReal.setText("");
                txtClaveReal.requestFocus();
   }
        
//        ActionListener opExaminar = new ActionListener(){
//                public void actionPerformed(ActionEvent e) {
//                        FileDialog file = new FileDialog(new Frame());
//                        
//                        file.setTitle("Selecciona una Imagen");
//                        file.setMode(FileDialog.LOAD);
//                        file.setVisible(true);
//                        
//                        if(file.getDirectory() != null){
//
//                                try {
//                                        String rootPicture = file.getDirectory()+file.getFile();
//                                        
//                                        File foto = new File(rootPicture);
//                                        File destino = new File(System.getProperty("user.dir")+"/tmp/mensaje.jpg");
//                                    
//                                        InputStream in = new FileInputStream(foto);
//                                        OutputStream out = new FileOutputStream(destino);
//                                        
//	                                    byte[] buf = new byte[1024];
//	                                    int len;
//
//	                                    while ((len = in.read(buf)) > 0) {
//	                                            out.write(buf, 0, len);
//	                                    }
//                                    
//	                                    in.close();
//	                                    out.close();
//                                        
//                                        String fileFoto = System.getProperty("user.dir")+"/tmp/mensaje.jpg";
//                                        ImageIcon tmpIconAuxFoto = new ImageIcon(fileFoto);
//                                        Icon iconoFoto = new ImageIcon(tmpIconAuxFoto.getImage().getScaledInstance(btnMensaje.getWidth(), btnMensaje.getHeight(), Image.SCALE_DEFAULT));
//                                        btnMensaje.setIcon(iconoFoto);        
//                                } catch (IOException e1) {
//                                        e1.printStackTrace();
//                                }
//                        }else{
//                                JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna imagen","Aviso",JOptionPane.WARNING_MESSAGE);
//                                return;
//                        }
//                }
//        };

//        ActionListener cerrar = new ActionListener(){
//                public void actionPerformed(ActionEvent e){
//                        System.exit(getDefaultCloseOperation());                
//                }
//        };
        
        public void panelLimpiar(){        
                txtClaveReal.setText("");
        }
        
        int x;                        int y;                        int z;
        
        @SuppressWarnings("static-access")
		public void init_tabla(){
                this.tabla.getTableHeader().setReorderingAllowed(false) ;
                
                if(anchoMon <= 1380){
                        x=45;
                        y=245;
                        z=60;
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
                        
                        this.tabla.getColumnModel().getColumn(5).setMaxWidth(z-10);
                        this.tabla.getColumnModel().getColumn(5).setMinWidth(z-10);
                        this.tabla.getColumnModel().getColumn(6).setMaxWidth(z+10);
                        this.tabla.getColumnModel().getColumn(6).setMinWidth(z+10);
                        this.tabla.getColumnModel().getColumn(7).setMaxWidth(z+30);
                        this.tabla.getColumnModel().getColumn(7).setMinWidth(z+30);
                        this.tabla.getColumnModel().getColumn(8).setMaxWidth(z+30);
                        this.tabla.getColumnModel().getColumn(8).setMinWidth(z+30);
                        
                }else{
                        x=50;
                        y=280;
                        z=80;
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
                        
                        this.tabla.getColumnModel().getColumn(5).setMaxWidth(z-40);
                        this.tabla.getColumnModel().getColumn(5).setMinWidth(z-40);
                        this.tabla.getColumnModel().getColumn(6).setMaxWidth(z+30);
                        this.tabla.getColumnModel().getColumn(6).setMinWidth(z+30);
                        this.tabla.getColumnModel().getColumn(7).setMaxWidth(z+10);
                        this.tabla.getColumnModel().getColumn(7).setMinWidth(z+10);
                        this.tabla.getColumnModel().getColumn(8).setMaxWidth(z+10);
                        this.tabla.getColumnModel().getColumn(8).setMinWidth(z+10);
                }
                
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
                                                        ((JComponent) componente).setOpaque(true); 
                                                        
                                                        int retardo = Integer.parseInt(tabla_model.getValueAt(row,4).toString().trim());
                                                        
                                                        if(retardo<4){
                                                                componente.setBackground(Color.green);
                                                                }
                                                        if(retardo>=4 && retardo<7){
                                                                componente.setBackground(Color.yellow);
                                                                }
                                                        if(retardo>=7 && retardo<=10){
                                                                componente.setBackground(new java.awt.Color(243,97,0));
                                                        }
                                                        if(retardo>10){
                                                                componente.setBackground(new java.awt.Color(255,0,0));
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
    }

        @SuppressWarnings("rawtypes")
        public static Object[] fila2(int folio_empleado,String tipo_entrada,int tipo_salida_comer){
                
//metodo para llenar vector para checador2--------------------------------------
                Object [] vector = new Object[10];
                
                if(new Obj_Empleado().insertar_comida(folio_empleado,tipo_entrada,tipo_salida_comer)){
                	lblSemaforoRojo.setEnabled(false);
                    lblSemaforoVerde.setEnabled(true);
                 Vector fila_sql=new Obj_Entosal().buscar_hora_entosal(folio_empleado);
                 for(int i=0 ; i<fila_sql.size(); i++ ){
                         vector[i]= "   "+ fila_sql.get(i);
                 }
                 
                        while(tabla.getRowCount()>0){
                                tabla_model.removeRow(0);
                        }
                        
                        Object [][] lista_tabla = new Obj_Traer_Checador().get_tabla_model();
                        String[] fila = new String[9];
                                for(int i=0; i<lista_tabla.length; i++){
                                        fila[0] = lista_tabla[i][0]+"";
                                        fila[1] = lista_tabla[i][1]+"";
                                        fila[2] = lista_tabla[i][2]+"";
                                        fila[3] = lista_tabla[i][3]+"";
                                        fila[4] = lista_tabla[i][4]+"";
                                        fila[5] = lista_tabla[i][5]+"";
                                        fila[6] = lista_tabla[i][6]+"";
                                        fila[7] = lista_tabla[i][7]+"";
                                        fila[8] = lista_tabla[i][8]+"";
                                        tabla_model.addRow(fila);
                                }
                }else{
                	lblSemaforoRojo.setEnabled(true);
                    lblSemaforoVerde.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Error al momento de checar","Error",JOptionPane.ERROR_MESSAGE);
                }
                return vector;
        }
        
        private static boolean isNumeric(String cadena){
        	try {
        		Integer.parseInt(cadena);
        		return true;
        	} catch (NumberFormatException nfe){
        		return false;
        	}
        }
        
    	public void Resolucion(int ancho, int alto){
    		int x = 10, y = 30, z = 100;
    		if(ancho == 800){
//    			valores unicos(800*600)
    			tmpIconAuxFondo = new ImageIcon(fileFondo2);
                iconoFondo = new ImageIcon(tmpIconAuxFondo.getImage().getScaledInstance(anchoMon,altoMon, Image.SCALE_DEFAULT));
                fondo.setIcon(iconoFondo);
                
    			trae_hora.lblHora.setFont(new java.awt.Font("Algerian",0,70));
    			
   			 lblClave.setForeground(Color.BLUE);
                lblClave.setFont(new Font("Arial",0,10));
                
                lblFecha.setForeground(Color.BLUE);
                lblFecha.setFont(new Font("Algerian",0,20));
                
                lblNota.setForeground(Color.BLUE);
                lblNota.setFont(new Font("Arial",0,20));
                
                lblNota2.setForeground(Color.BLUE);
                lblNota2.setFont(new Font("Arial",0,20));
                
                lblNombre.setFont(new Font("Monospaced",0,9));
                lblEstablecimiento.setFont(new Font("Monospaced",0,9));
                lblPuesto.setFont(new Font("Monospaced",0,9));
                lblHorario.setFont(new Font("Monospaced",0,9));
                
               panel.add(lblClave).setBounds(5,y,z,20);
               panel.add(txtClaveReal).setBounds(x*4,y,z-18,20);
               
               panel.add(trae_hora.lblHora).setBounds(x*13,y-20, z*5, 100);
               panel.add(lblNota).setBounds(x*6,y+=115, 800, 30);
               panel.add(lblNota2).setBounds(100,y+=30, 800, 30);
               
               panel.add(lblFecha).setBounds(570,100, 150, 30);
               panel.add(lblSemaforoRojo).setBounds(15,52, 40, 40);
               panel.add(lblSemaforoVerde).setBounds(70,52, 40, 40);
               panel.add(lblLogo).setBounds((anchoMon/2)-45, 21, 75, 75);
               panel.add(lblCerrar).setBounds(anchoMon-62,21, 70, 75);
               panel.add(btnFoto).setBounds(anchoMon-334,21,77,77);
               
               panel.add(lblNombre).setBounds(550,22,190,10);
               panel.add(lblEstablecimiento).setBounds(550,40,190,10);
               panel.add(lblPuesto).setBounds(550,58,190,10);
               panel.add(lblHorario).setBounds(550,76,190,10);
               
               panel.add(panelScroll).setBounds(15,y+63,773,altoMon-250);
               panel.add(fondo).setBounds(0,0,anchoMon,altoMon);
               
               iconoSemaforoR = new ImageIcon(tmpIconSemR.getImage().getScaledInstance(lblSemaforoRojo.getWidth(), lblSemaforoRojo.getHeight(), Image.SCALE_DEFAULT));
               lblSemaforoRojo.setIcon(iconoSemaforoR);
               
               iconoSemaforoV = new ImageIcon(tmpIconSemV.getImage().getScaledInstance(lblSemaforoVerde.getWidth(), lblSemaforoVerde.getHeight(), Image.SCALE_DEFAULT));
               lblSemaforoVerde.setIcon(iconoSemaforoV);
               
               iconoLogo = new ImageIcon(tmpIconLogo.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
               lblLogo.setIcon(iconoLogo);        
       
               iconoCerrar = new ImageIcon(tmpIconCerrar.getImage().getScaledInstance(70, 75, Image.SCALE_DEFAULT));
               lblCerrar.setIcon(iconoCerrar);
    			
    		}
    		if(ancho == 1024){
//    			valores unicos(1024*768)
    			tmpIconAuxFondo = new ImageIcon(fileFondo2);
                iconoFondo = new ImageIcon(tmpIconAuxFondo.getImage().getScaledInstance(anchoMon,altoMon, Image.SCALE_DEFAULT));
                fondo.setIcon(iconoFondo);
                
    			txaAvisos.setLineWrap(true);
                barra_mensaje.setOpaque(false);
                barra_mensaje.setViewportView(txaAvisos);
                
    			trae_hora.lblHora.setFont(new java.awt.Font("Algerian",0,80));
    			
    			lblClave.setForeground(Color.BLUE);
                lblClave.setFont(new Font("Arial",0,10));
                
                lblFecha.setForeground(Color.BLUE);
                lblFecha.setFont(new Font("Algerian",0,20));
                
                lblNota.setForeground(Color.BLUE);
                lblNota.setFont(new Font("Arial",0,30));
                
                lblNota2.setForeground(Color.BLUE);
                lblNota2.setFont(new Font("Arial",0,30));
                
                lblNombre.setFont(new Font("Monospaced",0,11));
                lblEstablecimiento.setFont(new Font("Monospaced",0,11));
                lblPuesto.setFont(new Font("Monospaced",0,11));
                lblHorario.setFont(new Font("Monospaced",0,11));
                
                txaAvisos.setBackground(new java.awt.Color(0,0,205));
                txaAvisos.setForeground(new java.awt.Color(255,69,0));
                
                Font font = new Font("Verdana", Font.BOLD, 24);
                txaAvisos.setFont(font);
               
               panel.add(lblClave).setBounds(5,y,z,20);
               panel.add(txtClaveReal).setBounds(x*4,y,z-18,20);
               
               panel.add(trae_hora.lblHora).setBounds(x*18,y-5, z*5, 100);
               panel.add(lblNota).setBounds(x*6,y+=165, 800, 30);
               panel.add(lblNota2).setBounds(100,y+=30, 800, 30);
               
               panel.add(lblFecha).setBounds(780,130, 150, 30);
               panel.add(lblSemaforoRojo).setBounds(5,52, 70, 70);
               panel.add(lblSemaforoVerde).setBounds(85,52, 70, 70);
               panel.add(lblLogo).setBounds((anchoMon/2)-58, 27, 95, 95);
               panel.add(lblCerrar).setBounds(anchoMon-79,28, 85, 95);
               panel.add(btnFoto).setBounds(anchoMon-431,24,105,105);
               
               panel.add(lblNombre).setBounds(705,30,230,20);
               panel.add(lblEstablecimiento).setBounds(705,55,230,20);
               panel.add(lblPuesto).setBounds(705,80,230,20);
               panel.add(lblHorario).setBounds(705,105,230,20);
               
               panel.add(barra_mensaje).setBounds(810,y-58,210,580);
               
               panel.add(panelScroll).setBounds(17,y+73,783,altoMon-320);
               panel.add(fondo).setBounds(0,0,anchoMon,altoMon);
               
               iconoSemaforoR = new ImageIcon(tmpIconSemR.getImage().getScaledInstance(lblSemaforoRojo.getWidth(), lblSemaforoRojo.getHeight(), Image.SCALE_DEFAULT));
               lblSemaforoRojo.setIcon(iconoSemaforoR);
               
               iconoSemaforoV = new ImageIcon(tmpIconSemV.getImage().getScaledInstance(lblSemaforoVerde.getWidth(), lblSemaforoVerde.getHeight(), Image.SCALE_DEFAULT));
               lblSemaforoVerde.setIcon(iconoSemaforoV);
               
               iconoLogo = new ImageIcon(tmpIconLogo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
               lblLogo.setIcon(iconoLogo);        
       
               iconoCerrar = new ImageIcon(tmpIconCerrar.getImage().getScaledInstance(lblCerrar.getWidth(), lblCerrar.getHeight(), Image.SCALE_DEFAULT));
               lblCerrar.setIcon(iconoCerrar);
    		}
    		if(ancho == 1152){
    			switch(alto){
//    			case 720: 
//    				
//    			break;
    			case 864:
    				tmpIconAuxFondo = new ImageIcon(fileFondo2);
                    iconoFondo = new ImageIcon(tmpIconAuxFondo.getImage().getScaledInstance(anchoMon,altoMon, Image.SCALE_DEFAULT));
                    fondo.setIcon(iconoFondo);
                    
        			txaAvisos.setLineWrap(true);
                    barra_mensaje.setOpaque(false);
                    barra_mensaje.setViewportView(txaAvisos);
                    
        			trae_hora.lblHora.setFont(new java.awt.Font("Algerian",0,90));
        			
        			lblClave.setForeground(Color.BLUE);
                    lblClave.setFont(new Font("Arial",0,12));
                    
                    lblFecha.setForeground(Color.BLUE);
                    lblFecha.setFont(new Font("Algerian",0,22));
                    
                    lblNota.setForeground(Color.BLUE);
                    lblNota.setFont(new Font("Arial",0,35));
                    
                    lblNota2.setForeground(Color.BLUE);
                    lblNota2.setFont(new Font("Arial",0,35));
                    
                    lblNombre.setFont(new Font("Monospaced",0,11));
                    lblEstablecimiento.setFont(new Font("Monospaced",0,11));
                    lblPuesto.setFont(new Font("Monospaced",0,11));
                    lblHorario.setFont(new Font("Monospaced",0,11));
                    
                    txaAvisos.setBackground(new java.awt.Color(0,0,205));
                    txaAvisos.setForeground(new java.awt.Color(255,69,0));
                    
                    Font font = new Font("Verdana", Font.BOLD, 24);
                    txaAvisos.setFont(font);
                   
                   panel.add(lblClave).setBounds(5,y,z,20);
                   panel.add(txtClaveReal).setBounds(x*4,y,z-18,20);
                   
                   panel.add(trae_hora.lblHora).setBounds(x*20,y+5, z*5, 100);
                   panel.add(lblNota).setBounds(x*6,y+=185, 800, 40);
                   panel.add(lblNota2).setBounds(100,y+=40, 800, 40);
                   
                   panel.add(lblFecha).setBounds(850,160, 150, 30);
                   panel.add(lblSemaforoRojo).setBounds(15,62, 70, 70);
                   panel.add(lblSemaforoVerde).setBounds(95,62, 70, 70);
                   panel.add(lblLogo).setBounds((anchoMon/2)-68, 27, 115, 115);
                   panel.add(lblCerrar).setBounds(anchoMon-89,28, 90, 110);
                   panel.add(btnFoto).setBounds(anchoMon-484,29,115,115);
                   
                   panel.add(lblNombre).setBounds(790,30,270,20);
                   panel.add(lblEstablecimiento).setBounds(790,55,270,20);
                   panel.add(lblPuesto).setBounds(790,80,270,20);
                   panel.add(lblHorario).setBounds(790,105,270,20);
                   
                   panel.add(barra_mensaje).setBounds(915,y-68,220,645);
                   
                   panel.add(panelScroll).setBounds(72,y+73,783,altoMon-360);
                   panel.add(fondo).setBounds(0,0,anchoMon,altoMon);
                   
                   iconoSemaforoR = new ImageIcon(tmpIconSemR.getImage().getScaledInstance(lblSemaforoRojo.getWidth(), lblSemaforoRojo.getHeight(), Image.SCALE_DEFAULT));
                   lblSemaforoRojo.setIcon(iconoSemaforoR);
                   
                   iconoSemaforoV = new ImageIcon(tmpIconSemV.getImage().getScaledInstance(lblSemaforoVerde.getWidth(), lblSemaforoVerde.getHeight(), Image.SCALE_DEFAULT));
                   lblSemaforoVerde.setIcon(iconoSemaforoV);
                   
                   iconoLogo = new ImageIcon(tmpIconLogo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
                   lblLogo.setIcon(iconoLogo);        
           
                   iconoCerrar = new ImageIcon(tmpIconCerrar.getImage().getScaledInstance(lblCerrar.getWidth(), lblCerrar.getHeight(), Image.SCALE_DEFAULT));
                   lblCerrar.setIcon(iconoCerrar);
                 break;
    			}
    		}
    		if(ancho >= 1280){
    			switch(alto){
    			case 600:
    				tmpIconAuxFondo = new ImageIcon(fileFondo);
                    iconoFondo = new ImageIcon(tmpIconAuxFondo.getImage().getScaledInstance(anchoMon,altoMon, Image.SCALE_DEFAULT));
                    fondo.setIcon(iconoFondo);
                    
        			txaAvisos.setLineWrap(true);
                    barra_mensaje.setOpaque(false);
                    barra_mensaje.setViewportView(txaAvisos);
                    
        			trae_hora.lblHora.setFont(new java.awt.Font("Algerian",0,100));
        			
        			lblClave.setForeground(Color.BLUE);
                    lblClave.setFont(new Font("Arial",0,12));
                    
                    lblFecha.setForeground(Color.BLUE);
                    lblFecha.setFont(new Font("Algerian",0,22));
                    
                    lblNota.setForeground(Color.BLUE);
                    lblNota.setFont(new Font("Arial",0,20));
                    
                    lblNota2.setForeground(Color.BLUE);
                    lblNota2.setFont(new Font("Arial",0,20));
                    
                    lblNombre.setFont(new Font("Monospaced",0,11));
                    lblEstablecimiento.setFont(new Font("Monospaced",0,11));
                    lblPuesto.setFont(new Font("Monospaced",0,11));
                    lblHorario.setFont(new Font("Monospaced",0,11));
                    
                    txaAvisos.setBackground(new java.awt.Color(0,0,205));
                    txaAvisos.setForeground(new java.awt.Color(255,69,0));
                    
                    font = new Font("Verdana", Font.BOLD, 24);
                    txaAvisos.setFont(font);
                   
                   panel.add(lblClave).setBounds(45,y,z,20);
                   panel.add(txtClaveReal).setBounds(x*8,y,z-18,20);
                   
                   panel.add(trae_hora.lblHora).setBounds(x*24,y, z*5, 100);
                   panel.add(lblNota).setBounds(x*12,y+=145, 800, 40);
                   panel.add(lblNota2).setBounds(160,y+=30, 800, 40);
                   
                   panel.add(lblFecha).setBounds(900,135, 250, 30);
                   panel.add(lblSemaforoRojo).setBounds(35,55, 70, 70);
                   panel.add(lblSemaforoVerde).setBounds(115,55, 70, 70);
                   panel.add(lblLogo).setBounds((anchoMon/2)-45, 27, 105, 105);
                   panel.add(lblCerrar).setBounds(anchoMon-89,28, 90, 100);
                   panel.add(btnFoto).setBounds(anchoMon-505,28,118,100);
                   
                   panel.add(lblNombre).setBounds(900,30,290,20);
                   panel.add(lblEstablecimiento).setBounds(900,55,290,20);
                   panel.add(lblPuesto).setBounds(900,80,290,20);
                   panel.add(lblHorario).setBounds(900,105,290,20);
                   
                   panel.add(panelScroll).setBounds(17,y+53,733,altoMon-268);
                   panel.add(fondo).setBounds(0,0,anchoMon,altoMon);
                   
                   iconoSemaforoR = new ImageIcon(tmpIconSemR.getImage().getScaledInstance(lblSemaforoRojo.getWidth(), lblSemaforoRojo.getHeight(), Image.SCALE_DEFAULT));
                   lblSemaforoRojo.setIcon(iconoSemaforoR);
                   
                   iconoSemaforoV = new ImageIcon(tmpIconSemV.getImage().getScaledInstance(lblSemaforoVerde.getWidth(), lblSemaforoVerde.getHeight(), Image.SCALE_DEFAULT));
                   lblSemaforoVerde.setIcon(iconoSemaforoV);
                   
                   iconoLogo = new ImageIcon(tmpIconLogo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
                   lblLogo.setIcon(iconoLogo);        
           
                   iconoCerrar = new ImageIcon(tmpIconCerrar.getImage().getScaledInstance(lblCerrar.getWidth(), lblCerrar.getHeight(), Image.SCALE_DEFAULT));
                   lblCerrar.setIcon(iconoCerrar);
                 break;
    			case 720:
    				tmpIconAuxFondo = new ImageIcon(fileFondo);
                    iconoFondo = new ImageIcon(tmpIconAuxFondo.getImage().getScaledInstance(1280,720, Image.SCALE_DEFAULT));
                    fondo.setIcon(iconoFondo);
    				
        			txaAvisos.setLineWrap(true);
                    barra_mensaje.setOpaque(false);
                    barra_mensaje.setViewportView(txaAvisos);
                    
        			trae_hora.lblHora.setFont(new java.awt.Font("Algerian",0,100));
        			
        			lblClave.setForeground(Color.BLUE);
                    lblClave.setFont(new Font("Arial",0,12));
                    
                    lblFecha.setForeground(Color.BLUE);
                    lblFecha.setFont(new Font("Algerian",0,30));
                    
                    lblNota.setForeground(Color.BLUE);
                    lblNota.setFont(new Font("Arial",0,22));
                    
                    lblNota2.setForeground(Color.BLUE);
                    lblNota2.setFont(new Font("Arial",0,22));
                    
                    lblNombre.setFont(new Font("Monospaced",0,14));
                    lblEstablecimiento.setFont(new Font("Monospaced",0,14));
                    lblPuesto.setFont(new Font("Monospaced",0,14));
                    lblHorario.setFont(new Font("Monospaced",0,14));
                   
                   panel.add(lblClave).setBounds(45,y+15,z,20);
                   panel.add(txtClaveReal).setBounds(x*8,y+15,z-18,20);
                   
                   panel.add(trae_hora.lblHora).setBounds(x*24,y+10, z*5, 100);
                   panel.add(lblNota).setBounds(x*10,y+=185, 800, 40);
                   panel.add(lblNota2).setBounds(150,y+=30, 800, 40);
                   
                   panel.add(lblFecha).setBounds(910,171, 250, 30);
                   panel.add(lblSemaforoRojo).setBounds(35,70, 70, 70);
                   panel.add(lblSemaforoVerde).setBounds(115,70, 70, 70);
                   panel.add(lblLogo).setBounds((1280/2)-60, 33, 125, 125);
                   panel.add(lblCerrar).setBounds(1280-89,33, 90, 123);
                   panel.add(btnFoto).setBounds(1280-505,33,118,123);
                   
                   panel.add(lblNombre).setBounds(900,33,290,20);
                   panel.add(lblEstablecimiento).setBounds(900,63,290,20);
                   panel.add(lblPuesto).setBounds(900,93,290,20);
                   panel.add(lblHorario).setBounds(900,123,290,20);
                   
                   panel.add(panelScroll).setBounds(19,y+62,733,401);
                   panel.add(fondo).setBounds(0,0,1280,720);
                   
                   iconoSemaforoR = new ImageIcon(tmpIconSemR.getImage().getScaledInstance(lblSemaforoRojo.getWidth(), lblSemaforoRojo.getHeight(), Image.SCALE_DEFAULT));
                   lblSemaforoRojo.setIcon(iconoSemaforoR);
                   
                   iconoSemaforoV = new ImageIcon(tmpIconSemV.getImage().getScaledInstance(lblSemaforoVerde.getWidth(), lblSemaforoVerde.getHeight(), Image.SCALE_DEFAULT));
                   lblSemaforoVerde.setIcon(iconoSemaforoV);
                   
                   iconoLogo = new ImageIcon(tmpIconLogo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
                   lblLogo.setIcon(iconoLogo);        
           
                   iconoCerrar = new ImageIcon(tmpIconCerrar.getImage().getScaledInstance(lblCerrar.getWidth(), lblCerrar.getHeight(), Image.SCALE_DEFAULT));
                   lblCerrar.setIcon(iconoCerrar);
                 break;
    			default:
    				tmpIconAuxFondo = new ImageIcon(fileFondo);
                    iconoFondo = new ImageIcon(tmpIconAuxFondo.getImage().getScaledInstance(1280,768, Image.SCALE_DEFAULT));
                    fondo.setIcon(iconoFondo);
    				
        			txaAvisos.setLineWrap(true);
                    barra_mensaje.setOpaque(false);
                    barra_mensaje.setViewportView(txaAvisos);
                    
        			trae_hora.lblHora.setFont(new java.awt.Font("Algerian",0,100));
        			
        			lblClave.setForeground(Color.BLUE);
                    lblClave.setFont(new Font("Arial",0,12));
                    
                    lblFecha.setForeground(Color.BLUE);
                    lblFecha.setFont(new Font("Algerian",0,30));
                    
                    lblNota.setForeground(Color.BLUE);
                    lblNota.setFont(new Font("Arial",0,22));
                    
                    lblNota2.setForeground(Color.BLUE);
                    lblNota2.setFont(new Font("Arial",0,22));
                    
                    lblNombre.setFont(new Font("Monospaced",0,14));
                    lblEstablecimiento.setFont(new Font("Monospaced",0,14));
                    lblPuesto.setFont(new Font("Monospaced",0,14));
                    lblHorario.setFont(new Font("Monospaced",0,14));
                   
                   panel.add(lblClave).setBounds(45,y+25,z,20);
                   panel.add(txtClaveReal).setBounds(x*8,y+25,z-18,20);
                   
                   panel.add(trae_hora.lblHora).setBounds(x*24,y+20, z*5, 100);
                   panel.add(lblNota).setBounds(x*10,y+=205, 800, 40);
                   panel.add(lblNota2).setBounds(150,y+=30, 800, 40);
                   
                   panel.add(lblFecha).setBounds(910,176, 250, 30);
                   panel.add(lblSemaforoRojo).setBounds(35,80, 70, 70);
                   panel.add(lblSemaforoVerde).setBounds(115,80, 70, 70);
                   
                   panel.add(btnChecar).setBounds(45,180,130,20);
                   
                   panel.add(lblLogo).setBounds((1280/2)-60, 38, 127, 127);
                   panel.add(lblCerrar).setBounds(1280-89,38, 90, 130);
                   panel.add(btnFoto).setBounds(1280-505,38,118,128);
                   
                   panel.add(lblNombre).setBounds(900,38,290,20);
                   panel.add(lblEstablecimiento).setBounds(900,68,290,20);
                   panel.add(lblPuesto).setBounds(900,98,290,20);
                   panel.add(lblHorario).setBounds(900,128,290,20);
                   
                   panel.add(panelScroll).setBounds(19,y+62,733,429);
                   panel.add(fondo).setBounds(0,0,1280,768);
                   
                   iconoSemaforoR = new ImageIcon(tmpIconSemR.getImage().getScaledInstance(lblSemaforoRojo.getWidth(), lblSemaforoRojo.getHeight(), Image.SCALE_DEFAULT));
                   lblSemaforoRojo.setIcon(iconoSemaforoR);
                   
                   iconoSemaforoV = new ImageIcon(tmpIconSemV.getImage().getScaledInstance(lblSemaforoVerde.getWidth(), lblSemaforoVerde.getHeight(), Image.SCALE_DEFAULT));
                   lblSemaforoVerde.setIcon(iconoSemaforoV);
                   
                   iconoLogo = new ImageIcon(tmpIconLogo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
                   lblLogo.setIcon(iconoLogo);        
           
                   iconoCerrar = new ImageIcon(tmpIconCerrar.getImage().getScaledInstance(lblCerrar.getWidth(), lblCerrar.getHeight(), Image.SCALE_DEFAULT));
                   lblCerrar.setIcon(iconoCerrar);
                 break;
    			}
    		}
    	}
    	public static void main(String args[]){
    		try{
    			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    			new Cat_Checador().setVisible(true);
    		}catch(Exception e){
    			System.err.println("Error :"+ e.getMessage());
    		}
    	}
}