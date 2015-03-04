/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-05-27
 *
 * This is a class representing the <ident> nonterminal symbol in an abstract
 * syntax tree. This class has no public constructors; the public interface for
 * creating an instance of the class is the static <tt>parse(String s)</tt>
 * factory method.
 */

import java.util.*;

/**
 * Outer Class: Ident
 */
public class Ident extends ASTNode
{
  String val;

  /**
   * Private Constructor
   */
  private Ident() { super(); }

  /**
   * A factory method that parses a String according to the BNF definition for
   * <ident>. The definition is: Any String that starts with a
   * JavaIdentifierStart character, followed by 0 or more JavaIdentifierPart
   * characters.
   *
   * @param  s the String to parse
   * @return an Ident object that is the root of an abstract syntax subtree
   * resulting from the parse, or null if the String cannot be parsed as a
   * <ident>
   */
  public static Ident parse( java.lang.String s )
  {
    if(s == null || s.trim().length() == 0)
    {
      return null;
    }

    // Trim String
    s = s.trim();

    // If first character is JavaIdentifierStart
    if(Character.isJavaIdentifierStart(s.charAt(0)))
    {
      Ident returnIdent = new Ident();
      returnIdent.val = ""+s.charAt(0);

      // Check for JavaIdentifierPart chars after
      int index = 1;
      boolean valid = true;
      while(index > 0 && index < s.length()) // While index < string length
      {
        if(Character.isJavaIdentifierPart(s.charAt(index)))
        {
          returnIdent.val += s.charAt(index++);
        }
        else
        {
          valid = false;
          break;
        }
      }

      if(valid)
      {
        return returnIdent;
      }
      else
      {
        return null;
      }
    }
    else
    {
      return null;
    }
  }

  /**
   * Evaluate the abstract syntax (sub)tree that is rooted at this ASTNode in
   * the context of the given symbol table, and return the result.
   *
   * @param symtab A map from variable identifiers to values, to use as a
   * symbol table in the evaluation
   * @return the <tt>double</tt> value that is the result of evaluating the
   * abstract syntax (sub)tree rooted at this ASTNode
   */
  public double eval( java.util.Map<java.lang.String,java.lang.Double> symtab )
  {
    if(this.arity() != 0)
    {
      throw new IllegalStateException("Arity != 0. Arity is "+this.arity());
    }
    else
    {
      // If key is in the Map, return its value
      try
      {
        return symtab.get(this.val);
      }
      catch( NullPointerException e )
      {
        throw new RuntimeException("UNINITIALIZED VARIABLE: "+this.val);
      }
    }
  }
} // End of public class Ident extends ASTNode
