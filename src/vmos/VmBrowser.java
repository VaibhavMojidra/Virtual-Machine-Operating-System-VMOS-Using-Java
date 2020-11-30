package vmos;

import static vmos.Static_Objects_Methods_Reuasble.Mypath;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class VmBrowser extends JInternalFrame
{
    public VmBrowser()
    {
        try {
            setTitle("VM Browser");
            setBounds(100,100, 750, 500);
            setClosable(true);
            setIcon(true);
            setMaximizable(true);
            setResizable(false);
            setFrameIcon(new ImageIcon(Mypath+"Resoures\\Vm_player_Logo"));
            setBackground(new java.awt.Color(73, 161, 255, 40));
            
            JPanel jp1=new JPanel();
            jp1.setBackground(Color.WHITE);
            jp1.setLayout(null);
            add(jp1);
            repaint();
            
            VMButton google=new VMButton();
            google.setBounds(70,40,250,80);
            google.setImage(new ImageIcon(Mypath+"Resoures\\google.png").getImage());
            google.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            google.setToolTipText("Google search engine");
            google.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    remove(jp1);
                    repaint();
                    bro("https://www.google.com/");
                }
            });
            jp1.add(google);
            jp1.repaint();
            
            VMButton yaho=new VMButton();
            yaho.setBounds(420,40,250,80);
            yaho.setImage(new ImageIcon(Mypath+"Resoures\\yahoo.png").getImage());
            yaho.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            yaho.setToolTipText("Yahoo search engine");
            yaho.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    remove(jp1);
                    repaint();
                    bro("https://in.yahoo.com/");
                }
            });
            jp1.add(yaho);
            jp1.repaint();
            
            VMButton bing=new VMButton();
            bing.setImage(new ImageIcon(Mypath+"Resoures\\bing.png").getImage());
            bing.setBounds(70,180,250,80);
            bing.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            bing.setToolTipText("Bing search engine");
            bing.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    remove(jp1);
                    repaint();
                    bro("https://www.bing.com/");
                }
            });
            jp1.add(bing);
            jp1.repaint();
            
            VMButton blackle=new VMButton();
            blackle.setImage(new ImageIcon(Mypath+"Resoures\\blackle.png").getImage());
            blackle.setBounds(420,180,250,80);
            blackle.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            blackle.setToolTipText("Blackle search engine");
            blackle.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    remove(jp1);
                    repaint();
                    bro("http://www.blackle.com/");
                }
            });
            jp1.add(blackle);
            jp1.repaint();
            
            VMButton ask=new VMButton();
            ask.setImage(new ImageIcon(Mypath+"Resoures\\ask.png").getImage());
            ask.setBounds(70,320,250,80);
            ask.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            ask.setToolTipText("Ask search engine");
            ask.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    remove(jp1);
                    repaint();
                    bro("http://www.ask.com/");
                }
            });
            jp1.add(ask);
            jp1.repaint();
            
            JButton other=new JButton("Other");
            JTextField tbbb=new JTextField();
            other.setLayout(null);
            tbbb.setFont(new Font("Bell MT",Font.PLAIN,23));
            JLabel ll=new JLabel("Enter Other Search Engine");
            ll.setFont(new Font("Baskerville Old Face",Font.ITALIC,15));
            ll.setBounds(0, 4, 230, 22);
            ll.setForeground(Color.decode("#3d5c5c"));
            tbbb.add(ll);
            tbbb.setBounds(10,25 ,230,30);
            tbbb.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent fe) 
            {
                tbbb.remove(ll);
                tbbb.repaint();
                other.repaint();
            }

            @Override
            public void focusLost(FocusEvent fe) 
            {
                if(tbbb.getText().equals(""))
                {
                    tbbb.add(ll);
                    tbbb.repaint();
                    other.repaint();
                }
            }
        });
            other.setBounds(420,320,250,80);
            other.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    remove(jp1);
                    repaint();
                    bro(("http://"+tbbb.getText()+"/").trim());
                }
            });
            other.setToolTipText("Other search engine");
            other.add(tbbb);
            jp1.add(other);
            jp1.repaint();
            
        } catch (Exception e) {
        }
        

        
    }
    
    public void bro(String url)
    {
                try{
         
        setBounds(100,100, 750, 500);
        setClosable(true);
        setIcon(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new ImageIcon(Mypath+"Resoures\\Vm_player_Logo"));
        JPanel ctp=new JPanel();
        ctp.setBorder(new EmptyBorder(5, 5, 5, 5));
        ctp.setLayout(new BorderLayout(0, 0));
        add(ctp);
        
        JPanel p=new JPanel();
        ctp.add(p,BorderLayout.CENTER);
        p.setLayout(new BorderLayout(0, 0));
        
        JWebBrowser b=new JWebBrowser();
        b.navigate(url);
        p.add(b,BorderLayout.CENTER);
        } catch (Exception e) {
        }
    }
    


    
    
    
}
