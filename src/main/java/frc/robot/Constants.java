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

    public final static class DrivetrainConstants {
        public static final int frontleftmotorPORT = 1;
        public static final int backleftmotorPORT = 2;
        public static final int frontrightmotorPORT = 14;
        public static final int backrightmotorPORT = 15;

        public final static boolean debug = false;
    }

    public final static class AccumulatorConstants {
        public final static boolean debug = false;
    }

    public final static class ClimberConstants {
        public final static boolean debug = false;
    }

    public final static class ShooterConstants {
        public static final int firstShooterPort = 0;
        public static final int secondShooterPort = 0;
        public static final int ssfPort = 0;
        public static final int bagPort = 0;

        public static final boolean debug = true;
    }

    public final static class LimelightConstants {
        public final static double verticalKP = 0.00001;
        public final static double horizontalKP = 0.00001;
        public final static double targetDistance = 30;

        public final static boolean debug = true;
    }
}