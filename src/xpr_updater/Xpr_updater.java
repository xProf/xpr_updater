/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xpr_updater;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.io.BufferedInputStream;
import java.io.File;
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
public class Xpr_updater 

{
    
    public static String mineFolder = "minecraft";    
    public static String downLoadURL="http://minecraft-tyachiv.org.ua/download/";
    public static String launcherFileName="client.zip";
    public static Label labelMain,labelText,labelBar1,labelBar2;
    
    
    public static void Run() throws MalformedURLException, IOException
    {
    openWindow();    
    downLoad();
    if (new File(getMineDirectory()+File.separator+launcherFileName).exists() )Runtime.getRuntime().exec(getMineDirectory()+File.separator+launcherFileName);
    //System.exit(0);
    }
    
    public static void openWindow() throws MalformedURLException, IOException 
    {
       JFrame okno=new JFrame("Minecraft Updater 0.01");
       okno.setLocation(50, 50);
       okno.setSize(300, 200);
       okno.setResizable(false);
       okno.setLayout(new BorderLayout());
       okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       okno.setVisible(true);
       labelMain=new Label();
       labelText=new Label();
       labelBar1=new Label();
       labelBar2=new Label();
       
       labelMain.setBackground(Color.decode("#440044"));
       labelMain.setForeground(Color.decode("#ffffff"));
       labelMain.setAlignment(1);
       labelMain.setLocation(0,80);
       labelMain.setSize(300, 120);
       labelMain.setVisible(true);
       
       labelText.setBackground(Color.decode("#440044"));
       labelText.setForeground(Color.decode("#ffffff"));
       labelText.setAlignment(1);
       labelText.setLocation(0,0);
       labelText.setSize(300, 80);
       labelText.setFont(null);
       labelText.setVisible(true);
       
       labelBar1.setBackground(Color.decode("#777700"));
       labelBar1.setLocation(20,100);
       labelBar1.setSize(0, 20);
       labelBar1.setVisible(true);
       
       labelBar2.setBackground(Color.decode("#007777"));
       labelBar2.setLocation(20,100);
       labelBar2.setSize(260, 20);
       labelBar2.setVisible(true);
       
       okno.add(labelBar1);
       okno.add(labelBar2);
       okno.add(labelMain);
       okno.add(labelText);
   }
    
    public static void downLoad() 
    {        
        try {
            FileOutputStream fos;
            long vsego=0;       
            byte[] b =new byte[1024];
            int count;
            
            URL url = null;
             
            try {
                 url = new URL(downLoadURL+launcherFileName);
             } catch (MalformedURLException ex) {
                 Logger.getLogger(Xpr_updater.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            URLConnection urlConnect=url.openConnection();
            BufferedInputStream inStream=new BufferedInputStream(urlConnect.getInputStream());

            
            long size=urlConnect.getContentLength();
            File mineDir=new File(getMineDirectory());
            if (!mineDir.exists()) mineDir.mkdir();
            
            fos = new FileOutputStream(getMineDirectory()+File.separator+launcherFileName);
            while((count=inStream.read(b))!=-1)
               {
                  fos.write(b, 0, count);
                  vsego=vsego+count;
                  labelText.setText("Updating:  "+(100*vsego/size)+"%");
                  labelBar1.setSize((int)(260*vsego/size), 20);
               }    
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(Xpr_updater.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    
    
    private static String getMineDirectory()
            {
                String OS = System.getProperty("os.name").toUpperCase();
                if (OS.contains("WIN"))
                    return System.getenv("APPDATA")+"\\."+mineFolder;
                else if (OS.contains("MAC"))
                       return System.getProperty("user.home") + "/Library/Application " + "Support";
                else if (OS.contains("NUX"))
                        return System.getProperty("user.home")+"/."+mineFolder;
                return System.getProperty("user.dir");
            }
    
    
    public static void main(String[] args) {
             javax.swing.SwingUtilities.invokeLater(new Runnable() 
                {
                    public void run() 
                        {
                        try {
                            Run();
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Xpr_updater.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Xpr_updater.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                });
    }
}
