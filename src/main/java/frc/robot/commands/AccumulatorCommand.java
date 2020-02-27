/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.AccumulatorSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Consumer;

/**
 * An example command that uses an example subsystem.
 */
public class AccumulatorCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final AccumulatorSubsystem m_accumulator;
  private final DoubleSupplier m_xbox;
  private final Consumer<Boolean> m_addBall;

  private static boolean lineBreakSensor = false;
  private static int ballsEntered = 0;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AccumulatorCommand(AccumulatorSubsystem accumulator, DoubleSupplier supplier, Consumer<Boolean> addBall) {
    m_accumulator = accumulator;
    m_xbox = supplier;
    m_addBall = addBall;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(accumulator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  public void end(boolean interrupted) {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (lineBreakSensor) {
      // The hop up motor should only run when there is a ball impeding the sensor.
      // lineBreakSensor is the previous value of the sensor
      if (m_accumulator.getSensorState()) {
        // previously the sensor was being impeded, and it is still currently being
        // impeded, so run hopUpMotor
        m_accumulator.hopUpMotor.set(0.1);
      } else {
        // previsouly the sensor was impeded, it is no longer being impeded, meaning a
        // ball has gone in
        ballsEntered++;
        
        m_addBall.accept(true);
      }

    } else {
      // if the sensor is not being impeded set motorPower to 0
      if (m_accumulator.hopUpMotor.get() != 0.0) {
        m_accumulator.hopUpMotor.set(0.0);
      }
    }
    // finally, update the previous lineBreakSensor value to match the
    // lineBreakSensor value
    lineBreakSensor = m_accumulator.getSensorState();

    // if trigger is being pressed, run flywheel and ballAlignment motors
    if (m_xbox.getAsDouble() > 0.05) {
      m_accumulator.flywheelMotor.set(0.1);
      m_accumulator.ballAlignmentMotor.set(0.1);
    } else {
      // trigger is not being pressed, so stop motor power
      if (m_accumulator.flywheelMotor.get() != 0.0) {
        m_accumulator.flywheelMotor.set(0.0);
      }
    }
  }

  // Called once the command ends or is interrupted.

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
