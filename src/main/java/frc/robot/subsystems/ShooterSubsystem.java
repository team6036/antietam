package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
    // Constants
    private static final int firstShooterPort = ShooterConstants.firstShooterPort;
    private static final int secondShooterPort = ShooterConstants.secondShooterPort;
    private static final int hopupPort = ShooterConstants.hopupPort;
    private static final int lineBreakPort = ShooterConstants.lineBreakPort;

    // Hardware
    private CANSparkMax firstShooter;
    private CANSparkMax secondShooter;
    private VictorSP hopupMotor;
    private DigitalInput lineBreakIn;

    // Others

    /**
     * Basic constructor
     */
    public ShooterSubsystem() {
        firstShooter = new CANSparkMax(firstShooterPort, MotorType.kBrushless);
        secondShooter = new CANSparkMax(secondShooterPort, MotorType.kBrushless);
        hopupMotor = new VictorSP(hopupPort);
        lineBreakIn = new DigitalInput(lineBreakPort);

        secondShooter.follow(firstShooter);
    }

    /**
     * Hardware feedback
     */
    public void debug() {
        SmartDashboard.putNumber("rpm1", -firstShooter.getEncoder().getVelocity());
        SmartDashboard.putNumber("rpm2", -secondShooter.getEncoder().getVelocity());
        SmartDashboard.putBoolean("acc line break", lineBreakIn.get());
    }

    /**
     * Set shooter motor power
     * 
     * @param power
     */
    public void setShooterVelocity(double power) {
        firstShooter.set(power);
    }

    /**
     * Get shooter velocity
     * 
     * @return shooter velocity
     */
    public double getShooterVelocity() {
        return firstShooter.getEncoder().getVelocity();
    }

    /**
     * Set hopup velocity
     * 
     * @param targetVelocity velocity
     */
    public void setHopupVelocity(double targetVelocity) {
        hopupMotor.set(targetVelocity);
    }

    public boolean getLineBreak() {
        return lineBreakIn.get();
    }

    public boolean getLineBreakOut() {
        return false;
    }

    @Override
    public void periodic() {
    }
}