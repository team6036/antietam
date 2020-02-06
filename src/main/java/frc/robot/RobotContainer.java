/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.commands.ControlPanelCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.ControlPanel.Buttons;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  int leftPort = 1;
  int rightPort = 15;
  int jport = 0;
  private final Joystick m_joystick = new Joystick(jport);
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem(leftPort, rightPort);
  private final DrivetrainCommand m_driveCommand = new DrivetrainCommand(m_drivetrainSubsystem, m_joystick);
  private final ControlPanelSubsystem m_controlpanelSubsystem = new ControlPanelSubsystem();;
  private final ControlPanelCommand m_controlpanelCommand = new ControlPanelCommand(m_controlpanelSubsystem);
  private final JoystickButton controlPanelCommandButton = new JoystickButton(m_joystick, Buttons.NORMAL);


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
    controlPanelCommandButton.whenPressed(m_controlpanelCommand);
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


  public void scheduleControlPanelCommands() {
    if (m_controlpanelCommand != null)
      m_controlpanelCommand.schedule();
  }
}
