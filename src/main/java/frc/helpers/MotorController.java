package frc.helpers;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.Constants;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.ClosedLoopConfig;

import com.revrobotics.RelativeEncoder;
//import com.revrobotics.spark.SparkRelativeEncoder;

import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase;

/**
     * @param deviceID The channel of the motor controller
     * @param controlMode Specify whether the motor controller is operating in Brushed or Brushless mode
     * @param idleMode Specify whether the motor controller is set to Coast or Brake mode
     * @param reverse Reverses the direction of the motor controller
     * @param positionFactor The ratio of encoder units to desired units (ie. units -> in)
*/

public class MotorController implements edu.wpi.first.wpilibj.motorcontrol.MotorController{
    private final SparkMax motor;
    public final SparkClosedLoopController pidController;
    private final RelativeEncoder encoder;
    private final String name;
    private double voltageConversionFactor;

    private final EncoderConfig encoderConfig;
    private final SparkMaxConfig sparkMaxConfig;

    public MotorController(String name, int deviceID, MotorType motorType, boolean inverted, IdleMode idleMode, double positionFactor){
        this.name = name;
        motor = new SparkMax(deviceID, motorType);
        pidController = motor.getClosedLoopController();
        voltageConversionFactor = Constants.OperatorConstants.voltageConversionFactor;

        //Encoder config
        encoderConfig = new EncoderConfig();
        encoderConfig.positionConversionFactor(positionFactor);

        //SparkMax config
        sparkMaxConfig = new SparkMaxConfig();
        sparkMaxConfig.inverted(inverted);
        sparkMaxConfig.idleMode(idleMode);
        sparkMaxConfig.apply(encoderConfig);

        motor.configure(sparkMaxConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);

        encoder = motor.getEncoder();
    }

    public MotorController(String name, int deviceID, MotorType motorType, boolean inverted, IdleMode idleMode){
        this(name, deviceID, motorType, inverted, idleMode, 1.0);
    }

    //Built-in
    @Override
    public void set(double speed){
        motor.set(speed);
    }

    @Override
    public double get(){
        return motor.get();
    }

    @Override
    public void stopMotor(){
        motor.set(0);
    }

    @Override
    public void setInverted(boolean isInverted){
        motor.setInverted(isInverted);
    }

    @Override
    public boolean getInverted(){
        return motor.getInverted();
    }

    //SparkMax specific
    public void resetEncoder(){
        encoder.setPosition(0);
    }

    public double getPosition(){
        return encoder.getPosition();
    }

    public void setPosition(double pos){
        encoder.setPosition(pos);
    }

    public double getVelocity(){
        return encoder.getVelocity();
    }

    public void setPID(double kP, double kI, double kD, double kFF){
        pidController.setPIDF(kP, kI, kD, kFF);
    }

    public void setReferencePosition(double pos){
        pidController.setReference(pos, ControlType.kPosition);
    }

    public void setVoltage(double volts){
        double capped = MathUtil.clamp(volts, -12.0, 12.0);
        motor.setVoltage(capped);
    }

    public void setVoltageFromSpeed(double speed){
        motor.setVoltage(speed * voltageConversionFactor);
    }

    public void setPositionConversionFactor(double factor){
        encoderConfig.positionConversionFactor(factor);
        sparkMaxConfig.apply(encoderConfig);
        motor.configure(sparkMaxConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
    }

    public void setVelocityConversionFactor(double factor){
        encoderConfig.velocityConversionFactor(factor);
        sparkMaxConfig.apply(encoderConfig);
        motor.configure(sparkMaxConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
    }

    public String getName(){
        return name;
    }
}
