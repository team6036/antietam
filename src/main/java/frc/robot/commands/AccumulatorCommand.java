/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.AccumulatorSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Consumer;

//TODO ball tube, both linebreaks, in shooter
/**
 * An example command that uses an example subsystem.
 */
public class AccumulatorCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final AccumulatorSubsystem m_accumulator;
  private final DoubleSupplier m_xbox;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AccumulatorCommand(AccumulatorSubsystem accumulator, DoubleSupplier supplier, Consumer<Boolean> addBall) {
    m_accumulator = accumulator;
    m_xbox = supplier;

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
    // m_accumulator.flywheelMotor.set(0.5);
    // System.out.println("Running");
    // if trigger is being pressed, run flywheel and ballAlignment motors
    if (m_xbox.getAsDouble() > 0.05) {

      m_accumulator.rollerMotor.set(Constants.AccumulatorConstants.rollerMotorPower);
      m_accumulator.serializerMotor.set(Constants.AccumulatorConstants.serializerMotorPower);
    } else {
      // trigger is not being pressed, so stop motor power
      if (m_accumulator.rollerMotor.get() != 0.0) {
        m_accumulator.rollerMotor.set(0.0);
        m_accumulator.serializerMotor.set(0.0);
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
