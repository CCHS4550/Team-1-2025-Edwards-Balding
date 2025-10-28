package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.shooter.ShooterIO.ShooterIOInputs;

public class Shooter extends SubsystemBase{
    public ShooterIO io;
    public ShooterIOInputs inputs;
    public ShooterIOInputs.AutoLogged autoLogged; 
}
