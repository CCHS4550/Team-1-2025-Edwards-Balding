package frc.robot.subsystems;

import static edu.wpi.first.wpilibj2.command.Commands.waitSeconds;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.shooter.Shooter;

public class autos {
    //Command moveForward - automatcially drives the robot foward with assigned speed and time
    public static Command moveForward(DriveTrain drivetrain){
        return drivetrain.autoDriveForward(0.5,1);
    }
    //Command turnRight(?)(PS*I added this on Tuesday night,it might be wrong*) - 
    public static Command turnRight(DriveTrain drivetrain){
        return drivetrain.autoDriveTurn(0.5,0.5);
    }
    //Command moveForwardandShoot - drives the robot foward and spins the shooter and feeds balls to the shooter with the index
    public static Command MoveForwardandShoot(DriveTrain drivetrain, Shooter shooter, Indexer indexer){

        // waitSecosnds() - makes the robot wait for a certain amount of time
        // indexer.revForTime - runs the indexer with the given speed and time
        Command feedCycle = Commands.sequence(
            indexer.revForTime(0.1,0.25),
            waitSeconds(1.5),

            indexer.revForTime(0.1,0.25),
            waitSeconds(1.5),

            indexer.revForTime(0.1,0.25),
            waitSeconds(1.5),

            indexer.revForTime(0.1,0.25),
            waitSeconds(1.5),

            indexer.revForTime(0.1,0.25),
            waitSeconds(1.5)
        );

        return Commands.sequence(
            //going to the goal
            drivetrain.autoDriveForward(0.5,1),
            drivetrain.autoDriveTurn(0.5,0.5),
            drivetrain.autoDriveForward(0.5,0.5),

            //fire the shooter
            Commands.deadline(
                shooter.autoShoot(0.3, 5.0),
                feedCycle
            )
        );
            
    }

    public static Command standardAutoRoutine(DriveTrain driveTrain, Shooter shooter, Indexer indexer){
        return MoveForwardandShoot(driveTrain, shooter, indexer);
    }
 }
        
        
