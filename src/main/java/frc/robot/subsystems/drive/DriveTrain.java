package frc.robot.subsystems.drive;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.helpers.MotorController;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    public DriveTrainIO io;

    public void stopRobot(){
        io.stopRobot();
    }

    public void driveStraight(double speed){
        io.driveStraight(speed);
    }

    public void turnDirection(String direction, double speed){
        io.turnDirection(direction,speed)
    }

    public void teleDrive(double moveSpeed, double turnSpeed){
        io.teleDrive(moveSpeed,turnSpeed)
    }

    public Command basicDrive(double speed){ //Drive straight while executed by command scheduler
        return this.runEnd(
            () -> driveStraight(speed), 
            () -> stopRobot());
    }

    public Command basicTurn(String direction, double speed){
        return this.runEnd(
            () -> differentialDrive(direction, speed), 
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
