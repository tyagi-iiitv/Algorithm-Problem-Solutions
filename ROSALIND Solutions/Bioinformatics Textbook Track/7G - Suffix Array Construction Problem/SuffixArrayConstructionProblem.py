import sys

def suffixArray( text ):
    # custom comparator
    def suffixCmp( i, j ):
        return cmp(text[i:],text[j:])
    
    suffixArr = [i for i in xrange(len(text))]
    suffixArr.sort(cmp=suffixCmp)
    return suffixArr

text = open(sys.argv[1]).next().strip()
print suffixArray(text)