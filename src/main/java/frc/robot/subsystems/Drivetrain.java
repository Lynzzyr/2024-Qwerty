package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// 5409: The Chargers
// http://github.com/FRC5409

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.kDrivetrain;
import frc.robot.Constants.kDrivetrain.kCANID;

public class Drivetrain extends SubsystemBase {

    private static Drivetrain instance = null;

    // Motors
    private final WPI_TalonSRX motFL;
    private final WPI_TalonSRX motFR;
    private final WPI_TalonSRX motBL;
    private final WPI_TalonSRX motBR;

    // Differential
    private final DifferentialDrive diff;

    private Drivetrain() {
        // Motors
        motFL = new WPI_TalonSRX(kCANID.kMotFL);
        motFR = new WPI_TalonSRX(kCANID.kMotFR);
        motBL = new WPI_TalonSRX(kCANID.kMotBL);
        motBR = new WPI_TalonSRX(kCANID.kMotBR);

        configMotors();

        motFL.follow(motBL);
        motFR.follow(motBR);

        // Differential
        diff = new DifferentialDrive(motFL, motFR);
    }

    // Get subsystem
    public static Drivetrain getInstance() {
        if (instance == null) instance = new Drivetrain();

        return instance;
    }

    private void configMotors() {
        motFL.setInverted(true);
        motFL.configPeakCurrentLimit(30);
        motFL.setNeutralMode(NeutralMode.Brake);
        motFL.configOpenloopRamp(kDrivetrain.kRampRate);
        
        motFR.setInverted(true);
        motFR.configPeakCurrentLimit(30);
        motFR.setNeutralMode(NeutralMode.Brake);
        motFR.configOpenloopRamp(kDrivetrain.kRampRate);
        
        motBL.configPeakCurrentLimit(30);
        motBL.setNeutralMode(NeutralMode.Brake);
        motBL.configOpenloopRamp(kDrivetrain.kRampRate);
        
        motBR.configPeakCurrentLimit(30);
        motBR.setNeutralMode(NeutralMode.Brake);
        motBR.configOpenloopRamp(kDrivetrain.kRampRate);
    }

    public void arcadeDrive(double accel, double zRot) {
        diff.arcadeDrive(accel, zRot);
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}
}