/**
 * Alexander Niema Moshiri
 * A09850737
 * a1moshir@ucsd.edu
 * PA2 (No Partner)
 */

#include <iostream> 
#include "RST.hpp"
#include "countint.hpp"
#include <cmath>
#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
using namespace std;

int main(int argc, char* argv[]) {
  /** Initialize Variables */
  int  maxSize   = -1;    /** initialize max size to -1 */
  int  numRuns   = -1;    /** initialize number of runs to -1 */
  int  structure = -1;    /** 1 = bst, 2 = rst, 3 = set */
  bool shuffle   = false; /** initialize shuffle bool */

  /******************** BEGIN ARGUMENT PARSE ********************/
  /** Ensure 4 arguments */
  if(argc != 5)
  {
    cout << "Invalid usage: must have 4 arguments." << endl;
    exit(0);
  }
  else
  {
    cout << "# Benchmarking average number of comparisons for successful find";
    cout << endl;
  }

  /** Create new strings for the arguments, for convenience */
  string arg1 = argv[1];
  string arg2 = argv[2];
  string arg3 = argv[3];
  string arg4 = argv[4];

  /** Parse 1st argument as "bst", "rst", or "set" */
  if(arg1 == "bst")
  {
    structure = 1;
  }
  else if(arg1 == "rst")
  {
    structure = 2;
  }
  else if(arg1 == "set")
  {
    structure = 3;
  }
  else
  {
    cout << "Invalid Argument 1. Options: bst, rst, set" << endl;
    exit(0);
  }
  cout << "# Data structure: " << arg1 << endl;

  /** Parse 2nd argument as "sorted" or "shuffled" */
  if(arg2 == "sorted")
  {
    shuffle = false;
  }
  else if(arg2 == "shuffled")
  {
    shuffle = true;
  }
  else
  {
    cout << "Invalid Argument 2. Options: sorted, shuffled" << endl;
    exit(0);
  }
  cout << "# Data: " << arg2 << endl;

  /** Parse 3rd argument as the max size of the data structure */
  for(unsigned i = 0; i < arg3.size(); i++)
  {
    if(!isdigit(arg3[i]))
    {
      cout << "Invalid Argument 3. Max size must be an integer." << endl;
      exit(0);
    }
  }
  maxSize = atoi(argv[3]); /** If it doesn't fail, valid capacity entered */
  cout << "# N is powers of 2, minus 1, from 1 to " << arg3 << endl;

  /** Parse 4th argument as the number of runs */
  for(unsigned i = 0; i < arg4.size(); i++)
  {
    if(!isdigit(arg4[i]))
    {
      cout << "Invalid Argument 4. Number of Runs must be an integer." << endl;
      exit(0);
    }
  }
  numRuns = atoi(argv[4]); /** If it doesn't fail, valid num runs entered */
  cout << "# Averaging over " << arg4 << " runs for each N " << endl;
  /******************** END ARGUMENT PARSE ********************/

  cout << "#" << endl;
  cout.width(arg3.size()); cout << left << "# N\t";
  cout.width(8); cout << right << "avgcomps\t";
  cout.width(8); cout << right << "stdev" << endl;

  /** Outer Loop, Each N */
  for(int N = 1; N <= maxSize; N = 2*N + 1) /** Used mathematical pattern */
  {
    /** Initialize sum and sumSquare Variables */
    double sum = 0;
    double sumSquare = 0;

    /** Inner Loop, Each Trial */
    for(int run = 0; run < numRuns; run++)
    {
      /** Initialize the data structures */
      std::set<countint> set; /** initialize set */
      BST<countint> bst = BST<countint>(); /** initialize BST */
      RST<countint> rst = RST<countint>(); /** initialize RST */

      /** Create vector of N distinct key */
      std::vector<countint> v;
      v.clear();
      for(int i = 0; i < N; i++)
      {
        v.push_back(i);
      }

      /** If in Shuffle Mode, shuffle the elements randomly */
      if(shuffle)
      {
        std::random_shuffle(v.begin(),v.end());
      }

      /** Add elements into empty structure */
      std::vector<countint>::iterator vit = v.begin();
      std::vector<countint>::iterator ven = v.end();
      for(vit = v.begin(); vit != ven; ++vit)
      {
        /** If Structure is BST, add into BST */
        if(structure == 1)
        {
          bst.insert(*vit);
        }

        /** If Structure is RST, add into RST */
        else if(structure == 2)
        {
          rst.insert(*vit);
        }

        /** If Structure is Set, add into Set */
        else if(structure == 3)
        {
          set.insert(*vit);
        }

        /** If none of those, something's wrong */
        else
        {
          cout << "Something went wrong: Data Structure is not set." << endl;
          exit(0);
        }
      }

      /** Find how many comparisons (on average) to find a key */
      countint::clearcount();
      for(vit = v.begin(); vit != ven; ++vit)
      {
        /** If Structure is BST, find from BST */
        if(structure == 1)
        {
          bst.find(*vit);
        }

        /** If Structure is RST, find from RST */
        else if(structure == 2)
        {
          rst.find(*vit);
        }

        /** If Structure is Set, find from Set */
        else if(structure == 3)
        {
          set.find(*vit);
        }

        /** If none of those, something's wrong */
        else
        {
          cout << "Something went wrong: Data Structure is not set." << endl;
          exit(0);
        }
      }
      double avgcomps = countint::getcount()/(double)N;
      sum += avgcomps;
      sumSquare += avgcomps*avgcomps;
/*cout << "AvgComps: " << avgcomps << "; Sum: " << sum << "; SumSquare: " << sumSquare << endl;*/
    }

    /** We now have sum and sumSquare of avgtime for all runs for this N */
    double tHat  = sum / (double)numRuns;
    double sHat  = sumSquare / (double)numRuns;
    double stdev;
    if(abs(sHat - (tHat*tHat)) < 1e-6) // If standard deviation is very tiny,
    {
      stdev = 0;                       // set it to 0
    }
    else
    {
      stdev = (double)sqrt(sHat - (tHat*tHat));
    }

    /** Now print the results! */
    cout.width(arg3.size()); cout << N << "\t";
    cout.width(8); cout << right << tHat << "\t";
    cout.width(8); cout << right << stdev << endl;
  }
}
