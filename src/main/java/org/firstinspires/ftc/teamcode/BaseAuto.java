package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class BaseAuto extends BaseOpMode {
    BNO055IMU imu;
    protected Orientation angles;

    public static final double TICKS_PER_REVOLUTION = 384.5;
    public static final double WHEEL_DIAMETER = 3.78; // 4.72441 WHERE DID THIS NUMBER COME FROM?
    public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
    public static final int TICKS_PER_INCH = (int)(TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE);
    // public static final double INCHES_PER_TICK = WHEEL_CIRCUMFERENCE / TICKS_PER_REVOLUTION;
    public ElapsedTime timer = new ElapsedTime();

    protected int stepCounter = 0;
    @Override
    public void init() {
        super.init();
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSpool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSpool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        imu = hardwareMap.get(BNO055IMU.class,"imu");
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        setScoopPosition(ScoopPosition.UP);
    }

    @Override
    public void start() {
        super.start();
        timer.reset();
        timer.startTime();
    }

    @Override
    public void loop() {
        super.loop();
    }

    protected void drive(double left, double right) {
        leftFront.setPower(left);
        leftBack.setPower(left);
        rightFront.setPower(right);
        rightBack.setPower(right);
    }

    protected void setTargetPositionOfDrive(int left, int right) {
        leftFront.setTargetPosition(left);
        rightFront.setTargetPosition(right);
        leftBack.setTargetPosition(left);
        rightBack.setTargetPosition(right);

    }

    // THIS ASSUMES THE "FRONT" IS THE LIFT SIDE
    protected void driveStraightForInches(double inches) {
        leftFront.setTargetPosition((int)(-TICKS_PER_INCH*inches));
        rightFront.setTargetPosition((int)(-TICKS_PER_INCH*inches));
        leftBack.setTargetPosition((int)(-TICKS_PER_INCH*inches));
        rightBack.setTargetPosition((int)(-TICKS_PER_INCH*inches));

    }

    protected void setModeOfDrive(DcMotor.RunMode runMode) {
        leftBack.setMode(runMode);
        leftFront.setMode(runMode);
        rightBack.setMode(runMode);
        rightFront.setMode(runMode);
    }



}

