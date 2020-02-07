package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

import frc.robot.Constants.TargetingGroupConstants;

public class TurretCommand extends CommandBase {
    private boolean autoTarget = TargetingGroupConstants.autoTarget;

    private TurretSubsystem m_turretSubsystem;
    private LimelightSubsystem m_limelightSubsystem;
    private DoubleSupplier jStick;

    /**
     * Simple constructor
     * @param m_turretSubsystem the turret that will be aiming
     * @param m_limlightSubsystem the feedback device for auto aiming
     * @param jStick input device for manual aiming.
     */
    public TurretCommand(TurretSubsystem m_turretSubsystem, LimelightSubsystem m_limlightSubsystem,
            DoubleSupplier jStick) {
        this.m_turretSubsystem = m_turretSubsystem;
        this.m_limelightSubsystem = m_limlightSubsystem;
        this.jStick = jStick;
    }

    /**
     * Changes from autotargeting to not
     */
    public void changeState() {
        autoTarget = !autoTarget;
    }

    /**
     * Passes the tx necessary to aim at the target, or joystick input Tells the
     * subsystem to turn accordingly.
     */
    @Override
    public void execute() {
        if (autoTarget) {
            boolean exists = false;
            if (m_limelightSubsystem.ta() != 0) {
                exists = true;
            }
            m_turretSubsystem.autoTarget(m_limelightSubsystem.tx(), exists);
        } else {
            m_turretSubsystem.turn(jStick.getAsDouble());
        }
    }
}