package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import objetos.Obj_Imprimir_LR;

import export.exportar_excel;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Imprimir_LR extends JFrame {

	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	 private static GregorianCalendar calendar = new GregorianCalendar();
	
	DefaultTableModel model = new DefaultTableModel(0,20){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(model);
	
	JLabel lblImprimir = new JLabel(new ImageIcon("imagen//imprimir-32.png"));
	JLabel lblExpor = new JLabel(new ImageIcon("imagen/export_excel.png"));
	
	public Cat_Imprimir_LR(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Print.png"));
		this.setTitle("Impresión de Lista de Raya");
		this.lblExpor.setText("Exportar");
		this.lblImprimir.setText("Imprimir");
		
		new Obj_Imprimir_LR().Imprimir();

		int largo = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		int ancho = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		
		campo.add(lblImprimir).setBounds(630, 10, 90, 45);
		campo.add(lblExpor).setBounds(725, 10, 90, 45);
		campo.add(getPanelTabla()).setBounds(20,60,largo-548,ancho-120);
	
		lblImprimir.addMouseListener(OpImprimir);
		lblExpor.addMouseListener(opExportar);
		
		cont.add(campo);

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
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
		// Creamos las columnas.
		int a = 0;
		int b = 31;
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
		tabla.getColumnModel().getColumn(a).setMaxWidth(b+9);
		tabla.getColumnModel().getColumn(a).setMinWidth(b+9);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth(b+9);
		tabla.getColumnModel().getColumn(a).setMinWidth(b+9);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth((b*4)-12);
		tabla.getColumnModel().getColumn(a).setMinWidth((b*4)-12);
		
		tabla.setRowHeight(8);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				
				lbl.setFont(new java.awt.Font("",0,7));
		
				if(row%2!=0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(214,214,214));
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
		
		DecimalFormat decimal = new DecimalFormat("#0.00");
		 
		String datos = "exec sp_select_imprimir_LR";
		
		Statement stmt = null;
		ResultSet rs;
						
		try {
			stmt = new Connexion().conexion().createStatement();
			rs = stmt.executeQuery(datos);
			
			
			String aux="";
			int cont =0;
			int contadorGeneral=0;
			float subtotal=0;
			
			float totalDeTotales=0;
			int filass = getFilas(datos);
			while (rs.next()) { 
																		
			   String [] fila = new String[20];
			   
			   String nombre = 		rs.getString(4).trim();
			   String establ = 		rs.getString(5).trim();
			   float sueldo =		rs.getFloat(6);
			   float bono =			rs.getFloat(7)+rs.getFloat(23);
			   float prestamo =		rs.getFloat(8);
			   float descuento =	rs.getFloat(9);
			   float pfinal=		rs.getFloat(10);
			   float fsod =			rs.getFloat(11);
			   float punt=			rs.getFloat(12);
			   float falta=			rs.getFloat(13);
			   float asis= 			rs.getFloat(14);
			   float gafete = 		rs.getFloat(15);
			   float corte=			rs.getFloat(16);
			   float infon=			rs.getFloat(17);
			   float pension=		rs.getFloat(18);
			   
			   float banamex=	 	rs.getFloat(19);
			   float banorte=		rs.getFloat(20);
			   float ext=			rs.getFloat(21);
			   float diaE=			rs.getFloat(22);
			   
			   float pagar= 		rs.getFloat(24);
			   String obs=			rs.getString(25).trim();
			   
			   if(establ.equals(aux)){
				   
				   fila[0]  ="  "+nombre;
				   fila[1]  ="  "+sueldo;
				   
				    	if(prestamo==0.0){fila[2]  ="";}	else{fila[2]  ="  "+prestamo;}
				    	if(descuento==0.0){fila[3] ="";}	else{fila[3]  ="  "+descuento;}	
				    	if(pfinal==0.0){fila[4]  ="";}		else{fila[4]  ="  "+pfinal;}
				    	if(fsod==0.0){fila[5]  ="";}		else{fila[5]  ="  "+fsod;}
				    	if(punt==0.0){fila[6]  ="";}		else{fila[6]  ="  "+punt;}
				    	if(falta==0.0){fila[7]  ="";}		else{fila[7]  ="  "+falta;}
				    	if(asis==0.0){fila[8]  ="";}		else{fila[8]  ="  "+asis;}
				    	if(gafete == 0.0){fila[9] = "";}    else{fila[9]  ="  "+gafete;}
				    	
				    	if(corte==0.0){fila[10]  ="";}		else{fila[10]  ="  "+corte;}
				    	if(infon==0.0){fila[11] ="";}		else{fila[11] ="  "+infon;}
				    	if(pension==0.0){fila[12] ="";}		else{fila[12] ="  "+pension;}
				    	if(banamex==0.0){fila[13] ="";}		else{fila[13] ="  "+banamex;}
				    	if(banorte==0.0){fila[14] ="";}		else{fila[14] ="  "+banorte;}
				    	if(ext==0.0){fila[15] ="";}			else{fila[15] ="  "+ext;}
				    	if(diaE==0.0){fila[16] ="";}		else{fila[16] ="  "+diaE;}
				    	if(bono==0.0){fila[17] ="";}		else{fila[17] ="  "+bono;}
				    	
				    fila[18] ="  "+decimal.format(pagar);
					fila[19] ="  "+obs;
					
					cont=cont+1;
					
					subtotal=subtotal+=pagar;
					
					totalDeTotales = totalDeTotales+=pagar;
					
					int filasTotales= filass+((contadorGeneral*3)-2);
										
					if(filasTotales==tabla.getRowCount()+1){
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
						fila[17] ="  TOTAL:";
						fila[18] ="  "+decimal.format(subtotal);
						fila[19] ="";
						
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
						fila[17] ="  TOTAL LR:";
						fila[18] ="  "+decimal.format(totalDeTotales);
						fila[19] ="";
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
						fila[16] ="";
						fila[17] ="  TOTAL:";
						fila[18] ="  "+decimal.format(subtotal);
						fila[19] ="";
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
						fila[19] ="";
					  model.addRow(fila);
					  	fila[0]  ="                        "+establ;
					  	fila[1]  ="SUELDO";
						fila[2]  ="  PREST";
						fila[3]  =" DESC P.";
						fila[4]  =" S FINAL";
						fila[5]  ="   FSOD";
						fila[6]  ="   PUNT";
						fila[7]  ="   FALTA";
						fila[8]  ="  ASIST";
						fila[9]  ="  GAFETE";
						fila[10]  ="  CORTE";
						fila[11] ="  INFVIT";
						fila[12] =" PENSION";
						fila[13] ="BANAM";
						fila[14] ="BANORT";
						fila[15] ="   EXT";
						fila[16] ="  DIA E.";
						fila[17] ="  BONO";
						fila[18] ="A PAGAR";
						fila[19] ="            OBSERVACIONES";
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
					    	
					    	if(gafete==0.0){fila[9]  ="";}		else{fila[9]  ="  "+gafete;}
					    	if(corte==0.0){fila[10]  ="";}		else{fila[10]  ="  "+corte;}
					    	if(infon==0.0){fila[11] ="";}		else{fila[11] ="  "+infon;}
					    	if(pension==0.0){fila[12] ="";}		else{fila[12] ="  "+pension;}
					    	if(banamex==0.0){fila[13] ="";}		else{fila[13] ="  "+banamex;}
					    	if(banorte==0.0){fila[14] ="";}		else{fila[14] ="  "+banorte;}
					    	if(ext==0.0){fila[15] ="";}			else{fila[15] ="  "+ext;}
					    	if(diaE==0.0){fila[16] ="";}		else{fila[16] ="  "+diaE;}
					    	if(bono==0.0){fila[17] ="";}		else{fila[17] ="  "+bono;}

					    	fila[18] ="  "+decimal.format(pagar);
						fila[19] ="  "+obs;
						
					  aux = establ;
					  cont=cont+4;
					  
					  subtotal=pagar;
					  totalDeTotales = totalDeTotales+=pagar;
					  					
				  }else{
					  
					  	fila[0]  ="                        "+establ;
					  	fila[1]  ="SUELDO";
						fila[2]  ="  PREST";
						fila[3]  =" DESC P.";
						fila[4]  =" S FINAL";
						fila[5]  ="   FSOD";
						fila[6]  ="   PUNT";
						fila[7]  ="   FALTA";
						fila[8]  ="  ASIST";
						fila[9]  ="  GAFETE";
						fila[10]  ="  CORTE";
						fila[11] ="  INFVIT";
						fila[12] =" PENSION";
						fila[13] ="BANAM";
						fila[14] ="BANORT";
						fila[15] ="   EXT";
						fila[16] ="  DIA E.";
						fila[17] ="  BONO";
						fila[18] ="A PAGAR";
						fila[19] ="            OBSERVACIONES";
						
					model.addRow(fila);
					 fila[0]  ="  "+nombre;
					    fila[1]  ="  "+sueldo;
						fila[2]  ="  "+prestamo;
						fila[3]  ="  "+descuento;
						fila[4]  ="  "+pfinal;
						fila[5]  ="  "+fsod;
						fila[6]  ="  "+punt;
						fila[7]  ="  "+falta;
						fila[8]  ="  "+asis;
						fila[9]  ="  "+gafete;
						fila[10]  ="  "+corte;
						fila[11] ="  "+infon;
						fila[12] ="  "+pension;
						fila[13] ="  "+banamex;
						fila[14] ="  "+banorte;
						fila[15] ="  "+ext;
						fila[16] ="  "+diaE;
						fila[17] ="  "+bono;
						fila[18] ="  "+pagar;
						fila[19] ="  "+obs;
						
						aux = establ;
						cont=cont+1;
						subtotal=subtotal+=pagar;
						
						totalDeTotales = totalDeTotales+=pagar;
						
					
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
	
	public static Date restarDias(Date date,int dias){
	      calendar.setGregorianChange(date);
	      calendar.set(GregorianCalendar.DAY_OF_YEAR, calendar.get(GregorianCalendar.DAY_OF_YEAR)-dias);
	      
	      return calendar.getTime();
	}
	
	MouseListener OpImprimir = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
		
			MessageFormat encabezado = new MessageFormat("Lista de Raya pag.[{0,number,integer}] del "+getFechaInicial()+" al "+getFechaFinal());
			try {
			tabla.print(JTable.PrintMode.NORMAL, encabezado, null);
			
			} catch (java.awt.print.PrinterException e1) {
				JOptionPane.showMessageDialog(null, "No se encontro la impresora!","Aviso",JOptionPane.WARNING_MESSAGE);
			}
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	
	public String getFechaInicial(){
		String valor = "";
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("exec SP_FECHA_INICIAL_LISTA_RAYA");
			while(rs.next()){
				valor = rs.getString(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public String getFechaFinal(){
		String valor = "";
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select top 1(fecha_final) from tb_pre_listaraya");
			while(rs.next()){
				valor = rs.getString(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
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
	public static void main(String [] arg){
		new Cat_Imprimir_LR().setVisible(true);
	}
}