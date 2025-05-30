package Console;

import lombok.Getter;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public abstract class FileParser<T> {
    private Class<T> classToSerialize;
    @Getter
    private String fileName;
    public abstract List<T> deserializeFromFile() throws IOException;
    public abstract void serializeIntoFile(Iterator<T> type) throws IOException;
}
