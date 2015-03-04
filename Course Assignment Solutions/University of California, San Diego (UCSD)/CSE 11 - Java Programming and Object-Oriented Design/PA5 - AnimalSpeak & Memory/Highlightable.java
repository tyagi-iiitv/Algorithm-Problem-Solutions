/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 11, 2013
 * File:  Highlightable.java
 * Sources of Help: The PA5 instructions posted on Ord's website.
 *
 * This file (interface) creates an outline for the following methods:
 *   void  showHighlight(Color color)
 *   void  hideHighlight()
 *   Color getHighLightColor()
 *
 * Ord gave us this code.
 */

import java.awt.Color;

public interface Highlightable
{
  public void  showHighlight(Color color);
  public void  hideHighlight();
  public Color getHighlightColor();
} // End of public interface Highlightable
