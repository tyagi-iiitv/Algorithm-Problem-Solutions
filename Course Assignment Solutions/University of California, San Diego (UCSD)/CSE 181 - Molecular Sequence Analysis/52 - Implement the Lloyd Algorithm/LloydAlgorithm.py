import sys
import math

def lloydAlgorithm( k, data ):
    centers = data[0:k]
    # my code iterates 1000 times. ideally, I should use the "improved" method to repeat until it stops improving
    for repeat in xrange(1000):
        clusters = centersToClusters(data,centers)
        centers = clustersToCenters(clusters)
    return centers

def centersToClusters( data, centers ):
    clusters = [[] for i in xrange(len(centers))]
    for dataPoint in data:
        bestCenter = 0
        bestDist = d(dataPoint,centers[0])
        for i in xrange(1,len(centers)):
            dist = d(dataPoint,centers[i])
            if dist < bestDist:
                bestDist = dist
                bestCenter = i
        clusters[bestCenter].append(dataPoint)
    return clusters

def d( dataPoint, center ): # euclidean distance
    return math.sqrt(sum([(dataPoint[i]-center[i])**2 for i in xrange(len(dataPoint))]))

def clustersToCenters( clusters ):
    return [average(cluster) for cluster in clusters]

def average( cluster ):
    avg = [0.0 for val in cluster[0]]
    for point in cluster:
        for i in xrange(len(point)):
            avg[i] += point[i]
    return [val/len(cluster) for val in avg]

if __name__ == '__main__':
    with open(sys.argv[1]) as input_data:
        k,m = [int(i) for i in input_data.readline().split(' ')]
        data = [[float(i) for i in line.split(' ')] for line in input_data.readlines()]
    centers = lloydAlgorithm(k,data)
    for center in centers:
        for val in center:
            print "%.3f" % val,
        print