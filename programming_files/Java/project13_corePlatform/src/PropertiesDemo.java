import java.io.*;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.util.InvalidPropertiesFormatException;
        import java.util.Properties;

public class PropertiesDemo {

    public static void main(String[] args) {
        properties_writer();
//        properties_reader();
//        properties_writer_xml();
//        properties_reader_xml();
    }

    public static void properties_writer() {
        Properties props = new Properties();

        props.setProperty("key2", "value2");
        System.out.println(props.getProperty("key1"));

        try (Writer writer = Files.newBufferedWriter(Paths.get("abc.properties"))) {
            props.store(writer, "Sample properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void properties_reader(){
        Properties props = new Properties();
        try(Reader reader = Files.newBufferedReader(Paths.get("abc.properties"))){
            props.load(reader);

            System.out.println(props.getProperty("key1"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // xml will work with output streams, so the output stream has to be stored as xml.
    static void properties_writer_xml(){
        Properties props = new Properties();

        try(OutputStream out = Files.newOutputStream(Paths.get("abc.xml"))){

            props.setProperty("key1", "value1");
            props.storeToXML(out, "sample properties");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void properties_reader_xml(){

        Properties props = new Properties();

        try(InputStream in = Files.newInputStream(Paths.get("abc.xml"))){
            props.loadFromXML(in);
            System.out.println(props.getProperty("key1"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void propertiesWithDefault(){
        Properties defaults = new Properties();
        defaults.setProperty("os", "Windows");
        Properties props = new Properties(defaults); // created with defaults
    }
}
