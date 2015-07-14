package dmfmm.StarvationAhoy.FoodStats.FileFormats;

import net.minecraft.item.Item;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class ItemListFormat {

    BufferedReader reader;
    BufferedWriter writer;

    public ArrayList<Item> items = new ArrayList<>();

    File thefile;

    public ItemListFormat(File readme){
        this.thefile = readme;

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

    public void load() throws IOException {
        String line = reader.readLine();
        while (line != null){
            this.items.add((Item) Item.itemRegistry.getObject(line.split("\n")[0]));
            line = reader.readLine();
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

    public void save() throws IOException {
        dump();
        this.thefile.delete();
        this.thefile.createNewFile();
        undump();

        for (Item i : this.items){
            String towrite = Item.itemRegistry.getNameForObject(i) + "\n";
            System.out.println(towrite);

            this.writer.write(towrite);
        }
        this.writer.flush();


        System.out.println("written");
    }



}
