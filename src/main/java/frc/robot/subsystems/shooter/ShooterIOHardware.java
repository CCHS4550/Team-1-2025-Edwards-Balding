package frc.robot.subsystems.shooter;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

//import edu.wpi.first.math.filter.SlewRateLimiter;

import frc.helpers.MotorController;
import frc.robot.Constants;

public class ShooterIOHardware implements ShooterIO{
    
    //Left motor on the left hand side when looking at CAD from front direction (left is inverted)
    private MotorController leftShooterMotor = new MotorController( "leftShooterMotor", 
                                                                    Constants.OperatorConstants.LShooterID, 
                                                                    MotorType.kBrushless, 
                                                                    true, 
                                                                    IdleMode.kBrake);
    
    private MotorController rightShooterMotor = new MotorController("rightShooterMotor", 
                                                                    Constants.OperatorConstants.RShooterID, 
                                                                    MotorType.kBrushless, 
                                                                    false, 
                                                                    IdleMode.kBrake);
    
    
    //private final SlewRateLimiter shooterLimiter = new SlewRateLimiter(1.0);

    @Override
    public void setShooterVoltage(double voltage)
    {
        leftShooterMotor.setVoltage(voltage);
        rightShooterMotor.setVoltage(voltage);
    }

    @Override
    public void setShooterVoltageDefault()
    {
        leftShooterMotor.setVoltage();
        rightShooterMotor.setVoltage();
    }

    @Override
    public void setShooterSpeed(double speed)
    {
        leftShooterMotor.set(speed);
        rightShooterMotor.set(speed);
    }

    @Override
    public void setShooterSpeedDefault()
    {
        this.setShooterSpeed(Constants.OperatorConstants.ShooterSpeed);
    }
}
