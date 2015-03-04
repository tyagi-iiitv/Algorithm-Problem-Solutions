/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-05-27
 *
 * This is a class representing the <oprn> nonterminal symbol in an abstract
 * syntax tree. This class has no public constructors; the public interface for
 * creating an instance of the class is the static <tt>parse(String s)</tt>
 * factory method.
 */

import java.util.*;

/**
 * Outer Class: Oprn
 */
public class Oprn extends ASTNode
{
  private int operator; // 0 = nothing, 1 = +, 2 = -

  /**
   * Private Constructor
   */
  private Oprn() { super(); }

  /**
   * A factory method that parses a String according to the BNF definition for
   * <oprn>. The BNF definition is: 
   * <oprn> := <term> | <oprn> "+" <term> | <oprn> "-" <term>
   *
   * @param  s the String to parse
   * @return an Oprn object that is the root of an abstract syntax (sub)tree
   * resulting from the parse, or <tt>null</tt> if the String cannot be parsed
   * as a <oprn>
   */
  public static Oprn parse( java.lang.String s )
  {
    if(s == null || s.trim().length() == 0)
    {
      return null;
    }

    // Trim String
    s = s.trim();

    // Attempt to parse as <term>
    Term attemptTerm = Term.parse(s);

    // If it successfully parsed as <factor>
    if(attemptTerm != null)
    {
      Oprn returnOprn = new Oprn();
      returnOprn.addChild(attemptTerm);
      return returnOprn;
    }

    // If not, check for + or -
    else
    {
      // Find index of last + and last - (if they exist)
      int plusIndex  = s.lastIndexOf('+');
      int minusIndex = s.lastIndexOf('-');

      // If neither exists, return null
      if(plusIndex == -1 && minusIndex == -1)
      {
        return null;
      }

      // If both exist
      else if(plusIndex != -1 && minusIndex != -1)
      {
        // Check for negative number
        int check = minusIndex-1;
        while(check >= 0 && s.charAt(check) == ' ')
        {
          check--;
        }
        if(check >= 0 && (s.charAt(check) == '+' || s.charAt(check) == '-' ||
           s.charAt(check) == '*' || s.charAt(check) == '/'))
        {
          while(check >= 0 && s.charAt(check) != '-')
          {
            check--;
          }
          minusIndex = check;
        }

        // Check for positive number
        check = plusIndex-1;
        while(check >= 0 && s.charAt(check) == ' ')
        {
          check--;
        }
        if(check >= 0 && (s.charAt(check) == '+' || s.charAt(check) == '-' ||
           s.charAt(check) == '*' || s.charAt(check) == '/'))
        {
          while(check >= 0 && s.charAt(check) != '+')
          {
            check--;
          }
          plusIndex = check;
        }

        // Find which one comes last
        int largerIndex = -1;
        int parsedOp = -1;
        if(plusIndex > minusIndex)
        {
          largerIndex = plusIndex;
        }
        else
        {
          largerIndex = minusIndex;
        }

        // Check for ()
        int leftParIndex = s.lastIndexOf('(');
        boolean valid = false;
        for(int i = 0; i < leftParIndex; i++)
        {
          // Check for symbol before "("
          if(s.charAt(i) == '+' || s.charAt(i) == '-')
          {
            valid = true;
          }
        }
        if(leftParIndex != -1 && valid && leftParIndex < largerIndex)
        {
          largerIndex--;
          while(s.charAt(largerIndex) != '+' && s.charAt(largerIndex) != '-' &&
                largerIndex >= leftParIndex)
          {
            largerIndex--;
          }
        }

        // Assign Operator
        if(s.charAt(largerIndex) == '+')
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

          // Attempt to parse first as <oprn> and last as <term>
          Oprn attemptFirst = Oprn.parse(first);
          Term attemptLast  = Term.parse(last);

          // If both parsed successfully
          if(attemptFirst != null && attemptLast != null)
          {
            Oprn returnOprn = new Oprn();
            returnOprn.addChild(attemptFirst);
            returnOprn.addChild(attemptLast);
            returnOprn.operator = parsedOp;
            return returnOprn;
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

      // If only + exists
      else if(plusIndex != -1)
      {
        // Check for positive number
        int check = plusIndex-1;
        while(check >= 0 && s.charAt(check) == ' ')
        {
          check--;
        }
        if(s.charAt(check) == '+' || s.charAt(check) == '-' ||
           s.charAt(check) == '*' || s.charAt(check) == '/' ||
           s.charAt(check) == '=')
        {
          while(check >= 0 && s.charAt(check) != '+')
          {
            check--;
          }
          plusIndex = check;
        }
        
        // Check for ()
        int leftParIndex = s.lastIndexOf('(');
        boolean valid = false;
        for(int i = 0; i < leftParIndex; i++)
        {
          // Check for symbol before "("
          if(s.charAt(i) == '+' || s.charAt(i) == '-')
          {
            valid = true;
          }
        }
        if(leftParIndex != -1 && valid && leftParIndex < plusIndex)
        {
          plusIndex--;
          while(s.charAt(plusIndex) != '+' && plusIndex >= leftParIndex)
          {
            plusIndex--;
          }
        }

        // Split String around plus index
        if(plusIndex != 0 && plusIndex+1 != s.length())
        {
          String first = s.substring(0,plusIndex);
          String last  = s.substring(plusIndex+1,s.length());
          first = first.trim();
          last  = last.trim();

          // Attempt to parse first as <oprn> and last as <term>
          Oprn attemptFirst = Oprn.parse(first);
          Term attemptLast  = Term.parse(last);

          // If both parsed successfully
          if(attemptFirst != null && attemptLast != null)
          {
            Oprn returnOprn = new Oprn();
            returnOprn.addChild(attemptFirst);
            returnOprn.addChild(attemptLast);
            returnOprn.operator = 1;
            return returnOprn;
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

      // If only - exists
      else if(minusIndex != -1)
      {
        // Check for negative number
        int check = minusIndex-1;
        while(check >= 0 && s.charAt(check) == ' ')
        {
          check--;
        }
        if(s.charAt(check) == '+' || s.charAt(check) == '-' ||
           s.charAt(check) == '*' || s.charAt(check) == '/')
        {
          while(check >= 0 && s.charAt(check) != '-')
          {
            check--;
          }
          minusIndex = check;
        }

        // Check for ()
        int leftParIndex = s.lastIndexOf('(');
        boolean valid = false;
        for(int i = 0; i < leftParIndex; i++)
        {
          // Check for symbol before "("
          if(s.charAt(i) == '+' || s.charAt(i) == '-')
          {
            valid = true;
          }
        }
        if(leftParIndex != -1 && valid && leftParIndex < minusIndex)
        {
          minusIndex--;
          while(s.charAt(minusIndex) != '-' && minusIndex >= leftParIndex)
          {
            minusIndex--;
          }
        }

        // Split String around minus index
        if(minusIndex != 0 && minusIndex+1 != s.length())
        {
          String first = s.substring(0,minusIndex);
          String last  = s.substring(minusIndex+1,s.length());
          first = first.trim();
          last  = last.trim();

          // Attempt to parse first as <oprn> and last as <term>
          Oprn attemptFirst = Oprn.parse(first);
          Term attemptLast  = Term.parse(last);

          // If both parsed successfully
          if(attemptFirst != null && attemptLast != null)
          {
            Oprn returnOprn = new Oprn();
            returnOprn.addChild(attemptFirst);
            returnOprn.addChild(attemptLast);
            returnOprn.operator = 2;
            return returnOprn;
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
    // Find how many children this has. 1 = <term>, 2 = <oprn> +- <term>
    int numChildren = this.arity();

    // If 1 child <term>
    if(numChildren == 1)
    {
      if(!(this.getChild(0) instanceof Term))
      {
        throw new IllegalStateException("If 1 child, it must be type Term");
      }
      else
      {
        // Point to child for convenience
        Term child = (Term)this.getChild(0);
        return child.eval(symtab);
      }
    }

    // If 2 children <oprn> +- <term>
    else if(numChildren == 2)
    {
      if(!(this.getChild(0) instanceof Oprn &&
           this.getChild(1) instanceof Term))
      {
        throw new IllegalStateException("If 2 children, first must be Oprn "+
                                        "and second must be Term");
      }
      else
      {
        // Point to children for convenience
        Oprn child0 = (Oprn)this.getChild(0);
        Term child1 = (Term)this.getChild(1);

        // If +, add them
        if(this.operator == 1)
        {
          return child0.eval(symtab) + child1.eval(symtab);
        }

        // If -, subtract them
        else if(this.operator == 2)
        {
          return child0.eval(symtab) - child1.eval(symtab);
        }

        // If neither, something went wrong
        else
        {
          throw new IllegalStateException("Operator is not + or -");
        }
      }
    }

    // If wrong # children
    else
    {
      throw new IllegalStateException("Arity != 1||2. Arity is "+this.arity());
    }
  }
} // End of public class Oprn extends ASTNode
