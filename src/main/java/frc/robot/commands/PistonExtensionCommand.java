package frc.robot.commands;

import frc.robot.subsystems.ControlPanelSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


enum PistonState {
    EXTENDED, RETRACTED
}

public class PistonExtensionCommand extends CommandBase {
    private final ControlPanelSubsystem m_controlPanel;
    private PistonState pState = PistonState.RETRACTED;
    public PistonExtensionCommand(ControlPanelSubsystem controlPanel) {
        this.m_controlPanel = controlPanel;
        addRequirements(controlPanel);
    }

    // Called when command is initialized. Only runs once
    @Override
    public void initialize() {
        if (pState == PistonState.RETRACTED) {
            m_controlPanel.extendPiston();
            pState = PistonState.EXTENDED;
        }
        else {
            m_controlPanel.retractPiston();
            pState = PistonState.RETRACTED;
        }
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }
}
