from Bio import SeqIO
threshold = 18
records = []
for record in SeqIO.parse(open("in.txt","rU"),"fastq"):
    records.append(record.letter_annotations["phred_quality"])
qualities = [float(0) for x in range(len(records[0]))]
for record in records:
    for i in range(len(record)):
        qualities[i] += record[i]
for i in range(len(qualities)):
    qualities[i] = qualities[i] / len(records)
count = 0
for i in qualities:
    if i < threshold:
        count += 1
print count