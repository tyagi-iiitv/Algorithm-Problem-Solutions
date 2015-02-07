from Bio import SeqIO
threshold = 26
percentage = 0.66
count = 0
for record in SeqIO.parse(open("in.txt","rU"),"fastq"):
    list = record.letter_annotations["phred_quality"]
    success = 0
    for entry in list:
        if entry >= threshold:
            success += 1
    if float(success) / len(list) >= percentage:
        count += 1
print count