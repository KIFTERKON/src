package catalogos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import objetos.Obj_Captura_Fuente_Sodas;

@SuppressWarnings("serial")
public class Cat_Captura_Fuente_Sodas extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JPasswordField txtClave = new JPasswordField();
	JTextField txtTicket = new JTextField();
	JTextField txtImporte = new JTextField();
	JPasswordField txtConfirmarCompra = new JPasswordField();
	
	JButton btnImprimir = new JButton("Imprimir autorizacion");
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");
	
	JLabel lblNombre_Empleado = new JLabel();
	JLabel lblEstablecimiento_Empleado = new JLabel();
	JLabel lblPuesto_Empleado = new JLabel();
	JLabel lblFoto = new 	JLabel();
	
	JLabel lblCajero = new JLabel("Cajera(O):");
	JLabel lblUsuario = new JLabel();
	
	JLabel lblClave = new JLabel("Clave:");
	JLabel lblTicket = new JLabel("Ticket:");
	JLabel lblImporte = new JLabel("Importe:");
	JLabel lblConfirmarCompra = new JLabel("Confirmar:");
	
	JLabel lblNombre = new JLabel("Nombre:");
	JLabel lblEstablecimiento = new JLabel("Establecimiento:");
	JLabel lblPuesto = new JLabel("Puesto:");
	
	JLabel lblSaldo = new JLabel();
	JLabel Imgsigno = new JLabel("$");
	
	JLabel lblEnmarcadoSaldo = new JLabel();
	
    public static DefaultTableModel tabla_model = new DefaultTableModel(null,
            new String[]{"Tiket", "Importe", "Cajera(o)", "PC","Fecha"}){
                    
            @SuppressWarnings("rawtypes")
            Class[] types = new Class[]{
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
                    }
                     return false;
             }
    };
	
    static JTable tabla = new JTable(tabla_model);
    JScrollPane panelScroll = new JScrollPane(tabla);
	
	Date date = new Date();
	DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
	String fecha = df4.format(date);
	
	String claveGafete="";
	
	Border blackline;
	
	public void getContenedor()
	{
		init_tabla();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
		this.setTitle("Captura de fuente de sodas");
		this.panel.setBorder(BorderFactory.createTitledBorder("Captura de fuente de sodas"));
		
//		new java.awt.Color(105,105,105)
		blackline = BorderFactory.createLineBorder(Color.blue);
		this.lblEnmarcadoSaldo.setBorder(BorderFactory.createTitledBorder(blackline,"Credito Disponible"));
		
		lblFoto.setBorder(LineBorder.createGrayLineBorder());
		Imgsigno.setFont(new Font("arial", Font.BOLD, 80));
		lblSaldo.setFont(new Font("arial", Font.BOLD, 80));
		
		lblClave.setFont(new Font("arial", Font.BOLD, 12));
		lblTicket.setFont(new Font("arial", Font.BOLD, 12));
		lblImporte.setFont(new Font("arial", Font.BOLD, 12));
		lblConfirmarCompra.setFont(new Font("arial", Font.BOLD, 12));
		
		lblCajero.setFont(new Font("arial", Font.BOLD, 16));
		lblUsuario.setFont(new Font("arial", Font.BOLD, 16));
		lblNombre.setFont(new Font("arial", Font.BOLD, 16));
		lblNombre_Empleado.setFont(new Font("arial", Font.BOLD, 16));
		lblEstablecimiento.setFont(new Font("arial", Font.BOLD, 16));
		lblEstablecimiento_Empleado.setFont(new Font("arial", Font.BOLD, 16));
		lblPuesto.setFont(new Font("arial", Font.BOLD, 16));
		lblPuesto_Empleado.setFont(new Font("arial", Font.BOLD, 16));	
		
		lblClave.setForeground(new java.awt.Color(105,105,105));
		lblTicket.setForeground(new java.awt.Color(105,105,105));
		lblImporte.setForeground(new java.awt.Color(105,105,105));
		lblConfirmarCompra.setForeground(new java.awt.Color(105,105,105));
		
		lblCajero.setForeground(new java.awt.Color(105,105,105));
		lblUsuario.setForeground(new java.awt.Color(105,105,105));
		lblNombre.setForeground(new java.awt.Color(105,105,105));
		lblEstablecimiento.setForeground(new java.awt.Color(105,105,105));
		lblPuesto.setForeground(new java.awt.Color(105,105,105));
		Imgsigno.setForeground(new java.awt.Color(105,105,105));
		lblSaldo.setForeground(new java.awt.Color(105,105,105));
		
		panel.add(lblCajero).setBounds(20,20,80,20);
		panel.add(lblUsuario).setBounds(105,20,350,20);
		
		panel.add(lblClave).setBounds(20,60,50,20);
		panel.add(txtClave).setBounds(90,60,130,20);
		
		panel.add(lblTicket).setBounds(20,90,50,20);
		panel.add(txtTicket).setBounds(90,90,130,20);
		
		panel.add(lblImporte).setBounds(20,120,50,20);
		panel.add(txtImporte).setBounds(90,120,130,20);
		
		panel.add(lblConfirmarCompra).setBounds(20,150,70,20);
		panel.add(txtConfirmarCompra).setBounds(90,150,130,20);
		
		panel.add(lblFoto).setBounds(235,60,170,170);
		
		panel.add(lblNombre).setBounds(420,60,70,20);
		panel.add(lblNombre_Empleado).setBounds(490,60,320,20);
		
		panel.add(lblEstablecimiento).setBounds(420,85,130,20);
		panel.add(lblEstablecimiento_Empleado).setBounds(550,85,280,20);
		
		panel.add(lblPuesto).setBounds(420,110,70,20);
		panel.add(lblPuesto_Empleado).setBounds(490,110,320,20);
		
		panel.add(lblEnmarcadoSaldo).setBounds(440,135,380,100);
		panel.add(Imgsigno).setBounds(450,150,80,80);
		panel.add(lblSaldo).setBounds(500,150,360,80);
		
		panel.add(btnImprimir).setBounds(30,180,180,20);
		panel.add(btnGuardar).setBounds(30,205,80,20);
		panel.add(btnCancelar).setBounds(130,205,80,20);
		
		panel.add(panelScroll).setBounds(20,240,800,300);
		
		ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.jpg");
	    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));	
		
		txtTicket.setEnabled(false);
		txtImporte.setEnabled(false);
		txtConfirmarCompra.setEnabled(false);
		
		btnGuardar.setEnabled(false);
		
		txtImporte.addKeyListener(numerico_action_punto);
		
		txtClave.addActionListener(opClave);
		txtTicket.addActionListener(opTiket);
		txtImporte.addActionListener(importe);
		txtConfirmarCompra.addActionListener(guardar);
		
		btnGuardar.addActionListener(guardar);
		btnImprimir.addActionListener(opImprmiAutorizacion);
		btnCancelar.addActionListener(cancelar);
		
		
		this.setSize(850,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cont.add(panel);
		CargarCajero();
	}
	
	public Cat_Captura_Fuente_Sodas(){
		getContenedor();
	}
	
	ActionListener opClave = new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtClave.getText().length()!=0){
				
				Obj_Captura_Fuente_Sodas capturaFS = new Obj_Captura_Fuente_Sodas().buscar(txtClave.getText());
				
					if(txtClave.getText().toUpperCase().equals(capturaFS.getClave())){
						
						ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
					    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));	
						
						lblNombre_Empleado.setText(capturaFS.getEmpleado());
						lblEstablecimiento_Empleado.setText(capturaFS.getEstablecimiento());
						lblPuesto_Empleado.setText(capturaFS.getPuesto());
						lblSaldo.setText(capturaFS.getTotal()+"");
						
						try {
							String[][] tabla = new Obj_Captura_Fuente_Sodas().tabla(txtClave.getText());
										
							while(tabla_model.getRowCount()>0){
								tabla_model.removeRow(0);
							}
								
							for(int i=0; i<tabla.length; i++){
								 		Object[] dom = new Object[5];
								 		
								 		dom[0] = tabla[i][0]+"   ";
								 		dom[1] = tabla[i][1]+"   ";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
								 		dom[2] = "   "+tabla[i][2];
								 		dom[3] = tabla[i][3];
								 		dom[4] = tabla[i][4]+"   ";
								 		
								 		tabla_model.addRow(dom);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						if(capturaFS.getTotal() <= 0){
							
							txtClave.setText("");
							txtClave.requestFocus();
							JOptionPane.showMessageDialog(null,"No cuenta con saldo suficiente","Aviso", JOptionPane.WARNING_MESSAGE);
							return;
						}else{
						
							txtClave.setEnabled(false);
							txtTicket.setEnabled(true);
							txtTicket.requestFocus();
					}
							
					}else{
						
						txtClave.setText("");
						txtClave.requestFocus();
						JOptionPane.showMessageDialog(null,"No se encontro al empleado o no cuenta \ncon fuente de sodas favor de comunicarse \na Desarrollo Humano","Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
			}else{
				txtClave.setText("");
				txtClave.requestFocus();
				JOptionPane.showMessageDialog(null,"Pase su gafete para confirmar empleado","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opTiket = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(txtTicket.getText().length() != 0 ){
				txtTicket.setEnabled(false);
				txtImporte.setEnabled(true);
				txtImporte.requestFocus();
				btnGuardar.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(null,"ingrese codigo de tiket para registrar compra","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opImprmiAutorizacion= new ActionListener(){
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
		
			
			
			if(txtClave.getText().equals("")){
				txtClave.requestFocus();
				JOptionPane.showMessageDialog(null,"Pase gafete para obtener ultimo ticket\ndel empleado","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
				
			}else{
				Obj_Captura_Fuente_Sodas capturaFS = new Obj_Captura_Fuente_Sodas().buscar(txtClave.getText());
				if(txtClave.getText().toUpperCase().trim().equals(capturaFS.getClave())){

//					new Reporte_Ticket_Fuente_Sodas(txtClave.getText().toUpperCase());
					new Imprime_Ticket_Captura_Fuente_Sodas(txtClave.getText().toUpperCase()).setVisible(true);
					 
				}else{
						txtClave.setText("");
						txtClave.requestFocus();
						JOptionPane.showMessageDialog(null,"El empleado no cuenta con fuente de sodas","Aviso", JOptionPane.WARNING_MESSAGE);
						return;
				}
			}
			
		}
	};
	
	ActionListener importe = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtImporte.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese una cantidad para continuar operacion!!!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				
				if(Float.parseFloat(txtImporte.getText())>Float.parseFloat(lblSaldo.getText())){
					JOptionPane.showMessageDialog(null, "No cuenta con el saldo suficiente !!!","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					txtConfirmarCompra.setEnabled(true);
					txtConfirmarCompra.requestFocus();
				}
			}
		}
	};
	
	
	ActionListener guardar = new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0) 
		{
			
			if(txtConfirmarCompra.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Pase su gafete para confirmar la compra!!!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				
				if(txtConfirmarCompra.getText().toUpperCase().equals(txtClave.getText().toUpperCase())){
					
					Obj_Captura_Fuente_Sodas sodas = new Obj_Captura_Fuente_Sodas();
				
						sodas.setClave(txtClave.getText().toUpperCase().trim());
						sodas.setEstablecimiento(lblEstablecimiento_Empleado.getText());
						sodas.setPuesto(lblPuesto_Empleado.getText());
						sodas.setTicket(txtTicket.getText());
						sodas.setImporte(Float.parseFloat(txtImporte.getText()));
						sodas.setUsuario(lblUsuario.getText());
							
						if(sodas.Guardar()){
							
							 while(tabla_model.getRowCount()>0){
							        tabla_model.removeRow(0);
							    }
							
//							 new Reporte_Ticket_Fuente_Sodas(txtClave.getText().toUpperCase().trim());
							 new Imprime_Ticket_Captura_Fuente_Sodas(txtClave.getText().toUpperCase()).setVisible(true);
							 
							ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.jpg");
							lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));	
							
								txtClave.setEnabled(true);
								txtTicket.setEnabled(false);
								txtImporte.setEnabled(false);
								txtConfirmarCompra.setEnabled(false);
								
							 	txtClave.setText("");
								txtTicket.setText("");
								txtImporte.setText("");
								txtConfirmarCompra.setText("");
								
								lblFoto.setText("");
								lblNombre_Empleado.setText("");
								lblEstablecimiento_Empleado.setText("");
								lblPuesto_Empleado.setText("");
								lblSaldo.setText("");
								
								JOptionPane.showMessageDialog(null, "Guardado exitosamente !!!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
					}else{
						JOptionPane.showMessageDialog(null, "La clave no coinside!!!","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}else{
					txtConfirmarCompra.setText("");
					JOptionPane.showMessageDialog(null, "No se ha podido realisar su pedido de forma correcta,\npase su gafete de nuevo o comuniquese a\nDesarrollo Humano","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		}
	};
	
	ActionListener cancelar = new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			txtClave.setText("");
			txtTicket.setText("");
			txtImporte.setText("");
			
			lblFoto.setText("");
			lblNombre_Empleado.setText("");
			lblEstablecimiento_Empleado.setText("");
			lblPuesto_Empleado.setText("");
			lblSaldo.setText("");
			
			txtClave.setEnabled(true);
			txtTicket.setEnabled(false);
			txtImporte.setEnabled(false);
			txtClave.requestFocus();
			
			ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.jpg");
		    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));	
			
			    while(tabla.getRowCount()>0){
	                tabla_model.removeRow(0);
			    }
		}
	};
	
	public void agregar()
	{
		if(txtImporte.getText().length()!=0)
		{
			double variable;
			
			variable=Double.parseDouble(txtImporte.getText());
			lblSaldo.setText(variable+"");
			
			String[] row = new String[7];
			row[0]=" "+lblNombre_Empleado.getText().toUpperCase();
			row[1]=""+lblEstablecimiento_Empleado.getText().toUpperCase();
			row[2]=""+fecha;
			row[3]=lblUsuario.getText().toUpperCase();
			row[4]=txtTicket.getText().toUpperCase();
			row[5]=txtImporte.getText().toUpperCase();
			tabla_model.addRow(row);
		}else{
			JOptionPane.showMessageDialog(null,"El campo de texto importe está vacío","Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	
	public void CargarCajero()
	{
		  File archivo = null;
 	      FileReader fr = null;
 	      BufferedReader br = null;
		 try {
 	         archivo = new File ("Config/users");
 	         fr = new FileReader (archivo);
 	         br = new BufferedReader(fr);
 	         String linea;
 	         while((linea=br.readLine())!=null)
 	        	lblUsuario.setText(linea);
 	      }
 	      catch(Exception e){
 	         e.printStackTrace();
 	      }finally{
 	         try{                   
 	            if( null != fr ){  
 	               fr.close();    
 	            }                 
 	         }catch (Exception e2){
 	            e2.printStackTrace();
 	         }
 	      }
	}
	
    @SuppressWarnings({ "static-access" })
	public void init_tabla(){
    	this.tabla.getTableHeader().setReorderingAllowed(false) ;
    	
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(100);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(100);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(100);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(100);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(320);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(320);
    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(140);
    	this.tabla.getColumnModel().getColumn(3).setMinWidth(140);
    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(140);
    	this.tabla.getColumnModel().getColumn(4).setMinWidth(140);		
    	
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
				
				if(table.getSelectedRow() == row){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(186,143,73));
				}
				
				switch(column){
					case 0 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 3 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					case 4 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
				}
			return lbl; 
			} 
		}; 

		for(int x = 0; x<tabla.getColumnCount(); x++){
			this.tabla.getColumnModel().getColumn(x).setCellRenderer(render); 
		}
    }
 	
 	KeyListener numerico_action = new KeyListener() {
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();

			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }
		}
		public void keyReleased(KeyEvent e) {	
		}
		public void keyPressed(KeyEvent e) {}
	};
	
	KeyListener numerico_action_punto = new KeyListener() {
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();

			 if(((caracter < '0') ||	
				    	(caracter > '9')) && 
				    	(caracter != '.' )){
				    	e.consume();
		    }
			 if (caracter==KeyEvent.VK_PERIOD){
 		    	
			    	String texto = txtImporte.getText().toString();
					if (texto.indexOf(".")>-1) e.consume();
					
				}
		}
		public void keyReleased(KeyEvent e) {	
		}
		public void keyPressed(KeyEvent e) {}
	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Captura_Fuente_Sodas().setVisible(true);
		}catch(Exception e){}
	}

	
	public class Imprime_Ticket_Captura_Fuente_Sodas extends JFrame
	{
		Container container = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
	//Declarar Imagen para Txa	
		ImageIcon img = new ImageIcon("imagen/fuenteSodasTicket.png");
		
		
		JScrollPane jScrollPane1 = new JScrollPane();
		JButton jButImprime = new JButton("Imprimir");
		
		private JTextArea jTextArea1=new JTextArea(){
			
			Image image = img.getImage();
			
			Image grayImage = GrayFilter.createDisabledImage(image);{
				setOpaque(false);
			}
			
			public void paint(Graphics g){
					g.drawImage(grayImage,0,0,this);
					super.paint(g);
			}
		};
//variables para ticket -----------------------------------------------------------------------------------
		String usuario= "Cajera(o): ";
		String fecha ="Fecha: ";
		String lblEmpleado="Empleado: ";
		
		String establecimiento="Establecimiento: ";
		String puesto="Puesto: ";
		String ticket="Ticket: ";
		String importe="Importe: $";
		String linea = "_____________________________________________________";
		String firma=" Firma: (  ";
//-----------------------------------------------------------------------------------------------------------

		@SuppressWarnings("deprecation")
		public Imprime_Ticket_Captura_Fuente_Sodas(String pass)
		{
			this.setTitle("Imprimir Ticket");
			Font font = new Font("ARIAL",Font.PLAIN,8);
			jTextArea1.setFont(font);
			
			Obj_Captura_Fuente_Sodas ultimiTicket = new Obj_Captura_Fuente_Sodas().buscar_ultimo_ticket(txtClave.getText().toUpperCase());
			
			usuario=		usuario+ultimiTicket.getUsuario();
			fecha= 			fecha+ultimiTicket.getFecha();
			lblEmpleado=	lblEmpleado+ultimiTicket.getEmpleado();
			establecimiento=establecimiento+ultimiTicket.getEstablecimiento();
			puesto=			puesto+ultimiTicket.getPuesto();
			ticket=			ticket+ultimiTicket.getTicket();
			importe=		importe+ultimiTicket.getImporte();
			firma=			firma+ultimiTicket.getEmpleado()+"  )";
			
			jScrollPane1.setViewportView(jTextArea1);
			
			jTextArea1.setText(
	        		new String ("\n\n\n\n\n\n\n"+usuario
	        				+"\n\n                                       "
	        				+"                   "+fecha+"\n\n"
    						+lblEmpleado+"\n\n"+establecimiento+"\n\n"
    						+puesto+"\n\n"+ticket+"\n\n"+importe+"\n\n\n\n"
    						+linea+"\n"+firma+"\n\n\n\n.")
	        		);
			
			panel.add(jButImprime).setBounds(134,10,100,20);
			panel.add(jTextArea1).setBounds(14,50,210,310);
			
			jButImprime.requestFocus();
			jButImprime.addActionListener(opImprimir);
			
			container.add(panel);

			this.setLocation(new Point(280, 170));
			this.setSize(new Dimension(260, 400));
			
				jTextArea1.requestFocus();
		        jTextArea1.setEditable(false);
		}
		
		ActionListener opImprimir = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(imprimir()){
					imprimir();
				}else{
					JOptionPane.showMessageDialog(null,"Fallo al intentar guardar","Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				dispose();
			}
		};
		
		public boolean imprimir(){
			boolean valor = false;
			
			Properties defaultProps = new Properties();
	 		
			PrintJob print=Toolkit.getDefaultToolkit().getPrintJob(this,"",defaultProps);
			Graphics g=print.getGraphics();

			if(g!=null){
				jTextArea1.printAll(g);
				print.end();
				g.dispose();
				valor= true;
				
			}
			return valor;
		}
	}
}