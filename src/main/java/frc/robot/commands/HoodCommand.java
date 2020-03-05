package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.Constants.HoodConstants.TurnState;
import frc.robot.Constants.HoodConstants;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.Limelight;

public class HoodCommand extends CommandBase {

    private static TurnState turnState = TurnState.MANUAL;
    private double kp = HoodConstants.kp;

    private HoodSubsystem m_hoodSubsystem;
    private DoubleSupplier jStick;
    private PIDController controller;

    public HoodCommand(HoodSubsystem m_hoodSubsystem, DoubleSupplier jStick) {
        this.m_hoodSubsystem = m_hoodSubsystem;
        this.jStick = jStick;
        controller = new PIDController(kp, 0, 0);
        addRequirements(m_hoodSubsystem);
    }

    // inside the HoodCommand
    /**
     * Turns or autoadjusts based on distance
     */
    @Override
    public void execute() {
        SmartDashboard.putNumber("Hood optical", m_hoodSubsystem.getOpticalEncoder());
        SmartDashboard.putNumber("Hood dce", m_hoodSubsystem.getAngle());
        switch (turnState) {
        case AUTOTARGET: {
            autoAdjust(distToAngle(Limelight.getDistance()));
            break;
        }
        case MANUAL: {
            m_hoodSubsystem.turn(jStick.getAsDouble());

            
           //System.out.println(jStick.getAsDouble());
           break;
        }
        case ZEROING: {
            //autoAdjust(0);
            break;
        }
        }
    }
    public static void zero(){
        switch(turnState){
            case ZEROING:{
                turnState=TurnState.AUTOTARGET;
            }
            default:{
                turnState=TurnState.ZEROING;
            }
        }
    }
    public static void manual(){
        switch(turnState){
            case MANUAL:{
                turnState=TurnState.AUTOTARGET;
            }
            default:{
                turnState=TurnState.MANUAL;
            }
        }
    }
    /**
     * gets angle from distance
     * 
     * @param distance the distance from target
     * @return the angle to put the hood at
     */
    private double distToAngle(double distance) {
        /**
         * fancy calculation
         */
        return distance;
    }

    /**
     * automatically adjusts to a given distance
     * 
     * @param distance distance to adjust to
     */
    private void autoAdjust(double distance) {
        controller.setSetpoint(distance);
        m_hoodSubsystem.turn(controller.calculate(m_hoodSubsystem.getAngle()));

    }

}