import sys
lines = [line for line in sys.stdin.read().splitlines()]
chems = set([int(i) for i in lines[0].split(' ')])
unfinished = set()
chem_rxns = dict()
reqs = dict()
ready = list()
for i in range(1,len(lines)):
    line = lines[i]
    parts = line.split('->')
    rxn = (tuple([int(i) for i in parts[0].split('+')]), tuple([int(i) for i in parts[1].split('+')]))
    unfinished.add(rxn)
    reqs[rxn] = set(rxn[0])
    for chem in rxn[0]:
        if chem in chems:
            reqs[rxn].remove(chem)
        else:
            if chem not in chem_rxns:
                chem_rxns[chem] = set()
            chem_rxns[chem].add(rxn)
    if len(reqs[rxn]) == 0:
        ready.append(rxn)
while len(ready) != 0:
    rxn = ready.pop()
    unfinished.remove(rxn)
    for c in rxn[1]:
        chems.add(c)
        if c in chem_rxns:
            for rxn2 in chem_rxns[c]:
                if c in reqs[rxn2]:
                    reqs[rxn2].remove(c)
                    if len(reqs[rxn2]) == 0 and rxn2 in unfinished:
                        ready.append(rxn2)
print(' '.join([str(i) for i in chems]))
