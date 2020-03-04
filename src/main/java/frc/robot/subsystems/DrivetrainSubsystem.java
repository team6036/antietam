package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DrivetrainSubsystem extends SubsystemBase {
    //Constants
    private static final int frontleftmotorPORT = DrivetrainConstants.frontleftmotorPORT;
    private static final int backleftmotorPORT = DrivetrainConstants.backleftmotorPORT;
    private static final int frontrightmotorPORT = DrivetrainConstants.frontrightmotorPORT;
    private static final int backrightmotorPORT = DrivetrainConstants.backrightmotorPORT;

    //Hardware
    public CANSparkMax frontLeftMotor; //Checked
    public CANSparkMax backLeftMotor; //Checked
    public CANSparkMax frontRightMotor; //Checked
    public CANSparkMax backRightMotor; //Checked

    //Others
    private DifferentialDrive drivetrain;

    /**
     * Constructs DrivetrainSubsystem
     */
    public DrivetrainSubsystem() {
        frontLeftMotor = new CANSparkMax(frontleftmotorPORT, MotorType.kBrushless);
        backLeftMotor = new CANSparkMax(backleftmotorPORT, MotorType.kBrushless);
        frontRightMotor = new CANSparkMax(frontrightmotorPORT, MotorType.kBrushless);
        backRightMotor = new CANSparkMax(backrightmotorPORT, MotorType.kBrushless);

        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

        drivetrain = new DifferentialDrive(frontLeftMotor, frontRightMotor);

        //add polarity here
    }
    /**
     * Polar driving 
     * @param forward speed [-1,1]
     * @param turn turn value, clockwise is positive [-1,1]
     */
    public void drive(double forward, double turn) {
        drivetrain.arcadeDrive(-forward, turn);
    }
    /**
     * Driving using direct set
     * @param left set left drivetrain speed [-1,1]
     * @param right set right drivetrain speed [-1,1]
     */
    public void lowLevelDrive(double left, double right) {
        frontLeftMotor.set(left);
        frontRightMotor.set(right);
    }
    /**
     * Hardware feedback
     */
    public void debug() {
        SmartDashboard.putNumber("leftDist", frontLeftMotor.getEncoder().getVelocity());
        SmartDashboard.putNumber("rightDist", frontRightMotor.getEncoder().getVelocity());
    }

}
