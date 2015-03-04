/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA2 (No Partner)
 * File Name:     BSTNode.hpp
 * Description:   This is the class for the BSTNode. The BSTNode is the node of
 *                the BST, so it is a simple data structure that just holds the
 *                data (and in this case, since we are making an RST, also the
 *                priority) as well as pointers to its (potential) children and
 *                its parent (NULL if the node is the root). The nodes of this
 *                type are managed by BST.hpp and RST.hpp. Note that the
 *                priority of any given node is determined randomly upon
 *                creation of the node via the constructor.
 *****************************************************************************/

#ifndef BSTNODE_HPP
#define BSTNODE_HPP
#include <stdlib.h>
#include <iostream>
#include <iomanip>

template<typename Data>
class BSTNode {

public:

  /** Constructor.  Initialize a BSTNode with the given Data item,
   *  no parent, and no children.
   */
  BSTNode(const Data & d) : data(d) {
    left = right = parent = 0;
  }

  /** declare pointers for left child, right child, and parent */
  BSTNode<Data>* left;
  BSTNode<Data>* right;
  BSTNode<Data>* parent;
  Data const data;   /** the const Data in this node. */
  int priority;      /** the priority in this node, assigned randomly */

  /** Return the successor of this BSTNode in a BST, or 0 if none.
   ** PRECONDITION: this BSTNode is a node in a BST.
   ** POSTCONDITION:  the BST is unchanged.
   ** RETURNS: the BSTNode that is the successor of this BSTNode,
   ** or 0 if there is none.
   */ /** DONE */
  BSTNode<Data>* successor() {
    BSTNode<Data>* curr;

    /** If there exists a right child, successor = leftmost child of it */
    if(right != NULL)
    {
      curr = right;
      while(curr->left != NULL)
      {
        curr = curr->left;
      }
      return curr;
    }

    /** Else, go up until curr.parent.left == curr, and return curr.parent */
    else
    {
      curr = this;
      while(curr->parent != NULL)
      {

        if(curr == curr->parent->left)
        {
          return curr->parent;
        }
        curr = curr->parent;
      }

      return NULL;
    }
  }

}; 

/** Overload operator<< to print a BSTNode's fields to an ostream. */
template <typename Data>
std::ostream & operator<<(std::ostream& stm, const BSTNode<Data> & n) {
  stm << '[';
  stm << std::setw(10) << &n;                 // address of the BSTNode
  stm << "; p:" << std::setw(10) << n.parent; // address of its parent
  stm << "; l:" << std::setw(10) << n.left;   // address of its left child
  stm << "; r:" << std::setw(10) << n.right;  // address of its right child
  stm << "; d:" << n.data;                    // its data field
  stm << ']';
  return stm;
}

#endif // BSTNODE_HPP
