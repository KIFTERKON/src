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
public class Reporte_Empleados_Faltantes_o_Retardo extends JFrame {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Reporte_Empleados_Faltantes_o_Retardo(int reporte,String Establecimiento) {
		
		if(reporte==1){
				String query = "exec sp_select_empleados_que_no_han_checado '"+Establecimiento+"';";
				
				Statement stmt = null;
				try {
					stmt =  new Connexion().conexion().createStatement();
				    ResultSet rs = stmt.executeQuery(query);
	
				    JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Checador_Empleados_Faltantes.jrxml");
					JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
					JasperViewer.viewReport(print, false);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		}
		
		if(reporte==2){
//			procedimiento en proceso
				String query = "exec sp_select_empleados_con_retardo '"+Establecimiento+"';";
				
				Statement stmt = null;
				try {
					stmt =  new Connexion().conexion().createStatement();
				    ResultSet rs = stmt.executeQuery(query);
//	reporte pendiente
				    JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Checador_Empleados_Con_Retardo_En_Checadas.jrxml");
					JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
					JasperViewer.viewReport(print, false);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		}
	}
}
