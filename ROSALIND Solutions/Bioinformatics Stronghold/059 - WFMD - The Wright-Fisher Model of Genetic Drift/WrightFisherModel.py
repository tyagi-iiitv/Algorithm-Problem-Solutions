from operator import mul
from fractions import Fraction

def WrightFisherModel( N, m, g, k ):
    pRec = 1.0 - (m/(2.0*N))
    p = [comb(2*N,i)*((pRec)**i)*(1.0-pRec)**(2*N-i) for i in xrange(1,2*N+1)]
    for gen in xrange(2,g+1):
        tempP = []
        for j in xrange(1,2*N+1):
            tempTerm = [comb(2*N, j)*((x/(2.0*N))**j)*(1.0-(x/(2.0*N)))**(2*N-j) for x in xrange(1,2*N+1)]
            tempP.append(sum([tempTerm[i]*p[i] for i in xrange(len(tempTerm))]))
        p = tempP
    return sum(p[k-1:])

def comb( n, k ):
    return int(reduce(mul,(Fraction(n-i,i+1) for i in xrange(k)),1))

N = 6
m = 10
g = 6
k = 7
print WrightFisherModel(N,m,g,k)