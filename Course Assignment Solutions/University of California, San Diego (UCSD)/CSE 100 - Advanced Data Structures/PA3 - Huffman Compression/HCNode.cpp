/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     HCNode.cpp
 * Description:   This is the class for the HCNode. The HCNode is the node of
 *                the HCTree, so it is a simple data structure that just holds
 *                the byte that's being kept track of, a pointer to its 0
 *                (left) child, and a pointer to its 1 (right) child. The nodes
 *                of this type are managed by HCTree.hpp. This is the actual
 *                class code.
 *****************************************************************************/
#include "HCNode.hpp"

/** Compare this HCNode and other for priority ordering. Smaller count means
 *  higher priority. Use node symbol for deterministic tiebreaking.
 */
bool HCNode::operator<(HCNode const & other) {
  /** if counts are different, just compare counts */
  if(count != other.count)
  {
    return count > other.count;
  }

  /** if counts are equal, use symbol to break tie (symbol must be set) */
  return symbol < other.symbol;
}
