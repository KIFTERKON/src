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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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

import frames.WholeNumberField;
import objetos.Obj_Bancos;
import objetos.Obj_Empleado;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Alimentacion_Por_Denominacion extends JDialog {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	@SuppressWarnings("rawtypes")
	TableRowSorter trsfiltro;
	
	JTextField txtAsignacion = new JTextField();
	JTextField txtFecha = new JTextField();
	JButton btnTotal = new JButton("TOTAL:");
	JLabel lblTotal = new JLabel("");
	
	boolean bandera = false;
	
	Object[][] Matriz ;
	
	Object[][] Tabla = getTabla();
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Folio", "Denominacion", "Valor", "$ Cantidad" }
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Integer.class, 
	    	java.lang.Integer.class 
         };
	     @SuppressWarnings("rawtypes")
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	
        	 switch(columna){
        	 	case 0 : return false;
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 : return true;
        	 } 				
 			return false;
 		}
		
	};
	
	JTable tabla = new JTable(model);
    JScrollPane scroll = new JScrollPane(tabla);
	
    JToolBar menu = new JToolBar();
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	@SuppressWarnings("rawtypes")
	public Cat_Alimentacion_Por_Denominacion(int folio_emp,String fecha){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dollar.png"));
		this.setTitle("Alimentacion por Denominacion");
			
		panel.add(new JLabel("Asignacion: ")).setBounds(20,40,100,20);
		panel.add(txtAsignacion).setBounds(20,60,100,20);
		panel.add(new JLabel("Fecha: ")).setBounds(20,90,100,20);
		panel.add(txtFecha).setBounds(20,110,100,20);
		panel.add(btnTotal).setBounds(20, 210, 100, 20);
		panel.add(lblTotal).setBounds(20, 230, 100, 20);
		panel.add(scroll).setBounds(140,40,425,240);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		panel.add(menu);
		cont.add(panel);

		 setUpIntegerEditor(tabla);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		tabla.getColumnModel().getColumn(0).setMinWidth(72);		
		tabla.getColumnModel().getColumn(1).setMaxWidth(220);
		tabla.getColumnModel().getColumn(1).setMinWidth(220);
		tabla.getColumnModel().getColumn(2).setMaxWidth(50);
		tabla.getColumnModel().getColumn(2).setMinWidth(50);
		tabla.getColumnModel().getColumn(3).setMaxWidth(80);
		tabla.getColumnModel().getColumn(3).setMinWidth(80);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
	
		btnGuardar.addActionListener(opGuardar);
		btnTotal.addActionListener(opTotal);
		
		txtFecha.setEditable(false);
		this.setModal(true);
		this.setSize(585, 320);
		this.setLocationRelativeTo(null);
		
		Obj_Empleado re = new Obj_Empleado();
		re=re.buscar(folio_emp);
		txtFecha.setText(fecha);
	}
	
	 private void setUpIntegerEditor(JTable table) {
	        final WholeNumberField integerField = new WholeNumberField(0, 3);
	        integerField.setHorizontalAlignment(WholeNumberField.RIGHT);

	        DefaultCellEditor integerEditor = 
	            new DefaultCellEditor(integerField) {
	                public Object getCellEditorValue() {
	                    return new Integer(integerField.getValue());
	                }
	            };
	        table.setDefaultEditor(Integer.class, integerEditor);
	}
		
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			guardar();
		}
	};
	
	ActionListener opTotal = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			suma();
		}
	};
		
	@SuppressWarnings("rawtypes")
	public void guardar(){
		Vector miVector = new Vector();
		if(getFilas("select * from tb_bancos where status = 1") > 1){
			if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						model.isCellEditable(i,j);
						miVector.add(model.getValueAt(i,j));
					}
					Obj_Bancos bancos = new Obj_Bancos();

					bancos.setFolio_empleado(Integer.parseInt(miVector.get(0).toString().trim()));
					bancos.setNombre_completo(miVector.get(1).toString().trim());
					bancos.setEstablecimiento(miVector.get(2).toString().trim());
					if(miVector.get(3) != ""){
						bancos.setBanamex(Integer.parseInt(miVector.get(3).toString().trim()));
					}else{
						miVector.set(3,0);
						bancos.setBanamex(Integer.parseInt(miVector.get(3).toString().trim()));
					}
					if(miVector.get(4) != ""){
						bancos.setBanorte(Integer.parseInt(miVector.get(4).toString().trim()));
					}else{
						miVector.set(4,0);
						bancos.setBanorte(Integer.parseInt(miVector.get(4).toString().trim()));
					}
					bancos.actualizar(Integer.parseInt(miVector.get(0).toString().trim()));
					
					miVector.clear();
				}
				JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				return;
			}
			
		}else{
			for(int i=0; i<model.getRowCount(); i++){
				for(int j=0; j<model.getColumnCount()-1; j++){
					model.isCellEditable(i,j);
					miVector.add(model.getValueAt(i,j).toString());
				}
				Obj_Bancos bancos = new Obj_Bancos();
				
				bancos.setFolio_empleado(Integer.parseInt(miVector.get(0).toString().trim()));
				bancos.setNombre_completo(miVector.get(1).toString().trim());
				bancos.setEstablecimiento(miVector.get(2).toString().trim());
				if(miVector.get(3) != ""){
					bancos.setBanamex(Integer.parseInt(miVector.get(3).toString().trim()));
				}else{
					miVector.set(3,0);
					bancos.setBanamex(Integer.parseInt(miVector.get(3).toString().trim()));
				}
				if(miVector.get(4) != ""){
					bancos.setBanorte(Integer.parseInt(miVector.get(4).toString().trim()));
				}else{
					miVector.set(4,0);
					bancos.setBanorte(Integer.parseInt(miVector.get(4).toString().trim()));
				}
				bancos.guardar();
				
				miVector.clear();
			}
			JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public Object[][] getTabla(){
		new Progress_Bar_Abrir().setVisible(true);
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
	
	public void suma(){
		float suma = 0;
		
		for(int i=0;i<model.getRowCount(); i++) {
			if(model.getValueAt(i,3).toString()==null){}
			float cantidad= Float.parseFloat(model.getValueAt(i,3).toString());
			float valor= Float.parseFloat(model.getValueAt(i,2).toString());
			suma=(suma+(cantidad*valor)); 
		} 
		lblTotal.setText("$ "+String.valueOf(suma));
	}
	
	public class Progress_Bar_Abrir extends JDialog {
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		JProgressBar barra = new JProgressBar();
		
		JLabel lblNombre = new JLabel("");
		
		public Progress_Bar_Abrir() {
			barra.setStringPainted(true);
			Thread hilo = new Thread(new Hilo());
			hilo.start();
			panel.setBorder(BorderFactory.createTitledBorder("Espere un momento..."));
			
			panel.add(barra).setBounds(20,25,350,20);
			panel.add(lblNombre).setBounds(30,45,350,20);
			
			cont.add(panel);
			
			this.setUndecorated(true);
			this.setSize(400,100);
			this.setModal(true);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		
		}
			class Hilo implements Runnable {
				public void run() {
					String todos = "select tb_denominaciones.folio as [Folio],"+
					 "  tb_denominaciones.nombre as [Nombre], "+
					 "  tb_divisas_tipo_de_cambio.valor as [Valor]," +
					 "	 tb_denominaciones.status as [Status] "+
					
		"  from tb_denominaciones,tb_divisas_tipo_de_cambio" +
		" where " +
					"tb_denominaciones.status=1 and " +
					"tb_divisas_tipo_de_cambio.nombre_divisas=tb_denominaciones.moneda";
					
		Statement stmt = null;
		ResultSet rs;
		Connexion con = new Connexion();
		try {
				stmt = con.conexion().createStatement();
				rs = stmt.executeQuery(todos);
				Matriz = new Object[getFilas(todos)][7];
				int i=0;
				int total = getFilas(todos);
				while(rs.next()){
					Matriz[i][0] = rs.getString(1).trim();
					Matriz[i][1] = rs.getString(2).trim();
					Matriz[i][2] = rs.getString(3).trim();
					Matriz[i][3] = "";
					i++;
					int porcent = (i*100)/total;
					barra.setValue(porcent);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}dispose();
			}
		}
	}
}
