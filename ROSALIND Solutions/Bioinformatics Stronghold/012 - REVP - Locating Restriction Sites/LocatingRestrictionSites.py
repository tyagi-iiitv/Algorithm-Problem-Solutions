def revComplement( t ):
    rev = ''
    for c in t:
        if c == 'A':
            rev = 'T' + rev
        elif c == 'C':
            rev = 'G' + rev
        elif c == 'G':
            rev = 'C' + rev
        elif c == 'T':
            rev = 'A' + rev
    return rev

def isRevPalindrome( text ):
    return text == revComplement(text)

#define dna
dna = ''
for l in xrange(4,13):
    for i in xrange(0,len(dna)-l+1):
        if isRevPalindrome(dna[i:i+l]):
            print str(i+1) + ' ' + str(l)