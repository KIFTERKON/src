package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;


import objetos.Obj_Establecimiento;
@SuppressWarnings("serial")
public class Cat_Filtro_Prestamo extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	DefaultTableModel model = new DefaultTableModel(0,6){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;

	JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    @SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Filtro_Prestamo() {
		this.setTitle("Filtro De Prestamos");
		panel.setBorder(BorderFactory.createTitledBorder("Filtro De Prestamos"));
		
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		panel.add(getPanelTabla()).setBounds(15,42,655,327);
		
		panel.add(txtFolio).setBounds(15,20,68,20);
		panel.add(txtNombre_Completo).setBounds(85,20,239,20);
		panel.add(cmbEstablecimientos).setBounds(325,20, 148, 20);

		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
		cont.add(panel);
		
		agregar(tabla);
		
		this.setSize(690,415);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0);
	    			dispose();
	    			new Cat_Prestamo(folio+"").setVisible(true);
	    			
	        	}
	        }
        });
    }
	
	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
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
	
	KeyListener opFiltroNombre = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
	ActionListener opFiltro = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
   
	private JScrollPane getPanelTabla()	{		
		new Progress_Bar_Abrir().setVisible(true); 
		JScrollPane scrol = new JScrollPane(tabla);
	    return scrol; 
	}

	KeyListener validaCantidad = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();				
			if(((caracter < '0') ||	
			    	(caracter > '9')) && 
			    	(caracter != '.' )){
			    	e.consume();
			    	}
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
		}	
	};
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.')){
		    	e.consume();
		    	}    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}								
	};
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	public class Progress_Bar_Abrir extends JDialog {
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		JProgressBar barra = new JProgressBar();
		
		public Progress_Bar_Abrir() {
			barra.setStringPainted(true);
			Thread hilo = new Thread(new Hilo());
			hilo.start();
			panel.setBorder(BorderFactory.createTitledBorder("Procesando prestamos, espere un momento..."));
			
			panel.add(barra).setBounds(20,25,350,20);
			
			cont.add(panel);
			
			this.setUndecorated(true);
			this.setSize(400,100);
			this.setModal(true);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		
		}
			class Hilo implements Runnable {
				public void run() {
					new Connexion();

					DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
					tcr.setHorizontalAlignment(SwingConstants.CENTER);
					
					int a=2;
					tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
					tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
					tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
					tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
					tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);

					tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
					tabla.getColumnModel().getColumn(0).setMaxWidth(70);
					tabla.getColumnModel().getColumn(0).setMinWidth(70);
					tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
					tabla.getColumnModel().getColumn(1).setMaxWidth(240);
					tabla.getColumnModel().getColumn(1).setMinWidth(240);
					tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
					tabla.getColumnModel().getColumn(2).setMaxWidth(130);
					tabla.getColumnModel().getColumn(2).setMinWidth(130);
					tabla.getColumnModel().getColumn(3).setHeaderValue("Status");
					tabla.getColumnModel().getColumn(3).setMaxWidth(50);
					tabla.getColumnModel().getColumn(3).setMinWidth(50);
					tabla.getColumnModel().getColumn(4).setHeaderValue("Limite De Prestamo");
					tabla.getColumnModel().getColumn(4).setMaxWidth(90);
					tabla.getColumnModel().getColumn(4).setMinWidth(90);
					tabla.getColumnModel().getColumn(5).setHeaderValue("Saldo");
					tabla.getColumnModel().getColumn(5).setMaxWidth(72);
					tabla.getColumnModel().getColumn(5).setMinWidth(72);
					
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
					String query = "exec sp_filtro_prestamo";
					Statement s;
					ResultSet rs;
					try {
						s = con.conexion().createStatement();
						rs = s.executeQuery(query);
						int total = getFilas(query);
						int i=0;
						while (rs.next()) {
							String [] fila = new String[6];

							fila[0] = rs.getString(1);
							fila[1] = rs.getString(2);
							fila[2] = rs.getString(3).trim(); 
							fila[3] = rs.getString(4);	
							fila[4] = "1  -  " + Math.rint(rs.getDouble(5)*100)/100;	
							fila[5] = Math.rint(rs.getDouble(6)*100)/100+"";
							i++;
							int porcent = (i*100)/total;
							barra.setValue(porcent);
				
							try {
								Thread.sleep(0);
							} catch (InterruptedException e) {
								e.printStackTrace();
									
							}
							model.addRow(fila); 
						}	
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
						


					dispose();
			}
		}
	}
}

