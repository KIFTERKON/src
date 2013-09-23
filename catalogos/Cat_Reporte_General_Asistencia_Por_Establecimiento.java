package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import reporte.Reporte_General_de_Asistencia_Por_Establecimiento;

import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class Cat_Reporte_General_Asistencia_Por_Establecimiento extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JDateChooser c_inicio = new JDateChooser();
	JDateChooser c_final = new JDateChooser();

	JButton btn_generar = new JButton("Generar Reporte");
	
	public Cat_Reporte_General_Asistencia_Por_Establecimiento(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/reporte_icon&16.png"));
		this.setTitle("Reportes General de Asistencia por Establecimiento");
		
		this.panel.setBorder(BorderFactory.createTitledBorder("Reporte General de Asistencia por Establecimiento"));
		
		this.panel.add(new JLabel("Fecha Inicio:")).setBounds(15,15,80,20);
		this.panel.add(c_inicio).setBounds(80,15,100,20);
		
		this.panel.add(new JLabel("Fecha Final:")).setBounds(15,40,80,20);
		this.panel.add(c_final).setBounds(80,40,100,20);
		
		this.panel.add(btn_generar).setBounds(20,75,120,20);
		
		this.btn_generar.addActionListener(op_generar);
		
		this.cont.add(panel);
		this.setSize(450,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	ActionListener op_generar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(validar_fechas().equals("")){
				String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 05:59:00";
				String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
				
				new Reporte_General_de_Asistencia_Por_Establecimiento(fecha_inicio, fecha_final);
				
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
			}

		}
	};
	
	public String validar_fechas(){
		String error = "";
		
		String fechainicioNull = c_inicio.getDate()+"";
		String fechafinalNull = c_final.getDate()+"";
		
		if(fechainicioNull.equals("null"))error+= "Fecha  inicio\n";
		if(fechafinalNull.equals("null"))error+= "Fecha Final\n";
		
		return error;
	}
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reporte_General_Asistencia_Por_Establecimiento().setVisible(true);
		}catch(Exception e){
			System.err.println("Error: "+e.getMessage());
		}
		
	}

}
