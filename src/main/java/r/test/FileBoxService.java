package r.test;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.Style;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
*/

// The Java class will be hosted at the URI path "/fileboxservice"

@Path("/fileboxservice/")
public class FileBoxService {
	
    final static String path="c:\\java_projects\\test\\";
 
    private RDir directory;
    
    public FileBoxService() {
       directory = new RDir(path);
       directory.setfilelist();
    } 
             
    @GET
    @Produces("text/html")
    @Path("/path/")
    public String getpath() {
        // Return path
        return "<html lang=\"en\"><body><font color=\"#0000FF\">"+path+"</body></html>";
    }  
    
    @GET
    @Produces("text/html")
    @Path("/ls/")  
    public String listfilesHTML() {
         
        // Return list of files as HTML
        String str1="";                  
        
        String str2="";
        
        str1="<html lang=\"en\"><body><font color=\"#000000\" style=\"bold\">Files in directory: "+path+"</font>";
        
    
        str2= str1 + directory.getfilelistHTML() + "</body></html>";
               
        return str2; 
    }  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lsjson/")  
    public String listfilesJSON() {
         
        // Return list of files as JSON
        
        return directory.getfilelistJSON();
    }  
    
    
}
