package vmos;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import static vmos.Static_Objects_Methods_Reuasble.Mypath;
public class calculator extends JInternalFrame implements ActionListener {

    static JInternalFrame f;

    static JTextField l;

    String s0, s1, s2;

    calculator() {
        s0 = s1 = s2 = "";
    }

    public void m(ImageDesktopPanel idp) {
        
        f = new JInternalFrame("VM Calculator");
        f.setIconifiable(true);
        f.setClosable(true);
        f.setBounds(400, 200, 200, 220);
        f.setFrameIcon(new ImageIcon(Mypath+"Resoures\\VM_cal_Logo.png"));

        calculator c = new calculator();

        l = new JTextField(16);
        l.setEditable(false);
        l.setBackground(Color.WHITE);

        JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, be, beq, beq1;

        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        beq1 = new JButton("=");

        ba = new JButton("+");
        bs = new JButton("-");
        bd = new JButton("/");
        bm = new JButton("*");
        beq = new JButton("C");

        be = new JButton(".");

        JPanel p = new JPanel();

        bm.addActionListener(c);
        bd.addActionListener(c);
        bs.addActionListener(c);
        ba.addActionListener(c);
        b9.addActionListener(c);
        b8.addActionListener(c);
        b7.addActionListener(c);
        b6.addActionListener(c);
        b5.addActionListener(c);
        b4.addActionListener(c);
        b3.addActionListener(c);
        b2.addActionListener(c);
        b1.addActionListener(c);
        b0.addActionListener(c);
        be.addActionListener(c);
        beq.addActionListener(c);
        beq1.addActionListener(c);

        p.add(l);
        p.add(ba);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(bs);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.add(bm);
        p.add(b7);
        p.add(b8);
        p.add(b9);
        p.add(bd);
        p.add(be);
        p.add(b0);
        p.add(beq);
        p.add(beq1);

        p.setBackground(new java.awt.Color(73, 161, 255, 40));

        f.setBackground(new java.awt.Color(73, 161, 255, 0));
        f.add(p);

        f.setSize(200, 220);
        f.show();
        idp.add(f);
        f.addInternalFrameListener(new InternalFrameAdapter() {
                        @Override
                        public void internalFrameClosing(InternalFrameEvent ife) {
                            f.removeAll();
                            System.gc();
                        }
                    });
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String s = e.getActionCommand();

            if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {

                if (!s1.equals("")) {
                    s2 = s2 + s;
                } else {
                    s0 = s0 + s;
                }

                l.setText(s0 + s1 + s2);
            } else if (s.charAt(0) == 'C') {

                s0 = s1 = s2 = "";

                l.setText(s0 + s1 + s2);
            } else if (s.charAt(0) == '=') {

                double te;

                if (s1.equals("+")) {
                    te = (Double.parseDouble(s0) + Double.parseDouble(s2));
                } else if (s1.equals("-")) {
                    te = (Double.parseDouble(s0) - Double.parseDouble(s2));
                } else if (s1.equals("/")) {
                    te = (Double.parseDouble(s0) / Double.parseDouble(s2));
                } else {
                    te = (Double.parseDouble(s0) * Double.parseDouble(s2));
                }

                l.setText(s0 + s1 + s2 + "=" + te);

                s0 = Double.toString(te);

                s1 = s2 = "";
            } else {

                if (s1.equals("") || s2.equals("")) {
                    s1 = s;
                } else {
                    double te;

                    if (s1.equals("+")) {
                        te = (Double.parseDouble(s0) + Double.parseDouble(s2));
                    } else if (s1.equals("-")) {
                        te = (Double.parseDouble(s0) - Double.parseDouble(s2));
                    } else if (s1.equals("/")) {
                        te = (Double.parseDouble(s0) / Double.parseDouble(s2));
                    } else {
                        te = (Double.parseDouble(s0) * Double.parseDouble(s2));
                    }

                    s0 = Double.toString(te);

                    s1 = s;

                    s2 = "";
                }

                l.setText(s0 + s1 + s2);
            }
        } catch (Exception ewww) {
        }

    }
}
