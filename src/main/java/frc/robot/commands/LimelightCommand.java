package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.Constants.LimelightConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command takes J-Stick input and makes calls based on it to the
 * drivetrain subsystem to move. In the future, this command will (likely) have
 * auto functionality, or another command will be made.
 */
public class LimelightCommand extends CommandBase {
  private LimelightSubsystem m_limelightSubsystem;
  private DrivetrainSubsystem m_drivetrainSubsystem;

  private final static double targetDistance = LimelightConstants.targetDistance;
  private final static double KpAim = -0.1;
  private final static double KpDistance = -0.1;
  private final static double min_aim_command = 0.05;

  private static double leftPower = 0;
  private static double rightPower = 0;

  private final static boolean debug = LimelightConstants.debug;

  public LimelightCommand(LimelightSubsystem m_limelightSubsystem, DrivetrainSubsystem m_drivetrainSubsystem) {
    this.m_limelightSubsystem = m_limelightSubsystem;
    this.m_drivetrainSubsystem = m_drivetrainSubsystem;
    addRequirements(m_limelightSubsystem);
  }

  // Called when the command is initially scheduled.
  /**
   * ideally will set the encoder readings to 0
   */
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (debug) {
      m_limelightSubsystem.debug();
    }
    approachTarget();
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

  // simply for testing, will turn to and approach target.
  private void approachTarget() {
    if (m_limelightSubsystem.ta() != 0) {
      double tx = m_limelightSubsystem.tx();
      double dist = m_limelightSubsystem.getDistance();
      double heading_error = -tx;
      double distance_error = -dist - targetDistance;
      double steering_adjust = 0.;

      /**
       * if above deadband, steering adjust +- adjusted error
       */
      if (tx > 1.0) {
        steering_adjust = KpAim * heading_error - min_aim_command;
      } else if (tx < 1.0) {
        steering_adjust = KpAim * heading_error + min_aim_command;
      }

      /**
       * fix dist error
       */
      double distance_adjust = KpDistance * distance_error;

      leftPower += steering_adjust + distance_adjust;
      rightPower -= steering_adjust + distance_adjust;
    } else{
      leftPower = -0.1;
      rightPower = -0.1;
    }
    SmartDashboard.putNumber("leftPower", leftPower);
    SmartDashboard.putNumber("rightPower", rightPower);
  }
}
