package frc.robot.subsystems.shooter;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.filter.SlewRateLimiter;
import frc.helpers.MotorController;
import frc.robot.Constants;

public class Shooter{
    //Left motor on the left hand side when looking at CAD from front direction (left is inverted)
    private MotorController leftShooterMotor = new MotorController("leftShooterMotor", Constants.OperatorConstants.LShooterID, MotorType.kBrushless, true, IdleMode.kCoast);
    private MotorController rightShooterMotor = new MotorController("leftShooterMotor", Constants.OperatorConstants.LShooterID, MotorType.kBrushless, false, IdleMode.kCoast);

    private final SlewRateLimiter shooterLimiter = new SlewRateLimiter(1.0);

    public void setShooterSpeed(double speed)
    {
        
    }
}
