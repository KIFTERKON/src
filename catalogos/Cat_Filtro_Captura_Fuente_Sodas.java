//package catalogos;
//
//import java.awt.Container;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JLayeredPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.RowFilter;
//import javax.swing.SwingConstants;
//import javax.swing.UIManager;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableRowSorter;
//
//import SQL.Connexion;
//@SuppressWarnings("serial")
//public class Cat_Filtro_Captura_Fuente_Sodas extends JFrame {
//	
//	Container cont = getContentPane();
//	JLayeredPane campo = new JLayeredPane();
//	
//	DefaultTableModel model = new DefaultTableModel(0,8){
//		public boolean isCellEditable(int fila, int columna){
//			if(columna < 0)
//				return true;
//			return false;
//		}
//	};
//	
//	JTable tabla = new JTable(model);
//	
//	@SuppressWarnings("rawtypes")
//	private TableRowSorter trsfiltro;
//	
//	JTextField txtFolio = new JTextField();
//	
//	
//	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public Cat_Filtro_Captura_Fuente_Sodas()	{
//		this.setTitle("Filtro de Asignacion de  Mensajes");
//		campo.setBorder(BorderFactory.createTitledBorder("Filtro De Asignacion de Mensajes"));
//		trsfiltro = new TableRowSorter(model); 
//		tabla.setRowSorter(trsfiltro);  
//		
//		campo.add(getPanelTabla()).setBounds(15,42,1100,260);
//		
//		campo.add(txtFolio).setBounds(15,20,48,20);
//		
//		agregar(tabla);
//		
//		cont.add(campo);
//		
//		txtFolio.addKeyListener(opFiltroFolio);
//		
//		this.setSize(1140,350);
//		this.setResizable(false);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}
//	private void agregar(final JTable tbl) {
//        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
//	        public void mouseClicked(MouseEvent e) {
//	        	if(e.getClickCount() == 2){
//	    			int fila = tabla.getSelectedRow();
//	    			Object folio =  tabla.getValueAt(fila, 0);
//	    			dispose();
//	    			new Cat_Captura_Fuente_Sodas(folio+"").setVisible(true);
//	        	}
//	        }
//        });
//    }
////	
//	KeyListener opFiltroFolio = new KeyListener() {
//		@SuppressWarnings("unchecked")
//		public void keyReleased(KeyEvent arg0) {
//			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
//		}
//		public void keyTyped(KeyEvent arg0) {
//			char caracter = arg0.getKeyChar();
//			if(((caracter < '0') ||
//				(caracter > '9')) &&
//			    (caracter != KeyEvent.VK_BACK_SPACE)){
//				arg0.consume(); 
//			}	
//		}
//		public void keyPressed(KeyEvent arg0) {}		
//	};
//	
//   	private JScrollPane getPanelTabla()	{		
//		new Connexion();
//		
//		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//		tcr.setHorizontalAlignment(SwingConstants.CENTER);
//		
//		
//		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
//		tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);
//		
//		// Creamos las columnas.
//		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
//		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
//		tabla.getColumnModel().getColumn(0).setMinWidth(50);
//		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre_Empleado");
//		tabla.getColumnModel().getColumn(1).setMaxWidth(200);
//		tabla.getColumnModel().getColumn(1).setMinWidth(200);
//		tabla.getColumnModel().getColumn(2).setHeaderValue("N°Cliente");
//		tabla.getColumnModel().getColumn(2).setMaxWidth(70);
//		tabla.getColumnModel().getColumn(2).setMinWidth(70);
//		tabla.getColumnModel().getColumn(3).setHeaderValue("Ticket");
//		tabla.getColumnModel().getColumn(3).setMaxWidth(100);
//		tabla.getColumnModel().getColumn(3).setMinWidth(100);
//		tabla.getColumnModel().getColumn(4).setHeaderValue("Importe");
//		tabla.getColumnModel().getColumn(4).setMaxWidth(150);
//		tabla.getColumnModel().getColumn(4).setMinWidth(150);
//		tabla.getColumnModel().getColumn(5).setHeaderValue("Nombre_Cliente");
//		tabla.getColumnModel().getColumn(5).setMaxWidth(200);
//		tabla.getColumnModel().getColumn(5).setMinWidth(200);
//		tabla.getColumnModel().getColumn(6).setHeaderValue("Establecimiento");
//		tabla.getColumnModel().getColumn(6).setMaxWidth(150);
//		tabla.getColumnModel().getColumn(6).setMinWidth(150);
//		tabla.getColumnModel().getColumn(7).setHeaderValue("Puesto");
//		tabla.getColumnModel().getColumn(7).setMaxWidth(180);
//		tabla.getColumnModel().getColumn(7).setMinWidth(180);
//		
//		Statement s;
//		ResultSet rs;
//		try {
//			s = new Connexion().conexion().createStatement();
//			rs=s.executeQuery(
//					"select tb_captura_fuente_sodas.folio[folio]," +
//					"tb_captura_fuente_sodas.nombre_empleado as[nombre_empleado],"+
//					"tb_captura_fuente_sodas.no_cliente as[n°cliente],"+
//					"tb_captura_fuente_sodas.ticket as[ticket],"+
//					"tb_captura_fuente_sodas.importe aS[importe],"+
//					"tb_captura_fuente_sodas.nombre_cliente as[nom_cliente],"+
//					"tb_captura_fuente_sodas.establecimiento_cliente as[establecimiento],"+
//					"tb_captura_fuente_sodas.puesto_cliente as[puesto]"+
//					"from tb_captura_fuente_sodas "+" order by folio asc"
//					);
//			
////			rs=s.executeQuery("select tb_asignacion_mensaje.folio [folio],"+
////					"tb_asignacion_mensaje.mensaje as[mensaje],"+
////					"tb_asignacion_mensaje.mensajearea as[mensajearea],"+
////					"tb_asignacion_mensaje.puesto as [puesto],"+
////					"tb_asignacion_mensaje.equipo as[equipo],"+
////					"tb_Asignacion_mensaje.jefatura as[jefatura],"+
////					"tb_asignacion_mensaje.empleado as[empleado]"+
////					
////					
////					"from tb_asignacion_mensaje"+" order by folio asc");
////			
//			while (rs.next())
//			{ 
//			   String [] fila = new String[8];
//			   fila[0] = rs.getString(1).trim();
//			   fila[1] = rs.getString(2).trim();
//			   fila[2] = rs.getString(3).trim();
//			   fila[3] = rs.getString(4).trim();
//			   fila[4] = rs.getString(5).trim();
//			   fila[5] = rs.getString(6).trim();
//			   fila[6] = rs.getString(7).trim();
//			   fila[7] = rs.getString(8).trim();
//			   model.addRow(fila); 
//			}	
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		 JScrollPane scrol = new JScrollPane(tabla);
//		   
//	    return scrol; 
//   	}
//}
