/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-05-27
 *
 * This is a class representing the <expr> nonterminal symbol in an abstract
 * syntax tree. This class has no public constructors; the public interface for
 * creating an instance of the class is the static <tt>parse(String s)</tt>
 * factory method.
 */

import java.util.*;

/**
 * Outer Class: Expr
 */
public class Expr extends ASTNode
{
  /**
   * Private Constructor
   */
  private Expr() { super(); }

  /**
   * A factory method that parses a String according to the BNF definition for
   * <expr>. The BNF definition is: 
   * <expr> := <assmt> | <oprn>
   *
   * @param  s the String to parse
   * @return an Expr object that is the root of an abstract syntax (sub)tree
   * resulting from the parse, or <tt>null</tt> if the String cannot be parsed
   * as a <expr>
   */
  public static Expr parse( java.lang.String s )
  {
    if(s == null || s.trim().length() == 0)
    {
      return null;
    }

    // Trim String
    s = s.trim();

    // Attempt to parse as <assmt> and <oprn>
    Assmt attemptAssmt = Assmt.parse(s);
    Oprn  attemptOprn  = Oprn.parse(s);

    // If it successfully parsed as <assmt>
    if(attemptAssmt != null)
    {
      Expr returnExpr = new Expr();
      returnExpr.addChild(attemptAssmt);
      return returnExpr;
    }

    // If it successfully parsed as <oprn>
    else if(attemptOprn != null)
    {
      Expr returnExpr = new Expr();
      returnExpr.addChild(attemptOprn);
      return returnExpr;
    }

    // If neither, return null
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
    // Check Arity
    if(this.arity() != 1)
    {
      throw new IllegalStateException("Arity != 1. Arity is "+this.arity());
    }

    // Check Child
    else if(!(this.getChild(0) instanceof Assmt ||
              this.getChild(0) instanceof Oprn))
    {
      throw new IllegalStateException("Child must be Assmt or Oprn");
    }

    // If all is good, evaluate child
    else
    {
      return this.getChild(0).eval(symtab);
    }
  }
} // End of public class Expr extends ASTNode
