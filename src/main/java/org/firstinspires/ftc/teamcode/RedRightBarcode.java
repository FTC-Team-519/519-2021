package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "RedRightBarcode", group = "main", preselectTeleOp = "cheep cheep beach")
public class RedRightBarcode extends BaseBarcode{

    // For the purpose of testing the spool encoders as of right now.
    // NOT an actual autonomous routine
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        switch (stepCounter) {
            case 0:
                leftSpool.setTargetPosition(SPOOL_ENCODER_BOTTOM_POSITION);
                rightSpool.setTargetPosition(SPOOL_ENCODER_BOTTOM_POSITION);
                leftSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightSpool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                stepCounter++;
                break;
            case 1:
                leftSpool.setPower(0.5);
                rightSpool.setPower(0.5);
                if (leftSpool.getCurrentPosition() > 1500) {
                    stepCounter++;
                }
                break;
            case 2:
                setScoopPosition(ScoopPosition.DROP);
                // do things. The only thing that position should be required for is the height that we are trying to find.;
                break;
        }

    }
}
