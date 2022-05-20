import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileSave{
    private List<FileHash> arrayList = new ArrayList<FileHash>();
    private String pathName;
    private File file;
    public FileSave(String pathName, List<FileHash> arrayList) throws Exception{
        this.pathName=pathName+"/secure.txt";
        this.arrayList=arrayList;
        File file = new File(this.pathName);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.file = file;

    }
    public void FileallSave() throws Exception{
        fileDeleteAll();
        for(FileHash fileHash : arrayList){
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.write(fileHash.getPath().toString()+"-"+fileHash.getHash()+"\n");
            bWriter.close();
        }
    }
    private void fileDeleteAll() throws Exception{
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write("");
        bWriter.close();

    }
    
}