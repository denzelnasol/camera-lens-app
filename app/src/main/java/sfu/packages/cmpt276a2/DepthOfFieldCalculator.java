package sfu.packages.cmpt276a2;

/**
 * Compute depth field values when given a lens and info about camera's settings
 */
public class DepthOfFieldCalculator {


    public double hyperFocalDistance(double aperture, double COC, double focalLength) {
        return ((focalLength * focalLength) / (aperture * COC));
    }

    public double nearFocalPoint(double hyperFocalPoint, double distance, double focalLength) {
        return ((convertToMM(distance) * (hyperFocalPoint - focalLength)) / (hyperFocalPoint + convertToMM(distance) - 2 * focalLength)) / 1000;
    }

    public double farFocalPoint(double hyperFocalPoint, double distance, double focalLength) {
        double result = ((convertToMM(distance) * (hyperFocalPoint - focalLength)) / (hyperFocalPoint - convertToMM(distance))) / 1000;
        if (result < 0 || result > hyperFocalPoint) {
            return Double.POSITIVE_INFINITY;
        }
        return result;
    }

    public double depthOfField(double farFocalPoint, double nearFocalPoint) {
        return (farFocalPoint - nearFocalPoint);
    }

    public double convertToMM(double meters) {
        return (meters * 1000);
    }
}
