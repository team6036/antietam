/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;

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
    public final static class PortConstants {
        public final static int xboxControllerPort = 1;
    }
    public final static class ControlPanel {
        public final static class Colors {
            public static final String RED = "red";
            public static final String GREEN = "green";
            public static final String BLUE = "blue";
            public static final String YELLOW = "yellow";
            public static final String UNKNOWN = "unknown";
            public static final ColorMatch ColorMatcher = new ColorMatch();
            public static final Color kBlueTarget = ColorMatcher.makeColor(0.143, 0.427, 0.429);
            public static final Color kGreenTarget = ColorMatcher.makeColor(0.197, 0.561, 0.240);
            public static final Color kRedTarget = ColorMatcher.makeColor(0.561, 0.232, 0.114);
            public static final Color kYellowTarget = ColorMatcher.makeColor(0.361, 0.524, 0.113);
        }
        public final static class Motor {
            public static final int CHANNEL = 1;
            public static final double SPEED = 0.1;
        }
        public final static class Piston {
            public static final int portSolenoid2A = 0;
            public static final int portSolenoid2B = 1;
            public static final int portSolenoid3A = 2;
            public static final int portSolenoid3B = 3;
        }
    }
}
