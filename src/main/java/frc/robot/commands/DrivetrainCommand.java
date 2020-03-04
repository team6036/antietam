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
   * Basic constructor
   * 
   * @param drivetrain subsystem
   * @param getY       supplier for joystick y
   * @param getX       supplier for joystick x
   */
  public DrivetrainCommand(DrivetrainSubsystem drivetrain, DoubleSupplier getY, DoubleSupplier getX) {
    this.getY = getY;
    this.getX = getX;
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  /**
   * Low level driving
   * 
   * @param left  left set
   * @param right right set
   */
  public static void drive(double left, double right) {
    leftSet = left;
    rightSet = right;
  }

  /**
   * Changes state
   * 
   * @param controlState target state
   */
  public void changeState(ControlState controlState) {
    this.controlState = controlState;
  }

  /**
   * Gets state
   * 
   * @return state
   */
  public ControlState getState() {
    return controlState;
  }

  /**
   * Drives manually or according to the values passed
   */
  @Override
  public void execute() {
    switch (controlState) {
      case MANUAL: {
        m_drivetrain.drive(getY.getAsDouble(), getX.getAsDouble());
        return;
      }
      default: {
        m_drivetrain.lowLevelDrive(leftSet, rightSet);
        return;
      }
    }
  }
}
