package r.test;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import java.io.IOException;

import org.apache.log4j.Logger;


@Path("/fileboxservice/")
public class FileBoxService {

	static Logger log = Logger.getLogger(FileBoxService.class.getName());

    final static String path="c:\\java_projects\\test111\\";

    private RDir directory;

    public FileBoxService() {
       directory = new RDir(path);
       directory.setfilelist();
    }
/*
    @GET
    @Produces("text/html")
    @Path("/path/")
    public String getpath() {
        // Return path
    	log.info("getpath() method ...");
        return "<html lang=\"en\"><body><font color=\"#0000FF\">"+path+"</body></html>";
    }
*/
/*
    @GET
    @Produces("text/html")
    @Path("/ls/")
    public String listfilesHTML() {
    	log.info("listfilesHTML() method ...");
        // Return list of files as HTML
        String str1="";

        String str2="";

        str1="<html lang=\"en\"><body><font color=\"#000000\" style=\"bold\">Files in directory: "+path+"</font>";


        str2= str1 + directory.getfilelistHTML() + "</body></html>";

        return str2;
    }
*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lsjson/")
    public String listfilesJSON() {

        // Return list of files as JSON
    	log.info("listfilesJSON() method  ...");
        return directory.getfilelistJSON();
    }
    @GET
	@Path("/getfilebyname/{fname}/")
	public byte[] getfilebyname(@PathParam("fname") String name) {
    	 log.info("getfilebyname(String name) method  ...");
         byte[] emptyfile = "The file is empty".getBytes();
    	 try  {
    		byte[] f = directory.getfile(name);
    		if (f.length > 0) {
            	return f;
            }
         }

         catch (IOException ex) {
      	    ex.printStackTrace();
         }
	  	 return emptyfile;
    }
/*
    @GET
	@Path("/getfiletest/")
    public String getfilebyname() {
    	log.info("getfilebyname(String name) method  ...");
    	return directory.getfiletest();
    }
*/
}
