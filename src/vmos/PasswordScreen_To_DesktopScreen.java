package vmos;

import java.awt.Color;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static vmos.StartScreen.jf;

public class PasswordScreen_To_DesktopScreen extends Static_Objects_Methods_Reuasble
{
    static int x=635,y=335,w=10,h=10;
    Clip clip;
    public void  PasswordScreen_To_DesktopScreenPanel()
    {
        
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\lion_roar.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        }
        
        JPanel jp=new JPanel();
        jp.setVisible(true);
        jp.setLayout(null);
        jp.setSize(1280,680);
        jp.setBackground(Color.WHITE);
        jp.setOpaque(true);
        jf.add(jp);
        jp.repaint();
        jf.repaint();
        
        
        JLabel das=new JLabel();
        das.setBorder(BorderFactory.createEmptyBorder());
        das.setOpaque(false);
        jp.add(das);
        jp.repaint();
        jf.repaint();
        
        
        Timer timr = new Timer();
        TimerTask task = new TimerTask() 
        {
            @Override
            public void run() 
            {
                if(w>1280)
                {
                    x=635;y=335;w=10;h=10;
                    timr.cancel();
                    jf.getContentPane().removeAll();
                    jf.repaint();
                    System.gc();
                    new DesktopScreen().DesktopScreenPanel();
                }
                das.setBounds(x, y, w, h);
        try {
        das.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"LOGO_ZOOM\\"+w+".png"))));
        } catch (Exception e) {}
                
                
                das.repaint();
                jp.repaint();
                jf.repaint();
                w=w+30;
                h=h+30;
                
                x=(1280-w)/2;
                y=(680-h)/2;
            }
        };
        timr.schedule(task, 0, 1);

        
        
    }
    
}
