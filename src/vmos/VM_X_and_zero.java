package vmos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import static vmos.Static_Objects_Methods_Reuasble.Mypath;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VM_X_and_zero extends JInternalFrame implements ItemListener, ActionListener {

    int i, j, ii, jj, x, y, yesnull;
    int a[][] = {{10, 1, 2, 3, 11}, {10, 1, 4, 7, 11}, {10, 1, 5, 9, 11}, {10, 2, 5, 8, 11},
    {10, 3, 5, 7, 11}, {10, 3, 6, 9, 11}, {10, 4, 5, 6, 11},
    {10, 7, 8, 9, 11}};
    int a1[][] = {{10, 1, 2, 3, 11}, {10, 1, 4, 7, 11}, {10, 1, 5, 9, 11}, {10, 2, 5, 8, 11},
    {10, 3, 5, 7, 11}, {10, 3, 6, 9, 11}, {10, 4, 5, 6, 11}, {10, 7, 8, 9, 11}};

    boolean state, type, set;

    Icon ic1, ic2, icon, ic11, ic22;
    Checkbox c1, c2;
    JLabel l1, l2;
    JButton b[] = new JButton[9];
    JButton reset;

    public void showButton() {

        x = 10;
        y = 10;
        j = 0;
        for (i = 0; i <= 8; i++, x += 100, j++) {
            b[i] = new JButton();
            if (j == 3) {
                j = 0;
                y += 100;
                x = 10;
            }
            b[i].setBounds(x, y, 100, 100);
            add(b[i]);
            b[i].addActionListener(this);
        }

        reset = new JButton("RESET");
        reset.setBounds(100, 350, 100, 50);
        reset.setBorder(BorderFactory.createBevelBorder(0));
        reset.addActionListener(this);
        reset.setForeground(Color.BLACK);
        reset.setBackground(Color.decode("#4F81BD"));
        reset.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                reset.setBackground(Color.decode("#197BF1"));
                reset.repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                reset.setBackground(Color.decode("#3883DE"));
                reset.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                reset.setBackground(Color.decode("#4F81BD"));
                reset.repaint();
            }

        });

        add(reset);

    }

    public void check(int num1) {
        for (ii = 0; ii <= 7; ii++) {
            for (jj = 1; jj <= 3; jj++) {
                if (a[ii][jj] == num1) {
                    a[ii][4] = 11;
                }

            }

        }
    }

    public void complogic(int num) {

        for (i = 0; i <= 7; i++) {
            for (j = 1; j <= 3; j++) {
                if (a[i][j] == num) {
                    a[i][0] = 11;
                    a[i][4] = 10;
                }
            }
        }
        for (i = 0; i <= 7; i++) {
            set = true;
            if (a[i][4] == 10) {
                int count = 0;
                for (j = 1; j <= 3; j++) {
                    if (b[(a[i][j] - 1)].getIcon() != null) {
                        count++;
                    } else {
                        yesnull = a[i][j];
                    }
                }
                if (count == 2) {
                    b[yesnull - 1].setIcon(ic2);
                    this.check(yesnull);
                    set = false;
                    break;
                }
            } else if (a[i][0] == 10) {
                for (j = 1; j <= 3; j++) {
                    if (b[(a[i][j] - 1)].getIcon() == null) {
                        b[(a[i][j] - 1)].setIcon(ic2);
                        this.check(a[i][j]);
                        set = false;
                        break;
                    }
                }
                if (set == false) {
                    break;
                }
            }

            if (set == false) {
                break;
            }
        }

    }

    VM_X_and_zero() {
        super("VM Tic Tac Toe Game");

        CheckboxGroup cbg = new CheckboxGroup();
        c1 = new Checkbox("Vs Computer", cbg, false);
        c1.setFont(new Font("Bell MT", Font.PLAIN, 15));
        c2 = new Checkbox("Vs Friend", cbg, false);
        c2.setFont(new Font("Bell MT", Font.PLAIN, 15));
        c1.setBounds(120, 80, 110, 40);
        c2.setBounds(120, 150, 100, 40);
        add(c1);
        add(c2);
        c1.addItemListener(this);
        c2.addItemListener(this);

        state = true;
        type = true;
        set = true;
        ic1 = new ImageIcon(Mypath+"Resoures\\x.jpg");
        ic2 = new ImageIcon(Mypath+"Resoures\\zero.jpg");
        ic11 = new ImageIcon(Mypath+"Resoures\\x_win.jpg");
        ic22 = new ImageIcon(Mypath+"Resoures\\zero_win.jpg");
        setResizable(false);
        setIconifiable(true);
        setClosable(true);
        setLayout(null);
        setBounds(200, 50, 335, 450);
        setVisible(true);
        setFrameIcon(new ImageIcon(Mypath+"Resoures\\VM_TTT_Logo.png"));
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void itemStateChanged(ItemEvent e) {
        if (c1.getState()) {
            type = false;
        } else if (c2.getState()) {
            type = true;
        }
        remove(c1);
        remove(c2);
        repaint(0, 0, 330, 450);
        showButton();
    }

    public void actionPerformed(ActionEvent e) {

        if (type == true) {

            if (e.getSource() == reset) {

                for (i = 0; i <= 8; i++) {
                    b[i].setIcon(null);
                }
            } else {

                for (i = 0; i <= 8; i++) {
                    if (e.getSource() == b[i]) {

                        if (b[i].getIcon() == null) {
                            if (state == true) {
                                icon = ic2;
                                state = false;
                            } else {
                                icon = ic1;
                                state = true;
                            }
                            b[i].setIcon(icon);
                        }
                    }
                }
            }
        } else if (type == false) {
            if (e.getSource() == reset) {
                for (i = 0; i <= 8; i++) {
                    b[i].setIcon(null);
                }
                for (i = 0; i <= 7; i++) {
                    for (j = 0; j <= 4; j++) {
                        a[i][j] = a1[i][j];
                    }
                }
            } else {
                for (i = 0; i <= 8; i++) {
                    if (e.getSource() == b[i]) {
                        if (b[i].getIcon() == null) {
                            b[i].setIcon(ic1);
                            if (b[4].getIcon() == null) {
                                b[4].setIcon(ic2);
                                this.check(5);
                            } else {
                                this.complogic(i);
                            }
                        }
                    }
                }
            }
        }

        for (i = 0; i <= 7; i++) {

            Icon icon1 = b[(a[i][1] - 1)].getIcon();
            Icon icon2 = b[(a[i][2] - 1)].getIcon();
            Icon icon3 = b[(a[i][3] - 1)].getIcon();
            if ((icon1 == icon2) && (icon2 == icon3) && (icon1 != null)) {
                if (icon1 == ic1) {
                    b[(a[i][1] - 1)].setIcon(ic11);
                    b[(a[i][2] - 1)].setIcon(ic11);
                    b[(a[i][3] - 1)].setIcon(ic11);
                    JOptionPane.showMessageDialog(VM_X_and_zero.this, "!!!Player 2 Won!!! click reset");
                    break;
                } else if (icon1 == ic2) {
                    b[(a[i][1] - 1)].setIcon(ic22);
                    b[(a[i][2] - 1)].setIcon(ic22);
                    b[(a[i][3] - 1)].setIcon(ic22);
                    JOptionPane.showMessageDialog(VM_X_and_zero.this, "!!!Player 1 Won!!! click reset");
                    break;
                }
            }
        }

    }

}
