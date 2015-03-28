dna = ''
rev = ''
for c in dna:
    if c == 'A':
        rev = 'T' + rev
    elif c == 'C':
        rev = 'G' + rev
    elif c == 'G':
        rev = 'C' + rev
    elif c == 'T':
        rev = 'A' + rev
print rev