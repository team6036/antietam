/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.subsystems.AccumulatorSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.commands.HoodCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.InstantCommand;

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

  private final JoystickButton m_xButton = new JoystickButton(m_controller, 3);
  private final JoystickButton m_aButton = new JoystickButton(m_controller, 1);
  private final JoystickButton m_threePointButton = new JoystickButton(m_joystick, 11);

  private final AccumulatorSubsystem m_accumulatorSubsystem = new AccumulatorSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
  private final HoodSubsystem m_hoodSubsystem = new HoodSubsystem();

  private final DrivetrainCommand m_drivetrainCommand = new DrivetrainCommand(m_drivetrainSubsystem,
      () -> m_joystick.getY(), () -> m_joystick.getX());
  private final ShooterCommand m_shooterCommand = new ShooterCommand(m_shooterSubsystem,
      () -> m_controller.getTriggerAxis(Hand.kRight));
  private final TurretCommand m_turretCommand = new TurretCommand(m_turretSubsystem,
      () -> m_controller.getY(Hand.kLeft));
  private final HoodCommand m_hoodCommand = new HoodCommand(m_hoodSubsystem, () -> m_controller.getY(Hand.kRight));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   * 
   * @NOTE: Do not touch the scheduler call unless you know what you're doing.
   */
  public RobotContainer() {
    CommandScheduler.getInstance().setDefaultCommand(m_drivetrainSubsystem, m_drivetrainCommand);
    CommandScheduler.getInstance().setDefaultCommand(m_shooterSubsystem, m_shooterCommand);
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
    m_xButton.whenPressed(new InstantCommand(() -> manualTarget()));
    m_aButton.whenPressed(new InstantCommand(() -> m_hoodCommand.zero()));
    m_threePointButton.whenPressed(new InstantCommand(()->threePoint()));
  }

  private void threePoint() {
    TurretCommand.threePoint();
  }

  private void manualTarget() {
    m_hoodCommand.changeState();
    TurretCommand.manual();
  }

}
