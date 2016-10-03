package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.w3c.dom.Element;

public class Command {
	protected String Node_Name;
	protected String comm_id;
	protected String comm_path;
	protected String comm_in;
	protected String comm_out;
	protected List <String> comm_args;
	
	public String getName()
	{
		return Node_Name;
	}
	public String getId()
	{
		return comm_id;
	}
	public String getPath()
	{
		return comm_path;
	}
	public String getIN()
	{
		return comm_in;
	}
	public String getOUT()
	{
		return comm_out;
	}
	public boolean hasIN()
	{
		if (comm_in.equals(""))
			return false;
		else
			return true;
	}
	public boolean hasOUT()
	{
		if (comm_out.equals(""))
			return false;
		else
			return true;
	}
	
	public List<String> getArgs()
	{
		return comm_args;
	}
	
	public void parse(Element elem) throws ProcessException
	{
		//if (elem.getNodeName()=="file")
			Node_Name=elem.getNodeName();
			comm_id=elem.getAttribute("id");
			comm_path=elem.getAttribute("path");
				comm_in=elem.getAttribute("in");
				comm_out=elem.getAttribute("out");
			List<String> cmdArgs = new ArrayList<String>();
			String arg = elem.getAttribute("args");
			if (!(arg == null || arg.isEmpty())) {
				StringTokenizer st = new StringTokenizer(arg);
				while (st.hasMoreTokens()) {
					String tok = st.nextToken();
					cmdArgs.add(tok);
				}
			}
			comm_args=cmdArgs;
		
	}
	
	
	
	
	
	public String describe() 
	{
		return null;
	}
/*	public void disp()
	{
		System.out.println(Node_Name+comm_id+comm_path+comm_in+comm_out);
	}*/
	public void execute(Map<String,Command> commands) throws ProcessException 
	{
		if(getName()=="exec")
		{
			List<String> c = new ArrayList<String>();
			c.add(comm_path);
			for(int i=0;i<comm_args.size();i++)
				c.add(comm_args.get(i));
			
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(c);
	 	 	builder.directory(new File("work"));
			File wd = builder.directory();
			
			System.out.println(wd.getAbsolutePath());
	//		System.out.println("IN:"+getIN());
	//		System.out.println("OUT:"+getOUT());
					
			System.out.println("Command being executed:"+c);
	//		System.out.println(hasIN());
	//		System.out.println(hasOUT());
		
			if (hasIN())
			{	
				try
				{
					String inFile=commands.get(getIN()).getPath();
					System.out.println("I/P file:"+inFile);
					builder.redirectInput(new File(inFile));
				}
				catch(Exception e)
				{
					throw new ProcessException(getIN()+" not mapped");
				}
			}
			
			if (hasOUT())
			{	
				try
				{
					String outFile=commands.get(getOUT()).getPath();	
					System.out.println("O/P file:"+outFile);
					builder.redirectOutput(new File(outFile));
				}
				catch(Exception e)
				{
					throw new ProcessException(getOUT() + " not mapped");
				}
			}
			builder.redirectError(new File(wd, "error.txt"));
			System.out.println("Starting process...");

			try
			{
				Process process = builder.start();
				process.waitFor();
				System.out.println("Successful!!");
			}
			catch(Exception e)
			{
			//	System.out.println(e.getMessage());
				throw new ProcessException(e.getMessage());
			}
		}
	}
}	
