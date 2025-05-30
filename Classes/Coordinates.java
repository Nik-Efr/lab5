package Classes;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coordinates {
    @CsvBindByName(column = "coordinates.x", required = true)
    private long x; //Значение поля должно быть больше -572, Поле не может быть null
    @CsvBindByName(column = "coordinates.y", required = true)
    private long y; //Значение поля должно быть больше -272

}