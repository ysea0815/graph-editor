package com.lgsim.engine.graphEditor.widget.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class test {
 static class window extends JWindow
 implements MouseListener, MouseMotionListener{
 JPopupMenu popMenu;
 JPanel panel = new JPanel();
 Point location;
 MouseEvent pressed;
 public window(){
 addMouseListener( this );
 addMouseMotionListener( this );
 JLabel label = new JLabel("JWindow", JLabel.CENTER);
 initPopMenu();
 add(label);
 setVisible(true);
 setAlwaysOnTop(true);
 setLocationRelativeTo(null);
 pack();
 }
 public void initPopMenu(){
 popMenu = new JPopupMenu();
 JMenuItem item;
 item = new JMenuItem("Title" );
 item.setEnabled(false);
 popMenu.add(item);
 popMenu.addSeparator();
 item = new JMenuItem("Item One" );
 popMenu.add(item);
 item = new JMenuItem("Item 2" );
 popMenu.add(item);
 item = new JMenuItem("Item 3" );
 popMenu.add(item);
 }
 public void mousePressed(MouseEvent e)
 {
 pressed = e;
 int nModifier = e.getModifiers();
 if (((nModifier & InputEvent.BUTTON2_MASK)!= 0)||
 ((nModifier & InputEvent.BUTTON3_MASK)!= 0))
 popMenu.show( this, e.getX(), e.getY() );
 }
 public void mouseClicked(MouseEvent e) {
 }
 public void mouseReleased(MouseEvent e) {}
 public void mouseDragged(MouseEvent me){
 }
 public void mouseMoved(MouseEvent e) {}
 public void mouseEntered(MouseEvent e) {}
 public void mouseExited(MouseEvent e) {}
 }
 public static void main(String[] args) {
 window dw = new window();
 }
}