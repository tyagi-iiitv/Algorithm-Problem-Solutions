from Bio import SeqIO
threshold = 22
records = []
for record in SeqIO.parse(open("in.txt","rU"),"fastq"):
    records.append(record)
for record in records:
    quals = record.letter_annotations["phred_quality"]
    start = 0
    for i in xrange(len(quals)):
        if quals[i] < threshold:
            start = i+1
        else:
            break
    end = len(quals)
    for i in xrange(len(quals)-1,-1,-1):
        if quals[i] < threshold:
            end = i
        else:
            break
    record.letter_annotations.clear()
    record.seq = record.seq[start:end]
    record.letter_annotations["phred_quality"] = quals[start:end]
    print(record.format("fastq"))