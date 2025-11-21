package frc.robot.subsystems.drive;

public interface DriveTrainIO {
    public default void stopRobot() {
    }

    public default void driveStraight(double speed) {
    }

    public default void differentialDrive(double forwardSpeed, double turnSpeed) {
    }
}
