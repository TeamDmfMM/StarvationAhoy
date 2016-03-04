package dmfmm.StarvationAhoy.Client.Gui.book_gui;

import java.util.ArrayList;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BookElement {

    public static class Token {

        public int type;

        public String value;

        public String data;

        public Token(String value){
            this("", value, 0);
        }

        public Token(String value, int type){
            this("", value, type);
        }

        public Token(String value, String data, int type){

            this.value = value;
            this.data = data;
            this.type = type;
        }
        // type = 0? raw string, from data
        // type = 1? raw arg, from data
        // type = 2? arg, key from data, value from value

    }

        public String drawFunction;

        public int x;
        public int y;

        public int page;

        public ArrayList<String> args;

        public ArrayList<Token> data;

        public BookElement(String drawFunction, int x, int y, int page, ArrayList<String> args, ArrayList<Token> data){


            this.drawFunction = drawFunction;
            this.x = x;
            this.y = y;
            this.page = page;
            this.args = args;
            this.data = data;
        }



    }




