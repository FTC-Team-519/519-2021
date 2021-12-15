
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "Parking Auto Red", group = "main")
public class ParkingAutoRed extends BaseAuto{

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
        timer.reset();
    }

    @Override
    public void loop() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
        switch(stepCounter) {
            case 0:
                setTargetPositionOfDrive((int)TICKS_PER_INCH * 17, (int)TICKS_PER_INCH * 17);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                stepCounter++;
                break;

            case 1:
                if (timer.seconds() > 4.0) {
                    timer.reset();
                    stepCounter++;

                } else {
                    drive(0.25, 0.25);
                }
                break;

            case 2:
                //setTargetPositionOfDrive((int)TICKS_PER_INCH * 15, (int)TICKS_PER_INCH * -15);
                setModeOfDrive(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                stepCounter++;
                break;

            case 3:
                if(angles.firstAngle<90) {
                    drive(0.25, -0.25);
                }
                else{
                    drive(0,0);
                    stepCounter++;
                }
                break;

            case 4:
                if(timer.seconds()>3.0) {
                    timer.reset();
                    stepCounter++;
                }
                else{
                    drive(0.25,0.25);
                }
                break;
            case 5:
                setTargetPositionOfDrive((int)TICKS_PER_INCH * 90, (int)TICKS_PER_INCH * 90);
                setModeOfDrive(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                break;

            case 6:
                if(timer.seconds()>8.0) {
                    timer.reset();
                    stepCounter++;
                }
                else{
                    drive(0.25,0.25);
                }
                break;

            default:
                drive(0.0,0.0);
                break;

        }
        telemetry.addData("TIMER: ", timer.seconds());
        telemetry.addData("STEP: ", stepCounter);

    }


}
