/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ClimberSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DrivetrainConstants;
// import frc.robot.Constants.DrivetrainConstants.ControlState;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class ClimberCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimberSubsystem m_climber;
  private final DrivetrainSubsystem m_drivetrain;
  private static boolean[] climberExtended = {false,false};
  private DoubleSupplier getX, getY;
  private static double leftSet, rightSet;
  // private ControlState controlState = ControlState.LIMELIGHT;
  // private double Kp = DrivetrainConstants.kp;
  // private double targetDist = DrivetrainConstants.targetDist;
  
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ClimberCommand(ClimberSubsystem climberSubsystem, DrivetrainSubsystem drivetrainSubsystem, DoubleSupplier getY, DoubleSupplier getX) {
    m_climber = climberSubsystem;
    m_drivetrain = drivetrainSubsystem;
    this.getY = getY;
    this.getX = getX;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climberSubsystem);
    addRequirements(drivetrainSubsystem);
  }

  public static void toggleArmPiston(double left, double right) {
    climberExtended[1] = climberExtended[0];
    climberExtended[0] = !climberExtended[0];
  }

  public static void drive(double left, double right) {
    leftSet = left;
    rightSet = right;
  }

  // public void changeState(ControlState controlState) {
  //   this.controlState = controlState;
  // }

  // public ControlState getState() {
  //   return controlState;
  // }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(climberExtended[0] != climberExtended[1]){
      m_climber.toggleArmPiston(climberExtended[0]);
      climberExtended[1] = climberExtended[0];
    }
    m_drivetrain.drive(getY.getAsDouble(), getX.getAsDouble());
    return;
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
