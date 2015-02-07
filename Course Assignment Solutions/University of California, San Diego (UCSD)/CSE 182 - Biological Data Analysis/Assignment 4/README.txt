|-------------------------------|
|Alexander Niema Moshiri        |
|2 November 2014                |
|CSE 182 Assignment 4 Submission|
|-------------------------------|

|------------------------------------------------------------------------------|
|************PLEASE INSTALL LATEST JAVA PRIOR TO RUNNING THE PROGRAM***********|
|------------------------------------------------------------------------------|
|                                LIST OF FILES:                                |
|Homologs.java -----> Solution to #2b (see below for usage)                    |
|Num4B.java --------> Solution to #4b, just pass in Swissprot FASTA            |
|out_2b.txt --------> Output for #2b                                           |
|out_2c.xlsx -------> Output for #2c (randomly generated sequence scores data) |
|out_2c_graph.pdf --> Column Graph generated from out_2c.xlsx                  |
|out_2d.txt --------> Output for #2d                                           |
|README.txt --------> This information file                                    |
|------------------------------------------------------------------------------|
|************************************USAGE*************************************|
|------------------------------------------------------------------------------|
|                               --- Homologs ---                               |
| COMPILE: javac Homologs.java                                                 |
| USAGE:   USAGE: java Homologs <database_file> <family_seqs_file>             |
|------------------------------------------------------------------------------|
|                                --- Num4B ---                                 |
| COMPILE: javac Num4B.java                                                    |
| USAGE:   java Num4B <swissprot_fasta>                                        |
|------------------------------------------------------------------------------|

1)  NOTE: Straight lines (|, /, \, and -) are normal transition links,
          and * are failure links
             (root)
              /  \
            (C)   \
             |     \
            (A)****(A)
             |      |
            (M)****(M)
             |      | \
            (P)****(P) \
             |      |  (I)
            (E)****(E)  |
             |      |  (N)
            (R)    (R)  |
                    |  (O)
                   (E)

2a) homology( database D, family F, scoring matrix M ):
      // Build frequency matrix "freqs"
      freqs = frequency matrix of amino acid k at any position i
      for each sequence curr in F:
        for pos from 1 to |curr|:
          freqs[curr(pos)][pos]++
      for i from 1 to |amino acids|:
        for j from 1 to |F alignment|:
          freqs(i,j) = freqs(i,j) / |F|

      // Build scoring profile matrix S(i,j)
      S = array(|F alignment|,|D|)
      for i from 1 to |F alignment|:
        for j from 1 to |D|:
          S(i,j) = 0
          for every amino acid k:
            S(i,j) = S(i,j) + ( f(k,i) * M(k,d_j) )

      // Compute scores of random sequences, Find "Homolog Threshold"
      n = 50000 // can change this (larger n = better distribution)
      average = 0
      standard_deviation = 0
      repeat n times:
        temp = random_score() (see Homologs.java for how I compute the random score)
        average = average + temp
        standard_deviation = standard_deviation + (temp*temp)
      average = average / n
      standard_deviation = (standard_deviation / n) - (average * average)
      z = 1.645 // 1.645 is the z-score for p = 0.05. Adjust as needed
      threshold = (1.645 * standard deviation) + average

      // Find Homologs
      homs = {}
      for start from 1 to (|D| - "length") (where "length" = # columns in family):
        potential = D's substring from index 'start' to index 'start + "length"'
        score = 0
        for i from 0 to "length":
          score = score + S(i,i+start)
        if score > threshold:
          homs.add(potential)

      Return homs as the answer

    The beginning portion of this algorithm is appropriate because it follows
    the methodology described in class to compute a frequency matrix from a
    given family F, and from the frequency matrix, a Scoring Profile against
    some given database D. Both matrices seem to have been implemented correctly
    based on my results. Once the Scoring Profile is constructed, the issue of
    "what constitutes a 'significant' homolog" comes about. For my algorithm,
    I simply randomly generate 'n' random sequences (in my code, n = 50000),
    compute the score of each of these random sequences, find the average, and
    then find the standard deviation. Using this data, I compute a "threshold"
    score, where p = 0.05 (aka the 95th percentile). In a lookup table, the Z
    value for this is z = 1.645, so threshold = 1.645 * stdev + mean. With this
    threshold, I check all possible homolog scores. Any potential homolog with a
    score larger than my threshold, I deem a valid homolog. See 2c for further
    explanation of why I believe this to be an appropriate threshold.

2b) The program is "Homologs.java". Since the Scoring Matrix file we were given
    simply returns 2 for a match and -1 for a mismatch, I simply wrote it in as
    a simple function that takes 2 arguments and returns 2 if they are equal and
    -1 otherwise. A more thorough scoring matrix can be provided by editing the
    M(a1,a2) method of the program.

    COMPILE: javac Homologs.java
    USAGE:   java Homologs <database_file> <family_seqs_file>

    See "out_2b.txt" for the homologs when using db.txt for database D and
    F1.txt for family F. There are 64 homologs.

2c) For my program, I used a p-value of 0.05. My thought process was that, in
    Biology, I typically see p = 0.05 deemed the "significant" p-value: all
    p-values less than or equal to 0.05 are considered "significant results,"
    while all p-values larger than 0.05 are considered "insignificant results."
    The term "significant" is used in relation to some null hypothesis: the
    expected outcome assuming nothing special is happening.

    For my program, I assume the null hypothesis is that each homolog I test
    scores no better than a purely randomly generated sequence. Thus, if a given
    potential homolog receives a score statistically significant with respect to
    the scores of randomly generated sequences, the potential homolog passes my
    test. In my program, I randomly generate 50,000 sequences and score each of
    them (the scores are compiled in the file "out_2c.xlsx", an Excel document).
    Using the data I received, I used Excel to create a Column graph to be able
    to visualize the distribution (the graph is both in the spreadsheet as well
    as on its own in the file "out_2c_graph.pdf").

    In my program, I use a z-score of 1.645, which would be the z-score
    corresponding to a p-value of 0.05 for a Normal Distribution. However, by
    looking at the graph, one can see that it doesn't fit a normal distribution.
    Instead, the data seems left-shifted. After analyzing the graph, it seems as
    though using this z-score is still fine. My reasoning is this: a z-score of
    1.645 corresponds to a specific score that serves as the 95 percentile score
    in a normal distribution. If the actual distribution is left-shifted, this
    means that the curve is actually lower on the right end (where this 95% score
    is located) than a standard curve would be, meaning if I use the score that
    correlates with a 95% score in a normal distribution, I would actually get a
    p-value of LESS THAN 0.05, which would actually be MORE SIGNIFICANT. In other
    words, by assuming my random scores fit a normal distribution, I am actually
    creating more severe restrictions for "significant" scores.

    Thus, I am sticking with my p-value methodology: By using the equation
    threshold = 1.645 * stdev + mean, I am actually creating a threshold that
    results in a p-value LESS THAN 0.05, and thus statistically significant. In
    other words, the resulting homologs resulting from my program are significant
    with p < 0.05.

2d) See "out_2d.txt" for the homologs when using db.txt for database D and
    F2.txt for family F. There are 37 homologs, so less homologs were found
    for F2 than for F1. Since there is a larger number of sequences in F2 than
    in F1, the amino acid frequencies in each column of the family are more
    refined and specific, resulting in a protein sequence motif that is more
    strict in F2 than that in F1. Because of this, even though the database D is
    the same in both trials, a smaller number of |F|-mers (where F is the length
    of the family, NOT the number of sequences in the family) are able to fit
    the motif dictated by the family.

3)  non-red(T):
      for i from 1 to |F|:
        TemplateSeq = F(i)
        for j from i+1 to |F|:
          QuerySeq = F(j)
          Identity = 0
          Length = min(|TemplateSeq|,|QuerySeq|)
          for k from 0 to Length:
            if TemplatSeq[k] == QuerySeq[k]:
              Identity = Identity + 1
          Identity = (Identity * 100) / Length
          if Identity > T:
            remove QuerySeq from F
            don't increment j (start at this same position next iteration)

    Basically, my algorithm brute forces the removal operation by checking
    sequence1 with sequence2, then 1 with 3, etc., then 2 with 3, 2 with 4,
    until there are no more sequences to remove. The approach is "perfect"
    in the sense that it absolutely forces itself to be correct (it is a
    brute force algorithm). However, its shortcoming is that it is VERY
    inefficient, so for a large number of sequences and/or long sequences,
    the algorithm would take a long time to run.

4a) Assuming the Swissprot database is 78M bp long, there are approximately 78M
    3mers (77,999,998 3mers to be exact). Assuming each position of the database
    has 20 possible amino acids, the probability of some random 3mer fitting our
    restriction is (6/20) * (3/20) * (7/20) = 63/4000. Thus, if 63/4000 of the
    77,999,998 possible 3mers will fit our RegEx, (63/4000)*78M = 1,228,500
    instances of our RegEx.

4b) After analyzing Swissprot, I was able to compute the following aa freqs:
    -A: 0.0826877492088394
    -C: 0.0137434633062586
    -D: 0.0546206886983189
    -E: 0.0674778260497571
    -F: 0.0386507426516552
    -G: 0.0708446026237472
    -H: 0.0227353126243715
    -I: 0.0594963909922413
    -K: 0.0583308766267384
    -L: 0.0966311114377013
    -M: 0.0241770092858113
    -N: 0.0405914389449102
    -P: 0.0471469427427636
    -Q: 0.0393190148062713
    -R: 0.0553836252048076
    -S: 0.0658049720110162
    -T: 0.0534676612709904
    -V: 0.0687513190291945
    -W: 0.0109155263771152
    -Y: 0.0292237261074899
    Using these values, I can compute more accurate frequencies for the 3-mer:
    -P(STAGCN)  = P(S)+P(T)+P(A)+P(G)+P(C)+P(N)      = 0.3271398873657622
    -P(RKH)     = P(R)+P(K)+P(H)                     = 0.1364498144559176
    -P(LIVMAFY) = P(L)+P(I)+P(V)+P(M)+P(A)+P(F)+P(Y) = 0.3996180487129332
    P(3mer) = P([STAGCN]) * P([RKH]) * P([LIVMAFY])  = 0.01783822116374366

    Therefore, out of the 77,999,998 possible 3mers in Swissprot:
      77,999,998 * 0.01783822116374366 = 1391381 instances of our RegEx.

5)  I used Java. It took me ~8 hours to finish. I discussed the assignment with
    Alice Berengut and Sandra Hui somewhat minimally (just the ideas behind #2).