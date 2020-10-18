import re

with open("GRE_vocab.md", "r") as file:
    data = file.readlines()

file_line = 0
file_new_dict = {}

# go through file lines and put words into dictionary
for line in data:
    s = re.search(r'\|\s*\b\w*\b\s*\|', line)
    if s is not None and file_line > 2:
        file_new_dict[file_line] = s.group(0).split('|')[1]
    file_line += 1

file_new_dict = sorted(file_new_dict.items(), key=lambda name: name[1])

file_line = 0

for line in data:
    if file_line > 2:


file.close()