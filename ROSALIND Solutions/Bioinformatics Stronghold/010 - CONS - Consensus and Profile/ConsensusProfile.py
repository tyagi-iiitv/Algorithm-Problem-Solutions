import sys
def dnaToProfile( dna ):
    prof = [[0 for i in xrange(len(dna[0]))] for i in xrange(4)]
    for seq in dna:
        for i in xrange(len(seq)):
            if seq[i] == 'A':
                prof[0][i] += 1
            elif seq[i] == 'C':
                prof[1][i] += 1
            elif seq[i] == 'G':
                prof[2][i] += 1
            elif seq[i] == 'T':
                prof[3][i] += 1
    return prof

def profileToConsensus( profile ):
    out = ''
    for i in xrange(len(profile[0])):
        m = max([profile[j][i] for j in xrange(4)])
        if profile[0][i] == m:
            out += 'A'
        elif profile[1][i] == m:
            out += 'C'
        elif profile[2][i] == m:
            out += 'G'
        elif profile[3][i] == m:
            out += 'T'
    return out

text = [line.strip() for line in open(sys.argv[1])]
i = 2
while i < len(text):
    if not text[i].startswith('>') and not text[i-1].startswith('>'):
        text[i-1] += text[i]
        del text[i]
        i -= 1
    i += 1
profile = dnaToProfile([text[i] for i in xrange(1,len(text),2)])
print profileToConsensus(profile)
print 'A: ' + str(profile[0])[1:].replace(']','').replace(',','')
print 'C: ' + str(profile[1])[1:].replace(']','').replace(',','')
print 'G: ' + str(profile[2])[1:].replace(']','').replace(',','')
print 'T: ' + str(profile[3])[1:].replace(']','').replace(',','')