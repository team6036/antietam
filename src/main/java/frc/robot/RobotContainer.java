/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.AccumulatorSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.LimelightCommand;
import frc.robot.commands.ShooterCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Joystick m_joystick = new Joystick(Constants.joystickPort);
  private final XboxController m_controller = new XboxController(Constants.xboxPort);

  private final AccumulatorSubsystem m_accumulatorSubsystem = new AccumulatorSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final LimelightSubsystem m_limelightSubsystem = new LimelightSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem(); 

  private final DrivetrainCommand m_drivetrainCommand = new DrivetrainCommand(m_drivetrainSubsystem, m_joystick);
  private final LimelightCommand m_limelightCommand = new LimelightCommand(m_limelightSubsystem, m_drivetrainSubsystem,
      m_shooterSubsystem);
  private final ShooterCommand m_shooterCommand = new ShooterCommand(m_shooterSubsystem, m_controller);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   * 
   * @NOTE: Do not touch the scheduler call unless you know what you're doing.
   */
  public RobotContainer() {
    //CommandScheduler.getInstance().setDefaultCommand(m_drivetrainSubsystem, m_drivetrainCommand);
    //CommandScheduler.getInstance().setDefaultCommand(m_shooterSubsystem, m_shooterCommand);
    CommandScheduler.getInstance().setDefaultCommand(m_limelightSubsystem, m_limelightCommand);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

}
