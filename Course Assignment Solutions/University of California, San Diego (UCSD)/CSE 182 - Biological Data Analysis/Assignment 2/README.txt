|-------------------------------|
|Alexander Niema Moshiri        |
|21 October 2014                |
|CSE 182 Assignment 2 Submission|
|-------------------------------|

|------------------------------------------------------------------------------|
|************PLEASE INSTALL LATEST JAVA PRIOR TO RUNNING THE PROGRAM***********|
|------------------------------------------------------------------------------|
|                                LIST OF FILES:                                |
|align_human_mouse.txt ---> The result of the query in #1d of the instructions |
|locAL.java --------------> The local alignment program                        |
|README.txt --------------> This information file                              |
|------------------------------------------------------------------------------|
|************************************USAGE*************************************|
|------------------------------------------------------------------------------|
| COMPILE: javac locAL.java                                                    |
| USAGE:   java locAL <file1> <file2> -m <match> -s <mismatch> -go <gap-open> -ge <gap-extend>
|           -file1: The query sequence (in FASTA format, a single sequence)    |
|           -file2: The subject sequence (in FASTA format, a single sequence)  |
|           -match: The score associated with a "match" (e.g. A-A)             |
|           -mismatch: The score associated with a "mismatch" (e.g. A-G)       |
|           -gap-open: The score associated with opening a gap                 |
|           -gap-extend: The score associated with extending a gap             |
|------------------------------------------------------------------------------|

1a) Done. The method that calculates local alignment score (and doesn't actually
    construct the alignment itself) uses the linear-space algorithm. S(s,t)
    The program is "locAL.java"

1b) Done. The S(s,t) method was updated to use affine gap penalties instead of
    only linear gap penalties. To test linear gap penalties, you can set -go to
    0 (and set -ge to what you want your linear gap penalty to be).

1c) Done. As per Professor Bafna's permission, the alignment is constructed
    using a global alignment of "trimmed" versions of the original sequences.
    The "trimmed" versions were created using information from the original
    linear-space algorithm.

1d) java locAL human.txt mouse.txt -m 1 -s -3 -go -2 -ge -2
    The output was saved as "align_human_mouse.txt"
    
2)  I used Java to do this assignment. It took me approximately 15 hours to do
    the assignment from start to finish. I did the entire assignment on my own,
    but I spoke with Alice Berengut to check the validity of my alignment. No
    code was exchanged: just output constructed alignments.