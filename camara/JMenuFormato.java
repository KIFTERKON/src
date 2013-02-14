package camara;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JMenuFormato extends JMenuItem implements ActionListener{

    private int ancho;
    private int alto;
    @SuppressWarnings("unused")
	private JPanel modificable;
    private MainCamara padre;

    public JMenuFormato(String etiqueta,int ancho,int alto,MainCamara Padre,JPanel modificable)
    {
        super(etiqueta);
        this.modificable=modificable;
        this.ancho=ancho;
        this.alto=alto;
        this.addActionListener(this);
        this.padre=Padre;
    }

    public void actionPerformed(ActionEvent e) {
       //modificable.setSize(ancho,alto);
       padre.setSize(ancho, alto+200);
    }
}