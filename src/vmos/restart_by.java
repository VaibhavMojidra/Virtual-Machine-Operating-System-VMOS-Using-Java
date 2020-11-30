package vmos;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import static vmos.StartScreen.jf;

public class restart_by {

    static int counter = 0;

    public restart_by() {
        JPanel jbp = new JPanel();
        jbp.setLayout(null);
        jbp.setSize(1280, 680);
        jbp.setBackground(Color.BLACK);
        jf.add(jbp);
        jf.repaint();

        JProgressBar pro = new JProgressBar();
        pro.setBounds(340, 430, 600, 50);
        pro.setForeground(new java.awt.Color(0, 204, 255));
        pro.setValue(50);
        pro.setBorder(BorderFactory.createBevelBorder(1));
        jbp.add(pro);
        jbp.repaint();

        Timer timr = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pro.setValue(counter);
                pro.repaint();
                if (counter == 100) {
                    jf.getContentPane().removeAll();
                    jf.repaint();
                    System.gc();
                    new LogScreenUser().LogScreenUserPanel();
                    jf.repaint();
                    timr.cancel();
                }
            }
        };
        timr.schedule(task, 000, 20);
    }

}
