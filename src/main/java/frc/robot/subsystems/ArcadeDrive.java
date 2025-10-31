package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class ArcadeDrive extends SubsystemBase {
    
    // initialize our drivetrain motors in the ArcadeDrive subsystem
    // since you are using two motors per side, one is a leader, the other is a follower
    private SparkMax leftLeader = new SparkMax(1, MotorType.kBrushless);
    private SparkMax leftFollower = new SparkMax(2, MotorType.kBrushless);
    private SparkMax rightLeader = new SparkMax(1, MotorType.kBrushless);
    private SparkMax rightFollower = new SparkMax(2, MotorType.kBrushless);

    // DifferentialDrive kinematics
    private DifferentialDrive driveKinematics = new DifferentialDrive(leftLeader, rightLeader);

    public ArcadeDrive() {

        // spark max configurations
        SparkMaxConfig leftLConfig = new SparkMaxConfig();
        SparkMaxConfig leftFConfig = new SparkMaxConfig();
        SparkMaxConfig rightLConfig = new SparkMaxConfig();
        SparkMaxConfig rightFConfig = new SparkMaxConfig();

        // current limit of 50A, you should never hit this
        // motor is set to brake (when not told to run, apply voltage to slow down)
        leftLConfig
            .smartCurrentLimit(50)
            .idleMode(IdleMode.kBrake);
        
        // the right side is the same as the left, but inverted
        rightLConfig.apply(leftLConfig).inverted(true);

        // the two follower motors should follow the leaders
        leftFConfig.apply(leftLConfig).follow(leftLeader);
        rightFConfig.apply(rightLConfig).follow(rightLeader);

        // apply configs to the sparkmaxes
        // reset to known safe parameters beforehand, and keep configurations in between runs
        leftLeader.configure(leftLConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftFollower.configure(leftFConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightLeader.configure(rightLConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightFollower.configure(rightFConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void drive(double speed, double rot) {
        // arcade drive
        driveKinematics.arcadeDrive(speed, rot);
    }

}
