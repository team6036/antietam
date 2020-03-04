# Turret Subsystem
The purpose of the class is to turn the turret.
It has 3 kinds of methods:

1. Turn methods
    * <a href="#autoTarget">autoTarget(error, exists)</a>
    * <a href="#turn">turn(amount) </a>
2. Debug prints
    * <a href="#debug">debug()</a>

--- 
# Turn methods
>## <a id="autoTarget">public void autoTarget(double error, boolean exists)</a>
Runs a cycle on the P loop to constantly aim at the target, gets passed the displacement and whether or not the target actually exists.
~~~java
public void autoTarget(double error, boolean exists) {
    /*
    Important:
    This method needs heavy modification. It needs to:
        1. have a field and behaviour for no target
        2. prevent overrotation
    */
        power += error * kp;
        motor.set(power);
    }
~~~
---
>## <a id="turn">public void turn(double amount)</a>
Turns the turret by setting the motor to the given value.
~~~java
public void turn(double amount) {
    if (amount >= 0.05) {
        power=amount;
        motor.set(power); // might need to reverse polarity
        //prevent overrotation
    }
}
~~~
---
# Debug methods
>## <a id="debug">public void debug()</a>
Prints out turret power to SmartDashboard
~~~java
public void debug() {
    SmartDashboard.putNumber("Turret Power", power);
}
~~~
---
Last updated Feb 1, 2020