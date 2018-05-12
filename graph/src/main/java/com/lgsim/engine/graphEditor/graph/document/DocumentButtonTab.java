package com.lgsim.engine.graphEditor.graph.document;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

public class DocumentButtonTab extends JPanel
{
  private final JTabbedPane pane;

  public DocumentButtonTab(final JTabbedPane pane)
  {
    super(new FlowLayout(FlowLayout.LEFT, 0, 0));
    if (pane == null)
    {
      throw new NullPointerException("TabbedPane is null");
    }
    this.pane = pane;
    setOpaque(false);
    JLabel label = new JLabel()
    {
      public String getText()
      {
        int i = pane.indexOfTabComponent(DocumentButtonTab.this);
        if (i != -1)
        {
          return pane.getTitleAt(i);
        }
        return null;
      }
    };
    add(label);
    label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
    JButton button = new TabButton();
    add(button);
    setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
  }


  private class TabButton extends JButton implements ActionListener
  {
    TabButton()
    {
      int size = 17;
      setPreferredSize(new Dimension(size, size));
      setToolTipText("close this tab");
      setUI(new BasicButtonUI());
      setContentAreaFilled(false);
      setFocusable(false);
      setBorder(BorderFactory.createEtchedBorder());
      setBorderPainted(false);
      addMouseListener(buttonMouseListener);
      setRolloverEnabled(true);
      addActionListener(this);
    }


    public void actionPerformed(ActionEvent e)
    {
      int i = pane.indexOfTabComponent(DocumentButtonTab.this);
      if (i != -1)
      {
        pane.remove(i);
      }
    }


    public void updateUI()
    {
    }


    protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g.create();
      if (getModel().isPressed())
      {
        g2.translate(1, 1);
      }
      g2.setStroke(new BasicStroke(2));
      g2.setColor(Color.BLACK);
      if (getModel().isRollover())
      {
        g2.setColor(Color.MAGENTA);
      }
      int delta = 6;
      g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
      g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
      g2.dispose();
    }
  }


  private final static MouseListener buttonMouseListener = new MouseAdapter()
  {
    public void mouseEntered(MouseEvent e)
    {
      Component component = e.getComponent();
      if (component instanceof AbstractButton)
      {
        AbstractButton button = (AbstractButton) component;
        button.setBorderPainted(true);
      }
    }


    public void mouseExited(MouseEvent e)
    {
      Component component = e.getComponent();
      if (component instanceof AbstractButton)
      {
        AbstractButton button = (AbstractButton) component;
        button.setBorderPainted(false);
      }
    }
  };
}


