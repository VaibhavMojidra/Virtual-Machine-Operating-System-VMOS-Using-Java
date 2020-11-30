package vmos;

import static vmos.Static_Objects_Methods_Reuasble.Mypath;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.Canvas;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class VM_Player {

    private MediaPlayerFactory mediaPlayerFactory;
    static boolean ppf = true;

    protected void VideoPlayer_app(JDesktopPane jpp) {
        try {

            JInternalFrame jif = new JInternalFrame("VM Player", true, true, true, false);
            jif.setVisible(true);
            jif.repaint();
            jif.setBounds(300, 100, 500, 370);
            jif.setBackground(new java.awt.Color(73, 161, 255, 40));
            jif.setFrameIcon(new ImageIcon(Mypath+"Resoures\\Vm_player_Logo"));
            jif.setLayout(null);
            jpp.add(jif);
            jpp.repaint();
            String pof = null;

            JFileChooser jfc = new JFileChooser("d:");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Videos", "mp4");
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Audios", "mp3");
            jfc.setFileFilter(filter2);
            jfc.setFileFilter(filter);
            int returnname = jfc.showOpenDialog(jif);
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if (returnname == JFileChooser.APPROVE_OPTION) {
                pof = jfc.getSelectedFile().getAbsolutePath();
            }

            Canvas c = new Canvas();
            c.setBackground(java.awt.Color.BLACK);
            c.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
            JPanel p = new JPanel();
            p.setVisible(true);
            p.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
            p.setLayout(null);
            p.add(c);
            jif.add(p);
            p.repaint();
            jif.repaint();

            JPanel cp = new JPanel();
            cp.setVisible(true);
            cp.setBounds(0, jif.getHeight() - 100, jif.getWidth(), 70);
            cp.setBorder(BorderFactory.createBevelBorder(1));
            cp.setBackground(new java.awt.Color(15, 0, 183, 45));
            cp.setOpaque(true);
            cp.setLayout(null);
            jif.add(cp);
            jif.repaint();

            NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "lib");
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            MediaPlayerFactory mpf = new MediaPlayerFactory();

            EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer();
            emp.setVideoSurface(mpf.newVideoSurface(c));
            emp.setEnableMouseInputHandling(false);
            emp.setEnableKeyInputHandling(false);

            emp.prepareMedia(pof);
            emp.play();
            c.repaint();
            p.repaint();
            jif.repaint();

            emp.parseMedia();

            JSlider tsdr = new JSlider(0, (int) emp.getLength());
            tsdr.setBounds(20, 10, jif.getWidth() - 40, 20);
            tsdr.setBackground(new java.awt.Color(0, 0, 0, 0));
            tsdr.setValue(0);
            cp.add(tsdr);
            cp.repaint();
            tsdr.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent ce) {
                    emp.setTime(tsdr.getValue());
                }
            });

            JLabel vol = new JLabel("", JLabel.CENTER);
            try {
                vol.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\Volume.png"))));
                vol.setBounds(350, 30, 27, 27);
                vol.setBackground(new java.awt.Color(0, 0, 0, 0));
                vol.setBorder(BorderFactory.createEmptyBorder());
                cp.add(vol, SwingConstants.CENTER);
                jif.repaint();
            } catch (Exception e) {
            }

            JSlider volumeslider = new JSlider();
            volumeslider.setBounds(380, 40, 100, 10);
            volumeslider.setValue(emp.getVolume());
            volumeslider.setBackground(new java.awt.Color(0, 0, 0, 0));
            volumeslider.repaint();
            volumeslider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent ce) {
                    if (volumeslider.getValue() <= 100 && volumeslider.getValue() >= 90) {
                        try {
                            vol.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\Volume.png"))));
                        } catch (Exception e) {
                        }
                        vol.repaint();
                        cp.repaint();
                    }
                    if (volumeslider.getValue() <= 89 && volumeslider.getValue() >= 40) {
                        try {
                            vol.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\Volume_mid.png"))));
                        } catch (Exception e) {
                        }
                        vol.repaint();
                        cp.repaint();
                    }
                    if (volumeslider.getValue() <= 39 && volumeslider.getValue() >= 5) {
                        try {
                            vol.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\Volume_slow.png"))));
                        } catch (Exception e) {
                        }
                        vol.repaint();
                        cp.repaint();
                    }
                    if (volumeslider.getValue() <= 4 && volumeslider.getValue() >= 0) {
                        try {
                            vol.setIcon(new ImageIcon(ImageIO.read(new File(Mypath+"Resoures\\Volume_zero.png"))));
                        } catch (Exception e) {
                        }
                        vol.repaint();
                        cp.repaint();
                    }

                    emp.setVolume(volumeslider.getValue());
                }
            });
            cp.add(volumeslider);
            cp.repaint();
            jif.repaint();

            VMButton play = new VMButton();
            play.setBounds(20, 32, 30, 30);
            play.setImage(new ImageIcon(Mypath+"Resoures\\play.png").getImage());
            play.setBorder(BorderFactory.createEmptyBorder());
            cp.repaint();
            jif.repaint();

            VMButton Pause = new VMButton();
            Pause.setBounds(20, 32, 30, 30);
            Pause.setImage(new ImageIcon(Mypath+"Resoures\\Pause.png").getImage());
            Pause.setBorder(BorderFactory.createEmptyBorder());
            Pause.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent me) {
                    emp.pause();
                    cp.remove(Pause);
                    cp.add(play);
                    ppf = false;
                    cp.repaint();
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent me) {
                    Pause.setImage(new ImageIcon(Mypath+"Resoures\\Pause_oc.png").getImage());
                    Pause.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent me) {
                    Pause.setImage(new ImageIcon(Mypath+"Resoures\\Pause.png").getImage());
                    Pause.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent me) {
                    Pause.setImage(new ImageIcon(Mypath+"Resoures\\Pause_oh.png").getImage());
                    Pause.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent me) {
                    Pause.setImage(new ImageIcon(Mypath+"Resoures\\Pause.png").getImage());
                    Pause.repaint();
                    cp.repaint();
                    jif.repaint();
                }
            });
            cp.add(Pause);
            cp.repaint();
            jif.repaint();

            play.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent me) {
                    emp.play();
                    cp.remove(play);
                    cp.add(Pause);
                    ppf = true;
                    cp.repaint();
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent me) {
                    play.setImage(new ImageIcon(Mypath+"Resoures\\play_oc.png").getImage());
                    play.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent me) {
                    play.setImage(new ImageIcon(Mypath+"Resoures\\play.png").getImage());
                    play.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent me) {
                    play.setImage(new ImageIcon(Mypath+"Resoures\\play_oh.png").getImage());
                    play.repaint();
                    cp.repaint();
                    jif.repaint();

                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent me) {
                    play.setImage(new ImageIcon(Mypath+"Resoures\\play.png").getImage());
                    play.repaint();
                    cp.repaint();
                    jif.repaint();
                }
            });

            VMButton Stop = new VMButton();
            Stop.setBounds(60, 32, 30, 30);
            Stop.setImage(new ImageIcon(Mypath+"Resoures\\Stop.png").getImage());
            Stop.setBorder(BorderFactory.createEmptyBorder());
            Stop.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent me) {
                    emp.stop();
                    if (ppf) {
                        cp.remove(Pause);
                        cp.add(play);
                        ppf = true;
                        cp.repaint();
                    } else {
                        ppf = false;
                        cp.repaint();
                    }
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent me) {
                    Stop.setImage(new ImageIcon(Mypath+"Resoures\\Stop_oc.png").getImage());
                    Stop.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent me) {
                    Stop.setImage(new ImageIcon(Mypath+"Resoures\\Stop.png").getImage());
                    Stop.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent me) {
                    Stop.setImage(new ImageIcon(Mypath+"Resoures\\Stop_oh.png").getImage());
                    Stop.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent me) {
                    Stop.setImage(new ImageIcon(Mypath+"Resoures\\Stop.png").getImage());
                    Stop.repaint();
                    cp.repaint();
                    jif.repaint();
                }
            });
            cp.add(Stop);
            cp.repaint();
            jif.repaint();

            jif.addInternalFrameListener(new InternalFrameListener() {
                @Override
                public void internalFrameOpened(InternalFrameEvent ife) {
                }

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    emp.stop();
                    emp.release();
                }

                @Override
                public void internalFrameClosed(InternalFrameEvent ife) {

                    emp.release();
                }

                @Override
                public void internalFrameIconified(InternalFrameEvent ife) {
                }

                @Override
                public void internalFrameDeiconified(InternalFrameEvent ife) {
                }

                @Override
                public void internalFrameActivated(InternalFrameEvent ife) {
                }

                @Override
                public void internalFrameDeactivated(InternalFrameEvent ife) {
                }
            });

            VMButton RestoreScreen = new VMButton();
            RestoreScreen.setBounds(100, 32, 30, 30);
            RestoreScreen.setImage(new ImageIcon(Mypath+"Resoures\\RestoreScreen.png").getImage());
            RestoreScreen.setBorder(BorderFactory.createEmptyBorder());

            VMButton FullScreen = new VMButton();
            FullScreen.setBounds(100, 32, 30, 30);
            FullScreen.setImage(new ImageIcon(Mypath+"Resoures\\FullScreen.png").getImage());
            FullScreen.setBorder(BorderFactory.createEmptyBorder());
            FullScreen.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent me) {
                    cp.remove(FullScreen);
                    cp.add(RestoreScreen);
                    cp.repaint();
                    jif.setBounds(0, 0, 1280, 680);
                    p.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                    c.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                    cp.setBounds(0, jif.getHeight() - 100, jif.getWidth(), 70);
                    volumeslider.setBounds(cp.getWidth() - 120, 40, 100, 10);
                    vol.setBounds(cp.getWidth() - 150, 30, 27, 27);
                    tsdr.setBounds(20, 10, jif.getWidth() - 40, 20);
                    p.repaint();
                    c.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent me) {
                    FullScreen.setImage(new ImageIcon(Mypath+"Resoures\\FullScreen_oc.png").getImage());
                    FullScreen.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent me) {
                    FullScreen.setImage(new ImageIcon(Mypath+"Resoures\\FullScreen.png").getImage());
                    FullScreen.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent me) {
                    FullScreen.setImage(new ImageIcon(Mypath+"Resoures\\FullScreen_oh.png").getImage());
                    FullScreen.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent me) {
                    FullScreen.setImage(new ImageIcon(Mypath+"Resoures\\FullScreen.png").getImage());
                    FullScreen.repaint();
                    cp.repaint();
                    jif.repaint();
                }
            });
            cp.add(FullScreen);
            cp.repaint();
            jif.repaint();

            RestoreScreen.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent me) {
                    cp.remove(RestoreScreen);
                    cp.add(FullScreen);
                    cp.repaint();
                    jif.setBounds(300, 100, 500, 370);
                    p.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                    c.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                    cp.setBounds(0, jif.getHeight() - 100, jif.getWidth(), 70);
                    volumeslider.setBounds(cp.getWidth() - 120, 40, 100, 10);
                    vol.setBounds(cp.getWidth() - 150, 30, 27, 27);
                    tsdr.setBounds(20, 10, jif.getWidth() - 40, 20);
                    p.repaint();
                    c.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent me) {
                    RestoreScreen.setImage(new ImageIcon(Mypath+"Resoures\\RestoreScreen_oc.png").getImage());
                    RestoreScreen.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent me) {
                    RestoreScreen.setImage(new ImageIcon(Mypath+"Resoures\\RestoreScreen.png").getImage());
                    RestoreScreen.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent me) {
                    RestoreScreen.setImage(new ImageIcon(Mypath+"Resoures\\RestoreScreen_oh.png").getImage());
                    RestoreScreen.repaint();
                    cp.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent me) {
                    RestoreScreen.setImage(new ImageIcon(Mypath+"Resoures\\RestoreScreen.png").getImage());
                    RestoreScreen.repaint();
                    cp.repaint();
                    jif.repaint();
                }
            });
            cp.add(RestoreScreen);
            cp.repaint();
            jif.repaint();

            jif.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent me) {
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent me) {

                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent me) {
                    p.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                    p.repaint();
                    c.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                    c.repaint();
                    cp.setBounds(0, jif.getHeight() - 100, jif.getWidth(), 70);
                    cp.repaint();
                    volumeslider.setBounds(cp.getWidth() - 120, 40, 100, 10);
                    volumeslider.repaint();
                    vol.setBounds(cp.getWidth() - 150, 30, 27, 27);
                    vol.repaint();
                    tsdr.setBounds(20, 10, jif.getWidth() - 40, 20);
                    tsdr.repaint();
                    jif.repaint();
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent me) {

                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent me) {

                }

            }
            );

            jif.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent ce) {
                    if (jif.isMaximum()) {
                        cp.remove(FullScreen);
                        cp.add(RestoreScreen);
                        p.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                        p.repaint();
                        c.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                        c.repaint();
                        cp.setBounds(0, jif.getHeight() - 100, jif.getWidth(), 70);
                        cp.repaint();
                        volumeslider.setBounds(cp.getWidth() - 120, 40, 100, 10);
                        volumeslider.repaint();
                        vol.setBounds(cp.getWidth() - 150, 30, 27, 27);
                        vol.repaint();
                        tsdr.setBounds(20, 10, jif.getWidth() - 40, 20);
                        tsdr.repaint();
                        jif.repaint();
                    } else {
                        cp.remove(RestoreScreen);
                        cp.add(FullScreen);
                        p.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                        p.repaint();
                        c.setBounds(0, 0, jif.getWidth(), jif.getHeight() - 100);
                        c.repaint();
                        cp.setBounds(0, jif.getHeight() - 100, jif.getWidth(), 70);
                        cp.repaint();
                        volumeslider.setBounds(cp.getWidth() - 120, 40, 100, 10);
                        volumeslider.repaint();
                        vol.setBounds(cp.getWidth() - 150, 30, 27, 27);
                        vol.repaint();
                        tsdr.setBounds(20, 10, jif.getWidth() - 40, 20);
                        tsdr.repaint();
                        jif.repaint();
                    }

                }
            });
                     jif.addInternalFrameListener(new InternalFrameAdapter() {
                        @Override
                        public void internalFrameClosing(InternalFrameEvent ife) {
                            jif.removeAll();
                            System.gc();
                        }
                    });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jpp, "Sorry couldn't load the media");
        }
    }

}
