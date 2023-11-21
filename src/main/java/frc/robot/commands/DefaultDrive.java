package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

// 5409: The Chargers
// http://github.com/FRC5409

/**
 * @author 
 */
public class DefaultDrive extends Command {

    private final Drivetrain m_drivetrain;
    private final CommandXboxController m_controller;

    public DefaultDrive(Drivetrain drivetrain, CommandXboxController controller) {
        m_drivetrain = drivetrain;
        m_controller = controller;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double accel = m_controller.getRightTriggerAxis();
        double decel = m_controller.getLeftTriggerAxis();
        double zRot = m_controller.getLeftX();

        m_drivetrain.arcadeDrive(accel - decel, zRot);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}