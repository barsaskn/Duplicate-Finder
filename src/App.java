import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a path: ");
        String pathName = scanner.nextLine();
        FileFinder fileFinder = new FileFinder(pathName);
        List<Path> list1 = new ArrayList<Path>();
        fileFinder.getAllDirectory();
        list1 = fileFinder.getOnlyFiles();
        List<FileHash> filehashList = new ArrayList<FileHash>();
        for (Path path : list1) {
            filehashList.add(new FileHash(path)); //filehashList
        }
        System.out.println("1-Write SAVE for save files to secure place\n2-Write CHECK check duplicate files\n3. Write BACKUP to see changed files from last save");
        String choose = scanner.nextLine();
        if(choose.equals("CHECK")){
            FileDuplicate fileDuplicate = new FileDuplicate(filehashList);
            fileDuplicate.getDuplicates();
            ArrayList<FileHash> duplicatelist=fileDuplicate.getResult();
            for (FileHash fileHash : duplicatelist) {
                System.out.println(fileHash.getPath().toString());
            }
        }
        else if(choose.equals("SAVE")){
            System.out.println("Enter a secure path: ");
            String securePathName = scanner.nextLine();
            System.out.println(filehashList.size());
            FileSave fileSave = new FileSave(securePathName, filehashList);
            fileSave.FileallSave();
        }
        scanner.close();
    }
}
