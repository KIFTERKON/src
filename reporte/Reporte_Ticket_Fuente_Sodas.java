package reporte;

import java.awt.Desktop;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import SQL.Connexion;

@SuppressWarnings("serial")
public class Reporte_Ticket_Fuente_Sodas extends JFrame {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Reporte_Ticket_Fuente_Sodas(String clave) {
		
		String query = "exec sp_reporte_ultimo_ticket_por_empleado '"+clave+"';";
		Statement stmt = null;
		
		try {
			stmt =  new Connexion().conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
//			JasperReport reporte=(JasperReport) JRLoader.loadObject(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Ticket_Fuente_de_Sodas.jasper"); 
			JasperReport reporte = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Ticket_Fuente_de_Sodas.jrxml");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
			JasperPrint jasperprint= JasperFillManager.fillReport(reporte, new HashMap(), resultSetDataSource);
//			JasperViewer.viewReport(print, false);
			JasperExportManager.exportReportToPdfFile( jasperprint, "C:/SCOI/reporte.pdf");
			
			imprimir("C:/SCOI/reporte.pdf");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Problemas al generar el reporte. \n Detalles: " + e);
			Logger.getLogger(Reporte_Ticket_Fuente_Sodas.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@SuppressWarnings({ "unused" })
	public void imprimir(String rutaDoc) throws IOException
	{
	       PrinterJob job = PrinterJob.getPrinterJob();
	       job.printDialog();
	       String impresora=job.getPrintService().getName();

	       //ESTE ES TU CÓDIGO
	       Desktop desktop = Desktop.getDesktop();
	       File fichero = new File(rutaDoc);
	      
	        
		              try{
		            	  Runtime R = Runtime.getRuntime();
		            	  
			                  Process pr = R.exec("Rundll32 printui.dll,PrintUIEntry /y /n \""+"EPSON TM-T88IV Receipt"+"\"");
			                  desktop.print(fichero);
//			                  desktop.print(fichero);
			                
		                  }catch(Exception ex){
		                	  System.out.println("Ha ocurrido un error al ejecutar el comando. Error: "+ex);
		                  }
	}


//	private long close() {
//		Runtime R = Runtime.getRuntime();
//		try {
//			R.exec("taskkill /f /im NitroPDF.exe");
//		} catch (Exception e2){}
//		return 0;
//	}


	
	}










//--------------------------------------------------------------------------------------------------------------------------------
//JasperViewer visor=new JasperViewer(jasperprint,false); 
//visor.setTitle("Geniz Reportes - GSF"); 
//visor.setVisible(false); 




//Runtime R = Runtime.getRuntime();
//	try {
//		R.exec("taskkill /f /im NitroPDF.exe");
//	} catch (Exception e2){}
//	}




//public void imprimir(String rutaDoc)
//{
//       PrinterJob job = PrinterJob.getPrinterJob();
//       job.printDialog();
//       String impresora=job.getPrintService().getName();
//
//       //ESTE ES TU CÓDIGO
//       java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
//       java.io.File fichero = new java.io.File(rutaDoc);
//       if (desktop.isSupported(Desktop.Action.PRINT)){
//            try {
//	              try{
//	                 Process pr = Runtime.getRuntime().exec("Rundll32 printui.dll,PrintUIEntry /y /n \""+impresora+"\"");
//	                  }catch(Exception ex){
//	                    System.out.println("Ha ocurrido un error al ejecutar el comando. Error: "+ex);
//	                  }
//	            desktop.print(fichero);
//	           } catch (Exception e){
//					System.out.print("El sistema no permite imprimir usando la clase Desktop");
//					e.printStackTrace();
//				}
//       }
//}




//en contructor-------------------------------------------------------------

//try{
//	JasperReport jasperReporte;
//	HashMap hm = new HashMap ();
//	hm.put("Str", "xxxx");
//
//	jasperReporte = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Ticket_Fuente_de_Sodas.jrxml");
//	JasperPrint print = JasperFillManager.fillReport(jasperReporte, hm, resultSetDataSource);
//	byte[] bytes = JasperExportManager.exportReportToPdf(print);
//	PrinterJob printJob = PrinterJob.getPrinterJob();
//
////	// Asigno el tamaño del papel (A4)
////	Paper paper = new Paper();
////	paper.setSize(595, 842);
////	paper.setImageableArea(0, 0, 595, 842);
////	PageFormat pf = printJob.defaultPage();
////	pf.setPaper(paper);
////	paper.setImageableArea(0, 0, 595, 842);
//	
////	printJob.setCopies(2);
//	
//	pf.setPaper(paper);
////paper.setImageableArea(0, 0, 80, 297);
//
//	// Cargo el PDF para imprimir
//	PdfDecoder pdf = null;
//	pdf = new PdfDecoder(true);
//	
////	pdf.openPdfArray(bytes);
//		pdf.setPageFormat(pf);
//
//	// Mando imprimir
//	//printJob.setPageable(pdf);
//	if(printJob.printDialog()) {
//	printJob.setPrintable(pdf);
//	printJob.print();
//	} 
//	}catch (Exception e) {
//	}       
    