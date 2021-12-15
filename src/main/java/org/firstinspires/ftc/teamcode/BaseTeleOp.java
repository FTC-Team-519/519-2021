package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class BaseTeleOp extends BaseOpMode{
    protected Gamepad driver = null;
    protected Gamepad gunner = null;

    @Override
    public void init() {
        super.init();
        driver = gamepad1;
        gunner = gamepad2;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {

    }
    public double deadBan(double power){
        if(power<0.1 && power>-0.1){
            return 0;
        }
        else {
            return power;
        }
    }
    public double normalize(double startValue){
        if(startValue>0){
            startValue = Math.pow(startValue,2);
        }
        else{
            startValue = -1*(Math.pow(startValue,2));
        }
        return startValue;
    }
}
