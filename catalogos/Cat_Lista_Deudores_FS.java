package catalogos;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import objetos.JTextFieldLimit;
import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Lista_Deudores_FS extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	//DECLARACION PARA CREAR UNA TABLA
	DefaultTableModel model = new DefaultTableModel(0,19){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	
	@SuppressWarnings("unchecked")
	private TableRowSorter trsfiltro;
	
	JLabel lblBuscar = new JLabel("BUSCAR : ");
	JTextField txtBuscar = new JTextField();
	
	String busqueda[] = {"Folio","Nombre Completo","Otra Cosa"};
	@SuppressWarnings("unchecked")
	JComboBox cmbBuscar = new JComboBox(busqueda);
	
	@SuppressWarnings("unchecked")
	public Cat_Lista_Deudores_FS()	{
		this.setTitle("..:: Filtro Fuente de Sodas ::..");
		txtBuscar.setDocument(new JTextFieldLimit(10));
		
		txtBuscar.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		
		cmbBuscar.setSelectedIndex(1);
		panel.add(getPanelTabla()).setBounds(10,70,1300,500);
		
		panel.add(lblBuscar).setBounds(10,30,70,20);
		panel.add(txtBuscar).setBounds(90,30,220,20);
		
		panel.add(new JLabel("Buscar por: ")).setBounds(330, 30, 80, 20);
		panel.add(cmbBuscar).setBounds(410, 30, 160, 20);
	
		cont.add(panel);
		Columnas();
		this.setModal(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 

	}
		
   	@SuppressWarnings("unchecked")
	public void filtro() { 
		// Busca segun el combo
		switch (cmbBuscar.getSelectedIndex()){
			case 0 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 0)); break;
			case 1 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 1)); break;
//			case 2 : trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 2)); break;	
		}		 
	}  
	private JScrollPane getPanelTabla()	{		
		new Connexion();
		Connection conn = Connexion.conexion();

		// Creamos las columnas.
		
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();		
			rs = s.executeQuery("select tb_empleado.folio as [Folio],"+
					 "  tb_fuente_sodas_rh.nombre_completo as [Nombre], "+
					 "  tb_establecimiento.nombre as [Establecimiento] "+

					"  from tb_empleado, tb_establecimiento, tb_fuente_sodas_rh"+

					"  where "+
						"  tb_empleado.establecimiento_id = tb_establecimiento.folio and"+
						"  tb_fuente_sodas_rh.status='1'"
						);
			while (rs.next())
			{ 
			   String [] fila = new String[3];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim(); 
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	 JScrollPane scrol = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
		tabla.setAutoResizeMode ( JTable.AUTO_RESIZE_OFF );
	    return scrol; 
	}
	public static void main(String args[]){
		new Cat_Lista_Deudores_FS().setVisible(true);
	}
	public void Columnas(){
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		int a=2;
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(1).setMaxWidth(230);
		tabla.getColumnModel().getColumn(1).setMinWidth(230);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMinWidth(100);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Fecha 1");
		tabla.getColumnModel().getColumn(3).setMaxWidth(70);
		tabla.getColumnModel().getColumn(3).setMinWidth(70);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Cantidad 1");
		tabla.getColumnModel().getColumn(4).setMaxWidth(75);
		tabla.getColumnModel().getColumn(4).setMinWidth(75);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Fecha 2");
		tabla.getColumnModel().getColumn(5).setMaxWidth(70);
		tabla.getColumnModel().getColumn(5).setMinWidth(70);
		tabla.getColumnModel().getColumn(6).setHeaderValue("Cantidad 2");
		tabla.getColumnModel().getColumn(6).setMaxWidth(75);
		tabla.getColumnModel().getColumn(6).setMinWidth(75);
		tabla.getColumnModel().getColumn(7).setHeaderValue("Fecha 3");
		tabla.getColumnModel().getColumn(7).setMaxWidth(70);
		tabla.getColumnModel().getColumn(7).setMinWidth(70);
		tabla.getColumnModel().getColumn(8).setHeaderValue("Cantidad 3");
		tabla.getColumnModel().getColumn(8).setMaxWidth(75);
		tabla.getColumnModel().getColumn(8).setMinWidth(75);
		tabla.getColumnModel().getColumn(9).setHeaderValue("Fecha 4");
		tabla.getColumnModel().getColumn(9).setMaxWidth(70);
		tabla.getColumnModel().getColumn(9).setMinWidth(70);
		tabla.getColumnModel().getColumn(10).setHeaderValue("Cantidad 4");
		tabla.getColumnModel().getColumn(10).setMaxWidth(75);
		tabla.getColumnModel().getColumn(10).setMinWidth(75);
		tabla.getColumnModel().getColumn(11).setHeaderValue("Fecha 5");
		tabla.getColumnModel().getColumn(11).setMaxWidth(70);
		tabla.getColumnModel().getColumn(11).setMinWidth(70);
		tabla.getColumnModel().getColumn(12).setHeaderValue("Cantidad 5");
		tabla.getColumnModel().getColumn(12).setMaxWidth(75);
		tabla.getColumnModel().getColumn(12).setMinWidth(75);
		tabla.getColumnModel().getColumn(13).setHeaderValue("Fecha 6");
		tabla.getColumnModel().getColumn(13).setMaxWidth(70);
		tabla.getColumnModel().getColumn(13).setMinWidth(70);
		tabla.getColumnModel().getColumn(14).setHeaderValue("Cantidad 6");
		tabla.getColumnModel().getColumn(14).setMaxWidth(75);
		tabla.getColumnModel().getColumn(14).setMinWidth(75);
		tabla.getColumnModel().getColumn(15).setHeaderValue("Fecha 7");
		tabla.getColumnModel().getColumn(15).setMaxWidth(70);
		tabla.getColumnModel().getColumn(15).setMinWidth(70);
		tabla.getColumnModel().getColumn(16).setHeaderValue("Cantidad 7");
		tabla.getColumnModel().getColumn(16).setMaxWidth(75);
		tabla.getColumnModel().getColumn(16).setMinWidth(75);
		tabla.getColumnModel().getColumn(17).setHeaderValue("Fecha 8");
		tabla.getColumnModel().getColumn(17).setMaxWidth(70);
		tabla.getColumnModel().getColumn(17).setMinWidth(70);
		tabla.getColumnModel().getColumn(18).setHeaderValue("Cantidad 8");
		tabla.getColumnModel().getColumn(18).setMaxWidth(75);
		tabla.getColumnModel().getColumn(18).setMinWidth(75);
	}
}
