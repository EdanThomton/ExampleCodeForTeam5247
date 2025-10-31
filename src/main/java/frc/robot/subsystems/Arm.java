package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

    private DigitalInput topLimitSwitch = new DigitalInput(0);
    private DigitalInput bottomLimitSwitch = new DigitalInput(1);

    private Talon redline0 = new Talon(0);
    private Talon redline1 = new Talon(1);

    public Arm() {}

    public Command articulate(double speed) {
        // go up until hitting the top or bottom, or until this command ends
        return runEnd(
            ()->{
                if(
                    (topLimitSwitch.get() && speed > 0) || 
                    (bottomLimitSwitch.get() && speed < 0)
                ) {
                    redline0.set(0.0);
                    redline1.set(0.0);
                } else {
                    redline0.set(speed);
                    redline1.set(speed);
                }
            },
            ()->{
                redline0.set(0.0);
                redline1.set(0.0);
            }
        );
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Top Limit Switch: ", topLimitSwitch.get());
        SmartDashboard.putBoolean("Bottom Limit Switch", bottomLimitSwitch.get());
    }
    
}
