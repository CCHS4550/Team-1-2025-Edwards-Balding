package frc.robot.Subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Drivetrain.DriveIO.DriveIOInputs;

public class Drive extends SubsystemBase{
    private final DriveIO io;
    private final DriveIOInputsAutologged inputs = new DriveIOInputsAutologged();

    public double xspeed =0;
    public double zrotation = 0;

    private Alert frontLeftDCalert;

    public enum wantedDriveState{
        ARCADE,
        DRIVEFORWARD,
        TURNLEFT,
        TURNRIGHT,
    }

    public enum driveState{
        ARCADE,
        DRIVEFORWARD,
        TURNLEFT,
        TURNRIGHT,
    }

    public final DifferentialDrive diffDrive;

    wantedDriveState wantedState = wantedDriveState.ARCADE;
    driveState systemState = driveState.ARCADE;

    public Drive(DriveIO io){
        this.io = io;

        diffDrive = new DifferentialDrive(
        (double output) -> {
            io.setFrontLeftOpenLoop(output);
            io.setBackLeftOpenLoop(output);
            },
        (double output) -> {
            io.setFrontRightOpenLoop(output);
            io.setBackRightOpenLoop(output);
        });

        frontLeftDCalert = new Alert("its disconnected dumbass", AlertType.kError);
    }

    @Override
    public void periodic(){
        io.updateInputs(inputs);

        if(DriverStation.isDisabled()){
            io.setFrontLeftOpenLoop(0);
            io.setBackLeftOpenLoop(0);
            io.setFrontRightOpenLoop(0);
            io.setBackRightOpenLoop(0);
        }
        systemState = handleStateTransistions();
        applyStates();

        frontLeftDCalert.set(!inputs.frontLeftConnected);
    }

    public driveState handleStateTransistions(){
        return switch(wantedState){
            case ARCADE -> driveState.ARCADE;
            case DRIVEFORWARD -> driveState.DRIVEFORWARD;
            case TURNLEFT -> driveState.TURNLEFT;
            case TURNRIGHT -> driveState.TURNRIGHT;
        };
    }

    public void applyStates(){
        switch(systemState){
            case ARCADE:
                diffDrive.arcadeDrive(xspeed, zrotation);
            case DRIVEFORWARD:
                io.setFrontLeftOpenLoop(3);
                io.setBackLeftOpenLoop(3);
                io.setFrontRightOpenLoop(3);
                io.setBackRightOpenLoop(3);
            case TURNLEFT:
                io.setFrontLeftOpenLoop(-3);
                io.setBackLeftOpenLoop(-3);
                io.setFrontRightOpenLoop(3);
                io.setBackRightOpenLoop(3);
            case TURNRIGHT:
            io.setFrontLeftOpenLoop(3);
            io.setBackLeftOpenLoop(3);
            io.setFrontRightOpenLoop(-3);
            io.setBackRightOpenLoop(-3);
        }
    }
    
}
