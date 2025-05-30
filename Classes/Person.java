package Classes;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    @CsvBindByName (column = "person.name", required = true)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @CsvBindByName (column = "person.birthday")
    @CsvDate("yyyy-MM-dd")
    private Date birthday;//Поле может быть null
    @CsvBindByName (column = "person.weight")
    private Double weight; //Поле может быть null, Значение поля должно быть больше 0
    @CsvBindByName (column = "person.passportID")
    private String passportID;//Строка не может быть пустой, Поле может быть null
    @CsvBindByName (column = "person.nationality")
    private Country nationality;//Поле может быть null

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return "Person(name=" + name +
                ", birthday=" + (birthday != null ? format.format(birthday) : null) +
                ", weight=" + weight +
                ", passportID=" + passportID +
                ", nationality=" + nationality + ")";
    }
}