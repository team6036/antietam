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
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {
  private static final int armPistonForwardPort = DrivetrainConstants.climberArmForwardPort;
  private static final int armPistonReversePort = DrivetrainConstants.climberArmReversePort;
  private static final int winchPistonForwardPort = DrivetrainConstants.climberWinchForwardPort;
  private static final int winchPistonReversePort = DrivetrainConstants.climberWinchReversePort;
  private DoubleSolenoid climberArmPiston;
  private DoubleSolenoid climberWinchPiston;
  /**
   * Creates a new ExampleSubsystem.
   */
  public DrivetrainSubsystem() {
    climberArmPiston = new DoubleSolenoid(armPistonForwardPort, armPistonReversePort);
    climberWinchPiston = new DoubleSolenoid(winchPistonForwardPort, winchPistonReversePort);
  }

  public void toggleClimberArmPiston(boolean extended) {
    if(extended) {
      if(climberArmPiston.get().equals(Value.kForward)) {
        climberArmPiston.set(Value.kReverse);
      }
    } else {
      if(climberArmPiston.get().equals(Value.kReverse)) {
        climberArmPiston.set(Value.kForward);
      }
    }
  }

  public void toggleClimberWinchPiston(boolean extended) {
    if(extended) {
      if(climberWinchPiston.get().equals(Value.kForward)) {
        climberWinchPiston.set(Value.kReverse);
      }
    } else {
      if(climberWinchPiston.get().equals(Value.kReverse)) {
        climberWinchPiston.set(Value.kForward);
      }
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
