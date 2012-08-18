/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xpr_updater;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
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
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author xProf
 */
public class MainWindow extends Applet

{
    
    private String mineFolder = "minecraft";    
    private String downLoadURL="http://minecraft-tyachiv.org.ua/download/";
    private String launcherFileName="minecraft.exe";
    private Label labelMain,labelText,labelBar1,labelBar2;
    private Image art;
    private JFrame okno;
    private int percent;
    
    
    public void run() throws MalformedURLException, IOException
    {
    percent=0;
    openWindow();    
    downLoad();
    if (new File(getMineDirectory()+File.separator+launcherFileName).exists() )Runtime.getRuntime().exec(getMineDirectory()+File.separator+launcherFileName);
    System.exit(0);
    }
    
    public void openWindow() throws MalformedURLException, IOException 
    {
       okno=new JFrame("Minecraft Updater 0.02");
       okno.setLocation(50, 50);
       okno.setSize(400, 300);
       okno.setResizable(false);
       okno.setLayout(new BorderLayout());
       okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       okno.setVisible(true);
      
       drawComponents(okno.getGraphics());
    }
        
 
    public void drawComponents(Graphics g) throws IOException
    {
    Image bgImage = ImageIO.read(Xpr_updater.class.getResource("dirt.png")).getScaledInstance(32, 32, 16);
    //Image bdImage=ImageIO.read(xpr_updater.Xpr_updater.class.getResource(mineFolder));
    int w=okno.getWidth();
    int h=okno.getHeight();
    for (int i=0;i<1+w/bgImage.getWidth(null);i++)
        for (int j=0;j<1+h/bgImage.getHeight(null);j++)
            g.drawImage(bgImage, i*32, j*32, null);
          g.setColor(Color.LIGHT_GRAY);
      String msg = "Оновлення лаунчера: "+percent+" %";
      g.setFont(new Font(null, 1, 20));
      FontMetrics fm = g.getFontMetrics();
      g.drawString(msg, w / 2 - fm.stringWidth(msg) / 2, h / 2 - fm.getHeight() * 2);
      g.setColor(Color.LIGHT_GRAY);
      g.drawRect(40, 200, w-80, 40);
      g.setColor(Color.GRAY);
      for (int i=0;i<(w-100)*percent/100;i++)
        g.drawRect(50,210,i,20);
      
    }
    
    public void downLoad() 
    {        
        try {
            FileOutputStream fos;
            long vsego=0;       
            int count;
            long size;
            byte[] b =new byte[1024];
            
            URL url = null;
             
            try {
                 url = new URL(downLoadURL+launcherFileName);
             } catch (MalformedURLException ex) {
                 Logger.getLogger(Xpr_updater.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            URLConnection urlConnect=url.openConnection();
            BufferedInputStream inStream=new BufferedInputStream(urlConnect.getInputStream());

            
            size=urlConnect.getContentLength();
            File mineDir=new File(getMineDirectory());
            if (!mineDir.exists()) mineDir.mkdir();
            
            fos = new FileOutputStream(getMineDirectory()+File.separator+launcherFileName);
            while((count=inStream.read(b))!=-1)
               {
                  fos.write(b, 0, count);
                  vsego=vsego+count;
                  percent=(int)(100*vsego/size);
                  drawComponents(okno.getGraphics());
               }    
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(Xpr_updater.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    
    
    private String getMineDirectory()
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
    
    
   
}   
