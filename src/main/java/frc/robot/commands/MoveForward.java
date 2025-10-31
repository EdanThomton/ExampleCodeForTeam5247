package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArcadeDrive;

public class MoveForward extends Command {

    ArcadeDrive driveSubsystem;

    // time the command started, in milliseconds
    long startTime;
    
    public MoveForward(ArcadeDrive driveSubsystem) {

        this.driveSubsystem = driveSubsystem;

        // move forward uses driveSubsystem
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        // drive forward
        driveSubsystem.drive(0.5, 0.0);
    }

    @Override
    public void end(boolean interrupted) {
        // stop driving
        driveSubsystem.drive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        // find how long the command has been running
        long currentTime = System.currentTimeMillis();
        long timeSinceStartMS = currentTime - startTime;

        // if the command has been running for 2 seconds, stop
        return timeSinceStartMS > 2000.0;
    }

}
