package frc.robot;

public final class Constants{
    public static class OperatorConstants{
        //DriveTrain constants
        public static final double voltageConversionFactor = 0;
        public static final int FLdeviceID = 1;
        public static final int FRdeviceID = 2;
        public static final int BLdeviceID = 3;
        public static final int BRdeviceID = 4;

        public static final double driveTrainControllerScalar = 0.5;

        //for intake
        public static final int flywheelMotorID = 61;
        public static final double flywheelVoltageDefault = 12;
        public static final double flywheelSpeedDefault = 0.5;
        
        //Shooter constants
        public static final int LShooterID = 41;
        public static final int RShooterID = 67;
        public static final double ShooterVoltage = 6; //-12.0 to 12.0
        public static final double ShooterSpeed = 0.5; //-1.0 to 1.0, needs testing

        //Indexer
        public static final int IndexShooterID = 14;
        public static final double IndexerOutput = 0.3; //Positive value
    }
}
