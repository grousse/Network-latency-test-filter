package sk.erikjakubech;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {

    private static ArrayList<String> data = new ArrayList<>();


    public static void fileRead(File file) {

        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                data.add(input);
            }
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String lostTracker(){
        Pattern p = Pattern.compile("\\d+");
        List<Integer> numOfLost = new ArrayList<>();
        for (String line : data) {
            if (line.contains("Lost = ")) {
                String end = line.substring(48);
                Matcher m = p.matcher(end);
                if(m.find()) {
                    numOfLost.add(Integer.valueOf(m.group()));
                }
            }
        }

        int sum = 0;
        for (Integer i : numOfLost) {
            sum = sum + i;
        }
        String result = "";
        if(sum == 0) {
            result = "Bez straty pripojenia";
        } else if (sum > 0) {
            result = "Prerušené spojenia:" + sum;
        }
        return result;
    }

    public static void displayData(String s) {
        Frame.getTextArea().append(s + "\n");
    }

    public static List<String> getData() {
        return data;
    }
}
