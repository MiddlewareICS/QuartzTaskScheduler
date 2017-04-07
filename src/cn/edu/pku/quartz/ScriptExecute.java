package cn.edu.pku.quartz;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import org.python.core.PyInteger;  
import org.python.core.PyObject;  
import org.python.util.PythonInterpreter;  
 
import java.io.*;  
import java.lang.System.*;  

import org.python.core.PyFunction;
import org.quartz.Job;
import org.quartz.JobDataMap;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScriptExecute implements Job {
	
	
	public String getFileData(File file) {
		
		try {
			
			FileInputStream fis = new FileInputStream(file);
		    byte[] data = new byte[(int)file.length()];
		    fis.read(data);		    
		    String s = new String(data, "UTF-8");
			return s;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}


	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		Email email = (Email) jobDataMap.get("Email");
		System.out.print(email.toEmail + "\n");
		String path = (String) jobDataMap.get("Script");
		System.out.println(path + "\n");
		
		
		/**
		 * This is for pythonb scripts
		 */
		PythonInterpreter interpreter = new PythonInterpreter();  
	    interpreter.execfile(path);  
	    PyFunction func = (PyFunction)interpreter.get("test",PyFunction.class);  
	    
	    int a = 2010, b = 2 ;  
	    PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));  
	    System.out.println("anwser = " + pyobj.toString());
		
//		ScriptEngine pyEngine = new ScriptEngineManager().getEngineByName("python");
//		
//		if (pyEngine != null){
//			
//			System.out.print("start\n");
//			
//			try {
//				/**
//				 * Read from file stream
//				 */
//				/*Reader scriptReader = new InputStreamReader(
//						ScriptExecute.class.getResourceAsStream(path));
//				pyEngine.eval(scriptReader);
//				pyEngine.eval(scriptReader);
//				*/
//				
//				/**
//				 * Bindings execute
//				 */
//				/*Bindings param = pyEngine.createBindings();
//				
//				param.put("c", 3);
//				param.put("d", 4);
//				
//				String jsData = getFileData( new File(path));
//				
//				pyEngine.eval(jsData, param);*/	
//				
//				String jsData = getFileData(new File(path));
//				pyEngine.eval(jsData);
//				
//				if (pyEngine instanceof Invocable) {
//	                Invocable invocable = (Invocable) pyEngine;
//	                // Method call
//	                Object result = invocable.invokeFunction("test", 1, 2);
//	                System.out.println("The result is: " + result);
//	            }
//			} catch (ScriptException e) {
//				// TODO Auto-generated catch block
//				System.out.println("This is just a test, so nobody cares it is correct or not ");
//				e.printStackTrace();
//			} catch (NoSuchMethodException e) {
//				System.out.println("Nobody care the method is exist or not");
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}

}
