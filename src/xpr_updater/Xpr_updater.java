/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xpr_updater;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import xpr_updater.MainWindow;

/**
 *
 * @author xProf
 */
public class Xpr_updater 

{
    
    
    
    public static void Run() throws MalformedURLException, IOException
    {
        MainWindow mainWindow=new MainWindow();
        mainWindow.run();
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
