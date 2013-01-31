package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
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

import objetos.Obj_Establecimiento;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Lista_Raya extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();

	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
    
	Object[][] Matriz;
	Object[][] Tabla = getMatriz(cmbEstablecimientos.getSelectedIndex());
	DefaultTableModel model = new DefaultTableModel(Tabla,
		new String[]{"","Folio", "Nombre Completo", "Establecimiento", "Sueldo",
		"P Bono complementario", "Saldo Prestamo Inicial", "Descuento Prestamo", "Saldo Final", "D Fuente Sodas",
		"D Puntualidad", "D Faltas", "D Asistencia", "D Cortes", "D Infonavit",
		"D Banamex", "D Banorte", "D Cooperación", "P Día Extras", "P Bono Extra",
		"A Pagar", "Observasiones I", "Observasiones II" }
													){
	     Class[] types = new Class[]{
	    	java.lang.Boolean.class,
	    	java.lang.Integer.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	
	    	java.lang.String.class,
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class,
	    	
	    	java.lang.String.class,
	    	java.lang.String.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class,
	    	
	    	java.lang.Float.class,
	    	java.lang.Float.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class,
	    	
	    	java.lang.Float.class,
	    	java.lang.String.class,
	    	java.lang.String.class
	    	
         };
	     public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0  : return true; 
        	 	case 1  : return false; 
        	 	case 3  : return false; 
        	 	case 4  : return false; 
        	 	
        	 	case 5  : return false; 
        	 	case 6  : return false; 
        	 	case 7  : return false; 
        	 	case 8  : return false; 
        	 	case 9  : return false; 
        	 	
        	 	case 10 : return false; 
        	 	case 11 : return false; 
        	 	case 12 : return false; 
        	 	case 13 : return false; 
        	 	case 14 : return false; 
        	 	
        	 	case 15 : return false; 
        	 	case 16 : return false; 
        	 	case 17 : return false; 
        	 	case 18 : return false; 
        	 	case 19 : return false;
        	 	
        	 	case 20 : return false; 
        	 	case 21 : return false; 
        	 	case 22 : return false; 
        	 }
 			return false;
 		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	

	private TableRowSorter trsfiltro;
	
	JLabel lblBuscar = new JLabel("BUSCAR : ");
	JTextField txtBuscar = new JTextField();
	
	String busqueda[] = new Obj_Establecimiento().Combo_Establecimiento();
	JComboBox cmbBuscar = new JComboBox(busqueda);
	
//	JButton btnAgregar = new JButton("Agregar");
	
	public Cat_Lista_Raya()	{
		
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer tcrR = new DefaultTableCellRenderer();
		tcrR.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer tcrL = new DefaultTableCellRenderer();
		tcrL.setHorizontalAlignment(SwingConstants.LEFT);
		
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcrL);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcrL);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(6).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(7).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(8).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(9).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(10).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(11).setCellRenderer(tcrR);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(16);
		tabla.getColumnModel().getColumn(0).setMinWidth(16);
		tabla.getColumnModel().getColumn(1).setMaxWidth(50);
		tabla.getColumnModel().getColumn(1).setMinWidth(50);
		tabla.getColumnModel().getColumn(2).setMaxWidth(235);
		tabla.getColumnModel().getColumn(2).setMinWidth(235);
		tabla.getColumnModel().getColumn(3).setMaxWidth(90);
		tabla.getColumnModel().getColumn(3).setMinWidth(90);
		tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		tabla.getColumnModel().getColumn(4).setMinWidth(60);
		tabla.getColumnModel().getColumn(5).setMaxWidth(130);
		tabla.getColumnModel().getColumn(5).setMinWidth(130);
		tabla.getColumnModel().getColumn(6).setMaxWidth(130);
		tabla.getColumnModel().getColumn(6).setMinWidth(130);
		tabla.getColumnModel().getColumn(7).setMaxWidth(120);
		tabla.getColumnModel().getColumn(7).setMinWidth(120);
		tabla.getColumnModel().getColumn(8).setMaxWidth(80);
		tabla.getColumnModel().getColumn(8).setMinWidth(80);
		tabla.getColumnModel().getColumn(9).setMaxWidth(90);
		tabla.getColumnModel().getColumn(9).setMinWidth(90);
		tabla.getColumnModel().getColumn(10).setMaxWidth(90);
		tabla.getColumnModel().getColumn(10).setMinWidth(90);
		tabla.getColumnModel().getColumn(11).setMaxWidth(60);
		tabla.getColumnModel().getColumn(11).setMinWidth(60);
		tabla.getColumnModel().getColumn(12).setMaxWidth(80);
		tabla.getColumnModel().getColumn(12).setMinWidth(80);
		tabla.getColumnModel().getColumn(13).setMaxWidth(80);
		tabla.getColumnModel().getColumn(13).setMinWidth(80);
		tabla.getColumnModel().getColumn(14).setMaxWidth(80);
		tabla.getColumnModel().getColumn(14).setMinWidth(80);
		tabla.getColumnModel().getColumn(15).setMaxWidth(80);
		tabla.getColumnModel().getColumn(15).setMinWidth(80);
		tabla.getColumnModel().getColumn(16).setMaxWidth(80);
		tabla.getColumnModel().getColumn(16).setMinWidth(80);
		tabla.getColumnModel().getColumn(17).setMaxWidth(85);
		tabla.getColumnModel().getColumn(17).setMinWidth(85);
		tabla.getColumnModel().getColumn(18).setMaxWidth(80);
		tabla.getColumnModel().getColumn(18).setMinWidth(80);
		tabla.getColumnModel().getColumn(19).setMaxWidth(80);
		tabla.getColumnModel().getColumn(19).setMinWidth(80);
		
		tabla.getColumnModel().getColumn(20).setMaxWidth(90);
		tabla.getColumnModel().getColumn(20).setMinWidth(90);
		tabla.getColumnModel().getColumn(21).setMaxWidth(230*5);
		tabla.getColumnModel().getColumn(21).setMinWidth(230*5);
		tabla.getColumnModel().getColumn(22).setMaxWidth(230*5);
		tabla.getColumnModel().getColumn(22).setMinWidth(230*5);
		
		TableCellRenderer render = new TableCellRenderer()	{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				
				if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
					
				} 
				return lbl; 
			} 
		}; 
		tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(2).setCellRenderer(render);
		tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(5).setCellRenderer(render);
		tabla.getColumnModel().getColumn(6).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(7).setCellRenderer(render);
		tabla.getColumnModel().getColumn(8).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(9).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(10).setCellRenderer(render);
		tabla.getColumnModel().getColumn(11).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(12).setCellRenderer(render);
		tabla.getColumnModel().getColumn(13).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(14).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(15).setCellRenderer(render);
		tabla.getColumnModel().getColumn(16).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(17).setCellRenderer(render);
		tabla.getColumnModel().getColumn(18).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(19).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(20).setCellRenderer(render);
		tabla.getColumnModel().getColumn(21).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(22).setCellRenderer(render); 
		
		
		txtBuscar.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(scroll).setBounds(10,70,1250,600);
        
		campo.add(lblBuscar).setBounds(10,30,70,20);
		campo.add(txtBuscar).setBounds(90,30,220,20);
		
		campo.add(cmbBuscar).setBounds(400, 30, 160, 20);
	
		cont.add(campo);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
	}
	
	ActionListener agregar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			int fila = tabla.getSelectedRow();
			Object folio =  tabla.getValueAt(fila, 0);
			new Cat_Empleado(folio+"").setVisible(true);	
			
		}	
	};
	
	public void filtro(){ 
		switch (cmbBuscar.getSelectedIndex()){
			case 0 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 2)); break;
			case 1 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 3)); break;
			case 2 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 4)); break;	
		}	
	}  
	
	private Object[][] getMatriz(int establecimiento){
		String qry = "select tb_empleado.folio," +
					"tb_empleado.nombre," +
					"tb_empleado.ap_paterno," +
					"tb_empleado.ap_materno," +
					"tb_establecimiento.nombre as establecimiento," +
					"tb_sueldo.sueldo " +
					
			"from tb_empleado, tb_establecimiento, tb_sueldo " +
			"where tb_empleado.establecimiento_id = tb_establecimiento.folio and " +
					"tb_empleado.establecimiento_id = "+establecimiento +" and " +
					"tb_empleado.sueldo_id = tb_sueldo.folio";
		
		String qry1 ="select tb_empleado.folio," +
				    "tb_empleado.nombre," +
		            "tb_empleado.ap_paterno," +
		            "tb_empleado.ap_materno," +
		            "tb_establecimiento.nombre as establecimiento," +
		            "tb_sueldo.sueldo," +
		            "tb_persecciones_extra.bono," +
		            "tb_persecciones_extra.dia_extra," +
		            "tb_persecciones_extra.dias " +
		
		    "from tb_empleado, tb_establecimiento, tb_sueldo, tb_persecciones_extra "+ 
		    "where tb_empleado.establecimiento_id = tb_establecimiento.folio and " +
		    	   "tb_empleado.sueldo_id = tb_sueldo.folio and "+
		    	   "tb_empleado.folio = tb_persecciones_extra.folio_empleado and "+
		    	   "tb_persecciones_extra.status=1 and tb_empleado.establecimiento_id = "+establecimiento;
		
		String todos = "select tb_empleado.folio," +
					"tb_empleado.nombre," +
					"tb_empleado.ap_paterno," +
					"tb_empleado.ap_materno," +
					"tb_establecimiento.nombre as establecimiento," +
					"tb_sueldo.sueldo " +
					
					"from tb_empleado, tb_establecimiento, tb_sueldo " +
				"where tb_empleado.establecimiento_id = tb_establecimiento.folio and " +
					  "tb_empleado.sueldo_id = tb_sueldo.folio";
		
		String todos1 = "select tb_empleado.folio," +
				    "tb_empleado.nombre," +
			        "tb_empleado.ap_paterno," +
			        "tb_empleado.ap_materno," +
			        "tb_establecimiento.nombre as establecimiento," +
			        "tb_sueldo.sueldo," +
			        "tb_bono.bono," +
			        "tb_empleado.infonavit," +
			        "tb_bancos.banamex," +
			        "tb_bancos.banorte," +
			        "tb_bancos.cooperacion " +
			
			"from tb_empleado, tb_establecimiento, tb_sueldo, tb_bono, tb_bancos "+ 
			"where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
			 	   "tb_empleado.sueldo_id = tb_sueldo.folio and " +
			 	   "tb_empleado.bono_id = tb_bono.folio and " +
			 	   "tb_bancos.folio_empleado = tb_empleado.folio";
				   

		Connection conn = Connexion.conexion();
		Statement s;
		ResultSet rs;
		
		float[] valoresAsistencia = getValoresPuntualidad();
		float puntualidad = valoresAsistencia[0];
		float asistencia = valoresAsistencia[1];
		
		try {
			if(establecimiento > 0){
				if(getFilas("select * from tb_persecciones_extra where status = 1") > 1){
					s = conn.createStatement();
					rs = s.executeQuery(qry1);
					Matriz = new Object[getFilas(qry1)][7];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = false;
						Matriz[i][1] = rs.getString(1).trim();
						Matriz[i][2] = "   "+rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][3] = "   "+rs.getString(5).trim();
						Matriz[i][4] = rs.getString(6).trim();
						Matriz[i][5] = Boolean.parseBoolean(rs.getString(7).trim());
						Matriz[i][6] = Integer.parseInt(rs.getString(8).trim());
						i++;
					}
				}else{
					s = conn.createStatement();
					rs = s.executeQuery(qry);
					Matriz = new Object[getFilas(qry)][7];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = false;
						Matriz[i][1] = rs.getString(1).trim();
						Matriz[i][2] = "   "+rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][3] = "   "+rs.getString(5).trim();
						Matriz[i][4] = "0";
						Matriz[i][5] = false;
						Matriz[i][6] = 0;
						i++;
					}
				}
			}else{
				if(getFilas("select * from tb_persecciones_extra where status = 1") > 1){
					s = conn.createStatement();
					rs = s.executeQuery(todos1);
					Matriz = new Object[getFilas(todos1)][21];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = false;
						int folio_empleado =  Integer.parseInt(rs.getString(1));
						Matriz[i][1] = folio_empleado;
						Matriz[i][2] = "   "+rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][3] = "   "+rs.getString(5).trim();
						float sueldo = Float.parseFloat(rs.getString(6).trim());
						Matriz[i][4] = sueldo;
						float bono_complemento = Float.parseFloat(rs.getString(7).trim());
						Matriz[i][5] = bono_complemento;
						float[] prestamos = getPrestamos(folio_empleado);
					
						Matriz[i][6] = prestamos[0];
						float DescuentoPrestamo = prestamos[1];
						Matriz[i][7] = DescuentoPrestamo;
						Matriz[i][8] = prestamos[2];
						float DescuentoFuenteSodas = getFuenteSodas(folio_empleado);
						Matriz[i][9] = DescuentoFuenteSodas;
						
						Object[] puntualidades = getPuntualidad(folio_empleado);
						boolean puntualidades1 = Boolean.parseBoolean(puntualidades[0].toString().trim());
						if(puntualidades1 != true){
							Matriz[i][10] = 0;
						}else{
							Matriz[i][10] = puntualidad;
						}
						boolean dias = Boolean.parseBoolean(puntualidades[1].toString().trim());
						int dias_faltas = Integer.parseInt(puntualidades[2].toString());
						float descuentoDias = Math.round(((sueldo + bono_complemento)/7) * dias_faltas);
						if(dias != true){
							Matriz[i][11] = 0;
						}else{
							Matriz[i][11] = descuentoDias;
							
						}
						boolean asis = Boolean.parseBoolean(puntualidades[3].toString().trim());
						if(asis != true){
							Matriz[i][12] = 0;
						}else{
							Matriz[i][12] = asistencia;
						}
						float cortes = getDescuento_Cortes(folio_empleado);
						Matriz[i][13] = cortes;
						float infonavit = Float.parseFloat(rs.getString(8).trim());
						Matriz[i][14] = infonavit;
						float banamex = Float.parseFloat(rs.getString(9).trim());
						Matriz[i][15] = banamex;
						float banorte = Float.parseFloat(rs.getString(10).trim());
						Matriz[i][16] = banorte;
						float cooperacion =  Float.parseFloat(rs.getString(11).trim());
						Matriz[i][17] = cooperacion;
						
						Object[] persecciones = getPersecciones(folio_empleado);
						boolean perseccion = Boolean.parseBoolean(persecciones[0].toString());
						float diasPerseccion = Math.round(((sueldo + bono_complemento)/7) * Float.parseFloat(persecciones[1].toString().trim()));
						float bono = Float.parseFloat(persecciones[2]+"");
						if(perseccion == true){
							Matriz[i][18] = diasPerseccion;
						}
						else {
							Matriz[i][18] = 0;
						}
						Matriz[i][19] = bono;
						
						float suma = sueldo + bono_complemento - DescuentoPrestamo - DescuentoFuenteSodas - puntualidad - descuentoDias -
									asistencia - cortes - infonavit - banamex - banorte + (cooperacion) + diasPerseccion + bono ;
						Matriz[i][20] = suma;
						
						i++;
					}
				}else{
					s = conn.createStatement();
					rs = s.executeQuery(todos);
					Matriz = new Object[getFilas(todos)][6];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = false;
						Matriz[i][1] = rs.getString(1).trim();
						Matriz[i][2] = "   "+rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][3] = "   "+rs.getString(5).trim();
						Matriz[i][4] = "0";
						Matriz[i][5] = false;
						Matriz[i][6] = 0;
						i++;
					}
				}
			}
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		    return Matriz; 

	}
	
	public static int getFilas(String qry){
		int filas=0;
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	
	public static float[] getPrestamos(int folio){
		float[] valores= new float[3];
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select cantidad, descuento, saldo from tb_prestamo where saldo > 0 and  folio_empleado="+folio);
			while(rs.next()){
				valores[0] = Float.parseFloat(rs.getString(1));
				valores[1] = Float.parseFloat(rs.getString(2));
				valores[2] = Float.parseFloat(rs.getString(3));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public static float getFuenteSodas(int folio){
		float valor= 0;
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select " +
											     "sum(tb_fuente_sodas_rh.cantidad)as Total " +
										  "from  tb_fuente_sodas_rh " +
										  "where tb_fuente_sodas_rh.status_ticket = '1' and " +
										  		 "tb_fuente_sodas_rh.status ='1' and " +
										  		 "tb_fuente_sodas_rh.folio_empleado ="+folio +" " + 
										  "group by tb_fuente_sodas_rh.nombre_completo,tb_fuente_sodas_rh.folio_empleado");
			while(rs.next()){
				valor = Float.parseFloat(rs.getString(1));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public static Object[] getPuntualidad(int folio){
		Object[] valores= new Object[4];
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select puntualidad, falta, dia_faltas, asistencia from tb_deduccion_inasistencia where status = '1' and folio_empleado="+folio);
			while(rs.next()){
				valores[0] = rs.getString(1);
				valores[1] = rs.getString(2);
				valores[2] = rs.getString(3);
				valores[3] = rs.getString(4);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public static float[] getValoresPuntualidad(){
		float[] valores= new float[2];
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select puntualidad, asistencia from tb_asistencia_puntualidad");
			while(rs.next()){
				valores[0] = Float.parseFloat(rs.getString(1));
				valores[1] = Float.parseFloat(rs.getString(2));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public static float getDescuento_Cortes(int folio){
		float valor= 0;
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select descuento from tb_diferencia_cortes where saldo > 0 and folio_empleado="+folio);
			while(rs.next()){
				valor = Float.parseFloat(rs.getString(1));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public static Object[] getPersecciones(int folio){
		String[] valores= new String[3];
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select dia_extra, dias, bono from tb_persecciones_extra where folio_empleado="+folio);
			while(rs.next()){
				valores[0] = rs.getString(1);			
				valores[1] = rs.getString(2);
				valores[2] = rs.getString(3);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
}