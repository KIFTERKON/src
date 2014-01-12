package reporte;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JFrame;

import SQL.Connexion;



import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Reporte_IZAGAR_de_Movimientos_Operados extends JFrame {

	@SuppressWarnings({ "rawtypes", "unchecked" })
		public Reporte_IZAGAR_de_Movimientos_Operados(String fecha) {
			String query = "exec sp_Reporte_IZAGAR_de_Movimientos_Operados '"+fecha+"';";
			System.out.println(fecha);
			Statement stmt = null;
			try {
				stmt =  new Connexion().conexionDB_DOS().createStatement();
			    ResultSet rs = stmt.executeQuery(query);
			    
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_IZAGAR_de_Movimientos_Operados.jrxml");
				JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	
}
