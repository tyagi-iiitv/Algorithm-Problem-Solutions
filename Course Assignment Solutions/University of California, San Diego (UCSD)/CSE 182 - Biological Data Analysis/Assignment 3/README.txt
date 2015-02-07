|-------------------------------|
|Alexander Niema Moshiri        |
|23 October 2014                |
|CSE 182 Assignment 3 Submission|
|-------------------------------|

|------------------------------------------------------------------------------|
|************PLEASE INSTALL LATEST JAVA PRIOR TO RUNNING THE PROGRAM***********|
|------------------------------------------------------------------------------|
|                                LIST OF FILES:                                |
|alignment_data.zip -> Alignment data L(n) and scores (used to make graph)     |
|data_graphs.xlsx ---> Excel Spreadsheet of the graphs and data for the graphs |
|graphs.pdf ---------> Graphs of various info for different params and n's     |
|L(1000)_data.zip ---> Data for Part 2a                                        |
|locAL.java ---------> The local alignment program                             |
|random_out_ex.txt --> Example output from randomDNA.java program (with freqs) |
|random_seqs.zip ----> Collection of random sequences used in the alignments   |
|randomDNA.java -----> Program that generates random DNA sequences             |
|README.txt ---------> This information file                                   |
|------------------------------------------------------------------------------|
|************************************USAGE*************************************|
|------------------------------------------------------------------------------|
|                                --- locAL ---                                 |
| COMPILE: javac locAL.java                                                    |
| USAGE:   java locAL <file1> <file2> -m <match> -s <mismatch> -go <gap-open> -ge <gap-extend>
|           -file1: The query sequence (FASTA format, multiple seqs allowed)   |
|           -file2: The subject sequence (FASTA format, multiple seqs allowed) |
|           -match: The score associated with a "match" (e.g. A-A)             |
|           -mismatch: The score associated with a "mismatch" (e.g. A-G)       |
|           -gap-open: The score associated with opening a gap                 |
|           -gap-extend: The score associated with extending a gap             |
|                                                                              |
|                              --- randomDNA ---                               |
| COMPILE: javac randomDNA.java                                                |
| USAGE:   java randomDNA <number of sequences> <length of sequences>          |
|------------------------------------------------------------------------------|

1a) My existing program already did this. It outputs the score and length of the
    optimal alignment, but I removed the actual alignment output for this
    assignment. I also changed it to allow for multiple sequences per file
    (to automate the process of aligning 500 sequences). <file1> and <file2>
    MUST have the same number of sequences!!! Also note, the program outputs the
    results in a format that is comma-delimited, so it might help to forward
    STDOUT to a csv file and open in Microsoft Excel. Status notifications are
    printed to STDERR, so forwarding STDOUT to file will produce a valid CSV and
    still print status notifications.
    
    COMPILE: javac locAL.java
    RUN:     java locAL <seq file1> <seq file2> -m <match> -s <mismatch> -go <gap-open> -ge <gap-extend>
    
    Use a gap-open value of 0 for linear gap penalties.

1b) The program is called "randomDNA.java". It takes as input the desired number
    of sequences and the desired length of the sequences, and it outputs the
    randomly generated sequences in the FASTA format to STDOUT, and it then
    outputs the nucleotide frequencies to STDERR. This is so that it's easier
    to save the sequences to a FASTA format file (just forward STDOUT to file).
    
    COMPILE: javac randomDNA.java
    RUN:     java randomDNA <number of seqs> <length of seqs>

1c) The lengths of the optimal local alignments with parameters P1 are
    (significantly) different than the lengths of the optimal alignments with
    parameters P2. They are different because P1 doesn't penalize gaps or
    mismatches (two choices that would allow for an increase in alignment
    length), whereas P2 penalizes both quite strongly. Because the P1 parameters
    allow for gaps and mismatches for free, the P1 alignments basically match
    whatever they can between the two sequences and use gaps to fit the
    alignment together. The P2 parameters, on the other hand, have such high
    mismatch and indel penalties that these become impossible, so the resulting
    alignments just be the longest contiguous perfect match between the two
    sequences.
    
    According to Excel's estimations (see data_graphs.xlsx):
      L_P1(n) = 1.2311n - 5.5217
      L_P2(n) = 0.0008n + 9

    See pages 1-3 of graphs.pdf for graphs of L(n) vs n (page 1), Score(n) vs. n
    (page 2), and L(n) vs. Score(n) (page 3) for 4 different parameters.

2a) See page 4 of graphs.pdf for the plot of L(1000) values (in other words,
    L(n) for n = 1000) for multiple different values of mismatch=indel
    penalties. The data used to construct the graph can be found in the file
    L(1000)_data.zip

2b) As can be seen from the graph (on page 4 of graphs.pdf), there is a clear
    jump penalty 2 (match = indel = -2) to jump penalty 1 (match = indel = -1).
    Thus, I would predict that the abrupt change in L(1000) occurs when the
    mismatch/indel penalty = -1. In other words, the parameters for the jump
    are as follows:
                      match = 1; mismatch = -1; indel = -1

3)  The larger the mismatch/indel penalty, the more strict the algorithm on
    only allowing perfect matches. In other words, when the penalty is far too
    large, only perfect matches are allowed for the alignment. When the penalty
    is small enough, however, mismatches/gaps (which are the only things that
    can potentially "extend" an alignment) become allowed.
    
    It seems that, when the mismatch/indel penalty becomes 1 (so score = -1),
    the algorithm becomes JUST lenient enough to allow for a significant amount
    of gaps/mismatches, resulting in the huge jump of alignment length.

4)  When BLASTing 'seq1' (all filters/masks disabled), the result had 4 hits.
    When BLASTing 'seq2' (all filters/masks disabled), the result had 100 hits.
    
    Just looking at sequence length, seq2 is a good amount longer than seq1.
    Since BLAST performs a Local Alignment, the longer the sequence, the more
    opportunity for subsequences that could potentially match other sequences in
    the BLAST database, even if by chance.

5)  "Retrotransposons" are genetic elements that can amplify themselves in a
    genome by copying themselves to RNA and then back to DNA that may integrate
    back to the genome. Thus, retrotransposons are one cause of "human repeats":
    sequences (can be entire genes or just snippets) that are repeated throughout
    the genome, often in varying numbers in different individuals. The existence
    of the repeat structure of DNA has an impact on BLAST (and Local Alignment
    in general). A large issue is, if a repeat is inside of a region that WOULD
    have been aligned, the result would effectively be a large gap in what would
    otherwise be a normal alignment. For example, if CCC was inserted into
    AAAAAA (to get AAACCCAAA), and this was aligned with AAAAAA, the original
    form would have perfectly aligned, but the form with the repeat will have a
    middle gap section. Now imagine, instead of a single codon (CCC), having a
    legitimately long repeat inserted in the middle of a region that would
    otherwise have aligned.

6)  I used Java for this assignment. The whole assignment took ~4-5 hours. I did
    not discuss the homework with anyone (other than a general question on the
    CSE 182 Google Group).