package vmos;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static vmos.Static_Objects_Methods_Reuasble.Mypath;
public class StartScreen
{
    static JFrame jf=new JFrame();
    static int stt=0;
    protected void StartScreenPanel()
    {
        
        jf.setSize(1280,680);
        jf.setUndecorated(true);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setIconImage((new ImageIcon(Mypath+"Resoures\\VMOSLOGO.png")).getImage());
        JFXPanel jp=new JFXPanel();
        jf.add(jp);
        jp.repaint();
        jf.repaint();
        
        
        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();

        URL mu=getClass().getResource("StartScreen.mp4");
        String meds=mu.toExternalForm();
        Media m= new Media(meds);
        MediaPlayer mp=new MediaPlayer(m);
        mp.setAutoPlay(true);
        
        
        MediaView mv=new MediaView(mp);
        mv.setFitHeight(720);
        mv.setFitWidth(1300);
        
        VBox root=new VBox(mv);
        root.setAlignment(Pos.CENTER);
        Scene sc=new Scene(root);
        
        jp.setScene(sc);
        jp.repaint();
        jf.add(jp);
        jp.repaint();
        jf.repaint();
        int temp;
            try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection conn= DriverManager.getConnection("jdbc:sqlserver://localhost\\VAIBHAV:1433;databaseName=VMOS","sa","xxxxxx");
                    PreparedStatement p=conn.prepareStatement("Select * from userdetails where sr_no=?");
                    p.setInt(1,1);
                    ResultSet rs= p.executeQuery();
                    if(rs.next())
                    {
                        temp=rs.getInt("sr_no");
                    }
                }
            catch (Exception e) 
              {
                e.printStackTrace();
              }
        
    
            Timer stimr = new Timer();
        TimerTask sttask = new TimerTask() {
            @Override
            public void run() {
                if(stt>=31)
                {
                    stt=0;
                    stimr.cancel();
                    jf.getContentPane().removeAll();
                    jf.repaint();
                    System.gc();
                    new LogScreenUser().LogScreenUserPanel();
                }
               stt++;
        }
        };

        stimr.schedule(sttask, 000, 1000);
   }
}