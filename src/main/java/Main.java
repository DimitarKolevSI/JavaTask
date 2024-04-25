import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<CarSpeedInformation> carSpeedInformations = new ArrayList<>();

    private static void loadCarSpeedInformation(String fileName) throws FileNotFoundException {
        File measurementsFile = new File(fileName);
        Scanner fileScanner = new Scanner(measurementsFile);
        while (fileScanner.hasNextLine()) {
            carSpeedInformations.add(CarSpeedInformation.createCarSpeedInformationFrom(fileScanner.nextLine()));
        }
        fileScanner.close();
    }

    private static String solveExerciseTwo() {
        return String.format("Exercise 2.\n" +
                "The data of %d vehicles were recorded in the measurement.\n", carSpeedInformations.size());
    }

    private static String solveExerciseThree() {
        long numberOfCarsExited = carSpeedInformations.stream()
                .filter(carSpeedInformation -> carSpeedInformation.exitHours < 9)
                .count();
        return String.format("Exercise 3.\n" +
                "Before 9 o'clock %d vehicles passed the exit point recorder.\n", numberOfCarsExited);
    }

    private static String solveExerciseFour() {
        Scanner input = new Scanner(System.in);
        System.out.print("Hours: ");
        int hours = input.nextInt();
        System.out.print("Minutes: ");
        int minutes = input.nextInt();
        long numberOfCarsEntered = carSpeedInformations.stream()
                .filter(information -> information.enterHours == hours && information.enterMinutes == minutes)
                .count();
        long numbersInTheSection = carSpeedInformations.stream()
                .filter(information -> information.enterHours <= hours && information.enterMinutes <= minutes &&
                        information.exitHours >= hours && information.exitMinutes >= minutes)
                .count();
        return String.format("Exercise 4.\n" +
                "a. The number of vehicle that passed the entry point recorder: %d\n" +
                "b. The traffic intensity: %.2f\n", numberOfCarsEntered, (double)numbersInTheSection / 10);
    }

    private static String solveExerciseFive() {
        String licenseNumber = "";
        double highestSpeed = -1;
        CarSpeedInformation fastestCar = new CarSpeedInformation();
        for (CarSpeedInformation carSpeedInformation: carSpeedInformations) {
            if (carSpeedInformation.getAverageSpeed() > highestSpeed) {
                highestSpeed = carSpeedInformation.getAverageSpeed();
                licenseNumber = carSpeedInformation.plateNumber;
                fastestCar = carSpeedInformation;
            }
        }
        CarSpeedInformation finalFastestCar = fastestCar;
        long numberOfOvertakenCars = carSpeedInformations.stream()
                .filter(carInformation -> carInformation.isCarOvertakenBy(finalFastestCar))
                .count();
        return String.format("Exercise 5.\n" +
                "The data of the vehicle with the highest speed are\n" +
                "license plate number: %s\n" +
                "average speed: %d km/h\n" +
                "number of overtaken vehicles: %d\n", licenseNumber, (int) highestSpeed, numberOfOvertakenCars);
    }

    private static String solveExerciseSix() {
        long numberOfSpeedingCars = carSpeedInformations.stream()
                .filter(carInformation -> carInformation.getAverageSpeed() > 90)
                .count();
        return String.format("Exercise 6.\n" +
                "%.2f%% percent of the vehicles were speeding.\n", (double) numberOfSpeedingCars / carSpeedInformations.size() * 100);
    }

    public static void main(String[] args) throws IOException {
        loadCarSpeedInformation("src/main/java/resources/measurements.txt");
        String fileName = "src/main/java/resources/result.txt";
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(solveExerciseTwo());
        printWriter.println(solveExerciseThree());
        printWriter.println(solveExerciseFour());
        printWriter.println(solveExerciseFive());
        printWriter.println(solveExerciseSix());
        printWriter.close();
    }
}