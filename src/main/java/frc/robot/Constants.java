package frc.robot;

public final class Constants {
    public final static int joystickPort = 0;
    public final static int xboxPort = 2;

    public final static class AccumulatorConstants {
        public static enum ExtendState {
            RETRACTED, EXTENDED;
        }

        public final static int rollerMotorPort = 0;
        public final static int serializerMotorPort = 1;
        public final static int ballTubeMotorPort = 2;

        public final static int solenoid1APort = 0;
        public final static int solenoid1BPort = 7;

        public final static double rollerMotorPower = 0.75;
        public final static double serializerMotorPower = -0.75;
        public final static double ballTubeMotorPower = -0.99;

        public final static boolean debug = false;
    }

    public final static class HoodConstants {
        public static enum TurnState {
            ZEROING, MANUAL, AUTOTARGET;
        }

        public static final boolean debug = false;
        public static final int motorPort = 6;
        public static final int encoderPort = 5;
        public static final double kp = 0.00001;
    }

    public final static class TurretConstants {
        public static enum TurretMode {
            THREEPOINT, TWOPOINT, MANUAL;
        }

        public static enum TurretState {
            SEEKING, PIDCONTROL, MANUAL;
        }

        public static final boolean debug = false;
        public static final int motorPort = 5;
        public static final int encoderPorts = 6;
        public static final double kp = 0.00001;
    }

    // control panel 3,4
    // pcm 1, 0,7 climb
    public final static class DrivetrainConstants {
        public static final double kp = 0.00001;
        public static final int frontleftmotorPORT = 1;
        public static final int backleftmotorPORT = 2;
        public static final int frontrightmotorPORT = 3;
        public static final int backrightmotorPORT = 4;

        // driveshifter 1,6
        // climb shift 2,5
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

        public static final int firstShooterPort = 6;
        public static final int secondShooterPort = 7;
        public static final int hopupPort = 4;
        public static final int lineBreakPort = 0;
        public static final double kp = 0.00001;

        public static final boolean debug = true;
    }

    public final static class LimelightConstants {
        public final static double verticalKP = 0.00001;
        public final static double horizontalKP = 0.00001;
        public final static double targetDistance = 30;

        public final static boolean debug = true;
    }

    public final static class TargetingGroupConstants {
        public final static boolean autoTarget = true;
    }
}
