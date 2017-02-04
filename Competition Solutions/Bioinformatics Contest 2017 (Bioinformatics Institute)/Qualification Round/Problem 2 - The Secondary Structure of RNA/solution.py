comp = {'A':'U','U':'A','C':'G','G':'C'}

def palindrome(s):
    if len(s) % 2 != 0:
        return False
    for i in range(int(len(s)/2)):
        if s[i] != comp[s[-(i+1)]]:
            return False
    return True

def solve(s):
    if len(s) == 0:
        return "perfect"
    if len(s) % 2 == 0:
        for i in range(2,len(s)+1,2):
            if palindrome(s[:i]) and solve(s[i:]) == "perfect":
                return "perfect"
        return "imperfect"
    else:
        for i in range(len(s)):
            if solve(s[:i] + s[i+1:]) == "perfect":
                return "almost perfect"
        return "imperfect"

import sys
s = sys.stdin.read().strip()
print(solve(s))
