/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final static class PortConstants {
        public final static int xboxControllerPort = 1;
    }
    public final static class ControlPanel {
        public final static class Colors {
            public final static String RED = "red";
            public final static String GREEN = "green";
            public final static String BLUE = "blue";
            public final static String YELLOW = "yellow";
            public final static String UNKNOWN = "unknown";
        }
        public final static class Motor {
            public final static int CHANNEL = 1;
        }
        public final static class Piston {
            public final static int portSolenoid2A = 0;
            public final static int portSolenoid2B = 1;
            public final static int portSolenoid3A = 2;
            public final static int portSolenoid3B = 3;
        }
    }
}
