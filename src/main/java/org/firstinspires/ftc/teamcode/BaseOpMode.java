package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class BaseOpMode extends OpMode {
    protected DcMotor leftFront;
    protected DcMotor rightFront;
    protected DcMotor leftBack;
    protected DcMotor rightBack;
    protected DcMotor leftSpool;
    protected DcMotor rightSpool;
    protected DcMotor spinner;
    protected DcMotor intake;
    protected Servo leftGrabby;
    protected Servo rightGrabby;
    protected Servo leftBox;
    protected Servo rightBox;
    protected final double SCOOP_DROP = 0.1;
    protected final double SCOOP_UP = 0.5;
    protected final double SCOOP_FORWARD = 0.9;
    protected final double SCOOP2_DROP = 0.9;
    protected final double SCOOP2_UP = 0.5;
    protected final double SCOOP2_FORWARD = 0.1;


    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftSpool = hardwareMap.dcMotor.get("leftSpool");
        rightSpool = hardwareMap.dcMotor.get("rightSpool");
        spinner = hardwareMap.dcMotor.get("spinner");
        intake = hardwareMap.dcMotor.get("intake");
        leftGrabby = hardwareMap.servo.get("leftGrabby");
        rightGrabby = hardwareMap.servo.get("rightGrabby");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftSpool.setDirection(DcMotorSimple.Direction.FORWARD);
        rightSpool.setDirection(DcMotorSimple.Direction.REVERSE);
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSpool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSpool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    @Override
    public void loop() {

    }
}
