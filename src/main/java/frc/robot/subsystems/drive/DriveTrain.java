package frc.robot.subsystems.drive;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.helpers.MotorController;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    private MotorController frontLeftMotor = new MotorController("FrontLeftMotor", Constants.OperatorConstants.FLdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);
    private MotorController frontRightMotor = new MotorController("FrontRightMotor", Constants.OperatorConstants.FRdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);
    private MotorController backLeftMotor = new MotorController("BackLeftMotor", Constants.OperatorConstants.BLdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);
    private MotorController backRightMotor = new MotorController("BackRightMotor", Constants.OperatorConstants.BRdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);

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

    public void stopRobot(){
        frontLeft.set(0);
        frontRight.set(0);
        backLeft.set(0);
        backRight.set(0);
    }

    public void driveStraight(double speed){
        frontLeftMotor.set(-speed);
        backLeftMotor.set(-speed); //Anticlockwise - so all wheels go forward 
        frontRightMotor.set(speed); //Clockwise
        backRightMotor.set(speed);
    }

    public void turnDirection(String direction, double speed){
        if(direction.equals("Left")){
            frontLeft.set(speed);
            frontRight.set(speed);
            backLeft.set(speed);
            backRight.set(speed);
        }else if(direction.equals("Right")){
            frontLeft.set(-speed);
            frontRight.set(-speed);
            backLeft.set(-speed);
            backRight.set(-speed);
        }else{
            System.out.println("Invalid direction: "+direction)
        }
    }

    public void teleDrive(double moveSpeed, double turnSpeed){
        diffDrive.arcadeDrive(moveSpeed, turnSpeed);
    }

    public Command basicDrive(double speed){ //Drive straight while executed by command scheduler
        return this.runEnd(
            () -> driveStraight(speed), 
            () -> stopRobot());
    }

    public Command basicTurn(String direction, double speed){
        return this.runEnd(
            () -> turnDirection(direction, speed), 
            () -> stopRobot());
    }

    public Command controlledDrive(double moveSpeed, double turnSpeed){
        return run(() -> teleDrive(moveSpeed, turnSpeed));
    }

    public Command autoDriveForward(double speed, double time){
        return deadline(
            waitSeconds(time), 
            basicDrive(speed)
        ).withTimeout(time);
    }

    public Command autoDriveTurn(double speed, boolean turn, double time){
        return deadline(
            waitSeconds(time), 
            basicTurn(turn, speed)
        ).withTimeout(time);
    }
}
