package frc.robot.subsystems.indexer;

import static edu.wpi.first.wpilibj2.command.Commands.deadline;
import static edu.wpi.first.wpilibj2.command.Commands.waitSeconds;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase{
    public IndexerIO io;

    public Indexer(IndexerIO io)
    {
        this.io = io;
    }

    public void setIndexerMotorForward()
    {
        io.setIndexerMotorForward();
    }

    public void setIndexerMotorReverse()
    {
        io.setIndexerMotorReverse();
    }

    public void stopIndexer()
    {
        io.stopIndexer();
    }

    public Command IndexerForward(){
        return this.runEnd(() -> setIndexerMotorForward(), ()-> stopIndexer()); 
    }
    public Command IndexerBackward(){
        return this.runEnd(() -> setIndexerMotorReverse(), ()-> stopIndexer()); 
    }
    public Command IndexStopper(){
        return this.run(() -> stopIndexer());
    }
    public Command revForTime(double speed, double time){
        return deadline(waitSeconds(time), IndexerForward()).withTimeout(time);
    }
}
