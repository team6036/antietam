/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final static int joystickPort = 0;
    public final static int xboxPort = 1;
    public final static class LinebreakConstants{
        public final static int inputPort = 0;
    }
    public final static class AccumulatorConstants {

        public final static int flywheelMotorPort = 1;
        public final static int ballAlignmentMotorPort = 2;
        public final static int hopUpMotorPort = 3;

        public final static int lineBreakSensorPort = 4;

        public final static int solenoid1APort = 6;
        public final static int solenoid1BPort = 7; // All random numbers FYI
        public final static int solenoid2APort = 8;
        public final static int solenoid2BPort = 9;
    }

    public final static class HoodConstants {
        public static enum TurnState {
            ZEROING, MANUAL, AUTOTARGET;
        }

        public static final int motorPort = 0;
        public static final int encoderPort = 0;
        public static final double kp = 0.00001;
    }

    public final static class TurretConstants {
        public static enum TurretMode {
            THREEPOINT, TWOPOINT, MANUAL;
        }

        public static enum TurretState {
            SEEKING, PIDCONTROL, MANUAL;
        }

        public static final int motorPort = 0;
        public static final int encoderPorts = 0;
        public static final double kp = 0.00001;
    }

    public final static class DrivetrainConstants {
        public static final double kp = 0.00001;
        public static final double targetDist = 50;
        public static final int frontleftmotorPORT = 15;
        public static final int backleftmotorPORT = 14;
        public static final int frontrightmotorPORT = 2;
        public static final int backrightmotorPORT = 1;

        public static enum ControlState {
            MANUAL, LIMELIGHT, CLIMBER;
        }

        public final static boolean debug = false;
    }

    public final static class ClimberConstants {
        public final static boolean debug = false;
    }

    public final static class ShooterConstants {
        public static enum BallStates {
            CONTAINED, WAITING, READY, LEAVING;
        }

        public static final int firstShooterPort = 0;
        public static final int secondShooterPort = 0;
        public static final int hopupPort = 0;
        public static final int lineBreakPort = 0;
        public static final double kp = 0.00001;

        public static final boolean debug = true;
    }

    public final static class LimelightConstants {
        public static enum DistanceMode {
            TRIG, CAMTRAN;
        }

        public final static double verticalKP = 0.00001;
        public final static double horizontalKP = 0.00001;
        public final static double targetDistance = 30;
        public final static double deltaY = 0;

        public final static boolean debug = true;
    }

    public final static class TargetingGroupConstants {
        public final static boolean autoTarget = true;
    }
}
