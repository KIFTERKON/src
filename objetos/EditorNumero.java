package objetos;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class EditorNumero extends JTextField implements TableCellEditor{

	private ArrayList<CellEditorListener> suscriptores = new ArrayList<CellEditorListener>();
	/*************************************CONSTRUCTOR POR DEFECTO******************************/
	public EditorNumero(){
			//Nos apuntamos a la pérdida de foco, que quiere decir que se ha
			// dejado de editar la celda. 
			this.addFocusListener(new FocusListener() {
				public void focusGained (FocusEvent e) {
					setText("");
				}
				public void focusLost (FocusEvent e){
					editado (true);
				}
			});
			
			//Nos apuntamos a un escuchador de tecla, para detectar si lo 
			//digitado es correcto. En nuestro caso es correcto si es número. 
			this.addKeyListener(new KeyAdapter(){
				public void keyTyped(KeyEvent arg0) {
					
					char caracter = arg0.getKeyChar(); 
					if(((caracter < '0') || (caracter > '9')) && (caracter != '.' ))
					arg0.consume(); // ignorar el evento de teclado
				}
			});
	}
	
	/*******************************METODOS PARA MANEJO DE SUSCRIPTORES************************/
	public void addCellEditorListener1(CellEditorListener l) {
		suscriptores.add(l); 
	}
	
	public void removeCellEditorListener1(CellEditorListener l) {
		suscriptores.remove(l); 
	}
	
	/*******************************METODOS PARA CANCELAR Y TERMINAR EDICION************************/
	
	/**Le dice al editor que cancele la edición y
	* no aceptar ningún valor editado parcialmente*/
	public void cancelCellEditing() { 
	}
	
	/**Le dice al editor para detener la edición y aceptar cualquier valor
	* parcialmente editado como el valor del editor. El editor retorna 
	* falso si la edición no se detuvo, lo que es útil para los editores
	* que validen y no puede aceptar entradas inválidas.
	* 
	* @ return true si la edición fue detenida, false en caso contrario*/
	public boolean stopCellEditing() {
	//indica si se puede detener la edición
		return true; 
	}
	
	/*******************************OTROS METODOS DE EDICION************************/
	
	/**Retorna el valor contenido en el editor*/
	public Object getCellEditorValue() {
		return Float.parseFloat(this.getText());
	}
	
	/**Retorna el componente utilizado como editor*/
	public Component getTableCellEditorComponent1(JTable t, Object value,
	boolean isSelected, int fila, int columna) {
		if(isSelected)
			this.setBackground(Color.YELLOW); 
		else
			this.setBackground(Color.WHITE);
		return this;
	}
	
	public boolean isCellEditable1(EventObject e) {
		//la celda es editable ante cualquier evento
		return true;
	}
	
	public boolean shouldSelectCell1(EventObject arg0) {
		//indica si al editar la celda, debemos seleccionar
		//la fila que la contiene
		return true;
	}
	
	/*******************************MIS METODOS DE EDICION************************/
	/**
	* Si cambiado es true, se avisa a los suscriptores de que se ha terminado
	* la edición. Si es false, se avisa de que se ha cancelado la edición.
	*/
	protected void editado(boolean cambiado)
	{
		ChangeEvent evento = new ChangeEvent (this);
		int i;
		for (i=0; i<suscriptores.size(); i++)
		{
			CellEditorListener aux = (CellEditorListener)suscriptores.get(i);
				if ((cambiado==true)&&(this.getText().isEmpty()==false)){
					aux.editingStopped(evento);
				}
				else{
					aux.editingCanceled(evento);
				}
		}
	}
	
	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
			boolean arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

}