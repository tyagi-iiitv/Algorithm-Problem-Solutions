# GIBBS SAMPLER

# Implement GibbsSampler

# Given:  Integers k, t, and N, followed by a collection of strings Dna.
# Return: The strings BestMotifs resulting from running GibbsSampler(Dna, k, t, N) with 20 random starts. Remember to use pseudocounts!

import random
import sys

def readInput( file ):
    parts = file.readline().split()
    k = int(parts[0])
    t = int(parts[1])
    if len(parts) >= 3:
        N = int(parts[2])
    else:
        N = 20*t
    dna = []
    for i in range(t):
        dna.append(file.readline().strip())
    return (dna,k,N)

def indexToAcid(i):
    return "ACGT"[i]

def acidToIndex(a):
    return "ACGT".index(a)

def repeatedGibbsSampler( dna, k, N, R ):
    bestMotifs = randomMotifs(dna,k)
    bestScore = Score(bestMotifs)
    for i in range(0,R):
        (motifs,score) = gibbsSampler(dna,k,N)
        if score < bestScore:
            bestMotifs = motifs
            bestScore = score
    return bestMotifs

def randomMotifs( dna, k ):
    return [randomKmer(seq,k) for seq in dna]

def randomKmer( seq, k ):
    start = random.randint(0, len(seq)-k)
    return seq[start:start+k] 

def Score( motifs ):
    score = 0
    for count in countsFromMotifs(motifs,0):
        score += sum(count) - max(count)
    return score

def countsFromMotifs( motifs, initCount ):
    k = len(motifs[0])
    for motif in motifs:
        assert k == len(motif)
    counts = []
    for i in range(k):
        currCount = [initCount] * 4
        for motif in motifs:
            currCount[acidToIndex(motif[i])] += 1
        counts.append(currCount)
    return counts

def gibbsSampler( dna, k, N ):
    t = len(dna)
    motifs = randomMotifs(dna,k)
    bestMotifs = motifs
    bestScore = Score(bestMotifs)
    for step in range(N):
        i = random.randint(0, t-1)
        profile = profileFromMotifs(motifs[:i] + motifs[i+1:], 1)
        pr = [probKmerInProfile(dna[i][s:s+k],profile) for s in range(len(dna[i])-k+1)]
        s = Random(pr)
        motifs[i] = dna[i][s:s+k]
        score = Score(motifs)
        if score < bestScore:
            bestMotifs = motifs[:]
            bestScore = score
    return (bestMotifs,bestScore)

def Random( pr ):
    total = float(sum(pr))
    r = random.random()
    partialSum = 0.0
    for i in range(len(pr)):
        partialSum += pr[i]
        if partialSum/total >= r:
            return i
    return -1

def profileFromMotifs( motifs, initCount ):
    counts = countsFromMotifs(motifs,initCount)
    profile = []
    for count in counts:
        total = float(sum(count))
        probs = [c/total for c in count]
        profile.append(probs)
    return profile

def probKmerInProfile( kmer, profile ):
    prob = 1.0
    for i in range(len(kmer)):
        prob *= profile[i][acidToIndex(kmer[i])]
    return prob

file = sys.argv[1]
(dna,k,N) = readInput(open(file))
R = 20
bestMotifs = repeatedGibbsSampler(dna,k,N,R)
for motif in bestMotifs:
    print motif