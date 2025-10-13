package frc.helpers;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkClosedLoopController;

import com.revrobotics.RelativeEncoder;
//import com.revrobotics.spark.SparkRelativeEncoder;

import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.SparkBase;

public class MotorController{
    private SparkMax motor;
    public SparkClosedLoopController pidController; //Keeping it PID for easy naming
    private RelativeEncoder encoder;
    private String name;
    
    public MotorController(String name, int deviceID, MotorType controlMode, boolean inverted, double positionFactor, IdleMode idleMode){
        this.motor = new SparkMax(deviceID, controlMode);
        this.name = name;
        this.pidController = motor.getClosedLoopController();
        this.encoder = motor.getEncoder();

        SparkMaxConfig sparkMaxConfig = new SparkMaxConfig();
        sparkMaxConfig.inverted(inverted);
        sparkMaxConfig.idleMode(idleMode);

        EncoderConfig encoderConfig = new EncoderConfig();
        encoderConfig.positionConversionFactor(positionFactor);
        
        motor.configure(sparkMaxConfig,SparkBase.ResetMode.kResetSafeParameters,SparkBase.PersistMode.kNoPersistParameters);
    }

    //MotorController motor = new MotorController("LeftMotor", 1, MotorType.kBrushless, false, 1.0, IdleMode.kBrake);
}
