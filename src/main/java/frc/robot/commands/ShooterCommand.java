/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.jStickListener;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
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
  private jStickListener m_rTrigger;

  private Velocity velocity = Velocity.MINIMUM;
  private ArrayList<BallStates> ballStates = new ArrayList<>(
      Arrays.asList(new BallStates[] { BallStates.CONTAINED, BallStates.CONTAINED, BallStates.CONTAINED }));
  private double shooterError, shooterPower, shooterTargetVelocity = 0;
  private PIDController pidController = new PIDController(kp, 0, 0);

  public ShooterCommand(ShooterSubsystem shooterSubsystem, jStickListener rTrigger) {
    m_shooterSubsystem = shooterSubsystem;
    this.m_rTrigger = rTrigger;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterSubsystem); // @TODO this for all commands and respective key subsystems
  }

  public void setTargetVelocity(double velocity) {
    pidController.setSetpoint(velocity);
    this.velocity=Velocity.ACCELERATING;
  }

  public void containBall() {
    if ((ballStates.get(0) == BallStates.CONTAINED || ballStates.get(0) == BallStates.LEAVING)
        && m_shooterSubsystem.getLineBreak()) {
      m_shooterSubsystem.setHopupVelocity(-0.1);
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (debug) {
      m_shooterSubsystem.debug();
    }
    /*
     * if(rTrigger.getY()>=0.05){ m_shooterSubsystem.shoot(); }
     */
    m_shooterSubsystem.setShooterVelocity(pidController.calculate(m_shooterSubsystem.getShooterVelocity()));
    if (pidController.getSetpoint() > 0 && m_shooterSubsystem.getShooterVelocity() != pidController.getSetpoint()) {
      velocity = Velocity.ACCELERATING;
    } else if (pidController.getSetpoint() == m_shooterSubsystem.getShooterVelocity()) {
      velocity = Velocity.TARGET;
    }
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
}

/**
 * SPEC: Add a setTargetVelocity function, which implements the WPILIB PID class
 * for setting a target velocity, while in PID set state to ACCELERATING, when
 * target velocity is reached set state to TARGET Add a containBall function,
 * which calls getLineBreakSensor and checks state of the first object in
 * ballStates == CONTAINED or WAITING, if getLineBreakSensor returns true then
 * setBallHopperVelocity backwards
 * 
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