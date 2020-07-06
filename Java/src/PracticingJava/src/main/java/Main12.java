import example.avro.User;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class Main12 {
    public static void main(String[] args) throws IOException {
        User user = new User("Sam Nelson",11,"red12");
        user.setName("Sam Nelson");
        user.setFavoriteColor("red");
        user.setFavoriteNumber(12);

        File file = new File("user.avro");
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new
                DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user.getSchema(), file);
        dataFileWriter.append(user);
        dataFileWriter.close();
    }
}
