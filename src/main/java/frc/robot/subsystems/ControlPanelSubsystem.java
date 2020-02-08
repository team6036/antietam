package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ControlPanel.Colors;
import frc.robot.Constants.ControlPanel.Motor;
import frc.robot.Constants.ControlPanel.Piston;

public class ControlPanelSubsystem extends SubsystemBase {
    // Control Panel variables
    private final ArrayList<String> controlPanel;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    private       Color currentColor = Colors.kBlueTarget;
    private       String currentColorString = "";
    private final VictorSP motor = new VictorSP(Motor.CHANNEL);
    // Piston variables
    private PistonState pistonState = PistonState.RETRACTED;
    private DoubleSolenoid solenoidLiftFront = new DoubleSolenoid(Piston.portSolenoid2A, Piston.portSolenoid2B);
    private DoubleSolenoid solenoidLiftBack = new DoubleSolenoid(Piston.portSolenoid3A, Piston.portSolenoid3B);

    public ControlPanelSubsystem() {
        controlPanel = new ArrayList<String>(Arrays.asList(Colors.RED, Colors.YELLOW, Colors.BLUE, Colors.GREEN));
        Colors.ColorMatcher.addColorMatch(Colors.kBlueTarget);
        Colors.ColorMatcher.addColorMatch(Colors.kGreenTarget);
        Colors.ColorMatcher.addColorMatch(Colors.kRedTarget);
        Colors.ColorMatcher.addColorMatch(Colors.kYellowTarget);
    }

    public static enum PistonState {
        EXTENDED, RETRACTED
    }

    public final PistonState getPistonState() {
        return pistonState;
    }

    public final void extendPiston() {
        solenoidLiftFront.set(DoubleSolenoid.Value.kForward);
        solenoidLiftBack.set(DoubleSolenoid.Value.kForward);
        pistonState = PistonState.EXTENDED;
        SmartDashboard.putString("{Control Panel}: Front Lift Piston = ", "ENGAGED");
        SmartDashboard.putString("{Control Panel}: Back Lift Piston  = ", "ENGAGED");
    }

    public final void retractPiston() {
        solenoidLiftFront.set(DoubleSolenoid.Value.kReverse);
        solenoidLiftBack.set(DoubleSolenoid.Value.kReverse);
        pistonState = PistonState.RETRACTED;
        SmartDashboard.putString("{Control Panel}: Front Lift Piston = ", "DISENGAGED");
        SmartDashboard.putString("{Control Panel}: Back Lift Piston  = ", "DISENGAGED");
    }

    public final Integer position() {
        String targetColorString = getTargetColor() ;
        if (targetColorString.equals(Colors.UNKNOWN))
            return -1;
        int currentColorIdx = controlPanel.indexOf(currentColorString);
        int targetColorIdx  = controlPanel.indexOf(targetColorString);
        int dist1 = targetColorIdx-currentColorIdx;
        int dist2 = -(currentColorIdx+(controlPanel.size()-targetColorIdx));
        return (dist1 > Math.abs(dist2)) ? dist1 : dist2;
    }

    public final void updateCurrentColor() {
        ColorMatchResult match = Colors.ColorMatcher.matchClosestColor(m_colorSensor.getColor());
        if (match.color == Colors.kBlueTarget || match.color == Colors.kRedTarget || match.color == Colors.kGreenTarget || match.color == Colors.kYellowTarget) {
            currentColor = match.color;
            if (match.color == Colors.kBlueTarget)
                currentColorString = Colors.BLUE;
            if (match.color == Colors.kRedTarget)
                currentColorString = Colors.RED;
            if (match.color == Colors.kGreenTarget)
                currentColorString = Colors.GREEN;
            if (match.color == Colors.kYellowTarget)
                currentColorString = Colors.YELLOW;
        }
        else
            currentColorString = Colors.UNKNOWN;
    }

    public final void setMotorSpeed(double speed) {
        motor.setSpeed(speed);
    }

    public final String getTargetColor() {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() > 0)
            switch (gameData.charAt(0)) {
                case 'B':
                    return Colors.BLUE;
                case 'G':
                    return Colors.GREEN;
                case 'R':
                    return Colors.RED;
                case 'Y':
                    return Colors.YELLOW;
            }
        return Colors.UNKNOWN;
    }

    public final String getCurrentColor() {
        return currentColorString;
    }

    @Override
    public void periodic(){
        updateCurrentColor();
        System.out.println("{" + currentColor.red + ", " + currentColor.green + ", " + currentColor.blue + "}");
        System.out.println("Likely color: " + currentColorString);
        SmartDashboard.putNumber("Red", currentColor.red);
        SmartDashboard.putNumber("Green", currentColor.green);
        SmartDashboard.putNumber("Blue", currentColor.blue);
        SmartDashboard.putNumber("IR", m_colorSensor.getIR());
        SmartDashboard.putNumber("Proximity", m_colorSensor.getProximity());
    }
}
