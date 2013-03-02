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

import SQL.Connexion;

import objetos.JTextFieldLimit;
import objetos.Obj_Sueldo;

@SuppressWarnings("serial")
public class Cat_Sueldo extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
Connexion con = new Connexion();
	
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
	JTextField txtSueldo = new JTextField();
	
	/*String puesto[] = new Obj_Puesto().Combo_Puesto();
	@SuppressWarnings("unchecked")
	JComboBox cmbPuesto = new JComboBox(puesto);*/
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	
	public Cat_Sueldo(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dollar.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Sueldo"));
		
		this.setTitle("Sueldo");
	
		chStatus.setSelected(true);
		
		int x = 45, y=40, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho,y,ancho,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Sueldo:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtSueldo).setBounds(ancho,y,ancho+x,20);
		panel.add(btnNuevo).setBounds(x+210,y,ancho,20);

//		panel.add(new JLabel("Puesto:")).setBounds(x,y+=30,ancho,20);
//		panel.add(cmbPuesto).setBounds(ancho,y,ancho+x,20);
		
		panel.add(btnEditar).setBounds(x+210,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x-10,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+210,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho-80+30,20,ancho+230,130);
		
		chStatus.setEnabled(false);
		txtSueldo.setEditable(false);
//		cmbPuesto.setEnabled(false);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtSueldo.setDocument(new JTextFieldLimit(8));
		
		txtFolio.addKeyListener(numerico_action);
		txtSueldo.addKeyListener(validaNumericoConPunto);
		txtFolio.addKeyListener(buscar_action);

		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		agregar(tabla);
		cont.add(panel);

		this.setSize(760,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	@SuppressWarnings("unused")
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        
						Obj_Sueldo fuente_sodas = new Obj_Sueldo().buscar(id);
						
						txtFolio.setText(id+"");
						txtSueldo.setText(modelo.getValueAt(fila,1)+"");
						chStatus.setSelected(true);
						btnNuevo.setVisible(false);
						btnEditar.setVisible(true);
					
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
		tabla.getColumnModel().getColumn(1).setHeaderValue("Sueldo");
		tabla.getColumnModel().getColumn(1).setMinWidth(160);
		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
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
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select folio,"+
					 			" sueldo"+
					" from tb_sueldo ");
			
			while (rs.next())
			{ 
			   String [] fila = new String[2];
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
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Sueldo sueldo = new Obj_Sueldo().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(sueldo.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							sueldo.setSueldo(Float.parseFloat(txtSueldo.getText()));
//							sueldo.setPuesto(cmbPuesto.getSelectedIndex());
							sueldo.setStatus(chStatus.isSelected());
							sueldo.actualizar(Integer.parseInt(txtFolio.getText()));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtSueldo.getText(),nroFila,1);
							
							panelLimpiar();
							panelEnabledFalse();
							btnEditar.setVisible(false);
							btnNuevo.setVisible(true);
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
						sueldo.setSueldo(Float.parseFloat(txtSueldo.getText()));
//						sueldo.setPuesto(cmbPuesto.getSelectedIndex());
						sueldo.setStatus(chStatus.isSelected());
						sueldo.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
						
						fila[0]=txtFolio.getText();
						fila[1]=txtSueldo.getText();
						
						modelo.addRow(fila); 
						
						panelLimpiar();
						panelEnabledFalse();
						btnEditar.setVisible(false);
						btnNuevo.setVisible(true);
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}			
		}
	};
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		txtSueldo.setEditable(false);
//		cmbPuesto.setEnabled(false);
		chStatus.setEnabled(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txtSueldo.setEditable(true);
//		cmbPuesto.setEnabled(true);
		chStatus.setEnabled(true);	
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtSueldo.setText("");
//		cmbPuesto.setSelectedItem("Selecciona un Puesto");
		chStatus.setSelected(true);
	}
	
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
		    		    	
		    	String texto = txtSueldo.getText().toString();
				if (texto.indexOf(".")>0) e.consume();
				
			}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
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
				Obj_Sueldo re = new Obj_Sueldo();
				re = re.buscar(Integer.parseInt(txtFolio.getText()));
				
				if(re.getFolio() != 0){
				
				txtFolio.setText(re.getFolio()+"");
				txtSueldo.setText(re.getSueldo()+"");
//				cmbPuesto.setSelectedIndex(re.getPuesto());
				if(re.getStatus() == true){chStatus.setSelected(true);}
				else{chStatus.setSelected(false);}
				
				
				
				panelEnabledFalse();
				
				btnEditar.setVisible(false);
				btnNuevo.setVisible(true);
				
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
		if(txtSueldo.getText().equals("")) 			error+= "Sueldo\n";
//		if(cmbPuesto.getSelectedItem().equals("Selecciona un Puesto") )		error+= "Puesto\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			try {
				Obj_Sueldo sueldo = new Obj_Sueldo().buscar_nuevo();
				if(sueldo.getFolio() != 0){
					panelLimpiar();
					panelEnabledTrue();
					txtFolio.setText(sueldo.getFolio()+1+"");
					txtFolio.setEditable(false);
					txtSueldo.requestFocus();
				}else{
					panelLimpiar();
					panelEnabledTrue();
					txtFolio.setText(1+"");
					txtFolio.setEditable(false);
					txtSueldo.requestFocus();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
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
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "No hay registro que Editar","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				panelEnabledTrue();
				txtFolio.setEditable(false);
				
				btnEditar.setVisible(false);
				btnNuevo.setVisible(true);
			}
			
		}		
	};
}
