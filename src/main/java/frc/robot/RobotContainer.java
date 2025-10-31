// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.MoveForward;
import frc.robot.subsystems.ArcadeDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    
    // The robot's subsystems and commands are defined here
    private ArcadeDrive driveSubsystem = new ArcadeDrive();
    private Shooter shooterSubsystem = new Shooter();
    private Arm armSubsystem = new Arm();

    // joysticks
    private CommandJoystick driverJoystick = new CommandJoystick(0);
    private CommandJoystick operatorJoystick = new CommandJoystick(1);

    /** The container for the robot. Contains subsystems, IO devices, and commands. */
    public RobotContainer() {
        // Configure the trigger bindings
        // the default command will run if no other commands are active
        driveSubsystem.setDefaultCommand(
            Commands.run(() -> {
                driveSubsystem.drive(driverJoystick.getY(), driverJoystick.getX());
            })
        );

        operatorJoystick.button(1).whileTrue(shooterSubsystem.shoot());
        operatorJoystick.button(7).whileTrue(armSubsystem.articulate(0.25));
        operatorJoystick.button(9).whileTrue(armSubsystem.articulate(-0.25));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // move forward during autonomous
        return new MoveForward(driveSubsystem);
    }
}
