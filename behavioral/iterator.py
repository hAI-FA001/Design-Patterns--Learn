# easily loop over values

class LinkedNode:
    def __init__(self, value=0, next=None):
        self.value = value
        self.next = next

class LinkedList:
    def __init__(self, head):
        self.head = head
        self.cur = head
    
    def __iter__(self):
        return self

    def __next__(self):
        if self.cur is not None:
            v = self.cur.value
            self.cur = self.cur.next
            return v
        raise StopIteration

if __name__ == "__main__":
    head = LinkedNode(value=1, next=LinkedNode(value=2, next=LinkedNode(value=3)))
    lst = LinkedList(head)
    for v in lst:
        print(v)