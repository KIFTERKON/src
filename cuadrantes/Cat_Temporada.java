package cuadrantes;

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
import com.toedter.calendar.JDateChooser;
import SQL.Connexion;
import objetos.JTextFieldLimit;
import objetos.Obj_Temporada;

@SuppressWarnings("serial")
public class Cat_Temporada extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
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
	JTextField txtDescripcion = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	JDateChooser txtCalendario = new JDateChooser();
	JDateChooser txtCalendario1 = new JDateChooser();
	
	String dias[] = {"Seleccione un dia","Todos","Lunes","Martes","Miercoles","Jueves","Viernes","Savado","Domingo",};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbDias = new JComboBox(dias);
	
	public Cat_Temporada(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
		this.setTitle("Temporada");
		this.panel.setBorder(BorderFactory.createTitledBorder("Temporada"));
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(5,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+43+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Descripcion:")).setBounds(5,y+=30,ancho,20);
		panel.add(txtDescripcion).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Fecha:      De")).setBounds(5,y+=30,100,20);
		panel.add(txtCalendario).setBounds(ancho-20,y,ancho-8,20);
		this.txtCalendario.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		panel.add(btnEditar).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("A")).setBounds(ancho+75,y,30,20);
		panel.add(txtCalendario1).setBounds(ancho+87,y,ancho-8,20);
		this.txtCalendario1.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		panel.add(new JLabel("Dia: ")).setBounds(5,y+=30,100,20);
		panel.add(cmbDias).setBounds(ancho-20,y,ancho+ancho-2,20);
		
		panel.add(btnDeshacer).setBounds(x+ancho+60,y+=50,ancho,20);
		panel.add(btnSalir).setBounds(x-10+60,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho+30,20,ancho+230,180);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtDescripcion.setDocument(new JTextFieldLimit(100));
		
		chStatus.setEnabled(false);
		txtDescripcion.setEditable(false);
		
		txtFolio.requestFocus();
		
//		txtFolio.addKeyListener(buscar_action);
//		txtFolio.addKeyListener(numerico_action);
//		txtValor.addKeyListener(validaNumericoConPunto);
		txtFolio.addKeyListener(numerico_action);
		
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(opNuevo);
		btnEditar.addActionListener(editar);
		cont.add(panel);
		
		agregar(tabla);
		
		
		this.setSize(760,240);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==2){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	
	        		Obj_Temporada temporada = new Obj_Temporada().buscar(id);
						
					txtFolio.setText(id+"");
					txtDescripcion.setText(modelo.getValueAt(fila,1)+"");
					chStatus.setSelected(temporada.isStatus());
						
					try {
						Date date = new SimpleDateFormat("dd/MM/yyyy").parse(temporada.getFecha_in());
						Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(temporada.getFecha_fin());
						txtCalendario.setDate(date);
						txtCalendario1.setDate(date1);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					cmbDias.setSelectedItem(temporada.getDia());
					btnEditar.setEnabled(true);
					
	        	}
	        }
        });
    }
	
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
			Obj_Temporada temporada = new Obj_Temporada().buscar(Integer.parseInt(txtFolio.getText()));
			
			if(temporada.getFolio() != 0){
			
			txtFolio.setText(temporada.getFolio()+"");
			txtDescripcion.setText(temporada.getDescripcion()+"");
			
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(temporada.getFecha_in());
				Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(temporada.getFecha_fin());
				txtCalendario.setDate(date);
				txtCalendario1.setDate(date1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cmbDias.setSelectedItem(temporada.getDia());
			chStatus.setSelected(temporada.isStatus());			
			btnNuevo.setEnabled(false);
			btnEditar.setEnabled(true);
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
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			txtFolio.setEditable(false);
			txtCalendario.setEnabled(true);
			txtCalendario1.setEnabled(true);
			btnEditar.setEnabled(false);
			btnNuevo.setEnabled(true);
			
		}		
	};
	
	ActionListener opNuevo = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			panelLimpiar();
			txtFolio.setText(new Obj_Temporada().nuevo()+"");
			txtFolio.setEditable(false);
			txtDescripcion.setEditable(true);
			txtCalendario.setEnabled(true);
			txtCalendario1.setEnabled(true);
			cmbDias.setEnabled(true);
			txtDescripcion.requestFocus();
			
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
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	};
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Temporada temporada = new Obj_Temporada().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(temporada.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							temporada.setDescripcion(txtDescripcion.getText());							
							temporada.setFecha_in(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
							temporada.setFecha_fin(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario1.getDate()));
							temporada.setDia(cmbDias.getSelectedItem().toString());
							
							temporada.actualizar(Integer.parseInt(txtFolio.getText()));
							
							modelo.setValueAt(txtFolio.getText().toUpperCase(),0,nroFila);
							modelo.setValueAt(txtDescripcion.getText().toUpperCase(),1,nroFila);
							
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
						temporada.setDescripcion(txtDescripcion.getText());
						temporada.setFecha_in(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate())+"");
						temporada.setFecha_fin(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario1.getDate())+"");
						temporada.setDia(cmbDias.getSelectedItem().toString());

						temporada.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText().toUpperCase();
						fila[1]=txtDescripcion.getText().toUpperCase();
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
	
	KeyListener numerico_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			int limite=10;

			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }
				if (txtFolio.getText().length()== limite)
			     e.consume();
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
		}
	};
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtDescripcion.setText("");
		txtCalendario.setDate(null);
		txtCalendario1.setDate(null);
		cmbDias.setSelectedIndex(0);
		chStatus.setSelected(true);
	}
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		txtDescripcion.setEditable(false);
		txtCalendario.setEnabled(false);
		txtCalendario1.setEnabled(false);
		chStatus.setEnabled(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txtDescripcion.setEditable(true);
		txtCalendario.setEnabled(false);
		txtCalendario1.setEnabled(false);
		chStatus.setEnabled(true);	
	}
	
	private String validaCampos(){
		String error="";
		String fechaInicio = txtCalendario.getDate()+"";
		String fechaFin = txtCalendario1.getDate()+"";
		
		if(txtDescripcion.getText().equals("")) error+= "Bono\n";
		if(fechaInicio.equals("null"))          error+= "Fecha Inicio\n";
		if(fechaFin.equals("null"))			    error+= "Fecha Fin\n";
		if(cmbDias.getSelectedIndex() == 0)		error+= "Día\n";
		return error;
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Descripcion");
//		tabla.getColumnModel().getColumn(1).setMinWidth(160);
//		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
			
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
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery("select tb_temporada.folio as [Folio], " +
									  " tb_temporada.descripcion as [Descripcion] " +
								"from tb_temporada");
			
			while (rs.next()) { 
				
			   String [] fila = new String[2];
			   fila[0] = rs.getString(1).trim().toUpperCase();
			   fila[1] = rs.getString(2).trim().toUpperCase();
						   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
}
