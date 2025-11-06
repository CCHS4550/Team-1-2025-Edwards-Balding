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
        io.differentialDrive(speed, 0);
    }

    public Command basicDrive(double speed){ //Drive straight while executed by command scheduler
        return this.runEnd(
            () -> driveStraight(speed), 
            () -> stopRobot());
    }

    public Command autoDriveForward(double speed, double time){
        return deadline(
            waitSeconds(time), 
            basicDrive(speed, 0)
        ).withTimeout(time);
    }

    public Command autoDriveTurn(double speed, boolean turn, double time){
        return deadline(
            waitSeconds(time), 
            differentialDrive(0, turn)
        ).withTimeout(time);
    }
}
