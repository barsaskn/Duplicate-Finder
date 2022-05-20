import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileFinder {
    private String filePath;
    private List<Path> results = new ArrayList<Path>();
    public FileFinder(String filePath){
        this.filePath=filePath;
    }
    public List<Path> getAllDirectory(){   //scan a path and return list of all subdirectories
        Path path = Paths.get(filePath);
        try(Stream<Path> subPaths=Files.walk(path)){
            subPaths.forEach(a-> results.add(a));    
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
        return results;
    }
    public List<Path> getOnlyFiles(){ //returns only files not directories
        List<Path> files = new ArrayList<Path>();
        for(Path path: results){
            File file = new File(path.toString());
            if(file.isFile()){
                files.add(path);
            }
        }
        return files;
    }
}
