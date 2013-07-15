package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import SQL.Connexion;


import objetos.JTextFieldLimit;
import objetos.Obj_Rango_Prestamos;

@SuppressWarnings("serial")
public class Cat_Rango_Prestamos extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
Connexion con = new Connexion();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,3)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new JTextField();
	JTextField txtPrestamoMinimo = new JTextField();
	JTextField txtPrestamoMaximo = new JTextField();
	JTextField txtDescuento = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnSalir = new JButton("Salir");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnEditar = new JButton("Editar");

	public Cat_Rango_Prestamos(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dollar.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Rango de Prestamos"));
		
		this.setTitle("Rango de Prestamos");
		
		chStatus.setSelected(true);
		
		int x = 45, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho+10,y,ancho+30,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Minimo:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtPrestamoMinimo).setBounds(ancho+10,y,ancho+30,20);
		
		panel.add(new JLabel("Maximo:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtPrestamoMaximo).setBounds(ancho+10,y,ancho+30,20);
		panel.add(btnNuevo).setBounds(x+210,y,ancho,20);
		
		panel.add(new JLabel("Descuento:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtDescuento).setBounds(ancho+10,y,ancho+30,20);
		panel.add(btnEditar).setBounds(x+210,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho-5,y+=27,ancho,20);
		panel.add(btnSalir).setBounds(x-10,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+210,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho-80+30,20,ancho+230,140);
	
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtPrestamoMinimo.setDocument(new JTextFieldLimit(10));
		txtPrestamoMaximo.setDocument(new JTextFieldLimit(10));
		txtDescuento.setDocument(new JTextFieldLimit(10));
		
		btnSalir.addActionListener(cerrar);
		btnGuardar.addActionListener(guardar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		txtPrestamoMinimo.addKeyListener(validaNumericoConPunto);
		txtPrestamoMaximo.addKeyListener(validaNumericoConPunto);
		txtDescuento.addKeyListener(validaNumericoConPunto);
		panelEnabledFalse();
		txtFolio.setEditable(true);
		cont.add(panel);
		
		btnEditar.setEnabled(false);
		
		agregar(tabla);
		txtPrestamoMinimo.setEditable(false);
		this.setSize(760,220);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        
						Obj_Rango_Prestamos rango = new Obj_Rango_Prestamos().buscar(id);
						
						txtFolio.setText(id+"");
						txtPrestamoMinimo.setText("1");
						txtPrestamoMaximo.setText(rango.getPrestamo_maximo()+"");
						txtDescuento.setText(rango.getDescuento()+"");
						btnEditar.setEnabled(true);
					
	        	}
	        }
        });
    }
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		// Creamos las columnas.
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Rango");
		tabla.getColumnModel().getColumn(1).setMinWidth(160);
		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Descuento");
		tabla.getColumnModel().getColumn(2).setMinWidth(80);
		tabla.getColumnModel().getColumn(2).setMaxWidth(80);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
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
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select tb_rango_prestamos.folio as [Folio],"+
					 "  tb_rango_prestamos.minimo as [Minimo], " +
					 "	tb_rango_prestamos.maximo as [Maximo], " +
					 "  tb_rango_prestamos.descuento as [Descuento] "+
					
					"  from tb_rango_prestamos");
			
			while (rs.next())
			{ 
				DecimalFormat DF = new DecimalFormat("#0.00");
				
				double minimo	=Double.parseDouble(rs.getString(2).trim());
				double maximo	=Double.parseDouble(rs.getString(3).trim());
				double descuent	=Double.parseDouble(rs.getString(4).trim());
				
			   String [] fila = new String[3];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = DF.format(minimo)+"   -   "+DF.format(maximo);
			   fila[2] = DF.format(descuent); 
			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{			
				Obj_Rango_Prestamos rango_prestamo = new Obj_Rango_Prestamos().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(rango_prestamo.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							rango_prestamo.setFolio(Integer.parseInt(txtFolio.getText()));
							rango_prestamo.setPrestamo_minimo(Double.parseDouble(txtPrestamoMinimo.getText()));
							rango_prestamo.setPrestamo_maximo(Double.parseDouble(txtPrestamoMaximo.getText()));
							rango_prestamo.setDescuento(Double.parseDouble(txtDescuento.getText()));
							rango_prestamo.setStatus(chStatus.isSelected());
							rango_prestamo.actualizar(Integer.parseInt(txtFolio.getText()));

							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtPrestamoMinimo.getText()+"   -   "+txtPrestamoMaximo.getText(),nroFila,1);
							modelo.setValueAt(txtDescuento.getText(), nroFila, 2);
							
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtPrestamoMinimo.requestFocus();

							btnEditar.setEnabled(false);
						}
						
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						return;
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						rango_prestamo.setFolio(Integer.parseInt(txtFolio.getText()));
						rango_prestamo.setPrestamo_minimo(Double.parseDouble(txtPrestamoMinimo.getText()));
						rango_prestamo.setPrestamo_maximo(Double.parseDouble(txtPrestamoMaximo.getText()));
						rango_prestamo.setDescuento(Double.parseDouble(txtDescuento.getText()));
						rango_prestamo.setStatus(chStatus.isSelected());
						rango_prestamo.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
						
						fila[0]=txtFolio.getText();
						fila[1]=txtPrestamoMinimo.getText()+"   -   "+txtPrestamoMaximo.getText();
						fila[2]=txtDescuento.getText();
						modelo.addRow(fila); 
						
						panelLimpiar();
						panelEnabledFalse();
						txtFolio.setEditable(true);
						btnEditar.setEnabled(false);
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}			
		}
	};
	
	ActionListener buscar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			DecimalFormat decimalFormat = new DecimalFormat("#0.00");
			
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Necesita un folio para buscar","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
			else{
				Obj_Rango_Prestamos rango_prestamo = new Obj_Rango_Prestamos().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(rango_prestamo.getFolio()!=0){
					txtFolio.setText(rango_prestamo.getFolio()+"");
					txtPrestamoMinimo.setText(decimalFormat.format(rango_prestamo.getPrestamo_minimo()));
					txtPrestamoMaximo.setText(decimalFormat.format(rango_prestamo.getPrestamo_maximo()));
					txtDescuento.setText(rango_prestamo.getDescuento()+"");
					if(rango_prestamo.isStatus() == true){chStatus.setSelected(true);}
					else{chStatus.setSelected(false);}
					
					btnNuevo.setEnabled(false);
					btnEditar.setEnabled(false);
					panelEnabledFalse();
					txtFolio.setEditable(true);
					txtFolio.requestFocus();
				
				}
				else{
					JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		}
	};
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
		
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.requestFocus();
			txtFolio.setEditable(true);
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(false);
		}
	};
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Rango_Prestamos rango_prestamo = new Obj_Rango_Prestamos().buscar_nuevo();
			if(rango_prestamo.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(rango_prestamo.getFolio()+1+"");
				txtPrestamoMinimo.setText(1+"");
				txtPrestamoMinimo.setEditable(false);
				txtFolio.setEditable(false);
				txtPrestamoMaximo.requestFocus();
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtPrestamoMinimo.setText(1+"");
				txtPrestamoMinimo.setEditable(false);
				txtFolio.setEditable(false);
				txtPrestamoMaximo.requestFocus();
			}
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			txtFolio.setEditable(false);
			txtPrestamoMinimo.setEditable(false);
			btnEditar.setEnabled(false);
			btnNuevo.setEnabled(true);
		}		
	};
	
	KeyListener buscar_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
			}
		}
	};
	
	KeyListener numerico_action = new KeyListener() {
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
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){
		    		    	
		    	String texto = txtPrestamoMaximo.getText().toString();
		    	String texto2 = txtPrestamoMinimo.getText().toString();
		    	
				if (texto.indexOf(".")>0) e.consume();
				if (texto2.indexOf(".")>0) e.consume();
			}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txtPrestamoMinimo.setEditable(true);
		txtPrestamoMaximo.setEditable(true);
		txtDescuento.setEditable(true);
		chStatus.setEnabled(true);	
	}
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		txtPrestamoMinimo.setEditable(false);
		txtPrestamoMaximo.setEditable(false);
		txtDescuento.setEditable(false);
		chStatus.setEnabled(false);
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtPrestamoMinimo.setText("");
		txtPrestamoMaximo.setText("");
		txtDescuento.setText("");
		chStatus.setSelected(true);
	}
	
	private String validaCampos(){
		
		String error="";
		
		if(txtFolio.getText().equals("")) 			error+= "Folio\n";
		if(txtPrestamoMinimo.getText().equals("")) 	error+= "Prestamo Minimo\n";
		if(txtPrestamoMaximo.getText().equals(""))	error+= "PrestamoMaximo\n";
		if(txtDescuento.getText().equals(""))		error+= "Descuento\n";
				
		return error;
	}
}
