package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.AbsoluteEncoder;
import frc.robot.Constants.TurretConstants;
import edu.wpi.first.wpilibj.controller.PIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogInput;


public class TurretSubsystem extends SubsystemBase {
    private final static int motorPort = TurretConstants.motorPort;
    private final static int encoderPort = TurretConstants.encoderPorts;
    private final static double kp = TurretConstants.kp;

    private CANSparkMax motor;
    private CANEncoder motorEncoder;
    private AbsoluteEncoder absEncoder;

    /**
     * change to abs encoder here
     * https://github.com/team6036/gettysburg-3/blob/master/src/main/java/frc/robot/
     * AbsoluteEncoder.java
     */
    private double power = 0;
    private double error = 0;
    private boolean exists = false;

    public TurretSubsystem() {
        motor = new CANSparkMax(motorPort, MotorType.kBrushless);
        motorEncoder = motor.getEncoder();
        absEncoder = new AbsoluteEncoder(new AnalogPotentiometer(new AnalogInput(encoderPort)));
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

    @Override
    public void periodic() {
    }

    public double getDisplacement(){
        return absEncoder.getAngle(); //might need to be adjusted to zero-angle
    }
    
    /**
     * Turns.
     * 
     * @param amount power to pass to motor
     */
    public void turn(double amount) {
        motor.set(amount); // might need to reverse polarity
        // prevent overrotation
        if (amount >= 0.1) {
            power = 0;
            motor.set(0);
        }
    }
}