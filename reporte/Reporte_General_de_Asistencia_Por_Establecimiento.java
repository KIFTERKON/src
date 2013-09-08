package reporte;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.UIManager;

import SQL.Connexion;



import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Reporte_General_de_Asistencia_Por_Establecimiento extends JFrame {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Reporte_General_de_Asistencia_Por_Establecimiento() {
		try {
			JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_General_de_Asistencia_Por_Establecimiento.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Reporte_General_de_Asistencia_Por_Establecimiento();
		}catch(Exception e){
			
		}
		
	}
}
