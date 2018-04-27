package view;

import java.awt.Color;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class StyleToggler extends SimpleAttributeSet
{
  private SimpleAttributeSet[] sets = new SimpleAttributeSet[2];
  private int toggler = 0;
  
  public StyleToggler()
  {
    this.sets[0] = new SimpleAttributeSet();
    this.sets[1] = new SimpleAttributeSet();
  }
  
  public AttributeSet next()
  {
    return this.sets[(this.toggler++ % 2)];
  }
}