import java.util.Scanner;
import java.util.HashMap;

/** A simple command line calculator.
 *  Requires classes that solve assignment P5, UCSD CSE 12 
 *  @author Paul Kube
 */
public class Calculator {

  public static void main(String args[]) {

    // Create a Scanner to read from stdin.
    Scanner in = new Scanner(System.in);
    String line;

    // Create a HashMap that maps from Strings to Doubles.
    // This will be used as a symbol table when evaluating expressions.
    // (It implements the mapping from identifiers to their values.)
    HashMap<String,Double> symbolTable = new HashMap<String,Double>();

    // Print greeting and instructions and initial prompt to stdout.
    greet(); prompt();

    // Loop reading lines from stdin. Parse and evaluate each line,
    // printing its value to stdout.
    // An empty line terminates the program.
    while(!((line = in.nextLine()).isEmpty())) {
      
      // parse the line, getting a pointer to the root of an AST
      ASTNode root = Expr.parse(line);
      
      if(root == null) {
	System.out.println("SYNTAX ERROR somewhere on that line!");
      } else {
	try {
	  
	  // evaluate the AST 
	  System.out.println("=> " + root.eval(symbolTable));
	  
	} catch (NullPointerException e) {
	  throw e; // fatal
	} catch (IndexOutOfBoundsException e) {
	  throw e; // fatal
	} catch (RuntimeException e) {
	  // this should be a semantic error in evaluating the AST
	  // we just print the message and continue
	  System.out.println(e.getMessage());
	}

      }
      
      prompt();  // for the next line
      
    }

    // done.
    System.out.println("Thanks for using the Calculator!  See you later.");
  }

  private static void greet() {
    System.out.println("Welcome to the Calculator.");
    System.out.println("Type an expression, then press Enter.");
    System.out.println("An empty line exits the program.\n");
  }

  private static void prompt() {
    System.out.print("CALC: ");
  }
  
}
