/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-05-27
 *
 * This is a class representing the <const> nonterminal symbol in an abstract
 * syntax tree. This class has no public constructors; the public interface for
 * creating an instance of the class is the static <tt>parse(String s)</tt>
 * factory method.
 */

import java.util.*;

/**
 * Outer Class: Const
 */
public class Const extends ASTNode
{
  private double val;

  /**
   * Private Constructor
   */
  private Const() { super(); }

  /**
   * A factory method that parses a String according to the BNF definition for
   * <const>. The definition is: Any String that can be parsed by
   * <tt>java.lang.Double.parseDouble(s)</tt> without throwing a
   * NumberFormatException.
   *
   * @param  s the String to parse
   * @return a Const object that is the root of an abstract syntax subtree
   * resulting from the parse, or null if the String cannot be parsed as a
   * <const>
   */
  public static Const parse( java.lang.String s )
  {
    Const returnConst = new Const();

    // Attempt to parse as Double
    try
    {
      returnConst.val = Double.parseDouble(s);
      return returnConst;
    }
    catch( NumberFormatException e ) { return null; }
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
      return this.val;
    }
  }
} // End of public class Const extends ASTNode
