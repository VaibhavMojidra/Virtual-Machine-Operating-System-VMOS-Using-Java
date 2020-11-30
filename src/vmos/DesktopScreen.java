package vmos;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import java.awt.Color;
import java.awt.EventQueue;
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
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.MouseInputListener;
import static vmos.LogScreenUser.logged_User;
import static vmos.StartScreen.jf;

public class DesktopScreen extends Static_Objects_Methods_Reuasble {

    static int wc = 1, uc = 30, scount = 0, sh = 10, sw = 10, sjw = 635, sjh = 165;
    static boolean check_d = false, sleeppa = false, checckdp = true, sisnd = true;
    ImageDesktopPanel jp = new ImageDesktopPanel();

    public void DesktopScreenPanel() {

        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();

        jp.setVisible(true);
        jp.setLayout(null);
        jp.setBounds(0, 0, 1280, 680);
        jf.add(jp);
        jp.repaint();
        jf.repaint();

        start_s();
        Timer timr = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                jp.setImage(new ImageIcon(Mypath+"Wallpapers\\" + wc + ".jpg").getImage());
                jp.repaint();
                jf.repaint();
                wc++;
                if (wc == 31) {
                    wc = 1;
                }
            }
        };

        timr.schedule(task, 000, 10000);

        ImagePanel jssp = new ImagePanel();
        jssp.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
        jssp.setVisible(true);
        jssp.setLayout(null);
        jssp.setSize(1280, 680);
        jssp.repaint();

        JLabel time_d = new JLabel("", JLabel.CENTER);
        time_d.setBackground(Color.decode("#197BF1"));
        time_d.setLayout(null);
        time_d.setOpaque(true);
        time_d.setBorder(BorderFactory.createBevelBorder(0));
        time_d.setBounds(850, 15, 400, 120);

        JLabel time = new JLabel();
        time.setForeground(Color.WHITE);
        time.setBounds(20, 5, 200, 100);
        time.setFont(new Font("Digital-7 Mono", Font.BOLD, 75));
        time_d.add(time);

        JLabel datee = new JLabel("", JLabel.CENTER);
        datee.setForeground(Color.WHITE);
        datee.setBounds(255, 0, 125, 59);
        datee.setFont(new Font("Digital-7 Mono", Font.BOLD, 45));
        time_d.add(datee);

        JLabel year = new JLabel("", JLabel.CENTER);
        year.setForeground(Color.WHITE);
        year.setBounds(255, 50, 125, 59);
        year.setFont(new Font("Digital-7 Mono", Font.BOLD, 60));
        time_d.add(year);

        Timer gt_d = new Timer();
        TimerTask getdatetask = new TimerTask() {
            @Override
            public void run() {
                Calendar cal = new GregorianCalendar();
                int h = cal.get(Calendar.HOUR);
                int m = cal.get(Calendar.MINUTE);
                int d = cal.get(Calendar.DAY_OF_MONTH);
                int mo = cal.get(Calendar.MONTH) + 1;
                int y = cal.get(Calendar.YEAR);
                if (h == 0 || h == 1 || h == 2 || h == 3 || h == 4 || h == 5 | h == 6 || h == 7 || h == 8 || h == 9) {
                    if (m == 0 || m == 1 || m == 2 || m == 3 || m == 4 || m == 5 | m == 6 || m == 7 || m == 8 || m == 9) {
                        time.setText("0" + h + ":0" + m);
                    } else {
                        time.setText("0" + h + ":" + m);
                    }
                } else {
                    if (m == 0 || m == 1 || m == 2 || m == 3 || m == 4 || m == 5 | m == 6 || m == 7 || m == 8 || m == 9) {
                        time.setText(h + ":0" + m);
                    } else {
                        time.setText(h + ":" + m);
                    }
                }
                if (mo == 0 || mo == 1 || mo == 2 || mo == 3 || mo == 4 || mo == 5 | mo == 6 || mo == 7 || mo == 8 || mo == 9) {
                    if (d == 0 || d == 1 || d == 2 || d == 3 || d == 4 || d == 5 | d == 6 || d == 7 || d == 8 || d == 9) {
                        datee.setText("0" + mo + ":0" + d);
                    } else {
                        datee.setText("0" + mo + ":" + d);
                    }
                } else {
                    if (d == 0 || d == 1 || d == 2 || d == 3 || d == 4 || d == 5 | d == 6 || d == 7 || d == 8 || d == 9) {
                        datee.setText(mo + ":0" + d);
                    } else {
                        datee.setText(mo + ":" + d);
                    }
                }

                time.repaint();
                datee.setText(d + "/" + mo);
                year.setText(y + "");
                System.gc();
            }
        };
        gt_d.schedule(getdatetask, 000, 1000);

        VMButton shicons = new VMButton();
        shicons.setBounds(600, 15, 120, 120);
        shicons.setImage(new ImageIcon(Mypath+"Resoures\\showi.png").getImage());
        shicons.setBorder(BorderFactory.createBevelBorder(0));

        VMButton sleep = new VMButton();
        sleep.setBounds(50, 15, 120, 120);
        sleep.setImage(new ImageIcon(Mypath+"Resoures\\sleep.png").getImage());
        vmo_b(sleep, "sleep");

        JPanel co = new JPanel();
        co.setBounds(390, 240, 500, 200);
        co.setLayout(null);
        co.setBorder(BorderFactory.createBevelBorder(0));
        co.setBackground(Color.decode("#91baf7"));

        VMButton sd = new VMButton();
        sd.setBounds(200, 15, 120, 120);
        sd.setImage(new ImageIcon(Mypath+"Resoures\\sd.png").getImage());
        vmo_b(sd, "sd");
        sd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                next_clk();
                if (checckdp) {
                    try {
                        jp.add(co, new Integer(1000));
                        checckdp = false;
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\Shutdown.wav").getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                        JLabel clabel = new JLabel("<html>Are you sure,You want to shut down?</html>", JLabel.CENTER);
                        clabel.setFont(new Font("Bell MT", Font.BOLD, 25));
                        clabel.setBounds(10, 10, 490, 100);
                        JButton yes = new JButton("YES");
                        yes.setBounds(125, 125, 100, 40);
                        yes.setFont(new Font("Bell MT", Font.BOLD, 20));
                        yes.setBorder(BorderFactory.createBevelBorder(0));
                        co.add(yes);
                        JButton no = new JButton("NO");
                        no.setBounds(275, 125, 100, 40);
                        no.setFont(new Font("Bell MT", Font.BOLD, 20));
                        no.setBorder(BorderFactory.createBevelBorder(0));
                        co.add(no);
                        co.add(clabel);
                        yes.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent kkae) {
                                clip.stop();
                                click();
                                checckdp = true;
                                co.removeAll();
                                jp.remove(co);
                                jp.repaint();
                                System.exit(0);
                                System.gc();
                            }
                        });
                        no.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent kkae) {
                                clip.stop();
                                click();
                                checckdp = true;
                                co.removeAll();
                                jp.remove(co);
                                jp.repaint();
                                System.gc();
                            }
                        });
                    } catch (Exception e) {
                    }

                }

            }
        });
        sd.setToolTipText("Shut Down");
        sd.repaint();

        VMButton rst = new VMButton();
        rst.setBounds(350, 15, 120, 120);
        rst.setImage(new ImageIcon(Mypath+"Resoures\\restart.png").getImage());
        vmo_b(rst, "restart");
        rst.setToolTipText("Restart");
        rst.repaint();
        rst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                next_clk();
                if (checckdp) {
                    try {
                        jp.add(co, new Integer(1000));
                        checckdp = false;
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\restart.wav").getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                        JLabel clabel = new JLabel("<html>Are you sure,You want to restart VMOS?</html>", JLabel.CENTER);
                        clabel.setFont(new Font("Bell MT", Font.BOLD, 25));
                        clabel.setBounds(10, 10, 490, 100);
                        JButton yes = new JButton("YES");
                        yes.setBounds(125, 125, 100, 40);
                        yes.setFont(new Font("Bell MT", Font.BOLD, 20));
                        yes.setBorder(BorderFactory.createBevelBorder(0));
                        co.add(yes);
                        JButton no = new JButton("NO");
                        no.setBounds(275, 125, 100, 40);
                        no.setFont(new Font("Bell MT", Font.BOLD, 20));
                        no.setBorder(BorderFactory.createBevelBorder(0));
                        co.add(no);
                        co.add(clabel);
                        yes.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent kkae) {
                                clip.stop();
                                click();
                                checckdp = true;
                                co.removeAll();
                                jp.remove(co);
                                jp.repaint();
                                jf.getContentPane().removeAll();
                                jf.repaint();
                                new restart_by();
                                System.gc();
                            }
                        });
                        no.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent kkae) {
                                clip.stop();
                                click();
                                checckdp = true;
                                co.removeAll();
                                jp.remove(co);
                                jp.repaint();
                                System.gc();
                            }
                        });
                    } catch (Exception e) {
                    }

                }

            }
        });

        JPanel ut = new JPanel();
        ut.setLayout(null);
        ut.setBounds(0, 0, 1280, 15);
        ut.setBorder(BorderFactory.createEmptyBorder());
        ut.setBackground(new Color(66, 134, 244, 105));
        JButton d = new JButton("");
        d.setFont(new Font("Arial", Font.PLAIN, 30));
        d.setBounds(0, 0, 1280, 15);
        ut.add(d);
        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (check_d) {
                    uc = 165;
                    Timer ut_d = new Timer();
                    TimerTask tasks = new TimerTask() {
                        @Override
                        public void run() {

                            if (uc == 165) {
                                ut.remove(sleep);
                                ut.remove(sd);
                                ut.remove(rst);
                                ut.remove(time_d);
                                ut.remove(shicons);
                                ut.repaint();
                            }
                            if (uc < 15) {
                                ut_d.cancel();
                            }
                            d.setBounds(0, uc - 15, 1280, 15);
                            ut.setBounds(0, 0, 1280, uc);
                            ut.repaint();
                            jp.repaint();
                            uc--;
                        }
                    };

                    ut_d.schedule(tasks, 000, 1);
                    check_d = false;
                } else {
                    uc = 15;
                    Timer ut_d = new Timer();
                    TimerTask taskss = new TimerTask() {
                        @Override
                        public void run() {
                            if (uc > 165) {
                                ut.add(sleep);
                                ut.add(sd);
                                ut.add(rst);
                                ut.add(time_d);
                                ut.add(shicons);
                                ut_d.cancel();
                                ut.repaint();
                            }
                            d.setBounds(0, uc - 15, 1280, 15);
                            ut.setBounds(0, 0, 1280, uc);
                            ut.repaint();
                            jp.repaint();
                            uc++;
                        }
                    };
                    ut_d.schedule(taskss, 000, 1);
                    check_d = true;
                }

            }
        });
        ut.repaint();
        jp.add(ut);
        jp.repaint();

        sleep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                next_clk();
                if (sleeppa) {

                } else {

                    if (check_d) {
                        uc = 165;
                        Timer ut_d = new Timer();
                        TimerTask taski = new TimerTask() {
                            @Override
                            public void run() {

                                if (uc == 165) {
                                    ut.remove(sleep);
                                    ut.remove(sd);
                                    ut.remove(rst);
                                    ut.remove(time_d);
                                    ut.remove(shicons);
                                    ut.repaint();
                                }
                                if (uc < 15) {
                                    ut_d.cancel();
                                }
                                d.setBounds(0, uc - 15, 1280, 15);
                                ut.setBounds(0, 0, 1280, uc);
                                ut.repaint();
                                jp.repaint();
                                uc--;
                            }
                        };

                        ut_d.schedule(taski, 000, 1);
                        check_d = false;
                    }
                    sleeppa = true;
                    PasswordSleep(jssp);
                    jp.add(jssp, new Integer(1000000));
                }

            }
        });
        sleep.setToolTipText("Sleep");
        sleep.repaint();

        JPanel strip = new JPanel();
        strip.setBounds(295, 500, 690, 75);
        strip.setBorder(BorderFactory.createEtchedBorder(new Color(255, 255, 255, 100), new Color(83, 83, 84, 200)));
        strip.setBackground(new Color(255, 255, 255, 85));
        strip.setOpaque(false);
        strip.setLayout(null);
        jp.repaint();
        jf.repaint();

        Timer sft = new Timer();
        TimerTask sftk = new TimerTask() {
            @Override
            public void run() {
                jp.repaint();
                jf.repaint();
                shicons.repaint();
                ut.repaint();
            }
        };
        sft.schedule(sftk, 000, 1);

        VMButton vm_player = new VMButton();
        vm_player.setBounds(20, 12, 50, 50);
        vm_player.setImage(new ImageIcon(Mypath+"Resoures\\Vm_player.png").getImage());
        vm_player.setBorder(BorderFactory.createEmptyBorder());
        vm_player.setOpaque(false);
        vm_player.setToolTipText("VM Player");
        vm_player.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                new VM_Player().VideoPlayer_app(jp);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                vm_player.setImage(new ImageIcon(Mypath+"Resoures\\Vm_player_oc.png").getImage());
                vm_player.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                vm_player.setImage(new ImageIcon(Mypath+"Resoures\\Vm_player_oh.png").getImage());
                vm_player.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                vm_player.setBounds(10, 2, 70, 70);
                vm_player.setImage(new ImageIcon(Mypath+"Resoures\\Vm_player_oh.png").getImage());
                vm_player.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                vm_player.setBounds(20, 12, 50, 50);
                vm_player.setImage(new ImageIcon(Mypath+"Resoures\\Vm_player.png").getImage());
                vm_player.repaint();
                strip.repaint();
            }
        });

        VMButton Vm_ImageViewer = new VMButton();
        Vm_ImageViewer.setBounds(80, 12, 50, 50);
        Vm_ImageViewer.setImage(new ImageIcon(Mypath+"Resoures\\Vm_ImageViewer.png").getImage());
        Vm_ImageViewer.setBorder(BorderFactory.createEmptyBorder());
        Vm_ImageViewer.setOpaque(false);
        Vm_ImageViewer.setToolTipText("VM Image Viewer");
        Vm_ImageViewer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                new VM_ImageViewer().VMImageViewer(jp);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                Vm_ImageViewer.setImage(new ImageIcon(Mypath+"Resoures\\Vm_ImageViewer_oc.png").getImage());
                Vm_ImageViewer.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                Vm_ImageViewer.setImage(new ImageIcon(Mypath+"Resoures\\Vm_ImageViewer_oh.png").getImage());
                Vm_ImageViewer.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                Vm_ImageViewer.setBounds(70, 2, 70, 70);
                Vm_ImageViewer.setImage(new ImageIcon(Mypath+"Resoures\\Vm_ImageViewer_oh.png").getImage());
                Vm_ImageViewer.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Vm_ImageViewer.setBounds(80, 12, 50, 50);
                Vm_ImageViewer.setImage(new ImageIcon(Mypath+"Resoures\\Vm_ImageViewer.png").getImage());
                Vm_ImageViewer.repaint();
                strip.repaint();
            }
        });

        VMButton Vm_Pad = new VMButton();
        Vm_Pad.setBounds(140, 12, 50, 50);
        Vm_Pad.setImage(new ImageIcon(Mypath+"Resoures\\Vm_Pad.png").getImage());
        Vm_Pad.setBorder(BorderFactory.createEmptyBorder());
        Vm_Pad.setOpaque(false);
        Vm_Pad.setToolTipText("VM Text Editor");
        Vm_Pad.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                new Vm_pad().VM_pade(jp);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                Vm_Pad.setImage(new ImageIcon(Mypath+"Resoures\\Vm_Pad_oc.png").getImage());
                Vm_Pad.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                Vm_Pad.setImage(new ImageIcon(Mypath+"Resoures\\Vm_Pad_oh.png").getImage());
                Vm_Pad.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                Vm_Pad.setBounds(130, 2, 70, 70);
                Vm_Pad.setImage(new ImageIcon(Mypath+"Resoures\\Vm_Pad_oh.png").getImage());
                Vm_Pad.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Vm_Pad.setBounds(140, 12, 50, 50);
                Vm_Pad.setImage(new ImageIcon(Mypath+"Resoures\\Vm_Pad.png").getImage());
                Vm_Pad.repaint();
                strip.repaint();
            }
        });

        VMButton File_Ex = new VMButton();
        File_Ex.setBounds(200, 12, 50, 50);
        File_Ex.setImage(new ImageIcon(Mypath+"Resoures\\File_Ex.png").getImage());
        File_Ex.setBorder(BorderFactory.createEmptyBorder());
        File_Ex.setToolTipText("VM File Explorer");
        File_Ex.setOpaque(false);
        File_Ex.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                try {
                    JFileChooser jfc = new JFileChooser("d:");
                    int returnname = jfc.showOpenDialog(jp);
                    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    String pof = null, extend = null;
                    if (returnname == JFileChooser.APPROVE_OPTION) {
                        pof = jfc.getSelectedFile().getAbsolutePath();
                        extend = pof.substring(pof.lastIndexOf("."), pof.length()).toLowerCase();
                    }
                    if (extend.equals(".mp4") || extend.equals(".avi") || extend.equals(".mpg") || extend.equals(".mov") || extend.equals(".3gp") || extend.equals(".3gpp") || extend.equals(".3gp2") || extend.equals(".mov") || extend.equals(".wmv") || extend.equals(".flv") || extend.equals(".mpeg2") || extend.equals(".mp3") || extend.equals(".wav") || extend.equals(".ogg") || extend.equals(".vob")) {
                        new Vm_player_via_file_explorer().vmp(pof, jp);
                    } else if (extend.equals(".jpeg") || extend.equals(".bmp") || extend.equals(".png") || extend.equals(".jpg") || extend.equals(".jpe")) {
                        new Vm_ImageViewer_via_file_explorer().viv(pof, jp);
                    } else if (extend.equals(".doc") || extend.equals(".txt") || extend.equals(".bat") || extend.equals(".c") || extend.equals(".cpp") || extend.equals(".css") || extend.equals(".html") || extend.equals(".htm") || extend.equals(".xhtml") || extend.equals(".java") || extend.equals(".class") || extend.equals(".js") || extend.equals(".jsp") || extend.equals(".php") || extend.equals(".docx") || extend.equals(".dot") || extend.equals("xml.") || extend.equals(".docm") || extend.equals(".dotm")) {
                        new Vm_pad_Via_file_explorer().Vmpp(pof, jp);
                    } else {
                        JOptionPane.showMessageDialog(jfc, "Unsupported File");
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void mousePressed(MouseEvent me) {
                File_Ex.setImage(new ImageIcon(Mypath+"Resoures\\File_Ex_oc.png").getImage());
                File_Ex.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                File_Ex.setImage(new ImageIcon(Mypath+"Resoures\\File_Ex_oh.png").getImage());
                File_Ex.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                File_Ex.setBounds(190, 2, 70, 70);
                File_Ex.setImage(new ImageIcon(Mypath+"Resoures\\File_Ex_oh.png").getImage());
                File_Ex.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                File_Ex.setBounds(200, 12, 50, 50);
                File_Ex.setImage(new ImageIcon(Mypath+"Resoures\\File_Ex.png").getImage());
                File_Ex.repaint();
                strip.repaint();
            }
        });

        VMButton VM_Bro = new VMButton();
        VM_Bro.setBounds(260, 12, 50, 50);
        VM_Bro.setImage(new ImageIcon(Mypath+"Resoures\\VM_Bro.png").getImage());
        VM_Bro.setBorder(BorderFactory.createEmptyBorder());
        VM_Bro.setOpaque(false);
        VM_Bro.setToolTipText("VM Browser");
        VM_Bro.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            VmBrowser wb = new VmBrowser();
                            wb.setVisible(true);
                            jp.add(wb, new Integer(100));

                        } catch (Exception e) {

                        }
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent me) {
                VM_Bro.setImage(new ImageIcon(Mypath+"Resoures\\VM_Bro_oc.png").getImage());
                VM_Bro.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                VM_Bro.setImage(new ImageIcon(Mypath+"Resoures\\VM_Bro_oh.png").getImage());
                VM_Bro.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                VM_Bro.setBounds(250, 2, 70, 70);
                VM_Bro.setImage(new ImageIcon(Mypath+"Resoures\\VM_Bro_oh.png").getImage());
                VM_Bro.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                VM_Bro.setBounds(260, 12, 50, 50);
                VM_Bro.setImage(new ImageIcon(Mypath+"Resoures\\VM_Bro.png").getImage());
                VM_Bro.repaint();
                strip.repaint();
            }
        });

        VMButton VM_Html_T = new VMButton();
        VM_Html_T.setBounds(320, 12, 50, 50);
        VM_Html_T.setImage(new ImageIcon(Mypath+"Resoures\\VM_Html_T.png").getImage());
        VM_Html_T.setBorder(BorderFactory.createEmptyBorder());
        VM_Html_T.setOpaque(false);
        VM_Html_T.setToolTipText("VM HTML Tester");
        VM_Html_T.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                new VM_HTML_Tester().VM_HTML_Testerp(jp);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                VM_Html_T.setImage(new ImageIcon(Mypath+"Resoures\\VM_Html_T_oc.png").getImage());
                VM_Html_T.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                VM_Html_T.setImage(new ImageIcon(Mypath+"Resoures\\VM_Html_T_oh.png").getImage());
                VM_Html_T.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                VM_Html_T.setBounds(310, 2, 70, 70);
                VM_Html_T.setImage(new ImageIcon(Mypath+"Resoures\\VM_Html_T_oh.png").getImage());
                VM_Html_T.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                VM_Html_T.setBounds(320, 12, 50, 50);
                VM_Html_T.setImage(new ImageIcon(Mypath+"Resoures\\VM_Html_T.png").getImage());
                VM_Html_T.repaint();
                strip.repaint();
            }
        });

        VMButton VM_sh = new VMButton();
        VM_sh.setBounds(380, 12, 50, 50);
        VM_sh.setImage(new ImageIcon(Mypath+"Resoures\\VM_sh.png").getImage());
        VM_sh.setBorder(BorderFactory.createEmptyBorder());
        VM_sh.setOpaque(false);
        VM_sh.setToolTipText("VM Snake Game");
        VM_sh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                EventQueue.invokeLater(() -> {
                    JInternalFrame ex = new JInternalFrame("VM Snake Game");
                    ex.setClosable(true);
                    ex.setResizable(false);
                    ex.setIconifiable(true);
                    ex.setMaximizable(false);
                    ex.setFrameIcon(new ImageIcon(Mypath+"Resoures\\VM_sh_Logo.png"));
                    try {
                        ex.setSelected(true);
                    } catch (PropertyVetoException ex1) {
                    }
                    ex.setLayout(null);
                    ex.setVisible(true);
                    ex.setBounds(100, 100, 312, 330);
                    ex.setResizable(false);
                    Board b = new Board();
                    try {
                        ex.setSelected(true);
                    } catch (Exception e) {
                    }
                    b.setBounds(0, 0, 300, 300);
                    ex.add(b);
                    ex.repaint();
                    jp.add(ex);
                    jp.repaint();

                    ex.addInternalFrameListener(new InternalFrameAdapter() {
                        @Override
                        public void internalFrameClosing(InternalFrameEvent ife) {
                            ex.removeAll();
                            System.gc();
                        }
                    });

                });
            }

            @Override
            public void mousePressed(MouseEvent me) {
                VM_sh.setImage(new ImageIcon(Mypath+"Resoures\\VM_sh_oc.png").getImage());
                VM_sh.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                VM_sh.setImage(new ImageIcon(Mypath+"Resoures\\VM_sh_oh.png").getImage());
                VM_sh.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                VM_sh.setBounds(370, 2, 70, 70);
                VM_sh.setImage(new ImageIcon(Mypath+"Resoures\\VM_sh_oh.png").getImage());
                VM_sh.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                VM_sh.setBounds(380, 12, 50, 50);
                VM_sh.setImage(new ImageIcon(Mypath+"Resoures\\VM_sh.png").getImage());
                VM_sh.repaint();
                strip.repaint();
            }
        });

        VMButton VM_TTT = new VMButton();
        VM_TTT.setBounds(440, 12, 50, 50);
        VM_TTT.setImage(new ImageIcon(Mypath+"Resoures\\VM_TTT.png").getImage());
        VM_TTT.setBorder(BorderFactory.createEmptyBorder());
        VM_TTT.setOpaque(false);
        VM_TTT.setToolTipText("VM Tic Tac Toe Game");
        VM_TTT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                VM_X_and_zero vp = new VM_X_and_zero();
                jp.add(vp);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                VM_TTT.setImage(new ImageIcon(Mypath+"Resoures\\VM_TTT_oc.png").getImage());
                VM_TTT.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                VM_TTT.setImage(new ImageIcon(Mypath+"Resoures\\VM_TTT_oh.png").getImage());
                VM_TTT.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                VM_TTT.setBounds(430, 2, 70, 70);
                VM_TTT.setImage(new ImageIcon(Mypath+"Resoures\\VM_TTT_oh.png").getImage());
                VM_TTT.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                VM_TTT.setBounds(440, 12, 50, 50);
                VM_TTT.setImage(new ImageIcon(Mypath+"Resoures\\VM_TTT.png").getImage());
                VM_TTT.repaint();
                strip.repaint();
            }
        });

        VMButton VM_cal = new VMButton();
        VM_cal.setBounds(500, 12, 50, 50);
        VM_cal.setImage(new ImageIcon(Mypath+"Resoures\\VM_cal.png").getImage());
        VM_cal.setBorder(BorderFactory.createEmptyBorder());
        VM_cal.setOpaque(false);
        VM_cal.setToolTipText("VM Calculator");
        VM_cal.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                new calculator().m(jp);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                VM_cal.setImage(new ImageIcon(Mypath+"Resoures\\VM_cal_oc.png").getImage());
                VM_cal.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                VM_cal.setImage(new ImageIcon(Mypath+"Resoures\\VM_cal_oh.png").getImage());
                VM_cal.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                VM_cal.setBounds(490, 2, 70, 70);
                VM_cal.setImage(new ImageIcon(Mypath+"Resoures\\VM_cal_oh.png").getImage());
                VM_cal.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                VM_cal.setBounds(500, 12, 50, 50);
                VM_cal.setImage(new ImageIcon(Mypath+"Resoures\\VM_cal.png").getImage());
                VM_cal.repaint();
                strip.repaint();
            }
        });

        VMButton VMP = new VMButton();
        VMP.setBounds(560, 12, 50, 50);
        VMP.setImage(new ImageIcon(Mypath+"Resoures\\VMP.png").getImage());
        VMP.setBorder(BorderFactory.createEmptyBorder());
        VMP.setOpaque(false);
        VMP.setToolTipText("VM Paint");
        VMP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                new Vm_paint(jp);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                VMP.setImage(new ImageIcon(Mypath+"Resoures\\VMP_oc.png").getImage());
                VMP.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                VMP.setImage(new ImageIcon(Mypath+"Resoures\\VMP_oh.png").getImage());
                VMP.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                VMP.setBounds(550, 2, 70, 70);
                VMP.setImage(new ImageIcon(Mypath+"Resoures\\VMP_oh.png").getImage());
                VMP.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                VMP.setBounds(560, 12, 50, 50);
                VMP.setImage(new ImageIcon(Mypath+"Resoures\\VMP.png").getImage());
                VMP.repaint();
                strip.repaint();
            }
        });

        VMButton VM_info = new VMButton();
        VM_info.setBounds(620, 12, 50, 50);
        VM_info.setImage(new ImageIcon(Mypath+"Resoures\\VM_info.png").getImage());
        VM_info.setBorder(BorderFactory.createEmptyBorder());
        VM_info.setOpaque(false);
        VM_info.setToolTipText("About VMOS");
        VM_info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                click();
                new AVMOS(jp);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                VM_info.setImage(new ImageIcon(Mypath+"Resoures\\VM_info_oc.png").getImage());
                VM_info.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                VM_info.setImage(new ImageIcon(Mypath+"Resoures\\VM_info_oh.png").getImage());
                VM_info.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                VM_info.setBounds(610, 2, 70, 70);
                VM_info.setImage(new ImageIcon(Mypath+"Resoures\\VM_info_oh.png").getImage());
                VM_info.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                VM_info.setBounds(620, 12, 50, 50);
                VM_info.setImage(new ImageIcon(Mypath+"Resoures\\VM_info.png").getImage());
                VM_info.repaint();
                strip.repaint();
            }
        });

        shicons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (sisnd) {
                    showclick();
                    shicons.setImage(new ImageIcon(Mypath+"Resoures\\hidei.png").getImage());
                    shicons.repaint();
                    jp.add(strip);
                    shicons.setEnabled(false);
                    scount = 0;
                    sh = 10;
                    sw = 10;
                    sjw = 635;
                    sjh = 165;
                    Timer stimr = new Timer();
                    TimerTask stask = new TimerTask() {
                        @Override
                        public void run() {
                            if (scount % 5 == 0) {
                                sh++;
                                strip.setBounds(sjw, sjh, sw, sh);
                            }
                            sw = sw + 2;
                            sjw--;
                            sjh++;
                            strip.setBounds(sjw, sjh, sw, sh);
                            if (scount == 335) {
                                strip.setBounds(295, 500, 690, 75);
                                strip.add(vm_player);
                                strip.add(Vm_ImageViewer);
                                strip.add(Vm_Pad);
                                strip.add(File_Ex);
                                strip.add(VM_Bro);
                                strip.add(VM_Html_T);
                                strip.add(VM_sh);
                                strip.add(VM_TTT);
                                strip.add(VM_cal);
                                strip.add(VMP);
                                strip.add(VM_info);
                                strip.repaint();
                                shicons.setEnabled(true);
                                stimr.cancel();
                            }
                            scount++;
                        }
                    };
                    stimr.schedule(stask, 000, 1);
                    sisnd = false;
                } else {
                    hideclick();
                    shicons.setImage(new ImageIcon(Mypath+"Resoures\\showi.png").getImage());
                    shicons.repaint();
                    shicons.setEnabled(false);
                    scount = 335;
                    sh = 75;
                    sw = 690;
                    sjw = 295;
                    sjh = 500;
                    Timer stimr = new Timer();
                    TimerTask stask = new TimerTask() {
                        @Override
                        public void run() {
                            if (scount == 335) {
                                strip.remove(vm_player);
                                strip.remove(Vm_ImageViewer);
                                strip.remove(Vm_Pad);
                                strip.remove(File_Ex);
                                strip.remove(VM_Bro);
                                strip.remove(VM_Html_T);
                                strip.remove(VM_sh);
                                strip.remove(VM_TTT);
                                strip.remove(VM_cal);
                                strip.remove(VMP);
                                strip.remove(VM_info);
                                strip.repaint();
                            }
                            if (scount % 5 == 0) {
                                sh--;
                                strip.setBounds(sjw, sjh, sw, sh);
                            }
                            sw = sw - 2;
                            sjw++;
                            sjh--;
                            strip.setBounds(sjw, sjh, sw, sh);
                            if (scount == 0) {
                                jp.remove(strip);
                                shicons.setEnabled(true);
                                stimr.cancel();
                            }
                            scount--;
                        }
                    };
                    stimr.schedule(stask, 000, 1);
                    sisnd = true;
                }

            }
        });
        shicons.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (sisnd) {
                    shicons.setImage(new ImageIcon(Mypath+"Resoures\\showi_oh.png").getImage());
                    shicons.repaint();
                    shicons.setToolTipText("Show Icons");
                } else {
                    shicons.setToolTipText("Hide Icons");
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                if (sisnd) {
                    shicons.setImage(new ImageIcon(Mypath+"Resoures\\showi.png").getImage());
                    shicons.repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseMoved(MouseEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    private void vmo_b(VMButton b, String ac) {
        b.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                b.setImage(new ImageIcon(Mypath+"Resoures\\" + ac + "_oc.png").getImage());
                b.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                b.setImage(new ImageIcon(Mypath+"Resoures\\" + ac + "_oh.png").getImage());
                b.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                b.setImage(new ImageIcon(Mypath+"Resoures\\" + ac + "_oh.png").getImage());
                b.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                b.setImage(new ImageIcon(Mypath+"Resoures\\" + ac + ".png").getImage());
                b.repaint();
            }
        });
    }

    JLabel errorr;
    JLabel infoo;

    protected void PasswordSleep(ImagePanel jssp) {

        try {
            JLabel acc_icon = new JLabel("", JLabel.CENTER);
            acc_icon.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\pass_icon.png"))));
            acc_icon.setBounds(492, 309, 25, 25);
            acc_icon.setBackground(Color.WHITE);
            acc_icon.setBorder(BorderFactory.createBevelBorder(0));
            acc_icon.setOpaque(true);
            blurit(acc_icon, jssp);
            jssp.add(acc_icon, SwingConstants.CENTER);
            jssp.repaint();
        } catch (Exception e) {
        }

        JPasswordField pwdd = new JPasswordField(JPasswordField.BOTTOM);
        pwdd.setBounds(526, 309, 230, 27);
        pwdd.setBorder(BorderFactory.createBevelBorder(1));
        pwdd.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel ll = new JLabel(" Password");
        ll.setFont(new Font("Baskerville Old Face", Font.ITALIC, 20));
        ll.setBounds(0, 4, 100, 22);
        ll.setForeground(Color.decode("#3d5c5c"));
        blurit(pwdd, jssp);
        pwdd.add(ll);
        pwdd.repaint();
        jssp.add(pwdd);
        jssp.repaint();
        pwdd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                pwdd.remove(ll);
                pwdd.repaint();
                jssp.repaint();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (pwdd.getText().equals("")) {
                    pwdd.add(ll);
                    pwdd.repaint();
                    jssp.repaint();
                }
            }
        });

        try {
            JLabel pf = new JLabel("", JLabel.CENTER);
            pf.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\pf.png"))));
            pf.setBounds(579, 158, 120, 120);
            pf.setBackground(Color.WHITE);
            pf.setBorder(BorderFactory.createEmptyBorder());
            blurit(pf, jssp);
            jssp.add(pf, SwingConstants.CENTER);
            jssp.repaint();
        } catch (Exception e) {
        }

        String ppp = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\VAIBHAV:1433;databaseName=VMOS", "sa", "xxxxxx");
            PreparedStatement p = conn.prepareStatement("Select * from userdetails where username=?");
            p.setString(1, logged_User.trim());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                ppp = rs.getString("img");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JLabel upp = new JLabel("", JLabel.CENTER);
            upp.setIcon(new ImageIcon(ImageIO.read(new File(ppp))));
            upp.setBounds(589, 168, 101, 101);
            upp.setBackground(Color.WHITE);
            upp.setBorder(BorderFactory.createEmptyBorder());
            upp.setOpaque(true);
            blurit(upp, jssp);
            jssp.add(upp, SwingConstants.CENTER);
            jssp.repaint();
        } catch (Exception e) {
        }

        JLabel cps = new JLabel("Caps Lock is ON", JLabel.CENTER);
        cps.setBounds(580, 348, 150, 20);
        cps.setBackground(Color.decode("#197BF1"));
        cps.setFont(new Font("Bell MT", Font.BOLD, 15));
        cps.setForeground(Color.WHITE);
        cps.setBorder(BorderFactory.createBevelBorder(0));
        cps.setOpaque(true);
        blurit(cps, jssp);
        jssp.repaint();

        try {
            infoo = new JLabel("", JLabel.CENTER);
            infoo.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\info.png"))));
            infoo.setBounds(550, 348, 20, 20);
            infoo.setBackground(Color.WHITE);
            infoo.setBorder(BorderFactory.createBevelBorder(0));
            infoo.setOpaque(true);
            blurit(infoo, jssp);
            jssp.repaint();
        } catch (Exception e) {
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                boolean check = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                if (check) {
                    if (cps.getParent() == null && infoo.getParent() == null) {
                        jssp.add(infoo, SwingConstants.CENTER);
                        jssp.add(cps);
                        jssp.repaint();
                    }
                } else {
                    if (cps.getParent() == jssp && infoo.getParent() == jssp) {
                        jssp.remove(infoo);
                        jssp.remove(cps);
                        jssp.repaint();
                    }
                }
            }
        };

        timer.schedule(task, 000, 100);

        try {
            JLabel logo = new JLabel();
            logo.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\Logo.png"))));
            logo.setBounds(462, 520, 87, 120);
            blurit(logo, jssp);
            jssp.add(logo, SwingConstants.CENTER);
            jssp.repaint();

        } catch (Exception e) {
            System.out.println("ImageNotFound");
        }

        VM_Label vmos = new VM_Label("VMOS", JLabel.CENTER);
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#34495E"), 100, 50, Color.decode("#000000"), true);
        vmos = new VM_Label("VMOS", JLabel.CENTER);
        vmos.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 74));
        vmos.setEffectIndex(VM_Label.EFFECT_COLOR_ANIMATION);
        vmos.setGradient(gp);
        vmos.setOutlineColor(Color.black);
        vmos.startAnimation(400);
        vmos.setBounds(550, 553, 300, 90);
        blurit(vmos, jssp);
        jssp.add(vmos);

        VMButton next = new VMButton();
        next.setBounds(761, 309, 31, 31);
        next.setImage(new ImageIcon(Mypath+"Resoures\\next.png").getImage());
        next.setBorder(BorderFactory.createEmptyBorder());
        next.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                next.setImage(new ImageIcon(Mypath+"Resoures\\next_oc.png").getImage());
                next.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                next.setImage(new ImageIcon(Mypath+"Resoures\\next.png").getImage());
                next.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                jssp.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                jssp.repaint();
                next.setImage(new ImageIcon(Mypath+"Resoures\\next_oh.png").getImage());
                next.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                jssp.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                jssp.repaint();
                next.setImage(new ImageIcon(Mypath+"Resoures\\next.png").getImage());
                next.repaint();
            }
        });
        VM_Label status = new VM_Label("Invalid Password", JLabel.CENTER);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (status.getParent() == null) {
                    String passdb = null;

                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection connn = DriverManager.getConnection("jdbc:sqlserver://localhost\\VAIBHAV:1433;databaseName=VMOS", "sa", "xxxxxx");
                        PreparedStatement p = connn.prepareStatement("Select * from userdetails where username=?");
                        p.setString(1, logged_User.trim());
                        ResultSet rs = p.executeQuery();
                        if (rs.next()) {
                            passdb = rs.getString("pwd");
                        }
                    } catch (Exception e) {
                    }
                    if ((passdb.trim()).equals(pwdd.getText())) {
                        snext();
                        sleeppa = false;
                        timer.cancel();
                        jssp.removeAll();
                        jssp.repaint();
                        System.gc();
                        jp.remove(jssp);

                    } else {

                        GradientPaint gpp = new GradientPaint(0, 0, Color.decode("#f44141"), 100, 50, Color.decode("#F1948A"), true);
                        status.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
                        status.setEffectIndex(VM_Label.EFFECT_COLOR_ANIMATION);
                        status.setGradient(gpp);
                        status.startAnimation(80);
                        status.setBounds(540, 400, 240, 30);
                        blurit(status, jssp);
                        jssp.add(status);
                        jssp.repaint();

                        try {
                            errorr = new JLabel("", JLabel.CENTER);
                            errorr.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\error.png"))));
                            errorr.setBounds(500, 397, 30, 30);
                            errorr.setBackground(Color.WHITE);
                            errorr.setBorder(BorderFactory.createBevelBorder(0));
                            errorr.setOpaque(true);
                            blurit(errorr, jssp);
                            jssp.add(errorr, SwingConstants.CENTER);
                            jssp.repaint();
                        } catch (Exception e) {
                        }
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
                                jssp.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                                jssp.repaint();
                                ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_oh.png").getImage());
                                ok_button.repaint();
                            }

                            @Override
                            public void mouseExited(MouseEvent me) {
                                jssp.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                                jssp.repaint();
                                ok_button.setImage(new ImageIcon(Mypath+"Resoures\\ok_button.png").getImage());
                                ok_button.repaint();
                            }
                        });
                        ok_button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                pwdd.setText("");
                                jssp.remove(status);
                                jssp.remove(ok_button);
                                jssp.remove(errorr);
                                jssp.repaint();
                            }
                        });
                        jssp.add(ok_button);
                        jssp.repaint();

                    }
                }
            }
        });

        jssp.add(next);
        jssp.repaint();

    }

    public void blurit(JLabel l, ImagePanel p) {
        l.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                p.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                p.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                p.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                p.repaint();
                p.repaint();
            }
        });
    }

    public void blurit(JPasswordField l, ImagePanel p) {
        l.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                p.setImage(new ImageIcon(Mypath+"Resoures\\passbg_blur.jpg").getImage());
                p.repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                p.setImage(new ImageIcon(Mypath+"Resoures\\passbg.jpg").getImage());
                p.repaint();
                p.repaint();
            }
        });
    }

}
