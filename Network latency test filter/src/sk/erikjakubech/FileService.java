package sk.erikjakubech;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileService {

    private static FileWriter writer;
    private static final String DEFAULT_FILE_PATTERN = "yyyy-MM-dd-HH-mm-ss";


    public static void createOutput() {
        JFileChooser saveAs = new JFileChooser();
        saveAs.setDialogTitle("Uložiť");
        int userSelection = saveAs.showSaveDialog(Frame.getFrame());


        if (userSelection == JFileChooser.APPROVE_OPTION){

            try {
                writer = new FileWriter(new File(saveAs.getSelectedFile().getAbsolutePath()) + ".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String s : FileReader.getData()) {
                try {
                    if (!s.contains("Reply") && !s.contains("Pinging") && !s.contains("Request")) {
                        writer.write(s + System.lineSeparator());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
