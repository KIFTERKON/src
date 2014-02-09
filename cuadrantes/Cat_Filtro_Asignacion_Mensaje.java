package cuadrantes;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import catalogos.Cat_Asignacion_Mensajes;

import SQL.Connexion;
@SuppressWarnings("serial")
public class Cat_Filtro_Asignacion_Mensaje extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	DefaultTableModel model = new DefaultTableModel(0,7){
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
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Filtro_Asignacion_Mensaje()	{
		this.setTitle("Filtro de Asignacion de  Mensajes");
		campo.setBorder(BorderFactory.createTitledBorder("Filtro De Asignacion de Mensajes"));
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(getPanelTabla()).setBounds(15,42,1030,260);
		
		campo.add(txtFolio).setBounds(15,20,48,20);
		
		agregar(tabla);
		
		cont.add(campo);
		
		txtFolio.addKeyListener(opFiltroFolio);
		
		this.setSize(1070,350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0);
	    			dispose();
	    			new Cat_Asignacion_Mensajes(folio+"").setVisible(true);
	        	}
	        }
        });
    }
//	
	KeyListener opFiltroFolio = new KeyListener() {
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
	
   	private JScrollPane getPanelTabla()	{		
		new Connexion();
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
		
		// Creamos las columnas.
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("NºMensaje");
		tabla.getColumnModel().getColumn(1).setMaxWidth(80);
		tabla.getColumnModel().getColumn(1).setMinWidth(80);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Mensaje");
		tabla.getColumnModel().getColumn(2).setMaxWidth(300);
		tabla.getColumnModel().getColumn(2).setMinWidth(300);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Puesto");
		tabla.getColumnModel().getColumn(3).setMaxWidth(150);
		tabla.getColumnModel().getColumn(3).setMinWidth(150);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Equipo");
		tabla.getColumnModel().getColumn(4).setMaxWidth(150);
		tabla.getColumnModel().getColumn(4).setMinWidth(150);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Jefatura");
		tabla.getColumnModel().getColumn(5).setMaxWidth(150);
		tabla.getColumnModel().getColumn(5).setMinWidth(150);
		tabla.getColumnModel().getColumn(6).setHeaderValue("Empleado");
		tabla.getColumnModel().getColumn(6).setMaxWidth(150);
		tabla.getColumnModel().getColumn(6).setMinWidth(150);
		
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs=s.executeQuery("select tb_asignacion_mensaje.folio [folio],"+
					"tb_asignacion_mensaje.mensaje as[mensaje],"+
					"tb_asignacion_mensaje.mensajearea as[mensajearea],"+
					"tb_asignacion_mensaje.puesto as [puesto],"+
					"tb_asignacion_mensaje.equipo as[equipo],"+
					"tb_Asignacion_mensaje.jefatura as[jefatura],"+
					"tb_asignacion_mensaje.empleado as[empleado]"+
					
					
					"from tb_asignacion_mensaje"+" order by folio asc");
			
			while (rs.next())
			{ 
			   String [] fila = new String[7];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim();
			   fila[3] = rs.getString(4).trim();
			   fila[4] = rs.getString(5).trim();
			   fila[5] = rs.getString(6).trim();
			   fila[6] = rs.getString(7).trim();
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
   	}
}
