package izagar;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import SQL.Connexion;

import objetos.Obj_Reportes_IZAGAR;

@SuppressWarnings("serial")
public class Cat_Reporte_BMS_Apartados extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtAsignacion = new JTextField();

	JButton btnGenerar = new JButton("Generar");

	
	public Cat_Reporte_BMS_Apartados(){
		this.setTitle("Reporte de Apartados y Abonos");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/ancla.jpg"));
		int x=25, y=25, z=80;
		panel.setBorder(BorderFactory.createTitledBorder("Reporte de Apartados y Abonos en una Asignacion"));
		
		panel.add(new JLabel("Asignacion:")).setBounds(x,y,z,20);		
		panel.add(txtAsignacion).setBounds(x+z,y,z+50,20);
        
		 panel.add(btnGenerar).setBounds(100,60,80,20);
		 btnGenerar.addActionListener(opGuardar) ;
		
		cont.add(panel);
			
		this.setSize(305,180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opSalir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			
			Obj_Reportes_IZAGAR objasignacion = new Obj_Reportes_IZAGAR();
			
			objasignacion.setAsignacion(txtAsignacion.getText());
			
			if(new Obj_Reportes_IZAGAR().guardar(objasignacion)){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Reportes\\Reporte_IZAGAR_de_Apartados.jrxml");
										
					@SuppressWarnings({ "rawtypes", "unchecked" })
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexionDB_DOS());
					JasperViewer.viewReport(print, false);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}	
			}
			else{
				 
				JOptionPane.showMessageDialog(null,"Ocurrio un error al Generar el Reporte","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	};
	
//	private String validaCampos(){
//		String error="";
//		
//		if(txtValorAsistencia.getText().equals("")) 	error+= "     Valor de Asistencia\n";
//		if(txtValorPuntualidad.getText().equals("")) 	error+= "     Valor de Puntualidad\n";
//		if(txtGafete.getText().equals("")) 		error+= "     Valor de Gafete\n";		
//		return error;
//	}

	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reporte_BMS_Apartados().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
