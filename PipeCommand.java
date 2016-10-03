package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import test.Command;
import test.ProcessException;

public class PipeCommand extends Command {
		
	//describing the pipecmdcommand
		@Override
		public String describe() {
			System.out.println("");
			String s= "PipeCmdCommand execution has started";
			return s;
		}

		//parsing the command
			
		
		@Override
		public void execute( Map<String, Command> batch_CommandList ) 
		{
			
			System.out.println("Started the Execution of the pipe cmd command  with id : "+ comm_id);
		
		}
		
		
	
		
		@Override
public void parse(Element elem) throws ProcessException  
		{
			
			
			NodeList pnode = elem.getChildNodes();
			String id = elem.getAttribute("id");
			if (id == null || id.isEmpty()) {
				throw new ProcessException("Missing ID in PipeCMD Command");
			}
			System.out.println("ID: " + id);
			

			String path = elem.getAttribute("path");
			if (path == null || path.isEmpty()) {
				throw new ProcessException("Missing PATH in PipeCMD Command");
			}
			System.out.println("Path: " + path);
			comm_path=path;
			comm_id=id;

			// Arguments must be passed as a list of
			// individual strings.
			List<String> cmdArgs = new ArrayList<String>();
			String arg = elem.getAttribute("args");
			if (!(arg == null || arg.isEmpty())) {
				StringTokenizer st = new StringTokenizer(arg);
				while (st.hasMoreTokens()) {
					String tok = st.nextToken();
					cmdArgs.add(tok);
				}
			}
			for (String argi : cmdArgs) {
				System.out.println("Arg " + argi);
			}
			comm_args=cmdArgs;

			String inID = elem.getAttribute("in");
			if (!(inID == null || inID.isEmpty())) 
			{
				System.out.println("inID: " + inID);
				comm_in=inID;
			}

			String outID = elem.getAttribute("out");
			if (!(outID == null || outID.isEmpty())) 
			{
				System.out.println("outID: " + outID);
				comm_out=outID;
			
			}



	
	
}

}