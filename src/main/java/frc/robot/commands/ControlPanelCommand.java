package frc.robot.commands;

import frc.robot.subsystems.ControlPanelSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class ControlPanelCommand extends CommandBase {
    private final ControlPanelSubsystem m_controlPanel;
    private final PistonExtensionCommand m_pistonExtensionCommand;
    private String targetColor;
    private String c1, c2;
    private double numRotations = 0;
    private int mode = 0;
    public ControlPanelCommand(ControlPanelSubsystem controlPanel, PistonExtensionCommand pec) {
        this.m_controlPanel = controlPanel;
        this.m_pistonExtensionCommand = pec;
        addRequirements(controlPanel);
    }

    // Called when command is initialized. Only runs once
    @Override
    public void initialize() {
        m_controlPanel.extendPiston();
        if (mode == 0) {
            m_controlPanel.setMotorSpeed(1);
            c1 = m_controlPanel.getCurrentColor();
        }
        else if (mode == 1) {
            targetColor = m_controlPanel.getTargetColor();
            Integer rotations = m_controlPanel.position();
            if (rotations > 0) 
                m_controlPanel.setMotorSpeed(1);
            else
                m_controlPanel.setMotorSpeed(-1);
        }
    }

    @Override
    public void execute() {
        // m_controlPanel.debug();
        if (mode == 0) {
            c2 = m_controlPanel.getCurrentColor();
            if (m_controlPanel.halfRotation(c1, c2))
                numRotations += 0.5;
        }
    }

    @Override
    public boolean isFinished() {
        if (mode == 0 && numRotations > 3 && numRotations < 5) {
            mode = 1;
            return true;
        }
        return targetColor.equals(m_controlPanel.getCurrentColor());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_controlPanel.setMotorSpeed(0);
        m_controlPanel.retractPiston();
    }
}
