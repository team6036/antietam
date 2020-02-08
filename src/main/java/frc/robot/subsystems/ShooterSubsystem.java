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
    private static final int ssfPort = ShooterConstants.ssfPort;
    private static final int bagPort = ShooterConstants.bagPort;

    private CANSparkMax shooter1;
    private CANSparkMax shooter2;
    private VictorSP ssf;
    private VictorSP bag;

    

    public ShooterSubsystem() {
        /*
        shooter1 = new CANSparkMax(firstShooterPort, MotorType.kBrushless); 
        shooter2 = new CANSparkMax(secondShooterPort, MotorType.kBrushless);
        ssf = new VictorSP(ssfPort);
        bag = new VictorSP(bagPort);
        */
        /*
        shooter1.enableVoltageCompensation(12);
        shooter2.enableVoltageCompensation(12);
        what it do??
        */
        //shooter2.follow(shooter1, true); //invert?
    }

    public void debug(){
        /*
        SmartDashboard.putNumber("rpm1", -shooter1.getEncoder().getVelocity());
        SmartDashboard.putNumber("rpm2", -shooter2.getEncoder().getVelocity());
        SmartDashboard.putNumber("current1", shooter1.getOutputCurrent());
        SmartDashboard.putNumber("current2", shooter2.getOutputCurrent());
        */
    }

    
}