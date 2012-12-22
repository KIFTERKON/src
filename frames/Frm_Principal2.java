package frames;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import catalogos.Cat_Empleado;

import SQL.Connexion;


import objetos.JTextFieldLimit;
import objetos.Obj_Establecimiento;
@SuppressWarnings("serial")
public class Frm_Principal2 extends JDialog{
		
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	DefaultTableModel model = new DefaultTableModel(0,20);
	JTable tabla = new JTable(model);
	JScrollPane panelScroll        = new JScrollPane(tabla);

	JLabel Imagen = new JLabel(new ImageIcon("imgen/LogoPrincipal.png"));

	@SuppressWarnings("unchecked")
	private TableRowSorter trsfiltro;
	
	JLabel lblBuscar = new JLabel("BUSCAR : ");
	JTextField txtBuscar = new JTextField();
	
	String busqueda[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings("unchecked")
	JComboBox cmbBuscar = new JComboBox(busqueda);
	
	JButton btnAgregar = new JButton("Agregar");
	
	@SuppressWarnings("unchecked")
	public Frm_Principal2()	{
		txtBuscar.setDocument(new JTextFieldLimit(10));
		
		txtBuscar.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(getPanelTabla()).setBounds(10,70,1250,327);
        
		campo.add(lblBuscar).setBounds(10,30,70,20);
		campo.add(txtBuscar).setBounds(90,30,220,20);
		
		campo.add(new JLabel("Buscar por: ")).setBounds(330, 30, 80, 20);
		campo.add(cmbBuscar).setBounds(410, 30, 160, 20);
	
		campo.add(btnAgregar).setBounds(590,30,80,20);
		campo.add(new JLabel(new ImageIcon("imagen/LogPrincipal.png"))).setBounds(0,395,400,218);
		btnAgregar.addActionListener(agregar);
		cont.add(campo);
		
		this.setModal(true);
		this.setSize(730,620);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	public JComponent getBase(){
		return campo;
	}
	
	ActionListener agregar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			int fila = tabla.getSelectedRow();
			Object folio =  tabla.getValueAt(fila, 0);
			new Cat_Empleado(folio+"").setVisible(true);	
			
		}	
	};
	
	@SuppressWarnings("unchecked")
	public void filtro(){ 
		switch (cmbBuscar.getSelectedIndex()){
			case 0 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 0)); break;
			case 1 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 1)); break;
			case 2 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 2)); break;	
		}	
	}  
	private JScrollPane getPanelTabla()	{
		new Connexion();
		Connection conn = Connexion.conexion();
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		int a=2;
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);

		int ancho= 60;
		tabla.getColumnModel().getColumn(0).setHeaderValue("Nº");
		tabla.getColumnModel().getColumn(0).setMaxWidth(45);
		tabla.getColumnModel().getColumn(0).setMinWidth(45);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(1).setMaxWidth(230);
		tabla.getColumnModel().getColumn(1).setMinWidth(230);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Sueldo");
		tabla.getColumnModel().getColumn(2).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(2).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(3).setHeaderValue("SP Inicial");
		tabla.getColumnModel().getColumn(3).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(3).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(4).setHeaderValue("D Prestamo");
		tabla.getColumnModel().getColumn(4).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(4).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Sal. Final");
		tabla.getColumnModel().getColumn(5).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(5).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(6).setHeaderValue("D FSodas");
		tabla.getColumnModel().getColumn(6).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(6).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(7).setHeaderValue("D Punt.");
		tabla.getColumnModel().getColumn(7).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(7).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(8).setHeaderValue("D Falt.");
		tabla.getColumnModel().getColumn(8).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(8).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(9).setHeaderValue("D Asist.");
		tabla.getColumnModel().getColumn(9).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(9).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(10).setHeaderValue("D Cortes");
		tabla.getColumnModel().getColumn(10).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(10).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(11).setHeaderValue("D Infonavit");
		tabla.getColumnModel().getColumn(11).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(11).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(12).setHeaderValue("D Banorte");
		tabla.getColumnModel().getColumn(12).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(12).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(13).setHeaderValue("D Banamex");
		tabla.getColumnModel().getColumn(13).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(13).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(14).setHeaderValue("D Coop");
		tabla.getColumnModel().getColumn(14).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(14).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(15).setHeaderValue("P Día Ext");
		tabla.getColumnModel().getColumn(15).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(15).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(16).setHeaderValue("P Bono");
		tabla.getColumnModel().getColumn(16).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(16).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(17).setHeaderValue("A pagar");
		tabla.getColumnModel().getColumn(17).setMaxWidth(ancho);
		tabla.getColumnModel().getColumn(17).setMinWidth(ancho);
		tabla.getColumnModel().getColumn(18).setHeaderValue("Obs");
		tabla.getColumnModel().getColumn(18).setMaxWidth(ancho*5);
		tabla.getColumnModel().getColumn(18).setMinWidth(ancho*5);
		tabla.getColumnModel().getColumn(19).setHeaderValue("Obs2");
		tabla.getColumnModel().getColumn(19).setMaxWidth(ancho*5);
		tabla.getColumnModel().getColumn(19).setMinWidth(ancho*5);
				
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery("select folio,nombre,ap_paterno,ap_materno from tb_empleado");
			
			while (rs.next()) {
			   String  fila[] = new String[4];
			   fila[0] = rs.getString(1);
			   fila[1] = rs.getString(2).trim()+" "+rs.getString(3).trim()+" "+rs.getString(4).trim();
			   
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		 JScrollPane scrol = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
			tabla.setAutoResizeMode ( JTable.AUTO_RESIZE_OFF );
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
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnAgregar.doClick();
			}
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
}
