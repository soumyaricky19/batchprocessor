package test;

import java.util.Map;

//import javax.lang.model.element.Element;
import org.w3c.dom.Element;

public class FileCommand extends Command{

	
	
	
	
	
	
	public void execute (String workDir, Map<String,Command> Commands){
		
		System.out.println("The file command is being executed");
		System.out.println("the id of the file command that is being executed is "+ comm_id);
		System.out.println("The path of the file whose id is " + comm_id + "set as " + comm_path);
		System.out.println("The execution of the file command is completed and the path of the file of given id is set to the given path");
	
	
	}
	
	
	
	
	@Override
	public void parse(Element element) throws ProcessException
	{
		try{
				String fileid = element.getAttribute("id");
				if(fileid == null || fileid.isEmpty()){
					throw new ProcessException("The id of the file command is missing and hence can not find it");
						
				}
				comm_id = fileid;
			}
		catch (ProcessException  e )
		{
			System.out.println("The missing id of the file has been caught as the error");
		}
		
		String filepath = element.getAttribute("path");
		if(filepath == null || filepath.isEmpty()){
		throw new ProcessException("The given file path is empty and the path value is missing in the command ");
		}
		comm_path = filepath;
}
}