package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private SparkMax motor0 = new SparkMax(5, MotorType.kBrushless);
    private SparkMax motor1 = new SparkMax(6, MotorType.kBrushless);

    public Shooter() {
        SparkMaxConfig motor0Config = new SparkMaxConfig();
        SparkMaxConfig motor1Config = new SparkMaxConfig();

        motor0Config
            .smartCurrentLimit(50)
            .idleMode(IdleMode.kBrake);

        // I'm assuming here that you have two shooter motors rotate in opposite directions,
        // if this isn't the case, remove the `.inverted(true)`
        motor1Config.apply(motor0Config).inverted(true);

        motor0.configure(motor0Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motor1.configure(motor1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public Command shoot() {
        // set motor speeds to 0.25 while the command is active
        // then set motor speeds to 0.0
        return runEnd(
            ()->{
                motor0.set(0.25);
                motor1.set(0.25);
            }, 
            ()->{
                motor0.set(0.0);
                motor1.set(0.0);
            }
        );
    }
    
}
