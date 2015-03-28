import sys
text = [line.strip() for line in open(sys.argv[1])]
i = 2
while i < len(text):
    if not text[i].startswith('>') and not text[i-1].startswith('>'):
        text[i-1] += text[i]
        del text[i]
        i -= 1
    i += 1
bestID = text[0]
bestGC = float((text[1].count('G') + text[1].count('C'))) / len(text[1]) * 100
for i in xrange(2,len(text),2):
    gc = float((text[i+1].count('G') + text[i+1].count('C'))) / len(text[i+1]) * 100
    if gc > bestGC:
        bestGC = gc
        bestID = text[i]
print bestID[1:] + '\n' + str(bestGC)