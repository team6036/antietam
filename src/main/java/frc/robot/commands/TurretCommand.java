package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.Constants.TurretConstants.TurretMode;
import frc.robot.Constants.TurretConstants;

public class TurretCommand extends CommandBase {

    private TurretSubsystem m_turretSubsystem;
    private DoubleSupplier jStick;
    private double kp = TurretConstants.kp;
    private static TurretMode turretMode = TurretMode.MANUAL;
    private PIDController pidController = new PIDController(kp, 0, 0);

    /**
     * Simple constructor
     * 
     * @param m_turretSubsystem   the turret that will be aiming
     * @param m_limlightSubsystem the feedback device for auto aiming
     * @param jStick              input device for manual aiming.
     */
    public TurretCommand(TurretSubsystem m_turretSubsystem, DoubleSupplier jStick) {
        pidController.setSetpoint(0);
        this.m_turretSubsystem = m_turretSubsystem;
        this.jStick = jStick;
        addRequirements(m_turretSubsystem);
    }

    /**
     * gobally callable; sets turret mode to threePoint
     */
    public static void threePoint() {
        turretMode = TurretMode.THREEPOINT;
    }

    /**
     * globally callable; sets turret mode to twoPoint
     */
    public static void twoPoint() {
        turretMode = TurretMode.TWOPOINT;
    }

    /**
     * globally callable; sets turret mode to manual
     */
    public static void manual() {
        turretMode = TurretMode.MANUAL;
    }

    /**
     * If in twopoint mode, turns according to limelight input if in Threepoint
     * mode, turns the drivetrain to be in line with the camera If in manual mode,
     * turns according to joystick input
     */
    @Override
    public void execute() {

        //m_turretSubsystem.turn(0.1);
        switch (turretMode) {
        case TWOPOINT: {
            if (Limelight.ta() != 0) {
                autoTarget(Limelight.tx());
            } else {
                seek();
            }
        }
        case THREEPOINT: {
            zero(m_turretSubsystem.getDisplacement());
        }
        case MANUAL: {

            m_turretSubsystem.turn(jStick.getAsDouble());
            //TODO move threshold to the command out of subsystem
        }
        }
    }

    /**
     * turns according to a given error/displacement, approaches zero
     * 
     * @param error
     */
    public void autoTarget(double error) {
        m_turretSubsystem.turn(pidController.calculate(error));
    }

    /**
     * should seek within a certain range for the target
     */
    public void seek() {
        // loop
    }

    /**
     * turns drivetrain to match turret,
     */ 
    //todo make sure to make the turret turn opposite so the error gets reduced lol
    public void zero(double d) {
        DrivetrainCommand.drive(pidController.calculate(d), -pidController.calculate(d)); // check polarity
    }
}