package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.AccumulatorSubsystem;
import frc.robot.Constants.AccumulatorConstants;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.Consumer;

//TODO ball tube, both linebreaks, in shooter
public class AccumulatorCommand extends CommandBase {
  // Constants
  private boolean debug = AccumulatorConstants.debug;

  // Hardware
  private final AccumulatorSubsystem m_accumulator;
  private final DoubleSupplier m_xbox;

  /**
   * Basic constructor
   * 
   * @param accumulator the subsystem
   * @param supplier    xbox trigger
   * @param addBall
   */
  public AccumulatorCommand(AccumulatorSubsystem accumulator, DoubleSupplier supplier, Consumer<Boolean> addBall) {
    m_accumulator = accumulator;
    m_xbox = supplier;

    addRequirements(accumulator);
  }

  /**
   * Runs the accumulator if trigger is pressed, if else, set it to stop
   */
  @Override
  public void execute() {
    if (debug) {
      m_accumulator.debug();
    }
    if (m_xbox.getAsDouble() > 0.05) {

      m_accumulator.rollerSet(Constants.AccumulatorConstants.rollerMotorPower);
      m_accumulator.serializerSet(Constants.AccumulatorConstants.serializerMotorPower);
      m_accumulator.ballTubeSet(Constants.AccumulatorConstants.ballTubeMotorPower);
    } else {
      m_accumulator.rollerSet(0.0);
      m_accumulator.serializerSet(0.0);
      m_accumulator.ballTubeSet(0.0);
    }
  }
}
