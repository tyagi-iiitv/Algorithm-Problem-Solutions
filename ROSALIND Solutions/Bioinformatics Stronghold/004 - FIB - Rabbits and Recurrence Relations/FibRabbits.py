# define n,k
n,k = 0,0

a,b = 0,1
for i in xrange(1,n):
    a,b = b,k*a+b
print b