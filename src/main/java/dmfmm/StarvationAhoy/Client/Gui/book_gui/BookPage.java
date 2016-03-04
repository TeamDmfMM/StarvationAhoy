package dmfmm.StarvationAhoy.Client.Gui.book_gui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class BookPage {

    // CONSTANTS BEGIN

    public static final int PAGE_1_START_X = 10;
    public static final int PAGE_2_START_X = 0;
    public static final int PAGE_WIDTH = 100;
    public static final int PAGE_START_Y = 10;
    public static final int PAGE_HEIGHT = 120;

    // CONSTANTS END

    public ArrayList<BookElement> elements;

    int numpages = 0;

    public void digestString(String string){

        String tokenizing = "";
        int lexer_state = 0;
        int lexer_arg = 0;
        String data_ = "";
        String alt_data = "";
        String alt_alt_data = "";

        ArrayList<String> currentArgs = new ArrayList<>();
        int x = 0;
        int y = 0;

        boolean specialchar = false;

        int page = 0;

        ArrayList<BookElement.Token> tokens = new ArrayList<>();

        ArrayList<BookElement> unresolved = new ArrayList<>();

        for (Character i : string.toCharArray()){

            if (i.equals('\\') && specialchar == false){
                specialchar = true;
                continue;
            }

            switch (lexer_state){

                case 0:

                    if (i.equals('<')){
                        lexer_state = 1;
                    }

                    break;

                case 1:

                    if (i.equals('|') && specialchar == false){
                        lexer_state = 2;
                        lexer_arg = 0;

                        data_ = "";

                        break;
                    }

                    tokenizing += i;

                    break;

                case 2:

                    if (i.equals('|') && specialchar == false){

                        switch (lexer_arg){
                            case 0:
                                x = Integer.parseInt(data_);
                                break;
                            case 1:
                                y = Integer.parseInt(data_);
                                break;
                            case 2:
                                page = Integer.parseInt(data_);
                                break;

                        }

                        lexer_arg += 1;
                        data_ = "";
                        if (lexer_arg == 3){
                            lexer_state = 3;
                            lexer_arg = 0;
                            data_ = "";

                            continue;
                        }

                        continue;
                    }
                    if (i.equals('>') && specialchar == false){

                        page = Integer.parseInt(data_);

                        lexer_arg += 1;
                        if (lexer_arg == 3){
                            lexer_state = 4;
                            lexer_arg = 0;
                            data_ = "";

                            continue;
                        }
                        else {
                            // uhoh
                        }

                    }

                    data_ += i;

                    break;
                case 3:

                    if (i.equals('|') && specialchar == false){
                        currentArgs.add(data_);

                        lexer_arg += 1;

                        data_ = "";
                        continue;
                    }

                    if (i.equals('>') && specialchar == false){

                        currentArgs.add(data_);
                        data_ = "";
                        lexer_arg = 0;
                        lexer_state = 4;

                        continue;

                    }

                    data_ += i;

                    break;

                case 4:

                    if (i.equals('{') && specialchar == false){

                        BookElement.Token token = new BookElement.Token(data_);

                        tokens.add(token);

                        data_ = "";

                        alt_data = "";
                        alt_alt_data = "";

                        lexer_state = 5;

                        lexer_arg = 0;

                        continue;
                    }

                    if (i.equals('<') && specialchar == false){

                        BookElement.Token token = new BookElement.Token(data_);

                        tokens.add(token);

                        data_ = "";

                        lexer_state = 6;

                        continue;

                    }

                    data_ += i;
                    break;
                case 5:


                    if (i.equals('}') && specialchar == false){

                        if (lexer_arg == 1){

                            BookElement.Token token = new BookElement.Token(data_, alt_data, 2);tokens.add(token);

                        }
                        else {

                            BookElement.Token token = new BookElement.Token(data_, 1);tokens.add(token);

                        }

                        data_ = "";
                        alt_data = "";

                        lexer_state = 4;
                        lexer_arg = 0;
                        continue;



                    }

                    if (i.equals(':') && specialchar == false){
                        lexer_arg = 1;
                        alt_data = "";

                        continue;
                    }

                    if (lexer_arg == 0){
                        data_ += i;
                    }
                    else {
                        alt_data += i;
                    }
                    break;
                case 6:
                    if (i.equals('>')){
                        lexer_arg = 0;
                        lexer_state = 0;

                        BookElement unresolve = new BookElement(tokenizing, x, y, page, currentArgs, tokens);

                        unresolved.add(unresolve);

                        tokenizing = "";
                        data_ = "";
                        alt_data = "";
                        alt_alt_data = "";

                        currentArgs = new ArrayList<>();
                        x = 0;
                        y = 0;

                        specialchar = false;

                        page = 0;

                        tokens = new ArrayList<>();
                    }



            }

            if (specialchar == true){
                specialchar = false;
            }
        }

    this.elements = unresolved;

    }

    public void rasterizeToLayout(BookPageGui theGui) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        int auto_pos_head_x = PAGE_1_START_X;
        int auto_pos_head_y = PAGE_START_Y;
        int auto_pos_head_page = 0;

        ArrayList<BookElement> newPages = new ArrayList<>();

        for (BookElement b : this.elements){
            if (b.x == -1){
                b.x = auto_pos_head_x;
            }
            else {
                b.x += PAGE_1_START_X;
            }
            if (b.y == -1){
                b.y = auto_pos_head_y;
            }
            else {
                b.y += PAGE_START_Y;
            }
            if (b.page == -1){
                b.page = auto_pos_head_page;
            }
            else {

            }



            Method splitterFunc = null;


                splitterFunc = BookPageGui.class.getMethod("splitElement" + b.drawFunction, BookElement.class);


            for (BookElement split : (ArrayList<BookElement>) splitterFunc.invoke(theGui, b)) {
                if (split.drawFunction == "EndMarker") {
                    auto_pos_head_y = split.y;
                    auto_pos_head_page = split.page;
                    if (this.numpages < split.page){
                        this.numpages = split.page;
                    }
                    continue;
                } else {
                    newPages.add(split);
                }
            }

        }
        this.elements = newPages;
    }

    public static String tokenized(ArrayList<BookElement.Token> tokens, Map<String, String> tokenchanges){

        String ret = "";
        for (BookElement.Token t : tokens){
            if (t.type == 0){
                ret += t.data;
            }
            if (tokenchanges != null){

                    if (t.type == 1){
                        if (tokenchanges.containsKey(t.data)){
                            ret += tokenchanges.get(t.data);
                        }
                    }

            }
        }


        return ret;
    }


}
