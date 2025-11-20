package frc.robot.subsystems.indexer;
public interface IndexerIO{
    public default void setIndexerMotorForward() {}

    public default void setIndexerMotorReverse() {}

    public default void stopIndexer() {}
}
