package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Revision_Lista_Raya;

import export.exportar_excel;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Filtro_LR extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	//DECLARACION PARA CREAR UNA TABLA
	DefaultTableModel model = new DefaultTableModel(0,18){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(model);

	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	JTextField txtLR = new JTextField();
	
	
	JLabel lblImprimir = new JLabel(new ImageIcon("imagen//imprimir-32.png"));
	JLabel lblExpor = new JLabel(new ImageIcon("imagen/export_excel.png"));
	JButton btnBuscar = new JButton("Filtro");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Filtro_LR()	{
		this.setTitle("..:: Lista de pago por establecimiento ::..");
		this.lblExpor.setText("Exportar");
		this.lblImprimir.setText("Imprimir");
		
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		int largo = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		int ancho = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		
		
		campo.add(txtLR).setBounds(15,20,68,20);
		campo.add(btnBuscar).setBounds(90,20,80,20);
		campo.add(lblImprimir).setBounds(630, 10, 90, 45);
		campo.add(lblExpor).setBounds(725, 10, 90, 45);
		campo.add(getPanelTabla()).setBounds(20,60,largo-548,ancho-120);
	
		lblImprimir.addMouseListener(OpImprimir);
		lblExpor.addMouseListener(opExportar);
		btnBuscar.addActionListener(buscar);
		
		cont.add(campo);
		this.setModal(true);
		this.setSize(largo-500,ancho);
		this.setLocationRelativeTo(null);
		
	}
 
	private JScrollPane getPanelTabla()	{	
		Connection conn = new Connexion().conexion();
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
		// Creamos las columnas.
		int a = 0;
		int b = 34;
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
		tabla.getColumnModel().getColumn(a).setMaxWidth(b+5);
		tabla.getColumnModel().getColumn(a).setMinWidth(b+5);
		tabla.getColumnModel().getColumn(a+=1).setHeaderValue("");
		tabla.getColumnModel().getColumn(a).setMaxWidth((b*4)-10);
		tabla.getColumnModel().getColumn(a).setMinWidth((b*4)-10);
		
//		TAMAÑO DE FILA DEL JTABLE
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
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery("select * from tb_lista_raya "+
								" order by Establecimiento asc");
			
			String aux="";
			int cont =0;
			while (rs.next())
			{ 
			   String [] fila = new String[18];
			   
			   String nombre= 		rs.getString(4).trim();
			   String stab= 		rs.getString(5).trim();
			   float sueldo=		rs.getFloat(6);
			   float bono=			rs.getFloat(7);
			   float prestamo=		rs.getFloat(8);
			   float descuento=		rs.getFloat(9);
			   float pfinal=		rs.getFloat(10);
			   float fsod= 			rs.getFloat(11);
			   float punt=			rs.getFloat(12);
			   float falta=			rs.getFloat(13);
			   float asis= 			rs.getFloat(14);
			   float corte=			rs.getFloat(15);
			   float infon=			rs.getFloat(16);
			   
			   float banorte=	 	rs.getFloat(17);
			   float banamex=		rs.getFloat(18);
			   float ext=			rs.getFloat(19);
			   float diaE=			rs.getFloat(20);
			   
			   float pagar= 		rs.getFloat(22);
			   String obs=			rs.getString(23).trim();
			   
			   if(stab.equals(aux)){
				   
				    fila[0]  ="  "+nombre;
				    fila[1]  ="  "+sueldo;
					fila[2]  ="  "+prestamo;
					fila[3]  ="  "+descuento;
					fila[4]  ="  "+pfinal;
					fila[5]  ="  "+fsod;
					fila[6]  ="  "+punt;
					fila[7]  ="  "+falta;
					fila[8]  ="  "+asis;
					fila[9]  ="  "+corte;
					fila[10] ="  "+infon;
					fila[11] ="  "+banorte;
					fila[12] ="  "+banamex;
					fila[13] ="  "+ext;
					fila[14] ="  "+diaE;
					fila[15] ="  "+bono;
					fila[16] ="  "+pagar;
					fila[17] ="  "+obs;
					
			   }else{
				  if(cont>=1){
					  
					  	fila[0] ="";
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
					  model.addRow(fila);
					  	fila[0] ="                        "+stab;
					    fila[1]  =" SUELDO";
						fila[2]  ="   PREST";
						fila[3]  ="  DESC P.";
						fila[4]  ="   S FINAL";
						fila[5]  ="    FSOD";
						fila[6]  ="    PUNT";
						fila[7]  ="    FALTA";
						fila[8]  ="   ASIS";
						fila[9]  ="   CORTE";
						fila[10] ="   INFVIT";
						fila[11] =" BANORT";
						fila[12] =" BANAM";
						fila[13] ="     EXT";
						fila[14] ="   DIA E.";
						fila[15] ="   BONO";
						fila[16] =" A PAGAR";
						fila[17] ="                OBSERVACIONES";
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
						fila[9]  ="  "+corte;
						fila[10] ="  "+infon;
						fila[11] ="  "+banorte;
						fila[12] ="  "+banamex;
						fila[13] ="  "+ext;
						fila[14] ="  "+diaE;
						fila[15] ="  "+bono;
						fila[16] ="  "+pagar;
						fila[17] ="  "+obs;
					  aux = stab;
				  
				  }else{
					  
					  	fila[0]  ="                        "+stab;
					  	fila[1]  =" SUELDO";
						fila[2]  ="   PREST";
						fila[3]  ="  DESC P.";
						fila[4]  ="   S FINAL";
						fila[5]  ="    FSOD";
						fila[6]  ="    PUNT";
						fila[7]  ="    FALTA";
						fila[8]  ="   ASIS";
						fila[9]  ="   CORTE";
						fila[10] ="   INFVIT";
						fila[11] =" BANORT";
						fila[12] =" BANAM";
						fila[13] ="     EXT";
						fila[14] ="    DIA E.";
						fila[15] ="   BONO";
						fila[16] =" A PAGAR";
						fila[17] ="                OBSERVACIONES";
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
						fila[9]  ="  "+corte;
						fila[10] ="  "+infon;
						fila[11] ="  "+banorte;
						fila[12] ="  "+banamex;
						fila[13] ="  "+ext;
						fila[14] ="  "+diaE;
						fila[15] ="  "+bono;
						fila[16] ="  "+pagar;
						fila[17] ="  "+obs;
						
						aux = stab;
						cont++;
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
	
	ActionListener buscar = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e){
			if(txtLR.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Revision_Lista_Raya LR = new Obj_Revision_Lista_Raya();
				LR = LR.buscar(Integer.parseInt(txtLR.getText()));
				if(LR.getFolio() != 0){			
					txtLR.setText(LR.getNumero_lista()+"");
					trsfiltro.setRowFilter(RowFilter.regexFilter(txtLR.getText(), 0));
					
					txtLR.setEditable(true);
					txtLR.requestFocus();
					
				}
				else{
					JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
					
					txtLR.setEditable(true);
					txtLR.setText("");
					return;
				}
			}
		}
	};
	
	KeyListener opFiltroFolio = new KeyListener(){
		public void keyReleased(KeyEvent arg0) {
//			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
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
		new Cat_Filtro_LR().setVisible(true);
	}
}
