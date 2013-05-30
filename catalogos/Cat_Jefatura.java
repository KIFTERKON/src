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

import objetos.JTextFieldLimit;
import objetos.Obj_Jefatura;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Jefatura extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel modelo = new DefaultTableModel(0,2)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	public Cat_Jefatura(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Jefatura"));
		
		this.setTitle("Jefaturas");
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(5,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Nombre:")).setBounds(5,y+=30,ancho,20);
		panel.add(txtNombre).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
		
		panel.add(btnEditar).setBounds(x+270,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho+60,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x-10+60,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho+30,20,ancho+230,130);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtNombre.setDocument(new JTextFieldLimit(100));
		
		chStatus.setEnabled(false);
		txtNombre.setEditable(false);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		cont.add(panel);
		
		agregar(tabla);
		
		this.setSize(760,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(30);
		tabla.getColumnModel().getColumn(0).setMinWidth(30);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre");
		tabla.getColumnModel().getColumn(1).setMinWidth(250);
		tabla.getColumnModel().getColumn(1).setMaxWidth(250);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
		TableCellRenderer render = new TableCellRenderer(){ 
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
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select tb_jefatura.folio as [Folio],"+
					"  tb_jefatura.descripcion as [Descripcion] "+
					"  from tb_jefatura");
			
			while (rs.next()){ 
			   String [] fila = new String[3];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			  			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        
					Obj_Jefatura jefatura = new Obj_Jefatura().buscar(id);
						
					txtFolio.setText(id+"");
					txtNombre.setText(modelo.getValueAt(fila,1)+"");
					chStatus.setSelected(Boolean.parseBoolean(jefatura.getStatus()+""));
					
					btnNuevo.setVisible(false);
					btnEditar.setVisible(true);
					
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Jefatura jefat = new Obj_Jefatura().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(jefat.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							
							jefat.setDescripcion(txtNombre.getText());
							jefat.setStatus(chStatus.isSelected());
							
							jefat.actualizar(Integer.parseInt(txtFolio.getText()));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtNombre.getText(),nroFila,1);
							
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
							btnEditar.setVisible(false);
							btnNuevo.setVisible(true);
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
						jefat.setDescripcion(txtNombre.getText());
						jefat.setStatus(chStatus.isSelected());
						jefat.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText();
						fila[1]=txtNombre.getText();
						modelo.addRow(fila); 
						
						panelLimpiar();
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						btnEditar.setVisible(false);
						btnNuevo.setVisible(true);
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
	
	ActionListener buscar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Jefatura jefatura = new Obj_Jefatura();
				jefatura = jefatura.buscar(Integer.parseInt(txtFolio.getText()));
				if(jefatura.getFolio() != 0){
					txtFolio.setText(jefatura.getFolio()+"");
					txtNombre.setText(jefatura.getDescripcion()+"");
					
					if(jefatura.getStatus() == true){chStatus.setSelected(true);}
					else{chStatus.setSelected(false);}
				
					btnNuevo.setVisible(false);
					btnEditar.setVisible(true);
					panelEnabledFalse();
					txtFolio.setEditable(true);
					txtFolio.requestFocus();
				}else{
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
		if(txtNombre.getText().equals("")) 			error+= "Nombre\n";
				
		return error;
	}
	

	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Jefatura jefat = new Obj_Jefatura().buscar_nuevo();
			if(jefat.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(jefat.getFolio()+1+"");
				txtFolio.setEditable(false);
				txtNombre.requestFocus();
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtFolio.setEditable(false);
				txtNombre.requestFocus();
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			btnEditar.setVisible(false);
			btnNuevo.setVisible(true);
			chStatus.setSelected(false);
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			txtFolio.setEditable(false);
			
		}		
	};
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		txtNombre.setEditable(false);
		chStatus.setEnabled(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txtNombre.setEditable(true);
		chStatus.setEnabled(true);	
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtNombre.setText("");
		chStatus.setSelected(true);
	}
	public static void main (String arg []){
		new Cat_Jefatura().setVisible(true);
	}
}