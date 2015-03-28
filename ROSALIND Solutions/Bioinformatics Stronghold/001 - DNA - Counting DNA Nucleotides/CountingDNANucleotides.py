dna = ''
count = [0,0,0,0]
for c in dna:
    if c == 'A':
        count[0] += 1
    elif c == 'C':
        count[1] += 1
    elif c == 'G':
        count[2] += 1
    elif c == 'T':
        count[3] += 1
    
for i in count:
    print str(i),