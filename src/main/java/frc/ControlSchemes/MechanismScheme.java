package frc.ControlSchemes;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.shooter.Shooter;

public class MechanismScheme {
    private static CommandXboxController controller;

    public void configure(Shooter shooter, Indexer indexer, int port) {
        controller = new CommandXboxController(port);

        configureButtons(shooter, indexer, port);
    }

    public static void configureButtons(Shooter shooter, Indexer indexer, int port) {
        RunCommand runShooter = new RunCommand(() -> {
            shooter.shoot();
        }, shooter);
        shooter.setDefaultCommand(runShooter);

        /*
         * controller.leftTrigger().whileTrue(intake.run());
         * controller.leftTrigger().whileFalse(intake.halt());
         */

        controller.a().onTrue(indexer.IndexerForward());
        controller.a().onFalse(indexer.IndexStopper());
    }
}