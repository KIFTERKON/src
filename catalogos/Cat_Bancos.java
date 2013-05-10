package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;


import objetos.Obj_Bancos;
import objetos.Obj_Establecimiento;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Bancos extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	@SuppressWarnings("rawtypes")
	TableRowSorter trsfiltro;
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	
	JCheckBox chbHabilitarBanamex = new JCheckBox("Habilitar");
	JCheckBox chbHabilitarBanorte = new JCheckBox("Habilitar");
	JCheckBox chbNegativos = new JCheckBox("Valores Negativos");
	
	boolean bandera = false;
	
	Object[][] Matriz ;
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings("rawtypes")
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	    
	Object[][] Tabla = getTabla();
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Banamex", "Banorte", "Total a Pagar" }
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class
         };
	     
	     @SuppressWarnings("rawtypes")
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : 
        	 		return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 :
    	 			if(chbHabilitarBanamex.isSelected()){
    	 				if(model.getValueAt(fila,4).toString() != ""){
    	 					return false;
    	 				}else{
        	 				return true;
    	 				}        	 				
    	 			 }else{
    	 				 return false;
    	 			 }        	 			
        	 	case 4 : 
    	 			if(chbHabilitarBanorte.isSelected()){
    	 				if(model.getValueAt(fila,3).toString().trim() != ""){
    	 					return false;
    	 				}else{
        	 				return true;
    	 				}
    	 			 }else{
    	 				 return false;
    	 			 }
        	 	case 5 : 
        	 		return false;

        	 } 				
 			return false;
 		}
	};
	
	JTable tabla = new JTable(model);
    JScrollPane scroll = new JScrollPane(tabla);
	
    String lista[] = {"0","1","2","3","4","5","6","7"};
    @SuppressWarnings("rawtypes")
	JComboBox cmbDias = new JComboBox(lista);
    	
    JToolBar menu = new JToolBar();
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	JButton btnActualizar = new JButton(new ImageIcon("imagen/Actualizar.png"));
	
	JLabel lblTotalBanamex = new JLabel("Total Banamex: "+returnBanamex());
	JLabel lblTotalBanorte = new JLabel("Total Banorte: "+returnBanorte());
	JLabel lblTotal = new JLabel("Total: "+returnTotal());
	
	@SuppressWarnings("rawtypes")
	public Cat_Bancos(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dollar.png"));
		this.setTitle("Bancos");
			
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		panel.add(txtFolio).setBounds(100,45,69,20);
		panel.add(txtNombre_Completo).setBounds(170,45,359,20);
		panel.add(cmbEstablecimientos).setBounds(530,45,210,20);
		panel.add(chbHabilitarBanamex).setBounds(750,45,90,20);
		panel.add(chbHabilitarBanorte).setBounds(875,45,90,20);
		panel.add(chbNegativos).setBounds(980,45,120,20);
		
		panel.add(scroll).setBounds(100,70,1030,580);
		
		panel.add(lblTotalBanamex).setBounds(1150,70,250,20);
		lblTotalBanamex.setFont(new Font("",0,14));
		
		panel.add(lblTotalBanorte).setBounds(1150,90,250,20);
		lblTotalBanorte.setFont(new Font("",0,14));
		
		panel.add(lblTotal).setBounds(1150,110,250,20);
		lblTotal.setFont(new Font("",0,14));
		
		menu.add(btnGuardar);
		menu.add(btnActualizar);
		menu.setBounds(0,0,150,25);
		panel.add(menu);
		cont.add(panel);

		tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		tabla.getColumnModel().getColumn(0).setMinWidth(72);		
		tabla.getColumnModel().getColumn(1).setMaxWidth(360);
		tabla.getColumnModel().getColumn(1).setMinWidth(360);
		tabla.getColumnModel().getColumn(2).setMaxWidth(210);
		tabla.getColumnModel().getColumn(2).setMinWidth(210);
		tabla.getColumnModel().getColumn(3).setMaxWidth(120);
		tabla.getColumnModel().getColumn(3).setMinWidth(120);
		tabla.getColumnModel().getColumn(4).setMaxWidth(120);
		tabla.getColumnModel().getColumn(4).setMinWidth(120);		
		tabla.getColumnModel().getColumn(5).setMaxWidth(130);
		tabla.getColumnModel().getColumn(5).setMinWidth(130);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);		
	
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
		tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(5).setCellRenderer(render);
		
		btnGuardar.addActionListener(opGuardar);
		btnActualizar.addActionListener(opActualizar);
		
		cmbEstablecimientos.addActionListener(opFiltro);
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		chbNegativos.addActionListener(opNegativos);

		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
		
	}
	
	ActionListener opActualizar = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			lblTotalBanamex.setText("Total Banamex: "+returnBanamex());
			lblTotalBanorte.setText("Total Banorte: "+returnBanorte());
			lblTotal.setText("Total: "+returnTotal());

		}
		
	};
	
	public float returnBanamex(){
		float valor = 0;
		
		for(int i=0; i<Tabla.length; i++){
			if(model.getValueAt(i,3) != null && model.getValueAt(i, 3).toString() != ""){
				valor = valor + Float.parseFloat(model.getValueAt(i,3)+"");
			}				
		}
		return valor;
	}
	
	public float returnBanorte(){
		float valor = 0;
		for(int i=0; i<Tabla.length; i++){
			if(model.getValueAt(i,4) != null && model.getValueAt(i,4).toString() != ""){
				valor = valor + Float.parseFloat(model.getValueAt(i,4)+"");
			}				
		}
		return valor;
	}
	
	public float returnTotal(){
		float valor = 0;
		for(int i=0; i<Tabla.length; i++){
			if(model.getValueAt(i, 3) != null && model.getValueAt(i,3).toString() != ""){
				valor = valor + Float.parseFloat(model.getValueAt(i,3)+"");
			}	
			if(model.getValueAt(i, 4) != null && model.getValueAt(i,4).toString() != ""){
				valor = valor + Float.parseFloat(model.getValueAt(i,4)+"");
			}		
		}
		return valor;
	}
	
	KeyListener opFiltroFolio = new KeyListener(){
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
		}
		public void keyTyped(KeyEvent arg0) {
			char caracter = arg0.getKeyChar();
			if(((caracter < '0') ||
				(caracter > '9')) &&
			    (caracter != KeyEvent.VK_BACK_SPACE)){
				arg0.consume(); 
			}	
		}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
	ActionListener opNegativos = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(chbNegativos.isSelected()){
				trsfiltro.setRowFilter(RowFilter.regexFilter("-", 5));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 5));
			}
		}
		
	};
	
	KeyListener opFiltroNombre = new KeyListener(){
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener opFiltro = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			new Progress_Bar_Guardar().setVisible(true);
		}
	};
		
	public Object[][] getTabla(){
		String todos1 = "exec sp_lista_banco";

		Statement stmt = null;
		ResultSet rs;
		Connexion con = new Connexion();
		try {
			stmt = con.conexion().createStatement();
			rs = stmt.executeQuery(todos1);
			Matriz = new Object[getFilas(todos1)][7];
			int i=0;
			while(rs.next()){
				Matriz[i][0] = rs.getString(1).trim();
				Matriz[i][1] = rs.getString(2);
				Matriz[i][2] = rs.getString(3).trim();
				float banamex = rs.getFloat(4);
				if(banamex == 0){
					Matriz[i][3] = "";
				}else{
					Matriz[i][3] = banamex;
				}
				float bannorte = rs.getFloat(5);
				if(bannorte == 0){
					Matriz[i][4] = "";
				}else{
					Matriz[i][4] = bannorte;
				}
				Matriz[i][5] = rs.getFloat(6);
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz; 
	}
	
	public int getFilas(String qry){
		int filas=0;
		Statement stmt = null;
		try {
			Connexion con = new Connexion();
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	
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
	
	public class Progress_Bar_Guardar extends JDialog {
		Container cont = getContentPane();
		
		JLayeredPane panel = new JLayeredPane();
		JProgressBar barra = new JProgressBar();
		
		String titleBorder = "";
		public Progress_Bar_Guardar() {
			barra.setStringPainted(true);
			Thread hilo = new Thread(new Hilo());
			hilo.start();
			panel.setBorder(BorderFactory.createTitledBorder("- ..."));
			panel.add(barra).setBounds(20,25,350,20);
			
			cont.add(panel);
			
			this.setUndecorated(true);
			this.setSize(400,100);
			this.setModal(true);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		
		}

		class Hilo implements Runnable {
			@SuppressWarnings("rawtypes")
			public void run() {
				int total = model.getRowCount();
		
				Vector miVector = new Vector();
				if(getFilas("exec sp_status_bancos") > 1){
					panel.setBorder(BorderFactory.createTitledBorder("Actualizando lista de Bancos..."));
					if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
						for(int i=0; i<model.getRowCount(); i++){
							for(int j=0; j<model.getColumnCount(); j++){
								if(model.getValueAt(i, j) != null){
									miVector.add(model.getValueAt(i,j));
								}else{
									miVector.add("");
								}
								
							}
							Obj_Bancos bancos = new Obj_Bancos();

							int Folio_Empleado = Integer.parseInt(miVector.get(0).toString().trim());
							
							bancos.setFolio_empleado(Folio_Empleado);
							bancos.setNombre_completo(miVector.get(1).toString().trim());
							bancos.setEstablecimiento(miVector.get(2).toString().trim());
							
							if(miVector.get(3).toString().equals("")){
								miVector.set(3,0);
								bancos.setBanamex(Float.parseFloat(miVector.get(3).toString().trim()));
								
							}else{
								bancos.setBanamex(Float.parseFloat(miVector.get(3).toString().trim()));
							}
							
							if(miVector.get(4).toString().equals("")){
								miVector.set(4,0);
								bancos.setBanorte(Float.parseFloat(miVector.get(4).toString().trim()));
							}else{
								bancos.setBanorte(Float.parseFloat(miVector.get(4).toString().trim()));
							}
							
							Obj_Bancos existe = new Obj_Bancos().buscar(Folio_Empleado);
							
							if(existe.getFolio_empleado() == Folio_Empleado){
								bancos.actualizar(Folio_Empleado);
							}else{
								bancos.guardar();
							}
							
							miVector.clear();
							int porcent = (i*100)/total;
							barra.setValue(porcent+1);
							try {
								Thread.sleep(0);
							} catch (InterruptedException e) {
								e.printStackTrace();
									
							}
						}
						lblTotalBanamex.setText("Total Banamex: "+returnBanamex());
						lblTotalBanorte.setText("Total Banorte: "+returnBanorte());
						lblTotal.setText("Total: "+returnTotal());
						Obj_Bancos bancos = new Obj_Bancos();
						bancos.Delete();
						JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
						
						
						dispose();
					}else{
						dispose();
						return;
					}
					
				}else{
					panel.setBorder(BorderFactory.createTitledBorder("Guardando lista de Bancos..."));
					for(int i=0; i<model.getRowCount(); i++){
						for(int j=0; j<model.getColumnCount()-1; j++){
							if(model.getValueAt(i, j) != null){
								miVector.add(model.getValueAt(i,j));
							}else{
								miVector.add("");
							}
						}
						Obj_Bancos bancos = new Obj_Bancos();
						
						bancos.setFolio_empleado(Integer.parseInt(miVector.get(0).toString().trim()));
						bancos.setNombre_completo(miVector.get(1).toString().trim());
						bancos.setEstablecimiento(miVector.get(2).toString().trim());
						
						if(miVector.get(3).toString().equals("")){
							miVector.set(3,0);
							bancos.setBanamex(Float.parseFloat(miVector.get(3).toString().trim()));
							
						}else{
							bancos.setBanamex(Float.parseFloat(miVector.get(3).toString().trim()));
						}
						
						if(miVector.get(4).toString().equals("")){
							miVector.set(4,0);
							bancos.setBanorte(Float.parseFloat(miVector.get(4).toString().trim()));
						}else{
							bancos.setBanorte(Float.parseFloat(miVector.get(4).toString().trim()));
						}
						
						bancos.guardar();
						
						miVector.clear();
						int porcent = (i*100)/total;
						barra.setValue(porcent+1);
						try {
							Thread.sleep(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
								
						}
					}
					lblTotalBanamex.setText("Total Banamex: "+returnBanamex());
					lblTotalBanorte.setText("Total Banorte: "+returnBanorte());
					lblTotal.setText("Total: "+returnTotal());
					Obj_Bancos bancos = new Obj_Bancos();
					bancos.Delete();
					JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
					dispose();
				}					
			}
		}
	}	
	
	
	
	
	
}