/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.jStickListener;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterCommand extends CommandBase {
  private final boolean debug = ShooterConstants.debug;

  private final ShooterSubsystem m_shooterSubsystem;

  private jStickListener rTrigger;

  public ShooterCommand(ShooterSubsystem shooterSubsystem, jStickListener rTrigger) {
    m_shooterSubsystem = shooterSubsystem;
    this.rTrigger = rTrigger;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterSubsystem); // @TODO this for all commands and respective key subsystems
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
    /*
    if(rTrigger.getY()>=0.05){
      m_shooterSubsystem.shoot();
    }
    */

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
