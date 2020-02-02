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
  private ShooterSubsystem m_shooterSubsystem;

  private final static double verticalKP = LimelightConstants.verticalKP;
  private final static double horizontalKP = LimelightConstants.horizontalKP;
  private final static double targetDistance = LimelightConstants.targetDistance;
  private final static boolean debug = LimelightConstants.debug;

  private double horizontalPower = 0;
  private double verticalPower = 0;
  private double horizontalError;
  private double verticalError;

  public LimelightCommand(LimelightSubsystem m_limelightSubsystem, DrivetrainSubsystem m_drivetrainSubsystem,
      ShooterSubsystem m_shooterSubsystem) {
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
    if(debug){
      m_limelightSubsystem.debug();
    }
    approachTarget(targetDistance);
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
  private void approachTarget(double targetDistance) {
    // turn
    if (m_limelightSubsystem.ta() != 0) {
      horizontalError = m_limelightSubsystem.tx();
      SmartDashboard.putNumber("Horizontal Error", horizontalError);
      horizontalPower += horizontalError * horizontalKP;
      
      verticalError = m_limelightSubsystem.getDistance() - targetDistance;
      SmartDashboard.putNumber("Vertical Error", verticalError);
      verticalPower += verticalError * verticalKP;
      m_drivetrainSubsystem.drive(-verticalPower, horizontalPower);
    }
  }
}
