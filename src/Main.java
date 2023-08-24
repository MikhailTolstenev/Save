import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame(String adres, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(adres);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void delete(String adres11) {
        File dir = new File(adres11);
        if (dir.delete()) {
            System.out.println("Файл удален");
        } else System.out.println("Не обнаружено");
    }

    public static void zipFiles(String adres, ArrayList<String> Files) {
        int num = 1;
        int BUFFER = 80000;

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(adres))) {

            for (String file : Files) {
                processFile(zout, file);
            }
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void processFile(ZipOutputStream out, String file1) {
        try (FileInputStream fis = new FileInputStream(file1)) {
            ZipEntry entry = new ZipEntry(String.valueOf(file1.hashCode()));
            out.putNextEntry(entry);

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            out.write(buffer);

            out.closeEntry();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());


        }
    }

    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(10, 10, 0, 1);
        GameProgress save2 = new GameProgress(15, 8, 2, 20);
        GameProgress save3 = new GameProgress(1, 3, 5, 30);

        saveGame("C:\\Games\\savegame\\save1.dat", save1);
        saveGame("C:\\Games\\savegame\\save2.dat", save2);
        saveGame("C:\\Games\\savegame\\save3.dat", save3);
        ArrayList<String> files = new ArrayList<>();
        files.add("C:\\Games\\savegame\\save1.dat");
        files.add("C:\\Games\\savegame\\save2.dat");
        files.add("C:\\Games\\savegame\\save3.dat");


        zipFiles("C:\\Games\\savegame\\saves11.zip", files);

        delete("C:\\Games\\savegame\\save1.dat");
        delete("C:\\Games\\savegame\\save2.dat");
        delete("C:\\Games\\savegame\\save3.dat");


    }
}
