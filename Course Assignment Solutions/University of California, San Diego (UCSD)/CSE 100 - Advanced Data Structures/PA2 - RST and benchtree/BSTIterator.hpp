/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA2 (No Partner)
 * File Name:     BSTIterator.hpp
 * Description:   This is the BSTIterator class. It is a simple way of cycling
 *                through the BST's contents. For our purposes, this is used
 *                by the find() function, which returns a BSTIterator starting
 *                at the found object. It does an in-order traversal by having
 *                each node call on its successor node as the next node.
 *****************************************************************************/

#ifndef BSTITERATOR_HPP
#define BSTITERATOR_HPP
#include "BSTNode.hpp"
#include <list>
#include <iterator>

template<typename Data>
class BSTIterator : public std::iterator<std::input_iterator_tag,Data> {

private:

  BSTNode<Data>* curr;

public:

  /** Constructor.  Use the argument to initialize the current BSTNode
   *  in this BSTIterator.
   */ /** DONE */
  BSTIterator(BSTNode<Data>* curr) {
    /** Assign global var curr to local var curr */
    this->curr = curr; 
  }

  /** Dereference operator. */
  Data operator*() const {
    return curr->data;
  }
  
  /** Pre-increment operator. */
  BSTIterator<Data>& operator++() {

    curr = curr->successor();
    return *this;
  }

  /** Post-increment operator. */
  BSTIterator<Data> operator++(int) {
    BSTIterator before = BSTIterator(curr);
    ++(*this);
    return before;
  }

  /** Equality test operator. */ /** DONE */
  bool operator==(BSTIterator<Data> const & other) const {
    return this->curr == other.curr;
  }

  /** Inequality test operator. */ /** DONE */
  bool operator!=(BSTIterator<Data> const & other) const {
    return this->curr != other.curr;
  }

};

#endif //BSTITERATOR_HPP
