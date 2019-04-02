package uk.ac.ucl.bag;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.*;

/**
 * This class implements methods common to all concrete bag implementations
 * but does not represent a complete bag implementation.<br />
 *
 * New bag objects are created using a BagFactory, which can be configured in the application
 * setup to select which bag implementation is to be used.
 */
import java.util.Iterator;

public abstract class AbstractBag<T extends Comparable> implements Bag<T>
{
  public Bag<T> createMergedAllOccurrences(Bag<T> b) throws BagException {
    Bag<T> result = BagFactory.getInstance().getBag();
    for (T value : this)
    {
      result.addWithOccurrences(value, this.countOf(value));
    }
    for (T value : b)
    {
      result.addWithOccurrences(value, b.countOf(value));
    }
    return result;
  }

  public Bag<T> createMergedAllUnique(Bag<T> b) throws BagException {
    Bag<T> result = BagFactory.getInstance().getBag();
    for (T value : this)
    {
      if (!result.contains(value)) result.add(value);
    }
    for (T value : b)
    {
      if (!result.contains(value)) result.add(value);
    }
    return result;
  }

  @Override
  public String toString(){
    boolean firstComma = true;
    StringBuilder builderStr = new StringBuilder();
    builderStr.append("[");
    for (T value : this){
      if(!firstComma){
        builderStr.append(" , ");
      }
      firstComma = false;
      builderStr.append(value);
      builderStr.append(" : ");
      builderStr.append(countOf(value));

    }
    builderStr.append("]");
    String outputStr = builderStr.toString();
    return outputStr;
  }


  public Bag<T> subtract(Bag<T> bag){
    for(T value: bag){
      if(bag.contains(value) && this.contains(value)){
        int x = bag.countOf(value);
        for(int i = 0;i<x;i++){
          this.remove(value);
        }
      }
    }return this;
  }

  public void removeAllCopies() {
    for(T value: this){
      int x = this.countOf(value);
      while(x>1){
        this.remove(value);
        --x;
      }
    }
  }


  public void saveBag() {
    try {
      String className = this.getClass().getName();
      String[] removeFrontList = className.split("\\.");
      String classType = removeFrontList[4];
      BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"));
      StringBuilder str = new StringBuilder();
      str.append(classType + "\n");
      for (T value : this) {
        str.append(value + " : ");
        str.append(this.countOf(value) + "\n");
      }
      String outStr = str.toString();
      String[] bufferList = outStr.split("\n");
      for (String cur : bufferList) {
        writer.write(cur);
        writer.newLine();
      }
      writer.close();
      System.out.println(className + " Saved");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }




}
