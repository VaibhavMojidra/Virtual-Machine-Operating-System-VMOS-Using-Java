package vmos;
import static vmos.Static_Objects_Methods_Reuasble.Mypath;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import static vmos.Vm_pad.ci;

public class Vm_pad_Via_file_explorer {

    JFileChooser dialog = new JFileChooser("d:");
    private String currentFile = "VM_Untitled", pof = null;
    private boolean changed = false, frnd = true;
    static int fs = 20;
    static String ci = "Arial";

    protected void Vmpp(String Path, ImageDesktopPanel dp) {
        JInternalFrame jif = new JInternalFrame("VM Player", true, true, true, true);
        jif.setFrameIcon(new ImageIcon(Mypath+"Resoures\\Vm_Pad_Logo.png"));
        jif.setVisible(true);
        jif.setLayout(null);
        jif.setBounds(300, 100, 500, 300);
        dp.add(jif);
        dp.repaint();
        jif.setTitle("VM Text Editor");

        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 0, 490, 20);
        mb.setLayout(null);
        JMenu f = new JMenu("File");
        f.setBounds(0, 0, 30, 20);
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save AS");
        JMenuItem close = new JMenuItem("Close");
        f.add(open);
        f.add(save);
        f.add(saveAs);
        f.add(close);
        mb.add(f);
        JMenu e = new JMenu("Edit");
        e.setBounds(40, 0, 30, 20);
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem fr = new JMenuItem("Replace");
        e.add(cut);
        e.add(copy);
        e.add(paste);
        e.add(fr);
        mb.add(e);
        jif.add(mb);
        jif.repaint();

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font(ci, Font.PLAIN, 20));
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                saveAs.setEnabled(true);
            }
        });

        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                StringSelection selection = new StringSelection(textArea.getSelectedText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
                StringBuilder st = new StringBuilder(textArea.getText());
                st.delete(textArea.getSelectionStart(), textArea.getSelectionEnd());
                textArea.setText(st.toString());
                textArea.repaint();
            }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                StringSelection selection = new StringSelection(textArea.getSelectedText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
        });

        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String data = null;
                try {
                    data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                    textArea.append(data);
                    textArea.repaint();
                } catch (UnsupportedFlavorException ex) {
                } catch (IOException ex) {
                }
                textArea.repaint();
            }
        });

        fr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (frnd) {
                    JInternalFrame jfr = new JInternalFrame("Find and Replace", false, true, false, false);
                    jfr.setVisible(true);
                    jfr.setLayout(null);
                    jfr.setBounds(0, 0, 240, 150);
                    textArea.add(jfr);
                    dp.repaint();
                    frnd = false;
                    JLabel find = new JLabel("Find: ");
                    find.setBounds(10, 10, 30, 20);
                    jfr.add(find);
                    jfr.repaint();

                    JTextField findtb = new JTextField();
                    findtb.setBounds(65, 10, 150, 20);
                    jfr.add(findtb);
                    jfr.repaint();

                    JLabel rp = new JLabel("Replace: ");
                    rp.setBounds(10, 40, 60, 20);
                    jfr.add(rp);
                    jfr.repaint();

                    JTextField rptb = new JTextField();
                    rptb.setBounds(65, 40, 150, 20);
                    jfr.add(rptb);
                    jfr.repaint();

                    JButton rep = new JButton("Replace");
                    rep.setBounds(95, 75, 85, 20);
                    rep.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            String txt = textArea.getText();
                            String txt2 = findtb.getText();
                            String txt3 = rptb.getText();
                            if (!(txt2.equals("")) && !(txt3.equals(""))) {
                                if (txt.toLowerCase().contains(txt2.toLowerCase())) {
                                    textArea.setText(txt.replaceAll("(?i)" + txt2, txt3));
                                    textArea.repaint();
                                }
                            } else {
                                JOptionPane.showMessageDialog(jfr, "Please enter values in all textbox.");
                            }
                        }
                    });
                    jfr.add(rep);
                    jfr.repaint();

                    jfr.addInternalFrameListener(new InternalFrameAdapter() {
                        @Override
                        public void internalFrameClosing(InternalFrameEvent ife) {
                            frnd = true;
                            jfr.removeAll();
                            System.gc();
                        }
                    });
                }
            }
        });

        JComboBox cf = new JComboBox();
        cf.setBounds(80, 0, 220, 20);
        cf.addItem("Choose Font");
        cf.addItem("Adobe Arabic");
        cf.addItem("Adobe Caslon Pro");
        cf.addItem("Adobe Caslon Pro Bold");
        cf.addItem("Adobe Devanagari");
        cf.addItem("Adobe Fan Heiti Std B");
        cf.addItem("Adobe Fangsong Std R");
        cf.addItem("Adobe Garamond Pro");
        cf.addItem("Adobe Garamond Pro Bold");
        cf.addItem("Adobe Gothic Std B");
        cf.addItem("Adobe Gurmukhi");
        cf.addItem("Adobe Hebrew");
        cf.addItem("Adobe Heiti Std R");
        cf.addItem("Adobe Kaiti Std R");
        cf.addItem("Adobe Ming Std L");
        cf.addItem("Adobe Myungjo Std M");
        cf.addItem("Adobe Naskh Medium");
        cf.addItem("Adobe Song Std L");
        cf.addItem("Agency FB");
        cf.addItem("Aharoni");
        cf.addItem("Algerian");
        cf.addItem("Andalus");
        cf.addItem("Angsana New");
        cf.addItem("AngsanaUPC");
        cf.addItem("Aparajita");
        cf.addItem("Arabic Typesetting");
        cf.addItem("Arial");
        cf.addItem("Arial Black");
        cf.addItem("Arial Narrow");
        cf.addItem("Arial Rounded MT Bold");
        cf.addItem("Arial Unicode MS");
        cf.addItem("Baskerville Old Face");
        cf.addItem("Batang");
        cf.addItem("BatangChe");
        cf.addItem("Bauhaus 93");
        cf.addItem("Bell MT");
        cf.addItem("Berlin Sans FB");
        cf.addItem("Berlin Sans FB Demi");
        cf.addItem("Bernard MT Condensed");
        cf.addItem("Birch Std");
        cf.addItem("Blackadder ITC");
        cf.addItem("Blackoak Std");
        cf.addItem("Bodoni MT");
        cf.addItem("Bodoni MT Black");
        cf.addItem("Bodoni MT Condensed");
        cf.addItem("Bodoni MT Poster Compressed");
        cf.addItem("Book Antiqua");
        cf.addItem("Bookman Old Style");
        cf.addItem("Bookshelf Symbol 7");
        cf.addItem("Bradley Hand ITC");
        cf.addItem("Britannic Bold");
        cf.addItem("Broadway");
        cf.addItem("Browallia New");
        cf.addItem("BrowalliaUPC");
        cf.addItem("Brush Script MT");
        cf.addItem("Brush Script Std");
        cf.addItem("Calibri");
        cf.addItem("Californian FB");
        cf.addItem("Calisto MT");
        cf.addItem("Cambria");
        cf.addItem("Cambria Math");
        cf.addItem("Candara");
        cf.addItem("Castellar");
        cf.addItem("Centaur");
        cf.addItem("Century");
        cf.addItem("Century Gothic");
        cf.addItem("Century Schoolbook");
        cf.addItem("Chaparral Pro");
        cf.addItem("Chaparral Pro Light");
        cf.addItem("Charlemagne Std");
        cf.addItem("Chiller");
        cf.addItem("Colonna MT");
        cf.addItem("Comic Sans MS");
        cf.addItem("Consolas");
        cf.addItem("Constantia");
        cf.addItem("Cooper Black");
        cf.addItem("Copperplate Gothic Bold");
        cf.addItem("Copperplate Gothic Light");
        cf.addItem("Corbel");
        cf.addItem("Cordia New");
        cf.addItem("CordiaUPC");
        cf.addItem("Courier New");
        cf.addItem("Curlz MT");
        cf.addItem("DaunPenh");
        cf.addItem("David");
        cf.addItem("DFKai-SB");
        cf.addItem("Dialog");
        cf.addItem("DialogInput");
        cf.addItem("Digital-7 Mono");
        cf.addItem("DilleniaUPC");
        cf.addItem("DokChampa");
        cf.addItem("Dotum");
        cf.addItem("DotumChe");
        cf.addItem("Ebrima");
        cf.addItem("Edwardian Script ITC");
        cf.addItem("Elephant");
        cf.addItem("Engravers MT");
        cf.addItem("Eras Bold ITC");
        cf.addItem("Eras Demi ITC");
        cf.addItem("Eras Light ITC");
        cf.addItem("Eras Medium ITC");
        cf.addItem("Estrangelo Edessa");
        cf.addItem("EucrosiaUPC");
        cf.addItem("Euphemia");
        cf.addItem("FangSong");
        cf.addItem("Felix Titling");
        cf.addItem("Footlight MT Light");
        cf.addItem("Forte");
        cf.addItem("Franklin Gothic Book");
        cf.addItem("Franklin Gothic Demi");
        cf.addItem("Franklin Gothic Demi Cond");
        cf.addItem("Franklin Gothic Heavy");
        cf.addItem("Franklin Gothic Medium");
        cf.addItem("Franklin Gothic Medium Cond");
        cf.addItem("FrankRuehl");
        cf.addItem("FreesiaUPC");
        cf.addItem("Freestyle Script");
        cf.addItem("French Script MT");
        cf.addItem("Gabriola");
        cf.addItem("Garamond");
        cf.addItem("Gautami");
        cf.addItem("Georgia");
        cf.addItem("Gigi");
        cf.addItem("Gill Sans MT");
        cf.addItem("Gill Sans MT Condensed");
        cf.addItem("Gill Sans MT Ext Condensed Bold");
        cf.addItem("Gill Sans Ultra Bold");
        cf.addItem("Gill Sans Ultra Bold Condensed");
        cf.addItem("Gisha");
        cf.addItem("Gloucester MT Extra Condensed");
        cf.addItem("Goudy Old Style");
        cf.addItem("Goudy Stout");
        cf.addItem("Gulim");
        cf.addItem("GulimChe");
        cf.addItem("Gungsuh");
        cf.addItem("GungsuhChe");
        cf.addItem("Haettenschweiler");
        cf.addItem("Harlow Solid Italic");
        cf.addItem("Harrington");
        cf.addItem("HelvLight");
        cf.addItem("High Tower Text");
        cf.addItem("Hobo Std");
        cf.addItem("Impact");
        cf.addItem("Imprint MT Shadow");
        cf.addItem("Informal Roman");
        cf.addItem("IrisUPC");
        cf.addItem("Iskoola Pota");
        cf.addItem("JasmineUPC");
        cf.addItem("Jokerman");
        cf.addItem("Juice ITC");
        cf.addItem("KaiTi");
        cf.addItem("Kalinga");
        cf.addItem("Kartika");
        cf.addItem("Khmer UI");
        cf.addItem("KodchiangUPC");
        cf.addItem("Kokila");
        cf.addItem("Kozuka Gothic Pr6N B");
        cf.addItem("Kozuka Gothic Pr6N EL");
        cf.addItem("Kozuka Gothic Pr6N H");
        cf.addItem("Kozuka Gothic Pr6N L");
        cf.addItem("Kozuka Gothic Pr6N M");
        cf.addItem("Kozuka Gothic Pr6N R");
        cf.addItem("Kozuka Gothic Pro B");
        cf.addItem("Kozuka Gothic Pro EL");
        cf.addItem("Kozuka Gothic Pro H");
        cf.addItem("Kozuka Gothic Pro L");
        cf.addItem("Kozuka Gothic Pro M");
        cf.addItem("Kozuka Gothic Pro R");
        cf.addItem("Kozuka Mincho Pr6N B");
        cf.addItem("Kozuka Mincho Pr6N EL");
        cf.addItem("Kozuka Mincho Pr6N H");
        cf.addItem("Kozuka Mincho Pr6N L");
        cf.addItem("Kozuka Mincho Pr6N M");
        cf.addItem("Kozuka Mincho Pr6N R");
        cf.addItem("Kozuka Mincho Pro B");
        cf.addItem("Kozuka Mincho Pro EL");
        cf.addItem("Kozuka Mincho Pro H");
        cf.addItem("Kozuka Mincho Pro L");
        cf.addItem("Kozuka Mincho Pro M");
        cf.addItem("Kozuka Mincho Pro R");
        cf.addItem("Kristen ITC");
        cf.addItem("Kunstler Script");
        cf.addItem("Lao UI");
        cf.addItem("Latha");
        cf.addItem("Lato");
        cf.addItem("Leelawadee");
        cf.addItem("Letter Gothic Std");
        cf.addItem("Levenim MT");
        cf.addItem("LilyUPC");
        cf.addItem("Lithos Pro Regular");
        cf.addItem("Lucida Bright");
        cf.addItem("Lucida Calligraphy");
        cf.addItem("Lucida Console");
        cf.addItem("Lucida Fax");
        cf.addItem("Lucida Handwriting");
        cf.addItem("Lucida Sans");
        cf.addItem("Lucida Sans Typewriter");
        cf.addItem("Lucida Sans Unicode");
        cf.addItem("Magneto");
        cf.addItem("Maiandra GD");
        cf.addItem("Malgun Gothic");
        cf.addItem("Mangal");
        cf.addItem("Marlett");
        cf.addItem("Matura MT Script Capitals");
        cf.addItem("Meiryo");
        cf.addItem("Meiryo UI");
        cf.addItem("Microsoft Himalaya");
        cf.addItem("Microsoft JhengHei");
        cf.addItem("Microsoft New Tai Lue");
        cf.addItem("Microsoft PhagsPa");
        cf.addItem("Microsoft Sans Serif");
        cf.addItem("Microsoft Tai Le");
        cf.addItem("Microsoft Uighur");
        cf.addItem("Microsoft YaHei");
        cf.addItem("Microsoft Yi Baiti");
        cf.addItem("MingLiU");
        cf.addItem("MingLiU-ExtB");
        cf.addItem("MingLiU_HKSCS");
        cf.addItem("MingLiU_HKSCS-ExtB");
        cf.addItem("Minion Pro");
        cf.addItem("Minion Pro Cond");
        cf.addItem("Minion Pro Med");
        cf.addItem("Minion Pro SmBd");
        cf.addItem("Miriam");
        cf.addItem("Miriam Fixed");
        cf.addItem("Mistral");
        cf.addItem("Modern No. 20");
        cf.addItem("Mongolian Baiti");
        cf.addItem("Monospaced");
        cf.addItem("Monotype Corsiva");
        cf.addItem("MoolBoran");
        cf.addItem("MS Gothic");
        cf.addItem("MS Mincho");
        cf.addItem("MS Outlook");
        cf.addItem("MS PGothic");
        cf.addItem("MS PMincho");
        cf.addItem("MS Reference Sans Serif");
        cf.addItem("MS Reference Specialty");
        cf.addItem("MS UI Gothic");
        cf.addItem("MT Extra");
        cf.addItem("MV Boli");
        cf.addItem("Myriad Arabic");
        cf.addItem("Myriad Hebrew");
        cf.addItem("Myriad Pro");
        cf.addItem("Myriad Pro Cond");
        cf.addItem("Myriad Pro Light");
        cf.addItem("Narkisim");
        cf.addItem("Niagara Engraved");
        cf.addItem("Niagara Solid");
        cf.addItem("NSimSun");
        cf.addItem("Nueva Std");
        cf.addItem("Nueva Std Cond");
        cf.addItem("Nyala");
        cf.addItem("OCR A Extended");
        cf.addItem("OCR A Std");
        cf.addItem("Old English Text MT");
        cf.addItem("Onyx");
        cf.addItem("Orator Std");
        cf.addItem("Palace Script MT");
        cf.addItem("Palatino Linotype");
        cf.addItem("Papyrus");
        cf.addItem("Parchment");
        cf.addItem("Perpetua");
        cf.addItem("Perpetua Titling MT");
        cf.addItem("Plantagenet Cherokee");
        cf.addItem("Playbill");
        cf.addItem("PMingLiU");
        cf.addItem("PMingLiU-ExtB");
        cf.addItem("Poor Richard");
        cf.addItem("Poplar Std");
        cf.addItem("Prestige Elite Std");
        cf.addItem("Pristina");
        cf.addItem("Raavi");
        cf.addItem("Rage Italic");
        cf.addItem("Ravie");
        cf.addItem("Rockwell");
        cf.addItem("Rockwell Condensed");
        cf.addItem("Rockwell Extra Bold");
        cf.addItem("Rod");
        cf.addItem("Sakkal Majalla");
        cf.addItem("SansSerif");
        cf.addItem("Script MT Bold");
        cf.addItem("Segoe Print");
        cf.addItem("Segoe Script");
        cf.addItem("Segoe UI");
        cf.addItem("Segoe UI Light");
        cf.addItem("Segoe UI Semibold");
        cf.addItem("Segoe UI Symbol");
        cf.addItem("Serif");
        cf.addItem("Shonar Bangla");
        cf.addItem("Showcard Gothic");
        cf.addItem("Shruti");
        cf.addItem("SimHei");
        cf.addItem("Simplified Arabic");
        cf.addItem("Simplified Arabic Fixed");
        cf.addItem("SimSun");
        cf.addItem("SimSun-ExtB");
        cf.addItem("Snap ITC");
        cf.addItem("Source Sans Pro");
        cf.addItem("Source Sans Pro Black");
        cf.addItem("Source Sans Pro ExtraLight");
        cf.addItem("Source Sans Pro Light");
        cf.addItem("Source Sans Pro Semibold");
        cf.addItem("Stencil");
        cf.addItem("Sylfaen");
        cf.addItem("Symbol");
        cf.addItem("Tahoma");
        cf.addItem("TeamViewer13");
        cf.addItem("Tekton Pro");
        cf.addItem("Tekton Pro Cond");
        cf.addItem("Tekton Pro Ext");
        cf.addItem("Tempus Sans ITC");
        cf.addItem("Times New Roman");
        cf.addItem("Traditional Arabic");
        cf.addItem("Trajan Pro 3");
        cf.addItem("Trebuchet MS");
        cf.addItem("Tunga");
        cf.addItem("Tw Cen MT");
        cf.addItem("Tw Cen MT Condensed");
        cf.addItem("Tw Cen MT Condensed Extra Bold");
        cf.addItem("Utsaah");
        cf.addItem("Vani");
        cf.addItem("Verdana");
        cf.addItem("Vijaya");
        cf.addItem("Viner Hand ITC");
        cf.addItem("Vivaldi");
        cf.addItem("Vladimir Script");
        cf.addItem("Vrinda");
        cf.addItem("Webdings");
        cf.addItem("Wide Latin");
        cf.addItem("Wingdings");
        cf.addItem("Wingdings 2");
        cf.addItem("Wingdings 3");
        cf.revalidate();
        cf.repaint();
        cf.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                ci = (String) cf.getSelectedItem();
                if (ci.equals("Choose Font")) {
                } else {
                    textArea.setFont(new Font(ci, Font.PLAIN, fs));
                    textArea.repaint();
                }
            }
        });
        mb.add(cf);
        mb.repaint();

        JComboBox cs = new JComboBox();
        cs.setBounds(310, 0, 100, 20);
        cs.addItem("Font Size");
        cs.addItem("1");
        cs.addItem("2");
        cs.addItem("3");
        cs.addItem("4");
        cs.addItem("5");
        cs.addItem("6");
        cs.addItem("7");
        cs.addItem("8");
        cs.addItem("9");
        cs.addItem("10");
        cs.addItem("11");
        cs.addItem("12");
        cs.addItem("13");
        cs.addItem("14");
        cs.addItem("15");
        cs.addItem("16");
        cs.addItem("17");
        cs.addItem("18");
        cs.addItem("19");
        cs.addItem("20");
        cs.addItem("21");
        cs.addItem("22");
        cs.addItem("23");
        cs.addItem("24");
        cs.addItem("25");
        cs.addItem("26");
        cs.addItem("27");
        cs.addItem("28");
        cs.addItem("29");
        cs.addItem("30");
        cs.addItem("31");
        cs.addItem("32");
        cs.addItem("33");
        cs.addItem("34");
        cs.addItem("35");
        cs.addItem("36");
        cs.addItem("37");
        cs.addItem("38");
        cs.addItem("39");
        cs.addItem("40");
        cs.addItem("41");
        cs.addItem("42");
        cs.addItem("43");
        cs.addItem("44");
        cs.addItem("45");
        cs.addItem("46");
        cs.addItem("47");
        cs.addItem("48");
        cs.addItem("49");
        cs.addItem("50");
        cs.addItem("51");
        cs.addItem("52");
        cs.addItem("53");
        cs.addItem("54");
        cs.addItem("55");
        cs.addItem("56");
        cs.addItem("57");
        cs.addItem("58");
        cs.addItem("59");
        cs.addItem("60");
        cs.addItem("61");
        cs.addItem("62");
        cs.addItem("63");
        cs.addItem("64");
        cs.addItem("65");
        cs.addItem("66");
        cs.addItem("67");
        cs.addItem("68");
        cs.addItem("69");
        cs.addItem("70");
        cs.addItem("71");
        cs.addItem("72");
        cs.addItem("73");
        cs.addItem("74");
        cs.addItem("75");
        cs.addItem("76");
        cs.addItem("77");
        cs.addItem("78");
        cs.addItem("79");
        cs.addItem("80");
        cs.addItem("81");
        cs.addItem("82");
        cs.addItem("83");
        cs.addItem("84");
        cs.addItem("85");
        cs.addItem("86");
        cs.addItem("87");
        cs.addItem("88");
        cs.addItem("89");
        cs.addItem("90");
        cs.addItem("91");
        cs.addItem("92");
        cs.addItem("93");
        cs.addItem("94");
        cs.addItem("95");
        cs.addItem("96");
        cs.addItem("97");
        cs.addItem("98");
        cs.addItem("99");
        cs.addItem("100");
        cs.revalidate();
        cs.repaint();
        cs.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                String cis = (String) cs.getSelectedItem();
                if (cis.equals("Font")) {
                } else {
                    try {
                        fs = Integer.valueOf((String) cs.getSelectedItem());
                    } catch (Exception e) {
                    }
                    textArea.setFont(new Font(ci, Font.PLAIN, fs));
                    textArea.repaint();
                }
            }
        });
        mb.add(cs);
        mb.repaint();

        JScrollPane VM_Text_Editor_Scroller = new JScrollPane(textArea);
        VM_Text_Editor_Scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        VM_Text_Editor_Scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        VM_Text_Editor_Scroller.setBounds(0, 20, 490, 248);
        jif.add(VM_Text_Editor_Scroller);
        jif.repaint();

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (changed) {
                        if (JOptionPane.showConfirmDialog(jif, "Would you like to save " + currentFile + " ?", "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            if (dialog.showOpenDialog(jif) == JFileChooser.APPROVE_OPTION) {
                                pof = dialog.getSelectedFile().getAbsolutePath();
                                try {
                                    FileWriter w = new FileWriter(pof);
                                    textArea.write(w);
                                    w.close();
                                    currentFile = pof;
                                    jif.setTitle(currentFile);
                                    changed = false;
                                    save.setEnabled(false);
                                } catch (IOException e) {
                                }
                            }
                        }
                    } else {
                        if (dialog.showOpenDialog(jif) == JFileChooser.APPROVE_OPTION) {
                            pof = dialog.getSelectedFile().getAbsolutePath();
                            try {
                                FileReader r = new FileReader(pof);
                                textArea.read(r, null);
                                r.close();
                                currentFile = pof;
                                jif.setTitle(currentFile);
                                changed = false;
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(jif, "Editor can't find the file called " + currentFile);
                            }
                        }
                    }
                    save.setEnabled(true);
                } catch (Exception e) {
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!currentFile.equals(pof)) {
                    try {
                        FileWriter w = new FileWriter(pof);
                        textArea.write(w);
                        w.close();
                        currentFile = pof;
                        jif.setTitle(currentFile);
                        changed = false;
                        save.setEnabled(false);
                    } catch (IOException e) {
                    }
                } else {
                    try {
                        FileWriter w = new FileWriter(pof);
                        textArea.write(w);
                        w.close();
                        currentFile = pof;
                        jif.setTitle(currentFile);
                        changed = false;
                        save.setEnabled(false);
                    } catch (IOException e) {
                    }
                }
            }

        });
        save.setEnabled(false);

        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (dialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

                    try {
                        FileWriter w = new FileWriter(dialog.getSelectedFile().getAbsolutePath());
                        textArea.write(w);
                        w.close();
                        currentFile = dialog.getSelectedFile().getAbsolutePath();
                        jif.setTitle(currentFile);
                        changed = false;
                        save.setEnabled(false);
                    } catch (IOException e) {
                    }
                }
            }
        });
        saveAs.setEnabled(false);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int ch = JOptionPane.showConfirmDialog(jif, "Would you like to save " + currentFile + " ?", "Save", JOptionPane.YES_NO_OPTION);
                if (ch == JOptionPane.YES_OPTION) {
                    if (!currentFile.equals("VM_Untitled")) {
                        try {
                            FileWriter w = new FileWriter(currentFile);
                            textArea.write(w);
                            w.close();
                            currentFile = currentFile;
                            jif.setTitle(currentFile);
                            changed = false;
                            save.setEnabled(false);
                        } catch (IOException e) {
                        }
                    } else {
                        if (dialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            try {
                                FileWriter w = new FileWriter(dialog.getSelectedFile().getAbsolutePath());
                                textArea.write(w);
                                w.close();
                                currentFile = dialog.getSelectedFile().getAbsolutePath();
                                jif.setTitle(currentFile);
                                changed = false;
                                save.setEnabled(false);
                                jif.dispose();
                                System.gc();
                            } catch (IOException e) {
                            }
                        }
                    }
                }
                if (ch == JOptionPane.NO_OPTION) {
                    jif.dispose();
                }
                if (ch == JOptionPane.CLOSED_OPTION || ch == JOptionPane.CANCEL_OPTION) {
                }

            }
        });

        if (true) {
            try {
                FileReader r = new FileReader(Path);
                textArea.read(r, null);
                r.close();
                currentFile = Path;
                jif.setTitle(currentFile);
                changed = false;
            } catch (IOException ewre) {
                JOptionPane.showMessageDialog(jif, "Editor can't find the file called " + currentFile);
            }
        }

        jif.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                mb.setBounds(0, 0, jif.getWidth() - 10, 20);
                mb.repaint();
                VM_Text_Editor_Scroller.setBounds(0, 20, jif.getWidth() - 10, jif.getHeight() - 52);
                VM_Text_Editor_Scroller.repaint();
                jif.repaint();
            }
        });

        jif.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                if (jif.isMaximum()) {
                    mb.setBounds(0, 0, jif.getWidth() - 10, 20);
                    mb.repaint();
                    VM_Text_Editor_Scroller.setBounds(0, 20, jif.getWidth() - 10, jif.getHeight() - 52);
                    VM_Text_Editor_Scroller.repaint();
                    jif.revalidate();
                    jif.repaint();
                } else {
                    mb.setBounds(0, 0, jif.getWidth() - 10, 20);
                    mb.repaint();
                    VM_Text_Editor_Scroller.setBounds(0, 20, jif.getWidth() - 10, jif.getHeight() - 52);
                    VM_Text_Editor_Scroller.repaint();
                    jif.repaint();
                }

            }
        });

        jif.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent ife) {
                frnd = true;
                jif.removeAll();
                System.gc();
            }
        });
    }
}
