package frc.robot.subsystems.drive;

import frc.helpers.*;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    private MotorController frontLeftMotor = new MotorController("FrontLeftMotor",
            Constants.OperatorConstants.FLdeviceID,
            MotorType.kBrushless,
            Constants.OperatorConstants.FLinverted,
            IdleMode.kBrake);

    private MotorController frontRightMotor = new MotorController("FrontRightMotor",
            Constants.OperatorConstants.FRdeviceID,
            MotorType.kBrushless,
            Constants.OperatorConstants.FRinverted,
            IdleMode.kBrake);

    private MotorController backLeftMotor = new MotorController("BackLeftMotor",
            Constants.OperatorConstants.BLdeviceID,
            MotorType.kBrushless,
            Constants.OperatorConstants.BLinverted,
            IdleMode.kBrake);

    private MotorController backRightMotor = new MotorController("BackRightMotor",
            Constants.OperatorConstants.BRdeviceID,
            MotorType.kBrushless,
            Constants.OperatorConstants.BRinverted,
            IdleMode.kBrake);

    private DifferentialDrive frontDifferentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);
    private DifferentialDrive backDifferentialDrive = new DifferentialDrive(backLeftMotor, backRightMotor); // because
                                                                                                            // motorcontrollergroups
                                                                                                            // are
                                                                                                            // deprecated

    public void differentialDrive(double forward, double turn) {
        frontDifferentialDrive.arcadeDrive(forward, turn);
        backDifferentialDrive.arcadeDrive(forward, turn); // to follow front
    }
}
