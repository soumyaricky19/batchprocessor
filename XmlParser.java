package test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
//import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import test.Batch;
import test.Command;

public class XmlParser {
	//method to build the batch
		public Batch buildBatch(File batchFile) 
		{
			
			Batch batch = new Batch();
			try {
				System.out.println("Opening " + batchFile);
				FileInputStream fis = new FileInputStream(batchFile);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fis);
				Element pnode = doc.getDocumentElement();
				NodeList nodes = pnode.getChildNodes();
				for (int idx = 0; idx < nodes.getLength(); idx++) {
					Node node = nodes.item(idx);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						NamedNodeMap attrs = node.getAttributes();
					      for (int i = 0; i < attrs.getLength(); i++) 
					      {
					        Attr attribute = (Attr) attrs.item(i);
					        if (attribute.getName() != "id" &&
					        	attribute.getName() != "path" &&
					        	attribute.getName() != "in" &&
					        	attribute.getName() != "out" &&
					        	attribute.getName() != "args")
					        		throw new ProcessException(attribute.getName()+" is WRONG!!!");
					      }
						Element elem = (Element) node;
						Command command = buildCommand(elem);
						batch.addCommand(command);
					}	
		
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Parsing complete with errors");
			}
			System.out.println("Parsing complete and batch is built");
			System.out.println("##########################################");

			return batch;
		}
	
		// method to parse the input xml and return the  command to be added into the batch
		private Command buildCommand(Element elem) throws ProcessException 		
		{
			/*
			String cmdName = elem.getNodeName();
			Command cmd = new Command();
			cmd.parse(elem);
//		//	cmd.disp();
			return cmd;
//			
			*/
				
			
			
			String commandName = elem.getNodeName();
			Command command = null;
			if (commandName == null) 
			{
				throw new ProcessException("Can not  parse command from "+ elem.getTextContent());
			} 
		
			
			else if ("pipecmd".equalsIgnoreCase(commandName)) 
			{
				System.out.println("");
				System.out.println("Parsing the pipe command now");
				command = new PipeCmdCommand();
				command.parse(elem);
			} 
		
			
			
			
			else if ("exec".equalsIgnoreCase(commandName)) 
			{
				System.out.println("");
				System.out.println("Parsing cmd");
				command = new Command();
				command.parse(elem);
			} 
		
			
			else if ("filename".equalsIgnoreCase(commandName)) 
				
			{
				System.out.println("");
				System.out.println("Parsing file");
				command = new FileCommand();
				command.parse(elem);
			} 
		
			else 
			{
				throw new ProcessException("Unknown command " + commandName + " from: "	+ elem.getBaseURI());
			}
			
			return command;
			
			
		}
			
			
			
			
			
		
		




}
