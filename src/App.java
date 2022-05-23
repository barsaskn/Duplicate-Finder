import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        String pathName=JOptionPane.showInputDialog("Enter a path");
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter a path: ");
        //String pathName = scanner.nextLine();
        FileFinder fileFinder = new FileFinder(pathName);
        List<Path> list1 = new ArrayList<Path>();
        fileFinder.getAllDirectory();
        list1 = fileFinder.getOnlyFiles();
        List<FileHash> filehashList = new ArrayList<FileHash>();
        for (Path path : list1) {
            filehashList.add(new FileHash(path)); //filehashList
        }
        while(true){
            String choose = JOptionPane.showInputDialog("1-Write SAVE for save files to secure place\n2-Write CHECK check duplicate files\n3. Write BACKUP to see changed files from last save");
            if(choose.equals("CHECK")){
                FileDuplicate fileDuplicate = new FileDuplicate(filehashList);
                fileDuplicate.getDuplicates();
                ArrayList<FileHash> duplicatelist=fileDuplicate.getResult();
                if(duplicatelist.size()==0)
                    JOptionPane.showMessageDialog(null, "All files are identical");
                else
                    JOptionPane.showMessageDialog(null,duplicatelist.size()+" files are not identical");
                    String temp="";
                for (FileHash fileHash : duplicatelist) {
                    temp+=fileHash.getPath().toString()+"\n";
                }
                JOptionPane.showMessageDialog(null,temp);
                
            }
            else if(choose.equals("SAVE")){
                //System.out.println("Enter a secure path: ");
                //String securePathName = scanner.nextLine();
                String securePathName = JOptionPane.showInputDialog("Enter a secure path");
                //System.out.println(filehashList.size());
                FileSave fileSave = new FileSave(securePathName, filehashList);
                fileSave.FileallSave();
            
            }
            else if(choose.equals("BACKUP")){
                //System.out.println("Enter a secure path: ");
                //String securePathName = scanner.nextLine();
                String securePathName = JOptionPane.showInputDialog("Enter a secure path:");
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
                    JOptionPane.showMessageDialog(null,"All file are same with the save");
                    //System.out.println("All files are same with the save.");
                else{
                    JOptionPane.showMessageDialog(null,"All file are not same with the save");
                
                for (FileHash fileHash : changedFiles) {
                    String temp="";
                    temp+=fileHash.getPath().toString()+" file is not same with the save.\n";                    
                    JOptionPane.showMessageDialog(null, temp);
                }
            
            }
            }

        }
    }

}

