package izagar;

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
import reporte.Reporte_IZAGAR_de_Movimientos_Operados;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Reporte_IZAGAR_de_Movimientos_Operados extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JDateChooser c_inicio = new JDateChooser();


	

	JButton btn_generar = new JButton("Generar Reporte");

	
	public Cat_Reporte_IZAGAR_de_Movimientos_Operados(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/reporte_icon&16.png"));
		this.setTitle("Reportes IZAGAR de Movimientos Operados");
		this.panel.setBorder(BorderFactory.createTitledBorder("Movimientos Operados"));
		this.panel.add(new JLabel("Fecha:")).setBounds(110,40,100,20);
		this.panel.add(c_inicio).setBounds(150,40,140,20);

	   
		this.btn_generar.addActionListener(op_generar);
		this.panel.add(btn_generar).setBounds(110,100,180,20);

		this.cont.add(panel);
		this.setSize(400,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	ActionListener op_generar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(validar_fechas().equals("")){
				String fecha = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate());
				new Reporte_IZAGAR_de_Movimientos_Operados(fecha);
				
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
		
	};
	public String validar_fechas(){
		String error = "";
		String fechaNull = c_inicio.getDate()+"";
	    if(fechaNull.equals("null"))error+= "Fecha \n";
		return error;
	}
	
	public static  void main(String []arg){
	new Cat_Reporte_IZAGAR_de_Movimientos_Operados().setVisible(true);	
		
	}
}
