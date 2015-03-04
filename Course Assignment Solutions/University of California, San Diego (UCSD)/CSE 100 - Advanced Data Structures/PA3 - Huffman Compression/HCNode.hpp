/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     HCNode.hpp
 * Description:   This is the class for the HCNode. The HCNode is the node of
 *                the HCTree, so it is a simple data structure that just holds
 *                the byte that's being kept track of, a pointer to its 0
 *                (left) child, and a pointer to its 1 (right) child. The nodes
 *                of this type are managed by HCTree.hpp. This is the header
 *                file.
 *****************************************************************************/
#ifndef HCNODE_HPP
#define HCNODE_HPP

#include <iostream>

typedef unsigned char byte;

using namespace std;

/** A class, instances of which are nodes in an HCTree.
 */
class HCNode {
  friend bool comp(HCNode* one, HCNode* other);

public:
  int count;   /** count/frequency of symbols in subtree */
  byte symbol; /** byte in the file we're keeping track of */
  HCNode* c0;  /** pointer to '0' child */
  HCNode* c1;  /** pointer to '1' child */
  HCNode* p;   /** pointer to parent */
  bool is0;    /** Am I a '0' child? */

  HCNode(int count,
	 byte symbol,
	 HCNode* c0 = 0,
	 HCNode* c1 = 0,
	 HCNode* p = 0,
         bool is0 = 0)
    : count(count), symbol(symbol), c0(c0), c1(c1), p(p) { }

  /** Less-than comparison, so HCNodes will work in std::priority_queue
   *  We want small counts to have high priority.
   *  And we want to break ties deterministically.
   */
  bool operator<(const HCNode& other);
};

/** For printing an HCNode to an ostream
 *  Possibly useful for debugging.
 */
ostream& operator<<(ostream&, const HCNode&) __attribute__((weak)); // shut the linker up
ostream& operator<<(ostream& stm, const HCNode& n) {
    stm << "[" << n.count << "," << (int) (n.symbol) << "]";
    return stm;
}

bool comp(HCNode* one, HCNode* other);


#endif // HCNODE_HPP
