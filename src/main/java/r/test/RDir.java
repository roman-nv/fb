package r.test;


import java.util.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


public class RDir {
    private String path;
    private Path p1;
    private List<Path> filelist;


    public RDir () {
    	 setpath(".");
    }

    public RDir (String Directory) {
    	 setpath(Directory);
    	 p1 = Paths.get(path);
    	 filelist = new ArrayList<Path>();
    	// filelist = listSourceFiles();

    }

    public String getpath() {
        return path;
    }

    public Path getp1() {
        return p1;
    }

    public void setpath(String path) {
        this.path = path;
    }

    public int getfilenumber () {
    	  return p1.getNameCount();
    }

    public void setfilelist () {
    	  listSourceFiles();
    }

    public List<Path> getfilelist () {
    	  return filelist;
    }

    public String getfilelistHTML () {
    	  String html="";
    	  for (Path p : this.filelist) {
           html = html+"<p>" + p.getFileName() + "</p>";
        }
    	  return html;
    }


    public String getfilelistJSON () {
    	  List<String> ll = new ArrayList<String>();

    	  ll.add("'"+this.filelist.get(0).getFileName()+"'");

    	  String j="{'files': [";
    	  for (Path p : this.filelist) {
    	  	   if (ll.indexOf("'"+p.getFileName()+"'") == 0) {
    	  	   	   continue;
    	  	   	}
    	  	   else {
               ll.add(",'" + p.getFileName() + "'");
             }
        }

        for (String s : ll) {
             j=j+s;
        }

        j = j + "]}";
    	  return j;
    }
    public void listSourceFiles() {

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(p1)) {
            for (Path p : directoryStream) {
            	if (!Files.isDirectory(p))
                filelist.add(p);
            }
        } catch (IOException ex) {
        	   ex.printStackTrace();
        }
    }
    //-----------------------------------------------------------
    public  byte[] getfile  (String name) throws IOException {
    	/*
    	byte[] filecontent = Files.readAllBytes(this.filelist.get(0));
    	return filecontent;
*/
        Path tmp_path = Paths.get(this.path+name);
        if (this.filelist.contains(tmp_path)) {

       		byte[] filecontent = Files.readAllBytes(tmp_path);
     	   return filecontent;
       }
       else {
    	   byte[] filecontent = "No such file".getBytes();
    	   return filecontent;
       }

     //return this.filelist.get(0);
    }
    public String getfiletest  ()  {
    	return this.filelist.get(0).toString();
    }

}
