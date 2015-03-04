/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-05-27
 *
 * This is a class representing the <term> nonterminal symbol in an abstract
 * syntax tree. This class has no public constructors; the public interface for
 * creating an instance of the class is the static <tt>parse(String s)</tt>
 * factory method.
 */

import java.util.*;

/**
 * Outer Class: Term
 */
public class Term extends ASTNode
{
  private int operator; // 0 = nothing, 1 = *, 2 = /

  /**
   * Private Constructor
   */
  private Term() { super(); }

  /**
   * A factory method that parses a String according to the BNF definition for
   * <term>. The BNF definition is: 
   * <term> := <factor> | <term> "*" <factor> | <term> "/" <factor>
   *
   * @param  s the String to parse
   * @return a Term object that is the root of an abstract syntax (sub)tree
   * resulting from the parse, or <tt>null</tt> if the String cannot be parsed
   * as a <term>
   */
  public static Term parse( java.lang.String s )
  {
    if(s == null || s.trim().length() == 0)
    {
      return null;
    }

    // Trim String
    s = s.trim();

    // Check for * or /
    int starIndex  = s.lastIndexOf('*');
    int slashIndex = s.lastIndexOf('/');

    // Attempt to parse as <factor>
    Factor attemptFactor = Factor.parse(s);

    // If it successfully parsed as <factor>
    if(attemptFactor != null)
    {
      Term returnTerm = new Term();
      returnTerm.addChild(attemptFactor);
      return returnTerm;
    }
    else
    {
      // If both exist
      if(starIndex != -1 && slashIndex != -1)
      {
        // Find which one comes last
        int largerIndex = -1;
        int parsedOp = -1;
        if(starIndex > slashIndex)
        {
          largerIndex = starIndex;
        }
        else
        {
          largerIndex = slashIndex;
        }

        // Check for ()
        int leftParIndex = s.lastIndexOf('(');
        boolean valid = false;
        for(int i = 0; i < leftParIndex; i++)
        {
          // Check for symbol before "("
          if(s.charAt(i) == '*' || s.charAt(i) == '/')
          {
            valid = true;
          }
        }
        if(leftParIndex != -1 && valid && leftParIndex < largerIndex)
        {
          largerIndex--;
          while(s.charAt(largerIndex) != '*' && s.charAt(largerIndex) != '/' &&
                largerIndex >= leftParIndex)
          {
            largerIndex--;
          }
        }

        // Assign Operator
        if(s.charAt(largerIndex) == '*')
        {
          parsedOp = 1;
        }
        else
        {
          parsedOp = 2;
        }

        // Split String around the larger index
        if(largerIndex != 0 && largerIndex+1 != s.length())
        {
          String first = s.substring(0,largerIndex);
          String last  = s.substring(largerIndex+1,s.length());
          first = first.trim();
          last  = last.trim();

          // Attempt to parse first as <term> and last as <factor>
          Term   attemptFirst = Term.parse(first);
          Factor attemptLast  = Factor.parse(last);

          // If both parsed successfully
          if(attemptFirst != null && attemptLast != null)
          {
            Term returnTerm = new Term();
            returnTerm.addChild(attemptFirst);
            returnTerm.addChild(attemptLast);
            returnTerm.operator = parsedOp;
            return returnTerm;
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

      // If only * exists
      else if(starIndex != -1)
      {
        // Check for ()
        int leftParIndex = s.lastIndexOf('(');
        boolean valid = false;
        for(int i = 0; i < leftParIndex; i++)
        {
          // Check for symbol before "("
          if(s.charAt(i) == '*' || s.charAt(i) == '/')
          {
            valid = true;
          }
        }
        if(leftParIndex != -1 && valid && leftParIndex < starIndex)
        {
          starIndex--;
          while(s.charAt(starIndex) != '*' && starIndex >= leftParIndex)
          {
            starIndex--;
          }
        }

        // Split String around star index
        if(starIndex != 0 && starIndex+1 != s.length())
        {
          String first = s.substring(0,starIndex);
          String last  = s.substring(starIndex+1,s.length());
          first = first.trim();
          last  = last.trim();

          // Attempt to parse first as <term> and last as <factor>
          Term   attemptFirst = Term.parse(first);
          Factor attemptLast  = Factor.parse(last);

          // If both parsed successfully
          if(attemptFirst != null && attemptLast != null)
          {
            Term returnTerm = new Term();
            returnTerm.addChild(attemptFirst);
            returnTerm.addChild(attemptLast);
            returnTerm.operator = 1;
            return returnTerm;
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

      // If only / exists
      else if(slashIndex != -1)
      {
        // Check for ()
        int leftParIndex = s.lastIndexOf('(');
        boolean valid = false;
        for(int i = 0; i < leftParIndex; i++)
        {
          // Check for symbol before "("
          if(s.charAt(i) == '*' || s.charAt(i) == '/')
          {
            valid = true;
          }
        }
        if(leftParIndex != -1 && valid && leftParIndex < slashIndex)
        {
          slashIndex--;
          while(s.charAt(slashIndex) != '/' && slashIndex >= leftParIndex)
          {
            slashIndex--;
          }
        }

        // Split String around slash index
        if(slashIndex != 0 && slashIndex+1 != s.length())
        {
          String first = s.substring(0,slashIndex);
          String last  = s.substring(slashIndex+1,s.length());
          first = first.trim();
          last  = last.trim();

          // Attempt to parse first as <term> and last as <factor>
          Term   attemptFirst = Term.parse(first);
          Factor attemptLast  = Factor.parse(last);

          // If both parsed successfully
          if(attemptFirst != null && attemptLast != null)
          {
            Term returnTerm = new Term();
            returnTerm.addChild(attemptFirst);
            returnTerm.addChild(attemptLast);
            returnTerm.operator = 2;
            return returnTerm;
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

      // If somehow have a weird case, return null
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
    // Find how many children this has. 1 = <factor>, 2 = <term> */ <factor>
    int numChildren = this.children.size();

    // If 1 child <factor>
    if(numChildren == 1)
    {
      if(!(this.getChild(0) instanceof Factor))
      {
        throw new IllegalStateException("If 1 child, it must be type Factor");
      }
      else
      {
        // Point to child for convenience
        Factor child = (Factor)this.getChild(0);
        return child.eval(symtab);
      }
    }

    // If 2 children <term> */ <factor>
    else if(numChildren == 2)
    {
      if(!(this.getChild(0) instanceof Term &&
           this.getChild(1) instanceof Factor))
      {
        throw new IllegalStateException("If 2 children, first must be Term "+
                                        "and second must be Factor");
      }
      else
      {
        // Point to children for convenience
        Term   child0 = (Term)this.getChild(0);
        Factor child1 = (Factor)this.getChild(1);

        // If *, multiply them
        if(this.operator == 1)
        {
          return child0.eval(symtab) * child1.eval(symtab);
        }

        // If /, divide them
        else if(this.operator == 2)
        {
          return child0.eval(symtab) / child1.eval(symtab);
        }

        // If neither, something went wrong
        else
        {
          throw new IllegalStateException("Operator is not * or /");
        }
      }
    }

    // If wrong # children
    else
    {
      throw new IllegalStateException("Arity != 1||2. Arity is "+this.arity());
    }
  }
} // End of public class Term extends ASTNode
