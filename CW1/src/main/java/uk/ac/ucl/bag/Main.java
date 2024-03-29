package uk.ac.ucl.bag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

/**
 * Example code illustrating the use of Bag objects.
 */
public class Main
{
  private BagFactory<String> factory = BagFactory.getInstance();

  public void print(Bag<String> bag)
  {
    boolean first = true;
    System.out.print("{");
    for (String value : bag)
    {
      if (!first) { System.out.print(" , "); }
      first = false;
      System.out.print(value);
    }
    System.out.println("}");
  }

  public void printAll(Bag<String> bag)
  {
    boolean first = true;
    System.out.print("{");
    Iterator<String> allIterator = bag.allOccurrencesIterator();
    while (allIterator.hasNext())
    {
      if (!first) { System.out.print(" , "); }
      first = false;
      System.out.print(allIterator.next());
    }
    System.out.println("}");
  }

  public void loadBag(String fileInput)  {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileInput));
      String className = reader.readLine();
      factory.setBagClass(className);
      String outputReader;
      Bag<String> testBag = factory.getBag();
      while ((outputReader = reader.readLine()) != null){
        String[] outputList = outputReader.split(" : ");
        int occur = Integer.valueOf(outputList[1]);
        testBag.addWithOccurrences(outputList[0], occur);
      }
      printAll(testBag);
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  public void go(String bagName)
  {
    factory.setBagClass(bagName);


    try
    {
      Bag<String> bag1;
      Bag<String> bag2;
      Bag<String> bag3;

      bag1 = factory.getBag();
      bag1.add("abc");
      bag1.add("def");
      bag1.add("hij");
      System.out.print("bag1 all unique:             ");
      print(bag1);
      System.out.print("bag1 all:                    ");
      printAll(bag1);

      bag2 = factory.getBag();
      bag2.add("def");
      bag2.add("def");
      bag2.add("def");
      bag2.add("klm");
      System.out.print("bag2 all unique:             ");
      print(bag2);
      System.out.print("bag2 all:                    ");
      printAll(bag2);

      bag3 = factory.getBag();
      bag3.addWithOccurrences("xyz", 5);
      bag3.add("opq");
      bag3.addWithOccurrences("123", 3);
      bag3.addWithOccurrences("123",4);
      System.out.print("bag3 all unique:             ");
      print(bag3);
      System.out.print("bag3 all:                    ");
      printAll(bag3);


      System.out.print("createMergedAllOccurrences:  ");
      Bag<String> bag4 = bag1.createMergedAllOccurrences(bag3);
      printAll(bag4);

      System.out.print("createMergedAllUnique:       ");
      Bag<String> bag5 = bag1.createMergedAllUnique(bag3);
      print(bag5);

      System.out.println("----------save bag test----------------");
      bag3.saveBag();
      
      System.out.println("----------subtract test----------------");
      Bag<String> bag7 = bag3.subtract(bag2);
      System.out.println(bag7);

      System.out.println("------------toString test--------------");
      String bag6 = bag2.toString();
      System.out.println(bag6);

      System.out.println("----------removeAllCopies test---------");
      bag3.removeAllCopies();
      System.out.println(bag3);

      System.out.println("----------load bag test-----------------");
      System.out.print("test bag:                    ");
      loadBag("C:\\Users\\USER\\Desktop\\COMP0004Bag-master\\save.txt");
    }
    catch (BagException e)
    {
      System.out.println("====> Bag Exception thrown...");
    }
  }

  public static void main(String[] args)
  {
//    System.out.println("------------ArrayBag------------");
//    new Main().go("ArrayBag");
    System.out.println("------------MapBag---------------");
    new Main().go("MapBag");
//    System.out.println("------------LinkedListBag---------------");
//    new Main().go("LinkedListBag");
  }
}