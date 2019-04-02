package uk.ac.ucl.bag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class MapBag<T extends Comparable> extends AbstractBag<T>
{
    private int maxSize;
    private HashMap<T ,Integer> hmap;

    public MapBag() throws BagException
    {
        this(MAX_SIZE);
    }

    public MapBag(int maxSize) throws BagException
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
        this.hmap = new HashMap<>();
    }


    @Override
    public void add(T value) throws BagException {
        if (hmap.size() < MAX_SIZE) {
            if (hmap.containsKey(value)){
                int x = hmap.get(value);
                hmap.put(value,x+1);
            }
            else {
                hmap.put(value,1);
            }
        }
        else   {
            throw new BagException("Bag is Full");
        }
    }

    @Override
    public void addWithOccurrences(T value, int occurrences) throws BagException {
        if(hmap.size()<MAX_SIZE){
            if(hmap.containsKey(value)){
                int x = hmap.get(value);
                hmap.put(value,occurrences + x);
            }
            else {
                hmap.put(value,occurrences);
            }
        }
        else{
            throw new BagException("Bag is Full");
        }
    }

    @Override
    public boolean contains(T value) {
        if (hmap.containsKey(value)){
            return true;
        }return false;
    }

    @Override
    public int countOf(T value) {
        int size = hmap.get(value);
        return size;
    }

    @Override
    public void remove(T value) {
        if (hmap.containsKey(value)){
            int x = hmap.get(value);
            hmap.put(value,x-1);
        }return;
    }

    @Override
    public int size() {
        return hmap.size();
    }

    @Override
    public boolean isEmpty() {
        return hmap.isEmpty();
    }


    private class MapBagUniqueIterator implements Iterator<T>
    {
        private ArrayList<T> keys = new ArrayList<T>(hmap.keySet());
        private int index = 0;

        public boolean hasNext()
        {
            if (index < hmap.size()) return true;
            return false;
        }

        public T next()
        {
            return keys.get(index++);
        }
    }

    private class MapBagIterator implements Iterator<T>
    {
        private int index = 0;
        private int count = 0;
        T cKey;
        int cOccurrences;
        private ArrayList<T> keys = new ArrayList<T>(hmap.keySet());

        public boolean hasNext()
        {
            if (index < hmap.size()) {
                cKey = keys.get(index);
                if (count < hmap.get(cKey)) return true;
                if ((count == hmap.get(cKey)) && ((index + 1) < hmap.size())) return true;
            }
            return false;
        }

        public T next()
        {
            cKey = keys.get(index);
            cOccurrences = hmap.get(cKey);
            if(cOccurrences>count){
                ++count;
                return cKey;
            }
            count = 1;
            cKey = keys.get(index);
            index++;
            return cKey;
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
