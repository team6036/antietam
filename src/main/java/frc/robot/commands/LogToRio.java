/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class LogToRio extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * Creates a new LogToRio.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LogToRio() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // temp fields (will hopefully become argumments to the method)
    String filename = "log";
    String message = "hello";

    try {
      File dir = new File("/home/admin/logs/");
      FileWriter fw = new FileWriter(new File(dir, filename + ".txt"), true);
      fw.write(currentTime() + message + "\n");
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String currentTime() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss - ");
    return dtf.format(LocalDateTime.now());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
