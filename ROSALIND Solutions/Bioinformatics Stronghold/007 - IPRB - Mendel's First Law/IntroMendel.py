#define k,m,n
k,m,n = 0,0,0
tot = float(k+m+n)
print (1 - (n/tot)*((n-1)/(tot-1)) - (n/tot)*(m/(tot-1)) - (m/tot)*((m-1)/(tot-1))*0.25)