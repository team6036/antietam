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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class AccumulatorCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final AccumulatorSubsystem m_accumulator;
  private final XboxController m_xbox;
  private static boolean PreviousValue = false;
  private static boolean PresentValue;
  private static int ballsEntered = 0;
  
  
 


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AccumulatorCommand(AccumulatorSubsystem accumulator, XboxController XboxControl) {
    m_accumulator = accumulator;
    m_xbox = XboxControl;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(accumulator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
  public void Unjam() {
      m_accumulator.setpower(-0.1, -0.1,-0.1);
  }
  
  public void flywheels_fowards() {
      m_accumulator.setpower(0.1,0.1,0.1);
  }
  
  public void RetractAccumulator(){
      m_accumulator.retract();
      
  }

  public void ExtendAccumulator(){
    m_accumulator.extend();

  }

  @Override
  public void end(boolean interrupted) {
      m_accumulator.setpower(0,0,0);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if (RobotContainer.Xbox.getTriggerAxis(Hand.kRight) > 0.05) {
          flywheels_fowards();
      }
      else if (RobotContainer.Xbox.getTriggerAxis(Hand.kLeft) > 0.05) {
          Unjam();
      }
      else {
          end(true);   //idk what happens if u put false here
      if (PreviousValue = true){
          PresentValue = m_accumulator.LineBreakSensor.get();
          if (PresentValue = false){
              PreviousValue = false;
          }
      if (PreviousValue = false){
          PresentValue = m_accumulator.LineBreakSensor.get();
          if (PresentValue = true){
                PreviousValue = true;
                ballsEntered += 1;
            }
          
      }
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
