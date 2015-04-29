def reverseComplement( s ):
    return ''.join([complement(s[i]) for i in xrange(len(s)-1,-1,-1)])

def complement( s ):
    return {'A':'T','T':'A','C':'G','G':'C'}[s]

seq = 'GATTACA'
print reverseComplement(seq)