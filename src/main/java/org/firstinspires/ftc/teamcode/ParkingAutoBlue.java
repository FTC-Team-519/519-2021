package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


@Autonomous(name = "blue side park",group = "main")
public class ParkingAutoBlue extends BaseAuto {


    @Override

    public void init() {
        super.init();
        timer.reset();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        // move forward from starting position by
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        switch (stepCounter){
            case 0:
//                leftFront.setTargetPosition((int) (TICKS_PER_INCH * 15));
//                rightFront.setTargetPosition((int) (TICKS_PER_INCH * 15));
//                leftBack.setTargetPosition((int) (TICKS_PER_INCH * 15));
//                rightBack.setTargetPosition((int) (TICKS_PER_INCH * 15));
                timer.reset();
                stepCounter++;
                break;
            case 1:
                if (timer.seconds()>=0.75) {
                    leftFront.setPower(0.0);
                    rightFront.setPower(0.0);
                    leftBack.setPower(0.0);
                    rightBack.setPower(0.0);
                    stepCounter++;
                    timer.reset();

                }
                else {
                    leftFront.setPower(0.5);
                    rightFront.setPower(0.5);
                    leftBack.setPower(0.5);
                    rightBack.setPower(0.5);
                }
                break;
            case 2:
                leftFront.setPower(0.0);
                rightFront.setPower(0.0);
                leftBack.setPower(0.0);
                rightBack.setPower(0.0);
                if(timer.seconds()>=0.25){
                    stepCounter++;
                }
            case 3:
                if (angles.firstAngle < 90) {
                    leftFront.setPower(-0.1);
                    leftBack.setPower(-0.1);
                    rightFront.setPower(0.1);
                    rightBack.setPower(0.1);
                }
                else {
                    leftFront.setPower(0.0);
                    leftBack.setPower(0.0);
                    rightFront.setPower(0.0);
                    rightBack.setPower(0.0);
                    timer.reset();

                    stepCounter++;
                }
                break;

            case 4:
                if(timer.seconds()>=3){
                    stepCounter++;
                }
                break;
            case 5:
//                timer.reset();
//                timer.startTime();
                if (timer.seconds()>=2.5) {
                    leftFront.setPower(0.0);
                    rightFront.setPower(0.0);
                    leftBack.setPower(0.0);
                    rightBack.setPower(0.0);
                    stepCounter++;
                    timer.reset();

                }
                else {
                    leftFront.setPower(0.5);
                    rightFront.setPower(0.5);
                    leftBack.setPower(0.5);
                    rightBack.setPower(0.5);
                }
                break;
            default:
                timer.reset();
                leftFront.setPower(0.0);
                leftBack.setPower(0.0);
                rightFront.setPower(0.0);
                rightBack.setPower(0.0);
                break;


        }

        telemetry.addData("time: ", timer.seconds());
        telemetry.addData("step",stepCounter);
        telemetry.addData("z",angles.firstAngle);
        telemetry.addData("y",angles.secondAngle);
        telemetry.addData("x",angles.thirdAngle);

//
    }
}
