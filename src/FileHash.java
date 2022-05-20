import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHash {
    private Path path;
    private String hash;
    public FileHash(Path path, String hash){
        this.path=path;
        this.hash=hash;
    }
    public FileHash(Path path) throws NoSuchAlgorithmException, IOException{
        this.path=path;
        this.makeHash();
    }
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException{ //returns hash string
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[1024];
        int bytesCount = 0; 
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };
        fis.close();
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();    
    }
    public void makeHash() throws IOException, NoSuchAlgorithmException{ //fills hash variable
        File file = new File(path.toString());
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        this.hash = getFileChecksum(md5Digest, file);

    }
    public Path getPath() {
        return path;
    }
    public void setPath(Path path) {
        this.path = path;
    }
    public String getHash() {
        return hash;
    }

    
}
