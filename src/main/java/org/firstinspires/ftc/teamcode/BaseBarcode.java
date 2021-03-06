package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class BaseBarcode extends BaseAuto {
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

    protected enum Position {
        LEFT, RIGHT, NOTHING
    }

    Position position = Position.NOTHING;

    private boolean positionIs1 = false;
    private boolean positionIs2 = false;
    private boolean positionIs3 = false;

    @Override
    public void init() {
        super.init();
        initVuforia();
        initTfod();
        if (tfod != null) {
            tfod.activate();
            //tfod.setClippingMargins();
            tfod.setZoom(1.00, 16.0 / 9.0);
        }

    }

    @Override
    public void init_loop() {
        super.init_loop();
        telemetry.addData("Distance: ", distanceSensor.getDistance(DistanceUnit.INCH));

        // will return null if the last call has the same recognitions as this one
        // this is the reason that the console flickers the telemetry updates sometimes
        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null) {
            telemetry.addData("# Object Detected", updatedRecognitions.size());
            int i = 0;
            int counter = 0;

            for (Recognition recognition : updatedRecognitions) {
                telemetry.addData(String.format("label (%d)", i), recognition.getLabel());

                /*telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                        recognition.getLeft(), recognition.getTop());
                telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                        recognition.getRight(), recognition.getBottom()); */

                double center = ((recognition.getLeft() + recognition.getRight()) / 2);

                if (recognition.getLabel().equals("Duck") || recognition.getLabel().equals("Cube")) {

                if (center <= 320) {
                    telemetry.addLine("Position: 1");
                    positionIs1 = true;
                    position = Position.LEFT;
                } else if (center > 320) {
                    telemetry.addLine("Position: 2");
                    positionIs2 = true;
                    position = Position.RIGHT;
                }

                }
            }

            if (updatedRecognitions.size() == 0) {
                position = Position.NOTHING;
            }
        }// else {
//            telemetry.addLine("Position: 3");
//            positionIs3 = true;
//            position = Position.NOTHING;
//        }
        telemetry.addData("Position: ", position.toString());
        telemetry.addData("Distance Sensor: ", distanceSensor.getDistance(DistanceUnit.INCH));
    }

    public Position getPosition() {
        return position;
    }

    private void initVuforia() {
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

