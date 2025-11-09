package frc.robot.subsystems.drive;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.helpers.MotorController;
import frc.robot.Constants;

public class DriveTrainHardware implements DriveTrainIO
{
    private MotorController frontLeftMotor = new MotorController("FrontLeftMotor", Constants.OperatorConstants.FLdeviceID, MotorType.kBrushless, true, IdleMode.kBrake); //invert 2 left motors
    private MotorController frontRightMotor = new MotorController("FrontRightMotor", Constants.OperatorConstants.FRdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);
    private MotorController backLeftMotor = new MotorController("BackLeftMotor", Constants.OperatorConstants.BLdeviceID, MotorType.kBrushless, true, IdleMode.kBrake);
    private MotorController backRightMotor = new MotorController("BackRightMotor", Constants.OperatorConstants.BRdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);

    private DifferentialDrive frontDifferentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);
    private DifferentialDrive backDifferentialDrive = new DifferentialDrive(backLeftMotor, backRightMotor);

    @Override
    public void stopRobot(){
        frontLeftMotor.set(0);
        frontRightMotor.set(0);
        backLeftMotor.set(0);
        backRightMotor.set(0);
    }

    @Override
    public void differentialDrive(double forwardSpeed, double turnSpeed)
    {
        if (turnSpeed > 0.5 || turnSpeed < -0.5)
        {
            frontDifferentialDrive.arcadeDrive(Constants.OperatorConstants.driveTrainControllerScalar*forwardSpeed, Constants.OperatorConstants.driveTrainControllerScalar*turnSpeed);
            backDifferentialDrive.arcadeDrive(Constants.OperatorConstants.driveTrainControllerScalar*forwardSpeed, Constants.OperatorConstants.driveTrainControllerScalar*turnSpeed);
        }
        else
        {
            frontDifferentialDrive.arcadeDrive(Constants.OperatorConstants.driveTrainControllerScalar*forwardSpeed, 0);
            backDifferentialDrive.arcadeDrive(Constants.OperatorConstants.driveTrainControllerScalar*forwardSpeed, 0);
        }
    }
}
/*
    @Override
    public void turnDirection(String direction, double speed){ //aarush is a DUMBASS <--------------------
        if(direction.equals("Left")){
            frontLeftMotor.set(speed);
            frontRightMotor.set(speed);
            backLeftMotor.set(speed);
            backRightMotor.set(speed);
        }else if(direction.equals("Right")){
            frontLeftMotor.set(-speed);
            frontRightMotor.set(-speed);
            backLeftMotor.set(-speed);
            backRightMotor.set(-speed);
        }else{
            System.out.println("Invalid direction: "+direction)
        }
    }
        */