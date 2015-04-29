def minimumSkew( s ):
    skew = [0]
    for i in xrange(len(s)):
        skew.append(skew[i] + {'A':0,'C':-1,'G':1,'T':0}[s[i]])
    m = min(skew)
    return [i for i in xrange(len(skew)) if skew[i] == m]

s = 'CCTATCGGTGGATTAGCATGTCCCTGTACGTTTCGCCGCGAACTAGTTCACACGGCTTGATGGCAAATGGTTTTTCCGGCGACCGTAATCGTCCACCGAG'
print ' '.join([str(i) for i in minimumSkew(s)])