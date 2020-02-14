/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
  private static final int armPistonForwardPort = ClimberConstants.climberArmForwardPort;
  private static final int armPistonReversePort = ClimberConstants.climberArmReversePort;
  private DoubleSolenoid armPiston;
  /**
   * Creates a new ExampleSubsystem.
   */
  public ClimberSubsystem() {
    armPiston = new DoubleSolenoid(armPistonForwardPort, armPistonReversePort);
  }

  public void toggleArmPiston(boolean extended) {
    if(extended) {
      if(armPiston.get().equals(Value.kForward)) {
        armPiston.set(Value.kReverse);
      }
    } else {
      if(armPiston.get().equals(Value.kReverse)) {
        armPiston.set(Value.kForward);
      }
    }
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
