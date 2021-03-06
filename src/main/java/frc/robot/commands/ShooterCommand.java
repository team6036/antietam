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
import frc.robot.Constants.ShooterConstants.BallStates;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterCommand extends CommandBase {
  // Constants
  private final boolean debug = ShooterConstants.debug;
  private final double kp = ShooterConstants.kp;

  // Controllers
  private final ShooterSubsystem m_shooterSubsystem;
  private DoubleSupplier m_rTrigger;

  private boolean ballSensorIn = true;

  // Other
  private ArrayList<BallStates> ballStates = new ArrayList<>(
      Arrays.asList(new BallStates[] { BallStates.CONTAINED, BallStates.CONTAINED, BallStates.CONTAINED }));
  private PIDController pidController = new PIDController(kp, 0, 0);

  public ShooterCommand(ShooterSubsystem shooterSubsystem, DoubleSupplier rTrigger) {
    m_shooterSubsystem = shooterSubsystem;
    this.m_rTrigger = rTrigger;
    balls = 0;
    addRequirements(shooterSubsystem); // @TODO this for all commands and respective key subsystems
  }

  public void addBall() {
    ballStates.add(BallStates.CONTAINED);
  }

  @Override
  public void execute() {
    if (debug) {
      m_shooterSubsystem.debug();
    }
    // if ball is contained or waiting, makes sure it doesnt come out
    // containBall();
    // if ball is ready, shoot, if it has left, remove it
    // shootBall();
    // if ball is contained, ramp up, if contained, stop
    // setVelocity();
    // if past a threshold, set all to waiting, if medium, only one, if low, do
    // nothing
    // handleJoystick();
    // if no balls, stop
    // reset();
    if (m_rTrigger.getAsDouble() < 0.05) {
      m_shooterSubsystem.setShooterVelocity(0.0);
      m_shooterSubsystem.setHopupVelocity(0.0);
    } else {
      m_shooterSubsystem.setShooterVelocity(m_rTrigger.getAsDouble());
      m_shooterSubsystem.setHopupVelocity(-0.99);
      // m_shooterSubsystem.setHopupVelocity(-0.5);
      SmartDashboard.putNumber("Shooter velocity", m_shooterSubsystem.getShooterVelocity());

    }
    m_shooterSubsystem.debug();
    countBall();

  }

  private int balls = 0;

  private void countBall() {
    SmartDashboard.putNumber("balls in", balls);
    if (!ballSensorIn) {
      if (m_shooterSubsystem.getLineBreak()) {
        balls++;

      }
    }
    ballSensorIn = m_shooterSubsystem.getLineBreak();
  }

  /**
   * handleBall() makes sure nothing comes out, spins backwards if it do, only
   * when contained or waiting, if ball is ready, feeds ball, once it detects it,
   * sets ball to leaving If ball is leaving, and no longer being detected,
   * deletes it
   */
  private void handleBall() {
    switch (ballStates.get(0)) {
      case CONTAINED: {
        if (m_shooterSubsystem.getLineBreak()) {
          m_shooterSubsystem.setHopupVelocity(-0.1);
        }
        break;
      }
      case WAITING: {
        if (m_shooterSubsystem.getLineBreak()) {
          m_shooterSubsystem.setHopupVelocity(-0.1);
        }
        break;
      }
      case READY: {
        m_shooterSubsystem.setHopupVelocity(0.1); // TODO change this to something reasonable
        if (m_shooterSubsystem.getLineBreak()) {
          ballStates.set(0, BallStates.LEAVING);
        }
        break;
      }
      case LEAVING: {
        if (!m_shooterSubsystem.getLineBreak()) {
          ballStates.remove(0);
        }
        break;
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
        pidController.setSetpoint(0.1); // TODO this needs to be changed to target speed
        if (pidController.getSetpoint() == m_shooterSubsystem.getShooterVelocity()) {
          ballStates.set(0, BallStates.READY);
        } else {
          m_shooterSubsystem.setShooterVelocity(pidController.calculate(m_shooterSubsystem.getShooterVelocity()));
        }
        break;
      }
      case READY: {
        pidController.setSetpoint(0.1); // TODO this needs to be changed to target speed
        if (pidController.getSetpoint() == m_shooterSubsystem.getShooterVelocity()) {
          ballStates.set(0, BallStates.READY);
        } else {
          m_shooterSubsystem.setShooterVelocity(pidController.calculate(m_shooterSubsystem.getShooterVelocity()));
        }
        break;
      }
      case CONTAINED: {
        pidController.setSetpoint(0);
        break;
      }
      default: {
        break;
      }
    }
  }

  /**
   * HandleJoystick() if after a certain threshold, set first to waiting, if after
   * another, set all to waiting
   */
  private void handleJoystick() {
    if (m_rTrigger.getAsDouble() > 0.5) {
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
  private void reset() {
    if (ballStates.get(0) == BallStates.CONTAINED) {
      m_shooterSubsystem.setShooterVelocity(0);
    }
  }
}