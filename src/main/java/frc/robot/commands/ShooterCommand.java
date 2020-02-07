/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterCommand extends CommandBase {
  private static enum Velocity {
    MINIMUM, ACCELERATING, TARGET;
  }

  private static enum BallStates {
    CONTAINED, WAITING, READY, LEAVING;
  }

  private final boolean debug = ShooterConstants.debug;
  private final double kp = ShooterConstants.kp;

  private final ShooterSubsystem m_shooterSubsystem;
  private DoubleSupplier m_rTrigger;

  private Velocity velocity = Velocity.MINIMUM;
  private ArrayList<BallStates> ballStates = new ArrayList<>(
      Arrays.asList(new BallStates[] { BallStates.CONTAINED, BallStates.CONTAINED, BallStates.CONTAINED }));
  private PIDController pidController = new PIDController(kp, 0, 0);

  public ShooterCommand(ShooterSubsystem shooterSubsystem, DoubleSupplier rTrigger) {
    m_shooterSubsystem = shooterSubsystem;
    this.m_rTrigger = rTrigger;
    addRequirements(shooterSubsystem); // @TODO this for all commands and respective key subsystems
  }

}

/**
 * SPEC: Add a setTargetVelocity function, which implements the WPILIB PID class
 * for setting a target velocity, while in PID set state to ACCELERATING, when
 * target velocity is reached set state to TARGET Add a containBall function,
 * which calls getLineBreakSensor and checks state of the first object in
 * ballStates == CONTAINED or WAITING, if getLineBreakSensor returns true then
 * setBallHopperVelocity backward
 */
/**
 * Add a feedBall function, which checks state of first object in ballStates ==
 * READY OR LEAVING, if first object in ballStates == READY, check if line break
 * sensor is impeded -> true then set state to leaving, false then run motors
 * slightly forward if first object in ballStates == LEAVING, check if line
 * break sensor is impeded -> true then run motors slightly forward, false then
 * delete first object(ball has left)
 * 
 * Add a handleJoystickValue function: constantly check RT, if it's above dead
 * band then: if below 0.5 -> set first object in ballStates to WAITING if above
 * 0.5 -> set all objects in ballStates to WAITING
 * 
 * Add a shootBall function: if first object in ballStates is WAITING and
 * velocity is MINIMUM, then setTargetVelocity to a random number(for now) if
 * velocity is TARGET, set first object in ballStates to READY
 * 
 * Add a reset function: (if first object in ballStates is contained or
 * ballStates is empty) and shooter is at target, then call
 * setShooterVelocity(minVelocity), change state to MINIMUM
 * 
 * Lastly, just call containBall, handleJoystickValue, feedBall, shootBall,
 * reset in execute
 */
