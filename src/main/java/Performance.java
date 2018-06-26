

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class PerformanceValidate
{
	double FuleWeight_Cruise, 
		Range, 
		R,
		AirDensity_SeaLevel, 
		StallVelocity,
		LiftOverDragRatio, 
		TotalWeight, 
		FuelWeight, 
		Efficiency, 
		SpecificFuelConsumption, 
		WingArea, 
		MaxLiftCoefficient;
	PerformanceValidate()
	{
		FuleWeight_Cruise = 0; 
		Range = 0;
		AirDensity_SeaLevel = 0.002378;
		StallVelocity = 0;
		
		LiftOverDragRatio = 0;
		TotalWeight = 0;
		FuelWeight = 0; 
		Efficiency = 0; 
		SpecificFuelConsumption = 0; 
		WingArea = 0;
		MaxLiftCoefficient = 0;	
	}
	
	 public String[] ReadLines(String filename) throws IOException
	 {
	      FileReader fileReder = new FileReader(filename);
	      BufferedReader bufferedReader = new BufferedReader(fileReder);
	      List<String> lines = new ArrayList<String>();
	      String line= null;
	      
	      while ((line = bufferedReader.readLine())!=null)
	      {
	        lines.add(line);
	      }
	      
	      bufferedReader.close();
	      return lines.toArray(new String[lines.size()]);
	    }
	 
	    public void CalculatePerformance(String filename) throws IOException
	    {
	      String[] data = ReadLines(filename);
	      //System.out.print(data[0]);
	      
	      if (data.length >=6) 
	      {	    	  
	  		LiftOverDragRatio = Float.parseFloat(data[0]);
	  		TotalWeight = Float.parseFloat(data[1]);
	  		FuelWeight = Float.parseFloat(data[2]);
	  		Efficiency = Float.parseFloat(data[3]);
	  		SpecificFuelConsumption = Float.parseFloat(data[4]);
	  		WingArea = Float.parseFloat(data[5]);
	  		MaxLiftCoefficient = Float.parseFloat(data[6]);
	  		}
	      
	      FuleWeight_Cruise = (float) (0.85 * FuelWeight);
	      
	      Range = (float) ((Efficiency * LiftOverDragRatio / SpecificFuelConsumption) 
	    		  * Math.log(TotalWeight/(TotalWeight - FuleWeight_Cruise)))/ 5280.0;
	      
	      StallVelocity = (float) Math.sqrt( 2 * TotalWeight / 
	    		  (AirDensity_SeaLevel * WingArea * MaxLiftCoefficient));
	      
	     /* System.out.println(FuelWeight);
	      System.out.println(FuleWeight_Cruise);
	      System.out.println(Range);
	      System.out.println(StallVelocity);*/
	      
	      PrintWriter writer = new PrintWriter("PerfOutput.txt", "UTF-8");
	      writer.println("Total weight of Fuel (lbs.) = " + FuelWeight);
	      writer.println("Weight of Fule Available for Cruise (lbs.) = " + FuleWeight_Cruise);
	      writer.println("");
	      writer.println("Max.Range (Miles) = "+ Range);
	      writer.println("Stall Speed(Miles/hr) = "+StallVelocity );
	      writer.close();
	    }
}
public class Performance {

	  public static void main(String[] args)throws Exception
	  {
	    PerformanceValidate per=new PerformanceValidate();
	    String filename = "PerfInput.txt";
	    per.CalculatePerformance(filename);

	  /* File file = new File("C:\\Users\\BalajiN\\Documents\\workspace\\Performance\\PerfInput.txt");
	 
	  BufferedReader br = new BufferedReader(new FileReader(file));
	 
	  String st;
	  while ((st = br.readLine()) != null)
	    System.out.println(st.split("=")[1]);
	 */
	  }

}
