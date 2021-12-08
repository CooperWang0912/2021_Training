package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Drive {
    public static CANSparkMax left1;
    public static WPI_TalonSRX left2;
    public static WPI_TalonSRX left3;
    public static CANSparkMax right1;
    public static WPI_TalonSRX right2;
    public static WPI_TalonSRX right3;
    public static Joystick JS;
    private void setSpark(final CANSparkMax spark){
        spark.restoreFactoryDefaults();
    }
    private void configTalon(WPI_TalonSRX talon){
        talon.configFactoryDefault();
        talon.setNeutralMode(NeutralMode.Brake);
    }

    public double x = JS.getRawAxis(0)/2;
    public double y = JS.getRawAxis(1)/2;
    // public CANSparkMax right1;
    public Drive(){
        setSpark(left1);
        left1.setInverted(false);
        left2 = new WPI_TalonSRX(3);
        left2.setInverted(false);
        left3 = new WPI_TalonSRX(4);
        left3.setInverted(false);
    
        setSpark(right1);
        right1.setInverted(true);
        right2 = new WPI_TalonSRX(3);
        right2.setInverted(true);
        right3 = new WPI_TalonSRX(4);
        right3.setInverted(true);
    }
    
    


    public void move(){
        x = JS.getRawAxis(0)/2;
        y = JS.getRawAxis(1)/2;
        left1.set(x+y);
        right1.set(y-x);
        left2.set(x+y);
        right2.set(y-x);
        left3.set(x+y);
        right3.set(y-x);
    }


}
