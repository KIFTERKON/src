package camara;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.media.CannotRealizeException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

@SuppressWarnings("serial")
public class MainCamara extends javax.swing.JFrame {
	
	
    private Dispositivos misDispositivos;
    String nombre;
    JButton btnGuardar = new JButton("Guardar");
    JButton btnSalir = new JButton("Salir");
    JPanel jPWebCam = new JPanel();
    JPanel jPanel1 = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea txtInfo = new JTextArea();
    JTextField txtNombre = new JTextField();
    
    public MainCamara(String folio) {
    	nombre=folio;
        initComponents();
        misDispositivos= new Dispositivos(this);
        btnGuardar.setEnabled(false);
        setLocationRelativeTo(null);
        ver();
    }

    private void initComponents() {
    	this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/camera.png"));
        this.setTitle("Captura Foto");
 
        jPWebCam.setBorder(javax.swing.BorderFactory.createTitledBorder("Wisky!"));
        jPWebCam.setLayout(new java.awt.BorderLayout());
        
        getContentPane().add(jPWebCam, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(397, 160));

        txtInfo.setColumns(20);
        txtInfo.setRows(5);
        jScrollPane1.setViewportView(txtInfo);
        
        btnSalir.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		misDispositivos.salir();
        		dispose();
        	}
        });
        btnGuardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            	 misDispositivos.CapturaFoto(nombre);
            }
        });
        
        addWindowListener( new java.awt.event.WindowAdapter() {
        	public void windowClosing(java.awt.event.WindowEvent e ) { 
        		misDispositivos.salir();
        		dispose();
        	} 
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
        		jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(20, 20, 20)
                .addComponent(btnSalir)
                .addGap(10, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap()));
        
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir))
                .addContainerGap()));
      
        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        setSize(new java.awt.Dimension(430, 513));
    }

    public void ver(){
    	btnGuardar.setEnabled(true);
    	infoDispositivo();
    	try {
    		misDispositivos.MuestraWebCam(jPWebCam,"vfw:Microsoft WDM Image Capture (Win32):0","rgb");
//			misDispositivos.MuestraWebCam(jPWebCam,"vfw:Microsoft WDM Image Capture (Win32):0","yuv");
		} catch (CannotRealizeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void infoDispositivo() {
        txtInfo.setText(misDispositivos.verInfoDispositivos());
    }

}
