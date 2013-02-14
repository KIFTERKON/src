package camara;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

@SuppressWarnings("serial")
public class MainCamara extends javax.swing.JFrame {
	
	
    private Dispositivos misDispositivos;
    String nombre;
    JButton jButton2 = new JButton("Ver");
    JMenu jMenu1 = new JMenu();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenuDispositivos = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenuItem jMenuItem2 = new JMenuItem();
    JPanel jPWebCam = new JPanel();
    JPanel jPanel1 = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea txtInfo = new JTextArea();
    JTextField txtNombre = new JTextField();
    
    public MainCamara(String folio) {
    	nombre=folio;
        initComponents();
        misDispositivos= new Dispositivos(this);
        misDispositivos.detectarDispositivos(jMenuDispositivos);
        setLocationRelativeTo(null);
        
    }

    private void initComponents() {

        setTitle("Captura Foto");
 
        jPWebCam.setBorder(javax.swing.BorderFactory.createTitledBorder("Wisky!"));
        jPWebCam.setLayout(new java.awt.BorderLayout());
        
        getContentPane().add(jPWebCam, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(397, 160));

        txtInfo.setColumns(20);
        txtInfo.setRows(5);
        jScrollPane1.setViewportView(txtInfo);
        
        jButton2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
            	 infoDispositivo();
            	 misDispositivos.MuestraWebCam(jPWebCam,"vfw:Microsoft WDM Image Capture (Win32):0","yuv");
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
        		jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(110, 110, 110))
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
                    .addComponent(jButton2))
                .addContainerGap()));

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jMenu1.setText("Archivo");

        jMenuItem2.setText("Capturar y Guardar");
        jMenuItem2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt)
            {
            	 misDispositivos.CapturaFoto(nombre);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				misDispositivos.salir();
            	dispose();
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenuDispositivos.setText("Dispositivos");
        jMenuBar1.add(jMenuDispositivos);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(413, 513));
    }

    private void infoDispositivo()
    {
        txtInfo.setText(misDispositivos.verInfoDispositivos());
    }

}
