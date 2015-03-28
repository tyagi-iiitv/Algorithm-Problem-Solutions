import re
#define s,t
s = ''
t = ''
inds = []
for i in xrange(len(s)-len(t)+1):
    if s[i:i+len(t)] == t:
        inds.append(i+1)
for i in inds:
    print i,