import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame (String adres, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(adres) ;
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(save);
        } catch ( Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    public static void zipFiles(String adres, String file1, String file2, String file3){
        try(ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(adres));
            FileInputStream fis1 = new FileInputStream(file1);
            FileInputStream fis2 = new FileInputStream(file2);
            FileInputStream fis3 = new FileInputStream(file3))
        {
            ZipEntry entry1 = new ZipEntry("newsave1.dat");
            ZipEntry entry2 = new ZipEntry("newsave2.dat");
            ZipEntry entry3 = new ZipEntry("newsave3.dat");
            zout.putNextEntry(entry1);
            zout.putNextEntry(entry2);
            zout.putNextEntry(entry3);
            byte[] buffer =new byte[fis1.available()];
            fis1.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
            byte[] buffer2 =new byte[fis2.available()];
            fis2.read(buffer2);
            zout.write(buffer2);
            zout.closeEntry();
            byte[] buffer3 =new byte[fis3.available()];
            fis3.read(buffer3);
            zout.write(buffer3);
            zout.closeEntry();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void delete(String adres){
        File dir = new File(adres);
        if (dir.delete())
            System.out.println("Файл удален");
    }
    public static void main(String[] args) {
        GameProgress save1=new GameProgress(10,10,0,1);
        GameProgress save2=new GameProgress(15,8,2,20);
        GameProgress save3=new GameProgress(1,3,5,30);
//        C:\Games\savegame
        saveGame("C:\\Games\\savegame\\save1.dat", save1);
        saveGame("C:\\Games\\savegame\\save2.dat", save2);
        saveGame("C:\\Games\\savegame\\save3.dat", save3);

        zipFiles("C:\\Games\\savegame\\saves.zip",
                "C:\\Games\\savegame\\save1.dat",
                "C:\\Games\\savegame\\save2.dat",
                "C:\\Games\\savegame\\save3.dat");
        delete("C:\\Games\\savegame\\save1.dat");
        delete("C:\\Games\\savegame\\save2.dat");
        delete("C:\\Games\\savegame\\save3.dat");


    }
}
