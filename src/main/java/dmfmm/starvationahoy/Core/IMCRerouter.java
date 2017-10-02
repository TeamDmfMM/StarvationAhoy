package dmfmm.starvationahoy.Core;

import net.minecraftforge.fml.common.event.FMLInterModComms;
import dmfmm.starvationahoy.CropWash.ModuleCropWash;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class IMCRerouter {

    Map<String, Method> links = new HashMap<>();

    public IMCRerouter(){


        addModule(ModuleCropWash.class, "cropwash");


    }

    public void onImcMessage (FMLInterModComms.IMCMessage message){

        String token = message.key;

        String[] stuff = token.split("-");

        String tokenized = stuff[0];
        if (links.containsKey(tokenized)){

            Method toCall = links.get(tokenized);

            try {
                toCall.invoke(null, message);
            } catch (IllegalAccessException  | InvocationTargetException e) {
                e.printStackTrace();
            }
        }


    }

    public void addModule(Class module, String imcName){


        try {
            Method imcFunc = module.getDeclaredMethod("imc", FMLInterModComms.IMCMessage.class);

            links.put(imcName, imcFunc);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


}
