def approximatePatternMatching( p, s, d ):
    return [i for i in xrange(len(s)-len(p)+1) if hamming(p,s[i:i+len(p)]) <= d]

def hamming( s, t ):
    return sum([s[i] != t[i] for i in xrange(len(s))])

pattern = 'ATTCTGGA'
text = 'CGCCCGAATCCAGAACGCATTCCCATATTTCGGGACCACTGGCCTCCACGGTACGGACGTCAATCAAATGCCTAGCGGCTTGTGGTTTCTCCTACGCTCC'
d = 3
print ' '.join([str(i) for i in approximatePatternMatching(pattern,text,d)])