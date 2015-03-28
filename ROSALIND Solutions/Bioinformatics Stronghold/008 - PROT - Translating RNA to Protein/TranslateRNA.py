import sys
#define rna
rna = ''
table = {}
for line in open(sys.argv[1]):
    key,val = line.strip().split(' ')
    table[key] = val
out = ''
for i in xrange(0,len(rna),3):
    if i+3 <= len(rna) and table[rna[i:i+3]] != 'Stop':
        out += table[rna[i:i+3]]
print out