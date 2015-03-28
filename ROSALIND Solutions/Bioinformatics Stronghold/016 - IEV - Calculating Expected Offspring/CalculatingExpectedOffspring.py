input = '16298 16360 18376 16233 18250 19449'
nums = [int(i) for i in input.split(' ')]
es = [0.75*nums[3],0.5*nums[4]]
for i in xrange(3):
    es.append(nums[i])
print sum(es)*2