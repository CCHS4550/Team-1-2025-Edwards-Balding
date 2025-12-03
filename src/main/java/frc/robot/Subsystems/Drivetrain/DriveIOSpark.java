package frc.robot.Subsystems.Drivetrain;

import static edu.wpi.first.units.Units.Newton;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.filter.Debouncer;
import frc.robot.SparkUtil;

public class DriveIOSpark implements DriveIO{
    private final SparkBase frontLeft;
    private final RelativeEncoder frontLeftEncoder;
    private final SparkBase backLeft;
    private final RelativeEncoder backLeftEncoder;
    private final SparkBase frontRight;
    private final RelativeEncoder frontRightEncoder;
    private final SparkBase backRight;
    private final RelativeEncoder backRightEncoder;

    private final Debouncer frontLeftDebouncer = new Debouncer(0.5);
    private final Debouncer backLeftDebouncer = new Debouncer(0.5);
    private final Debouncer frontRightDebouncer = new Debouncer(0.5);
    private final Debouncer backRightDebouncer = new Debouncer(0.5);

    public DriveIOSpark(){
        frontLeft = new SparkMax(0, MotorType.kBrushless);
        frontLeftEncoder = frontLeft.getEncoder();
        backLeft = new SparkMax(0, MotorType.kBrushless);
        backLeftEncoder = backLeft.getEncoder();
        frontRight = new SparkMax(0, MotorType.kBrushless);
        frontRightEncoder = frontRight.getEncoder();
        backRight = new SparkMax(0, MotorType.kBrushless);
        backRightEncoder = backRight.getEncoder();

        var frontLeftConfig = new SparkMaxConfig();

        frontLeftConfig.inverted(false);
        frontLeftConfig.idleMode(IdleMode.kBrake);
        frontLeftConfig.smartCurrentLimit(40);
        frontLeftConfig.voltageCompensation(12);

        frontLeftConfig.encoder.positionConversionFactor(1);
        frontLeftConfig.encoder.velocityConversionFactor(1);

        frontLeft.configure(frontLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void updateInputs(DriveIOInputs inputs) {
        SparkUtil.stickyFault = false;
        inputs.frontLeftConnected = frontLeftDebouncer.calculate(SparkUtil.stickyFault);
        inputs.frontLeftPositionRad = frontLeftEncoder.getPosition();
    }
    @Override
    public void setFrontLeftOpenLoop(double volts){
        frontLeft.setVoltage(volts);
    }

    @Override
    public void setBackLeftOpenLoop(double volts){
        backLeft.setVoltage(volts);
    }

    @Override
    public void setFrontRightOpenLoop(double volts){
        frontRight.setVoltage(volts);
    }

    @Override
    public void setBackRightOpenLoop(double volts){
        backRight.setVoltage(volts);
    }
}
