/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

// the below imports are for the logging command
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
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


  /**
   * The 2 methods below are used to log messages to a text file on the roborio's memory
   * 
   * Example Use: logShit("log", "this is an example message")
   * Example Output: "2020/02/13 14:58:14 - example message"
   * 
   * default value of filename should be log unless specifically looking at one value in which case you can use "accelerationLog" or something like that
   */

   // writes to log
   public static void logShit(String filename, String message) {
    try {
      File dir = new File("/home/admin/logs/");
      FileWriter fw = new FileWriter(new File(dir, filename + ".txt"), true);
      fw.write(currentTime() + message + "\n");
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
   }

   // returns the current date and time as a string in the format "yyyy/MM/dd HH:mm:ss - "
   public static String currentTime() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss - ");
    return dtf.format(LocalDateTime.now());
  }

  /**
   * The 2 methods below are used to log messages to a text file on the roborio's memory
   * 
   * Example Use: logShit("log", "this is an example message")
   * Example Output: "2020/02/13 14:58:14 - example message"
   * 
   * default value of filename should be log unless specifically looking at one value in which case you can use "accelerationLog" or something like that
   */

   // writes to log
   public static void logShit(String filename, String message) {
    try {
      File dir = new File("/home/admin/logs/");
      FileWriter fw = new FileWriter(new File(dir, filename + ".txt"), true);
      fw.write(currentTime() + message + "\n");
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
   }

   // returns the current date and time as a string in the format "yyyy/MM/dd HH:mm:ss - "
   public static String currentTime() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss - ");
    return dtf.format(LocalDateTime.now());
  }
}
