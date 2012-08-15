/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xpr_updater;

import java.awt.Label;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author xProf
 */
public class Xpr_updater {
        
//FileOutputStream fos;
//HttpURLConnection urlConnect;
//BufferedInputStream inStream;
    
    public static void openWindow() throws MalformedURLException, IOException 
    {
       JFrame okno=new JFrame("Test");
       okno.setLocation(0, 0);
       okno.setSize(300, 200);
       okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Label label;
       long vsego=0;
       okno.setVisible(true);
       
       byte[] b =new byte[1024];
       int count;
            label=new Label("ras ras ras");
            okno.add(label);
            label.setLocation(10,10);
            label.setSize(200, 30);
            label.setVisible(true);
            FileOutputStream fos;
            URL url = new URL("http://minecraft-tyachiv.org.ua/download/minecraft.jar");
            long size=url.getFile().length();
            URLConnection urlConnect=url.openConnection();
            BufferedInputStream inStream=new BufferedInputStream(urlConnect.getInputStream());

 
                fos = new FileOutputStream("d:/_musor/minecraft.jar");

                while((count=inStream.read(b))!=-1)
                {
                    fos.write(b, 0, count);
                    vsego=vsego+count;
                    label.setText(" "+(size));
                }    
                fos.close();


        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
             javax.swing.SwingUtilities.invokeLater(new Runnable() 
                {
                    public void run() 
                        {
                        try {
                            openWindow();
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Xpr_updater.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Xpr_updater.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                });
    }
}
