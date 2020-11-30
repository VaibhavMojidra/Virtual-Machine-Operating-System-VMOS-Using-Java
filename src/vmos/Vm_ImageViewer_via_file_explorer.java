package vmos;

import static vmos.Static_Objects_Methods_Reuasble.Mypath;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class Vm_ImageViewer_via_file_explorer {

    private JMenuItem open;
    private JFileChooser fc;
    private JLabel jl;

    protected void viv(String path, ImageDesktopPanel jpp) {
        JInternalFrame jif = new JInternalFrame("VM ImageViewer", false, true, false, true);
        jif.setVisible(true);
        jif.repaint();
        jif.setBounds(300, 50, 700, 450);
        jif.setBackground(new java.awt.Color(73, 161, 255, 40));
        jif.setFrameIcon(new ImageIcon(Mypath+"Resoures\\VMIV_Logo.png"));
        jif.setLayout(new FlowLayout());
        jpp.add(jif);
        jpp.repaint();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        open = new JMenuItem("Open");
        menu1.add(open);
        menuBar.add(menu1);
        jif.setJMenuBar(menuBar);
        jif.repaint();

        jl = new JLabel();
        jif.add(jl);
        jif.repaint();

        fc = new JFileChooser("d:");

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int getfile;
                if (ae.getSource() == open) {
                    getfile = fc.showOpenDialog(jif);
                    if (getfile == JFileChooser.APPROVE_OPTION) {
                        try {
                            String Imagepath = fc.getSelectedFile().getPath();
                            BufferedImage temp = ImageIO.read(new File(Imagepath));
                            int ow = temp.getWidth();
                            int oh = temp.getHeight();
                            int sw = 800;
                            int sh = (sw * oh) / ow;
                            BufferedImage img = new ImgUtils().scaleImage(sw, sh, Imagepath);
                            jl.setIcon(new ImageIcon(img));
                            jif.setBounds(100, 100, sw + 30, sh + 75);
                            jif.repaint();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        });

        try {
            BufferedImage temp = ImageIO.read(new File(path));
            int ow = temp.getWidth();
            int oh = temp.getHeight();
            int sw = 800;
            int sh = (sw * oh) / ow;
            BufferedImage img = new ImgUtils().scaleImage(sw, sh, path);
            jl.setIcon(new ImageIcon(img));
            jif.setBounds(100, 100, sw + 30, sh + 75);
            jif.repaint();
        } catch (Exception e) {
        }

        jif.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                jif.removeAll();
                System.gc();
            }
        });
    }
}
