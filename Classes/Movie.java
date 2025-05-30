package Classes;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@NoArgsConstructor //конструктор классса с помощью аннотации
@AllArgsConstructor
public class Movie implements Comparable<Movie> {
    @CsvBindByName(column = "id", required = true)
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @CsvBindByName(column = "name", required = true)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @CsvRecurse
    private Coordinates coordinates; //Поле не может быть null
    @CsvBindByName(column = "creationDate", required = true)
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @CsvBindByName(column = "oscarsCount")
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле может быть null
    @CsvBindByName(column = "genre", required = true)
    private MovieGenre genre; //Поле не может быть null
    @CsvBindByName(column = "mpaaRating", required = true)
    private MpaaRating mpaaRating; //Поле не может быть null
    @CsvRecurse
    private Person operator; //Поле может быть null

    @Override // делаем сортировку по умолчанию по количеству оскаров
    public int compareTo(Movie h) {
        if (this.oscarsCount == null && h.oscarsCount == null) {
            return 0;
        }
        if (this.oscarsCount == null) {
            return 1;
        }
        if (h.oscarsCount == null) {
            return -1;
        }
        return this.oscarsCount - h.oscarsCount;
    }


    public static Movie createFromInput(Context context) {
        String movieName = Movie.enterName(context);


        Person person = null;

        if answer == yes {
            person = new Person(Movie.enterPersonName(context), enterPersonBirthday(context), enterPersonWeight(context), enterPersonPassportID(context), enterPersonNationality(context));
        }



        return new Movie(0, movieName,
                Movie.enterCoordinates(context),
                LocalDateTime.now(),
                Movie.enterOscarsCount(context),
                Movie.enterGenre(context),
                Movie.enterMpaaRating(context),
                person);
    }

    public static String enterName(Context context){
        context.universalOutput("Введите имя: ");
        String current = context.getScanner().nextLine();

        if ((current.isBlank())) {
            context.universalOutput("Неправильный ввод команды. Поле не должно быть пусто.");
            return enterName(context);
        } else {
            context.universalOutput("Имя введено.");
            return current;
        }
    }
    public static Coordinates enterCoordinates(Context context){
        context.universalOutput("Введите координаты:");
        try {
            context.universalOutput("x: ");
            long x = Long.parseLong(context.getScanner().nextLine());
            if (x < -571) {
                context.universalOutput("x должен быть больше -572 ");
                return enterCoordinates(context);
            }
            context.universalOutput("y: ");
            long y = Long.parseLong(context.getScanner().nextLine());
            if (y < -271) {
                context.universalOutput("y должен быть больше -272");
                return enterCoordinates(context);
            }
            return new Coordinates(x, y);
        } catch (NumberFormatException e) {
            context.universalOutput("Введите координаты в формате long.");
            return enterCoordinates(context);
        }

    }

    public static Integer enterOscarsCount(Context context) {
        context.universalOutput("Введите количество Оскаров: ");
        try {
            String oscars = context.getScanner().nextLine();
            if (oscars.isBlank() || oscars.equalsIgnoreCase("null")) {
                return null;
            }
            int oscarsCount = Integer.parseInt(oscars);
            if (oscarsCount < 1) {
                context.universalOutput("Количество Оскаров не может быть меньше нуля.");
                return enterOscarsCount(context);
            }
            return oscarsCount;

        } catch (NumberFormatException e) {
            context.universalOutput("Введите число в формате integer.");
            return enterOscarsCount(context);
        }
    }
    public static MovieGenre enterGenre(Context context) {
        context.universalOutput("Выберите жанр (ACTION, TRAGEDY, THRILLER): ");
        try {
            return MovieGenre.valueOf((context.getScanner().nextLine().toUpperCase()));

        } catch (IllegalArgumentException e) {
            context.universalOutput("Введите один из параметров: ACTION, TRAGEDY, THRILLER.");
            return enterGenre(context);
        }
    }
    public static MpaaRating enterMpaaRating(Context context) {
        context.universalOutput("Выберите возрастной рейтинг (G, PG, PG_13, NC_17): ");
        try {
            return MpaaRating.valueOf((context.getScanner().nextLine()));

        } catch (IllegalArgumentException e) {
            context.universalOutput("Введите один из параметров: G, PG, PG_13, NC_17.");
            return enterMpaaRating(context);
        }
    }
    public static String enterPersonName(Context context) {
        context.universalOutput("Введите имя оператора: ");
        String name = context.getScanner().nextLine();

        if (name.isBlank() || name.equalsIgnoreCase("null")) {
            context.universalOutput("Неправильный ввод команды. Поле не должно быть пусто.");
            return enterPersonName(context);
        } else {
            return name;
        }
    }
    public static Double enterPersonWeight(Context context) {
        context.universalOutput("Введите вес оператора: ");
        String input = context.getScanner().nextLine();
        if (input.isBlank() || input.equalsIgnoreCase("null")) {
            return null;
        }
        try {
            double weight = Double.parseDouble(input);
            if (weight <= 0) {
                context.universalOutput("Вес оператора не может быть меньше нуля.");
                return enterPersonWeight(context);
            }
            return weight;
        } catch (NumberFormatException e) {
            context.universalOutput("Введите число в формате double.");
            return enterPersonWeight(context);
        }
    }
    public static String enterPersonPassportID(Context context) {
        context.universalOutput("Введите ID паспорта оператора: ");
        String passID = context.getScanner().nextLine();

        if (passID.isEmpty()) {
            context.universalOutput("Неправильный ввод команды. Поле не должно быть пусто.");
            return enterPersonPassportID(context);
        } else {
            return passID;
        }
    }
    public static Date enterPersonBirthday(Context context) {
        context.universalOutput("Введите дату рождения оператора: ");
        String input = context.getScanner().nextLine();
        if (input.equalsIgnoreCase("null")) {
            return null;
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(input);
        } catch (ParseException e) {
            context.universalOutput("Введите дату в формате YYYY-MM-DD.");
            return enterPersonBirthday(context);
        }
    }
    public static Country enterPersonNationality(Context context) {
        context.universalOutput("Выберите страну происхождения оператора (USA, UNITED_KINGDOM, ITALY, THAILAND, JAPAN): ");
        String input = context.getScanner().nextLine();
        if (input.isBlank() || input.equalsIgnoreCase("null")) {
            return null;
        }
        try {
            return Country.valueOf(input);

        } catch (IllegalArgumentException e) {
            context.universalOutput("Введите один из параметров: USA, UNITED_KINGDOM, ITALY, THAILAND, JAPAN.");
            return enterPersonNationality(context);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Movie(id=" + id +
                ", name=" + name +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate.format(formatter) +
                ", oscarsCount=" + oscarsCount +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", operator=" + operator + ")";
    }
}