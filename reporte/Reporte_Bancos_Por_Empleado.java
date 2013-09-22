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
public class Reporte_Bancos_Por_Empleado extends JFrame {

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public Reporte_Bancos_Por_Empleado() {
//		try {
//			JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Bancos_Por_Empleado.jrxml");
//			JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
//			JasperViewer.viewReport(print, false);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Reporte_Bancos_Por_Empleado(int folio, String NOMBRE){
		String query = "select tb_empleado.folio as folio, "+
	   "tb_empleado.nombre + ' ' + tb_empleado.ap_paterno + ' ' + tb_empleado.ap_materno as NombreCompleto, "+
	   "tb_establecimiento.nombre as Establecimiento "+
"from tb_empleado "+
"left join tb_establecimiento on tb_establecimiento.folio = tb_empleado.establecimiento_id  "+

"where tb_empleado.folio < "+folio +"and tb_empleado.nombre='"+ NOMBRE+"'";
		Statement stmt = null;
		try{
			stmt =  new Connexion().conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
			JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\Reportes\\Silhouette.jrxml");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
			JasperPrint print = JasperFillManager.fillReport(report, new HashMap(),resultSetDataSource);
			JasperViewer.viewReport(print,false);
		}catch(Exception e){
			System.err.print("Error de reporte Bancos por Empleado"+e.getMessage());
		}
	}
	
	public static void main(String args[]){
		new Reporte_Bancos_Por_Empleado(500,"EDGAR");
	}

}
