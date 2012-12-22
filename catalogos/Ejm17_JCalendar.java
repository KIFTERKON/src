package catalogos;

import java.awt.Container;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class Ejm17_JCalendar extends JFrame {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	JDateChooser calEjemplo1=new  JDateChooser();
	JTextField txtFecha = new JTextField();
	
    public Ejm17_JCalendar()
    {
    	
//      JCalendar calEjemplo1=new  JCalendar();
 	
    	panel.add(new JLabel("Fecha :")).setBounds(20,150,80,20);
    	panel.add(calEjemplo1).setBounds(120,150,100,20);
      
      cont.add(panel);

      this.setSize(400, 300);
    }

    public static void main(String args[]) {
        Ejm17_JCalendar obj = new Ejm17_JCalendar();
        obj.setVisible(true);
    }
}
