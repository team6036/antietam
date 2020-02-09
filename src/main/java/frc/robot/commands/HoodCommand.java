package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.Constants.TargetingGroupConstants;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.Limelight;

public class HoodCommand extends CommandBase {
    private boolean autoTarget = TargetingGroupConstants.autoTarget;
    private HoodSubsystem m_hoodSubsystem;
    private DoubleSupplier jStick;

    public HoodCommand(HoodSubsystem m_hoodSubsystem, DoubleSupplier jStick) {
        this.m_hoodSubsystem = m_hoodSubsystem;
        this.jStick = jStick;
    }

    /**
     * Turns or autoadjusts based on distance
     */
    @Override
    public void execute() {
        if (autoTarget) {
            m_hoodSubsystem.autoAdjust(Limelight.getDistance());
        } else {
            m_hoodSubsystem.turn(jStick.getAsDouble());
        }
    }

    public void zero(){
        
    }

    /**
     * changes autotarget <-> not
     */
    public void changeState() {
        autoTarget = !autoTarget;
    }
}