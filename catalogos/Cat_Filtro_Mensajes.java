package catalogos;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;
@SuppressWarnings("serial")
public class Cat_Filtro_Mensajes extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	DefaultTableModel model = new DefaultTableModel(0,2){
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
	public Cat_Filtro_Mensajes()	{
		this.setTitle("Filtro de Mensajes");
		campo.setBorder(BorderFactory.createTitledBorder("Filtro De Mensajes"));
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(getPanelTabla()).setBounds(15,42,560,260);
		
		campo.add(txtFolio).setBounds(15,20,48,20);
		
		agregar(tabla);
		
		cont.add(campo);
		
		txtFolio.addKeyListener(opFiltroFolio);
		
		this.setSize(595,350);
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
	    			new Cat_Mensajes(folio+"").setVisible(true);
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
		
		// Creamos las columnas.
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Mensaje");
		tabla.getColumnModel().getColumn(1).setMaxWidth(550);
		tabla.getColumnModel().getColumn(1).setMinWidth(550);
		
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs=s.executeQuery("select tb_mensajes.folio [folio]," +
					"tb_mensajes.mensaje as[mensaje]"+
					"from tb_mensajes");
			
			while (rs.next())
			{ 
			   String [] fila = new String[2];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
   	}
}
