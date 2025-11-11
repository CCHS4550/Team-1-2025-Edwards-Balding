package frc.ControlSchemes;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class MechanismScheme
{
    private static CommandXboxController controller;

    public static void configure(Shooter shooter, Intake intake, Indexer indexer, int port)
    {
        controller = new CommandXboxController(port);

        configureButtons(shooter, intake, indexer, port);
    }

    public static void configureButtons(Shooter shooter, Intake intake, Indexer indexer, int port)
    {
        controller.rightTrigger().whileTrue(shooter.shoot());

        controller.leftTrigger().whileTrue(intake.run());

        controller.a().onTrue(indexer.IndexerForward());
        controller.a().onFalse(indexer.IndexStopper());
    }
}