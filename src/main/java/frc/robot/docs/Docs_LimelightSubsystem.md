# Limelight Subsystem
The purpose of this subsystem class is to simply return given values from the limelight. It does not directly control __anything__.

It has two kinds of methods:
1. Pipeline values
    * <a href="#tx">tx()</a>
    * <a href="#ty">ty()</a>
    * <a href="#ts">ts()</a>
    * <a href="#ta">ta()</a>
    * <a href="#camTran">camTran()</a>
    * <a href="#getDistance">getDistance()</a>
2. Debug prints
    * <a href="#debug">debug()</a>

---
# Pipeline values
>## <a id="tx"> public double tx() </a>
Returns the angle of horizontal displacement from the detected target. 

This is not a 3d feature, so the target detected might not always be the correct one.
~~~java
public double tx() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
}
~~~
---
>## <a id="ty"> public double ty() </a>
Returns the angle of vertical displacement from the detected target. 

This is not a 3d feature, so the target detected might not always be the correct one.
~~~java
public double ty() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
}
~~~
---
>## <a id="ts"> public double ts() </a>
Returns the angle of rotational displacement from the detected target. How turned the target is from the crosshair's axis.

This is not a 3d feature, so the target detected might not always be the correct one.
~~~java
public double ts() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0.0);
}
~~~
---
> ## <a id="ta"> public double ta() </a>
Returns the area of the limelight's FOV that is covered by the detected target. 

This is not a 3d feature, so the target detected might not always be the correct one.
~~~java
public double ta() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);
}
~~~
---
> ## <a id="camTran"> public int[] camTran() </a>
Returns an array of six values (in inches and degrees) from the limelight's 3d values.
1. X displacement. How much to the left or right of the target the robot is.
2. Y displacement. How much above or below the target the robot is.
3. Z displacement. How far away the robot is from the target.
4. Pitch. How much the target is leaning forwards or backwards.
5. Yaw. How much the target is turned left or right.
6. Roll. How turned-on-its-head the target is.

![alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Yaw_Axis_Corrected.svg/250px-Yaw_Axis_Corrected.svg.png "Pitch-Yaw-Roll")

These six values are integers that have been rounded up from doubles. This might change in the future.
~~~java 
public int[] camTran() {
    double[] raw = NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran").getDoubleArray(new double[] {});
    int[] retval = new int[raw.length];
    for (int i = 0; i < raw.length; i++) {
        retval[i] = (int) (raw[i] + 0.5);
    }
    return retval;
}
~~~
---
> ## <a id="getDistance"> public double getDistance </a>
Returns the distance calculated by the limelight's 3d pipeline. In inches.
~~~java
public double getDistance() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran").getDoubleArray(new double[] {})[2];
}
~~~
---
# Debug values
> ## <a id="debug"> public void debug() </a>
Prints debug values to SmartDashboard. Includes <a href="#tx">tx()</a>, <a href="#ty">ty()</a>, <a href="#ts">ts()</a>, <a href="#ta">ta()</a>, and <a href="#camTran">camTran()</a>.
~~~java
public void debug() {
    SmartDashboard.putNumber("TX", tx());
    SmartDashboard.putNumber("TY", ty());
    SmartDashboard.putNumber("TS", ts());
    SmartDashboard.putNumber("Image area", ta());
    SmartDashboard.putNumber("Distance", getDistance());
    SmartDashboard.putString("Camtran", Arrays.toString(camTran()));
}
~~~
---
Last updated Feb 1, 2020.