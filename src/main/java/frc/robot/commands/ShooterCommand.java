/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.DoubleSupplier;
import frc.robot.Constants.ShooterConstants.BallStates;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterCommand extends CommandBase {

  private final boolean debug = ShooterConstants.debug;
  private final double kp = ShooterConstants.kp;

  private final ShooterSubsystem m_shooterSubsystem;
  private DoubleSupplier m_rTrigger;

  private boolean ballSensorIn = true;

  public ArrayList<BallStates> ballStates = new ArrayList<>(
      Arrays.asList(new BallStates[] { BallStates.CONTAINED, BallStates.CONTAINED, BallStates.CONTAINED }));
  // private PIDController pidController = new PIDController(kp, 0, 0);

  public ShooterCommand(ShooterSubsystem shooterSubsystem, DoubleSupplier rTrigger) {
    m_shooterSubsystem = shooterSubsystem;
    this.m_rTrigger = rTrigger;
    addRequirements(shooterSubsystem);
    // pidController.setTolerance(100); // @TODO this for all commands and
    // respective key subsystems
  }

  public void addBall() {
    ballStates.add(BallStates.CONTAINED);
  }

  @Override
  public void execute() {
    if (debug) {
      m_shooterSubsystem.debug();
      SmartDashboard.putString("value", ballStates.get(0).toString());
    }
    // // if ball is contained or waiting, makes sure it doesnt come out
    // containBall();
    handleJoystick();
    // // if ball is ready, shoot, if it has left, remove it
    countBall();
    shootBall();
    // // if ball is contained, ramp up, if contained, stop
    setVelocity();
    // // if past a threshold, set all to waiting, if medium, only one, if low, do
    // nothing
    // // if no balls, stop
    reset();
  }

  private void countBall() {
    if (!ballSensorIn) {
      if (m_shooterSubsystem.getLineBreakIn()) {
        m_shooterSubsystem.incrementBalls();
      }
    } 
    ballSensorIn = m_shooterSubsystem.getLineBreakIn();
  }

  /**
   * CointainBall() makes sure nothing comes out, spins backwards if it do, only
   * when contained or waiting
   */
  private void containBall() {
    switch (ballStates.get(0)) {
    case CONTAINED: {
      if (m_shooterSubsystem.getLineBreakOut()) {
        m_shooterSubsystem.setHopupVelocity(0);
      }
      return;
    }
    case WAITING: {
      if (m_shooterSubsystem.getLineBreakOut()) {
        m_shooterSubsystem.setHopupVelocity(-0.1);
      }
      return;
    }
    default: {
      return;
    }
    }
  }

  /**
   * Shootball() If ball is ready, feeds ball, once it detects it, sets ball to
   * leaving If ball is leaving, and no longer being detected, deletes it
   */
  private void shootBall() {
    switch (ballStates.get(0)) {
    case READY: {
      m_shooterSubsystem.setHopupVelocity(-0.4); // TODO change this to final
      if (m_shooterSubsystem.getLineBreakOut()) {
        ballStates.set(0, BallStates.LEAVING);
      }
      return;
    }
    case LEAVING: {
      if (!m_shooterSubsystem.getLineBreakOut()) {
        ballStates.remove(0);
      }
      return;
    }
    default: {
      return;
    }
    }

  }

  /**
   * SetVelocity() sets velocity if waiting, if at speed, sets velocity to target
   * and ball to ready might be good to integrate the ball part to shootball
   */
  private void setVelocity() {
    switch (ballStates.get(0)) {
    case WAITING: {
      m_shooterSubsystem.setShooterVelocity(1);
      // pidController.setSetpoint(4500); // TODO this needs to be changed to target
      // speed
      // if (pidController.getSetpoint() == m_shooterSubsystem.getShooterVelocity()) {
      if (m_shooterSubsystem.getShooterVelocity() >= 4000) {
        ballStates.set(0, BallStates.READY);
      }
      /*
       * } else { SmartDashboard.putNumber("shooter velocity",
       * pidController.calculate(m_shooterSubsystem.getShooterVelocity()));
       * m_shooterSubsystem.setShooterVelocity(pidController.calculate(
       * m_shooterSubsystem.getShooterVelocity())); } return;
       */
      break;
    }
    case CONTAINED: {
      // pidController.setSetpoint(0);
      // m_shooterSubsystem.setShooterVelocity(0);
      m_shooterSubsystem.setShooterVelocity(0);;
      m_shooterSubsystem.setHopupVelocity(0);
      return;
    }
    default: {
      return;
    }
    }
  }

  /**
   * HandleJoystick() if after a certain threshold, set first to waiting, if after
   * another, set all to waiting
   */
  public void handleJoystick() {
    if (m_rTrigger.getAsDouble() > 0.5) {
      System.out.println("Engaging all");
      for (int i = 0; i < ballStates.size(); i++) {
        ballStates.set(i, BallStates.WAITING);
      }
      TurretCommand.twoPoint();
    } else if (m_rTrigger.getAsDouble() > 0.1) {
      ballStates.set(0, BallStates.WAITING);
      TurretCommand.twoPoint();
    }
  }

  /**
   * reset() if next is contained, set velocity to minimum
   */
  public void reset() {
    if (ballStates.get(0) == BallStates.CONTAINED) {
      System.out.println("Containing");
      // m_shooterSubsystem.setShooterVelocity(0);
    }
  }
}