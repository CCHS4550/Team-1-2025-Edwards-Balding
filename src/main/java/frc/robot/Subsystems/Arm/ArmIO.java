package frc.robot.Subsystems.Arm;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;

public interface ArmIO {
    @AutoLog
    public static class ArmIOInputs{
        public boolean armConnected = false;
        public double armPositionRad = 0.0;
        public double armVeloRadPerSec = 0.0;
        public double armVolts = 0.0;
        public double armAmps = 0.0;
    }

    public default void updateInputs(ArmIOInputs inputs){};

    public default void armOpenLoop(double volts){};

    public default void goToAngleRadians(Rotation2d angle, double arbFF){};
}
