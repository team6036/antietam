/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DrivetrainSubsystem extends SubsystemBase {
    private static final int frontleftmotorPORT = DrivetrainConstants.frontleftmotorPORT;
    private static final int backleftmotorPORT = DrivetrainConstants.backleftmotorPORT;
    private static final int frontrightmotorPORT = DrivetrainConstants.frontrightmotorPORT;
    private static final int backrightmotorPORT = DrivetrainConstants.backrightmotorPORT;

    private CANSparkMax frontleftmotor;
    private CANSparkMax backleftmotor;

    private CANSparkMax frontrightmotor;
    private CANSparkMax backrightmotor;

    private double Kp = 0.00001;
    private double currentDistance;

    // private final DifferentialDrive Drivetrain;

    /**
     * Creates a new ExampleSubsystem.
     */
    public DrivetrainSubsystem() {
        frontleftmotor = new CANSparkMax(frontleftmotorPORT, MotorType.kBrushless);
        backleftmotor = new CANSparkMax(backleftmotorPORT, MotorType.kBrushless);
        frontrightmotor = new CANSparkMax(frontrightmotorPORT, MotorType.kBrushless);
        backrightmotor = new CANSparkMax(backrightmotorPORT, MotorType.kBrushless);

        backleftmotor.follow(frontleftmotor);
        backrightmotor.follow(frontrightmotor);

        // Drivetrain = new DifferentialDrive (frontleftmotor, frontrightmotor);

    }

    public void drive(double y, double x) {
        // Drivetrain.arcadeDrive(y,x);
        frontleftmotor.set(y);
        frontrightmotor.set(y);
        SmartDashboard.putNumber("leftDist", frontleftmotor.getEncoder().getVelocity());
        SmartDashboard.putNumber("rightDist", frontrightmotor.getEncoder().getVelocity());
    }

}
