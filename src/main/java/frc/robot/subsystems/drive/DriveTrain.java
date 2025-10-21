package frc.robot.subsystems.drive;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.helpers.MotorController;
import frc.robot.Constants;

public class DriveTrain{
    private MotorController frontLeftMotor = new MotorController("FrontLeftMotor", Constants.OperatorConstants.FLdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);
    private MotorController frontRightMotor = new MotorController("FrontRightMotor", Constants.OperatorConstants.FRdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);
    private MotorController backLeftMotor = new MotorController("BackLeftMotor", Constants.OperatorConstants.BLdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);
    private MotorController backRightMotor = new MotorController("BackRightMotor", Constants.OperatorConstants.BRdeviceID, MotorType.kBrushless, false, IdleMode.kBrake);

    //Set output to each motor
    public DifferentialDrive diffDrive = new DifferentialDrive(
        (double output) -> {
            frontLeftMotor.set(output);
            backLeftMotor.set(output);
        },
        (double output) -> {
            frontRightMotor.set(output);
            backRightMotor.set(output);
        }
    );
}
