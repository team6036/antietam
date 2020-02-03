package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {


    private static final int firstShooterPort = ShooterConstants.firstShooterPort;
    private static final int secondShooterPort = ShooterConstants.secondShooterPort;
    private static final int hopupPort = ShooterConstants.hopupPort;

    private CANSparkMax firstShooter;
    private CANSparkMax secondShooter;
    private VictorSP hopupMotor;

    

    public ShooterSubsystem() {
        firstShooter = new CANSparkMax(firstShooterPort, MotorType.kBrushless);
        secondShooter = new CANSparkMax(secondShooterPort, MotorType.kBrushless);
        hopupMotor = new VictorSP(hopupPort);

        secondShooter.follow(firstShooter);
    }
    /**
     * 
     */
    public void debug(){
        SmartDashboard.putNumber("rpm1", -firstShooter.getEncoder().getVelocity());
        SmartDashboard.putNumber("rpm2", -secondShooter.getEncoder().getVelocity());
    }

    
}