package vmos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static vmos.StartScreen.jf;

public class LogScreenUser extends Static_Objects_Methods_Reuasble
{
    JLabel error;
    static String logged_User="vaibhav";
    protected void LogScreenUserPanel()
    {
        logged_User=null;
        
        
        ImagePanel jp=new ImagePanel();
        jp.setImage(new ImageIcon(Mypath+"Resoures\\logubg.jpg").getImage());
        jp.setVisible(true);
        jp.setLayout(null);
        jp.setSize(1280,680);
        jf.add(jp);
        jp.repaint();
        jf.repaint();
        
       
            
        
        try{
        JLabel acc_icon =new JLabel("",JLabel.CENTER);
        acc_icon.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\user_icon.png"))));
        acc_icon.setBounds(492, 289,25,25);
        acc_icon.setBackground(Color.WHITE);
        acc_icon.setBorder(BorderFactory.createBevelBorder(0));
        acc_icon.setOpaque(true);
        blurit(acc_icon, jp);
        jp.add(acc_icon,SwingConstants.CENTER);
        jf.repaint();
        }catch(Exception e){}
        
        JTextField usertb = new JTextField();
        usertb.setBounds(526,289,230,27);
        usertb.setBorder(BorderFactory.createBevelBorder(1));
        usertb.setFont(new Font("Bell MT",Font.PLAIN,22));
        JLabel ll=new JLabel(" Username");
        ll.setFont(new Font("Baskerville Old Face",Font.ITALIC,20));
        ll.setBounds(0, 4, 100, 22);
        ll.setForeground(Color.decode("#3d5c5c"));
        blurit(usertb, jp);
        usertb.add(ll);
        usertb.repaint();
        usertb.addKeyListener(new KeyAdapter() 
        {
            public void keyTyped(KeyEvent e) {
            char keyChar = e.getKeyChar();
            if (Character.isLowerCase(keyChar))
            {
                e.setKeyChar(Character.toUpperCase(keyChar));
            }
        }
     });
        usertb.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent fe) 
            {
                usertb.remove(ll);
                usertb.repaint();
                jp.repaint();
            }

            @Override
            public void focusLost(FocusEvent fe) 
            {
                if(usertb.getText().equals(""))
                {
                    usertb.add(ll);
                    usertb.repaint();
                    jp.repaint();
                }
            }
        });
        jp.add(usertb);
        jp.repaint();
        
        
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
    next.setBounds(761, 287, 31, 31);
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
                jp.setImage(new ImageIcon(Mypath+"Resoures\\logubg_blur.jpg").getImage());
                jp.repaint();
                next.setImage(new ImageIcon(Mypath+"Resoures\\next_oh.png").getImage());
                next.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                jp.setImage(new ImageIcon(Mypath+"Resoures\\logubg.jpg").getImage());
                jp.repaint();
                next.setImage(new ImageIcon(Mypath+"Resoures\\next.png").getImage());
                next.repaint();
            }
        });
        VM_Label status = new VM_Label("Invalid Username", JLabel.CENTER);
        next.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                if(status.getParent()==null)
                {
                    boolean res = false;
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection connn = DriverManager.getConnection("jdbc:sqlserver://localhost\\VAIBHAV:1433;databaseName=VMOS", "sa", "xxxxxx");
                        Statement smmt = connn.createStatement();
                        ResultSet rss = smmt.executeQuery("select * from userdetails where username='" + usertb.getText().trim() + "'");
                        if (rss.next()) {
                            res = true;
                        } else {
                            res = false;
                        }
                    } catch (Exception e) {
                        res = false;
                    }
                    if (res) 
                    {
                        logged_User=usertb.getText().trim();
                        jf.getContentPane().removeAll();
                        jf.repaint();
                        System.gc();
                        new PasswordScreen().PasswordScreenPanel();
                        
                    } else {

                        GradientPaint gpp = new GradientPaint(0, 0, Color.decode("#f44141"), 100, 50, Color.decode("#F1948A"), true);
                        status.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
                        status.setEffectIndex(VM_Label.EFFECT_COLOR_ANIMATION);
                        status.setGradient(gpp);
                        status.startAnimation(80);
                        status.setBounds(540, 360, 240, 30);
                        blurit(status, jp);
                        jp.add(status);
                        jp.repaint();

                        try {
                            error = new JLabel("", JLabel.CENTER);
                            error.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\error.png"))));
                            error.setBounds(500, 357, 30, 30);
                            error.setBackground(Color.WHITE);
                            error.setBorder(BorderFactory.createBevelBorder(0));
                            error.setOpaque(true);
                            blurit(error, jp);
                            jp.add(error, SwingConstants.CENTER);
                            jf.repaint();
                        } catch (Exception e) { }
                        
                        VMButton ok_button = new VMButton();
                        ok_button.setBounds(605, 420, 70, 22);
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
                                jp.setImage(new ImageIcon(Mypath+"Resoures\\logubg_blur.jpg").getImage());
                                jp.repaint();
                                ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_oh.png").getImage());
                                ok_button.repaint();
                            }

                            @Override
                            public void mouseExited(MouseEvent me) {
                                jp.setImage(new ImageIcon(Mypath+"Resoures\\logubg.jpg").getImage());
                                jp.repaint();
                                ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_button.png").getImage());
                                ok_button.repaint();
                            }
                        });
                        ok_button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                usertb.setText(null);
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
                p.setImage(new ImageIcon(Mypath+"Resoures\\logubg_blur.jpg").getImage());
                p.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                p.setImage(new ImageIcon(Mypath+"Resoures\\logubg.jpg").getImage());
                p.repaint();
                p.repaint();
            }
        });
    }
    
    public void blurit(JTextField l,ImagePanel p)
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
                p.setImage(new ImageIcon(Mypath+"Resoures\\logubg_blur.jpg").getImage());
                p.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                p.setImage(new ImageIcon(Mypath+"Resoures\\logubg.jpg").getImage());
                p.repaint();
                p.repaint();
            }
        });
    
    }
    
    
}
