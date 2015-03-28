import sys

def LCS( text ):
    for k in xrange(len(text[0]),1,-1):
        for start in xrange(len(text[0])-k+1):
            curr = text[0][start:start+k]
            found = True
            for i in xrange(1,len(text)):
                if not curr in text[i]:
                    found = False
                    break
            if found:
                return curr

text = [line.strip() for line in open(sys.argv[1])]
i = 2
while i < len(text):
    if not text[i].startswith('>') and not text[i-1].startswith('>'):
        text[i-1] += text[i]
        del text[i]
        i -= 1
    i += 1
i = 0
while i < len(text):
    del text[i]
    i += 1
print LCS(text)