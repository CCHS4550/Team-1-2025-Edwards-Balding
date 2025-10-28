package frc.robot.subsystems.shooter;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.helpers.MotorController;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    //Left motor on the left hand side when looking at CAD from front direction (left is inverted)
    private MotorController leftShooterMotor = new MotorController("leftShooterMotor", Constants.OperatorConstants.LShooterID, MotorType.kBrushless, true, IdleMode.kBrake);
    private MotorController rightShooterMotor = new MotorController("leftShooterMotor", Constants.OperatorConstants.RShooterID, MotorType.kBrushless, false, IdleMode.kBrake);

    //private final SlewRateLimiter shooterLimiter = new SlewRateLimiter(1.0);

    public void setShooterVoltage(double voltage)
    {
        leftShooterMotor.setVoltage(voltage);
        rightShooterMotor.setVoltage(voltage);
    }

    public void setShooterVoltageDefault()
    {
        leftShooterMotor.setVoltage();
        rightShooterMotor.setVoltage();
    }

    public void setShooterSpeed(double speed)
    {
        leftShooterMotor.set(speed);
        rightShooterMotor.set(speed);
    }

    public void setShooterSpeedDefault()
    {
        this.setShooterSpeed(Constants.OperatorConstants.ShooterSpeed);
    }

    public Command shoot()
    {
        return this.runEnd(()->setShooterSpeedDefault(), (()->setShooterVoltage(0)));
    }

    public Command halt(){
        return Commands.runOnce(()-> {}, this);
    }
}
