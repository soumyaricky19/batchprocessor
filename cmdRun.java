package test;

	import java.io.*;
	import java.util.Map;

	import test.Batch;
	import test.Command;

	public class cmdRun 
	{
		public static void main (String args[]) throws ProcessException
		{
		//	String fileName =  args[0];
			System.out.println("Enter");
			java.util.Scanner sc=new java.util.Scanner(System.in);
			String fileName=sc.nextLine();
			File f = new File(fileName);
			System.out.println("Absolute path:"+f.getAbsolutePath());
			
			XmlParser xp=new XmlParser();
			Batch b=xp.buildBatch(f);
			runBatch(b);
			System.out.println("COMPLETED");
		}			
			
		public static void runBatch(Batch batch) throws ProcessException
		{
			Map<String, Command> commandlist;
			commandlist=batch.getCommands();
			
//			System.out.println("List of Commands in the Batch are");
	       
	        for(String key : commandlist.keySet())
	        {
	            System.out.println(key.toString());
	           // if (commandlist.get(key).getName()=="wd")
	            	batch.setWorkingDir("work");
	            
	            commandlist.get(key).execute(commandlist);
	        }
		}
	}