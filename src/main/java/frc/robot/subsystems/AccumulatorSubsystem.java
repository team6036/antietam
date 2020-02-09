package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AccumulatorSubsystem extends SubsystemBase {
  public VictorSP flywheelMotor = new VictorSP(Constants.MotorONEID);
  public VictorSP ballAlignmentMotor = new VictorSP(Constants.MotorTWOID);
  public VictorSP hopUpMotor = new VictorSP(Constants.MotorTHREEID);

  public static DoubleSolenoid solenoid1 = new DoubleSolenoid(Constants.portSolenoid2A, Constants.portSolenoid2B);
  public static DoubleSolenoid solenoid2 = new DoubleSolenoid(Constants.portSolenoid3A, Constants.portSolenoid3B);

  public static boolean sensor;
  public final DigitalInput LineBreakSensor = new DigitalInput(Constants.LineBreakSensorID);

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
