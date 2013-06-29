package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


import objetos.Obj_Atributos;
import objetos.Obj_OpRespuesta;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_OpRespuesta extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,2)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new JTextField();
	JTextField txtDescripcion = new JTextField();
	
	String opciones[] = {"Seleccione una Opción","Opción Libre", "Opción Multiple"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbOpciones = new JComboBox(opciones);
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	public Cat_OpRespuesta(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Opcion de Respuesta"));
		
		this.setTitle("Opciones de Respuesta");
		
		int x = 15, y=15, ancho=100;
		
		this.panel.add(new JLabel("Folio:")).setBounds(x, y, ancho, 20);
		this.panel.add(txtFolio).setBounds(x+=ancho, y, ancho, 20);
		this.panel.add(btnBuscar).setBounds(x+=ancho+10, y, 32,20);
		this.panel.add(chStatus).setBounds(x+=40, y, 70, 20);
		
		this.panel.add(new JLabel("Opciones:")).setBounds(x-=(ancho*2)+50,y+=25, ancho,20);
		this.panel.add(cmbOpciones).setBounds(x+=ancho, y, ancho*2, 20);
		
		this.panel.add(new JLabel("Descripción:")).setBounds(x-=ancho, y+=25, ancho, 20);
		this.panel.add(txtDescripcion).setBounds(x+=ancho, y, ancho*2, 20);
		
		
//		panel.add(new JLabel("Folio:")).setBounds(5,y,ancho,20);
//		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
//		panel.add(btnBuscar).setBounds(ancho+ancho-x,y,32,20);
//		
//		panel.add(chStatus).setBounds(x+(ancho*2),y,70,20);
//		
//		panel.add(new JLabel("Opciones:")).setBounds(5,y+=30,ancho,20);
//		panel.add(cmbOpciones).setBounds(ancho-20,y,ancho+ancho,20);
//		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
//		
//		System.out.println(x);
//		panel.add(new JLabel("Descripción:")).setBounds(x, y+=30, ancho,20);
//		panel.add(txtDescripcion).setBounds(ancho-20,y,ancho+ancho,20);
//		
//		panel.add(btnEditar).setBounds(x+270,y+=30,ancho,20);
//		panel.add(btnDeshacer).setBounds(x+ancho+60,y+=30,ancho,20);
//		panel.add(btnSalir).setBounds(x-10+60,y,ancho,20);
//		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
//		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho+30,20,ancho+230,130);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		btnEditar.setEnabled(false);
		cont.add(panel);
		
		agregar(tabla);
		
		this.setSize(760,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
//	private JScrollPane getPanelTabla()	{		
//		new Connexion();
//
//		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
//		tabla.getColumnModel().getColumn(0).setMinWidth(50);
//		tabla.getColumnModel().getColumn(0).setMinWidth(50);
//		tabla.getColumnModel().getColumn(1).setHeaderValue("Descripcion");
//		tabla.getColumnModel().getColumn(1).setMinWidth(160);
//		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
//		
//		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//		tcr.setHorizontalAlignment(SwingConstants.CENTER);
//		
//		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
//		
//		TableCellRenderer render = new TableCellRenderer() 
//		{ 
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
//			boolean hasFocus, int row, int column) { 
//				JLabel lbl = new JLabel(value == null? "": value.toString());
//		
//				if(row%2==0){
//						lbl.setOpaque(true); 
//						lbl.setBackground(new java.awt.Color(177,177,177));
//				} 
//			return lbl; 
//			} 
//		}; 
//						tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
//						tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
//		
//		Statement s;
//		ResultSet rs;
//		try {
//			s = new Connexion().conexion().createStatement();
//			rs = s.executeQuery("select tb_op_respuesta.folio as [Folio],"+
//					 "  tb_op_respuesta.descripcion as [Descripcion] "+
//					
//					"  from tb_op_respuesta where status=1");
//			
//			while (rs.next())
//			{ 
//			   String [] fila = new String[2];
//			   fila[0] = rs.getString(1).trim();
//			   fila[1] = rs.getString(2).trim();
//			   
//			   modelo.addRow(fila); 
//			}	
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		 JScrollPane scrol = new JScrollPane(tabla);
//		   
//	    return scrol; 
//	}
	
	@SuppressWarnings("unused")
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        
						Obj_Puesto fuente_sodas = new Obj_Puesto().buscar(id);
						
						txtFolio.setText(id+"");
						btnEditar.setEnabled(true);
						chStatus.setSelected(true);
					
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_OpRespuesta opR = new Obj_OpRespuesta().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(opR.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							
							opR.setStatus(chStatus.isSelected());
							
							opR.actualizar(Integer.parseInt(txtFolio.getText()));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
						}
						
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						opR.setStatus(chStatus.isSelected());
						opR.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText();
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
	
	ActionListener buscar = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
			Obj_Atributos atrib = new Obj_Atributos();
			atrib = atrib.buscar(Integer.parseInt(txtFolio.getText()));
			
			if(atrib.getFolio() != 0){
			
			txtFolio.setText(atrib.getFolio()+"");
			System.out.println(atrib.getStatus());
			if(atrib.getStatus() == true){chStatus.setSelected(true);}
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
	
	private String validaCampos(){
		String error="";
				
		return error;
	}
	

	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_OpRespuesta opR = new Obj_OpRespuesta().buscar_nuevo();
			if(opR.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(opR.getFolio()+1+"");
				txtFolio.setEditable(false);
				cmbOpciones.requestFocus();
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtFolio.setEditable(false);
				cmbOpciones.requestFocus();
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(false);
			chStatus.setSelected(false);
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			txtFolio.setEditable(false);
			btnEditar.setEnabled(false);
			btnNuevo.setEnabled(true);
		}		
	};
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		cmbOpciones.setEditable(false);
		chStatus.setEnabled(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		cmbOpciones.setEditable(true);
		chStatus.setEnabled(true);	
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		cmbOpciones.setSelectedIndex(0);
		chStatus.setSelected(true);
	}
	public static void main (String arg []){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_OpRespuesta().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}
}