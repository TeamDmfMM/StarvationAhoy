package dmfmm.StarvationAhoy.FoodStats.FileFormats;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class IntLoadFormat {

    public Map<String, Float> data = new HashMap<>();

    BufferedReader reader;
    BufferedWriter writer;

    File thefile;

    public IntLoadFormat(File readme){

        thefile = readme;

        if (this.thefile.exists() == false) {
            try {

                this.thefile.getParentFile().mkdirs();
                this.thefile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.reader = new BufferedReader(new FileReader(readme));
            this.writer = new BufferedWriter(new FileWriter(readme));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void dump(){
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void undump(){
        try {
            this.reader = new BufferedReader(new FileReader(this.thefile));
            this.writer = new BufferedWriter(new FileWriter(this.thefile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load() throws IOException {

        boolean p = false;

        String t = "";

        String line = this.reader.readLine();
        while (line != null){
            String linest = line.split("\n")[0];
            if (p == false){
                t = linest;
                p = true;
            }
            else {
                float g = Float.parseFloat(linest);
                data.put(t, g);
                p = false;
            }
            line = this.reader.readLine();
        }

    }

    public void save() throws IOException {
        dump();
        this.thefile.delete();
        this.thefile.createNewFile();
        undump();

        boolean w = false;

        for (String val : data.keySet()){
            if (w == true) { writer.newLine(); }
            float value = data.get(val);
            this.writer.write(val);
            writer.newLine();
            writer.write(String.valueOf(value));
            w = true;
        }

        writer.flush();



    }
}
