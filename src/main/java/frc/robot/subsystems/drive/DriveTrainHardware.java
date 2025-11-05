package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import frc.helpers.MotorController;
import frc.robot.Constants;

public class DriveTrainHardware implements DriveTrainIO
{
    private MotorController frontLeftMotor = new MotorController("FrontLeftMotor", Constants.OperatorConstants.FLdeviceID, MotorType.kBrushless, true, IdleMode.kBrake);
    private MotorController frontRightMotor = new MotorController("FrontRightMotor", Constants.OperatorConstants.FRdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);
    private MotorController backLeftMotor = new MotorController("BackLeftMotor", Constants.OperatorConstants.BLdeviceID, MotorType.kBrushless, true, IdleMode.kBrake);
    private MotorController backRightMotor = new MotorController("BackRightMotor", Constants.OperatorConstants.BRdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);

    private DifferentialDrive leftDifferentialDrive = new DifferentialDrive(frontLeftMotor, backLeftMotor);
    private DifferentialDrive rightDifferentialDrive = new DifferentialDrive(frontRightMotor, backRightMotor);
    /*
    //Set output to each motor
    public DifferentialDrive diffDrive = new DifferentialDrive(
        (double output) -> {
            frontLeftMotor.set(output);
            backLeftMotor.set(output);
        },
        (double output) -> {
            frontRightMotor.set(output);
            backRightMotor.set(output);
        }
    );
    */

    @Override
    public void stopRobot(){
        frontLeftMotor.set(0);
        frontRightMotor.set(0);
        backLeftMotor.set(0);
        backRightMotor.set(0);
    }

    @Override
    public void driveStraight(double speed){
        frontLeftMotor.set(speed);
        backLeftMotor.set(speed); 
        frontRightMotor.set(speed); //Clockwise (maybe)
        backRightMotor.set(speed);
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

    @Override
    public void differentialDrive(double forwardSpeed, double turnSpeed)
    {
        if (turnSpeed > 0.5 || turnSpeed < -0.5)
        {
            frontDifferentialDrive.arcadeDrive(driveTrainControllerScalar*moveSpeed, driveTrainControllerScalar*turnSpeed);
            backDifferentialDrive.arcadeDrive(driveTrainControllerScalar*moveSpeed, driveTrainControllerScalar*turnSpeed);
        }
        else
        {
            frontDifferentialDrive.arcadeDrive(driveTrainControllerScalar*moveSpeed, 0);
            backDifferentialDrive.arcadeDrive(driveTrainControllerScalar*moveSpeed, 0);
        }
    }
}
