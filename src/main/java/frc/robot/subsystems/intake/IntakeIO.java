package frc.robot.subsystems.intake;

public interface IntakeIO
{
    public default void setFlywheelVoltage(double voltage) {}
    
    public default void setFlywheelSpeed(double speed) {}

    public default void setFlywheelVoltageDefault() {}

    public default void setFlywheelSpeedDefault() {}

}