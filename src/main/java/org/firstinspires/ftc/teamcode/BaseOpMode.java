package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class BaseOpMode extends OpMode {


    /***
     * PASSWORD TO THE ROBOT CONTROLLER IS: epsilondelta2
     */

    protected DistanceSensor distanceSensor;
    protected DcMotor leftFront;
    protected DcMotor rightFront;
    protected DcMotor leftBack;
    protected DcMotor rightBack;
    protected DcMotor leftSpool;
    protected DcMotor rightSpool;
    protected DcMotor spinner;
    protected DcMotor intake;

    protected Servo leftBox;
    protected Servo rightBox;

    protected Servo leftGrabby;
    protected Servo rightGrabby;
    protected final double SCOOP_DROP = 0.1;
    protected final double SCOOP_UP = 0.5;
    protected final double SCOOP_FORWARD = 0.9;

    // Shallower Drop
    protected final double SCOOP_DROP_2 = 0.13;
    protected final double SCOOP2_DROP_2 = 0.87;

    protected final double SCOOP2_DROP = 0.9;
    protected final double SCOOP2_UP = 0.5;
    protected final double SCOOP2_FORWARD = 0.1;

    protected final double GRABBY_LEFT_TO_UP = 0.5;
    protected final double GRABBY_LEFT_TO_DOWN = 1.0;
    protected final double GRABBY_RIGHT_TO_UP = 0.5;
    protected final double GRABBY_RIGHT_TO_DOWN = 0.0;

    protected final int SPOOL_ENCODER_BOTTOM_POSITION = 1850;

    protected final int SPOOL_ENCODER_TOP_POSITION = 4200;
    protected final int SPOOL_ENCODER_MIDDLE_POSITION = 3100;
    // 1100 maybe
    // 3400 for second height, at DROP (not DROP 2)
    // 4200

    enum ScoopPosition {
        UP, FORWARD, DROP, DROP2
    }

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
        leftBox = hardwareMap.servo.get("leftBox");
        rightBox = hardwareMap.servo.get("rightBox");
        leftGrabby = hardwareMap.servo.get("leftGrabby");
        rightGrabby = hardwareMap.servo.get("rightGrabby");
        distanceSensor = hardwareMap.get(DistanceSensor.class,"distance");

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

        leftSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    @Override
    public void loop() {

    }

    public void setScoopPosition(ScoopPosition position) {
        switch (position) {
            case UP:
                leftBox.setPosition(SCOOP_UP);
                rightBox.setPosition(SCOOP2_UP);
                break;
            case FORWARD:
                leftBox.setPosition(SCOOP_FORWARD);
                rightBox.setPosition(SCOOP2_FORWARD);
                break;
            case DROP:
                leftBox.setPosition(SCOOP_DROP);
                rightBox.setPosition(SCOOP2_DROP);
                break;
            case DROP2:
                leftBox.setPosition(SCOOP_DROP_2);
                rightBox.setPosition(SCOOP2_DROP_2);
                break;
        }
    }
}
