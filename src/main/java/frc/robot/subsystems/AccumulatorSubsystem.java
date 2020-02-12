package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AccumulatorSubsystem extends SubsystemBase {
  public VictorSP flywheelMotor = new VictorSP(Constants.AccumulatorConstants.flywheelMotorPort);
  public VictorSP ballAlignmentMotor = new VictorSP(Constants.AccumulatorConstants.ballAlignmentMotorPort);
  public VictorSP hopUpMotor = new VictorSP(Constants.AccumulatorConstants.hopUpMotorPort);

  public static DoubleSolenoid solenoid1 = new DoubleSolenoid(Constants.AccumulatorConstants.solenoid1APort,
      Constants.AccumulatorConstants.solenoid1BPort);
  public static DoubleSolenoid solenoid2 = new DoubleSolenoid(Constants.AccumulatorConstants.solenoid2APort,
      Constants.AccumulatorConstants.solenoid2BPort);

  public static boolean sensor;
  public final DigitalInput LineBreakSensor = new DigitalInput(Constants.AccumulatorConstants.lineBreakSensorPort);

  public enum State {
    EXTENDED, RETRACTED
  }

  private State state = State.RETRACTED;

  public boolean getSensorState() {
    return LineBreakSensor.get(); // get() returns True False
  }

  public void extend() {
    if (state == State.EXTENDED) {
      return;
    }
    solenoid1.set(DoubleSolenoid.Value.kForward);
    solenoid2.set(DoubleSolenoid.Value.kForward);
    state = State.EXTENDED;
  }

  public void retract() {
    if (state == State.RETRACTED) {
      return;
    }

    solenoid1.set(DoubleSolenoid.Value.kReverse);
    solenoid2.set(DoubleSolenoid.Value.kReverse);
    state = State.RETRACTED;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
