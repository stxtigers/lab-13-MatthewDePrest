import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Main
{
  public static void main(String[] args)
  {

  }
  public static void stats(String filename) throws IOException
  {
    Scanner fileReader = new Scanner(new File(filename));
     double[] buffer = new double[5000]; // a buffer is a place in memory you read data into 
     int count = 0;
    
     //Load file into buffer array
     while(fileReader.hasNext())
     {
     buffer[count] = fileReader.nextInt(); 
     count++;
     }
     
     double[] numbers = Arrays.copyOf(buffer, count); // gived us right size array 
     buffer = null; //allows the buffer to be a garbage collector
     
     double mean = average(numbers);
     double stdev = stdev(numbers);
     double median = findMedian(numbers);
     printArray(mean,median,stdev);
    
  }
  
  
  
  
  
  //Assume no null data inbetween non-null dat
  // Wont have this -> {"Hello", "Bob", null, "happy"} 
  //Array doesn't have to be full
  public static String[] insert(String s, int i, String[] a, int logicalSize)
  {
     if(logicalSize <= a.length)
    {
      return null;
    }
    else
    {
      String[] copy = new String[a.length + 1];
      int n = 0;
      while(n < i)
      {
        copy[n] = a[n];
        n++;
      }
      copy[i] = s;
      n = i + 1;
      while(n < a.length + 1)
      {
        copy[n] = a[n - 1];
        n++;
      }
      return copy;
    }
  
  }
  

  public static int[] delete(int target, int[] a, int logicalSize)
  {
    int[] newList = new int[a.length - 1];
    int i = 0;
    int trueNumber = -1;
    while(i < a.length)
    {
      if(a[i] == target)
      {
        trueNumber = i;
      }
      i++;
    }
    if(trueNumber == -1)
    {
      return null;
    }
    else
    {
      int n = 0;
      while(n < i)
      {
        newList[n] = a[n];
        n++;
      }
      while(n < newList.length)
      {
        newList[n] = a[n - 1];
        n++;
      }
      return newList;
    }
  
  }

private static double[] copyArray(double[] numbers)
  {
    double[] copy = new double[numbers.length];
    for(int i = 0; i < numbers.length; i++)
    {
      copy[i] = numbers[i];
    }
    return copy;
  }

   public static double stdev(double[] a)
  {
    int i = 0;
    double sum = 0;
    while(i < a.length)
    {
      sum = sum + a[i];
      i++;
    }
    double mean = sum/a.length;
    int n = 0;
    double sum2 = 0;
    while(n < a.length)
    {
      sum2 = sum + (a[n] - mean) * (a[n] - mean);
    }
    return Math.sqrt(sum2 / (a.length - 1));
  }

  private static double findMedian(double[] numbers)
  {
    double[] List2 = sort(numbers);
    
    if(List2.length % 2 == 1)
    {
      return List2[List2.length/2];
    }
    else
    {
      
      double average = List2[List2.length/2-1] + List2[List2.length/2];
      return average;
    }
  }

  private static void printArray(double mean, double median, double stdev) throws IOException
  {
    //Open output.txt for writing
    PrintWriter writer = new PrintWriter(new File("output.txt"));
    
    writer.println("Mean: " + mean + " " + "Median: "+ median + " " + "Stdev: " + stdev);

    writer.close();
  }

private static double average(double[] numbers)
  {
    
    double sum = 0;
    for(int i = 0; i < numbers.length; i++)
    {
      sum += numbers[i];
    }
    return sum/numbers.length; 

  }
 
 private static double[] sort(double[] numbers)
  {
    double[] sortedArray = copyArray(numbers);
   
    for(int j = sortedArray.length - 1; j < 2; j--)
    {
      
      for(int i = 0; i < j; i++)
      {
        if(sortedArray[i] > sortedArray[i+1])  
        {
          double sorted = sortedArray[i];
          sortedArray[i] = sortedArray[i+1];
          sortedArray[i+1] = sorted;
        }
      }
    }
    return sortedArray;
  }
  public static String[] removeNulls(String[] a)
  {
    int count = 0;
    int i = 0;
    while(i < a.length)
    {
      if(a[i].equals(null))
      {
        count++;
      }
      i++;
    }
    String[] copy = new String[a.length - count];
    int spot = 0;
    int count2 = 0;
    while(spot < copy.length)
    {
      if(a[spot].equals(null))
      {
        count2++;
      }
      else
      {
        copy[spot - count2] = a[spot];
      }
      spot++;
    }
    return copy;
  }
  

}