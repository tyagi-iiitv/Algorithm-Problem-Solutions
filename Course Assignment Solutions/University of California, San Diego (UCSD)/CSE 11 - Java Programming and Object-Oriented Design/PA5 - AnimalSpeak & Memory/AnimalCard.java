/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 11, 2013
 * File:  AnimalCard.java
 * Sources of Help: The PA5 instructions posted on Ord's website.
 *
 * This file simply extends the three other previously-made interfaces,
 * "Speakable", "Highlightable", and "Hideable". This is to define the
 * AnimalCard type.
 *
 * Ord gave us this code.
 */

import java.awt.*;

interface AnimalCard extends Speakable, Highlightable, Hideable
{
  public abstract boolean equals(Object o);
  public abstract boolean isShown();
  public abstract void    setHighlightColor(Color color);
} // End of interface AnimalCard extends Speakable, Highlightable, Hideable
