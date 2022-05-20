import java.util.ArrayList;
import java.util.List;

public class FileDuplicate {    //
    private List<FileHash> start = new ArrayList<FileHash>();
    private ArrayList<FileHash> result = new ArrayList<FileHash>();
    public FileDuplicate(List<FileHash> start){
        this.start=start;
    }
    public void getDuplicates(){
        for(int i=0; i<start.size()-1 ; i++){
            for(int j=i+1;j<start.size();j++){
                if(start.get(i).getHash().equals(start.get(j).getHash())){
                    result.add(start.get(i));
                    result.add(start.get(j));
                }
            }
        }
    }
    public ArrayList<FileHash> getResult() {
        return this.result;
    }
}
    
    

