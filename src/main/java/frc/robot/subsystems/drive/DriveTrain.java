package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    public DriveTrainIO io;

    public void stopRobot(){
        io.stopRobot();
    }

    public void driveStraight(double speed){
        io.differentialDrive(speed, 0);
    }

    public void drive(double speed, double turn)
    {
        io.differentialDrive(speed, turn);
    }

    public Command basicDrive(double speed){ //Drive straight while executed by command scheduler
        return this.runEnd(
            () -> driveStraight(speed), 
            () -> stopRobot());
    }

    public Command basicTurn(double turn)
    {
        return this.runEnd(() -> io.differentialDrive(0, turn), () -> stopRobot());
    }

    public Command autoDriveForward(double speed, double time){
        return Commands.deadline(
            Commands.waitSeconds(time), 
            basicDrive(speed)
        ).withTimeout(time);
    }

    public Command autoDriveTurn(double turn, double time){
        return Commands.deadline(
            Commands.waitSeconds(time), 
            basicTurn(turn)
        ).withTimeout(time);
    }
}
