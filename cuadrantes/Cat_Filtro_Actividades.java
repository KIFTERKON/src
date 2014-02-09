package cuadrantes;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Actividad;

@SuppressWarnings("serial")
public class Cat_Filtro_Actividades extends JFrame {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	public JTextField txtFolio = new JTextField();
	public JTextField txtActividad = new JTextField();
	
	public DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Actividad().filtro(),
            new String[]{"Folio", "Nombre de Actividad", "Descripción"}
			){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class
         };
	     
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
        public boolean isCellEditable(int fila, int columna){
        	switch(columna){
        		case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 
        	 } 				
 			return false;
 		}
                
	};
	
	public JTable tabla = new JTable(tabla_model);
	private JScrollPane scroll_tabla = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
	
	public Cat_Filtro_Actividades(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		this.setTitle("Filtro de Actividades");
		
		this.panel.setBorder(BorderFactory.createTitledBorder("Filtro de Actividades"));
		
		this.panel.add(txtFolio).setBounds(10,35,44,20);
		this.panel.add(txtActividad).setBounds(56,35,605,20);
		
		this.panel.add(scroll_tabla).setBounds(10,60,1000,335);
		
		this.cont.add(panel);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		
		this.init_tabla();
		
		this.tabla.addMouseListener(opAgregar);
		this.tabla.addKeyListener(seleccionActividadconteclado);
		
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtActividad.addKeyListener(op_filtro_nombre);
		
		
		this.setSize(1024,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
//      asigna el foco al JTextField deseado al arrancar la ventana
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){
                txtActividad.requestFocus();
             }
        });
//      pone el foco en el txtActividad al presionar la tecla scape
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "foco");
        
        getRootPane().getActionMap().put("foco", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	txtActividad.setText("");
            	txtActividad.requestFocus();
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
        
		
	}
	
	MouseListener opAgregar = new MouseListener() {
		public void mouseReleased(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() == 2){
    			int fila = tabla.getSelectedRow();
    			Object folio =  tabla.getValueAt(fila, 0);
    			dispose();
    			new Cat_Actividad(Integer.parseInt(folio.toString().trim())).setVisible(true);
        	}
		}
	};
	
	KeyListener seleccionActividadconteclado = new KeyListener() {
		@SuppressWarnings("static-access")
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
			if(caracter==e.VK_ENTER){
			int fila=tabla.getSelectedRow()-1;
			String folio = tabla.getValueAt(fila,0).toString().trim();
				
			new Cat_Actividad(Integer.parseInt(folio.toString().trim())).setVisible(true);
						dispose();
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	@SuppressWarnings({ "unchecked" })
	public void init_tabla(){
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(45);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(45);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(1500);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(750);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(2000);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(1324);
    	
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
				
				if(table.getSelectedRow() == row){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(186,143,73));
				}
			
				switch(column){
					case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 

		this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(2).setCellRenderer(render);

		this.tabla.setRowSorter(trsfiltro);  
		
		
		
		
				
    }

	 KeyListener op_filtro_folio = new KeyListener(){
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
		
		KeyListener op_filtro_nombre = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtActividad.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
	
}
