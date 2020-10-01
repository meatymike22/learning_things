class BinarySearch:
    def __init__(self, lists):
        self.lists = lists

    def __call__(self, num):
        self.bin_s(num, self.lists)

    def bin_s(self, num, lister):
        lis = sorted(lister)
        low = 0
        high = len(lis)
        mid = int((low + high)/2) if high != 1 else 0

        if lis[mid] == num:
            print("I found {a} on index: {b}!".format(a=lis[mid], b=self.lists.index(num)))
        elif mid == 0:
            print("Number not found")
        elif lis[mid] > num:
            self.bin_s(num, lis[:mid])
        elif lis[mid] < num:
            self.bin_s(num, lis[mid:])
        else:
            print("error")
