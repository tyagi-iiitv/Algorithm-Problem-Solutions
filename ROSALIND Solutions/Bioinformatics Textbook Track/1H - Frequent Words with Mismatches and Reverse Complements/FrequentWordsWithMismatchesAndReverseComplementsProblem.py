def frequentWordsWithMismatchesAndReverseComplements( s, k, d ):
    counts = {}
    for i in xrange(len(s)-k+1):
        for sub in [s[i:i+k],reverseComplement(s[i:i+k])]:
            for neighbor in neighbors(sub,d):
                if neighbor not in counts:
                    counts[neighbor] = 0
                counts[neighbor] += 1
    m = max(counts.values())
    return [kmer for kmer in counts if counts[kmer] == m]

def neighbors( s, d ):
    if d == 0:
        return [s]
    if len(s) == 1:
        return ['A','C','G','T']
    out = []
    for neighbor in neighbors(s[1:],d):
        if hamming(s[1:],neighbor) < d:
            out.extend(['A'+neighbor,'C'+neighbor,'G'+neighbor,'T'+neighbor])
        else:
            out.append(s[0] + neighbor)
    return out

def hamming( s, t ):
    return sum([s[i] != t[i] for i in xrange(len(s))])

def reverseComplement( s ):
    return ''.join([complement(s[i]) for i in xrange(len(s)-1,-1,-1)])

def complement( s ):
    return {'A':'T','T':'A','C':'G','G':'C'}[s]

s = 'ACGTTGCATGTCGCATGATGCATGAGAGCT'
k = 4
d = 1
print ' '.join(frequentWordsWithMismatchesAndReverseComplements(s,k,d))