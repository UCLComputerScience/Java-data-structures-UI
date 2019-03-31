package uk.ac.ucl.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListBag<T extends Comparable> extends AbstractBag<T> {
    private static class Node<E>
    {
        public E value;
        public int occurrences;
        public Node<E> next;
        public Node(E value, int occurrences, Node<E> next)
        {
            this.value = value;
            this.occurrences = occurrences;
            this.next = next;
        }
    }

    private int maxSize;
    private Node<T> head;

    public LinkedListBag() throws BagException
    {
        this(MAX_SIZE);
    }

    public LinkedListBag(int maxSize) throws BagException
    {
        if (maxSize > MAX_SIZE)
        {
            throw new BagException("Attempting to create a Bag with size greater than maximum");
        }
        if (maxSize < 1)
        {
            throw new BagException("Attempting to create a Bag with size less than 1");
        }
        this.maxSize = maxSize;
        this.head = new Node<>(null,0,null);
    }

    @Override
    public void add(T value) throws BagException{
    if(size()<MAX_SIZE) {
        if (!contains(value)) {
            if (head == null)
            {
                head = new Node<>(value,1,null);
            }

            else
            {
                Node<T> tmpNode = head;
                while (tmpNode.next != null)
                {
                    tmpNode = head.next;
                }
                tmpNode.next = new Node<>(value,1,null);
            }
        }
        else {
            updateNode(value);
        }
    }
        else if (size()==MAX_SIZE && contains(value)) {
        updateNode(value);
    }
        else {
        throw new BagException("Bag is full");
    }
}

    public void updateNode(T value){
        Node<T> tmpNode = head;
        while(tmpNode.next != null && !tmpNode.value.equals(value)){
            tmpNode = tmpNode.next;
        }
        Node<T> nextNode = tmpNode.next;
        tmpNode = new Node<>(value,++tmpNode.occurrences,nextNode);
    }

    public void updateNodeWithOcc(T value, int x){
        Node<T> tmpNode = head;
        while(tmpNode.next != null && !tmpNode.value.equals(value)){
            tmpNode = tmpNode.next;
        }
        Node<T> nextNode = tmpNode.next;
        tmpNode = new Node<>(value,x + tmpNode.occurrences,nextNode);
    }

    @Override
    public void addWithOccurrences(T value, int occurrences) throws BagException {

        if (size()<MAX_SIZE){
            if (!contains(value)) {
                if (head == null) {
                    head = new Node<>(value, occurrences, null);
                }
                else {
                    Node<T> tmpNode = head;
                    while (tmpNode.next != null) {
                        tmpNode = head.next;
                    }
                    tmpNode.next = new Node<>(value, occurrences, null);
                }
            }
            else {
                updateNodeWithOcc(value,occurrences);
            }
        }
        else {
            throw new BagException("Bag is Full");
        }
    }

    @Override
    public boolean contains(T value) {
        Node<T> tmpNode = head;
        while(tmpNode!=null){
            if (tmpNode.value.equals(value)){
                return true;
            }
            tmpNode = tmpNode.next;
        }return false;
    }

    @Override
    public int countOf(T value) {
        Node<T> tmpNode = head;
        while (!tmpNode.value.equals(value)) {
            tmpNode = tmpNode.next;
            if (tmpNode.value.equals(value)) return tmpNode.occurrences;
        }return 0;
    }

    @Override
    public void remove(T value) {
        if(head == null){
            throw new RuntimeException("Nothing to Delete");
        }
        if(head.value.equals(value)){
            head = head.next;
            return;
        }
        Node<T> curHead = head;
        Node<T> prevHead = null;
        while(curHead!=null && !curHead.value.equals(value)){
            prevHead = curHead;
            curHead = curHead.next;
        }
        if(curHead == null){
            throw new RuntimeException("Cannot Delete");
        }prevHead.next = curHead.next;
    }

    @Override
    public int size() {
        int x =0;
        Node<T> tmpNode = head;
        while (tmpNode!=null){
            tmpNode = tmpNode.next;
            ++x;
        }return x;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }


    private class LinkedListUniqueIterator implements Iterator<T> {
        Node<T> tmpNode;
        public LinkedListUniqueIterator(){ tmpNode = head; }

        public boolean hasNext() {
            return (tmpNode != null);
        }

        public T next() {
            if (hasNext()) {
                T cValue = tmpNode.value;
                tmpNode = tmpNode.next;
                return cValue;
            }
            throw new NoSuchElementException("No more elements left in the List");
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private int count = 0;
        private int countOccur = 1;
        Node<T> tmpNode = head;
        int cOccurrences;

        public boolean hasNext() {
            int x = tmpNode.occurrences;
            if (tmpNode.next != null && x > 0){
                return true;
            }
            else if (tmpNode.next == null){
                while(x > countOccur){
                    ++countOccur;
                    return true;
                }
                return false;
            }
            return false;
        }

        public T next() {
            cOccurrences = tmpNode.occurrences;
            if (cOccurrences > count) {
                count++;
                T cValue = tmpNode.value;
                return cValue;
            }
            else {
                count = 1;
                tmpNode = tmpNode.next;
                T cValue = tmpNode.value;
                return cValue;
            }
        }
    }

    @Override
    public Iterator<T> allOccurrencesIterator() {
        return new LinkedListIterator();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListUniqueIterator();
    }


}
