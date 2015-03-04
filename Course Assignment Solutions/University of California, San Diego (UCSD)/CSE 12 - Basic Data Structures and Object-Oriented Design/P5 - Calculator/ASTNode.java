import java.util.*;

/**
 * The parent abstract class of all types of abstract syntax tree (AST) nodes.
 * Constructors and methods of ASTNode permit an ASTNode to have any number 
 * of ASTNode children.
 * 
 * <p>In addition to the instance methods shown here, a class <tt>X</tt> extending
 * <tt>ASTNode</tt> abstract class must provide a static factory method with this signature:
 * 
 *  <pre>public static X parse(java.lang.String s)</pre>
 *
 * This method will parse the given String according to the rules for
 * the nonterminal symbol represented by class <tt>X</tt>, and return an instance of
 * class <tt>X</tt> which is the root of an abstract syntax (sub)tree rooted at that
 * instance that corresponds to the parse of the String; or null if such a parse is not
 * possible.
 *
 * @author Paul Kube
 */

public abstract class ASTNode implements Iterable<ASTNode> {

  /**
   *  The List of ASTNodes that are children of this ASTNode.
   */
  protected List<ASTNode> children;

  /**
   *  Initialize an ASTNode with an empty list of children.
   */
  protected ASTNode() {
    children = new LinkedList<ASTNode>();
  }

  /**
   *  Initialize an ASTNode with children given by the variable length argument list.
   *  @param children One or more ASTNodes that are to be children of this ASTNode.
   *  @throws NullPointerException if any of the arguments are <tt>null</tt>
   */
  protected ASTNode(ASTNode... children) {
    this();
    for(ASTNode c: children) this.addChild(c);
  }

  /**
   *  Append an ASTNode to the list of children of this ASTNode.
   *  <br>PRECONDITION: n is a non-null ASTNode
   *  <br>POSTCONDITION: n has been appended to the list of children of this ASTNode.
   *  @param n the ASTNode to append to the list of children of this ASTNode
   *  @throws NullPointerException if n is <tt>null</tt>
   */
  protected void addChild(ASTNode n) {
    if(n == null) throw new NullPointerException("Can't have null child.");
    children.add(n);
  }

  /**
   *  Return the child of this ASTNode given by the index.
   *  <br>PRECONDITION: index is a non-null ASTNode.
   *  <br>POSTCONDITION: this ASTNode, and its children, are unchanged.
   *  @param index the index of the child of this ASTNode to return.
   *  @throws IndexOutOfBoundsException if index is less than 0 or
   *  greater than or equal to the number of children of this ASTNode.
   */
  public ASTNode getChild(int index) {
    return children.get(index);
  }
  
  /**
   *  Return the number of children this ASTNode has.
   *  <br>PRECONDITION: none
   *  <br>POSTCONDITION: this ASTNode, and its children, are unchanged.
   *  @return the number of children this ASTNode has.
   */
  public int arity() {
    return children.size();
  }

  /**
   *  Return an Iterator over the children of this ASTNode.
   *  @return an Iterator over the children of this ASTNode.
   */
  public Iterator<ASTNode> iterator() {
    return children.iterator();
  }

  /**
   *  Evaluate the abstract syntax (sub)tree that is rooted at this ASTNode
   *  in the context of the given symbol table, and return the result.
   *  @param symtab A map from variable identifiers to values, to use as a
   *  symbol table in the evaluation.
   *  @return the <tt>double</tt> value that is the result of
   *  evaluating the abstract syntax (sub)tree rooted at this ASTNode.
   *  @throws IllegalStateException if the abstract syntax (sub)tree
   *  rooted at this ASTNode cannot be evaluated.
   *  @throws RuntimeException containing an appropriate message
   *  if a variable identifier in the AST rooted at this ASTNode
   *  is not properly initialized when evaluated.
   */
  public abstract double eval(java.util.Map<String,Double> symtab);

}
