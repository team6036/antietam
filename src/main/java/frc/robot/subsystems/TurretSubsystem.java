package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class TurretSubsystem extends SubsystemBase {
    private final static int motorPort = TurretConstants.motorPort;
    private final static int[] encoderPorts = TurretConstants.encoderPorts;
    private final static double kp = TurretConstants.kp;
    private CANSparkMax motor;

    // these might not be needed
    private CANEncoder motorEncoder;
    private Encoder absEncoder;

    private double power = 0;

    public TurretSubsystem() {
        motor = new CANSparkMax(motorPort, MotorType.kBrushless);
        motorEncoder = motor.getEncoder();
        absEncoder = new Encoder(encoderPorts[0], encoderPorts[1]);
    }

    /**
     * Prints the motor power
     */
    public void debug() {
        SmartDashboard.putNumber("Turret Power", power);
    }

    /**
     * Uses PID to automatically aim the turret at the target
     * 
     * @param error  horizontal angle of displacement from target
     * @param exists whether there is a target at all (error will be zero)
     */
    public void autoTarget(double error, boolean exists) {
        // might also need to reverse polarity on error
        /*
         * Important: This method needs heavy modification. It needs to: 1. have a field
         * and behaviour for no target 2. prevent overrotation
         */
        if (exists) {
            power += error * kp;
            motor.set(power);
        } else {
            //turn such that it doesnt over-turn
        }
    }

    /**
     * Turns.
     * 
     * @param amount power to pass to motor
     */
    public void turn(double amount) {
        if (amount >= 0.1) {
            power = amount;
            motor.set(power); // might need to reverse polarity
            // prevent overrotation
        } else {
            power = 0;
            motor.set(0);
        }
    }
}