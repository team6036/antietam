package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.AccumulatorConstants;

public class AccumulatorSubsystem extends SubsystemBase {
  public VictorSP rollerMotor = new VictorSP(Constants.AccumulatorConstants.rollerMotorPort);
  public VictorSP serializerMotor = new VictorSP(Constants.AccumulatorConstants.serializerMotorPort);
  public VictorSP ballTubeMotor = new VictorSP(AccumulatorConstants.ballTubeMotorPort);

  public static DoubleSolenoid solenoid1 = new DoubleSolenoid(Constants.AccumulatorConstants.solenoid1APort,
      Constants.AccumulatorConstants.solenoid1BPort);
  public static boolean sensor;

  public enum State {
    EXTENDED, RETRACTED
  }

  private State state = State.RETRACTED;

  public void extend() {
    if (state == State.EXTENDED) {
      return;
    }
    solenoid1.set(DoubleSolenoid.Value.kForward);
    state = State.EXTENDED;
  }

  public void retract() {
    if (state == State.RETRACTED) {
      return;
    }

    solenoid1.set(DoubleSolenoid.Value.kReverse);
    state = State.RETRACTED;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void reset() {

  }
}
