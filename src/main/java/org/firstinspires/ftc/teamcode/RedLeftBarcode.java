package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "RedLeftBarcode", group = "main", preselectTeleOp = "cheep cheep beach")

public class RedLeftBarcode extends BaseBarcode{

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void init_loop() {
        super.init_loop();
    }

    @Override
    public void loop() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        telemetry.addData("Z", angles.firstAngle);
        telemetry.addData("Y", angles.secondAngle);
        telemetry.addData("X", angles.thirdAngle);

        switch (stepCounter) {
            case 0:
                setTargetPositionOfDrive(-TICKS_PER_INCH * 36,-TICKS_PER_INCH * 36);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                break;
            case 1:
                drive(0.2, 0.2);
                if (timer.seconds() > 4.0) {
                    stepCounter++;
                }
                break;
            case 2:
                setTargetPositionOfDrive(-TICKS_PER_INCH * 29,-TICKS_PER_INCH * 29);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                stepCounter++;
                break;
            case 3:
                drive(0.2, 0.2);
                if (timer.seconds() > 2.0) {
                    stepCounter++;
                }
                break;
            case 4:
                setModeOfDrive(DcMotor.RunMode.RUN_USING_ENCODER);
                stepCounter++;
                break;
            case 5:
                if (angles.firstAngle > -70) {
                    drive(0.1, -0.1);
                } else {
                    drive(0.0, 0.0);
                    stepCounter++;
                    timer.reset();
                }
                break;
            case 6:
                setModeOfDrive(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                setTargetPositionOfDrive((int)(-TICKS_PER_INCH * 11.0),(int)(-TICKS_PER_INCH * 11.0));
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                break;
            case 7:
                drive(0.2, 0.2);
                if (timer.seconds() > 1.0) {
                    leftSpool.setTargetPosition(SPOOL_ENCODER_TOP_POSITION);
                    rightSpool.setTargetPosition(SPOOL_ENCODER_TOP_POSITION);
                    leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    stepCounter++;
                }
                break;
            case 8:
                switch (position) {
                    case LEFT:
                        leftSpool.setTargetPosition(SPOOL_ENCODER_BOTTOM_POSITION);
                        rightSpool.setTargetPosition(SPOOL_ENCODER_BOTTOM_POSITION);
                        leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        break;
                    case RIGHT:
                        leftSpool.setTargetPosition(SPOOL_ENCODER_MIDDLE_POSITION);
                        rightSpool.setTargetPosition(SPOOL_ENCODER_MIDDLE_POSITION);
                        leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        break;
                    case NOTHING:
                        leftSpool.setTargetPosition(SPOOL_ENCODER_TOP_POSITION);
                        rightSpool.setTargetPosition(SPOOL_ENCODER_TOP_POSITION);
                        leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        break;
                    default:
                        telemetry.addLine("SOMETHING WENT VERY WRONG!!! STUPID PROGRAMMER DID SOMETHING STUPID");
                        break;
                }
                timer.reset();
                stepCounter++;
                break;
            case 9:
                leftSpool.setPower(1.0);
                rightSpool.setPower(1.0);
                if (timer.seconds() > 2) {
                    timer.reset();
                    stepCounter++;
                }
                break;
            case 10:
                setScoopPosition(ScoopPosition.DROP2);
                if (timer.seconds() > 1.5) {
                    timer.reset();
                    stepCounter++;
                }
                break;
            case 11:
                setScoopPosition(ScoopPosition.UP);
                stepCounter++;

                break;
            case 12:
                setTargetPositionOfDrive(TICKS_PER_INCH * 4,TICKS_PER_INCH * 4);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                stepCounter++;
                break;
            case 13:
                drive(0.2, 0.2);
                if (timer.seconds() > 1.0) {
                    stepCounter++;
                }
                break;
            case 14:
                leftSpool.setTargetPosition(1000);
                rightSpool.setTargetPosition(1000);
                setModeOfDrive(DcMotor.RunMode.RUN_USING_ENCODER);
                stepCounter += 1;
                break;
            case 15:
                if (angles.firstAngle < 135) {
                    leftSpool.setPower(0.5);
                    rightSpool.setPower(0.5);
                    drive(-0.1, 0.1);
                } else {
                    drive(0.0, 0.0);
                    stepCounter++;
                    timer.reset();
                }
                break;
            case 16:
                setModeOfDrive(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                setTargetPositionOfDrive((int)(-TICKS_PER_INCH * 31.0),(int)(-TICKS_PER_INCH * 31.0));
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                break;
            case 17:
                drive(0.2, 0.2);
                if (timer.seconds() > 3.0) {
                    stepCounter++;
                    timer.reset();
                }
                break;
            case 18:
                spinner.setPower(-0.5);
                if (timer.seconds() > 3.5) {
                    spinner.setPower(0.0);
                    stepCounter++;
                }
                break;
            case 19:
                setModeOfDrive(DcMotor.RunMode.RUN_USING_ENCODER);
                timer.reset();
                stepCounter++;
                break;
            case 20:
                if (angles.firstAngle < 170) {
                    drive(-0.05, 0.1);
                } else {
                    drive(0.0, 0.0);
                    stepCounter++;
                    timer.reset();
                    setModeOfDrive(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                }
                break;
            case 21:
                setTargetPositionOfDrive(TICKS_PER_INCH * 16,TICKS_PER_INCH * 16);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                timer.reset();
                break;
            case 22:
                drive(0.25, 0.25);
                if (timer.seconds() > 2) {
                    stepCounter++;
                    setModeOfDrive(DcMotor.RunMode.RUN_USING_ENCODER);
                    stepCounter++;
                }
                break;
            case 23:
                if ((angles.firstAngle > 90 && angles.firstAngle < 180) ||(angles.firstAngle < 80 && angles.firstAngle > -180)) {
                    drive(0.0, 0.3);
                } else {
                  drive(0.0, 0.0);
                  stepCounter++;
                }
                break;
            default:
                telemetry.addLine("SOMETHING WENT VERY WRONG!!! STUPID PROGRAMMER DID SOMETHING STUPID");
        }
    }
}
