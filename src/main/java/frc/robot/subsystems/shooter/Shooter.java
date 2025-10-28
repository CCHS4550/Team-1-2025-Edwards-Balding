package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase{
    public ShooterIO io;

    public Shooter(ShooterIO io)
    {
        this.io = io;
    }

    public void setShooterVoltage(double voltage)
    {
        io.setShooterVoltage(voltage);
    }

    public void setShooterVoltageDefault()
    {
        io.setShooterVoltageDefault();
    }

    public void setShooterSpeed(double speed)
    {
        io.setShooterSpeed(speed);
    }    

    public void setShooterSpeedDefault()
    {
        io.setShooterSpeedDefault();
    }

    public Command shoot()
    {
        return this.runEnd(()->setShooterSpeedDefault(), (()->setShooterVoltage(0)));
    }

    public Command halt()
    {
        return Commands.runOnce(()-> {}, this);
    }
    
}
