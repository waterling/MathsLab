package lab.third.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;

public class CubicSplineOfTenPoints extends CubicSpline {

    public CubicSplineOfTenPoints() {
        super(9);
    }

    @Override
    protected void initializePoints() {
        double x = 0;
        for (int i = 0; i < 10; i++) {
            x += new BigDecimal(random()*0.62).setScale(2, RoundingMode.UP).doubleValue();
            coordinateX[i] = x;
            coordinateY[i] = new BigDecimal(cos(x)).setScale(2, RoundingMode.UP).doubleValue();
        }

    }
}
