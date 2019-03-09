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
        head = null;
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
    }

    @Override
    public void add(T value) throws BagException {
        if(size()<MAX_SIZE) {
            if (!contains(value)) {
                if (head == null) {
                    head = new Node<T>(value, 1, null);
                } else {
                    Node<T> tmpHead = head;
                    while (tmpHead.next != null) {
                        tmpHead = tmpHead.next;
                    }
                    tmpHead.next = new Node<T>(value, 1, null);
                }
            }addWithValue(value);
        }
        else if (size()==MAX_SIZE && contains(value)) {
            addWithValue(value);
        }throw new BagException("Bag is Full");
    }

    public void addWithValue(T value){
        Node<T> tmpNode = head;
        while(tmpNode != null && !tmpNode.value.equals(value)){
            tmpNode = tmpNode.next;
        }++tmpNode.occurrences;
    }

    @Override
    public void addWithOccurrences(T value, int occurrences) throws BagException {
        Node<T> tmpNode = head;
        if (size()<MAX_SIZE){
            if (!contains(value)){
                tmpNode = new Node<T>(value,occurrences,null);
            }while(tmpNode != null && !tmpNode.value.equals(value)){
                tmpNode=tmpNode.next;
            }int x = tmpNode.occurrences;
            tmpNode.occurrences = x + occurrences;
        }throw new BagException("Bag is Full");
    }

    @Override
    public boolean contains(T value) {
        Node<T> tmpNode = head;
        while(tmpNode!=null){
            tmpNode = tmpNode.next;
            if(tmpNode.value.equals(value)){
                return true;
            }
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
            tmpNode = tmpNode.next;;
            ++x;
        }return x;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }


    private class MapBagUniqueIterator implements Iterator<T> {
        Node<T> tmpNode = head;
        private int index = 0;

        public boolean hasNext() {
            return (tmpNode.next != null);
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

    private class MapBagIterator implements Iterator<T> {
        private int index = 0;
        private int count = 0;
        Node<T> tmpNode = head;
        int cOccurrences;

        public boolean hasNext() {
            int x = tmpNode.occurrences;
            if (x > 0 && tmpNode.next == null) {
                return true;
            } else if (tmpNode.next != null) {
                return true;
            }
            return false;
        }

        public T next() {
            int x = tmpNode.occurrences;
            T cValue = tmpNode.value;
            if(hasNext()) {
                if (x > count) {
                    count++;
                    return cValue;
                } else {
                    count = 1;
                    tmpNode = tmpNode.next;
                    return cValue;
                }
            }throw new NoSuchElementException("No More Element left in List");
        }
    }

    @Override
    public Iterator<T> allOccurrencesIterator() {
        return new MapBagIterator();
    }

    @Override
    public Iterator<T> iterator() {
        return new MapBagUniqueIterator();
    }
}
