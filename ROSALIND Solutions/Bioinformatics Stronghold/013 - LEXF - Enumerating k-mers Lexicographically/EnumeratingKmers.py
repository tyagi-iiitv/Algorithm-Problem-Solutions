import itertools
#define n,alphabet
n,alphabet = 0,''
out = [str(kmer)[2:].replace("', '",'').replace("')",'') for kmer in list(itertools.product(alphabet,repeat=n))]
for kmer in sorted(out, key=lambda word: [alphabet.index(c) for c in word[0]]):
    print kmer