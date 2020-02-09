package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;





public class AccumulatorSubsystem extends SubsystemBase {
  public VictorSP motorONE = new VictorSP(RobotContainer.MotorONEID);
  public VictorSP motorTWO = new VictorSP(RobotContainer.MotorTWOID);
  public VictorSP motorTHREE = new VictorSP(RobotContainer.MotorTHREEID);
  
  
  
  private DoubleSolenoid solenoid1 = Constants.solenoidLiftFront;
  private DoubleSolenoid solenoid2 = Constants.solenoidLiftBack;
  public static boolean sensor;
  public final DigitalInput LineBreakSensor= new DigitalInput(RobotContainer.LineBreakSensorID);  //Idk if it should be digital input or output

  /**
   * Creates a new ExampleSubsystem.
   */

  public void setpower(double motorpowerone, double motorpowertwo, double motorpowerthree) {
      motorONE.set(motorpowerone);
      motorTWO.set(motorpowertwo);
      motorTHREE.set(motorpowerthree);
      
  
  }
  public void stoppower(){
    motorONE.set(0);
    motorTWO.set(0);
    motorTHREE.set(0);

}


  public enum State {
    EXTENDED, RETRACTED
  }
  private State state = State.RETRACTED;
  
  public boolean getSensorState() {
      return LineBreakSensor.get();   //get() returns True False
  }

  

  public void extend() {
    solenoid1.set(DoubleSolenoid.Value.kForward);
    solenoid2.set(DoubleSolenoid.Value.kForward);
    state = State.EXTENDED;
  }
  public void retract() {
    solenoid1.set(DoubleSolenoid.Value.kReverse);
    solenoid2.set(DoubleSolenoid.Value.kReverse);
    state = State.RETRACTED;
  }
 
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  }
  