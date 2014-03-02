package reporte;

import java.util.HashMap;
import SQL.Connexion;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Reporte_De_Cumpleanios_Del_Mes {

	@SuppressWarnings({ "rawtypes", "unchecked" })
		public Reporte_De_Cumpleanios_Del_Mes() {
			try {
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Cumpleanios_Del_Mes.jrxml");
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

