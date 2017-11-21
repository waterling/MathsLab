package lab.third.algorithms;

public class CubicSplineForSinus extends CubicSpline {
    @Override
    public void initializePoints() {
        throw new UnsupportedOperationException();
    }

    public void calc() {
        for (double i = 0; i < 2 * Math.PI; i += 0.01) {
            functionPoints.put(String.format("%.2f", i), Math.cos(i));
        }
    }
}
