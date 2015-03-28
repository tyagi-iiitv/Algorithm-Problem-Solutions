s = ''
t = ''
count = 0
for i in xrange(len(s)):
    if s[i] != t[i]:
        count += 1
print count