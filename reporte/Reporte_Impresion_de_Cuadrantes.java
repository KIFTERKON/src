package reporte;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JFrame;


import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import SQL.Connexion;

@SuppressWarnings("serial")
public class Reporte_Impresion_de_Cuadrantes extends JFrame {
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Reporte_Impresion_de_Cuadrantes(int folio_empleado ) {
		String query ="exec sp_Reporte_Impresion_Cuadrante_del_dia '" +folio_empleado+"'";
		Statement stmt = null;
		
		try {
			stmt =  new Connexion().conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Impresion_Cuadrante.jrxml");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
			JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}


