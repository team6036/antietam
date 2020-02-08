/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Constants.PortConstants;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.commands.ControlPanelCommand;
import frc.robot.commands.PistonExtensionCommand;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.CommandScheduler;




/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XboxController xbox = new XboxController(PortConstants.xboxControllerPort);
  private final JoystickButton xbox_y = new JoystickButton(xbox, 4);
  private final JoystickButton xbox_b = new JoystickButton(xbox, 2);
  private final ControlPanelSubsystem m_controlpanelSubsystem = new ControlPanelSubsystem();
  private final ControlPanelCommand m_controlpanelCommand = new ControlPanelCommand(m_controlpanelSubsystem);
  private final PistonExtensionCommand m_pistonExtensionCommand = new PistonExtensionCommand(m_controlpanelSubsystem);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // CommandScheduler.getInstance().setDefaultCommand(m_drivetrainSubsystem, m_driveCommand);
    CommandScheduler.getInstance().registerSubsystem(m_controlpanelSubsystem);
    configureButtonBindings();
  }


  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    xbox_y.whenPressed(m_pistonExtensionCommand);
    xbox_b.whenPressed(m_controlpanelCommand);
  }
}
