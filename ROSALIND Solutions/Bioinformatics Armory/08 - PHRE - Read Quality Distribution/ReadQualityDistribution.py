from Bio import SeqIO
threshold = 18
count = 0
for record in SeqIO.parse(open("in.txt","rU"),"fastq"):
    list = record.letter_annotations["phred_quality"]
    if float(sum(list))/len(list) < threshold:
        count += 1
print count