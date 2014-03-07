package reporte;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import SQL.Connexion;

import objetos.Obj_Establecimiento;

@SuppressWarnings("serial")
public class Reporte_de_Plantilla_de_Personal_con_Horario extends JFrame{

	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	JButton btngenerar = new JButton("Generar");
	
	public Reporte_de_Plantilla_de_Personal_con_Horario(){
	
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/user_icon&16.png"));
		this.setTitle("Reporte de Plantilla");
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		panel.setBorder(BorderFactory.createTitledBorder("Plantilla de Personal con Horario"));
		
		panel.add(cmbEstablecimiento).setBounds(10, 50, 220, 20);
		panel.add(btngenerar).setBounds(70, 120, 100, 20);
		
		cont.add(panel);
		
		btngenerar.addActionListener(opGenerar);
		
		this.setSize(250, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opGenerar = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			String query = "exec sp_Reporte_de_Plantilla_de_Personal_con_Horario '"+cmbEstablecimiento.getSelectedItem()+"';";
				Statement stmt = null;
				try {
					stmt =  new Connexion().conexion().createStatement();
				    ResultSet rs = stmt.executeQuery(query);
				    
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_de_Plantilla_de_Personal_con_Horario.jrxml");
					JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
					@SuppressWarnings({ "rawtypes", "unchecked" })
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
					JasperViewer.viewReport(print, false);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
			
		
	};
	
	public static void main(String [] arg){
		new Reporte_de_Plantilla_de_Personal_con_Horario().setVisible(true);
	}
}
