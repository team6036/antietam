package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
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
    private AnalogInput lineBreak;

    // Others

    /**
     * Basic constructor
     */
    public ShooterSubsystem() {
        firstShooter = new CANSparkMax(firstShooterPort, MotorType.kBrushless);
        secondShooter = new CANSparkMax(secondShooterPort, MotorType.kBrushless);
        hopupMotor = new VictorSP(hopupPort);
        lineBreak = new AnalogInput(lineBreakPort);

        secondShooter.follow(firstShooter);
    }

    /**
     * Hardware feedback
     */
    public void debug() {
        SmartDashboard.putNumber("shooterV", getShooterVelocity());
        SmartDashboard.putBoolean("linebreak", getLineBreak());
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

    /**
     * Gets linebreak state
     * 
     * @return state
     */
    public boolean getLineBreak() {
        return (lineBreak.getValue() == 1);
    }
}