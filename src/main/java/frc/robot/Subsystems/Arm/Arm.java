package frc.robot.Subsystems.Arm;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase{
    private final ArmIO io;

    private final ArmIOInnputsAutologged inputs = new ArmIOInnputsAutologged();

    private final Alert ArmDCAlert;

    private ArmFeedforward armFF = new ArmFeedforward(0, 0, 0, 0);

    private TrapezoidProfile.Constraints constraints = new Constraints(0, 0);
    private TrapezoidProfile profile = new TrapezoidProfile(constraints);

    private TrapezoidProfile.State state = new State();

    private TrapezoidProfile.State goal = new State();

    public enum wantedState{
        NINETY
    }

    public enum systemState{
        NINETY
    }

    wantedState WantedState = wantedState.NINETY;
    systemState SystemState = systemState.NINETY;

    public Arm(ArmIO io){
        this.io = io;
    }
    @Override
    public void periodic() {
       io.updateInputs(inputs);

    }

    private systemState handleStateTransitions(){
        return switch (WantedState) {
            case NINETY -> systemState.NINETY;
        };
    }

    private void applyStates(){
        switch(SystemState){
            case NINETY:
                
        }
    }

    private void goToNinety(){
        state = profile.calculate(0.02, state, goal);

        double arbFF = armFF.calculate(state.position, state.velocity);

        io.goToAngleRadians(Rotation2d.fromRadians(state.position), arbFF);
    }
    
}
