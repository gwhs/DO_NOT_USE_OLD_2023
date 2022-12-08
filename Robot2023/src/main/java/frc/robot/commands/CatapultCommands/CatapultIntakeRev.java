// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeCommands.IntakeDeploy;
import frc.robot.commands.IntakeCommands.IntakeStow;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IntakeMotor;

public class CatapultIntakeRev extends SequentialCommandGroup {
  /** Creates a new CatapultIntake. */
  private double deployDelay = 0.3;
  public CatapultIntakeRev(IntakeMotor m_IntakeMotor, CatapultSubsystem m_CatapultSubsystemRight, CatapultSubsystem m_CatapultSubsystemLeft, double leftSpeed, double rightSpeed, double deploySpeed, double delay) {
    addCommands(new IntakeDeploy(m_IntakeMotor, deploySpeed),
                  new SequentialCommandGroup(new WaitCommand(deployDelay)),
                    new CatapultDouble(m_CatapultSubsystemRight, m_CatapultSubsystemLeft, leftSpeed, rightSpeed, delay),
                      new SequentialCommandGroup(new WaitCommand(delay), 
                        new IntakeStow(m_IntakeMotor, deploySpeed)));
  }
}
