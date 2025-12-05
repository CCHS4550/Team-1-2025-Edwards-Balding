package frc.robot.Subsystems.Arm;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController.ArbFFUnits;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.geometry.Rotation2d;

public class ArmIOSpark implements ArmIO{
    private final SparkBase armSpark;
    private final AbsoluteEncoder armAbsoluteEncoder;
    
    private SparkClosedLoopController armController;

    private final Debouncer armDebouncer= new Debouncer(0.5);

    public ArmIOSpark(){
        armSpark = new SparkMax(0, MotorType.kBrushless);
        armAbsoluteEncoder = armSpark.getAbsoluteEncoder();
        armController = armSpark.getClosedLoopController();

        var armConfig = new SparkMaxConfig();

        armConfig.idleMode(IdleMode.kBrake).smartCurrentLimit(60).voltageCompensation(12);

        armConfig.absoluteEncoder.positionConversionFactor(123).velocityConversionFactor(123/60).setSparkMaxDataPortConfig().averageDepth(2);

        armConfig.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder).positionWrappingEnabled(false).pidf(1, 0, 0, 0);

        armConfig.signals.
        absoluteEncoderPositionAlwaysOn(true).
        absoluteEncoderPositionPeriodMs(10).
        absoluteEncoderVelocityAlwaysOn(true).
        absoluteEncoderVelocityPeriodMs(20).
        appliedOutputPeriodMs(20).
        busVoltagePeriodMs(20).
        outputCurrentPeriodMs(20);

        armSpark.configure(armConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    @Override
    public void updateInputs(ArmIOInputs inputs) {
        
    }

    @Override
    public void armOpenLoop(double volts){
        armSpark.setVoltage(volts);
    }

    @Override
    public void goToAngleRadians(Rotation2d angle, double arbFF){
        armController.setReference(angle.getRadians(), ControlType.kPosition, ClosedLoopSlot.kSlot0, arbFF, ArbFFUnits.kVoltage);
    }
}

