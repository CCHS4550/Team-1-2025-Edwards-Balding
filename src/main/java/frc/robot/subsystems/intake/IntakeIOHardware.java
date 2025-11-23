/*
 * package frc.robot.subsystems.intake;
 * 
 * import com.revrobotics.spark.SparkLowLevel.MotorType;
 * import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
 * 
 * import frc.helpers.MotorController;
 * import frc.robot.Constants;
 * 
 * public class IntakeIOHardware implements IntakeIO
 * {
 * private MotorController intakeFlywheelMotor = new
 * MotorController("intakeFlywheelMotor",
 * Constants.OperatorConstants.flywheelMotorID,
 * MotorType.kBrushless,
 * true,
 * IdleMode.kBrake);
 * 
 * 
 * @Override
 * public void setFlywheelVoltage(double voltage)
 * {
 * intakeFlywheelMotor.setVoltage(voltage);
 * }
 * 
 * @Override
 * public void setFlywheelSpeed(double speed)
 * {
 * intakeFlywheelMotor.set(speed);
 * }
 * 
 * @Override
 * public void setFlywheelVoltageDefault()
 * {
 * intakeFlywheelMotor.setVoltage(Constants.OperatorConstants.
 * flywheelVoltageDefault);
 * }
 * 
 * @Override
 * public void setFlywheelSpeedDefault()
 * {
 * intakeFlywheelMotor.setVoltage(Constants.OperatorConstants.
 * flywheelSpeedDefault);
 * }
 * }
 */