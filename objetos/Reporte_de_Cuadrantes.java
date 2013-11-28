package objetos;

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
public class Reporte_de_Cuadrantes extends JFrame {

	@SuppressWarnings({ "rawtypes", "unchecked" })
		public Reporte_de_Cuadrantes(String fecha_inicio, String fecha_final, int operadorEqTrabajo, String cadenaEqTrabajo,
										int operadorEmpleado, String cadenaEmpleados,
										int operadorEstablecimiento, String cadenaEstablecimiento,
										int operadorPuesto, String cadenaPuesto,
										int operadorDepartamento, String cadenaDepartamento,
										int operadorNivelCritico, String cadenaNivelCritico,
										int operadorRespuesta, String cadenaRespuesta,
										int reportePresentado
									 ) {
		
//		generar procedimiento almacenado--------------------------------------------------------------
		String query = "exec sp_reporte_dinamico_de_cuadrantes '"+fecha_inicio+"','"+fecha_final+"','"+
				  												operadorEqTrabajo+"','"+cadenaEqTrabajo+"','"+
																operadorEmpleado+"','"+cadenaEmpleados+"','"+
																operadorEstablecimiento+"','"+cadenaEstablecimiento+"','"+
																operadorPuesto+"','"+cadenaPuesto+"','"+
																operadorDepartamento+"','"+cadenaDepartamento+"','"+
																operadorNivelCritico+"','"+cadenaNivelCritico+"','"+
																operadorRespuesta+"','"+cadenaRespuesta+"','"+
																reportePresentado
																+"';";
			
			Statement stmt = null;
			try {
				stmt =  new Connexion().conexion().createStatement();
			    ResultSet rs = stmt.executeQuery(query);
			    
			    System.out.println(query);
////		crear plantilla cambiar ruta------------------------------------------------------------
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_Dinamico_de_Cuadrantes.jrxml");
				JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
}