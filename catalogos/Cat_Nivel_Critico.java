package catalogos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import SQL.Connexion;

import objetos.JTextFieldLimit;
import objetos.Obj_Nivel_Critico;

@SuppressWarnings("serial")
public class Cat_Nivel_Critico extends JFrame{
	
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
	JTextField txtDescripcion = new JTextField();
	
	String valor[] = {"SELECCIONE UNA OPCION","ROJO","AMARILLO","VERDE"};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbValor = new JComboBox(valor);
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	public Cat_Nivel_Critico(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Nivel Critico"));
		
		this.setTitle("Nivel Critico");
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(5,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Descripcion:")).setBounds(5,y+=30,ancho,20);
		panel.add(txtDescripcion).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Valor:")).setBounds(5,y+=30,ancho,20);
		panel.add(cmbValor).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnEditar).setBounds(x+270,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho+60,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x-10+60,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho+30,20,ancho+230,130);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtDescripcion.setDocument(new JTextFieldLimit(100));
		
		chStatus.setEnabled(false);
		txtDescripcion.setEditable(false);
		cmbValor.setEditable(false);
		
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
		cmbValor.addActionListener(opColores);
		cont.add(panel);
		
		tabla.addMouseListener(agregar);
		
		this.setSize(760,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	ActionListener opColores = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			switch(cmbValor.getSelectedIndex()){
				case 0: cmbValor.setBackground(Color.WHITE); txtFolio.requestFocus(); break;
				case 1: cmbValor.setBackground(new Color(Integer.parseInt("A60000",16))); txtFolio.requestFocus(); break;
				case 2: cmbValor.setBackground(new Color(Integer.parseInt("FF952B",16))); txtFolio.requestFocus(); break;
				case 3: cmbValor.setBackground(new Color(Integer.parseInt("008000",16))); txtFolio.requestFocus(); break;
			}
			
		}
		
	};
	
	private JScrollPane getPanelTabla()	{
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Descripcion");
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
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("exec sp_select_nivel_critico");

			while (rs.next()){ 
			   String [] fila = new String[3];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim();			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JScrollPane scrol = new JScrollPane(tabla);
	    return scrol; 
	}
	
	MouseListener agregar = new MouseListener(){
		public void mouseClicked(MouseEvent e) {
		  	if(e.getClickCount()==2){
        		int fila = tabla.getSelectedRow();
        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
        
				txtFolio.setText(id+"");
				txtDescripcion.setText(modelo.getValueAt(fila,1)+"");
				cmbValor.setSelectedItem(modelo.getValueAt(fila,2)+"");
				btnEditar.setEnabled(true);
				chStatus.setSelected(true);
				
        	}
		}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
		
	};
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(cmbValor.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "Seleccione un Valor", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));

			}else{
				
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Nivel_Critico nc = new Obj_Nivel_Critico().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(nc.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							
							nc.setDescripcion(txtDescripcion.getText());
							nc.setValor(cmbValor.getSelectedIndex());
							nc.setStatus(chStatus.isSelected());
							
							nc.actualizar(Integer.parseInt(txtFolio.getText()));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtDescripcion.getText().toUpperCase(),nroFila,1);
							modelo.setValueAt(cmbValor.getSelectedItem(), nroFila, 2);
							
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
						nc.setDescripcion(txtDescripcion.getText());
						nc.setValor(cmbValor.getSelectedIndex());
						nc.setStatus(chStatus.isSelected());
						nc.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText();
						fila[1]=txtDescripcion.getText().toUpperCase();
						fila[2]=cmbValor.getSelectedItem()+"";
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
	
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
			Obj_Nivel_Critico nivel_critico = new Obj_Nivel_Critico().buscar(Integer.parseInt(txtFolio.getText()));
			
			if(nivel_critico.getFolio() != 0){
				txtFolio.setText(nivel_critico.getFolio()+"");
				txtDescripcion.setText(nivel_critico.getDescripcion());
								
				switch(nivel_critico.getValor()){
					case 1: cmbValor.setForeground(Color.RED); break;
					case 2: cmbValor.setForeground(Color.YELLOW); break;
					case 3: cmbValor.setForeground(Color.GREEN); break;
					default : cmbValor.setForeground(Color.BLACK); break;  
				}
				
				cmbValor.setSelectedIndex(Integer.parseInt(nivel_critico.getValor()+""));
				
				if(nivel_critico.getStatus() == true){chStatus.setSelected(true);}
				else{chStatus.setSelected(false);}
				
				btnNuevo.setEnabled(false);
				btnEditar.setEnabled(false);
				panelEnabledFalse();
				txtFolio.setEditable(true);
				txtFolio.requestFocus();
			
			}
			else{
				panelLimpiar();
				panelEnabledFalse();
				txtFolio.setEditable(true);
				txtFolio.requestFocus();
				btnNuevo.setEnabled(true);
				btnEditar.setEnabled(false);
				chStatus.setSelected(false);
				
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
		if(txtDescripcion.getText().equals("")) 			error+= "Bono\n";
				
		return error;
	}
	

	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Nivel_Critico nc = new Obj_Nivel_Critico().buscar_nuevo();
			if(nc.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(nc.getFolio()+1+"");
				txtFolio.setEditable(false);
				txtDescripcion.requestFocus();
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtFolio.setEditable(false);
				txtDescripcion.requestFocus();
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
		txtDescripcion.setEditable(false);
		chStatus.setEnabled(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txtDescripcion.setEditable(true);
		chStatus.setEnabled(true);	
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtDescripcion.setText("");
		cmbValor.setSelectedIndex(0);
		chStatus.setSelected(true);
	}
	public static void main (String arg []){
		//new Cat_Nivel_Critico().setVisible(true);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Nivel_Critico().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}	  	
	}
}