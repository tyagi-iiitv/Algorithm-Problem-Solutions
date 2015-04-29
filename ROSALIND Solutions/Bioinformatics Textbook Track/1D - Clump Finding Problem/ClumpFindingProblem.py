def clumpFinding( s, k, L, t ):
    out = []
    for start in xrange(len(s)-L+1):
        window = s[start:start+L]
        counts = {}
        for i in xrange(len(window)-k+1):
            if window[i:i+k] not in counts:
                counts[window[i:i+k]] = 0
            counts[window[i:i+k]] += 1
        for kmer in counts:
            if counts[kmer] >= t and kmer not in out:
                out.append(kmer)
    return out

s = 'CGGACTCGACAGATGTGAAGAAATGTGAAGACTGAGTGAAGAGAAGAGGAAACACGACACGACATTGCGACATAATGTACGAATGTAATGTGCCTATGGC'
k = 5
L = 75
t = 4
print ' '.join(clumpFinding(s,k,L,t))