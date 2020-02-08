/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AccumulatorCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.AccumulatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final AccumulatorSubsystem m_accumulatorSubsystem = new AccumulatorSubsystem();

  
  

  private final static int joystickPort = 5;
  public final static int MotorONEID = 1;
  public final static int MotorTWOID = 2;
  public final static int MotorTHREEID = 3;
  public final static int LineBreakSensorID = 4;
  public final static int portSolenoid2A = 6;
  public final static int portSolenoid3A = 7;    //All random numbers FYI
  public final static int portSolenoid2B = 8;
  public final static int portSolenoid3B = 9;

  public final static XboxController Xbox = new XboxController(RobotContainer.joystickPort);

  private final AccumulatorCommand m_accumulatorCommand = new AccumulatorCommand(m_accumulatorSubsystem, Xbox);
  
  
  private final JoystickButton LeftBumper = new JoystickButton(Xbox, 5);
  private final JoystickButton RightBumper = new JoystickButton(Xbox, 6);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    CommandScheduler.getInstance().setDefaultCommand(m_accumulatorSubsystem, m_accumulatorCommand);
    
    // Configure the button bindings
    configureButtonBindings();
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    LeftBumper.whenPressed(new InstantCommand(() -> m_accumulatorSubsystem.extend()));
    RightBumper.whenPressed(new InstantCommand(() -> m_accumulatorSubsystem.retract()));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
