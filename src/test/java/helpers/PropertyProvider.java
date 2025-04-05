package helpers;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static PropertyProvider intance;
    @Getter
    private  final Properties properties = new Properties();

    public static synchronized PropertyProvider getIntance(){
        if (intance==null)
            intance = new PropertyProvider();
        return intance;
    }
    private PropertyProvider(){
        try (InputStream propertiesInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("env_local.properties")){
            properties.load(propertiesInputStream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {return properties.getProperty(key);}
}
