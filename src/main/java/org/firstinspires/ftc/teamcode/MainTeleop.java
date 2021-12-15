package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="cheep cheep beach",group = "Main")
public class MainTeleop extends BaseTeleOp{
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        double rightPower;
        double leftPower;
        double drive = -driver.left_stick_y;
        double turn = driver.right_stick_x;
        // right dpad spins intake in
        // left dpad spins intake out
        // right and left bumpers control the lift
        // right up and left down
        // dpad up is spinner ( only one direction)
        // x is lift center
        // y is drop lift
        // a is intake position

        leftPower =  normalize(deadBan( Range.clip(drive + turn, -1.0, 1.0) ));
        rightPower = normalize(deadBan( Range.clip(drive - turn, -1.0, 1.0) ));
        leftFront.setPower(leftPower);
        rightFront.setPower(rightPower);
        leftBack.setPower(leftPower);
        rightBack.setPower(rightPower);



        if (driver.right_bumper) {
            intake.setPower(0.5);
        } else if (driver.left_bumper) {
            intake.setPower(-0.5);
        } else {
            intake.setPower(0.0);
        }

        if(driver.left_trigger> 0){

        }

        if (gunner.right_bumper) {
            rightSpool.setPower(0.5);
            leftSpool.setPower(0.5);
        } else if (gunner.left_bumper) {
            rightSpool.setPower(-0.5);
            leftSpool.setPower(-0.5);
        } else {
            rightSpool.setPower(0);
            leftSpool.setPower(0);
        }

        if (gunner.dpad_up) {
            spinner.setPower(0.5);
        } else if (gunner.dpad_down){
            spinner.setPower(-0.5);
        }
        else{
            spinner.setPower(0.0);
        }

        if(gunner.x) {
            leftGrabby.setPosition(SCOOP_UP);
            rightGrabby.setPosition(SCOOP2_UP);
        }
        else if(gunner.y){
            leftGrabby.setPosition(SCOOP_DROP);
            rightGrabby.setPosition(SCOOP2_DROP);
        }
        else if(gunner.a){
            leftGrabby.setPosition(SCOOP_FORWARD);
            rightGrabby.setPosition(SCOOP2_FORWARD);
        }

    }
}
