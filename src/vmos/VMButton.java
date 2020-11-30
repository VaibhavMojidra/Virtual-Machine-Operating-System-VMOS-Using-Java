package vmos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class VMButton extends javax.swing.JButton
{
    private Image BackGroundimage;


    public VMButton()
    {}

    public Image getImage() 
    {return BackGroundimage;}

    public void setImage(Image image) 
    {
        this.BackGroundimage = image;
        Dimension size=new Dimension(this.getWidth(),this.getHeight());
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setSize(size);
        this.setOpaque(false);
    }
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        if(this.BackGroundimage!=null)
        {
            g.drawImage(BackGroundimage, 0, 0,this.getWidth(),this.getHeight(),null);
        }
    }
}
