import java.util.Scanner;


class BreadPriceException extends Exception {
    public BreadPriceException(String message) {
        super(message);
    }
}


class InvalidMonthException extends Exception {
    public InvalidMonthException(String message) {
        super(message);
    }
}


class DayOutOfRangeException extends Exception {
    public DayOutOfRangeException(String message) {
        super(message);
    }
}

public class MainApp {


    public static void buyBread(double price) throws BreadPriceException {
        if (price > 8) {
            throw new BreadPriceException("Хлеб слишком дорогой");
        }
        System.out.println("Хлеб куплен по цене: " + price);
    }


    public static void buyFood(double breadPrice) {
        try {
            buyBread(breadPrice);
        } catch (BreadPriceException e) {
            System.out.println(e.getMessage());
        }
    }


    static class DayInMonthValidator {
        public static void validate(int month, int day) throws InvalidMonthException, DayOutOfRangeException {
            if (month < 1 || month > 12) {
                throw new InvalidMonthException("Номер месяца невалидный: " + month);
            }

            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (day <= 0 || day > daysInMonth[month - 1]) {
                throw new DayOutOfRangeException(
                        "Неверное количество дней: " + day + " для месяца: " + month +
                                ". Допустимое значение в диапазоне от 1 до " + daysInMonth[month - 1]
                );
            }
            System.out.println("Дата валидна: " + day + " день " + month + " месяца");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Введите цену хлеба (в евро):");
        double breadPrice = scanner.nextDouble();
        buyFood(breadPrice);


        System.out.println("Введите номер месяца:");
        int month = scanner.nextInt();
        System.out.println("Введите число месяца:");
        int day = scanner.nextInt();

        try {
            DayInMonthValidator.validate(month, day);
        } catch (InvalidMonthException | DayOutOfRangeException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
