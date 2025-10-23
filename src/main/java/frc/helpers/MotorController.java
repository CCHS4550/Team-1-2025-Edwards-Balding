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

public class MotorController{
    private SparkMax motor;
    public SparkClosedLoopController pidController; //Keeping it PID for easy reference
    private RelativeEncoder encoder;
    private String name;
    private double voltageConversionFactor;

    //Configurations to change values for the encoder and motor
    private EncoderConfig encoderConfig;
    private SparkMaxConfig sparkMaxConfig;
    
    public MotorController(String name, int deviceID, MotorType controlMode, boolean inverted, IdleMode idleMode, double positionFactor){
        this.motor = new SparkMax(deviceID, controlMode);
        this.name = name;
        this.pidController = motor.getClosedLoopController();

        this.encoderConfig = new EncoderConfig();
        encoderConfig.positionConversionFactor(positionFactor);

        this.sparkMaxConfig = new SparkMaxConfig();
        sparkMaxConfig.inverted(inverted);
        sparkMaxConfig.idleMode(idleMode);
        sparkMaxConfig.apply(encoderConfig);

        motor.configure(sparkMaxConfig,SparkBase.ResetMode.kResetSafeParameters,SparkBase.PersistMode.kNoPersistParameters);

        this.encoder = motor.getEncoder();
        voltageConversionFactor = Constants.OperatorConstants.voltageConversionFactor;
    }
    public MotorController(String name, int deviceID, MotorType controlMode, boolean inverted, IdleMode idleMode){
        this.motor = new SparkMax(deviceID, controlMode);
        this.name = name;
        this.pidController = motor.getClosedLoopController();

        this.encoderConfig = new EncoderConfig();
        encoderConfig.positionConversionFactor(1.0);

        this.sparkMaxConfig = new SparkMaxConfig();
        sparkMaxConfig.inverted(inverted);
        sparkMaxConfig.idleMode(idleMode);
        sparkMaxConfig.apply(encoderConfig);

        motor.configure(sparkMaxConfig,SparkBase.ResetMode.kResetSafeParameters,SparkBase.PersistMode.kNoPersistParameters);

        this.encoder = motor.getEncoder();
        voltageConversionFactor = Constants.OperatorConstants.voltageConversionFactor;
    }

    public void reset(){
        encoder.setPosition(0);
    }

    public void set(double speed){
        motor.set(speed);
    }

    public void set(boolean stop, double speed){
        if(!stop){
            motor.set(speed);
        }else{
            motor.set(0);
        }
    }

    public double getSpeed(){
        return motor.get();
    }
    

    public void disable(){
        motor.disable();
    }

    public void setVoltage(){
        motor.setVoltage(Constants.OperatorConstants.ShooterVoltage);
    }

    public void setVoltage(double volts){
        motor.setVoltage(volts);
    }

    public void setVoltage(double volts, double limit){
        if(motor.getOutputCurrent() <= limit){
            motor.setVoltage(volts);
        }else{
            motor.setVoltage(0);
        }
    }

    public void setVoltageFromSpeed(double speed){
        motor.setVoltage(speed*voltageConversionFactor);
    }

    public double getVelocity(){
        return encoder.getVelocity();
    }

    public void setPositionConversionFactor(double factor){
        encoderConfig.positionConversionFactor(factor);
        sparkMaxConfig.apply(encoderConfig);
        motor.configure(sparkMaxConfig,SparkBase.ResetMode.kResetSafeParameters,SparkBase.PersistMode.kNoPersistParameters);
        this.encoder = motor.getEncoder();
    }

    public void setVelocityConversionFactor(double factor) {
        encoderConfig.velocityConversionFactor(factor);
        sparkMaxConfig.apply(encoderConfig);
        motor.configure(sparkMaxConfig,SparkBase.ResetMode.kResetSafeParameters,SparkBase.PersistMode.kNoPersistParameters);
        this.encoder = motor.getEncoder();
    }
    

    //Sets the encoder position
    public void setPosition(double pos){
        encoder.setPosition(pos);
    }

    //By default the position is in encoder units, but will return a distance if the Position Conversion Factor has been set.
    public double getPosition(){
        return encoder.getPosition();
    }
    
    /**
     * Sets the PID values, must be positive
     * @param Kp The proportional gain value
     * @param Ki The integral gain value
     * @param Kd The derivative gain value
     */
    public void setPID(double Kp, double Ki, double Kd, double ff){
        ClosedLoopConfig closedLoopConfig = new ClosedLoopConfig();
        closedLoopConfig.pidf(Kp,Ki,Kd,ff); //Going to be deprecated but too lazy to fix it up right now

        sparkMaxConfig.apply(closedLoopConfig);
        motor.configure(sparkMaxConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
    }

    public void setReferencePosition(double pos){
        pidController.setReference(pos, ControlType.kPosition);
    }
    
    public String getName(){
        return name;
    }
}
