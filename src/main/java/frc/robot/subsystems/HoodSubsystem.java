package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HoodConstants;

public class HoodSubsystem extends SubsystemBase {
    private static final int motorPort = HoodConstants.motorPort;
    private static final int[] encoderPorts = HoodConstants.encoderPorts;
    private static final double kp = HoodConstants.kp;

    private VictorSP motor;
    private Encoder encoder;

    private double target = 0;
    private double power = 0;
    private double error = 0;
    
    public HoodSubsystem() {
        motor = new VictorSP(motorPort);
        encoder = new Encoder(encoderPorts[0], encoderPorts[1]);
    }

    /**
     * gets angle from distance
     * 
     * @param distance the distance from target
     * @return the angle to put the hood at
     */
    private double getAngle(double distance) {
        /**
         * fancy calculation
         */
        return distance;
    }

    /**
     * converts the angle to go to to the position on the encoder
     * 
     * @param angle target angle
     * @return encoder position to go to
     */
    private double angleToPosition(double angle) {
        /**
         * fancy calculation to convert
         */
        return angle;
    }

    /**
     * automatically adjusts to a given distance
     * 
     * @param distance distance to adjust to
     */
    public void autoAdjust(double distance) {
        turnToAngle(angleToPosition(getAngle(distance)));
    }

    /**
     * turns hood to down-position
     */
    public void zero() {
        turnToAngle(0);
    }

    /**
     * turns to an input, converts it to an encoder position too
     * 
     * @param input jstick input
     */
    public void turn(double input) {
        turnToAngle(angleToPosition(input));
    }

    /**
     * turns to a given angle
     * 
     * @param angle angle to turn to
     */
    private void turnToAngle(double angle) {
        error = angle - encoder.get();
        power += error * kp;
        motor.set(power);
    }
}