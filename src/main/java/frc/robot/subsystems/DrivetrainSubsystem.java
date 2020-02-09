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

    private CANSparkMax frontLeftMotor;
    private CANSparkMax backLeftMotor;

    private CANSparkMax frontRightMotor;
    private CANSparkMax backRightMotor;

    private double Kp = 0.00001;
    private double currentDistance;
    private DifferentialDrive drivetrain;


    // private final DifferentialDrive Drivetrain;

    /**
     * Creates a new ExampleSubsystem.
     */
    public DrivetrainSubsystem() {
        frontLeftMotor = new CANSparkMax(frontleftmotorPORT, MotorType.kBrushless);
        backLeftMotor = new CANSparkMax(backleftmotorPORT, MotorType.kBrushless);
        frontRightMotor = new CANSparkMax(frontrightmotorPORT, MotorType.kBrushless);
        backRightMotor = new CANSparkMax(backrightmotorPORT, MotorType.kBrushless);

        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

        drivetrain = new DifferentialDrive(frontLeftMotor, frontRightMotor);
    }

    public void drive(double forward, double turn) {
        drivetrain.arcadeDrive(forward, turn);
    }

    public void lowLevelDrive(double left, double right) {
        frontLeftMotor.set(left);
        frontRightMotor.set(right);
    }

    public void debug() {
        SmartDashboard.putNumber("leftDist", frontLeftMotor.getEncoder().getVelocity());
        SmartDashboard.putNumber("rightDist", frontRightMotor.getEncoder().getVelocity());
    }

}
