/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.TargetingGroupConstants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterCommand extends CommandBase {
  private final boolean debug = ShooterConstants.debug;
  private boolean autoTarget = TargetingGroupConstants.autoTarget;

  private final XboxController m_controller;
  private final ShooterSubsystem m_shooterSubsystem;

  /**
   * Creates a new ExampleCommand.
   * 
   * The subsystem used by this command.
   */
  public ShooterCommand(ShooterSubsystem shooterSubsystem, XboxController controller) {
    m_shooterSubsystem = shooterSubsystem;
    this.m_controller = controller;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterSubsystem);
  }

  public void changeState() {
    autoTarget = !autoTarget;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (debug) {
      m_shooterSubsystem.debug();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
