import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//paul mcnut

public class CalculateDistribution
{
	private static String runTime = "";

	private static int phil1Eats = 0; private static int phil1Thinks = 0;
	private static int phil2Eats = 0; private static int phil2Thinks = 0;
	private static int phil3Eats = 0; private static int phil3Thinks = 0;
	private static int phil4Eats = 0; private static int phil4Thinks = 0;
	private static int phil5Eats = 0; private static int phil5Thinks = 0; 

	public static void main(String[] args) throws FileNotFoundException
	{
        File file = new File("C:\\Users\\Ricardo Barron-Silva\\Desktop\\stuf\\output.txt");

		Scanner input = new Scanner(file);  
		while(input.hasNextLine())
		{
			String line = input.nextLine(); 
   			
   			if(line.contains("1"))
   			{
   				if (line.contains("done eating"))
   			 		phil1Eats++;
   				else if (line.contains("done thinking"))
					phil1Thinks++;
   			}
   			else if(line.contains("2"))
   			{
   				if (line.contains("done eating"))
   			 		phil2Eats++;
   			 	else if (line.contains("done thinking"))
					phil2Thinks++;
   			}
   			else if(line.contains("3"))
   			{
   				if (line.contains("done eating"))
   			 		phil3Eats++;
   				else if (line.contains("done thinking"))
					phil3Thinks++;
   			}
   			else if(line.contains("4"))
   			{
   				if (line.contains("done eating"))
   			 		phil4Eats++;
   				else if (line.contains("done thinking"))
					phil4Thinks++;
   			}
   			else if(line.contains("5"))
   			{
   				if (line.contains("done eating"))
   			 		phil5Eats++;
   				else if (line.contains("done thinking"))
					phil5Thinks++;
   			}
   			
   			if(line.contains("time"))
   			{
   				runTime = line.substring(line.indexOf(":"),line.length()-1);
   			}
		}

		System.out.println("Phil1 ate " + phil1Eats + " times and contemplated " + phil1Thinks + " times.");
		System.out.println("Phil2 ate " + phil2Eats + " times and contemplated " + phil2Thinks + " times.");
		System.out.println("Phil3 ate " + phil3Eats + " times and contemplated " + phil3Thinks + " times.");
		System.out.println("Phil4 ate " + phil4Eats + " times and contemplated " + phil4Thinks + " times.");
		System.out.println("Phil5 ate " + phil5Eats + " times and contemplated " + phil5Thinks + " times.");

		System.out.println("The philosophers were at the table for " + runTime);
    }
}