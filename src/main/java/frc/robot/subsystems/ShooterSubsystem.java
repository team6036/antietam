package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

    private static final int firstShooterPort = ShooterConstants.firstShooterPort;
    private static final int secondShooterPort = ShooterConstants.secondShooterPort;
    private static final int hopupPort = ShooterConstants.hopupPort;
    private static final int lineBreakPort = ShooterConstants.lineBreakPort;

    private CANSparkMax firstShooter;
    private CANSparkMax secondShooter;
    private VictorSP hopupMotor;
    private DigitalInput lineBreakIn;

    public ShooterSubsystem() {
        firstShooter = new CANSparkMax(firstShooterPort, MotorType.kBrushless);
        secondShooter = new CANSparkMax(secondShooterPort, MotorType.kBrushless);
        hopupMotor = new VictorSP(hopupPort);
        lineBreakIn = new DigitalInput(9);

        secondShooter.follow(firstShooter);
    }

    public void debug() {
        SmartDashboard.putNumber("rpm1", -firstShooter.getEncoder().getVelocity());
        SmartDashboard.putNumber("rpm2", -secondShooter.getEncoder().getVelocity());
        SmartDashboard.putBoolean("acc line break", lineBreakIn.get());
    }

    public void setShooterVelocity(double power) {
        firstShooter.set(power);
    }

    public double getShooterVelocity() {
        return firstShooter.getEncoder().getVelocity();
    }

    public void setHopupVelocity(double targetVelocity) {
        hopupMotor.set(targetVelocity);
    }

    public boolean getLineBreakIn() {
        return lineBreakIn.get();
    }

    public boolean getLineBreakOut(){
        return false;
    }
    

    @Override
    public void periodic() {
    }
}