

package frc.robot;


import com.ctre.phoenix.led.ColorFlowAnimation.Direction;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.DefaultDriveCommandRobotOriented;
import frc.robot.utils.Utilities;

public class FinalContainer implements BaseContainer{
  private final XboxController m_controller1 = new XboxController(0);
  private final XboxController m_controller2 = new XboxController(1);

  //drivetrain
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  public static Direction direction;
  public FinalContainer() {



    m_drivetrainSubsystem.zeroGyroscope();
    m_drivetrainSubsystem.forcingZero();
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -Utilities.modifyAxis(m_controller1.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
            () -> -Utilities.modifyAxis(m_controller1.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
            //() -> -modifyAxis(m_controller1.getRightX()) * 2//DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
            () -> Utilities.modifyAxis(m_controller1.getLeftTriggerAxis() - m_controller1.getRightTriggerAxis()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));
    
    // m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommandRobotOriented(
    //         m_drivetrainSubsystem,
    //         () -> -Utilities.modifyAxis(m_controller2.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
    //         () -> -Utilities.modifyAxis(m_controller2.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
    //         //() -> -modifyAxis(m_controller1.getRightX()) * 2//DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    //         () -> Utilities.modifyAxis(m_controller2.getLeftTriggerAxis() - m_controller2.getRightTriggerAxis()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    // ));

    configureButtonBindings();
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton buttonX = new JoystickButton(m_controller1, XboxController.Button.kX.value);
    JoystickButton buttonB = new JoystickButton(m_controller1, XboxController.Button.kB.value);
    JoystickButton buttonA = new JoystickButton(m_controller1, XboxController.Button.kA.value);
    JoystickButton buttonY = new JoystickButton(m_controller1, XboxController.Button.kY.value);
    JoystickButton buttonLBumper = new JoystickButton(m_controller1, XboxController.Button.kLeftBumper.value);
    JoystickButton buttonRBumper = new JoystickButton(m_controller1, XboxController.Button.kRightBumper.value);
    JoystickButton buttonBack = new JoystickButton(m_controller1, XboxController.Button.kBack.value);
    JoystickButton buttonLeftJoystickButton = new JoystickButton(m_controller1, XboxController.Button.kLeftStick.value);
    JoystickButton buttonRightJoystickButton = new JoystickButton(m_controller1, XboxController.Button.kRightStick.value);
    JoystickButton buttonStart = new JoystickButton(m_controller1, XboxController.Button.kStart.value);

    JoystickButton buttonX2 = new JoystickButton(m_controller2, XboxController.Button.kX.value);
    JoystickButton buttonB2 = new JoystickButton(m_controller2, XboxController.Button.kB.value);
    JoystickButton buttonA2 = new JoystickButton(m_controller2, XboxController.Button.kA.value);
    JoystickButton buttonY2 = new JoystickButton(m_controller2, XboxController.Button.kY.value);
    JoystickButton buttonLBumper2 = new JoystickButton(m_controller2, XboxController.Button.kLeftBumper.value);
    JoystickButton buttonRBumper2 = new JoystickButton(m_controller2, XboxController.Button.kRightBumper.value);
    JoystickButton buttonBack2 = new JoystickButton(m_controller2, XboxController.Button.kBack.value);
    JoystickButton buttonLeftJoystickButton2 = new JoystickButton(m_controller2, XboxController.Button.kLeftStick.value);
    JoystickButton buttonRightJoystickButton2 = new JoystickButton(m_controller2, XboxController.Button.kRightStick.value);
    // JoystickButton button = new JoystickButton(m_controller1, XboxController.k.value);
    
    buttonBack.whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    buttonStart.whenPressed(() -> m_drivetrainSubsystem.forcingZero());
    
    // limelight and tof testing
    // buttonB.whenPressed(new TurnToZeroLimelight(0, m_drivetrainSubsystem, ll));
    // // buttonB.whenPressed(new GoToDistanceTimeOfFlight(6, m_drivetrainSubsystem, tof));
    // buttonA.whenPressed(new PrintLLandTOFDistance());
    // buttonX.whenPressed(new TurnToZeroLimelight(0, m_drivetrainSubsystem, ll).withTimeout(0.75));
    //buttonY2.whenPressed(new AutoCommand(m_drivetrainSubsystem, m_catapultSubsystemLeft, m_catapultSubsystemRight, m_intakeMotor, m_upperLowerIntake));
    



    // buttonLBumper2.whenPressed();
    // buttonRBumber2.whenPressed();


  }
/*
    * Use this to pass the autonomous command to the main {@link Robot} class.
    *
    * @return the command to run in autonomous
*/

// public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
    
    
  // }
}

