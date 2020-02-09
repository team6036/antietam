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
    private PIDController pidController = new PIDController(kp,0,0);

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
    }

    public static void threePoint() {
        turretMode = TurretMode.THREEPOINT;
    }

    public static void twoPoint() {
        turretMode = TurretMode.TWOPOINT;
    }

    public static void manual() {
        turretMode = TurretMode.MANUAL;
    }

    /**
     * Passes the tx necessary to aim at the target, or joystick input Tells the
     * subsystem to turn accordingly.
     */
    @Override
    public void execute() {
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
        }
        }
    }
    public void autoTarget(double error){
        m_turretSubsystem.turn(pidController.calculate(error));
    }
    public void seek(){
        //loop
    }
    public void zero(double d){
        DrivetrainCommand.drive(pidController.calculate(d), -pidController.calculate(d)); //check polarity
    }
}