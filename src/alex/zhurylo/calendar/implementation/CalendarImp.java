package alex.zhurylo.calendar.implementation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class CalendarImp {
    private static final String COLOR_RED = "\u001B[31m";
    private static String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_BLUE = "\u001B[34m";
    private static final String EXIT_PROGRAMME_LOWER_CASE = "exit";
    private static final String EXIT_PROGRAMME_UPPER_CASE = "EXIT";
    private static final Integer YEAR_NOW = 2018;
    private static final TextStyle STYLE_OF_NAME = TextStyle.FULL_STANDALONE;
    private static final String MONDEY = "Пн ";
    private static final String TUESDAY = "Вт ";
    private static final String WEDNESDAY = "Ср ";
    private static final String THURSDAY = "Чт ";
    private static final String FRIDAY = "Пт ";
    private static final String SATURDAY = " Сб ";
    private static final String SUNDAY = "Нд";
    private static final Scanner SCANNER_DATA = new Scanner(System.in);

    private static int returnFirstDayOfMonth(String firstDay) {
        int firstDayOfMonth = 0;
        switch (firstDay) {
            case ("SUNDAY"):
                firstDayOfMonth = 0;
                break;
            case ("MONDAY"):
                firstDayOfMonth = 1;
                break;
            case ("TUESDAY"):
                firstDayOfMonth = 2;
                break;
            case ("WEDNESDAY"):
                firstDayOfMonth = 3;
                break;
            case ("THURSDAY"):
                firstDayOfMonth = 4;
                break;
            case ("FRIDAY"):
                firstDayOfMonth = 5;
                break;
            case ("SATURDAY"):
                firstDayOfMonth = 6;
                break;
        }
        return firstDayOfMonth;
    }

    private static DayOfWeek returnNameDate(String firstDay) {
        DayOfWeek dayOfWeek;
        switch (firstDay) {
            case ("SUNDAY"):
                dayOfWeek = DayOfWeek.SUNDAY;
                break;
            case ("MONDAY"):
                dayOfWeek = DayOfWeek.MONDAY;
                break;
            case ("TUESDAY"):
                dayOfWeek = DayOfWeek.TUESDAY;
                break;
            case ("WEDNESDAY"):
                dayOfWeek = DayOfWeek.WEDNESDAY;
                break;
            case ("THURSDAY"):
                dayOfWeek = DayOfWeek.THURSDAY;
                break;
            case ("FRIDAY"):
                dayOfWeek = DayOfWeek.FRIDAY;
                break;
            default:
                dayOfWeek = DayOfWeek.SATURDAY;
                break;
        }
        return dayOfWeek;
    }

    private static Month returnNameMonth(String nameMonthGivenUser) {
        Month nameMonth = null;
        switch (nameMonthGivenUser) {
            case ("Junuary"):
            case ("JUNUARY"):
            case ("Січень"):
                nameMonth = Month.JANUARY;
                break;
            case ("February"):
            case ("FEBRUARY"):
            case ("Лютий"):
                nameMonth = Month.FEBRUARY;
                break;
            case ("March"):
            case ("MARCH"):
            case ("Березень"):
                nameMonth = Month.MARCH;
                break;
            case ("April"):
            case ("APRIL"):
            case ("Квітень"):
                nameMonth = Month.APRIL;
                break;
            case ("May"):
            case ("MAY"):
            case ("Травень"):
                nameMonth = Month.MAY;
                break;
            case ("June"):
            case ("JUNE"):
            case ("Червень"):
                nameMonth = Month.JUNE;
                break;
            case ("July"):
            case ("JULY"):
            case ("Липень"):
                nameMonth = Month.JULY;
                break;
            case ("Augest"):
            case ("AUGUST"):
            case ("Серпень"):
                nameMonth = Month.AUGUST;
                break;
            case ("September"):
            case ("SEPTEMBER"):
            case ("Вересень"):
                nameMonth = Month.SEPTEMBER;
                break;
            case ("October"):
            case ("OCTOBER"):
            case ("Жовтень"):
                nameMonth = Month.OCTOBER;
                break;
            case ("November"):
            case ("NOVEMBER"):
            case ("Листопад"):
                nameMonth = Month.NOVEMBER;
                break;
            case ("December"):
            case ("DECEMBER"):
            case ("Грудень"):
                nameMonth = Month.DECEMBER;
                break;

        }
        return nameMonth;
    }

    public static void startApplacation() {

        Boolean exucute = true;
        LocalDate dayNow = LocalDate.now();
        Integer dateOfMonth;
        Integer yearNow;
        LocalDate localDate;
        Locale languageLocale = Locale.getDefault();
        int dayCounter = 1;
        while (exucute) {
            System.out.println("Write please name month. Example format ( Junuary ; JUNUARY  ; Січень ) ");
            System.out.println("To exit the program, please enter ( EXIT or exit)");
            String userDataEntered = SCANNER_DATA.next();
            if (userDataEntered.equals(EXIT_PROGRAMME_UPPER_CASE) || userDataEntered.equals(EXIT_PROGRAMME_LOWER_CASE)) {
                exucute = false;
            }
            Month nameMonth = returnNameMonth(userDataEntered);
            if (nameMonth == null) {
                System.out.println("Unfortunately, you entered incorrect data, so the current month is displayed");
                localDate = LocalDate.now();
            } else {
                dateOfMonth = nameMonth.getValue();
                localDate = LocalDate.of(YEAR_NOW, nameMonth, dateOfMonth);
            }
            yearNow = localDate.getYear();
            String nameFirstDayOfMonth;
            if (localDate.getMonth() == Month.JANUARY) {
                nameFirstDayOfMonth = String.valueOf(Year.now().atMonth(localDate.getMonth().minus(1).ordinal()).atEndOfMonth().getDayOfWeek().plus(2));
            } else {
                nameFirstDayOfMonth = String.valueOf(Year.now().atMonth(localDate.getMonth().minus(1)).atEndOfMonth().getDayOfWeek());
            }
            DayOfWeek dayOfWeek = returnNameDate(nameFirstDayOfMonth);
            int lengthOfMonth = localDate.lengthOfMonth();
            System.out.printf("%15s %d  \n", localDate.getMonth().getDisplayName(STYLE_OF_NAME, languageLocale), yearNow);
            System.out.println("----------------------------");
            System.out.printf("%s %s %s %s %s %s %s\n ", MONDEY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY);
            int firstDayofWeek = returnFirstDayOfMonth(nameFirstDayOfMonth);
            for (int space = 0; space < firstDayofWeek; space++) {
                System.out.printf("%4s", "    ");
            }
            dayCounter += firstDayofWeek - 1;
            for (int i = 1; i <= lengthOfMonth; i++) {
                dayCounter++;
                if (dayNow.getDayOfMonth() == i && dayNow.getMonth() == localDate.getMonth()) {
                    System.out.print(COLOR_BLUE);
                }
                System.out.print(COLOR_RESET);
                if (dayOfWeek.plus(i) == DayOfWeek.SUNDAY && i == 1) {
                    System.out.print(COLOR_RED);
                    System.out.printf("%- 1d\n", i);
                    dayCounter--;
                } else if (dayOfWeek.plus(i) == DayOfWeek.SATURDAY ) {
                    System.out.print(COLOR_RED);
                    System.out.printf("%- 4d", i);
                    if (i != lengthOfMonth)
                        System.out.printf("%- 4d\n", ++i);
                    System.out.print(COLOR_RESET);
                } else {
                    System.out.print(COLOR_RESET);
                    System.out.printf("%-4d", i);
                }
            }
            System.out.println();
            System.out.println("You can write another mounth ");
        }
    }
}
