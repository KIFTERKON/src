package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import objetos.Obj_Captura_Fuente_Sodas;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;
import objetos.Obj_Sueldo;

import ObjetoChecador.Obj_Traer_Checador;
import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Captura_Fuente_Sodas extends JFrame
{
	
	/*agregar al filtro ke traiga el filtro de los usuarios */
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JPasswordField txtClave = new JPasswordField();
	JTextField txtTicket = new JTextField();
	JTextField txtImporte = new JTextField();
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnFiltro = new JButton("Filtro");
	JButton btnImprimir = new JButton("Imprimir autorizacion");
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");
	
	
	JLabel lblNombre_Empleado = new JLabel();
	JLabel lblEstablecimiento_Empleado = new JLabel();
	JLabel lblPuesto_Empleado = new JLabel();
	JLabel lblFoto = new 	JLabel();
	JLabel lblVariableGlobal = new JLabel();
	
	JLabel lblCajera = new JLabel();
	JLabel lblSaldo = new JLabel();
	JLabel Imgsigno = new JLabel("$");
	
//	new Obj_Traer_Checador().get_tabla_model()
    public static DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Traer_Checador().get_tabla_model(),
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
	public void getContenedor()
	{
		init_tabla();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
		this.setTitle("Captura de fuente de sodas");
		this.panel.setBorder(BorderFactory.createTitledBorder("Captura de fuente de sodas"));
		
		lblFoto.setBorder(LineBorder.createGrayLineBorder());
		Imgsigno.setFont(new Font("arial", Font.BOLD, 80));
		lblSaldo.setFont(new Font("arial", Font.BOLD, 80));
		
		
		panel.add(new JLabel("Cajera(O):")).setBounds(20,20,70,20);
		panel.add(lblCajera).setBounds(90,20,300,20);
		
		panel.add(new JLabel("Folio Empleado:")).setBounds(20,50,90,20);
		panel.add(txtFolio).setBounds(100,50,60,20);
		panel.add(btnBuscar).setBounds(160,50,30,20);
		panel.add(btnFiltro).setBounds(190,50,60,20);
		
		panel.add(new JLabel("Clave:")).setBounds(20,80,50,20);
		panel.add(txtClave).setBounds(100,80,150,20);
		
		panel.add(new JLabel("Ticket:")).setBounds(20,110,50,20);
		panel.add(txtTicket).setBounds(100,110,150,20);
		
		panel.add(new JLabel("Importe")).setBounds(20,140,50,20);
		panel.add(txtImporte).setBounds(100,140,150,20);
		
		panel.add(lblFoto).setBounds(300,50,150,150);
		
		panel.add(new JLabel("Nombre:")).setBounds(500,50,50,20);
		panel.add(lblNombre_Empleado).setBounds(550,50,250,20);
		
		panel.add(new JLabel("Establecimiento:")).setBounds(500,80,80,20);
		panel.add(lblEstablecimiento_Empleado).setBounds(590,80,300,20);
		
		panel.add(new JLabel("Puesto:")).setBounds(500,110,50,20);
		panel.add(lblPuesto_Empleado).setBounds(550,110,300,20);
		
		panel.add(Imgsigno).setBounds(490,150,80,80);
		panel.add(lblSaldo).setBounds(540,150,220,80);
		
		panel.add(btnImprimir).setBounds(90,200,170,20);
		panel.add(panelScroll).setBounds(20,240,750,350);
		
		panel.add(btnGuardar).setBounds(50,630,80,20);
		panel.add(btnCancelar).setBounds(150,630,80,20);
		
		txtClave.setEditable(false);
		txtTicket.setEditable(false);
		txtImporte.setEditable(false);
		txtFolio.addKeyListener(numerico_action);
		btnFiltro.addActionListener(filtro);
		txtImporte.addKeyListener(numerico_action_punto);
		
		
		btnBuscar.addActionListener(accion);
		txtFolio.addActionListener(accion);
		
		txtTicket.addActionListener(ticket);
		txtImporte.addActionListener(importe);
		
		txtClave.addActionListener(opClave);
		
		btnCancelar.addActionListener(cancelar);
		btnGuardar.addActionListener(guardar);
		txtClave.addKeyListener(numerico_action);
		
		this.setSize(800,700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cont.add(panel);
		CargarCajero();
	}
	
	public Cat_Captura_Fuente_Sodas(String algo)
	{
		getContenedor();
		
		
		Obj_Captura_Fuente_Sodas soda = new Obj_Captura_Fuente_Sodas().buscar_folio(Integer.parseInt(algo));
		if(soda.getNombre_cajera()!="")
		{
			new Connexion();
			txtFolio.setText(soda.getNo_cliente()+"");
			txtClave.setText(soda.getNo_cliente()+"");
			txtTicket.setText(soda.getTicket()+"");
			txtImporte.setText(soda.getImporte()+"");
			
			lblNombre_Empleado.setText(soda.getNombre_cliente()+"");
			lblEstablecimiento_Empleado.setText(soda.getEstablecimiento_cliente()+"");
			lblPuesto_Empleado.setText(soda.getPuesto_cliente()+"");
			
			double variable;
			
			variable=Double.parseDouble(txtImporte.getText());
			lblSaldo.setText(variable+"");
			
			ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
		    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));	
			
			String[] row = new String[6];
			row[0]=" "+lblNombre_Empleado.getText().toUpperCase();
			row[1]=""+lblEstablecimiento_Empleado.getText().toUpperCase();
			row[2]=""+fecha;
			row[3]=lblCajera.getText().toUpperCase();
			row[4]=txtTicket.getText().toUpperCase();
			row[5]=txtImporte.getText().toUpperCase();
			tabla_model.addRow(row);
			
		}else{
			String file = System.getProperty("user.dir")+"/Iconos/Un.JPG";
			ImageIcon tmpIconAux = new ImageIcon(file);
			lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));	
		}
	}
	
	public Cat_Captura_Fuente_Sodas()
	{
		getContenedor();
	}
	
	ActionListener guardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			Obj_Captura_Fuente_Sodas sodas = new Obj_Captura_Fuente_Sodas();
			
			sodas.setNombre_cajera(lblCajera.getText());
			sodas.setNo_cliente(Integer.parseInt(txtFolio.getText()));
			sodas.setTicket(txtTicket.getText());
			sodas.setImporte(Double.parseDouble(txtImporte.getText()));
			sodas.setEstablecimiento_cliente(lblEstablecimiento_Empleado.getText());
			sodas.setPuesto_cliente(lblPuesto_Empleado.getText());
			sodas.setFoto(new File(System.getProperty("user.dir")+"/tmp/tmp_update/tmp.jpg"));
			sodas.Guardar();
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
			row[3]=lblCajera.getText().toUpperCase();
			row[4]=txtTicket.getText().toUpperCase();
			row[5]=txtImporte.getText().toUpperCase();
			tabla_model.addRow(row);
		}else{
			JOptionPane.showMessageDialog(null,"El campo de texto importe está vacío","Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	
	ActionListener importe = new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			agregar();
		}
	};
	
	ActionListener opClave = new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0)
		{
			if(txtClave.getText().length()!=0){
			if(txtClave.getText().equals(lblVariableGlobal.getText()))
			{
			txtClave.setEditable(false);
			txtTicket.setEditable(true);
			txtTicket.requestFocus();
			
			}else{
				txtClave.setText("");
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
			}
			}
		}
	};
	
	ActionListener accion = new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			if(txtFolio.getText().length()!=0){
			Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
				txtClave.requestFocus();
				txtFolio.setEditable(false);
				txtClave.setEditable(true);


				ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
			    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));	
				
				lblNombre_Empleado.setText(empleado.getNombre()+" "+empleado.getAp_paterno()+" "+empleado.getAp_materno());
				
				Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(empleado.getEstablecimiento());
				lblEstablecimiento_Empleado.setText(comboNombreEsta.getNombre());
				
				Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(empleado.getPuesto());
				lblPuesto_Empleado.setText(comboNombrePues.getPuesto());
				lblVariableGlobal.setText(empleado.getNo_checador()+"");
				
//				(derecho a fuente de sodas)-(cantidad retirada)  
//				falta aplicar descuelto de consumos anteriores 
				Obj_Sueldo sueldo = new Obj_Sueldo().buscar(empleado.getSueldo());
				lblSaldo.setText((sueldo.getSueldo()*.3)+"");
			}
		}
	};
	
	ActionListener ticket = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtTicket.getText().length()!=0)
			{
				txtTicket.setEditable(false);
				txtImporte.setEditable(true);
				txtImporte.requestFocus();
				
			}
		}
	};
	
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
 	        	 lblCajera.setText(linea);
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
	
	ActionListener cancelar = new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			dispose();
		}
	};
	
	class CELL_EDITOR extends DefaultCellEditor{
        private static final long serialVersionUID = 1L;
 
        public CELL_EDITOR(JCheckBox checkBox) {
            super(checkBox);
            checkBox.setHorizontalAlignment(JLabel.RIGHT);
        }
        }
        
    @SuppressWarnings({ "static-access" })
	public void init_tabla(){
    	this.tabla.getTableHeader().setReorderingAllowed(false) ;
    	
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(90);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(90);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(90);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(90);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(310);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(310);
    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(3).setMinWidth(120);
    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(4).setMinWidth(120);		
    	
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
					case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 3 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 4 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
				}
			return lbl; 
			} 
		}; 

		for(int x = 0; x<tabla.getColumnCount(); x++){
			this.tabla.getColumnModel().getColumn(x).setCellRenderer(render); 
		}
    }
 	
 	ActionListener filtro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
//			dispose();
//			new Cat_Filtro_Empleado().setVisible(true);
			
		}
	};
 
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
}

