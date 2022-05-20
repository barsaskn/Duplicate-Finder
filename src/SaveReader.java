import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveReader {
    private ArrayList<FileHash> arrayList = new ArrayList<FileHash>();
    private String pathName;
    private File file;
    public SaveReader(String pathName) throws Exception{
        this.pathName=pathName+"/secure.txt";
        File file = new File(this.pathName);
        this.file = file;
    }
    public ArrayList<FileHash> getSavedFiles() throws Exception{
        FileInputStream fileInputStream=new FileInputStream(this.file);       
        Scanner scanner=new Scanner(fileInputStream);       
        while(scanner.hasNextLine()){  
            String temp=scanner.nextLine();      
            for(int i=0; i<temp.length();i++){
                if(temp.charAt(i)=='*'){
                    arrayList.add(new FileHash(Paths.get(temp.substring(0, i)),temp.substring(i+1)));
                    break;
                }
            }
        }  
        scanner.close();
        return arrayList;
    }
}
