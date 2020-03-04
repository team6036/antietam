# Drivetrain Subsystem
This class controls the 4 CANSpark Maxes in our drivetrain using WPIlib's ArcadeDrive. 

The ports for the motors are set in:
~~~java 
import frc.robot.Constants.DrivetrainConstants;
~~~
It treats each side of the drivetrain as a single entity, with one motor following the other.

It has two types of methods:
1. Movement methods
    * <a href="#drive">drive(forward, turn)</a>
2. Debug methods
    * <a href="#debug">debug()</a>
---
# Movement methods
> ## <a id="drive">public void drive(double forward, double turn)</a>
Sets the motors to the forward value, with both sides adjusted to turn. (Not sure of the inner workings of the DifferentialDrive class)
~~~java
public void drive(double forward, double turn) {
    drivetrain.arcadeDrive(forward,turn);
}
~~~
---
# Debug methods
> ## <a id="debug">public void debug()</a>
Prints out the velocity of each side of the drivetrain. (From CANEncoders, not actual velocity, just motor rpm)
~~~java
public void debug(){
    SmartDashboard.putNumber("leftDist", frontleftmotor.getEncoder().getVelocity());
    SmartDashboard.putNumber("rightDist", frontrightmotor.getEncoder().getVelocity());
}
~~~