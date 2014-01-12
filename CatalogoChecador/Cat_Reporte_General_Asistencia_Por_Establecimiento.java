package CatalogoChecador;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import objetos.Obj_Departamento;
import objetos.Obj_Establecimiento;

import reporte.Reporte_General_de_Asistencia_Por_Establecimiento;
import reporte.Reporte_General_de_Asistencia_Por_Establecimiento_Mas_Registros_Faltantes;
import reporte.Reporte_de_Permisos_a_Empleados;

import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class Cat_Reporte_General_Asistencia_Por_Establecimiento extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JDateChooser c_inicio = new JDateChooser();
	JDateChooser c_final = new JDateChooser();
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	String departamento[] = new Obj_Departamento().Combo_Departamento();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbDepartamento = new JComboBox(departamento);

	JButton btn_generar_Asistencia = new JButton("Reporte de Asistencia Sin Faltas");
	JButton btn_generar_Completo = new JButton  ("Reporte de Asistencia Completo");
	JButton btn_generar_Permisos = new JButton  ("Reporte de Permisos a Empleados");
	
	public Cat_Reporte_General_Asistencia_Por_Establecimiento(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/reporte_icon&16.png"));
		this.setTitle("Reportes de Asistencia");
		this.panel.setBorder(BorderFactory.createTitledBorder("Reportes de Asistencia"));
		this.panel.add(new JLabel("Fecha Inicio:")).setBounds(15,25,100,20);
		this.panel.add(c_inicio).setBounds(80,25,100,20);
		this.panel.add(new JLabel("Fecha Final:")).setBounds(15,55,100,20);
		this.panel.add(c_final).setBounds(80,55,100,20);
	    this.panel.add(new JLabel("Establecimiento:")).setBounds(200,25,150,20);
		this.panel.add(cmbEstablecimiento).setBounds(280,25,170,20);
		this.panel.add(new JLabel("Departamento:")).setBounds(200,55,150,20);
		this.panel.add(cmbDepartamento).setBounds(280,55,170,20);
		this.btn_generar_Asistencia.addActionListener(op_generar);
		this.btn_generar_Completo.addActionListener(op_generar_cregistros);
		this.btn_generar_Permisos.addActionListener(op_generar_permisos);
		this.panel.add(btn_generar_Asistencia).setBounds(140,100,210,20);
		this.panel.add(btn_generar_Completo).setBounds(140,130,210,20);
		this.panel.add(btn_generar_Permisos).setBounds(140,160,210,20);
		this.cont.add(panel);
		this.setSize(490,270);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	ActionListener op_generar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(validar_fechas().equals("")){
				String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
				String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
				String Establecimiento = cmbEstablecimiento.getSelectedItem().toString();
				
				if(c_inicio.getDate().before(c_final.getDate())){
					new Reporte_General_de_Asistencia_Por_Establecimiento(fecha_inicio, fecha_final,Establecimiento);
				}else{
					JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
		
	};
	ActionListener op_generar_cregistros = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(validar_fechas().equals("")){
				String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
				String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
				String Establecimiento = cmbEstablecimiento.getSelectedItem().toString();

				if(c_inicio.getDate().before(c_final.getDate())){
					new Reporte_General_de_Asistencia_Por_Establecimiento_Mas_Registros_Faltantes(fecha_inicio, fecha_final,Establecimiento);
				}else{
					JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
	};
	ActionListener op_generar_permisos = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(validar_fechas().equals("")){
				String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
				String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
				String Establecimiento = cmbEstablecimiento.getSelectedItem().toString();
				
				if(c_inicio.getDate().before(c_final.getDate())){
					new Reporte_de_Permisos_a_Empleados(fecha_inicio, fecha_final,Establecimiento);
				}else{
					JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
				return;
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

}
