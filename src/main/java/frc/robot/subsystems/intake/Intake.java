package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    public IntakeIO io;

    public Intake(IntakeIO io)
    {
        this.io = io;
    }

    public void setFlywheelVoltage(double voltage)
    {
        io.setFlywheelVoltage(voltage);
    }

    public void setFlywheelSpeed(double speed)
    {
        io.setFlywheelSpeed(speed);
    }

    public void setFlywheelVoltageDefault()
    {
        io.setFlywheelVoltageDefault();
    }

    public void setFlywheelSpeedDefault()
    {
        io.setFlywheelSpeedDefault();
    }

    public Command run()
    {
        return this.runEnd(()->setFlywheelSpeedDefault(), (()->setFlywheelVoltage(0)));
    }

    public Command halt()
    {
        return Commands.runOnce(()-> {}, this);
    }
}
