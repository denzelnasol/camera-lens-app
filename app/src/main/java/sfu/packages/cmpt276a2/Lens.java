package sfu.packages.cmpt276a2;

/**
 * Stores info about the make, maximum aperture, and focal length of a single lens.
 */
public class Lens {
    private String make;
    private double maximumAperture;
    private int focalLength;
    private int iconID;

    public Lens(String make, double maximumAperture, int focalLength, int iconID) {
        super();
        this.make = make;
        this.maximumAperture = maximumAperture;
        this.focalLength = focalLength;
        this.iconID = iconID;
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

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

}

