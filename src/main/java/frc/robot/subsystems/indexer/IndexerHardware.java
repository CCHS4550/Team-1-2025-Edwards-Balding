package frc.robot.subsystems.indexer;
import frc.helpers.MotorController;
import frc.robot.Constants;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

//import edu.wpi.first.math.filter.SlewRateLimiter;

public class IndexerHardware implements IndexerIO{
    private MotorController indexerMotor = new MotorController("indexerMotor", 
                                                                Constants.OperatorConstants.IndexShooterID, 
                                                                MotorType.kBrushless, 
                                                                false, 
                                                                IdleMode.kBrake);
    
    @Override
    public void setIndexerMotorForward()
    {
        indexerMotor.set(Constants.OperatorConstants.IndexerOutput);
    }

    @Override
    public void setIndexerMotorReverse()
    {
        indexerMotor.set(-1*Constants.OperatorConstants.IndexerOutput);
    }

    @Override
    public void stopIndexer()
    {
        indexerMotor.set(0);
    }
}
