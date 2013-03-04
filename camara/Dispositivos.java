package camara;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.Format;
import javax.imageio.*;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.cdm.CaptureDeviceManager;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.format.YUVFormat;
import javax.media.util.BufferToImage;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Dispositivos {

    private MainCamara padre;
	private Player player;
	
	public Dispositivos(){
		
	}
	
	public Dispositivos(MainCamara padre){
        this.padre=padre;
    }

   
	@SuppressWarnings("rawtypes")
	public String verInfoDispositivos()
    {
      String rpta="";
      Vector listaDispositivos = null;
      
     listaDispositivos = CaptureDeviceManager.getDeviceList();
     Iterator it = listaDispositivos.iterator();
      while (it.hasNext())
      {
        CaptureDeviceInfo cdi = (CaptureDeviceInfo)it.next();
        rpta+=cdi.getName()+"\n";
      }
      if(rpta.compareTo("")!=0)
          rpta="Dispositivos detectados:\n\n"+rpta;
      else
          rpta="Sin Dispositivos Detectados";
      
      return rpta;
    }
	public void salir(){
		player.close();
	}
	
	@SuppressWarnings("rawtypes")
	public void detectarDispositivos(JMenu dispositivos)
    {
      Vector listaDispositivos = null;
      listaDispositivos = CaptureDeviceManager.getDeviceList();
      Iterator it = listaDispositivos.iterator();

      String nombre="";
      while (it.hasNext())
      {
          CaptureDeviceInfo cdi = (CaptureDeviceInfo)it.next();
          nombre=cdi.getName(); //cdi.getName() --> Obtiene el nombre del Dispositivo Detectado
          
          if(nombre.indexOf("Image")!=-1)
          {
              JMenu menuFormato=new JMenu(nombre);
              JMenuFormato tamanios=null;
              CaptureDeviceInfo dev = CaptureDeviceManager.getDevice(nombre);
              Format[] cfmts = dev.getFormats();

              for(int i=0; i<cfmts.length;i++)
              {
                  if(cfmts[i].getEncoding().compareTo("yuv")==0)
                  {tamanios=new JMenuFormato(cfmts[i].getEncoding()+" "+
                          ((YUVFormat)cfmts[i]).getSize().width+"x"+
                          ((YUVFormat)cfmts[i]).getSize().height,
                          ((YUVFormat)cfmts[i]).getSize().width,
                          ((YUVFormat)cfmts[i]).getSize().height,
                          padre,
                          padre.jPWebCam);
                  }
                  else if(cfmts[i].getEncoding().compareTo("rgb")==0)
                  {tamanios=new JMenuFormato(cfmts[i].getEncoding()+" "+
                          ((RGBFormat)cfmts[i]).getSize().width+"x"+
                          ((RGBFormat)cfmts[i]).getSize().height,
                          ((RGBFormat)cfmts[i]).getSize().width,
                          ((RGBFormat)cfmts[i]).getSize().height,
                          padre,
                          padre.jPWebCam);
                  }
                  menuFormato.add(tamanios);
              }
              dispositivos.add(menuFormato);
          }
      }
    }

	public void MuestraWebCam(JPanel panelCam,String dispositivo,String FormatoColor) throws IOException, CannotRealizeException {
		if(player != null)
            return;
        
        CaptureDeviceInfo dev = CaptureDeviceManager.getDevice(dispositivo);
        MediaLocator loc = dev.getLocator();
        try {
                player = Manager.createRealizedPlayer(loc);
                System.out.println(player);
               
            } catch (IOException ex) {
            	System.out.println("Ponga la camara 0");
            } catch (NoPlayerException ex) {
            	System.out.println("Ponga la camara 1");
            } catch (CannotRealizeException ex) { 
            	System.out.println("Ponga la camara 3");
            }
          
    
        player.start();
           
        try {
        	
            Thread.sleep(1000);
        } catch (InterruptedException ex) { }

        Component comp;

        if ((comp = player.getVisualComponent())!= null) {
          panelCam.add(comp,BorderLayout.CENTER);
          padre.pack();
        }
    }
    
	public void CapturaFoto(String nombre) {
    	Image img=null;
        FrameGrabbingControl fgc = (FrameGrabbingControl)
        player.getControl("javax.media.control.FrameGrabbingControl");
        Buffer buf = fgc.grabFrame();
        BufferToImage btoi = new BufferToImage((VideoFormat)buf.getFormat());
        img = btoi.createImage(buf);

        if (img != null) {
            Integer i = new Integer(JFileChooser.APPROVE_OPTION);
            if (i != null){
            	File folder = new File("x:\\Empleados");
            	if(folder.exists()){
            		  String imagen = "X:\\Empleados\\"+nombre;
                      if (imagen.lastIndexOf(".") > 0){
                          imagen = imagen.substring(0,imagen.lastIndexOf("."));
                      }
                      imagen = imagen+".JPG";
                      System.out.println("imagen:"+imagen);
                      File imagenArch = new File(imagen);
                      String formato = "JPEG";
                      player.close();
                      padre.dispose();
                       try{
                         ImageIO.write((RenderedImage) img,formato,imagenArch);
                      }catch (IOException ioe){
                    	  System.out.println("Error al guardar la imagen");
                      }
            	}else{
            		JOptionPane.showMessageDialog(null, "El directorio '\\192.168.2.180\\RRHH' no está conectado a la unidad de red ");	
            	}
              
            }
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(padre, "A ocurrido un error!!");
        }
        img=null;
     }
}