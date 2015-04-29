def frequentWords( s, k ):
    counts = {}
    for i in xrange(len(s)-k+1):
        if s[i:i+k] not in counts:
            counts[s[i:i+k]] = 0
        counts[s[i:i+k]] += 1
    m = max(counts.values())
    out = []
    for kmer in counts:
        if counts[kmer] == m:
            out.append(kmer)
    return out

s = 'ACGTTGCATGTCGCATGATGCATGAGAGCT'
k = 4
print ' '.join(frequentWords(s,k))