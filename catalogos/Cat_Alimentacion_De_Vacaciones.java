package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.JTextFieldLimit;
import objetos.Obj_Alimentacion_De_Vacaciones;

import SQL.Connexion;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Alimentacion_De_Vacaciones extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
//	campos de datos de enpleado	
	JLabel lblMargenEmpleado = new JLabel();
	JTextField txtFolioVacaciones = new JTextField();
	JTextField txtFechaIngreso = new JTextField();
	JTextField txtFechaIngresoIMSS = new JTextField();
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnNuevo = new JButton("Nuevo");
	
	JTextField txtFolioEmpleado = new JTextField();
	JTextField txtEmpleado = new JTextField();
	JTextField txtEstablecimiento = new JTextField();
	JTextField txtPuesto = new JTextField();
	
	JTextField txtSalarioDiarioIn= new JTextField();
	JTextField txtGrupoDeVacaciones = new JTextField();
	JTextField txtProximasVacaciones = new JTextField();
	
	JButton btnFoto = new JButton("");
	
	String fileFoto = System.getProperty("user.dir")+"/Iconos/Un.JPG";
    ImageIcon tmpIconAuxFoto = new ImageIcon(fileFoto);
    
//	campo de vacaciones de empleados
	JLabel lblMargenVacaciones = new JLabel();
	
	JDateChooser fechaInicio = new JDateChooser();
	JDateChooser fechaFin = new JDateChooser();
	
	JCheckBox chbStatus = new JCheckBox("Status",true);
	
	JTextField txtVacaciones = new JTextField();
	JTextField txtPrima = new JTextField();
	JTextField txtSueldoSemana = new JTextField();
	JTextField txtInfonavit = new JTextField();
	JTextField txtPrestamo = new JTextField();
	JTextField txtCorteCaja = new JTextField();
	JTextField txtFSodas = new JTextField();
	
	JLabel lblSigno = new JLabel("Total a pagar $");
	JLabel lblTotal = new JLabel("00000.00");
	
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	
//	campo de tabla de vacaciones
	JLabel lblMargenTabla = new JLabel();

    Border blackline;
    
	public Cat_Alimentacion_De_Vacaciones(){
		
//		efectos de bordes
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		
		lblSigno.setFont(new Font("arial", Font.BOLD, 38));
		lblTotal.setFont(new Font("arial", Font.BOLD, 38));
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
		this.setTitle("Alimentacion De Vacaciones");
		
		this.lblMargenEmpleado.setBorder(BorderFactory.createTitledBorder(blackline,"Datos Del Empleado"));
		this.lblMargenVacaciones.setBorder(BorderFactory.createTitledBorder(blackline,"Asignacion De Vacaciones"));
		this.lblMargenTabla.setBorder(BorderFactory.createTitledBorder(blackline,"Tabla De Vacaciones Pasadas"));
		
		int y=20;
//		campos de datos de enpleado
		panel.add(lblMargenEmpleado).setBounds(3, 0, 675, 205);
		panel.add(new JLabel("Folio Vacaciones: ")).setBounds(20, y, 110, 20);
		panel.add(txtFolioVacaciones).setBounds(130, y, 60, 20);
		
		panel.add(btnBuscar).setBounds(200, y, 30, 20);
		panel.add(btnNuevo).setBounds(240, y, 80, 20);
		
		panel.add(new JLabel("Empleado: ")).setBounds(20, y+=25, 110, 20);
		panel.add(txtFolioEmpleado).setBounds(130, y, 50, 20);
		panel.add(txtEmpleado).setBounds(181, y, 319, 20);
		
		panel.add(new JLabel("Establecimiento: ")).setBounds(20, y+=25, 110, 20);
		panel.add(txtEstablecimiento).setBounds(130, y, 190, 20);
		
		panel.add(new JLabel("Puesto: ")).setBounds(20, y+=25, 60, 20);
		panel.add(txtPuesto).setBounds(130, y, 370, 20);
		
		panel.add(new JLabel("Fecha Ingreso: ")).setBounds(20, y+=25, 130, 20);
		panel.add(txtFechaIngreso).setBounds(150, y, 100, 20);
		
		panel.add(new JLabel("Grupo De Vacaciones: ")).setBounds(290, y, 140, 20);
		panel.add(txtGrupoDeVacaciones).setBounds(400, y, 100, 20);
		
		panel.add(new JLabel("Fecha Ingreso IMSS: ")).setBounds(20, y+=25, 120, 20);
		panel.add(txtFechaIngresoIMSS).setBounds(150, y, 100, 20);
		
		panel.add(new JLabel("Proximas Vacaciones: ")).setBounds(290, y, 140, 20);
		panel.add(txtProximasVacaciones).setBounds(400, y, 100, 20);
		
		panel.add(new JLabel("Salario Diario Integrado: ")).setBounds(20, y+=25, 140, 20);
		panel.add(txtSalarioDiarioIn).setBounds(150, y, 100, 20);
		
		panel.add(btnFoto).setBounds(510, 30, 150, 150);

//		campo de vacaciones de empleados
		panel.add(lblMargenVacaciones).setBounds(3, 205, 675, 150);
		
		panel.add(new JLabel("Fecha Inicial: ")).setBounds(20, y+=55, 140, 20);
		panel.add(fechaInicio).setBounds(125, y, 100, 20);
		
		panel.add(new JLabel("Fecha Final: ")).setBounds(235, y, 140, 20);
		panel.add(fechaFin).setBounds(340, y, 100, 20);

		panel.add(chbStatus).setBounds(560, y, 80, 20);
		
		panel.add(new JLabel("Vacaciones: ")).setBounds(20, y+=25, 140, 20);
		panel.add(txtVacaciones).setBounds(125, y, 100, 20);
		
		panel.add(new JLabel("Prima Vacacional: ")).setBounds(235, y, 140, 20);
		panel.add(txtPrima).setBounds(340, y, 100, 20);
		
		panel.add(new JLabel("Infonavit: ")).setBounds(450, y, 100, 20);
		panel.add(txtInfonavit).setBounds(560, y, 100, 20);
		
		panel.add(new JLabel("Sueldo Semanal: ")).setBounds(20, y+=25, 140, 20);
		panel.add(txtSueldoSemana).setBounds(125, y, 100, 20);
		
		panel.add(new JLabel("Corte De Caja: ")).setBounds(235, y, 140, 20);
		panel.add(txtCorteCaja).setBounds(340, y, 100, 20);
		
		panel.add(new JLabel("Fuente De Sodas: ")).setBounds(450, y, 120, 20);
		panel.add(txtFSodas).setBounds(560, y, 100, 20);

		panel.add(new JLabel("Prestamo: ")).setBounds(20, y+=25, 140, 20);
		panel.add(txtPrestamo).setBounds(125, y, 100, 20);
		
		panel.add(lblSigno).setBounds(230, y, 450, 50);
		
		panel.add(btnDeshacer).setBounds(20, y+=25, 90, 20);
		panel.add(btnGuardar).setBounds(125, y, 90, 20);
		
		lblSigno.setText(lblSigno.getText()+lblTotal.getText());

//		campo de tabla de vacaciones
		panel.add(lblMargenTabla).setBounds(3, 355, 675, 150);
		
        Icon iconoFoto = new ImageIcon(tmpIconAuxFoto.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
        btnFoto.setIcon(iconoFoto);
		
        btnNuevo.addActionListener(opNuevo);
        
        fechaFin.setEnabled(false);

//	FUNCION PARA AGREGAR UNA AXION AL SELECCIONAR UNA FECHA
        fechaInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
        	  public void propertyChange(PropertyChangeEvent e) {
        	            if ("date".equals(e.getPropertyName())) {
//        	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
//        	                int rangoEnDias = 50;
//        	                int tiempo =(24 * 60 * 60 * 1000);
//        	                long dias = rangoEnDias * tiempo;
//        	                
//        	                long tiempoActual = fechaInicio.getDate().getTime();
//        	                Date fechaLimite = new Date(tiempoActual + dias);
//        	               
//        	                fechaFin.setDate(fechaLimite);
        	            	new Obj_Alimentacion_De_Vacaciones().buscar_vacaciones(Integer.valueOf(txtFolioEmpleado.getText()), Integer.valueOf(txtProximasVacaciones.getText()), new SimpleDateFormat("dd/MM/yyyy").format(fechaInicio.getDate()));
        	            	
        	            }
        	        }
        	    });
//---------------------------------------------------------------------------------------------------------------------------
		cont.add(panel);
		this.setSize(700,545);
	}
	
	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Filtro_Permiso_Empleado().setVisible(true);
		}
	};
	
	ActionListener opFecha = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.print("adfkjssdg");
		}
	};
	
	
	
	//Filtro Empleado
	public class Filtro_Permiso_Empleado extends JFrame{
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		Connexion con = new Connexion();
		
		DefaultTableModel model = new DefaultTableModel(0,3){
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla = new JTable(model);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JLabel lblBuscar = new JLabel("BUSCAR : ");
		JTextField txtBuscar = new JTextField();
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Filtro_Permiso_Empleado()	{
			this.setTitle("Filtro Empleados");
			txtBuscar.setDocument(new JTextFieldLimit(130));
			
			txtBuscar.addKeyListener(new KeyAdapter() { 
				public void keyReleased(final KeyEvent e) { 
	                filtro(); 
	            } 
	        });
		
			trsfiltro = new TableRowSorter(model); 
			tabla.setRowSorter(trsfiltro);  
			
			campo.add(getPanelTabla()).setBounds(10,70,365,450);
			
			agregar(tabla);
			
			campo.add(lblBuscar).setBounds(30,30,70,20);
			campo.add(txtBuscar).setBounds(95,30,215,20);
			
			cont.add(campo);
			
			this.setSize(390,570);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			tabla.addKeyListener(seleccionEmpleadoconteclado);
			
		}
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		        		
		        		int fila = tabla.getSelectedRow();
		    			String folio =  tabla.getValueAt(fila, 0).toString().trim();
		    			dispose();
		    			
		    			llenarDatosEmpleado(Integer.valueOf(folio));
		        	}
		        }
	        });
	    }
		
		public void llenarDatosEmpleado(int folio){
			
			Obj_Alimentacion_De_Vacaciones alimentacion = new Obj_Alimentacion_De_Vacaciones().buscar(folio);
			txtFolioVacaciones.setText(alimentacion.getFolio_vacaciones()+"");
			txtFolioEmpleado.setText(alimentacion.getFolio_empleado()+"");
			txtEmpleado.setText(alimentacion.getEmpleado());
			txtEstablecimiento.setText(alimentacion.getEstablecimiento());
			txtPuesto.setText(alimentacion.getPuesto());
			txtFechaIngreso.setText(alimentacion.getFecha_ingreso());
			txtFechaIngresoIMSS.setText(alimentacion.getFecha_ingreso_imss());
			txtSalarioDiarioIn.setText(alimentacion.getSalario_diario_integrado()+"");
			txtGrupoDeVacaciones.setText(alimentacion.getGrupo_vacacional());
			txtProximasVacaciones.setText("1");
			
			ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
	         Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
	         btnFoto.setIcon(iconoDefault);
		}
		
		@SuppressWarnings("unchecked")
		public void filtro() { 
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));
		}  
		private JScrollPane getPanelTabla()	{		
			new Connexion();
			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			

			
			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMaxWidth(70);
			tabla.getColumnModel().getColumn(0).setMinWidth(70);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Empleado");
			tabla.getColumnModel().getColumn(1).setMaxWidth(185);
			tabla.getColumnModel().getColumn(1).setMinWidth(185);
			tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
			tabla.getColumnModel().getColumn(2).setMaxWidth(100);
			tabla.getColumnModel().getColumn(2).setMinWidth(100);
			
			TableCellRenderer render = new TableCellRenderer() 
			{ 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					Component componente = null;
					
					componente = new JLabel(value == null? "": value.toString());
			
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}	
				return componente; 
				} 
			}; 
							tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
							tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
							tabla.getColumnModel().getColumn(2).setCellRenderer(render); 
			
			Statement s;
			ResultSet rs;
			try {
				s = con.conexion().createStatement();
				rs = s.executeQuery("sp_select_permiso_checador_filtro_empleados" );
				
				while (rs.next())
				{ 
				   String [] fila = new String[3];
				   fila[0] = rs.getString(1).trim();
				   fila[1] = rs.getString(2).trim();
				   fila[2] = rs.getString(3).trim();
				   
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
		
		KeyListener validaNumericoConPunto = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				
			    if(((caracter < '0') ||	
			    	(caracter > '9')) && 
			    	(caracter != '.')){
			    	e.consume();
			    	}
			    		    		       	
			}
			@Override
			public void keyPressed(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
									
		};
		
		KeyListener seleccionEmpleadoconteclado = new KeyListener() {
			@SuppressWarnings({ "static-access", "unused" })
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				
				if(caracter==e.VK_ENTER){
				int fila=tabla.getSelectedRow()-1;
				String folio = tabla.getValueAt(fila,0).toString().trim();
					
//				txtFolioEmpleado.setText(folio);
				btnBuscar.doClick();
				dispose();
				}
			}
			@Override
			public void keyPressed(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
									
		};
		
		
	}
	public static void main(String [] arg){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Alimentacion_De_Vacaciones().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
