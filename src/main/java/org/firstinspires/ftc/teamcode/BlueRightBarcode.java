package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "BlueRightBarcode", group = "main", preselectTeleOp = "cheep cheep beach")
public class BlueRightBarcode extends BaseBarcode{

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
                setTargetPositionOfDrive((int)(-TICKS_PER_INCH * 35.7),(int)(-TICKS_PER_INCH * 35.7));
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                switch (position) {
                    case NOTHING:
                        leftSpool.setTargetPosition(SPOOL_ENCODER_BOTTOM_POSITION);
                        rightSpool.setTargetPosition(SPOOL_ENCODER_BOTTOM_POSITION);
                        leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        break;
                    case LEFT:
                        leftSpool.setTargetPosition(SPOOL_ENCODER_MIDDLE_POSITION);
                        rightSpool.setTargetPosition(SPOOL_ENCODER_MIDDLE_POSITION);
                        leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        break;
                    case RIGHT:
                        leftSpool.setTargetPosition(SPOOL_ENCODER_TOP_POSITION);
                        rightSpool.setTargetPosition(SPOOL_ENCODER_TOP_POSITION);
                        leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        break;
                    default:
                        telemetry.addLine("SOMETHING WENT VERY WRONG!!! STUPID PROGRAMMER DID SOMETHING STUPID");
                        break;
                }
                stepCounter++;
                break;
            case 1:
                drive(0.2, 0.2);
                leftSpool.setPower(0.8);
                rightSpool.setPower(0.8);
                if (timer.seconds() > 4.0) {
                    drive(0.0, 0.0);
                    stepCounter++;
                }
                break;
            case 2:
                setTargetPositionOfDrive(-TICKS_PER_INCH * 26,-TICKS_PER_INCH * 26);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                stepCounter++;
                break;
            case 3:
                drive(0.2, 0.2);
                leftSpool.setPower(0.8);
                rightSpool.setPower(0.8);
                if (timer.seconds() > 2.0) {
                    stepCounter++;
                }
                break;
            case 4:
                setModeOfDrive(DcMotor.RunMode.RUN_USING_ENCODER);
                stepCounter++;
                break;
            case 5:
                if (angles.firstAngle < 62) {
                    drive(-0.1, 0.1);
                } else {
                    drive(0.0, 0.0);
                    stepCounter++;
                    timer.reset();
                }
                break;
            case 6:
                setModeOfDrive(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                setTargetPositionOfDrive((int)(-TICKS_PER_INCH * 9.5),(int)(-TICKS_PER_INCH * 9.5));
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                break;
            case 7:
                drive(0.2, 0.2);
                if (timer.seconds() > 1.0) {
//                    leftSpool.setTargetPosition(SPOOL_ENCODER_TOP_POSITION);
//                    rightSpool.setTargetPosition(SPOOL_ENCODER_TOP_POSITION);
//                    leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    stepCounter++;
                }
                break;
            case 8:

                //timer.reset();
                stepCounter++;
                break;
            case 9:
                timer.reset();
                stepCounter++;
                break;
            case 10:
                setScoopPosition(ScoopPosition.DROP2);
                if (timer.seconds() > 1.5) {
                    drive(0.0, 0.0);
                    timer.reset();
                    stepCounter++;
                }
                break;
            case 11:
                setScoopPosition(ScoopPosition.UP);
                stepCounter++;

                break;
            case 12:
                setTargetPositionOfDrive(TICKS_PER_INCH * 13,TICKS_PER_INCH * 13);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                stepCounter++;
                break;
            case 13:
                drive(0.2, 0.2);
                if (timer.seconds() > 1.0) {
                    stepCounter += 1;
                }
                break;
            case 14:
                leftSpool.setTargetPosition(1000);
                rightSpool.setTargetPosition(1000);
                setModeOfDrive(DcMotor.RunMode.RUN_USING_ENCODER);
                stepCounter += 1;
                break;
            case 15:
                if (angles.firstAngle > -127.5) {
                    leftSpool.setPower(0.5);
                    rightSpool.setPower(0.5);
                    drive(0.08, -0.08);
                } else {
                    drive(0.0, 0.0);
                    stepCounter++;
                    timer.reset();
                }
                break;
            case 16:
                setModeOfDrive(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                driveStraightForInches(28.0);
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

                spinner.setPower(0.45);
                drive(-0.02, -0.02);

                if (timer.seconds() > 4.0) {
                    drive(0.0, 0.0);
                    stepCounter++;
                }
                break;
            case 19:
                setModeOfDrive(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                driveStraightForInches(-28);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                timer.reset();
                break;
            case 20:
                drive(0.2, 0.2);
                if (timer.seconds() > 3.0) {
                    drive(0.0, 0.0);
                    setModeOfDrive(DcMotor.RunMode.RUN_USING_ENCODER);
                    stepCounter++;
                }
                break;
            case 21:
                if (angles.firstAngle < -100) {
                    drive(-0.2, 0.2);
                } else {
                    drive(0.0, 0.0);
                    stepCounter++;
                }
                break;
            case 22:
                setModeOfDrive(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                driveStraightForInches(24);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                timer.reset();
                break;
            case 23:
                drive(0.2, 0.2);
                if (timer.seconds() > 4.0) {
                    drive(0.0, 0.0);
                }
                break;

            default:
                telemetry.addLine("Something went horribly wrong, blame the programmer");
        }
    }
}
