package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Arrays;

public class Limelight {
    public static double tx() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
    }

    public static double ty() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
    }

    public static double ts() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0.0);
    }

    public static double ta() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);
    }

    public static int[] camTran() {
        double[] raw = NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran")
                .getDoubleArray(new double[] {});
        int[] retval = new int[raw.length];
        for (int i = 0; i < raw.length; i++) {
            retval[i] = (int) (raw[i] + 0.5);
        }
        return retval;
    }
    public static double getDistance() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran")
                .getDoubleArray(new double[] {})[2];
    }
    // sends all of the crap
    public static void debug() {
        SmartDashboard.putNumber("TX", tx());
        SmartDashboard.putNumber("TY", ty());
        SmartDashboard.putNumber("TS", ts());
        SmartDashboard.putNumber("Image area", ta());
        SmartDashboard.putNumber("Distance", getDistance());
        SmartDashboard.putString("Camtran", Arrays.toString(camTran()));
    }
}