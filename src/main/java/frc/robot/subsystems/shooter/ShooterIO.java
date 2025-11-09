package frc.robot.subsystems.shooter;

public interface ShooterIO {

    //public default void 
    public default void setShooterVoltage(double voltage) {}

    public default void setShooterVoltageDefault() {} 

    public default void setShooterSpeed(double speed) {}

    public default void setShooterSpeedDefault() {}
}