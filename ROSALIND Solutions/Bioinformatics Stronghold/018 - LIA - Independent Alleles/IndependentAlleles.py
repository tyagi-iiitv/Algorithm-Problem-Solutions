from scipy.misc import comb
k,n = 7,34
t = 2**k
print round(sum([comb(t, i, 1) * 0.25**i * 0.75**(t-i) for i in range(n, t+1)]),3)