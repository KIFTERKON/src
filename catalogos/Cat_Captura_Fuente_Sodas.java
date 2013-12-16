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
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

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
	
	JTextField txtFolioEmpleado = new JTextField();
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
	
	JLabel lblUsuario = new JLabel();
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
	
	String claveGafete="";
	
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
		panel.add(lblUsuario).setBounds(90,20,300,20);
		
		panel.add(new JLabel("Folio Empleado:")).setBounds(20,50,90,20);
		panel.add(txtFolioEmpleado).setBounds(100,50,60,20);
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
		
		btnGuardar.setEnabled(false);
		
		txtFolioEmpleado.addKeyListener(numerico_action);
		btnFiltro.addActionListener(filtro);
		txtImporte.addKeyListener(numerico_action_punto);
		
		
		btnBuscar.addActionListener(opBuscar);
		txtFolioEmpleado.addActionListener(opBuscar);
		
		txtClave.addActionListener(opClave);
		
		txtTicket.addActionListener(opTiket);
		
		txtImporte.addActionListener(opImprmiAutorizacion);
		btnImprimir.addActionListener(opImprmiAutorizacion);
		
		btnCancelar.addActionListener(cancelar);
		btnGuardar.addActionListener(guardar);
		
		this.setSize(800,700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cont.add(panel);
		CargarCajero();
	}
	
	public Cat_Captura_Fuente_Sodas(){
		getContenedor();
	}
	
//	public Cat_Captura_Fuente_Sodas(String algo){
//		getContenedor();
//		
//		Obj_Captura_Fuente_Sodas soda = new Obj_Captura_Fuente_Sodas().buscar_folio(Integer.parseInt(algo));
//		if(soda.getNombre_cajera()!="")
//		{
////			new Connexion();
//			txtFolioEmpleado.setText(soda.getNo_cliente()+"");
//			txtClave.setText(soda.getNo_cliente()+"");
//			txtTicket.setText(soda.getTicket()+"");
//			txtImporte.setText(soda.getImporte()+"");
//			
//			lblNombre_Empleado.setText(soda.getNombre_cliente()+"");
//			lblEstablecimiento_Empleado.setText(soda.getEstablecimiento_cliente()+"");
//			lblPuesto_Empleado.setText(soda.getPuesto_cliente()+"");
//			
//			double variable;
//			
//			variable=Double.parseDouble(txtImporte.getText());
//			lblSaldo.setText(variable+"");
//			
//			ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
//		    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));	
//			
////			String[] row = new String[6];
////			row[0]=" "+lblNombre_Empleado.getText().toUpperCase();
////			row[1]=""+lblEstablecimiento_Empleado.getText().toUpperCase();
////			row[2]=""+fecha;
////			row[3]=lblUsuario.getText().toUpperCase();
////			row[4]=txtTicket.getText().toUpperCase();
////			row[5]=txtImporte.getText().toUpperCase();
////			tabla_model.addRow(row);
//			
//		}else{
//			String file = System.getProperty("user.dir")+"/Iconos/Un.JPG";
//			ImageIcon tmpIconAux = new ImageIcon(file);
//			lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));	
//		}
//	}
	
	ActionListener opBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			if(txtFolioEmpleado.getText().length()!=0){
			Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolioEmpleado.getText()));
				txtClave.requestFocus();
				txtFolioEmpleado.setEditable(false);
				txtClave.setEditable(true);

				claveGafete=empleado.getNo_checador();

				ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
			    lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));	
				
				lblNombre_Empleado.setText(empleado.getNombre()+" "+empleado.getAp_paterno()+" "+empleado.getAp_materno());
				
				Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(empleado.getEstablecimiento());
				lblEstablecimiento_Empleado.setText(comboNombreEsta.getNombre());
				
				Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(empleado.getPuesto());
				lblPuesto_Empleado.setText(comboNombrePues.getPuesto());
//				lblVariableGlobal.setText(empleado.getNo_checador()+"");
				
//				(derecho a fuente de sodas)-(cantidad retirada)  
//				falta aplicar descuelto de consumos anteriores 
				Obj_Sueldo sueldo = new Obj_Sueldo().buscar(empleado.getSueldo());
				lblSaldo.setText((sueldo.getSueldo()*.3)+"");
			}
		}
	};
	
	ActionListener opClave = new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtClave.getText().length()!=0){
				
					if(txtClave.getText().toUpperCase().equals(claveGafete)){
						txtClave.setEditable(false);
						txtTicket.setEditable(true);
						txtTicket.requestFocus();
					}else{
						
						txtFolioEmpleado.setEditable(true);
						txtFolioEmpleado.setText("");
						txtClave.setEditable(false);
						txtClave.setText("");
						txtFolioEmpleado.requestFocus();
						JOptionPane.showMessageDialog(null,"La clave no corresponde al empleado","Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
			}else{
				txtFolioEmpleado.setEditable(true);
				txtFolioEmpleado.setText("");
				txtClave.setEditable(false);
				txtClave.setText("");
				txtFolioEmpleado.requestFocus();
				JOptionPane.showMessageDialog(null,"Pase su gafete para confirmar empleado","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opTiket = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(txtTicket.getText().length() != 0 ){
				txtTicket.setEditable(false);
				txtImporte.setEditable(true);
				txtImporte.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null,"ingrese codigo de tiket para registrar compra","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opImprmiAutorizacion= new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(txtImporte.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Ingresar cantidad del importe","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
//				mandar a imprimir nota(tiket etc...  y desblokear btnGuardar)
				btnGuardar.setEnabled(true);
				JOptionPane.showMessageDialog(null,"imprimir autorizacion","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener guardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			Obj_Captura_Fuente_Sodas sodas = new Obj_Captura_Fuente_Sodas();
			
//			sodas.setNombre_cajera(lblUsuario.getText());
//			sodas.setNo_cliente(Integer.parseInt(txtFolioEmpleado.getText()));
//			sodas.setTicket(txtTicket.getText());
//			sodas.setImporte(Double.parseDouble(txtImporte.getText()));
//			sodas.setEstablecimiento_cliente(lblEstablecimiento_Empleado.getText());
//			sodas.setPuesto_cliente(lblPuesto_Empleado.getText());
//			sodas.setFoto(new File(System.getProperty("user.dir")+"/tmp/tmp_update/tmp.jpg"));
			sodas.Guardar();
		}
	};
	
	ActionListener cancelar = new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			dispose();
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
			new Cat_Filtro_Captura_FS().setVisible(true);
			
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
	
	@SuppressWarnings({ "unchecked" })
	public class Cat_Filtro_Captura_FS extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		Connexion con = new Connexion();
		
		DefaultTableModel model = new DefaultTableModel(0,5){
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla = new JTable(model);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
		@SuppressWarnings("rawtypes")
		JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
		
		@SuppressWarnings({ "rawtypes" })
		public Cat_Filtro_Captura_FS()	{
			this.setModal(true);
			this.setTitle("Filtro Fuente de Sodas");
			panel.setBorder(BorderFactory.createTitledBorder("Filtro Fuente de Sodas"));

			trsfiltro = new TableRowSorter(model); 
			tabla.setRowSorter(trsfiltro);  
			
			panel.add(getPanelTabla()).setBounds(15,42,625,327);
			
			panel.add(txtFolio).setBounds(15,20,68,20);
			panel.add(txtNombre_Completo).setBounds(85,20,239,20);
			panel.add(cmbEstablecimientos).setBounds(325,20, 148, 20);
			
			agregar(tabla);
			
			cont.add(panel);
			
			txtFolio.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			cmbEstablecimientos.addActionListener(opFiltro);
			
			this.setSize(660,415);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
		
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		    			int fila = tabla.getSelectedRow();
		    			Object folio =  tabla.getValueAt(fila, 0);
		    			
		    			txtFolioEmpleado.setText(folio+"");
		    			btnBuscar.doClick();
		    			dispose();
		        	}
		        }
	        });
	    }
		
		KeyListener opFiltroFolio = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
			}
			public void keyTyped(KeyEvent arg0) {
				char caracter = arg0.getKeyChar();
				if(((caracter < '0') ||
					(caracter > '9')) &&
				    (caracter != KeyEvent.VK_BACK_SPACE)){
					arg0.consume(); 
				}	
			}
			public void keyPressed(KeyEvent arg0) {}
			
		};
		
		KeyListener opFiltroNombre = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}
			
		};
		
		ActionListener opFiltro = new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(cmbEstablecimientos.getSelectedIndex() != 0){
					trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
				}else{
					trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
				}
			}
		};
		
		private JScrollPane getPanelTabla()	{		
			new Connexion();

			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMaxWidth(70);
			tabla.getColumnModel().getColumn(0).setMinWidth(70);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
			tabla.getColumnModel().getColumn(1).setMaxWidth(240);
			tabla.getColumnModel().getColumn(1).setMinWidth(240);
			tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
			tabla.getColumnModel().getColumn(2).setMaxWidth(150);
			tabla.getColumnModel().getColumn(2).setMinWidth(150);
			tabla.getColumnModel().getColumn(3).setHeaderValue("Status");
			tabla.getColumnModel().getColumn(3).setMaxWidth(90);
			tabla.getColumnModel().getColumn(3).setMinWidth(90);
			tabla.getColumnModel().getColumn(4).setHeaderValue("F Sodas");
			tabla.getColumnModel().getColumn(4).setMaxWidth(70);
			tabla.getColumnModel().getColumn(4).setMinWidth(70);
			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			int a=2;
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
			
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					JLabel lbl = new JLabel(value == null? "": value.toString());
			
					if(row%2==0){
							lbl.setOpaque(true); 
							lbl.setBackground(new java.awt.Color(177,177,177));
					} 
				return lbl; 
				} 
			}; 
			tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(2).setCellRenderer(render);
			tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
			
			Statement s;
			ResultSet rs;
			try {
				s = con.conexion().createStatement();
				rs = s.executeQuery("exec sp_lista_fuente_sodas_rh");
				
				while (rs.next()){ 
					String [] fila = new String[5];
					fila[0] = rs.getString(1).trim();
					fila[1] = rs.getString(2).trim()+" "+rs.getString(3).trim()+" "+rs.getString(4).trim();
					fila[2] = rs.getString(5).trim(); 
					switch (Integer.parseInt(rs.getString(6).trim())){
						case 1 : fila[3] = "Vigente"; break;
						case 2 : fila[3] = "Vacaciones"; break;
						case 3 : fila[3] = "Baja"; break;	
					}	
					if(Integer.parseInt(rs.getString(7).trim()) == 1){
						fila[4] = "Si";
					}else {
						fila[4] = "No";
					}
				   model.addRow(fila); 
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 JScrollPane scrol = new JScrollPane(tabla);
		    return scrol; 
		}
		
		KeyListener validaCantidad = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();				
				if(((caracter < '0') ||	
				    	(caracter > '9')) && 
				    	(caracter != '.' )){
				    	e.consume();
				    	}
			}
			@Override
			public void keyReleased(KeyEvent e) {	
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}	
		};
	}

}

