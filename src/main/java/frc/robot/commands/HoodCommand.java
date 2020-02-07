package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.function.DoubleSupplier;
import frc.robot.Constants.TargetingGroupConstants;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class HoodCommand extends CommandBase {
    private boolean autoTarget = TargetingGroupConstants.autoTarget;
    private HoodSubsystem m_hoodSubsystem;
    private LimelightSubsystem m_limelightSubsystem;
    private DoubleSupplier jStick;
    private JoystickButton aButton;

    public HoodCommand(HoodSubsystem m_hoodSubsystem, LimelightSubsystem m_limelightSubsystem, DoubleSupplier jStick,
            JoystickButton aButton) {
        this.m_hoodSubsystem = m_hoodSubsystem;
        this.m_limelightSubsystem = m_limelightSubsystem;
        this.jStick = jStick;
        this.aButton = aButton;
    }

    /**
     * Turns or autoadjusts based on distance
     */
    @Override
    public void execute() {
        if (autoTarget) {
            m_hoodSubsystem.autoAdjust(m_limelightSubsystem.getDistance());
        } else {
            m_hoodSubsystem.turn(jStick.getAsDouble());
        }
    }

    /**
     * binds the a key to retract the hood
     */
    @Override
    public void initialize() {
        aButton.whenPressed(new InstantCommand(() -> m_hoodSubsystem.zero()));
    }

    /**
     * changes autotarget <-> not
     */
    public void changeState() {
        autoTarget = !autoTarget;
    }
}