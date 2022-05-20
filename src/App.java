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
        while(true){
            String choose = scanner.nextLine();
            if(choose.equals("CHECK")){
                FileDuplicate fileDuplicate = new FileDuplicate(filehashList);
                fileDuplicate.getDuplicates();
                ArrayList<FileHash> duplicatelist=fileDuplicate.getResult();
                if(duplicatelist.size()==0)
                    System.out.println("All files are identical");
                else
                    System.out.println(duplicatelist.size()+" files are not identical");
                for (FileHash fileHash : duplicatelist) {
                    System.out.println(fileHash.getPath().toString());
                }
                break;
            }
            else if(choose.equals("SAVE")){
                System.out.println("Enter a secure path: ");
                String securePathName = scanner.nextLine();
                System.out.println(filehashList.size());
                FileSave fileSave = new FileSave(securePathName, filehashList);
                fileSave.FileallSave();
                break;
            }
            else if(choose.equals("BACKUP")){
                System.out.println("Enter a secure path: ");
                String securePathName = scanner.nextLine();
                SaveReader saveReader = new SaveReader(securePathName);
                ArrayList<FileHash> listfromsave= saveReader.getSavedFiles();
                ArrayList<FileHash> changedFiles = new ArrayList<FileHash>();
                for(int i=0;i<filehashList.size();i++){
                    boolean flag=true;
                    for(int j=0; j<listfromsave.size();j++){
                            if(filehashList.get(i).getPath().toString().equals(listfromsave.get(j).getPath().toString()) && filehashList.get(i).getHash().equals(listfromsave.get(j).getHash()))
                            flag=false;
                        }
                    if(flag)
                        changedFiles.add(filehashList.get(i));
                }
                if(changedFiles.size()==0) 
                    System.out.println("All files are same with the save.");
                for (FileHash fileHash : changedFiles) {
                    System.out.println(fileHash.getPath().toString()+" file is not same with the save.\n");                    
                }
                break;
            }
            System.out.println("Correct your choose.\n");
        }
        scanner.close();
    }

}

