package Console;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.Getter;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;


public class CsvFileParser<T> extends FileParser<T> {

    private final Class<T> classToSerialize;
    @Getter
    private final String fileName;

    public CsvFileParser(String fileName, Class<T> classToSerialize) {
        this.fileName = fileName;
        this.classToSerialize = classToSerialize;
    }

    @Override
    public List<T> deserializeFromFile() { // спарсиваем, делаем через try, чтобы закрыть поток
        try (FileReader fileReader = new FileReader(fileName)) {
            CsvToBean<T> builder = new CsvToBeanBuilder<T>(fileReader)
                    .withType(classToSerialize)
                    .withSeparator(';')
                    .withIgnoreQuotations(true)
                    .build();

            List<T> list = builder.parse();

            return list;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Ошибка чтения CSV");
            return null;
        }
    }

    @Override
    public void serializeIntoFile(Iterator<T> type) { // записываем результат
        try (PrintWriter writer = new PrintWriter(fileName)) {
            StatefulBeanToCsv<T> builder =  new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(';')
                    .withApplyQuotesToAll(false).build();

            builder.write(type);

        } catch (IOException e) {
            System.err.println("Ошибка записи файла CSV");
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }
}