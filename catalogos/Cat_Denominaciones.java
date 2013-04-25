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
import javax.swing.JComboBox;
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

import objetos.Obj_Denominaciones;
import objetos.Obj_Divisa_Y_TipoDeCambio;

@SuppressWarnings("serial")
public class Cat_Denominaciones extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
Connexion con = new Connexion();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,5)	{
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
	
	String divisa[] = new Obj_Denominaciones().Combo_Denominaciones();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbMoneda = new JComboBox(divisa);
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	public Cat_Denominaciones(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Denominaciones"));
		
		this.setTitle("Denominaciones");
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
		
		panel.add(chStatus).setBounds(x+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Nombre:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtNombre).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Moneda:")).setBounds(x,y+=30,ancho,20);
		panel.add(cmbMoneda).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnEditar).setBounds(x+270,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho+60,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x-10+60,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho*4,20,380,130);
		
		chStatus.setSelected(true);
		chStatus.setEnabled(false);
		txtFolio.setEditable(false);
		txtNombre.setEditable(false);
		cmbMoneda.setEditable(false);
		
		txtNombre.requestFocus();
		txtFolio.addKeyListener(numerico_action);
		cmbMoneda.addKeyListener(guardar_action);
		
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		btnEditar.setEnabled(false);
		cont.add(panel);
		
		agregar(tabla);
		
		this.setSize(820,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Denominacion");
		tabla.getColumnModel().getColumn(1).setMinWidth(150);
		tabla.getColumnModel().getColumn(1).setMaxWidth(150);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Moneda");
		tabla.getColumnModel().getColumn(2).setMinWidth(90);
		tabla.getColumnModel().getColumn(2).setMaxWidth(90);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Valor");
		tabla.getColumnModel().getColumn(3).setMinWidth(45);
		tabla.getColumnModel().getColumn(3).setMaxWidth(45);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(4).setMinWidth(45);
		tabla.getColumnModel().getColumn(4).setMaxWidth(45);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
		
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
						tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(4).setCellRenderer(render);
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select tb_denominaciones.folio as [Folio],"+
					 "  tb_denominaciones.nombre as [Nombre], "+
					 "  tb_denominaciones.moneda as [Moneda], " +
					 "  tb_divisas_tipo_de_cambio.valor as [Valor]," +
					 "	 tb_denominaciones.status as [Status] "+
					
		"  from tb_denominaciones,tb_divisas_tipo_de_cambio" +
		" where " +
				"tb_divisas_tipo_de_cambio.nombre_divisas=tb_denominaciones.moneda");
			
			while (rs.next())
			{ 
			   String [] fila = new String[5];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim();
			   fila[3] = rs.getString(4).trim();
			   fila[4] = rs.getString(5).trim();
			   
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
	        
						Obj_Denominaciones denominaciones = new Obj_Denominaciones().buscar(id);
						
						txtFolio.setText(id+"");
						txtNombre.setText(modelo.getValueAt(fila,1)+"");
						cmbMoneda.setSelectedItem(modelo.getValueAt(fila,2)+"");
						btnEditar.setEnabled(true);
						btnNuevo.setEnabled(false);
						txtNombre.setEditable(false);
						
						if(Integer.parseInt(modelo.getValueAt(fila,4)+"")!=0){
							chStatus.setSelected(true);
						}else{
							chStatus.setSelected(false);
						}
						
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Folio Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
			if(txtNombre.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Nombre De La Denominacion Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Denominaciones denominaciones = new Obj_Denominaciones().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(denominaciones.getFolio() == Integer.parseInt(txtFolio.getText())){
					
					if(cmbMoneda.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "Seleccione un Tipo de Moneda", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){

							Obj_Divisa_Y_TipoDeCambio divisas = new Obj_Divisa_Y_TipoDeCambio();
							divisas = divisas.buscar_divisas(cmbMoneda.getSelectedItem()+"");

							int nroFila = tabla.getSelectedRow();
							
							System.out.println(cmbMoneda.getSelectedItem()+"");
							
							denominaciones.setNombre(txtNombre.getText()+"");
							denominaciones.setMoneda(cmbMoneda.getSelectedItem()+"");
							denominaciones.setStatus(chStatus.isSelected());
							
							denominaciones.actualizar(Integer.parseInt(txtFolio.getText()+""));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtNombre.getText(),nroFila,1);
							modelo.setValueAt(cmbMoneda.getSelectedItem()+"", nroFila, 2);
							modelo.setValueAt(divisas.getValor(),nroFila,3);
							
							if(denominaciones.getStatus()==true){
								modelo.setValueAt(1, nroFila, 4);
							}else{
								modelo.setValueAt(0, nroFila, 4);
							}
		
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}else{
						return;
					}
				}else{
					if(txtNombre.getText().equals("")){
						JOptionPane.showMessageDialog(null, "El Nombre De La Denominacion Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					}
					if(cmbMoneda.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "Seleccione un Tipo de Moneda", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						denominaciones = denominaciones.buscar(Integer.parseInt(txtFolio.getText()));
						
						Obj_Divisa_Y_TipoDeCambio divisas = new Obj_Divisa_Y_TipoDeCambio();
						divisas = divisas.buscar_divisas(cmbMoneda.getSelectedItem()+"");						

						
						denominaciones.setNombre(txtNombre.getText());
						denominaciones.setMoneda(cmbMoneda.getSelectedItem()+"");
						denominaciones.setStatus(chStatus.isSelected());
						denominaciones.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText();
						fila[1]=txtNombre.getText();
						fila[2]=cmbMoneda.getSelectedItem()+"";
						
						System.out.println("nombre cmbMoneda: "+ cmbMoneda.getSelectedItem()+"");
						System.out.println("nombre divisa: "+ divisas.getNombre());
						fila[3]=divisas.getValor();

						if(denominaciones.getStatus()==true){
							fila[4]=1;
						}else{
							fila[4]=0;
						}	
						modelo.addRow(fila); 
						
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}
			txtNombre.requestFocus();
			panelLimpiar();
			panelEnabledFalse();
		}
	};
	
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
		    	String texto =cmbMoneda.getSelectedIndex()+"";

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
	
	@SuppressWarnings("unused")
	private String validaCampos(){
		String error="";
		if(txtNombre.getText().equals("")) 			error+= "Nombre\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Denominaciones denominaciones = new Obj_Denominaciones().buscar_nuevo();
			if(denominaciones.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(denominaciones.getFolio()+1+"");
				txtNombre.requestFocus();
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtNombre.requestFocus();
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledFalse();
			
			txtFolio.setText("");
			txtNombre.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(false);
			chStatus.setSelected(true);
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
		txtNombre.setEditable(false);
	}		
	
	public void panelEnabledTrue(){	
		txtNombre.setEditable(true);
	}
	
	public void panelLimpiar(){
		txtFolio.setText("");
		txtNombre.setText("");
		cmbMoneda.setSelectedIndex(0);
	}
	public static void main (String [] arg){
		new Cat_Denominaciones().setVisible(true);
	}
}