package cuadrantes;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;


import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Filtro_Nivel_Jerarquico extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel model = new DefaultTableModel(0,3){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JLabel lblBuscar = new JLabel("BUSCAR : ");
	JTextField txtBuscar = new JTextField();
	
//	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento_Empleados();
//	@SuppressWarnings("rawtypes")
//	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	@SuppressWarnings("rawtypes")
	public Cat_Filtro_Nivel_Jerarquico()	{
		this.setTitle("Filtro Nivel Jerarquico");
		txtBuscar.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(getPanelTabla()).setBounds(10,100,420,450);
		
		agregar(tabla);
		
		campo.add(lblBuscar).setBounds(30,30,70,20);
		campo.add(txtBuscar).setBounds(95,30,215,20);
		
		cont.add(campo);
		
		this.setSize(450,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	        		int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0).toString().trim();
	    			dispose();
	    			new Cat_Nivel_Jerarquico(folio+"").setVisible(true);
	        	}
	        }
        });
    }
	
	public void filtro() { 
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));
	}  
	private JScrollPane getPanelTabla()	{		
		new Connexion();
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(70);
		tabla.getColumnModel().getColumn(0).setMinWidth(70);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Descripcion");
		tabla.getColumnModel().getColumn(1).setMaxWidth(185);
		tabla.getColumnModel().getColumn(1).setMinWidth(185);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Nivel Jerarquico");
		tabla.getColumnModel().getColumn(2).setMaxWidth(150);
		tabla.getColumnModel().getColumn(2).setMinWidth(150);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				
				if(row%2==0){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(177,177,177));
				} 
			
				if(table.getSelectedRow() == row){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(186,143,73));
				}
			
				switch(column){
					case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
				return lbl; 
			} 
		};
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(2).setCellRenderer(render); 
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select tb_nivel_jerarquico.folio " +
					",tb_nivel_jerarquico.descripcion " +
					",tb_puesto.nombre " +
					"from tb_nivel_jerarquico " +
					"inner join tb_puesto on tb_puesto.folio=tb_nivel_jerarquico.puesto_principal" );
			
			while (rs.next())
			{ 
			   String [] fila = new String[3];
			   fila[0] = rs.getString(1)+"  ";
			   fila[1] = "   "+rs.getString(2).trim();
			   fila[2] = "   "+rs.getString(3).trim();
			   
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
}
