package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AccumulatorConstants;
import frc.robot.Constants.AccumulatorConstants.ExtendState;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AccumulatorSubsystem extends SubsystemBase {
  // Constants
  private int rollerMotorPort = AccumulatorConstants.rollerMotorPort;
  private int serializerMotorPort = AccumulatorConstants.serializerMotorPort;
  private int ballTubeMotorPort = AccumulatorConstants.ballTubeMotorPort;
  private int solenoid1APort = AccumulatorConstants.solenoid1APort;
  private int solenoid1BPort = AccumulatorConstants.solenoid1BPort;

  // Hardware
  private VictorSP rollerMotor; // TODO check
  private VictorSP serializerMotor; // TODO check
  private VictorSP ballTubeMotor; // TODO check
  private DoubleSolenoid solenoid; // TODO check

  // Others
  private ExtendState state = ExtendState.RETRACTED;

  /**
   * Basic constructor
   */
  public AccumulatorSubsystem() {
    rollerMotor = new VictorSP(rollerMotorPort);
    serializerMotor = new VictorSP(serializerMotorPort);
    ballTubeMotor = new VictorSP(ballTubeMotorPort);
    solenoid = new DoubleSolenoid(solenoid1APort, solenoid1BPort);
  }

  /**
   * Hardware feedback
   */
  public void debug(){
    SmartDashboard.putBoolean("Extended", (state.equals(ExtendState.EXTENDED)));
  }

  /**
   * Sets roller motor power
   * 
   * @param set
   */
  public void rollerSet(double set) {
    rollerMotor.set(set);
  }

  /**
   * Sets serializer motor power
   * 
   * @param set
   */
  public void serializerSet(double set) {
    serializerMotor.set(set);
  }

  /**
   * Sets balltube motor power
   * 
   * @param set
   */
  public void ballTubeSet(double set) {
    ballTubeMotor.set(set);
  }

  /**
   * Extends accumulator arm
   */
  public void extend() {
    if (state == ExtendState.EXTENDED) {
      return;
    }
    solenoid.set(Value.kForward);
    state = ExtendState.EXTENDED;
  }

  /**
   * Retracts accumulator arm
   */
  public void retract() {
    if (state == ExtendState.RETRACTED) {
      return;
    }

    solenoid.set(Value.kReverse);
    state = ExtendState.RETRACTED;
  }
}
