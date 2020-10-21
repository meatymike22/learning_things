import re

with open("GRE_vocab.md", "r") as file:
    data = file.readlines()


def reg_word(f_line):
    word = re.search(r'(\|?\s+\b\w+\b\s+\|?)?', f_line)

    if word.groups()[0] is not None:
        return word.group(0).split('|')[1]
    else:
        return ""


def tuple_param_to_list(list_tup, param):
    lines = []

    for i in list_tup:
        lines.append(i[param])

    return lines


file_line = 0
pre_words = []
words = {}

for line in data:
    line_word = reg_word(line)

    if file_line < 4:
        pre_words.append((line_word, line))
    else:
        words[line_word] = line

    file_line += 1

sorted_words = sorted(words.items(), key=lambda name: name[0])

post_pre = tuple_param_to_list(pre_words, 1)
post_words = tuple_param_to_list(sorted_words, 1)

all_words = (post_pre + post_words)

text_write = ""

for i in all_words:
    text_write += i

print(text_write)

with open("GRE_vocab.md", "w") as file:
    file.write(text_write)
    file.close()