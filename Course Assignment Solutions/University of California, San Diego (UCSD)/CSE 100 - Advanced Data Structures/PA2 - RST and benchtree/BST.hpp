/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA2 (No Partner)
 * File Name:     BST.hpp
 * Description:   This is the overarching Binary Search Tree data structure. It
 *                makes use of another class (BSTNode) to create a BST by using
 *                the insert algorithm we learned in class (compare data all
 *                the way down until you find the new node's spot, and insert
 *                there).
 *****************************************************************************/

#ifndef BST_HPP
#define BST_HPP
#include "BSTNode.hpp"
#include "BSTIterator.hpp"
#include <iostream>

template<typename Data>
class BST {

protected:

  /** Pointer to the root of this BST, or 0 if the BST is empty */
  BSTNode<Data>* root;

  /** Number of Data items stored in this BST. */
  unsigned int isize;

public:

  /** define iterator as an aliased typename for BSTIterator<Data>. */
  typedef BSTIterator<Data> iterator;

  /** Default constructor.
      Initialize an empty BST.
   */
  BST() : root(0), isize(0) {  }


  /** Default destructor.
      Delete every node in this BST.
   */ /** DONE */
  virtual ~BST() {
    /** If empty, do nothing. If not empty: */
    if(isize != 0)
    {
      deleteAll(root);
      isize = 0;
    }
  }

  /** Given a reference to a Data item, insert a copy of it in this BST.
   *  Return  true if the item was added to this BST
   *  as a result of this call to insert,
   *  false if an item equal to this one was already in this BST.
   *  Note: This function should use only the '<' operator when comparing
   *  Data items.
   */ /** DONE */
  virtual bool insert(const Data& item) {
    /** If root == null, no tree, so make new node for root */
    if(root == NULL)
    {
      root = new BSTNode<Data>(item);
      isize++;
      return true;
    }

    BSTNode<Data>* curr = root;

    insertcomp:
    /** If item is less than current data, item goes in left subtree */
    if(item < curr->data)
    {
      /** If there is no left subtree, make one with item */
      if(curr->left == NULL)
      {
        curr->left = new BSTNode<Data>(item);
        curr->left->parent = curr;
        isize++;
        return true;
      }

      /** If there is a left subtree, traverse into it */
      else
      {
        curr = curr->left;
        goto insertcomp;
      }
    }

    /** If current data is less than item, item goes in right subtree */
    else if(curr->data < item)
    {
      /** If there is no right subtree, make one with item */
      if(curr->right == NULL)
      {
        curr->right = new BSTNode<Data>(item);
        curr->right->parent = curr;
        isize++;
        return true;
      }

      /** If there is a right subtree, traverse into it */
      else
      {
        curr = curr->right;
        goto insertcomp;
      }
    }

    /** Only other case: repeat item, so don't add */
    else
    {
      return false;
    }
  }


  /** Find a Data item in the BST.
   *  Return an iterator pointing to the item, or pointing past
   *  the last node in the BST if not found.
   *  Note: This function should use only the '<' operator when comparing
   *  Data items.
   */ /** DONE */
  iterator find(const Data& item) const {
    /** If root is null, no tree, so return iterator pointing to null */
    if(root == NULL)
    {
      return typename BST<Data>::iterator(0);
    }

    BSTNode<Data>* curr = root;

    findcomp:
    /** If item is smaller than curr's data, item is in left subtree */
    if(item < curr->data)
    {
      /** If there is no left subtree, it doesn't exist */
      if(curr->left == NULL)
      {
        return typename BST<Data>::iterator(0);
      }

      /** If there is a left subtree, traverse into it */
      else
      {
        curr = curr->left;
        goto findcomp;
      }
    }

    /** If item is larger than curr's data, item is in right subtree */
    else if(curr->data < item)
    {
      /** If there is no right subtree, it doesn't exist */
      if(curr->right == NULL)
      {
        return typename BST<Data>::iterator(0);
      }

      /** If there is a right subtree, traverse into it */
      else
      {
        curr = curr->right;
        goto findcomp;
      }
    }

    /** Only other case is if it's a match */
    else
    {
      return typename BST<Data>::iterator(curr);
    }
  }

  
  /** Return the number of items currently in the BST.
   */ /** DONE */
  unsigned int size() const {
    return isize;
  }

  /** Return true if the BST is empty, else false.
   */ /** DONE */
  bool empty() const {
    return isize == 0;
  }

  /** Return an iterator pointing to the first item in the BST.
   */ /** DONE */
  iterator begin() const {
    return typename BST<Data>::iterator( first(root) );
  }

  /** Return an iterator pointing past the last item in the BST.
   */
  iterator end() const {
    return typename BST<Data>::iterator(0);
  }

  /** Perform an inorder traversal of this BST.
   */
  void inorder() const {
    inorder(root);
  }


private:

  /** Recursive inorder traversal 'helper' function */
  void inorder(BSTNode<Data>* n) const {
    if(n==0) return;
    inorder(n->left);
    std::cout << *n << std::endl;
    inorder(n->right);
  }

  static BSTNode<Data>* first(BSTNode<Data>* root) {
    if(root == 0) return 0;
    while(root->left != 0) root = root->left;
    return root;
  }

  /** Do a postorder traversal, deleting nodes */
  static void deleteAll(BSTNode<Data>* n) {
    if (0 == n) return;
    deleteAll(n->left);
    deleteAll(n->right);
    delete n;
  }
 };


#endif //BST_HPP
