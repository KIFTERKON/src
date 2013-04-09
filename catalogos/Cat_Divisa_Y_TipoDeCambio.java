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
import objetos.Obj_Divisa_Y_TipoDeCambio;

@SuppressWarnings("serial")
public class Cat_Divisa_Y_TipoDeCambio extends JFrame{
	
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
	JTextField txtNombre_divisas = new JTextField();
	JTextField txtValor = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
//	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	public Cat_Divisa_Y_TipoDeCambio(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Divisa y Tipo de Cambio"));
		
		this.setTitle("Divisa y Tipo de Cambio");
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
//		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Moneda:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtNombre_divisas).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Valor:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtValor).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnEditar).setBounds(x+270,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho+60,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x-10+60,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho+30,20,ancho+230,130);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtNombre_divisas.setDocument(new JTextFieldLimit(100));
		txtValor.setDocument(new JTextFieldLimit(20));
		
		txtFolio.setEditable(false);
		txtNombre_divisas.setEditable(false);
		txtValor.setEditable(false);
		chStatus.setEnabled(false);
		
		txtFolio.requestFocus();
//		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		txtValor.addKeyListener(guardar_action);
		
		txtValor.addKeyListener(validaNumericoConPunto);
		
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
//		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		btnEditar.setEnabled(false);
		cont.add(panel);
		
		agregar(tabla);
		
		this.setSize(760,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Moneda");
		tabla.getColumnModel().getColumn(1).setMinWidth(160);
		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Valor");
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
			rs = s.executeQuery("select tb_divisas_tipo_de_cambio.folio as [Folio],"+
					 "  tb_divisas_tipo_de_cambio.nombre_divisas as [Nombre], "+
					 "  tb_divisas_tipo_de_cambio.valor as [Valor] "+
					
					"  from tb_divisas_tipo_de_cambio where status=1");
			
			while (rs.next())
			{ 
				DecimalFormat decimalFormat = new DecimalFormat("#0.00");
				String cantidadd = decimalFormat.format(Double.parseDouble(rs.getString(3)));
				float saldo = Float.parseFloat(cantidadd);
			   String [] fila = new String[3];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = saldo+""; 
			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	@SuppressWarnings("unused")
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        
						Obj_Divisa_Y_TipoDeCambio denominaciones = new Obj_Divisa_Y_TipoDeCambio().buscar(id);
						
						txtFolio.setText(id+"");
						txtNombre_divisas.setText(modelo.getValueAt(fila,1)+"");
						txtValor.setText(modelo.getValueAt(fila,2)+"");
						btnEditar.setEnabled(true);
						btnNuevo.setEnabled(false);
						txtNombre_divisas.setEditable(false);
						txtValor.setEditable(false);
						chStatus.setSelected(true);
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}
			else{			
				Obj_Divisa_Y_TipoDeCambio divisas = new Obj_Divisa_Y_TipoDeCambio().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(divisas.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						
							int nroFila = tabla.getSelectedRow();
							divisas.setNombre(txtNombre_divisas.getText());
							divisas.setValor(Float.parseFloat(txtValor.getText()));
							divisas.setStatus(chStatus.isSelected());
							divisas.actualizar(Integer.parseInt(txtFolio.getText()));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtNombre_divisas.getText(),nroFila,1);
							modelo.setValueAt(txtValor.getText(), nroFila, 2);
							
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
						
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						divisas.setNombre(txtNombre_divisas.getText());
						divisas.setValor(Float.parseFloat(txtValor.getText()));
						divisas.setStatus(chStatus.isSelected());
						divisas.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText();
						fila[1]=txtNombre_divisas.getText();
						fila[2]=txtValor.getText();
						modelo.addRow(fila); 
						
						panelLimpiar();
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}			
		}
	};
	
//	KeyListener buscar_action = new KeyListener() {
//		@Override
//		public void keyTyped(KeyEvent e){
//		}
//		@Override
//		public void keyReleased(KeyEvent e) {	
//		}
//		@Override
//		public void keyPressed(KeyEvent e) {
//			if(e.getKeyCode()==KeyEvent.VK_ENTER){
//				btnBuscar.doClick();
//			}
//		}
//	};
	
	KeyListener guardar_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnGuardar.doClick();
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
	
//	ActionListener buscar = new ActionListener()
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			if(txtFolio.getText().equals("")){
//				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
//				return;
//			}else{
//			Obj_Divisa_Y_TipoDeCambio divisas = new Obj_Divisa_Y_TipoDeCambio();
//			divisas = divisas.buscar(Integer.parseInt(txtFolio.getText()));
//			
//			if(divisas.getFolio() != 0){
//			
//			txtFolio.setText(divisas.getFolio()+"");
//			txtNombre_divisas.setText(divisas.getNombre()+"");
//			txtValor.setText(divisas.getValor()+"");
//			System.out.println(divisas.getStatus());
//			if(divisas.getStatus() == true){chStatus.setSelected(true);}
//			else{chStatus.setSelected(false);}
//			
//			btnNuevo.setEnabled(false);
//			btnEditar.setEnabled(false);
//			panelEnabledFalse();
//			txtFolio.setEditable(true);
//			txtFolio.requestFocus();
//			
//			}
//			else{
//				JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
//				return;
//				}
//			}
//		}
//	};
	
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
		    	String texto = txtValor.getText().toString();
				if (texto.indexOf(".")>0) e.consume();
				
			}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
		
	};
	
	private String validaCampos(){
		String error="";
		if(txtNombre_divisas.getText().equals("")) 			error+= "Bono\n";
		if(txtValor.getText().equals(""))		error+= "Valor\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Divisa_Y_TipoDeCambio denominaciones = new Obj_Divisa_Y_TipoDeCambio().buscar_nuevo();
			if(denominaciones.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				btnEditar.setEnabled(false);
				txtFolio.setText(denominaciones.getFolio()+1+"");
				txtNombre_divisas.requestFocus();
				chStatus.setSelected(true);
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtNombre_divisas.requestFocus();
				chStatus.setSelected(true);
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(false);
			chStatus.setSelected(false);
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			btnEditar.setEnabled(false);
			btnNuevo.setEnabled(false);
		}		
	};
	
	public void panelEnabledFalse(){
		txtFolio.setEditable(false);
		txtNombre_divisas.setEditable(false);
		txtValor.setEditable(false);
		
		txtNombre_divisas.setEditable(false);
	}		
	
	public void panelEnabledTrue(){	
		txtNombre_divisas.setEditable(true);
		txtValor.setEditable(true);
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtNombre_divisas.setText("");
		txtValor.setText("");
		chStatus.setSelected(true);
	}
	public static void main (String [] arg){
		new Cat_Divisa_Y_TipoDeCambio().setVisible(true);
	}
}