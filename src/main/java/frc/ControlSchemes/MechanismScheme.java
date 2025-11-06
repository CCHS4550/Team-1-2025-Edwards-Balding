package frc.robot.controlschemes;

import frc.helpers.ControlScheme;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.helpers.ControlScheme;
import frc.robot.Constants;
import frc.robot.subsystems.Door;
import frc.robot.subsystems.DriveTrain;

public class MechanismScheme implements ControlScheme
{
    private static CommandXboxController controller;

    public static void configure(DriveTrain drivetrain, int port)
    {
        controller = new CommandXboxController(port);

        configureButtons(drivetrain, port);
    }

    public static void configureButtons(DriveTrain drivetrain, int port)
    {
        //RunCommand that checks if right trigger is pressed and fires shooter flywheels
        
        //RunCommand that checks if left trigger is pressed and fires intake motors

    }
}