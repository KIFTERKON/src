package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import export.exportar_excel;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Imprimir_LR extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	//DECLARACION PARA CREAR UNA TABLA
	DefaultTableModel model = new DefaultTableModel(0,19){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(model);
	
	JLabel lblImprimir = new JLabel(new ImageIcon("imagen//imprimir-32.png"));
	JLabel lblExpor = new JLabel(new ImageIcon("imagen/export_excel.png"));
	
	public Cat_Imprimir_LR()	{
		this.setTitle("..:: Analisis de Lista de Raya ::..");
		this.lblExpor.setText("Exportar");
		this.lblImprimir.setText("Imprimir");

		int largo = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		int ancho = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		
		campo.add(lblImprimir).setBounds(630, 10, 90, 45);
		campo.add(lblExpor).setBounds(725, 10, 90, 45);
		campo.add(getPanelTabla()).setBounds(20,60,largo-548,ancho-120);
	
		lblImprimir.addMouseListener(OpImprimir);
		lblExpor.addMouseListener(opExportar);
		
		cont.add(campo);
		this.setModal(true);
		this.setSize(largo-500,ancho);
		this.setLocationRelativeTo(null);
		
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
 
	private JScrollPane getPanelTabla()	{	
		Connection conn = new Connexion().conexion();
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
		// Creamos las columnas.
		int a = 0;
		int b = 32;
		tabla.getColumnModel().getColumn(a).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(140);
		tabla.getColumnModel().getColumn(a).setMinWidth(140);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b);
		tabla.getColumnModel().getColumn(a).setMinWidth(b);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b+5);
		tabla.getColumnModel().getColumn(a).setMinWidth(b+5);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth((b*4)-15);
		tabla.getColumnModel().getColumn(a).setMinWidth((b*4)-15);
		
//		TAMAÑO DE FILA DEL JTABLE
		tabla.setRowHeight(8);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				
				lbl.setFont(new java.awt.Font("",0,7));
		
//				if(row%2!=0){
//						lbl.setOpaque(true); 
//						lbl.setBackground(new java.awt.Color(214,214,214));
//				} 
			return lbl; 
			} 
		}; 
						tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
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
						
						String datos = "select * from tb_imprimir_lista_raya "+
											" order by Establecimiento asc";
		
		Statement stmt = null;
		ResultSet rs;
		Connexion con = new Connexion();
						
		try {
			stmt = con.conexion().createStatement();
			rs = stmt.executeQuery(datos);
			
			
			String aux="";
			int cont =0;
			int contadorGeneral=0;
			float subtotal=0;
			int filass = getFilas(datos);
			while (rs.next())
			{ 
			
				DecimalFormat decimal = new DecimalFormat("#0.00");
				
			   String [] fila = new String[19];
			   
			   String nombre= 		rs.getString(4).trim();
			   String stab= 		rs.getString(5).trim();
			   float sueldo=		rs.getFloat(6);
			   float bono=			rs.getFloat(7)+rs.getFloat(22);
			   float prestamo=		rs.getFloat(8);
			   float descuento=		rs.getFloat(9);
			   float pfinal=		rs.getFloat(10);
			   float fsod= 			rs.getFloat(11);
			   float punt=			rs.getFloat(12);
			   float falta=			rs.getFloat(13);
			   float asis= 			rs.getFloat(14);
			   float corte=			rs.getFloat(15);
			   float infon=			rs.getFloat(16);
			   float pension=		rs.getFloat(17);
			   
			   float banamex=	 	rs.getFloat(18);
			   float banorte=		rs.getFloat(19);
			   float ext=			rs.getFloat(20);
			   float diaE=			rs.getFloat(21);
			   
			   float pagar= 		rs.getFloat(23);
			   String obs=			rs.getString(24).trim();
			   
			   if(stab.equals(aux)){
				   
				   fila[0]  ="  "+nombre;
				   fila[1]  ="  "+sueldo;
				   
				    	if(prestamo==0.0){fila[2]  ="";}	else{fila[2]  ="  "+prestamo;}
				    	if(descuento==0.0){fila[3] ="";}	else{fila[3]  ="  "+descuento;}	
				    	if(pfinal==0.0){fila[4]  ="";}		else{fila[4]  ="  "+pfinal;}
				    	if(fsod==0.0){fila[5]  ="";}		else{fila[5]  ="  "+fsod;}
				    	if(punt==0.0){fila[6]  ="";}		else{fila[6]  ="  "+punt;}
				    	if(falta==0.0){fila[7]  ="";}		else{fila[7]  ="  "+falta;}
				    	if(asis==0.0){fila[8]  ="";}		else{fila[8]  ="  "+asis;}
				    	if(corte==0.0){fila[9]  ="";}		else{fila[9]  ="  "+corte;}
				    	if(infon==0.0){fila[10] ="";}		else{fila[10] ="  "+infon;}
				    	if(pension==0.0){fila[11] ="";}		else{fila[11] ="  "+pension;}
				    	if(banamex==0.0){fila[12] ="";}		else{fila[12] ="  "+banamex;}
				    	if(banorte==0.0){fila[13] ="";}		else{fila[13] ="  "+banorte;}
				    	if(ext==0.0){fila[14] ="";}			else{fila[14] ="  "+ext;}
				    	if(diaE==0.0){fila[15] ="";}		else{fila[15] ="  "+diaE;}
				    	if(bono==0.0){fila[16] ="";}		else{fila[16] ="  "+bono;}
				    	
				    fila[17] ="  "+decimal.format(pagar);
					fila[18] ="  "+obs;
					
					cont=cont+1;
					
					subtotal=subtotal+=pagar;
					
					int filasTotales= filass+((contadorGeneral*3)-2);
					
					System.out.println("filas. "+filass);
					System.out.println("ContG. "+contadorGeneral);
					System.out.println("filasT. "+filasTotales);
					System.out.println("Tabla. "+(tabla.getRowCount()+1));
					
					if(filasTotales-1==tabla.getRowCount()+1){
						model.addRow(fila);

						fila[0]  ="";
					  	fila[1]  ="";
						fila[2]  ="";
						fila[3]  ="";
						fila[4]  ="";
						fila[5]  ="";
						fila[6]  ="";
						fila[7]  ="";
						fila[8]  ="";
						fila[9]  ="";
						fila[10] ="";
						fila[11] ="";
						fila[12] ="";
						fila[13] ="";
						fila[14] ="";
						fila[15] ="";
						fila[16] ="  TOTAL:";
						fila[17] ="  "+decimal.format(subtotal);
						fila[18] ="";
					}
					
			   }else{
				   contadorGeneral++;
				  if(cont>=1){
					  
					 
					  	fila[0]  ="";
					  	fila[1]  ="";
						fila[2]  ="";
						fila[3]  ="";
						fila[4]  ="";
						fila[5]  ="";
						fila[6]  ="";
						fila[7]  ="";
						fila[8]  ="";
						fila[9]  ="";
						fila[10] ="";
						fila[11] ="";
						fila[12] ="";
						fila[13] ="";
						fila[14] ="";
						fila[15] ="";
						fila[16] ="  TOTAL:";
						fila[17] ="  "+decimal.format(subtotal);
						fila[18] ="";
					model.addRow(fila);
					  	fila[0]  ="";
					    fila[1]  ="";
						fila[2]  ="";
						fila[3]  ="";
						fila[4]  ="";
						fila[5]  ="";
						fila[6]  ="";
						fila[7]  ="";
						fila[8]  ="";
						fila[9]  ="";
						fila[10] ="";
						fila[11] ="";
						fila[12] ="";
						fila[13] ="";
						fila[14] ="";
						fila[15] ="";
						fila[16] ="";
						fila[17] ="";
						fila[18] ="";
						
					  model.addRow(fila);
					  	fila[0]  ="                        "+stab;
					  	fila[1]  ="SUELDO";
						fila[2]  ="  PREST";
						fila[3]  =" DESC P.";
						fila[4]  =" S FINAL";
						fila[5]  ="   FSOD";
						fila[6]  ="   PUNT";
						fila[7]  ="   FALTA";
						fila[8]  ="  ASIST";
						fila[9]  ="  CORTE";
						fila[10] ="  INFVIT";
						fila[11] =" PENSION";
						fila[12] ="BANAM";
						fila[13] ="BANORT";
						fila[14] ="   EXT";
						fila[15] ="  DIA E.";
						fila[16] ="  BONO";
						fila[17] ="A PAGAR";
						fila[18] ="            OBSERVACIONES";
					 model.addRow(fila);
					 	fila[0]  ="  "+nombre;
					    fila[1]  ="  "+sueldo;
					    
					    	if(prestamo==0.0){fila[2]  ="";}	else{fila[2]  ="  "+prestamo;}
					    	if(descuento==0.0){fila[3] ="";}	else{fila[3]  ="  "+descuento;}	
					    	if(pfinal==0.0){fila[4]  ="";}		else{fila[4]  ="  "+pfinal;}
					    	if(fsod==0.0){fila[5]  ="";}		else{fila[5]  ="  "+fsod;}
					    	if(punt==0.0){fila[6]  ="";}		else{fila[6]  ="  "+punt;}
					    	if(falta==0.0){fila[7]  ="";}		else{fila[7]  ="  "+falta;}
					    	if(asis==0.0){fila[8]  ="";}		else{fila[8]  ="  "+asis;}
					    	if(corte==0.0){fila[9]  ="";}		else{fila[9]  ="  "+corte;}
					    	if(infon==0.0){fila[10] ="";}		else{fila[10] ="  "+infon;}
					    	if(pension==0.0){fila[11] ="";}		else{fila[11] ="  "+pension;}
					    	if(banamex==0.0){fila[12] ="";}		else{fila[12] ="  "+banamex;}
					    	if(banorte==0.0){fila[13] ="";}		else{fila[13] ="  "+banorte;}
					    	if(ext==0.0){fila[14] ="";}			else{fila[14] ="  "+ext;}
					    	if(diaE==0.0){fila[15] ="";}		else{fila[15] ="  "+diaE;}
					    	if(bono==0.0){fila[16] ="";}		else{fila[16] ="  "+bono;}

					    	fila[17] ="  "+decimal.format(pagar);
						fila[18] ="  "+obs;
						
					  aux = stab;
					  cont=cont+4;
					  
					  subtotal = pagar;
					  
					   System.out.println("filas. "+filass);
						System.out.println("ContG. "+contadorGeneral);
						System.out.println("Tabla. "+(tabla.getRowCount()+1));
				  }else{
					  
						fila[0]  ="                        "+stab;
					  	fila[1]  ="SUELDO";
						fila[2]  ="  PREST";
						fila[3]  =" DESC P.";
						fila[4]  =" S FINAL";
						fila[5]  ="   FSOD";
						fila[6]  ="   PUNT";
						fila[7]  ="   FALTA";
						fila[8]  ="  ASIST";
						fila[9]  ="  CORTE";
						fila[10] ="  INFVIT";
						fila[11] =" PENSION";
						fila[12] ="BANAM";
						fila[13] ="BANORT";
						fila[14] ="   EXT";
						fila[15] ="  DIA E.";
						fila[16] ="  BONO";
						fila[17] ="A PAGAR";
						fila[18] ="            OBSERVACIONES";
						
//					model.addRow(fila);
//					 fila[0]  ="  "+nombre;
//					    fila[1]  ="  "+sueldo;
//						fila[2]  ="  "+prestamo;
//						fila[3]  ="  "+descuento;
//						fila[4]  ="  "+pfinal;
//						fila[5]  ="  "+fsod;
//						fila[6]  ="  "+punt;
//						fila[7]  ="  "+falta;
//						fila[8]  ="  "+asis;
//						fila[9]  ="  "+corte;
//						fila[10] ="  "+infon;
//						fila[11] ="  "+pension;
//						fila[12] ="  "+banamex;
//						fila[13] ="  "+banorte;
//						fila[14] ="  "+ext;
//						fila[15] ="  "+diaE;
//						fila[16] ="  "+bono;
//						fila[17] ="  "+pagar;
//						fila[18] ="  "+obs;
//						
						aux = stab;
						cont=cont+1;
						
						   System.out.println("filas. "+filass);
							System.out.println("ContG. "+contadorGeneral);
							System.out.println("Tabla. "+(tabla.getRowCount()+1));
				  }
			   }
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	MouseListener opExportar = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			try {
				Calendar c = new GregorianCalendar();
				
				String dia = c.get(Calendar.DATE)+"";
				String mes = (c.get(Calendar.MONTH)+1)+"";
				String anio = c.get(Calendar.YEAR)+"";
				
				if(dia.length()==1){
					if(mes.length()==1){
						String nombre = "Lista de Raya [0"+dia+"-0"+mes+"-"+anio+"]";
						
						 List<JTable> tb = new ArrayList<JTable>();
				            List<String> nom = new ArrayList<String>();
				            tb.add(tabla);
				            nom.add("LISTA");
				            
				            exportar_excel excelExporter = new exportar_excel(tb, new File(nombre+".xls"), nom);
				            if (excelExporter.export()) {
				                JOptionPane.showMessageDialog(null, "DATOS EXPORTADOS CON EXITO!");
				            	Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+nombre+".xls");
				            }
				            
					}else{
						String nombre = "Lista de Raya [0"+dia+"-"+mes+"-"+anio+"]";
						
						 List<JTable> tb = new ArrayList<JTable>();
				            List<String> nom = new ArrayList<String>();
				            tb.add(tabla);
				            nom.add("LISTA");
				            
				            exportar_excel excelExporter = new exportar_excel(tb, new File(nombre+".xls"), nom);
				            if (excelExporter.export()) {
				                JOptionPane.showMessageDialog(null, "DATOS EXPORTADOS CON EXITO!");
				            	Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+nombre+".xls");
				            }
					}
					
				}else{
					if(mes.length()==1){
						String nombre = "Lista de Raya ["+dia+"-0"+mes+"-"+anio+"]";
						
						 List<JTable> tb = new ArrayList<JTable>();
				            List<String> nom = new ArrayList<String>();
				            tb.add(tabla);
				            nom.add("LISTA");
				            
				            exportar_excel excelExporter = new exportar_excel(tb, new File(nombre+".xls"), nom);
				            if (excelExporter.export()) {
				                JOptionPane.showMessageDialog(null, "DATOS EXPORTADOS CON EXITO!");
				            	Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+nombre+".xls");
				            }
				            
					}else{
						String nombre = "Lista de Raya ["+dia+"-"+mes+"-"+anio+"]";
						
						 List<JTable> tb = new ArrayList<JTable>();
				            List<String> nom = new ArrayList<String>();
				            tb.add(tabla);
				            nom.add("LISTA");
				            
				            exportar_excel excelExporter = new exportar_excel(tb, new File(nombre+".xls"), nom);
				            if (excelExporter.export()) {
				                JOptionPane.showMessageDialog(null, "DATOS EXPORTADOS CON EXITO!");
				            	Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+nombre+".xls");
				            }
					}
					
				}
				
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	
	MouseListener OpImprimir = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			MessageFormat encabezado = new MessageFormat("Lista de Raya pag.[{0,number,integer}]");
			try {
//			tabla.print(JTable.PrintMode.FIT_WIDTH, encabezado, null);
			tabla.print(JTable.PrintMode.NORMAL, encabezado, null);
			
			} catch (java.awt.print.PrinterException e1) {
				JOptionPane.showMessageDialog(null, "No se encontro la impresora!","Aviso",JOptionPane.WARNING_MESSAGE);
//				System.err.format("No se puede imprimir %s%n", e1.getMessage());
			}
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	
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
			
		    // VERIFICAR SI LA TECLA PULSADA NO ES UN DIGITO
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
	public static void main(String [] arg){
		new Cat_Imprimir_LR().setVisible(true);
	}
}