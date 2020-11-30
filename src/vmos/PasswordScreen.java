package vmos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import static vmos.StartScreen.jf;
import static vmos.LogScreenUser.logged_User;

public class PasswordScreen extends Static_Objects_Methods_Reuasble
{
        JLabel error;
        JLabel info ;
    protected void PasswordScreenPanel()
    {
        
        ImagePanel jp=new ImagePanel();
        jp.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
        jp.setVisible(true);
        jp.setLayout(null);
        jp.setSize(1280,680);
        jf.add(jp);
        jp.repaint();
        jf.repaint();
        
        try{
        JLabel acc_icon =new JLabel("",JLabel.CENTER);
        acc_icon.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\pass_icon.png"))));
        acc_icon.setBounds(492, 309,25,25);
        acc_icon.setBackground(Color.WHITE);
        acc_icon.setBorder(BorderFactory.createBevelBorder(0));
        acc_icon.setOpaque(true);
        blurit(acc_icon, jp);
        jp.add(acc_icon,SwingConstants.CENTER);
        jf.repaint();
        }catch(Exception e){}
        
        JPasswordField pwdd=new JPasswordField(JPasswordField.BOTTOM);
        pwdd.setBounds(526,309,230,27);
        pwdd.setBorder(BorderFactory.createBevelBorder(1));
        pwdd.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel ll=new JLabel(" Password");
        ll.setFont(new Font("Baskerville Old Face",Font.ITALIC,20));
        ll.setBounds(0, 4, 100, 22);
        ll.setForeground(Color.decode("#3d5c5c"));
        blurit(pwdd, jp);
        pwdd.add(ll);
        pwdd.repaint();
        jp.add(pwdd);
        jp.repaint();
        pwdd.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent fe) 
            {
                pwdd.remove(ll);
                pwdd.repaint();
                jp.repaint();
            }

            @Override
            public void focusLost(FocusEvent fe) 
            {
                if(pwdd.getText().equals(""))
                {
                    pwdd.add(ll);
                    pwdd.repaint();
                    jp.repaint();
                }
            }
        });
        
        try{
        JLabel pf =new JLabel("",JLabel.CENTER);
        pf.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\pf.png"))));
        pf.setBounds(579,158,120,120);
        pf.setBackground(Color.WHITE);
        pf.setBorder(BorderFactory.createEmptyBorder());
        blurit(pf, jp);
        jp.add(pf,SwingConstants.CENTER);
        jf.repaint();
        }catch(Exception e){}

        String ppp=null;
        try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection conn= DriverManager.getConnection("jdbc:sqlserver://localhost\\VAIBHAV:1433;databaseName=VMOS","sa","xxxxxx");
                    PreparedStatement p=conn.prepareStatement("Select * from userdetails where username=?");
                    p.setString(1,logged_User.trim());
                    ResultSet rs= p.executeQuery();
                    if(rs.next())
                    {
                        ppp=rs.getString("img");
                    }
                }
            catch (Exception e) 
              {
                e.printStackTrace();
              }
        
        try
        {
        JLabel upp =new JLabel("",JLabel.CENTER);
        upp.setIcon(new ImageIcon(ImageIO.read(new File(ppp))));
        upp.setBounds(589,168,101,101);
        upp.setBackground(Color.WHITE);
        upp.setBorder(BorderFactory.createEmptyBorder());
        upp.setOpaque(true);
        blurit(upp, jp);
        jp.add(upp,SwingConstants.CENTER);
        jf.repaint();
        }catch(Exception e){}
       
        JLabel cps= new JLabel("Caps Lock is ON",JLabel.CENTER);
        cps.setBounds(580,348,150,20);
        cps.setBackground(Color.decode("#197BF1"));
        cps.setFont(new Font("Bell MT",Font.BOLD,15));
        cps.setForeground(Color.WHITE);
        cps.setBorder(BorderFactory.createBevelBorder(0));
        cps.setOpaque(true);
        blurit(cps, jp);
        jp.repaint();
        
        try{
        info =new JLabel("",JLabel.CENTER);
        info.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\info.png"))));
        info.setBounds(550, 348,20,20);
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createBevelBorder(0));
        info.setOpaque(true);
        blurit(info, jp);
        jf.repaint();
        }catch(Exception e){}
        
        Timer timer = new Timer(); 
        TimerTask task = new TimerTask() 
        {
            @Override
            public void run() 
            {
                boolean check = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                if(check)
                {
                    if(cps.getParent()==null&&info.getParent()==null)
                    {
                        jp.add(info,SwingConstants.CENTER);
                        jp.add(cps);
                        jp.repaint();
                    }
                }
                else
                {
                    if(cps.getParent()==jp&&info.getParent()==jp)
                    {
                        jp.remove(info);
                        jp.remove(cps);
                        jp.repaint();
                    }
                }
            }
        }; 
          
        timer.schedule(task,000,100);

        
        try 
        {
            JLabel logo=new JLabel();
            logo.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\Logo.png"))));
            logo.setBounds(462, 520, 87, 120);
            blurit(logo, jp);
            jp.add(logo,SwingConstants.CENTER);
            jf.repaint();
            
        } catch (Exception e) {System.out.println("ImageNotFound");}
        
        
   

    
    VM_Label vmos = new VM_Label("VMOS",JLabel.CENTER);
    GradientPaint gp = new GradientPaint(0, 0, Color.decode("#34495E"), 100, 50,Color.decode("#000000"), true);
    vmos = new VM_Label("VMOS", JLabel.CENTER);
    vmos.setFont(new Font("Copperplate Gothic Bold", Font.BOLD,74));
    vmos.setEffectIndex(VM_Label.EFFECT_COLOR_ANIMATION);
    vmos.setGradient(gp);
    vmos.setOutlineColor(Color.black);
    vmos.startAnimation(400);
    vmos.setBounds(550, 553,300,90);
    blurit(vmos, jp);
    jp.add(vmos);
    
    
 

    VMButton next=new VMButton();
    next.setBounds(761, 309, 31, 31);
    next.setImage(new ImageIcon(Mypath+"Resoures\\next.png").getImage());
    next.setBorder(BorderFactory.createEmptyBorder());
    next.addMouseListener(new MouseListener() 
        {
            
            @Override
            public void mouseClicked(MouseEvent me){}
            @Override
            public void mousePressed(MouseEvent me) 
            {
               next_clk();
               next.setImage(new ImageIcon(Mypath+"Resoures\\next_oc.png").getImage());
               next.repaint();
            }
            @Override
            public void mouseReleased(MouseEvent me)
            {
                next.setImage(new ImageIcon(Mypath+"Resoures\\next.png").getImage());
                next.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) 
            {
                jp.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                jp.repaint();
                next.setImage(new ImageIcon(Mypath+"Resoures\\next_oh.png").getImage());
                next.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                jp.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                jp.repaint();
                next.setImage(new ImageIcon(Mypath+"Resoures\\next.png").getImage());
                next.repaint();
            }
        });
        VM_Label status = new VM_Label("Invalid Password", JLabel.CENTER);
        next.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                if(status.getParent()==null)
                {
                    String passdb=null;
                    
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection connn = DriverManager.getConnection("jdbc:sqlserver://localhost\\VAIBHAV:1433;databaseName=VMOS", "sa", "xxxxxx");
                        PreparedStatement p = connn.prepareStatement("Select * from userdetails where username=?");
                        p.setString(1, logged_User.trim());
                        ResultSet rs = p.executeQuery();
                        if (rs.next()) {
                            passdb = rs.getString("pwd");
                        }
                    } catch (Exception e) {}
                    if ((passdb.trim()).equals(pwdd.getText())) 
                    {
                        timer.cancel();
                        jf.getContentPane().removeAll();
                        jf.repaint();
                        System.gc();
                        new PasswordScreen_To_DesktopScreen().PasswordScreen_To_DesktopScreenPanel();
                        
                    } else {

                        GradientPaint gpp = new GradientPaint(0, 0, Color.decode("#f44141"), 100, 50, Color.decode("#F1948A"), true);
                        status.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
                        status.setEffectIndex(VM_Label.EFFECT_COLOR_ANIMATION);
                        status.setGradient(gpp);
                        status.startAnimation(80);
                        status.setBounds(540, 400, 240, 30);
                        blurit(status, jp);
                        jp.add(status);
                        jp.repaint();

                       try {
                            error = new JLabel("", JLabel.CENTER);
                            error.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\error.png"))));
                            error.setBounds(500, 397, 30, 30);
                            error.setBackground(Color.WHITE);
                            error.setBorder(BorderFactory.createBevelBorder(0));
                            error.setOpaque(true);
                            blurit(error, jp);
                            jp.add(error, SwingConstants.CENTER);
                            jf.repaint();
                        } catch (Exception e) { }
                        VMButton ok_button = new VMButton();
                        ok_button.setBounds(605, 460, 70, 22);
                        ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_button.png").getImage());
                        ok_button.setBorder(BorderFactory.createEmptyBorder());
                        ok_button.addMouseListener(new MouseListener() {

                            @Override
                            public void mouseClicked(MouseEvent me) {
                            }

                            @Override
                            public void mousePressed(MouseEvent me) {
                                ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_oc.png").getImage());
                                ok_button.repaint();
                            }

                            @Override
                            public void mouseReleased(MouseEvent me) {
                                ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_button.png").getImage());
                                ok_button.repaint();
                            }

                            @Override
                            public void mouseEntered(MouseEvent me) {
                                jp.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                                jp.repaint();
                                ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_oh.png").getImage());
                                ok_button.repaint();
                            }

                            @Override
                            public void mouseExited(MouseEvent me) {
                                jp.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                                jp.repaint();
                                ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_button.png").getImage());
                                ok_button.repaint();
                            }
                        });
                        ok_button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                pwdd.setText("");
                                jp.remove(status);
                                jp.remove(ok_button);
                                jp.remove(error);
                                jp.repaint();
                            }
                        });
                        jp.add(ok_button);
                        jp.repaint();

                    }
                }
            }
        });
        
    jp.add(next);
    jp.repaint();
    
    VMButton back=new VMButton();
    back.setBounds(1150, 550, 70, 70);
    back.setImage(new ImageIcon(Mypath+"Resoures\\back.png").getImage());
    back.setBorder(BorderFactory.createEmptyBorder());
    back.addMouseListener(new MouseListener() 
    {
            @Override
            public void mouseClicked(MouseEvent me) 
            {}

            @Override
            public void mousePressed(MouseEvent me) 
            {   
                back.setImage(new ImageIcon(Mypath+"Resoures\\back_oc.png").getImage());
                back.repaint();
                jp.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) 
            {
                back.setImage(new ImageIcon(Mypath+"Resoures\\back.png").getImage());
                back.repaint();
                jp.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) 
            {
                back.setImage(new ImageIcon(Mypath+"Resoures\\back_oh.png").getImage());
                back.repaint();
                jp.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                jp.repaint();
                jp.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                back.setImage(new ImageIcon(Mypath+"Resoures\\back.png").getImage());
                back.repaint();
                jp.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                jp.repaint();
            }
        });
    back.addActionListener(new ActionListener() 
    {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                jf.getContentPane().removeAll();
                jf.repaint();
                System.gc();
                new LogScreenUser().LogScreenUserPanel();
            }
        });
    jp.add(back);
    jp.repaint();
    }
    
    public void blurit(JLabel l,ImagePanel p)
    {
        l.addMouseListener(new MouseListener() 
        {
            
            @Override
            public void mouseClicked(MouseEvent me){}
            @Override
            public void mousePressed(MouseEvent me) {}
            @Override
            public void mouseReleased(MouseEvent me) {}

            @Override
            public void mouseEntered(MouseEvent me) 
            {
                p.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                p.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                p.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                p.repaint();
                p.repaint();
            }
        });
    }
    
    public void blurit(JPasswordField l,ImagePanel p)
    {
        l.addMouseListener(new MouseListener() 
        {
            
            @Override
            public void mouseClicked(MouseEvent me){}
            @Override
            public void mousePressed(MouseEvent me) {}
            @Override
            public void mouseReleased(MouseEvent me) {}

            @Override
            public void mouseEntered(MouseEvent me) 
            {
                p.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                p.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                p.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                p.repaint();
            }
        });
    }
    
}
