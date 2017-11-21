package lab.third.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;

public class CubicSplineOfFourPoints extends CubicSpline {

    public CubicSplineOfFourPoints() {
        super(3);
    }

    protected void initializePoints() {
        double x = 0;
        for (int i = 0; i < 4; i++) {
            x += new BigDecimal(random() * 1.57).setScale(2, RoundingMode.UP).doubleValue();
            coordinateX[i] = x;
            coordinateY[i] = new BigDecimal(cos(x)).setScale(2, RoundingMode.UP).doubleValue();
        }
    }
}