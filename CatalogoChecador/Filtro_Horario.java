package CatalogoChecador;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.JTextFieldLimit;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Filtro_Horario extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtNombre = new JTextField();
	JTextField txtFolio = new JTextField();

	DefaultTableModel modelo = new DefaultTableModel(0,2)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(modelo);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Filtro_Horario()
	{
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/nivG.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Filtro Horario"));	
		trsfiltro = new TableRowSorter(modelo); 
		tabla.setRowSorter(trsfiltro); 
		
//		trsfiltro = new TableRowSorter(modelo); 
//		tabla.setRowSorter(trsfiltro);  
		
		panel.add(getPanelTabla()).setBounds(20,50,540        ,400);
		panel.add(txtFolio).setBounds(20,20,115,20);
		panel.add(txtNombre).setBounds(140,20,300,20);
		
		cont.add(panel);
		txtNombre.setToolTipText("Filtro");
		
		txtFolio.setDocument(new JTextFieldLimit(9));
//		txtNombre.setDocument(new JTextFieldLimit(40));
		
		txtNombre.addKeyListener(opFiltroNombre);
		txtFolio.addKeyListener(opFiltroFolio);
		
		agregar(tabla);
		
		this.setTitle("Filtro Horario");
		this.setSize(730,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	        		
	    			int fila = tabla.getSelectedRow();
	    			int folio = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	    			dispose();
	    			new Cat_Horario(folio).setVisible(true);
	        	}
	        }
        });
    }
	
//	KeyListener numerico_action = new KeyListener() {
//		public void keyTyped(KeyEvent e) {
//			char caracter = e.getKeyChar();
//
//		   if(((caracter < '0') ||
//		        (caracter > '9')) &&
//		        (caracter != KeyEvent.VK_BACK_SPACE)){
//		    	e.consume(); 
//		    }			
//		}
//		@Override
//		public void keyPressed(KeyEvent e){}
//		@Override
//		public void keyReleased(KeyEvent e){}
//								
//	};
	
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
//			falla filtro
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
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
		tabla.getColumnModel().getColumn(0).setMinWidth(120);
		tabla.getColumnModel().getColumn(0).setMinWidth(120);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre");
		tabla.getColumnModel().getColumn(1).setMinWidth(420);
		tabla.getColumnModel().getColumn(1).setMaxWidth(420);
		
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

		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery( "  select * from tb_horarios");
			int folio;
			String nombre;
//			String entrada;
//			String salida;
//			String receso;
			
			while (rs.next())
			{ 
				folio= rs.getInt(1);
				nombre= rs.getString(2).trim();
				
			   String [] fila = new String[2];
			   fila[0] = folio+"";
			   fila[1] = nombre;
//			   fila[2] = salida.substring(11,19);
			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	public static void main(String [] arg){
		new Filtro_Horario().setVisible(true);
	}
}
