package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.AbsoluteEncoder;
import frc.robot.Constants.TurretConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogInput;


public class TurretSubsystem extends SubsystemBase {
    private final static int motorPort = TurretConstants.motorPort;
    private final static int encoderPort = TurretConstants.encoderPorts;


    private CANSparkMax motor;
    private AbsoluteEncoder absEncoder;

    private double power = 0;

    public TurretSubsystem() {
        motor = new CANSparkMax(motorPort, MotorType.kBrushless);
        absEncoder = new AbsoluteEncoder(new AnalogPotentiometer(new AnalogInput(encoderPort)));
    }

    /**
     * Prints the motor power
     */
    public void debug() {
        SmartDashboard.putNumber("Turret Power", power);
        SmartDashboard.putNumber("Turret Angle", absEncoder.getAngle());
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
    /**
     * returns angle of displacement to drivetrain
     */
    public double getDisplacement(){
        return absEncoder.getAngle(); //might need to be adjusted to zero-angle
    }
    
    /**
     * Turns.
    //todo make sure to accout for overrotation
     * @param amount power to pass to motor
     */
    public void turn(double amount) {
        motor.set(amount * 0.5);
        //System.out.println(amount);
        // prevent overrotation
        if (amount >= 0.15) {
            power = amount*0.1;
            
        }else{
            power = 0;
        }
       
    }
}