package frc.robot.subsystems;

import frc.robot.Constants.ControlPanel.Colors;
import frc.robot.Constants.ControlPanel.Motor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.I2C;
import java.util.ArrayList;
import java.util.Arrays;
import edu.wpi.first.wpilibj.VictorSP;

public class ControlPanelSubsystem extends SubsystemBase {
    private final ArrayList<String> controlPanel;
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    private       Color currentColor = kBlueTarget;
    private       String currentColorString = "";
    private final VictorSP motor = new VictorSP(Motor.CHANNEL);

    public ControlPanelSubsystem() {
        controlPanel = new ArrayList<String>(Arrays.asList(Colors.RED, Colors.YELLOW, Colors.BLUE, Colors.GREEN));
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }

    public final boolean halfRotation(String c1, String c2) {
        return c1.equals(c2);
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
        ColorMatchResult match = m_colorMatcher.matchClosestColor(m_colorSensor.getColor());
        if (match.color == kBlueTarget || match.color == kRedTarget || match.color == kGreenTarget || match.color == kYellowTarget) {
            currentColor = match.color;
            if (match.color == kBlueTarget)
                currentColorString = Colors.BLUE;
            if (match.color == kRedTarget)
                currentColorString = Colors.RED;
            if (match.color == kGreenTarget)
                currentColorString = Colors.GREEN;
            if (match.color == kYellowTarget)
                currentColorString = Colors.YELLOW;
        }
        else
            currentColorString = Colors.UNKNOWN;
    }

    public final void setMotorSpeed(double speed) {
        motor.setSpeed(speed);
    }
    public final void getMotorSpeed(double speed) {
        motor.getSpeed();
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
