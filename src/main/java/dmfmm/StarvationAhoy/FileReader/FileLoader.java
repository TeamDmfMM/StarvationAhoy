package dmfmm.StarvationAhoy.FileReader;

import net.minecraft.client.Minecraft;

public class FileLoader {
	
	public static String getCfgPath(){
		String path;
		String jfile = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
		jfile = jfile.substring(0, jfile.length() - 1);
		jfile = jfile.replace('\\', '/');
		jfile.replace(".", "");
		path = jfile;
		path += "config/";
		return path;
	}
	
	
}