package catalogos;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import objetos.Obj_Deduccion_Asistencia;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Deduccion_Asistencia extends JDialog {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Object[][] Tabla = getTabla();
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Puntualidad", "Falta", "Dias Falta", "Asistencia" }
			){
	     Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Object.class,
	    	java.lang.Boolean.class
	    	
         };
	     public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 : return true; 
        	 	case 4 : return true; 
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 6 : return true;
        	 } 				
 			return false;
 		}
		
	};
	JTable tabla = new JTable(model);
    JScrollPane scroll = new JScrollPane(tabla);
	
    TableColumn ColumnaDias = tabla.getColumnModel().getColumn(5);
   
    String lista[] = {"0","1","2","3","4","5","6","7"};
    JComboBox cmbDias = new JComboBox(lista);
    	
    JToolBar menu = new JToolBar();
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	public Cat_Deduccion_Asistencia(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Lista.png"));
		this.setTitle("Deducción por Asistencia");

		panel.add(scroll).setBounds(100,70,1150,580);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		panel.add(menu);
		cont.add(panel);
	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		tabla.getColumnModel().getColumn(0).setMinWidth(72);
		tabla.getColumnModel().getColumn(1).setMaxWidth(360);
		tabla.getColumnModel().getColumn(1).setMinWidth(360);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setMaxWidth(210);
		tabla.getColumnModel().getColumn(2).setMinWidth(210);
		tabla.getColumnModel().getColumn(3).setMaxWidth(120);
		tabla.getColumnModel().getColumn(3).setMinWidth(120);
		tabla.getColumnModel().getColumn(4).setMaxWidth(120);
		tabla.getColumnModel().getColumn(4).setMinWidth(120);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(5).setMaxWidth(120);
		tabla.getColumnModel().getColumn(5).setMinWidth(120);
		tabla.getColumnModel().getColumn(6).setMaxWidth(130);
		tabla.getColumnModel().getColumn(6).setMinWidth(130);
		ColumnaDias.setCellEditor(new javax.swing.DefaultCellEditor(cmbDias));

		btnGuardar.addActionListener(opGuardar);
		this.setModal(true);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
	}
	ActionListener opActualizar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			System.out.println("Se actualizo");
		}
	};
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			tabla.setSelectionMode(1);
			guardar();
		}
	};
		
	public void guardar(){
		Vector miVector = new Vector();
		if(getFilas("select * from tb_deduccion_asistencia where status = 1") > 1){
			if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						miVector.add(model.getValueAt(i,j)+" ");
					}
					Obj_Deduccion_Asistencia deduccion = new Obj_Deduccion_Asistencia();
					
					deduccion.setFolioEmpleado(Integer.parseInt(miVector.get(0).toString().trim()));
					deduccion.setNombre_completo(miVector.get(1).toString().trim());
					deduccion.setEstablecimiento(miVector.get(2).toString().trim());
					deduccion.setPuntualidad(miVector.get(3).toString().trim());
					deduccion.setFalta(miVector.get(4).toString().trim());
					deduccion.setDia_faltas(Integer.parseInt(miVector.get(5).toString().trim()));
					deduccion.setAsistencia(miVector.get(6).toString().trim());
					deduccion.actualizar(Integer.parseInt(miVector.get(0).toString().trim()));
					
					miVector.clear();
				}
				JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				return;
			}
			
		}else{
			for(int i=0; i<model.getRowCount(); i++){
				for(int j=0; j<model.getColumnCount(); j++){
					miVector.add(model.getValueAt(i,j)+" ");
				}
				Obj_Deduccion_Asistencia deduccion = new Obj_Deduccion_Asistencia();
				
				deduccion.setFolioEmpleado(Integer.parseInt(miVector.get(0).toString().trim()));
				deduccion.setNombre_completo(miVector.get(1).toString().trim());
				deduccion.setEstablecimiento(miVector.get(2).toString().trim());
				deduccion.setPuntualidad(miVector.get(3).toString().trim());
				deduccion.setFalta(miVector.get(4).toString().trim());
				deduccion.setDia_faltas(Integer.parseInt(miVector.get(5).toString().trim()));
				deduccion.setAsistencia(miVector.get(6).toString().trim());
				deduccion.guardar();
				
				miVector.clear();
			}
			JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
		}
	}
	public Object[][] getTabla(){
	
		String qry = "select tb_empleado.folio," +
						"tb_empleado.nombre," +
						"tb_empleado.ap_paterno," +
						"tb_empleado.ap_materno," +
						"tb_establecimiento.nombre as establecimiento " +
					  "from tb_empleado, tb_establecimiento " +
					  "where tb_empleado.establecimiento_id = tb_establecimiento.folio";
		
		String qry1 ="select tb_empleado.folio," +
						    "tb_empleado.nombre," +
	                        "tb_empleado.ap_paterno," +
                            "tb_empleado.ap_materno," +
                            "tb_establecimiento.nombre as establecimiento," +
                            "tb_deduccion_asistencia.puntualidad," +
                            "tb_deduccion_asistencia.falta," +
                            "tb_deduccion_asistencia.dia_faltas," +
                            "tb_deduccion_asistencia.asistencia " +

                    "from tb_empleado, tb_establecimiento, tb_deduccion_asistencia "+ 
                    "where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
                    	   "tb_empleado.folio = tb_deduccion_asistencia.folio_empleado and "+
                    	   "tb_deduccion_asistencia.status=1";
		
		Object[][] Matriz = new Object[getFilas(qry)][7];
		Connection conn = Connexion.conexion();
		Statement s;
		ResultSet rs;
		try {
			if(getFilas("select * from tb_deduccion_asistencia where status = 1") > 1){
				s = conn.createStatement();
				rs = s.executeQuery(qry1);
				int i=0;
				while(rs.next()){
					Matriz[i][0] = rs.getString(1).trim();
					Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
					Matriz[i][2] = rs.getString(5).trim();
					Matriz[i][3] = Boolean.parseBoolean(rs.getString(6).trim());
					Matriz[i][4] = Boolean.parseBoolean(rs.getString(7).trim());
					Matriz[i][5] = Integer.parseInt(rs.getString(8).trim());
					Matriz[i][6] = Boolean.parseBoolean(rs.getString(9).trim());
					i++;
				}
			}else{
				s = conn.createStatement();
				rs = s.executeQuery(qry);
				int i=0;
				while(rs.next()){
					Matriz[i][0] = rs.getString(1).trim();
					Matriz[i][1] = rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
					Matriz[i][2] = rs.getString(5).trim();
					Matriz[i][3] = false;
					Matriz[i][4] = false;
					Matriz[i][5] = 0;
					Matriz[i][6] = false;
					i++;
				}
			}
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz; 
	}
	
	public static int getFilas(String qry){
		int filas=0;
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	

}
