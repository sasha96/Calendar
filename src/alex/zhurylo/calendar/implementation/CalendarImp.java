package alex.zhurylo.calendar.implementation;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class CalendarImp {
    private static final String ANSI_RED = "\u001B[31m";
    private static String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static int returnFirstDayOfMonth(String firstDay) {
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

    public static Month returnNameMonth(String nameMonthGivenUser) {
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
        Scanner inputData = new Scanner(System.in);
        Boolean exucute = true;
        LocalDate dayNow = LocalDate.now();
        while (exucute) {
            System.out.println("Write please name month. Example format ( Junuary ; JUNUARY  ; Січень ) ");
            System.out.println("To exit the program, please enter ( EXIT or exit)");
            String userDataEntered = inputData.next();
            if (userDataEntered.equals("EXIT") || userDataEntered.equals("exit")) {
                exucute = false;
            }
            Month nameMonth = returnNameMonth(userDataEntered);
            LocalDate localDate = null;
            if (nameMonth == null) {
                System.out.println("Unfortunately, you entered incorrect data, so the current month is displayed");
                localDate = LocalDate.now();
            } else {
                localDate = LocalDate.of(2018, nameMonth, nameMonth.getValue());
            }
            String nameFirstDayOfMonth = "";
            if (localDate.getMonth() == Month.JANUARY) {
                nameFirstDayOfMonth = String.valueOf(Year.now().atMonth(localDate.getMonth().minus(1).ordinal()).atEndOfMonth().getDayOfWeek().plus(2));
            } else {
                nameFirstDayOfMonth = String.valueOf(Year.now().atMonth(localDate.getMonth().minus(1)).atEndOfMonth().getDayOfWeek());
            }
            int dayCounter = 1;
            int lengthOfMonth = localDate.lengthOfMonth();
            System.out.printf("%15s %d  \n", localDate.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()), localDate.getYear());
            System.out.println("----------------------------");
            System.out.printf("%s %s %s %s %s %s %s\n ", "Пон", "Вт", "Сер", " Чет ", "Пт ", "Сб ", " Нд");
            int firstDayofWeek = returnFirstDayOfMonth(nameFirstDayOfMonth);
            for (int space = 0; space < firstDayofWeek; space++) {
                System.out.printf("%4s", "    ");
            }
            dayCounter += firstDayofWeek - 1;
            for (int i = 1; i <= lengthOfMonth; i++) {
                dayCounter++;
                if (dayNow.getDayOfMonth() == i && dayNow.getMonth() == localDate.getMonth()) {
                    System.out.print(ANSI_BLUE);
                }
                System.out.print(ANSI_RESET);
                if (dayCounter % 7 == 0 && i == 1) {
                    System.out.print(ANSI_RED);
                    System.out.printf("%- 1d\n", i);
                    dayCounter--;
                }
                else if (dayCounter % 6 == 0) {
                    System.out.print(ANSI_RED);
                    System.out.printf("%- 4d", i);
                    if (i != lengthOfMonth)
                        System.out.printf("%- 4d\n", ++i);
                    System.out.print(ANSI_RESET);
                } else {
                    System.out.print(ANSI_RESET);
                    System.out.printf("%-4d", i);
                }
            }
            System.out.println();
            System.out.println("You can write another mounth ");
        }
    }
}
