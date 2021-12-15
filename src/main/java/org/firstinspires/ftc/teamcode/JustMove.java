package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@TeleOp(name="something offensive", group="Iterative Opmode")
public class JustMove extends OpMode {
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    private static final String VUFORIA_KEY =
            "AVjWNQH/////AAABmfTAg894fEL/rQj8b+u8l7Qw34HtrMgOnmf6xTlvK+Afn5EmrjzwTJ7/aTw0eGzNWdd0u+f1Rv8T8gH+kytJmYIPDIKOiLHuHJvMc0lwvEgKfiE33bZAoGW/ZoX2kyIHVWgr9I2yNKtE/SS4Ik4imJIJbe4QwFBMed02dz05R+j6Oi3wW4CutaknKYb5BH68RviV8b98QDV6FUwLa0u+biIkAEciicgHoQuDWCA2hrByaIEEm4XgXCF0H37hyv0Ra7SZsm6YMcTC2mNSIblMD77iL7MFyUoFdoQnykv+KJiNelhdjfswwCQWszNLYpqzwo56nAimSAr8s4C7Cub1GAlYVfq5XnG/7ZWH0oSg1x8T";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private static final double TICS_PER_REV = 1425.1;
    private static final double WHEEL_CIRCUMFERENCE= 3.75 * Math.PI;
    private static final double TICS_PER_INCH = TICS_PER_REV / WHEEL_CIRCUMFERENCE;
    private static final double INCHES_PER_TIC = WHEEL_CIRCUMFERENCE / TICS_PER_REV;
    private DcMotor left_drive= null;
    private DcMotor right_drive= null;
    private boolean positionIs1 = false;
    private boolean positionIs2 = false;
    private boolean positionIs3 = false;

    public void init() {
        left_drive = hardwareMap.get(DcMotor.class, "left_drive");
        right_drive = hardwareMap.get(DcMotor.class, "right_drive");

        left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left_drive.setTargetPosition((int)INCHES_PER_TIC);
        right_drive.setTargetPosition((int) INCHES_PER_TIC);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_drive.setPower(0.5);
        right_drive.setPower(0.5);

        initVuforia();
        initTfod();
        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1.25, 16.0 / 9.0);
        }
    }
    public void loop(){

            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                int i = 0;
                int counter = 0;
                double center = 0;
                for (Recognition recognition : updatedRecognitions) {
                    telemetry.addData(String.format("label (%d)", i), recognition.getLabel());

                    /*telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                            recognition.getLeft(), recognition.getTop());
                    telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                            recognition.getRight(), recognition.getBottom()); */

                    center = ( ( recognition.getLeft() + recognition.getRight() ) /2 );

                    if (recognition.getLabel().equals("Duck") || recognition.getLabel().equals("Cube")) {

                        if(center<=320){
                            telemetry.addLine("Position: 2");
                            positionIs2 = true;
                        }
                        else if(center>320){
                            telemetry.addLine("Position: 3");
                            positionIs3 = true;
                        }
                        else{
                            telemetry.addLine("Position: 1");
                            positionIs1 = true;
                        }
                    }
                }


            }
            if (!positionIs2 && !positionIs3) {
                telemetry.addLine("Position: 1");
                positionIs1 = true;
            }
        }

    public int getPosition(){
        if(positionIs1){
            return 1;
        }
        else if(positionIs2){
            return 2;
        }
        else{
            return 3;
        }
    }
    private void initVuforia(){
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}

