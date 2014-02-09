package checador;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
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
		
		panel.add(getPanelTabla()).setBounds(20,50,800,400);
		panel.add(txtFolio).setBounds(20,20,80,20);
		panel.add(txtNombre).setBounds(100,20,720,20);
		
//      asigna el foco al JTextField deseado al arrancar la ventana
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){
                	txtNombre.requestFocus();
             }              
                });
//      pone el foco en el txtFolio al presionar la tecla scape
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "foco");
        
        getRootPane().getActionMap().put("foco", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
          	  txtNombre.setText("");
                txtNombre.requestFocus();
            }
        });
//      pone el foco en la tabla al presionar f4
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_F4 , 0), "dtabla");
        
        getRootPane().getActionMap().put("dtabla", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
          	tabla.requestFocus();
            }
        });
        
		cont.add(panel);
		txtNombre.setToolTipText("Filtro");
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		
		
		trsfiltro = new TableRowSorter(modelo); 
		tabla.setRowSorter(trsfiltro);
		
		txtNombre.addKeyListener(opFiltroNombre);
		txtFolio.addKeyListener(opFiltroFolio);
		
		agregar(tabla);
		
		this.setTitle("Filtro Horario");
		this.setSize(845,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		tabla.addKeyListener(seleccionEmpleadoconteclado);
		
		
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	        		
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0);
	    			new Cat_Horario(Integer.parseInt(folio+"")).setVisible(true);
	    			dispose();
	        	}
	        }
        });
    }
	
	KeyListener seleccionEmpleadoconteclado = new KeyListener() {
		@SuppressWarnings("static-access")
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
			if(caracter==e.VK_ENTER){
			int fila=tabla.getSelectedRow()-1;
			String folio = tabla.getValueAt(fila,0).toString().trim();
				
			new Cat_Horario(Integer.parseInt(folio+"")).setVisible(true);
						dispose();
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
		
	
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
		tabla.getColumnModel().getColumn(0).setMinWidth(80);
		tabla.getColumnModel().getColumn(0).setMinWidth(80);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre");
		tabla.getColumnModel().getColumn(1).setMinWidth(720);
		tabla.getColumnModel().getColumn(1).setMaxWidth(720);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				JComponent componente= null;
				
				componente = new JLabel(value == null? "": value.toString());
		        
				if(row%2==0){
					componente.setOpaque(true); 
					componente.setBackground(new java.awt.Color(177,177,177));
				} 
				if(table.getSelectedRow() == row){
					((JComponent) componente).setOpaque(true); 
					componente.setBackground(new java.awt.Color(186,143,73));
				}	
				
			return componente; 
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
	
}
