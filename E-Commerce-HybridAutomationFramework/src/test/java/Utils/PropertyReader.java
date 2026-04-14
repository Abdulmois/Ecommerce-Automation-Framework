package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {

	public static String readKey(String key) throws Exception   {

		FileInputStream fileInputStream=null;

		Properties p=null;

		try{
			fileInputStream=new FileInputStream(new File(System.getProperty("user.dir")+ "/src/test/resources/Utils/data.properties"));
			p = new Properties();
			p.load(fileInputStream);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(Objects.nonNull(fileInputStream)) {
				fileInputStream.close();
			}
		}




		if(p.getProperty(key)==null) {
			throw new Exception(p.getProperty(key)+ "not found");
		}

		else {
			return p.getProperty(key);
		}




	}
}
