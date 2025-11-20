// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.ControlSchemes.driveScheme;
import frc.ControlSchemes.MechanismScheme;
import frc.robot.Constants.OperatorConstants;

import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.subsystems.drive.DriveTrainIO;
import frc.robot.subsystems.drive.DriveTrainHardware;

import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIO;
import frc.robot.subsystems.intake.IntakeIOHardware;

import frc.robot.subsystems.indexer.*; //geeked vs locked in

import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterIO;
import frc.robot.subsystems.shooter.ShooterIOHardware;

import frc.robot.subsystems.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer{
  // The robot's subsystems and commands are defined here...
  private final driveScheme driveScheme = new driveScheme();
  private final DriveTrainIO drivetrainHardware = new DriveTrainHardware();
  private final DriveTrain driveTrain = new DriveTrain(drivetrainHardware);

  private final MechanismScheme mechanismScheme = new MechanismScheme();
  private final IntakeIO intakeHardware = new IntakeIOHardware();
  private final Intake intake = new Intake(intakeHardware);
  private final ShooterIO shooterHardware = new ShooterIOHardware();
  private final Shooter shooter = new Shooter(shooterHardware);
  private final IndexerIO indexerHardware = new IndexerHardware();
  private final Indexer indexer = new Indexer(indexerHardware);


  public RobotContainer(){
    driveScheme.configure(driveTrain, 0);
    mechanismScheme.configure(shooter, intake, indexer, 0);
  }

  public Command getAutonomousCommand()
  {
    return autos.standardAutoRoutine(driveTrain, shooter, indexer);
  }
  
}