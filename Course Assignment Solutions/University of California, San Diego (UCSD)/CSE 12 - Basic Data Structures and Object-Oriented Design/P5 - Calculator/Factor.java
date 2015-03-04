/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-05-27
 *
 * This is a class representing the <factor> nonterminal symbol in an abstract
 * syntax tree. This class has no public constructors; the public interface for
 * creating an instance of the class is the static <tt>parse(String s)</tt>
 * factory method.
 */

import java.util.*;

/**
 * Outer Class: Factor
 */
public class Factor extends ASTNode
{
  /**
   * Private Constructor
   */
  private Factor() { super(); }

  /**
   * A factory method that parses a String according to the BNF definition for
   * <factor>. The BNF definition is: 
   * <factor> := <const> | <ident> | "(" <expr> ")"
   *
   * @param  s the String to parse
   * @return an Factor object that is the root of an abstract syntax (sub)tree
   * resulting from the parse, or <tt>null</tt> if the String cannot be parsed
   * as a <factor>
   */
  public static Factor parse( java.lang.String s )
  {
    if(s == null || s.trim().length() == 0)
    {
      return null;
    }

    // Trim String
    s = s.trim();

    // Check for (<expr>)
    // If first char is "(" AND last char is ")"
    if(s.charAt(0) == '(' && s.charAt(s.length()-1) == ')')
    {
      // Create new substring without parenthesis
      String sub = s.substring(1,s.length()-1);

      // Attempt to parse substring as <expr>
      Expr attemptExpr = Expr.parse(sub);

      // If it successfully parsed as <expr>
      if(attemptExpr != null)
      {
        Factor returnFactor = new Factor();
        returnFactor.addChild(attemptExpr);
        return returnFactor;
      }
      else
      {
        return null;
      }
    }
    else
    {
      // Attempt to parse as <const> and <ident>
      Const attemptConst = Const.parse(s);
      Ident attemptIdent = Ident.parse(s);

      // If it successfully parsed as <const>
      if(attemptConst != null)
      {
        Factor returnFactor = new Factor();
        returnFactor.addChild(attemptConst);
        return returnFactor;
      }

      // If it successfully parsed as <ident>
      else if(attemptIdent != null)
      {
        Factor returnFactor = new Factor();
        returnFactor.addChild(attemptIdent);
        return returnFactor;
      }

      else
      {
        return null;
      }
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

    // Check child
    else if(!(this.getChild(0) instanceof Const ||
              this.getChild(0) instanceof Ident ||
              this.getChild(0) instanceof Expr))
    {
      throw new IllegalStateException("Child must be Const, Ident, or Expr");
    }

    // If all is good, evaluate child
    else
    {
      return this.getChild(0).eval(symtab);
    }
  }
} // End of public class Factor extends ASTNode
