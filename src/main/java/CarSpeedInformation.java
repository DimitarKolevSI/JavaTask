import java.util.List;

public class CarSpeedInformation {
    String plateNumber;
    int enterHours;
    int enterMinutes;
    int enterSeconds;
    int enterMilliseconds;
    int exitHours;
    int exitMinutes;
    int exitSeconds;
    int exitMilliseconds;

    public double getAverageSpeed() {
        double enterTime = enterHours + (enterMinutes * 60 + enterSeconds + (double) enterMilliseconds / 1000) / 3600;
        double exitTime = exitHours + (exitMinutes * 60 + exitSeconds + (double) exitMilliseconds / 1000) / 3600;
        return 1 / (exitTime - enterTime) * 10;
    }

    public boolean isCarOvertakenBy(CarSpeedInformation carSpeedInformation) {
        if (carSpeedInformation.enterHours < enterHours) {
            return false;
        }
        if (carSpeedInformation.enterHours == enterHours && carSpeedInformation.enterMinutes < enterMinutes) {
            return false;
        }
        if (carSpeedInformation.enterHours == enterHours && carSpeedInformation.enterMinutes == enterMinutes &&
                carSpeedInformation.enterSeconds < enterSeconds) {
            return false;
        }
        if (carSpeedInformation.enterHours == enterHours && carSpeedInformation.enterMinutes == enterMinutes &&
                carSpeedInformation.enterSeconds == enterSeconds && carSpeedInformation.enterMilliseconds < enterMilliseconds) {
            return false;
        }

        return carSpeedInformation.exitHours < exitHours ||
                (carSpeedInformation.exitHours == exitHours && carSpeedInformation.exitMinutes < exitMinutes) ||
                (carSpeedInformation.exitHours == exitHours && carSpeedInformation.exitMinutes == exitMinutes && carSpeedInformation.exitSeconds < exitSeconds) ||
                (carSpeedInformation.exitHours == exitHours && carSpeedInformation.exitMinutes == exitMinutes && carSpeedInformation.exitSeconds == exitSeconds && carSpeedInformation.exitMilliseconds < exitMilliseconds);
    }

    public static CarSpeedInformation createCarSpeedInformationFrom(String textFileLine) {
        String[] numbers = textFileLine.split(" ");
        CarSpeedInformation speedInformation = new CarSpeedInformation();
        speedInformation.plateNumber = numbers[0];
        speedInformation.enterHours = Integer.parseInt(numbers[1]);
        speedInformation.enterMinutes = Integer.parseInt(numbers[2]);
        speedInformation.enterSeconds = Integer.parseInt(numbers[3]);
        speedInformation.enterMilliseconds = Integer.parseInt(numbers[4]);
        speedInformation.exitHours = Integer.parseInt(numbers[5]);
        speedInformation.exitMinutes = Integer.parseInt(numbers[6]);
        speedInformation.exitSeconds = Integer.parseInt(numbers[7]);
        speedInformation.exitMilliseconds = Integer.parseInt(numbers[8]);
        return speedInformation;
    }
}
