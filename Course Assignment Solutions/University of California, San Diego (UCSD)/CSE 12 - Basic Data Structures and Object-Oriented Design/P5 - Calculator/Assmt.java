/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-05-27
 *
 * This is a class representing the <assmt> nonterminal symbol in an abstract
 * syntax tree. This class has no public constructors; the public interface for
 * creating an instance of the class is the static <tt>parse(String s)</tt>
 * factory method.
 */

import java.util.*;

/**
 * Outer Class: Assmt
 */
public class Assmt extends ASTNode
{
  /**
   * Private Constructor
   */
  private Assmt() { super(); }

  /**
   * A factory method that parses a String according to the BNF definition for
   * <assmt>. The BNF definition is: 
   * <assmt> := <ident> "=" <expr>
   *
   * @param  s the String to parse
   * @return an Assmt object that is the root of an abstract syntax (sub)tree
   * resulting from the parse, or <tt>null</tt> if the String cannot be parsed
   * as a <assmt>
   */
  public static Assmt parse( java.lang.String s )
  {
    if(s == null || s.trim().length() == 0)
    {
      return null;
    }

    // Trim String
    s = s.trim();

    // Find index of "=" (if any)
    int equalsIndex = s.indexOf('=');

    // Check for (something = something)
    boolean valid = true;
    int check = equalsIndex-1;
    while(check >= 0 && s.charAt(check) != '+' && s.charAt(check) != '-')
    {
      if(s.charAt(check) == '(')
      {
        valid = false;
      }
      check--;
    }

    // If it found "=", split string and work on the parts
    if(valid &&
       equalsIndex != -1 &&           // Check for not found at all
       equalsIndex !=  0 &&           // Check for not first char
       equalsIndex != (s.length()-1)) // Check for not last char
    {
      String first = s.substring(0,equalsIndex);
      String last  = s.substring(equalsIndex+1,s.length());

      // Trim both
      first = first.trim();
      last  = last.trim();

      // Attempt to parse first as <ident> and last as <expr>
      Ident attemptIdent = Ident.parse(first);
      Expr  attemptExpr  = Expr.parse(last);

      // If both parsed successfully
      if(attemptIdent != null && attemptExpr != null)
      {
        Assmt returnAssmt = new Assmt();
        returnAssmt.addChild(attemptIdent);
        returnAssmt.addChild(attemptExpr);
        return returnAssmt;
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
    // Check Arity
    if(this.arity() != 2)
    {
      throw new IllegalStateException("Arity != 2. Arity is "+this.arity());
    }

    // Check children types
    else if(!(this.getChild(0) instanceof Ident))
    {
      throw new IllegalStateException("First Child is not type Ident");
    }
    else if(!(this.getChild(1) instanceof Expr))
    {
      throw new IllegalStateException("Second Child is not type Expr");
    }

    // If all is good, evaluate
    else
    {
      // Point to children for convenience
      Ident child0 = (Ident)this.getChild(0);
      Expr  child1 = (Expr)this.getChild(1);

      // Store key and value
      String key   = child0.val;
      double value = child1.eval(symtab);
      symtab.put(key,value);

      return value;
    }
  }
} // End of public class Assmt extends ASTNode
