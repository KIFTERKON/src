package CatalogoChecador;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import objetos.JTextFieldLimit;
import ObjetoChecador.Obj_Dias_Inhabiles;
import SQL.Connexion;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Dias_Inhabiles extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
Connexion con = new Connexion();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,4)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new JTextField();
	
	JDateChooser txtFecha = new JDateChooser();
	
	JTextField txtDescripcion = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	public Cat_Dias_Inhabiles(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Días Inhábiles"));
		
		this.setTitle("Días Inhábiles");
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,65,20);
		panel.add(btnGuardar).setBounds(x+310,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho+320,y,ancho,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtFecha).setBounds(ancho-20,y,ancho+20,20);
		panel.add(btnNuevo).setBounds(x+200,y,ancho,20);
		panel.add(btnEditar).setBounds(x+310,y,ancho,20);
		panel.add(btnSalir).setBounds(x+ancho+320,y,ancho,20);
		
		panel.add(new JLabel("Descripcion:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtDescripcion).setBounds(ancho-20,y,(ancho*6)+60,20);
		
		panel.add(getPanelTabla()).setBounds(20,y+=30,ancho+760,480);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtDescripcion.setDocument(new JTextFieldLimit(100));
		
		chStatus.setEnabled(false);
		txtDescripcion.setEditable(false);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
//	----------------------------------------------------------------------------------------------------------------	
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		btnEditar.setEnabled(false);
		cont.add(panel);
		
		agregar(tabla);
		
		this.setSize(910,650);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Fecha");
		tabla.getColumnModel().getColumn(1).setMinWidth(100);
		tabla.getColumnModel().getColumn(1).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Descripcion");
		tabla.getColumnModel().getColumn(2).setMinWidth(650);
		tabla.getColumnModel().getColumn(2).setMaxWidth(650);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Año");
		tabla.getColumnModel().getColumn(3).setMinWidth(60);
		tabla.getColumnModel().getColumn(3).setMaxWidth(60);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		
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
		
		 JScrollPane scrol = new JScrollPane(tabla);
		 
		 	//////////////////limpia la tabla antes de acer otra busqueda   ////////////////
			/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
			/**/	   		 getTabla();												/**/
			////////////////////////////////////////////////////////////////////////////////
		   
	    return scrol; 
	}
	
	public void getTabla(){
		String todos1 = "select tb_dias_inhabiles.folio as [Folio]," +
				" tb_dias_inhabiles.fecha as [Fecha]," +
				" tb_dias_inhabiles.descripcion as [Descripcion]" +
		" from tb_dias_inhabiles" +
		" order by tb_dias_inhabiles.fecha desc";
//		Statement stmt = null;
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery(todos1);
			
			while (rs.next())
			{ 
			   String [] fila = new String[4];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim().toUpperCase();
			   fila[2] = rs.getString(3).trim().toUpperCase(); 
			   
			   int iniciocadena = rs.getString(2).trim().length();
			   fila[3] =  rs.getString(2).trim().substring(iniciocadena-4,iniciocadena); 
			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        
						Obj_Dias_Inhabiles diaInA = new Obj_Dias_Inhabiles().buscar(id);
						
						txtFolio.setText(id+"");
						
						try {
							Date date_fecha = new SimpleDateFormat("dd/MM/yyyy").parse(modelo.getValueAt(fila,1)+"");
							txtFecha.setDate(date_fecha);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
						txtDescripcion.setText(modelo.getValueAt(fila,2)+"");
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
				Obj_Dias_Inhabiles diaInA = new Obj_Dias_Inhabiles().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(diaInA.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							
							diaInA.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
							diaInA.setDescripcion(txtDescripcion.getText());
							
							diaInA.actualizar(Integer.parseInt(txtFolio.getText()));
							
							////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
							/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
							/**/	   		 getTabla();												/**/
							////////////////////////////////////////////////////////////////////////////////
//							modelo.setValueAt(txtFolio.getText(),nroFila,0);
//							modelo.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()),nroFila,1);
//							modelo.setValueAt(txtDescripcion.getText().toUpperCase(), nroFila, 2);
							 
							int iniciocadena = txtFecha.getDate().toString().trim().length();
							modelo.setValueAt(txtFecha.getDate().toString().substring(iniciocadena-4, iniciocadena),nroFila,3);
							
							txtFecha.setDate(null);
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
				    	diaInA.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
						diaInA.setDescripcion(txtDescripcion.getText());
						diaInA.guardar();
						
//						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
						/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
						/**/	   		 getTabla();												/**/
						////////////////////////////////////////////////////////////////////////////////
//						fila[0]=txtFolio.getText();
//						fila[1]=new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate());
//						fila[2]=txtDescripcion.getText().toUpperCase();
//						
//						fila[3]=new SimpleDateFormat("yyyy").format(txtFecha.getDate());
//						modelo.addRow(fila); 
						
						txtFecha.setDate(null);
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
			Obj_Dias_Inhabiles diaInA = new Obj_Dias_Inhabiles();
			diaInA = diaInA.buscar(Integer.parseInt(txtFolio.getText()));
			
			if(diaInA.getFolio() != 0){
			
			txtFolio.setText(diaInA.getFolio()+"");

			try {
				Date date_fecha = new SimpleDateFormat("dd/MM/yyyy").parse(diaInA.getFecha());
				txtFecha.setDate(date_fecha);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			txtDescripcion.setText(diaInA.getDescripcion()+"");

//			if(puesto.getStatus() == true){chStatus.setSelected(true);}
//			else{chStatus.setSelected(false);}
			
			btnNuevo.setEnabled(false);
			btnEditar.setEnabled(true);
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
		if(txtFecha.getDate().equals("")) 			error+= "Fecha\n";
		if(txtDescripcion.getText().equals(""))		error+= "Descripcion\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Dias_Inhabiles diaInA = new Obj_Dias_Inhabiles().buscar_nuevo();
			if(diaInA.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(diaInA.getFolio()+1+"");
				txtFolio.setEditable(false);
				txtFecha.requestFocus();
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtFolio.setEditable(false);
				txtFecha.requestFocus();
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
		chStatus.setSelected(true);
	}
	
	public static void main(String[]a)
	{
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Dias_Inhabiles().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}