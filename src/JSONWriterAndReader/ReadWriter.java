package JSONWriterAndReader;

import java.io.IOException;
import java.util.Map;

public interface ReadWriter {


    void saveToFile() throws IOException;


    Map<String, ?> readFromFile() throws IOException, ClassNotFoundException;
}
