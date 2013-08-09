package catalogos;

import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;

import objetos.Obj_Establecimiento;

@SuppressWarnings("serial")
public class Cat_Root_Lista_Raya extends JFrame {
	
	public Container cont = getContentPane();
	public JLayeredPane panel = new JLayeredPane();
	
	public JToolBar menu_toolbar = new JToolBar();
	
	public JTextField txtFolio = new JTextField();
	public JTextField txtNombre_Completo = new JTextField();
	
	public String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	
	public JDateChooser txtCalendario = new JDateChooser();
	
	public JButton btn_guardar = new JButton(new ImageIcon("Iconos/save_icon&16.png"));
	public JButton btn_imprimir = new JButton(new ImageIcon("Iconos/print_icon&16.png"));
	public JButton btn_refrescar = new JButton(new ImageIcon("Iconos/refresh_icon&16.png"));
	public JButton btn_nomina = new JButton(new ImageIcon("Iconos/nomina_icon&16.png"));
	public JButton btn_generar = new JButton(new ImageIcon("Iconos/generar_icon&16.png"));
	//se agrega el nombre de la variable boton y se le agrega el icono
	public JButton btn_lista_raya_pasadas = new JButton(new ImageIcon("Iconos/consulta_lista_raya_icon&16.png"));

	public Cat_Root_Lista_Raya(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/list_bullets_icon&16.png"));
		
		this.panel.add(menu_toolbar).setBounds(0,0,200,25);
		
		this.panel.add(txtFolio).setBounds(30,35,85,20);
		this.panel.add(txtNombre_Completo).setBounds(116,35,310,20);
		this.panel.add(cmbEstablecimientos).setBounds(427,35,160,20);
		this.panel.add(txtCalendario).setBounds(588,35,90,20);
		this.txtCalendario.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		this.menu_toolbar.add(btn_guardar);
			this.btn_guardar.setToolTipText("Guardar");
		this.menu_toolbar.add(btn_imprimir);
			this.btn_imprimir.setToolTipText("Imprimir");
		this.menu_toolbar.add(btn_refrescar);
			this.btn_refrescar.setToolTipText("Refrescar");
		this.menu_toolbar.add(btn_nomina);
			this.btn_nomina.setToolTipText("Totales de Nómina");
		this.menu_toolbar.add(btn_generar);
			this.btn_generar.setToolTipText("Generar");
//	se le agrega la etiqueta al boton		
			this.menu_toolbar.add(btn_lista_raya_pasadas);
			this.btn_lista_raya_pasadas.setToolTipText("Consulta de Lista de Raya Pasadas");
		
		
		this.menu_toolbar.setEnabled(false);
		
	}

}
