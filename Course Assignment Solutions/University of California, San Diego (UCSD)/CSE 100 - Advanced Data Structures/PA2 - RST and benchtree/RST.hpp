/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA2 (No Partner)
 * File Name:     RST.hpp
 * Description:   This is the overarching Random Search Tree class. It uses the
 *                BSTNode class to create a Random Search Tree out of nodes
 *                containing data and priorities. The Random Search Tree is a
 *                Treap, so its insert function must maintain BST properties
 *                with respect to the nodes' data values as well as maintain
 *                Heap properties with respect to the nodes' priorities. To do
 *                so, the insert() function is implemented as it was discussed
 *                in class, using a BST insert algorithm for the base add, and
 *                then fixing Heap properties via AVL rotations.
 *****************************************************************************/

#ifndef RST_HPP
#define RST_HPP
#include "BST.hpp"

template <typename Data>
class RST : public BST<Data> {

public:

  /** Given a reference to a Data item, insert a copy of it in this RST.
   *  Return true if the item was added to this RST as a result of this call to
   *  insert, false if an item equal to this one was already in the BST.
   *  Note: This function should only use the '<' operator when comparing Data
   *  items.
   *
   *  This function uses the RST insert algorithm learned in class, in which
   *  an element is first added strictly via a BST insert algorithm using its
   *  Data item. After the element is added, AVL rotations are used to move
   *  nodes around until Heap structure (with respect to the nodes' priorities)
   *  is restored.
   */
  virtual bool insert(const Data& item) {
    /** If root == null, no tree, so make new node for root */
    if(BST<Data>::root == NULL)
    {
      BST<Data>::root = new BSTNode<Data>(item);
      srand(rand()); /** Even more randomized */
      BST<Data>::root->priority = rand();
      BST<Data>::isize++;
      return true;
    }

    BSTNode<Data>* curr = BST<Data>::root;

    insertcomp:
    /** If item is less than current data, item goes in left subtree */
    if(item < curr->data)
    {
      /** If there is no left subtree, make one with item */
      if(curr->left == NULL)
      {
        curr->left = new BSTNode<Data>(item);
        srand(rand()); /** Even more randomized */
        curr->left->priority = rand();
        curr->left->parent = curr;
        BST<Data>::isize++;

        /** Now AVL rotate to preserve Heap property */
        curr = curr->left;
        while(curr->parent != 0 && curr->parent->priority < curr->priority)
        {
          /** If curr is left child of parent, AVL Right */
          if(curr == curr->parent->left)
          {
            avlRight(curr->parent);
          }

          /** If curr is right child of parent, AVL Left */
          else if(curr == curr->parent->right)
          {
            avlLeft(curr->parent);
          }

          /** If curr is neither right nor left child, something's wrong */
          else
          {
            exit(1);
          }
        }

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
        srand(rand()); /** Even more randomized */
        curr->right->priority = rand();
        curr->right->parent = curr;
        BST<Data>::isize++;

        /** Now AVL rotate to preserve Heap property */
        curr = curr->right;
        while(curr->parent != 0 &&
              curr->parent->priority < curr->priority)
        {
          /** If curr is left child of parent, AVL Right */
          if(curr == curr->parent->left)
          {
            avlRight(curr->parent);
          }

          /** If curr is right child of parent, AVL Left */
          else if(curr == curr->parent->right)
          {
            avlLeft(curr->parent);
          }

          /** If curr is neither right nor left child, something's wrong */
          else
          {
            exit(1);
          }
        }

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

private:

  /** Given a pointer to some a node, perform an AVL Left rotation on the node
   *  using the methodology described in class. The variable names seem to be
   *  undescriptive, but I chose them to match the diagram displayed in lecture
   *  to make it easier for me to work through. Here is the diagram:
   *
   *                                (Y)
   *                               /   \
   *                              /     \
   *                            (a)     (X)
   *                                   /   \
   *                                  /     \
   *                                (b)     (c)
   */
  void avlLeft(BSTNode<Data>* Y) {
    /** Create pointers to look like my diagram */
    BSTNode<Data>* X = Y->right;
    BSTNode<Data>* b = X->left;

    /** If Y was root, X is now root */
    if(Y == BST<Data>::root)
    {
      BST<Data>::root = X;
      X->parent = 0;
    }

    /** If Y was its parent's left child, X is now parent's left child */
    else if(Y == Y->parent->left)
    {
      Y->parent->left = X;
      X->parent = Y->parent;
    }

    /** If Y was its parent's right child, X is now parent's right child */
    else if(Y == Y->parent->right)
    {
      Y->parent->right = X;
      X->parent = Y->parent;
    }

    /** If Y isn't root nor its parent's left/right child, something wrong */
    else
    {
      exit(1);
    }

    /** Now to fix all the other nodes */
    Y->parent = X;   /** Y's parent is X */
    Y->right = b;    /** Y's right child is b */
    if(b != 0)
      b->parent = Y; /** b's parent is Y */
    X->left = Y;     /** X's left child is Y */
  }

  /** Given a pointer to some a node, perform an AVL Right rotation on the node
   *  using the methodology described in class. The variable names seem to be
   *  undescriptive, but I chose them to match the diagram displayed in lecture
   *  to make it easier for me to work through. Here is the diagram:
   *
   *                                (X)
   *                               /   \
   *                              /     \
   *                            (Y)     (c)
   *                           /   \
   *                          /     \
   *                        (a)     (b)
   */
  void avlRight(BSTNode<Data>* X) {
    /** Create pointers to look like my diagram */
    BSTNode<Data>* Y = X->left;
    BSTNode<Data>* b = Y->right;

    /** If X was root, Y is now root */
    if(X == BST<Data>::root)
    {
      BST<Data>::root = Y;
      Y->parent = 0;
    }

    /** If X was its parent's left child, Y is now parent's left child */
    else if(X == X->parent->left)
    {
      X->parent->left = Y;
      Y->parent = X->parent;
    }

    /** If X was its parent's right child, Y is now parent's right child */
    else if(X == X->parent->right)
    {
      X->parent->right = Y;
      Y->parent = X->parent;
    }

    /** If X isn't root nor its parent's left/right child, something wrong */
    else
    {
      exit(1);
    }

    /** Now to fix all the other nodes */
    X->parent = Y;   /** X's parent is Y */
    X->left = b;     /** X's left child is b */
    if(b != 0)
      b->parent = X; /** b's parentis X */
    Y->right = X;    /** Y's right child is X */
  }
};
#endif // RST_HPP
