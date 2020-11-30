/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class ImagePanel extends javax.swing.JPanel 
{
    private Image BackGroundimage;


    public ImagePanel()
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