// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static CANSparkMax left1;
  public static WPI_TalonSRX left2;
  public static WPI_TalonSRX left3;
 
  public static CANSparkMax right1;
  public static WPI_TalonSRX right2;
  public static WPI_TalonSRX right3;
  
  public static Joystick JS;
  public static JoystickButton eStopTriger;
  public static JoystickButton startButton;
  
  public double x;
  public double y;

  public static DifferentialDrive robot;
  public static SpeedControllerGroup left;
  public static SpeedControllerGroup right;

  private void configTalon(WPI_TalonSRX talon) {
    talon.configFactoryDefault();
    talon.setNeutralMode(NeutralMode.Brake);
  }

  private void jsDrive() {
    x = JS.getRawAxis(0)/2;
    y = JS.getRawAxis(1)/2;
//    lvalue = y - x / 2;
//    rvalue = y + x / 2;
    left1.set(y-x);
    left2.set(y-x);
    left3.set(y-x); 
    right1.set(y+x);
    right2.set(y+x);
    right3.set(y+x); 
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    left1 = new CANSparkMax(12, MotorType.kBrushless);
    left1.restoreFactoryDefaults();
    left1.setInverted(true);
    
    left2 = new WPI_TalonSRX(3);
    configTalon(left2);
    left2.setInverted(true);
    
    left3 = new WPI_TalonSRX(4);
    configTalon(left3);
    left3.setInverted(true);

    right1 = new CANSparkMax(11, MotorType.kBrushless);
    right1.restoreFactoryDefaults();
    right1.setInverted(false);
    
    right2 = new WPI_TalonSRX(1);
    right2.setInverted(false);
    configTalon(right2);
    
    right3 = new WPI_TalonSRX(2);
    right3.setInverted(false);
    configTalon(right3);

    JS = new Joystick(0);
    eStopTriger = new JoystickButton(JS, 1);
    startButton = new JoystickButton(JS, 2);
//    lvalue = y - x / 2;
//    rvalue = y + x / 2;
/*    left = new SpeedControllerGroup(left1, left2, left3);
    right = new SpeedControllerGroup(right1, right2, right3);
    robot = new DifferentialDrive(left, right);
*/
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */



  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}
    
  @Override
  public void teleopPeriodic() {
    jsDrive();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
