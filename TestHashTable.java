package see;
import java.util.*;
import java.nio.file.*;

class TestHashTable
{
   public TestHashTable ()
   {
   }
   
   void run ( int threshold )
   {
      
      
      int hit1=0, miss1=0, hit2=0, miss2=0;
      List<String> lines = null;
      List<String> linesmiss = null;
      
   
      try {
         lines = Files.readAllLines (Paths.get ("names36.txt"));
         linesmiss = Files.readAllLines (Paths.get ("namesmiss.txt"));
      } catch (Exception e) {
         System.out.println (e.toString ());
      }   
      HashTable h = new HashTable();
      
      for ( int i=0; i<lines.size (); i++ )
      {
         h.insert (lines.get (i));
      }
      
      for ( int i=0; i<lines.size (); i++ )
      {
         if (h.find (lines.get(i)))
            hit1++;
         else
            miss1++;
      }

      for ( int i=0; i<linesmiss.size (); i++ )
      {
         if (h.find (linesmiss.get(i)))
            hit2++;
         else
            miss2++;
      }
      
      System.out.println ("Collision Threshold: " + threshold);
      if (h.getCollisions () <= threshold)
         System.out.println ("Collisions Threshold Met!");
      else   
         System.out.println ("Collisions Threshold Not Met! Collisions = " + h.getCollisions ());
      System.out.println ("Seen data [Hit:Miss]: " + hit1 + ":" + miss1);
      System.out.println ("Unseen data [Hit:Miss]: " + hit2 + ":" + miss2);
   }

   public static void main ( String [] args )
   {
	   
      (new TestHashTable ()).run(Integer.valueOf (args[0]));
   }
}

