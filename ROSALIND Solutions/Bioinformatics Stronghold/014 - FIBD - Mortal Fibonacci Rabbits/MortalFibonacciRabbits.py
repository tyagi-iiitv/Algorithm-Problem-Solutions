#define n,m
n,m = 93,20
arr = [0 for i in xrange(m-1)]
arr.append(1)
for gen in xrange(n-1):
    newR = sum([arr[i] for i in xrange(m-1)])
    for i in xrange(m-1):
        arr[i] = arr[i+1]
    arr[m-1] = newR
print sum(arr)