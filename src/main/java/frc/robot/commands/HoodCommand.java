package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.Constants.HoodConstants.TurnState;
import frc.robot.Constants.HoodConstants;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.Limelight;

public class HoodCommand extends CommandBase {
    // Constants
    private static TurnState turnState = TurnState.MANUAL;
    private double kp = HoodConstants.kp;
    private boolean debug = HoodConstants.debug;

    // Hardware
    private HoodSubsystem m_hoodSubsystem;
    private DoubleSupplier jStick;
    private PIDController controller;

    /**
     * Basic constructor
     * 
     * @param m_hoodSubsystem
     * @param jStick          for manual hood control
     */
    public HoodCommand(HoodSubsystem m_hoodSubsystem, DoubleSupplier jStick) {
        this.m_hoodSubsystem = m_hoodSubsystem;
        this.jStick = jStick;
        controller = new PIDController(kp, 0, 0);
        addRequirements(m_hoodSubsystem);
    }

    /**
     * MANUAL: turns manually according to jstick AUTOTARGET: turns according to
     * distance ZEROING: Puts hood all the way down
     */
    @Override
    public void execute() {
        if (debug) {
            m_hoodSubsystem.debug();
        }
        switch (turnState) {
            case AUTOTARGET: {
                autoAdjust(distToAngle(Limelight.getDistance()));
                break;
            }
            case MANUAL: {
                m_hoodSubsystem.turn(jStick.getAsDouble());

                // System.out.println(jStick.getAsDouble());
                break;
            }
            case ZEROING: {
                // autoAdjust(0);
                break;
            }
        }
    }

    /**
     * Toggles zeroing state Default is autotarget
     */
    public void zero() {
        switch (turnState) {
            case ZEROING: {
                turnState = TurnState.AUTOTARGET;
                break;
            }
            default: {
                turnState = TurnState.ZEROING;
                break;
            }
        }
    }

    // TODO: make this not static, pass lambdas
    /**
     * Toggles manual state Default is autotarget
     */
    public static void manual() {
        switch (turnState) {
            case MANUAL: {
                turnState = TurnState.AUTOTARGET;
                break;
            }
            default: {
                turnState = TurnState.MANUAL;
                break;
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
         * TODO fancy calculation
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