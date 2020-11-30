package vmos;

import static vmos.Static_Objects_Methods_Reuasble.Mypath;
import static vmos.Static_Objects_Methods_Reuasble.MypathForHTML;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.html.HTMLDocument;


public class VM_HTML_Tester 
{
    protected void VM_HTML_Testerp(ImageDesktopPanel idp)
    {
        try {
        JInternalFrame jif=new JInternalFrame("VM HTML Tester");
        jif.setFrameIcon(new ImageIcon(Mypath+"Resoures\\VMH_Logo.png"));
        jif.setBounds(100, 50,800,410);
        jif.setLayout(null);
        jif.setVisible(true);
        jif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jif.setBackground(new java.awt.Color(73, 161, 255, 40));
        jif.repaint();
        jif.setClosable(true);
        jif.setIconifiable(true);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial Rounded MT", Font.PLAIN, 15));
        JScrollPane Scroller = new JScrollPane(textArea);
        Scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        Scroller.setBounds(2, 5,249, 345);
        Scroller.setBorder(BorderFactory.createEtchedBorder(0));
        jif.add(Scroller);
        jif.repaint();
        
        FileReader r = new FileReader(Mypath+"Resoures\\Tester.html");
        textArea.read(r, null);
        r.close();
        
        JEditorPane hf=new JEditorPane();
        hf.setPage(MypathForHTML+"Resoures/Tester.html");
        hf.repaint();
        hf.revalidate();
        
        JScrollPane VM_Text_Editor_Scroller = new JScrollPane(hf);
        VM_Text_Editor_Scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        VM_Text_Editor_Scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        VM_Text_Editor_Scroller.setBounds(255, 5, 527, 345);
        VM_Text_Editor_Scroller.setBorder(BorderFactory.createBevelBorder(0));
        jif.add(VM_Text_Editor_Scroller);
        jif.repaint();
        jif.revalidate();
        
        JLabel code=new JLabel("Code",JLabel.CENTER);
        code.setBounds(0, 351, 199, 30);
        code.setFont(new Font("Arial",Font.BOLD,20));
        code.setForeground(Color.BLACK);
        jif.add(code);
        
        JLabel output=new JLabel("Output",JLabel.CENTER);
        output.setBounds(301, 351, 495, 30);
        output.setFont(new Font("Arial",Font.BOLD,20));
        output.setForeground(Color.BLACK);
        jif.add(output);
        
        JButton ref=new JButton("Refresh");
        ref.setBounds(200, 351, 100, 30);
        ref.setFont(new Font("Arial",Font.BOLD,20));
        ref.setBorder(BorderFactory.createBevelBorder(0));
        ref.setForeground(Color.BLACK);
        ref.setBackground(Color.decode("#4F81BD"));
        ref.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                ref.setBackground(Color.decode("#197BF1"));
                ref.repaint();
            }

            @Override
            public void mousePressed(MouseEvent me){}

            @Override
            public void mouseReleased(MouseEvent me) {}

            @Override
            public void mouseEntered(MouseEvent me) 
            {
                ref.setBackground(Color.decode("#3883DE"));
                ref.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                ref.setBackground(Color.decode("#4F81BD"));
                ref.repaint();
            }

        });
        ref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                try {
                FileWriter w = new FileWriter(Mypath+"Resoures\\Tester.html");
                textArea.write(w);
                w.close();
                   hf.setDocument(new HTMLDocument());
                   hf.setPage(MypathForHTML+"Resoures/Tester.html");
                   hf.repaint();
                   hf.revalidate();
                } catch (Exception e) {
                }
            }
        });
        jif.add(ref);
        jif.repaint();
        idp.add(jif);
        
        jif.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                try {
                FileWriter ww= new FileWriter(Mypath+"Resoures\\Tester.html");
                JTextArea text = new JTextArea("<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"	<title>\n" +
"		<h1><font color='red'>Welcome to VMOS</font></h1>\n" +
"	</title>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"</body>\n" +
"</html>");
                text.write(ww);
                ww.close();
                    
                jif.removeAll();
                                jif.dispose();
                System.gc();
                idp.repaint();
                } catch (Exception e) {
                }
                
            }
        });
        
        } catch (Exception e) {
        }
        
        
        
        
    }
    
}
