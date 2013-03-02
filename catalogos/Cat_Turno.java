package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import objetos.Obj_Turno;

@SuppressWarnings("serial")
public class Cat_Turno extends JFrame{
	
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
	JTextField txtNombre = new JTextField();
	JTextField txtHorario = new JTextField();
	
	JCheckBox chbStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	
	public Cat_Turno(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Turnos"));
		
		this.setTitle("Turnos");
		
		int x = 45, y=30, ancho=100;
		
		chbStatus.setSelected(true);
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho,y,ancho,20);
		panel.add(btnBuscar).setBounds(ancho+ancho+10,y,32,20);
		
		panel.add(chbStatus).setBounds(43+(ancho*2),y,ancho,20);
		
		panel.add(new JLabel("Turno:")).setBounds(x,y+=30,70,20);
		panel.add(txtNombre).setBounds(ancho,y,ancho+40,20);
		panel.add(btnNuevo).setBounds(x+210,y,ancho-10,20);
		
		panel.add(new JLabel("Horario:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtHorario).setBounds(ancho,y,ancho+40,20);
		panel.add(btnEditar).setBounds(x+210,y,ancho-10,20);
		panel.add(btnDeshacer).setBounds(x+ancho,y+=30,ancho-10,20);
		panel.add(btnSalir).setBounds(x-10,y,ancho-10,20);
		panel.add(btnGuardar).setBounds(x+210,y,ancho-10,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho-80+30,20,ancho+230,130);

		txtNombre.setEditable(false);
		txtHorario.setEditable(false);
		chbStatus.setEnabled(false);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtNombre.setDocument(new JTextFieldLimit(100));
		txtHorario.setDocument(new JTextFieldLimit(50));
		
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
		
		this.setSize(760,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		// Creamos las columnas.
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre");
		tabla.getColumnModel().getColumn(1).setMinWidth(160);
		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Horario");
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
			rs = s.executeQuery("select tb_turno.folio as [Folio],"+
					 "  tb_turno.nombre as [Nombre], "+
					 "  tb_turno.horario as [Horario] "+
					
					"  from tb_turno");
			
			while (rs.next())
			{ 
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
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
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
				btnEditar.setEnabled(false);
				btnNuevo.setEnabled(true);
				chbStatus.setEnabled(true);
			}
		}
	};
	
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				try {
					Obj_Turno turno = new Obj_Turno().buscar(Integer.parseInt(txtFolio.getText()));
					if(turno.getFolio() != 0){
						
						txtFolio.setText(turno.getFolio()+"");
						txtNombre.setText(turno.getNombre()+"");
						txtHorario.setText(turno.getHorario()+"");
						if(turno.isStatus() == true){chbStatus.setSelected(true);}
						else{chbStatus.setSelected(false);}
						btnNuevo.setEnabled(false);
						btnEditar.setEnabled(true);
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						
					} else{
						JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
						return;
					}
				
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} 			
			}
		}
	};
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			try {
				Obj_Turno turno = new Obj_Turno().buscar_nuevo();
				
				if(turno.getFolio() != 0){
					panelLimpiar();
					panelEnabledTrue();
					txtFolio.setText(turno.getFolio()+1+"");
					txtFolio.setEditable(false);
					txtNombre.requestFocus();
				}else{
					panelLimpiar();
					panelEnabledTrue();
					txtFolio.setText(1+"");
					txtFolio.setEditable(false);
					txtNombre.requestFocus();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	};
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				try {
					Obj_Turno turno = new Obj_Turno().buscar(Integer.parseInt(txtFolio.getText()));
					
					if(turno.getFolio() == Integer.parseInt(txtFolio.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
							if(validaCampos()!="") {
								JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
								return;
							}else{
								turno.setNombre(txtNombre.getText());
								turno.setHorario(txtHorario.getText());
								turno.setStatus(chbStatus.isSelected());
								turno.actualizar(Integer.parseInt(txtFolio.getText()));	

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
							turno.setNombre(txtNombre.getText());
							turno.setHorario(txtHorario.getText());
							turno.setStatus(chbStatus.isSelected());
							turno.guardar();
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
							JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						}
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} 				
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
			btnEditar.setEnabled(true);
		}
	};
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtNombre.setText("");
		txtHorario.setText("");
	}
	
	public void panelEnabledFalse(){
		txtFolio.setEditable(false);
		txtNombre.setEditable(false);
		txtHorario.setEditable(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txtNombre.setEditable(true);
		txtHorario.setEditable(true);
	}
	
	private String validaCampos(){
		String error="";
		if(txtNombre.getText().equals("")) 		error+= "Nombre\n";
		if(txtHorario.getText().equals(""))		error+= "Horario\n";
				
		return error;
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
	
}
