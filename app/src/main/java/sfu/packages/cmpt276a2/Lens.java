package sfu.packages.cmpt276a2;

/**
 * Stores info about the make, maximum aperture, and focal length of a single lens.
 */
public class Lens {
    private String make;
    private double maximumAperture;
    private int focalLength;

    public Lens(String make, double maximumAperture, int focalLength) {
        super();
        this.make = make;
        this.maximumAperture = maximumAperture;
        this.focalLength = focalLength;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getMaximumAperture() {
        return maximumAperture;
    }

    public void setMaximumAperture(double maximumAperture) {
        this.maximumAperture = maximumAperture;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }
}
