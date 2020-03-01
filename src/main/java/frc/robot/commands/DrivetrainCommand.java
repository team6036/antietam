/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import frc.robot.Constants.DrivetrainConstants.ControlState;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class DrivetrainCommand extends CommandBase {

  private DoubleSupplier getX, getY;
  private final DrivetrainSubsystem m_drivetrain;
  private static double leftSet, rightSet;

  private ControlState controlState = ControlState.MANUAL;

  /**
   * Creates a new ExampleCommand.
   * 
   * The subsystem used by this command.
   */
  public DrivetrainCommand(DrivetrainSubsystem drivetrain, DoubleSupplier getY, DoubleSupplier getX) {
    this.getY = getY;
    this.getX = getX;
    m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  public static void drive(double left, double right) {
    leftSet = left;
    rightSet = right;
  }

  public void changeState(ControlState controlState) {
    this.controlState = controlState;
  }

  public ControlState getState() {
    return controlState;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_drivetrain.frontRightMotor.set(0.1);
    switch (controlState) {
    case MANUAL: {
      m_drivetrain.drive(getY.getAsDouble(), getX.getAsDouble());
      return;
    }
    default: {
     // m_drivetrain.lowLevelDrive(leftSet, rightSet);
      return;
    }
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
