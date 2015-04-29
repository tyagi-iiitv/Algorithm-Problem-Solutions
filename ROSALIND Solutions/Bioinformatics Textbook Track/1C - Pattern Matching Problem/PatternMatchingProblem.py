def patternMatching( pattern, text ):
    return [i for i in xrange(len(text)-len(pattern)+1) if pattern == text[i:i+len(pattern)]]

p = 'ATAT'
t = 'GATATATGCATATACTT'
print ' '.join([str(i) for i in patternMatching(p,t)])