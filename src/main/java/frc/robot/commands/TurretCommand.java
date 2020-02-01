package frc.robot.commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.jStickListener;

public class TurretCommand extends CommandBase{
    private final static int motorPort = TurretConstants.motorPort;
    private CANSparkMax motor;
    public TurretCommand(TurretSubsystem m_turretSubsystem, jStickListener jStick){

    }
}