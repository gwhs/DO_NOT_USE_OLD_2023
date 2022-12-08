package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

public class AutoCommand extends SequentialCommandGroup {
    private static Pose2d leftInitPose = new Pose2d(6.89, 4.44, new Rotation2d(Math.toRadians(159.0)));
    private static Pose2d rightInitPose = new Pose2d(7.95, 2.73, new Rotation2d(Math.toRadians(-111.00)));

    private static Pose2d rightOneEndPose = new Pose2d(6.72, 0.54, new Rotation2d(-111.0));
    private static Pose2d rightTwoEndPose = new Pose2d(7.79, 2.76, new Rotation2d(-102.26));
    private static Pose2d rightThreeEndPose = new Pose2d(7.82, 2.60, new Rotation2d(-107.1));
    private static Pose2d leftEndPose = new Pose2d(6.65, 4.56, new Rotation2d(159.0));

    public static AutoCommand OneCargo(DrivetrainSubsystem m_drivetrainSubsystem, CatapultSubsystem m_catapultSubsystemLeft, CatapultSubsystem m_catapultSubsystemRight, IntakeMotor m_intakeMotor, UpperLowerIntake m_upperLowerIntake, double delay) {
        return new AutoCommand("1 Cargo - Right", rightInitPose, rightOneEndPose, delay, m_drivetrainSubsystem, m_catapultSubsystemLeft, m_catapultSubsystemRight, m_intakeMotor, m_upperLowerIntake);
    }

    public static AutoCommand TwoCargoLeft(DrivetrainSubsystem m_drivetrainSubsystem, CatapultSubsystem m_catapultSubsystemLeft, CatapultSubsystem m_catapultSubsystemRight, IntakeMotor m_intakeMotor, UpperLowerIntake m_upperLowerIntake, double delay) {
        return new AutoCommand("2 Cargo - Left", leftInitPose, leftEndPose, delay, m_drivetrainSubsystem, m_catapultSubsystemLeft, m_catapultSubsystemRight, m_intakeMotor, m_upperLowerIntake);
    }

    public static AutoCommand TwoCargoRight(DrivetrainSubsystem m_drivetrainSubsystem, CatapultSubsystem m_catapultSubsystemLeft, CatapultSubsystem m_catapultSubsystemRight, IntakeMotor m_intakeMotor, UpperLowerIntake m_upperLowerIntake, double delay) {
        return new AutoCommand("2 Cargo - Right - f", rightInitPose, rightTwoEndPose, delay, m_drivetrainSubsystem, m_catapultSubsystemLeft, m_catapultSubsystemRight, m_intakeMotor, m_upperLowerIntake);
    }

    public static AutoCommand ThreeCargoRight(DrivetrainSubsystem m_drivetrainSubsystem, CatapultSubsystem m_catapultSubsystemLeft, CatapultSubsystem m_catapultSubsystemRight, IntakeMotor m_intakeMotor, UpperLowerIntake m_upperLowerIntake, double delay) {
        return new AutoCommand("3 Cargo - Right", rightInitPose, rightThreeEndPose, delay, m_drivetrainSubsystem, m_catapultSubsystemLeft, m_catapultSubsystemRight, m_intakeMotor, m_upperLowerIntake);
    }

        public AutoCommand(String pathName, Pose2d initPose, Pose2d endPose, double delay, DrivetrainSubsystem m_drivetrainSubsystem, CatapultSubsystem m_catapultSubsystemLeft, CatapultSubsystem m_catapultSubsystemRight, IntakeMotor m_intakeMotor, UpperLowerIntake m_upperLowerIntake) {
            PathPlannerTrajectory path = PathPlanner.loadPath(pathName, 2, 1.5);
            PathPlannerTrajectory zero = PathPlanner.loadPath(pathName + " Turn", 2, 1.5);
            
            addCommands(
                        new InstantCommand(() -> m_drivetrainSubsystem.forcingZero()),
                        new WaitCommand(delay),
                        new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(initPose)),
                        new ParallelCommandGroup(
                            new PPSwerveControllerCommand(
                                path,
                                m_drivetrainSubsystem::getPose,
                                m_drivetrainSubsystem.getKinematics(),
                                new PIDController(1, 0, 0),
                                new PIDController(1, 0, 0),
                                m_drivetrainSubsystem.getThetaController(),
                                m_drivetrainSubsystem::setStates,
                                m_drivetrainSubsystem)), 
                        new InstantCommand(() -> m_drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0))),
                        new WaitCommand(1)
                        // new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(endPose)),
                        // new PPSwerveControllerCommand(
                        //         zero,
                        //         m_drivetrainSubsystem::getPose,
                        //         m_drivetrainSubsystem.getKinematics(),
                        //         new PIDController(1, 0, 0),
                        //         new PIDController(1, 0, 0),
                        //         m_drivetrainSubsystem.getThetaController(),
                        //         m_drivetrainSubsystem::setStates,
                        //         m_drivetrainSubsystem),
                        // new InstantCommand(() -> m_drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0))),
                        // new InstantCommand(() -> m_drivetrainSubsystem.zeroGyroscope())
                        );
    
        }

}