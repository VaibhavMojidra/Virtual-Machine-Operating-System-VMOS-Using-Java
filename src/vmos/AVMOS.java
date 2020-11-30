/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmos;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import static vmos.Static_Objects_Methods_Reuasble.Mypath;
import static vmos.Static_Objects_Methods_Reuasble.MypathForHTML;
/**
 *
 * @author admin
 */
public class AVMOS {

    public AVMOS(ImageDesktopPanel idp) {
        try {
            JInternalFrame jif = new JInternalFrame("About VMOS");
            jif.setFrameIcon(new ImageIcon(Mypath+"Resoures\\VMINF_Logo.png"));
            jif.setBounds(100, 50, 543, 372);
            jif.setLayout(null);
            jif.setVisible(true);
            jif.setBackground(new java.awt.Color(73, 161, 255, 40));
            jif.repaint();
            jif.setClosable(true);
            jif.setIconifiable(true);

            JEditorPane hf = new JEditorPane();
            hf.setPage(MypathForHTML+"Resoures/VMOS_Info.html");
            hf.repaint();
            hf.revalidate();

            JScrollPane VM_Text_Editor_Scroller = new JScrollPane(hf);
            VM_Text_Editor_Scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            VM_Text_Editor_Scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            VM_Text_Editor_Scroller.setBounds(0, 0, 527, 345);
            jif.add(VM_Text_Editor_Scroller);
            jif.repaint();
            jif.revalidate();

            idp.add(jif);
            idp.repaint();

            jif.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    jif.removeAll();
                    jif.dispose();
                    System.gc();
                    idp.repaint();

                }
            });
        } catch (Exception e) {
        }

    }

}
