/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA2 (No Partner)
 * File Name:     countint.cpp
 * Description:   See the description by Paul Kube below.
 *****************************************************************************/

#include "countint.hpp"

/** Implementation of the countint class
 *  See: countint.hpp
 *  @author Paul Kube (c) 2010
 */

unsigned long countint::count = 0;  // define  static count variable

void countint::clearcount() {
  count = 0;
}

unsigned long countint::getcount() {
  return count;
}
  
int countint::getval() const {
  return i;
}

bool countint::operator<(countint const & o) const {
    count++;
    return i < o.i;
}

bool countint::operator<=(countint const & o) const {
    count++;
    return i <= o.i;
}

bool countint::operator==(countint const & o) const {
    count++;
    return i == o.i;
}

bool countint::operator>(countint const & o) const {
    count++;
    return i > o.i;
}

bool countint::operator>=(countint const & o) const {
    count++;
    return i >= o.i;
}

bool countint::operator!=(countint const & o) const {
    count++;
    return i != o.i;
}

std::ostream& operator<<(std::ostream& stm, const countint& i) {
    stm << i.getval();
    return stm;
}

