package catalogos;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import objetos.Obj_Captura_Fuente_Sodas;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Captura_Fuente_Sodas extends JFrame
{
	
	/*agregar al filtro ke traiga el filtro de los usuarios */
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtNo_Checador_Cliente = new JTextField();
	JPasswordField txtClave_Cliente = new JPasswordField();
	JTextField txtTicket = new JTextField();
	JTextField txtImporte = new JTextField();
	
	JButton btnFiltro = new JButton("Filtro");
	JButton btnImprimir = new JButton("Imprimir autorizacion");
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");
	
	
	JLabel lblNombre_Empleado = new JLabel();
	JLabel lblEstablecimiento_empleado = new JLabel();
	JLabel lblpuesto_empleado = new JLabel();
	JLabel lblFoto = new 	JLabel();
	JLabel lblVariableGlobal = new JLabel();
	
	JLabel lblCajera = new JLabel();
	JLabel lblSaldo = new JLabel();
	JLabel Imgsigno = new JLabel(new ImageIcon("Imagen/Dollar.png"));
	
	DefaultTableModel model = new DefaultTableModel(0,7){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	
	Date date = new Date();
	DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
	String fecha = df4.format(date);
	public void getContenedor()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
		this.setTitle("Captura de fuente de sodas");
		this.panel.setBorder(BorderFactory.createTitledBorder("Captura de fuente de sodas"));
		panel.add(new JLabel("Cajera(O):")).setBounds(20,20,70,20);
		
		panel.add(lblCajera).setBounds(90,20,300,20);
		
		panel.add(new JLabel("Numero:")).setBounds(20,50,70,20);
		panel.add(txtNo_Checador_Cliente).setBounds(90,50,150,20);
		panel.add(btnFiltro).setBounds(260,50,70,20);
		panel.add(lblFoto).setBounds(450,50,150,150);
		panel.add(new JLabel("Nombre:")).setBounds(650,50,50,20);
		panel.add(lblNombre_Empleado).setBounds(700,50,250,20);
		
		lblFoto.setBorder(LineBorder.createGrayLineBorder());
		
		panel.add(new JLabel("Establecimiento:")).setBounds(650,80,80,20);
		panel.add(lblEstablecimiento_empleado).setBounds(740,80,300,20);
		
		panel.add(new JLabel("Clave:")).setBounds(20,80,50,20);
		panel.add(txtClave_Cliente).setBounds(90,80,150,20);
		panel.add(new JLabel("Puesto:")).setBounds(650,110,50,20);
		panel.add(lblpuesto_empleado).setBounds(700,110,300,20);
		
		panel.add(new JLabel("Ticket:")).setBounds(20,110,50,20);
		panel.add(txtTicket).setBounds(90,110,150,20);

		panel.add(new JLabel("Importe")).setBounds(20,140,50,20);
		panel.add(txtImporte).setBounds(90,140,150,20);

		panel.add(lblSaldo).setBounds(700,180,150,50);
		panel.add(Imgsigno).setBounds(680,195,20,20);
		
		panel.add(btnImprimir).setBounds(90,200,170,20);
		
		panel.add(btnGuardar).setBounds(50,630,80,20);
		panel.add(btnCancelar).setBounds(150,630,80,20);
		
		lblSaldo.setFont(new Font("Algerian", Font.BOLD, 30));
		panel.add(getPanelTabla()).setBounds(20,240,1150,350);
		
		txtClave_Cliente.setEditable(false);
		txtTicket.setEditable(false);
		txtImporte.setEditable(false);
		txtNo_Checador_Cliente.requestFocus();
		txtNo_Checador_Cliente.addKeyListener(numerico_action);
		btnFiltro.addActionListener(filtro);
		txtImporte.addKeyListener(numerico_action_punto);
		
		txtNo_Checador_Cliente.addActionListener(accion);
		txtClave_Cliente.addKeyListener(numerico_action);
		txtTicket.addActionListener(ticket);
		txtImporte.addActionListener(importe);
		
		txtClave_Cliente.addActionListener(pass);
		
		btnCancelar.addActionListener(cancelar);
		btnGuardar.addActionListener(guardar);
		
		this.setSize(1200,700);
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
			txtNo_Checador_Cliente.setText(soda.getNo_cliente()+"");
			txtClave_Cliente.setText(soda.getNo_cliente()+"");
			txtTicket.setText(soda.getTicket()+"");
			txtImporte.setText(soda.getImporte()+"");
			
			lblNombre_Empleado.setText(soda.getNombre_cliente()+"");
			lblEstablecimiento_empleado.setText(soda.getEstablecimiento_cliente()+"");
			lblpuesto_empleado.setText(soda.getPuesto_cliente()+"");
			
			double variable;
			
			variable=Double.parseDouble(txtImporte.getText());
			lblSaldo.setText(variable+"");
			
			ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
		    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));	
			
			String[] row = new String[6];
			row[0]=" "+lblNombre_Empleado.getText().toUpperCase();
			row[1]=""+lblEstablecimiento_empleado.getText().toUpperCase();
			row[2]=""+fecha;
			row[3]=lblCajera.getText().toUpperCase();
			row[4]=txtTicket.getText().toUpperCase();
			row[5]=txtImporte.getText().toUpperCase();
			model.addRow(row);
			
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
			sodas.setNo_cliente(Integer.parseInt(txtNo_Checador_Cliente.getText()));
			sodas.setTicket(txtTicket.getText());
			sodas.setImporte(Double.parseDouble(txtImporte.getText()));
			sodas.setNombre_cliente(lblNombre_Empleado.getText());
			sodas.setEstablecimiento_cliente(lblEstablecimiento_empleado.getText());
			sodas.setPuesto_cliente(lblpuesto_empleado.getText());
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
			row[1]=""+lblEstablecimiento_empleado.getText().toUpperCase();
			row[2]=""+fecha;
			row[3]=lblCajera.getText().toUpperCase();
			row[4]=txtTicket.getText().toUpperCase();
			row[5]=txtImporte.getText().toUpperCase();
			model.addRow(row);
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
	
	ActionListener pass = new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0)
		{
			if(txtClave_Cliente.getText().length()!=0){
			if(txtClave_Cliente.getText().equals(lblVariableGlobal.getText()))
			{
			txtClave_Cliente.setEditable(false);
			txtTicket.setEditable(true);
			txtTicket.requestFocus();
			
			}else{
				txtClave_Cliente.setText("");
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
			}
			}
		}
	};
	
	ActionListener accion = new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			if(txtNo_Checador_Cliente.getText().length()!=0){
			Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtNo_Checador_Cliente.getText()));
				txtClave_Cliente.requestFocus();
				txtNo_Checador_Cliente.setEditable(false);
				txtClave_Cliente.setEditable(true);


				ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
			    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));	
				
				lblNombre_Empleado.setText(empleado.getNombre()+" "+empleado.getAp_paterno()+" "+empleado.getAp_materno());
				
				Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(empleado.getEstablecimiento());
				lblEstablecimiento_empleado.setText(comboNombreEsta.getNombre());
				
				Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(empleado.getPuesto());
				lblpuesto_empleado.setText(comboNombrePues.getPuesto());
				lblVariableGlobal.setText(empleado.getNo_checador()+"");
				
				
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
        
 	private JScrollPane getPanelTabla()	{		
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(6).setCellRenderer(tcr,(new JCheckBox()));
		tabla.getColumnModel().getColumn(6).setCellEditor(new CELL_EDITOR(new JCheckBox()));
		// Creamos las columnas.
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio Empleado");
		tabla.getColumnModel().getColumn(0).setMaxWidth(200);
		tabla.getColumnModel().getColumn(0).setMinWidth(200);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Folio_Establecimiento");
		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
		tabla.getColumnModel().getColumn(1).setMinWidth(160);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Fecha");
		tabla.getColumnModel().getColumn(2).setMaxWidth(200);
		tabla.getColumnModel().getColumn(2).setMinWidth(200);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Folio_Cajera");
		tabla.getColumnModel().getColumn(3).setMaxWidth(200);
		tabla.getColumnModel().getColumn(3).setMinWidth(200);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Ticket");
		tabla.getColumnModel().getColumn(4).setMaxWidth(150);
		tabla.getColumnModel().getColumn(4).setMinWidth(150);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Importe");
		tabla.getColumnModel().getColumn(5).setMaxWidth(150);
		tabla.getColumnModel().getColumn(5).setMinWidth(150);
		tabla.getColumnModel().getColumn(6).setHeaderValue("status");
		tabla.getColumnModel().getColumn(6).setMaxWidth(90);
		tabla.getColumnModel().getColumn(6).setMinWidth(90);
		
		JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
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

