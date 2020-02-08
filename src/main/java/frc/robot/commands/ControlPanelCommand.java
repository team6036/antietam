package frc.robot.commands;

import frc.robot.Constants.ControlPanel.Motor;
import frc.robot.subsystems.ControlPanelSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;



enum Mode {
    TURN_3_TO_5_TIMES, TURN_TO_NEAREST_COLOR
}

public class ControlPanelCommand extends CommandBase {
    private final ControlPanelSubsystem m_controlPanel;
    private String targetColor;
    private String c1;
    private double numRotations = 0;
    private Mode mode = Mode.TURN_3_TO_5_TIMES;
    public ControlPanelCommand(ControlPanelSubsystem controlPanel) {
        this.m_controlPanel = controlPanel;
        addRequirements(controlPanel);
    }

    // Called when command is initialized. Only runs once
    @Override
    public void initialize() {
        if (mode == Mode.TURN_3_TO_5_TIMES) {
            m_controlPanel.setMotorSpeed(Motor.SPEED);
            c1 = m_controlPanel.getCurrentColor();
        }
        else if (mode == Mode.TURN_TO_NEAREST_COLOR) {
            targetColor = m_controlPanel.getTargetColor();
            Integer rotations = m_controlPanel.position();
            if (rotations > 0) 
                m_controlPanel.setMotorSpeed(Motor.SPEED);
            else
                m_controlPanel.setMotorSpeed(-Motor.SPEED);
        }
    }

    @Override
    public void execute() {
        if (mode == Mode.TURN_3_TO_5_TIMES) {
            if (m_controlPanel.halfRotation(c1, m_controlPanel.getCurrentColor())) {
                c1 = m_controlPanel.getCurrentColor();
                numRotations += 0.5;
            }
        }
    }

    @Override
    public boolean isFinished() {
        if (mode == Mode.TURN_3_TO_5_TIMES && numRotations > 3 && numRotations < 5) {
            mode = Mode.TURN_TO_NEAREST_COLOR;
            return true;
        }
        return targetColor.equals(m_controlPanel.getCurrentColor());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_controlPanel.setMotorSpeed(0);
    }
}
