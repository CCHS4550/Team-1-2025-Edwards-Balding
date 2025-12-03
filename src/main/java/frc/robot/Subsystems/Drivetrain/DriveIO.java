package frc.robot.Subsystems.Drivetrain;
import org.littletonrobotics.junction.AutoLog;

import org.littletonrobotics.junction.AutoLog;

public interface DriveIO {
    @AutoLog
    public static class DriveIOInputs{
        public boolean frontLeftConnected = false;
        public double frontLeftPositionRad = 0.0;
        public double frontLeftVeloRadPerSec = 0.0;
        public double frontLeftVolts = 0.0;
        public double frontLeftAmps = 0.0;

        public boolean backLeftConnected = false;
        public double backLeftPositionRad = 0.0;
        public double backLeftVeloRadPerSec = 0.0;
        public double backLeftVolts = 0.0;
        public double backLeftAmps = 0.0;

        public boolean frontRightConnected = false;
        public double frontRightPositionRad = 0.0;
        public double frontRightVeloRadPerSec = 0.0;
        public double frontRightVolts = 0.0;
        public double frontRightAmps = 0.0;

        public boolean backRightConnected = false;
        public double backRightPositionRad = 0.0;
        public double backRightVeloRadPerSec = 0.0;
        public double backRightVolts = 0.0;
        public double backRightAmps = 0.0;
    }

    public default void updateInputs(DriveIOInputs inputs){};

    public default void setFrontLeftOpenLoop(double volts){};

    public default void setBackLeftOpenLoop(double volts){};

    public default void setFrontRightOpenLoop(double volts){};

    public default void setBackRightOpenLoop(double volts){};
    
    
} 